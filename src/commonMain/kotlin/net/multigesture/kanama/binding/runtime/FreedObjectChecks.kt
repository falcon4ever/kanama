package net.multigesture.kanama.binding.runtime

import kotlin.jvm.JvmField
import net.multigesture.kanama.api.OS

/**
 * The freed-object check (task 131 item 2, F2): a call through a wrapper whose Godot object was
 * freed throws `IllegalStateException("Invalid access to previously freed instance ...")` instead
 * of dereferencing a dangling pointer, so the error is contained and reported like any script error
 * (GDScript says "Invalid access to previously freed instance" and keeps running).
 *
 * `GodotObject.segment` -- the receiver of every wrapper ptrcall and the object argument of every
 * call that takes one -- asks [ObjectRuntime.isLive] when [enabled]: `object_get_instance_from_id`
 * on the instance id the wrapper captured at construction must still answer the wrapper's pointer.
 * Godot's instance ids carry a validator, so a freed object's id never resolves again, even after
 * its ObjectDB slot is reused.
 *
 * One extra engine call per wrapper call: on by default where `OS.is_debug_build()` is true (the
 * editor and debug export templates), off in release templates. `KANAMA_FREED_OBJECT_CHECKS=1` /
 * `0` in the game's environment forces it on or off (a measurement knob, not a documented setting).
 */
internal object FreedObjectChecks {
  /** Read on every wrapper call; a plain static field on the JVM. */
  @JvmField var enabled: Boolean = false

  /** Environment override read by [configure]: `1`/`true`/`on` or `0`/`false`/`off`. */
  const val ENVIRONMENT_VARIABLE: String = "KANAMA_FREED_OBJECT_CHECKS"

  /**
   * Decides [enabled] once the engine singletons exist (desktop/Android: the SCENE initialization
   * level; iOS: the same level of the runtime's initialize export). Never throws: a failed query
   * leaves the check off, which is the behaviour before task 131.
   */
  fun configure() {
    enabled =
      runCatching { decide(OS.getEnvironment(ENVIRONMENT_VARIABLE)) { OS.isDebugBuild() } }
        .getOrDefault(false)
  }

  /** [configure]'s decision for an environment [override] value; pure, so it is unit-testable. */
  fun decide(override: String, debugBuild: () -> Boolean): Boolean =
    when (override.trim().lowercase()) {
      "1",
      "true",
      "on" -> true
      "0",
      "false",
      "off" -> false
      else -> debugBuild()
    }

  /** The exception a call through a wrapper of a freed object throws. */
  fun freedInstance(wrapperClass: String, instanceId: Long): IllegalStateException =
    IllegalStateException(
      "Invalid access to previously freed instance ($wrapperClass, instance id $instanceId)"
    )
}
