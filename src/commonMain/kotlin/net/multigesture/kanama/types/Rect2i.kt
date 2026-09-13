package net.multigesture.kanama.types

/**
 * A 2D axis-aligned bounding box using integer coordinates. Kanama value types are immutable
 * snapshots; assign a new value back to the Godot property after changing components.
 *
 * Generated from Godot docs: Rect2i
 */
data class Rect2i(
  /**
   * The origin point. This is usually the top-left corner of the rectangle.
   *
   * Generated from Godot docs: Rect2i.position
   */
  val position: Vector2i,
  /**
   * The rectangle's width and height, starting from `position`. Setting this value also affects the
   * `end` point. Note: It's recommended setting the width and height to non-negative values, as
   * most methods in Godot assume that the `position` is the top-left corner, and the `end` is the
   * bottom-right corner. To get an equivalent rectangle with non-negative size, use `abs`.
   *
   * Generated from Godot docs: Rect2i.size
   */
  val size: Vector2i,
) {
  /**
   * The ending point. This is usually the bottom-right corner of the rectangle, and is equivalent
   * to `position + size`. Setting this point affects the `size`.
   *
   * Generated from Godot docs: Rect2i.end
   */
  val end: Vector2i
    get() = Vector2i(position.x + size.x, position.y + size.y)

  fun area(): Long = size.x.toLong() * size.y.toLong()

  companion object {
    val ZERO = Rect2i(Vector2i(0, 0), Vector2i(0, 0))
  }
}
