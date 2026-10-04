package net.multigesture.kanama.binding

import java.lang.foreign.MemorySegment
import java.lang.ref.WeakReference
import net.multigesture.kanama.api.RefCounted
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.OwnedReleaseCleaner
import net.multigesture.kanama.binding.runtime.OwnedReleases
import net.multigesture.kanama.binding.runtime.PendingRelease
import net.multigesture.kanama.binding.runtime.ReleaseHook
import net.multigesture.kanama.binding.runtime.ScriptOwnerIds

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
 *   release ran (a `ResourceCache` hit), the Kotlin object -- and the property values it held -- is
 *   gone, so the instance is rebuilt from the script's factory, as
 *   `CSharpInstance::_internal_new_managed` does, and for a resource saved to a file refilled from
 *   an uncached load of that file: what GDScript does there (the resource died at count 0 and the
 *   cache hit re-parses the file). Both happen on the instance's first use
 *   ([ScriptOwnerLinks.materialize]), never in the callback itself: the engine calls
 *   `refcount_incremented` under its ResourceCache lock and `ResourceLoader` mutex, where neither
 *   the script's constructor nor a nested load may run. A free with no use builds nothing. A
 *   resource with no file, or a sub-resource stored inside another file, keeps its defaults, with a
 *   warning. Owner-link releases run first in each drain, so the window is at most until the next
 *   frame.
 *
 * A plain script class (one that does not extend `KanamaScript`) has no field the runtime can
 * anchor its instance in, so its native link cannot go weak safely and it takes no `+1`. Instead
 * its owner keeps the lifetime it had before task 132's GC fallback: the fallback never releases an
 * owned wrapper of an owner with a plain script instance ([OwnedReleases] parks that release until
 * the script is detached, or until shutdown), so a script object kept without its resource stays
 * usable. Closing the wrappers still releases at once. Extend `KanamaScript` to get the full model.
 */
