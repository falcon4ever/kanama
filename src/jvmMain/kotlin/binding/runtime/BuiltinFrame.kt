package net.multigesture.kanama.binding.runtime

import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout.ADDRESS
import java.lang.foreign.ValueLayout.JAVA_BYTE
import java.lang.foreign.ValueLayout.JAVA_DOUBLE
import java.lang.foreign.ValueLayout.JAVA_FLOAT
import java.lang.foreign.ValueLayout.JAVA_INT
import java.lang.foreign.ValueLayout.JAVA_LONG
import java.lang.invoke.MethodHandle
import net.multigesture.kanama.ffi.GodotFFI
import net.multigesture.kanama.types.GodotReal
import net.multigesture.kanama.types.GodotRealSegment
import net.multigesture.kanama.types.GodotRealStorage

/**
 * Desktop/Android implementation of the value types' builtin calls -- the `actual`s of
 * `src/commonMain/.../binding/runtime/BuiltinFrame.expect.kt`, where the contract lives; the iOS
 * one is `src/iosMain/.../binding/runtime/BuiltinFrame.kt`.
 *
 * Task 134 B made a call allocation-free: each thread owns one native frame (slots for the base and
 * the arguments, a return slot, and the argument-pointer array pointing at the slots, written
 * once), a generated member writes its values into it, and every builtin method is called through
 * ONE unbound downcall handle -- a JVM constant called with `invokeExact`, its target the method
 * pointer [BuiltinMethod] resolved once.
 */
internal actual class BuiltinMethod
actual constructor(private val variantType: Int, private val name: String, private val hash: Long) {
  // Resolved on first call (after GodotFFI.bootstrap). A race resolves twice to the same pointer.
  private var target: MemorySegment = MemorySegment.NULL

  internal fun target(): MemorySegment {
    val resolved = target
    return if (resolved.address() != 0L) resolved else resolve()
  }

  private fun resolve(): MemorySegment {
    val fn =
      Resolve.HANDLE.invokeWithArguments(variantType, GodotStrings.makeStringName(name), hash)
        as MemorySegment
    check(fn.address() != 0L) {
      "variant_get_ptr_builtin_method($variantType, $name, $hash) returned NULL"
    }
    target = fn
    return fn
  }

  private object Resolve {
    @JvmField
    val HANDLE: MethodHandle =
      GodotFFI.lookup(
        "variant_get_ptr_builtin_method",
        FunctionDescriptor.of(ADDRESS, JAVA_INT, ADDRESS, JAVA_LONG),
      )
  }
}

/**
 * The calling thread's frames, used as a stack: [builtinFrame] takes the frame at [depth] (growing
 * the array on demand) and the call that frame makes returns it. A builtin can re-enter Kotlin
 * while it runs -- a WARN/ERR print reaches every registered logger synchronously, and a GDScript
 * logger may call a Kotlin script -- so a builtin call made from inside another one must not touch
 * the outer call's slots, which the engine is still reading by reference.
 */
internal class FrameStack {
  @JvmField var depth = 0

  @JvmField var frames: Array<BuiltinFrame?> = arrayOfNulls(INITIAL_FRAMES)

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

  private companion object {
    const val INITIAL_FRAMES = 4
  }
}

private val FRAMES: ThreadLocal<FrameStack> = ThreadLocal.withInitial { FrameStack() }

// The first thread to make a builtin call (in practice Godot's main thread) finds its stack without
// the ThreadLocal lookup. Only that thread ever reads `fastStack`, and it wrote it itself, so the
// fields need no volatile: another thread sees `fastThread` as not itself whatever it reads.
private var fastThread: Thread? = null
private var fastStack: FrameStack? = null

internal actual fun builtinFrame(): BuiltinFrame {
  val thread = Thread.currentThread()
  if (thread === fastThread) return fastStack!!.acquire()
  return slowFrame(thread)
}

private fun slowFrame(thread: Thread): BuiltinFrame {
  val stack = FRAMES.get()
  if (fastThread == null) {
    synchronized(FRAMES) {
      if (fastThread == null) {
        fastStack = stack
        fastThread = thread
      }
    }
  }
  return stack.acquire()
}

