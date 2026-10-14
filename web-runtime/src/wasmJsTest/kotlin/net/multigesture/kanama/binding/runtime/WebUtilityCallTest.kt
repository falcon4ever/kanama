package net.multigesture.kanama.binding.runtime

import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue
import net.multigesture.kanama.api.GD
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
    assertEquals(0.25, GD.lerpf(0.0, 1.0, 0.25))
    val parts = request.split('\u001F')
    assertEquals(listOf("0", "lerpf", "2", ""), parts.take(4))
    assertEquals("3:" + WebPackedFloats.encode(0.0), parts[4])
    assertEquals(3 + 4, parts.size)
    webBuiltinTransportForTests = { packed ->
      request = packed
      "2:7"
    }
    assertEquals(7L, GD.clampi(9L, 0L, 7L))
    assertEquals(listOf("2:9", "2:0", "2:7"), request.split('\u001F').drop(4))
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

  @Test
  fun aProxyFailureFailsLoud() {
    webBuiltinTransportForTests = { "E:no utility function sin taking 1 argument(s)" }
    val error = assertFailsWith<IllegalStateException> { GD.sin(1.0) }
    assertTrue("utility function sin" in error.message!!, error.message)
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
