@file:OptIn(ExperimentalForeignApi::class)

package net.multigesture.kanama.binding.runtime

import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.COpaquePointerVar
import kotlinx.cinterop.CPointed
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.CValuesRef
import kotlinx.cinterop.DoubleVar
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.FloatVar
import kotlinx.cinterop.IntVar
import kotlinx.cinterop.LongVar
import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.cstr
import kotlinx.cinterop.get
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.plus
import kotlinx.cinterop.ptr
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.set
import kotlinx.cinterop.value
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_builtin_call
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_get_builtin_method
import net.multigesture.kanama.types.GodotRealArray
import net.multigesture.kanama.types.GodotRealVar

/**
 * iOS implementation of value-type (builtin) method calls — the `actual` of the `expect object
 * BuiltinCalls` in
 * `src/commonMain/kotlin/net/multigesture/kanama/binding/runtime/BuiltinCalls.expect.kt`, which is
 * where the contract and its KDoc live, and the analogue of [ObjectCalls] for Godot's builtin
 * Variant types (Transform3D, Basis, Vector*, …). Engine-computed methods like
 * `Transform3D.inverse()` route through `variant_get_ptr_builtin_method` (resolved once, cached
 * like a MethodBind) + the generic C `kanama_ios_godot_builtin_call`, which calls `method(base,
 * args, ret, argc)` with raw value byte buffers. Mirrors the desktop `BuiltinTypes.call`.
 *
 * Marshalling note: value components are `real_t` in iOS builds, laid out in the same column-major
 * order the [ObjectCalls] ptrcall helpers use.
 */
