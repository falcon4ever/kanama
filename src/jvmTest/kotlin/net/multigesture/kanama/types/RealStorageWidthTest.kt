package net.multigesture.kanama.types

import java.lang.foreign.Arena
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

/**
 * Task 134 (D1): every decimal component is `Double` in Kotlin, and only the engine buffer has the
 * build's `real_t` width. These pin the split: the value types keep full Double precision, the
 * boundary narrows on the way in and widens on the way out (exactly like GDScript, whose `float` is
 * 64-bit while a `Vector3` stores `real_t`), and Color always crosses as float32.
 */
class RealStorageWidthTest {
  @Test
  fun valueTypesHoldDoubleComponents() {
    val v = Vector3(0.1, 0.2, 0.3)
    assertEquals(0.1, v.x)
    assertEquals(0.30000000000000004, (v + Vector3(0.2, 0.0, 0.0)).x)
    assertEquals(0.1, Color(0.1, 0.2, 0.3).r)
    assertEquals(1.0, Color(0.1, 0.2, 0.3).a)
    // The Number overloads stay for Int/Float literals and convert once.
    assertEquals(Vector3(1.0, 2.0, 3.0), Vector3(1, 2, 3))
    assertEquals(Vector2(0.5, 0.25), Vector2(0.5f, 0.25f))
    assertEquals(Color(1.0, 0.0, 0.0, 1.0), Color(1, 0, 0))
  }

  @Test
  fun boundaryNarrowsToTheEngineWidthAndWidensBack() {
    val single = GodotReal.SIZE_BYTES == 4L
    val roundTripped = GodotReal.fromC(GodotReal.toC(0.1))
    if (single) {
      assertEquals(0.1f.toDouble(), roundTripped)
      assertNotEquals(0.1, roundTripped)
    } else {
      assertEquals(0.1, roundTripped)
    }
    // Exactly representable values survive the round trip unchanged in either width.
    assertEquals(1.5, GodotReal.fromC(GodotReal.toC(1.5)))

    Arena.ofConfined().use { arena ->
      val buf = arena.allocate(GodotReal.SIZE_BYTES * 3, GodotReal.ALIGN_BYTES)
      GodotRealSegment.writeIndex(buf, 0, 0.1)
      GodotRealSegment.writeIndex(buf, 1, -2.5)
      GodotRealSegment.writeIndex(buf, 2, 1.0e10)
      assertEquals(roundTripped, GodotRealSegment.readIndex(buf, 0))
      assertEquals(-2.5, GodotRealSegment.readIndex(buf, 1))
      assertTrue(Vector3(roundTripped, 0.0, 0.0).isEqualApprox(Vector3(0.1, 0.0, 0.0)))
    }
  }
}
