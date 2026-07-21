package net.multigesture.kanama.types

import kotlin.math.abs

/**
 * A plane in Hessian normal form. Kanama value types are immutable snapshots; assign a new value
 * back to the Godot property after changing components.
 *
 * Generated from Godot docs: Plane
 */
data class Plane(
  /**
   * The normal of the plane, typically a unit vector. Shouldn't be a zero vector as `Plane` with
   * such `normal` does not represent a valid plane. In the scalar equation of the plane `ax + by +
   * cz = d`, this is the vector `(a, b, c)`, where `d` is the `d` property.
   *
   * Generated from Godot docs: Plane.normal
   */
  val normal: Vector3,
  /**
   * The distance from the origin to the plane, expressed in terms of `normal` (according to its
   * direction and magnitude). Actual absolute distance from the origin to the plane can be
   * calculated as `abs(d) / normal.length()` (if `normal` has zero length then this `Plane` does
   * not represent a valid plane). In the scalar equation of the plane `ax + by + cz = d`, this is
   * `d`, while the `(a, b, c)` coordinates are represented by the `normal` property.
   *
   * Generated from Godot docs: Plane.d
   */
  val d: real_t,
) {
  constructor(normal: Vector3, d: Number) : this(normal, GodotReal.fromNumber(d))

  // Match GDScript/C# `==`: signed zero equal (-0.0 == 0.0), NaN reflexive. `normal` delegates to
  // Vector3.equals (also fixed); `d` is compared/hashed the same way. See
  // wrapper-coverage-roadmap.md.
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other !is Plane) return false
    return normal == other.normal && (d == other.d || (d.isNaN() && other.d.isNaN()))
  }

  override fun hashCode(): Int = 31 * normal.hashCode() + (d + 0.0f).hashCode()

  /**
   * Returns the shortest distance from the plane to the position `point`. If the point is above the
   * plane, the distance will be positive. If below, the distance will be negative.
   *
   * Generated from Godot docs: Plane.distance_to
   */
  fun distanceTo(point: Vector3): Double =
    (normal.x * point.x + normal.y * point.y + normal.z * point.z - d).toDouble()

  /**
   * Returns the intersection point of a ray consisting of the position `from` and the direction
   * normal `dir` with this plane. If no intersection is found, `null` is returned.
   *
   * Generated from Godot docs: Plane.intersects_ray
   */
  fun intersectsRay(from: Vector3, dir: Vector3): Vector3? {
    val denominator = normal.dot(dir)
    if (abs(denominator) <= 0.00001) return null
    val signedDistance = (normal.dot(from) - d.toDouble()) / denominator
    if (signedDistance > 0.00001) return null
    return from + dir * -signedDistance
  }

  companion object {
    val ZERO = Plane(Vector3.ZERO, 0f)
  }
}
