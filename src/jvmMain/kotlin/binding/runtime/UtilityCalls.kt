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
import net.multigesture.kanama.ffi.GodotFFI
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.RID

/**
 * Desktop/Android implementation of the utility-call seam -- the `actual`s of
 * `src/commonMain/.../binding/runtime/UtilityCalls.expect.kt`, where the contract lives; the iOS
 * one is `src/iosMain/.../binding/runtime/UtilityCalls.kt`. The frame path is
 * [BuiltinFrame.callUtility].
 */
internal actual class UtilityFunction
actual constructor(private val name: String, private val hash: Long) {
  // Resolved on first call (after GodotFFI.bootstrap). A race resolves twice to the same pointer.
  private var target: MemorySegment = MemorySegment.NULL

  internal fun target(): MemorySegment {
    val resolved = target
    return if (resolved.address() != 0L) resolved else resolve()
  }

  private fun resolve(): MemorySegment {
    val fn =
      Resolve.HANDLE.invokeWithArguments(GodotStrings.makeStringName(name), hash) as MemorySegment
    check(fn.address() != 0L) { "variant_get_ptr_utility_function($name, $hash) returned NULL" }
    target = fn
    return fn
  }

  private object Resolve {
    @JvmField
    val HANDLE: MethodHandle =
      GodotFFI.lookup(
        "variant_get_ptr_utility_function",
        FunctionDescriptor.of(ADDRESS, ADDRESS, JAVA_LONG),
      )
  }
}

