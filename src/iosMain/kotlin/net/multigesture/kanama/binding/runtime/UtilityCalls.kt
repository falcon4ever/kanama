@file:OptIn(ExperimentalForeignApi::class)

package net.multigesture.kanama.binding.runtime

import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.COpaquePointerVar
import kotlinx.cinterop.DoubleVar
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.IntVar
import kotlinx.cinterop.LongVar
import kotlinx.cinterop.alloc
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.cstr
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.set
import kotlinx.cinterop.value
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_get_utility_function
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_utility_call
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
  private const val VT_NIL = 0
  private const val VT_BOOL = 1
  private const val VT_INT = 2
  private const val VT_FLOAT = 3
  private const val VT_STRING = 4
  private const val VT_RID = 23
  private const val VT_PACKED_BYTE_ARRAY = 29
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
      val value = args[i]
      when (if (i < argTypes.size) argTypes[i] else VT_NIL) {
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
        VT_PACKED_BYTE_ARRAY -> {
          tags[i] = PT_PACKED_BYTE_ARRAY
          ptrs[i] = with(ObjectCalls) { packByteDesc(value as ByteArray) }
        }
        VT_NIL -> {
          tags[i] = PT_VARIANT
          ptrs[i] = with(ObjectCalls) { packVariantDesc(value) }
        }
        else -> error("iOS: unsupported utility argument Variant type ${argTypes[i]}")
      }
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
}
