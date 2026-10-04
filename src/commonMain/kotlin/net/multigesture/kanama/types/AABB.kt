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
  /** Godot's `str(b)`: `[P: (0.0, 0.0, 0.0), S: (1.0, 1.0, 1.0)]`. */
  override fun toString(): String = "[P: $position, S: $size]"

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

  fun volume(): Double = widenReal(size.rawX * size.rawY * size.rawZ)

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

  // ===== BEGIN GENERATED BUILTIN MEMBERS: AABB (generate_builtin_ops.py) =====
  operator fun times(other: Transform3D): AABB = transform3DXformInvAabb(other, this)

  /**
   * Returns an `AABB` equivalent to this bounding box, with its width, height, and depth modified
   * to be non-negative values.
   *
   * Generated from Godot docs: AABB.abs
   */
  fun abs(): AABB = builtinAABB(builtinReals(AABBMethods.abs, builtinArg(), 6, emptyList()))

  /**
   * Returns the center point of the bounding box. This is the same as `position + (size / 2.0)`.
   *
   * Generated from Godot docs: AABB.get_center
   */
  fun getCenter(): Vector3 {
    val half = narrowReal(0.5)
    return Vector3.raw(
      position.rawX + size.rawX * half,
      position.rawY + size.rawY * half,
      position.rawZ + size.rawZ * half,
    )
  }

  /**
   * Returns `true` if this bounding box's width, height, and depth are all positive. See also
   * `get_volume`.
   *
   * Generated from Godot docs: AABB.has_volume
   */
  fun hasVolume(): Boolean = size.x > 0.0 && size.y > 0.0 && size.z > 0.0

  /**
   * Returns `true` if this bounding box has a surface or a length, that is, at least one component
   * of `size` is greater than `0`. Otherwise, returns `false`.
   *
   * Generated from Godot docs: AABB.has_surface
   */
  fun hasSurface(): Boolean = size.x > 0.0 || size.y > 0.0 || size.z > 0.0

  /**
   * Returns `true` if this bounding box's values are finite, by calling `Vector3.is_finite` on the
   * `position` and the `size`.
   *
   * Generated from Godot docs: AABB.is_finite
   */
  fun isFinite(): Boolean = builtinBool(AABBMethods.isFinite, builtinArg(), emptyList())

  /**
   * Returns `true` if this bounding box overlaps with the box `with`. The edges of both boxes are
   * always excluded.
   *
   * Generated from Godot docs: AABB.intersects
   */
  fun intersects(with: AABB): Boolean =
    builtinBool(AABBMethods.intersects, builtinArg(), listOf(with.builtinArg()))

  /**
   * Returns `true` if this bounding box completely encloses the `with` box. The edges of both boxes
   * are included.
   *
   * Generated from Godot docs: AABB.encloses
   */
  fun encloses(with: AABB): Boolean =
    builtinBool(AABBMethods.encloses, builtinArg(), listOf(with.builtinArg()))

  /**
   * Returns `true` if this bounding box is on both sides of the given `plane`.
   *
   * Generated from Godot docs: AABB.intersects_plane
   */
  fun intersectsPlane(plane: Plane): Boolean =
    builtinBool(AABBMethods.intersectsPlane, builtinArg(), listOf(plane.builtinArg()))

  /**
   * Returns the intersection between this bounding box and `with`. If the boxes do not intersect,
   * returns an empty `AABB`. If the boxes intersect at the edge, returns a flat `AABB` with no
   * volume (see `has_surface` and `has_volume`).
   *
   * Generated from Godot docs: AABB.intersection
   */
  fun intersection(with: AABB): AABB =
    builtinAABB(builtinReals(AABBMethods.intersection, builtinArg(), 6, listOf(with.builtinArg())))

  /**
   * Returns an `AABB` that encloses both this bounding box and `with` around the edges. See also
   * `encloses`.
   *
   * Generated from Godot docs: AABB.merge
   */
  fun merge(with: AABB): AABB =
    builtinAABB(builtinReals(AABBMethods.merge, builtinArg(), 6, listOf(with.builtinArg())))

  /**
   * Returns a copy of this bounding box expanded to align the edges with the given `to_point`, if
   * necessary.
   *
   * Generated from Godot docs: AABB.expand
   */
  fun expand(toPoint: Vector3): AABB =
    builtinAABB(builtinReals(AABBMethods.expand, builtinArg(), 6, listOf(toPoint.builtinArg())))

  /**
   * Returns a copy of this bounding box extended on all sides by the given amount `by`. A negative
   * amount shrinks the box instead.
   *
   * Generated from Godot docs: AABB.grow
   */
  fun grow(by: Double): AABB =
    builtinAABB(builtinReals(AABBMethods.grow, builtinArg(), 6, listOf(argReal(by))))

  /**
   * Returns the vertex's position of this bounding box that's the farthest in the given direction.
   * This point is commonly known as the support point in collision detection algorithms.
   *
   * Generated from Godot docs: AABB.get_support
   */
  fun getSupport(direction: Vector3): Vector3 =
    builtinVector3(
      builtinReals(AABBMethods.getSupport, builtinArg(), 3, listOf(direction.builtinArg()))
    )

  /**
   * Returns the longest normalized axis of this bounding box's `size`, as a `Vector3`
   * (`Vector3.RIGHT`, `Vector3.UP`, or `Vector3.BACK`).
   *
   * Generated from Godot docs: AABB.get_longest_axis
   */
  fun getLongestAxis(): Vector3 =
    builtinVector3(builtinReals(AABBMethods.getLongestAxis, builtinArg(), 3, emptyList()))

  /**
   * Returns the index to the longest axis of this bounding box's `size` (see `Vector3.Axis.X`,
   * `Vector3.Axis.Y`, and `Vector3.Axis.Z`). For an example, see `get_longest_axis`.
   *
   * Generated from Godot docs: AABB.get_longest_axis_index
   */
  fun getLongestAxisIndex(): Long =
    builtinLong(AABBMethods.getLongestAxisIndex, builtinArg(), emptyList())

  /**
   * Returns the longest dimension of this bounding box's `size`. For an example, see
   * `get_longest_axis`.
   *
   * Generated from Godot docs: AABB.get_longest_axis_size
   */
  fun getLongestAxisSize(): Double =
    builtinDouble(AABBMethods.getLongestAxisSize, builtinArg(), emptyList())

  /**
   * Returns the shortest normalized axis of this bounding box's `size`, as a `Vector3`
   * (`Vector3.RIGHT`, `Vector3.UP`, or `Vector3.BACK`).
   *
   * Generated from Godot docs: AABB.get_shortest_axis
   */
  fun getShortestAxis(): Vector3 =
    builtinVector3(builtinReals(AABBMethods.getShortestAxis, builtinArg(), 3, emptyList()))

  /**
   * Returns the index to the shortest axis of this bounding box's `size` (see `Vector3.Axis.X`,
   * `Vector3.Axis.Y`, and `Vector3.Axis.Z`). For an example, see `get_shortest_axis`.
   *
   * Generated from Godot docs: AABB.get_shortest_axis_index
   */
  fun getShortestAxisIndex(): Long =
    builtinLong(AABBMethods.getShortestAxisIndex, builtinArg(), emptyList())

  /**
   * Returns the shortest dimension of this bounding box's `size`. For an example, see
   * `get_shortest_axis`.
   *
   * Generated from Godot docs: AABB.get_shortest_axis_size
   */
  fun getShortestAxisSize(): Double =
    builtinDouble(AABBMethods.getShortestAxisSize, builtinArg(), emptyList())

  /**
   * Returns the position of one of the 8 vertices that compose this bounding box. With an `idx` of
   * `0` this is the same as `position`, and an `idx` of `7` is the same as `end`.
   *
   * Generated from Godot docs: AABB.get_endpoint
   */
  fun getEndpoint(idx: Long): Vector3 =
    builtinVector3(builtinReals(AABBMethods.getEndpoint, builtinArg(), 3, listOf(argLong(idx))))

  /**
   * Returns the first point where this bounding box and the given segment intersect, as a
   * `Vector3`. If no intersection occurs, returns `null`. The segment begins at `from` and ends at
   * `to`.
   *
   * Generated from Godot docs: AABB.intersects_segment
   */
  fun intersectsSegment(from: Vector3, to: Vector3): Vector3? =
    builtinVariantReals(
        AABBMethods.intersectsSegment,
        builtinArg(),
        3,
        listOf(from.builtinArg(), to.builtinArg()),
      )
      ?.let { c -> Vector3.raw(c[0], c[1], c[2]) }

  /**
   * Returns the first point where this bounding box and the given ray intersect, as a `Vector3`. If
   * no intersection occurs, returns `null`. The ray begin at `from`, faces `dir` and extends
   * towards infinity.
   *
   * Generated from Godot docs: AABB.intersects_ray
   */
  fun intersectsRay(from: Vector3, dir: Vector3): Vector3? =
    builtinVariantReals(
        AABBMethods.intersectsRay,
        builtinArg(),
        3,
        listOf(from.builtinArg(), dir.builtinArg()),
      )
      ?.let { c -> Vector3.raw(c[0], c[1], c[2]) }

  // ===== END GENERATED BUILTIN MEMBERS: AABB =====

  companion object {
    val ZERO = AABB(Vector3.ZERO, Vector3.ZERO)
  }
}
