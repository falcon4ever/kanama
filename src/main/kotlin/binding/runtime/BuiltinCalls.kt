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

/**
 * Desktop/Android implementation of value-type (builtin) method calls — the one facade the shared
 * value-type bodies under `src/commonMain/kotlin/net/multigesture/kanama/types/` call, so that
 * `Transform3D.inverse()` and friends have ONE body for every backend (task 104 step 2).
 *
 * The public surface of this object is the contract: the iOS object at
 * `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/binding/runtime/BuiltinCalls.kt` declares
 * exactly the same members over its C shim. The compiler cannot prove that until the root becomes a
 * multiplatform module and this pair turns into an `expect object` (step 3), so
 * `scripts/check_builtin_calls_contract.py` proves it in the meantime.
 *
 * Here the implementation is the FFM one: `variant_get_ptr_builtin_method` resolves the method once
 * (cached like a MethodBind, keyed by the pointer the resolution returned), and the builtin ptrcall
 * `method(base, args, ret, argc)` runs over Arena-allocated `real_t` buffers. Method pointers
 * travel as `Long` on both platforms, so a shared body never names a platform pointer type.
 */
object BuiltinCalls {
  // Godot Variant type ids (Variant::Type) — must match the engine enum, and the [VariantType]
  // entries of the same name.
  const val VT_VECTOR2 = 5
  const val VT_VECTOR3 = 9
  const val VT_QUATERNION = 15
  const val VT_BASIS = 17
  const val VT_TRANSFORM3D = 18

  // Builtin arg tags. The desktop ptr-ABI is positional and untyped — the callee knows the layout —
  // so these are carried for the shared call sites and the iOS shim, which dispatches on them.
  const val PT_BOOL = 1
  const val PT_INT64 = 3
  const val PT_FLOAT64 = 5
  const val PT_VECTOR2 = 6
  const val PT_VECTOR3 = 8
  const val PT_TRANSFORM3D = 19
  const val PT_QUATERNION = 20

  /** An argument to a builtin method call (the value-type analogue of a ptrcall arg). */
  sealed interface BArg {
    /**
     * A struct value laid out as `real_t` [values] (Vector3/Basis/Transform3D/…); [tag] is its
     * PT_*.
     */
    data class Floats(val tag: Int, val values: GodotRealArray) : BArg

    /** A `bool` arg (1-byte). */
    data class Bool(val value: Boolean) : BArg

    /**
     * A scalar `float`/`double` arg. Godot's ptr-ABI encodes a Variant FLOAT argument as an 8-byte
     * `double` regardless of the engine's real_t precision, so this is never a real_t.
     */
    data class Real(val value: Double) : BArg

    /** An `int` arg (`int64_t` at ptrcall) — Basis' EulerOrder, array indices, … */
    data class Int64(val value: Long) : BArg
  }

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
  fun getBuiltinMethod(variantType: Int, method: String, hash: Long): Long {
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
        for (i in base.indices) GodotReal.writeIndex(buf, i.toLong(), base[i])
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
        for (i in arg.values.indices) GodotReal.writeIndex(buf, i.toLong(), arg.values[i])
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
  fun call(
    methodPtr: Long,
    base: GodotRealArray,
    retCount: Int,
    args: List<BArg> = emptyList(),
  ): GodotRealArray =
    Arena.ofConfined().use { arena ->
      val size = if (retCount > 0) retCount else 1
      val ret = arena.allocate(GodotReal.SIZE_BYTES * size, GodotReal.ALIGN_BYTES)
      invokeBuiltin(arena, methodPtr, base, args, ret)
      GodotRealArray(retCount) { GodotReal.readIndex(ret, it.toLong()) }
    }

  /**
   * No-arg builtin method whose base and return are the same value type laid out as `real_t`
   * components (inverse / transposed / orthonormalized / …).
   */
  fun callNoArgsFloat32(methodPtr: Long, base: GodotRealArray): GodotRealArray =
    call(methodPtr, base, base.size, emptyList())

  /**
   * Builtin method returning a scalar `float` (dot / length / determinant / …). Godot's GDExtension
   * ptr-ABI encodes a `float`-typed (Variant FLOAT) return as an 8-byte `double` regardless of the
   * engine's real_t precision — NOT a real_t, unlike value-type *components*.
   */
  fun callScalar(methodPtr: Long, base: GodotRealArray, args: List<BArg> = emptyList()): Double =
    Arena.ofConfined().use { arena ->
      val ret = arena.allocate(JAVA_DOUBLE)
      invokeBuiltin(arena, methodPtr, base, args, ret)
      ret.get(JAVA_DOUBLE, 0)
    }

  /**
   * Builtin method returning a `bool` (is_normalized / is_finite / …). Godot's ptr-ABI encodes a
   * bool return as a single `uint8_t` (`PtrToArg<bool>` = uint8), so decode one byte (≠ 0 → true).
   */
  fun callBool(methodPtr: Long, base: GodotRealArray, args: List<BArg> = emptyList()): Boolean =
    Arena.ofConfined().use { arena ->
      val ret = arena.allocate(JAVA_BYTE)
      invokeBuiltin(arena, methodPtr, base, args, ret)
      ret.get(JAVA_BYTE, 0).toInt() != 0
    }

  /**
   * Builtin method returning an `int` (max_axis_index / …). Godot's ptr-ABI encodes an int return
   * as `int64_t` (`PtrToArg<int64_t>` is direct 8-byte), so decode a Long.
   */
  fun callInt(methodPtr: Long, base: GodotRealArray, args: List<BArg> = emptyList()): Long =
    Arena.ofConfined().use { arena ->
      val ret = arena.allocate(JAVA_LONG)
      invokeBuiltin(arena, methodPtr, base, args, ret)
      ret.get(JAVA_LONG, 0)
    }
}
