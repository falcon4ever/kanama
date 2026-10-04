package net.multigesture.kanama.binding

import java.lang.foreign.MemorySegment
import java.lang.ref.WeakReference
import net.multigesture.kanama.api.RefCounted
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.OwnedReleaseCleaner
import net.multigesture.kanama.binding.runtime.PendingRelease
import net.multigesture.kanama.binding.runtime.ReleaseHook

/**
 * A Kotlin script object on a `RefCounted` owner keeps its owner alive, as holding the script
 * object does in GDScript (task 132 blocker 1). Without this, a script object reached through
 * `kotlinScriptInstance<T>()` or `newScriptInstance<T>().instance` outlived its owner as soon as
 * the wrappers that held the owner's references were closed or collected.
 *
 * C#'s model (`CSharpInstance`, Godot's `modules/mono/csharp_script.cpp`), for a script object that
 * extends `KanamaScript`:
 * - the instance holds one `+1` of its own on the owner, taken when the instance is created;
 * - the native side's link to the Kotlin objects (the [ScriptOwnerLink] that `ObjectRegistry` and
 *   `ScriptBridge` keep) is STRONG while the owner's reference count is above 1 -- someone besides
 *   the script object holds it, so the engine may call into the instance at any time -- and WEAK at
 *   exactly 1, when only the script object's `+1` is left. `refcount_incremented` /
 *   `refcount_decremented` switch it, as `CSharpInstance::refcount_incremented/decremented` swap
 *   the GC handle;
 * - the script object anchors its instance ([net.multigesture.kanama.api.KanamaScript]'s anchor
 *   field), so while game code can reach the script object the instance stays alive, and when it
 *   cannot, a cleanup registered on the script object queues the release of the owner `+1` through
 *   [net.multigesture.kanama.binding.runtime.OwnedReleases] (main thread, next frame); the owner
 *   then dies like any unreferenced `RefCounted`.
 * - if the engine re-references the owner after the GC collected the script object but before that
 *   release ran (a `ResourceCache` hit), the instance is recreated from the script's factory, as
 *   `CSharpInstance::_internal_new_managed` does; its property values start from their defaults,
 *   and a warning says so.
 *
 * A plain script class (one that does not extend `KanamaScript`) has no field the runtime can
 * anchor its instance in, so its native link cannot go weak safely and it takes no `+1`. Instead
 * its owner keeps the lifetime it had before task 132's GC fallback: the fallback never releases an
 * owned wrapper of an owner with a plain script instance ([OwnedReleases] parks that release until
 * the script is detached, or until shutdown), so a script object kept without its resource stays
 * usable. Closing the wrappers still releases at once. Extend `KanamaScript` to get the full model.
 */
internal class ScriptOwnerLink(
  si: KanamaScriptInstance,
  val owner: MemorySegment,
  val instanceId: Long,
  val script: KanamaScript?,
  /** True for a `KanamaScript` object: the link holds a +1 and may go weak (class comment). */
  val anchored: Boolean,
) : ReleaseHook {
  @Volatile private var strongInstance: KanamaScriptInstance? = si
  @Volatile private var weakInstance: WeakReference<KanamaScriptInstance> = WeakReference(si)

  /** Whether the instance's own `+1` on the owner is held (taken, not yet released). */
  @Volatile
  var holdsOwnerRef: Boolean = false
    private set

  /** The cleanup that releases the owner `+1` once the script object is unreachable. */
  @Volatile var pending: PendingRelease? = null

  fun instance(): KanamaScriptInstance? = strongInstance ?: weakInstance.get()

  val isWeak: Boolean
    get() = strongInstance == null

  @Synchronized
  fun takeOwnerRef(): Boolean {
    if (holdsOwnerRef) return false
    holdsOwnerRef = RefCounted.retainHandle(owner)
    return holdsOwnerRef
  }

  /** Back to STRONG; false when the GC already collected the instance. */
  @Synchronized
  fun makeStrong(): Boolean {
    if (strongInstance != null) return true
    val si = weakInstance.get() ?: return false
    strongInstance = si
    return true
  }

  @Synchronized
  fun makeWeak() {
    if (!anchored) return
    strongInstance = null
  }

  @Synchronized
  fun replaceInstance(si: KanamaScriptInstance) {
    strongInstance = si
    weakInstance = WeakReference(si)
  }

  // The drain is about to release the owner +1 this link holds.
  @Synchronized
  override fun beforeRelease(): Boolean {
    if (!holdsOwnerRef) return false
    holdsOwnerRef = false
    return true
  }
}

internal object ScriptOwnerLinks {
  private val enabled: Boolean =
    System.getenv("KANAMA_SCRIPT_OWNER_LINKS")?.trim()?.lowercase() !in setOf("0", "false", "off")

  private const val GET_REFERENCE_COUNT_HASH = 3905245786L

  private val getReferenceCountBind by lazy {
    ObjectCalls.getMethodBind("RefCounted", "get_reference_count", GET_REFERENCE_COUNT_HASH)
  }

