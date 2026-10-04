package net.multigesture.kanama.web

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Task 133 C3: decimals on the Web text channels survive NaN and the infinities both ways. GDScript
 * prints `nan` / `inf` / `-inf` (Kotlin's `toDouble()` rejects them) and its `split_floats` reads
 * Kotlin's `NaN` / `Infinity` as 0.0, so both sides use GDScript's spelling.
 */
class WebPackedFloatsTest {
  @Test
  fun encodesNanAndInfinitiesAsGdscriptPrintsThem() {
    assertEquals("nan", WebPackedFloats.encode(Double.NaN))
    assertEquals("inf", WebPackedFloats.encode(Double.POSITIVE_INFINITY))
    assertEquals("-inf", WebPackedFloats.encode(Double.NEGATIVE_INFINITY))
    assertEquals("2.5", WebPackedFloats.encode(2.5))
  }

  @Test
  fun decodesWhatGdscriptPrints() {
    // `"%s,%s,%s,%s" % [c.r, c.g, c.b, c.a]` of Color(NAN, INF, 1.5e-12, 0.1) (the review probe).
    val parts = "nan,inf,0.0000000000015,0.10000000149012".split(',').map(WebPackedFloats::decode)
    assertTrue(parts[0].isNaN())
    assertEquals(Double.POSITIVE_INFINITY, parts[1])
    assertEquals(1.5e-12, parts[2])
    assertEquals(0.10000000149012, parts[3])
    assertEquals(Double.NEGATIVE_INFINITY, WebPackedFloats.decode("-inf"))
  }

  @Test
  fun roundTripsEveryValue() {
    for (value in listOf(0.0, -0.0, 1.0 / 3.0, 1.0e-300, 3.4028234663852886e38, Double.MAX_VALUE)) {
      assertEquals(value, WebPackedFloats.decode(WebPackedFloats.encode(value)))
    }
  }
}
