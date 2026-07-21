package net.multigesture.kanama.types

/**
 * A 2D axis-aligned bounding box using floating-point coordinates. Kanama value types are immutable
 * snapshots; assign a new value back to the Godot property after changing components.
 *
 * Generated from Godot docs: Rect2
 */
data class Rect2(
  /**
   * The origin point. This is usually the top-left corner of the rectangle.
   *
   * Generated from Godot docs: Rect2.position
   */
  val position: Vector2,
  /**
   * The rectangle's width and height, starting from `position`. Setting this value also affects the
   * `end` point. Note: It's recommended setting the width and height to non-negative values, as
   * most methods in Godot assume that the `position` is the top-left corner, and the `end` is the
   * bottom-right corner. To get an equivalent rectangle with non-negative size, use `abs`.
   *
   * Generated from Godot docs: Rect2.size
   */
  val size: Vector2,
) {
  /** Godot-style fuzzy compare: true if position and size are approximately equal. */
  /**
   * Returns `true` if this rectangle and `rect` are approximately equal, by calling
   * `Vector2.is_equal_approx` on the `position` and the `size`.
   *
   * Generated from Godot docs: Rect2.is_equal_approx
   */
  fun isEqualApprox(other: Rect2): Boolean =
    position.isEqualApprox(other.position) && size.isEqualApprox(other.size)

  /**
   * kanama convenience (Godot has no composite `is_zero_approx`): true if position and size are
   * approximately zero.
   */
  fun isZeroApprox(): Boolean = position.isZeroApprox() && size.isZeroApprox()

  /**
   * The ending point. This is usually the bottom-right corner of the rectangle, and is equivalent
   * to `position + size`. Setting this point affects the `size`.
   *
   * Generated from Godot docs: Rect2.end
   */
  val end: Vector2
    get() = position + size

  fun area(): Double = (size.x * size.y).toDouble()

  /**
   * Returns `true` if the rectangle contains the given `point`. By convention, points on the right
   * and bottom edges are not included. Note: This method is not reliable for `Rect2` with a
   * negative `size`. Use `abs` first to get a valid rectangle.
   *
   * Generated from Godot docs: Rect2.has_point
   */
  fun hasPoint(point: Vector2): Boolean =
    point.x >= position.x &&
      point.x < position.x + size.x &&
      point.y >= position.y &&
      point.y < position.y + size.y

  companion object {
    val ZERO = Rect2(Vector2.ZERO, Vector2.ZERO)
  }
}
