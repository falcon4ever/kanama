package net.multigesture.kanama.types

import kotlin.math.sqrt

/**
 * A 4-component float vector. Used as the column type of [Projection] and the element type of
 * PackedVector4Array. Kanama value types are immutable snapshots.
 *
 * iOS marshalling note: at ptrcall a Vector4 is 4 `real_t` (float32 on single-precision iOS).
 */
data class Vector4(val x: real_t, val y: real_t, val z: real_t, val w: real_t) {
  constructor(
    x: Number,
    y: Number,
    z: Number,
    w: Number,
  ) : this(
    GodotReal.fromNumber(x),
    GodotReal.fromNumber(y),
    GodotReal.fromNumber(z),
    GodotReal.fromNumber(w),
  )

  // Match GDScript/C# `==`: signed zero equal (-0.0 == 0.0), NaN reflexive. See
  // wrapper-coverage-roadmap.md. hashCode canonicalizes signed zero so equal vectors hash equal.
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other !is Vector4) return false
    return (x == other.x || (x.isNaN() && other.x.isNaN())) &&
      (y == other.y || (y.isNaN() && other.y.isNaN())) &&
      (z == other.z || (z.isNaN() && other.z.isNaN())) &&
      (w == other.w || (w.isNaN() && other.w.isNaN()))
  }

  override fun hashCode(): Int {
    var result = (x + 0.0).hashCode()
    result = 31 * result + (y + 0.0).hashCode()
    result = 31 * result + (z + 0.0).hashCode()
    result = 31 * result + (w + 0.0).hashCode()
    return result
  }

  /** Godot `Vector4.is_equal_approx`: per-component fuzzy compare (CMP_EPSILON tolerance). */
  fun isEqualApprox(other: Vector4): Boolean =
    isEqualApprox(x, other.x) &&
      isEqualApprox(y, other.y) &&
      isEqualApprox(z, other.z) &&
      isEqualApprox(w, other.w)

  /** Godot `Vector4.is_zero_approx`: true if every component is approximately zero. */
  fun isZeroApprox(): Boolean =
    isZeroApprox(x) && isZeroApprox(y) && isZeroApprox(z) && isZeroApprox(w)

  operator fun plus(other: Vector4): Vector4 =
    Vector4(x + other.x, y + other.y, z + other.z, w + other.w)

  operator fun minus(other: Vector4): Vector4 =
    Vector4(x - other.x, y - other.y, z - other.z, w - other.w)

  operator fun times(scale: Number): Vector4 =
    Vector4(
      x.toDouble() * scale.toDouble(),
      y.toDouble() * scale.toDouble(),
      z.toDouble() * scale.toDouble(),
      w.toDouble() * scale.toDouble(),
    )

  operator fun unaryMinus(): Vector4 = Vector4(-x, -y, -z, -w)

  fun lengthSquared(): Double = (x * x + y * y + z * z + w * w).toDouble()

  fun length(): Double = sqrt(lengthSquared())

  companion object {
    val ZERO = Vector4(0.0, 0.0, 0.0, 0.0)
    val ONE = Vector4(1.0, 1.0, 1.0, 1.0)
  }
}
