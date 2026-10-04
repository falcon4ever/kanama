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
  ) : this(GodotReal.toC(x), GodotReal.toC(y), GodotReal.toC(z), GodotReal.toC(w), RawStorage)

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
    get() = GodotReal.fromC(rawX)

  /**
   * The vector's Y component. Also accessible by using the index position `[1]`.
   *
   * Generated from Godot docs: Vector4.y
   */
  val y: Double
    get() = GodotReal.fromC(rawY)

  /**
   * The vector's Z component. Also accessible by using the index position `[2]`.
   *
   * Generated from Godot docs: Vector4.z
   */
  val z: Double
    get() = GodotReal.fromC(rawZ)

  /**
   * The vector's W component. Also accessible by using the index position `[3]`.
   *
   * Generated from Godot docs: Vector4.w
   */
  val w: Double
    get() = GodotReal.fromC(rawW)

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
  operator fun times(scale: Double): Vector4 = scaled(GodotReal.toC(scale))

  operator fun times(scale: Float): Vector4 = scaled(GodotReal.toC(scale.toDouble()))

  operator fun times(scale: Int): Vector4 = scaled(GodotReal.toC(scale.toDouble()))

  operator fun times(scale: Long): Vector4 = scaled(GodotReal.toC(scale.toDouble()))

  private fun scaled(s: GodotRealStorage): Vector4 = raw(rawX * s, rawY * s, rawZ * s, rawW * s)

  operator fun unaryMinus(): Vector4 = raw(-rawX, -rawY, -rawZ, -rawW)

  /**
   * Returns the squared length (squared magnitude) of this vector. This method runs faster than
   * `length`, so prefer it if you need to compare vectors or need the squared distance for some
   * formula.
   *
   * Generated from Godot docs: Vector4.length_squared
   */
  fun lengthSquared(): Double = GodotReal.fromC(rawLengthSquared())

  private fun rawLengthSquared(): GodotRealStorage =
    rawX * rawX + rawY * rawY + rawZ * rawZ + rawW * rawW

  /**
   * Returns the length (magnitude) of this vector.
   *
   * Generated from Godot docs: Vector4.length
   */
  fun length(): Double = GodotReal.fromC(sqrt(rawLengthSquared()))

  /**
   * Returns the dot product of this vector and `with`.
   *
   * Generated from Godot docs: Vector4.dot
   */
  fun dot(other: Vector4): Double =
    GodotReal.fromC(rawX * other.rawX + rawY * other.rawY + rawZ * other.rawZ + rawW * other.rawW)

  companion object {
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
