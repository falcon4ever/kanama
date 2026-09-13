package net.multigesture.kanama.types

/**
 * A 3D vector using integer coordinates. Kanama value types are immutable snapshots; assign a new
 * value back to the Godot property after changing components.
 *
 * Generated from Godot docs: Vector3i
 */
data class Vector3i(
  /**
   * The vector's X component. Also accessible by using the index position `[0]`.
   *
   * Generated from Godot docs: Vector3i.x
   */
  val x: Int,
  /**
   * The vector's Y component. Also accessible by using the index position `[1]`.
   *
   * Generated from Godot docs: Vector3i.y
   */
  val y: Int,
  /**
   * The vector's Z component. Also accessible by using the index position `[2]`.
   *
   * Generated from Godot docs: Vector3i.z
   */
  val z: Int,
) {
  /** Returns a copy with the X component replaced. */
  fun withX(value: Int): Vector3i = Vector3i(value, y, z)

  /** Returns a copy with the Y component replaced. */
  fun withY(value: Int): Vector3i = Vector3i(x, value, z)

  /** Returns a copy with the Z component replaced. */
  fun withZ(value: Int): Vector3i = Vector3i(x, y, value)

  companion object {
    /**
     * Zero vector, a vector with all components set to `0`.
     *
     * Generated from Godot docs: Vector3i.ZERO
     */
    val ZERO = Vector3i(0, 0, 0)
    /**
     * One vector, a vector with all components set to `1`.
     *
     * Generated from Godot docs: Vector3i.ONE
     */
    val ONE = Vector3i(1, 1, 1)
  }
}
