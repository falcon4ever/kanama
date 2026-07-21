package net.multigesture.kanama.types

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
