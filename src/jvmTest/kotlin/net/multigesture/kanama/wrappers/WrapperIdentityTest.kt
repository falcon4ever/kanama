package net.multigesture.kanama.wrappers

import java.lang.foreign.MemorySegment
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.api.Node3D
import net.multigesture.kanama.binding.runtime.BuiltinTypes
import net.multigesture.kanama.binding.runtime.FreedObjectChecks
import net.multigesture.kanama.binding.runtime.ObjectRuntime
import net.multigesture.kanama.binding.runtime.ScriptErrors
import net.multigesture.kanama.binding.runtime.ScriptOwnerIds

/**
 * Task 131 items 6 (F23) and 2 (F2), without an engine: the instance-id capture and the liveness
 * question go to the `ObjectRuntime` test seams. The engine-backed versions are the runtime smoke's
 * `freed_object_smoke.tscn` and the iOS self-test rows.
 */
class WrapperIdentityTest {
  /** Fake engine: an object's instance id is its address + 1000; [freed] objects are dead. */
  private val freed = mutableSetOf<Long>()

  @BeforeTest
  fun installSeams() {
    ObjectRuntime.instanceIdOverride = { it.address() + 1000 }
    ObjectRuntime.isLiveOverride = { segment, id ->
      segment.address() !in freed && id == segment.address() + 1000
    }
  }

  @AfterTest
  fun clearSeams() {
    ObjectRuntime.instanceIdOverride = null
    ObjectRuntime.isLiveOverride = null
    FreedObjectChecks.enabled = false
    ScriptErrors.sinkOverride = null
  }

  private fun handle(address: Long) = GodotHandle(MemorySegment.ofAddress(address))

  @Test
  fun twoWrappersOfOneObjectAreEqualWhateverTheirClass() {
    val asNode3D = Node3D(handle(0x1000))
    val asNode = Node(handle(0x1000))
    val asObject = GodotObject(handle(0x1000))

    assertEquals<GodotObject>(asNode3D, asNode)
    assertEquals<GodotObject>(asNode, asNode3D)
    assertEquals<GodotObject>(asObject, asNode3D)
    assertEquals(asNode3D.hashCode(), asNode.hashCode())
    assertEquals(1, setOf<GodotObject>(asNode3D, asNode, asObject).size)
    assertTrue(listOf<GodotObject>(asNode).contains(asNode3D))
    assertEquals("found", mapOf<GodotObject, String>(asNode to "found")[asNode3D])
  }

  @Test
  fun wrappersOfTwoObjectsAreNotEqual() {
    val first = Node(handle(0x1000))
    val second = Node(handle(0x2000))

    assertNotEquals(first, second)
    assertFalse(first.isSameInstance(second))
    assertEquals(2, setOf(first, second).size)
  }

  @Test
  fun equalityNeverAsksTheEngineSoItHoldsAfterTheObjectIsFreed() {
    val node = Node(handle(0x1000))
    val view = Node3D(handle(0x1000))
    freed += 0x1000
    FreedObjectChecks.enabled = true
    ObjectRuntime.isLiveOverride = { _, _ -> error("equals/hashCode must not ask the engine") }

    assertEquals<GodotObject>(node, view)
    assertEquals(node.hashCode(), view.hashCode())
  }

  @Test
  fun aCallThroughAFreedWrapperThrowsWhileTheCheckIsOn() {
    val node = Node3D(handle(0x1000))
    FreedObjectChecks.enabled = true
    node.segment // alive: no throw

    freed += 0x1000
    val thrown = assertFailsWith<IllegalStateException> { node.segment }
    assertEquals(
      "Invalid access to previously freed instance (Node3D, instance id ${0x1000 + 1000})",
      thrown.message,
    )
    assertEquals("<Freed Object>", node.toString())
  }

  @Test
  fun aFreedWrapperEncodesAsNilSilently() {
    val node = Node3D(handle(0x1000))
    FreedObjectChecks.enabled = true
    assertEquals(0x1000L, FreedObjectChecks.valueSegment(node).address())

    freed += 0x1000
    // GDScript semantics: holding / returning a freed object is nil, not an error.
    assertEquals(0L, FreedObjectChecks.valueSegment(node).address())
    assertEquals(0L, BuiltinTypes.objectValueSegment(node).address())
  }

  /** A plain `@ScriptClass` shape: no KanamaScript base, so no `self`. */
  private class PlainScript(val godotObject: GodotHandle)

