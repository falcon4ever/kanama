package net.multigesture.kanama.binding.runtime

import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull
import kotlin.test.assertTrue
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3
import net.multigesture.kanama.web.WebPackedFloats

/**
 * Task 134 D1 review S2: the Web builtin-call crossing, against a fake proxy (Node has no bridge).
 * The request carries every value with its Variant type and every decimal as its IEEE-754 bits; a
 * result of the wrong type (the engine's nil for a wrong argument count or type) or a proxy-side
 * failure throws instead of reading zeros, and a pooled frame never leaks an earlier result.
 */
class WebBuiltinRemoteTest {
  @AfterTest
  fun reset() {
    webBuiltinTransportForTests = null
  }

  private fun bits(vararg values: Double) = values.joinToString(",") { WebPackedFloats.encode(it) }

  @Test
  fun sendsTypedValuesAndReadsTheResult() {
    var request = ""
    webBuiltinTransportForTests = { packed ->
      request = packed
      "9:" + bits(1.5, -0.5, 0.5)
    }
    val snapped = Vector3(1.26, -0.74, 0.5).snapped(Vector3(0.5, 0.5, 0.5))
    assertEquals(Vector3(1.5, -0.5, 0.5), snapped)
    val parts = request.split('\u001F')
    assertEquals(listOf("9", "snapped", "0"), parts.take(3))
    assertEquals("9:" + bits(1.26f.toDouble(), (-0.74f).toDouble(), 0.5), parts[3])
    assertEquals("9:" + bits(0.5, 0.5, 0.5), parts[4])
  }

  @Test
  fun aNilForATypedReturnFailsLoud() {
    webBuiltinTransportForTests = { "0:" }
    val error =
      assertFailsWith<IllegalStateException> { Vector2(1.0, 0.0).angleTo(Vector2(0.0, 1.0)) }
    assertTrue("returned Variant type 0" in error.message!!, error.message)
  }

  @Test
  fun aProxyFailureFailsLoud() {
    webBuiltinTransportForTests = { "E:snapped takes 1 argument(s), the call passed 0" }
    val error = assertFailsWith<IllegalStateException> { Vector3.ONE.snapped(Vector3.ONE) }
    assertTrue("takes 1 argument" in error.message!!, error.message)
  }

  @Test
  fun aVariantReturnMayBeNilAndNeverReadsAStaleResult() {
    webBuiltinTransportForTests = { "9:" + bits(7.0, 8.0, 9.0) }
    val box = AABB(Vector3.ZERO, Vector3.ONE)
    assertEquals(Vector3(7.0, 8.0, 9.0), box.intersectsRay(Vector3(0.5, 5.0, 0.5), Vector3.DOWN))
    webBuiltinTransportForTests = { "0:" }
    assertNull(box.intersectsRay(Vector3(0.5, 5.0, 0.5), Vector3.RIGHT))
  }
}
