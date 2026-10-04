package net.multigesture.kanama.types

import kotlin.jvm.JvmInline
import kotlin.math.sqrt

/**
 * A 4D vector using floating-point coordinates. Kanama value types are immutable snapshots; assign
 * a new value back to the Godot property after changing components.
 *
 * Generated from Godot docs: Vector4
 */
class Vector4
private constructor(
  internal val rawX: GodotRealStorage,
  internal val rawY: GodotRealStorage,
  internal val rawZ: GodotRealStorage,
  internal val rawW: GodotRealStorage,
  @Suppress("UNUSED_PARAMETER") raw: RawStorage,
) {
  // ===== BEGIN GENERATED ENUMS: Vector4 (scripts/generate_api_wrapper.py — do not edit) =====
  /**
   * Godot's `Vector4.Axis` enum as a typed value: `.value` is the raw number Godot uses, and the
   * companion holds the named values (`Vector4.Axis.<NAME>`).
   *
   * Generated from Godot docs: Vector4.Axis
   */
  @JvmInline
  value class Axis(override val value: Long) : net.multigesture.kanama.api.GodotEnumValue {
    companion object {
      /**
       * Enumerated value for the X axis. Returned by `max_axis_index` and `min_axis_index`.
       *
       * Generated from Godot docs: Vector4.AXIS_X
       */
      val X: Axis
        get() = Axis(0L)

      /**
       * Enumerated value for the Y axis. Returned by `max_axis_index` and `min_axis_index`.
       *
       * Generated from Godot docs: Vector4.AXIS_Y
       */
      val Y: Axis
        get() = Axis(1L)

      /**
       * Enumerated value for the Z axis. Returned by `max_axis_index` and `min_axis_index`.
       *
       * Generated from Godot docs: Vector4.AXIS_Z
       */
      val Z: Axis
        get() = Axis(2L)

      /**
       * Enumerated value for the W axis. Returned by `max_axis_index` and `min_axis_index`.
       *
       * Generated from Godot docs: Vector4.AXIS_W
       */
      val W: Axis
        get() = Axis(3L)
    }
  }

  // ===== END GENERATED ENUMS: Vector4 =====

  /** A vector stored at Godot's `real_t` width: each component is rounded to it, as in Godot. */
  constructor(
    x: Double,
    y: Double,
    z: Double,
    w: Double,
  ) : this(narrowReal(x), narrowReal(y), narrowReal(z), narrowReal(w), RawStorage)

  /** GDScript's `Vector4(0, 0, 0, 1)`: integer components. */
  constructor(
    x: Int,
    y: Int,
    z: Int,
    w: Int,
  ) : this(x.toDouble(), y.toDouble(), z.toDouble(), w.toDouble())

  /**
   * The vector's X component. Also accessible by using the index position `[0]`.
   *
   * Generated from Godot docs: Vector4.x
   */
  val x: Double
    get() = widenReal(rawX)

  /**
   * The vector's Y component. Also accessible by using the index position `[1]`.
   *
   * Generated from Godot docs: Vector4.y
   */
  val y: Double
    get() = widenReal(rawY)

  /**
   * The vector's Z component. Also accessible by using the index position `[2]`.
   *
   * Generated from Godot docs: Vector4.z
   */
  val z: Double
    get() = widenReal(rawZ)

  /**
   * The vector's W component. Also accessible by using the index position `[3]`.
   *
   * Generated from Godot docs: Vector4.w
   */
  val w: Double
    get() = widenReal(rawW)

  operator fun component1(): Double = x

  operator fun component2(): Double = y

  operator fun component3(): Double = z

  operator fun component4(): Double = w

  /** This vector with some components replaced. */
  fun copy(
    x: Double = this.x,
    y: Double = this.y,
    z: Double = this.z,
    w: Double = this.w,
  ): Vector4 = Vector4(x, y, z, w)

  // Godot's `==` on the stored components (signed zero equal, -0.0 == 0.0); NaN equals NaN to keep
  // the JVM equals contract reflexive. hashCode canonicalizes signed zero so equal vectors hash
  // equal.
  override fun equals(other: Any?): Boolean =
    this === other ||
      (other is Vector4 &&
        storedEquals(rawX, other.rawX) &&
        storedEquals(rawY, other.rawY) &&
        storedEquals(rawZ, other.rawZ) &&
        storedEquals(rawW, other.rawW))

  override fun hashCode(): Int {
    var result = storedHash(rawX)
    result = 31 * result + storedHash(rawY)
    result = 31 * result + storedHash(rawZ)
    result = 31 * result + storedHash(rawW)
    return result
  }

  /** Godot's `str(v)`: `(0.1, 0.2, 0.3, 0.4)`. */
  override fun toString(): String =
    "(${godotRealString(x, true)}, ${godotRealString(y, true)}, " +
      "${godotRealString(z, true)}, ${godotRealString(w, true)})"

  /** Godot `Vector4.is_equal_approx`: per-component fuzzy compare (CMP_EPSILON tolerance). */
  /**
   * Returns `true` if this vector and `to` are approximately equal, by running
   * `@GlobalScope.is_equal_approx` on each component.
   *
   * Generated from Godot docs: Vector4.is_equal_approx
   */
  fun isEqualApprox(other: Vector4): Boolean =
    isEqualApprox(x, other.x) &&
      isEqualApprox(y, other.y) &&
      isEqualApprox(z, other.z) &&
      isEqualApprox(w, other.w)

  /** Godot `Vector4.is_zero_approx`: true if every component is approximately zero. */
  /**
   * Returns `true` if this vector's values are approximately zero, by running
   * `@GlobalScope.is_zero_approx` on each component. This method is faster than using
   * `is_equal_approx` with one value as a zero vector.
   *
   * Generated from Godot docs: Vector4.is_zero_approx
   */
  fun isZeroApprox(): Boolean =
    isZeroApprox(x) && isZeroApprox(y) && isZeroApprox(z) && isZeroApprox(w)

  operator fun plus(other: Vector4): Vector4 =
    raw(rawX + other.rawX, rawY + other.rawY, rawZ + other.rawZ, rawW + other.rawW)

  operator fun minus(other: Vector4): Vector4 =
    raw(rawX - other.rawX, rawY - other.rawY, rawZ - other.rawZ, rawW - other.rawW)

  // A scalar operand is a `real_t` in Godot (`Vector4 * float` narrows the float first).
  operator fun times(scale: Double): Vector4 = scaled(narrowReal(scale))

  operator fun times(scale: Float): Vector4 = scaled(narrowReal(scale.toDouble()))

  operator fun times(scale: Int): Vector4 = scaled(narrowReal(scale.toDouble()))

  operator fun times(scale: Long): Vector4 = scaled(narrowReal(scale.toDouble()))

  private fun scaled(s: GodotRealStorage): Vector4 = raw(rawX * s, rawY * s, rawZ * s, rawW * s)

  operator fun unaryMinus(): Vector4 = raw(-rawX, -rawY, -rawZ, -rawW)

  /**
   * Returns the squared length (squared magnitude) of this vector. This method runs faster than
   * `length`, so prefer it if you need to compare vectors or need the squared distance for some
   * formula.
   *
   * Generated from Godot docs: Vector4.length_squared
   */
  fun lengthSquared(): Double = widenReal(rawLengthSquared())

  private fun rawLengthSquared(): GodotRealStorage =
    realDot(rawX, rawY, rawZ, rawW, rawX, rawY, rawZ, rawW)

  /**
   * Returns the length (magnitude) of this vector.
   *
   * Generated from Godot docs: Vector4.length
   */
  fun length(): Double = widenReal(sqrt(rawLengthSquared()))

  /**
   * Returns the dot product of this vector and `with`.
   *
   * Generated from Godot docs: Vector4.dot
   */
  fun dot(other: Vector4): Double =
    widenReal(realDot(rawX, rawY, rawZ, rawW, other.rawX, other.rawY, other.rawZ, other.rawW))

  // ===== BEGIN GENERATED BUILTIN MEMBERS: Vector4 (generate_builtin_ops.py) =====
  operator fun unaryPlus(): Vector4 = this

  operator fun div(scalar: Int): Vector4 = div(scalar.toDouble())

  operator fun div(scalar: Long): Vector4 = div(scalar.toDouble())

  operator fun div(scalar: Double): Vector4 {
    val s = narrowReal(scalar)
    return raw(rawX / s, rawY / s, rawZ / s, rawW / s)
  }

  operator fun compareTo(other: Vector4): Int {
    val c0 = godotCompareStep(rawX, other.rawX)
    if (c0 != 0) return c0
    val c1 = godotCompareStep(rawY, other.rawY)
    if (c1 != 0) return c1
    val c2 = godotCompareStep(rawZ, other.rawZ)
    if (c2 != 0) return c2
    return godotCompareStep(rawW, other.rawW)
  }

  operator fun times(other: Vector4): Vector4 =
    raw(rawX * other.rawX, rawY * other.rawY, rawZ * other.rawZ, rawW * other.rawW)

  operator fun div(other: Vector4): Vector4 =
    raw(rawX / other.rawX, rawY / other.rawY, rawZ / other.rawZ, rawW / other.rawW)

  operator fun times(other: Projection): Vector4 = projectionXformInv(other, this)

  /**
   * Returns the axis of the vector's lowest value. See `AXIS_*` constants. If all components are
   * equal, this method returns `Axis.W`.
   *
   * Generated from Godot docs: Vector4.min_axis_index
   */
  fun minAxisIndex(): Long {
    var index = 0L
    var value = rawX
    if (rawY <= value) {
      index = 1L
      value = rawY
    }
    if (rawZ <= value) {
      index = 2L
      value = rawZ
    }
    if (rawW <= value) {
      index = 3L
      value = rawW
    }
    return index
  }

  /**
   * Returns the axis of the vector's highest value. See `AXIS_*` constants. If all components are
   * equal, this method returns `Axis.X`.
   *
   * Generated from Godot docs: Vector4.max_axis_index
   */
  fun maxAxisIndex(): Long {
    var index = 0L
    var value = rawX
    if (rawY > value) {
      index = 1L
      value = rawY
    }
    if (rawZ > value) {
      index = 2L
      value = rawZ
    }
    if (rawW > value) {
      index = 3L
      value = rawW
    }
    return index
  }

  /**
   * Returns a new vector with all components in absolute values (i.e. positive).
   *
   * Generated from Godot docs: Vector4.abs
   */
  fun abs(): Vector4 = raw(godotFabs(rawX), godotFabs(rawY), godotFabs(rawZ), godotFabs(rawW))

  /**
   * Returns a new vector with each component set to `1.0` if it's positive, `-1.0` if it's
   * negative, and `0.0` if it's zero. The result is identical to calling `@GlobalScope.sign` on
   * each component.
   *
   * Generated from Godot docs: Vector4.sign
   */
  fun sign(): Vector4 = raw(godotSign(rawX), godotSign(rawY), godotSign(rawZ), godotSign(rawW))

  /**
   * Returns a new vector with all components rounded down (towards negative infinity).
   *
   * Generated from Godot docs: Vector4.floor
   */
  fun floor(): Vector4 = raw(godotFloor(rawX), godotFloor(rawY), godotFloor(rawZ), godotFloor(rawW))

  /**
   * Returns a new vector with all components rounded up (towards positive infinity).
   *
   * Generated from Godot docs: Vector4.ceil
   */
  fun ceil(): Vector4 = raw(godotCeil(rawX), godotCeil(rawY), godotCeil(rawZ), godotCeil(rawW))

  /**
   * Returns a new vector with all components rounded to the nearest integer, with halfway cases
   * rounded away from zero.
   *
   * Generated from Godot docs: Vector4.round
   */
  fun round(): Vector4 = raw(godotRound(rawX), godotRound(rawY), godotRound(rawZ), godotRound(rawW))

  /**
   * Returns the result of the linear interpolation between this vector and `to` by amount `weight`.
   * `weight` is on the range of `0.0` to `1.0`, representing the amount of interpolation.
   *
   * Generated from Godot docs: Vector4.lerp
   */
  fun lerp(to: Vector4, weight: Double): Vector4 {
    /**
     * The vector's W component. Also accessible by using the index position `[3]`.
     *
     * Generated from Godot docs: Vector4.w
     */
    val w = narrowReal(weight)
    return raw(
      realLerp(rawX, to.rawX, w),
      realLerp(rawY, to.rawY, w),
      realLerp(rawZ, to.rawZ, w),
      realLerp(rawW, to.rawW, w),
    )
  }

  /**
   * Performs a cubic interpolation between this vector and `b` using `pre_a` and `post_b` as
   * handles, and returns the result at position `weight`. `weight` is on the range of 0.0 to 1.0,
   * representing the amount of interpolation.
   *
   * Generated from Godot docs: Vector4.cubic_interpolate
   */
  fun cubicInterpolate(b: Vector4, preA: Vector4, postB: Vector4, weight: Double): Vector4 {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, b)
    f.put(2, preA)
    f.put(3, postB)
    f.putDouble(4, weight)
    f.call(Vector4Methods.cubicInterpolate, 4)
    return f.retVector4()
  }

  /**
   * Performs a cubic interpolation between this vector and `b` using `pre_a` and `post_b` as
   * handles, and returns the result at position `weight`. `weight` is on the range of 0.0 to 1.0,
   * representing the amount of interpolation. It can perform smoother interpolation than
   * `cubic_interpolate` by the time values.
   *
   * Generated from Godot docs: Vector4.cubic_interpolate_in_time
   */
  fun cubicInterpolateInTime(
    b: Vector4,
    preA: Vector4,
    postB: Vector4,
    weight: Double,
    bT: Double,
    preAT: Double,
    postBT: Double,
  ): Vector4 {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, b)
    f.put(2, preA)
    f.put(3, postB)
    f.putDouble(4, weight)
    f.putDouble(5, bT)
    f.putDouble(6, preAT)
    f.putDouble(7, postBT)
    f.call(Vector4Methods.cubicInterpolateInTime, 7)
    return f.retVector4()
  }

  /**
   * Returns a vector composed of the `@GlobalScope.fposmod` of this vector's components and `mod`.
   *
   * Generated from Godot docs: Vector4.posmod
   */
  fun posmod(mod: Double): Vector4 {
    val m = narrowReal(mod)
    return raw(
      godotFposmod(rawX, m),
      godotFposmod(rawY, m),
      godotFposmod(rawZ, m),
      godotFposmod(rawW, m),
    )
  }

  /**
   * Returns a vector composed of the `@GlobalScope.fposmod` of this vector's components and
   * `modv`'s components.
   *
   * Generated from Godot docs: Vector4.posmodv
   */
  fun posmodv(modv: Vector4): Vector4 =
    raw(
      godotFposmod(rawX, modv.rawX),
      godotFposmod(rawY, modv.rawY),
      godotFposmod(rawZ, modv.rawZ),
      godotFposmod(rawW, modv.rawW),
    )

  /**
   * Returns a new vector with each component snapped to the nearest multiple of the corresponding
   * component in `step`. This can also be used to round the components to an arbitrary number of
   * decimals.
   *
   * Generated from Godot docs: Vector4.snapped
   */
  fun snapped(step: Vector4): Vector4 {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, step)
    f.call(Vector4Methods.snapped, 1)
    return f.retVector4()
  }

  /**
   * Returns a new vector with each component snapped to the nearest multiple of `step`. This can
   * also be used to round the components to an arbitrary number of decimals.
   *
   * Generated from Godot docs: Vector4.snappedf
   */
  fun snappedf(step: Double): Vector4 {
    val f = builtinFrame()
    f.put(0, this)
    f.putDouble(1, step)
    f.call(Vector4Methods.snappedf, 1)
    return f.retVector4()
  }

  /**
   * Returns a new vector with all components clamped between the components of `min` and `max`, by
   * running `@GlobalScope.clamp` on each component.
   *
   * Generated from Godot docs: Vector4.clamp
   */
  fun clamp(min: Vector4, max: Vector4): Vector4 =
    raw(
      godotClamp(rawX, min.rawX, max.rawX),
      godotClamp(rawY, min.rawY, max.rawY),
      godotClamp(rawZ, min.rawZ, max.rawZ),
      godotClamp(rawW, min.rawW, max.rawW),
    )

  /**
   * Returns a new vector with all components clamped between `min` and `max`, by running
   * `@GlobalScope.clamp` on each component.
   *
   * Generated from Godot docs: Vector4.clampf
   */
  fun clampf(min: Double, max: Double): Vector4 {
    val lo = narrowReal(min)
    val hi = narrowReal(max)
    return raw(
      godotClamp(rawX, lo, hi),
      godotClamp(rawY, lo, hi),
      godotClamp(rawZ, lo, hi),
      godotClamp(rawW, lo, hi),
    )
  }

  /**
   * Returns the result of scaling the vector to unit length. Equivalent to `v / v.length()`.
   * Returns `(0, 0, 0, 0)` if `v.length() == 0`. See also `is_normalized`. Note: This function may
   * return incorrect values if the input vector length is near zero.
   *
   * Generated from Godot docs: Vector4.normalized
   */
  fun normalized(): Vector4 =
    realNormalize(
      isFinite(),
      rawLengthSquared(),
      { ZERO },
      { len -> raw(rawX / len, rawY / len, rawZ / len, rawW / len) },
    )

  /**
   * Returns `true` if the vector is normalized, i.e. its length is approximately equal to 1.
   *
   * Generated from Godot docs: Vector4.is_normalized
   */
  fun isNormalized(): Boolean {
    val f = builtinFrame()
    f.put(0, this)
    f.call(Vector4Methods.isNormalized, 0)
    return f.retBool()
  }

  /**
   * Returns the normalized vector pointing from this vector to `to`. This is equivalent to using
   * `(b - a).normalized()`.
   *
   * Generated from Godot docs: Vector4.direction_to
   */
  fun directionTo(to: Vector4): Vector4 =
    raw(to.rawX - rawX, to.rawY - rawY, to.rawZ - rawZ, to.rawW - rawW).normalized()

  /**
   * Returns the Euclidean distance (https://en.wikipedia.org/wiki/Euclidean_distance) between this
   * vector and `to`.
   *
   * Generated from Godot docs: Vector4.distance_to
   */
  fun distanceTo(to: Vector4): Double = (to - this).length()

  /**
   * Returns the squared Euclidean distance (https://en.wikipedia.org/wiki/Euclidean_distance)
   * between this vector and `to`. This method runs faster than `distance_to`, so prefer it if you
   * need to compare vectors or need the squared distance for some formula.
   *
   * Generated from Godot docs: Vector4.distance_squared_to
   */
  fun distanceSquaredTo(to: Vector4): Double = (to - this).lengthSquared()

  /**
   * Returns the inverse of the vector. This is the same as `Vector4(1.0 / v.x, 1.0 / v.y, 1.0 /
   * v.z, 1.0 / v.w)`.
   *
   * Generated from Godot docs: Vector4.inverse
   */
  fun inverse(): Vector4 {
    val one = narrowReal(1.0)
    return raw(one / rawX, one / rawY, one / rawZ, one / rawW)
  }

  /**
   * Returns `true` if this vector is finite, by calling `@GlobalScope.is_finite` on each component.
   *
   * Generated from Godot docs: Vector4.is_finite
   */
  fun isFinite(): Boolean = rawX.isFinite() && rawY.isFinite() && rawZ.isFinite() && rawW.isFinite()

  /**
   * Returns the component-wise minimum of this and `with`, equivalent to `Vector4(minf(x, with.x),
   * minf(y, with.y), minf(z, with.z), minf(w, with.w))`.
   *
   * Generated from Godot docs: Vector4.min
   */
  fun min(with: Vector4): Vector4 =
    raw(
      godotMin(rawX, with.rawX),
      godotMin(rawY, with.rawY),
      godotMin(rawZ, with.rawZ),
      godotMin(rawW, with.rawW),
    )

  /**
   * Returns the component-wise minimum of this and `with`, equivalent to `Vector4(minf(x, with),
   * minf(y, with), minf(z, with), minf(w, with))`.
   *
   * Generated from Godot docs: Vector4.minf
   */
  fun minf(with: Double): Vector4 {
    val s = narrowReal(with)
    return raw(godotMin(rawX, s), godotMin(rawY, s), godotMin(rawZ, s), godotMin(rawW, s))
  }

  /**
   * Returns the component-wise maximum of this and `with`, equivalent to `Vector4(maxf(x, with.x),
   * maxf(y, with.y), maxf(z, with.z), maxf(w, with.w))`.
   *
   * Generated from Godot docs: Vector4.max
   */
  fun max(with: Vector4): Vector4 =
    raw(
      godotMax(rawX, with.rawX),
      godotMax(rawY, with.rawY),
      godotMax(rawZ, with.rawZ),
      godotMax(rawW, with.rawW),
    )

  /**
   * Returns the component-wise maximum of this and `with`, equivalent to `Vector4(maxf(x, with),
   * maxf(y, with), maxf(z, with), maxf(w, with))`.
   *
   * Generated from Godot docs: Vector4.maxf
   */
  fun maxf(with: Double): Vector4 {
    val s = narrowReal(with)
    return raw(godotMax(rawX, s), godotMax(rawY, s), godotMax(rawZ, s), godotMax(rawW, s))
  }

  // ===== END GENERATED BUILTIN MEMBERS: Vector4 =====

  companion object {
    // ===== BEGIN GENERATED BUILTIN STATICS: Vector4 (generate_builtin_ops.py) =====
    /**
     * Infinity vector, a vector with all components set to `@GDScript.INF`.
     *
     * Generated from Godot docs: Vector4.INF
     */
    val INF: Vector4 =
      Vector4(
        Double.POSITIVE_INFINITY,
        Double.POSITIVE_INFINITY,
        Double.POSITIVE_INFINITY,
        Double.POSITIVE_INFINITY,
      )

    // ===== END GENERATED BUILTIN STATICS: Vector4 =====

    /** A vector from components already at the storage width (marshalling; no conversion). */
    internal fun raw(
      x: GodotRealStorage,
      y: GodotRealStorage,
      z: GodotRealStorage,
      w: GodotRealStorage,
    ): Vector4 = Vector4(x, y, z, w, RawStorage)

    /**
     * Zero vector, a vector with all components set to `0`.
     *
     * Generated from Godot docs: Vector4.ZERO
     */
    val ZERO = Vector4(0.0, 0.0, 0.0, 0.0)
    /**
     * One vector, a vector with all components set to `1`.
     *
     * Generated from Godot docs: Vector4.ONE
     */
    val ONE = Vector4(1.0, 1.0, 1.0, 1.0)
  }
}
