package net.multigesture.kanama.types

import kotlin.math.sqrt

// One body for every backend (task 104 step 2): `inverse`, `slerp` and `from_euler` are computed by
// the engine through BuiltinCalls (their shortest-arc and non-unit handling is the engine's), the
// rest is exact arithmetic in Kotlin. At ptrcall a Quaternion is 4 `real_t` in x, y, z, w order.
/**
 * A unit quaternion used for representing 3D rotations. Kanama value types are immutable snapshots;
 * assign a new value back to the Godot property after changing components.
 *
 * Generated from Godot docs: Quaternion
 */
class Quaternion
private constructor(
  internal val rawX: GodotRealStorage,
  internal val rawY: GodotRealStorage,
  internal val rawZ: GodotRealStorage,
  internal val rawW: GodotRealStorage,
  @Suppress("UNUSED_PARAMETER") raw: RawStorage,
) {
  /** A quaternion stored at Godot's `real_t` width: each component is rounded to it. */
  constructor(
    x: Double,
    y: Double,
    z: Double,
    w: Double,
  ) : this(narrowReal(x), narrowReal(y), narrowReal(z), narrowReal(w), RawStorage)

  /** GDScript's `Quaternion(0, 0, 0, 1)`: integer components. */
  constructor(
    x: Int,
    y: Int,
    z: Int,
    w: Int,
  ) : this(x.toDouble(), y.toDouble(), z.toDouble(), w.toDouble())

  /**
   * X component of the quaternion. This is the value along the "imaginary" `i` axis. Note:
   * Quaternion components should usually not be manipulated directly.
   *
   * Generated from Godot docs: Quaternion.x
   */
  val x: Double
    get() = widenReal(rawX)

  /**
   * Y component of the quaternion. This is the value along the "imaginary" `j` axis. Note:
   * Quaternion components should usually not be manipulated directly.
   *
   * Generated from Godot docs: Quaternion.y
   */
  val y: Double
    get() = widenReal(rawY)

  /**
   * Z component of the quaternion. This is the value along the "imaginary" `k` axis. Note:
   * Quaternion components should usually not be manipulated directly.
   *
   * Generated from Godot docs: Quaternion.z
   */
  val z: Double
    get() = widenReal(rawZ)

  /**
   * W component of the quaternion. This is the "real" part. Note: Quaternion components should
   * usually not be manipulated directly.
   *
   * Generated from Godot docs: Quaternion.w
   */
  val w: Double
    get() = widenReal(rawW)

  operator fun component1(): Double = x

  operator fun component2(): Double = y

  operator fun component3(): Double = z

  operator fun component4(): Double = w

  /** This quaternion with some components replaced. */
  fun copy(
    x: Double = this.x,
    y: Double = this.y,
    z: Double = this.z,
    w: Double = this.w,
  ): Quaternion = Quaternion(x, y, z, w)

  // Godot's `==` on the stored components (signed zero equal, -0.0 == 0.0); NaN equals NaN to keep
  // the JVM equals contract reflexive. hashCode canonicalizes signed zero so equal values hash
  // equal.
  override fun equals(other: Any?): Boolean =
    this === other ||
      (other is Quaternion &&
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

  /** Godot's `str(q)`: `(0, 0, 0, 1)` (no `.0` on whole numbers, unlike the vectors). */
  override fun toString(): String =
    "(${godotRealString(x, false)}, ${godotRealString(y, false)}, " +
      "${godotRealString(z, false)}, ${godotRealString(w, false)})"

  /** Godot `Quaternion.is_equal_approx`: per-component fuzzy compare (CMP_EPSILON tolerance). */
  /**
   * Returns `true` if this quaternion and `to` are approximately equal, by calling
   * `@GlobalScope.is_equal_approx` on each component.
   *
   * Generated from Godot docs: Quaternion.is_equal_approx
   */
  fun isEqualApprox(other: Quaternion): Boolean =
    isEqualApprox(x, other.x) &&
      isEqualApprox(y, other.y) &&
      isEqualApprox(z, other.z) &&
      isEqualApprox(w, other.w)

  /**
   * Returns this quaternion's length, squared. Note: This method is faster than `length`, so prefer
   * it if you only need to compare quaternion lengths.
   *
   * Generated from Godot docs: Quaternion.length_squared
   */
  fun lengthSquared(): Double = widenReal(rawLengthSquared())

  private fun rawLengthSquared(): GodotRealStorage =
    realDot(rawX, rawY, rawZ, rawW, rawX, rawY, rawZ, rawW)

  /**
   * Returns this quaternion's length, also called magnitude.
   *
   * Generated from Godot docs: Quaternion.length
   */
  fun length(): Double = widenReal(sqrt(rawLengthSquared()))

  /**
   * Returns a copy of this quaternion, normalized so that its length is `1.0`. See also
   * `is_normalized`.
   *
   * Generated from Godot docs: Quaternion.normalized
   */
  fun normalized(): Quaternion {
    // Godot: `*this / length()`, which multiplies by `1 / length` in `real_t` -- a zero
    // quaternion gives NaN components, as in Godot (the edge parity row checks it).
    val len = sqrt(rawLengthSquared())
    val inverseLength = narrowReal(1.0) / len
    return raw(
      rawX * inverseLength,
      rawY * inverseLength,
      rawZ * inverseLength,
      rawW * inverseLength,
    )
  }

  // Godot's `Quaternion::operator*=`, term for term and in its order (float addition is not
  // associative, so the order is part of the bit-exact result).
  operator fun times(other: Quaternion): Quaternion =
    realQuaternionProduct(rawX, rawY, rawZ, rawW, other.rawX, other.rawY, other.rawZ, other.rawW) {
      x,
      y,
      z,
      w ->
      raw(x, y, z, w)
    }

  operator fun unaryMinus(): Quaternion = raw(-rawX, -rawY, -rawZ, -rawW)

  /**
   * Returns the dot product between this quaternion and `with`. This is equivalent to `(quat.x *
   * with.x) + (quat.y * with.y) + (quat.z * with.z) + (quat.w * with.w)`.
   *
   * Generated from Godot docs: Quaternion.dot
   */
  fun dot(other: Quaternion): Double =
    widenReal(realDot(rawX, rawY, rawZ, rawW, other.rawX, other.rawY, other.rawZ, other.rawW))

  // ===== BEGIN GENERATED BUILTIN MEMBERS: Quaternion (generate_builtin_ops.py) =====
  operator fun unaryPlus(): Quaternion = this

  operator fun times(scalar: Int): Quaternion = times(scalar.toDouble())

  operator fun times(scalar: Long): Quaternion = times(scalar.toDouble())

  operator fun div(scalar: Int): Quaternion = div(scalar.toDouble())

  operator fun div(scalar: Long): Quaternion = div(scalar.toDouble())

  operator fun times(scalar: Double): Quaternion {
    val s = narrowReal(scalar)
    return raw(rawX * s, rawY * s, rawZ * s, rawW * s)
  }

  operator fun div(scalar: Double): Quaternion {
    val s = narrowReal(1.0) / narrowReal(scalar)
    return raw(rawX * s, rawY * s, rawZ * s, rawW * s)
  }

  operator fun times(other: Vector3): Vector3 = quaternionXform(this, other)

  operator fun plus(other: Quaternion): Quaternion =
    raw(rawX + other.rawX, rawY + other.rawY, rawZ + other.rawZ, rawW + other.rawW)

  operator fun minus(other: Quaternion): Quaternion =
    raw(rawX - other.rawX, rawY - other.rawY, rawZ - other.rawZ, rawW - other.rawW)

  /**
   * Returns `true` if this quaternion is normalized. See also `normalized`.
   *
   * Generated from Godot docs: Quaternion.is_normalized
   */
  fun isNormalized(): Boolean {
    val f = builtinFrame()
    f.put(0, this)
    f.call(QuaternionMethods.isNormalized, 0)
    return f.retBool()
  }

  /**
   * Returns `true` if this quaternion is finite, by calling `@GlobalScope.is_finite` on each
   * component.
   *
   * Generated from Godot docs: Quaternion.is_finite
   */
  fun isFinite(): Boolean = rawX.isFinite() && rawY.isFinite() && rawZ.isFinite() && rawW.isFinite()

  /**
   * Returns the inverse version of this quaternion, inverting the sign of every component except
   * `w`.
   *
   * Generated from Godot docs: Quaternion.inverse
   */
  fun inverse(): Quaternion {
    val f = builtinFrame()
    f.put(0, this)
    f.call(QuaternionMethods.inverse, 0)
    return f.retQuaternion()
  }

  /**
   * Returns the logarithm of this quaternion. Multiplies this quaternion's rotation axis by its
   * rotation angle, and stores the result in the returned quaternion's vector part (`x`, `y`, and
   * `z`). The returned quaternion's real part (`w`) is always `0.0`.
   *
   * Generated from Godot docs: Quaternion.log
   */
  fun log(): Quaternion {
    val f = builtinFrame()
    f.put(0, this)
    f.call(QuaternionMethods.log, 0)
    return f.retQuaternion()
  }

  /**
   * Returns the exponential of this quaternion. The rotation axis of the result is the normalized
   * rotation axis of this quaternion, the angle of the result is the length of the vector part of
   * this quaternion.
   *
   * Generated from Godot docs: Quaternion.exp
   */
  fun exp(): Quaternion {
    val f = builtinFrame()
    f.put(0, this)
    f.call(QuaternionMethods.exp, 0)
    return f.retQuaternion()
  }

  /**
   * Returns the angle between this quaternion and `to`. This is the magnitude of the angle you
   * would need to rotate by to get from one to the other. Note: The magnitude of the floating-point
   * error for this method is abnormally high, so methods such as `is_zero_approx` will not work
   * reliably.
   *
   * Generated from Godot docs: Quaternion.angle_to
   */
  fun angleTo(to: Quaternion): Double {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, to)
    f.call(QuaternionMethods.angleTo, 1)
    return f.retDouble()
  }

  /**
   * Performs a spherical-linear interpolation with the `to` quaternion, given a `weight` and
   * returns the result. Both this quaternion and `to` must be normalized.
   *
   * Generated from Godot docs: Quaternion.slerp
   */
  fun slerp(to: Quaternion, weight: Double): Quaternion {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, to)
    f.putDouble(2, weight)
    f.call(QuaternionMethods.slerp, 2)
    return f.retQuaternion()
  }

  /**
   * Performs a spherical-linear interpolation with the `to` quaternion, given a `weight` and
   * returns the result. Unlike `slerp`, this method does not check if the rotation path is smaller
   * than 90 degrees. Both this quaternion and `to` must be normalized.
   *
   * Generated from Godot docs: Quaternion.slerpni
   */
  fun slerpni(to: Quaternion, weight: Double): Quaternion {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, to)
    f.putDouble(2, weight)
    f.call(QuaternionMethods.slerpni, 2)
    return f.retQuaternion()
  }

  /**
   * Performs a spherical cubic interpolation between quaternions `pre_a`, this vector, `b`, and
   * `post_b`, by the given amount `weight`.
   *
   * Generated from Godot docs: Quaternion.spherical_cubic_interpolate
   */
  fun sphericalCubicInterpolate(
    b: Quaternion,
    preA: Quaternion,
    postB: Quaternion,
    weight: Double,
  ): Quaternion {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, b)
    f.put(2, preA)
    f.put(3, postB)
    f.putDouble(4, weight)
    f.call(QuaternionMethods.sphericalCubicInterpolate, 4)
    return f.retQuaternion()
  }

  /**
   * Performs a spherical cubic interpolation between quaternions `pre_a`, this vector, `b`, and
   * `post_b`, by the given amount `weight`. It can perform smoother interpolation than
   * `spherical_cubic_interpolate` by the time values.
   *
   * Generated from Godot docs: Quaternion.spherical_cubic_interpolate_in_time
   */
  fun sphericalCubicInterpolateInTime(
    b: Quaternion,
    preA: Quaternion,
    postB: Quaternion,
    weight: Double,
    bT: Double,
    preAT: Double,
    postBT: Double,
  ): Quaternion {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, b)
    f.put(2, preA)
    f.put(3, postB)
    f.putDouble(4, weight)
    f.putDouble(5, bT)
    f.putDouble(6, preAT)
    f.putDouble(7, postBT)
    f.call(QuaternionMethods.sphericalCubicInterpolateInTime, 7)
    return f.retQuaternion()
  }

  /**
   * Returns this quaternion's rotation as a `Vector3` of Euler angles
   * (https://en.wikipedia.org/wiki/Euler_angles), in radians. The order of each consecutive
   * rotation can be changed with `order` (see `EulerOrder` constants). In Godot, Euler angles
   * always use intrinsic order. By default, the intrinsic YXZ convention is used
   * (`EulerOrder.YXZ`): since we are decomposing, local Z (roll) is calculated first, then local X
   * (pitch), and lastly local Y (yaw). When using the opposite method `from_euler` to compose a
   * rotation, this order is reversed.
   *
   * Generated from Godot docs: Quaternion.get_euler
   */
  fun getEuler(order: Long = 2L): Vector3 {
    val f = builtinFrame()
    f.put(0, this)
    f.putLong(1, order)
    f.call(QuaternionMethods.getEuler, 1)
    return f.retVector3()
  }

  /**
   * Returns the rotation axis of the rotation represented by this quaternion.
   *
   * Generated from Godot docs: Quaternion.get_axis
   */
  fun getAxis(): Vector3 {
    val f = builtinFrame()
    f.put(0, this)
    f.call(QuaternionMethods.getAxis, 0)
    return f.retVector3()
  }

  /**
   * Returns the angle of the rotation represented by this quaternion. Note: The quaternion must be
   * normalized.
   *
   * Generated from Godot docs: Quaternion.get_angle
   */
  fun getAngle(): Double {
    val f = builtinFrame()
    f.put(0, this)
    f.call(QuaternionMethods.getAngle, 0)
    return f.retDouble()
  }

  // ===== END GENERATED BUILTIN MEMBERS: Quaternion =====

  companion object {
    // ===== BEGIN GENERATED BUILTIN STATICS: Quaternion (generate_builtin_ops.py) =====
    /**
     * Constructs a new `Quaternion` from the given `Vector3` of Euler angles
     * (https://en.wikipedia.org/wiki/Euler_angles), in radians. In Godot, Euler angles always use
     * intrinsic order. This method always uses the intrinsic YXZ convention (`EulerOrder.YXZ`).
     *
     * Generated from Godot docs: Quaternion.from_euler
     */
    fun fromEuler(euler: Vector3): Quaternion {
      val f = builtinFrame()
      f.put(1, euler)
      f.callStatic(QuaternionMethods.fromEuler, 1)
      return f.retQuaternion()
    }

    // ===== END GENERATED BUILTIN STATICS: Quaternion =====

    /**
     * The identity quaternion, representing no rotation. This has the same rotation as
     * `Basis.IDENTITY`. If a `Vector3` is rotated (multiplied) by this quaternion, it does not
     * change. Note: In GDScript, this constant is equivalent to creating a [constructor Quaternion]
     * without any arguments. It can be used to make your code clearer, and for consistency with C#.
     *
     * Generated from Godot docs: Quaternion.IDENTITY
     */
    val IDENTITY = Quaternion(0.0, 0.0, 0.0, 1.0)

    /** A quaternion from components already at the storage width (marshalling; no conversion). */
    internal fun raw(
      x: GodotRealStorage,
      y: GodotRealStorage,
      z: GodotRealStorage,
      w: GodotRealStorage,
    ): Quaternion = Quaternion(x, y, z, w, RawStorage)
  }
}
