package net.multigesture.kanama.types

import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout.ADDRESS
import java.lang.foreign.ValueLayout.JAVA_BYTE
import java.lang.foreign.ValueLayout.JAVA_DOUBLE
import java.lang.foreign.ValueLayout.JAVA_INT
import java.lang.foreign.ValueLayout.JAVA_LONG
import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType
import java.util.concurrent.ConcurrentHashMap
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import net.multigesture.kanama.binding.runtime.BArg
import net.multigesture.kanama.binding.runtime.BuiltinCalls
import net.multigesture.kanama.ffi.GodotFFI

/**
 * Drives the desktop [BuiltinCalls] facade end to end — resolution through
 * `variant_get_ptr_builtin_method`, the builtin ptrcall itself, and the decode of each return width
 * — against a fake GDExtension built out of Panama upcall stubs.
 *
 * A JVM unit test has no Godot process, so the engine side is stubbed: [FakeGodot] serves the two
 * entry points the facade resolves (`string_name_new_with_utf8_chars` for the method name and
 * `variant_get_ptr_builtin_method`) and hands back one builtin method pointer per method name, each
 * reading the base and the tagged args back out of the buffers the facade laid out. What is under
 * test is therefore exactly the half that is Kanama's: the `real_t` base/return layout, the `BArg`
 * encodings and the four return decodes. The engine's own arithmetic is proved by the desktop
 * runtime smoke and the iOS device gate, not here.
 */
class BuiltinCallsFacadeTest {

  @Test
  fun valueTypeCallRoundTripsBaseArgsAndReturn() {
    FakeGodot.bootstrapOnce()

    // Shape: Vector3.lerp(to: Vector3, weight: float) -> Vector3. The fake replies with
    // base + arg0 * arg1, so every marshalled field has to arrive intact for the expected
    // value to come back — and `weight` has to arrive as the ptr-ABI's 8-byte double.
    val method = BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_VECTOR3, "lerp", LERP_HASH)
    assertTrue(method != 0L, "fake engine returned a null builtin method pointer")

    val result =
      BuiltinCalls.call(
        method,
        realsOf(1.0, 2.0, 3.0),
        3,
        listOf(BArg.Floats(BuiltinCalls.PT_VECTOR3, realsOf(10.0, 20.0, 30.0)), BArg.Real(0.5)),
      )

    assertEquals(3, result.size)
    assertEquals(6.0, doubleAt(result, 0), TOLERANCE)
    assertEquals(12.0, doubleAt(result, 1), TOLERANCE)
    assertEquals(18.0, doubleAt(result, 2), TOLERANCE)
    assertEquals(2, FakeGodot.lastArgc)
  }

  @Test
  fun noArgCallSizesTheReturnFromTheBase() {
    FakeGodot.bootstrapOnce()
    val method = BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_BASIS, "inverse", INVERSE_HASH)
    val base = GodotRealArray(9) { GodotReal.fromNumber(it + 1) }

    val result = BuiltinCalls.callNoArgsFloat32(method, base)

    assertEquals(9, result.size)
    for (i in 0 until 9) assertEquals((i + 1).toDouble(), doubleAt(result, i), TOLERANCE)
    assertEquals(0, FakeGodot.lastArgc)
  }

  @Test
  fun scalarBoolAndIntReturnsUseTheirOwnPtrAbiWidths() {
    FakeGodot.bootstrapOnce()
    val base = realsOf(2.0, 0.0, 0.0)

    // A `float` return is an 8-byte double at ptrcall, never a real_t.
    val dot = BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_VECTOR3, "dot", DOT_HASH)
    assertEquals(FakeGodot.SCALAR_REPLY, BuiltinCalls.callScalar(dot, base, emptyList()), TOLERANCE)

    // A `bool` return is one uint8 byte.
    val isNormalized =
      BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_VECTOR3, "is_normalized", IS_NORMALIZED_HASH)
    assertTrue(BuiltinCalls.callBool(isNormalized, base, emptyList()))

    // An `int` return is an int64 — and BArg.Int64 is what carries an int argument down
    // (Basis.get_euler's EulerOrder is the shared bodies' only user of it).
    val maxAxis =
      BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_VECTOR3, "max_axis_index", MAX_AXIS_HASH)
    assertEquals(FakeGodot.INT_REPLY, BuiltinCalls.callInt(maxAxis, base, listOf(BArg.Int64(7L))))
    assertEquals(7L, FakeGodot.lastInt64Arg)
  }

  @Test
  fun staticBuiltinIsCalledWithANullBaseAndABoolArgIsOneByte() {
    FakeGodot.bootstrapOnce()
    val method = BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_BASIS, "looking_at", LOOKING_AT_HASH)

    val result =
      BuiltinCalls.call(
        method,
        GodotRealArray(0),
        9,
        listOf(
          BArg.Floats(BuiltinCalls.PT_VECTOR3, realsOf(0.0, 0.0, -4.0)),
          BArg.Floats(BuiltinCalls.PT_VECTOR3, realsOf(0.0, 1.0, 0.0)),
          BArg.Bool(true),
        ),
      )

    assertEquals(9, result.size)
    assertTrue(FakeGodot.lastBaseWasNull, "a static builtin must be called with a NULL base")
    assertEquals(3, FakeGodot.lastArgc)
    assertEquals(1, FakeGodot.lastBoolArg)
    // The fake echoes arg0 into the first three return components.
    assertEquals(0.0, doubleAt(result, 0), TOLERANCE)
    assertEquals(0.0, doubleAt(result, 1), TOLERANCE)
    assertEquals(-4.0, doubleAt(result, 2), TOLERANCE)
  }

  private fun realsOf(vararg values: Double): GodotRealArray =
    GodotRealArray(values.size) { GodotReal.fromDouble(values[it]) }

  private fun doubleAt(values: GodotRealArray, index: Int): Double =
    GodotReal.toC(values[index]).toDouble()

  private companion object {
    const val TOLERANCE = 1e-6
    const val LERP_HASH = 1682608829L
    const val INVERSE_HASH = 594669093L
    const val DOT_HASH = 1047977935L
    const val IS_NORMALIZED_HASH = 3918633141L
    const val MAX_AXIS_HASH = 3173160232L
    const val LOOKING_AT_HASH = 3728732505L
  }
}

