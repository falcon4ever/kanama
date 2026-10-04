package net.multigesture.kanama.binding.runtime

import kotlin.jvm.JvmField
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.OS
import net.multigesture.kanama.api.RefCounted

/**
 * The freed-object check (task 131 item 2, F2), with GDScript's semantics:
 * - a CALL through a wrapper whose Godot object was freed throws `IllegalStateException("Invalid
 *   access to previously freed instance ...")` instead of dereferencing a dangling pointer, so it
 *   is contained and reported like any script error;
 * - HOLDING, comparing or passing on such a wrapper as a value is silent: encoded as a value (a
 *   script property read, a script method or virtual return, a Variant argument, an Array or
 *   Dictionary element) it becomes nil ([valueSegment]), as a freed object stored in a GDScript
 *   variable reads back as null.
 *
 * `GodotObject.segment` -- the receiver of every wrapper ptrcall and the object argument of every
 * typed call that takes one -- asks [ObjectRuntime.isLive] when [enabled]:
 * `object_get_instance_from_id` on the instance id the wrapper captured at construction must still
 * answer the wrapper's pointer. Godot's instance ids carry a validator, so a freed object's id
 * never resolves again, even after its ObjectDB slot is reused.
 *
 * One extra engine call per checked use: on by default where `OS.is_debug_build()` is true (the
 * editor and debug export templates), off in release templates. `KANAMA_FREED_OBJECT_CHECKS=1` /
 * `0` in the game's environment forces it on or off (a measurement knob, not a documented setting).
 *
 * The O(1) alternative (task 132 D7) is built beside it on desktop/Android:
 * `KANAMA_FREED_OBJECT_CHECKS=binding` turns the check on -- in a release template too -- with an
 * instance binding per object ([LiveFlag], [bindings]): Godot's free callback marks the flag dead
 * and the check before a call is one field read. It is not the default because it moves the cost to
 * wrapper construction: measured on the editor binary (Apple M1 Max, task 132), a wrapper costs ~30
 * ns to build instead of ~11 ns while a checked call costs ~17.5 ns instead of ~30 ns, and the
 * Bunnymark-style loop (two wrappers and two calls per bunny per frame) ran slower with it than
 * with the lookup. iOS has no instance binding yet and keeps the lookup.
 */
internal object FreedObjectChecks {
  /** Read on every wrapper call; a plain static field on the JVM. */
  @JvmField var enabled: Boolean = false

  /**
   * Whether wrappers built from now on attach an instance binding and check its [LiveFlag] (task
   * 132 D7) instead of asking `object_get_instance_from_id` per call. Read once per wrapper
   * construction.
   */
  @JvmField var bindings: Boolean = false

  /**
   * Environment override read by [configure]: `1`/`true`/`on` or `0`/`false`/`off`, or `binding`
   * (on, with the instance-binding check where the backend has it).
   */
  const val ENVIRONMENT_VARIABLE: String = "KANAMA_FREED_OBJECT_CHECKS"

  /**
   * Decides [enabled] once the engine singletons exist (desktop/Android: the SCENE initialization
   * level; iOS: the same level of the runtime's initialize export) and returns the line the backend
   * logs: `on (debug build)`, `off (release build)`, `on (KANAMA_FREED_OBJECT_CHECKS=1)`, or why it
   * is off. [lookupAvailable] false (the backend could not resolve `object_get_instance_from_id`)
   * turns the check off once instead of failing every call. Never throws: a detection error leaves
   * the check off and is named in the returned line, never silently.
   */
  fun configure(lookupAvailable: Boolean = true, bindingAvailable: Boolean = false): String {
    bindings = false
    if (!lookupAvailable) {
      enabled = false
      return "off (object_get_instance_from_id is not available)"
    }
    val override =
      runCatching { OS.getEnvironment(ENVIRONMENT_VARIABLE) }
        .getOrElse {
          enabled = false
          return "off (reading $ENVIRONMENT_VARIABLE failed: ${describe(it)})"
        }
    if (override.trim().lowercase() == BINDING) {
      if (!bindingAvailable) {
        enabled = true
        return "on ($ENVIRONMENT_VARIABLE=$BINDING: no instance binding on this backend, lookup)"
      }
      enabled = true
      bindings = true
      return "on ($ENVIRONMENT_VARIABLE=$BINDING: instance binding)"
    }
    val forced = forcedBy(override)
    if (forced != null) {
      enabled = forced
      return "${if (forced) "on" else "off"} ($ENVIRONMENT_VARIABLE=${override.trim()})"
    }
    val debugBuild =
      runCatching { OS.isDebugBuild() }
        .getOrElse {
          enabled = false
          return "off (OS.is_debug_build failed: ${describe(it)})"
        }
    enabled = debugBuild
    return if (debugBuild) "on (debug build)" else "off (release build)"
  }

  private const val BINDING = "binding"

  /** [configure]'s decision for an environment [override] value; pure, so it is unit-testable. */
  fun decide(override: String, debugBuild: () -> Boolean): Boolean =
    forcedBy(override) ?: debugBuild()

  private fun forcedBy(override: String): Boolean? =
    when (override.trim().lowercase()) {
      "1",
      "true",
      "on" -> true
      "0",
      "false",
      "off" -> false
      else -> null
    }

  private fun describe(t: Throwable): String = t.message ?: (t::class.simpleName ?: "error")

  /**
   * The pointer that encodes [obj] as a VALUE (see the class comment): NULL -- nil -- when the
   * check is on and the object was freed, silently. A closed `RefCounted` still throws its
   * closed-handle error (a use-after-close is a program error, not a stale reference).
   */
  fun valueSegment(obj: GodotObject): RawSegment {
    val raw = obj.handle.segment
    if (enabled && !obj.isAlive(raw)) return NULL_SEGMENT
    if (obj is RefCounted) obj.checkOpen()
    return raw
  }

  /**
   * The exception a call through a wrapper of a freed object throws. The id is printed unsigned, as
   * Godot prints instance ids (a RefCounted id has its top bit set).
   */
  fun freedInstance(wrapperClass: String, instanceId: Long): IllegalStateException =
    IllegalStateException(
      "Invalid access to previously freed instance ($wrapperClass, instance id " +
        "${instanceId.toULong()})"
    )
}
