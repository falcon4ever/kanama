package net.multigesture.kanama.api

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Projection
import net.multigesture.kanama.types.Vector3
import net.multigesture.kanama.types.Vector4

/**
 * Task 134 D1: a signal's arguments as the proxy's `_kanama_web_signal_dispatch_args` packs them
 * (`<Variant.Type>:<payload>`, unit-separated) read by the typed argument decoders.
 */
class WebSignalArgsTest {
  private val packed =
    listOf("2:-5", "3:0.5", "1:1", "4:hi%1Fthere", "24:0", "9:1.0,2.0,3.0", "20:0.25,0.5,1.0,1.0")
      .joinToString("\u001F")

  @Test
  fun readsEveryArgument() {
    val args = WebSignalArgs(packed)
    assertEquals(7, args.size)
    assertEquals(-5L, SignalArgType.LONG.read(args, 0))
    assertEquals(0.5, SignalArgType.DOUBLE.read(args, 1))
    assertEquals(-5.0, SignalArgType.DOUBLE.read(args, 0))
    assertEquals(true, SignalArgType.BOOLEAN.read(args, 2))
    assertEquals("hi\u001Fthere", SignalArgType.STRING.read(args, 3))
    assertNull(SignalArgType.nullableObjectOf("Node") { GodotObject(it) }.read(args, 4))
    assertEquals(Vector3(1.0, 2.0, 3.0), SignalArgType.valueOf<Vector3>("Vector3", Vector3::class).read(args, 5))
    assertEquals(Color(0.25, 0.5, 1.0, 1.0), SignalArgType.VARIANT.read(args, 6))
    assertEquals(0, WebSignalArgs("").size)
  }

  @Test
  fun mismatchesFailLoud() {
    val args = WebSignalArgs(packed)
    assertFailsWith<IllegalArgumentException> { SignalArgType.LONG.read(args, 1) }
    assertFailsWith<IllegalArgumentException> { SignalArgType.objectOf("Node") { GodotObject(it) }.read(args, 4) }
    assertFailsWith<IllegalArgumentException> { SignalArgType.valueOf<Vector4>("Vector4", Vector4::class).read(args, 5) }
    assertFailsWith<IllegalArgumentException> { SignalArgType.LONG.read(args, 7) }
    val projection = WebSignalArgs("19:" + (1..16).joinToString(",") { "$it" })
    assertEquals(
      Projection(Vector4(1.0, 2.0, 3.0, 4.0), Vector4(5.0, 6.0, 7.0, 8.0), Vector4(9.0, 10.0, 11.0, 12.0), Vector4(13.0, 14.0, 15.0, 16.0)),
      SignalArgType.valueOf<Projection>("Projection", Projection::class).read(args = projection, index = 0),
    )
  }
}
