package net.multigesture.kanama.types

import kotlin.math.abs

/**
 * A plane in Hessian normal form. Kanama value types are immutable snapshots; assign a new value
 * back to the Godot property after changing components.
 *
 * Generated from Godot docs: Plane
 */
class Plane
private constructor(
  /**
   * The normal of the plane, typically a unit vector. Shouldn't be a zero vector as `Plane` with
   * such `normal` does not represent a valid plane. In the scalar equation of the plane `ax + by +
   * cz = d`, this is the vector `(a, b, c)`, where `d` is the `d` property.
   *
   * Generated from Godot docs: Plane.normal
   */
  val normal: Vector3,
  internal val rawD: GodotRealStorage,
  @Suppress("UNUSED_PARAMETER") raw: RawStorage,
) {
  /** A plane whose `d` is stored at Godot's `real_t` width (rounded to it, as in Godot). */
  constructor(normal: Vector3, d: Double) : this(normal, narrowReal(d), RawStorage)

  constructor(normal: Vector3, d: Int) : this(normal, d.toDouble())

  constructor(x: Double, y: Double, z: Double, d: Double) : this(Vector3(x, y, z), d)

  /** GDScript's `Plane(0, 1, 0, 0)`: integer coefficients. */
  constructor(x: Int, y: Int, z: Int, d: Int) : this(Vector3(x, y, z), d.toDouble())

  /**
   * The distance from the origin to the plane, expressed in terms of `normal` (according to its
   * direction and magnitude). Actual absolute distance from the origin to the plane can be
   * calculated as `abs(d) / normal.length()` (if `normal` has zero length then this `Plane` does
   * not represent a valid plane). In the scalar equation of the plane `ax + by + cz = d`, this is
   * `d`, while the `(a, b, c)` coordinates are represented by the `normal` property.
   *
   * Generated from Godot docs: Plane.d
   */
  val d: Double
    get() = widenReal(rawD)

  operator fun component1(): Vector3 = normal

  operator fun component2(): Double = d

  /** This plane with `normal` or `d` replaced. */
  fun copy(normal: Vector3 = this.normal, d: Double = this.d): Plane = Plane(normal, d)

  // Godot's `==` on the stored values: `normal` through Vector3.equals, `d` the same way (signed
  // zero equal, NaN reflexive for the JVM equals contract).
  override fun equals(other: Any?): Boolean =
    this === other || (other is Plane && normal == other.normal && storedEquals(rawD, other.rawD))

  override fun hashCode(): Int = 31 * normal.hashCode() + storedHash(rawD)

  /** Godot's `str(p)`: `[N: (0.0, 1.0, 0.0), D: 0]`. */
  override fun toString(): String = "[N: $normal, D: ${godotRealString(d, false)}]"

  /**
   * Returns the shortest distance from the plane to the position `point`. If the point is above the
   * plane, the distance will be positive. If below, the distance will be negative.
   *
   * Generated from Godot docs: Plane.distance_to
   */
  fun distanceTo(point: Vector3): Double = widenReal(rawDistanceTo(point))

  // Godot: `normal.dot(p_point) - d`, in `real_t`.
  private fun rawDistanceTo(point: Vector3): GodotRealStorage =
    normal.rawX * point.rawX + normal.rawY * point.rawY + normal.rawZ * point.rawZ - rawD

  /**
   * Returns the intersection point of a ray consisting of the position `from` and the direction
   * normal `dir` with this plane. If no intersection is found, `null` is returned.
   *
   * Generated from Godot docs: Plane.intersects_ray
   */
  fun intersectsRay(from: Vector3, dir: Vector3): Vector3? {
    val denominator = normal.dot(dir)
    if (abs(denominator) <= 0.00001) return null
    val signedDistance = rawDistanceTo(from) / narrowReal(denominator)
    if (widenReal(signedDistance) > 0.00001) return null
    return from + dir * widenReal(-signedDistance)
  }

  // ===== BEGIN GENERATED BUILTIN MEMBERS: Plane (generate_builtin_ops.py) =====
  operator fun unaryMinus(): Plane = raw(-normal, -rawD)

  operator fun unaryPlus(): Plane = this

  operator fun times(other: Transform3D): Plane = transform3DXformInvPlane(other, this)

  /**
   * Returns a copy of the plane, with normalized `normal` (so it's a unit vector). Returns
   * `Plane(0, 0, 0, 0)` if `normal` can't be normalized (it has zero length).
   *
   * Generated from Godot docs: Plane.normalized
   */
  fun normalized(): Plane =
    builtinPlane(builtinReals(PlaneMethods.normalized, builtinArg(), 4, emptyList()))

  /**
   * Returns the center of the plane.
   *
   * Generated from Godot docs: Plane.get_center
   */
  fun getCenter(): Vector3 =
    builtinVector3(builtinReals(PlaneMethods.getCenter, builtinArg(), 3, emptyList()))

  /**
   * Returns `true` if this plane and `to_plane` are approximately equal, by running
   * `@GlobalScope.is_equal_approx` on each component.
   *
   * Generated from Godot docs: Plane.is_equal_approx
   */
  fun isEqualApprox(toPlane: Plane): Boolean =
    builtinBool(PlaneMethods.isEqualApprox, builtinArg(), listOf(toPlane.builtinArg()))

  /**
   * Returns `true` if this plane is finite, by calling `@GlobalScope.is_finite` on each component.
   *
   * Generated from Godot docs: Plane.is_finite
   */
  fun isFinite(): Boolean = builtinBool(PlaneMethods.isFinite, builtinArg(), emptyList())

  /**
   * Returns `true` if `point` is located above the plane.
   *
   * Generated from Godot docs: Plane.is_point_over
   */
  fun isPointOver(point: Vector3): Boolean =
    builtinBool(PlaneMethods.isPointOver, builtinArg(), listOf(point.builtinArg()))

  /**
   * Returns `true` if `point` is inside the plane. Comparison uses a custom minimum `tolerance`
   * threshold.
   *
   * Generated from Godot docs: Plane.has_point
   */
  fun hasPoint(point: Vector3, tolerance: Double = 1e-05): Boolean =
    builtinBool(PlaneMethods.hasPoint, builtinArg(), listOf(point.builtinArg(), argReal(tolerance)))

  /**
   * Returns the orthogonal projection of `point` into a point in the plane.
   *
   * Generated from Godot docs: Plane.project
   */
  fun project(point: Vector3): Vector3 =
    builtinVector3(builtinReals(PlaneMethods.project, builtinArg(), 3, listOf(point.builtinArg())))

  /**
   * Returns the intersection point of the three planes `b`, `c` and this plane. If no intersection
   * is found, `null` is returned.
   *
   * Generated from Godot docs: Plane.intersect_3
   */
  fun intersect3(b: Plane, c: Plane): Vector3? =
    builtinVariantReals(
        PlaneMethods.intersect3,
        builtinArg(),
        3,
        listOf(b.builtinArg(), c.builtinArg()),
      )
      ?.let { c -> Vector3.raw(c[0], c[1], c[2]) }

  /**
   * Returns the intersection point of a segment from position `from` to position `to` with this
   * plane. If no intersection is found, `null` is returned.
   *
   * Generated from Godot docs: Plane.intersects_segment
   */
  fun intersectsSegment(from: Vector3, to: Vector3): Vector3? =
    builtinVariantReals(
        PlaneMethods.intersectsSegment,
        builtinArg(),
        3,
        listOf(from.builtinArg(), to.builtinArg()),
      )
      ?.let { c -> Vector3.raw(c[0], c[1], c[2]) }

  // ===== END GENERATED BUILTIN MEMBERS: Plane =====

  companion object {
    /** A plane whose `d` is already at the storage width (marshalling; no conversion). */
    internal fun raw(normal: Vector3, d: GodotRealStorage): Plane = Plane(normal, d, RawStorage)

    val ZERO = Plane(Vector3.ZERO, 0.0)
  }
}
