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
    if (variantType == VariantType.STRING || variantType.name.startsWith("PACKED_")) {
      BuiltinTypes.destroyTyped(variantType, cell)
    }
  }

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
