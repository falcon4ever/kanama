package net.multigesture.kanama.types

import kotlin.jvm.JvmInline

/**
 * A 2D vector using integer coordinates. Kanama value types are immutable snapshots; assign a new
 * value back to the Godot property after changing components.
 *
 * Generated from Godot docs: Vector2i
 */
data class Vector2i(
  /**
   * The vector's X component. Also accessible by using the index position `[0]`.
   *
   * Generated from Godot docs: Vector2i.x
   */
  val x: Int,
  /**
   * The vector's Y component. Also accessible by using the index position `[1]`.
   *
   * Generated from Godot docs: Vector2i.y
   */
  val y: Int,
) {
  // ===== BEGIN GENERATED ENUMS: Vector2i (scripts/generate_api_wrapper.py — do not edit) =====
  /**
   * Godot's `Vector2i.Axis` enum as a typed value: `.value` is the raw number Godot uses, and the
   * companion holds the named values (`Vector2i.Axis.<NAME>`).
   *
   * Generated from Godot docs: Vector2i.Axis
   */
  @JvmInline
  value class Axis(override val value: Long) : net.multigesture.kanama.api.GodotEnumValue {
    companion object {
      /**
       * Enumerated value for the X axis. Returned by `max_axis_index` and `min_axis_index`.
       *
       * Generated from Godot docs: Vector2i.AXIS_X
       */
      val X: Axis
        get() = Axis(0L)

      /**
       * Enumerated value for the Y axis. Returned by `max_axis_index` and `min_axis_index`.
       *
       * Generated from Godot docs: Vector2i.AXIS_Y
       */
      val Y: Axis
        get() = Axis(1L)
    }
  }

  // ===== END GENERATED ENUMS: Vector2i =====

  /** Returns a copy with the X component replaced. */
  fun withX(value: Int): Vector2i = Vector2i(value, y)

  /** Returns a copy with the Y component replaced. */
  fun withY(value: Int): Vector2i = Vector2i(x, value)

  // ===== BEGIN GENERATED BUILTIN MEMBERS: Vector2i (generate_builtin_ops.py) =====
  operator fun unaryMinus(): Vector2i = Vector2i(-x, -y)

  operator fun unaryPlus(): Vector2i = this

  operator fun times(scalar: Int): Vector2i = Vector2i(x * scalar, y * scalar)

  operator fun times(scalar: Long): Vector2i = times(scalar.toInt())

  operator fun div(scalar: Int): Vector2i = Vector2i(x / scalar, y / scalar)

  operator fun div(scalar: Long): Vector2i = div(scalar.toInt())

  operator fun rem(scalar: Int): Vector2i = Vector2i(x % scalar, y % scalar)

  operator fun rem(scalar: Long): Vector2i = rem(scalar.toInt())

  operator fun times(scalar: Double): Vector2 {
    val s = narrowReal(scalar)
    return Vector2.raw(narrowReal(x.toDouble()) * s, narrowReal(y.toDouble()) * s)
  }

  operator fun div(scalar: Double): Vector2 {
    val s = narrowReal(scalar)
    return Vector2.raw(narrowReal(x.toDouble()) / s, narrowReal(y.toDouble()) / s)
  }

  operator fun compareTo(other: Vector2i): Int {
    val c0 = godotCompareStep(x, other.x)
    if (c0 != 0) return c0
    return godotCompareStep(y, other.y)
  }

  operator fun plus(other: Vector2i): Vector2i = Vector2i(x + other.x, y + other.y)

  operator fun minus(other: Vector2i): Vector2i = Vector2i(x - other.x, y - other.y)

  operator fun times(other: Vector2i): Vector2i = Vector2i(x * other.x, y * other.y)

  operator fun div(other: Vector2i): Vector2i = Vector2i(x / other.x, y / other.y)

  operator fun rem(other: Vector2i): Vector2i = Vector2i(x % other.x, y % other.y)

  /**
   * Returns the aspect ratio of this vector, the ratio of `x` to `y`.
   *
   * Generated from Godot docs: Vector2i.aspect
   */
  fun aspect(): Double = widenReal(narrowReal(x.toDouble()) / narrowReal(y.toDouble()))

  /**
   * Returns the axis of the vector's highest value. See `AXIS_*` constants. If all components are
   * equal, this method returns `Axis.X`.
   *
   * Generated from Godot docs: Vector2i.max_axis_index
   */
  fun maxAxisIndex(): Long = if (x < y) 1L else 0L

  /**
   * Returns the axis of the vector's lowest value. See `AXIS_*` constants. If all components are
   * equal, this method returns `Axis.Y`.
   *
   * Generated from Godot docs: Vector2i.min_axis_index
   */
  fun minAxisIndex(): Long = if (x < y) 0L else 1L

  /**
   * Returns the Euclidean distance (https://en.wikipedia.org/wiki/Euclidean_distance) between this
   * vector and `to`.
   *
   * Generated from Godot docs: Vector2i.distance_to
   */
  fun distanceTo(to: Vector2i): Double = (to - this).length()

  /**
   * Returns the squared Euclidean distance (https://en.wikipedia.org/wiki/Euclidean_distance)
   * between this vector and `to`. This method runs faster than `distance_to`, so prefer it if you
   * need to compare vectors or need the squared distance for some formula.
   *
   * Generated from Godot docs: Vector2i.distance_squared_to
   */
  fun distanceSquaredTo(to: Vector2i): Long = (to - this).lengthSquared()

  /**
   * Returns the length (magnitude) of this vector.
   *
   * Generated from Godot docs: Vector2i.length
   */
  fun length(): Double = godotSqrt(lengthSquared().toDouble())

  /**
   * Returns the squared length (squared magnitude) of this vector. This method runs faster than
   * `length`, so prefer it if you need to compare vectors or need the squared distance for some
   * formula.
   *
   * Generated from Godot docs: Vector2i.length_squared
   */
  fun lengthSquared(): Long = x.toLong() * x + y.toLong() * y

  /**
   * Returns a new vector with each component set to `1` if it's positive, `-1` if it's negative,
   * and `0` if it's zero. The result is identical to calling `@GlobalScope.sign` on each component.
   *
   * Generated from Godot docs: Vector2i.sign
   */
  fun sign(): Vector2i = Vector2i(godotSign(x), godotSign(y))

  /**
   * Returns a new vector with all components in absolute values (i.e. positive).
   *
   * Generated from Godot docs: Vector2i.abs
   */
  fun abs(): Vector2i = Vector2i(godotAbs(x), godotAbs(y))

  /**
   * Returns a new vector with all components clamped between the components of `min` and `max`, by
   * running `@GlobalScope.clamp` on each component.
   *
   * Generated from Godot docs: Vector2i.clamp
   */
  fun clamp(min: Vector2i, max: Vector2i): Vector2i =
    Vector2i(godotClamp(x, min.x, max.x), godotClamp(y, min.y, max.y))

  /**
   * Returns a new vector with all components clamped between `min` and `max`, by running
   * `@GlobalScope.clamp` on each component.
   *
   * Generated from Godot docs: Vector2i.clampi
   */
  fun clampi(min: Long, max: Long): Vector2i {
    val lo = min.toInt()
    val hi = max.toInt()
    return Vector2i(godotClamp(x, lo, hi), godotClamp(y, lo, hi))
  }

  /**
   * Returns a new vector with each component snapped to the closest multiple of the corresponding
   * component in `step`.
   *
   * Generated from Godot docs: Vector2i.snapped
   */
  fun snapped(step: Vector2i): Vector2i {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, step)
    f.call(Vector2iMethods.snapped, 1)
    return f.retVector2i()
  }

  /**
   * Returns a new vector with each component snapped to the closest multiple of `step`.
   *
   * Generated from Godot docs: Vector2i.snappedi
   */
  fun snappedi(step: Long): Vector2i {
    val f = builtinFrame()
    f.put(0, this)
    f.putLong(1, step)
    f.call(Vector2iMethods.snappedi, 1)
    return f.retVector2i()
  }

  /**
   * Returns the component-wise minimum of this and `with`, equivalent to `Vector2i(mini(x, with.x),
   * mini(y, with.y))`.
   *
   * Generated from Godot docs: Vector2i.min
   */
  fun min(with: Vector2i): Vector2i = Vector2i(godotMin(x, with.x), godotMin(y, with.y))

  /**
   * Returns the component-wise minimum of this and `with`, equivalent to `Vector2i(mini(x, with),
   * mini(y, with))`.
   *
   * Generated from Godot docs: Vector2i.mini
   */
  fun mini(with: Long): Vector2i {
    val s = with.toInt()
    return Vector2i(godotMin(x, s), godotMin(y, s))
  }

  /**
   * Returns the component-wise maximum of this and `with`, equivalent to `Vector2i(maxi(x, with.x),
   * maxi(y, with.y))`.
   *
   * Generated from Godot docs: Vector2i.max
   */
  fun max(with: Vector2i): Vector2i = Vector2i(godotMax(x, with.x), godotMax(y, with.y))

  /**
   * Returns the component-wise maximum of this and `with`, equivalent to `Vector2i(maxi(x, with),
   * maxi(y, with))`.
   *
   * Generated from Godot docs: Vector2i.maxi
   */
  fun maxi(with: Long): Vector2i {
    val s = with.toInt()
    return Vector2i(godotMax(x, s), godotMax(y, s))
  }

  // ===== END GENERATED BUILTIN MEMBERS: Vector2i =====

  companion object {
    // ===== BEGIN GENERATED BUILTIN STATICS: Vector2i (generate_builtin_ops.py) =====
    /**
     * Min vector, a vector with all components equal to `INT32_MIN`. Can be used as a negative
     * integer equivalent of `Vector2.INF`.
     *
     * Generated from Godot docs: Vector2i.MIN
     */
    val MIN: Vector2i = Vector2i(Int.MIN_VALUE, Int.MIN_VALUE)

    /**
     * Max vector, a vector with all components equal to `INT32_MAX`. Can be used as an integer
     * equivalent of `Vector2.INF`.
     *
     * Generated from Godot docs: Vector2i.MAX
     */
    val MAX: Vector2i = Vector2i(Int.MAX_VALUE, Int.MAX_VALUE)

    /**
     * Left unit vector. Represents the direction of left.
     *
     * Generated from Godot docs: Vector2i.LEFT
     */
    val LEFT: Vector2i = Vector2i(-1, 0)

    /**
     * Right unit vector. Represents the direction of right.
     *
     * Generated from Godot docs: Vector2i.RIGHT
     */
    val RIGHT: Vector2i = Vector2i(1, 0)

    /**
     * Up unit vector. Y is down in 2D, so this vector points -Y.
     *
     * Generated from Godot docs: Vector2i.UP
     */
    val UP: Vector2i = Vector2i(0, -1)

    /**
     * Down unit vector. Y is down in 2D, so this vector points +Y.
     *
     * Generated from Godot docs: Vector2i.DOWN
     */
    val DOWN: Vector2i = Vector2i(0, 1)

    // ===== END GENERATED BUILTIN STATICS: Vector2i =====

    /**
     * Zero vector, a vector with all components set to `0`.
     *
     * Generated from Godot docs: Vector2i.ZERO
     */
    val ZERO = Vector2i(0, 0)
    /**
     * One vector, a vector with all components set to `1`.
     *
     * Generated from Godot docs: Vector2i.ONE
     */
    val ONE = Vector2i(1, 1)
  }
}
