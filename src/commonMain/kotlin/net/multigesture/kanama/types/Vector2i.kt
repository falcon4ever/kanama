package net.multigesture.kanama.types

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
