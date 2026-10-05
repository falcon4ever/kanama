@file:OptIn(ExperimentalForeignApi::class)

package net.multigesture.kanama.binding.runtime

import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.COpaquePointerVar
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.DoubleVar
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.IntVar
import kotlinx.cinterop.LongVar
import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.cstr
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.set
import kotlinx.cinterop.value
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_builtin_call_boxed
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_get_utility_function
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_utility_call
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.RID

/**
 * iOS implementation of the utility-call seam -- the `actual`s of
 * `src/commonMain/.../binding/runtime/UtilityCalls.expect.kt`; the desktop one is
 * `src/jvmMain/kotlin/binding/runtime/UtilityCalls.kt`. Both paths call the one C entry
 * `kanama_ios_godot_utility_call`; the frame path is [BuiltinFrame.callUtility].
 */
internal actual class UtilityFunction
actual constructor(private val name: String, private val hash: Long) {
  private var pointer = 0L

  internal fun pointer(): Long {
    val resolved = pointer
    if (resolved != 0L) return resolved
    val fn = kanama_ios_godot_get_utility_function(name, hash)
    // The shim reports the failed lookup; never hand a NULL function to the call shim.
    check(fn != 0L) { "variant_get_ptr_utility_function($name, $hash) returned NULL" }
    pointer = fn
    return fn
  }
}

