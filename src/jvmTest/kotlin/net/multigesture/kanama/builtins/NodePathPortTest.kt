package net.multigesture.kanama.builtins

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue
import net.multigesture.kanama.types.NodePath

/**
 * Task 134 D2 review: NodePath's members are Godot's `NodePath(String)` parse ported to Kotlin
 * (node_path.cpp). These are the cases that print no error (the error paths call GD.pushError, so
 * the runtime smoke's `text=` row compares them, and every other case, with GDScript).
 */
class NodePathPortTest {
  @Test
  fun parsesNamesSubnamesAndAbsoluteLikeGodot() {
    val p = NodePath("/root/Arm//Hand/:position:x")
    assertTrue(p.isAbsolute())
    assertEquals(3L, p.getNameCount())
    assertEquals("Hand", p.getName(2))
    assertEquals(2L, p.getSubnameCount())
    assertEquals("x", p.getSubname(1))
    assertEquals("root/Arm/Hand", p.getConcatenatedNames())
    assertEquals("position:x", p.getConcatenatedSubnames())
  }

  @Test
  fun emptyAndEdgeTexts() {
    assertNull(parseNodePath(""))
    assertNull(parseNodePath(":")) // a lone end-of-path `:` is allowed and leaves nothing
    assertTrue(NodePath("").isEmpty())
    assertFalse(NodePath("/").isEmpty())
    assertTrue(NodePath("/").isAbsolute())
    assertEquals(0L, NodePath("/").getNameCount())
    assertEquals(listOf("a"), parseNodePath("a:")!!.names) // trailing `:` allowed
    assertEquals(listOf("b"), parseNodePath(":b")!!.subnames)
    assertEquals(listOf("a"), parseNodePath("a\u0000b")!!.names) // the text ends at a NUL
  }

  @Test
  fun sliceAndPropertyPathFollowGodot() {
    val p = NodePath("/Arm/Hand:position:x")
    assertEquals(NodePath("/Arm"), p.slice(0, 1))
    assertEquals(NodePath("Hand:position"), p.slice(1, 3))
    assertEquals(NodePath(":x"), p.slice(-1))
    assertEquals(NodePath(""), p.slice(4, 4))
    assertEquals(NodePath(":Arm/Hand:position:x"), p.getAsPropertyPath())
    assertEquals(NodePath(":a"), NodePath(":a").getAsPropertyPath())
  }
}
