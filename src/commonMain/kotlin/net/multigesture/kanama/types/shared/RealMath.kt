@file:Suppress("NOTHING_TO_INLINE")

package net.multigesture.kanama.types

import kotlin.math.sqrt

// Godot's own `real_t` formulas (core/math), shared by the native and the Web value types (task
// 134 A2; see ValueTypeStorage.kt for why this directory is compiled by both). Each one keeps
// Godot's operand order — float addition is not associative, so the order is part of the result —
// and computes in `GodotRealStorage`, so the results are Godot's to the bit (as long as the engine
// build does not fuse multiply-adds, which the runtime smoke's parity rows would catch). The
// `make` lambdas are inlined: a value type passes its raw factory, with no allocation in between.

/** `Vector2::dot`. */
internal inline fun realDot(
  ax: GodotRealStorage,
  ay: GodotRealStorage,
  bx: GodotRealStorage,
  by: GodotRealStorage,
): GodotRealStorage = ax * bx + ay * by

/** `Vector3::dot`. */
internal inline fun realDot(
  ax: GodotRealStorage,
  ay: GodotRealStorage,
  az: GodotRealStorage,
  bx: GodotRealStorage,
  by: GodotRealStorage,
  bz: GodotRealStorage,
): GodotRealStorage = ax * bx + ay * by + az * bz

/** `Vector4::dot` and `Quaternion::dot`. */
internal inline fun realDot(
  ax: GodotRealStorage,
  ay: GodotRealStorage,
  az: GodotRealStorage,
  aw: GodotRealStorage,
  bx: GodotRealStorage,
  by: GodotRealStorage,
  bz: GodotRealStorage,
  bw: GodotRealStorage,
): GodotRealStorage = ax * bx + ay * by + az * bz + aw * bw

/** `Vector3::cross`. */
internal inline fun <R> realCross(
  ax: GodotRealStorage,
  ay: GodotRealStorage,
  az: GodotRealStorage,
  bx: GodotRealStorage,
  by: GodotRealStorage,
  bz: GodotRealStorage,
  make: (GodotRealStorage, GodotRealStorage, GodotRealStorage) -> R,
): R = make(ay * bz - az * by, az * bx - ax * bz, ax * by - ay * bx)

/**
 * `VectorN::normalize` for any component count: a non-finite vector and a zero vector become zero,
 * otherwise every component is divided by `sqrt(length_squared)`. [lengthSquared] is the vector's
 * own `real_t` length squared; [finite] whether every component is finite.
 */
internal inline fun <R> realNormalize(
  finite: Boolean,
  lengthSquared: GodotRealStorage,
  zero: () -> R,
  divided: (GodotRealStorage) -> R,
): R = if (!finite || lengthSquared == narrowReal(0.0)) zero() else divided(sqrt(lengthSquared))

/** `Math::lerp(from, to, weight)` in `real_t`. */
internal inline fun realLerp(
  from: GodotRealStorage,
  to: GodotRealStorage,
  weight: GodotRealStorage,
): GodotRealStorage = from + (to - from) * weight

/** `Quaternion::operator*=`, term for term. */
internal inline fun <R> realQuaternionProduct(
  x: GodotRealStorage,
  y: GodotRealStorage,
  z: GodotRealStorage,
  w: GodotRealStorage,
  qx: GodotRealStorage,
  qy: GodotRealStorage,
  qz: GodotRealStorage,
  qw: GodotRealStorage,
  make: (GodotRealStorage, GodotRealStorage, GodotRealStorage, GodotRealStorage) -> R,
): R =
  make(
    w * qx + x * qw + y * qz - z * qy,
    w * qy + y * qw + z * qx - x * qz,
    w * qz + z * qw + x * qy - y * qx,
    w * qw - x * qx - y * qy - z * qz,
  )

/**
 * `Basis::set_quaternion`: [make] receives the nine elements in Godot's row-major order
 * (`rows[0][0]`, `rows[0][1]`, … `rows[2][2]`).
 */
internal inline fun <R> realBasisFromQuaternion(
  x: GodotRealStorage,
  y: GodotRealStorage,
  z: GodotRealStorage,
  w: GodotRealStorage,
  make:
    (
      GodotRealStorage,
      GodotRealStorage,
      GodotRealStorage,
      GodotRealStorage,
      GodotRealStorage,
      GodotRealStorage,
      GodotRealStorage,
      GodotRealStorage,
      GodotRealStorage,
    ) -> R,
): R {
  val one = narrowReal(1.0)
  val d = x * x + y * y + z * z + w * w
  val s = narrowReal(2.0) / d
  val xs = x * s
  val ys = y * s
  val zs = z * s
  val wx = w * xs
  val wy = w * ys
  val wz = w * zs
  val xx = x * xs
  val xy = x * ys
  val xz = x * zs
  val yy = y * ys
  val yz = y * zs
  val zz = z * zs
  return make(
    one - (yy + zz),
    xy - wz,
    xz + wy,
    xy + wz,
    one - (xx + zz),
    yz - wx,
    xz - wy,
    yz + wx,
    one - (xx + yy),
  )
}
