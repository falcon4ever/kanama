package net.multigesture.kanama.types

import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Task 134 A2: the Web value types store float32 and compute with Godot's `real_t` formulas (the
 * sources shared with the native types), so their results are Godot's. The expected values are the
 * float32 bits Godot 4.7.2 prints for the same operations (the runtime smoke's
 * `value_type_storage_ref.gd`), written as little-endian hex like
 * `PackedFloat32Array([f]).to_byte_array().hex_encode()`.
 */
class WebValueTypeStorageTest {
  private fun bits(value: Double): String {
    val raw = value.toFloat().toRawBits()
    return (0 until 4).joinToString("") {
      ((raw shr (8 * it)) and 0xFF).toString(16).padStart(2, '0')
    }
  }

  private fun bits(v: Vector2): String = "${bits(v.x)},${bits(v.y)}"

  private fun bits(v: Vector3): String = "${bits(v.x)},${bits(v.y)},${bits(v.z)}"

  @Test
  fun arithmeticHasGodotsFloat32Bits() {
    val a = Vector2(0.1, 0.2)
    val b = Vector2(0.7, 0.3)
    val c = Vector3(0.1, 0.2, 0.3)
    val d = Vector3(0.7, 0.11, 0.13)
    assertEquals("cdcc4c3f,0000003f", bits(a + b))
    assertEquals("999919bf,ceccccbd", bits(a - b))
    assertEquals("0bd7233c,0bd7a33c", bits(a * 0.1))
    assertEquals("aaaaaa3e,aaaa2a3f", bits(a / 0.3))
    assertEquals("2ff9643e", bits(a.length()))
    assertEquals("4460e5bb,5fba493e,941804be", bits(c.cross(d)))
    assertEquals("76d6883e,76d6083f,b2414d3f", bits(c.normalized()))
    assertEquals("a892bf3e", bits(c.length()))
    val basis = Basis(Quaternion(0.1, 0.2, 0.3, 0.9).normalized())
    assertEquals(
      "d4ef393f,744b1c3f,2aafa1be/a2bc06bf,f21a4a3f,2aafa13e/a05be23e,ee5881bd,790d653f",
      "${bits(basis.x)}/${bits(basis.y)}/${bits(basis.z)}",
    )
    assertEquals("cecccc3d,cccc4c3e,9a99993e", bits(basis * c))
  }

  @Test
  fun storageEqualityAndPrintingAreGodots() {
    val v = Vector2(0.1, 0.2)
    assertEquals(0.1f.toDouble(), v.x)
    assertEquals(v, Vector2(0.1f.toDouble(), 0.2f.toDouble()))
    assertEquals(Color(0.0, 0.0, 0.0), Color(-0.0, 0.0, 0.0))
    assertEquals("(0.1, 0.2)", v.toString())
    assertEquals("(1.0, 0.5, 0.0, 1.0)", Color(1.0, 0.5, 0.0).toString())
    assertEquals("(0, 0, 0, 1)", Quaternion.IDENTITY.toString())
    assertEquals(Vector3(1.5, 0.0, 2.0), Vector3(1.5, 0, 2))
    assertEquals(Color(1.0, 1.0, 1.0, 0.72), Color(1, 1, 1, 0.72))
  }
}
