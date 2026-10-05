package net.multigesture.kanama.builtins

import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector2

/**
 * Task 134 D2: the PackedByteArray codecs ported to Kotlin, on vectors worked out from Godot's
 * source (variant_call.cpp, marshalls.h, Math::make_half_float). The runtime smoke's `bytes=` row
 * compares the same functions with GDScript over random arrays; these pin the documented edges
 * (sign extension, little-endian order, half-float truncation, the throwing out-of-range form).
 */
class GodotBytesTest {
  private val bytes = byteArrayOf(0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x88.toByte())

  @Test
  fun decodesLittleEndianWithGodotsSignedness() {
    assertEquals(0x01L, bytes.decodeU8(0))
    assertEquals(0x88L, bytes.decodeU8(7))
    assertEquals(-120L, bytes.decodeS8(7))
    assertEquals(0x0201L, bytes.decodeU16(0))
    assertEquals(0x04030201L, bytes.decodeU32(0))
    assertEquals(0x8807060504030201uL.toLong(), bytes.decodeU64(0))
    assertEquals(bytes.decodeU64(0), bytes.decodeS64(0))
    assertEquals(-0x77f9L, bytes.decodeS16(6))
  }

  @Test
  fun encodesTruncateLikeGodot() {
    val c = ByteArray(8)
    c.encodeU16(0, 0x12345L)
    assertContentEquals(byteArrayOf(0x45, 0x23, 0, 0, 0, 0, 0, 0), c)
    c.encodeS32(4, -2L)
    assertEquals(-2L, c.decodeS32(4))
    assertEquals(0xFFFFFFFEL, c.decodeU32(4))
    c.encodeDouble(0, -1.5)
    assertEquals(-1.5, c.decodeDouble(0))
    c.encodeFloat(0, 0.1)
    assertEquals(0.1f.toDouble(), c.decodeFloat(0))
  }

  @Test
  fun halfFloatsFollowMathMakeHalfFloat() {
    val c = ByteArray(2)
    for ((value, bits) in
      listOf(
        1.0 to 0x3C00L,
        -2.0 to 0xC000L,
        65504.0 to 0x7BFFL,
        1e9 to 0x7C00L,
        1e-8 to 0L,
        -1e-8 to 0L,
      )) {
      c.encodeHalf(0, value)
      assertEquals(bits, c.decodeU16(0), "half bits of $value")
    }
    // Godot truncates the mantissa (no rounding): 1.0009765625 + a little stays 0x3C01.
    c.encodeHalf(0, 1.00195)
    assertEquals(0x3C01L, c.decodeU16(0))
    c.encodeU16(0, 0x0001L) // the smallest subnormal half decodes exactly
    assertEquals(5.960464477539063e-8, c.decodeHalf(0))
    c.encodeU16(0, 0x7E00L)
    assertTrue(c.decodeHalf(0).isNaN())
  }

  @Test
  fun bswapAndHexMatchGodot() {
    val c = bytes.copyOf()
    c.bswap16(0, 2)
    assertContentEquals(byteArrayOf(0x02, 0x01, 0x04, 0x03, 0x05, 0x06, 0x07, 0x88.toByte()), c)
    val d = bytes.copyOf()
    d.bswap64()
    assertContentEquals(bytes.reversedArray(), d)
    assertEquals("0102030405060788", bytes.hexEncode())
    ByteArray(0).bswap32() // an empty array is a no-op, as in Godot
  }

  @Test
  fun arraysReinterpretTheBytes() {
    assertEquals(listOf(0x04030201, 0x88070605.toInt()), bytes.toInt32Array())
    assertEquals(emptyList(), ByteArray(0).toFloat64Array())
    val v = listOf(Vector2(1.5, -2.0)).toByteArray()
    assertEquals(listOf(Vector2(1.5, -2.0)), v.toVector2Array())
    val colors = listOf(Color(0.25, 0.5, 0.75, 1.0))
    assertEquals(colors, colors.toByteArray().toColorArray())
    assertContentEquals(
      byteArrayOf(0x61, 0, 0xC3.toByte(), 0xBC.toByte(), 0),
      listOf("a", "ü").toByteArray(),
    )
    assertContentEquals(
      byteArrayOf(1, 0, 0, 0, 0xFF.toByte(), 0xFF.toByte(), 0xFF.toByte(), 0xFF.toByte()),
      listOf(1, -1).toByteArray(),
    )
  }

  @Test
  fun outOfRangeThrowsWhereGodotPrintsAnError() {
    assertFailsWith<IndexOutOfBoundsException> { bytes.decodeU32(5) }
    assertFailsWith<IndexOutOfBoundsException> { bytes.decodeU8(-1) }
    assertFailsWith<IndexOutOfBoundsException> { ByteArray(3).encodeU32(0, 1L) }
    assertFailsWith<IllegalArgumentException> { ByteArray(6).toInt32Array() }
    assertFailsWith<IllegalArgumentException> { bytes.copyOf().bswap32(0, 3) }
  }
}
