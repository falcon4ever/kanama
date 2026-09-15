package net.multigesture.kanama.binding.runtime

import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout.ADDRESS
import java.lang.foreign.ValueLayout.JAVA_BYTE
import java.lang.foreign.ValueLayout.JAVA_DOUBLE
import java.lang.foreign.ValueLayout.JAVA_INT
import java.lang.foreign.ValueLayout.JAVA_LONG
import java.lang.invoke.MethodHandle
import java.util.concurrent.ConcurrentHashMap
import net.multigesture.kanama.ffi.GodotFFI
import net.multigesture.kanama.types.GodotReal
import net.multigesture.kanama.types.GodotRealArray
import net.multigesture.kanama.types.GodotRealSegment

/**
 * Desktop/Android implementation of value-type (builtin) method calls — the `actual` of the `expect
 * object BuiltinCalls` in
 * `src/commonMain/kotlin/net/multigesture/kanama/binding/runtime/BuiltinCalls.expect.kt`, which is
 * where the contract and its KDoc live. The compiler holds this object and the iOS one at
 * `src/iosMain/kotlin/net/multigesture/kanama/binding/runtime/BuiltinCalls.kt` to the same member
 * set and the same parameter names (task 104 step 3 parcel C'; it replaced the step-2 Python gate).
 *
 * Here the implementation is the FFM one: `variant_get_ptr_builtin_method` resolves the method once
 * (cached like a MethodBind, keyed by the pointer the resolution returned), and the builtin ptrcall
 * `method(base, args, ret, argc)` runs over Arena-allocated `real_t` buffers. Method pointers
 * travel as `Long` on both platforms, so a shared body never names a platform pointer type.
 */
