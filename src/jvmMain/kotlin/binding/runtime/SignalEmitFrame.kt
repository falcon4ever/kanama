package net.multigesture.kanama.binding.runtime

import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout.JAVA_BYTE
import java.lang.foreign.ValueLayout.JAVA_DOUBLE
import java.lang.foreign.ValueLayout.JAVA_INT
import java.lang.foreign.ValueLayout.JAVA_LONG
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.SignalArgWriter
import net.multigesture.kanama.ffi.GodotFFI

/**
 * The typed signals' `emit(…)` on desktop/Android (task 134 C review S5): `Object.emit_signal`
 * through `object_method_bind_call`, with the signal name and the arguments built as Variants in
 * place in a per-thread native frame, every pointer passed as a `long`, so an emission allocates
 * nothing (a `String` argument copies its text, and a value type outside
 * `int`/`float`/`bool`/`String`/objects takes the generic encode). The untyped path
 * ([Signals.emitAny]) allocated an arena, a list and a slice per argument.
 *
 * Frames nest: a handler that emits again runs inside the outer `emit_signal`, whose argument
 * Variants must stay intact, so each nesting level has its own frame.
 */
internal class SignalEmitFrame private constructor() : SignalArgWriter {
  private val block = Arena.ofAuto().allocate(FRAME_SIZE, 16L)
  private val base = block.address()
  private val scratch: MemorySegment = block.asSlice(SCRATCH_OFFSET, 16)
  private val scratchAddress = base + SCRATCH_OFFSET
  private var instance = 0L
  private var signal = ""
  private var count = 0

  /** Bit i set: Variant slot i holds something its destructor must release. */
  private var owned = 0

  private fun slot(index: Int): Long {
    require(index in 0 until count) { "signal '$signal': argument ${index + 1} of $count" }
    return base + (index + 1) * VARIANT_SIZE
  }

  override fun long(index: Int, value: Long) {
    scratch.set(JAVA_LONG, 0, value)
    Native.FROM_INT.invoke(slot(index), scratchAddress)
  }

  override fun double(index: Int, value: Double) {
    scratch.set(JAVA_DOUBLE, 0, value)
    Native.FROM_FLOAT.invoke(slot(index), scratchAddress)
  }

  override fun bool(index: Int, value: Boolean) {
    scratch.set(JAVA_BYTE, 0, if (value) 1 else 0)
    Native.FROM_BOOL.invoke(slot(index), scratchAddress)
  }

  override fun string(index: Int, value: String) {
    val target = slot(index)
    GodotStrings.initString(scratch, value)
    try {
      Native.FROM_STRING.invoke(target, scratchAddress)
    } finally {
      GodotStrings.destroyString(scratch)
    }
    owned = owned or (1 shl (index + 1))
  }

  override fun obj(index: Int, value: GodotObject?) {
    val target = slot(index)
    // A wrapper of a freed object encodes as nil, as on the untyped path (task 131 item 2).
    val address = value?.let { FreedObjectChecks.valueSegment(it).address() } ?: 0L
    if (address == 0L) {
      Native.NEW_NIL.invoke(target)
    } else {
      scratch.set(JAVA_LONG, 0, address)
      Native.FROM_OBJECT.invoke(target, scratchAddress)
      owned = owned or (1 shl (index + 1))
    }
  }

  override fun value(index: Int, value: Any?) {
    val target = MemorySegment.ofAddress(slot(index)).reinterpret(VARIANT_SIZE)
    Arena.ofConfined().use { arena -> BuiltinTypes.initVariantFromAny(target, value, arena) }
    owned = owned or (1 shl (index + 1))
  }

  private fun begin(instance: Long, signal: String, count: Int) {
    require(count <= MAX_ARGUMENTS) { "signal '$signal': at most $MAX_ARGUMENTS arguments" }
    this.instance = instance
    this.signal = signal
    this.count = count
    owned = 0
  }

