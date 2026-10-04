package net.multigesture.kanama.types

import kotlin.jvm.JvmInline
import kotlin.math.sqrt

// One body for every backend (task 104 step 2): methods whose result depends on Godot's own
// edge-case handling are computed by the engine through BuiltinCalls; exact arithmetic is plain
// Kotlin. At ptrcall a Vector2 is 2 `real_t` in x, y order.
/**
 * A 2D vector using floating-point coordinates. Kanama value types are immutable snapshots; assign
 * a new value back to the Godot property after changing components.
 *
 * Generated from Godot docs: Vector2
 */
class Vector2
private constructor(
  internal val rawX: GodotRealStorage,
  internal val rawY: GodotRealStorage,
  @Suppress("UNUSED_PARAMETER") raw: RawStorage,
) {
  // ===== BEGIN GENERATED ENUMS: Vector2 (scripts/generate_api_wrapper.py — do not edit) =====
  /**
   * Godot's `Vector2.Axis` enum as a typed value: `.value` is the raw number Godot uses, and the
   * companion holds the named values (`Vector2.Axis.<NAME>`).
   *
   * Generated from Godot docs: Vector2.Axis
   */
  @JvmInline
  value class Axis(override val value: Long) : net.multigesture.kanama.api.GodotEnumValue {
    companion object {
      /**
       * Enumerated value for the X axis. Returned by `max_axis_index` and `min_axis_index`.
       *
       * Generated from Godot docs: Vector2.AXIS_X
       */
      val X: Axis
        get() = Axis(0L)

      /**
       * Enumerated value for the Y axis. Returned by `max_axis_index` and `min_axis_index`.
       *
       * Generated from Godot docs: Vector2.AXIS_Y
       */
      val Y: Axis
        get() = Axis(1L)
    }
  }

  // ===== END GENERATED ENUMS: Vector2 =====

  /** A vector stored at Godot's `real_t` width: each component is rounded to it, as in Godot. */
  constructor(x: Double, y: Double) : this(narrowReal(x), narrowReal(y), RawStorage)

  /** GDScript's `Vector2(1, 2)`: integer components. */
  constructor(x: Int, y: Int) : this(x.toDouble(), y.toDouble())

  // GDScript's mixed `Vector2(x, 0)`: every Int/Double mix, so exactly one overload matches a
  // call and none boxes (hand-written: the value types have no generator path for constructors).
  constructor(x: Int, y: Double) : this(x.toDouble(), y)

  constructor(x: Double, y: Int) : this(x, y.toDouble())

  /**
   * The vector's X component. Also accessible by using the index position `[0]`.
   *
   * Generated from Godot docs: Vector2.x
   */
  val x: Double
    get() = widenReal(rawX)

  /**
   * The vector's Y component. Also accessible by using the index position `[1]`.
   *
   * Generated from Godot docs: Vector2.y
   */
  val y: Double
    get() = widenReal(rawY)

  operator fun component1(): Double = x

  operator fun component2(): Double = y

  /** This vector with some components replaced. */
  fun copy(x: Double = this.x, y: Double = this.y): Vector2 = Vector2(x, y)

  // Godot's `==` on the stored components (signed zero equal, -0.0 == 0.0); NaN equals NaN to keep
  // the JVM equals contract reflexive. hashCode canonicalizes signed zero so equal vectors hash
  // equal.
  override fun equals(other: Any?): Boolean =
    this === other ||
      (other is Vector2 && storedEquals(rawX, other.rawX) && storedEquals(rawY, other.rawY))

  override fun hashCode(): Int = 31 * storedHash(rawX) + storedHash(rawY)

  /** Godot's `str(v)`: `(0.1, 0.2)`. */
  override fun toString(): String = "(${godotRealString(x, true)}, ${godotRealString(y, true)})"

  /** Godot `Vector2.is_equal_approx`: per-component fuzzy compare (CMP_EPSILON tolerance). */
  /**
   * Returns `true` if this vector and `to` are approximately equal, by running
   * `@GlobalScope.is_equal_approx` on each component.
   *
   * Generated from Godot docs: Vector2.is_equal_approx
   */
  fun isEqualApprox(other: Vector2): Boolean =
    isEqualApprox(x, other.x) && isEqualApprox(y, other.y)

  /** Godot `Vector2.is_zero_approx`: true if both components are approximately zero. */
  /**
   * Returns `true` if this vector's values are approximately zero, by running
   * `@GlobalScope.is_zero_approx` on each component. This method is faster than using
   * `is_equal_approx` with one value as a zero vector.
   *
   * Generated from Godot docs: Vector2.is_zero_approx
   */
  fun isZeroApprox(): Boolean = isZeroApprox(x) && isZeroApprox(y)

  operator fun plus(other: Vector2): Vector2 = raw(rawX + other.rawX, rawY + other.rawY)

  operator fun minus(other: Vector2): Vector2 = raw(rawX - other.rawX, rawY - other.rawY)

  // A scalar operand is a `real_t` in Godot (`Vector2 * float` narrows the float first).
  operator fun times(scale: Double): Vector2 = scaled(narrowReal(scale))

  operator fun times(scale: Float): Vector2 = scaled(narrowReal(scale.toDouble()))

  operator fun times(scale: Int): Vector2 = scaled(narrowReal(scale.toDouble()))

  operator fun times(scale: Long): Vector2 = scaled(narrowReal(scale.toDouble()))

  operator fun div(scale: Double): Vector2 = divided(narrowReal(scale))

  operator fun div(scale: Float): Vector2 = divided(narrowReal(scale.toDouble()))

  operator fun div(scale: Int): Vector2 = divided(narrowReal(scale.toDouble()))

  operator fun div(scale: Long): Vector2 = divided(narrowReal(scale.toDouble()))

  private fun scaled(s: GodotRealStorage): Vector2 = raw(rawX * s, rawY * s)

  private fun divided(s: GodotRealStorage): Vector2 = raw(rawX / s, rawY / s)

  operator fun unaryMinus(): Vector2 = raw(-rawX, -rawY)

  /**
   * Returns the squared length (squared magnitude) of this vector. This method runs faster than
   * `length`, so prefer it if you need to compare vectors or need the squared distance for some
   * formula.
   *
   * Generated from Godot docs: Vector2.length_squared
   */
  fun lengthSquared(): Double = widenReal(rawLengthSquared())

  private fun rawLengthSquared(): GodotRealStorage = realDot(rawX, rawY, rawX, rawY)

  /**
   * Returns the length (magnitude) of this vector.
   *
   * Generated from Godot docs: Vector2.length
   */
  fun length(): Double = widenReal(sqrt(rawLengthSquared()))

  /**
   * Returns the result of scaling the vector to unit length. Equivalent to `v / v.length()`.
   * Returns `(0, 0)` if `v.length() == 0`. See also `is_normalized`. Note: This function may return
   * incorrect values if the input vector length is near zero.
   *
   * Generated from Godot docs: Vector2.normalized
   */
  fun normalized(): Vector2 {
    return realNormalize(
      rawX.isFinite() && rawY.isFinite(),
      rawLengthSquared(),
      { ZERO },
      { len -> raw(rawX / len, rawY / len) },
    )
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
   * Generated from Godot docs: Vector2.dot
   */
  fun dot(other: Vector2): Double = widenReal(realDot(rawX, rawY, other.rawX, other.rawY))

  /**
   * Returns the Euclidean distance (https://en.wikipedia.org/wiki/Euclidean_distance) between this
   * vector and `to`.
   *
   * Generated from Godot docs: Vector2.distance_to
   */
  fun distanceTo(other: Vector2): Double = (this - other).length()

  /**
   * Returns the squared Euclidean distance (https://en.wikipedia.org/wiki/Euclidean_distance)
   * between this vector and `to`. This method runs faster than `distance_to`, so prefer it if you
   * need to compare vectors or need the squared distance for some formula.
   *
   * Generated from Godot docs: Vector2.distance_squared_to
   */
  fun distanceSquaredTo(other: Vector2): Double = (this - other).lengthSquared()

  fun withX(value: Double): Vector2 = raw(narrowReal(value), rawY)

  fun withY(value: Double): Vector2 = raw(rawX, narrowReal(value))

  fun withX(value: Int): Vector2 = withX(value.toDouble())

  fun withY(value: Int): Vector2 = withY(value.toDouble())

  // ===== BEGIN GENERATED BUILTIN MEMBERS: Vector2 (generate_builtin_ops.py) =====
  operator fun unaryPlus(): Vector2 = this

  operator fun compareTo(other: Vector2): Int {
    val c0 = godotCompareStep(rawX, other.rawX)
    if (c0 != 0) return c0
    return godotCompareStep(rawY, other.rawY)
  }

  operator fun times(other: Vector2): Vector2 = raw(rawX * other.rawX, rawY * other.rawY)

  operator fun div(other: Vector2): Vector2 = raw(rawX / other.rawX, rawY / other.rawY)

  operator fun times(other: Transform2D): Vector2 = transform2DXformInv(other, this)

  /**
   * Returns this vector's angle with respect to the positive X axis, or `(1, 0)` vector, in
   * radians. For example, `Vector2.RIGHT.angle()` will return zero, `Vector2.DOWN.angle()` will
   * return `PI / 2` (a quarter turn, or 90 degrees), and `Vector2(1, -1).angle()` will return `-PI
   * / 4` (a negative eighth turn, or -45 degrees). This is equivalent to calling
   * `@GlobalScope.atan2` with `y` and `x`. Illustration of the returned angle.
   * (https://raw.githubusercontent.com/godotengine/godot-docs/master/img/vector2_angle.png)
   *
   * Generated from Godot docs: Vector2.angle
   */
  fun angle(): Double {
    val f = builtinFrame()
    f.put(0, this)
    f.call(Vector2Methods.angle, 0)
    return f.retDouble()
  }

  /**
   * Returns the signed angle to the given vector, in radians. The result ranges from `-PI` to `PI`
   * (inclusive). Illustration of the returned angle.
   * (https://raw.githubusercontent.com/godotengine/godot-docs/master/img/vector2_angle_to.png)
   *
   * Generated from Godot docs: Vector2.angle_to
   */
  fun angleTo(to: Vector2): Double {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, to)
    f.call(Vector2Methods.angleTo, 1)
    return f.retDouble()
  }

  /**
   * Returns the signed angle between the X axis and the line from this vector to point `to`, in
   * radians. The result ranges from `-PI` to `PI` (inclusive). `a.angle_to_point(b)` is equivalent
   * to `(b - a).angle()`. See also `angle`. Illustration of the returned angle.
   * (https://raw.githubusercontent.com/godotengine/godot-docs/master/img/vector2_angle_to_point.png)
   *
   * Generated from Godot docs: Vector2.angle_to_point
   */
  fun angleToPoint(to: Vector2): Double {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, to)
    f.call(Vector2Methods.angleToPoint, 1)
    return f.retDouble()
  }

  /**
   * Returns the normalized vector pointing from this vector to `to`. `a.direction_to(b)` is
   * equivalent to `(b - a).normalized()`. See also `normalized`.
   *
   * Generated from Godot docs: Vector2.direction_to
   */
  fun directionTo(to: Vector2): Vector2 = raw(to.rawX - rawX, to.rawY - rawY).normalized()

  /**
   * Returns the vector with a maximum length by limiting its length to `length`. If the vector is
   * non-finite, the result is undefined.
   *
   * Generated from Godot docs: Vector2.limit_length
   */
  fun limitLength(length: Double = 1.0): Vector2 {
    val p = narrowReal(length)
    val l = godotSqrt(rawX * rawX + rawY * rawY)
    if (!(l > narrowReal(0.0) && p < l)) return this
    return raw(rawX / l * p, rawY / l * p)
  }

  /**
   * Returns `true` if the vector is normalized, i.e. its length is approximately equal to 1.
   *
   * Generated from Godot docs: Vector2.is_normalized
   */
  fun isNormalized(): Boolean {
    val f = builtinFrame()
    f.put(0, this)
    f.call(Vector2Methods.isNormalized, 0)
    return f.retBool()
  }

  /**
   * Returns `true` if this vector is finite, by calling `@GlobalScope.is_finite` on each component.
   *
   * Generated from Godot docs: Vector2.is_finite
   */
  fun isFinite(): Boolean = rawX.isFinite() && rawY.isFinite()

  /**
   * Returns a vector composed of the `@GlobalScope.fposmod` of this vector's components and `mod`.
   *
   * Generated from Godot docs: Vector2.posmod
   */
  fun posmod(mod: Double): Vector2 {
    val m = narrowReal(mod)
    return raw(godotFposmod(rawX, m), godotFposmod(rawY, m))
  }

  /**
   * Returns a vector composed of the `@GlobalScope.fposmod` of this vector's components and
   * `modv`'s components.
   *
   * Generated from Godot docs: Vector2.posmodv
   */
  fun posmodv(modv: Vector2): Vector2 =
    raw(godotFposmod(rawX, modv.rawX), godotFposmod(rawY, modv.rawY))

  /**
   * Returns a new vector resulting from projecting this vector onto the given vector `b`. The
   * resulting new vector is parallel to `b`. See also `slide`. Note: If the vector `b` is a zero
   * vector, the components of the resulting new vector will be `@GDScript.NAN`.
   *
   * Generated from Godot docs: Vector2.project
   */
  fun project(b: Vector2): Vector2 {
    val s = (rawX * b.rawX + rawY * b.rawY) / (b.rawX * b.rawX + b.rawY * b.rawY)
    return raw(b.rawX * s, b.rawY * s)
  }

  /**
   * Returns the result of the linear interpolation between this vector and `to` by amount `weight`.
   * `weight` is on the range of `0.0` to `1.0`, representing the amount of interpolation.
   *
   * Generated from Godot docs: Vector2.lerp
   */
  fun lerp(to: Vector2, weight: Double): Vector2 {
    val w = narrowReal(weight)
    return raw(realLerp(rawX, to.rawX, w), realLerp(rawY, to.rawY, w))
  }

  /**
   * Returns the result of spherical linear interpolation between this vector and `to`, by amount
   * `weight`. `weight` is on the range of 0.0 to 1.0, representing the amount of interpolation.
   * This method also handles interpolating the lengths if the input vectors have different lengths.
   * For the special case of one or both input vectors having zero length, this method behaves like
   * `lerp`.
   *
   * Generated from Godot docs: Vector2.slerp
   */
  fun slerp(to: Vector2, weight: Double): Vector2 {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, to)
    f.putDouble(2, weight)
    f.call(Vector2Methods.slerp, 2)
    return f.retVector2()
  }

  /**
   * Performs a cubic interpolation between this vector and `b` using `pre_a` and `post_b` as
   * handles, and returns the result at position `weight`. `weight` is on the range of 0.0 to 1.0,
   * representing the amount of interpolation.
   *
   * Generated from Godot docs: Vector2.cubic_interpolate
   */
  fun cubicInterpolate(b: Vector2, preA: Vector2, postB: Vector2, weight: Double): Vector2 {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, b)
    f.put(2, preA)
    f.put(3, postB)
    f.putDouble(4, weight)
    f.call(Vector2Methods.cubicInterpolate, 4)
    return f.retVector2()
  }

  /**
   * Performs a cubic interpolation between this vector and `b` using `pre_a` and `post_b` as
   * handles, and returns the result at position `weight`. `weight` is on the range of 0.0 to 1.0,
   * representing the amount of interpolation. It can perform smoother interpolation than
   * `cubic_interpolate` by the time values.
   *
   * Generated from Godot docs: Vector2.cubic_interpolate_in_time
   */
  fun cubicInterpolateInTime(
    b: Vector2,
    preA: Vector2,
    postB: Vector2,
    weight: Double,
    bT: Double,
    preAT: Double,
    postBT: Double,
  ): Vector2 {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, b)
    f.put(2, preA)
    f.put(3, postB)
    f.putDouble(4, weight)
    f.putDouble(5, bT)
    f.putDouble(6, preAT)
    f.putDouble(7, postBT)
    f.call(Vector2Methods.cubicInterpolateInTime, 7)
    return f.retVector2()
  }

  /**
   * Returns the point at the given `t` on the Bézier curve
   * (https://en.wikipedia.org/wiki/B%C3%A9zier_curve) defined by this vector and the given
   * `control_1`, `control_2`, and `end` points.
   *
   * Generated from Godot docs: Vector2.bezier_interpolate
   */
  fun bezierInterpolate(control1: Vector2, control2: Vector2, end: Vector2, t: Double): Vector2 {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, control1)
    f.put(2, control2)
    f.put(3, end)
    f.putDouble(4, t)
    f.call(Vector2Methods.bezierInterpolate, 4)
    return f.retVector2()
  }

  /**
   * Returns the derivative at the given `t` on the Bézier curve
   * (https://en.wikipedia.org/wiki/B%C3%A9zier_curve) defined by this vector and the given
   * `control_1`, `control_2`, and `end` points.
   *
   * Generated from Godot docs: Vector2.bezier_derivative
   */
  fun bezierDerivative(control1: Vector2, control2: Vector2, end: Vector2, t: Double): Vector2 {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, control1)
    f.put(2, control2)
    f.put(3, end)
    f.putDouble(4, t)
    f.call(Vector2Methods.bezierDerivative, 4)
    return f.retVector2()
  }

  /**
   * Returns the axis of the vector's highest value. See `AXIS_*` constants. If all components are
   * equal, this method returns `Axis.X`.
   *
   * Generated from Godot docs: Vector2.max_axis_index
   */
  fun maxAxisIndex(): Long = if (rawX < rawY) 1L else 0L

  /**
   * Returns the axis of the vector's lowest value. See `AXIS_*` constants. If all components are
   * equal, this method returns `Axis.Y`.
   *
   * Generated from Godot docs: Vector2.min_axis_index
   */
  fun minAxisIndex(): Long = if (rawX < rawY) 0L else 1L

  /**
   * Returns a new vector moved toward `to` by the fixed `delta` amount. Will not go past the final
   * value.
   *
   * Generated from Godot docs: Vector2.move_toward
   */
  fun moveToward(to: Vector2, delta: Double): Vector2 {
    val d = narrowReal(delta)
    val v0 = to.rawX - rawX
    val v1 = to.rawY - rawY
    val len = godotSqrt(v0 * v0 + v1 * v1)
    if (len <= d || len < narrowReal(0.00001)) return to
    return raw(rawX + v0 / len * d, rawY + v1 / len * d)
  }

  /**
   * Returns the result of rotating this vector by `angle` (in radians). See also
   * `@GlobalScope.deg_to_rad`.
   *
   * Generated from Godot docs: Vector2.rotated
   */
  fun rotated(angle: Double): Vector2 {
    val f = builtinFrame()
    f.put(0, this)
    f.putDouble(1, angle)
    f.call(Vector2Methods.rotated, 1)
    return f.retVector2()
  }

  /**
   * Returns a perpendicular vector rotated 90 degrees counter-clockwise compared to the original,
   * with the same length.
   *
   * Generated from Godot docs: Vector2.orthogonal
   */
  fun orthogonal(): Vector2 = raw(rawY, -rawX)

  /**
   * Returns a new vector with all components rounded down (towards negative infinity).
   *
   * Generated from Godot docs: Vector2.floor
   */
  fun floor(): Vector2 = raw(godotFloor(rawX), godotFloor(rawY))

  /**
   * Returns a new vector with all components rounded up (towards positive infinity).
   *
   * Generated from Godot docs: Vector2.ceil
   */
  fun ceil(): Vector2 = raw(godotCeil(rawX), godotCeil(rawY))

  /**
   * Returns a new vector with all components rounded to the nearest integer, with halfway cases
   * rounded away from zero.
   *
   * Generated from Godot docs: Vector2.round
   */
  fun round(): Vector2 = raw(godotRound(rawX), godotRound(rawY))

  /**
   * Returns this vector's aspect ratio, which is `x` divided by `y`.
   *
   * Generated from Godot docs: Vector2.aspect
   */
  fun aspect(): Double = widenReal(rawX / rawY)

  /**
   * Returns a new vector resulting from sliding this vector along a line with normal `n`. The
   * resulting new vector is perpendicular to `n`, and is equivalent to this vector minus its
   * projection on `n`. See also `project`. Note: The vector `n` must be normalized. See also
   * `normalized`.
   *
   * Generated from Godot docs: Vector2.slide
   */
  fun slide(n: Vector2): Vector2 {
    val d = rawX * n.rawX + rawY * n.rawY
    return raw(rawX - n.rawX * d, rawY - n.rawY * d)
  }

  /**
   * Returns the vector "bounced off" from a line defined by the given normal `n` perpendicular to
   * the line. Note: `bounce` performs the operation that most engines and frameworks call
   * `reflect()`.
   *
   * Generated from Godot docs: Vector2.bounce
   */
  fun bounce(n: Vector2): Vector2 {
    val d = rawX * n.rawX + rawY * n.rawY
    val two = narrowReal(2.0)
    return raw(-(n.rawX * two * d - rawX), -(n.rawY * two * d - rawY))
  }

  /**
   * Returns the result of reflecting the vector from a line defined by the given direction vector
   * `line`. Note: `reflect` differs from what other engines and frameworks call `reflect()`. In
   * other engines, `reflect()` takes a normal direction which is a direction perpendicular to the
   * line. In Godot, you specify the direction of the line directly. See also `bounce` which does
   * what most engines call `reflect()`.
   *
   * Generated from Godot docs: Vector2.reflect
   */
  fun reflect(line: Vector2): Vector2 {
    val d = rawX * line.rawX + rawY * line.rawY
    val two = narrowReal(2.0)
    return raw(line.rawX * two * d - rawX, line.rawY * two * d - rawY)
  }

  /**
   * Returns the 2D analog of the cross product for this vector and `with`. This is the signed area
   * of the parallelogram formed by the two vectors. If the second vector is clockwise from the
   * first vector, then the cross product is the positive area. If counter-clockwise, the cross
   * product is the negative area. If the two vectors are parallel this returns zero, making it
   * useful for testing if two vectors are parallel. Note: Cross product is not defined in 2D
   * mathematically. This method embeds the 2D vectors in the XY plane of 3D space and uses their
   * cross product's Z component as the analog.
   *
   * Generated from Godot docs: Vector2.cross
   */
  fun cross(with: Vector2): Double = widenReal(rawX * with.rawY - rawY * with.rawX)

  /**
   * Returns a new vector with all components in absolute values (i.e. positive).
   *
   * Generated from Godot docs: Vector2.abs
   */
  fun abs(): Vector2 = raw(godotFabs(rawX), godotFabs(rawY))

  /**
   * Returns a new vector with each component set to `1.0` if it's positive, `-1.0` if it's
   * negative, and `0.0` if it's zero. The result is identical to calling `@GlobalScope.sign` on
   * each component.
   *
   * Generated from Godot docs: Vector2.sign
   */
  fun sign(): Vector2 = raw(godotSign(rawX), godotSign(rawY))

  /**
   * Returns a new vector with all components clamped between the components of `min` and `max`, by
   * running `@GlobalScope.clamp` on each component.
   *
   * Generated from Godot docs: Vector2.clamp
   */
  fun clamp(min: Vector2, max: Vector2): Vector2 =
    raw(godotClamp(rawX, min.rawX, max.rawX), godotClamp(rawY, min.rawY, max.rawY))

  /**
   * Returns a new vector with all components clamped between `min` and `max`, by running
   * `@GlobalScope.clamp` on each component.
   *
   * Generated from Godot docs: Vector2.clampf
   */
  fun clampf(min: Double, max: Double): Vector2 {
    val lo = narrowReal(min)
    val hi = narrowReal(max)
    return raw(godotClamp(rawX, lo, hi), godotClamp(rawY, lo, hi))
  }

  /**
   * Returns a new vector with each component snapped to the nearest multiple of the corresponding
   * component in `step`. This can also be used to round the components to an arbitrary number of
   * decimals.
   *
   * Generated from Godot docs: Vector2.snapped
   */
  fun snapped(step: Vector2): Vector2 {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, step)
    f.call(Vector2Methods.snapped, 1)
    return f.retVector2()
  }

  /**
   * Returns a new vector with each component snapped to the nearest multiple of `step`. This can
   * also be used to round the components to an arbitrary number of decimals.
   *
   * Generated from Godot docs: Vector2.snappedf
   */
  fun snappedf(step: Double): Vector2 {
    val f = builtinFrame()
    f.put(0, this)
    f.putDouble(1, step)
    f.call(Vector2Methods.snappedf, 1)
    return f.retVector2()
  }

  /**
   * Returns the component-wise minimum of this and `with`, equivalent to `Vector2(minf(x, with.x),
   * minf(y, with.y))`.
   *
   * Generated from Godot docs: Vector2.min
   */
  fun min(with: Vector2): Vector2 = raw(godotMin(rawX, with.rawX), godotMin(rawY, with.rawY))

  /**
   * Returns the component-wise minimum of this and `with`, equivalent to `Vector2(minf(x, with),
   * minf(y, with))`.
   *
   * Generated from Godot docs: Vector2.minf
   */
  fun minf(with: Double): Vector2 {
    val s = narrowReal(with)
    return raw(godotMin(rawX, s), godotMin(rawY, s))
  }

  /**
   * Returns the component-wise maximum of this and `with`, equivalent to `Vector2(maxf(x, with.x),
   * maxf(y, with.y))`.
   *
   * Generated from Godot docs: Vector2.max
   */
  fun max(with: Vector2): Vector2 = raw(godotMax(rawX, with.rawX), godotMax(rawY, with.rawY))

  /**
   * Returns the component-wise maximum of this and `with`, equivalent to `Vector2(maxf(x, with),
   * maxf(y, with))`.
   *
   * Generated from Godot docs: Vector2.maxf
   */
  fun maxf(with: Double): Vector2 {
    val s = narrowReal(with)
    return raw(godotMax(rawX, s), godotMax(rawY, s))
  }

  // ===== END GENERATED BUILTIN MEMBERS: Vector2 =====

  companion object {
    // ===== BEGIN GENERATED BUILTIN STATICS: Vector2 (generate_builtin_ops.py) =====
    /**
     * Infinity vector, a vector with all components set to `@GDScript.INF`.
     *
     * Generated from Godot docs: Vector2.INF
     */
    val INF: Vector2 = Vector2(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY)

    /**
     * Creates a `Vector2` rotated to the given `angle` in radians. This is equivalent to doing
     * `Vector2(cos(angle), sin(angle))` or `Vector2.RIGHT.rotated(angle)`.
     *
     * Generated from Godot docs: Vector2.from_angle
     */
    fun fromAngle(angle: Double): Vector2 {
      val f = builtinFrame()
      f.putDouble(1, angle)
      f.callStatic(Vector2Methods.fromAngle, 1)
      return f.retVector2()
    }

    // ===== END GENERATED BUILTIN STATICS: Vector2 =====

    /** A vector from components already at the storage width (marshalling; no conversion). */
    internal fun raw(x: GodotRealStorage, y: GodotRealStorage): Vector2 = Vector2(x, y, RawStorage)

    /**
     * Zero vector, a vector with all components set to `0`.
     *
     * Generated from Godot docs: Vector2.ZERO
     */
    val ZERO = Vector2(0.0, 0.0)
    /**
     * One vector, a vector with all components set to `1`.
     *
     * Generated from Godot docs: Vector2.ONE
     */
    val ONE = Vector2(1.0, 1.0)
    /**
     * Up unit vector. Y is down in 2D, so this vector points -Y.
     *
     * Generated from Godot docs: Vector2.UP
     */
    val UP = Vector2(0.0, -1.0)
    /**
     * Down unit vector. Y is down in 2D, so this vector points +Y.
     *
     * Generated from Godot docs: Vector2.DOWN
     */
    val DOWN = Vector2(0.0, 1.0)
    /**
     * Left unit vector. Represents the direction of left.
     *
     * Generated from Godot docs: Vector2.LEFT
     */
    val LEFT = Vector2(-1.0, 0.0)
    /**
     * Right unit vector. Represents the direction of right.
     *
     * Generated from Godot docs: Vector2.RIGHT
     */
    val RIGHT = Vector2(1.0, 0.0)
  }
}
