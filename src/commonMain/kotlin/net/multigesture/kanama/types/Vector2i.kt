package net.multigesture.kanama.types

import kotlin.jvm.JvmInline

/**
 * A 2D vector using integer coordinates. Kanama value types are immutable snapshots; assign a new
 * value back to the Godot property after changing components.
 *
 * Generated from Godot docs: Vector2i
 */
data class Vector2i(
  /**
   * The vector's X component. Also accessible by using the index position `[0]`.
   *
   * Generated from Godot docs: Vector2i.x
   */
  val x: Int,
  /**
   * The vector's Y component. Also accessible by using the index position `[1]`.
   *
   * Generated from Godot docs: Vector2i.y
   */
  val y: Int,
) {
  // ===== BEGIN GENERATED ENUMS: Vector2i (scripts/generate_api_wrapper.py — do not edit) =====
  /**
   * Godot's `Vector2i.Axis` enum as a typed value: `.value` is the raw number Godot uses, and the
   * companion holds the named values (`Vector2i.Axis.<NAME>`).
   *
   * Generated from Godot docs: Vector2i.Axis
   */
  @JvmInline
  value class Axis(override val value: Long) : net.multigesture.kanama.api.GodotEnumValue {
    companion object {
      /**
       * Enumerated value for the X axis. Returned by `max_axis_index` and `min_axis_index`.
       *
       * Generated from Godot docs: Vector2i.AXIS_X
       */
      val X: Axis
        get() = Axis(0L)

      /**
       * Enumerated value for the Y axis. Returned by `max_axis_index` and `min_axis_index`.
       *
       * Generated from Godot docs: Vector2i.AXIS_Y
       */
      val Y: Axis
        get() = Axis(1L)
    }
  }

  // ===== END GENERATED ENUMS: Vector2i =====

  /** Returns a copy with the X component replaced. */
  fun withX(value: Int): Vector2i = Vector2i(value, y)

  /** Returns a copy with the Y component replaced. */
  fun withY(value: Int): Vector2i = Vector2i(x, value)

  companion object {
    /**
     * Zero vector, a vector with all components set to `0`.
     *
     * Generated from Godot docs: Vector2i.ZERO
     */
    val ZERO = Vector2i(0, 0)
    /**
     * One vector, a vector with all components set to `1`.
     *
     * Generated from Godot docs: Vector2i.ONE
     */
    val ONE = Vector2i(1, 1)
  }
}
