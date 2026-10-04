package net.multigesture.kanama.binding.runtime

import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout.ADDRESS
import java.lang.foreign.ValueLayout.JAVA_INT
import java.lang.foreign.ValueLayout.JAVA_LONG
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
      GodotFFI.downcallHandle(
        addr,
        FunctionDescriptor.ofVoid(ADDRESS, ADDRESS),
        "variant_from_type_constructor",
      )
    }

  /** `(GDExtensionTypePtr, GDExtensionVariantPtr) -> void` — unwraps variant into typed. */
  fun variantToType(type: VariantType): MethodHandle =
    toType.getOrPut(type.id) {
      val addr = getToTypeCtor.invoke(type.id) as MemorySegment
      check(addr.address() != 0L) { "get_variant_to_type_constructor(${type.name}) returned NULL" }
      GodotFFI.downcallHandle(
        addr,
        FunctionDescriptor.ofVoid(ADDRESS, ADDRESS),
        "variant_to_type_constructor",
      )
    }

  private val toTypeByAddress = ConcurrentHashMap<Int, MethodHandle>()

  /**
   * `variant_to_type` for [type] taking both pointers as `long` addresses, `(long typed_out, long
   * variant)` -> void, so a caller holding raw addresses allocates no segment (task 134 D4).
   */
  fun variantToTypeByAddress(type: VariantType): MethodHandle =
    toTypeByAddress.getOrPut(type.id) {
      val addr = getToTypeCtor.invoke(type.id) as MemorySegment
      check(addr.address() != 0L) { "get_variant_to_type_constructor(${type.name}) returned NULL" }
      GodotFFI.downcallHandle(
        addr,
        FunctionDescriptor.ofVoid(JAVA_LONG, JAVA_LONG),
        "variant_to_type_constructor_by_address",
      )
    }

  /** `variant_get_type` on a Variant address held as a `long` (task 134 D4). */
  val variantTypeByAddress: MethodHandle by lazy {
    GodotFFI.lookup("variant_get_type", FunctionDescriptor.of(JAVA_INT, JAVA_LONG))
  }

  fun variantTypeOf(variant: MemorySegment): VariantType? {
    val id = variantTypeId(variant)
    return VariantType.entries.firstOrNull { it.id == id }
  }

  /** Godot's `Variant::Type` of [variant] as its integer id (no enum lookup). */
  fun variantTypeId(variant: MemorySegment): Int = getType.invoke(variant) as Int
}
