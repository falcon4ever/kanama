@file:Suppress("REDUNDANT_CALL_OF_CONVERSION_METHOD")

package net.multigesture.kanama.types

import kotlin.math.abs
import kotlin.math.sqrt
import net.multigesture.kanama.binding.runtime.BArg
import net.multigesture.kanama.binding.runtime.BuiltinCalls

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
data class Vector3(
  /**
   * The vector's X component. Also accessible by using the index position `[0]`.
   *
   * Generated from Godot docs: Vector3.x
   */
  val x: real_t,
  /**
   * The vector's Y component. Also accessible by using the index position `[1]`.
   *
   * Generated from Godot docs: Vector3.y
   */
  val y: real_t,
  /**
   * The vector's Z component. Also accessible by using the index position `[2]`.
   *
   * Generated from Godot docs: Vector3.z
   */
  val z: real_t,
) {
  constructor(
    x: Number,
    y: Number,
    z: Number,
  ) : this(GodotReal.fromNumber(x), GodotReal.fromNumber(y), GodotReal.fromNumber(z))

  // Match GDScript/C# `==`: signed zero compares equal (-0.0 == 0.0), while NaN stays reflexive
  // (NaN == NaN) to satisfy the JVM equals contract. The default data-class `equals` uses
  // `real_t.equals`, which gives the opposite of both. See wrapper-coverage-roadmap.md.
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other !is Vector3) return false
    return (x == other.x || (x.isNaN() && other.x.isNaN())) &&
      (y == other.y || (y.isNaN() && other.y.isNaN())) &&
      (z == other.z || (z.isNaN() && other.z.isNaN()))
  }

  override fun hashCode(): Int {
    // Canonicalize signed zero (-0.0 -> +0.0 via `+ 0.0f`) so equal vectors hash equal.
    var result = (x + 0.0f).hashCode()
    result = 31 * result + (y + 0.0f).hashCode()
    result = 31 * result + (z + 0.0f).hashCode()
    return result
  }

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

  operator fun plus(other: Vector3): Vector3 = Vector3(x + other.x, y + other.y, z + other.z)

  operator fun minus(other: Vector3): Vector3 = Vector3(x - other.x, y - other.y, z - other.z)

  operator fun times(scale: Number): Vector3 =
    Vector3(
      x.toDouble() * scale.toDouble(),
      y.toDouble() * scale.toDouble(),
      z.toDouble() * scale.toDouble(),
    )

  operator fun times(scale: Double): Vector3 =
    Vector3(
      GodotReal.fromDouble(x.toDouble() * scale),
      GodotReal.fromDouble(y.toDouble() * scale),
      GodotReal.fromDouble(z.toDouble() * scale),
    )

  operator fun times(scale: Float): Vector3 =
    Vector3(
      GodotReal.fromDouble(x.toDouble() * scale.toDouble()),
      GodotReal.fromDouble(y.toDouble() * scale.toDouble()),
      GodotReal.fromDouble(z.toDouble() * scale.toDouble()),
    )

  operator fun div(scale: Number): Vector3 =
    Vector3(
      x.toDouble() / scale.toDouble(),
      y.toDouble() / scale.toDouble(),
      z.toDouble() / scale.toDouble(),
    )

  operator fun div(scale: Double): Vector3 =
    Vector3(
      GodotReal.fromDouble(x.toDouble() / scale),
      GodotReal.fromDouble(y.toDouble() / scale),
      GodotReal.fromDouble(z.toDouble() / scale),
    )

  operator fun div(scale: Float): Vector3 =
    Vector3(
      GodotReal.fromDouble(x.toDouble() / scale.toDouble()),
      GodotReal.fromDouble(y.toDouble() / scale.toDouble()),
      GodotReal.fromDouble(z.toDouble() / scale.toDouble()),
    )

  operator fun unaryMinus(): Vector3 = Vector3(-x, -y, -z)

  /**
   * Returns the squared length (squared magnitude) of this vector. This method runs faster than
   * `length`, so prefer it if you need to compare vectors or need the squared distance for some
   * formula.
   *
   * Generated from Godot docs: Vector3.length_squared
   */
  fun lengthSquared(): Double = (x * x + y * y + z * z).toDouble()

  /**
   * Returns the length (magnitude) of this vector.
   *
   * Generated from Godot docs: Vector3.length
   */
  fun length(): Double = sqrt(lengthSquared())

  /**
   * Returns the result of scaling the vector to unit length. Equivalent to `v / v.length()`.
   * Returns `(0, 0, 0)` if `v.length() == 0`. See also `is_normalized`. Note: This function may
   * return incorrect values if the input vector length is near zero.
   *
   * Generated from Godot docs: Vector3.normalized
   */
  fun normalized(): Vector3 {
    val len = length()
    return if (len == 0.0) ZERO else this / len
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
   * equal, this method returns `AXIS_X`.
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
  fun dot(other: Vector3): Double = (x * other.x + y * other.y + z * other.z).toDouble()

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
    Vector3(y * other.z - z * other.y, z * other.x - x * other.z, x * other.y - y * other.x)

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
        listOf(BArg.Floats(BuiltinCalls.PT_VECTOR3, normal.toGodotRealArray())),
      )
    )

  fun withX(value: Number): Vector3 = Vector3(value, y, z)

  fun withY(value: Number): Vector3 = Vector3(x, value, z)

  fun withZ(value: Number): Vector3 = Vector3(x, y, value)

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
        BArg.Floats(BuiltinCalls.PT_VECTOR3, to.toGodotRealArray()),
        BArg.Floats(BuiltinCalls.PT_VECTOR3, axis.toGodotRealArray()),
      ),
    )

  // The (Vector3, float) -> Vector3 shape that lerp / rotated / move_toward share.
  private fun callVector3RealRetVector3(methodPtr: Long, vector: Vector3, value: Double): Vector3 =
    fromGodotRealArray(
      BuiltinCalls.call(
        methodPtr,
        toGodotRealArray(),
        3,
        listOf(BArg.Floats(BuiltinCalls.PT_VECTOR3, vector.toGodotRealArray()), BArg.Real(value)),
      )
    )

  private fun toGodotRealArray(): GodotRealArray =
    GodotRealArray(3).also {
      it[0] = GodotReal.toC(x)
      it[1] = GodotReal.toC(y)
      it[2] = GodotReal.toC(z)
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

    private val lerpBind by lazy {
      BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_VECTOR3, "lerp", LERP_HASH)
    }
    private val limitLengthBind by lazy {
      BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_VECTOR3, "limit_length", LIMIT_LENGTH_HASH)
    }
    private val bounceBind by lazy {
      BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_VECTOR3, "bounce", BOUNCE_HASH)
    }
    private val rotatedBind by lazy {
      BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_VECTOR3, "rotated", ROTATED_HASH)
    }
    private val moveTowardBind by lazy {
      BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_VECTOR3, "move_toward", MOVE_TOWARD_HASH)
    }
    private val signedAngleToBind by lazy {
      BuiltinCalls.getBuiltinMethod(
        BuiltinCalls.VT_VECTOR3,
        "signed_angle_to",
        SIGNED_ANGLE_TO_HASH,
      )
    }

    private fun fromGodotRealArray(c: GodotRealArray): Vector3 =
      Vector3(GodotReal.fromC(c[0]), GodotReal.fromC(c[1]), GodotReal.fromC(c[2]))

    /**
     * Zero vector, a vector with all components set to `0`.
     *
     * Generated from Godot docs: Vector3.ZERO
     */
    val ZERO = Vector3(0f, 0f, 0f)
    /**
     * One vector, a vector with all components set to `1`.
     *
     * Generated from Godot docs: Vector3.ONE
     */
    val ONE = Vector3(1f, 1f, 1f)
    /**
     * Up unit vector.
     *
     * Generated from Godot docs: Vector3.UP
     */
    val UP = Vector3(0f, 1f, 0f)
    /**
     * Down unit vector.
     *
     * Generated from Godot docs: Vector3.DOWN
     */
    val DOWN = Vector3(0f, -1f, 0f)
    /**
     * Forward unit vector. Represents the local direction of forward, and the global direction of
     * north. Keep in mind that the forward direction for lights, cameras, etc is different from 3D
     * assets like characters, which face towards the camera by convention. Use
     * `Vector3.MODEL_FRONT` and similar constants when working in 3D asset space.
     *
     * Generated from Godot docs: Vector3.FORWARD
     */
    val FORWARD = Vector3(0f, 0f, -1f)
    /**
     * Back unit vector. Represents the local direction of back, and the global direction of south.
     *
     * Generated from Godot docs: Vector3.BACK
     */
    val BACK = Vector3(0f, 0f, 1f)
    /**
     * Right unit vector. Represents the local direction of right, and the global direction of east.
     *
     * Generated from Godot docs: Vector3.RIGHT
     */
    val RIGHT = Vector3(1f, 0f, 0f)
    /**
     * Left unit vector. Represents the local direction of left, and the global direction of west.
     *
     * Generated from Godot docs: Vector3.LEFT
     */
    val LEFT = Vector3(-1f, 0f, 0f)
  }
}
