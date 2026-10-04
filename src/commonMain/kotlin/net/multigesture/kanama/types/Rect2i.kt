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

  // ===== BEGIN GENERATED BUILTIN MEMBERS: Rect2i (generate_builtin_ops.py) =====
  /**
   * Returns the center point of the rectangle. This is the same as `position + (size / 2)`. Note:
   * If the `size` is odd, the result will be rounded towards `position`.
   *
   * Generated from Godot docs: Rect2i.get_center
   */
  fun getCenter(): Vector2i = Vector2i(position.x + size.x / 2, position.y + size.y / 2)

  /**
   * Returns `true` if this rectangle has positive width and height. See also `get_area`.
   *
   * Generated from Godot docs: Rect2i.has_area
   */
  fun hasArea(): Boolean = size.x > 0 && size.y > 0

  /**
   * Returns `true` if the rectangle contains the given `point`. By convention, points on the right
   * and bottom edges are not included. Note: This method is not reliable for `Rect2i` with a
   * negative `size`. Use `abs` first to get a valid rectangle.
   *
   * Generated from Godot docs: Rect2i.has_point
   */
  fun hasPoint(point: Vector2i): Boolean =
    !(point.x < position.x ||
      point.y < position.y ||
      point.x >= position.x + size.x ||
      point.y >= position.y + size.y)

  /**
   * Returns `true` if this rectangle overlaps with the `b` rectangle. The edges of both rectangles
   * are excluded.
   *
   * Generated from Godot docs: Rect2i.intersects
   */
  fun intersects(b: Rect2i): Boolean =
    !(position.x >= b.position.x + b.size.x ||
      position.x + size.x <= b.position.x ||
      position.y >= b.position.y + b.size.y ||
      position.y + size.y <= b.position.y)

  /**
   * Returns `true` if this `Rect2i` completely encloses another one.
   *
   * Generated from Godot docs: Rect2i.encloses
   */
  fun encloses(b: Rect2i): Boolean =
    b.position.x >= position.x &&
      b.position.y >= position.y &&
      b.position.x + b.size.x <= position.x + size.x &&
      b.position.y + b.size.y <= position.y + size.y

  /**
   * Returns the intersection between this rectangle and `b`. If the rectangles do not intersect,
   * returns an empty `Rect2i`.
   *
   * Generated from Godot docs: Rect2i.intersection
   */
  fun intersection(b: Rect2i): Rect2i {
    if (!b.intersects(this)) return Rect2i(Vector2i(0, 0), Vector2i(0, 0))
    val px = godotMax(b.position.x, position.x)
    val py = godotMax(b.position.y, position.y)
    val ex = godotMin(b.position.x + b.size.x, position.x + size.x)
    val ey = godotMin(b.position.y + b.size.y, position.y + size.y)
    return Rect2i(Vector2i(px, py), Vector2i(ex - px, ey - py))
  }

  /**
   * Returns a `Rect2i` that encloses both this rectangle and `b` around the edges. See also
   * `encloses`.
   *
   * Generated from Godot docs: Rect2i.merge
   */
  fun merge(b: Rect2i): Rect2i {
    val px = godotMin(b.position.x, position.x)
    val py = godotMin(b.position.y, position.y)
    val ex = godotMax(b.position.x + b.size.x, position.x + size.x)
    val ey = godotMax(b.position.y + b.size.y, position.y + size.y)
    return Rect2i(Vector2i(px, py), Vector2i(ex - px, ey - py))
  }

  /**
   * Returns a copy of this rectangle expanded to align the edges with the given `to` point, if
   * necessary.
   *
   * Generated from Godot docs: Rect2i.expand
   */
  fun expand(to: Vector2i): Rect2i {
    var bx = position.x
    var by = position.y
    var ex = position.x + size.x
    var ey = position.y + size.y
    if (to.x < bx) bx = to.x
    if (to.y < by) by = to.y
    if (to.x > ex) ex = to.x
    if (to.y > ey) ey = to.y
    return Rect2i(Vector2i(bx, by), Vector2i(ex - bx, ey - by))
  }

  /**
   * Returns a copy of this rectangle extended on all sides by the given `amount`. A negative
   * `amount` shrinks the rectangle instead. See also `grow_individual` and `grow_side`.
   *
   * Generated from Godot docs: Rect2i.grow
   */
  fun grow(amount: Long): Rect2i {
    val a = amount.toInt()
    return Rect2i(
      Vector2i(position.x - a, position.y - a),
      Vector2i(size.x + a * 2, size.y + a * 2),
    )
  }

  /**
   * Returns a copy of this rectangle with its `side` extended by the given `amount` (see `Side`
   * constants). A negative `amount` shrinks the rectangle, instead. See also `grow` and
   * `grow_individual`.
   *
   * Generated from Godot docs: Rect2i.grow_side
   */
  fun growSide(side: Long, amount: Long): Rect2i =
    growIndividual(
      if (side == 0L) amount else 0L,
      if (side == 1L) amount else 0L,
      if (side == 2L) amount else 0L,
      if (side == 3L) amount else 0L,
    )

  /**
   * Returns a copy of this rectangle with its `left`, `top`, `right`, and `bottom` sides extended
   * by the given amounts. Negative values shrink the sides, instead. See also `grow` and
   * `grow_side`.
   *
   * Generated from Godot docs: Rect2i.grow_individual
   */
  fun growIndividual(left: Long, top: Long, right: Long, bottom: Long): Rect2i {
    val l = left.toInt()
    val t = top.toInt()
    val r = right.toInt()
    val b = bottom.toInt()
    return Rect2i(
      Vector2i(position.x - l, position.y - t),
      Vector2i(size.x + (l + r), size.y + (t + b)),
    )
  }

  /**
   * Returns a `Rect2i` equivalent to this rectangle, with its width and height modified to be
   * non-negative values, and with its `position` being the top-left corner of the rectangle.
   *
   * Generated from Godot docs: Rect2i.abs
   */
  fun abs(): Rect2i =
    Rect2i(
      Vector2i(position.x + godotMin(size.x, 0), position.y + godotMin(size.y, 0)),
      Vector2i(godotAbs(size.x), godotAbs(size.y)),
    )

  // ===== END GENERATED BUILTIN MEMBERS: Rect2i =====

  companion object {
    val ZERO = Rect2i(Vector2i(0, 0), Vector2i(0, 0))
  }
}
