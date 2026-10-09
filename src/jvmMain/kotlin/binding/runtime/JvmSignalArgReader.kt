package net.multigesture.kanama.binding.runtime

import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout.JAVA_BYTE
import java.lang.foreign.ValueLayout.JAVA_DOUBLE
import java.lang.foreign.ValueLayout.JAVA_LONG
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.SignalArgReader
import net.multigesture.kanama.api.SignalArgumentException

/**
 * Reads a signal emission's arguments in place from Godot's `const Variant **` array (task 134 D4).
 * The array is read through one segment per emission (sized to the arguments), each Variant is
 * addressed as a `long` (the converters take `long` arguments), and `int`, `float`, `bool`,
 * `String`/`StringName` and objects are converted through one per-thread scratch cell, so reading
 * an argument allocates nothing but the value itself (a String, a wrapper for an object). Every
 * other type goes through [BuiltinTypes.readVariantScalar], the `GodotObject.call` decode.
 *
 * One reader per thread, reused by nested emissions: [dispatch] saves and restores the array it
 * reads, and a typed signal decodes all of its arguments before it calls the user's callback.
 */
internal class JvmSignalArgReader private constructor() : SignalArgReader {
  private var argv: MemorySegment = MemorySegment.NULL
  private var argc = 0
  private val scratch: MemorySegment = Arena.ofAuto().allocate(SCRATCH_BYTES, 16L)
  private val scratchAddress = scratch.address()

  override val count: Int
    get() = argc

  /**
   * Runs [block] reading the [count] Variants whose pointers are at [args] (Godot's zero-length
   * upcall segment). The array is read at offsets relative to [args], never at its absolute
   * address: an Android heap pointer is tagged (top byte `0xB4`) and negative as a signed `long`,
   * which no segment offset can be (task 138 item 21).
   */
  fun dispatch(args: MemorySegment, count: Int, block: (SignalArgReader) -> Unit) {
    val savedArgs = argv
    val savedCount = argc
    argv = if (count > 0) args.reinterpret(count * 8L) else MemorySegment.NULL
    argc = count
    try {
      block(this)
    } finally {
      argv = savedArgs
      argc = savedCount
    }
  }

  /** The address of argument [index]'s Variant. */
  private fun variant(index: Int): Long {
    if (index < 0 || index >= argc) {
      throw SignalArgumentException("argument ${index + 1} was not emitted ($argc were)")
    }
    return argv.get(JAVA_LONG, index * 8L)
  }

  private fun typeOf(variant: Long): Int = Native.GET_TYPE.invoke(variant) as Int

  private fun mismatch(index: Int, expected: String, typeId: Int): Nothing =
    throw SignalArgumentException(
      "argument ${index + 1}: expected $expected, got ${VariantType.entries.firstOrNull { it.id == typeId }?.name ?: typeId}"
    )

  override fun long(index: Int): Long {
    val v = variant(index)
    val type = typeOf(v)
    if (type != INT) mismatch(index, "int", type)
    Native.TO_INT.invoke(scratchAddress, v)
    return scratch.get(JAVA_LONG, 0)
  }

  override fun double(index: Int): Double {
    val v = variant(index)
    return when (val type = typeOf(v)) {
      FLOAT -> {
        Native.TO_FLOAT.invoke(scratchAddress, v)
        scratch.get(JAVA_DOUBLE, 0)
      }
      INT -> {
        Native.TO_INT.invoke(scratchAddress, v)
        scratch.get(JAVA_LONG, 0).toDouble()
      }
      else -> mismatch(index, "float", type)
    }
  }

  override fun bool(index: Int): Boolean {
    val v = variant(index)
    val type = typeOf(v)
    if (type != BOOL) mismatch(index, "bool", type)
    Native.TO_BOOL.invoke(scratchAddress, v)
    return scratch.get(JAVA_BYTE, 0).toInt() != 0
  }

  override fun string(index: Int): String {
    val v = variant(index)
    return when (val type = typeOf(v)) {
      STRING -> {
        Native.TO_STRING.invoke(scratchAddress, v)
        try {
          GodotStrings.readString(scratch)
        } finally {
          GodotStrings.destroyString(scratch)
        }
      }
      STRING_NAME -> {
        Native.TO_STRING_NAME.invoke(scratchAddress, v)
        try {
          GodotStrings.readStringName(scratch)
        } finally {
          BuiltinTypes.destroyTyped(VariantType.STRING_NAME, scratch)
        }
      }
      else -> mismatch(index, "String", type)
    }
  }

  override fun objectHandle(index: Int): GodotHandle? {
    val v = variant(index)
    return when (val type = typeOf(v)) {
      NIL -> null
      OBJECT -> {
        Native.TO_OBJECT.invoke(scratchAddress, v)
        val address = scratch.get(JAVA_LONG, 0)
        if (address == 0L) null else GodotHandle(MemorySegment.ofAddress(address))
      }
      else -> mismatch(index, "Object", type)
    }
  }

  override fun value(index: Int): Any? {
    val v = MemorySegment.ofAddress(variant(index)).reinterpret(BuiltinTypes.VARIANT_SIZE)
    return Arena.ofConfined().use { arena -> BuiltinTypes.readVariantScalar(v, arena) }
  }

  /** The address-based converters, resolved on first use (the engine is up by then). */
  private object Native {
    val GET_TYPE = VariantConverters.variantTypeByAddress
    val TO_INT = VariantConverters.variantToTypeByAddress(VariantType.INT)
    val TO_FLOAT = VariantConverters.variantToTypeByAddress(VariantType.FLOAT)
    val TO_BOOL = VariantConverters.variantToTypeByAddress(VariantType.BOOL)
    val TO_STRING = VariantConverters.variantToTypeByAddress(VariantType.STRING)
    val TO_STRING_NAME = VariantConverters.variantToTypeByAddress(VariantType.STRING_NAME)
    val TO_OBJECT = VariantConverters.variantToTypeByAddress(VariantType.OBJECT)
  }

  companion object {
    /** Big enough for every type converted in place (a pointer or a 64-bit scalar). */
    private const val SCRATCH_BYTES = 16L
    private const val NIL = 0
    private const val BOOL = 1
    private const val INT = 2
    private const val FLOAT = 3
    private const val STRING = 4
    private const val STRING_NAME = 21
    private const val OBJECT = 24

    private val readers = ThreadLocal.withInitial { JvmSignalArgReader() }

    /** This thread's reader. */
    fun current(): JvmSignalArgReader = readers.get()
  }
}
