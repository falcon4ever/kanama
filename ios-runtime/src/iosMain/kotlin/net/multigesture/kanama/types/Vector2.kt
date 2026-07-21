package net.multigesture.kanama.types

import kotlin.math.atan2
import kotlin.math.sqrt
import net.multigesture.kanama.binding.runtime.BuiltinCalls

data class Vector2(val x: real_t, val y: real_t) {
  constructor(x: Number, y: Number) : this(GodotReal.fromNumber(x), GodotReal.fromNumber(y))

  // Match GDScript/C# `==`: signed zero equal (-0.0 == 0.0), NaN reflexive. See
  // wrapper-coverage-roadmap.md. hashCode canonicalizes signed zero so equal vectors hash equal.
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other !is Vector2) return false
    return (x == other.x || (x.isNaN() && other.x.isNaN())) &&
      (y == other.y || (y.isNaN() && other.y.isNaN()))
  }

  override fun hashCode(): Int {
    var result = (x + 0.0).hashCode()
    result = 31 * result + (y + 0.0).hashCode()
    return result
  }

  /** Godot `Vector2.is_equal_approx`: per-component fuzzy compare (CMP_EPSILON tolerance). */
  fun isEqualApprox(other: Vector2): Boolean =
    isEqualApprox(x, other.x) && isEqualApprox(y, other.y)

  /** Godot `Vector2.is_zero_approx`: true if both components are approximately zero. */
  fun isZeroApprox(): Boolean = isZeroApprox(x) && isZeroApprox(y)

  operator fun plus(other: Vector2): Vector2 = Vector2(x + other.x, y + other.y)

  operator fun minus(other: Vector2): Vector2 = Vector2(x - other.x, y - other.y)

  operator fun times(scale: Number): Vector2 =
    Vector2(x.toDouble() * scale.toDouble(), y.toDouble() * scale.toDouble())

  operator fun div(scale: Number): Vector2 =
    Vector2(x.toDouble() / scale.toDouble(), y.toDouble() / scale.toDouble())

  // Godot Vector2.lerp — component-wise linear interpolation (matches the engine exactly).
  fun lerp(to: Vector2, weight: Double): Vector2 = this + (to - this) * weight

  operator fun unaryMinus(): Vector2 = Vector2(-x, -y)

  fun lengthSquared(): Double = (x * x + y * y).toDouble()

  fun length(): Double = sqrt(lengthSquared())

  /** Returns this vector scaled to unit length, or [ZERO] if it has zero length (matches Godot). */
  fun normalized(): Vector2 {
    val len = length()
    return if (len == 0.0) ZERO else this / len
  }

  /**
   * Returns a copy with each component clamped between [min] and [max] (matches Godot
   * Vector2.clamp).
   */
  fun clamp(min: Vector2, max: Vector2): Vector2 =
    Vector2(x.coerceIn(min.x, max.x), y.coerceIn(min.y, max.y))

  fun angle(): Double = atan2(y.toDouble(), x.toDouble())

  fun withX(value: Number): Vector2 = Vector2(value, y)

  fun withY(value: Number): Vector2 = Vector2(x, value)

  /**
   * Returns this vector rotated by [angle] radians, computed by Godot via the value-type builtin
   * call path (so the rotation direction matches the engine exactly).
   */
  fun rotated(angle: Double): Vector2 =
    fromGodotRealArray(
      BuiltinCalls.call(rotatedBind, toGodotRealArray(), 2, listOf(BuiltinCalls.BArg.Real(angle)))
    )

  private fun toGodotRealArray(): GodotRealArray =
    GodotRealArray(2).also {
      it[0] = GodotReal.toC(x)
      it[1] = GodotReal.toC(y)
    }

  companion object {
    val ZERO = Vector2(0.0, 0.0)
    val ONE = Vector2(1.0, 1.0)
    val LEFT = Vector2(-1.0, 0.0)
    val RIGHT = Vector2(1.0, 0.0)
    val UP = Vector2(0.0, -1.0)
    val DOWN = Vector2(0.0, 1.0)

    private val rotatedBind by lazy {
      BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_VECTOR2, "rotated", 2544004089L)
    }

    private fun fromGodotRealArray(c: GodotRealArray): Vector2 =
      Vector2(GodotReal.fromC(c[0]), GodotReal.fromC(c[1]))
  }
}
