package net.multigesture.kanama.api

import kotlin.math.ln
import kotlin.math.pow

/** Pure-Kotlin Godot math helpers (no engine crossing). */
object Mathf {
  val PI: Double = kotlin.math.PI
  val TAU: Double = kotlin.math.PI * 2.0

  fun abs(value: Double): Double = kotlin.math.abs(value)

  fun log(value: Double): Double = ln(value)

  fun cos(value: Double): Double = kotlin.math.cos(value)

  fun sin(value: Double): Double = kotlin.math.sin(value)

  fun clamp(value: Double, min: Double, max: Double): Double = value.coerceIn(min, max)

  fun lerp(from: Double, to: Double, weight: Double): Double = from + (to - from) * weight

  fun inverseLerp(from: Double, to: Double, value: Double): Double =
    if (to == from) 0.0 else (value - from) / (to - from)

  fun sqrt(value: Double): Double = kotlin.math.sqrt(value)

  fun pow(value: Double, exponent: Double): Double = value.pow(exponent)

  fun atan2(y: Double, x: Double): Double = kotlin.math.atan2(y, x)

  fun min(a: Double, b: Double): Double = kotlin.math.min(a, b)

  fun min(a: Long, b: Long): Long = kotlin.math.min(a, b)

  fun max(a: Double, b: Double): Double = kotlin.math.max(a, b)

  /** Godot's roundi: round half away from zero to the nearest integer. */
  fun roundToInt(value: Double): Long = kotlin.math.round(value).toLong()

  /** Godot's wrapi: wrap [value] into the half-open range [min, max). */
  fun wrap(value: Long, min: Long, max: Long): Long {
    val range = max - min
    if (range == 0L) return min
    return min + ((value - min) % range + range) % range
  }

  fun isEqualApprox(a: Double, b: Double): Boolean = kotlin.math.abs(a - b) < 1e-6

  /** Godot's angle_lerp / lerp_angle: interpolate the shortest arc between two angles (radians). */
  fun lerpAngle(from: Double, to: Double, weight: Double): Double {
    val difference = (to - from) % TAU
    val distance = (2.0 * difference) % TAU - difference
    return from + distance * weight
  }
}
