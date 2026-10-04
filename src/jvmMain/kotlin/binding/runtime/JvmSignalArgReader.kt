package net.multigesture.kanama.binding.runtime

import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout.ADDRESS
import java.lang.foreign.ValueLayout.JAVA_BYTE
import java.lang.foreign.ValueLayout.JAVA_DOUBLE
import java.lang.foreign.ValueLayout.JAVA_LONG
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.SignalArgReader
import net.multigesture.kanama.api.SignalArgumentException

/**
 * Reads a signal emission's arguments in place from Godot's `const Variant **` array (task 134 D4):
 * the typed signals decode `int`, `float`, `bool`, `String`/`StringName` and objects through one
 * per-thread scratch cell, so a 2-argument emission allocates nothing beyond the decoded values (a
 * String, a wrapper for an object, a box for a primitive handed to a generic lambda). Every other
 * type goes through [BuiltinTypes.readVariantScalar], the `GodotObject.call` decode.
 *
 * One reader per thread, reused by nested emissions: [dispatch] saves and restores the array it
 * reads, and a typed signal decodes all of its arguments before it calls the user's callback.
 */
internal class JvmSignalArgReader private constructor() : SignalArgReader {
  private var argv: MemorySegment = MemorySegment.NULL
  private var argc = 0
  private val scratch: MemorySegment = Arena.ofAuto().allocate(SCRATCH_BYTES, 16L)

  override val count: Int
    get() = argc

  /** Runs [block] reading the [count] Variants at [args]. */
  fun dispatch(args: MemorySegment, count: Int, block: (SignalArgReader) -> Unit) {
    val savedArgs = argv
    val savedCount = argc
    argv = if (count == 0) MemorySegment.NULL else args.reinterpret(count * 8L)
    argc = count
    try {
      block(this)
    } finally {
      argv = savedArgs
      argc = savedCount
    }
  }

  private fun variant(index: Int): MemorySegment {
    if (index < 0 || index >= argc) {
      throw SignalArgumentException("argument ${index + 1} was not emitted ($argc were)")
    }
    return argv.getAtIndex(ADDRESS, index.toLong()).reinterpret(BuiltinTypes.VARIANT_SIZE)
  }

  private fun mismatch(index: Int, expected: String, typeId: Int): Nothing =
    throw SignalArgumentException(
      "argument ${index + 1}: expected $expected, got ${VariantType.entries.firstOrNull { it.id == typeId }?.name ?: typeId}"
    )

  override fun long(index: Int): Long {
    val v = variant(index)
    val type = VariantConverters.variantTypeId(v)
    if (type != INT) mismatch(index, "int", type)
    Converters.TO_INT.invoke(scratch, v)
    return scratch.get(JAVA_LONG, 0)
  }

  override fun double(index: Int): Double {
    val v = variant(index)
    return when (val type = VariantConverters.variantTypeId(v)) {
      FLOAT -> {
        Converters.TO_FLOAT.invoke(scratch, v)
        scratch.get(JAVA_DOUBLE, 0)
      }
      INT -> {
        Converters.TO_INT.invoke(scratch, v)
        scratch.get(JAVA_LONG, 0).toDouble()
      }
      else -> mismatch(index, "float", type)
    }
  }

  override fun bool(index: Int): Boolean {
    val v = variant(index)
    val type = VariantConverters.variantTypeId(v)
    if (type != BOOL) mismatch(index, "bool", type)
    Converters.TO_BOOL.invoke(scratch, v)
    return scratch.get(JAVA_BYTE, 0).toInt() != 0
  }

  override fun string(index: Int): String {
    val v = variant(index)
    return when (val type = VariantConverters.variantTypeId(v)) {
      STRING -> {
        Converters.TO_STRING.invoke(scratch, v)
        try {
          GodotStrings.readString(scratch)
        } finally {
          GodotStrings.destroyString(scratch)
        }
      }
      STRING_NAME -> {
        Converters.TO_STRING_NAME.invoke(scratch, v)
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
    return when (val type = VariantConverters.variantTypeId(v)) {
      NIL -> null
      OBJECT -> {
        Converters.TO_OBJECT.invoke(scratch, v)
        val address = scratch.get(JAVA_LONG, 0)
        if (address == 0L) null else GodotHandle(MemorySegment.ofAddress(address))
      }
      else -> mismatch(index, "Object", type)
    }
  }

  override fun value(index: Int): Any? {
    val v = variant(index)
    return Arena.ofConfined().use { arena -> BuiltinTypes.readVariantScalar(v, arena) }
  }

  /** The variant-to-type converters, resolved on first use (the engine is up by then). */
  private object Converters {
    val TO_INT = VariantConverters.variantToType(VariantType.INT)
    val TO_FLOAT = VariantConverters.variantToType(VariantType.FLOAT)
    val TO_BOOL = VariantConverters.variantToType(VariantType.BOOL)
    val TO_STRING = VariantConverters.variantToType(VariantType.STRING)
    val TO_STRING_NAME = VariantConverters.variantToType(VariantType.STRING_NAME)
    val TO_OBJECT = VariantConverters.variantToType(VariantType.OBJECT)
  }

  companion object {
    /** Big enough for every type decoded in place (a pointer or a 64-bit scalar). */
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