actual object BuiltinCalls {
  actual fun getBuiltinMethod(variantType: Int, method: String, hash: Long): Long =
    kanama_ios_godot_get_builtin_method(variantType, method, hash)

  // Marshal [base] (real_t components) + [args] (PT-tagged), then invoke the builtin
  // method writing into the caller-supplied [ret] buffer. The ret encoding is the
  // caller's choice (real_t components for a value-type return; an 8-byte double for a
  // scalar `float` return — see [callScalar]). Concentrates the arg layout/tags, mirroring
  // the desktop `BuiltinTypes` call helpers.
  private fun MemScope.invokeBuiltin(
    methodPtr: Long,
    base: GodotRealArray,
    args: List<BArg>,
    ret: CValuesRef<*>?,
  ) {
    val baseBuf = allocArray<GodotRealVar>(if (base.isNotEmpty()) base.size else 1)
    for (i in base.indices) baseBuf[i] = base[i]
    invokeWith(methodPtr, baseBuf, args, ret)
  }

  // The task 134 B entry points: [base] is marshalled like an argument (null: a static method,
  // which Godot calls with a NULL instance; the shim gets a one-byte dummy like the empty-base
  // path above).
  private fun MemScope.invokeOn(
    methodPtr: Long,
    base: BArg?,
    args: List<BArg>,
    ret: CValuesRef<*>?,
  ) {
    val baseBuf = if (base == null) alloc<ByteVar>().ptr else marshal(base).second
    invokeWith(methodPtr, baseBuf, args, ret)
  }

  // One argument as (tag, pointer): the shim switches on the tag only for the CONSTRUCT tags (a
  // String built from the C string); everything else is a raw value buffer it passes through.
  private fun MemScope.marshal(a: BArg): Pair<Int, CPointer<CPointed>> =
    when (a) {
      is BArg.Floats -> {
        val b = allocArray<GodotRealVar>(if (a.values.isNotEmpty()) a.values.size else 1)
        for (j in a.values.indices) b[j] = a.values[j]
        a.tag to b.reinterpret()
      }
      is BArg.Bool -> {
        val b = alloc<ByteVar>()
        b.value = if (a.value) 1 else 0
        PT_BOOL to b.ptr.reinterpret()
      }
      is BArg.Real -> {
        val b = alloc<DoubleVar>()
        b.value = a.value
        PT_FLOAT64 to b.ptr.reinterpret()
      }
      is BArg.Int64 -> {
        val b = alloc<LongVar>()
        b.value = a.value
        PT_INT64 to b.ptr.reinterpret()
      }
      is BArg.Ints -> {
        val b = allocArray<IntVar>(if (a.values.isNotEmpty()) a.values.size else 1)
        for (j in a.values.indices) b[j] = a.values[j]
        PT_INT32 to b.reinterpret()
      }
      is BArg.Float32s -> {
        val b = allocArray<FloatVar>(if (a.values.isNotEmpty()) a.values.size else 1)
        for (j in a.values.indices) b[j] = a.values[j]
        PT_FLOAT32 to b.reinterpret()
      }
      is BArg.Str -> PT_STRING to a.value.cstr.getPointer(this).reinterpret()
    }

  private fun MemScope.invokeWith(
    methodPtr: Long,
    baseBuf: CPointer<*>,
    args: List<BArg>,
    ret: CValuesRef<*>?,
  ) {
    val n = args.size
    val tags = allocArray<IntVar>(if (n > 0) n else 1)
    val ptrs = allocArray<COpaquePointerVar>(if (n > 0) n else 1)
    args.forEachIndexed { i, a ->
      val (tag, ptr) = marshal(a)
      tags[i] = tag
      ptrs[i] = ptr
    }
    kanama_ios_godot_builtin_call(
      methodPtr,
      baseBuf,
      if (n > 0) tags else null,
      if (n > 0) ptrs else null,
      n,
      ret,
    )
  }

  actual fun call(
    methodPtr: Long,
    base: GodotRealArray,
    retCount: Int,
    args: List<BArg>,
  ): GodotRealArray = memScoped {
    val ret = allocArray<GodotRealVar>(if (retCount > 0) retCount else 1)
    invokeBuiltin(methodPtr, base, args, ret)
    GodotRealArray(retCount) { ret[it] }
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
   * engine's real_t precision — NOT a real_t/float32, unlike value-type *components*. So decode the
   * return as a double (matches desktop `BuiltinTypes`, which allocates a JAVA_DOUBLE ret). An
   * `int`/`bool` scalar return would need its own decode width.
   */
  actual fun callScalar(methodPtr: Long, base: GodotRealArray, args: List<BArg>): Double =
    memScoped {
      val ret = alloc<DoubleVar>()
      invokeBuiltin(methodPtr, base, args, ret.ptr)
      ret.value
    }

  /**
   * Builtin method returning a `bool` (is_normalized / is_finite / …). Godot's ptr-ABI encodes a
   * bool return as a single `uint8_t` (`PtrToArg<bool>` = uint8), so decode one byte (≠ 0 → true).
   */
  actual fun callBool(methodPtr: Long, base: GodotRealArray, args: List<BArg>): Boolean =
    memScoped {
      val ret = alloc<ByteVar>()
      invokeBuiltin(methodPtr, base, args, ret.ptr)
      ret.value.toInt() != 0
    }

  /**
   * Builtin method returning an `int` (max_axis_index / … ). Godot's ptr-ABI encodes an int return
   * as `int64_t` (`PtrToArg<int64_t>` is direct 8-byte), so decode a Long.
   */
  actual fun callInt(methodPtr: Long, base: GodotRealArray, args: List<BArg>): Long = memScoped {
    val ret = alloc<LongVar>()
    invokeBuiltin(methodPtr, base, args, ret.ptr)
    ret.value
  }

  actual fun invokeReals(
    methodPtr: Long,
    base: BArg?,
    retCount: Int,
    args: List<BArg>,
  ): GodotRealArray = memScoped {
    val ret = allocArray<GodotRealVar>(if (retCount > 0) retCount else 1)
    invokeOn(methodPtr, base, args, ret)
    GodotRealArray(retCount) { ret[it] }
  }

  actual fun invokeInts(methodPtr: Long, base: BArg?, retCount: Int, args: List<BArg>): IntArray =
    memScoped {
      val ret = allocArray<IntVar>(if (retCount > 0) retCount else 1)
      invokeOn(methodPtr, base, args, ret)
      IntArray(retCount) { ret[it] }
    }

  actual fun invokeFloat32s(
    methodPtr: Long,
    base: BArg?,
    retCount: Int,
    args: List<BArg>,
  ): FloatArray = memScoped {
    val ret = allocArray<FloatVar>(if (retCount > 0) retCount else 1)
    invokeOn(methodPtr, base, args, ret)
    FloatArray(retCount) { ret[it] }
  }

  actual fun invokeDouble(methodPtr: Long, base: BArg?, args: List<BArg>): Double = memScoped {
    val ret = alloc<DoubleVar>()
    invokeOn(methodPtr, base, args, ret.ptr)
    ret.value
  }

  actual fun invokeLong(methodPtr: Long, base: BArg?, args: List<BArg>): Long = memScoped {
    val ret = alloc<LongVar>()
    invokeOn(methodPtr, base, args, ret.ptr)
    ret.value
  }

  actual fun invokeBool(methodPtr: Long, base: BArg?, args: List<BArg>): Boolean = memScoped {
    val ret = alloc<ByteVar>()
    invokeOn(methodPtr, base, args, ret.ptr)
    ret.value.toInt() != 0
  }

  actual fun invokeVariantReals(
    methodPtr: Long,
    base: BArg?,
    count: Int,
    args: List<BArg>,
  ): GodotRealArray? = memScoped {
    // A Variant: its Variant::Type (int32) first, the payload at offset 8 (64 bytes cover the
    // float32 and the float64 layouts). NIL is type 0; the value types these methods return are
    // POD, so the Variant needs no destructor.
    val ret = allocArray<LongVar>(VARIANT_CELL_LONGS)
    invokeOn(methodPtr, base, args, ret)
    if (ret.reinterpret<IntVar>()[0] == 0) {
      null
    } else {
      val payload = (ret + 1)!!.reinterpret<GodotRealVar>()
      GodotRealArray(count) { payload[it] }
    }
  }

  private const val VARIANT_CELL_LONGS = 8
}