  /** A KanamaScript shape: `self` is the wrapper captured at script creation. */
  private class SelfScript(godotObject: GodotHandle) :
    net.multigesture.kanama.api.KanamaScript<Node>(godotObject, ::Node)

  @Test
  fun aCustomScriptValueOfAFreedOwnerIsNilWithoutReadingTheOwner() {
    FreedObjectChecks.enabled = true
    // The fake engine faults on any read of a freed owner (what object_get_instance_id on freed
    // memory is): the old generated shape `GodotObject(it.godotObject)` trips it.
    ObjectRuntime.instanceIdOverride = { segment ->
      check(segment.address() !in freed) { "read the freed owner" }
      segment.address() + 1000
    }
    val plain = PlainScript(handle(0x5000))
    ScriptOwnerIds.remember(plain, 0x5000L + 1000)
    val withSelf = SelfScript(handle(0x6000))
    assertEquals(
      0x5000L,
      BuiltinTypes.scriptValue(plain, plain.godotObject)!!.handle.segment.address(),
    )
    assertTrue(BuiltinTypes.scriptValue(withSelf, withSelf.godotObject) === withSelf.self)

    freed += 0x5000
    freed += 0x6000
    assertEquals(null, BuiltinTypes.scriptValue(plain, plain.godotObject))
    assertEquals(null, BuiltinTypes.scriptValue(withSelf, withSelf.godotObject))
    // The red shape, for comparison: building a wrapper over the freed owner reads it.
    assertFailsWith<IllegalStateException> { GodotObject(plain.godotObject) }
  }

  @Test
  fun isSameInstanceAgreesWithEquals() {
    val node = Node(handle(0x1000))
    val view = Node3D(handle(0x1000))
    val other = Node(handle(0x2000))

    assertTrue(node.isSameInstance(view))
    assertFalse(node.isSameInstance(other))
    freed += 0x1000
    assertTrue(node.isSameInstance(view), "identity survives the free, like equals")
  }

  @Test
  fun instanceIdsArePrintedUnsignedLikeGodot() {
    val thrown = FreedObjectChecks.freedInstance("ScriptBacktrace", -9223372002478258583L)
    assertEquals(
      "Invalid access to previously freed instance (ScriptBacktrace, instance id " +
        "9223372071231293033)",
      thrown.message,
    )
  }

  @Test
  fun theCheckIsOffUnlessEnabled() {
    val node = Node(handle(0x1000))
    freed += 0x1000
    FreedObjectChecks.enabled = false

    assertEquals(0x1000L, node.segment.address()) // release build: unchecked, as before
  }

  @Test
  fun theFreedObjectErrorIsReportedAtTheGameLine() {
    val node = Node(handle(0x1000))
    FreedObjectChecks.enabled = true
    freed += 0x1000
    val thrown = runCatching { gameCallOnFreedNode(node) }.exceptionOrNull()!!

    val report = ScriptErrors.reportFor(thrown, "Player._process")
    assertEquals(
      "java.lang.IllegalStateException: Invalid access to previously freed instance " +
        "(Node, instance id ${0x1000 + 1000})",
      report.message,
    )
    assertEquals("WrapperIdentityTest.gameCallOnFreedNode", report.function)
    assertEquals("WrapperIdentityTest.kt", report.file)
    assertEquals(GAME_CALL_LINE, report.line)
  }

  // This package is outside Kanama's runtime packages, so this frame is "game" code.
  private fun gameCallOnFreedNode(node: Node) {
    node.segment // the line every generated wrapper call reads first
  }

  @Test
  fun configureFollowsTheBuildTypeUnlessTheEnvironmentOverrides() {
    assertTrue(FreedObjectChecks.decide("") { true })
    assertFalse(FreedObjectChecks.decide("") { false })
    assertTrue(FreedObjectChecks.decide("1") { false })
    assertTrue(FreedObjectChecks.decide(" ON ") { false })
    assertFalse(FreedObjectChecks.decide("0") { true })
    assertFalse(FreedObjectChecks.decide("off") { true })
    assertTrue(FreedObjectChecks.decide("maybe") { true })
  }

  private companion object {
    /** The line of `node.segment` in [gameCallOnFreedNode] (Gradle runs tests from the root). */
    val GAME_CALL_LINE: Int =
      java.io
        .File("src/jvmTest/kotlin/net/multigesture/kanama/wrappers/WrapperIdentityTest.kt")
        .readLines()
        .indexOfFirst { it.contains("node.segment // the line every generated") } + 1
  }
}
