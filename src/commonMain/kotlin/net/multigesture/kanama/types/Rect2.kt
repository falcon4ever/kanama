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
  /** Godot's `str(r)`: `[P: (0.0, 0.0), S: (1.0, 1.0)]`. */
  override fun toString(): String = "[P: $position, S: $size]"

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

  fun area(): Double = widenReal(size.rawX * size.rawY)

  // ===== BEGIN GENERATED BUILTIN MEMBERS: Rect2 (generate_builtin_ops.py) =====
  operator fun times(other: Transform2D): Rect2 = transform2DXformInvRect(other, this)

  /**
   * Returns the center point of the rectangle. This is the same as `position + (size / 2.0)`.
   *
   * Generated from Godot docs: Rect2.get_center
   */
  fun getCenter(): Vector2 {
    val half = narrowReal(0.5)
    return Vector2.raw(position.rawX + size.rawX * half, position.rawY + size.rawY * half)
  }

  /**
   * Returns `true` if this rectangle has positive width and height. See also `get_area`.
   *
   * Generated from Godot docs: Rect2.has_area
   */
  fun hasArea(): Boolean = size.x > 0.0 && size.y > 0.0

  /**
   * Returns `true` if the rectangle contains the given `point`. By convention, points on the right
   * and bottom edges are not included. Note: This method is not reliable for `Rect2` with a
   * negative `size`. Use `abs` first to get a valid rectangle.
   *
   * Generated from Godot docs: Rect2.has_point
   */
  fun hasPoint(point: Vector2): Boolean =
    !(point.rawX < position.rawX ||
      point.rawY < position.rawY ||
      point.rawX >= position.rawX + size.rawX ||
      point.rawY >= position.rawY + size.rawY)

  /**
   * Returns `true` if this rectangle's values are finite, by calling `Vector2.is_finite` on the
   * `position` and the `size`.
   *
   * Generated from Godot docs: Rect2.is_finite
   */
  fun isFinite(): Boolean {
    val f = builtinFrame()
    f.put(0, this)
    f.call(Rect2Methods.isFinite, 0)
    return f.retBool()
  }

  /**
   * Returns `true` if this rectangle overlaps with the `b` rectangle. The edges of both rectangles
   * are excluded, unless `include_borders` is `true`.
   *
   * Generated from Godot docs: Rect2.intersects
   */
  fun intersects(b: Rect2, includeBorders: Boolean = false): Boolean =
    if (includeBorders)
      !(position.rawX > b.position.rawX + b.size.rawX ||
        position.rawX + size.rawX < b.position.rawX ||
        position.rawY > b.position.rawY + b.size.rawY ||
        position.rawY + size.rawY < b.position.rawY)
    else
      !(position.rawX >= b.position.rawX + b.size.rawX ||
        position.rawX + size.rawX <= b.position.rawX ||
        position.rawY >= b.position.rawY + b.size.rawY ||
        position.rawY + size.rawY <= b.position.rawY)

  /**
   * Returns `true` if this rectangle completely encloses the `b` rectangle.
   *
   * Generated from Godot docs: Rect2.encloses
   */
  fun encloses(b: Rect2): Boolean =
    b.position.rawX >= position.rawX &&
      b.position.rawY >= position.rawY &&
      b.position.rawX + b.size.rawX <= position.rawX + size.rawX &&
      b.position.rawY + b.size.rawY <= position.rawY + size.rawY

  /**
   * Returns the intersection between this rectangle and `b`. If the rectangles do not intersect,
   * returns an empty `Rect2`.
   *
   * Generated from Godot docs: Rect2.intersection
   */
  fun intersection(b: Rect2): Rect2 {
    if (!b.intersects(this)) return Rect2(Vector2.ZERO, Vector2.ZERO)
    val px = godotMax(b.position.rawX, position.rawX)
    val py = godotMax(b.position.rawY, position.rawY)
    val ex = godotMin(b.position.rawX + b.size.rawX, position.rawX + size.rawX)
    val ey = godotMin(b.position.rawY + b.size.rawY, position.rawY + size.rawY)
    return Rect2(Vector2.raw(px, py), Vector2.raw(ex - px, ey - py))
  }

  /**
   * Returns a `Rect2` that encloses both this rectangle and `b` around the edges. See also
   * `encloses`.
   *
   * Generated from Godot docs: Rect2.merge
   */
  fun merge(b: Rect2): Rect2 {
    val px = godotMin(b.position.rawX, position.rawX)
    val py = godotMin(b.position.rawY, position.rawY)
    val ex = godotMax(b.position.rawX + b.size.rawX, position.rawX + size.rawX)
    val ey = godotMax(b.position.rawY + b.size.rawY, position.rawY + size.rawY)
    return Rect2(Vector2.raw(px, py), Vector2.raw(ex - px, ey - py))
  }

  /**
   * Returns a copy of this rectangle expanded to align the edges with the given `to` point, if
   * necessary.
   *
   * Generated from Godot docs: Rect2.expand
   */
  fun expand(to: Vector2): Rect2 {
    var bx = position.rawX
    var by = position.rawY
    var ex = position.rawX + size.rawX
    var ey = position.rawY + size.rawY
    if (to.rawX < bx) bx = to.rawX
    if (to.rawY < by) by = to.rawY
    if (to.rawX > ex) ex = to.rawX
    if (to.rawY > ey) ey = to.rawY
    return Rect2(Vector2.raw(bx, by), Vector2.raw(ex - bx, ey - by))
  }

  /**
   * Returns the vertex's position of this rect that's the farthest in the given direction. This
   * point is commonly known as the support point in collision detection algorithms.
   *
   * Generated from Godot docs: Rect2.get_support
   */
  fun getSupport(direction: Vector2): Vector2 {
    val zero = narrowReal(0.0)
    return Vector2.raw(
      if (direction.rawX > zero) position.rawX + size.rawX else position.rawX,
      if (direction.rawY > zero) position.rawY + size.rawY else position.rawY,
    )
  }

  /**
   * Returns a copy of this rectangle extended on all sides by the given `amount`. A negative
   * `amount` shrinks the rectangle instead. See also `grow_individual` and `grow_side`.
   *
   * Generated from Godot docs: Rect2.grow
   */
  fun grow(amount: Double): Rect2 {
    val a = narrowReal(amount)
    return Rect2(
      Vector2.raw(position.rawX - a, position.rawY - a),
      Vector2.raw(size.rawX + a * narrowReal(2.0), size.rawY + a * narrowReal(2.0)),
    )
  }

  /**
   * Returns a copy of this rectangle with its `side` extended by the given `amount` (see `Side`
   * constants). A negative `amount` shrinks the rectangle, instead. See also `grow` and
   * `grow_individual`.
   *
   * Generated from Godot docs: Rect2.grow_side
   */
  fun growSide(side: Long, amount: Double): Rect2 =
    growIndividual(
      if (side == 0L) amount else 0.0,
      if (side == 1L) amount else 0.0,
      if (side == 2L) amount else 0.0,
      if (side == 3L) amount else 0.0,
    )

  /**
   * Returns a copy of this rectangle with its `left`, `top`, `right`, and `bottom` sides extended
   * by the given amounts. Negative values shrink the sides, instead. See also `grow` and
   * `grow_side`.
   *
   * Generated from Godot docs: Rect2.grow_individual
   */
  fun growIndividual(left: Double, top: Double, right: Double, bottom: Double): Rect2 {
    val l = narrowReal(left)
    val t = narrowReal(top)
    val r = narrowReal(right)
    val b = narrowReal(bottom)
    return Rect2(
      Vector2.raw(position.rawX - l, position.rawY - t),
      Vector2.raw(size.rawX + (l + r), size.rawY + (t + b)),
    )
  }

  /**
   * Returns a `Rect2` equivalent to this rectangle, with its width and height modified to be
   * non-negative values, and with its `position` being the top-left corner of the rectangle.
   *
   * Generated from Godot docs: Rect2.abs
   */
  fun abs(): Rect2 {
    val zero = narrowReal(0.0)
    return Rect2(
      Vector2.raw(
        position.rawX + godotMin(size.rawX, zero),
        position.rawY + godotMin(size.rawY, zero),
      ),
      Vector2.raw(godotFabs(size.rawX), godotFabs(size.rawY)),
    )
  }

  // ===== END GENERATED BUILTIN MEMBERS: Rect2 =====

  companion object {
    val ZERO = Rect2(Vector2.ZERO, Vector2.ZERO)
  }
}
