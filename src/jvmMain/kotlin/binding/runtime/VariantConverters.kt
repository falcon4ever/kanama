package net.multigesture.kanama.binding.runtime

import java.lang.foreign.Arena
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

  private val getObjectInstanceId by lazy {
    GodotFFI.lookup("variant_get_object_instance_id", FunctionDescriptor.of(JAVA_LONG, ADDRESS))
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

  private val fromTypeByAddress = ConcurrentHashMap<Int, MethodHandle>()

  /** `variant_from_type` for [type] on `long` addresses, `(long variant_out, long typed_in)`. */
  fun variantFromTypeByAddress(type: VariantType): MethodHandle =
    fromTypeByAddress.getOrPut(type.id) {
      val addr = getFromTypeCtor.invoke(type.id) as MemorySegment
      check(addr.address() != 0L) {
        "get_variant_from_type_constructor(${type.name}) returned NULL"
      }
      GodotFFI.downcallHandle(
        addr,
        FunctionDescriptor.ofVoid(JAVA_LONG, JAVA_LONG),
        "variant_from_type_constructor_by_address",
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

  private val construct by lazy {
    GodotFFI.lookup(
      "variant_construct",
      FunctionDescriptor.ofVoid(JAVA_INT, ADDRESS, ADDRESS, JAVA_INT, ADDRESS),
    )
  }

  /**
   * `Variant::construct([type], [variant])` into [out] (uninitialized, `BuiltinTypes.VARIANT_SIZE`
   * bytes): the conversion GDScript applies to a value of another type that it assigns to a typed
   * script property (see `BuiltinTypes.readAssignableVariant`). True when it succeeded. [out] is a
   * valid Variant either way (the engine placement-news it first); the caller destroys it.
   */
  fun construct(
    type: VariantType,
    variant: MemorySegment,
    out: MemorySegment,
    arena: Arena,
  ): Boolean {
    val args = arena.allocate(ADDRESS.byteSize(), 8L)
    args.set(ADDRESS, 0L, variant)
    // GDExtensionCallError { int32 error; int32 argument; int32 expected; }, error == 0 is OK.
    val error = arena.allocate(12L, 4L)
    construct.invoke(type.id, out, args, 1, error)
    return error.get(JAVA_INT, 0L) == 0
  }

  /** Godot's `Variant::Type` of [variant] as its integer id (no enum lookup). */
  fun variantTypeId(variant: MemorySegment): Int = getType.invoke(variant) as Int

  /**
   * The instance id an OBJECT [variant] holds (0 for any other type), read from the Variant, so it
   * is safe when the object was freed. Negative = bit 63 set = a RefCounted (Godot's
   * `ObjectID::is_ref_counted`).
   */
  fun objectInstanceIdOf(variant: MemorySegment): Long = getObjectInstanceId.invoke(variant) as Long
}
