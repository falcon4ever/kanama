package net.multigesture.kanama.binding.runtime

import kotlin.concurrent.Volatile
import kotlin.concurrent.atomics.AtomicInt
import kotlin.concurrent.atomics.ExperimentalAtomicApi
import net.multigesture.kanama.api.OS
import net.multigesture.kanama.api.RefCounted

/**
 * The references a script's property setters took, owned by the runtime per owner object, not by
 * the Kotlin script object (task 132). One implementation for desktop, Android and iOS.
 *
 * Setting a script property that holds `RefCounted` values (a resource, a custom resource script, a
 * `List`/`Map` of them) takes a reference on each value, so the value lives as long as the property
 * holds it, as in GDScript. Those references used to be given back by reading the Kotlin property
 * values when the instance was freed; with the owner links the GC can collect a `KanamaScript`
 * object before its owner dies, and every reference then leaked (City-Builder: "244 resources still
 * in use at exit"). Here each set records exactly the references it took under (owner, property):
 * - a set replaces its property's entry and releases the previous one, after the new references are
 *   taken, so re-setting the same value is safe;
 * - the instance's free ([releaseOwner]) releases every entry of the owner, whether or not the
 *   Kotlin object still exists; a rebuilt instance's refill registers again through the same sets.
 *
 * Every entry is a raw reference of the registry's own: a wrapper the Kotlin property holds keeps
 * its own reference (released by its `close()` or the GC fallback), so a Kotlin alias of an old
 * value stays valid after the engine sets the property again, as in GDScript. Only `RefCounted`
 * objects are ever recorded: a `reference()` ptrcall on another object writes into whatever that
 * object has where `RefCounted` keeps its count.
 *
 * Desktop records through the setter's capture (`ScriptPropertyCapture`, jvmMain); iOS through the
 * shim's set path (`KanamaIosRuntime.retainScriptInstancePropertyObjects`).
 */
@OptIn(ExperimentalAtomicApi::class)
internal object ScriptPropertyRetains {
  // A tiny lock: sets and frees run on the main thread, resource loads on worker threads. Never
  // held
  // across an engine call (a release can free an owner whose free re-enters [releaseOwner]).
  private val lock = AtomicInt(0)

  // owner address -> property name -> the handles whose +1 this registry holds.
  private val byOwner = HashMap<Long, HashMap<String, List<RawSegment>>>()

  private val trace: Boolean by lazy {
    runCatching { OS.getEnvironment("KANAMA_TRACE_SCRIPT_PROPERTY_CLEANUP") }.getOrDefault("") ==
      "1"
  }

  private inline fun <T> locked(block: () -> T): T {
    while (!lock.compareAndSet(0, 1)) {
      // Contention is rare and short: a map update.
    }
    try {
      return block()
    } finally {
      lock.store(0)
    }
  }

  /**
   * Registers [handles] (each carrying a `+1` already taken for this registry) under [owner]'s
   * [property] and releases the ones registered there before.
   */
  fun register(owner: Long, property: String, handles: List<RawSegment>) {
    val previous = locked {
      if (handles.isEmpty()) {
        byOwner[owner]?.remove(property)
      } else {
        byOwner.getOrPut(owner) { HashMap() }.put(property, handles.toList())
      }
    }
    if (previous != null) release(previous)
  }

  /** The instance's free: releases everything the sets of [owner]'s properties took. */
  fun releaseOwner(owner: Long) {
    val properties = locked { byOwner.remove(owner) } ?: return
    for (handles in properties.values) release(handles)
  }

  /** The number of references registered for [owner] (tests, self-test rows). */
  fun countFor(owner: Long): Int = locked { byOwner[owner]?.values?.sumOf { it.size } ?: 0 }

  /** Test seam: replaces the engine release of one handle (JVM unit tests have no engine). */
  @Volatile internal var releaseOverride: ((RawSegment) -> Unit)? = null

  /** Releases one registry reference: `unreference()`, and destroy at zero. */
  fun release(handles: List<RawSegment>) {
    for (handle in handles) {
      runCatching {
          val override = releaseOverride
          if (override != null) {
            override(handle)
          } else {
            val destroyed = RefCounted.releaseHandle(handle)
            if (trace) {
              println(
                "[kanama:kt] script property cleanup RefCounted " +
                  "handle=0x${handle.address().toString(16)} destroy=$destroyed"
              )
            }
          }
        }
        .onFailure { error ->
          println("[kanama:kt] failed to release a script property reference: ${error.message}")
        }
    }
  }
}