internal class ScriptOwnerLink(
  si: KanamaScriptInstance?,
  val owner: MemorySegment,
  val instanceId: Long,
  val script: KanamaScript?,
  /** True for a `KanamaScript` object: the link holds a +1 and may go weak (class comment). */
  val anchored: Boolean,
) : ReleaseHook {
  @Volatile private var strongInstance: KanamaScriptInstance? = si
  @Volatile private var weakInstance: WeakReference<KanamaScriptInstance?> = WeakReference(si)

  /** Whether the instance's own `+1` on the owner is held (taken, not yet released). */
  @Volatile
  var holdsOwnerRef: Boolean = false
    private set

  /** The cleanup that releases the owner `+1` once the script object is unreachable. */
  @Volatile var pending: PendingRelease? = null

  /** A rebuilt instance's file, refilled on first use ([ScriptOwnerLinks.materialize]). */
  @Volatile var pendingRefill: String? = null

  /**
   * True for a link whose instance must be rebuilt (its script object was collected while the
   * engine re-referenced the owner): the first use builds it ([ScriptOwnerLinks.materialize]).
   */
  @Volatile var needsBuild: Boolean = false

  /** Installs a built instance (STRONG; the count decides from then on). */
  @Synchronized
  fun install(si: KanamaScriptInstance) {
    strongInstance = si
    weakInstance = WeakReference(si)
  }

  /** The free path: forget any rebuild or refill still owed (the owner is going away). */
  @Synchronized
  fun abandon() {
    needsBuild = false
    pendingRefill = null
  }

  /**
   * The free path: let go of the instance. The cleanup registered on the script object reaches this
   * link through its release's hook, so a link still STRONG here (the owner lives on: its script
   * was detached or replaced) would keep the script object -- and everything its properties hold --
   * reachable from the cleaner forever. Returns the instance it held, if any.
   */
  @Synchronized
  fun detach(): KanamaScriptInstance? {
    val si = instance()
    strongInstance = null
    weakInstance = WeakReference(null)
    return si
  }

  fun instance(): KanamaScriptInstance? = strongInstance ?: weakInstance.get()

  val isWeak: Boolean
    get() = strongInstance == null

  @Synchronized
  fun takeOwnerRef(): Boolean {
    if (holdsOwnerRef) return false
    holdsOwnerRef = RefCounted.retainHandle(owner)
    return holdsOwnerRef
  }

  /** Test seam: the +1 [takeOwnerRef] would take, without an engine. */
  @Synchronized
  internal fun markOwnerRefHeldForTest() {
    holdsOwnerRef = true
  }

  /**
   * `refcount_incremented`'s decision, under this link's lock with the count read inside it, so a
   * concurrent [onDecremented] cannot interleave between the read and the switch. False when the
   * instance must be rebuilt (STRONG was due but the GC already collected it).
   */
  @Synchronized
  fun onIncremented(count: () -> Int): Boolean {
    if (!anchored || !holdsOwnerRef) return true
    return sync(count())
  }

  /** `refcount_decremented`'s decision (same lock): true when the owner may die (count 0). */
  @Synchronized
  fun onDecremented(count: () -> Int): Boolean {
    if (!holdsOwnerRef) return true
    val current = count()
    if (current == 0) return true
    if (anchored) sync(current)
    return false
  }

  // Both callbacks set the state from the count they read under the lock, so whichever runs last
  // after the last count change leaves it right: STRONG above 1, WEAK at 1. False when STRONG is
  // due but the GC already collected the instance (it must be rebuilt).
  private fun sync(current: Int): Boolean {
    if (current <= 1) {
      strongInstance = null
      return true
    }
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
  fun makeStrong() {
    if (strongInstance == null) strongInstance = weakInstance.get()
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
  // KANAMA_SCRIPT_OWNER_LINKS=0 turns the owner links off alone (a measurement knob: the smoke's
  // red run); KANAMA_GC_RELEASES=0 turns them off too, with the rest of the GC fallback.
  private val enabled: Boolean =
    System.getenv("KANAMA_SCRIPT_OWNER_LINKS")?.trim()?.lowercase() !in setOf("0", "false", "off")

  private const val GET_REFERENCE_COUNT_HASH = 3905245786L
  private const val GET_PATH_HASH = 201670096L
  private const val GET_SCRIPT_PROPERTY_LIST_HASH = 2915620761L
  private const val PROPERTY_USAGE_STORAGE = 2L

  private val getReferenceCountBind by lazy {
    ObjectCalls.getMethodBind("RefCounted", "get_reference_count", GET_REFERENCE_COUNT_HASH)
  }

  private val getPathBind by lazy {
    ObjectCalls.getMethodBind("Resource", "get_path", GET_PATH_HASH)
  }

  /** Test seam: the owner's reference count, in JVM unit tests that have no engine. */
  @Volatile internal var referenceCountOverride: ((MemorySegment) -> Int)? = null

  private fun referenceCount(owner: MemorySegment): Int =
    referenceCountOverride?.let { it(owner) }
      ?: ObjectCalls.ptrcallNoArgsRetInt(getReferenceCountBind, owner)

  /**
   * The registry value for a new instance [si] on [owner]: a [ScriptOwnerLink] for a `RefCounted`
   * owner (bit 63 of the instance id, `ObjectID::is_ref_counted`), else [si] itself.
   */
  fun linkFor(si: KanamaScriptInstance, owner: MemorySegment, script: KanamaScript?): Any {
    val instanceId = ObjectCalls.objectGetInstanceId(owner)
    if (instanceId >= 0L) return si
    val kotlinObject = si.kotlinObject
    // No cleaner (Android before API 33) or the fallback off: no instance +1, no parking either,
    // the pre-task-132 lifetime.
    if (!OwnedReleaseCleaner.available || !enabled || !OwnedReleases.enabled) return si
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
    if (link.onIncremented { referenceCount(link.owner) }) return
    recreate(handle, link)
  }

  /** `refcount_decremented`: true when the owner may die (count 0). WEAK at exactly 1. */
  fun decremented(link: ScriptOwnerLink): Boolean =
    link.onDecremented { referenceCount(link.owner) }

  // The script object was collected but the engine referenced the owner again before the queued
  // release ran. Nothing is built here: refcount_incremented runs under the engine's
  // ResourceCache lock and ResourceLoader mutex, where neither the script's constructor nor a
  // file load may run. The link is replaced by one that owes a build and a refill; the first use
  // of the instance ([materialize]) pays them, and a free with no use pays nothing. The old
  // release stays queued and drops the old +1.
  private fun recreate(handle: Long, link: ScriptOwnerLink) {
    val script = link.script ?: return
    val replacement = ScriptOwnerLink(null, link.owner, link.instanceId, script, anchored = true)
    replacement.needsBuild = true
    val path =
      runCatching { ObjectCalls.ptrcallNoArgsRetString(getPathBind, link.owner) }.getOrDefault("")
    when {
      path.isEmpty() ->
        OwnedReleaseCleaner.warn(
          "The ${script.kotlinClassName} script object of a resource with no file was collected " +
            "while the engine re-referenced the resource; its script instance is recreated with its " +
            "default property values. Keep a reference to the script object (or the resource) " +
            "while you use it."
        )
      "::" in path ->
        OwnedReleaseCleaner.warn(
          "The ${script.kotlinClassName} script object of a sub-resource of " +
            "${path.substringBefore("::")} was collected while the engine re-referenced the " +
            "resource; its script instance is recreated with its default property values (a " +
            "sub-resource is not reloaded on its own). Keep a reference to the script object (or " +
            "the resource) while you use it."
        )
      else -> replacement.pendingRefill = path
    }
    ObjectRegistry.replace(handle, replacement)
    ScriptBridge.retrackOwner(link.owner, replacement, null)
  }

  /**
   * The first use of a link that owes a build and a refill (see [recreate]), from `ScriptBridge.si`
   * / `kotlinObjectForOwner`, outside the engine's loader locks: builds the instance from the
   * script's factory (the script constructor runs), adopts it (its own +1, anchor, cleanup), then
   * refills it from the owner's file. Engine callbacks on the instance all go through `si`, so the
   * first callback is the first use.
   */
  fun materialize(link: ScriptOwnerLink) {
    if (link.needsBuild) {
      synchronized(link) {
        if (link.needsBuild) {
          // Cleared first: a callback the constructor itself triggers sees no instance yet.
          link.needsBuild = false
          val script = link.script
          val fresh =
            script?.let { s ->
              runCatching { s.factory?.let { factory -> factory(link.owner) } }.getOrNull()
            }
          if (fresh == null) {
            link.pendingRefill = null
          } else {
            fresh.script = script
            link.install(fresh)
            adopt(link, fresh)
            link.makeStrong()
            ScriptOwnerIds.remember(fresh.kotlinObject, link.instanceId)
          }
        }
      }
    }
    if (link.pendingRefill != null) refillIfPending(link)
  }

  /**
   * Refills a rebuilt instance from an uncached load of its owner's file (see the class comment),
   * once, on its first use: the stored (`PROPERTY_USAGE_STORAGE`) script properties of the
   * re-parsed copy are set on the owner, through its script instance, and the copy is released.
   */
  private fun refillIfPending(link: ScriptOwnerLink) {
    val path =
      synchronized(link) { link.pendingRefill.also { link.pendingRefill = null } } ?: return
    val script = link.script ?: return
    val copy =
      runCatching {
          net.multigesture.kanama.api.ResourceLoader.load(
            path,
            "",
            net.multigesture.kanama.api.ResourceLoader.CacheMode.IGNORE,
          )
        }
        .getOrNull()
    if (copy == null) {
      OwnedReleaseCleaner.warn(
        "The ${script.kotlinClassName} script instance of $path was recreated but the file could " +
          "not be loaded again; its property values were reset."
      )
      return
    }
    try {
      val owner =
        net.multigesture.kanama.api.GodotObject(net.multigesture.kanama.api.GodotHandle(link.owner))
      val source = net.multigesture.kanama.api.GodotObject(copy.handle)
      for (name in storedScriptPropertyNames(script)) owner.set(name, source.get(name))
    } finally {
      copy.close()
    }
  }

  private fun storedScriptPropertyNames(script: KanamaScript): List<String> {
    val bind =
      ObjectCalls.getMethodBind("Script", "get_script_property_list", GET_SCRIPT_PROPERTY_LIST_HASH)
    return ObjectCalls.ptrcallNoArgsRetDictionaryList(bind, script.godotObject).mapNotNull { info ->
      val usage = (info["usage"] as? Number)?.toLong() ?: 0L
      (info["name"] as? String)?.takeIf { usage and PROPERTY_USAGE_STORAGE != 0L }
    }
  }

  /**
   * `free` of an instance whose owner is NOT dying (the script was detached or replaced): its `+1`
   * must still be dropped, but not from inside the engine's `set_script`, so it is queued for the
   * next frame's drain. In the dying path the drain already released it. A plain-class owner's
   * parked releases are handed back to the drain.
   */
  fun freed(link: ScriptOwnerLink) {
    link.abandon()
    if (!link.anchored) {
      plainScriptOwners -= link.owner.address()
      OwnedReleases.unparkLater(link.owner, link.instanceId)
    }
    val pending = link.pending
    // tryDisarm false: the cleanup already queued this release.
    if (link.holdsOwnerRef && pending != null && pending.tryDisarm()) {
      OwnedReleaseCleaner.enqueue(PendingRelease(link.owner, link.instanceId, null, link))
    }
    // The instance is gone: drop the link's hold on it and the script object's cleanup (its
    // release is disarmed or already queued), so a detached script object is collected like any
    // other object once game code drops it (a script detached from, or swapped on, a live owner).
    val si = link.detach()
    val script = si?.kotlinObject as? net.multigesture.kanama.api.KanamaScript<*>
    val anchor = script?.kanamaInstanceAnchor as? ScriptInstanceAnchor
    if (anchor != null && anchor.instance === si) {
      script.kanamaInstanceAnchor = null
      OwnedReleaseCleaner.cancel(anchor.registration)
    }
  }
}

/** What a `KanamaScript` object anchors: its instance, and the cleanup registered on it. */
internal class ScriptInstanceAnchor(val instance: Any, val registration: Any?)
