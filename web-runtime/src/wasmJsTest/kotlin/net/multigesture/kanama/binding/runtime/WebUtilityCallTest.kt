package net.multigesture.kanama.binding.runtime

import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue
import net.multigesture.kanama.api.GD
import net.multigesture.kanama.api.Mathf
import net.multigesture.kanama.builtins.godotFormat
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.web.WebPackedFloats
import net.multigesture.kanama.web.WebPackedValues

/**
 * Task 129 E / task 138 item 2: the shared generated GD on Web (utility calls on the builtin
 * opcode, static flag 2) and Map / List / Array / Dictionary on that crossing, against a fake
 * transport (Node has no bridge).
 */
class WebUtilityCallTest {
  @AfterTest
  fun reset() {
    webBuiltinTransportForTests = null
  }

  @Test
  fun aFrameUtilityCrossesItsArgumentsAsWritten() {
    var request = ""
    webBuiltinTransportForTests = { packed ->
      request = packed
      "3:" + WebPackedFloats.encode(0.25)
    }
    assertEquals(0.25, GD.randfRange(0.0, 1.0))
    val parts = request.split('\u001F')
    assertEquals(listOf("0", "randf_range", "2", ""), parts.take(4))
    assertEquals("3:" + WebPackedFloats.encode(0.0), parts[4])
    assertEquals(2 + 4, parts.size)
    webBuiltinTransportForTests = { packed ->
      request = packed
      "2:7"
    }
    assertEquals(7L, GD.randiRange(9L, 7L))
    assertEquals(listOf("2:9", "2:7"), request.split('\u001F').drop(4))
  }

  /** Task 129 E review B1: the arithmetic utilities run in Kotlin, never over the bridge. */
  @Test
  fun arithmeticUtilitiesNeverCross() {
    webBuiltinTransportForTests = { packed -> error("crossed: ${packed.split('\u001F')[1]}") }
    assertEquals(0.25, GD.lerpf(0.0, 1.0, 0.25))
    assertEquals(7L, GD.clampi(9L, 0L, 7L))
    assertEquals(kotlin.math.sin(0.7), GD.sin(0.7))
    assertEquals(kotlin.math.sin(0.7), Mathf.sin(0.7))
    assertEquals(3L, GD.roundi(2.5))
    assertEquals(-3L, Mathf.roundToInt(-2.5))
    assertEquals(-4L, GD.floori(-3.5))
    assertEquals(2L, GD.posmod(-1L, 3L))
    assertEquals(0.5, GD.ease(0.5, 1.0))
    assertEquals(3.0, GD.max(1.0, 3.0, 2.0))
    assertEquals(3L, GD.max(1L, 3, 2L))
    assertEquals(-2.5, GD.min(1.0, -2.5f.toDouble()))
    // Godot's Variant clamp: `value < min` then `value > max` on the result.
    assertEquals(1.0, GD.clamp(0.0 as Any?, 3.0, 1.0))
    assertEquals(3.0, GD.clampf(0.0, 3.0, 1.0))
    assertEquals(2.5, GD.abs(-2.5))
    assertEquals(4L, GD.abs(-4L))
    assertEquals(2.0, GD.snapped(2.2, 0.5))
    assertEquals(1.0, Mathf.wrap(-1.0, 0.0, 2.0))
    assertEquals(kotlin.math.exp(-6.0 * 0.11512925464970228420089957273422), GD.dbToLinear(-6.0))
  }

  /** The inputs a port does not cover still reach the engine (its error, its answer). */
  @Test
  fun uncoveredInputsCross() {
    val crossed = mutableListOf<String>()
    webBuiltinTransportForTests = { packed ->
      crossed += packed.split('\u001F')[1]
      "2:0"
    }
    GD.floori(Double.NaN)
    GD.roundi(1e19)
    GD.posmod(5L, 0L)
    Mathf.posmod(5L, 0L)
    GD.max(1L, 2.5)
    GD.abs(listOf(1L))
    assertEquals(listOf("floori", "roundi", "posmod", "posmod", "max", "abs"), crossed)
  }

  @Test
  fun aBoxedUtilityDecodesContainers() {
    var request = ""
    webBuiltinTransportForTests = { packed ->
      request = packed
      WebPackedValues.encodeVariant(listOf(1L, "x", mapOf("k" to true)))
    }
    assertEquals(listOf(1L, "x", mapOf("k" to true)), GD.strToVar("[1, \"x\", {\"k\": true}]"))
    assertEquals(listOf("0", "str_to_var", "2", ""), request.split('\u001F').take(4))
  }

  /** Review N9: printing before any proxy is installed prints here; anything else fails loud. */
  @Test
  fun textUtilitiesPrintWithoutAProxy() {
    webBuiltinTransportForTests = { "E:no proxy is installed" }
    GD.print("before", 1L)
    GD.pushError("early")
    val error = assertFailsWith<IllegalStateException> { GD.str(1L) }
    assertTrue("no proxy" in error.message!!, error.message)
  }

  @Test
  fun aProxyFailureFailsLoud() {
    webBuiltinTransportForTests = { "E:no utility function randf taking 0 argument(s)" }
    val error = assertFailsWith<IllegalStateException> { GD.randf() }
    assertTrue("utility function randf" in error.message!!, error.message)
  }

  @Test
  fun mapAndListArgumentsCrossTheBuiltinCall() {
    var request = ""
    webBuiltinTransportForTests = { packed ->
      request = packed
      "4:1-x"
    }
    assertEquals("1-x", "{a}-{b}".godotFormat(mapOf("a" to 1L, "b" to "x")))
    val values = WebPackedValues.decodeVariant(request.split('\u001F')[4])
    assertEquals(mapOf("a" to 1L, "b" to "x"), values)
  }

  @Test
  fun containersRoundTripWhateverTheyHold() {
    val value =
      listOf(
        1L,
        2.5,
        "a,b%1F\u001F%2C",
        mapOf("k" to listOf(true, null), 3L to emptyList<Any?>()),
        NodePath("x/y"),
        emptyMap<Any?, Any?>(),
      )
    assertEquals(value, WebPackedValues.decodeVariant(WebPackedValues.encodeVariant(value)))
  }
}