  private fun referenceCount(owner: MemorySegment): Int =
    ObjectCalls.ptrcallNoArgsRetInt(getReferenceCountBind, owner)

  /**
   * The registry value for a new instance [si] on [owner]: a [ScriptOwnerLink] for a `RefCounted`
   * owner (bit 63 of the instance id, `ObjectID::is_ref_counted`), else [si] itself.
   */
  fun linkFor(si: KanamaScriptInstance, owner: MemorySegment, script: KanamaScript?): Any {
    val instanceId = ObjectCalls.objectGetInstanceId(owner)
    if (instanceId >= 0L) return si
    val kotlinObject = si.kotlinObject
    // No cleaner (Android before API 33): no instance +1 either, the pre-task-132 lifetime.
    // KANAMA_SCRIPT_OWNER_LINKS=0 does the same (a measurement knob: the smoke's red run).
    if (!OwnedReleaseCleaner.available || !enabled) return si
    val anchorable = kotlinObject is net.multigesture.kanama.api.KanamaScript<*>
    val link = ScriptOwnerLink(si, owner, instanceId, script, anchorable)
    if (anchorable) adopt(link, si) else plainScriptOwners += owner.address()
    return link
  }

  // Takes the instance's +1, anchors the instance in the script object and registers the cleanup
  // on the script object; the link starts STRONG and goes weak when the count drops to 1.
  private fun adopt(link: ScriptOwnerLink, si: KanamaScriptInstance) {
    val kotlinObject = si.kotlinObject as net.multigesture.kanama.api.KanamaScript<*>
    if (!link.takeOwnerRef()) return
    val release = PendingRelease(link.owner, link.instanceId, null, link)
    link.pending = release
    val registration = OwnedReleaseCleaner.register(kotlinObject, release)
    kotlinObject.kanamaInstanceAnchor = ScriptInstanceAnchor(si, registration)
    if (referenceCount(link.owner) <= 1) link.makeWeak()
  }

  // Owners whose script object is a plain class: the GC fallback parks their owned-wrapper
  // releases (see ScriptOwnerLink). Written on whatever thread creates/frees the instance.
  private val plainScriptOwners = java.util.concurrent.ConcurrentHashMap.newKeySet<Long>()

  fun ownerHasPlainScript(address: Long): Boolean = address in plainScriptOwners

  /** `refcount_incremented`: the engine took another reference; go STRONG above 1. */
  fun incremented(handle: Long, link: ScriptOwnerLink) {
    if (!link.anchored || !link.isWeak) return
    if (referenceCount(link.owner) <= 1) return
    if (link.makeStrong()) return
    recreate(handle, link)
  }

  /** `refcount_decremented`: true when the owner may die (count 0). WEAK at exactly 1. */
  fun decremented(link: ScriptOwnerLink): Boolean {
    if (!link.holdsOwnerRef) return true
    val count = referenceCount(link.owner)
    if (count == 0) return true
    if (count == 1 && link.anchored && link.holdsOwnerRef) link.makeWeak()
    return false
  }

  // The script object was collected but the engine referenced the owner again before the queued
  // release ran: rebuild the instance from the script's factory (its property values reset), with
  // a +1 and a cleanup of its own. The old release stays queued and drops the old +1.
  private fun recreate(handle: Long, link: ScriptOwnerLink) {
    val script = link.script ?: return
    val fresh = runCatching { script.factory?.invoke(link.owner) }.getOrNull() ?: return
    fresh.script = script
    val replacement = ScriptOwnerLink(fresh, link.owner, link.instanceId, script, anchored = true)
    adopt(replacement, fresh)
    replacement.makeStrong()
    ObjectRegistry.replace(handle, replacement)
    ScriptBridge.retrackOwner(link.owner, replacement, fresh.kotlinObject)
    OwnedReleaseCleaner.warn(
      "The ${script.kotlinClassName} script object of a resource was collected while the engine " +
        "re-referenced the resource; its script instance was recreated and its property values " +
        "reset. Keep a reference to the script object (or the resource) while you use it."
    )
  }

  /**
   * `free` of an instance whose owner is NOT dying (the script was detached or replaced): its `+1`
   * must still be dropped, but not from inside the engine's `set_script`, so it is queued for the
   * next frame's drain. In the dying path the drain already released it.
   */
  fun freed(link: ScriptOwnerLink) {
    if (!link.anchored) plainScriptOwners -= link.owner.address()
    if (!link.holdsOwnerRef) return
    val pending = link.pending ?: return
    // tryDisarm false: the cleanup already queued this release.
    if (pending.tryDisarm()) {
      OwnedReleaseCleaner.enqueue(PendingRelease(link.owner, link.instanceId, null, link))
    }
  }
}

/** What a `KanamaScript` object anchors: its instance, and the cleanup registered on it. */
internal class ScriptInstanceAnchor(val instance: Any, val registration: Any?)
