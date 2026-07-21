package net.multigesture.kanama.types

/**
 * A plane in 3D space: a unit [normal] and the signed distance [d] from the origin along it. Kanama
 * value types are immutable snapshots.
 *
 * iOS marshalling note: at ptrcall a Plane is 4 `real_t` (float32 on single-precision iOS) —
 * normal.x, normal.y, normal.z, d.
 */
data class Plane(val normal: Vector3, val d: real_t) {
  constructor(
    x: Number,
    y: Number,
    z: Number,
    d: Number,
  ) : this(Vector3(x, y, z), GodotReal.fromNumber(d))

  // Match GDScript/C# `==`: signed zero equal (-0.0 == 0.0), NaN reflexive. `normal` delegates to
  // Vector3.equals (also fixed); `d` is compared/hashed the same way. See
  // wrapper-coverage-roadmap.md.
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other !is Plane) return false
    return normal == other.normal && (d == other.d || (d.isNaN() && other.d.isNaN()))
  }

  override fun hashCode(): Int = 31 * normal.hashCode() + (d + 0.0).hashCode()

  companion object {
    val ZERO = Plane(Vector3.ZERO, GodotReal.fromNumber(0.0))
  }
}
