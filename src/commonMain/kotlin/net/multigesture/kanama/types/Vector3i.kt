package net.multigesture.kanama.types

import kotlin.jvm.JvmInline

/**
 * A 3D vector using integer coordinates. Kanama value types are immutable snapshots; assign a new
 * value back to the Godot property after changing components.
 *
 * Generated from Godot docs: Vector3i
 */
data class Vector3i(
  /**
   * The vector's X component. Also accessible by using the index position `[0]`.
   *
   * Generated from Godot docs: Vector3i.x
   */
  val x: Int,
  /**
   * The vector's Y component. Also accessible by using the index position `[1]`.
   *
   * Generated from Godot docs: Vector3i.y
   */
  val y: Int,
  /**
   * The vector's Z component. Also accessible by using the index position `[2]`.
   *
   * Generated from Godot docs: Vector3i.z
   */
  val z: Int,
) {
  // ===== BEGIN GENERATED ENUMS: Vector3i (scripts/generate_api_wrapper.py — do not edit) =====
  /**
   * Godot's `Vector3i.Axis` enum as a typed value: `.value` is the raw number Godot uses, and the
   * companion holds the named values (`Vector3i.Axis.<NAME>`).
   *
   * Generated from Godot docs: Vector3i.Axis
   */
  @JvmInline
  value class Axis(override val value: Long) : net.multigesture.kanama.api.GodotEnumValue {
    companion object {
      /**
       * Enumerated value for the X axis. Returned by `max_axis_index` and `min_axis_index`.
       *
       * Generated from Godot docs: Vector3i.AXIS_X
       */
      val X: Axis
        get() = Axis(0L)

      /**
       * Enumerated value for the Y axis. Returned by `max_axis_index` and `min_axis_index`.
       *
       * Generated from Godot docs: Vector3i.AXIS_Y
       */
      val Y: Axis
        get() = Axis(1L)

      /**
       * Enumerated value for the Z axis. Returned by `max_axis_index` and `min_axis_index`.
       *
       * Generated from Godot docs: Vector3i.AXIS_Z
       */
      val Z: Axis
        get() = Axis(2L)
    }
  }

  // ===== END GENERATED ENUMS: Vector3i =====

  /** Returns a copy with the X component replaced. */
  fun withX(value: Int): Vector3i = Vector3i(value, y, z)

  /** Returns a copy with the Y component replaced. */
  fun withY(value: Int): Vector3i = Vector3i(x, value, z)

  /** Returns a copy with the Z component replaced. */
  fun withZ(value: Int): Vector3i = Vector3i(x, y, value)

  // ===== BEGIN GENERATED BUILTIN MEMBERS: Vector3i (generate_builtin_ops.py) =====
  operator fun unaryMinus(): Vector3i = Vector3i(-x, -y, -z)

  operator fun unaryPlus(): Vector3i = this

  operator fun times(scalar: Int): Vector3i = Vector3i(x * scalar, y * scalar, z * scalar)

  operator fun times(scalar: Long): Vector3i = times(scalar.toInt())

  operator fun div(scalar: Int): Vector3i = Vector3i(x / scalar, y / scalar, z / scalar)

  operator fun div(scalar: Long): Vector3i = div(scalar.toInt())

  operator fun rem(scalar: Int): Vector3i = Vector3i(x % scalar, y % scalar, z % scalar)

  operator fun rem(scalar: Long): Vector3i = rem(scalar.toInt())

  operator fun times(scalar: Double): Vector3 {
    val s = narrowReal(scalar)
    return Vector3.raw(
      narrowReal(x.toDouble()) * s,
      narrowReal(y.toDouble()) * s,
      narrowReal(z.toDouble()) * s,
    )
  }

  operator fun div(scalar: Double): Vector3 {
    val s = narrowReal(scalar)
    return Vector3.raw(
      narrowReal(x.toDouble()) / s,
      narrowReal(y.toDouble()) / s,
      narrowReal(z.toDouble()) / s,
    )
  }

  operator fun compareTo(other: Vector3i): Int {
    val c0 = godotCompareStep(x, other.x)
    if (c0 != 0) return c0
    val c1 = godotCompareStep(y, other.y)
    if (c1 != 0) return c1
    return godotCompareStep(z, other.z)
  }

  operator fun plus(other: Vector3i): Vector3i = Vector3i(x + other.x, y + other.y, z + other.z)

  operator fun minus(other: Vector3i): Vector3i = Vector3i(x - other.x, y - other.y, z - other.z)

  operator fun times(other: Vector3i): Vector3i = Vector3i(x * other.x, y * other.y, z * other.z)

  operator fun div(other: Vector3i): Vector3i = Vector3i(x / other.x, y / other.y, z / other.z)

  operator fun rem(other: Vector3i): Vector3i = Vector3i(x % other.x, y % other.y, z % other.z)

  /**
   * Returns the axis of the vector's lowest value. See `AXIS_*` constants. If all components are
   * equal, this method returns `Axis.Z`.
   *
   * Generated from Godot docs: Vector3i.min_axis_index
   */
  fun minAxisIndex(): Long = if (x < y) (if (x < z) 0L else 2L) else (if (y < z) 1L else 2L)

  /**
   * Returns the axis of the vector's highest value. See `AXIS_*` constants. If all components are
   * equal, this method returns `Axis.X`.
   *
   * Generated from Godot docs: Vector3i.max_axis_index
   */
  fun maxAxisIndex(): Long = if (x < y) (if (y < z) 2L else 1L) else (if (x < z) 2L else 0L)

  /**
   * Returns the Euclidean distance (https://en.wikipedia.org/wiki/Euclidean_distance) between this
   * vector and `to`.
   *
   * Generated from Godot docs: Vector3i.distance_to
   */
  fun distanceTo(to: Vector3i): Double = (to - this).length()

  /**
   * Returns the squared Euclidean distance (https://en.wikipedia.org/wiki/Euclidean_distance)
   * between this vector and `to`. This method runs faster than `distance_to`, so prefer it if you
   * need to compare vectors or need the squared distance for some formula.
   *
   * Generated from Godot docs: Vector3i.distance_squared_to
   */
  fun distanceSquaredTo(to: Vector3i): Long = (to - this).lengthSquared()

  /**
   * Returns the length (magnitude) of this vector.
   *
   * Generated from Godot docs: Vector3i.length
   */
  fun length(): Double = godotSqrt(lengthSquared().toDouble())

  /**
   * Returns the squared length (squared magnitude) of this vector. This method runs faster than
   * `length`, so prefer it if you need to compare vectors or need the squared distance for some
   * formula.
   *
   * Generated from Godot docs: Vector3i.length_squared
   */
  fun lengthSquared(): Long = x.toLong() * x + y.toLong() * y + z.toLong() * z

  /**
   * Returns a new vector with each component set to `1` if it's positive, `-1` if it's negative,
   * and `0` if it's zero. The result is identical to calling `@GlobalScope.sign` on each component.
   *
   * Generated from Godot docs: Vector3i.sign
   */
  fun sign(): Vector3i = Vector3i(godotSign(x), godotSign(y), godotSign(z))

  /**
   * Returns a new vector with all components in absolute values (i.e. positive).
   *
   * Generated from Godot docs: Vector3i.abs
   */
  fun abs(): Vector3i = Vector3i(godotAbs(x), godotAbs(y), godotAbs(z))

  /**
   * Returns a new vector with all components clamped between the components of `min` and `max`, by
   * running `@GlobalScope.clamp` on each component.
   *
   * Generated from Godot docs: Vector3i.clamp
   */
  fun clamp(min: Vector3i, max: Vector3i): Vector3i =
    Vector3i(godotClamp(x, min.x, max.x), godotClamp(y, min.y, max.y), godotClamp(z, min.z, max.z))

  /**
   * Returns a new vector with all components clamped between `min` and `max`, by running
   * `@GlobalScope.clamp` on each component.
   *
   * Generated from Godot docs: Vector3i.clampi
   */
  fun clampi(min: Long, max: Long): Vector3i {
    val lo = min.toInt()
    val hi = max.toInt()
    return Vector3i(godotClamp(x, lo, hi), godotClamp(y, lo, hi), godotClamp(z, lo, hi))
  }

  /**
   * Returns a new vector with each component snapped to the closest multiple of the corresponding
   * component in `step`.
   *
   * Generated from Godot docs: Vector3i.snapped
   */
  fun snapped(step: Vector3i): Vector3i =
    builtinVector3i(
      builtinInts(Vector3iMethods.snapped, builtinArg(), 3, listOf(step.builtinArg()))
    )

  /**
   * Returns a new vector with each component snapped to the closest multiple of `step`.
   *
   * Generated from Godot docs: Vector3i.snappedi
   */
  fun snappedi(step: Long): Vector3i =
    builtinVector3i(builtinInts(Vector3iMethods.snappedi, builtinArg(), 3, listOf(argLong(step))))

  /**
   * Returns the component-wise minimum of this and `with`, equivalent to `Vector3i(mini(x, with.x),
   * mini(y, with.y), mini(z, with.z))`.
   *
   * Generated from Godot docs: Vector3i.min
   */
  fun min(with: Vector3i): Vector3i =
    Vector3i(godotMin(x, with.x), godotMin(y, with.y), godotMin(z, with.z))

  /**
   * Returns the component-wise minimum of this and `with`, equivalent to `Vector3i(mini(x, with),
   * mini(y, with), mini(z, with))`.
   *
   * Generated from Godot docs: Vector3i.mini
   */
  fun mini(with: Long): Vector3i {
    val s = with.toInt()
    return Vector3i(godotMin(x, s), godotMin(y, s), godotMin(z, s))
  }

  /**
   * Returns the component-wise maximum of this and `with`, equivalent to `Vector3i(maxi(x, with.x),
   * maxi(y, with.y), maxi(z, with.z))`.
   *
   * Generated from Godot docs: Vector3i.max
   */
  fun max(with: Vector3i): Vector3i =
    Vector3i(godotMax(x, with.x), godotMax(y, with.y), godotMax(z, with.z))

  /**
   * Returns the component-wise maximum of this and `with`, equivalent to `Vector3i(maxi(x, with),
   * maxi(y, with), maxi(z, with))`.
   *
   * Generated from Godot docs: Vector3i.maxi
   */
  fun maxi(with: Long): Vector3i {
    val s = with.toInt()
    return Vector3i(godotMax(x, s), godotMax(y, s), godotMax(z, s))
  }

  // ===== END GENERATED BUILTIN MEMBERS: Vector3i =====

  companion object {
    /**
     * Zero vector, a vector with all components set to `0`.
     *
     * Generated from Godot docs: Vector3i.ZERO
     */
    val ZERO = Vector3i(0, 0, 0)
    /**
     * One vector, a vector with all components set to `1`.
     *
     * Generated from Godot docs: Vector3i.ONE
     */
    val ONE = Vector3i(1, 1, 1)
  }
}
