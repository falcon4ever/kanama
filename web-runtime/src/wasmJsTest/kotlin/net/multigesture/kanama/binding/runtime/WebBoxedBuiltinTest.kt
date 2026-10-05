package net.multigesture.kanama.binding.runtime

import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue
import net.multigesture.kanama.builtins.bigrams
import net.multigesture.kanama.builtins.getExtension
import net.multigesture.kanama.builtins.getStringFromUtf8
import net.multigesture.kanama.builtins.md5Buffer
import net.multigesture.kanama.builtins.num
import net.multigesture.kanama.builtins.splitFloats
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.web.WebPackedFloats

/**
 * Task 134 D2: the boxed builtin call on Web (`UtilityCalls.callMethod`), against a fake proxy
 * (Node has no bridge): the base and the arguments cross as encoded Variants, a static method as
 * the static flag with no base, and the String / StringName / NodePath / packed returns decode.
 */
class WebBoxedBuiltinTest {
  @AfterTest
  fun reset() {
    webBuiltinTransportForTests = null
  }

  @Test
  fun aStringMethodSendsItsBaseAndReadsAString() {
    var request = ""
    webBuiltinTransportForTests = { packed ->
      request = packed
      "4:gz"
    }
    assertEquals("gz", "res://a/b.tar.gz".getExtension())
    assertEquals(listOf("4", "get_extension", "0", "4:res://a/b.tar.gz"), request.split('\u001F'))
  }

  @Test
  fun aStaticMethodHasNoBase() {
    var request = ""
    webBuiltinTransportForTests = { packed ->
      request = packed
      "4:3.14"
    }
    assertEquals("3.14", String.num(3.14159, 2L))
    val parts = request.split('\u001F')
    assertEquals(listOf("4", "num", "1", ""), parts.take(4))
    assertEquals("3:" + WebPackedFloats.encode(3.14159), parts[4])
    assertEquals("2:2", parts[5])
  }

  @Test
  fun packedReturnsDecode() {
    webBuiltinTransportForTests = { "34:2\u001Fab\u001Fb%1Fc" }
    assertEquals(listOf("ab", "b\u001Fc"), "abc".bigrams())
    webBuiltinTransportForTests = {
      "33:" + WebPackedFloats.encode(1.0) + "," + WebPackedFloats.encode(2.5)
    }
    assertEquals(listOf(1.0, 2.5), "1,2.5".splitFloats(","))
    webBuiltinTransportForTests = { "29:00ff10" }
    assertContentEquals(byteArrayOf(0, -1, 16), "x".md5Buffer())
    webBuiltinTransportForTests = { "34:0" }
    assertEquals(emptyList(), "".bigrams())
  }

  @Test
  fun aByteArrayBaseCrossesAsHexAndNodePathIsLocal() {
    var bytesRequest = ""
    webBuiltinTransportForTests = { packed ->
      bytesRequest = packed
      "4:é"
    }
    assertEquals("é", byteArrayOf(0xC3.toByte(), 0xA9.toByte()).getStringFromUtf8())
    assertEquals("29:c3a9", bytesRequest.split('\u001F')[3])
    // NodePath's members are Godot's parse ported to Kotlin: no crossing (the transport would
    // fail).
    webBuiltinTransportForTests = { error("NodePath members must not cross the bridge") }
    assertEquals(NodePath("Arm"), NodePath("Arm/Hand").slice(0L, 1L))
    assertEquals("Hand", NodePath("Arm/Hand").getName(1L))
    assertEquals(NodePath(":Arm/Hand:x"), NodePath("/Arm/Hand:x").getAsPropertyPath())
    // hash() stays in the engine.
    var request = ""
    webBuiltinTransportForTests = { packed ->
      request = packed
      "2:42"
    }
    assertEquals(42L, NodePath("Arm").hash())
    assertEquals(listOf("22", "hash", "0", "22:Arm"), request.split('\u001F'))
  }

  @Test
  fun aReturnOfTheWrongTypeFailsLoud() {
    webBuiltinTransportForTests = { "0:" }
    val error = assertFailsWith<IllegalStateException> { "a.png".getExtension() }
    assertTrue("returned Variant type 0" in error.message!!, error.message)
  }
}
