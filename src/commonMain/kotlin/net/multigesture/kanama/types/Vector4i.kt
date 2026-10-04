package net.multigesture.kanama.types

import kotlin.jvm.JvmInline

/**
 * A 4D vector using integer coordinates. Kanama value types are immutable snapshots; assign a new
 * value back to the Godot property after changing components.
 *
 * Generated from Godot docs: Vector4i
 */
data class Vector4i(
  /**
   * The vector's X component. Also accessible by using the index position `[0]`.
   *
   * Generated from Godot docs: Vector4i.x
   */
  val x: Int,
  /**
   * The vector's Y component. Also accessible by using the index position `[1]`.
   *
   * Generated from Godot docs: Vector4i.y
   */
  val y: Int,
  /**
   * The vector's Z component. Also accessible by using the index position `[2]`.
   *
   * Generated from Godot docs: Vector4i.z
   */
  val z: Int,
  /**
   * The vector's W component. Also accessible by using the index position `[3]`.
   *
   * Generated from Godot docs: Vector4i.w
   */
  val w: Int,
) {
  // ===== BEGIN GENERATED ENUMS: Vector4i (scripts/generate_api_wrapper.py — do not edit) =====
  /**
   * Godot's `Vector4i.Axis` enum as a typed value: `.value` is the raw number Godot uses, and the
   * companion holds the named values (`Vector4i.Axis.<NAME>`).
   *
   * Generated from Godot docs: Vector4i.Axis
   */
  @JvmInline
  value class Axis(override val value: Long) : net.multigesture.kanama.api.GodotEnumValue {
    companion object {
      /**
       * Enumerated value for the X axis. Returned by `max_axis_index` and `min_axis_index`.
       *
       * Generated from Godot docs: Vector4i.AXIS_X
       */
      val X: Axis
        get() = Axis(0L)

      /**
       * Enumerated value for the Y axis. Returned by `max_axis_index` and `min_axis_index`.
       *
       * Generated from Godot docs: Vector4i.AXIS_Y
       */
      val Y: Axis
        get() = Axis(1L)

      /**
       * Enumerated value for the Z axis. Returned by `max_axis_index` and `min_axis_index`.
       *
       * Generated from Godot docs: Vector4i.AXIS_Z
       */
      val Z: Axis
        get() = Axis(2L)

      /**
       * Enumerated value for the W axis. Returned by `max_axis_index` and `min_axis_index`.
       *
       * Generated from Godot docs: Vector4i.AXIS_W
       */
      val W: Axis
        get() = Axis(3L)
    }
  }

  // ===== END GENERATED ENUMS: Vector4i =====

  // ===== BEGIN GENERATED BUILTIN MEMBERS: Vector4i (generate_builtin_ops.py) =====
  operator fun unaryMinus(): Vector4i = Vector4i(-x, -y, -z, -w)

  operator fun unaryPlus(): Vector4i = this

  operator fun times(scalar: Int): Vector4i =
    Vector4i(x * scalar, y * scalar, z * scalar, w * scalar)

  operator fun times(scalar: Long): Vector4i = times(scalar.toInt())

  operator fun div(scalar: Int): Vector4i = Vector4i(x / scalar, y / scalar, z / scalar, w / scalar)

  operator fun div(scalar: Long): Vector4i = div(scalar.toInt())

  operator fun rem(scalar: Int): Vector4i = Vector4i(x % scalar, y % scalar, z % scalar, w % scalar)

  operator fun rem(scalar: Long): Vector4i = rem(scalar.toInt())

  operator fun times(scalar: Double): Vector4 {
    val s = narrowReal(scalar)
    return Vector4.raw(
      narrowReal(x.toDouble()) * s,
      narrowReal(y.toDouble()) * s,
      narrowReal(z.toDouble()) * s,
      narrowReal(w.toDouble()) * s,
    )
  }

  operator fun div(scalar: Double): Vector4 {
    val s = narrowReal(scalar)
    return Vector4.raw(
      narrowReal(x.toDouble()) / s,
      narrowReal(y.toDouble()) / s,
      narrowReal(z.toDouble()) / s,
      narrowReal(w.toDouble()) / s,
    )
  }

  operator fun compareTo(other: Vector4i): Int {
    val c0 = godotCompareStep(x, other.x)
    if (c0 != 0) return c0
    val c1 = godotCompareStep(y, other.y)
    if (c1 != 0) return c1
    val c2 = godotCompareStep(z, other.z)
    if (c2 != 0) return c2
    return godotCompareStep(w, other.w)
  }

  operator fun plus(other: Vector4i): Vector4i =
    Vector4i(x + other.x, y + other.y, z + other.z, w + other.w)

  operator fun minus(other: Vector4i): Vector4i =
    Vector4i(x - other.x, y - other.y, z - other.z, w - other.w)

  operator fun times(other: Vector4i): Vector4i =
    Vector4i(x * other.x, y * other.y, z * other.z, w * other.w)

  operator fun div(other: Vector4i): Vector4i =
    Vector4i(x / other.x, y / other.y, z / other.z, w / other.w)

  operator fun rem(other: Vector4i): Vector4i =
    Vector4i(x % other.x, y % other.y, z % other.z, w % other.w)

  /**
   * Returns the axis of the vector's lowest value. See `AXIS_*` constants. If all components are
   * equal, this method returns `Axis.W`.
   *
   * Generated from Godot docs: Vector4i.min_axis_index
   */
  fun minAxisIndex(): Long {
    var index = 0L
    var value = x
    if (y <= value) {
      index = 1L
      value = y
    }
    if (z <= value) {
      index = 2L
      value = z
    }
    if (w <= value) {
      index = 3L
      value = w
    }
    return index
  }

  /**
   * Returns the axis of the vector's highest value. See `AXIS_*` constants. If all components are
   * equal, this method returns `Axis.X`.
   *
   * Generated from Godot docs: Vector4i.max_axis_index
   */
  fun maxAxisIndex(): Long {
    var index = 0L
    var value = x
    if (y > value) {
      index = 1L
      value = y
    }
    if (z > value) {
      index = 2L
      value = z
    }
    if (w > value) {
      index = 3L
      value = w
    }
    return index
  }

  /**
   * Returns the length (magnitude) of this vector.
   *
   * Generated from Godot docs: Vector4i.length
   */
  fun length(): Double = godotSqrt(lengthSquared().toDouble())

  /**
   * Returns the squared length (squared magnitude) of this vector. This method runs faster than
   * `length`, so prefer it if you need to compare vectors or need the squared distance for some
   * formula.
   *
   * Generated from Godot docs: Vector4i.length_squared
   */
  fun lengthSquared(): Long = x.toLong() * x + y.toLong() * y + z.toLong() * z + w.toLong() * w

  /**
   * Returns a new vector with each component set to `1` if it's positive, `-1` if it's negative,
   * and `0` if it's zero. The result is identical to calling `@GlobalScope.sign` on each component.
   *
   * Generated from Godot docs: Vector4i.sign
   */
  fun sign(): Vector4i = Vector4i(godotSign(x), godotSign(y), godotSign(z), godotSign(w))

  /**
   * Returns a new vector with all components in absolute values (i.e. positive).
   *
   * Generated from Godot docs: Vector4i.abs
   */
  fun abs(): Vector4i = Vector4i(godotAbs(x), godotAbs(y), godotAbs(z), godotAbs(w))

  /**
   * Returns a new vector with all components clamped between the components of `min` and `max`, by
   * running `@GlobalScope.clamp` on each component.
   *
   * Generated from Godot docs: Vector4i.clamp
   */
  fun clamp(min: Vector4i, max: Vector4i): Vector4i =
    Vector4i(
      godotClamp(x, min.x, max.x),
      godotClamp(y, min.y, max.y),
      godotClamp(z, min.z, max.z),
      godotClamp(w, min.w, max.w),
    )

  /**
   * Returns a new vector with all components clamped between `min` and `max`, by running
   * `@GlobalScope.clamp` on each component.
   *
   * Generated from Godot docs: Vector4i.clampi
   */
  fun clampi(min: Long, max: Long): Vector4i {
    val lo = min.toInt()
    val hi = max.toInt()
    return Vector4i(
      godotClamp(x, lo, hi),
      godotClamp(y, lo, hi),
      godotClamp(z, lo, hi),
      godotClamp(w, lo, hi),
    )
  }

  /**
   * Returns a new vector with each component snapped to the closest multiple of the corresponding
   * component in `step`.
   *
   * Generated from Godot docs: Vector4i.snapped
   */
  fun snapped(step: Vector4i): Vector4i {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, step)
    f.call(Vector4iMethods.snapped, 1)
    return f.retVector4i()
  }

  /**
   * Returns a new vector with each component snapped to the closest multiple of `step`.
   *
   * Generated from Godot docs: Vector4i.snappedi
   */
  fun snappedi(step: Long): Vector4i {
    val f = builtinFrame()
    f.put(0, this)
    f.putLong(1, step)
    f.call(Vector4iMethods.snappedi, 1)
    return f.retVector4i()
  }

  /**
   * Returns the component-wise minimum of this and `with`, equivalent to `Vector4i(mini(x, with.x),
   * mini(y, with.y), mini(z, with.z), mini(w, with.w))`.
   *
   * Generated from Godot docs: Vector4i.min
   */
  fun min(with: Vector4i): Vector4i =
    Vector4i(godotMin(x, with.x), godotMin(y, with.y), godotMin(z, with.z), godotMin(w, with.w))

  /**
   * Returns the component-wise minimum of this and `with`, equivalent to `Vector4i(mini(x, with),
   * mini(y, with), mini(z, with), mini(w, with))`.
   *
   * Generated from Godot docs: Vector4i.mini
   */
  fun mini(with: Long): Vector4i {
    val s = with.toInt()
    return Vector4i(godotMin(x, s), godotMin(y, s), godotMin(z, s), godotMin(w, s))
  }

  /**
   * Returns the component-wise maximum of this and `with`, equivalent to `Vector4i(maxi(x, with.x),
   * maxi(y, with.y), maxi(z, with.z), maxi(w, with.w))`.
   *
   * Generated from Godot docs: Vector4i.max
   */
  fun max(with: Vector4i): Vector4i =
    Vector4i(godotMax(x, with.x), godotMax(y, with.y), godotMax(z, with.z), godotMax(w, with.w))

  /**
   * Returns the component-wise maximum of this and `with`, equivalent to `Vector4i(maxi(x, with),
   * maxi(y, with), maxi(z, with), maxi(w, with))`.
   *
   * Generated from Godot docs: Vector4i.maxi
   */
  fun maxi(with: Long): Vector4i {
    val s = with.toInt()
    return Vector4i(godotMax(x, s), godotMax(y, s), godotMax(z, s), godotMax(w, s))
  }

  /**
   * Returns the Euclidean distance (https://en.wikipedia.org/wiki/Euclidean_distance) between this
   * vector and `to`.
   *
   * Generated from Godot docs: Vector4i.distance_to
   */
  fun distanceTo(to: Vector4i): Double = (to - this).length()

  /**
   * Returns the squared Euclidean distance (https://en.wikipedia.org/wiki/Euclidean_distance)
   * between this vector and `to`. This method runs faster than `distance_to`, so prefer it if you
   * need to compare vectors or need the squared distance for some formula.
   *
   * Generated from Godot docs: Vector4i.distance_squared_to
   */
  fun distanceSquaredTo(to: Vector4i): Long = (to - this).lengthSquared()

  // ===== END GENERATED BUILTIN MEMBERS: Vector4i =====

  companion object {
    // ===== BEGIN GENERATED BUILTIN STATICS: Vector4i (generate_builtin_ops.py) =====
    /**
     * Min vector, a vector with all components equal to `INT32_MIN`. Can be used as a negative
     * integer equivalent of `Vector4.INF`.
     *
     * Generated from Godot docs: Vector4i.MIN
     */
    val MIN: Vector4i = Vector4i(Int.MIN_VALUE, Int.MIN_VALUE, Int.MIN_VALUE, Int.MIN_VALUE)

    /**
     * Max vector, a vector with all components equal to `INT32_MAX`. Can be used as an integer
     * equivalent of `Vector4.INF`.
     *
     * Generated from Godot docs: Vector4i.MAX
     */
    val MAX: Vector4i = Vector4i(Int.MAX_VALUE, Int.MAX_VALUE, Int.MAX_VALUE, Int.MAX_VALUE)

    // ===== END GENERATED BUILTIN STATICS: Vector4i =====

    /**
     * Zero vector, a vector with all components set to `0`.
     *
     * Generated from Godot docs: Vector4i.ZERO
     */
    val ZERO = Vector4i(0, 0, 0, 0)
    /**
     * One vector, a vector with all components set to `1`.
     *
     * Generated from Godot docs: Vector4i.ONE
     */
    val ONE = Vector4i(1, 1, 1, 1)
  }
}
