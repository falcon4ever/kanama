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

  companion object {
    /** A plane whose `d` is already at the storage width (marshalling; no conversion). */
    internal fun raw(normal: Vector3, d: GodotRealStorage): Plane = Plane(normal, d, RawStorage)

    val ZERO = Plane(Vector3.ZERO, 0.0)
  }
}
