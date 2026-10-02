package net.multigesture.kanama.types

import kotlin.jvm.JvmInline

/**
 * A 4D vector using integer coordinates. Kanama value types are immutable snapshots; assign a new
 * value back to the Godot property after changing components.
 *
 * Generated from Godot docs: Vector4i
 */
data class Vector4i(
  /**
   * The vector's X component. Also accessible by using the index position `[0]`.
   *
   * Generated from Godot docs: Vector4i.x
   */
  val x: Int,
  /**
   * The vector's Y component. Also accessible by using the index position `[1]`.
   *
   * Generated from Godot docs: Vector4i.y
   */
  val y: Int,
  /**
   * The vector's Z component. Also accessible by using the index position `[2]`.
   *
   * Generated from Godot docs: Vector4i.z
   */
  val z: Int,
  /**
   * The vector's W component. Also accessible by using the index position `[3]`.
   *
   * Generated from Godot docs: Vector4i.w
   */
  val w: Int,
) {
  // ===== BEGIN GENERATED ENUMS: Vector4i (scripts/generate_api_wrapper.py — do not edit) =====
  /**
   * Godot's `Vector4i.Axis` enum as a typed value: `.value` is the raw number Godot uses, and the
   * companion holds the named values (`Vector4i.Axis.<NAME>`).
   *
   * Generated from Godot docs: Vector4i.Axis
   */
  @JvmInline
  value class Axis(override val value: Long) : net.multigesture.kanama.api.GodotEnumValue {
    companion object {
      /**
       * Enumerated value for the X axis. Returned by `max_axis_index` and `min_axis_index`.
       *
       * Generated from Godot docs: Vector4i.AXIS_X
       */
      val X: Axis
        get() = Axis(0L)

      /**
       * Enumerated value for the Y axis. Returned by `max_axis_index` and `min_axis_index`.
       *
       * Generated from Godot docs: Vector4i.AXIS_Y
       */
      val Y: Axis
        get() = Axis(1L)

      /**
       * Enumerated value for the Z axis. Returned by `max_axis_index` and `min_axis_index`.
       *
       * Generated from Godot docs: Vector4i.AXIS_Z
       */
      val Z: Axis
        get() = Axis(2L)

      /**
       * Enumerated value for the W axis. Returned by `max_axis_index` and `min_axis_index`.
       *
       * Generated from Godot docs: Vector4i.AXIS_W
       */
      val W: Axis
        get() = Axis(3L)
    }
  }

  // ===== END GENERATED ENUMS: Vector4i =====

  companion object {
    /**
     * Zero vector, a vector with all components set to `0`.
     *
     * Generated from Godot docs: Vector4i.ZERO
     */
    val ZERO = Vector4i(0, 0, 0, 0)
    /**
     * One vector, a vector with all components set to `1`.
     *
     * Generated from Godot docs: Vector4i.ONE
     */
    val ONE = Vector4i(1, 1, 1, 1)
  }
}
