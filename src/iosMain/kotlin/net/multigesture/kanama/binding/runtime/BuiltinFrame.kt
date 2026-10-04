@file:OptIn(ExperimentalForeignApi::class)

package net.multigesture.kanama.binding.runtime

import kotlin.experimental.ExperimentalNativeApi
import kotlin.native.ref.createCleaner
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.COpaquePointerVar
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.DoubleVar
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.FloatVar
import kotlinx.cinterop.IntVar
import kotlinx.cinterop.LongVar
import kotlinx.cinterop.NativePtr
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.get
import kotlinx.cinterop.nativeHeap
import kotlinx.cinterop.plus
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.set
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_builtin_call
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_get_builtin_method
import net.multigesture.kanama.types.GodotRealStorage
import net.multigesture.kanama.types.GodotRealVar

/**
 * iOS implementation of the value types' builtin calls -- the `actual`s of
 * `src/commonMain/.../binding/runtime/BuiltinFrame.expect.kt`; the desktop one is
 * `src/jvmMain/kotlin/binding/runtime/BuiltinFrame.kt`.
 *
 * Each thread owns one native frame (nativeHeap, kept for the thread's life): slots for the base
 * and the arguments, a return slot, and the shim's tag and pointer arrays, pointing at the slots
 * once. `kanama_ios_godot_builtin_call` passes POD slots through and builds a Godot String from a C
 * string for a `PT_STRING` slot, destroying it after the call.
 */
internal actual class BuiltinMethod
actual constructor(private val variantType: Int, private val name: String, private val hash: Long) {
  private var pointer = 0L

  internal fun pointer(): Long {
    val resolved = pointer
    if (resolved != 0L) return resolved
    val fn = kanama_ios_godot_get_builtin_method(variantType, name, hash)
    // The shim reports the failed lookup; never hand a NULL method to the call shim.
    check(fn != 0L) { "variant_get_ptr_builtin_method($variantType, $name, $hash) returned NULL" }
    pointer = fn
    return fn
  }
}

/**
 * The calling thread's frames, used as a stack (as on desktop): a builtin can re-enter Kotlin while
 * it runs (an engine WARN/ERR print reaches a GDScript logger that calls a Kotlin script), so a
 * nested builtin call takes the next frame instead of overwriting the slots the outer call is still
 * reading.
 */
internal class FrameStack {
  var depth = 0
  private var frames: Array<BuiltinFrame?> = arrayOfNulls(4)

  // The common case, no builtin call in flight on this thread: one field test, no array access.
  private val first = BuiltinFrame(this, 0)

  fun acquire(): BuiltinFrame {
    val index = depth
    depth = index + 1
    return if (index == 0) first else deeper(index)
  }

  private fun deeper(index: Int): BuiltinFrame {
    if (index >= frames.size) frames = frames.copyOf(maxOf(index + 1, frames.size * 2))
    return frames[index] ?: BuiltinFrame(this, index).also { frames[index] = it }
  }
}

@kotlin.native.concurrent.ThreadLocal private val frames: FrameStack = FrameStack()

internal actual fun builtinFrame(): BuiltinFrame = frames.acquire()

// A frame's three native blocks, freed by the frame's Cleaner once the frame is collected (a
// thread's @ThreadLocal frame stack becomes unreachable when the thread ends), so a thread that
// called a builtin does not leave its ~1.3 KB behind.
private class FrameBlocks(val memory: NativePtr, val tags: NativePtr, val pointers: NativePtr) {
  fun free() {
    nativeHeap.free(memory)
    nativeHeap.free(tags)
    nativeHeap.free(pointers)
  }
}

