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

  // ===== BEGIN GENERATED BUILTIN MEMBERS: AABB (generate_builtin_ops.py) =====
  operator fun times(other: Transform3D): AABB = transform3DXformInvAabb(other, this)

  /**
   * Returns an `AABB` equivalent to this bounding box, with its width, height, and depth modified
   * to be non-negative values.
   *
   * Generated from Godot docs: AABB.abs
   */
  fun abs(): AABB {
    val f = builtinFrame()
    f.put(0, this)
    f.call(AABBMethods.abs, 0)
    return f.retAABB()
  }

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
   * Returns `true` if the bounding box contains the given `point`. By convention, points exactly on
   * the right, top, and front sides are not included. Note: This method is not reliable for `AABB`
   * with a negative `size`. Use `abs` first to get a valid bounding box.
   *
   * Generated from Godot docs: AABB.has_point
   */
  fun hasPoint(point: Vector3): Boolean =
    !(point.rawX < position.rawX ||
      point.rawY < position.rawY ||
      point.rawZ < position.rawZ ||
      point.rawX > position.rawX + size.rawX ||
      point.rawY > position.rawY + size.rawY ||
      point.rawZ > position.rawZ + size.rawZ)

  /**
   * Returns `true` if this bounding box's values are finite, by calling `Vector3.is_finite` on the
   * `position` and the `size`.
   *
   * Generated from Godot docs: AABB.is_finite
   */
  fun isFinite(): Boolean {
    val f = builtinFrame()
    f.put(0, this)
    f.call(AABBMethods.isFinite, 0)
    return f.retBool()
  }

  /**
   * Returns `true` if this bounding box overlaps with the box `with`. The edges of both boxes are
   * always excluded.
   *
   * Generated from Godot docs: AABB.intersects
   */
  fun intersects(with: AABB): Boolean =
    !(position.rawX >= with.position.rawX + with.size.rawX ||
      position.rawX + size.rawX <= with.position.rawX ||
      position.rawY >= with.position.rawY + with.size.rawY ||
      position.rawY + size.rawY <= with.position.rawY ||
      position.rawZ >= with.position.rawZ + with.size.rawZ ||
      position.rawZ + size.rawZ <= with.position.rawZ)

  /**
   * Returns `true` if this bounding box completely encloses the `with` box. The edges of both boxes
   * are included.
   *
   * Generated from Godot docs: AABB.encloses
   */
  fun encloses(with: AABB): Boolean =
    position.rawX <= with.position.rawX &&
      position.rawX + size.rawX >= with.position.rawX + with.size.rawX &&
      position.rawY <= with.position.rawY &&
      position.rawY + size.rawY >= with.position.rawY + with.size.rawY &&
      position.rawZ <= with.position.rawZ &&
      position.rawZ + size.rawZ >= with.position.rawZ + with.size.rawZ

  /**
   * Returns `true` if this bounding box is on both sides of the given `plane`.
   *
   * Generated from Godot docs: AABB.intersects_plane
   */
  fun intersectsPlane(plane: Plane): Boolean {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, plane)
    f.call(AABBMethods.intersectsPlane, 1)
    return f.retBool()
  }

  /**
   * Returns the intersection between this bounding box and `with`. If the boxes do not intersect,
   * returns an empty `AABB`. If the boxes intersect at the edge, returns a flat `AABB` with no
   * volume (see `has_surface` and `has_volume`).
   *
   * Generated from Godot docs: AABB.intersection
   */
  fun intersection(with: AABB): AABB {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, with)
    f.call(AABBMethods.intersection, 1)
    return f.retAABB()
  }

  /**
   * Returns an `AABB` that encloses both this bounding box and `with` around the edges. See also
   * `encloses`.
   *
   * Generated from Godot docs: AABB.merge
   */
  fun merge(with: AABB): AABB {
    val e1X = size.rawX + position.rawX
    val e2X = with.size.rawX + with.position.rawX
    val nX = if (position.rawX < with.position.rawX) position.rawX else with.position.rawX
    val xX = if (e1X > e2X) e1X else e2X
    val e1Y = size.rawY + position.rawY
    val e2Y = with.size.rawY + with.position.rawY
    val nY = if (position.rawY < with.position.rawY) position.rawY else with.position.rawY
    val xY = if (e1Y > e2Y) e1Y else e2Y
    val e1Z = size.rawZ + position.rawZ
    val e2Z = with.size.rawZ + with.position.rawZ
    val nZ = if (position.rawZ < with.position.rawZ) position.rawZ else with.position.rawZ
    val xZ = if (e1Z > e2Z) e1Z else e2Z
    return AABB(Vector3.raw(nX, nY, nZ), Vector3.raw(xX - nX, xY - nY, xZ - nZ))
  }

  /**
   * Returns a copy of this bounding box expanded to align the edges with the given `to_point`, if
   * necessary.
   *
   * Generated from Godot docs: AABB.expand
   */
  fun expand(toPoint: Vector3): AABB {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, toPoint)
    f.call(AABBMethods.expand, 1)
    return f.retAABB()
  }

  /**
   * Returns a copy of this bounding box extended on all sides by the given amount `by`. A negative
   * amount shrinks the box instead.
   *
   * Generated from Godot docs: AABB.grow
   */
  fun grow(by: Double): AABB {
    val f = builtinFrame()
    f.put(0, this)
    f.putDouble(1, by)
    f.call(AABBMethods.grow, 1)
    return f.retAABB()
  }

  /**
   * Returns the vertex's position of this bounding box that's the farthest in the given direction.
   * This point is commonly known as the support point in collision detection algorithms.
   *
   * Generated from Godot docs: AABB.get_support
   */
  fun getSupport(direction: Vector3): Vector3 {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, direction)
    f.call(AABBMethods.getSupport, 1)
    return f.retVector3()
  }

  /**
   * Returns the longest normalized axis of this bounding box's `size`, as a `Vector3`
   * (`Vector3.RIGHT`, `Vector3.UP`, or `Vector3.BACK`).
   *
   * Generated from Godot docs: AABB.get_longest_axis
   */
  fun getLongestAxis(): Vector3 {
    val f = builtinFrame()
    f.put(0, this)
    f.call(AABBMethods.getLongestAxis, 0)
    return f.retVector3()
  }

  /**
   * Returns the index to the longest axis of this bounding box's `size` (see `Vector3.Axis.X`,
   * `Vector3.Axis.Y`, and `Vector3.Axis.Z`). For an example, see `get_longest_axis`.
   *
   * Generated from Godot docs: AABB.get_longest_axis_index
   */
  fun getLongestAxisIndex(): Long {
    val f = builtinFrame()
    f.put(0, this)
    f.call(AABBMethods.getLongestAxisIndex, 0)
    return f.retLong()
  }

  /**
   * Returns the longest dimension of this bounding box's `size`. For an example, see
   * `get_longest_axis`.
   *
   * Generated from Godot docs: AABB.get_longest_axis_size
   */
  fun getLongestAxisSize(): Double {
    val f = builtinFrame()
    f.put(0, this)
    f.call(AABBMethods.getLongestAxisSize, 0)
    return f.retDouble()
  }

  /**
   * Returns the shortest normalized axis of this bounding box's `size`, as a `Vector3`
   * (`Vector3.RIGHT`, `Vector3.UP`, or `Vector3.BACK`).
   *
   * Generated from Godot docs: AABB.get_shortest_axis
   */
  fun getShortestAxis(): Vector3 {
    val f = builtinFrame()
    f.put(0, this)
    f.call(AABBMethods.getShortestAxis, 0)
    return f.retVector3()
  }

  /**
   * Returns the index to the shortest axis of this bounding box's `size` (see `Vector3.Axis.X`,
   * `Vector3.Axis.Y`, and `Vector3.Axis.Z`). For an example, see `get_shortest_axis`.
   *
   * Generated from Godot docs: AABB.get_shortest_axis_index
   */
  fun getShortestAxisIndex(): Long {
    val f = builtinFrame()
    f.put(0, this)
    f.call(AABBMethods.getShortestAxisIndex, 0)
    return f.retLong()
  }

  /**
   * Returns the shortest dimension of this bounding box's `size`. For an example, see
   * `get_shortest_axis`.
   *
   * Generated from Godot docs: AABB.get_shortest_axis_size
   */
  fun getShortestAxisSize(): Double {
    val f = builtinFrame()
    f.put(0, this)
    f.call(AABBMethods.getShortestAxisSize, 0)
    return f.retDouble()
  }

  /**
   * Returns the position of one of the 8 vertices that compose this bounding box. With an `idx` of
   * `0` this is the same as `position`, and an `idx` of `7` is the same as `end`.
   *
   * Generated from Godot docs: AABB.get_endpoint
   */
  fun getEndpoint(idx: Long): Vector3 {
    val f = builtinFrame()
    f.put(0, this)
    f.putLong(1, idx)
    f.call(AABBMethods.getEndpoint, 1)
    return f.retVector3()
  }

  /**
   * Returns the first point where this bounding box and the given segment intersect, as a
   * `Vector3`. If no intersection occurs, returns `null`. The segment begins at `from` and ends at
   * `to`.
   *
   * Generated from Godot docs: AABB.intersects_segment
   */
  fun intersectsSegment(from: Vector3, to: Vector3): Vector3? {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, from)
    f.put(2, to)
    f.call(AABBMethods.intersectsSegment, 2)
    return if (f.retVariantIsNil()) null
    else Vector3.raw(f.retVariantReal(0), f.retVariantReal(1), f.retVariantReal(2))
  }

  /**
   * Returns the first point where this bounding box and the given ray intersect, as a `Vector3`. If
   * no intersection occurs, returns `null`. The ray begin at `from`, faces `dir` and extends
   * towards infinity.
   *
   * Generated from Godot docs: AABB.intersects_ray
   */
  fun intersectsRay(from: Vector3, dir: Vector3): Vector3? {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, from)
    f.put(2, dir)
    f.call(AABBMethods.intersectsRay, 2)
    return if (f.retVariantIsNil()) null
    else Vector3.raw(f.retVariantReal(0), f.retVariantReal(1), f.retVariantReal(2))
  }

  // ===== END GENERATED BUILTIN MEMBERS: AABB =====

  companion object {
    val ZERO = AABB(Vector3.ZERO, Vector3.ZERO)
  }
}