  private fun finish(send: Boolean) {
    try {
      if (!send) return
      val name = GodotStrings.makeStringName(signal)
      Native.FROM_STRING_NAME.invoke(base, name.address())
      owned = owned or 1
      val pointers = base + POINTERS_OFFSET
      for (i in 0..count) {
        ADDRESS_SPACE.set(JAVA_LONG, pointers + i * 8L, base + i * VARIANT_SIZE)
      }
      ADDRESS_SPACE.set(JAVA_INT, base + ERROR_OFFSET, 0)
      Native.METHOD_BIND_CALL.invoke(
        Signals.emitSignalBind.address(),
        instance,
        pointers,
        count + 1L,
        base + RETURN_OFFSET,
        base + ERROR_OFFSET,
      )
      val error = ADDRESS_SPACE.get(JAVA_INT, base + ERROR_OFFSET)
      check(error == 0) { "emit_signal($signal) failed: error_type=$error" }
    } finally {
      for (i in 0..count) {
        if (owned and (1 shl i) != 0) Native.DESTROY.invoke(base + i * VARIANT_SIZE)
      }
      owned = 0
    }
  }

  private object Native {
    val FROM_INT = VariantConverters.variantFromTypeByAddress(VariantType.INT)
    val FROM_FLOAT = VariantConverters.variantFromTypeByAddress(VariantType.FLOAT)
    val FROM_BOOL = VariantConverters.variantFromTypeByAddress(VariantType.BOOL)
    val FROM_STRING = VariantConverters.variantFromTypeByAddress(VariantType.STRING)
    val FROM_STRING_NAME = VariantConverters.variantFromTypeByAddress(VariantType.STRING_NAME)
    val FROM_OBJECT = VariantConverters.variantFromTypeByAddress(VariantType.OBJECT)
    val NEW_NIL = GodotFFI.lookup("variant_new_nil", FunctionDescriptor.ofVoid(JAVA_LONG))
    val DESTROY = GodotFFI.lookup("variant_destroy", FunctionDescriptor.ofVoid(JAVA_LONG))
    val METHOD_BIND_CALL =
      GodotFFI.lookup(
        "object_method_bind_call",
        FunctionDescriptor.ofVoid(JAVA_LONG, JAVA_LONG, JAVA_LONG, JAVA_LONG, JAVA_LONG, JAVA_LONG),
      )
  }

  private class Stack {
    val frames = ArrayList<SignalEmitFrame>(2)
    var depth = 0
  }

  companion object {
    /** The typed signals have at most five arguments; the name is one more Variant. */
    private const val MAX_ARGUMENTS = 5
    private const val VARIANT_SIZE = 24L
    private const val POINTERS_OFFSET = (MAX_ARGUMENTS + 1) * VARIANT_SIZE // 144
    private const val RETURN_OFFSET = POINTERS_OFFSET + (MAX_ARGUMENTS + 1) * 8L // 192
    private const val ERROR_OFFSET = RETURN_OFFSET + VARIANT_SIZE // 216
    private const val SCRATCH_OFFSET = ERROR_OFFSET + 16L // 232
    private const val FRAME_SIZE = SCRATCH_OFFSET + 16L

    private val ADDRESS_SPACE = JvmSignalArgReader.ADDRESS_SPACE

    private val stacks = ThreadLocal.withInitial { Stack() }

    fun begin(instance: Long, signal: String, count: Int): SignalEmitFrame {
      val stack = stacks.get()
      val frame =
        if (stack.depth < stack.frames.size) stack.frames[stack.depth]
        else SignalEmitFrame().also { stack.frames += it }
      stack.depth++
      try {
        frame.begin(instance, signal, count)
      } catch (t: Throwable) {
        stack.depth--
        throw t
      }
      return frame
    }

    fun finish(frame: SignalEmitFrame, send: Boolean) {
      try {
        frame.finish(send)
      } finally {
        stacks.get().depth--
      }
    }
  }
}