@OptIn(ExperimentalNativeApi::class)
internal actual class BuiltinFrame
internal constructor(private val stack: FrameStack, private val index: Int) {
  private val memory: CPointer<ByteVar> = nativeHeap.allocArray(TOTAL_BYTES)
  private val tags: CPointer<IntVar> = nativeHeap.allocArray(MAX_ARGS)
  private val pointers: CPointer<COpaquePointerVar> = nativeHeap.allocArray(MAX_ARGS)

  @Suppress("unused") // held for its effect: frees the blocks when this frame is collected
  private val cleaner =
    createCleaner(FrameBlocks(memory.rawValue, tags.rawValue, pointers.rawValue)) { it.free() }

  // Bit i: slot i holds a nativeHeap C string for a String argument, freed after the call.
  private var strings = 0

  init {
    for (i in 0 until MAX_ARGS) {
      tags[i] = PT_INT32 // POD passthrough
      pointers[i] = slot(i + 1)
    }
  }

  private fun slot(index: Int): COpaquePointer = (memory + index * SLOT_BYTES)!!

  private fun reals(slot: Int): CPointer<GodotRealVar> = slot(slot).reinterpret()

  private fun ints(slot: Int): CPointer<IntVar> = slot(slot).reinterpret()

  private fun floats(slot: Int): CPointer<FloatVar> = slot(slot).reinterpret()

  private val ret: COpaquePointer = (memory + RET_OFFSET)!!

  actual fun putReal(slot: Int, index: Int, value: GodotRealStorage) {
    reals(slot)[index] = value
  }

  actual fun putInt32(slot: Int, index: Int, value: Int) {
    ints(slot)[index] = value
  }

  actual fun putFloat32(slot: Int, index: Int, value: Float) {
    floats(slot)[index] = value
  }

  actual fun putDouble(slot: Int, value: Double) {
    slot(slot).reinterpret<DoubleVar>()[0] = value
  }

  actual fun putLong(slot: Int, value: Long) {
    slot(slot).reinterpret<LongVar>()[0] = value
  }

  actual fun putBool(slot: Int, value: Boolean) {
    slot(slot).reinterpret<ByteVar>()[0] = if (value) 1 else 0
  }

  actual fun putString(slot: Int, value: String) {
    // The shim builds the String from a C string for a PT_STRING argument; the base is never one.
    require(slot >= 1) { "a String is only ever an argument of a builtin call" }
    val utf8 = value.encodeToByteArray()
    val cString = nativeHeap.allocArray<ByteVar>(utf8.size + 1)
    for (i in utf8.indices) cString[i] = utf8[i]
    cString[utf8.size] = 0
    tags[slot - 1] = PT_STRING
    pointers[slot - 1] = cString
    strings = strings or (1 shl slot)
  }

  actual fun call(method: BuiltinMethod, argc: Int) {
    invoke(method, slot(0), argc)
  }

  actual fun callStatic(method: BuiltinMethod, argc: Int) {
    // Static builtins ignore the instance; the shim gets a valid buffer as before.
    invoke(method, slot(0), argc)
  }

  private fun invoke(method: BuiltinMethod, base: COpaquePointer, argc: Int) {
    try {
      // Resolved inside the try: a failed resolution still frees the C strings and the frame.
      val pointer = method.pointer()
      // Zero the head of the return slot: a Variant return reads NIL unless the method writes one.
      ret.reinterpret<LongVar>()[0] = 0L
      ret.reinterpret<LongVar>()[1] = 0L
      kanama_ios_godot_builtin_call(
        pointer,
        base,
        if (argc > 0) tags else null,
        if (argc > 0) pointers else null,
        argc,
        ret,
      )
    } finally {
      if (strings != 0) releaseStrings()
      // Return this frame (and any deeper one an exception left taken) to the thread's stack.
      stack.depth = index
    }
  }

  private fun releaseStrings() {
    for (slot in 1..MAX_ARGS) {
      if (strings and (1 shl slot) != 0) {
        nativeHeap.free(pointers[slot - 1]!!.rawValue)
        tags[slot - 1] = PT_INT32
        pointers[slot - 1] = slot(slot)
      }
    }
    strings = 0
  }

  actual fun retReal(index: Int): GodotRealStorage = ret.reinterpret<GodotRealVar>()[index]

  actual fun retInt32(index: Int): Int = ret.reinterpret<IntVar>()[index]

  actual fun retFloat32(index: Int): Float = ret.reinterpret<FloatVar>()[index]

  actual fun retDouble(): Double = ret.reinterpret<DoubleVar>()[0]

  actual fun retLong(): Long = ret.reinterpret<LongVar>()[0]

  actual fun retBool(): Boolean = ret.reinterpret<ByteVar>()[0].toInt() != 0

  actual fun retVariantIsNil(): Boolean = ret.reinterpret<IntVar>()[0] == 0

  actual fun retVariantReal(index: Int): GodotRealStorage =
    (memory + (RET_OFFSET + VARIANT_PAYLOAD))!!.reinterpret<GodotRealVar>()[index]

  private companion object {
    // As desktop: 8 arguments at most (Projection.create_for_hmd), 128-byte slots (a float64
    // Projection), a Variant's payload at offset 8.
    const val MAX_ARGS = 8
    const val SLOTS = MAX_ARGS + 1
    const val SLOT_BYTES = 128
    const val RET_OFFSET = SLOTS * SLOT_BYTES
    const val TOTAL_BYTES = RET_OFFSET + 128
    const val VARIANT_PAYLOAD = 8
  }
}
