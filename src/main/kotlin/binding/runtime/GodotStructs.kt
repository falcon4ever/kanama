package net.multigesture.kanama.binding.runtime

import java.lang.foreign.MemoryLayout
import java.lang.foreign.ValueLayout.ADDRESS
import java.lang.foreign.ValueLayout.JAVA_BYTE
import java.lang.foreign.ValueLayout.JAVA_INT

/**
 * FFM [MemoryLayout]s for the GDExtension C structs we have to hand-build before passing them into
 * engine entry points.
 *
 * Packing is the tricky part: Godot's C structs use tightly packed uint32/uint8 pairs that the
 * 8-byte-aligned layouts don't give you for free. The layouts here match what the C compiler emits
 * for the 4.7 beta 2 header on arm64/x86_64 — verified empirically.
 */
object GodotStructs {

  /** `GDExtensionClassCreationInfo6` in the 4.7 beta 2 header. */
  val classCreationInfo: MemoryLayout =
    MemoryLayout.structLayout(
      JAVA_BYTE.withName("is_virtual"),
      JAVA_BYTE.withName("is_abstract"),
      JAVA_BYTE.withName("is_exposed"),
      JAVA_BYTE.withName("is_runtime"),
      MemoryLayout.paddingLayout(4),
      ADDRESS.withName("icon_path"),
      ADDRESS.withName("set_func"),
      ADDRESS.withName("get_func"),
      ADDRESS.withName("get_property_list_func"),
      ADDRESS.withName("free_property_list_func"),
      ADDRESS.withName("property_can_revert_func"),
      ADDRESS.withName("property_get_revert_func"),
      ADDRESS.withName("validate_property_func"),
      ADDRESS.withName("notification_func"),
      ADDRESS.withName("to_string_func"),
      ADDRESS.withName("reference_func"),
      ADDRESS.withName("unreference_func"),
      ADDRESS.withName("create_instance_func"),
      ADDRESS.withName("free_instance_func"),
      ADDRESS.withName("recreate_instance_func"),
      ADDRESS.withName("get_virtual_func"),
      ADDRESS.withName("get_virtual_call_data_func"),
      ADDRESS.withName("call_virtual_with_data_func"),
      ADDRESS.withName("class_userdata"),
    )

  /** `GDExtensionPropertyInfo` — 48 bytes on 64-bit. */
  val propertyInfo: MemoryLayout =
    MemoryLayout.structLayout(
      JAVA_INT.withName("type"),
      MemoryLayout.paddingLayout(4),
      ADDRESS.withName("name"),
      ADDRESS.withName("class_name"),
      JAVA_INT.withName("hint"),
      MemoryLayout.paddingLayout(4),
      ADDRESS.withName("hint_string"),
      JAVA_INT.withName("usage"),
      MemoryLayout.paddingLayout(4),
    )

  /**
   * `GDExtensionClassMethodInfo` — 88 bytes on 64-bit.
   *
   * Watch the packing: `method_flags` (uint32) and `has_return_value` (uint8) share the same 8-byte
   * slot — the uint8 sits at +36 and is followed by 3 bytes of padding before the next
   * 8-byte-aligned pointer. Similarly `return_value_metadata` (int) and `argument_count` (uint32)
   * both live in the same 8-byte slot.
   */
  val classMethodInfo: MemoryLayout =
    MemoryLayout.structLayout(
      ADDRESS.withName("name"),
      ADDRESS.withName("method_userdata"),
      ADDRESS.withName("call_func"),
      ADDRESS.withName("ptrcall_func"),
      JAVA_INT.withName("method_flags"),
      JAVA_BYTE.withName("has_return_value"),
      MemoryLayout.paddingLayout(3),
      ADDRESS.withName("return_value_info"),
      JAVA_INT.withName("return_value_metadata"),
      JAVA_INT.withName("argument_count"),
      ADDRESS.withName("arguments_info"),
      ADDRESS.withName("arguments_metadata"),
      JAVA_INT.withName("default_argument_count"),
      MemoryLayout.paddingLayout(4),
      ADDRESS.withName("default_arguments"),
    )

  /**
   * `GDExtensionScriptInstanceInfo3` — 26 function-pointer fields, all 8 bytes. Total: 208 bytes on
   * 64-bit. All pointers; optional ones may be NULL.
   *
   * Field order matches gdextension_interface.h exactly: set_func, get_func,
   * get_property_list_func, free_property_list_func, get_class_category_func,
   * property_can_revert_func, property_get_revert_func, get_owner_func, get_property_state_func,
   * get_method_list_func, free_method_list_func, get_property_type_func, validate_property_func,
   * has_method_func, get_method_argument_count_func, call_func, notification_func, to_string_func,
   * refcount_incremented_func, refcount_decremented_func, get_script_func, is_placeholder_func,
   * set_fallback_func, get_fallback_func, get_language_func, free_func.
   */
  val scriptInstanceInfo3: MemoryLayout =
    MemoryLayout.structLayout(
      ADDRESS.withName("set_func"),
      ADDRESS.withName("get_func"),
      ADDRESS.withName("get_property_list_func"),
      ADDRESS.withName("free_property_list_func"),
      ADDRESS.withName("get_class_category_func"),
      ADDRESS.withName("property_can_revert_func"),
      ADDRESS.withName("property_get_revert_func"),
      ADDRESS.withName("get_owner_func"),
      ADDRESS.withName("get_property_state_func"),
      ADDRESS.withName("get_method_list_func"),
      ADDRESS.withName("free_method_list_func"),
      ADDRESS.withName("get_property_type_func"),
      ADDRESS.withName("validate_property_func"),
      ADDRESS.withName("has_method_func"),
      ADDRESS.withName("get_method_argument_count_func"),
      ADDRESS.withName("call_func"),
      ADDRESS.withName("notification_func"),
      ADDRESS.withName("to_string_func"),
      ADDRESS.withName("refcount_incremented_func"),
      ADDRESS.withName("refcount_decremented_func"),
      ADDRESS.withName("get_script_func"),
      ADDRESS.withName("is_placeholder_func"),
      ADDRESS.withName("set_fallback_func"),
      ADDRESS.withName("get_fallback_func"),
      ADDRESS.withName("get_language_func"),
      ADDRESS.withName("free_func"),
    )

  fun offsetOf(layout: MemoryLayout, field: String): Long =
    layout.byteOffset(MemoryLayout.PathElement.groupElement(field))
}