internal actual object UtilityCalls {
  private const val STR_BUF_SIZE = 1024L

  actual fun call(
    fn: UtilityFunction,
    argTypes: IntArray,
    args: Array<out Any?>,
    retType: Int,
  ): Any? = memScoped {
    val pointer = fn.pointer()
    val count = args.size
    val tags = allocArray<IntVar>(if (count > 0) count else 1)
    val ptrs = allocArray<COpaquePointerVar>(if (count > 0) count else 1)
    for (i in 0 until count) {
      putArgument(tags, ptrs, i, if (i < argTypes.size) argTypes[i] else VT_NIL, args[i])
    }
    val outInt = alloc<LongVar>()
    val outDouble = alloc<DoubleVar>()
    val outStr = allocArray<ByteVar>(STR_BUF_SIZE)
    val outStrLen = alloc<LongVar>()
    val outIsRefCounted = alloc<IntVar>()
    val delivered =
      kanama_ios_godot_utility_call(
        pointer,
        tags,
        ptrs,
        count,
        retType,
        null,
        outInt.ptr,
        outDouble.ptr,
        outStr,
        STR_BUF_SIZE,
        outStrLen.ptr,
        outIsRefCounted.ptr,
      )
    when {
      retType < 0 || delivered < 0 -> null
      retType == VT_RID -> RID(outInt.value)
      else ->
        with(ObjectCalls) {
          decodeVariantScalarReturn(
            delivered,
            outInt,
            outDouble,
            outStr,
            STR_BUF_SIZE,
            outStrLen,
            outIsRefCounted,
          )
        }
    }
  }

  actual fun callMethod(
    method: BuiltinMethod,
    baseType: Int,
    base: Any?,
    argTypes: IntArray,
    args: Array<out Any?>,
    retType: Int,
  ): Any? = memScoped {
    val pointer = method.pointer()
    val count = args.size
    val tags = allocArray<IntVar>(if (count > 0) count else 1)
    val ptrs = allocArray<COpaquePointerVar>(if (count > 0) count else 1)
    for (i in 0 until count) {
      putArgument(tags, ptrs, i, if (i < argTypes.size) argTypes[i] else VT_NIL, args[i])
    }
    // The base, built C-side like an argument of its type (null: Godot's NULL instance).
    val baseTag: Int
    val basePtr: COpaquePointer?
    when {
      base == null -> {
        baseTag = PT_VOID
        basePtr = null
      }
      baseType == VT_STRING -> {
        baseTag = PT_STRING
        basePtr = (base as String).cstr.ptr
      }
      baseType == VT_STRING_NAME -> {
        baseTag = PT_STRING_NAME
        basePtr = (base as String).cstr.ptr
      }
      baseType == VT_NODE_PATH -> {
        baseTag = PT_NODE_PATH
        basePtr = (base as NodePath).path.cstr.ptr
      }
      baseType == VT_PACKED_BYTE_ARRAY -> {
        baseTag = PT_PACKED_BYTE_ARRAY
        basePtr = with(ObjectCalls) { packByteDesc(base as ByteArray) }
      }
      else -> error("iOS: unsupported builtin base Variant type $baseType")
    }
    val outInt = alloc<LongVar>()
    val outDouble = alloc<DoubleVar>()
    val outStr = allocArray<ByteVar>(STR_BUF_SIZE)
    val outStrLen = alloc<LongVar>()
    val outIsRefCounted = alloc<IntVar>()
    val delivered =
      kanama_ios_godot_builtin_call_boxed(
        pointer,
        baseTag,
        basePtr,
        tags,
        ptrs,
        count,
        retType,
        null,
        outInt.ptr,
        outDouble.ptr,
        outStr,
        STR_BUF_SIZE,
        outStrLen.ptr,
        outIsRefCounted.ptr,
      )
    check(delivered >= 0 || retType < 0) {
      "iOS: a builtin method on Variant type $baseType did not run (the shim reported why)"
    }
    when {
      retType < 0 -> null
      retType == VT_RID -> RID(outInt.value)
      retType == VT_NODE_PATH ->
        with(ObjectCalls) {
          NodePath(
            decodeVariantScalarReturn(
              delivered,
              outInt,
              outDouble,
              outStr,
              STR_BUF_SIZE,
              outStrLen,
              outIsRefCounted,
            )
              as String
          )
        }
      else ->
        with(ObjectCalls) {
          decodeVariantScalarReturn(
            delivered,
            outInt,
            outDouble,
            outStr,
            STR_BUF_SIZE,
            outStrLen,
            outIsRefCounted,
          )
        }
    }
  }

  /** Argument [i] of a boxed call, tagged and laid out for the shim (see [call]). */
  private fun MemScope.putArgument(
    tags: CPointer<IntVar>,
    ptrs: CPointer<COpaquePointerVar>,
    i: Int,
    type: Int,
    value: Any?,
  ) {
    when (type) {
      VT_BOOL -> {
        val cell = alloc<ByteVar>()
        cell.value = if (value as Boolean) 1 else 0
        tags[i] = PT_BOOL
        ptrs[i] = cell.ptr
      }
      VT_INT -> {
        val cell = alloc<LongVar>()
        cell.value = (value as Number).toLong()
        tags[i] = PT_INT64
        ptrs[i] = cell.ptr
      }
      VT_FLOAT -> {
        val cell = alloc<DoubleVar>()
        cell.value = (value as Number).toDouble()
        tags[i] = PT_FLOAT64
        ptrs[i] = cell.ptr
      }
      VT_STRING -> {
        tags[i] = PT_STRING
        ptrs[i] = (value as String).cstr.ptr
      }
      VT_STRING_NAME -> {
        tags[i] = PT_STRING_NAME
        ptrs[i] = (value as String).cstr.ptr
      }
      VT_NODE_PATH -> {
        tags[i] = PT_NODE_PATH
        ptrs[i] = (value as NodePath).path.cstr.ptr
      }
      VT_PACKED_BYTE_ARRAY -> {
        tags[i] = PT_PACKED_BYTE_ARRAY
        ptrs[i] = with(ObjectCalls) { packByteDesc(value as ByteArray) }
      }
      VT_NIL -> {
        tags[i] = PT_VARIANT
        ptrs[i] = with(ObjectCalls) { packVariantDesc(value) }
      }
      else -> error("iOS: unsupported boxed-call argument Variant type $type")
    }
  }
}
