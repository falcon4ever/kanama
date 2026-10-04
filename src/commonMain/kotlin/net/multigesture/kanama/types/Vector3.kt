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
  ) : this(GodotReal.toC(x), GodotReal.toC(y), GodotReal.toC(z), RawStorage)

  /** GDScript's `Vector3(0, 1, 0)`: integer components. */
  constructor(x: Int, y: Int, z: Int) : this(x.toDouble(), y.toDouble(), z.toDouble())

  /**
   * The vector's X component. Also accessible by using the index position `[0]`.
   *
   * Generated from Godot docs: Vector3.x
   */
  val x: Double
    get() = GodotReal.fromC(rawX)

  /**
   * The vector's Y component. Also accessible by using the index position `[1]`.
   *
   * Generated from Godot docs: Vector3.y
   */
  val y: Double
    get() = GodotReal.fromC(rawY)

  /**
   * The vector's Z component. Also accessible by using the index position `[2]`.
   *
   * Generated from Godot docs: Vector3.z
   */
  val z: Double
    get() = GodotReal.fromC(rawZ)

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
  operator fun times(scale: Double): Vector3 = scaled(GodotReal.toC(scale))

  operator fun times(scale: Float): Vector3 = scaled(GodotReal.toC(scale.toDouble()))

  operator fun times(scale: Int): Vector3 = scaled(GodotReal.toC(scale.toDouble()))

  operator fun times(scale: Long): Vector3 = scaled(GodotReal.toC(scale.toDouble()))

  operator fun div(scale: Double): Vector3 = divided(GodotReal.toC(scale))

  operator fun div(scale: Float): Vector3 = divided(GodotReal.toC(scale.toDouble()))

  operator fun div(scale: Int): Vector3 = divided(GodotReal.toC(scale.toDouble()))

  operator fun div(scale: Long): Vector3 = divided(GodotReal.toC(scale.toDouble()))

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
  fun lengthSquared(): Double = GodotReal.fromC(rawLengthSquared())

  private fun rawLengthSquared(): GodotRealStorage = rawX * rawX + rawY * rawY + rawZ * rawZ

  /**
   * Returns the length (magnitude) of this vector.
   *
   * Generated from Godot docs: Vector3.length
   */
  fun length(): Double = GodotReal.fromC(sqrt(rawLengthSquared()))

  /**
   * Returns the result of scaling the vector to unit length. Equivalent to `v / v.length()`.
   * Returns `(0, 0, 0)` if `v.length() == 0`. See also `is_normalized`. Note: This function may
   * return incorrect values if the input vector length is near zero.
   *
   * Generated from Godot docs: Vector3.normalized
   */
  fun normalized(): Vector3 {
    val lengthSquared = rawLengthSquared()
    if (lengthSquared == GodotReal.toC(0.0)) return ZERO
    val len = sqrt(lengthSquared)
    return raw(rawX / len, rawY / len, rawZ / len)
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
    GodotReal.fromC(rawX * other.rawX + rawY * other.rawY + rawZ * other.rawZ)

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
    raw(
      rawY * other.rawZ - rawZ * other.rawY,
      rawZ * other.rawX - rawX * other.rawZ,
      rawX * other.rawY - rawY * other.rawX,
    )

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

  fun withX(value: Double): Vector3 = raw(GodotReal.toC(value), rawY, rawZ)

  fun withY(value: Double): Vector3 = raw(rawX, GodotReal.toC(value), rawZ)

  fun withZ(value: Double): Vector3 = raw(rawX, rawY, GodotReal.toC(value))

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

  companion object {
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
