package net.multigesture.kanama.types

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

  companion object {
    val EMPTY = RID(0L)
  }
}
