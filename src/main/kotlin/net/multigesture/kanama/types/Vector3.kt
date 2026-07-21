@file:Suppress("REDUNDANT_CALL_OF_CONVERSION_METHOD")

package net.multigesture.kanama.types

import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout.JAVA_DOUBLE
import kotlin.math.sqrt
import net.multigesture.kanama.binding.runtime.BuiltinTypes
import net.multigesture.kanama.binding.runtime.VariantType

private const val LERP_HASH = 1682608829L
private const val LIMIT_LENGTH_HASH = 514930144L
private const val BOUNCE_HASH = 2923479887L
private const val ROTATED_HASH = 1682608829L
private const val MOVE_TOWARD_HASH = 1682608829L
private const val SIGNED_ANGLE_TO_HASH = 2781412522L

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
  fun lerp(to: Vector3, weight: Double): Vector3 =
    callVector3FloatRetVector3("lerp", LERP_HASH, to, weight)

  /**
   * Returns the vector with a maximum length by limiting its length to `length`. If the vector is
   * non-finite, the result is undefined.
   *
   * Generated from Godot docs: Vector3.limit_length
   */
  fun limitLength(maxLength: Double): Vector3 =
    callFloatRetVector3("limit_length", LIMIT_LENGTH_HASH, maxLength)

  /**
   * Returns the vector "bounced off" from a plane defined by the given normal `n`. Note: `bounce`
   * performs the operation that most engines and frameworks call `reflect()`.
   *
   * Generated from Godot docs: Vector3.bounce
   */
  fun bounce(normal: Vector3): Vector3 = callVector3RetVector3("bounce", BOUNCE_HASH, normal)

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
    callVector3FloatRetVector3("rotated", ROTATED_HASH, axis, angle)

  /**
   * Returns a new vector moved toward `to` by the fixed `delta` amount. Will not go past the final
   * value.
   *
   * Generated from Godot docs: Vector3.move_toward
   */
  fun moveToward(to: Vector3, delta: Double): Vector3 =
    callVector3FloatRetVector3("move_toward", MOVE_TOWARD_HASH, to, delta)

  /**
   * Returns the signed angle to the given vector, in radians. The sign of the angle is positive in
   * a counter-clockwise direction and negative in a clockwise direction when viewed from the side
   * specified by the `axis`.
   *
   * Generated from Godot docs: Vector3.signed_angle_to
   */
  fun signedAngleTo(to: Vector3, axis: Vector3): Double =
    callVector3Vector3RetDouble("signed_angle_to", SIGNED_ANGLE_TO_HASH, to, axis)

  private fun callVector3RetVector3(method: String, hash: Long, value: Vector3): Vector3 {
    Arena.ofConfined().use { arena ->
      val base = arena.allocate(GodotReal.SIZE_BYTES * 3, GodotReal.ALIGN_BYTES)
      val valueArg = arena.allocate(GodotReal.SIZE_BYTES * 3, GodotReal.ALIGN_BYTES)
      val ret = arena.allocate(GodotReal.SIZE_BYTES * 3, GodotReal.ALIGN_BYTES)
      writeTo(base)
      value.writeTo(valueArg)
      BuiltinTypes.call(
        type = VariantType.VECTOR3,
        method = method,
        hash = hash,
        base = base,
        args = listOf(valueArg),
        rReturn = ret,
      )
      return readFrom(ret)
    }
  }

  private fun callFloatRetVector3(method: String, hash: Long, value: Double): Vector3 {
    Arena.ofConfined().use { arena ->
      val base = arena.allocate(GodotReal.SIZE_BYTES * 3, GodotReal.ALIGN_BYTES)
      val valueArg = arena.allocate(JAVA_DOUBLE)
      val ret = arena.allocate(GodotReal.SIZE_BYTES * 3, GodotReal.ALIGN_BYTES)
      writeTo(base)
      valueArg.set(JAVA_DOUBLE, 0, value)
      BuiltinTypes.call(
        type = VariantType.VECTOR3,
        method = method,
        hash = hash,
        base = base,
        args = listOf(valueArg),
        rReturn = ret,
      )
      return readFrom(ret)
    }
  }

  private fun callVector3FloatRetVector3(
    method: String,
    hash: Long,
    vector: Vector3,
    value: Double,
  ): Vector3 {
    Arena.ofConfined().use { arena ->
      val base = arena.allocate(GodotReal.SIZE_BYTES * 3, GodotReal.ALIGN_BYTES)
      val vectorArg = arena.allocate(GodotReal.SIZE_BYTES * 3, GodotReal.ALIGN_BYTES)
      val valueArg = arena.allocate(JAVA_DOUBLE)
      val ret = arena.allocate(GodotReal.SIZE_BYTES * 3, GodotReal.ALIGN_BYTES)
      writeTo(base)
      vector.writeTo(vectorArg)
      valueArg.set(JAVA_DOUBLE, 0, value)
      BuiltinTypes.call(
        type = VariantType.VECTOR3,
        method = method,
        hash = hash,
        base = base,
        args = listOf(vectorArg, valueArg),
        rReturn = ret,
      )
      return readFrom(ret)
    }
  }

  private fun callVector3Vector3RetDouble(
    method: String,
    hash: Long,
    first: Vector3,
    second: Vector3,
  ): Double {
    Arena.ofConfined().use { arena ->
      val base = arena.allocate(GodotReal.SIZE_BYTES * 3, GodotReal.ALIGN_BYTES)
      val firstArg = arena.allocate(GodotReal.SIZE_BYTES * 3, GodotReal.ALIGN_BYTES)
      val secondArg = arena.allocate(GodotReal.SIZE_BYTES * 3, GodotReal.ALIGN_BYTES)
      val ret = arena.allocate(JAVA_DOUBLE)
      writeTo(base)
      first.writeTo(firstArg)
      second.writeTo(secondArg)
      BuiltinTypes.call(
        type = VariantType.VECTOR3,
        method = method,
        hash = hash,
        base = base,
        args = listOf(firstArg, secondArg),
        rReturn = ret,
      )
      return ret.get(JAVA_DOUBLE, 0)
    }
  }

  private fun writeTo(dest: MemorySegment) {
    GodotReal.writeIndex(dest, 0, x)
    GodotReal.writeIndex(dest, 1, y)
    GodotReal.writeIndex(dest, 2, z)
  }

  companion object {
    private fun readFrom(src: MemorySegment): Vector3 =
      Vector3(GodotReal.readIndex(src, 0), GodotReal.readIndex(src, 1), GodotReal.readIndex(src, 2))

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
