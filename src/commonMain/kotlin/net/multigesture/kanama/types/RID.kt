package net.multigesture.kanama.types

import kotlin.jvm.JvmInline

/**
 * A handle for a `Resource`'s unique identifier. Kanama value types are immutable snapshots; assign
 * a new value back to the Godot property after changing components.
 *
 * Generated from Godot docs: RID
 */
@JvmInline
value class RID(val value: Long) {
  /**
   * Returns `true` if the `RID` is not `0`.
   *
   * Generated from Godot docs: RID.is_valid
   */
  fun isValid(): Boolean = value != 0L

  // ===== BEGIN GENERATED BUILTIN MEMBERS: RID (generate_builtin_ops.py) =====
  operator fun compareTo(other: RID): Int = value.toULong().compareTo(other.value.toULong())

  /**
   * Returns the ID of the referenced low-level resource.
   *
   * Generated from Godot docs: RID.get_id
   */
  fun getId(): Long = value

  // ===== END GENERATED BUILTIN MEMBERS: RID =====

  companion object {
    val EMPTY = RID(0L)
  }
}
