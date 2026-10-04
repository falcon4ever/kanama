@file:Suppress("NOTHING_TO_INLINE")

package net.multigesture.kanama.types

import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.sqrt
import kotlin.math.truncate

// Godot's scalar helpers as the generated value-type members use them (task 134 B), shared by the
// native and the Web value types like RealMath.kt. Each mirrors Godot's definition exactly, NaN
// and signed-zero behaviour included, which is why Kotlin's own `round`/`sign`/`min` (half-even,
// NaN-propagating, -0.0-aware) are not used. Float and Double overloads rather than
// GodotRealStorage ones: Color stores float32 in every build, and in a float32 build the storage
// alias IS Float, so one alias overload would clash with the Float one.

/** C `round` (`Math::round`): halves away from zero. */
internal inline fun godotRound(x: Float): Float {
  val t = truncate(x)
  return if (abs(x - t) >= 0.5f) t + (if (x < 0f) -1f else 1f) else t
}

/** C `round` (`Math::round`): halves away from zero. */
internal inline fun godotRound(x: Double): Double {
  val t = truncate(x)
  return if (abs(x - t) >= 0.5) t + (if (x < 0.0) -1.0 else 1.0) else t
}

/** `Math::abs` / `Math::floor` / `Math::ceil` / `Math::sqrt` (IEEE: the same in every language). */
internal inline fun godotFabs(x: Float): Float = abs(x)

internal inline fun godotFabs(x: Double): Double = abs(x)

internal inline fun godotFloor(x: Float): Float = floor(x)

internal inline fun godotFloor(x: Double): Double = floor(x)

internal inline fun godotCeil(x: Float): Float = ceil(x)

internal inline fun godotCeil(x: Double): Double = ceil(x)

internal inline fun godotSqrt(x: Double): Double = sqrt(x)

internal inline fun godotSqrt(x: Float): Float = sqrt(x)

/** `Math::lerp(float, float, float)`: `from + (to - from) * weight` (Color's channels). */
internal inline fun realLerpF(from: Float, to: Float, weight: Float): Float =
  from + (to - from) * weight

/**
 * `Math::fposmod`: C `fmod` (Kotlin's `%` on Float/Double), moved to the divisor's sign, then `+ 0`
 * so a zero result is +0.
 */
internal inline fun godotFposmod(x: Float, y: Float): Float {
  var value = x % y
  if ((value < 0f && y > 0f) || (value > 0f && y < 0f)) value += y
  return value + 0f
}

internal inline fun godotFposmod(x: Double, y: Double): Double {
  var value = x % y
  if ((value < 0.0 && y > 0.0) || (value > 0.0 && y < 0.0)) value += y
  return value + 0.0
}

/** Godot's `SIGN`: `1`, `-1`, else `0` (so NaN and -0.0 give +0). */
internal inline fun godotSign(x: Float): Float = if (x > 0f) 1f else if (x < 0f) -1f else 0f

internal inline fun godotSign(x: Double): Double = if (x > 0.0) 1.0 else if (x < 0.0) -1.0 else 0.0

internal inline fun godotSign(x: Int): Int = if (x > 0) 1 else if (x < 0) -1 else 0

/** Godot's `MIN`: `a < b ? a : b`. */
internal inline fun godotMin(a: Float, b: Float): Float = if (a < b) a else b

internal inline fun godotMin(a: Double, b: Double): Double = if (a < b) a else b

internal inline fun godotMin(a: Int, b: Int): Int = if (a < b) a else b

/** Godot's `MAX`: `a > b ? a : b`. */
internal inline fun godotMax(a: Float, b: Float): Float = if (a > b) a else b

internal inline fun godotMax(a: Double, b: Double): Double = if (a > b) a else b

internal inline fun godotMax(a: Int, b: Int): Int = if (a > b) a else b

/** Godot's `CLAMP`: `a < lo ? lo : (a > hi ? hi : a)`. */
internal inline fun godotClamp(a: Float, lo: Float, hi: Float): Float =
  if (a < lo) lo else if (a > hi) hi else a

internal inline fun godotClamp(a: Double, lo: Double, hi: Double): Double =
  if (a < lo) lo else if (a > hi) hi else a

internal inline fun godotClamp(a: Int, lo: Int, hi: Int): Int =
  if (a < lo) lo else if (a > hi) hi else a

/** `int32_t` absolute value as C computes it (`abs(INT_MIN)` stays `INT_MIN`). */
internal inline fun godotAbs(x: Int): Int = if (x < 0) -x else x

/**
 * The comparison of Godot's lexicographic vector `<`/`>` as one `compareTo` step: equal components
 * defer to the next one. A NaN component compares greater (Godot answers false to all four
 * comparisons there, which one `Int` cannot express).
 */
internal inline fun godotCompareStep(a: Float, b: Float): Int =
  if (a == b) 0 else if (a < b) -1 else 1

internal inline fun godotCompareStep(a: Double, b: Double): Int =
  if (a == b) 0 else if (a < b) -1 else 1

internal inline fun godotCompareStep(a: Int, b: Int): Int = if (a == b) 0 else if (a < b) -1 else 1

/** `Color::_append_hex`: a float32 channel as two lower-case hex digits. */
internal fun godotHexByte(channel: Float, out: StringBuilder) {
  // C++ converts the rounded float to int (arm64 saturates, NaN gives 0, as Kotlin's toInt does).
  val v = godotClamp(godotRound(channel * 255.0f).toInt(), 0, 255)
  out.append(HEX_DIGITS[(v shr 4) and 0xF]).append(HEX_DIGITS[v and 0xF])
}

private const val HEX_DIGITS = "0123456789abcdef"
