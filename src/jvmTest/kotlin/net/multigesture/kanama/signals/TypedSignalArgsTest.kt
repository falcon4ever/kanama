package net.multigesture.kanama.signals

import java.lang.foreign.MemorySegment
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull
import kotlin.test.assertSame
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.ListSignalArgReader
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.api.SignalArgReader
import net.multigesture.kanama.api.SignalArgType
import net.multigesture.kanama.api.SignalArgumentException
import net.multigesture.kanama.api.SignalObjectWrapper
import net.multigesture.kanama.binding.runtime.SignalCallables
import net.multigesture.kanama.binding.runtime.SignalCallbackRegistry
import net.multigesture.kanama.types.Vector2

/**
 * Task 134 D4: how a typed signal reads each emitted argument ([SignalArgType] over a
 * [net.multigesture.kanama.api.SignalArgReader]) and how a connection's closure is released,
 * without an engine. The engine-side rows (connect, one-shot, deferred, await, freed receiver, emit
 * seen by GDScript) are `typed_signal_smoke.tscn` in the runtime smoke.
 */
class TypedSignalArgsTest {
  private val registered = mutableListOf<Long>()

  @AfterTest
  fun cleanUp() {
    registered.forEach(SignalCallbackRegistry::unregister)
  }

  private fun handle(address: Long) = GodotHandle(MemorySegment.ofAddress(address))

  @Test
  fun scalarsReadAsTheirGodotTypes() {
    val args = ListSignalArgReader(listOf(7L, 2.5, true, "name", 3L))
    assertEquals(7L, SignalArgType.LONG.read(args, 0))
    assertEquals(2.5, SignalArgType.DOUBLE.read(args, 1))
    assertEquals(true, SignalArgType.BOOLEAN.read(args, 2))
    assertEquals("name", SignalArgType.STRING.read(args, 3))
    // A Godot int widens to a typed float, as for a GDScript `float` parameter.
    assertEquals(3.0, SignalArgType.DOUBLE.read(args, 4))
  }

  @Test
  fun aWrongTypeOrAMissingArgumentThrowsSignalArgumentException() {
    val args = ListSignalArgReader(listOf("not an int", 5L))
    val wrong = assertFailsWith<SignalArgumentException> { SignalArgType.LONG.read(args, 0) }
    assertEquals("argument 1: expected int, got String", wrong.message)
    val value =
      assertFailsWith<SignalArgumentException> {
        SignalArgType.valueOf<Vector2>("Vector2", Vector2::class).read(args, 1)
      }
    assertEquals("argument 2: expected Vector2, got Long", value.message)
    assertFailsWith<SignalArgumentException> { SignalArgType.STRING.read(args, 2) }
  }

  /**
   * Object arguments by handle, as the platform readers return them (a wrapper needs an engine).
   */
  private class HandleReader(private val handles: List<GodotHandle?>) : SignalArgReader {
    override val count: Int
      get() = handles.size

    override fun long(index: Int): Long = error("unused")

    override fun double(index: Int): Double = error("unused")

    override fun bool(index: Int): Boolean = error("unused")

    override fun string(index: Int): String = error("unused")

    override fun objectHandle(index: Int): GodotHandle? = handles[index]

    override fun value(index: Int): Any? = error("unused")
  }

  @Test
  fun objectsAreWrappedAndNullIsOnlyAcceptedWhereDeclared() {
    val args = HandleReader(listOf(handle(0x1000), null))
    val wrap = SignalObjectWrapper { "wrapped@" + it.segment.address().toString(16) }
    assertEquals("wrapped@1000", SignalArgType.objectOf("Node2D", wrap).read(args, 0))
    val missing =
      assertFailsWith<SignalArgumentException> {
        SignalArgType.objectOf("Node", wrap).read(args, 1)
      }
    assertEquals("argument 2: expected Node, got null", missing.message)
    assertNull(SignalArgType.nullableObjectOf("Resource", wrap).read(args, 1))
  }

  @Test
  fun enumsWrapTheIntAndEmitTheirRawValue() {
    val mode = SignalArgType.enumOf("Node.ProcessMode", { Node.ProcessMode(it) }, { it.value })
    assertEquals(Node.ProcessMode.ALWAYS, mode.read(ListSignalArgReader(listOf(3L)), 0))
    assertEquals(3L, mode.write(Node.ProcessMode.ALWAYS))
  }

  @Test
  fun valueTypesAndVariantsPassThrough() {
    val v = Vector2(1.5, -2.0)
    val list = listOf("a", "b")
    val args = ListSignalArgReader(listOf(v, list, null))
    assertEquals(v, SignalArgType.valueOf<Vector2>("Vector2", Vector2::class).read(args, 0))
    assertSame(
      list,
      SignalArgType.valueOf<List<String>>("PackedStringArray", List::class).read(args, 1),
    )
    assertNull(SignalArgType.VARIANT.read(args, 2))
  }

  @Test
  fun freeFuncRunsTheReleaseHookAndCloseDoesNot() {
    var released = 0
    val freed = SignalCallbackRegistry.register(0, { released++ }) {}.also { registered += it }
    SignalCallables.free(MemorySegment.ofAddress(freed)) // Godot dropped the Callable
    assertEquals(1, released)

    val closed = SignalCallbackRegistry.register(0, { released++ }) {}.also { registered += it }
    SignalCallbackRegistry.unregister(closed) // SignalConnection.close()
    SignalCallables.free(MemorySegment.ofAddress(closed)) // the disconnect's own free_func
    assertEquals(1, released)
  }

  @Test
  fun theListFormSeesEveryDeclaredArgument() {
    var seen: List<Any?> = emptyList()
    val id = SignalCallbackRegistry.register(2) { seen = it }.also { registered += it }
    SignalCallbackRegistry.invoke(id, listOf(1L, "two", 3.0)) // a pre-131 registrar's dispatch
    assertEquals(listOf<Any?>(1L, "two"), seen)
  }
}
