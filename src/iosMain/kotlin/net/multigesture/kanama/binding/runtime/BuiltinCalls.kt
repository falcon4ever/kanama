@file:OptIn(ExperimentalForeignApi::class)

package net.multigesture.kanama.binding.runtime

import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.COpaquePointerVar
import kotlinx.cinterop.CPointed
import kotlinx.cinterop.CValuesRef
import kotlinx.cinterop.DoubleVar
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.IntVar
import kotlinx.cinterop.LongVar
import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.get
import kotlinx.cinterop.memScoped
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
    val n = args.size
    val tags = allocArray<IntVar>(if (n > 0) n else 1)
    val ptrs = allocArray<COpaquePointerVar>(if (n > 0) n else 1)
    args.forEachIndexed { i, a ->
      when (a) {
        is BArg.Floats -> {
          val b = allocArray<GodotRealVar>(if (a.values.isNotEmpty()) a.values.size else 1)
          for (j in a.values.indices) b[j] = a.values[j]
          tags[i] = a.tag
          ptrs[i] = b.reinterpret<CPointed>()
        }
        is BArg.Bool -> {
          val b = alloc<ByteVar>()
          b.value = if (a.value) 1 else 0
          tags[i] = PT_BOOL
          ptrs[i] = b.ptr.reinterpret<CPointed>()
        }
        is BArg.Real -> {
          val b = alloc<DoubleVar>()
          b.value = a.value
          tags[i] = PT_FLOAT64
          ptrs[i] = b.ptr.reinterpret<CPointed>()
        }
        is BArg.Int64 -> {
          val b = alloc<LongVar>()
          b.value = a.value
          tags[i] = PT_INT64
          ptrs[i] = b.ptr.reinterpret<CPointed>()
        }
      }
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

  /**
   * Call a builtin method whose base and return are value types laid out as `real_t` components
   * ([base] in, [retCount] values out), with optional [args].
   */
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
}
