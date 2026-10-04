package net.multigesture.kanama.binding

import java.lang.foreign.MemorySegment
import java.util.concurrent.ConcurrentHashMap
import net.multigesture.kanama.api.RefCounted
import net.multigesture.kanama.binding.runtime.BuiltinTypes

/**
 * The references a script's property setters took, owned by the runtime per owner object, not by
 * the Kotlin script object (task 132).
 *
 * A generated setter for a script property that holds `RefCounted` values (a resource wrapper, a
 * custom resource script, a `List`/`Map` of them) takes a `+1` on each value, so the value lives as
 * long as the property holds it, as it does in GDScript. Those references used to be given back by
 * a per-script cleanup that read the Kotlin property values in `free`; but with the owner links
 * ([ScriptOwnerLinks]) the GC can collect the Kotlin script object before its owner dies, and the
 * cleanup then had nothing to read: every `+1` leaked (City-Builder: "244 resources still in use at
 * exit"). Here the setter's references are recorded under (owner, property) as it takes them:
 * - a setter replaces its property's entry and releases the previous one, so re-setting a property
 *   releases the values it no longer holds;
 * - `free` ([ScriptBridge.siFree]) releases every entry of the owner, whether or not the Kotlin
 *   object still exists; a rebuilt instance's refill registers again through the same setters.
 *
 * Only what a setter took is released here. A value the script assigned itself (`smokeScene =
 * PackedScene.create()`) is the Kotlin code's: the generated free-path cleanup closes it while the
 * Kotlin object is alive (running first, so a wrapper registered here that it already closed is
 * skipped), else the GC fallback releases it. Plain script classes and `Node` owners use the same
 * registry.
 *
 * The generated setter wraps its read in [capture]; the retaining readers in [BuiltinTypes] report
 * each reference they take through [recordHandle] / [recordWrapper]. Captures nest per thread: a
 * read that resolves a script object may run another owner's setter (a rebuilt instance's refill).
 */
internal object ScriptPropertyRetains {
  private class Capture {
    val items = ArrayList<Any>(4)
  }

  private val captures = ThreadLocal.withInitial { ArrayDeque<Capture>() }

  // owner address -> property name -> what its setter took: a raw handle (MemorySegment) whose
  // +1 is released with unreference, or an owned RefCounted wrapper released with close().
  private val byOwner = ConcurrentHashMap<Long, ConcurrentHashMap<String, List<Any>>>()

  private val trace: Boolean = System.getenv("KANAMA_TRACE_SCRIPT_PROPERTY_CLEANUP") == "1"

  /**
   * Runs [read] (a setter's decode) and registers the references it took under [owner]'s
   * [property], releasing the ones registered there before. A throwing read releases what it took
   * and leaves the previous entry in place.
   */
  fun <T> capture(owner: MemorySegment, property: String, read: () -> T): T {
    val stack = captures.get()
    val capture = Capture()
    stack.addLast(capture)
    val value =
      try {
        read()
      } catch (t: Throwable) {
        stack.removeLast()
        release(property, capture.items)
        throw t
      }
    stack.removeLast()
    register(owner.address(), property, capture.items)
    return value
  }

  /** A retaining reader took a `+1` on [handle] (released with `unreference`). */
  fun recordHandle(handle: MemorySegment) {
    captures.get().lastOrNull()?.items?.add(handle)
  }

  /** A retaining reader made [wrapper] own a `+1` (released with `close()`). */
  fun recordWrapper(wrapper: RefCounted) {
    captures.get().lastOrNull()?.items?.add(wrapper)
  }

  private fun register(owner: Long, property: String, items: List<Any>) {
    val previous =
      if (items.isEmpty()) {
        byOwner[owner]?.remove(property)
      } else {
        byOwner.computeIfAbsent(owner) { ConcurrentHashMap() }.put(property, items.toList())
      }
    if (previous != null) release(property, previous)
  }

  /** `free` of [owner]'s script instance: releases everything its setters took. */
  fun releaseOwner(owner: Long) {
    val properties = byOwner.remove(owner) ?: return
    properties.forEach { (property, items) -> release(property, items) }
  }

  /** The number of references registered for [owner] (tests). */
  internal fun countFor(owner: Long): Int = byOwner[owner]?.values?.sumOf { it.size } ?: 0

  /** Test seam: replaces the engine release of one item (JVM unit tests have no engine). */
  @Volatile internal var releaseOverride: ((Any) -> Unit)? = null

  private fun release(property: String, items: List<Any>) {
    for (item in items) {
      runCatching {
          val override = releaseOverride
          when {
            override != null -> override(item)
            item is MemorySegment -> BuiltinTypes.releaseRefCounted(item)
            item is RefCounted ->
              // Closed already by game code: nothing of the setter's is left to release.
              if (item.isOwned) {
                if (trace) {
                  System.err.println(
                    "[kanama:kt] script property cleanup $property type=${item::class.simpleName}"
                  )
                }
                item.close()
              }
          }
          Unit
        }
        .onFailure { error ->
          System.err.println(
            "[kanama:kt] failed to release a reference of script property $property: ${error.message}"
          )
        }
    }
  }
}