internal actual object UtilityCalls {
  // A typed argument or return cell: the widest is a String / Packed*Array (16 bytes) or a Variant
  // (24, 40 in a float64 build).
  private const val CELL_BYTES = 40L

  actual fun call(
    fn: UtilityFunction,
    argTypes: IntArray,
    args: Array<out Any?>,
    retType: Int,
  ): Any? {
    val target = fn.target()
    Arena.ofConfined().use { arena ->
      val count = args.size
      val variants = arrayOfNulls<MemorySegment>(count)
      val typed = arrayOfNulls<MemorySegment>(count)
      val argv = if (count == 0) MemorySegment.NULL else arena.allocate(ADDRESS, count.toLong())
      // Allocations are zeroed: a zeroed String / Packed*Array / Variant is a valid empty value,
      // which is what the utility's return encode assigns over.
      val ret = if (retType < 0) MemorySegment.NULL else arena.allocate(CELL_BYTES, 8L)
      try {
        for (i in 0 until count) {
          val variant = arena.allocate(BuiltinTypes.VARIANT_SIZE, 8L)
          BuiltinTypes.initVariantFromAny(variant, args[i], arena)
          variants[i] = variant
          val type = if (i < argTypes.size) argTypes[i] else 0
          val pointer =
            if (type == 0) {
              variant
            } else {
              val cell = arena.allocate(CELL_BYTES, 8L)
              VariantConverters.variantToType(variantType(type)).invoke(cell, variant)
              typed[i] = cell
              cell
            }
          argv.setAtIndex(ADDRESS, i.toLong(), pointer)
        }
        callExact(target, ret, argv, count)
        return decode(retType, ret, arena)
      } finally {
        for (i in 0 until count) {
          val cell = typed[i]
          if (cell != null) destroyTyped(argTypes[i], cell)
          variants[i]?.let(BuiltinTypes::destroyVariant)
        }
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
  ): Any? {
    // The thread's builtin frame holds the base, the arguments and the return (no per-call arena
    // for the common String / number shapes); a String cell is released by the frame's call, the
    // other typed cells built here are destroyed below. A builtin can re-enter Kotlin (a logger),
    // which takes the next frame of the stack, as for the value types.
    val count = args.size
    require(count <= 8) { "builtin call with $count arguments (the frame holds 8)" }
    val f = builtinFrame()
    var owned = 0 // bit i: slot i holds a cell of type cellTypes[i] to destroy after the call
    val cellTypes = IntArray(count + 1)
    var arena: Arena? = null
    fun arena(): Arena = arena ?: Arena.ofConfined().also { arena = it }
    fun put(slot: Int, type: Int, value: Any?) {
      when (type) {
        VT_STRING -> f.putString(slot, value as String)
        VT_INT -> f.putLong(slot, (value as Number).toLong())
        VT_FLOAT -> f.putDouble(slot, (value as Number).toDouble())
        VT_BOOL -> f.putBool(slot, value as Boolean)
        VT_STRING_NAME -> {
          GodotStrings.initStringName(f.slotSegment(slot), value as String)
          cellTypes[slot] = type
          owned = owned or (1 shl slot)
        }
        VT_NODE_PATH -> {
          val text = arena().allocate(8L, 8L)
          GodotStrings.initString(text, (value as NodePath).path)
          try {
            BuiltinTypes.construct(
              type = VariantType.NODE_PATH,
              dest = f.slotSegment(slot),
              constructorIndex = 2,
              args = listOf(text),
            )
          } finally {
            GodotStrings.destroyString(text)
          }
          cellTypes[slot] = type
          owned = owned or (1 shl slot)
        }
        VT_PACKED_BYTE_ARRAY -> {
          BuiltinTypes.initPackedByteArray(f.slotSegment(slot), value as ByteArray)
          cellTypes[slot] = type
          owned = owned or (1 shl slot)
        }
        VT_NIL -> {
          BuiltinTypes.initVariantFromAny(f.slotSegment(slot), value, arena())
          cellTypes[slot] = VARIANT_CELL
          owned = owned or (1 shl slot)
        }
        else -> error("boxed builtin call: unsupported Variant type $type")
      }
    }
    try {
      try {
        if (base != null) put(0, baseType, base)
        for (i in 0 until count) put(i + 1, if (i < argTypes.size) argTypes[i] else VT_NIL, args[i])
      } catch (e: Throwable) {
        // Nothing was called: hand the frame back (its call would have) and release the cells.
        f.callAborted()
        throw e
      }
      if (base == null) f.callStatic(method, count) else f.call(method, count)
      val ret = f.retSegment()
      return when (retType) {
        -1 -> null
        VT_BOOL -> ret.get(JAVA_BYTE, 0L).toInt() != 0
        VT_INT -> ret.get(JAVA_LONG, 0L)
        VT_FLOAT -> ret.get(JAVA_DOUBLE, 0L)
        VT_STRING ->
          try {
            GodotStrings.readString(ret)
          } finally {
            GodotStrings.destroyString(ret)
          }
        VT_STRING_NAME ->
          try {
            GodotStrings.readStringName(ret)
          } finally {
            BuiltinTypes.destroyTyped(VariantType.STRING_NAME, ret)
          }
        else -> decode(retType, ret, arena())
      }
    } finally {
      for (slot in 0..count) {
        if (owned and (1 shl slot) == 0) continue
        val cell = f.slotSegment(slot)
        if (cellTypes[slot] == VARIANT_CELL) BuiltinTypes.destroyVariant(cell)
        else destroyTyped(cellTypes[slot], cell)
      }
      arena?.close()
    }
  }

  // cellTypes marker of a slot holding a Variant (VT_NIL is 0, the "no cell" default).
  private const val VARIANT_CELL = -2

  private fun decode(retType: Int, ret: MemorySegment, arena: Arena): Any? =
    when (retType) {
      -1 -> null
      VariantType.BOOL.id -> ret.get(JAVA_BYTE, 0L).toInt() != 0
      VariantType.INT.id -> ret.get(JAVA_LONG, 0L)
      VariantType.FLOAT.id -> ret.get(JAVA_DOUBLE, 0L)
      VariantType.RID.id -> RID(ret.get(JAVA_LONG, 0L))
      VariantType.NIL.id ->
        try {
          BuiltinTypes.readVariantScalarOwned(ret, arena)
        } finally {
          BuiltinTypes.destroyVariant(ret)
        }
      else -> {
        val variant = arena.allocate(BuiltinTypes.VARIANT_SIZE, 8L)
        VariantConverters.variantFromType(variantType(retType)).invoke(variant, ret)
        destroyTyped(retType, ret)
        try {
          BuiltinTypes.readVariantScalarOwned(variant, arena)
        } finally {
          BuiltinTypes.destroyVariant(variant)
        }
      }
    }

  // Only the types with a destructor own memory; Object and the scalars have none.
  private fun destroyTyped(type: Int, cell: MemorySegment) {
    val variantType = variantType(type)
    if (variantType in OWNING_TYPES || variantType.name.startsWith("PACKED_")) {
      BuiltinTypes.destroyTyped(variantType, cell)
    }
  }

  private val OWNING_TYPES =
    setOf(
      VariantType.STRING,
      VariantType.STRING_NAME,
      VariantType.NODE_PATH,
      VariantType.CALLABLE,
      VariantType.SIGNAL,
      VariantType.DICTIONARY,
      VariantType.ARRAY,
    )

  private fun variantType(id: Int): VariantType =
    VariantType.entries.firstOrNull { it.id == id } ?: error("Unsupported utility Variant type $id")

  // Block-bodied, so the invokeExact is a statement with the handle's exact type.
  private fun callExact(target: MemorySegment, ret: MemorySegment, argv: MemorySegment, argc: Int) {
    Call.HANDLE.invokeExact(target, ret, argv, argc)
  }

  /** `GDExtensionPtrUtilityFunction(ret, args, argc)`, unbound: the function is argument 0. */
  private object Call {
    @JvmField
    val HANDLE: MethodHandle =
      GodotFFI.unboundDowncallHandle(
        FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, JAVA_INT),
        "variant_ptr_utility_function",
      )
  }
}
