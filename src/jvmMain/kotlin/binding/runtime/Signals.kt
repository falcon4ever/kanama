package net.multigesture.kanama.binding.runtime

import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout.ADDRESS
import java.lang.foreign.ValueLayout.JAVA_INT
import java.lang.foreign.ValueLayout.JAVA_LONG
import net.multigesture.kanama.ffi.GodotFFI

/**
 * Emission side of `@Signal`.
 *
 * Godot exposes `Object::emit_signal` as a vararg method whose first argument is the signal's
 * StringName. We call it via the standard `classdb_get_method_bind` + `object_method_bind_call`
 * pair, packing the signal name and each Kotlin-side argument as Variants.
 *
 * The MethodBind for `Object::emit_signal` is looked up exactly once via its canonical hash (from
 * `extension_api.json` for Godot 4.7 beta 2) and cached for the rest of the process.
 */
object Signals {

  /** `sizeof(Variant)` on 64-bit (`float_64`), from `extension_api.json`. */
  private const val VARIANT_SIZE = 24L

  /** `sizeof(GDExtensionCallError)` rounded up for 8-byte alignment. */
  private const val CALL_ERROR_SIZE = 16L

  /** Canonical hash for `Object::emit_signal` in Godot 4.7 beta 2 (from `extension_api.json`). */
  private const val EMIT_SIGNAL_HASH = 4047867050L

  private val classdbGetMethodBind by lazy {
    GodotFFI.lookup(
      "classdb_get_method_bind",
      FunctionDescriptor.of(ADDRESS, ADDRESS, ADDRESS, JAVA_LONG),
    )
  }

  private val objectMethodBindCall by lazy {
    GodotFFI.lookup(
      "object_method_bind_call",
      FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, ADDRESS, JAVA_LONG, ADDRESS, ADDRESS),
    )
  }

  private val variantDestroy by lazy {
    GodotFFI.lookup("variant_destroy", FunctionDescriptor.ofVoid(ADDRESS))
  }

  private val emitSignalBind: MemorySegment by lazy {
    val objectClass = GodotStrings.makeStringName("Object")
    val methodName = GodotStrings.makeStringName("emit_signal")
    val bind =
      classdbGetMethodBind.invoke(objectClass, methodName, EMIT_SIGNAL_HASH) as MemorySegment
    check(bind.address() != 0L) {
      "classdb_get_method_bind(Object::emit_signal, hash=$EMIT_SIGNAL_HASH) returned NULL"
    }
    bind
  }

  data class Arg(val type: VariantType, val value: Any)

  fun emitAny(instance: MemorySegment, signalName: String, args: List<Any?>) {
    val signalStringName = GodotStrings.makeStringName(signalName)
    Arena.ofConfined().use { arena ->
      val totalVariantCount = 1 + args.size
      val variantBuffer = arena.allocate(VARIANT_SIZE * totalVariantCount, 8)
      val argPtrArray = arena.allocate(ADDRESS, totalVariantCount.toLong())

      val signalVariant = variantBuffer.asSlice(0, VARIANT_SIZE)
      VariantConverters.variantFromType(VariantType.STRING_NAME)
        .invoke(signalVariant, signalStringName)
      var initializedVariants = 1
      argPtrArray.setAtIndex(ADDRESS, 0L, signalVariant)

      args.forEachIndexed { i, arg ->
        val variantSlot = variantBuffer.asSlice(VARIANT_SIZE * (i + 1), VARIANT_SIZE)
        BuiltinTypes.initVariantFromAny(variantSlot, arg, arena)
        initializedVariants += 1
        argPtrArray.setAtIndex(ADDRESS, (i + 1).toLong(), variantSlot)
      }

      val retVariant = arena.allocate(VARIANT_SIZE, 8)
      val errStruct = arena.allocate(CALL_ERROR_SIZE, 8)

      try {
        objectMethodBindCall.invoke(
          emitSignalBind,
          instance,
          argPtrArray,
          totalVariantCount.toLong(),
          retVariant,
          errStruct,
        )

        val errorType = errStruct.get(JAVA_INT, 0)
        check(errorType == 0) { "emit_signal($signalName) failed: error_type=$errorType" }
      } finally {
        for (i in 0 until initializedVariants) {
          variantDestroy.invoke(variantBuffer.asSlice(VARIANT_SIZE * i, VARIANT_SIZE))
        }
        variantDestroy.invoke(retVariant)
      }
    }
  }

  fun emit(instance: MemorySegment, signalName: String, args: List<Arg>) {
    val signalStringName = GodotStrings.makeStringName(signalName)
    Arena.ofConfined().use { arena ->
      val totalVariantCount = 1 + args.size
      val variantBuffer = arena.allocate(VARIANT_SIZE * totalVariantCount, 8)
      val argPtrArray = arena.allocate(ADDRESS, totalVariantCount.toLong())

      val signalVariant = variantBuffer.asSlice(0, VARIANT_SIZE)
      VariantConverters.variantFromType(VariantType.STRING_NAME)
        .invoke(signalVariant, signalStringName)
      var initializedVariants = 1
      argPtrArray.setAtIndex(ADDRESS, 0L, signalVariant)

      args.forEachIndexed { i, arg ->
        val variantSlot = variantBuffer.asSlice(VARIANT_SIZE * (i + 1), VARIANT_SIZE)
        BuiltinTypes.initVariantFromAny(variantSlot, arg.value, arena)
        initializedVariants += 1
        argPtrArray.setAtIndex(ADDRESS, (i + 1).toLong(), variantSlot)
      }

      val retVariant = arena.allocate(VARIANT_SIZE, 8)
      val errStruct = arena.allocate(CALL_ERROR_SIZE, 8)

      try {
        objectMethodBindCall.invoke(
          emitSignalBind,
          instance,
          argPtrArray,
          totalVariantCount.toLong(),
          retVariant,
          errStruct,
        )

        val errorType = errStruct.get(JAVA_INT, 0)
        if (errorType != 0) {
          System.err.println("[kanama:kt] emit_signal($signalName) failed: error_type=$errorType")
        }
      } finally {
        for (i in 0 until initializedVariants) {
          variantDestroy.invoke(variantBuffer.asSlice(VARIANT_SIZE * i, VARIANT_SIZE))
        }
        variantDestroy.invoke(retVariant)
      }
    }
  }
}