actual object BuiltinCalls {
  // Godot Variant type ids (Variant::Type) — must match the engine enum, and the [VariantType]
  // entries of the same name.
  actual const val VT_VECTOR2 = 5
  actual const val VT_VECTOR3 = 9
  actual const val VT_QUATERNION = 15
  actual const val VT_BASIS = 17
  actual const val VT_TRANSFORM3D = 18

  // Builtin arg tags. The desktop ptr-ABI is positional and untyped — the callee knows the layout —
  // so these are carried for the shared call sites and the iOS shim, which dispatches on them.
  actual const val PT_BOOL = 1
  actual const val PT_INT64 = 3
  actual const val PT_FLOAT64 = 5
  actual const val PT_VECTOR2 = 6
  actual const val PT_VECTOR3 = 8
  actual const val PT_TRANSFORM3D = 19
  actual const val PT_QUATERNION = 20

  private val builtinMethodDescriptor: FunctionDescriptor =
    FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, ADDRESS, JAVA_INT)

  private val getPtrBuiltinMethod by lazy {
    GodotFFI.lookup(
      "variant_get_ptr_builtin_method",
      FunctionDescriptor.of(ADDRESS, JAVA_INT, ADDRESS, JAVA_LONG),
    )
  }

  // Resolved builtin method pointer -> its bound downcall handle. Populated by
  // [getBuiltinMethod], which is the only way a caller can obtain a pointer, so the
  // raw `Long` never has to be turned back into a MemorySegment here (GodotFFI stays
  // the only place that maps a raw address).
  private val handles = ConcurrentHashMap<Long, MethodHandle>()

  /**
   * Resolve a builtin method of [variantType] by [method] name and signature [hash], as an opaque
   * pointer. Cache the result in a `by lazy` on the value type — resolution costs a StringName and
   * an engine lookup.
   */
  actual fun getBuiltinMethod(variantType: Int, method: String, hash: Long): Long {
    val fn =
      getPtrBuiltinMethod.invoke(variantType, GodotStrings.makeStringName(method), hash)
        as MemorySegment
    check(fn.address() != 0L) {
      "variant_get_ptr_builtin_method($variantType, $method, $hash) returned NULL"
    }
    val address = fn.address()
    handles.computeIfAbsent(address) {
      GodotFFI.downcallHandle(fn, builtinMethodDescriptor, "variant_ptr_builtin_method")
    }
    return address
  }

  private fun handleFor(methodPtr: Long): MethodHandle =
    checkNotNull(handles[methodPtr]) {
      "builtin method pointer $methodPtr was not resolved through BuiltinCalls.getBuiltinMethod"
    }

  // Marshal [base] (real_t components) + [args] (PT-tagged), then invoke the builtin method
  // writing into the caller-supplied [ret] buffer. The ret encoding is the caller's choice
  // (real_t components for a value-type return; an 8-byte double for a scalar `float` return —
  // see [callScalar]). A static builtin (Basis.looking_at, Quaternion.from_euler) is called with
  // an empty [base], which is Godot's NULL instance.
  private fun invokeBuiltin(
    arena: Arena,
    methodPtr: Long,
    base: GodotRealArray,
    args: List<BArg>,
    ret: MemorySegment,
  ) {
    val baseBuf =
      if (base.isEmpty()) {
        MemorySegment.NULL
      } else {
        val buf = arena.allocate(GodotReal.SIZE_BYTES * base.size, GodotReal.ALIGN_BYTES)
        for (i in base.indices) GodotRealSegment.writeIndex(buf, i.toLong(), base[i])
        buf
      }
    val argArray =
      if (args.isEmpty()) {
        MemorySegment.NULL
      } else {
        val arr = arena.allocate(ADDRESS, args.size.toLong())
        args.forEachIndexed { i, arg -> arr.setAtIndex(ADDRESS, i.toLong(), allocArg(arena, arg)) }
        arr
      }
    handleFor(methodPtr).invoke(baseBuf, argArray, ret, args.size)
  }

  private fun allocArg(arena: Arena, arg: BArg): MemorySegment =
    when (arg) {
      is BArg.Floats -> {
        val size = if (arg.values.isNotEmpty()) arg.values.size else 1
        val buf = arena.allocate(GodotReal.SIZE_BYTES * size, GodotReal.ALIGN_BYTES)
        for (i in arg.values.indices) GodotRealSegment.writeIndex(buf, i.toLong(), arg.values[i])
        buf
      }
      is BArg.Bool -> {
        val buf = arena.allocate(JAVA_BYTE)
        buf.set(JAVA_BYTE, 0, if (arg.value) 1.toByte() else 0.toByte())
        buf
      }
      is BArg.Real -> {
        val buf = arena.allocate(JAVA_DOUBLE)
        buf.set(JAVA_DOUBLE, 0, arg.value)
        buf
      }
      is BArg.Int64 -> {
        val buf = arena.allocate(JAVA_LONG)
        buf.set(JAVA_LONG, 0, arg.value)
        buf
      }
    }

  /**
   * Call a builtin method whose base and return are value types laid out as `real_t` components
   * ([base] in, [retCount] values out), with optional [args].
   */
  actual fun call(
    methodPtr: Long,
    base: GodotRealArray,
    retCount: Int,
    args: List<BArg>,
  ): GodotRealArray =
    Arena.ofConfined().use { arena ->
      val size = if (retCount > 0) retCount else 1
      val ret = arena.allocate(GodotReal.SIZE_BYTES * size, GodotReal.ALIGN_BYTES)
      invokeBuiltin(arena, methodPtr, base, args, ret)
      GodotRealArray(retCount) { GodotRealSegment.readIndex(ret, it.toLong()) }
    }

  /**
   * No-arg builtin method whose base and return are the same value type laid out as `real_t`
   * components (inverse / transposed / orthonormalized / …).
   */
  actual fun callNoArgsFloat32(methodPtr: Long, base: GodotRealArray): GodotRealArray =
    call(methodPtr, base, base.size, emptyList())

  /**
   * Builtin method returning a scalar `float` (dot / length / determinant / …). Godot's GDExtension
   * ptr-ABI encodes a `float`-typed (Variant FLOAT) return as an 8-byte `double` regardless of the
   * engine's real_t precision — NOT a real_t, unlike value-type *components*.
   */
  actual fun callScalar(methodPtr: Long, base: GodotRealArray, args: List<BArg>): Double =
    Arena.ofConfined().use { arena ->
      val ret = arena.allocate(JAVA_DOUBLE)
      invokeBuiltin(arena, methodPtr, base, args, ret)
      ret.get(JAVA_DOUBLE, 0)
    }

  /**
   * Builtin method returning a `bool` (is_normalized / is_finite / …). Godot's ptr-ABI encodes a
   * bool return as a single `uint8_t` (`PtrToArg<bool>` = uint8), so decode one byte (≠ 0 → true).
   */
  actual fun callBool(methodPtr: Long, base: GodotRealArray, args: List<BArg>): Boolean =
    Arena.ofConfined().use { arena ->
      val ret = arena.allocate(JAVA_BYTE)
      invokeBuiltin(arena, methodPtr, base, args, ret)
      ret.get(JAVA_BYTE, 0).toInt() != 0
    }

  /**
   * Builtin method returning an `int` (max_axis_index / …). Godot's ptr-ABI encodes an int return
   * as `int64_t` (`PtrToArg<int64_t>` is direct 8-byte), so decode a Long.
   */
  actual fun callInt(methodPtr: Long, base: GodotRealArray, args: List<BArg>): Long =
    Arena.ofConfined().use { arena ->
      val ret = arena.allocate(JAVA_LONG)
      invokeBuiltin(arena, methodPtr, base, args, ret)
      ret.get(JAVA_LONG, 0)
    }
}
