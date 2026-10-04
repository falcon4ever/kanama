package net.multigesture.kanama.types

import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.log10

// Storage and printing shared by the value types (task 134 A2). A value type keeps its components
// at Godot's width — `real_t` ([GodotRealStorage]: float32 by default, float64 with
// `-PkanamaPrecision=double`) for Vector2…Projection, float32 for Color — and exposes them as
// `Double`. So a value is exactly the value Godot stores: `node.position = v; node.position == v`
// holds and `toString()` prints what GDScript's `str(v)` prints.
//
// ONE source for the native and the Web value types: this directory (`types/shared`) is also a
// source directory of `web-runtime`, which supplies its own `GodotRealStorage` (Float),
// `REAL_IS_SINGLE`, `narrowReal` and `widenReal` (`WebReal.kt`); natively they come from the
// generated `Real.kt`. Keep this directory free of anything else either side lacks.

/** Selects a value type's raw constructor: the arguments are already at the storage width. */
internal object RawStorage

/**
 * Godot's `Math::is_same` for a stored component: `==` (so `-0.0 == 0.0`), plus NaN equal to NaN,
 * which keeps the JVM `equals` contract reflexive.
 */
internal fun storedEquals(a: Float, b: Float): Boolean = a == b || (a.isNaN() && b.isNaN())

internal fun storedEquals(a: Double, b: Double): Boolean = a == b || (a.isNaN() && b.isNaN())

/** Hash of a stored component consistent with [storedEquals] (signed zero canonicalised). */
internal fun storedHash(value: Float): Int = (value + 0.0f).hashCode()

internal fun storedHash(value: Double): Int = (value + 0.0).hashCode()

/**
 * Godot's `String::num_real(real_t, trailing)` for a stored `real_t` component (`value` is the
 * exact widened stored value): integers print as `1` or `1.0`, others with 6 significant decimals
 * in a float32 build (14 in a float64 build), trailing zeros removed.
 */
internal fun godotRealString(value: Double, trailing: Boolean): String =
  if (REAL_IS_SINGLE) numRealFloat(value.toFloat(), trailing) else numRealDouble(value, trailing)

/** Godot's `String::num(double, 4)`, the form `Color` prints its float32 channels with. */
internal fun godotColorChannelString(value: Float): String = godotNum(value.toDouble(), 4)

private fun numRealFloat(value: Float, trailing: Boolean): String {
  if (value.isNaN() || value.isInfinite()) return godotNum(value.toDouble(), 0)
  // C++ `(int64_t)p_num`; Kotlin saturates out-of-range values like arm64 does.
  val whole = value.toLong()
  if (value == whole.toFloat()) return if (trailing) "$whole.0" else whole.toString()
  var decimals = 6
  val absValue = abs(value)
  if (absValue > 10f) decimals -= floor(log10(absValue)).toInt()
  return godotNum(value.toDouble(), decimals)
}

private fun numRealDouble(value: Double, trailing: Boolean): String {
  if (value.isNaN() || value.isInfinite()) return godotNum(value, 0)
  val whole = value.toLong()
  if (value == whole.toDouble()) return if (trailing) "$whole.0" else whole.toString()
  var decimals = 14
  val absValue = abs(value)
  if (absValue > 10.0) decimals -= floor(log10(absValue)).toInt()
  return godotNum(value, decimals)
}

private const val MAX_DECIMALS = 32

/** Godot's `String::num(double, decimals)`: C `%.Nlf`, then trailing zeros removed (one kept). */
internal fun godotNum(value: Double, decimals: Int): String {
  if (value.isNaN()) return "nan"
  if (value.isInfinite()) return if (value < 0) "-inf" else "inf"
  var places = decimals
  if (places < 0) {
    places = 14
    val absValue = abs(value)
    if (absValue > 10) places -= floor(log10(absValue)).toInt()
  }
  if (places > MAX_DECIMALS) places = MAX_DECIMALS
  // A negative count left over is Godot's "%lf": printf's default of 6 places.
  val text = formatFixed(value, if (places < 0) 6 else places)
  if ('.' !in text) return text
  var end = text.length
  while (text[end - 1] == '0') end--
  if (text[end - 1] == '.') end++
  return text.substring(0, end)
}

/**
 * C `printf("%.{places}f", value)`: the exact binary value rounded half-to-even to [places]
 * decimals, the way the C library formats it on every platform Godot ships.
 */
internal fun formatFixed(value: Double, places: Int): String {
  val bits = value.toRawBits()
  val negative = bits < 0
  val exponentBits = ((bits ushr 52) and 0x7FF).toInt()
  var mantissa = bits and 0xF_FFFF_FFFF_FFFFL
  if (exponentBits != 0) mantissa = mantissa or (1L shl 52)
  // value = mantissa * 2^exponent
  var exponent = (if (exponentBits == 0) 1 else exponentBits) - 1075
  if (mantissa == 0L) exponent = 0
  while (exponent < 0 && mantissa != 0L && (mantissa and 1L) == 0L) {
    mantissa = mantissa ushr 1
    exponent++
  }
  val digits =
    fastScaledDigits(mantissa, exponent, places) ?: BigNat.scaledDigits(mantissa, exponent, places)
  val padded =
    if (digits.length <= places) "0".repeat(places - digits.length + 1) + digits else digits
  val body =
    if (places == 0) padded
    else
      padded.substring(0, padded.length - places) + "." + padded.substring(padded.length - places)
  return if (negative) "-$body" else body
}