internal actual class BuiltinFrame
internal constructor(private val stack: FrameStack, private val index: Int) {
  private val memory: MemorySegment = Arena.ofAuto().allocate(TOTAL_BYTES, 16L)
  private val slots: Array<MemorySegment> =
    Array(SLOTS) { memory.asSlice(it * SLOT_BYTES, SLOT_BYTES) }
  private val base: MemorySegment = slots[0]
  private val ret: MemorySegment = memory.asSlice(RET_OFFSET, RET_BYTES)
  private val args: MemorySegment = memory.asSlice(ARGS_OFFSET, MAX_ARGS * ADDRESS.byteSize())

  // Bit i: slot i holds a Godot String built by putString, destroyed after the call.
  private var strings = 0

  init {
    for (i in 0 until MAX_ARGS) args.setAtIndex(ADDRESS, i.toLong(), slots[i + 1])
  }

  actual fun putReal(slot: Int, index: Int, value: GodotRealStorage) {
    GodotRealSegment.writeRaw(memory, slot * REALS_PER_SLOT + index, value)
  }

  actual fun putInt32(slot: Int, index: Int, value: Int) {
    memory.set(JAVA_INT, slot * SLOT_BYTES + index * 4L, value)
  }

  actual fun putFloat32(slot: Int, index: Int, value: Float) {
    memory.set(JAVA_FLOAT, slot * SLOT_BYTES + index * 4L, value)
  }

  actual fun putDouble(slot: Int, value: Double) {
    memory.set(JAVA_DOUBLE, slot * SLOT_BYTES, value)
  }

  actual fun putLong(slot: Int, value: Long) {
    memory.set(JAVA_LONG, slot * SLOT_BYTES, value)
  }

  actual fun putBool(slot: Int, value: Boolean) {
    memory.set(JAVA_BYTE, slot * SLOT_BYTES, if (value) 1.toByte() else 0.toByte())
  }

  actual fun putString(slot: Int, value: String) {
    GodotStrings.initString(slots[slot], value)
    strings = strings or (1 shl slot)
  }

  actual fun call(method: BuiltinMethod, argc: Int) {
    invoke(method, base, argc)
  }

  actual fun callStatic(method: BuiltinMethod, argc: Int) {
    invoke(method, MemorySegment.NULL, argc)
  }

  private fun invoke(method: BuiltinMethod, self: MemorySegment, argc: Int) {
    try {
      // Resolved inside the try: a failed resolution still releases the Strings and the frame.
      val target = method.target()
      // Zero the head of the return slot: a Variant return reads NIL unless the method writes one.
      ret.set(JAVA_LONG, 0L, 0L)
      ret.set(JAVA_LONG, 8L, 0L)
      callExact(target, self, argc)
    } finally {
      if (strings != 0) releaseStrings()
      // Return this frame (and any deeper one an exception left taken) to the thread's stack. The
      // caller reads the return slot next, before any other builtin call on this thread.
      stack.depth = index
    }
  }

  // Block-bodied, so the invokeExact is a statement and compiles to the handle's exact
  // `(MemorySegment, MemorySegment, MemorySegment, MemorySegment, int)void` type.
  private fun callExact(target: MemorySegment, self: MemorySegment, argc: Int) {
    Call.HANDLE.invokeExact(target, self, args, ret, argc)
  }

  private fun releaseStrings() {
    for (slot in 0 until SLOTS) {
      if (strings and (1 shl slot) != 0) GodotStrings.destroyString(slots[slot])
    }
    strings = 0
  }

  actual fun retReal(index: Int): GodotRealStorage =
    GodotRealSegment.readRaw(memory, RET_OFFSET / GodotReal.SIZE_BYTES + index)

  actual fun retInt32(index: Int): Int = ret.get(JAVA_INT, index * 4L)

  actual fun retFloat32(index: Int): Float = ret.get(JAVA_FLOAT, index * 4L)

  actual fun retDouble(): Double = ret.get(JAVA_DOUBLE, 0L)

  actual fun retLong(): Long = ret.get(JAVA_LONG, 0L)

  actual fun retBool(): Boolean = ret.get(JAVA_BYTE, 0L).toInt() != 0

  actual fun retVariantIsNil(): Boolean = ret.get(JAVA_INT, 0L) == 0

  actual fun retVariantReal(index: Int): GodotRealStorage =
    GodotRealSegment.readRaw(memory, (RET_OFFSET + VARIANT_PAYLOAD) / GodotReal.SIZE_BYTES + index)

  /** `GDExtensionPtrBuiltInMethod(base, args, ret, argc)`, unbound: the method is argument 0. */
  private object Call {
    @JvmField
    val HANDLE: MethodHandle =
      GodotFFI.unboundDowncallHandle(
        FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, ADDRESS, JAVA_INT),
        "variant_ptr_builtin_method",
      )
  }

  private companion object {
    // The widest builtin method takes 8 arguments (Projection.create_for_hmd); the widest value is
    // a float64 Projection (128 bytes); a Variant is 24 bytes (40 in a float64 build).
    const val MAX_ARGS = 8
    const val SLOTS = MAX_ARGS + 1
    const val SLOT_BYTES = 128L
    const val RET_BYTES = 128L
    const val RET_OFFSET = SLOTS * SLOT_BYTES
    const val ARGS_OFFSET = RET_OFFSET + RET_BYTES
    const val TOTAL_BYTES = ARGS_OFFSET + MAX_ARGS * 8L
    const val VARIANT_PAYLOAD = 8L
    val REALS_PER_SLOT = SLOT_BYTES / GodotReal.SIZE_BYTES
  }
}
