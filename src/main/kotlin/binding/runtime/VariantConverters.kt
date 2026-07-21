package net.multigesture.kanama.binding.runtime

import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout.ADDRESS
import java.lang.foreign.ValueLayout.JAVA_INT
import java.lang.invoke.MethodHandle
import java.util.concurrent.ConcurrentHashMap
import net.multigesture.kanama.ffi.GodotFFI

/**
 * Lazy cache for the per-type variant constructors Godot exposes.
 *
 * `get_variant_from_type_constructor(T)` returns a function pointer of signature `(variant_out,
 * typed_in)` that wraps a raw typed value into a Variant. `get_variant_to_type_constructor(T)` is
 * the reverse.
 *
 * We cache the downcall [MethodHandle] per [VariantType] so we only pay the lookup + downcallHandle
 * cost once per type per process.
 */
object VariantConverters {

  private val getFromTypeCtor by lazy {
    GodotFFI.lookup("get_variant_from_type_constructor", FunctionDescriptor.of(ADDRESS, JAVA_INT))
  }

  private val getToTypeCtor by lazy {
    GodotFFI.lookup("get_variant_to_type_constructor", FunctionDescriptor.of(ADDRESS, JAVA_INT))
  }

  private val getType by lazy {
    GodotFFI.lookup("variant_get_type", FunctionDescriptor.of(JAVA_INT, ADDRESS))
  }

  private val fromType = ConcurrentHashMap<Int, MethodHandle>()
  private val toType = ConcurrentHashMap<Int, MethodHandle>()

  /** `(GDExtensionVariantPtr, GDExtensionTypePtr) -> void` — wraps typed into variant. */
  fun variantFromType(type: VariantType): MethodHandle =
    fromType.getOrPut(type.id) {
      val addr = getFromTypeCtor.invoke(type.id) as MemorySegment
      check(addr.address() != 0L) {
        "get_variant_from_type_constructor(${type.name}) returned NULL"
      }
      GodotFFI.linker.downcallHandle(addr, FunctionDescriptor.ofVoid(ADDRESS, ADDRESS))
    }

  /** `(GDExtensionTypePtr, GDExtensionVariantPtr) -> void` — unwraps variant into typed. */
  fun variantToType(type: VariantType): MethodHandle =
    toType.getOrPut(type.id) {
      val addr = getToTypeCtor.invoke(type.id) as MemorySegment
      check(addr.address() != 0L) { "get_variant_to_type_constructor(${type.name}) returned NULL" }
      GodotFFI.linker.downcallHandle(addr, FunctionDescriptor.ofVoid(ADDRESS, ADDRESS))
    }

  fun variantTypeOf(variant: MemorySegment): VariantType? {
    val id = getType.invoke(variant) as Int
    return VariantType.entries.firstOrNull { it.id == id }
  }
}