private val POW10 =
  LongArray(19).also { table ->
    table[0] = 1L
    for (i in 1 until table.size) table[i] = table[i - 1] * 10L
  }

/** round_half_even(mantissa * 2^exponent * 10^places) as digits, when it fits a Long; else null. */
private fun fastScaledDigits(mantissa: Long, exponent: Int, places: Int): String? {
  if (exponent > 0 || places >= POW10.size) return null
  val scale = POW10[places]
  if (mantissa > Long.MAX_VALUE / scale) return null
  val scaled = mantissa * scale
  val shift = -exponent
  if (shift == 0) return scaled.toString()
  if (shift >= 63) return "0" // scaled < 2^63 <= half of 2^shift: rounds to zero
  var quotient = scaled ushr shift
  val remainder = scaled and ((1L shl shift) - 1)
  val half = 1L shl (shift - 1)
  if (remainder > half || (remainder == half && (quotient and 1L) == 1L)) quotient++
  return quotient.toString()
}

/** The few arbitrary-size natural-number steps [formatFixed] needs past a Long. */
private object BigNat {
  // Little-endian 32-bit limbs held in Longs.
  fun scaledDigits(mantissa: Long, exponent: Int, places: Int): String {
    var n = longArrayOf(mantissa and 0xFFFF_FFFFL, mantissa ushr 32)
    repeat(places) { n = mulSmall(n, 10L) }
    if (exponent >= 0) return toDecimal(shiftLeft(n, exponent))
    val shift = -exponent
    val quotient = shiftRight(n, shift)
    val halfBit = testBit(n, shift - 1)
    val sticky = anyBitBelow(n, shift - 1)
    val rounded =
      if (halfBit && (sticky || testBit(quotient, 0))) addSmall(quotient, 1L) else quotient
    return toDecimal(rounded)
  }

  private fun mulSmall(n: LongArray, factor: Long): LongArray {
    val out = LongArray(n.size + 1)
    var carry = 0L
    for (i in n.indices) {
      val product = n[i] * factor + carry
      out[i] = product and 0xFFFF_FFFFL
      carry = product ushr 32
    }
    out[n.size] = carry
    return out
  }

  private fun addSmall(n: LongArray, addend: Long): LongArray {
    val out = n.copyOf(n.size + 1)
    var carry = addend
    var i = 0
    while (carry != 0L) {
      val sum = out[i] + carry
      out[i] = sum and 0xFFFF_FFFFL
      carry = sum ushr 32
      i++
    }
    return out
  }

  private fun shiftLeft(n: LongArray, shift: Int): LongArray {
    val limbShift = shift / 32
    val bitShift = shift % 32
    val out = LongArray(n.size + limbShift + 1)
    for (i in n.indices) {
      val wide = n[i] shl bitShift
      out[i + limbShift] = out[i + limbShift] or (wide and 0xFFFF_FFFFL)
      out[i + limbShift + 1] = out[i + limbShift + 1] or (wide ushr 32)
    }
    return out
  }

  private fun shiftRight(n: LongArray, shift: Int): LongArray {
    val limbShift = shift / 32
    val bitShift = shift % 32
    if (limbShift >= n.size) return longArrayOf(0L)
    val out = LongArray(n.size - limbShift)
    for (i in out.indices) {
      val low = n[i + limbShift] ushr bitShift
      val high = if (i + limbShift + 1 < n.size) (n[i + limbShift + 1] shl (32 - bitShift)) else 0L
      out[i] = (low or (if (bitShift == 0) 0L else high)) and 0xFFFF_FFFFL
    }
    return out
  }

  private fun testBit(n: LongArray, bit: Int): Boolean {
    if (bit < 0) return false
    val limb = bit / 32
    return limb < n.size && ((n[limb] ushr (bit % 32)) and 1L) == 1L
  }

  private fun anyBitBelow(n: LongArray, bit: Int): Boolean {
    if (bit <= 0) return false
    val fullLimbs = bit / 32
    for (i in 0 until minOf(fullLimbs, n.size)) if (n[i] != 0L) return true
    if (fullLimbs >= n.size) return false
    val mask = (1L shl (bit % 32)) - 1
    return (n[fullLimbs] and mask) != 0L
  }

  private fun toDecimal(number: LongArray): String {
    var n = number.copyOf()
    val chunks = ArrayList<Long>()
    while (n.any { it != 0L }) {
      var remainder = 0L
      for (i in n.indices.reversed()) {
        val current = (remainder shl 32) or n[i]
        n[i] = current / 1_000_000_000L
        remainder = current % 1_000_000_000L
      }
      chunks += remainder
    }
    if (chunks.isEmpty()) return "0"
    val text = StringBuilder(chunks.last().toString())
    for (i in chunks.size - 2 downTo 0) text.append(chunks[i].toString().padStart(9, '0'))
    return text.toString()
  }
}