/**
 * The stub engine. `get_proc_address` and every pointer it hands back are Panama upcall stubs, so
 * the facade links and calls real native adapters — the same code path a running Godot takes.
 */
private object FakeGodot {
  const val SCALAR_REPLY = 42.5
  const val INT_REPLY = 3L

  @Volatile var lastArgc: Int = -1

  @Volatile var lastInt64Arg: Long = -1

  @Volatile var lastBoolArg: Int = -1

  @Volatile var lastBaseWasNull: Boolean = false

  // StringName storage address -> the text it was built from, so the fake
  // `variant_get_ptr_builtin_method` can tell which method is being resolved.
  private val stringNames = ConcurrentHashMap<Long, String>()
  private val methods = mutableMapOf<String, MemorySegment>()

  private val VOID_BUILTIN: FunctionDescriptor =
    FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, ADDRESS, JAVA_INT)

  private var bootstrapped = false

  @Synchronized
  fun bootstrapOnce() {
    if (bootstrapped) return
    bootstrapped = true
    val arena = Arena.ofShared()

    methods["lerp"] = stub(arena, "valueTypeMethod", VOID_BUILTIN)
    methods["looking_at"] = stub(arena, "valueTypeMethod", VOID_BUILTIN)
    methods["inverse"] = stub(arena, "copyBaseMethod", VOID_BUILTIN)
    methods["dot"] = stub(arena, "scalarMethod", VOID_BUILTIN)
    methods["is_normalized"] = stub(arena, "boolMethod", VOID_BUILTIN)
    methods["max_axis_index"] = stub(arena, "intMethod", VOID_BUILTIN)
    stringNameNew = stub(arena, "stringNameNew", FunctionDescriptor.ofVoid(ADDRESS, ADDRESS))
    getPtrBuiltinMethod =
      stub(
        arena,
        "getPtrBuiltinMethod",
        FunctionDescriptor.of(ADDRESS, JAVA_INT, ADDRESS, JAVA_LONG),
      )
    val procAddress = stub(arena, "getProcAddress", FunctionDescriptor.of(ADDRESS, ADDRESS))
    GodotFFI.bootstrap(procAddress.address())
  }

  private lateinit var stringNameNew: MemorySegment
  private lateinit var getPtrBuiltinMethod: MemorySegment

  private fun stub(arena: Arena, name: String, descriptor: FunctionDescriptor): MemorySegment {
    val lookup = MethodHandles.lookup()
    val carriers =
      descriptor.argumentLayouts().map {
        when (it) {
          ADDRESS -> MemorySegment::class.java
          JAVA_INT -> Integer.TYPE
          JAVA_LONG -> java.lang.Long.TYPE
          else -> error("unsupported fake-engine layout $it")
        }
      }
    val returns: Class<*> =
      if (descriptor.returnLayout().isPresent) MemorySegment::class.java else Void.TYPE
    val target = lookup.bind(this, name, MethodType.methodType(returns, carriers))
    return GodotFFI.linker.upcallStub(target, descriptor, arena)
  }

  @Suppress("unused")
  fun getProcAddress(name: MemorySegment): MemorySegment =
    when (val text = name.reinterpret(MAX_C_STRING).getString(0)) {
      "string_name_new_with_utf8_chars" -> stringNameNew
      "variant_get_ptr_builtin_method" -> getPtrBuiltinMethod
      else -> error("fake engine has no entry point '$text'")
    }

  /** A StringName is an opaque 8-byte cell; remember the text it was built from. */
  @Suppress("unused")
  fun stringNameNew(storage: MemorySegment, text: MemorySegment) {
    val cell = storage.reinterpret(STRING_NAME_SIZE)
    cell.set(JAVA_LONG, 0, cell.address())
    stringNames[cell.address()] = text.reinterpret(MAX_C_STRING).getString(0)
  }

  @Suppress("unused", "UNUSED_PARAMETER")
  fun getPtrBuiltinMethod(variantType: Int, name: MemorySegment, hash: Long): MemorySegment {
    val text =
      checkNotNull(stringNames[name.reinterpret(STRING_NAME_SIZE).get(JAVA_LONG, 0)]) {
        "builtin method resolved with a StringName the fake engine never built"
      }
    return checkNotNull(methods[text]) { "fake engine has no builtin method '$text'" }
  }

  /**
   * `Vector3.lerp`-shaped: reply with `base + arg0 * arg1`, or — when the base is NULL, i.e. a
   * static builtin like `Basis.looking_at` — with arg0 padded out to nine components.
   */
  @Suppress("unused")
  fun valueTypeMethod(base: MemorySegment, args: MemorySegment, ret: MemorySegment, argc: Int) {
    record(base, args, argc)
    val first = argAt(args, 0).reinterpret(GodotReal.SIZE_BYTES * 3)
    if (lastBaseWasNull) {
      val out = ret.reinterpret(GodotReal.SIZE_BYTES * 9)
      for (i in 0 until 9) {
        val value =
          if (i < 3) GodotRealSegment.readIndex(first, i.toLong()) else GodotReal.fromDouble(0.0)
        GodotRealSegment.writeIndex(out, i.toLong(), value)
      }
      return
    }
    val baseBuf = base.reinterpret(GodotReal.SIZE_BYTES * 3)
    val weight = argAt(args, 1).reinterpret(JAVA_DOUBLE.byteSize()).get(JAVA_DOUBLE, 0)
    val out = ret.reinterpret(GodotReal.SIZE_BYTES * 3)
    for (i in 0 until 3) {
      val start = GodotReal.toC(GodotRealSegment.readIndex(baseBuf, i.toLong())).toDouble()
      val delta = GodotReal.toC(GodotRealSegment.readIndex(first, i.toLong())).toDouble()
      GodotRealSegment.writeIndex(out, i.toLong(), GodotReal.fromDouble(start + delta * weight))
    }
  }

  /** No-arg value-type method: echo the 9-component base back. */
  @Suppress("unused")
  fun copyBaseMethod(base: MemorySegment, args: MemorySegment, ret: MemorySegment, argc: Int) {
    record(base, args, argc)
    val baseBuf = base.reinterpret(GodotReal.SIZE_BYTES * 9)
    val out = ret.reinterpret(GodotReal.SIZE_BYTES * 9)
    for (i in 0 until 9) GodotRealSegment.writeIndex(
      out,
      i.toLong(),
      GodotRealSegment.readIndex(baseBuf, i.toLong()),
    )
  }

  @Suppress("unused")
  fun scalarMethod(base: MemorySegment, args: MemorySegment, ret: MemorySegment, argc: Int) {
    record(base, args, argc)
    ret.reinterpret(JAVA_DOUBLE.byteSize()).set(JAVA_DOUBLE, 0, SCALAR_REPLY)
  }

  @Suppress("unused")
  fun boolMethod(base: MemorySegment, args: MemorySegment, ret: MemorySegment, argc: Int) {
    record(base, args, argc)
    ret.reinterpret(JAVA_BYTE.byteSize()).set(JAVA_BYTE, 0, 1)
  }

  @Suppress("unused")
  fun intMethod(base: MemorySegment, args: MemorySegment, ret: MemorySegment, argc: Int) {
    record(base, args, argc)
    if (argc > 0) {
      lastInt64Arg = argAt(args, 0).reinterpret(JAVA_LONG.byteSize()).get(JAVA_LONG, 0)
    }
    ret.reinterpret(JAVA_LONG.byteSize()).set(JAVA_LONG, 0, INT_REPLY)
  }

  private fun record(base: MemorySegment, args: MemorySegment, argc: Int) {
    lastArgc = argc
    lastBaseWasNull = base.address() == 0L
    lastInt64Arg = -1
    lastBoolArg = -1
    if (argc == 3) {
      lastBoolArg = argAt(args, 2).reinterpret(JAVA_BYTE.byteSize()).get(JAVA_BYTE, 0).toInt()
    }
  }

  private fun argAt(args: MemorySegment, index: Int): MemorySegment =
    args.reinterpret(ADDRESS.byteSize() * (index + 1)).getAtIndex(ADDRESS, index.toLong())

  private const val STRING_NAME_SIZE = 8L
  private const val MAX_C_STRING = 256L
}
