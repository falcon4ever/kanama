package net.multigesture.kanama.types

/**
 * A 3D axis-aligned bounding box. Kanama value types are immutable snapshots; assign a new value
 * back to the Godot property after changing components.
 *
 * Generated from Godot docs: AABB
 */
data class AABB(
  /**
   * The origin point. This is usually the corner on the bottom-left and forward of the bounding
   * box.
   *
   * Generated from Godot docs: AABB.position
   */
  val position: Vector3,
  /**
   * The bounding box's width, height, and depth starting from `position`. Setting this value also
   * affects the `end` point. Note: It's recommended setting the width, height, and depth to
   * non-negative values. This is because most methods in Godot assume that the `position` is the
   * bottom-left-forward corner, and the `end` is the top-right-back corner. To get an equivalent
   * bounding box with non-negative size, use `abs`.
   *
   * Generated from Godot docs: AABB.size
   */
  val size: Vector3,
) {
  /** Godot-style fuzzy compare: true if position and size are approximately equal. */
  /**
   * Returns `true` if this bounding box and `aabb` are approximately equal, by calling
   * `Vector3.is_equal_approx` on the `position` and the `size`.
   *
   * Generated from Godot docs: AABB.is_equal_approx
   */
  fun isEqualApprox(other: AABB): Boolean =
    position.isEqualApprox(other.position) && size.isEqualApprox(other.size)

  /**
   * kanama convenience (Godot has no composite `is_zero_approx`): true if position and size are
   * approximately zero.
   */
  fun isZeroApprox(): Boolean = position.isZeroApprox() && size.isZeroApprox()

  /**
   * The ending point. This is usually the corner on the top-right and back of the bounding box, and
   * is equivalent to `position + size`. Setting this point affects the `size`.
   *
   * Generated from Godot docs: AABB.end
   */
  val end: Vector3
    get() = position + size

  fun volume(): Double = (size.x * size.y * size.z).toDouble()

  /**
   * Returns `true` if the bounding box contains the given `point`. By convention, points exactly on
   * the right, top, and front sides are not included. Note: This method is not reliable for `AABB`
   * with a negative `size`. Use `abs` first to get a valid bounding box.
   *
   * Generated from Godot docs: AABB.has_point
   */
  fun hasPoint(point: Vector3): Boolean =
    point.x >= position.x &&
      point.x <= position.x + size.x &&
      point.y >= position.y &&
      point.y <= position.y + size.y &&
      point.z >= position.z &&
      point.z <= position.z + size.z

  companion object {
    val ZERO = AABB(Vector3.ZERO, Vector3.ZERO)
  }
}
