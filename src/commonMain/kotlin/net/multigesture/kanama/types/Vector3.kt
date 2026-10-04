package net.multigesture.kanama.types

import kotlin.jvm.JvmInline
import kotlin.math.abs
import kotlin.math.sqrt

// One body for every backend (task 104 step 2): methods whose result depends on Godot's own
// edge-case handling (epsilons, normalization) are computed by the engine through BuiltinCalls;
// exact arithmetic is plain Kotlin. At ptrcall a Vector3 is 3 `real_t` in x, y, z order.
/**
 * A 3D vector using floating-point coordinates. Kanama value types are immutable snapshots; assign
 * a new value back to the Godot property after changing components.
 *
 * Generated from Godot docs: Vector3
 */
class Vector3
private constructor(
  internal val rawX: GodotRealStorage,
  internal val rawY: GodotRealStorage,
  internal val rawZ: GodotRealStorage,
  @Suppress("UNUSED_PARAMETER") raw: RawStorage,
) {
  // ===== BEGIN GENERATED ENUMS: Vector3 (scripts/generate_api_wrapper.py — do not edit) =====
  /**
   * Godot's `Vector3.Axis` enum as a typed value: `.value` is the raw number Godot uses, and the
   * companion holds the named values (`Vector3.Axis.<NAME>`).
   *
   * Generated from Godot docs: Vector3.Axis
   */
  @JvmInline
  value class Axis(override val value: Long) : net.multigesture.kanama.api.GodotEnumValue {
    companion object {
      /**
       * Enumerated value for the X axis. Returned by `max_axis_index` and `min_axis_index`.
       *
       * Generated from Godot docs: Vector3.AXIS_X
       */
      val X: Axis
        get() = Axis(0L)

      /**
       * Enumerated value for the Y axis. Returned by `max_axis_index` and `min_axis_index`.
       *
       * Generated from Godot docs: Vector3.AXIS_Y
       */
      val Y: Axis
        get() = Axis(1L)

      /**
       * Enumerated value for the Z axis. Returned by `max_axis_index` and `min_axis_index`.
       *
       * Generated from Godot docs: Vector3.AXIS_Z
       */
      val Z: Axis
        get() = Axis(2L)
    }
  }

  // ===== END GENERATED ENUMS: Vector3 =====

  /** A vector stored at Godot's `real_t` width: each component is rounded to it, as in Godot. */
  constructor(
    x: Double,
    y: Double,
    z: Double,
  ) : this(narrowReal(x), narrowReal(y), narrowReal(z), RawStorage)

  /** GDScript's `Vector3(0, 1, 0)`: integer components. */
  constructor(x: Int, y: Int, z: Int) : this(x.toDouble(), y.toDouble(), z.toDouble())

  // GDScript's mixed `Vector3(x, 0, 0)`: every Int/Double mix, so exactly one overload matches a
  // call and none boxes (hand-written: the value types have no generator path for constructors).
  constructor(x: Int, y: Int, z: Double) : this(x.toDouble(), y.toDouble(), z)

  constructor(x: Int, y: Double, z: Int) : this(x.toDouble(), y, z.toDouble())

  constructor(x: Int, y: Double, z: Double) : this(x.toDouble(), y, z)

  constructor(x: Double, y: Int, z: Int) : this(x, y.toDouble(), z.toDouble())

  constructor(x: Double, y: Int, z: Double) : this(x, y.toDouble(), z)

  constructor(x: Double, y: Double, z: Int) : this(x, y, z.toDouble())

  /**
   * The vector's X component. Also accessible by using the index position `[0]`.
   *
   * Generated from Godot docs: Vector3.x
   */
  val x: Double
    get() = widenReal(rawX)

  /**
   * The vector's Y component. Also accessible by using the index position `[1]`.
   *
   * Generated from Godot docs: Vector3.y
   */
  val y: Double
    get() = widenReal(rawY)

  /**
   * The vector's Z component. Also accessible by using the index position `[2]`.
   *
   * Generated from Godot docs: Vector3.z
   */
  val z: Double
    get() = widenReal(rawZ)

  operator fun component1(): Double = x

  operator fun component2(): Double = y

  operator fun component3(): Double = z

  /** This vector with some components replaced. */
  fun copy(x: Double = this.x, y: Double = this.y, z: Double = this.z): Vector3 = Vector3(x, y, z)

  // Godot's `==` on the stored components (signed zero equal, -0.0 == 0.0); NaN equals NaN to keep
  // the JVM equals contract reflexive. hashCode canonicalizes signed zero so equal vectors hash
  // equal.
  override fun equals(other: Any?): Boolean =
    this === other ||
      (other is Vector3 &&
        storedEquals(rawX, other.rawX) &&
        storedEquals(rawY, other.rawY) &&
        storedEquals(rawZ, other.rawZ))

  override fun hashCode(): Int = 31 * (31 * storedHash(rawX) + storedHash(rawY)) + storedHash(rawZ)

  /** Godot's `str(v)`: `(0.1, 0.2, 0.3)`. */
  override fun toString(): String =
    "(${godotRealString(x, true)}, ${godotRealString(y, true)}, ${godotRealString(z, true)})"

  /** Godot `Vector3.is_equal_approx`: per-component fuzzy compare (CMP_EPSILON tolerance). */
  /**
   * Returns `true` if this vector and `to` are approximately equal, by running
   * `@GlobalScope.is_equal_approx` on each component.
   *
   * Generated from Godot docs: Vector3.is_equal_approx
   */
  fun isEqualApprox(other: Vector3): Boolean =
    isEqualApprox(x, other.x) && isEqualApprox(y, other.y) && isEqualApprox(z, other.z)

  /** Godot `Vector3.is_zero_approx`: true if every component is approximately zero. */
  /**
   * Returns `true` if this vector's values are approximately zero, by running
   * `@GlobalScope.is_zero_approx` on each component. This method is faster than using
   * `is_equal_approx` with one value as a zero vector.
   *
   * Generated from Godot docs: Vector3.is_zero_approx
   */
  fun isZeroApprox(): Boolean = isZeroApprox(x) && isZeroApprox(y) && isZeroApprox(z)

  operator fun plus(other: Vector3): Vector3 =
    raw(rawX + other.rawX, rawY + other.rawY, rawZ + other.rawZ)

  operator fun minus(other: Vector3): Vector3 =
    raw(rawX - other.rawX, rawY - other.rawY, rawZ - other.rawZ)

  // A scalar operand is a `real_t` in Godot (`Vector3 * float` narrows the float first).
  operator fun times(scale: Double): Vector3 = scaled(narrowReal(scale))

  operator fun times(scale: Float): Vector3 = scaled(narrowReal(scale.toDouble()))

  operator fun times(scale: Int): Vector3 = scaled(narrowReal(scale.toDouble()))

  operator fun times(scale: Long): Vector3 = scaled(narrowReal(scale.toDouble()))

  operator fun div(scale: Double): Vector3 = divided(narrowReal(scale))

  operator fun div(scale: Float): Vector3 = divided(narrowReal(scale.toDouble()))

  operator fun div(scale: Int): Vector3 = divided(narrowReal(scale.toDouble()))

  operator fun div(scale: Long): Vector3 = divided(narrowReal(scale.toDouble()))

  private fun scaled(s: GodotRealStorage): Vector3 = raw(rawX * s, rawY * s, rawZ * s)

  private fun divided(s: GodotRealStorage): Vector3 = raw(rawX / s, rawY / s, rawZ / s)

  operator fun unaryMinus(): Vector3 = raw(-rawX, -rawY, -rawZ)

  /**
   * Returns the squared length (squared magnitude) of this vector. This method runs faster than
   * `length`, so prefer it if you need to compare vectors or need the squared distance for some
   * formula.
   *
   * Generated from Godot docs: Vector3.length_squared
   */
  fun lengthSquared(): Double = widenReal(rawLengthSquared())

  private fun rawLengthSquared(): GodotRealStorage = realDot(rawX, rawY, rawZ, rawX, rawY, rawZ)

  /**
   * Returns the length (magnitude) of this vector.
   *
   * Generated from Godot docs: Vector3.length
   */
  fun length(): Double = widenReal(sqrt(rawLengthSquared()))

  /**
   * Returns the result of scaling the vector to unit length. Equivalent to `v / v.length()`.
   * Returns `(0, 0, 0)` if `v.length() == 0`. See also `is_normalized`. Note: This function may
   * return incorrect values if the input vector length is near zero.
   *
   * Generated from Godot docs: Vector3.normalized
   */
  fun normalized(): Vector3 {
    return realNormalize(
      rawX.isFinite() && rawY.isFinite() && rawZ.isFinite(),
      rawLengthSquared(),
      { ZERO },
      { len -> raw(rawX / len, rawY / len, rawZ / len) },
    )
  }

  /**
   * Returns `true` if the vector is normalized, i.e. its length is approximately equal to 1.
   *
   * Generated from Godot docs: Vector3.is_normalized
   */
  fun isNormalized(): Boolean {
    // Godot `Vector3::is_normalized`: Math::is_equal_approx(length_squared(), 1, UNIT_EPSILON),
    // whose exact-equality short-circuit is what makes an infinite component behave.
    val lengthSquared = lengthSquared()
    return lengthSquared == 1.0 || abs(lengthSquared - 1.0) < UNIT_EPSILON
  }

  /**
   * Returns the dot product of this vector and `with`. This can be used to compare the angle
   * between two vectors. For example, this can be used to determine whether an enemy is facing the
   * player. The dot product will be `0` for a right angle (90 degrees), greater than 0 for angles
   * narrower than 90 degrees and lower than 0 for angles wider than 90 degrees. When using unit
   * (normalized) vectors, the result will always be between `-1.0` (180 degree angle) when the
   * vectors are facing opposite directions, and `1.0` (0 degree angle) when the vectors are
   * aligned. Note: `a.dot(b)` is equivalent to `b.dot(a)`.
   *
   * Generated from Godot docs: Vector3.dot
   */
  fun dot(other: Vector3): Double =
    widenReal(realDot(rawX, rawY, rawZ, other.rawX, other.rawY, other.rawZ))

  /**
   * Returns the cross product of this vector and `with`. This returns a vector perpendicular to
   * both this and `with`, which would be the normal vector of the plane defined by the two vectors.
   * As there are two such vectors, in opposite directions, this method returns the vector defined
   * by a right-handed coordinate system. If the two vectors are parallel this returns an empty
   * vector, making it useful for testing if two vectors are parallel.
   *
   * Generated from Godot docs: Vector3.cross
   */
  fun cross(other: Vector3): Vector3 =
    realCross(rawX, rawY, rawZ, other.rawX, other.rawY, other.rawZ) { x, y, z -> raw(x, y, z) }

  /**
   * Returns the Euclidean distance (https://en.wikipedia.org/wiki/Euclidean_distance) between this
   * vector and `to`.
   *
   * Generated from Godot docs: Vector3.distance_to
   */
  fun distanceTo(other: Vector3): Double = (this - other).length()

  /**
   * Returns the squared Euclidean distance (https://en.wikipedia.org/wiki/Euclidean_distance)
   * between this vector and `to`. This method runs faster than `distance_to`, so prefer it if you
   * need to compare vectors or need the squared distance for some formula.
   *
   * Generated from Godot docs: Vector3.distance_squared_to
   */
  fun distanceSquaredTo(other: Vector3): Double = (this - other).lengthSquared()

  fun withX(value: Double): Vector3 = raw(narrowReal(value), rawY, rawZ)

  fun withY(value: Double): Vector3 = raw(rawX, narrowReal(value), rawZ)

  fun withZ(value: Double): Vector3 = raw(rawX, rawY, narrowReal(value))

  fun withX(value: Int): Vector3 = withX(value.toDouble())

  fun withY(value: Int): Vector3 = withY(value.toDouble())

  fun withZ(value: Int): Vector3 = withZ(value.toDouble())

  // ===== BEGIN GENERATED BUILTIN MEMBERS: Vector3 (generate_builtin_ops.py) =====
  operator fun unaryPlus(): Vector3 = this

  operator fun compareTo(other: Vector3): Int {
    val c0 = godotCompareStep(rawX, other.rawX)
    if (c0 != 0) return c0
    val c1 = godotCompareStep(rawY, other.rawY)
    if (c1 != 0) return c1
    return godotCompareStep(rawZ, other.rawZ)
  }

  operator fun times(other: Vector3): Vector3 =
    raw(rawX * other.rawX, rawY * other.rawY, rawZ * other.rawZ)

  operator fun div(other: Vector3): Vector3 =
    raw(rawX / other.rawX, rawY / other.rawY, rawZ / other.rawZ)

  operator fun times(other: Quaternion): Vector3 = quaternionXformInv(other, this)

  operator fun times(other: Basis): Vector3 = basisXformInv(other, this)

  operator fun times(other: Transform3D): Vector3 = transform3DXformInv(other, this)

  /**
   * Returns the axis of the vector's lowest value. See `AXIS_*` constants. If all components are
   * equal, this method returns `Axis.Z`.
   *
   * Generated from Godot docs: Vector3.min_axis_index
   */
  fun minAxisIndex(): Long =
    if (rawX < rawY) (if (rawX < rawZ) 0L else 2L) else (if (rawY < rawZ) 1L else 2L)

  /**
   * Returns the axis of the vector's highest value. See `AXIS_*` constants. If all components are
   * equal, this method returns `Axis.X`.
   *
   * Generated from Godot docs: Vector3.max_axis_index
   */
  fun maxAxisIndex(): Long =
    if (rawX < rawY) (if (rawY < rawZ) 2L else 1L) else (if (rawX < rawZ) 2L else 0L)

  /**
   * Returns the unsigned minimum angle to the given vector, in radians.
   *
   * Generated from Godot docs: Vector3.angle_to
   */
  fun angleTo(to: Vector3): Double {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, to)
    f.call(Vector3Methods.angleTo, 1)
    return f.retDouble()
  }

  /**
   * Returns the signed angle to the given vector, in radians. The sign of the angle is positive in
   * a counter-clockwise direction and negative in a clockwise direction when viewed from the side
   * specified by the `axis`.
   *
   * Generated from Godot docs: Vector3.signed_angle_to
   */
  fun signedAngleTo(to: Vector3, axis: Vector3): Double {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, to)
    f.put(2, axis)
    f.call(Vector3Methods.signedAngleTo, 2)
    return f.retDouble()
  }

  /**
   * Returns the normalized vector pointing from this vector to `to`. This is equivalent to using
   * `(b - a).normalized()`.
   *
   * Generated from Godot docs: Vector3.direction_to
   */
  fun directionTo(to: Vector3): Vector3 =
    raw(to.rawX - rawX, to.rawY - rawY, to.rawZ - rawZ).normalized()

  /**
   * Returns the vector with a maximum length by limiting its length to `length`. If the vector is
   * non-finite, the result is undefined.
   *
   * Generated from Godot docs: Vector3.limit_length
   */
  fun limitLength(length: Double = 1.0): Vector3 {
    val p = narrowReal(length)
    val l = godotSqrt(rawX * rawX + rawY * rawY + rawZ * rawZ)
    if (!(l > narrowReal(0.0) && p < l)) return this
    return raw(rawX / l * p, rawY / l * p, rawZ / l * p)
  }

  /**
   * Returns `true` if this vector is finite, by calling `@GlobalScope.is_finite` on each component.
   *
   * Generated from Godot docs: Vector3.is_finite
   */
  fun isFinite(): Boolean = rawX.isFinite() && rawY.isFinite() && rawZ.isFinite()

  /**
   * Returns the inverse of the vector. This is the same as `Vector3(1.0 / v.x, 1.0 / v.y, 1.0 /
   * v.z)`.
   *
   * Generated from Godot docs: Vector3.inverse
   */
  fun inverse(): Vector3 {
    val one = narrowReal(1.0)
    return raw(one / rawX, one / rawY, one / rawZ)
  }

  /**
   * Returns a new vector with all components clamped between the components of `min` and `max`, by
   * running `@GlobalScope.clamp` on each component.
   *
   * Generated from Godot docs: Vector3.clamp
   */
  fun clamp(min: Vector3, max: Vector3): Vector3 =
    raw(
      godotClamp(rawX, min.rawX, max.rawX),
      godotClamp(rawY, min.rawY, max.rawY),
      godotClamp(rawZ, min.rawZ, max.rawZ),
    )

  /**
   * Returns a new vector with all components clamped between `min` and `max`, by running
   * `@GlobalScope.clamp` on each component.
   *
   * Generated from Godot docs: Vector3.clampf
   */
  fun clampf(min: Double, max: Double): Vector3 {
    val lo = narrowReal(min)
    val hi = narrowReal(max)
    return raw(godotClamp(rawX, lo, hi), godotClamp(rawY, lo, hi), godotClamp(rawZ, lo, hi))
  }

  /**
   * Returns a new vector with each component snapped to the nearest multiple of the corresponding
   * component in `step`. This can also be used to round the components to an arbitrary number of
   * decimals.
   *
   * Generated from Godot docs: Vector3.snapped
   */
  fun snapped(step: Vector3): Vector3 {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, step)
    f.call(Vector3Methods.snapped, 1)
    return f.retVector3()
  }

  /**
   * Returns a new vector with each component snapped to the nearest multiple of `step`. This can
   * also be used to round the components to an arbitrary number of decimals.
   *
   * Generated from Godot docs: Vector3.snappedf
   */
  fun snappedf(step: Double): Vector3 {
    val f = builtinFrame()
    f.put(0, this)
    f.putDouble(1, step)
    f.call(Vector3Methods.snappedf, 1)
    return f.retVector3()
  }

  /**
   * Returns the result of rotating this vector around a given axis by `angle` (in radians). The
   * axis must be a normalized vector. See also `@GlobalScope.deg_to_rad`.
   *
   * Generated from Godot docs: Vector3.rotated
   */
  fun rotated(axis: Vector3, angle: Double): Vector3 {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, axis)
    f.putDouble(2, angle)
    f.call(Vector3Methods.rotated, 2)
    return f.retVector3()
  }

  /**
   * Returns the result of the linear interpolation between this vector and `to` by amount `weight`.
   * `weight` is on the range of `0.0` to `1.0`, representing the amount of interpolation.
   *
   * Generated from Godot docs: Vector3.lerp
   */
  fun lerp(to: Vector3, weight: Double): Vector3 {
    val w = narrowReal(weight)
    return raw(realLerp(rawX, to.rawX, w), realLerp(rawY, to.rawY, w), realLerp(rawZ, to.rawZ, w))
  }

  /**
   * Returns the result of spherical linear interpolation between this vector and `to`, by amount
   * `weight`. `weight` is on the range of 0.0 to 1.0, representing the amount of interpolation.
   * This method also handles interpolating the lengths if the input vectors have different lengths.
   * For the special case of one or both input vectors having zero length, this method behaves like
   * `lerp`.
   *
   * Generated from Godot docs: Vector3.slerp
   */
  fun slerp(to: Vector3, weight: Double): Vector3 {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, to)
    f.putDouble(2, weight)
    f.call(Vector3Methods.slerp, 2)
    return f.retVector3()
  }

  /**
   * Performs a cubic interpolation between this vector and `b` using `pre_a` and `post_b` as
   * handles, and returns the result at position `weight`. `weight` is on the range of 0.0 to 1.0,
   * representing the amount of interpolation.
   *
   * Generated from Godot docs: Vector3.cubic_interpolate
   */
  fun cubicInterpolate(b: Vector3, preA: Vector3, postB: Vector3, weight: Double): Vector3 {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, b)
    f.put(2, preA)
    f.put(3, postB)
    f.putDouble(4, weight)
    f.call(Vector3Methods.cubicInterpolate, 4)
    return f.retVector3()
  }

  /**
   * Performs a cubic interpolation between this vector and `b` using `pre_a` and `post_b` as
   * handles, and returns the result at position `weight`. `weight` is on the range of 0.0 to 1.0,
   * representing the amount of interpolation. It can perform smoother interpolation than
   * `cubic_interpolate` by the time values.
   *
   * Generated from Godot docs: Vector3.cubic_interpolate_in_time
   */
  fun cubicInterpolateInTime(
    b: Vector3,
    preA: Vector3,
    postB: Vector3,
    weight: Double,
    bT: Double,
    preAT: Double,
    postBT: Double,
  ): Vector3 {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, b)
    f.put(2, preA)
    f.put(3, postB)
    f.putDouble(4, weight)
    f.putDouble(5, bT)
    f.putDouble(6, preAT)
    f.putDouble(7, postBT)
    f.call(Vector3Methods.cubicInterpolateInTime, 7)
    return f.retVector3()
  }

  /**
   * Returns the point at the given `t` on the Bézier curve
   * (https://en.wikipedia.org/wiki/B%C3%A9zier_curve) defined by this vector and the given
   * `control_1`, `control_2`, and `end` points.
   *
   * Generated from Godot docs: Vector3.bezier_interpolate
   */
  fun bezierInterpolate(control1: Vector3, control2: Vector3, end: Vector3, t: Double): Vector3 {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, control1)
    f.put(2, control2)
    f.put(3, end)
    f.putDouble(4, t)
    f.call(Vector3Methods.bezierInterpolate, 4)
    return f.retVector3()
  }

  /**
   * Returns the derivative at the given `t` on the Bézier curve
   * (https://en.wikipedia.org/wiki/B%C3%A9zier_curve) defined by this vector and the given
   * `control_1`, `control_2`, and `end` points.
   *
   * Generated from Godot docs: Vector3.bezier_derivative
   */
  fun bezierDerivative(control1: Vector3, control2: Vector3, end: Vector3, t: Double): Vector3 {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, control1)
    f.put(2, control2)
    f.put(3, end)
    f.putDouble(4, t)
    f.call(Vector3Methods.bezierDerivative, 4)
    return f.retVector3()
  }

  /**
   * Returns a new vector moved toward `to` by the fixed `delta` amount. Will not go past the final
   * value.
   *
   * Generated from Godot docs: Vector3.move_toward
   */
  fun moveToward(to: Vector3, delta: Double): Vector3 {
    val d = narrowReal(delta)
    val v0 = to.rawX - rawX
    val v1 = to.rawY - rawY
    val v2 = to.rawZ - rawZ
    val len = godotSqrt(v0 * v0 + v1 * v1 + v2 * v2)
    if (len <= d || len < narrowReal(0.00001)) return to
    return raw(rawX + v0 / len * d, rawY + v1 / len * d, rawZ + v2 / len * d)
  }

  /**
   * Returns the outer product with `with`.
   *
   * Generated from Godot docs: Vector3.outer
   */
  fun outer(with: Vector3): Basis {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, with)
    f.call(Vector3Methods.outer, 1)
    return f.retBasis()
  }

  /**
   * Returns a new vector with all components in absolute values (i.e. positive).
   *
   * Generated from Godot docs: Vector3.abs
   */
  fun abs(): Vector3 = raw(godotFabs(rawX), godotFabs(rawY), godotFabs(rawZ))

  /**
   * Returns a new vector with all components rounded down (towards negative infinity).
   *
   * Generated from Godot docs: Vector3.floor
   */
  fun floor(): Vector3 = raw(godotFloor(rawX), godotFloor(rawY), godotFloor(rawZ))

  /**
   * Returns a new vector with all components rounded up (towards positive infinity).
   *
   * Generated from Godot docs: Vector3.ceil
   */
  fun ceil(): Vector3 = raw(godotCeil(rawX), godotCeil(rawY), godotCeil(rawZ))

  /**
   * Returns a new vector with all components rounded to the nearest integer, with halfway cases
   * rounded away from zero.
   *
   * Generated from Godot docs: Vector3.round
   */
  fun round(): Vector3 = raw(godotRound(rawX), godotRound(rawY), godotRound(rawZ))

  /**
   * Returns a vector composed of the `@GlobalScope.fposmod` of this vector's components and `mod`.
   *
   * Generated from Godot docs: Vector3.posmod
   */
  fun posmod(mod: Double): Vector3 {
    val m = narrowReal(mod)
    return raw(godotFposmod(rawX, m), godotFposmod(rawY, m), godotFposmod(rawZ, m))
  }

  /**
   * Returns a vector composed of the `@GlobalScope.fposmod` of this vector's components and
   * `modv`'s components.
   *
   * Generated from Godot docs: Vector3.posmodv
   */
  fun posmodv(modv: Vector3): Vector3 =
    raw(godotFposmod(rawX, modv.rawX), godotFposmod(rawY, modv.rawY), godotFposmod(rawZ, modv.rawZ))

  /**
   * Returns a new vector resulting from projecting this vector onto the given vector `b`. The
   * resulting new vector is parallel to `b`. See also `slide`. Note: If the vector `b` is a zero
   * vector, the components of the resulting new vector will be `@GDScript.NAN`.
   *
   * Generated from Godot docs: Vector3.project
   */
  fun project(b: Vector3): Vector3 {
    val s =
      (rawX * b.rawX + rawY * b.rawY + rawZ * b.rawZ) /
        (b.rawX * b.rawX + b.rawY * b.rawY + b.rawZ * b.rawZ)
    return raw(b.rawX * s, b.rawY * s, b.rawZ * s)
  }

  /**
   * Returns a new vector resulting from sliding this vector along a plane with normal `n`. The
   * resulting new vector is perpendicular to `n`, and is equivalent to this vector minus its
   * projection on `n`. See also `project`. Note: The vector `n` must be normalized. See also
   * `normalized`.
   *
   * Generated from Godot docs: Vector3.slide
   */
  fun slide(n: Vector3): Vector3 {
    val d = rawX * n.rawX + rawY * n.rawY + rawZ * n.rawZ
    return raw(rawX - n.rawX * d, rawY - n.rawY * d, rawZ - n.rawZ * d)
  }

  /**
   * Returns the vector "bounced off" from a plane defined by the given normal `n`. Note: `bounce`
   * performs the operation that most engines and frameworks call `reflect()`.
   *
   * Generated from Godot docs: Vector3.bounce
   */
  fun bounce(n: Vector3): Vector3 {
    val d = rawX * n.rawX + rawY * n.rawY + rawZ * n.rawZ
    val two = narrowReal(2.0)
    return raw(-(n.rawX * two * d - rawX), -(n.rawY * two * d - rawY), -(n.rawZ * two * d - rawZ))
  }

  /**
   * Returns the result of reflecting the vector through a plane defined by the given normal vector
   * `n`. Note: `reflect` differs from what other engines and frameworks call `reflect()`. In other
   * engines, `reflect()` returns the result of the vector reflected by the given plane. The
   * reflection thus passes through the given normal. While in Godot the reflection passes through
   * the plane and can be thought of as bouncing off the normal. See also `bounce` which does what
   * most engines call `reflect()`.
   *
   * Generated from Godot docs: Vector3.reflect
   */
  fun reflect(line: Vector3): Vector3 {
    val d = rawX * line.rawX + rawY * line.rawY + rawZ * line.rawZ
    val two = narrowReal(2.0)
    return raw(line.rawX * two * d - rawX, line.rawY * two * d - rawY, line.rawZ * two * d - rawZ)
  }

  /**
   * Returns a new vector with each component set to `1.0` if it's positive, `-1.0` if it's
   * negative, and `0.0` if it's zero. The result is identical to calling `@GlobalScope.sign` on
   * each component.
   *
   * Generated from Godot docs: Vector3.sign
   */
  fun sign(): Vector3 = raw(godotSign(rawX), godotSign(rawY), godotSign(rawZ))

  /**
   * Returns the octahedral-encoded (oct32) form of this `Vector3` as a `Vector2`. Since a `Vector2`
   * occupies 1/3 less memory compared to `Vector3`, this form of compression can be used to pass
   * greater amounts of `normalized` `Vector3`s without increasing storage or memory requirements.
   * See also `octahedron_decode`. Note: `octahedron_encode` can only be used for `normalized`
   * vectors. `octahedron_encode` does not check whether this `Vector3` is normalized, and will
   * return a value that does not decompress to the original value if the `Vector3` is not
   * normalized. Note: Octahedral compression is lossy, although visual differences are rarely
   * perceptible in real world scenarios.
   *
   * Generated from Godot docs: Vector3.octahedron_encode
   */
  fun octahedronEncode(): Vector2 {
    val f = builtinFrame()
    f.put(0, this)
    f.call(Vector3Methods.octahedronEncode, 0)
    return f.retVector2()
  }

  /**
   * Returns the component-wise minimum of this and `with`, equivalent to `Vector3(minf(x, with.x),
   * minf(y, with.y), minf(z, with.z))`.
   *
   * Generated from Godot docs: Vector3.min
   */
  fun min(with: Vector3): Vector3 =
    raw(godotMin(rawX, with.rawX), godotMin(rawY, with.rawY), godotMin(rawZ, with.rawZ))

  /**
   * Returns the component-wise minimum of this and `with`, equivalent to `Vector3(minf(x, with),
   * minf(y, with), minf(z, with))`.
   *
   * Generated from Godot docs: Vector3.minf
   */
  fun minf(with: Double): Vector3 {
    val s = narrowReal(with)
    return raw(godotMin(rawX, s), godotMin(rawY, s), godotMin(rawZ, s))
  }

  /**
   * Returns the component-wise maximum of this and `with`, equivalent to `Vector3(maxf(x, with.x),
   * maxf(y, with.y), maxf(z, with.z))`.
   *
   * Generated from Godot docs: Vector3.max
   */
  fun max(with: Vector3): Vector3 =
    raw(godotMax(rawX, with.rawX), godotMax(rawY, with.rawY), godotMax(rawZ, with.rawZ))

  /**
   * Returns the component-wise maximum of this and `with`, equivalent to `Vector3(maxf(x, with),
   * maxf(y, with), maxf(z, with))`.
   *
   * Generated from Godot docs: Vector3.maxf
   */
  fun maxf(with: Double): Vector3 {
    val s = narrowReal(with)
    return raw(godotMax(rawX, s), godotMax(rawY, s), godotMax(rawZ, s))
  }

  // ===== END GENERATED BUILTIN MEMBERS: Vector3 =====

  companion object {
    // ===== BEGIN GENERATED BUILTIN STATICS: Vector3 (generate_builtin_ops.py) =====
    /**
     * Infinity vector, a vector with all components set to `@GDScript.INF`.
     *
     * Generated from Godot docs: Vector3.INF
     */
    val INF: Vector3 =
      Vector3(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY)

    /**
     * Unit vector pointing towards the left side of imported 3D assets.
     *
     * Generated from Godot docs: Vector3.MODEL_LEFT
     */
    val MODEL_LEFT: Vector3 = Vector3(1.0, 0.0, 0.0)

    /**
     * Unit vector pointing towards the right side of imported 3D assets.
     *
     * Generated from Godot docs: Vector3.MODEL_RIGHT
     */
    val MODEL_RIGHT: Vector3 = Vector3(-1.0, 0.0, 0.0)

    /**
     * Unit vector pointing towards the top side (up) of imported 3D assets.
     *
     * Generated from Godot docs: Vector3.MODEL_TOP
     */
    val MODEL_TOP: Vector3 = Vector3(0.0, 1.0, 0.0)

    /**
     * Unit vector pointing towards the bottom side (down) of imported 3D assets.
     *
     * Generated from Godot docs: Vector3.MODEL_BOTTOM
     */
    val MODEL_BOTTOM: Vector3 = Vector3(0.0, -1.0, 0.0)

    /**
     * Unit vector pointing towards the front side (facing forward) of imported 3D assets.
     *
     * Generated from Godot docs: Vector3.MODEL_FRONT
     */
    val MODEL_FRONT: Vector3 = Vector3(0.0, 0.0, 1.0)

    /**
     * Unit vector pointing towards the rear side (back) of imported 3D assets.
     *
     * Generated from Godot docs: Vector3.MODEL_REAR
     */
    val MODEL_REAR: Vector3 = Vector3(0.0, 0.0, -1.0)

    /**
     * Returns the `Vector3` from an octahedral-compressed form created using `octahedron_encode`
     * (stored as a `Vector2`).
     *
     * Generated from Godot docs: Vector3.octahedron_decode
     */
    fun octahedronDecode(uv: Vector2): Vector3 {
      val f = builtinFrame()
      f.put(1, uv)
      f.callStatic(Vector3Methods.octahedronDecode, 1)
      return f.retVector3()
    }

    // ===== END GENERATED BUILTIN STATICS: Vector3 =====

    /** Godot `UNIT_EPSILON` (`core/math/math_defs.h`), the tolerance of `is_normalized`. */
    private const val UNIT_EPSILON = 0.00001

    // Godot's Vector3::Axis values, the return of `max_axis_index`. Kept private: neither
    // platform exposed them before, and the shared body's public surface is exactly the
    // union of the two it replaces.

    /** A vector from components already at the storage width (marshalling; no conversion). */
    internal fun raw(x: GodotRealStorage, y: GodotRealStorage, z: GodotRealStorage): Vector3 =
      Vector3(x, y, z, RawStorage)

    /**
     * Zero vector, a vector with all components set to `0`.
     *
     * Generated from Godot docs: Vector3.ZERO
     */
    val ZERO = Vector3(0.0, 0.0, 0.0)
    /**
     * One vector, a vector with all components set to `1`.
     *
     * Generated from Godot docs: Vector3.ONE
     */
    val ONE = Vector3(1.0, 1.0, 1.0)
    /**
     * Up unit vector.
     *
     * Generated from Godot docs: Vector3.UP
     */
    val UP = Vector3(0.0, 1.0, 0.0)
    /**
     * Down unit vector.
     *
     * Generated from Godot docs: Vector3.DOWN
     */
    val DOWN = Vector3(0.0, -1.0, 0.0)
    /**
     * Forward unit vector. Represents the local direction of forward, and the global direction of
     * north. Keep in mind that the forward direction for lights, cameras, etc is different from 3D
     * assets like characters, which face towards the camera by convention. Use
     * `Vector3.MODEL_FRONT` and similar constants when working in 3D asset space.
     *
     * Generated from Godot docs: Vector3.FORWARD
     */
    val FORWARD = Vector3(0.0, 0.0, -1.0)
    /**
     * Back unit vector. Represents the local direction of back, and the global direction of south.
     *
     * Generated from Godot docs: Vector3.BACK
     */
    val BACK = Vector3(0.0, 0.0, 1.0)
    /**
     * Right unit vector. Represents the local direction of right, and the global direction of east.
     *
     * Generated from Godot docs: Vector3.RIGHT
     */
    val RIGHT = Vector3(1.0, 0.0, 0.0)
    /**
     * Left unit vector. Represents the local direction of left, and the global direction of west.
     *
     * Generated from Godot docs: Vector3.LEFT
     */
    val LEFT = Vector3(-1.0, 0.0, 0.0)
  }
}
