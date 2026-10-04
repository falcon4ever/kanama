package net.multigesture.kanama.binding.runtime

import kotlin.concurrent.Volatile
import kotlin.jvm.JvmField

/**
 * Whether one Godot object is still alive (task 132 D7): the data of the instance binding Kanama
 * attaches to an object the first time a wrapper of it is built. Godot calls the binding's free
 * callback from the object's destructor, which sets [dead]; every wrapper of the object holds the
 * same flag, so the freed-object check before a call is one field read instead of an
 * `object_get_instance_from_id` engine call.
 */
internal class LiveFlag(
  /** The object's instance id, read once when the binding was created. */
  @JvmField val instanceId: Long
) {
  /** Set by the free callback, on whatever thread destroys the object. */
  @Volatile @JvmField var dead: Boolean = false

  companion object {
    /** For an object freed between the binding call and the flag lookup. */
    fun freed(): LiveFlag = LiveFlag(0L).also { it.dead = true }
  }
}
