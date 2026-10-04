package net.multigesture.kanama.types

import kotlin.jvm.JvmInline
import kotlin.math.abs
import kotlin.math.sqrt
import net.multigesture.kanama.binding.runtime.BArg
import net.multigesture.kanama.binding.runtime.BuiltinCalls
import net.multigesture.kanama.binding.runtime.PT_VECTOR3
import net.multigesture.kanama.binding.runtime.VT_VECTOR3

private const val LERP_HASH = 1682608829L
private const val LIMIT_LENGTH_HASH = 514930144L
private const val BOUNCE_HASH = 2923479887L
private const val ROTATED_HASH = 1682608829L
private const val MOVE_TOWARD_HASH = 1682608829L
private const val SIGNED_ANGLE_TO_HASH = 2781412522L

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
   * Returns the axis of the vector's highest value. See `AXIS_*` constants. If all components are
   * equal, this method returns `Axis.X`.
   *
   * Generated from Godot docs: Vector3.max_axis_index
   */
  fun maxAxisIndex(): Int =
    // Godot `Vector3::max_axis_index`, ties going to the earlier axis.
    if (x < y) (if (y < z) AXIS_Z else AXIS_Y) else (if (x < z) AXIS_Z else AXIS_X)

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

  /**
   * Returns the result of the linear interpolation between this vector and `to` by amount `weight`.
   * `weight` is on the range of `0.0` to `1.0`, representing the amount of interpolation.
   *
   * Generated from Godot docs: Vector3.lerp
   */
  fun lerp(to: Vector3, weight: Double): Vector3 = callVector3RealRetVector3(lerpBind, to, weight)

  /**
   * Returns the vector with a maximum length by limiting its length to `length`. If the vector is
   * non-finite, the result is undefined.
   *
   * Generated from Godot docs: Vector3.limit_length
   */
  fun limitLength(maxLength: Double): Vector3 =
    fromGodotRealArray(
      BuiltinCalls.call(limitLengthBind, toGodotRealArray(), 3, listOf(BArg.Real(maxLength)))
    )

  /**
   * Returns the vector "bounced off" from a plane defined by the given normal `n`. Note: `bounce`
   * performs the operation that most engines and frameworks call `reflect()`.
   *
   * Generated from Godot docs: Vector3.bounce
   */
  fun bounce(normal: Vector3): Vector3 =
    fromGodotRealArray(
      BuiltinCalls.call(
        bounceBind,
        toGodotRealArray(),
        3,
        listOf(BArg.Floats(PT_VECTOR3, normal.toGodotRealArray())),
      )
    )

  fun withX(value: Double): Vector3 = raw(narrowReal(value), rawY, rawZ)

  fun withY(value: Double): Vector3 = raw(rawX, narrowReal(value), rawZ)

  fun withZ(value: Double): Vector3 = raw(rawX, rawY, narrowReal(value))

  fun withX(value: Int): Vector3 = withX(value.toDouble())

  fun withY(value: Int): Vector3 = withY(value.toDouble())

  fun withZ(value: Int): Vector3 = withZ(value.toDouble())

  /**
   * Returns the result of rotating this vector around a given axis by `angle` (in radians). The
   * axis must be a normalized vector. See also `@GlobalScope.deg_to_rad`.
   *
   * Generated from Godot docs: Vector3.rotated
   */
  fun rotated(axis: Vector3, angle: Double): Vector3 =
    callVector3RealRetVector3(rotatedBind, axis, angle)

  /**
   * Returns a new vector moved toward `to` by the fixed `delta` amount. Will not go past the final
   * value.
   *
   * Generated from Godot docs: Vector3.move_toward
   */
  fun moveToward(to: Vector3, delta: Double): Vector3 =
    callVector3RealRetVector3(moveTowardBind, to, delta)

  /**
   * Returns the signed angle to the given vector, in radians. The sign of the angle is positive in
   * a counter-clockwise direction and negative in a clockwise direction when viewed from the side
   * specified by the `axis`.
   *
   * Generated from Godot docs: Vector3.signed_angle_to
   */
  fun signedAngleTo(to: Vector3, axis: Vector3): Double =
    BuiltinCalls.callScalar(
      signedAngleToBind,
      toGodotRealArray(),
      listOf(
        BArg.Floats(PT_VECTOR3, to.toGodotRealArray()),
        BArg.Floats(PT_VECTOR3, axis.toGodotRealArray()),
      ),
    )

  // The (Vector3, float) -> Vector3 shape that lerp / rotated / move_toward share.
  private fun callVector3RealRetVector3(methodPtr: Long, vector: Vector3, value: Double): Vector3 =
    fromGodotRealArray(
      BuiltinCalls.call(
        methodPtr,
        toGodotRealArray(),
        3,
        listOf(BArg.Floats(PT_VECTOR3, vector.toGodotRealArray()), BArg.Real(value)),
      )
    )

  private fun toGodotRealArray(): GodotRealArray =
    GodotRealArray(3).also {
      it[0] = rawX
      it[1] = rawY
      it[2] = rawZ
    }

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
   * Returns the unsigned minimum angle to the given vector, in radians.
   *
   * Generated from Godot docs: Vector3.angle_to
   */
  fun angleTo(to: Vector3): Double =
    builtinDouble(Vector3Methods.angleTo, builtinArg(), listOf(to.builtinArg()))

  /**
   * Returns the normalized vector pointing from this vector to `to`. This is equivalent to using
   * `(b - a).normalized()`.
   *
   * Generated from Godot docs: Vector3.direction_to
   */
  fun directionTo(to: Vector3): Vector3 =
    raw(to.rawX - rawX, to.rawY - rawY, to.rawZ - rawZ).normalized()

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
  fun snapped(step: Vector3): Vector3 =
    builtinVector3(builtinReals(Vector3Methods.snapped, builtinArg(), 3, listOf(step.builtinArg())))

  /**
   * Returns a new vector with each component snapped to the nearest multiple of `step`. This can
   * also be used to round the components to an arbitrary number of decimals.
   *
   * Generated from Godot docs: Vector3.snappedf
   */
  fun snappedf(step: Double): Vector3 =
    builtinVector3(builtinReals(Vector3Methods.snappedf, builtinArg(), 3, listOf(argReal(step))))

  /**
   * Returns the result of spherical linear interpolation between this vector and `to`, by amount
   * `weight`. `weight` is on the range of 0.0 to 1.0, representing the amount of interpolation.
   * This method also handles interpolating the lengths if the input vectors have different lengths.
   * For the special case of one or both input vectors having zero length, this method behaves like
   * `lerp`.
   *
   * Generated from Godot docs: Vector3.slerp
   */
  fun slerp(to: Vector3, weight: Double): Vector3 =
    builtinVector3(
      builtinReals(Vector3Methods.slerp, builtinArg(), 3, listOf(to.builtinArg(), argReal(weight)))
    )

  /**
   * Performs a cubic interpolation between this vector and `b` using `pre_a` and `post_b` as
   * handles, and returns the result at position `weight`. `weight` is on the range of 0.0 to 1.0,
   * representing the amount of interpolation.
   *
   * Generated from Godot docs: Vector3.cubic_interpolate
   */
  fun cubicInterpolate(b: Vector3, preA: Vector3, postB: Vector3, weight: Double): Vector3 =
    builtinVector3(
      builtinReals(
        Vector3Methods.cubicInterpolate,
        builtinArg(),
        3,
        listOf(b.builtinArg(), preA.builtinArg(), postB.builtinArg(), argReal(weight)),
      )
    )

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
  ): Vector3 =
    builtinVector3(
      builtinReals(
        Vector3Methods.cubicInterpolateInTime,
        builtinArg(),
        3,
        listOf(
          b.builtinArg(),
          preA.builtinArg(),
          postB.builtinArg(),
          argReal(weight),
          argReal(bT),
          argReal(preAT),
          argReal(postBT),
        ),
      )
    )

  /**
   * Returns the point at the given `t` on the Bézier curve
   * (https://en.wikipedia.org/wiki/B%C3%A9zier_curve) defined by this vector and the given
   * `control_1`, `control_2`, and `end` points.
   *
   * Generated from Godot docs: Vector3.bezier_interpolate
   */
  fun bezierInterpolate(control1: Vector3, control2: Vector3, end: Vector3, t: Double): Vector3 =
    builtinVector3(
      builtinReals(
        Vector3Methods.bezierInterpolate,
        builtinArg(),
        3,
        listOf(control1.builtinArg(), control2.builtinArg(), end.builtinArg(), argReal(t)),
      )
    )

  /**
   * Returns the derivative at the given `t` on the Bézier curve
   * (https://en.wikipedia.org/wiki/B%C3%A9zier_curve) defined by this vector and the given
   * `control_1`, `control_2`, and `end` points.
   *
   * Generated from Godot docs: Vector3.bezier_derivative
   */
  fun bezierDerivative(control1: Vector3, control2: Vector3, end: Vector3, t: Double): Vector3 =
    builtinVector3(
      builtinReals(
        Vector3Methods.bezierDerivative,
        builtinArg(),
        3,
        listOf(control1.builtinArg(), control2.builtinArg(), end.builtinArg(), argReal(t)),
      )
    )

  /**
   * Returns the outer product with `with`.
   *
   * Generated from Godot docs: Vector3.outer
   */
  fun outer(with: Vector3): Basis =
    builtinBasis(builtinReals(Vector3Methods.outer, builtinArg(), 9, listOf(with.builtinArg())))

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
  fun posmod(mod: Double): Vector3 =
    builtinVector3(builtinReals(Vector3Methods.posmod, builtinArg(), 3, listOf(argReal(mod))))

  /**
   * Returns a vector composed of the `@GlobalScope.fposmod` of this vector's components and
   * `modv`'s components.
   *
   * Generated from Godot docs: Vector3.posmodv
   */
  fun posmodv(modv: Vector3): Vector3 =
    builtinVector3(builtinReals(Vector3Methods.posmodv, builtinArg(), 3, listOf(modv.builtinArg())))

  /**
   * Returns a new vector resulting from projecting this vector onto the given vector `b`. The
   * resulting new vector is parallel to `b`. See also `slide`. Note: If the vector `b` is a zero
   * vector, the components of the resulting new vector will be `@GDScript.NAN`.
   *
   * Generated from Godot docs: Vector3.project
   */
  fun project(b: Vector3): Vector3 =
    builtinVector3(builtinReals(Vector3Methods.project, builtinArg(), 3, listOf(b.builtinArg())))

  /**
   * Returns a new vector resulting from sliding this vector along a plane with normal `n`. The
   * resulting new vector is perpendicular to `n`, and is equivalent to this vector minus its
   * projection on `n`. See also `project`. Note: The vector `n` must be normalized. See also
   * `normalized`.
   *
   * Generated from Godot docs: Vector3.slide
   */
  fun slide(n: Vector3): Vector3 =
    builtinVector3(builtinReals(Vector3Methods.slide, builtinArg(), 3, listOf(n.builtinArg())))

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
  fun reflect(n: Vector3): Vector3 =
    builtinVector3(builtinReals(Vector3Methods.reflect, builtinArg(), 3, listOf(n.builtinArg())))

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
  fun octahedronEncode(): Vector2 =
    builtinVector2(builtinReals(Vector3Methods.octahedronEncode, builtinArg(), 2, emptyList()))

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
     * Returns the `Vector3` from an octahedral-compressed form created using `octahedron_encode`
     * (stored as a `Vector2`).
     *
     * Generated from Godot docs: Vector3.octahedron_decode
     */
    fun octahedronDecode(uv: Vector2): Vector3 =
      builtinVector3(
        builtinReals(Vector3Methods.octahedronDecode, null, 3, listOf(uv.builtinArg()))
      )

    // ===== END GENERATED BUILTIN STATICS: Vector3 =====

    /** Godot `UNIT_EPSILON` (`core/math/math_defs.h`), the tolerance of `is_normalized`. */
    private const val UNIT_EPSILON = 0.00001

    // Godot's Vector3::Axis values, the return of `max_axis_index`. Kept private: neither
    // platform exposed them before, and the shared body's public surface is exactly the
    // union of the two it replaces.
    private const val AXIS_X = 0
    private const val AXIS_Y = 1
    private const val AXIS_Z = 2

    private val lerpBind by lazy { BuiltinCalls.getBuiltinMethod(VT_VECTOR3, "lerp", LERP_HASH) }
    private val limitLengthBind by lazy {
      BuiltinCalls.getBuiltinMethod(VT_VECTOR3, "limit_length", LIMIT_LENGTH_HASH)
    }
    private val bounceBind by lazy {
      BuiltinCalls.getBuiltinMethod(VT_VECTOR3, "bounce", BOUNCE_HASH)
    }
    private val rotatedBind by lazy {
      BuiltinCalls.getBuiltinMethod(VT_VECTOR3, "rotated", ROTATED_HASH)
    }
    private val moveTowardBind by lazy {
      BuiltinCalls.getBuiltinMethod(VT_VECTOR3, "move_toward", MOVE_TOWARD_HASH)
    }
    private val signedAngleToBind by lazy {
      BuiltinCalls.getBuiltinMethod(VT_VECTOR3, "signed_angle_to", SIGNED_ANGLE_TO_HASH)
    }

    private fun fromGodotRealArray(c: GodotRealArray): Vector3 = raw(c[0], c[1], c[2])

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
