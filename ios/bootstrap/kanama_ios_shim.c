/*
 * Kanama iOS GDExtension bootstrap.
 *
 * iOS cannot use the desktop JVM/Panama bootstrap. This shim keeps the Godot
 * entry point and ABI-sensitive Godot structs in C, and delegates runtime
 * state to a Kotlin/Native static library linked into the same xcframework.
 */

#include <limits.h>
#include <dlfcn.h>
#include <objc/message.h>
#include <objc/runtime.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "gdextension_interface.h"

extern int32_t kanama_ios_runtime_entry(uintptr_t p_get_proc_address, uintptr_t p_library, uintptr_t r_initialization);
extern void kanama_ios_runtime_initialize(int32_t level);
extern void kanama_ios_runtime_deinitialize(int32_t level);
extern void kanama_ios_runtime_frame(void);
extern int64_t kanama_ios_runtime_script_resource_create(const char *path);
extern void kanama_ios_runtime_script_resource_free(int64_t script_handle);
extern void kanama_ios_runtime_script_resource_base_type(int64_t script_handle, char *buffer, int32_t buffer_size);
extern int32_t kanama_ios_runtime_script_resource_method_count(int64_t script_handle);
extern void kanama_ios_runtime_script_resource_method_name(
    int64_t script_handle,
    int32_t method_index,
    char *buffer,
    int32_t buffer_size
);
extern int32_t kanama_ios_runtime_script_resource_method_argument_count(
    int64_t script_handle,
    int32_t method_index
);
extern int32_t kanama_ios_runtime_script_resource_property_count(int64_t script_handle);
extern void kanama_ios_runtime_script_resource_property_name(
    int64_t script_handle,
    int32_t property_index,
    char *buffer,
    int32_t buffer_size
);
// Godot Variant::Type of property [property_index] (so the script instance can advertise its
// @ScriptProperty list to the engine — needed for scene-stored values to be applied).
extern int32_t kanama_ios_runtime_script_resource_property_type(
    int64_t script_handle,
    int32_t property_index
);
extern int32_t kanama_ios_runtime_script_resource_signal_count(int64_t script_handle);
extern void kanama_ios_runtime_script_resource_signal_name(
    int64_t script_handle,
    int32_t signal_index,
    char *buffer,
    int32_t buffer_size
);
extern int32_t kanama_ios_runtime_script_resource_rpc_config_count(int64_t script_handle);
extern void kanama_ios_runtime_script_resource_rpc_config_method_name(
    int64_t script_handle,
    int32_t rpc_config_index,
    char *buffer,
    int32_t buffer_size
);
extern int32_t kanama_ios_runtime_script_resource_rpc_config_mode(
    int64_t script_handle,
    int32_t rpc_config_index
);
extern int32_t kanama_ios_runtime_script_resource_rpc_config_call_local(
    int64_t script_handle,
    int32_t rpc_config_index
);
extern int32_t kanama_ios_runtime_script_resource_rpc_config_transfer_mode(
    int64_t script_handle,
    int32_t rpc_config_index
);
extern int32_t kanama_ios_runtime_script_resource_rpc_config_channel(
    int64_t script_handle,
    int32_t rpc_config_index
);
extern int64_t kanama_ios_runtime_script_instance_create(int64_t script_handle, int64_t owner_object);
extern void kanama_ios_runtime_script_instance_ready(int64_t instance_handle);
// Generic per-signature inbound call: the C callback marshals every Variant arg into a
// PT-tagged buffer (the inverse of kanama_ios_godot_object_call) and the Kotlin runtime decodes
// each arg by its tag and dispatches the script method. Replaces the enumerated call_* exports.
// ret_tag_out/ret_buf_out (Phase 5.3b): the Kotlin runtime writes the value-returning method's
// result as PT-tagged bytes (the inverse of the inbound arg marshalling); the C callback then
// builds the engine return Variant via kanama_ios_pt_arg_to_variant. ret_tag_out is set to
// KANAMA_IOS_PT_VOID when the method returns nothing marshalled. Pass NULL/NULL to ignore returns.
extern int32_t kanama_ios_runtime_script_instance_call_v(
    int64_t instance_handle,
    int32_t method_index,
    const int32_t *arg_tags,
    const void *const *arg_ptrs,
    int32_t arg_count,
    int32_t *ret_tag_out,
    void *ret_buf_out
);
extern int32_t kanama_ios_runtime_script_instance_set_property(
    int64_t instance_handle,
    int32_t property_index,
    int64_t value
);
extern int32_t kanama_ios_runtime_script_instance_set_property_array(
    int64_t instance_handle,
    int32_t property_index,
    const int64_t *objects,
    int32_t count
);
extern int32_t kanama_ios_runtime_script_instance_set_property_string(
    int64_t instance_handle,
    int32_t property_index,
    const char *value
);
// `List<String>` (Godot PackedStringArray) @ScriptProperty delivery. The C dispatch extracts
// each packed-array element to a utf8 C string and passes them here; the Kotlin runtime hands
// a List<String> to the generated setPropertyStringArray bridge branch.
extern int32_t kanama_ios_runtime_script_instance_set_property_string_array(
    int64_t instance_handle,
    int32_t property_index,
    const char *const *strings,
    int32_t count
);
// Value-type (NodePath/Vector2/Vector3/Color) @ScriptProperty delivery: the C side extracts
// the Variant into a PT-tagged raw byte buffer and hands it across. NODE_PATH ships its utf8
// path bytes (PT_NODE_PATH); Vector2/3/Color ship their float32 components (PT_VECTOR2/
// PT_VECTOR3/PT_COLOR). Mirrors the value-type method-call marshalling.
extern int32_t kanama_ios_runtime_script_instance_set_property_value(
    int64_t instance_handle,
    int32_t property_index,
    int32_t pt_tag,
    const uint8_t *bytes,
    int32_t length
);
extern void kanama_ios_runtime_script_instance_free(int64_t instance_handle);
extern void kanama_ios_runtime_dispatch_callable(
    int64_t callback_id,
    int32_t argument_count,
    int64_t arg0,
    int64_t arg1,
    int64_t arg2,
    int64_t arg3
);
extern void kanama_ios_runtime_release_callable(int64_t callback_id);
extern void kanama_ios_runtime_objectcalls_selftest(void);

typedef enum {
    KANAMA_IOS_CLASS_SCRIPT_LANGUAGE = 1,
    KANAMA_IOS_CLASS_SCRIPT = 2,
    KANAMA_IOS_CLASS_RESOURCE_LOADER = 3,
} KanamaIosClassKind;

typedef enum {
    KANAMA_IOS_VIRTUAL_STRING_NAME_EMPTY = 1,
    KANAMA_IOS_VIRTUAL_STRING_NAME_LABEL,
    KANAMA_IOS_VIRTUAL_STRING_EMPTY,
    KANAMA_IOS_VIRTUAL_STRING_NAME,
    KANAMA_IOS_VIRTUAL_STRING_TYPE,
    KANAMA_IOS_VIRTUAL_STRING_EXTENSION,
    KANAMA_IOS_VIRTUAL_STRING_SOURCE,
    KANAMA_IOS_VIRTUAL_PACKED_EXTENSIONS,
    KANAMA_IOS_VIRTUAL_PACKED_EMPTY,
    KANAMA_IOS_VIRTUAL_ARRAY_EMPTY,
    KANAMA_IOS_VIRTUAL_DICTIONARY_EMPTY,
    KANAMA_IOS_VIRTUAL_VARIANT_NIL,
    KANAMA_IOS_VIRTUAL_BOOL_TRUE,
    KANAMA_IOS_VIRTUAL_BOOL_FALSE,
    KANAMA_IOS_VIRTUAL_INT_ZERO,
    KANAMA_IOS_VIRTUAL_INT_NEGATIVE_ONE,
    KANAMA_IOS_VIRTUAL_VOID,
    KANAMA_IOS_VIRTUAL_LANGUAGE_CREATE_SCRIPT,
    KANAMA_IOS_VIRTUAL_SCRIPT_GET_LANGUAGE,
    KANAMA_IOS_VIRTUAL_SCRIPT_INSTANCE_BASE_TYPE,
    KANAMA_IOS_VIRTUAL_SCRIPT_INSTANCE_CREATE,
    KANAMA_IOS_VIRTUAL_SCRIPT_HAS_METHOD,
    KANAMA_IOS_VIRTUAL_SCRIPT_HAS_SIGNAL,
    KANAMA_IOS_VIRTUAL_SCRIPT_METHOD_ARG_COUNT,
    KANAMA_IOS_VIRTUAL_SCRIPT_RPC_CONFIG,
    KANAMA_IOS_VIRTUAL_RESOURCE_GET_TYPE,
    KANAMA_IOS_VIRTUAL_RESOURCE_LOAD,
} KanamaIosVirtualId;

typedef struct {
    char *method_name_text;
    int32_t mode;
    int32_t call_local;
    int32_t transfer_mode;
    int32_t channel;
} KanamaIosRpcConfig;

typedef struct {
    KanamaIosClassKind kind;
    GDExtensionObjectPtr godot_object;
    int64_t script_handle;
    char *script_path;
    char *script_base_type_text;
    uint64_t script_base_type;
    uint64_t *script_method_names;
    char **script_method_name_texts;
    int32_t script_method_count;
    uint64_t *script_property_names;
    int32_t *script_property_types;
    int32_t script_property_count;
    uint64_t *script_signal_names;
    char **script_signal_name_texts;
    int32_t script_signal_count;
    KanamaIosRpcConfig *script_rpc_configs;
    int32_t script_rpc_config_count;
} KanamaIosExtensionInstance;

typedef struct {
    int64_t runtime_handle;
    GDExtensionObjectPtr owner_object;
    GDExtensionObjectPtr script_object;
    KanamaIosExtensionInstance *script;
    GDExtensionObjectPtr referenced_objects[16];
    int referenced_object_count;
} KanamaIosScriptInstance;

static int g_kanama_ios_initialized = 0;
static int g_kanama_ios_audio_session_configured = 0;
static GDExtensionInterfaceGetProcAddress g_get_proc_address = NULL;
static GDExtensionClassLibraryPtr g_library = NULL;
static GDExtensionInterfaceCallableCustomCreate2 g_callable_custom_create2 = NULL;

static GDExtensionInterfaceStringNameNewWithUtf8Chars g_string_name_new = NULL;
static GDExtensionInterfaceStringNewWithUtf8Chars g_string_new = NULL;
static GDExtensionInterfaceStringToUtf8Chars g_string_to_utf8_chars = NULL;
static GDExtensionInterfaceVariantGetPtrDestructor g_variant_get_ptr_destructor = NULL;
static GDExtensionInterfaceVariantGetPtrConstructor g_variant_get_ptr_constructor = NULL;
static GDExtensionInterfaceVariantGetPtrBuiltinMethod g_variant_get_ptr_builtin_method = NULL;
static GDExtensionInterfaceVariantGetPtrKeyedSetter g_variant_get_ptr_keyed_setter = NULL;
// Keyed getter is used only by the debug self-test (kanama_ios_ptrcall_selftest) to read back
// the rpc-config Dictionary; it is intentionally NOT part of the mandatory init gate.
static GDExtensionInterfaceVariantGetPtrKeyedGetter g_variant_get_ptr_keyed_getter = NULL;
static GDExtensionInterfaceGetVariantFromTypeConstructor g_get_variant_from_type_constructor = NULL;
static GDExtensionInterfaceGetVariantToTypeConstructor g_get_variant_to_type_constructor = NULL;
static GDExtensionInterfaceVariantDestroy g_variant_destroy = NULL;
static GDExtensionInterfaceVariantGetType g_variant_get_type = NULL;
static GDExtensionInterfaceVariantNewNil g_variant_new_nil = NULL;
static GDExtensionPtrDestructor g_string_name_destructor = NULL;
static GDExtensionPtrDestructor g_string_destructor = NULL;
static GDExtensionInterfaceGlobalGetSingleton g_global_get_singleton = NULL;
static GDExtensionInterfaceClassdbGetMethodBind g_classdb_get_method_bind = NULL;
static GDExtensionInterfaceClassdbConstructObject3 g_classdb_construct_object = NULL;
static GDExtensionInterfaceClassdbRegisterExtensionClass6 g_classdb_register_extension_class = NULL;
static GDExtensionInterfaceClassdbUnregisterExtensionClass g_classdb_unregister_extension_class = NULL;
static GDExtensionInterfaceObjectSetInstance g_object_set_instance = NULL;
static GDExtensionInterfaceObjectMethodBindCall g_object_method_bind_call = NULL;
static GDExtensionInterfaceObjectMethodBindPtrcall g_object_method_bind_ptrcall = NULL;
static GDExtensionInterfaceScriptInstanceCreate3 g_script_instance_create = NULL;
static GDExtensionInterfaceRegisterMainLoopCallbacks g_register_main_loop_callbacks = NULL;

static GDExtensionObjectPtr g_engine_singleton = NULL;
static GDExtensionObjectPtr g_resource_loader_singleton = NULL;
static GDExtensionObjectPtr g_script_language_object = NULL;
static GDExtensionObjectPtr g_resource_loader_object = NULL;
static GDExtensionMethodBindPtr g_object_notification_bind = NULL;
static GDExtensionMethodBindPtr g_engine_get_main_loop_bind = NULL;
static GDExtensionMethodBindPtr g_engine_register_script_language_bind = NULL;
static GDExtensionMethodBindPtr g_engine_unregister_script_language_bind = NULL;
static GDExtensionMethodBindPtr g_resource_loader_add_format_loader_bind = NULL;
static GDExtensionMethodBindPtr g_resource_loader_remove_format_loader_bind = NULL;

static GDExtensionMethodBindPtr g_scene_tree_get_first_node_in_group_bind = NULL;
static GDExtensionMethodBindPtr g_label_set_text_bind = NULL;
static GDExtensionMethodBindPtr g_node_add_child_bind = NULL;
static GDExtensionMethodBindPtr g_node_remove_child_bind = NULL;
static GDExtensionMethodBindPtr g_node_get_child_count_bind = NULL;
static GDExtensionMethodBindPtr g_node_get_child_bind = NULL;
static GDExtensionMethodBindPtr g_node_get_node_or_null_bind = NULL;
static GDExtensionMethodBindPtr g_node_get_tree_bind = NULL;
static GDExtensionMethodBindPtr g_node_get_viewport_bind = NULL;
static GDExtensionMethodBindPtr g_node_create_tween_bind = NULL;
static GDExtensionMethodBindPtr g_node_queue_free_bind = NULL;
static GDExtensionMethodBindPtr g_node_set_process_bind = NULL;
static GDExtensionMethodBindPtr g_node_set_physics_process_bind = NULL;
static GDExtensionMethodBindPtr g_node_set_process_input_bind = NULL;
static GDExtensionMethodBindPtr g_node_set_process_unhandled_input_bind = NULL;
static GDExtensionMethodBindPtr g_node_set_process_shortcut_input_bind = NULL;
static GDExtensionMethodBindPtr g_node_set_process_unhandled_key_input_bind = NULL;
static GDExtensionMethodBindPtr g_object_is_class_bind = NULL;
static GDExtensionMethodBindPtr g_node_is_in_group_bind = NULL;
static GDExtensionMethodBindPtr g_input_event_is_pressed_bind = NULL;
static GDExtensionMethodBindPtr g_input_event_is_released_bind = NULL;
static GDExtensionMethodBindPtr g_input_event_mouse_button_get_button_index_bind = NULL;
static GDExtensionMethodBindPtr g_node2d_set_position_bind = NULL;
static GDExtensionMethodBindPtr g_node2d_get_position_bind = NULL;
static GDExtensionMethodBindPtr g_node2d_set_scale_bind = NULL;
static GDExtensionMethodBindPtr g_node2d_get_scale_bind = NULL;
static GDExtensionMethodBindPtr g_node3d_set_position_bind = NULL;
static GDExtensionMethodBindPtr g_node3d_get_position_bind = NULL;
static GDExtensionMethodBindPtr g_node3d_set_rotation_bind = NULL;
static GDExtensionMethodBindPtr g_node3d_get_rotation_bind = NULL;
static GDExtensionMethodBindPtr g_node3d_set_scale_bind = NULL;
static GDExtensionMethodBindPtr g_node3d_get_scale_bind = NULL;
static GDExtensionMethodBindPtr g_node3d_set_global_position_bind = NULL;
static GDExtensionMethodBindPtr g_node3d_get_global_position_bind = NULL;
static GDExtensionMethodBindPtr g_node3d_rotate_y_bind = NULL;
static GDExtensionMethodBindPtr g_canvas_item_get_viewport_rect_bind = NULL;
static GDExtensionMethodBindPtr g_canvas_item_get_local_mouse_position_bind = NULL;
static GDExtensionMethodBindPtr g_canvas_item_hide_bind = NULL;
static GDExtensionMethodBindPtr g_canvas_item_show_bind = NULL;
static GDExtensionMethodBindPtr g_canvas_item_set_modulate_bind = NULL;
static GDExtensionMethodBindPtr g_packed_scene_instantiate_bind = NULL;
static GDExtensionMethodBindPtr g_ref_counted_reference_bind = NULL;
static GDExtensionMethodBindPtr g_ref_counted_unreference_bind = NULL;
static GDExtensionMethodBindPtr g_gpu_particles2d_set_emitting_bind = NULL;
static GDExtensionMethodBindPtr g_gpu_particles2d_set_lifetime_bind = NULL;
static GDExtensionMethodBindPtr g_gpu_particles2d_restart_bind = NULL;
static GDExtensionMethodBindPtr g_gpu_particles3d_set_emitting_bind = NULL;
static GDExtensionMethodBindPtr g_gpu_particles3d_restart_bind = NULL;
static GDExtensionMethodBindPtr g_collision_shape3d_set_disabled_bind = NULL;
static GDExtensionMethodBindPtr g_resource_loader_load_bind = NULL;
static GDExtensionMethodBindPtr g_sprite2d_set_texture_bind = NULL;
static GDExtensionMethodBindPtr g_audio_set_stream_bind = NULL;
static GDExtensionMethodBindPtr g_audio_set_volume_db_bind = NULL;
static GDExtensionMethodBindPtr g_audio_set_pitch_scale_bind = NULL;
static GDExtensionMethodBindPtr g_audio_set_bus_bind = NULL;
static GDExtensionMethodBindPtr g_audio_set_stream_paused_bind = NULL;
static GDExtensionMethodBindPtr g_audio_play_bind = NULL;
static GDExtensionMethodBindPtr g_object_emit_signal_bind = NULL;
static GDExtensionMethodBindPtr g_object_connect_bind = NULL;
static GDExtensionMethodBindPtr g_object_disconnect_bind = NULL;
static GDExtensionMethodBindPtr g_tween_tween_property_bind = NULL;
static GDExtensionMethodBindPtr g_tween_tween_callback_bind = NULL;
static GDExtensionMethodBindPtr g_tween_tween_method_bind = NULL;
static GDExtensionMethodBindPtr g_property_tweener_from_bind = NULL;
static GDExtensionMethodBindPtr g_tween_set_parallel_bind = NULL;
static GDExtensionMethodBindPtr g_tween_kill_bind = NULL;
static GDExtensionMethodBindPtr g_property_tweener_set_trans_bind = NULL;
static GDExtensionMethodBindPtr g_property_tweener_set_ease_bind = NULL;
static GDExtensionMethodBindPtr g_viewport_get_visible_rect_bind = NULL;
static GDExtensionPtrConstructor g_node_path_from_string_constructor = NULL;
static GDExtensionPtrConstructor g_string_from_string_name_constructor = NULL;
static GDExtensionPtrConstructor g_packed_string_array_constructor = NULL;
static GDExtensionPtrConstructor g_array_constructor = NULL;
static GDExtensionPtrConstructor g_dictionary_constructor = NULL;
static GDExtensionPtrKeyedSetter g_dictionary_keyed_setter = NULL;
// Debug-self-test only (read-back of the rpc-config Dictionary); not in the mandatory init gate.
static GDExtensionPtrKeyedGetter g_dictionary_keyed_getter = NULL;
static GDExtensionTypeFromVariantConstructorFunc g_variant_to_dictionary = NULL;
static GDExtensionPtrDestructor g_node_path_destructor = NULL;
static GDExtensionPtrDestructor g_dictionary_destructor = NULL;
static GDExtensionPtrBuiltInMethod g_packed_string_array_push_back = NULL;
// PackedStringArray read-back (ptrcall return -> List<String>): variable-length elements.
static GDExtensionPtrDestructor g_packed_string_array_destructor = NULL;
static GDExtensionPtrBuiltInMethod g_packed_string_array_size_method = NULL;
static GDExtensionInterfacePackedStringArrayOperatorIndexConst
    g_packed_string_array_operator_index_const = NULL;
static GDExtensionTypeFromVariantConstructorFunc g_variant_to_bool = NULL;
static GDExtensionTypeFromVariantConstructorFunc g_variant_to_string = NULL;
static GDExtensionTypeFromVariantConstructorFunc g_variant_to_float = NULL;
static GDExtensionTypeFromVariantConstructorFunc g_variant_to_object = NULL;
static GDExtensionTypeFromVariantConstructorFunc g_variant_to_int = NULL;
static GDExtensionTypeFromVariantConstructorFunc g_variant_to_vector2i = NULL;
static GDExtensionTypeFromVariantConstructorFunc g_variant_to_array = NULL;
// Value-type @ScriptProperty extraction (set-property value path).
static GDExtensionTypeFromVariantConstructorFunc g_variant_to_vector2 = NULL;
static GDExtensionTypeFromVariantConstructorFunc g_variant_to_vector3 = NULL;
static GDExtensionTypeFromVariantConstructorFunc g_variant_to_color = NULL;
static GDExtensionTypeFromVariantConstructorFunc g_variant_to_node_path = NULL;
static GDExtensionTypeFromVariantConstructorFunc g_variant_to_string_name = NULL;
static GDExtensionTypeFromVariantConstructorFunc g_variant_to_plane = NULL;
static GDExtensionTypeFromVariantConstructorFunc g_variant_to_packed_string_array = NULL;
static GDExtensionPtrConstructor g_string_from_node_path_constructor = NULL;
static GDExtensionPtrBuiltInMethod g_array_size_method = NULL;
static GDExtensionPtrBuiltInMethod g_array_get_method = NULL;
// Array destructor (variant_get_ptr_destructor(ARRAY)) — frees the Array opaque produced by a
// typed-object-array ptrcall return before the helper unwinds.
static GDExtensionPtrDestructor g_array_destructor = NULL;
// PackedInt32Array read-back (ptrcall return -> List<Int>). size is the no-arg
// "size" builtin method; operator_index_const yields a pointer to element i.
static GDExtensionPtrDestructor g_packed_int32_array_destructor = NULL;
static GDExtensionPtrBuiltInMethod g_packed_int32_array_size_method = NULL;
static GDExtensionInterfacePackedInt32ArrayOperatorIndexConst
    g_packed_int32_array_operator_index_const = NULL;
// PackedFloat32Array read-back (ptrcall return -> List<Float>), same shape as int32.
static GDExtensionPtrDestructor g_packed_float32_array_destructor = NULL;
static GDExtensionPtrBuiltInMethod g_packed_float32_array_size_method = NULL;
static GDExtensionInterfacePackedFloat32ArrayOperatorIndexConst
    g_packed_float32_array_operator_index_const = NULL;
// PackedFloat32Array build path (List<Float> -> ptrcall arg): default constructor + push_back.
static GDExtensionPtrConstructor g_packed_float32_array_constructor = NULL;
static GDExtensionPtrBuiltInMethod g_packed_float32_array_push_back = NULL;
// PackedVector2Array / PackedColorArray read-back. operator_index_const yields a pointer
// to the element (Vector2 = 2 float32, Color = 4 float32 on iOS real_t=float32).
static GDExtensionPtrDestructor g_packed_vector2_array_destructor = NULL;
static GDExtensionPtrBuiltInMethod g_packed_vector2_array_size_method = NULL;
static GDExtensionInterfacePackedVector2ArrayOperatorIndexConst
    g_packed_vector2_array_operator_index_const = NULL;
static GDExtensionPtrDestructor g_packed_color_array_destructor = NULL;
static GDExtensionPtrBuiltInMethod g_packed_color_array_size_method = NULL;
static GDExtensionInterfacePackedColorArrayOperatorIndexConst
    g_packed_color_array_operator_index_const = NULL;
// Build path (List -> ptrcall arg) for PackedVector2Array / PackedColorArray.
static GDExtensionPtrConstructor g_packed_vector2_array_constructor = NULL;
static GDExtensionPtrBuiltInMethod g_packed_vector2_array_push_back = NULL;
static GDExtensionPtrConstructor g_packed_color_array_constructor = NULL;
static GDExtensionPtrBuiltInMethod g_packed_color_array_push_back = NULL;
static GDExtensionVariantFromTypeConstructorFunc g_variant_from_vector2i = NULL;
static GDExtensionPtrConstructor g_callable_object_method_constructor = NULL;
static GDExtensionVariantFromTypeConstructorFunc g_variant_from_callable = NULL;
static GDExtensionPtrDestructor g_callable_destructor = NULL;
static GDExtensionPtrBuiltInMethod g_callable_bindv = NULL;   // Callable.bindv(Array) -> Callable
static GDExtensionPtrBuiltInMethod g_array_push_back = NULL;  // Array.push_back(Variant)
static GDExtensionVariantFromTypeConstructorFunc g_variant_from_vector2 = NULL;
static GDExtensionVariantFromTypeConstructorFunc g_variant_from_vector3 = NULL;
static GDExtensionVariantFromTypeConstructorFunc g_variant_from_color = NULL;
static GDExtensionVariantFromTypeConstructorFunc g_variant_from_node_path = NULL;
static GDExtensionVariantFromTypeConstructorFunc g_variant_from_float = NULL;
static GDExtensionVariantFromTypeConstructorFunc g_variant_from_object = NULL;
static GDExtensionVariantFromTypeConstructorFunc g_variant_from_string_name = NULL;
static GDExtensionVariantFromTypeConstructorFunc g_variant_from_int = NULL;
static GDExtensionVariantFromTypeConstructorFunc g_variant_from_bool = NULL;
static GDExtensionVariantFromTypeConstructorFunc g_variant_from_string = NULL;
static GDExtensionVariantFromTypeConstructorFunc g_variant_from_dictionary = NULL;
static GDExtensionVariantFromTypeConstructorFunc g_variant_from_packed_string_array = NULL;
static int g_main_loop_callbacks_registered = 0;
static int g_main_loop_callbacks_active = 0;
static int g_main_loop_callback_frame_count = 0;
static int g_input_toggle_pending = 0;
static int g_input_toggle_done = 0;
static int g_input_toggle_frame = 0;
static GDExtensionObjectPtr g_input_toggle_nodes[64];
static int g_input_toggle_node_count = 0;
static int g_ios_script_classes_registered = 0;

static uint64_t g_name_KanamaIosScriptLanguage = 0;
static uint64_t g_name_KanamaIosScript = 0;
static uint64_t g_name_KanamaIosResourceFormatLoader = 0;
static uint64_t g_name_ScriptLanguageExtension = 0;
static uint64_t g_name_ScriptExtension = 0;
static uint64_t g_name_ResourceFormatLoader = 0;
static uint64_t g_name_Label = 0;
static uint64_t g_name__ready = 0;
static uint64_t g_name__process = 0;
static uint64_t g_name__physics_process = 0;
static uint64_t g_name__input = 0;
static uint64_t g_name__unhandled_input = 0;
static uint64_t g_name__shortcut_input = 0;
static uint64_t g_name__unhandled_key_input = 0;
static uint64_t g_name__enter_tree = 0;
static uint64_t g_name__exit_tree = 0;
static uint64_t g_name__get_name = 0;
static uint64_t g_name__get_type = 0;
static uint64_t g_name__get_extension = 0;
static uint64_t g_name__get_recognized_extensions = 0;
static uint64_t g_name__create_script = 0;
static uint64_t g_name__make_template = 0;
static uint64_t g_name__is_valid = 0;
static uint64_t g_name__can_instantiate = 0;
static uint64_t g_name__get_language = 0;
static uint64_t g_name__get_instance_base_type = 0;
static uint64_t g_name__instance_create = 0;
static uint64_t g_name__placeholder_instance_create = 0;
static uint64_t g_name__has_source_code = 0;
static uint64_t g_name__get_source_code = 0;
static uint64_t g_name__set_source_code = 0;
static uint64_t g_name__reload = 0;
static uint64_t g_name__get_global_name = 0;
static uint64_t g_name__get_doc_class_name = 0;
static uint64_t g_name__inherits_script = 0;
static uint64_t g_name__has_method = 0;
static uint64_t g_name__has_static_method = 0;
static uint64_t g_name__get_script_method_argument_count = 0;
static uint64_t g_name__get_method_info = 0;
static uint64_t g_name__is_tool = 0;
static uint64_t g_name__has_script_signal = 0;
static uint64_t g_name__has_property_default_value = 0;
static uint64_t g_name__get_property_default_value = 0;
static uint64_t g_name__update_exports = 0;
static uint64_t g_name__get_script_method_list = 0;
static uint64_t g_name__get_script_property_list = 0;
static uint64_t g_name__get_script_signal_list = 0;
static uint64_t g_name__get_constants = 0;
static uint64_t g_name__get_members = 0;
static uint64_t g_name__get_rpc_config = 0;
static uint64_t g_name__instance_has = 0;
static uint64_t g_name__is_placeholder_fallback_enabled = 0;
static uint64_t g_name__handles_type = 0;
static uint64_t g_name__get_resource_type = 0;
static uint64_t g_name__load = 0;
static uint64_t g_name__debug_get_current_stack_info = 0;
static uint64_t g_name_push_back = 0;
static const char *g_pending_script_resource_path = NULL;
// Per-resource/per-instance lifecycle logging is off by default: it spams startup (one
// fflush'd line per node — heavy for big scenes), skews perf timing, and floods the
// device-console capture before the frame loop. Flip to 1 for lifecycle debugging.
static int g_kanama_log_lifecycle = 0;

// ptrcall argument/return type tags for the generic dispatch
// (kanama_ios_godot_ptrcall). These describe the ptrcall NATIVE representation,
// not the Variant type — the generated ObjectCalls helpers pick the tag from the
// method's extension_api.json type + `meta` (e.g. float vs double, int32 vs int64).
// Kotlin lays out the POD/struct/object arg bytes; the dispatch passes them through
// unchanged, and only CONSTRUCT-tagged types (StringName/String/NodePath) are built
// C-side. Returns are written raw by ptrcall into a Kotlin-sized buffer.
#define KANAMA_IOS_PTRCALL_MAX_ARGS 16

// Opaque byte size of a Packed*Array in the 64-bit GDExtension ABI (extension_api.json
// builtin_class_sizes, float_64/double_64). It is 16, NOT 8 like String/StringName/
// NodePath — a Packed*Array is a CowData pointer plus a proxy pointer. An 8-byte ptrcall
// return slot overflows the stack during the return-encode and crashes reading CowData
// metadata at ptr-16. EVERY Packed*Array ptrcall return/arg storage MUST be sized by this
// constant (assert with KANAMA_IOS_PACKED_ARRAY_STORAGE). Pointer-width-dependent, not
// precision-dependent. Enforced by scripts/audit_builtin_storage_sizes.py.
#define KANAMA_IOS_PACKED_ARRAY_OPAQUE_SIZE 16

// Declare a zero-initialized Packed*Array ptrcall storage slot, statically asserted to be
// at least the 64-bit ABI opaque size. Use this for every Packed*Array marshalling helper.
#define KANAMA_IOS_PACKED_ARRAY_STORAGE(name)                                        \
    uint64_t name[(KANAMA_IOS_PACKED_ARRAY_OPAQUE_SIZE + sizeof(uint64_t) - 1) /     \
                  sizeof(uint64_t)] = { 0 };                                          \
    _Static_assert(sizeof(name) >= KANAMA_IOS_PACKED_ARRAY_OPAQUE_SIZE,              \
        "Packed*Array ptrcall storage must be >= the 16-byte 64-bit ABI opaque size")

enum {
    KANAMA_IOS_PT_VOID = 0,
    KANAMA_IOS_PT_BOOL,        // uint8 (1 byte)
    KANAMA_IOS_PT_INT32,       // int32
    KANAMA_IOS_PT_INT64,       // int64
    KANAMA_IOS_PT_FLOAT32,     // float
    KANAMA_IOS_PT_FLOAT64,     // double
    KANAMA_IOS_PT_VECTOR2,     // 2x float32
    KANAMA_IOS_PT_VECTOR2I,    // 2x int32
    KANAMA_IOS_PT_VECTOR3,     // 3x float32
    KANAMA_IOS_PT_VECTOR3I,    // 3x int32
    KANAMA_IOS_PT_VECTOR4,     // 4x float32
    KANAMA_IOS_PT_COLOR,       // 4x float32
    KANAMA_IOS_PT_RECT2,       // 4x float32
    KANAMA_IOS_PT_OBJECT,      // pointer-sized handle (passthrough)
    KANAMA_IOS_PT_RID,         // pointer/uint64 (passthrough)
    // CONSTRUCT-tagged: arg ptr is a C string; the dispatch builds the value from it
    // and destroys it after the call (StringName/String/NodePath).
    KANAMA_IOS_PT_STRING_NAME,
    KANAMA_IOS_PT_STRING,      // String built from the C string arg
    KANAMA_IOS_PT_NODE_PATH,   // NodePath built from the C string arg
    // POD passthrough (raw real_t bytes, like Vector3/Color): the caller lays out the
    // float32 components and the dispatch passes them straight through.
    KANAMA_IOS_PT_BASIS,       // 9x float32 (column-major)
    KANAMA_IOS_PT_TRANSFORM3D, // 12x float32 (9 basis column-major + 3 origin)
    KANAMA_IOS_PT_QUATERNION,  // 4x float32 (x, y, z, w)
    KANAMA_IOS_PT_AABB,        // 6x float32 (position xyz + size xyz)
    KANAMA_IOS_PT_TRANSFORM2D, // 6x float32 (columns x, y, origin — each Vector2)
    // (KANAMA_IOS_PT_RID = 14 above is also POD passthrough: a single uint64.)
    // BUILD-tagged Packed*Array args: arg ptr is a KanamaIosPackedArgDesc {count, data}; the
    // dispatch builds the array (constructor + push_back per element) into a 16-byte cell and
    // destroys it after the call. Vector2 element = 2 float32, Color element = 4 float32.
    KANAMA_IOS_PT_PACKED_VECTOR2_ARRAY,
    KANAMA_IOS_PT_PACKED_COLOR_ARRAY,
    // POD passthrough return: 16x float32 (4 column-major Vector4 columns x, y, z, w).
    // Appended at the end so the Packed*Array tag values above are not renumbered.
    KANAMA_IOS_PT_PROJECTION,
    // Typed-array element selector (not a ptrcall arg/ret tag): Array[Plane] element = 4 float32.
    KANAMA_IOS_PT_PLANE,
    // CONSTRUCT-tagged Callable arg: arg ptr is a KanamaIosCallableArgDesc {object_handle, method};
    // the dispatch builds an object+method Callable (constructor index 2) into a cell, passes it to
    // ptrcall, and destroys the cell after the call. Object+method form only (Phase 1.4 Callable-args).
    KANAMA_IOS_PT_CALLABLE,
};

// Descriptor for a BUILD-tagged Packed*Array arg (mirrors KanamaIosPackedArgDesc in
// ios/include/kanama_ios.h — the shim does not include that header). `data` is a flat
// float32 element buffer: Vector2 = 2 floats/elem, Color = 4 floats/elem.
typedef struct {
    int64_t count;
    const void *data;
} KanamaIosPackedArgDesc;

// Descriptor for a CONSTRUCT-tagged Callable arg (mirrors KanamaIosCallableArgDesc in
// ios/include/kanama_ios.h — the shim does not include that header). `object_handle` is the
// target GodotObject pointer as int64; `method` is its method name as a C string.
typedef struct {
    int64_t object_handle;
    const char *method;
} KanamaIosCallableArgDesc;

enum {
    KANAMA_IOS_VARIANT_TYPE_NIL = 0,
    KANAMA_IOS_VARIANT_TYPE_BASIS = 17,
    KANAMA_IOS_VARIANT_TYPE_TRANSFORM3D = 18,
    KANAMA_IOS_VARIANT_TYPE_BOOL = 1,
    KANAMA_IOS_VARIANT_TYPE_INT = 2,
    KANAMA_IOS_VARIANT_TYPE_FLOAT = 3,
    KANAMA_IOS_VARIANT_TYPE_STRING = 4,
    KANAMA_IOS_VARIANT_TYPE_VECTOR2 = 5,
    KANAMA_IOS_VARIANT_TYPE_VECTOR2I = 6,
    KANAMA_IOS_VARIANT_TYPE_RECT2 = 7,
    KANAMA_IOS_VARIANT_TYPE_VECTOR3 = 9,
    KANAMA_IOS_VARIANT_TYPE_PLANE = 14,
    KANAMA_IOS_VARIANT_TYPE_QUATERNION = 15,
    KANAMA_IOS_VARIANT_TYPE_COLOR = 20,
    KANAMA_IOS_VARIANT_TYPE_STRING_NAME = 21,
    KANAMA_IOS_VARIANT_TYPE_NODE_PATH = 22,
    KANAMA_IOS_VARIANT_TYPE_OBJECT = 24,
    KANAMA_IOS_VARIANT_TYPE_CALLABLE = 25,
    KANAMA_IOS_VARIANT_TYPE_DICTIONARY = 27,
    KANAMA_IOS_VARIANT_TYPE_ARRAY = 28,
    KANAMA_IOS_VARIANT_TYPE_PACKED_INT32_ARRAY = 30,
    KANAMA_IOS_VARIANT_TYPE_PACKED_FLOAT32_ARRAY = 32,
    KANAMA_IOS_VARIANT_TYPE_PACKED_STRING_ARRAY = 34,
    KANAMA_IOS_VARIANT_TYPE_PACKED_VECTOR2_ARRAY = 35,
    KANAMA_IOS_VARIANT_TYPE_PACKED_COLOR_ARRAY = 37,
    KANAMA_IOS_OBJECT_NOTIFICATION_HASH = 4023243586U,
    KANAMA_IOS_ENGINE_GET_MAIN_LOOP_HASH = 1016888095U,
    KANAMA_IOS_ENGINE_REGISTER_SCRIPT_LANGUAGE_HASH = 1850254898U,
    KANAMA_IOS_ENGINE_UNREGISTER_SCRIPT_LANGUAGE_HASH = 1850254898U,
    KANAMA_IOS_RESOURCE_LOADER_ADD_FORMAT_LOADER_HASH = 2896595483U,
    KANAMA_IOS_RESOURCE_LOADER_REMOVE_FORMAT_LOADER_HASH = 405397102U,
    KANAMA_IOS_SCENE_TREE_GET_FIRST_NODE_IN_GROUP_HASH = 4071044623U,
    KANAMA_IOS_LABEL_SET_TEXT_HASH = 83702148U,
    KANAMA_IOS_NODE_ADD_CHILD_HASH = 3863233950U,
    KANAMA_IOS_NODE_REMOVE_CHILD_HASH = 1078189570U,
    KANAMA_IOS_NODE_GET_CHILD_COUNT_HASH = 894402480U,
    KANAMA_IOS_NODE_GET_CHILD_HASH = 541253412U,
    KANAMA_IOS_NODE_GET_NODE_OR_NULL_HASH = 2734337346U,
    KANAMA_IOS_NODE_GET_TREE_HASH = 2958820483U,
    KANAMA_IOS_NODE_GET_VIEWPORT_HASH = 3596683776U,
    KANAMA_IOS_NODE_CREATE_TWEEN_HASH = 3426978995U,
    KANAMA_IOS_NODE_QUEUE_FREE_HASH = 3218959716U,
    KANAMA_IOS_NODE_SET_PROCESS_HASH = 2586408642U,
    KANAMA_IOS_NODE_SET_PHYSICS_PROCESS_HASH = 2586408642U,
    KANAMA_IOS_NODE_SET_PROCESS_INPUT_HASH = 2586408642U,
    KANAMA_IOS_NODE_SET_PROCESS_UNHANDLED_INPUT_HASH = 2586408642U,
    KANAMA_IOS_OBJECT_STRING_NAME_BOOL_HASH = 2619796661U,
    KANAMA_IOS_NOARGS_BOOL_HASH = 36873697U,
    KANAMA_IOS_INPUT_EVENT_MOUSE_BUTTON_GET_BUTTON_INDEX_HASH = 1132662608U,
    KANAMA_IOS_NODE2D_SET_POSITION_HASH = 743155724U,
    KANAMA_IOS_NODE2D_GET_POSITION_HASH = 3341600327U,
    KANAMA_IOS_NODE2D_SET_SCALE_HASH = 743155724U,
    KANAMA_IOS_NODE2D_GET_SCALE_HASH = 3341600327U,
    KANAMA_IOS_NODE3D_SET_POSITION_HASH = 3460891852U,
    KANAMA_IOS_NODE3D_GET_POSITION_HASH = 3360562783U,
    KANAMA_IOS_NODE3D_SET_ROTATION_HASH = 3460891852U,
    KANAMA_IOS_NODE3D_GET_ROTATION_HASH = 3360562783U,
    KANAMA_IOS_NODE3D_SET_SCALE_HASH = 3460891852U,
    KANAMA_IOS_NODE3D_GET_SCALE_HASH = 3360562783U,
    KANAMA_IOS_NODE3D_SET_GLOBAL_POSITION_HASH = 3460891852U,
    KANAMA_IOS_NODE3D_GET_GLOBAL_POSITION_HASH = 3360562783U,
    KANAMA_IOS_NODE3D_ROTATE_Y_HASH = 373806689U,
    KANAMA_IOS_CANVAS_ITEM_GET_VIEWPORT_RECT_HASH = 1639390495U,
    KANAMA_IOS_CANVAS_ITEM_GET_LOCAL_MOUSE_POSITION_HASH = 3341600327U,
    KANAMA_IOS_CANVAS_ITEM_NOARGS_HASH = 3218959716U,
    KANAMA_IOS_CANVAS_ITEM_SET_MODULATE_HASH = 2920490490U,
    KANAMA_IOS_ARRAY_SIZE_HASH = 3173160232U,
    KANAMA_IOS_ARRAY_GET_HASH = 708700221U,
    KANAMA_IOS_ARRAY_PUSH_BACK_HASH = 3316032543U,
    KANAMA_IOS_CALLABLE_BINDV_HASH = 3564560322U,
    KANAMA_IOS_PACKED_SCENE_INSTANTIATE_HASH = 2628778455U,
    KANAMA_IOS_REF_COUNTED_NOARGS_HASH = 2240911060U,
    KANAMA_IOS_BOOL_ARG_HASH = 2586408642U,
    KANAMA_IOS_DOUBLE_ARG_HASH = 373806689U,
    KANAMA_IOS_GPU_PARTICLES_RESTART_HASH = 107499316U,
    KANAMA_IOS_RESOURCE_LOADER_LOAD_HASH = 3358495409U,
    KANAMA_IOS_SPRITE2D_SET_TEXTURE_HASH = 4051416890U,
    KANAMA_IOS_AUDIO_STREAM_PLAYER_SET_STREAM_HASH = 2210767741U,
    KANAMA_IOS_AUDIO_STREAM_PLAYER_SET_FLOAT_HASH = 373806689U, // set_volume_db / set_pitch_scale (float)->void
    KANAMA_IOS_AUDIO_STREAM_PLAYER_PLAY_HASH = 1958160172U,
    KANAMA_IOS_AUDIO_STREAM_PLAYER_SET_BUS_HASH = 3304788590U,
    KANAMA_IOS_AUDIO_STREAM_PLAYER_SET_STREAM_PAUSED_HASH = 2586408642U,
    KANAMA_IOS_OBJECT_EMIT_SIGNAL_HASH = 4047867050U,
    KANAMA_IOS_OBJECT_CONNECT_HASH = 1518946055U,
    KANAMA_IOS_OBJECT_DISCONNECT_HASH = 1874754934U,
    KANAMA_IOS_OBJECT_CALL_HASH = 3400424181U,
    KANAMA_IOS_OBJECT_SET_DEFERRED_HASH = 3776071444U,
    KANAMA_IOS_INPUT_SET_CUSTOM_MOUSE_CURSOR_HASH = 703945977U,
    KANAMA_IOS_TWEEN_TWEEN_PROPERTY_HASH = 4049770449U,
    KANAMA_IOS_TWEEN_TWEEN_CALLBACK_HASH = 1540176488U,
    KANAMA_IOS_TWEEN_TWEEN_METHOD_HASH = 2337877153U,
    KANAMA_IOS_PROPERTY_TWEENER_FROM_HASH = 4190193059U,
    KANAMA_IOS_TWEEN_SET_PARALLEL_HASH = 1942052223U,
    KANAMA_IOS_TWEEN_KILL_HASH = 3218959716U,
    KANAMA_IOS_PROPERTY_TWEENER_SET_TRANS_HASH = 1899107404U,
    KANAMA_IOS_PROPERTY_TWEENER_SET_EASE_HASH = 1080455622U,
    KANAMA_IOS_VIEWPORT_GET_VISIBLE_RECT_HASH = 1639390495U,
    KANAMA_IOS_PACKED_STRING_ARRAY_PUSH_BACK_HASH = 816187996U,
    KANAMA_IOS_PACKED_STRING_ARRAY_SIZE_HASH = 3173160232U,
    // No-arg "size" builtin method hash; signature-derived so it matches Array.size.
    KANAMA_IOS_PACKED_INT32_ARRAY_SIZE_HASH = 3173160232U,
    KANAMA_IOS_PACKED_FLOAT32_ARRAY_SIZE_HASH = 3173160232U,
    KANAMA_IOS_PACKED_FLOAT32_ARRAY_PUSH_BACK_HASH = 4094791666U,
    KANAMA_IOS_PACKED_VECTOR2_ARRAY_SIZE_HASH = 3173160232U,
    KANAMA_IOS_PACKED_COLOR_ARRAY_SIZE_HASH = 3173160232U,
    KANAMA_IOS_PACKED_VECTOR2_ARRAY_PUSH_BACK_HASH = 4188891560U,
    KANAMA_IOS_PACKED_COLOR_ARRAY_PUSH_BACK_HASH = 1007858200U,
    KANAMA_IOS_NOTIFICATION_POSTINITIALIZE = 0,
    KANAMA_IOS_NOTIFICATION_ENTER_TREE = 10,
    KANAMA_IOS_NOTIFICATION_EXIT_TREE = 11,
    KANAMA_IOS_NOTIFICATION_READY = 13,
};

static void *kanama_ios_lookup(const char *name) {
    if (g_get_proc_address == NULL) {
        return NULL;
    }
    return (void *)g_get_proc_address(name);
}

static int kanama_ios_resolve_godot_api(void) {
    if (g_string_name_new != NULL) {
        return 1;
    }

    g_string_name_new = (GDExtensionInterfaceStringNameNewWithUtf8Chars)kanama_ios_lookup(
        "string_name_new_with_utf8_chars"
    );
    g_string_new = (GDExtensionInterfaceStringNewWithUtf8Chars)kanama_ios_lookup(
        "string_new_with_utf8_chars"
    );
    g_string_to_utf8_chars = (GDExtensionInterfaceStringToUtf8Chars)kanama_ios_lookup(
        "string_to_utf8_chars"
    );
    g_variant_get_ptr_destructor = (GDExtensionInterfaceVariantGetPtrDestructor)kanama_ios_lookup(
        "variant_get_ptr_destructor"
    );
    g_variant_get_ptr_constructor = (GDExtensionInterfaceVariantGetPtrConstructor)kanama_ios_lookup(
        "variant_get_ptr_constructor"
    );
    g_variant_get_ptr_builtin_method = (GDExtensionInterfaceVariantGetPtrBuiltinMethod)kanama_ios_lookup(
        "variant_get_ptr_builtin_method"
    );
    g_variant_get_ptr_keyed_setter = (GDExtensionInterfaceVariantGetPtrKeyedSetter)kanama_ios_lookup(
        "variant_get_ptr_keyed_setter"
    );
    g_variant_get_ptr_keyed_getter = (GDExtensionInterfaceVariantGetPtrKeyedGetter)kanama_ios_lookup(
        "variant_get_ptr_keyed_getter"
    );
    g_get_variant_from_type_constructor = (GDExtensionInterfaceGetVariantFromTypeConstructor)kanama_ios_lookup(
        "get_variant_from_type_constructor"
    );
    g_get_variant_to_type_constructor = (GDExtensionInterfaceGetVariantToTypeConstructor)kanama_ios_lookup(
        "get_variant_to_type_constructor"
    );
    g_variant_destroy = (GDExtensionInterfaceVariantDestroy)kanama_ios_lookup("variant_destroy");
    g_variant_get_type = (GDExtensionInterfaceVariantGetType)kanama_ios_lookup("variant_get_type");
    // Optional: lambda/bound signal callbacks need this. Not added to the required
    // resolution check below so older runtimes still load; connect_callable NULL-checks it.
    g_callable_custom_create2 = (GDExtensionInterfaceCallableCustomCreate2)kanama_ios_lookup("callable_custom_create2");
    g_variant_new_nil = (GDExtensionInterfaceVariantNewNil)kanama_ios_lookup("variant_new_nil");
    g_global_get_singleton = (GDExtensionInterfaceGlobalGetSingleton)kanama_ios_lookup(
        "global_get_singleton"
    );
    g_classdb_get_method_bind = (GDExtensionInterfaceClassdbGetMethodBind)kanama_ios_lookup(
        "classdb_get_method_bind"
    );
    g_classdb_construct_object = (GDExtensionInterfaceClassdbConstructObject3)kanama_ios_lookup(
        "classdb_construct_object3"
    );
    g_classdb_register_extension_class = (GDExtensionInterfaceClassdbRegisterExtensionClass6)kanama_ios_lookup(
        "classdb_register_extension_class6"
    );
    g_classdb_unregister_extension_class = (GDExtensionInterfaceClassdbUnregisterExtensionClass)kanama_ios_lookup(
        "classdb_unregister_extension_class"
    );
    g_object_set_instance = (GDExtensionInterfaceObjectSetInstance)kanama_ios_lookup(
        "object_set_instance"
    );
    g_object_method_bind_call = (GDExtensionInterfaceObjectMethodBindCall)kanama_ios_lookup(
        "object_method_bind_call"
    );
    g_object_method_bind_ptrcall = (GDExtensionInterfaceObjectMethodBindPtrcall)kanama_ios_lookup(
        "object_method_bind_ptrcall"
    );
    g_script_instance_create = (GDExtensionInterfaceScriptInstanceCreate3)kanama_ios_lookup(
        "script_instance_create3"
    );
    g_register_main_loop_callbacks = (GDExtensionInterfaceRegisterMainLoopCallbacks)kanama_ios_lookup(
        "register_main_loop_callbacks"
    );

    if (
        g_string_name_new == NULL ||
        g_string_new == NULL ||
        g_string_to_utf8_chars == NULL ||
        g_variant_get_ptr_destructor == NULL ||
        g_variant_get_ptr_constructor == NULL ||
        g_variant_get_ptr_builtin_method == NULL ||
        g_variant_get_ptr_keyed_setter == NULL ||
        g_get_variant_from_type_constructor == NULL ||
        g_get_variant_to_type_constructor == NULL ||
        g_variant_destroy == NULL ||
        g_variant_get_type == NULL ||
        g_variant_new_nil == NULL ||
        g_global_get_singleton == NULL ||
        g_classdb_get_method_bind == NULL ||
        g_classdb_construct_object == NULL ||
        g_classdb_register_extension_class == NULL ||
        g_classdb_unregister_extension_class == NULL ||
        g_object_set_instance == NULL ||
        g_object_method_bind_call == NULL ||
        g_object_method_bind_ptrcall == NULL ||
        g_script_instance_create == NULL ||
        g_register_main_loop_callbacks == NULL
    ) {
        fprintf(stderr, "[kanama][ios][c] error: failed to resolve required Godot API functions\n");
        return 0;
    }

    g_string_name_destructor = g_variant_get_ptr_destructor(KANAMA_IOS_VARIANT_TYPE_STRING_NAME);
    g_string_destructor = g_variant_get_ptr_destructor(KANAMA_IOS_VARIANT_TYPE_STRING);
    g_node_path_destructor = g_variant_get_ptr_destructor(KANAMA_IOS_VARIANT_TYPE_NODE_PATH);
    g_dictionary_destructor = g_variant_get_ptr_destructor(KANAMA_IOS_VARIANT_TYPE_DICTIONARY);
    g_node_path_from_string_constructor = g_variant_get_ptr_constructor(KANAMA_IOS_VARIANT_TYPE_NODE_PATH, 2);
    // String(from: StringName) — constructor index 2 in extension_api.json. Used to
    // marshal StringName ptrcall returns (no GDExtension StringName->utf8 exists).
    g_string_from_string_name_constructor = g_variant_get_ptr_constructor(KANAMA_IOS_VARIANT_TYPE_STRING, 2);
    g_packed_string_array_constructor = g_variant_get_ptr_constructor(
        KANAMA_IOS_VARIANT_TYPE_PACKED_STRING_ARRAY,
        0
    );
    g_array_constructor = g_variant_get_ptr_constructor(KANAMA_IOS_VARIANT_TYPE_ARRAY, 0);
    g_dictionary_constructor = g_variant_get_ptr_constructor(KANAMA_IOS_VARIANT_TYPE_DICTIONARY, 0);
    g_dictionary_keyed_setter = g_variant_get_ptr_keyed_setter(KANAMA_IOS_VARIANT_TYPE_DICTIONARY);
    // Keyed getter + variant->Dictionary back off the debug self-test only. Resolve them
    // opportunistically; they are deliberately excluded from the mandatory NULL gate below so a
    // missing symbol can never fail production shim init.
    if (g_variant_get_ptr_keyed_getter != NULL) {
        g_dictionary_keyed_getter = g_variant_get_ptr_keyed_getter(KANAMA_IOS_VARIANT_TYPE_DICTIONARY);
    }
    g_variant_to_dictionary = g_get_variant_to_type_constructor(KANAMA_IOS_VARIANT_TYPE_DICTIONARY);
    g_variant_to_bool = g_get_variant_to_type_constructor(KANAMA_IOS_VARIANT_TYPE_BOOL);
    g_variant_to_string = g_get_variant_to_type_constructor(KANAMA_IOS_VARIANT_TYPE_STRING);
    g_variant_to_float = g_get_variant_to_type_constructor(KANAMA_IOS_VARIANT_TYPE_FLOAT);
    g_variant_to_object = g_get_variant_to_type_constructor(KANAMA_IOS_VARIANT_TYPE_OBJECT);
    g_variant_to_int = g_get_variant_to_type_constructor(KANAMA_IOS_VARIANT_TYPE_INT);
    g_variant_to_vector2i = g_get_variant_to_type_constructor(KANAMA_IOS_VARIANT_TYPE_VECTOR2I);
    g_variant_to_array = g_get_variant_to_type_constructor(KANAMA_IOS_VARIANT_TYPE_ARRAY);
    g_variant_to_vector2 = g_get_variant_to_type_constructor(KANAMA_IOS_VARIANT_TYPE_VECTOR2);
    g_variant_to_vector3 = g_get_variant_to_type_constructor(KANAMA_IOS_VARIANT_TYPE_VECTOR3);
    g_variant_to_color = g_get_variant_to_type_constructor(KANAMA_IOS_VARIANT_TYPE_COLOR);
    g_variant_to_node_path = g_get_variant_to_type_constructor(KANAMA_IOS_VARIANT_TYPE_NODE_PATH);
    g_variant_to_string_name = g_get_variant_to_type_constructor(KANAMA_IOS_VARIANT_TYPE_STRING_NAME);
    g_variant_to_plane = g_get_variant_to_type_constructor(KANAMA_IOS_VARIANT_TYPE_PLANE);
    g_variant_to_packed_string_array = g_get_variant_to_type_constructor(
        KANAMA_IOS_VARIANT_TYPE_PACKED_STRING_ARRAY);
    // String(from: NodePath) — constructor index 3 in extension_api.json. Lets the set-property
    // value path turn a NodePath Variant into utf8 (no GDExtension NodePath->utf8 exists).
    g_string_from_node_path_constructor = g_variant_get_ptr_constructor(KANAMA_IOS_VARIANT_TYPE_STRING, 3);
    g_variant_from_vector2i = g_get_variant_from_type_constructor(KANAMA_IOS_VARIANT_TYPE_VECTOR2I);
    g_callable_object_method_constructor = g_variant_get_ptr_constructor(KANAMA_IOS_VARIANT_TYPE_CALLABLE, 2);
    g_variant_from_callable = g_get_variant_from_type_constructor(KANAMA_IOS_VARIANT_TYPE_CALLABLE);
    g_callable_destructor = g_variant_get_ptr_destructor(KANAMA_IOS_VARIANT_TYPE_CALLABLE);
    g_variant_from_vector2 = g_get_variant_from_type_constructor(KANAMA_IOS_VARIANT_TYPE_VECTOR2);
    g_variant_from_vector3 = g_get_variant_from_type_constructor(KANAMA_IOS_VARIANT_TYPE_VECTOR3);
    g_variant_from_color = g_get_variant_from_type_constructor(KANAMA_IOS_VARIANT_TYPE_COLOR);
    g_variant_from_node_path = g_get_variant_from_type_constructor(KANAMA_IOS_VARIANT_TYPE_NODE_PATH);
    g_variant_from_float = g_get_variant_from_type_constructor(KANAMA_IOS_VARIANT_TYPE_FLOAT);
    g_variant_from_object = g_get_variant_from_type_constructor(KANAMA_IOS_VARIANT_TYPE_OBJECT);
    g_variant_from_string_name = g_get_variant_from_type_constructor(KANAMA_IOS_VARIANT_TYPE_STRING_NAME);
    g_variant_from_int = g_get_variant_from_type_constructor(KANAMA_IOS_VARIANT_TYPE_INT);
    g_variant_from_bool = g_get_variant_from_type_constructor(KANAMA_IOS_VARIANT_TYPE_BOOL);
    g_variant_from_string = g_get_variant_from_type_constructor(KANAMA_IOS_VARIANT_TYPE_STRING);
    g_variant_from_dictionary = g_get_variant_from_type_constructor(KANAMA_IOS_VARIANT_TYPE_DICTIONARY);
    g_variant_from_packed_string_array =
        g_get_variant_from_type_constructor(KANAMA_IOS_VARIANT_TYPE_PACKED_STRING_ARRAY);

    if (
        g_string_name_destructor == NULL ||
        g_string_destructor == NULL ||
        g_node_path_destructor == NULL ||
        g_dictionary_destructor == NULL ||
        g_node_path_from_string_constructor == NULL ||
        g_string_from_string_name_constructor == NULL ||
        g_packed_string_array_constructor == NULL ||
        g_array_constructor == NULL ||
        g_dictionary_constructor == NULL ||
        g_dictionary_keyed_setter == NULL ||
        g_variant_to_float == NULL ||
        g_variant_to_object == NULL ||
        g_variant_to_int == NULL ||
        g_variant_to_vector2i == NULL ||
        g_variant_from_vector2i == NULL ||
        g_callable_object_method_constructor == NULL ||
        g_variant_from_callable == NULL ||
        g_callable_destructor == NULL ||
        g_variant_from_vector2 == NULL ||
        g_variant_from_color == NULL ||
        g_variant_from_node_path == NULL ||
        g_variant_from_float == NULL ||
        g_variant_from_object == NULL ||
        g_variant_from_string_name == NULL ||
        g_variant_from_int == NULL ||
        g_variant_from_bool == NULL ||
        g_variant_from_string == NULL ||
        g_variant_from_dictionary == NULL ||
        g_variant_from_packed_string_array == NULL
    ) {
        fprintf(stderr, "[kanama][ios][c] error: failed to resolve Godot builtin helpers\n");
        return 0;
    }

    return 1;
}

static void kanama_ios_init_string_name(uint64_t *storage, const char *value) {
    *storage = 0;
    g_string_name_new((GDExtensionUninitializedStringNamePtr)storage, value);
}

// Guardrail: a Variant-based method_bind_call silently returns a non-OK
// GDExtensionCallError when an argument Variant is malformed (e.g. a raw type
// value passed where a boxed Variant was expected). Without surfacing this, the
// failure is invisible right up until a later variant_destroy crashes on the
// garbage type tag. Log loudly so marshalling mistakes are caught immediately.
static int kanama_ios_check_call_error(const char *what, const GDExtensionCallError *error) {
    if (error == NULL || error->error == GDEXTENSION_CALL_OK) {
        return 1;
    }
    fprintf(stderr,
            "[kanama][ios][c] CALL ERROR in %s: error=%d argument=%d expected=%d\n",
            what ? what : "(unknown)", (int)error->error,
            (int)error->argument, (int)error->expected);
    fflush(stderr);
    return 0;
}

// Debug-build guardrail: verify that an argument Variant actually carries the
// type we intend before handing it to object_method_bind_call. This catches the
// whole class of "raw type value passed where a boxed Variant was expected"
// mistakes (the connect() SIGSEGV of 2026-06-09) structurally, at the call site
// that introduced it, instead of as an opaque crash in a later variant_destroy.
// Compiled in by default; define KANAMA_IOS_DEBUG_VARIANT_CHECKS=0 for release
// builds to remove the per-argument type lookups entirely.
#ifndef KANAMA_IOS_DEBUG_VARIANT_CHECKS
#define KANAMA_IOS_DEBUG_VARIANT_CHECKS 1
#endif

static int kanama_ios_check_variant_arg(
    const char *what,
    int index,
    GDExtensionConstVariantPtr arg,
    GDExtensionVariantType expected
) {
#if KANAMA_IOS_DEBUG_VARIANT_CHECKS
    if (g_variant_get_type == NULL || arg == NULL) {
        return 1;
    }
    GDExtensionVariantType actual = g_variant_get_type(arg);
    if (actual != (GDExtensionVariantType)expected) {
        fprintf(stderr,
                "[kanama][ios][c] VARIANT ARG MISMATCH in %s: arg %d is type %d, expected %d "
                "(likely a raw type value passed where a boxed Variant was required)\n",
                what ? what : "(unknown)", index, (int)actual, (int)expected);
        fflush(stderr);
        return 0;
    }
#else
    (void)what; (void)index; (void)arg; (void)expected;
#endif
    return 1;
}

static void kanama_ios_destroy_string_name(uint64_t *storage) {
    if (*storage != 0 && g_string_name_destructor != NULL) {
        g_string_name_destructor((GDExtensionStringNamePtr)storage);
        *storage = 0;
    }
}

static void kanama_ios_init_string(uint64_t *storage, const char *value) {
    *storage = 0;
    g_string_new((GDExtensionUninitializedStringPtr)storage, value);
}

static void kanama_ios_destroy_string(uint64_t *storage) {
    if (*storage != 0 && g_string_destructor != NULL) {
        g_string_destructor((GDExtensionStringPtr)storage);
        *storage = 0;
    }
}

static void kanama_ios_init_node_path(uint64_t *storage, const char *value) {
    *storage = 0;
    if (g_node_path_from_string_constructor == NULL) {
        return;
    }
    uint64_t string_storage = 0;
    kanama_ios_init_string(&string_storage, value);
    const GDExtensionConstTypePtr args[1] = {
        (GDExtensionConstTypePtr)&string_storage,
    };
    g_node_path_from_string_constructor((GDExtensionUninitializedTypePtr)storage, args);
    kanama_ios_destroy_string(&string_storage);
}

static void kanama_ios_destroy_node_path(uint64_t *storage) {
    if (*storage != 0 && g_node_path_destructor != NULL) {
        g_node_path_destructor((GDExtensionTypePtr)storage);
        *storage = 0;
    }
}

static char *kanama_ios_strdup(const char *value) {
    if (value == NULL) {
        return NULL;
    }
    size_t length = strlen(value);
    char *copy = malloc(length + 1);
    if (copy == NULL) {
        return NULL;
    }
    memcpy(copy, value, length + 1);
    return copy;
}

static char *kanama_ios_string_to_utf8_dup(GDExtensionConstStringPtr value) {
    if (value == NULL || g_string_to_utf8_chars == NULL) {
        return NULL;
    }
    GDExtensionInt length = g_string_to_utf8_chars(value, NULL, 0);
    if (length < 0) {
        return NULL;
    }
    char *buffer = calloc((size_t)length + 1, sizeof(char));
    if (buffer == NULL) {
        return NULL;
    }
    g_string_to_utf8_chars(value, buffer, length + 1);
    buffer[length] = '\0';
    return buffer;
}

static void kanama_ios_cache_name(uint64_t *storage, const char *value) {
    if (*storage == 0) {
        kanama_ios_init_string_name(storage, value);
    }
}

static uint64_t kanama_ios_string_name_value(GDExtensionConstStringNamePtr name) {
    if (name == NULL) {
        return 0;
    }
    return *((const uint64_t *)name);
}

static int kanama_ios_string_name_eq(GDExtensionConstStringNamePtr name, uint64_t cached) {
    return cached != 0 && kanama_ios_string_name_value(name) == cached;
}

static int32_t kanama_ios_script_method_index(
    const KanamaIosExtensionInstance *script,
    GDExtensionConstStringNamePtr name
) {
    if (script == NULL || name == NULL || script->script_method_names == NULL) {
        return -1;
    }
    uint64_t value = kanama_ios_string_name_value(name);
    for (int32_t i = 0; i < script->script_method_count; i++) {
        if (script->script_method_names[i] == value) {
            return i;
        }
    }
    return -1;
}

static int32_t kanama_ios_script_property_index(
    const KanamaIosExtensionInstance *script,
    GDExtensionConstStringNamePtr name
) {
    if (script == NULL || name == NULL || script->script_property_names == NULL) {
        return -1;
    }
    uint64_t value = kanama_ios_string_name_value(name);
    for (int32_t i = 0; i < script->script_property_count; i++) {
        if (script->script_property_names[i] == value) {
            return i;
        }
    }
    return -1;
}

static int32_t kanama_ios_script_signal_index(
    const KanamaIosExtensionInstance *script,
    GDExtensionConstStringNamePtr name
) {
    if (script == NULL || name == NULL || script->script_signal_names == NULL) {
        return -1;
    }
    uint64_t value = kanama_ios_string_name_value(name);
    for (int32_t i = 0; i < script->script_signal_count; i++) {
        if (script->script_signal_names[i] == value) {
            return i;
        }
    }
    return -1;
}

static const char *kanama_ios_script_method_name_text(
    const KanamaIosExtensionInstance *script,
    int32_t method_index
) {
    if (
        script == NULL ||
        method_index < 0 ||
        method_index >= script->script_method_count ||
        script->script_method_name_texts == NULL
    ) {
        return "";
    }
    return script->script_method_name_texts[method_index] != NULL
        ? script->script_method_name_texts[method_index]
        : "";
}

static int32_t kanama_ios_script_method_arg_count(
    const KanamaIosExtensionInstance *script,
    int32_t method_index
) {
    if (script == NULL || method_index < 0 || method_index >= script->script_method_count) {
        return 0;
    }
    return kanama_ios_runtime_script_resource_method_argument_count(script->script_handle, method_index);
}

static void kanama_ios_script_resource_init_metadata(KanamaIosExtensionInstance *instance) {
    if (instance == NULL || instance->kind != KANAMA_IOS_CLASS_SCRIPT || instance->script_handle == 0) {
        return;
    }

    char base_type[128];
    base_type[0] = '\0';
    kanama_ios_runtime_script_resource_base_type(instance->script_handle, base_type, (int32_t)sizeof(base_type));
    if (base_type[0] == '\0') {
        snprintf(base_type, sizeof(base_type), "%s", "Node");
    }
    instance->script_base_type_text = kanama_ios_strdup(base_type);
    kanama_ios_init_string_name(&instance->script_base_type, base_type);

    // Do NOT early-return when method_count == 0: a script may declare only signals and/or
    // properties (e.g. a signal-only Events autoload). Returning here skipped the property and
    // signal sections, so the Script object never advertised its @Signals — runtime
    // Object::connect to those signals then failed with ERR_INVALID_PARAMETER.
    int32_t method_count = kanama_ios_runtime_script_resource_method_count(instance->script_handle);
    if (method_count > 0) {
        instance->script_method_names = calloc((size_t)method_count, sizeof(uint64_t));
        instance->script_method_name_texts = calloc((size_t)method_count, sizeof(char *));
        if (instance->script_method_names == NULL || instance->script_method_name_texts == NULL) {
            free(instance->script_method_names);
            free(instance->script_method_name_texts);
            instance->script_method_names = NULL;
            instance->script_method_name_texts = NULL;
        } else {
            instance->script_method_count = method_count;
            for (int32_t i = 0; i < method_count; i++) {
                char method_name[128];
                method_name[0] = '\0';
                kanama_ios_runtime_script_resource_method_name(
                    instance->script_handle,
                    i,
                    method_name,
                    (int32_t)sizeof(method_name)
                );
                if (method_name[0] == '\0') {
                    continue;
                }
                kanama_ios_init_string_name(&instance->script_method_names[i], method_name);
                instance->script_method_name_texts[i] = kanama_ios_strdup(method_name);
            }
        }
    }

    int32_t property_count = kanama_ios_runtime_script_resource_property_count(instance->script_handle);
    if (property_count > 0) {
        instance->script_property_names = calloc((size_t)property_count, sizeof(uint64_t));
        instance->script_property_types = calloc((size_t)property_count, sizeof(int32_t));
        if (instance->script_property_names == NULL || instance->script_property_types == NULL) {
            free(instance->script_property_names);
            free(instance->script_property_types);
            instance->script_property_names = NULL;
            instance->script_property_types = NULL;
            return;
        }
        instance->script_property_count = property_count;
        for (int32_t i = 0; i < property_count; i++) {
            char property_name[128];
            property_name[0] = '\0';
            kanama_ios_runtime_script_resource_property_name(
                instance->script_handle,
                i,
                property_name,
                (int32_t)sizeof(property_name)
            );
            instance->script_property_types[i] = kanama_ios_runtime_script_resource_property_type(
                instance->script_handle, i);
            if (property_name[0] == '\0') {
                continue;
            }
            kanama_ios_init_string_name(&instance->script_property_names[i], property_name);
        }
    }

    int32_t signal_count = kanama_ios_runtime_script_resource_signal_count(instance->script_handle);
    if (signal_count > 0) {
        instance->script_signal_names = calloc((size_t)signal_count, sizeof(uint64_t));
        instance->script_signal_name_texts = calloc((size_t)signal_count, sizeof(char *));
        if (instance->script_signal_names == NULL || instance->script_signal_name_texts == NULL) {
            free(instance->script_signal_names);
            free(instance->script_signal_name_texts);
            instance->script_signal_names = NULL;
            instance->script_signal_name_texts = NULL;
            return;
        }
        instance->script_signal_count = signal_count;
        for (int32_t i = 0; i < signal_count; i++) {
            char signal_name[128];
            signal_name[0] = '\0';
            kanama_ios_runtime_script_resource_signal_name(
                instance->script_handle,
                i,
                signal_name,
                (int32_t)sizeof(signal_name)
            );
            if (signal_name[0] == '\0') {
                continue;
            }
            kanama_ios_init_string_name(&instance->script_signal_names[i], signal_name);
            instance->script_signal_name_texts[i] = kanama_ios_strdup(signal_name);
        }
    }

    int32_t rpc_config_count = kanama_ios_runtime_script_resource_rpc_config_count(instance->script_handle);
    if (rpc_config_count > 0) {
        instance->script_rpc_configs = calloc((size_t)rpc_config_count, sizeof(KanamaIosRpcConfig));
        if (instance->script_rpc_configs == NULL) {
            return;
        }
        instance->script_rpc_config_count = rpc_config_count;
        for (int32_t i = 0; i < rpc_config_count; i++) {
            char method_name[128];
            method_name[0] = '\0';
            kanama_ios_runtime_script_resource_rpc_config_method_name(
                instance->script_handle,
                i,
                method_name,
                (int32_t)sizeof(method_name)
            );
            instance->script_rpc_configs[i].method_name_text = kanama_ios_strdup(method_name);
            instance->script_rpc_configs[i].mode =
                kanama_ios_runtime_script_resource_rpc_config_mode(instance->script_handle, i);
            instance->script_rpc_configs[i].call_local =
                kanama_ios_runtime_script_resource_rpc_config_call_local(instance->script_handle, i);
            instance->script_rpc_configs[i].transfer_mode =
                kanama_ios_runtime_script_resource_rpc_config_transfer_mode(instance->script_handle, i);
            instance->script_rpc_configs[i].channel =
                kanama_ios_runtime_script_resource_rpc_config_channel(instance->script_handle, i);
        }
    }
}

static void kanama_ios_script_resource_clear_metadata(KanamaIosExtensionInstance *instance) {
    if (instance == NULL) {
        return;
    }
    kanama_ios_destroy_string_name(&instance->script_base_type);
    if (instance->script_method_names != NULL) {
        for (int32_t i = 0; i < instance->script_method_count; i++) {
            kanama_ios_destroy_string_name(&instance->script_method_names[i]);
        }
    }
    if (instance->script_method_name_texts != NULL) {
        for (int32_t i = 0; i < instance->script_method_count; i++) {
            free(instance->script_method_name_texts[i]);
        }
    }
    free(instance->script_method_names);
    free(instance->script_method_name_texts);
    free(instance->script_base_type_text);
    free(instance->script_path);
    if (instance->script_property_names != NULL) {
        for (int32_t i = 0; i < instance->script_property_count; i++) {
            kanama_ios_destroy_string_name(&instance->script_property_names[i]);
        }
        free(instance->script_property_names);
    }
    free(instance->script_property_types);
    if (instance->script_signal_names != NULL) {
        for (int32_t i = 0; i < instance->script_signal_count; i++) {
            kanama_ios_destroy_string_name(&instance->script_signal_names[i]);
        }
        free(instance->script_signal_names);
    }
    if (instance->script_signal_name_texts != NULL) {
        for (int32_t i = 0; i < instance->script_signal_count; i++) {
            free(instance->script_signal_name_texts[i]);
        }
        free(instance->script_signal_name_texts);
    }
    if (instance->script_rpc_configs != NULL) {
        for (int32_t i = 0; i < instance->script_rpc_config_count; i++) {
            free(instance->script_rpc_configs[i].method_name_text);
        }
        free(instance->script_rpc_configs);
    }
    instance->script_method_names = NULL;
    instance->script_method_name_texts = NULL;
    instance->script_base_type_text = NULL;
    instance->script_path = NULL;
    instance->script_property_names = NULL;
    instance->script_property_types = NULL;
    instance->script_property_count = 0;
    instance->script_signal_names = NULL;
    instance->script_signal_name_texts = NULL;
    instance->script_signal_count = 0;
    instance->script_rpc_configs = NULL;
    instance->script_rpc_config_count = 0;
    instance->script_path = NULL;
    instance->script_method_count = 0;
}

static void kanama_ios_cache_names(void) {
    kanama_ios_cache_name(&g_name_KanamaIosScriptLanguage, "KanamaIosScriptLanguage");
    kanama_ios_cache_name(&g_name_KanamaIosScript, "KanamaIosScript");
    kanama_ios_cache_name(&g_name_KanamaIosResourceFormatLoader, "KanamaIosResourceFormatLoader");
    kanama_ios_cache_name(&g_name_ScriptLanguageExtension, "ScriptLanguageExtension");
    kanama_ios_cache_name(&g_name_ScriptExtension, "ScriptExtension");
    kanama_ios_cache_name(&g_name_ResourceFormatLoader, "ResourceFormatLoader");
    kanama_ios_cache_name(&g_name_Label, "Label");
    kanama_ios_cache_name(&g_name__ready, "_ready");
    kanama_ios_cache_name(&g_name__process, "_process");
    kanama_ios_cache_name(&g_name__physics_process, "_physics_process");
    kanama_ios_cache_name(&g_name__input, "_input");
    kanama_ios_cache_name(&g_name__unhandled_input, "_unhandled_input");
    kanama_ios_cache_name(&g_name__shortcut_input, "_shortcut_input");
    kanama_ios_cache_name(&g_name__unhandled_key_input, "_unhandled_key_input");
    kanama_ios_cache_name(&g_name__enter_tree, "_enter_tree");
    kanama_ios_cache_name(&g_name__exit_tree, "_exit_tree");
    kanama_ios_cache_name(&g_name__get_name, "_get_name");
    kanama_ios_cache_name(&g_name__get_type, "_get_type");
    kanama_ios_cache_name(&g_name__get_extension, "_get_extension");
    kanama_ios_cache_name(&g_name__get_recognized_extensions, "_get_recognized_extensions");
    kanama_ios_cache_name(&g_name__create_script, "_create_script");
    kanama_ios_cache_name(&g_name__make_template, "_make_template");
    kanama_ios_cache_name(&g_name__is_valid, "_is_valid");
    kanama_ios_cache_name(&g_name__can_instantiate, "_can_instantiate");
    kanama_ios_cache_name(&g_name__get_language, "_get_language");
    kanama_ios_cache_name(&g_name__get_instance_base_type, "_get_instance_base_type");
    kanama_ios_cache_name(&g_name__instance_create, "_instance_create");
    kanama_ios_cache_name(&g_name__placeholder_instance_create, "_placeholder_instance_create");
    kanama_ios_cache_name(&g_name__has_source_code, "_has_source_code");
    kanama_ios_cache_name(&g_name__get_source_code, "_get_source_code");
    kanama_ios_cache_name(&g_name__set_source_code, "_set_source_code");
    kanama_ios_cache_name(&g_name__reload, "_reload");
    kanama_ios_cache_name(&g_name__get_global_name, "_get_global_name");
    kanama_ios_cache_name(&g_name__get_doc_class_name, "_get_doc_class_name");
    kanama_ios_cache_name(&g_name__inherits_script, "_inherits_script");
    kanama_ios_cache_name(&g_name__has_method, "_has_method");
    kanama_ios_cache_name(&g_name__has_static_method, "_has_static_method");
    kanama_ios_cache_name(&g_name__get_script_method_argument_count, "_get_script_method_argument_count");
    kanama_ios_cache_name(&g_name__get_method_info, "_get_method_info");
    kanama_ios_cache_name(&g_name__is_tool, "_is_tool");
    kanama_ios_cache_name(&g_name__has_script_signal, "_has_script_signal");
    kanama_ios_cache_name(&g_name__has_property_default_value, "_has_property_default_value");
    kanama_ios_cache_name(&g_name__get_property_default_value, "_get_property_default_value");
    kanama_ios_cache_name(&g_name__update_exports, "_update_exports");
    kanama_ios_cache_name(&g_name__get_script_method_list, "_get_script_method_list");
    kanama_ios_cache_name(&g_name__get_script_property_list, "_get_script_property_list");
    kanama_ios_cache_name(&g_name__get_script_signal_list, "_get_script_signal_list");
    kanama_ios_cache_name(&g_name__get_constants, "_get_constants");
    kanama_ios_cache_name(&g_name__get_members, "_get_members");
    kanama_ios_cache_name(&g_name__get_rpc_config, "_get_rpc_config");
    kanama_ios_cache_name(&g_name__instance_has, "_instance_has");
    kanama_ios_cache_name(&g_name__is_placeholder_fallback_enabled, "_is_placeholder_fallback_enabled");
    kanama_ios_cache_name(&g_name__handles_type, "_handles_type");
    kanama_ios_cache_name(&g_name__get_resource_type, "_get_resource_type");
    kanama_ios_cache_name(&g_name__load, "_load");
    kanama_ios_cache_name(&g_name__debug_get_current_stack_info, "_debug_get_current_stack_info");
    kanama_ios_cache_name(&g_name_push_back, "push_back");

    if (g_packed_string_array_push_back == NULL) {
        g_packed_string_array_push_back = g_variant_get_ptr_builtin_method(
            KANAMA_IOS_VARIANT_TYPE_PACKED_STRING_ARRAY,
            (GDExtensionConstStringNamePtr)&g_name_push_back,
            KANAMA_IOS_PACKED_STRING_ARRAY_PUSH_BACK_HASH
        );
    }
}

int64_t kanama_ios_godot_get_method_bind(
    const char *class_name,
    const char *method_name,
    int64_t hash
) {
    if (!kanama_ios_resolve_godot_api()) {
        return 0;
    }

    uint64_t class_name_storage = 0;
    uint64_t method_name_storage = 0;
    kanama_ios_init_string_name(&class_name_storage, class_name);
    kanama_ios_init_string_name(&method_name_storage, method_name);
    GDExtensionMethodBindPtr method_bind = g_classdb_get_method_bind(
        (GDExtensionConstStringNamePtr)&class_name_storage,
        (GDExtensionConstStringNamePtr)&method_name_storage,
        (GDExtensionInt)hash
    );
    kanama_ios_destroy_string_name(&method_name_storage);
    kanama_ios_destroy_string_name(&class_name_storage);
    return (int64_t)(intptr_t)method_bind;
}

// Generic typed ptrcall dispatch — the single marshalling chokepoint that every
// generated ObjectCalls helper routes through (see docs/internals/
// ios-backend-architecture.md §"Contract: generic ptrcall dispatch").
//   arg_types[i]  : KANAMA_IOS_PT_* tag describing arg i's ptrcall representation
//   arg_ptrs[i]   : pointer to arg i's value, laid out by the caller (Kotlin). For
//                   POD/struct/object the bytes ARE the ptrcall value and pass
//                   through unchanged; for STRING_NAME the pointer is a C string and
//                   the StringName is constructed here (and destroyed after).
//   ret_type      : KANAMA_IOS_PT_* (VOID for no return)
//   ret_out       : caller-allocated buffer sized for ret_type; ptrcall writes the
//                   raw native return value into it. NULL when VOID.
// Build a Packed*Array arg into a 16-byte cell from a KanamaIosPackedArgDesc; defined after the
// packed cache functions. Returns 1 on success (so the dispatch destroys only what it built).
static int kanama_ios_build_packed_arg(int32_t tag, const KanamaIosPackedArgDesc *desc, uint64_t *cell);
static void kanama_ios_destroy_packed_arg(int32_t tag, uint64_t *cell);

void kanama_ios_godot_ptrcall(
    int64_t method_bind,
    int64_t instance,
    const int32_t *arg_types,
    const void *const *arg_ptrs,
    int32_t arg_count,
    int32_t ret_type,
    void *ret_out
) {
    if (!kanama_ios_resolve_godot_api() || method_bind == 0 || instance == 0) {
        return;
    }
    if (arg_count < 0) {
        arg_count = 0;
    }
    if (arg_count > KANAMA_IOS_PTRCALL_MAX_ARGS) {
        arg_count = KANAMA_IOS_PTRCALL_MAX_ARGS;
    }

    const void *args[KANAMA_IOS_PTRCALL_MAX_ARGS];
    // Storage for CONSTRUCT-tagged builtins (StringName/String/NodePath) built from a
    // C string arg. constructed[i] holds the tag (0 = passthrough) so the matching
    // destructor runs after the call.
    uint64_t builtin_cells[KANAMA_IOS_PTRCALL_MAX_ARGS];
    // 16-byte cells for BUILD-tagged Packed*Array args (opaque size is 16 on 64-bit).
    uint64_t packed_cells[KANAMA_IOS_PTRCALL_MAX_ARGS][2];
    _Static_assert(sizeof(packed_cells[0]) >= KANAMA_IOS_PACKED_ARRAY_OPAQUE_SIZE,
        "Packed*Array dispatch cell must be >= the 16-byte 64-bit ABI opaque size");
    // 24-byte cells for CONSTRUCT-tagged Callable args (object+method Callable is 16 bytes on
    // 64-bit; 24 matches the connect path's safety margin). The method-name StringName reuses the
    // arg's builtin_cells[i] slot (a Callable arg uses no other cell in that slot).
    uint8_t callable_cells[KANAMA_IOS_PTRCALL_MAX_ARGS][24];
    int constructed[KANAMA_IOS_PTRCALL_MAX_ARGS];

    for (int32_t i = 0; i < arg_count; i++) {
        constructed[i] = 0;
        int32_t tag = (arg_types != NULL) ? arg_types[i] : KANAMA_IOS_PT_VOID;
        const char *str = (arg_ptrs != NULL) ? (const char *)arg_ptrs[i] : "";
        switch (tag) {
            case KANAMA_IOS_PT_STRING_NAME:
                builtin_cells[i] = 0;
                kanama_ios_init_string_name(&builtin_cells[i], str);
                args[i] = (const void *)&builtin_cells[i];
                constructed[i] = tag;
                break;
            case KANAMA_IOS_PT_STRING:
                builtin_cells[i] = 0;
                kanama_ios_init_string(&builtin_cells[i], str);
                args[i] = (const void *)&builtin_cells[i];
                constructed[i] = tag;
                break;
            case KANAMA_IOS_PT_NODE_PATH:
                builtin_cells[i] = 0;
                kanama_ios_init_node_path(&builtin_cells[i], str);
                args[i] = (const void *)&builtin_cells[i];
                constructed[i] = tag;
                break;
            case KANAMA_IOS_PT_PACKED_VECTOR2_ARRAY:
            case KANAMA_IOS_PT_PACKED_COLOR_ARRAY: {
                // arg ptr is a KanamaIosPackedArgDesc {count, data}; build the array into the cell.
                const KanamaIosPackedArgDesc *desc =
                    (arg_ptrs != NULL) ? (const KanamaIosPackedArgDesc *)arg_ptrs[i] : NULL;
                packed_cells[i][0] = 0;
                packed_cells[i][1] = 0;
                if (kanama_ios_build_packed_arg(tag, desc, packed_cells[i])) {
                    args[i] = (const void *)packed_cells[i];
                    constructed[i] = tag;
                } else {
                    args[i] = (const void *)packed_cells[i];  // empty/zeroed fallback
                }
                break;
            }
            case KANAMA_IOS_PT_CALLABLE: {
                // arg ptr is a KanamaIosCallableArgDesc {object_handle, method}; build an
                // object+method Callable (constructor index 2, resolved in resolve_godot_api) into
                // callable_cells[i] and pass it by pointer to ptrcall. The method-name StringName
                // lives in builtin_cells[i] so the destroy loop can free both after the call.
                const KanamaIosCallableArgDesc *desc =
                    (arg_ptrs != NULL) ? (const KanamaIosCallableArgDesc *)arg_ptrs[i] : NULL;
                memset(callable_cells[i], 0, sizeof(callable_cells[i]));
                builtin_cells[i] = 0;
                kanama_ios_init_string_name(
                    &builtin_cells[i],
                    (desc != NULL && desc->method != NULL) ? desc->method : "");
                GDExtensionObjectPtr target =
                    (desc != NULL) ? (GDExtensionObjectPtr)(intptr_t)desc->object_handle : NULL;
                const void *callable_args[2] = { &target, &builtin_cells[i] };
                g_callable_object_method_constructor(callable_cells[i], callable_args);
                args[i] = (const void *)callable_cells[i];
                constructed[i] = tag;
                break;
            }
            default:
                // POD / struct / object: the caller-laid bytes are the ptrcall value.
                args[i] = (arg_ptrs != NULL ? arg_ptrs[i] : NULL);
                break;
        }
    }

    g_object_method_bind_ptrcall(
        (GDExtensionMethodBindPtr)(intptr_t)method_bind,
        (GDExtensionObjectPtr)(intptr_t)instance,
        (const GDExtensionConstTypePtr *)args,
        (ret_type == KANAMA_IOS_PT_VOID) ? NULL : ret_out);

    for (int32_t i = 0; i < arg_count; i++) {
        switch (constructed[i]) {
            case KANAMA_IOS_PT_STRING_NAME:
                kanama_ios_destroy_string_name(&builtin_cells[i]);
                break;
            case KANAMA_IOS_PT_STRING:
                kanama_ios_destroy_string(&builtin_cells[i]);
                break;
            case KANAMA_IOS_PT_NODE_PATH:
                kanama_ios_destroy_node_path(&builtin_cells[i]);
                break;
            case KANAMA_IOS_PT_PACKED_VECTOR2_ARRAY:
            case KANAMA_IOS_PT_PACKED_COLOR_ARRAY:
                kanama_ios_destroy_packed_arg(constructed[i], packed_cells[i]);
                break;
            case KANAMA_IOS_PT_CALLABLE:
                // Destroy the Callable cell and its method-name StringName. The engine
                // copy-constructs the Callable parameter, so freeing our cell here is safe even
                // when the engine retains its own copy (callable-args-design.md, Decision 2).
                g_callable_destructor(callable_cells[i]);
                kanama_ios_destroy_string_name(&builtin_cells[i]);
                break;
            default:
                break;
        }
    }
}

// Resolve a builtin (value-type) method pointer — variant_get_ptr_builtin_method, the
// value-type analogue of get_method_bind. The hash keys the method's signature shape
// (all no-arg->Self methods on a type share one hash); the StringName selects the method.
// Returns the function pointer as int64 (0 on failure), cached Kotlin-side like a MethodBind.
int64_t kanama_ios_godot_get_builtin_method(
    int32_t variant_type,
    const char *method,
    int64_t hash
) {
    if (!kanama_ios_resolve_godot_api() || g_variant_get_ptr_builtin_method == NULL || method == NULL) {
        return 0;
    }
    uint64_t name_storage = 0;
    kanama_ios_init_string_name(&name_storage, method);
    GDExtensionPtrBuiltInMethod m = g_variant_get_ptr_builtin_method(
        (GDExtensionVariantType)variant_type,
        (GDExtensionConstStringNamePtr)&name_storage,
        (GDExtensionInt)hash);
    kanama_ios_destroy_string_name(&name_storage);
    if (m == NULL) {
        fprintf(stderr,
                "[kanama][ios][c] get_builtin_method(type=%d, %s, %lld) returned NULL\n",
                (int)variant_type, method, (long long)hash);
        fflush(stderr);
    }
    return (int64_t)(intptr_t)m;
}

// Call a builtin (value-type) method: method_ptr(base, args, ret, argc). `base` and
// `ret_out` are raw value byte buffers (laid out by Kotlin, e.g. float32 components);
// args are raw TYPE bytes like ptrcall (POD passthrough; String/StringName/NodePath are
// constructed C-side from a C string and destroyed after). Mirrors kanama_ios_godot_ptrcall
// but drives a builtin method pointer + value base instead of a MethodBind + object instance.
void kanama_ios_godot_builtin_call(
    int64_t method_ptr,
    void *base,
    const int32_t *arg_types,
    const void *const *arg_ptrs,
    int32_t arg_count,
    void *ret_out
) {
    if (!kanama_ios_resolve_godot_api() || method_ptr == 0) {
        return;
    }
    if (arg_count < 0) {
        arg_count = 0;
    }
    if (arg_count > KANAMA_IOS_PTRCALL_MAX_ARGS) {
        arg_count = KANAMA_IOS_PTRCALL_MAX_ARGS;
    }

    const void *args[KANAMA_IOS_PTRCALL_MAX_ARGS];
    uint64_t builtin_cells[KANAMA_IOS_PTRCALL_MAX_ARGS];
    int constructed[KANAMA_IOS_PTRCALL_MAX_ARGS];

    for (int32_t i = 0; i < arg_count; i++) {
        constructed[i] = 0;
        int32_t tag = (arg_types != NULL) ? arg_types[i] : KANAMA_IOS_PT_VOID;
        const char *str = (arg_ptrs != NULL) ? (const char *)arg_ptrs[i] : "";
        switch (tag) {
            case KANAMA_IOS_PT_STRING_NAME:
                builtin_cells[i] = 0;
                kanama_ios_init_string_name(&builtin_cells[i], str);
                args[i] = (const void *)&builtin_cells[i];
                constructed[i] = tag;
                break;
            case KANAMA_IOS_PT_STRING:
                builtin_cells[i] = 0;
                kanama_ios_init_string(&builtin_cells[i], str);
                args[i] = (const void *)&builtin_cells[i];
                constructed[i] = tag;
                break;
            case KANAMA_IOS_PT_NODE_PATH:
                builtin_cells[i] = 0;
                kanama_ios_init_node_path(&builtin_cells[i], str);
                args[i] = (const void *)&builtin_cells[i];
                constructed[i] = tag;
                break;
            default:
                args[i] = (arg_ptrs != NULL ? arg_ptrs[i] : NULL);
                break;
        }
    }

    ((GDExtensionPtrBuiltInMethod)(intptr_t)method_ptr)(
        (GDExtensionTypePtr)base,
        (const GDExtensionConstTypePtr *)args,
        (GDExtensionTypePtr)ret_out,
        (int32_t)arg_count);

    for (int32_t i = 0; i < arg_count; i++) {
        switch (constructed[i]) {
            case KANAMA_IOS_PT_STRING_NAME: kanama_ios_destroy_string_name(&builtin_cells[i]); break;
            case KANAMA_IOS_PT_STRING:      kanama_ios_destroy_string(&builtin_cells[i]); break;
            case KANAMA_IOS_PT_NODE_PATH:   kanama_ios_destroy_node_path(&builtin_cells[i]); break;
            default: break;
        }
    }
}

void kanama_ios_godot_ptrcall_string_arg(
    int64_t method_bind,
    int64_t instance,
    const char *value
) {
    if (!kanama_ios_resolve_godot_api()) {
        return;
    }
    if (method_bind == 0 || instance == 0) {
        fprintf(stderr, "[kanama][ios][c] warning: string ptrcall skipped for null method/object\n");
        return;
    }

    uint64_t string_storage = 0;
    kanama_ios_init_string(&string_storage, value);
    const GDExtensionConstTypePtr args[1] = {
        (GDExtensionConstTypePtr)&string_storage,
    };
    g_object_method_bind_ptrcall(
        (GDExtensionMethodBindPtr)(intptr_t)method_bind,
        (GDExtensionObjectPtr)(intptr_t)instance,
        args,
        NULL
    );
    kanama_ios_destroy_string(&string_storage);
}

int64_t kanama_ios_godot_ptrcall_no_args_ret_string(
    int64_t method_bind,
    int64_t instance,
    char *out_buf,
    int64_t buf_size
) {
    if (!kanama_ios_resolve_godot_api() || method_bind == 0 || instance == 0) {
        return -1;
    }
    if (g_string_to_utf8_chars == NULL || g_object_method_bind_ptrcall == NULL) {
        return -1;
    }

    // ptrcall writes the returned Godot String (a single 8-byte CowData pointer on
    // 64-bit; 0 for the empty string) into this cell — same storage convention as
    // kanama_ios_init_string.
    uint64_t string_storage = 0;
    g_object_method_bind_ptrcall(
        (GDExtensionMethodBindPtr)(intptr_t)method_bind,
        (GDExtensionObjectPtr)(intptr_t)instance,
        NULL,
        &string_storage
    );

    // string_to_utf8_chars: NULL buffer => measure only; otherwise writes up to
    // buf_size bytes (no null terminator) and returns the FULL byte length.
    int64_t length = (int64_t)g_string_to_utf8_chars(
        (GDExtensionConstStringPtr)&string_storage,
        (out_buf != NULL && buf_size > 0) ? out_buf : NULL,
        (out_buf != NULL && buf_size > 0) ? buf_size : 0
    );

    kanama_ios_destroy_string(&string_storage);
    return length;
}

int64_t kanama_ios_godot_ptrcall_no_args_ret_string_name(
    int64_t method_bind,
    int64_t instance,
    char *out_buf,
    int64_t buf_size
) {
    if (!kanama_ios_resolve_godot_api() || method_bind == 0 || instance == 0) {
        return -1;
    }
    if (g_string_to_utf8_chars == NULL || g_object_method_bind_ptrcall == NULL ||
        g_string_from_string_name_constructor == NULL) {
        return -1;
    }

    // ptrcall writes the returned Godot StringName (an 8-byte interned pointer) here.
    uint64_t string_name_storage = 0;
    g_object_method_bind_ptrcall(
        (GDExtensionMethodBindPtr)(intptr_t)method_bind,
        (GDExtensionObjectPtr)(intptr_t)instance,
        NULL,
        &string_name_storage
    );

    // No GDExtension StringName->utf8 exists, so build a String(from: StringName)
    // and UTF-8 encode that (same path as the String-return helper).
    uint64_t string_storage = 0;
    const GDExtensionConstTypePtr ctor_args[1] = {
        (GDExtensionConstTypePtr)&string_name_storage,
    };
    g_string_from_string_name_constructor(
        (GDExtensionUninitializedTypePtr)&string_storage, ctor_args);

    int64_t length = (int64_t)g_string_to_utf8_chars(
        (GDExtensionConstStringPtr)&string_storage,
        (out_buf != NULL && buf_size > 0) ? out_buf : NULL,
        (out_buf != NULL && buf_size > 0) ? buf_size : 0
    );

    kanama_ios_destroy_string(&string_storage);
    kanama_ios_destroy_string_name(&string_name_storage);
    return length;
}

int64_t kanama_ios_godot_ptrcall_no_args_ret_node_path(
    int64_t method_bind,
    int64_t instance,
    char *out_buf,
    int64_t buf_size
) {
    if (!kanama_ios_resolve_godot_api() || method_bind == 0 || instance == 0) {
        return -1;
    }
    if (g_string_to_utf8_chars == NULL || g_object_method_bind_ptrcall == NULL ||
        g_string_from_node_path_constructor == NULL) {
        return -1;
    }

    // ptrcall writes the returned Godot NodePath (an 8-byte CowData pointer) here.
    uint64_t node_path_storage = 0;
    g_object_method_bind_ptrcall(
        (GDExtensionMethodBindPtr)(intptr_t)method_bind,
        (GDExtensionObjectPtr)(intptr_t)instance,
        NULL,
        &node_path_storage
    );

    // No GDExtension NodePath->utf8 exists, so build a String(from: NodePath) and UTF-8
    // encode that (same path as the StringName-return helper).
    uint64_t string_storage = 0;
    const GDExtensionConstTypePtr ctor_args[1] = {
        (GDExtensionConstTypePtr)&node_path_storage,
    };
    g_string_from_node_path_constructor(
        (GDExtensionUninitializedTypePtr)&string_storage, ctor_args);

    int64_t length = (int64_t)g_string_to_utf8_chars(
        (GDExtensionConstStringPtr)&string_storage,
        (out_buf != NULL && buf_size > 0) ? out_buf : NULL,
        (out_buf != NULL && buf_size > 0) ? buf_size : 0
    );

    kanama_ios_destroy_string(&string_storage);
    kanama_ios_destroy_node_path(&node_path_storage);
    return length;
}

// Lazily resolve the PackedInt32Array read-back trio (destructor + "size" builtin
// method + operator_index_const). Must be called after kanama_ios_resolve_godot_api().
static void kanama_ios_cache_packed_int32_methods(void) {
    if (g_packed_int32_array_destructor == NULL && g_variant_get_ptr_destructor != NULL) {
        g_packed_int32_array_destructor =
            g_variant_get_ptr_destructor(KANAMA_IOS_VARIANT_TYPE_PACKED_INT32_ARRAY);
    }
    if (g_packed_int32_array_size_method == NULL && g_variant_get_ptr_builtin_method != NULL) {
        uint64_t name_storage = 0;
        kanama_ios_init_string_name(&name_storage, "size");
        g_packed_int32_array_size_method = g_variant_get_ptr_builtin_method(
            KANAMA_IOS_VARIANT_TYPE_PACKED_INT32_ARRAY,
            (GDExtensionConstStringNamePtr)&name_storage,
            (GDExtensionInt)KANAMA_IOS_PACKED_INT32_ARRAY_SIZE_HASH
        );
        kanama_ios_destroy_string_name(&name_storage);
    }
    if (g_packed_int32_array_operator_index_const == NULL) {
        g_packed_int32_array_operator_index_const =
            (GDExtensionInterfacePackedInt32ArrayOperatorIndexConst)kanama_ios_lookup(
                "packed_int32_array_operator_index_const");
    }
}

// PackedInt32Array no-arg getter return. ptrcall writes the returned array (an 8-byte
// CowData pointer) into a cell; we read its element count via the "size" builtin method,
// then copy up to buf_cap int32 elements out through operator_index_const. Two-call
// length protocol like the String/NodePath helpers: Kotlin calls once with out_buf=NULL
// to learn the count, allocates, then calls again to fill. Returns the FULL element count
// (negative on resolution failure).
int64_t kanama_ios_godot_ptrcall_no_args_ret_packed_int32_array(
    int64_t method_bind,
    int64_t instance,
    int32_t *out_buf,
    int64_t buf_cap
) {
    if (!kanama_ios_resolve_godot_api() || method_bind == 0 || instance == 0) {
        return -1;
    }
    if (g_object_method_bind_ptrcall == NULL) {
        return -1;
    }
    kanama_ios_cache_packed_int32_methods();
    if (g_packed_int32_array_size_method == NULL ||
        g_packed_int32_array_operator_index_const == NULL) {
        return -1;
    }

    // PackedInt32Array's opaque size is 16 bytes in the 64-bit GDExtension ABI — NOT 8 like
    // String/NodePath. The macro sizes the slot from KANAMA_IOS_PACKED_ARRAY_OPAQUE_SIZE and
    // static-asserts it, so the ptrcall return-encode can't overflow the stack.
    KANAMA_IOS_PACKED_ARRAY_STORAGE(array_storage);
    g_object_method_bind_ptrcall(
        (GDExtensionMethodBindPtr)(intptr_t)method_bind,
        (GDExtensionObjectPtr)(intptr_t)instance,
        NULL,
        array_storage
    );

    int64_t count = 0;
    g_packed_int32_array_size_method(array_storage, NULL, &count, 0);

    if (out_buf != NULL && buf_cap > 0 && count > 0) {
        int64_t n = (count < buf_cap) ? count : buf_cap;
        for (int64_t i = 0; i < n; i++) {
            const int32_t *elem =
                g_packed_int32_array_operator_index_const(array_storage, (GDExtensionInt)i);
            out_buf[i] = (elem != NULL) ? *elem : 0;
        }
    }

    if (g_packed_int32_array_destructor != NULL) {
        g_packed_int32_array_destructor(array_storage);
    }
    return count;
}

// Lazily resolve the PackedFloat32Array read-back trio. Mirror of the int32 variant.
static void kanama_ios_cache_packed_float32_methods(void) {
    if (g_packed_float32_array_destructor == NULL && g_variant_get_ptr_destructor != NULL) {
        g_packed_float32_array_destructor =
            g_variant_get_ptr_destructor(KANAMA_IOS_VARIANT_TYPE_PACKED_FLOAT32_ARRAY);
    }
    if (g_packed_float32_array_size_method == NULL && g_variant_get_ptr_builtin_method != NULL) {
        uint64_t name_storage = 0;
        kanama_ios_init_string_name(&name_storage, "size");
        g_packed_float32_array_size_method = g_variant_get_ptr_builtin_method(
            KANAMA_IOS_VARIANT_TYPE_PACKED_FLOAT32_ARRAY,
            (GDExtensionConstStringNamePtr)&name_storage,
            (GDExtensionInt)KANAMA_IOS_PACKED_FLOAT32_ARRAY_SIZE_HASH
        );
        kanama_ios_destroy_string_name(&name_storage);
    }
    if (g_packed_float32_array_operator_index_const == NULL) {
        g_packed_float32_array_operator_index_const =
            (GDExtensionInterfacePackedFloat32ArrayOperatorIndexConst)kanama_ios_lookup(
                "packed_float32_array_operator_index_const");
    }
    // Build path (List<Float> -> ptrcall arg): default constructor + push_back.
    if (g_packed_float32_array_constructor == NULL && g_variant_get_ptr_constructor != NULL) {
        g_packed_float32_array_constructor =
            g_variant_get_ptr_constructor(KANAMA_IOS_VARIANT_TYPE_PACKED_FLOAT32_ARRAY, 0);
    }
    if (g_packed_float32_array_push_back == NULL && g_variant_get_ptr_builtin_method != NULL) {
        uint64_t name_storage = 0;
        kanama_ios_init_string_name(&name_storage, "push_back");
        g_packed_float32_array_push_back = g_variant_get_ptr_builtin_method(
            KANAMA_IOS_VARIANT_TYPE_PACKED_FLOAT32_ARRAY,
            (GDExtensionConstStringNamePtr)&name_storage,
            (GDExtensionInt)KANAMA_IOS_PACKED_FLOAT32_ARRAY_PUSH_BACK_HASH
        );
        kanama_ios_destroy_string_name(&name_storage);
    }
}

// PackedFloat32Array no-arg getter return — element type float32. Same protocol and 16-byte
// storage as the int32 variant (Packed*Array opaque size is 16 on 64-bit). Returns the FULL
// element count (negative on resolution failure).
int64_t kanama_ios_godot_ptrcall_no_args_ret_packed_float32_array(
    int64_t method_bind,
    int64_t instance,
    float *out_buf,
    int64_t buf_cap
) {
    if (!kanama_ios_resolve_godot_api() || method_bind == 0 || instance == 0) {
        return -1;
    }
    if (g_object_method_bind_ptrcall == NULL) {
        return -1;
    }
    kanama_ios_cache_packed_float32_methods();
    if (g_packed_float32_array_size_method == NULL ||
        g_packed_float32_array_operator_index_const == NULL) {
        return -1;
    }

    KANAMA_IOS_PACKED_ARRAY_STORAGE(array_storage);
    g_object_method_bind_ptrcall(
        (GDExtensionMethodBindPtr)(intptr_t)method_bind,
        (GDExtensionObjectPtr)(intptr_t)instance,
        NULL,
        array_storage
    );

    int64_t count = 0;
    g_packed_float32_array_size_method(array_storage, NULL, &count, 0);

    if (out_buf != NULL && buf_cap > 0 && count > 0) {
        int64_t n = (count < buf_cap) ? count : buf_cap;
        for (int64_t i = 0; i < n; i++) {
            const float *elem =
                g_packed_float32_array_operator_index_const(array_storage, (GDExtensionInt)i);
            out_buf[i] = (elem != NULL) ? *elem : 0.0f;
        }
    }

    if (g_packed_float32_array_destructor != NULL) {
        g_packed_float32_array_destructor(array_storage);
    }
    return count;
}

// PackedFloat32Array ARG (build-from-list): construct an empty array, push_back each element
// from the caller's flat float buffer, ptrcall the (single-arg, void) method with it, then
// destruct. 16-byte storage (Packed*Array opaque size). This is the inverse of the read-back.
void kanama_ios_godot_ptrcall_with_packed_float32_arg(
    int64_t method_bind,
    int64_t instance,
    const float *elems,
    int64_t count
) {
    if (!kanama_ios_resolve_godot_api() || method_bind == 0 || instance == 0) {
        return;
    }
    if (g_object_method_bind_ptrcall == NULL) {
        return;
    }
    kanama_ios_cache_packed_float32_methods();
    if (g_packed_float32_array_constructor == NULL || g_packed_float32_array_push_back == NULL) {
        return;
    }

    KANAMA_IOS_PACKED_ARRAY_STORAGE(array_storage);
    g_packed_float32_array_constructor((GDExtensionUninitializedTypePtr)array_storage, NULL);

    if (count < 0) {
        count = 0;
    }
    for (int64_t i = 0; i < count; i++) {
        // push_back(value: float) takes the scalar as an 8-byte DOUBLE in the ptr-ABI (Godot's
        // script float is double), even though the array stores float32. Passing a 4-byte float
        // makes push_back read 8 bytes of garbage. Same quirk as scalar-float ptrcall returns.
        double value = (elems != NULL) ? (double)elems[i] : 0.0;
        const GDExtensionConstTypePtr pb_args[1] = { (GDExtensionConstTypePtr)&value };
        uint8_t pb_ret = 0;
        g_packed_float32_array_push_back(array_storage, pb_args, &pb_ret, 1);
    }

    const void *args[1] = { array_storage };
    g_object_method_bind_ptrcall(
        (GDExtensionMethodBindPtr)(intptr_t)method_bind,
        (GDExtensionObjectPtr)(intptr_t)instance,
        (const GDExtensionConstTypePtr *)args,
        NULL);

    if (g_packed_float32_array_destructor != NULL) {
        g_packed_float32_array_destructor(array_storage);
    }
}

// Lazily resolve the PackedVector2Array read-back trio.
static void kanama_ios_cache_packed_vector2_methods(void) {
    if (g_packed_vector2_array_destructor == NULL && g_variant_get_ptr_destructor != NULL) {
        g_packed_vector2_array_destructor =
            g_variant_get_ptr_destructor(KANAMA_IOS_VARIANT_TYPE_PACKED_VECTOR2_ARRAY);
    }
    if (g_packed_vector2_array_size_method == NULL && g_variant_get_ptr_builtin_method != NULL) {
        uint64_t name_storage = 0;
        kanama_ios_init_string_name(&name_storage, "size");
        g_packed_vector2_array_size_method = g_variant_get_ptr_builtin_method(
            KANAMA_IOS_VARIANT_TYPE_PACKED_VECTOR2_ARRAY,
            (GDExtensionConstStringNamePtr)&name_storage,
            (GDExtensionInt)KANAMA_IOS_PACKED_VECTOR2_ARRAY_SIZE_HASH
        );
        kanama_ios_destroy_string_name(&name_storage);
    }
    if (g_packed_vector2_array_operator_index_const == NULL) {
        g_packed_vector2_array_operator_index_const =
            (GDExtensionInterfacePackedVector2ArrayOperatorIndexConst)kanama_ios_lookup(
                "packed_vector2_array_operator_index_const");
    }
    if (g_packed_vector2_array_constructor == NULL && g_variant_get_ptr_constructor != NULL) {
        g_packed_vector2_array_constructor =
            g_variant_get_ptr_constructor(KANAMA_IOS_VARIANT_TYPE_PACKED_VECTOR2_ARRAY, 0);
    }
    if (g_packed_vector2_array_push_back == NULL && g_variant_get_ptr_builtin_method != NULL) {
        uint64_t name_storage = 0;
        kanama_ios_init_string_name(&name_storage, "push_back");
        g_packed_vector2_array_push_back = g_variant_get_ptr_builtin_method(
            KANAMA_IOS_VARIANT_TYPE_PACKED_VECTOR2_ARRAY,
            (GDExtensionConstStringNamePtr)&name_storage,
            (GDExtensionInt)KANAMA_IOS_PACKED_VECTOR2_ARRAY_PUSH_BACK_HASH
        );
        kanama_ios_destroy_string_name(&name_storage);
    }
}

// PackedVector2Array no-arg getter return. Each element is a Vector2 (2 float32 on iOS);
// out_buf receives count*2 floats (buf_cap is an ELEMENT count). 16-byte storage. Returns
// the FULL element count (negative on resolution failure).
int64_t kanama_ios_godot_ptrcall_no_args_ret_packed_vector2_array(
    int64_t method_bind,
    int64_t instance,
    float *out_buf,
    int64_t buf_cap
) {
    if (!kanama_ios_resolve_godot_api() || method_bind == 0 || instance == 0) {
        return -1;
    }
    if (g_object_method_bind_ptrcall == NULL) {
        return -1;
    }
    kanama_ios_cache_packed_vector2_methods();
    if (g_packed_vector2_array_size_method == NULL ||
        g_packed_vector2_array_operator_index_const == NULL) {
        return -1;
    }

    KANAMA_IOS_PACKED_ARRAY_STORAGE(array_storage);
    g_object_method_bind_ptrcall(
        (GDExtensionMethodBindPtr)(intptr_t)method_bind,
        (GDExtensionObjectPtr)(intptr_t)instance,
        NULL,
        array_storage
    );

    int64_t count = 0;
    g_packed_vector2_array_size_method(array_storage, NULL, &count, 0);

    if (out_buf != NULL && buf_cap > 0 && count > 0) {
        int64_t n = (count < buf_cap) ? count : buf_cap;
        for (int64_t i = 0; i < n; i++) {
            const float *elem = (const float *)g_packed_vector2_array_operator_index_const(
                array_storage, (GDExtensionInt)i);
            out_buf[i * 2] = (elem != NULL) ? elem[0] : 0.0f;
            out_buf[i * 2 + 1] = (elem != NULL) ? elem[1] : 0.0f;
        }
    }

    if (g_packed_vector2_array_destructor != NULL) {
        g_packed_vector2_array_destructor(array_storage);
    }
    return count;
}

// Lazily resolve the PackedColorArray read-back trio.
static void kanama_ios_cache_packed_color_methods(void) {
    if (g_packed_color_array_destructor == NULL && g_variant_get_ptr_destructor != NULL) {
        g_packed_color_array_destructor =
            g_variant_get_ptr_destructor(KANAMA_IOS_VARIANT_TYPE_PACKED_COLOR_ARRAY);
    }
    if (g_packed_color_array_size_method == NULL && g_variant_get_ptr_builtin_method != NULL) {
        uint64_t name_storage = 0;
        kanama_ios_init_string_name(&name_storage, "size");
        g_packed_color_array_size_method = g_variant_get_ptr_builtin_method(
            KANAMA_IOS_VARIANT_TYPE_PACKED_COLOR_ARRAY,
            (GDExtensionConstStringNamePtr)&name_storage,
            (GDExtensionInt)KANAMA_IOS_PACKED_COLOR_ARRAY_SIZE_HASH
        );
        kanama_ios_destroy_string_name(&name_storage);
    }
    if (g_packed_color_array_operator_index_const == NULL) {
        g_packed_color_array_operator_index_const =
            (GDExtensionInterfacePackedColorArrayOperatorIndexConst)kanama_ios_lookup(
                "packed_color_array_operator_index_const");
    }
    if (g_packed_color_array_constructor == NULL && g_variant_get_ptr_constructor != NULL) {
        g_packed_color_array_constructor =
            g_variant_get_ptr_constructor(KANAMA_IOS_VARIANT_TYPE_PACKED_COLOR_ARRAY, 0);
    }
    if (g_packed_color_array_push_back == NULL && g_variant_get_ptr_builtin_method != NULL) {
        uint64_t name_storage = 0;
        kanama_ios_init_string_name(&name_storage, "push_back");
        g_packed_color_array_push_back = g_variant_get_ptr_builtin_method(
            KANAMA_IOS_VARIANT_TYPE_PACKED_COLOR_ARRAY,
            (GDExtensionConstStringNamePtr)&name_storage,
            (GDExtensionInt)KANAMA_IOS_PACKED_COLOR_ARRAY_PUSH_BACK_HASH
        );
        kanama_ios_destroy_string_name(&name_storage);
    }
}

// PackedColorArray no-arg getter return. Each element is a Color (4 float32 RGBA); out_buf
// receives count*4 floats (buf_cap is an ELEMENT count). 16-byte storage. Returns the FULL
// element count (negative on resolution failure).
int64_t kanama_ios_godot_ptrcall_no_args_ret_packed_color_array(
    int64_t method_bind,
    int64_t instance,
    float *out_buf,
    int64_t buf_cap
) {
    if (!kanama_ios_resolve_godot_api() || method_bind == 0 || instance == 0) {
        return -1;
    }
    if (g_object_method_bind_ptrcall == NULL) {
        return -1;
    }
    kanama_ios_cache_packed_color_methods();
    if (g_packed_color_array_size_method == NULL ||
        g_packed_color_array_operator_index_const == NULL) {
        return -1;
    }

    KANAMA_IOS_PACKED_ARRAY_STORAGE(array_storage);
    g_object_method_bind_ptrcall(
        (GDExtensionMethodBindPtr)(intptr_t)method_bind,
        (GDExtensionObjectPtr)(intptr_t)instance,
        NULL,
        array_storage
    );

    int64_t count = 0;
    g_packed_color_array_size_method(array_storage, NULL, &count, 0);

    if (out_buf != NULL && buf_cap > 0 && count > 0) {
        int64_t n = (count < buf_cap) ? count : buf_cap;
        for (int64_t i = 0; i < n; i++) {
            const float *elem = (const float *)g_packed_color_array_operator_index_const(
                array_storage, (GDExtensionInt)i);
            for (int c = 0; c < 4; c++) {
                out_buf[i * 4 + c] = (elem != NULL) ? elem[c] : 0.0f;
            }
        }
    }

    if (g_packed_color_array_destructor != NULL) {
        g_packed_color_array_destructor(array_storage);
    }
    return count;
}

// Build a BUILD-tagged Packed*Array arg (Vector2/Color) into a 16-byte cell from a flat float
// descriptor (Vector2 = 2 float32 per element, Color = 4 float32; value types, NOT scalar-widened).
// Returns 1 if it constructed an array the caller must destroy. Used only by the generic dispatch.
static int kanama_ios_build_packed_arg(int32_t tag, const KanamaIosPackedArgDesc *desc, uint64_t *cell) {
    if (desc == NULL) {
        return 0;
    }
    int64_t count = (desc->count < 0) ? 0 : desc->count;
    const float *f = (const float *)desc->data;
    if (tag == KANAMA_IOS_PT_PACKED_VECTOR2_ARRAY) {
        kanama_ios_cache_packed_vector2_methods();
        if (g_packed_vector2_array_constructor == NULL || g_packed_vector2_array_push_back == NULL) {
            return 0;
        }
        g_packed_vector2_array_constructor((GDExtensionUninitializedTypePtr)cell, NULL);
        for (int64_t i = 0; i < count; i++) {
            const GDExtensionConstTypePtr pb[1] = { (GDExtensionConstTypePtr)(f + i * 2) };
            uint8_t r = 0;
            g_packed_vector2_array_push_back(cell, pb, &r, 1);
        }
        return 1;
    }
    if (tag == KANAMA_IOS_PT_PACKED_COLOR_ARRAY) {
        kanama_ios_cache_packed_color_methods();
        if (g_packed_color_array_constructor == NULL || g_packed_color_array_push_back == NULL) {
            return 0;
        }
        g_packed_color_array_constructor((GDExtensionUninitializedTypePtr)cell, NULL);
        for (int64_t i = 0; i < count; i++) {
            const GDExtensionConstTypePtr pb[1] = { (GDExtensionConstTypePtr)(f + i * 4) };
            uint8_t r = 0;
            g_packed_color_array_push_back(cell, pb, &r, 1);
        }
        return 1;
    }
    return 0;
}

static void kanama_ios_destroy_packed_arg(int32_t tag, uint64_t *cell) {
    if (tag == KANAMA_IOS_PT_PACKED_VECTOR2_ARRAY && g_packed_vector2_array_destructor != NULL) {
        g_packed_vector2_array_destructor(cell);
    } else if (tag == KANAMA_IOS_PT_PACKED_COLOR_ARRAY && g_packed_color_array_destructor != NULL) {
        g_packed_color_array_destructor(cell);
    }
}

// Lazily resolve the PackedStringArray read-back trio (destructor + size + operator_index_const).
static void kanama_ios_cache_packed_string_methods(void) {
    if (g_packed_string_array_destructor == NULL && g_variant_get_ptr_destructor != NULL) {
        g_packed_string_array_destructor =
            g_variant_get_ptr_destructor(KANAMA_IOS_VARIANT_TYPE_PACKED_STRING_ARRAY);
    }
    if (g_packed_string_array_size_method == NULL && g_variant_get_ptr_builtin_method != NULL) {
        uint64_t name_storage = 0;
        kanama_ios_init_string_name(&name_storage, "size");
        g_packed_string_array_size_method = g_variant_get_ptr_builtin_method(
            KANAMA_IOS_VARIANT_TYPE_PACKED_STRING_ARRAY,
            (GDExtensionConstStringNamePtr)&name_storage,
            (GDExtensionInt)KANAMA_IOS_PACKED_STRING_ARRAY_SIZE_HASH
        );
        kanama_ios_destroy_string_name(&name_storage);
    }
    if (g_packed_string_array_operator_index_const == NULL) {
        g_packed_string_array_operator_index_const =
            (GDExtensionInterfacePackedStringArrayOperatorIndexConst)kanama_ios_lookup(
                "packed_string_array_operator_index_const");
    }
}

// PackedStringArray no-arg getter return — VARIABLE-LENGTH elements (each is a Godot String).
// Unlike the fixed-width packed types, the result is serialized into out_buf as a length-
// prefixed blob: [count:int32][len0:int32][utf8_0 bytes][len1:int32][utf8_1]...  Two-call
// protocol: Kotlin calls once with out_buf=NULL to learn the total byte size, allocates, then
// calls again to fill. Returns the FULL byte size (negative on resolution failure). Per element
// operator_index_const yields the String ptr, then string_to_utf8_chars encodes it.
int64_t kanama_ios_godot_ptrcall_no_args_ret_packed_string_array(
    int64_t method_bind,
    int64_t instance,
    char *out_buf,
    int64_t buf_size
) {
    if (!kanama_ios_resolve_godot_api() || method_bind == 0 || instance == 0) {
        return -1;
    }
    if (g_object_method_bind_ptrcall == NULL || g_string_to_utf8_chars == NULL) {
        return -1;
    }
    kanama_ios_cache_packed_string_methods();
    if (g_packed_string_array_size_method == NULL ||
        g_packed_string_array_operator_index_const == NULL) {
        return -1;
    }

    KANAMA_IOS_PACKED_ARRAY_STORAGE(array_storage);
    g_object_method_bind_ptrcall(
        (GDExtensionMethodBindPtr)(intptr_t)method_bind,
        (GDExtensionObjectPtr)(intptr_t)instance,
        NULL,
        array_storage
    );

    int64_t count = 0;
    g_packed_string_array_size_method(array_storage, NULL, &count, 0);
    if (count < 0) {
        count = 0;
    }

    int can_write = (out_buf != NULL && buf_size >= 4);
    if (can_write) {
        int32_t count32 = (int32_t)count;
        memcpy(out_buf, &count32, 4);
    }
    int64_t total = 4;  // leading count header

    for (int64_t i = 0; i < count; i++) {
        GDExtensionStringPtr str =
            g_packed_string_array_operator_index_const(array_storage, (GDExtensionInt)i);
        int64_t len = (str != NULL)
            ? (int64_t)g_string_to_utf8_chars((GDExtensionConstStringPtr)str, NULL, 0)
            : 0;
        if (len < 0) {
            len = 0;
        }
        // Write [len][utf8] only when the whole record fits; otherwise just keep tallying so
        // the first (measuring) call still returns the correct total.
        if (can_write && buf_size >= total + 4 + len) {
            int32_t len32 = (int32_t)len;
            memcpy(out_buf + total, &len32, 4);
            if (len > 0) {
                g_string_to_utf8_chars(
                    (GDExtensionConstStringPtr)str, out_buf + total + 4, len);
            }
        }
        total += 4 + len;
    }

    if (g_packed_string_array_destructor != NULL) {
        g_packed_string_array_destructor(array_storage);
    }
    return total;
}

// Resolves the generic Array size/get/destructor builtins; defined later in the file.
static void kanama_ios_cache_array_methods(void);

// ptrcall (no args) -> a typed builtin Array; serialize its elements into a length-prefixed blob:
//   [int32 count] ( [int32 byteLen][bytes] )*
// elem_kind selects the element decode: INT32/INT64 -> 8-byte int64 LE; STRING/STRING_NAME/
// NODE_PATH -> the element's utf8 (StringName/NodePath go via String(from: ...)). Two-call length
// protocol like the Packed* helpers: out_buf=NULL/buf_size=0 measures the total byte count first.
// Returns the total bytes (>= 4), or -1 on error. Uses the generic Array size/get builtins.
int64_t kanama_ios_godot_ptrcall_no_args_ret_typed_array_blob(
    int64_t method_bind,
    int64_t instance,
    int32_t elem_kind,
    char *out_buf,
    int64_t buf_size
) {
    if (!kanama_ios_resolve_godot_api() || method_bind == 0 || instance == 0) {
        return -1;
    }
    if (g_object_method_bind_ptrcall == NULL) {
        return -1;
    }
    kanama_ios_cache_array_methods();
    if (g_array_size_method == NULL || g_array_get_method == NULL) {
        return -1;
    }

    // Array opaque is 8 bytes on 64-bit (OPAQUE_8_BYTE_TYPES), like the ret-object-array slot.
    uint64_t array_storage = 0;
    g_object_method_bind_ptrcall(
        (GDExtensionMethodBindPtr)(intptr_t)method_bind,
        (GDExtensionObjectPtr)(intptr_t)instance,
        NULL,
        &array_storage
    );

    int64_t count = 0;
    g_array_size_method(&array_storage, NULL, &count, 0);
    if (count < 0) {
        count = 0;
    }

    int can_write = (out_buf != NULL && buf_size >= 4);
    if (can_write) {
        int32_t count32 = (int32_t)count;
        memcpy(out_buf, &count32, 4);
    }
    int64_t total = 4;  // leading count header

    for (int64_t i = 0; i < count; i++) {
        uint8_t elem_variant[24];
        memset(elem_variant, 0, sizeof(elem_variant));
        const GDExtensionConstTypePtr get_args[1] = { (GDExtensionConstTypePtr)&i };
        g_array_get_method(&array_storage, get_args, elem_variant, 1);

        int64_t len = 0;
        if (elem_kind == KANAMA_IOS_PT_INT32 || elem_kind == KANAMA_IOS_PT_INT64) {
            int64_t v = 0;
            if (g_variant_to_int != NULL) {
                g_variant_to_int(&v, elem_variant);
            }
            len = 8;
            if (can_write && buf_size >= total + 4 + len) {
                int32_t len32 = (int32_t)len;
                memcpy(out_buf + total, &len32, 4);
                memcpy(out_buf + total + 4, &v, 8);
            }
        } else if (elem_kind == KANAMA_IOS_PT_PLANE) {
            // Plane = 4x float32 (normal.x, normal.y, normal.z, d) — POD, fixed 16-byte record.
            float plane[4] = { 0.0f, 0.0f, 0.0f, 0.0f };
            if (g_variant_to_plane != NULL) {
                g_variant_to_plane(plane, elem_variant);
            }
            len = 16;
            if (can_write && buf_size >= total + 4 + len) {
                int32_t len32 = (int32_t)len;
                memcpy(out_buf + total, &len32, 4);
                memcpy(out_buf + total + 4, plane, 16);
            }
        } else {
            // STRING_NAME / NODE_PATH / STRING -> Godot String -> utf8.
            uint64_t string_storage = 0;
            int have_string = 0;
            if (elem_kind == KANAMA_IOS_PT_STRING_NAME && g_variant_to_string_name != NULL &&
                g_string_from_string_name_constructor != NULL) {
                uint64_t sn = 0;
                g_variant_to_string_name(&sn, elem_variant);
                const GDExtensionConstTypePtr ca[1] = { (GDExtensionConstTypePtr)&sn };
                g_string_from_string_name_constructor((GDExtensionUninitializedTypePtr)&string_storage, ca);
                kanama_ios_destroy_string_name(&sn);
                have_string = 1;
            } else if (elem_kind == KANAMA_IOS_PT_NODE_PATH && g_variant_to_node_path != NULL &&
                       g_string_from_node_path_constructor != NULL) {
                uint64_t np = 0;
                g_variant_to_node_path(&np, elem_variant);
                const GDExtensionConstTypePtr ca[1] = { (GDExtensionConstTypePtr)&np };
                g_string_from_node_path_constructor((GDExtensionUninitializedTypePtr)&string_storage, ca);
                kanama_ios_destroy_node_path(&np);
                have_string = 1;
            } else if (g_variant_to_string != NULL) {
                g_variant_to_string(&string_storage, elem_variant);
                have_string = 1;
            }
            if (have_string && g_string_to_utf8_chars != NULL) {
                len = (int64_t)g_string_to_utf8_chars(
                    (GDExtensionConstStringPtr)&string_storage, NULL, 0);
                if (len < 0) {
                    len = 0;
                }
                if (can_write && buf_size >= total + 4 + len) {
                    int32_t len32 = (int32_t)len;
                    memcpy(out_buf + total, &len32, 4);
                    if (len > 0) {
                        g_string_to_utf8_chars(
                            (GDExtensionConstStringPtr)&string_storage, out_buf + total + 4, len);
                    }
                }
            }
            if (have_string) {
                kanama_ios_destroy_string(&string_storage);
            }
        }

        if (g_variant_destroy != NULL) {
            g_variant_destroy(elem_variant);
        }
        total += 4 + len;
    }

    if (g_array_destructor != NULL) {
        g_array_destructor(&array_storage);
    }
    return total;
}

// ptrcall (with args) -> a generic Array, serialized to a SELF-DESCRIBING blob:
//   [int32 count] ( [int32 variant_type][int32 byteLen][bytes] )*
// Per-element decode by Variant type: BOOL(1 byte)/INT(8 int64)/FLOAT(8 double)/OBJECT(8 handle)/
// STRING|STRING_NAME|NODE_PATH(utf8). Non-scalar elements (Dictionary, nested Array, ...) keep their
// type tag with byteLen 0 so Kotlin surfaces null (the scalar-decode philosophy). Two-call length
// protocol (out_buf=NULL/buf_size=0 measures). Args are PT-tagged like the other arg-bearing helpers.
int64_t kanama_ios_godot_ptrcall_ret_variant_array_blob(
    int64_t method_bind,
    int64_t instance,
    const int32_t *arg_types,
    const void *const *arg_ptrs,
    int32_t arg_count,
    char *out_buf,
    int64_t buf_size
) {
    if (!kanama_ios_resolve_godot_api() || method_bind == 0 || instance == 0) {
        return -1;
    }
    kanama_ios_cache_array_methods();
    if (g_array_size_method == NULL || g_array_get_method == NULL || g_variant_get_type == NULL) {
        return -1;
    }

    // Array opaque is 8 bytes; the generic dispatch writes it (and builds any String-family args).
    uint64_t array_storage = 0;
    kanama_ios_godot_ptrcall(
        method_bind, instance, arg_types, arg_ptrs, arg_count,
        KANAMA_IOS_PT_OBJECT /* any non-void ret tag -> ret_out is used */, &array_storage);

    int64_t count = 0;
    g_array_size_method(&array_storage, NULL, &count, 0);
    if (count < 0) {
        count = 0;
    }

    int can_write = (out_buf != NULL && buf_size >= 4);
    if (can_write) {
        int32_t count32 = (int32_t)count;
        memcpy(out_buf, &count32, 4);
    }
    int64_t total = 4;

    for (int64_t i = 0; i < count; i++) {
        uint8_t elem_variant[24];
        memset(elem_variant, 0, sizeof(elem_variant));
        const GDExtensionConstTypePtr get_args[1] = { (GDExtensionConstTypePtr)&i };
        g_array_get_method(&array_storage, get_args, elem_variant, 1);
        int32_t vtype = (int32_t)g_variant_get_type((GDExtensionConstVariantPtr)elem_variant);

        uint8_t scratch[8];
        const void *payload = NULL;
        int64_t len = 0;
        uint64_t string_storage = 0;
        int have_string = 0;
        switch (vtype) {
            case KANAMA_IOS_VARIANT_TYPE_BOOL: {
                uint8_t b = 0;
                if (g_variant_to_bool != NULL) g_variant_to_bool(&b, elem_variant);
                scratch[0] = b; payload = scratch; len = 1;
                break;
            }
            case KANAMA_IOS_VARIANT_TYPE_INT: {
                int64_t v = 0;
                if (g_variant_to_int != NULL) g_variant_to_int(&v, elem_variant);
                memcpy(scratch, &v, 8); payload = scratch; len = 8;
                break;
            }
            case KANAMA_IOS_VARIANT_TYPE_FLOAT: {
                double v = 0.0;
                if (g_variant_to_float != NULL) g_variant_to_float(&v, elem_variant);
                memcpy(scratch, &v, 8); payload = scratch; len = 8;
                break;
            }
            case KANAMA_IOS_VARIANT_TYPE_OBJECT: {
                GDExtensionObjectPtr o = NULL;
                if (g_variant_to_object != NULL) g_variant_to_object(&o, elem_variant);
                int64_t h = (int64_t)(intptr_t)o;
                memcpy(scratch, &h, 8); payload = scratch; len = 8;
                break;
            }
            case KANAMA_IOS_VARIANT_TYPE_STRING:
                if (g_variant_to_string != NULL) { g_variant_to_string(&string_storage, elem_variant); have_string = 1; }
                break;
            case KANAMA_IOS_VARIANT_TYPE_STRING_NAME:
                if (g_variant_to_string_name != NULL && g_string_from_string_name_constructor != NULL) {
                    uint64_t sn = 0;
                    g_variant_to_string_name(&sn, elem_variant);
                    const GDExtensionConstTypePtr ca[1] = { (GDExtensionConstTypePtr)&sn };
                    g_string_from_string_name_constructor((GDExtensionUninitializedTypePtr)&string_storage, ca);
                    kanama_ios_destroy_string_name(&sn);
                    have_string = 1;
                }
                break;
            case KANAMA_IOS_VARIANT_TYPE_NODE_PATH:
                if (g_variant_to_node_path != NULL && g_string_from_node_path_constructor != NULL) {
                    uint64_t np = 0;
                    g_variant_to_node_path(&np, elem_variant);
                    const GDExtensionConstTypePtr ca[1] = { (GDExtensionConstTypePtr)&np };
                    g_string_from_node_path_constructor((GDExtensionUninitializedTypePtr)&string_storage, ca);
                    kanama_ios_destroy_node_path(&np);
                    have_string = 1;
                }
                break;
            default:
                break;  // non-scalar (Dictionary/Array/...) -> len 0 -> null
        }
        if (have_string && g_string_to_utf8_chars != NULL) {
            len = (int64_t)g_string_to_utf8_chars((GDExtensionConstStringPtr)&string_storage, NULL, 0);
            if (len < 0) len = 0;
        }
        if (can_write && buf_size >= total + 8 + len) {
            int32_t vt = vtype;
            int32_t len32 = (int32_t)len;
            memcpy(out_buf + total, &vt, 4);
            memcpy(out_buf + total + 4, &len32, 4);
            if (len > 0) {
                if (have_string) {
                    g_string_to_utf8_chars((GDExtensionConstStringPtr)&string_storage, out_buf + total + 8, len);
                } else if (payload != NULL) {
                    memcpy(out_buf + total + 8, payload, (size_t)len);
                }
            }
        }
        if (have_string) {
            kanama_ios_destroy_string(&string_storage);
        }
        if (g_variant_destroy != NULL) {
            g_variant_destroy((GDExtensionVariantPtr)elem_variant);
        }
        total += 8 + len;
    }

    if (g_array_destructor != NULL) {
        g_array_destructor(&array_storage);
    }
    return total;
}

static GDExtensionMethodBindPtr kanama_ios_get_method_bind_cached(
    GDExtensionMethodBindPtr *cache,
    const char *class_name,
    const char *method_name,
    int64_t hash
) {
    if (*cache != NULL) {
        return *cache;
    }
    *cache = (GDExtensionMethodBindPtr)(intptr_t)kanama_ios_godot_get_method_bind(
        class_name,
        method_name,
        hash
    );
    return *cache;
}

static GDExtensionObjectPtr kanama_ios_global_singleton(const char *name) {
    if (!kanama_ios_resolve_godot_api()) {
        return NULL;
    }

    uint64_t singleton_name_storage = 0;
    kanama_ios_init_string_name(&singleton_name_storage, name);
    GDExtensionObjectPtr singleton = g_global_get_singleton(
        (GDExtensionConstStringNamePtr)&singleton_name_storage
    );
    kanama_ios_destroy_string_name(&singleton_name_storage);
    return singleton;
}

static GDExtensionObjectPtr kanama_ios_engine_singleton(void) {
    if (g_engine_singleton != NULL) {
        return g_engine_singleton;
    }
    g_engine_singleton = kanama_ios_global_singleton("Engine");
    return g_engine_singleton;
}

static GDExtensionObjectPtr kanama_ios_resource_loader_singleton(void) {
    if (g_resource_loader_singleton != NULL) {
        return g_resource_loader_singleton;
    }
    g_resource_loader_singleton = kanama_ios_global_singleton("ResourceLoader");
    return g_resource_loader_singleton;
}

static GDExtensionObjectPtr kanama_ios_scene_tree(void) {
    GDExtensionObjectPtr engine = kanama_ios_engine_singleton();
    GDExtensionMethodBindPtr get_main_loop = kanama_ios_get_method_bind_cached(
        &g_engine_get_main_loop_bind,
        "Engine",
        "get_main_loop",
        KANAMA_IOS_ENGINE_GET_MAIN_LOOP_HASH
    );
    if (engine == NULL || get_main_loop == NULL) {
        return NULL;
    }

    GDExtensionObjectPtr scene_tree = NULL;
    g_object_method_bind_ptrcall(get_main_loop, engine, NULL, &scene_tree);
    return scene_tree;
}

static int32_t kanama_ios_godot_ptrcall_object_arg_ret_int(
    GDExtensionMethodBindPtr method_bind,
    GDExtensionObjectPtr instance,
    GDExtensionObjectPtr object_arg
) {
    if (method_bind == NULL || instance == NULL) {
        return -1;
    }
    GDExtensionObjectPtr object_cell = object_arg;
    const GDExtensionConstTypePtr args[1] = {
        (GDExtensionConstTypePtr)&object_cell,
    };
    int32_t ret = -1;
    g_object_method_bind_ptrcall(method_bind, instance, args, &ret);
    return ret;
}

static void kanama_ios_godot_ptrcall_object_bool_arg(
    GDExtensionMethodBindPtr method_bind,
    GDExtensionObjectPtr instance,
    GDExtensionObjectPtr object_arg,
    GDExtensionBool bool_arg
) {
    if (method_bind == NULL || instance == NULL) {
        return;
    }
    GDExtensionObjectPtr object_cell = object_arg;
    GDExtensionBool bool_cell = bool_arg;
    const GDExtensionConstTypePtr args[2] = {
        (GDExtensionConstTypePtr)&object_cell,
        (GDExtensionConstTypePtr)&bool_cell,
    };
    g_object_method_bind_ptrcall(method_bind, instance, args, NULL);
}

static void kanama_ios_godot_ptrcall_object_bool_int_arg(
    GDExtensionMethodBindPtr method_bind,
    GDExtensionObjectPtr instance,
    GDExtensionObjectPtr object_arg,
    GDExtensionBool bool_arg,
    int32_t int_arg
) {
    if (method_bind == NULL || instance == NULL) {
        return;
    }
    GDExtensionObjectPtr object_cell = object_arg;
    GDExtensionBool bool_cell = bool_arg;
    int32_t int_cell = int_arg;
    const GDExtensionConstTypePtr args[3] = {
        (GDExtensionConstTypePtr)&object_cell,
        (GDExtensionConstTypePtr)&bool_cell,
        (GDExtensionConstTypePtr)&int_cell,
    };
    g_object_method_bind_ptrcall(method_bind, instance, args, NULL);
}

static void kanama_ios_godot_ptrcall_object_arg(
    GDExtensionMethodBindPtr method_bind,
    GDExtensionObjectPtr instance,
    GDExtensionObjectPtr object_arg
) {
    if (method_bind == NULL || instance == NULL) {
        return;
    }
    GDExtensionObjectPtr object_cell = object_arg;
    const GDExtensionConstTypePtr args[1] = {
        (GDExtensionConstTypePtr)&object_cell,
    };
    g_object_method_bind_ptrcall(method_bind, instance, args, NULL);
}

static GDExtensionObjectPtr kanama_ios_godot_ptrcall_int_bool_arg_ret_object(
    GDExtensionMethodBindPtr method_bind,
    GDExtensionObjectPtr instance,
    int32_t int_arg,
    GDExtensionBool bool_arg
) {
    if (method_bind == NULL || instance == NULL) {
        return NULL;
    }
    int32_t int_cell = int_arg;
    GDExtensionBool bool_cell = bool_arg;
    const GDExtensionConstTypePtr args[2] = {
        (GDExtensionConstTypePtr)&int_cell,
        (GDExtensionConstTypePtr)&bool_cell,
    };
    GDExtensionObjectPtr ret = NULL;
    g_object_method_bind_ptrcall(method_bind, instance, args, &ret);
    return ret;
}

static int32_t kanama_ios_godot_ptrcall_bool_arg_ret_int(
    GDExtensionMethodBindPtr method_bind,
    GDExtensionObjectPtr instance,
    GDExtensionBool bool_arg
) {
    if (method_bind == NULL || instance == NULL) {
        return 0;
    }
    GDExtensionBool bool_cell = bool_arg;
    const GDExtensionConstTypePtr args[1] = {
        (GDExtensionConstTypePtr)&bool_cell,
    };
    int32_t ret = 0;
    g_object_method_bind_ptrcall(method_bind, instance, args, &ret);
    return ret;
}

static void kanama_ios_godot_ptrcall_bool_arg(
    GDExtensionMethodBindPtr method_bind,
    GDExtensionObjectPtr instance,
    GDExtensionBool bool_arg
) {
    if (method_bind == NULL || instance == NULL) {
        return;
    }
    GDExtensionBool bool_cell = bool_arg;
    const GDExtensionConstTypePtr args[1] = {
        (GDExtensionConstTypePtr)&bool_cell,
    };
    g_object_method_bind_ptrcall(method_bind, instance, args, NULL);
}

// Single 32-bit float argument. Godot methods like set_volume_db(float) take a
// C++ float (4 bytes) in ptrcall, so the incoming double must be narrowed.
static void kanama_ios_godot_ptrcall_float_arg(
    GDExtensionMethodBindPtr method_bind,
    GDExtensionObjectPtr instance,
    double value
) {
    if (method_bind == NULL || instance == NULL) {
        return;
    }
    // SCALAR float methods take a `double` (8 bytes) at the ptrcall boundary:
    // Godot's PtrToArg<float> == PtrToArgConvert<float,double>. Passing a 4-byte
    // float here made Godot read 8 bytes from a 4-byte cell → garbage (this was the
    // inaudible-audio bug: set_volume_db got a garbage dB). Pass the full double.
    double value_cell = value;
    const GDExtensionConstTypePtr args[1] = {
        (GDExtensionConstTypePtr)&value_cell,
    };
    g_object_method_bind_ptrcall(method_bind, instance, args, NULL);
}

// Single StringName argument (void return). Builds the StringName, ptrcalls with
// a pointer to it, then destroys it.
static void kanama_ios_godot_ptrcall_string_name_arg(
    GDExtensionMethodBindPtr method_bind,
    GDExtensionObjectPtr instance,
    const char *value
) {
    if (method_bind == NULL || instance == NULL || value == NULL) {
        return;
    }
    uint64_t storage = 0;
    kanama_ios_init_string_name(&storage, value);
    const GDExtensionConstTypePtr args[1] = {
        (GDExtensionConstTypePtr)&storage,
    };
    g_object_method_bind_ptrcall(method_bind, instance, args, NULL);
    kanama_ios_destroy_string_name(&storage);
}

static int32_t kanama_ios_godot_ptrcall_string_name_arg_ret_bool(
    GDExtensionMethodBindPtr method_bind,
    GDExtensionObjectPtr instance,
    const char *value
) {
    if (method_bind == NULL || instance == NULL || value == NULL) {
        return 0;
    }
    uint64_t name_storage = 0;
    kanama_ios_init_string_name(&name_storage, value);
    const GDExtensionConstTypePtr args[1] = {
        (GDExtensionConstTypePtr)&name_storage,
    };
    GDExtensionBool ret = 0;
    g_object_method_bind_ptrcall(method_bind, instance, args, &ret);
    kanama_ios_destroy_string_name(&name_storage);
    return ret != 0 ? 1 : 0;
}

static GDExtensionObjectPtr kanama_ios_godot_ptrcall_noargs_ret_object(
    GDExtensionMethodBindPtr method_bind,
    GDExtensionObjectPtr instance
) {
    if (method_bind == NULL || instance == NULL) {
        return NULL;
    }
    GDExtensionObjectPtr ret = NULL;
    g_object_method_bind_ptrcall(method_bind, instance, NULL, &ret);
    return ret;
}

static int32_t kanama_ios_godot_ptrcall_noargs_ret_bool(
    GDExtensionMethodBindPtr method_bind,
    GDExtensionObjectPtr instance
) {
    if (method_bind == NULL || instance == NULL) {
        return 0;
    }
    GDExtensionBool ret = 0;
    g_object_method_bind_ptrcall(method_bind, instance, NULL, &ret);
    return ret != 0 ? 1 : 0;
}

static int64_t kanama_ios_godot_ptrcall_noargs_ret_int64(
    GDExtensionMethodBindPtr method_bind,
    GDExtensionObjectPtr instance
) {
    if (method_bind == NULL || instance == NULL) {
        return 0;
    }
    int64_t ret = 0;
    g_object_method_bind_ptrcall(method_bind, instance, NULL, &ret);
    return ret;
}

static GDExtensionObjectPtr kanama_ios_godot_ptrcall_int64_arg_ret_object(
    GDExtensionMethodBindPtr method_bind,
    GDExtensionObjectPtr instance,
    int64_t value
) {
    if (method_bind == NULL || instance == NULL) {
        return NULL;
    }
    int64_t value_cell = value;
    const GDExtensionConstTypePtr args[1] = {
        (GDExtensionConstTypePtr)&value_cell,
    };
    GDExtensionObjectPtr ret = NULL;
    g_object_method_bind_ptrcall(method_bind, instance, args, &ret);
    return ret;
}

static void kanama_ios_godot_ptrcall_noargs(
    GDExtensionMethodBindPtr method_bind,
    GDExtensionObjectPtr instance
) {
    if (method_bind == NULL || instance == NULL) {
        return;
    }
    g_object_method_bind_ptrcall(method_bind, instance, NULL, NULL);
}

static void kanama_ios_godot_ptrcall_double_arg_direct(
    GDExtensionMethodBindPtr method_bind,
    GDExtensionObjectPtr instance,
    double value
) {
    if (method_bind == NULL || instance == NULL) {
        return;
    }
    double value_cell = value;
    const GDExtensionConstTypePtr args[1] = {
        (GDExtensionConstTypePtr)&value_cell,
    };
    g_object_method_bind_ptrcall(method_bind, instance, args, NULL);
}

static GDExtensionObjectPtr kanama_ios_godot_ptrcall_node_path_arg_ret_object(
    GDExtensionMethodBindPtr method_bind,
    GDExtensionObjectPtr instance,
    const char *path
) {
    if (method_bind == NULL || instance == NULL || path == NULL) {
        return NULL;
    }
    uint64_t path_storage = 0;
    kanama_ios_init_node_path(&path_storage, path);
    if (path_storage == 0) {
        return NULL;
    }
    const GDExtensionConstTypePtr args[1] = {
        (GDExtensionConstTypePtr)&path_storage,
    };
    GDExtensionObjectPtr ret = NULL;
    g_object_method_bind_ptrcall(method_bind, instance, args, &ret);
    kanama_ios_destroy_node_path(&path_storage);
    return ret;
}

static void kanama_ios_godot_notify_postinitialize(GDExtensionObjectPtr object) {
    GDExtensionMethodBindPtr notification_bind = kanama_ios_get_method_bind_cached(
        &g_object_notification_bind,
        "Object",
        "notification",
        KANAMA_IOS_OBJECT_NOTIFICATION_HASH
    );
    if (object == NULL || notification_bind == NULL) {
        return;
    }
    int32_t notification = KANAMA_IOS_NOTIFICATION_POSTINITIALIZE;
    GDExtensionBool reversed = 0;
    const GDExtensionConstTypePtr args[2] = {
        (GDExtensionConstTypePtr)&notification,
        (GDExtensionConstTypePtr)&reversed,
    };
    g_object_method_bind_ptrcall(notification_bind, object, args, NULL);
}

int64_t kanama_ios_godot_construct_object(const char *class_name) {
    if (!kanama_ios_resolve_godot_api() || class_name == NULL) {
        return 0;
    }

    uint64_t class_name_storage = 0;
    kanama_ios_init_string_name(&class_name_storage, class_name);
    GDExtensionObjectPtr object = g_classdb_construct_object(
        (GDExtensionConstStringNamePtr)&class_name_storage
    );
    kanama_ios_destroy_string_name(&class_name_storage);
    kanama_ios_godot_notify_postinitialize(object);
    return (int64_t)(intptr_t)object;
}

// Public cinterop export: resolve a Godot engine singleton by name (Input, Engine, …)
// for the generated/bespoke wrappers (ObjectCalls.getSingleton). Mirrors desktop's
// global_get_singleton; the returned handle is borrowed (engine-owned, do not free).
int64_t kanama_ios_godot_get_singleton(const char *name) {
    if (name == NULL) {
        return 0;
    }
    return (int64_t)(intptr_t)kanama_ios_global_singleton(name);
}

void kanama_ios_godot_object_queue_free(int64_t object) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_node_queue_free_bind,
        "Node",
        "queue_free",
        KANAMA_IOS_NODE_QUEUE_FREE_HASH
    );
    if (object == 0 || method_bind == NULL) {
        return;
    }
    g_object_method_bind_ptrcall(method_bind, (GDExtensionObjectPtr)(intptr_t)object, NULL, NULL);
}

void kanama_ios_godot_node_add_child(int64_t parent, int64_t child) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_node_add_child_bind,
        "Node",
        "add_child",
        KANAMA_IOS_NODE_ADD_CHILD_HASH
    );
    kanama_ios_godot_ptrcall_object_bool_int_arg(
        method_bind,
        (GDExtensionObjectPtr)(intptr_t)parent,
        (GDExtensionObjectPtr)(intptr_t)child,
        0,
        0
    );
}

void kanama_ios_godot_node_remove_child(int64_t parent, int64_t child) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_node_remove_child_bind,
        "Node",
        "remove_child",
        KANAMA_IOS_NODE_REMOVE_CHILD_HASH
    );
    kanama_ios_godot_ptrcall_object_arg(
        method_bind,
        (GDExtensionObjectPtr)(intptr_t)parent,
        (GDExtensionObjectPtr)(intptr_t)child
    );
}

int64_t kanama_ios_godot_node_get_child_count(int64_t node) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_node_get_child_count_bind,
        "Node",
        "get_child_count",
        KANAMA_IOS_NODE_GET_CHILD_COUNT_HASH
    );
    return kanama_ios_godot_ptrcall_bool_arg_ret_int(
        method_bind,
        (GDExtensionObjectPtr)(intptr_t)node,
        0
    );
}

int64_t kanama_ios_godot_node_get_child(int64_t node, int32_t index) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_node_get_child_bind,
        "Node",
        "get_child",
        KANAMA_IOS_NODE_GET_CHILD_HASH
    );
    GDExtensionObjectPtr child = kanama_ios_godot_ptrcall_int_bool_arg_ret_object(
        method_bind,
        (GDExtensionObjectPtr)(intptr_t)node,
        index,
        0
    );
    return (int64_t)(intptr_t)child;
}

int32_t kanama_ios_godot_object_is_class(int64_t object, const char *class_name) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_object_is_class_bind,
        "Object",
        "is_class",
        KANAMA_IOS_OBJECT_STRING_NAME_BOOL_HASH
    );
    return kanama_ios_godot_ptrcall_string_name_arg_ret_bool(
        method_bind,
        (GDExtensionObjectPtr)(intptr_t)object,
        class_name
    );
}

int32_t kanama_ios_godot_node_is_in_group(int64_t node, const char *group_name) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_node_is_in_group_bind,
        "Node",
        "is_in_group",
        KANAMA_IOS_OBJECT_STRING_NAME_BOOL_HASH
    );
    return kanama_ios_godot_ptrcall_string_name_arg_ret_bool(
        method_bind,
        (GDExtensionObjectPtr)(intptr_t)node,
        group_name
    );
}

int32_t kanama_ios_godot_input_event_is_pressed(int64_t event) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_input_event_is_pressed_bind,
        "InputEvent",
        "is_pressed",
        KANAMA_IOS_NOARGS_BOOL_HASH
    );
    return kanama_ios_godot_ptrcall_noargs_ret_bool(
        method_bind,
        (GDExtensionObjectPtr)(intptr_t)event
    );
}

int32_t kanama_ios_godot_input_event_is_released(int64_t event) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_input_event_is_released_bind,
        "InputEvent",
        "is_released",
        KANAMA_IOS_NOARGS_BOOL_HASH
    );
    return kanama_ios_godot_ptrcall_noargs_ret_bool(
        method_bind,
        (GDExtensionObjectPtr)(intptr_t)event
    );
}

int64_t kanama_ios_godot_input_event_mouse_button_get_button_index(int64_t event) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_input_event_mouse_button_get_button_index_bind,
        "InputEventMouseButton",
        "get_button_index",
        KANAMA_IOS_INPUT_EVENT_MOUSE_BUTTON_GET_BUTTON_INDEX_HASH
    );
    return kanama_ios_godot_ptrcall_noargs_ret_int64(
        method_bind,
        (GDExtensionObjectPtr)(intptr_t)event
    );
}

int64_t kanama_ios_godot_node_get_node_or_null(int64_t node, const char *path) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_node_get_node_or_null_bind,
        "Node",
        "get_node_or_null",
        KANAMA_IOS_NODE_GET_NODE_OR_NULL_HASH
    );
    GDExtensionObjectPtr child = kanama_ios_godot_ptrcall_node_path_arg_ret_object(
        method_bind,
        (GDExtensionObjectPtr)(intptr_t)node,
        path
    );
    return (int64_t)(intptr_t)child;
}

int64_t kanama_ios_godot_node_get_tree(int64_t node) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_node_get_tree_bind,
        "Node",
        "get_tree",
        KANAMA_IOS_NODE_GET_TREE_HASH
    );
    GDExtensionObjectPtr tree = kanama_ios_godot_ptrcall_noargs_ret_object(
        method_bind,
        (GDExtensionObjectPtr)(intptr_t)node
    );
    return (int64_t)(intptr_t)tree;
}

int64_t kanama_ios_godot_node_get_viewport(int64_t node) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_node_get_viewport_bind,
        "Node",
        "get_viewport",
        KANAMA_IOS_NODE_GET_VIEWPORT_HASH
    );
    GDExtensionObjectPtr viewport = kanama_ios_godot_ptrcall_noargs_ret_object(
        method_bind,
        (GDExtensionObjectPtr)(intptr_t)node
    );
    return (int64_t)(intptr_t)viewport;
}

int64_t kanama_ios_godot_node_create_tween(int64_t node) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_node_create_tween_bind,
        "Node",
        "create_tween",
        KANAMA_IOS_NODE_CREATE_TWEEN_HASH
    );
    GDExtensionObjectPtr tween = kanama_ios_godot_ptrcall_noargs_ret_object(
        method_bind,
        (GDExtensionObjectPtr)(intptr_t)node
    );
    return (int64_t)(intptr_t)tween;
}

void kanama_ios_godot_node_set_process_input(int64_t node, int32_t enabled) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_node_set_process_input_bind,
        "Node",
        "set_process_input",
        KANAMA_IOS_NODE_SET_PROCESS_INPUT_HASH
    );
    kanama_ios_godot_ptrcall_bool_arg(method_bind, (GDExtensionObjectPtr)(intptr_t)node, enabled);
}

void kanama_ios_godot_node_set_process_unhandled_input(int64_t node, int32_t enabled) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_node_set_process_unhandled_input_bind,
        "Node",
        "set_process_unhandled_input",
        KANAMA_IOS_NODE_SET_PROCESS_UNHANDLED_INPUT_HASH
    );
    kanama_ios_godot_ptrcall_bool_arg(method_bind, (GDExtensionObjectPtr)(intptr_t)node, enabled);
}

static void kanama_ios_godot_ptrcall_vector2_get(
    GDExtensionMethodBindPtr *cache,
    const char *class_name,
    const char *method_name,
    int64_t hash,
    int64_t node,
    double *x,
    double *y
) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        cache,
        class_name,
        method_name,
        hash
    );
    float ret[2] = {0.0f, 0.0f};
    if (node != 0 && method_bind != NULL) {
        g_object_method_bind_ptrcall(method_bind, (GDExtensionObjectPtr)(intptr_t)node, NULL, ret);
    }
    if (x != NULL) {
        *x = ret[0];
    }
    if (y != NULL) {
        *y = ret[1];
    }
}

static void kanama_ios_godot_ptrcall_vector2_set(
    GDExtensionMethodBindPtr *cache,
    const char *class_name,
    const char *method_name,
    int64_t hash,
    int64_t node,
    double x,
    double y
) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        cache,
        class_name,
        method_name,
        hash
    );
    if (node == 0 || method_bind == NULL) {
        return;
    }
    float value[2] = {(float)x, (float)y};
    const GDExtensionConstTypePtr args[1] = {
        (GDExtensionConstTypePtr)value,
    };
    g_object_method_bind_ptrcall(method_bind, (GDExtensionObjectPtr)(intptr_t)node, args, NULL);
}

static void kanama_ios_godot_ptrcall_vector3_get(
    GDExtensionMethodBindPtr *cache,
    const char *class_name,
    const char *method_name,
    int64_t hash,
    int64_t node,
    double *x,
    double *y,
    double *z
) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        cache,
        class_name,
        method_name,
        hash
    );
    float ret[3] = {0.0f, 0.0f, 0.0f};
    if (node != 0 && method_bind != NULL) {
        g_object_method_bind_ptrcall(method_bind, (GDExtensionObjectPtr)(intptr_t)node, NULL, ret);
    }
    if (x != NULL) {
        *x = ret[0];
    }
    if (y != NULL) {
        *y = ret[1];
    }
    if (z != NULL) {
        *z = ret[2];
    }
}

static void kanama_ios_godot_ptrcall_vector3_set(
    GDExtensionMethodBindPtr *cache,
    const char *class_name,
    const char *method_name,
    int64_t hash,
    int64_t node,
    double x,
    double y,
    double z
) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        cache,
        class_name,
        method_name,
        hash
    );
    if (node == 0 || method_bind == NULL) {
        return;
    }
    float value[3] = {(float)x, (float)y, (float)z};
    const GDExtensionConstTypePtr args[1] = {
        (GDExtensionConstTypePtr)value,
    };
    g_object_method_bind_ptrcall(method_bind, (GDExtensionObjectPtr)(intptr_t)node, args, NULL);
}

static void kanama_ios_godot_ptrcall_double_arg(
    GDExtensionMethodBindPtr *cache,
    const char *class_name,
    const char *method_name,
    int64_t hash,
    int64_t node,
    double value
) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        cache,
        class_name,
        method_name,
        hash
    );
    if (node == 0 || method_bind == NULL) {
        return;
    }
    double value_cell = value;
    const GDExtensionConstTypePtr args[1] = {
        (GDExtensionConstTypePtr)&value_cell,
    };
    g_object_method_bind_ptrcall(method_bind, (GDExtensionObjectPtr)(intptr_t)node, args, NULL);
}

void kanama_ios_godot_node2d_get_position(int64_t node, double *x, double *y) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_node2d_get_position_bind,
        "Node2D",
        "get_position",
        KANAMA_IOS_NODE2D_GET_POSITION_HASH
    );
    float ret[2] = {0.0f, 0.0f};
    if (node != 0 && method_bind != NULL) {
        g_object_method_bind_ptrcall(method_bind, (GDExtensionObjectPtr)(intptr_t)node, NULL, ret);
    }
    if (x != NULL) {
        *x = ret[0];
    }
    if (y != NULL) {
        *y = ret[1];
    }
}

void kanama_ios_godot_node2d_set_position(int64_t node, double x, double y) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_node2d_set_position_bind,
        "Node2D",
        "set_position",
        KANAMA_IOS_NODE2D_SET_POSITION_HASH
    );
    if (node == 0 || method_bind == NULL) {
        return;
    }
    float value[2] = {(float)x, (float)y};
    const GDExtensionConstTypePtr args[1] = {
        (GDExtensionConstTypePtr)value,
    };
    g_object_method_bind_ptrcall(method_bind, (GDExtensionObjectPtr)(intptr_t)node, args, NULL);
}

void kanama_ios_godot_node2d_get_scale(int64_t node, double *x, double *y) {
    kanama_ios_godot_ptrcall_vector2_get(
        &g_node2d_get_scale_bind,
        "Node2D",
        "get_scale",
        KANAMA_IOS_NODE2D_GET_SCALE_HASH,
        node,
        x,
        y
    );
}

void kanama_ios_godot_node2d_set_scale(int64_t node, double x, double y) {
    kanama_ios_godot_ptrcall_vector2_set(
        &g_node2d_set_scale_bind,
        "Node2D",
        "set_scale",
        KANAMA_IOS_NODE2D_SET_SCALE_HASH,
        node,
        x,
        y
    );
}

void kanama_ios_godot_node3d_get_position(int64_t node, double *x, double *y, double *z) {
    kanama_ios_godot_ptrcall_vector3_get(
        &g_node3d_get_position_bind,
        "Node3D",
        "get_position",
        KANAMA_IOS_NODE3D_GET_POSITION_HASH,
        node,
        x,
        y,
        z
    );
}

void kanama_ios_godot_node3d_set_position(int64_t node, double x, double y, double z) {
    kanama_ios_godot_ptrcall_vector3_set(
        &g_node3d_set_position_bind,
        "Node3D",
        "set_position",
        KANAMA_IOS_NODE3D_SET_POSITION_HASH,
        node,
        x,
        y,
        z
    );
}

void kanama_ios_godot_node3d_get_rotation(int64_t node, double *x, double *y, double *z) {
    kanama_ios_godot_ptrcall_vector3_get(
        &g_node3d_get_rotation_bind,
        "Node3D",
        "get_rotation",
        KANAMA_IOS_NODE3D_GET_ROTATION_HASH,
        node,
        x,
        y,
        z
    );
}

void kanama_ios_godot_node3d_set_rotation(int64_t node, double x, double y, double z) {
    kanama_ios_godot_ptrcall_vector3_set(
        &g_node3d_set_rotation_bind,
        "Node3D",
        "set_rotation",
        KANAMA_IOS_NODE3D_SET_ROTATION_HASH,
        node,
        x,
        y,
        z
    );
}

void kanama_ios_godot_node3d_get_scale(int64_t node, double *x, double *y, double *z) {
    kanama_ios_godot_ptrcall_vector3_get(
        &g_node3d_get_scale_bind,
        "Node3D",
        "get_scale",
        KANAMA_IOS_NODE3D_GET_SCALE_HASH,
        node,
        x,
        y,
        z
    );
}

void kanama_ios_godot_node3d_set_scale(int64_t node, double x, double y, double z) {
    kanama_ios_godot_ptrcall_vector3_set(
        &g_node3d_set_scale_bind,
        "Node3D",
        "set_scale",
        KANAMA_IOS_NODE3D_SET_SCALE_HASH,
        node,
        x,
        y,
        z
    );
}

void kanama_ios_godot_node3d_get_global_position(int64_t node, double *x, double *y, double *z) {
    kanama_ios_godot_ptrcall_vector3_get(
        &g_node3d_get_global_position_bind,
        "Node3D",
        "get_global_position",
        KANAMA_IOS_NODE3D_GET_GLOBAL_POSITION_HASH,
        node,
        x,
        y,
        z
    );
}

void kanama_ios_godot_node3d_set_global_position(int64_t node, double x, double y, double z) {
    kanama_ios_godot_ptrcall_vector3_set(
        &g_node3d_set_global_position_bind,
        "Node3D",
        "set_global_position",
        KANAMA_IOS_NODE3D_SET_GLOBAL_POSITION_HASH,
        node,
        x,
        y,
        z
    );
}

void kanama_ios_godot_node3d_rotate_y(int64_t node, double angle) {
    kanama_ios_godot_ptrcall_double_arg(
        &g_node3d_rotate_y_bind,
        "Node3D",
        "rotate_y",
        KANAMA_IOS_NODE3D_ROTATE_Y_HASH,
        node,
        angle
    );
}

void kanama_ios_godot_canvas_item_get_viewport_rect(
    int64_t object,
    double *x,
    double *y,
    double *width,
    double *height
) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_canvas_item_get_viewport_rect_bind,
        "CanvasItem",
        "get_viewport_rect",
        KANAMA_IOS_CANVAS_ITEM_GET_VIEWPORT_RECT_HASH
    );
    float ret[4] = {0.0f, 0.0f, 0.0f, 0.0f};
    if (object != 0 && method_bind != NULL) {
        g_object_method_bind_ptrcall(method_bind, (GDExtensionObjectPtr)(intptr_t)object, NULL, ret);
    }
    if (x != NULL) {
        *x = ret[0];
    }
    if (y != NULL) {
        *y = ret[1];
    }
    if (width != NULL) {
        *width = ret[2];
    }
    if (height != NULL) {
        *height = ret[3];
    }
}

void kanama_ios_godot_canvas_item_get_local_mouse_position(int64_t object, double *x, double *y) {
    kanama_ios_godot_ptrcall_vector2_get(
        &g_canvas_item_get_local_mouse_position_bind,
        "CanvasItem",
        "get_local_mouse_position",
        KANAMA_IOS_CANVAS_ITEM_GET_LOCAL_MOUSE_POSITION_HASH,
        object,
        x,
        y
    );
}

void kanama_ios_godot_canvas_item_hide(int64_t object) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_canvas_item_hide_bind,
        "CanvasItem",
        "hide",
        KANAMA_IOS_CANVAS_ITEM_NOARGS_HASH
    );
    kanama_ios_godot_ptrcall_noargs(method_bind, (GDExtensionObjectPtr)(intptr_t)object);
}

void kanama_ios_godot_canvas_item_show(int64_t object) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_canvas_item_show_bind,
        "CanvasItem",
        "show",
        KANAMA_IOS_CANVAS_ITEM_NOARGS_HASH
    );
    kanama_ios_godot_ptrcall_noargs(method_bind, (GDExtensionObjectPtr)(intptr_t)object);
}

void kanama_ios_godot_canvas_item_set_modulate(
    int64_t object,
    double r,
    double g,
    double b,
    double a
) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_canvas_item_set_modulate_bind,
        "CanvasItem",
        "set_modulate",
        KANAMA_IOS_CANVAS_ITEM_SET_MODULATE_HASH
    );
    if (object == 0 || method_bind == NULL) {
        return;
    }
    float color[4] = {(float)r, (float)g, (float)b, (float)a};
    const GDExtensionConstTypePtr args[1] = {
        (GDExtensionConstTypePtr)color,
    };
    g_object_method_bind_ptrcall(method_bind, (GDExtensionObjectPtr)(intptr_t)object, args, NULL);
}

int64_t kanama_ios_godot_packed_scene_instantiate(int64_t packed_scene, int64_t edit_state) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_packed_scene_instantiate_bind,
        "PackedScene",
        "instantiate",
        KANAMA_IOS_PACKED_SCENE_INSTANTIATE_HASH
    );
    GDExtensionObjectPtr node = kanama_ios_godot_ptrcall_int64_arg_ret_object(
        method_bind,
        (GDExtensionObjectPtr)(intptr_t)packed_scene,
        edit_state
    );
    return (int64_t)(intptr_t)node;
}

void kanama_ios_godot_gpu_particles2d_set_emitting(int64_t particles, int32_t value) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_gpu_particles2d_set_emitting_bind,
        "GPUParticles2D",
        "set_emitting",
        KANAMA_IOS_BOOL_ARG_HASH
    );
    kanama_ios_godot_ptrcall_bool_arg(
        method_bind,
        (GDExtensionObjectPtr)(intptr_t)particles,
        value != 0 ? 1 : 0
    );
}

void kanama_ios_godot_gpu_particles2d_set_lifetime(int64_t particles, double value) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_gpu_particles2d_set_lifetime_bind,
        "GPUParticles2D",
        "set_lifetime",
        KANAMA_IOS_DOUBLE_ARG_HASH
    );
    kanama_ios_godot_ptrcall_double_arg_direct(
        method_bind,
        (GDExtensionObjectPtr)(intptr_t)particles,
        value
    );
}

void kanama_ios_godot_gpu_particles2d_restart(int64_t particles, int32_t keep_seed) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_gpu_particles2d_restart_bind,
        "GPUParticles2D",
        "restart",
        KANAMA_IOS_GPU_PARTICLES_RESTART_HASH
    );
    kanama_ios_godot_ptrcall_bool_arg(
        method_bind,
        (GDExtensionObjectPtr)(intptr_t)particles,
        keep_seed != 0 ? 1 : 0
    );
}

void kanama_ios_godot_gpu_particles3d_set_emitting(int64_t particles, int32_t value) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_gpu_particles3d_set_emitting_bind,
        "GPUParticles3D",
        "set_emitting",
        KANAMA_IOS_BOOL_ARG_HASH
    );
    kanama_ios_godot_ptrcall_bool_arg(
        method_bind,
        (GDExtensionObjectPtr)(intptr_t)particles,
        value != 0 ? 1 : 0
    );
}

void kanama_ios_godot_gpu_particles3d_restart(int64_t particles, int32_t keep_seed) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_gpu_particles3d_restart_bind,
        "GPUParticles3D",
        "restart",
        KANAMA_IOS_GPU_PARTICLES_RESTART_HASH
    );
    kanama_ios_godot_ptrcall_bool_arg(
        method_bind,
        (GDExtensionObjectPtr)(intptr_t)particles,
        keep_seed != 0 ? 1 : 0
    );
}

void kanama_ios_godot_collision_shape3d_set_disabled(int64_t shape, int32_t disabled) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_collision_shape3d_set_disabled_bind,
        "CollisionShape3D",
        "set_disabled",
        KANAMA_IOS_BOOL_ARG_HASH
    );
    kanama_ios_godot_ptrcall_bool_arg(
        method_bind,
        (GDExtensionObjectPtr)(intptr_t)shape,
        disabled != 0 ? 1 : 0
    );
}

int64_t kanama_ios_godot_resource_loader_load(const char *path, const char *type_hint) {
    if (!kanama_ios_resolve_godot_api() || path == NULL) {
        return 0;
    }
    GDExtensionObjectPtr resource_loader = kanama_ios_resource_loader_singleton();
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_resource_loader_load_bind,
        "ResourceLoader",
        "load",
        KANAMA_IOS_RESOURCE_LOADER_LOAD_HASH
    );
    if (resource_loader == NULL || method_bind == NULL) {
        return 0;
    }

    uint64_t path_storage = 0;
    uint64_t type_hint_storage = 0;
    int32_t cache_mode = 1;
    kanama_ios_init_string(&path_storage, path);
    kanama_ios_init_string(&type_hint_storage, type_hint != NULL ? type_hint : "");
    const GDExtensionConstTypePtr args[3] = {
        (GDExtensionConstTypePtr)&path_storage,
        (GDExtensionConstTypePtr)&type_hint_storage,
        (GDExtensionConstTypePtr)&cache_mode,
    };
    GDExtensionObjectPtr ret = NULL;
    g_object_method_bind_ptrcall(method_bind, resource_loader, args, &ret);
    kanama_ios_destroy_string(&type_hint_storage);
    kanama_ios_destroy_string(&path_storage);
    return (int64_t)(intptr_t)ret;
}

void kanama_ios_godot_sprite2d_set_texture(int64_t sprite, int64_t texture) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_sprite2d_set_texture_bind,
        "Sprite2D",
        "set_texture",
        KANAMA_IOS_SPRITE2D_SET_TEXTURE_HASH
    );
    kanama_ios_godot_ptrcall_object_arg(
        method_bind,
        (GDExtensionObjectPtr)(intptr_t)sprite,
        (GDExtensionObjectPtr)(intptr_t)texture
    );
}

void kanama_ios_godot_audio_stream_player_set_stream(int64_t player, int64_t stream) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_audio_set_stream_bind,
        "AudioStreamPlayer",
        "set_stream",
        KANAMA_IOS_AUDIO_STREAM_PLAYER_SET_STREAM_HASH
    );
    kanama_ios_godot_ptrcall_object_arg(
        method_bind,
        (GDExtensionObjectPtr)(intptr_t)player,
        (GDExtensionObjectPtr)(intptr_t)stream
    );
}

void kanama_ios_godot_audio_stream_player_set_volume_db(int64_t player, double volume_db) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_audio_set_volume_db_bind,
        "AudioStreamPlayer",
        "set_volume_db",
        KANAMA_IOS_AUDIO_STREAM_PLAYER_SET_FLOAT_HASH
    );
    kanama_ios_godot_ptrcall_float_arg(method_bind, (GDExtensionObjectPtr)(intptr_t)player, volume_db);
}

void kanama_ios_godot_audio_stream_player_set_pitch_scale(int64_t player, double pitch_scale) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_audio_set_pitch_scale_bind,
        "AudioStreamPlayer",
        "set_pitch_scale",
        KANAMA_IOS_AUDIO_STREAM_PLAYER_SET_FLOAT_HASH
    );
    kanama_ios_godot_ptrcall_float_arg(method_bind, (GDExtensionObjectPtr)(intptr_t)player, pitch_scale);
}

void kanama_ios_godot_audio_stream_player_set_bus(int64_t player, const char *bus) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_audio_set_bus_bind,
        "AudioStreamPlayer",
        "set_bus",
        KANAMA_IOS_AUDIO_STREAM_PLAYER_SET_BUS_HASH
    );
    kanama_ios_godot_ptrcall_string_name_arg(method_bind, (GDExtensionObjectPtr)(intptr_t)player, bus);
}

void kanama_ios_godot_audio_stream_player_set_stream_paused(int64_t player, int32_t paused) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_audio_set_stream_paused_bind,
        "AudioStreamPlayer",
        "set_stream_paused",
        KANAMA_IOS_AUDIO_STREAM_PLAYER_SET_STREAM_PAUSED_HASH
    );
    kanama_ios_godot_ptrcall_bool_arg(
        method_bind,
        (GDExtensionObjectPtr)(intptr_t)player,
        paused != 0 ? 1 : 0
    );
}

void kanama_ios_godot_audio_stream_player_play(int64_t player, double from_position) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_audio_play_bind,
        "AudioStreamPlayer",
        "play",
        KANAMA_IOS_AUDIO_STREAM_PLAYER_PLAY_HASH
    );
    kanama_ios_godot_ptrcall_float_arg(method_bind, (GDExtensionObjectPtr)(intptr_t)player, from_position);
}

int32_t kanama_ios_godot_object_emit_signal_int(
    int64_t object,
    const char *signal_name,
    int64_t value
) {
    if (!kanama_ios_resolve_godot_api() || object == 0 || signal_name == NULL) {
        return -1;
    }
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_object_emit_signal_bind,
        "Object",
        "emit_signal",
        KANAMA_IOS_OBJECT_EMIT_SIGNAL_HASH
    );
    if (method_bind == NULL) {
        return -1;
    }

    uint64_t signal_name_storage = 0;
    kanama_ios_init_string_name(&signal_name_storage, signal_name);
    int64_t value_cell = value;
    uint8_t signal_variant[24];
    uint8_t value_variant[24];
    uint8_t ret_variant[24];
    memset(signal_variant, 0, sizeof(signal_variant));
    memset(value_variant, 0, sizeof(value_variant));
    memset(ret_variant, 0, sizeof(ret_variant));
    g_variant_from_string_name(signal_variant, &signal_name_storage);
    g_variant_from_int(value_variant, &value_cell);
    kanama_ios_check_variant_arg("Object::emit_signal", 0, signal_variant, KANAMA_IOS_VARIANT_TYPE_STRING_NAME);
    kanama_ios_check_variant_arg("Object::emit_signal", 1, value_variant, KANAMA_IOS_VARIANT_TYPE_INT);
    const GDExtensionConstVariantPtr args[2] = {
        (GDExtensionConstVariantPtr)signal_variant,
        (GDExtensionConstVariantPtr)value_variant,
    };
    GDExtensionCallError error;
    memset(&error, 0, sizeof(error));
    g_object_method_bind_call(
        method_bind,
        (GDExtensionObjectPtr)(intptr_t)object,
        args,
        2,
        ret_variant,
        &error
    );
    kanama_ios_check_call_error("Object::emit_signal", &error);
    g_variant_destroy(ret_variant);
    g_variant_destroy(value_variant);
    g_variant_destroy(signal_variant);
    kanama_ios_destroy_string_name(&signal_name_storage);
    return error.error == GDEXTENSION_CALL_OK ? 0 : -1;
}

int32_t kanama_ios_godot_object_emit_signal_vector2i(
    int64_t object,
    const char *signal_name,
    int64_t x,
    int64_t y
) {
    if (!kanama_ios_resolve_godot_api() || object == 0 || signal_name == NULL) {
        return -1;
    }
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_object_emit_signal_bind,
        "Object",
        "emit_signal",
        KANAMA_IOS_OBJECT_EMIT_SIGNAL_HASH
    );
    if (method_bind == NULL) {
        return -1;
    }

    uint64_t signal_name_storage = 0;
    kanama_ios_init_string_name(&signal_name_storage, signal_name);
    int32_t raw_vec[2] = { (int32_t)x, (int32_t)y };
    uint8_t signal_variant[24];
    uint8_t value_variant[24];
    uint8_t ret_variant[24];
    memset(signal_variant, 0, sizeof(signal_variant));
    memset(value_variant, 0, sizeof(value_variant));
    memset(ret_variant, 0, sizeof(ret_variant));
    g_variant_from_string_name(signal_variant, &signal_name_storage);
    g_variant_from_vector2i(value_variant, raw_vec);
    kanama_ios_check_variant_arg("Object::emit_signal", 0, signal_variant, KANAMA_IOS_VARIANT_TYPE_STRING_NAME);
    kanama_ios_check_variant_arg("Object::emit_signal", 1, value_variant, KANAMA_IOS_VARIANT_TYPE_VECTOR2I);
    const GDExtensionConstVariantPtr args[2] = {
        (GDExtensionConstVariantPtr)signal_variant,
        (GDExtensionConstVariantPtr)value_variant,
    };
    GDExtensionCallError error;
    memset(&error, 0, sizeof(error));
    g_object_method_bind_call(
        method_bind,
        (GDExtensionObjectPtr)(intptr_t)object,
        args,
        2,
        ret_variant,
        &error
    );
    kanama_ios_check_call_error("Object::emit_signal", &error);
    g_variant_destroy(ret_variant);
    g_variant_destroy(value_variant);
    g_variant_destroy(signal_variant);
    kanama_ios_destroy_string_name(&signal_name_storage);
    return error.error == GDEXTENSION_CALL_OK ? 0 : -1;
}

int64_t kanama_ios_godot_object_connect(
    int64_t object,
    const char *signal_name,
    int64_t target_object,
    const char *method_name,
    int64_t flags
) {
    if (!kanama_ios_resolve_godot_api() || object == 0 || target_object == 0 ||
        signal_name == NULL || method_name == NULL) {
        return -1;
    }
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_object_connect_bind,
        "Object",
        "connect",
        KANAMA_IOS_OBJECT_CONNECT_HASH
    );
    if (method_bind == NULL) {
        return -1;
    }

    uint64_t signal_name_storage = 0;
    uint64_t method_name_storage = 0;
    kanama_ios_init_string_name(&signal_name_storage, signal_name);
    kanama_ios_init_string_name(&method_name_storage, method_name);

    GDExtensionObjectPtr target = (GDExtensionObjectPtr)(intptr_t)target_object;
    const void *callable_args[2] = { &target, &method_name_storage };
    // Build the raw Callable value, then box it into a Variant. The previous code
    // passed the raw Callable directly where a Variant was expected, which made
    // Object::connect read a garbage Variant type tag (INVALID_ARGUMENT) and then
    // crash when variant_destroy dispatched on that garbage tag.
    uint8_t callable_value[24];
    uint8_t callable_variant[24];
    memset(callable_value, 0, sizeof(callable_value));
    memset(callable_variant, 0, sizeof(callable_variant));
    g_callable_object_method_constructor(callable_value, callable_args);
    g_variant_from_callable(callable_variant, callable_value);

    int64_t flags_cell = flags;
    uint8_t signal_variant[24];
    uint8_t flags_variant[24];
    uint8_t ret_variant[24];
    memset(signal_variant, 0, sizeof(signal_variant));
    memset(flags_variant, 0, sizeof(flags_variant));
    memset(ret_variant, 0, sizeof(ret_variant));
    g_variant_from_string_name(signal_variant, &signal_name_storage);
    g_variant_from_int(flags_variant, &flags_cell);
    kanama_ios_check_variant_arg("Object::connect", 0, signal_variant, KANAMA_IOS_VARIANT_TYPE_STRING_NAME);
    kanama_ios_check_variant_arg("Object::connect", 1, callable_variant, KANAMA_IOS_VARIANT_TYPE_CALLABLE);
    kanama_ios_check_variant_arg("Object::connect", 2, flags_variant, KANAMA_IOS_VARIANT_TYPE_INT);
    const GDExtensionConstVariantPtr args[3] = {
        (GDExtensionConstVariantPtr)signal_variant,
        (GDExtensionConstVariantPtr)callable_variant,
        (GDExtensionConstVariantPtr)flags_variant,
    };
    GDExtensionCallError error;
    memset(&error, 0, sizeof(error));
    g_object_method_bind_call(
        method_bind,
        (GDExtensionObjectPtr)(intptr_t)object,
        args,
        3,
        ret_variant,
        &error
    );
    int call_ok = kanama_ios_check_call_error("Object::connect", &error);
    // Object::connect returns a Godot Error enum (0 == OK) in ret_variant. The
    // call-level GDExtensionCallError only tells us the method dispatched — a
    // failed connection (e.g. unknown signal) surfaces here, not there. Surface
    // it so silently-dropped connections are visible.
    int64_t connect_error = 0;
    if (call_ok && g_variant_to_int != NULL) {
        g_variant_to_int(&connect_error, ret_variant);
        if (connect_error != 0) {
            fprintf(stderr,
                    "[kanama][ios][c] connect FAILED: signal=%s target_method=%s returned Error=%lld "
                    "(is the signal registered? see _has_script_signal)\n",
                    signal_name, method_name, (long long)connect_error);
            fflush(stderr);
        }
    }
    g_variant_destroy(ret_variant);
    g_variant_destroy(flags_variant);
    g_variant_destroy(signal_variant);
    g_variant_destroy(callable_variant);
    g_callable_destructor(callable_value);
    kanama_ios_destroy_string_name(&method_name_storage);
    kanama_ios_destroy_string_name(&signal_name_storage);
    return (call_ok && connect_error == 0) ? 0 : -1;
}

// Box one PT-tagged arg into a Variant (out_variant[24]). For string-family tags the
// intermediate String/StringName/NodePath builtin is constructed into *out_cell and its
// PT tag recorded in *out_cell_kind so the caller can destroy it after the call (0 = none).
// Shared by the Object.call dispatch and the bound-Callable connect path.
static void kanama_ios_pt_arg_to_variant(
    int32_t tag,
    const void *p,
    uint8_t out_variant[24],
    uint64_t *out_cell,
    int *out_cell_kind
) {
    memset(out_variant, 0, 24);
    *out_cell = 0;
    *out_cell_kind = 0;
    switch (tag) {
        case KANAMA_IOS_PT_VOID:
            g_variant_new_nil((GDExtensionUninitializedVariantPtr)out_variant);
            break;
        case KANAMA_IOS_PT_BOOL: {
            uint8_t b = (p != NULL && *(const uint8_t *)p) ? 1 : 0;
            g_variant_from_bool(out_variant, &b);
            break;
        }
        case KANAMA_IOS_PT_INT32:
        case KANAMA_IOS_PT_INT64: {
            int64_t v = (p != NULL) ? *(const int64_t *)p : 0;
            g_variant_from_int(out_variant, &v);
            break;
        }
        case KANAMA_IOS_PT_FLOAT32:
        case KANAMA_IOS_PT_FLOAT64: {
            double v = (p != NULL) ? *(const double *)p : 0.0;
            g_variant_from_float(out_variant, &v);
            break;
        }
        case KANAMA_IOS_PT_STRING:
            kanama_ios_init_string(out_cell, (p != NULL) ? (const char *)p : "");
            g_variant_from_string(out_variant, out_cell);
            *out_cell_kind = KANAMA_IOS_PT_STRING;
            break;
        case KANAMA_IOS_PT_STRING_NAME:
            kanama_ios_init_string_name(out_cell, (p != NULL) ? (const char *)p : "");
            g_variant_from_string_name(out_variant, out_cell);
            *out_cell_kind = KANAMA_IOS_PT_STRING_NAME;
            break;
        case KANAMA_IOS_PT_NODE_PATH:
            kanama_ios_init_node_path(out_cell, (p != NULL) ? (const char *)p : "");
            g_variant_from_node_path(out_variant, out_cell);
            *out_cell_kind = KANAMA_IOS_PT_NODE_PATH;
            break;
        case KANAMA_IOS_PT_OBJECT: {
            // p points to an int64 handle; a 0 handle boxes as a nil Object.
            GDExtensionObjectPtr obj =
                (p != NULL) ? (GDExtensionObjectPtr)(intptr_t)(*(const int64_t *)p) : NULL;
            g_variant_from_object(out_variant, &obj);
            break;
        }
        case KANAMA_IOS_PT_VECTOR2:
            g_variant_from_vector2(out_variant, (void *)p);
            break;
        case KANAMA_IOS_PT_VECTOR2I:
            g_variant_from_vector2i(out_variant, (void *)p);
            break;
        case KANAMA_IOS_PT_VECTOR3:
            g_variant_from_vector3(out_variant, (void *)p);
            break;
        case KANAMA_IOS_PT_COLOR:
            g_variant_from_color(out_variant, (void *)p);
            break;
        default:
            fprintf(stderr,
                    "[kanama][ios][c] pt_arg_to_variant: unsupported arg tag %d -> nil\n",
                    (int)tag);
            fflush(stderr);
            g_variant_new_nil((GDExtensionUninitializedVariantPtr)out_variant);
            break;
    }
}

// Build the engine return Variant for a value-returning virtual/method from the PT-tagged return
// scratch (task 13 — non-POD virtual returns). POD/fixed-width kinds (Bool/Int/Float/Vector2/
// Vector2i/…) delegate to kanama_ios_pt_arg_to_variant, where ret_buf holds the value bytes inline.
// Variable-length kinds (STRING) can exceed the fixed ret_buf, so the Kotlin encoder instead writes
// a `char *` pointer into ret_buf; we build the Godot String from it, copy it into the return
// Variant, then destroy the temporary. Destroy-after-read: the engine owns `out_variant` and takes
// its own reference on construction, so the local String cell must be released here (same ownership
// as the inbound String arg cells).
static void kanama_ios_pt_return_to_variant(int32_t tag, const void *ret_buf, uint8_t out_variant[24]) {
    if (tag == KANAMA_IOS_PT_STRING) {
        const char *s = (ret_buf != NULL) ? *(const char *const *)ret_buf : NULL;
        uint64_t cell = 0;
        memset(out_variant, 0, 24);
        kanama_ios_init_string(&cell, (s != NULL) ? s : "");
        g_variant_from_string(out_variant, &cell);
        kanama_ios_destroy_string(&cell);
        return;
    }
    // POD return kinds carry no backing cell (cell_kind stays 0 — nothing to free).
    uint64_t cell = 0;
    int cell_kind = 0;
    kanama_ios_pt_arg_to_variant(tag, ret_buf, out_variant, &cell, &cell_kind);
    (void)cell;
    (void)cell_kind;
}

// Generic Variant Object.call dispatch. Boxes each PT-tagged arg into a Variant,
// invokes method_bind via object_method_bind_call (the Variant path, for varargs /
// dynamic dispatch the ptrcall path can't express — Object::call, set_deferred,
// set_custom_mouse_cursor, …), and decodes a SCALAR return (nil/bool/int/float/
// String/Object) back through the out params. Returns the decoded Variant type tag
// (KANAMA_IOS_VARIANT_TYPE_*), or -1 if the call did not dispatch. Marshalling is
// concentrated here and guarded by check_call_error so the boxing bug class stays
// in one place (see docs/internals/reference/ios-backend-architecture.md).
int32_t kanama_ios_godot_object_call(
    int64_t method_bind,
    int64_t instance,
    const int32_t *arg_tags,
    const void *const *arg_ptrs,
    int32_t arg_count,
    int64_t *out_int,
    double *out_double,
    char *out_str,
    int64_t out_str_size,
    int64_t *out_str_len
) {
    if (out_int != NULL) *out_int = 0;
    if (out_double != NULL) *out_double = 0.0;
    if (out_str_len != NULL) *out_str_len = 0;
    if (!kanama_ios_resolve_godot_api() || method_bind == 0 || instance == 0) {
        return -1;
    }
    if (arg_count < 0) {
        arg_count = 0;
    }
    if (arg_count > KANAMA_IOS_PTRCALL_MAX_ARGS) {
        arg_count = KANAMA_IOS_PTRCALL_MAX_ARGS;
    }

    uint8_t variants[KANAMA_IOS_PTRCALL_MAX_ARGS][24];
    // String-family builtin storage: cells[i] holds the constructed String/StringName/
    // NodePath; cell_kind[i] is its PT tag (0 = none) so the matching destructor runs.
    uint64_t cells[KANAMA_IOS_PTRCALL_MAX_ARGS];
    int cell_kind[KANAMA_IOS_PTRCALL_MAX_ARGS];
    GDExtensionConstVariantPtr args[KANAMA_IOS_PTRCALL_MAX_ARGS];

    for (int32_t i = 0; i < arg_count; i++) {
        int32_t tag = (arg_tags != NULL) ? arg_tags[i] : KANAMA_IOS_PT_VOID;
        const void *p = (arg_ptrs != NULL) ? arg_ptrs[i] : NULL;
        kanama_ios_pt_arg_to_variant(tag, p, variants[i], &cells[i], &cell_kind[i]);
        args[i] = (GDExtensionConstVariantPtr)variants[i];
    }

    uint8_t ret_variant[24];
    memset(ret_variant, 0, sizeof(ret_variant));
    GDExtensionCallError error;
    memset(&error, 0, sizeof(error));
    g_object_method_bind_call(
        (GDExtensionMethodBindPtr)(intptr_t)method_bind,
        (GDExtensionObjectPtr)(intptr_t)instance,
        (arg_count > 0) ? args : NULL,
        arg_count,
        ret_variant,
        &error
    );
    int call_ok = kanama_ios_check_call_error("Object::call", &error);

    int32_t ret_type = KANAMA_IOS_VARIANT_TYPE_NIL;
    if (call_ok) {
        ret_type = (int32_t)g_variant_get_type(ret_variant);
        switch (ret_type) {
            case KANAMA_IOS_VARIANT_TYPE_BOOL: {
                uint8_t b = 0;
                g_variant_to_bool(&b, ret_variant);
                if (out_int != NULL) *out_int = b ? 1 : 0;
                break;
            }
            case KANAMA_IOS_VARIANT_TYPE_INT: {
                int64_t v = 0;
                g_variant_to_int(&v, ret_variant);
                if (out_int != NULL) *out_int = v;
                break;
            }
            case KANAMA_IOS_VARIANT_TYPE_FLOAT: {
                double v = 0.0;
                g_variant_to_float(&v, ret_variant);
                if (out_double != NULL) *out_double = v;
                break;
            }
            case KANAMA_IOS_VARIANT_TYPE_STRING: {
                uint64_t str_storage = 0;
                g_variant_to_string(&str_storage, ret_variant);
                if (g_string_to_utf8_chars != NULL) {
                    int64_t len = (int64_t)g_string_to_utf8_chars(
                        (GDExtensionConstStringPtr)&str_storage,
                        (out_str != NULL && out_str_size > 0) ? out_str : NULL,
                        (out_str != NULL && out_str_size > 0) ? out_str_size : 0);
                    if (out_str_len != NULL) *out_str_len = len;
                }
                kanama_ios_destroy_string(&str_storage);
                break;
            }
            case KANAMA_IOS_VARIANT_TYPE_STRING_NAME: {
                // No GDExtension StringName->utf8: build String(from: StringName) then encode.
                // ret_type stays STRING_NAME (21) so Kotlin reads out_str as the decoded String.
                uint64_t sn_storage = 0;
                g_variant_to_string_name(&sn_storage, ret_variant);
                uint64_t str_storage = 0;
                const GDExtensionConstTypePtr ctor_args[1] = { (GDExtensionConstTypePtr)&sn_storage };
                g_string_from_string_name_constructor(
                    (GDExtensionUninitializedTypePtr)&str_storage, ctor_args);
                if (g_string_to_utf8_chars != NULL) {
                    int64_t len = (int64_t)g_string_to_utf8_chars(
                        (GDExtensionConstStringPtr)&str_storage,
                        (out_str != NULL && out_str_size > 0) ? out_str : NULL,
                        (out_str != NULL && out_str_size > 0) ? out_str_size : 0);
                    if (out_str_len != NULL) *out_str_len = len;
                }
                kanama_ios_destroy_string(&str_storage);
                kanama_ios_destroy_string_name(&sn_storage);
                break;
            }
            case KANAMA_IOS_VARIANT_TYPE_NODE_PATH: {
                // No GDExtension NodePath->utf8: build String(from: NodePath) then encode.
                // ret_type stays NODE_PATH (22) so Kotlin reads out_str + wraps in NodePath.
                uint64_t np_storage = 0;
                g_variant_to_node_path(&np_storage, ret_variant);
                uint64_t str_storage = 0;
                const GDExtensionConstTypePtr ctor_args[1] = { (GDExtensionConstTypePtr)&np_storage };
                g_string_from_node_path_constructor(
                    (GDExtensionUninitializedTypePtr)&str_storage, ctor_args);
                if (g_string_to_utf8_chars != NULL) {
                    int64_t len = (int64_t)g_string_to_utf8_chars(
                        (GDExtensionConstStringPtr)&str_storage,
                        (out_str != NULL && out_str_size > 0) ? out_str : NULL,
                        (out_str != NULL && out_str_size > 0) ? out_str_size : 0);
                    if (out_str_len != NULL) *out_str_len = len;
                }
                kanama_ios_destroy_string(&str_storage);
                kanama_ios_destroy_node_path(&np_storage);
                break;
            }
            case KANAMA_IOS_VARIANT_TYPE_OBJECT: {
                GDExtensionObjectPtr obj = NULL;
                g_variant_to_object(&obj, ret_variant);
                if (out_int != NULL) *out_int = (int64_t)(intptr_t)obj;
                break;
            }
            default:
                // NIL / un-decoded type: leave outs zero; Kotlin surfaces null.
                break;
        }
    }

    g_variant_destroy(ret_variant);
    for (int32_t i = 0; i < arg_count; i++) {
        g_variant_destroy(variants[i]);
        switch (cell_kind[i]) {
            case KANAMA_IOS_PT_STRING:      kanama_ios_destroy_string(&cells[i]); break;
            case KANAMA_IOS_PT_STRING_NAME: kanama_ios_destroy_string_name(&cells[i]); break;
            case KANAMA_IOS_PT_NODE_PATH:   kanama_ios_destroy_node_path(&cells[i]); break;
            default: break;
        }
    }
    return call_ok ? ret_type : -1;
}

// Object.disconnect(signal, Callable(target, method)) — the symmetric teardown of
// kanama_ios_godot_object_connect (same object+method Callable construction + boxing).
// Returns 0 on a clean dispatch, -1 otherwise.
int32_t kanama_ios_godot_object_disconnect(
    int64_t object,
    const char *signal_name,
    int64_t target_object,
    const char *method_name
) {
    if (!kanama_ios_resolve_godot_api() || object == 0 || target_object == 0 ||
        signal_name == NULL || method_name == NULL) {
        return -1;
    }
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_object_disconnect_bind,
        "Object",
        "disconnect",
        KANAMA_IOS_OBJECT_DISCONNECT_HASH
    );
    if (method_bind == NULL) {
        return -1;
    }

    uint64_t signal_name_storage = 0;
    uint64_t method_name_storage = 0;
    kanama_ios_init_string_name(&signal_name_storage, signal_name);
    kanama_ios_init_string_name(&method_name_storage, method_name);

    GDExtensionObjectPtr target = (GDExtensionObjectPtr)(intptr_t)target_object;
    const void *callable_args[2] = { &target, &method_name_storage };
    uint8_t callable_value[24];
    uint8_t callable_variant[24];
    memset(callable_value, 0, sizeof(callable_value));
    memset(callable_variant, 0, sizeof(callable_variant));
    g_callable_object_method_constructor(callable_value, callable_args);
    g_variant_from_callable(callable_variant, callable_value);

    uint8_t signal_variant[24];
    uint8_t ret_variant[24];
    memset(signal_variant, 0, sizeof(signal_variant));
    memset(ret_variant, 0, sizeof(ret_variant));
    g_variant_from_string_name(signal_variant, &signal_name_storage);
    kanama_ios_check_variant_arg("Object::disconnect", 0, signal_variant, KANAMA_IOS_VARIANT_TYPE_STRING_NAME);
    kanama_ios_check_variant_arg("Object::disconnect", 1, callable_variant, KANAMA_IOS_VARIANT_TYPE_CALLABLE);
    const GDExtensionConstVariantPtr args[2] = {
        (GDExtensionConstVariantPtr)signal_variant,
        (GDExtensionConstVariantPtr)callable_variant,
    };
    GDExtensionCallError error;
    memset(&error, 0, sizeof(error));
    g_object_method_bind_call(
        method_bind,
        (GDExtensionObjectPtr)(intptr_t)object,
        args,
        2,
        ret_variant,
        &error
    );
    int call_ok = kanama_ios_check_call_error("Object::disconnect", &error);
    g_variant_destroy(ret_variant);
    g_variant_destroy(signal_variant);
    g_variant_destroy(callable_variant);
    g_callable_destructor(callable_value);
    kanama_ios_destroy_string_name(&method_name_storage);
    kanama_ios_destroy_string_name(&signal_name_storage);
    return call_ok ? 0 : -1;
}

// Defined later in the file (resolves Array size/get/destructor); forward-declared so the
// bound-Callable helper can reuse its destructor resolution.
static void kanama_ios_cache_array_methods(void);

// Lazily resolve the bound-Callable builtin methods: Array.push_back + Callable.bindv. Must be
// called after kanama_ios_resolve_godot_api() (needs g_variant_get_ptr_builtin_method + StringName).
static void kanama_ios_cache_bound_callable_methods(void) {
    if (g_array_push_back != NULL && g_callable_bindv != NULL) {
        return;
    }
    if (g_variant_get_ptr_builtin_method == NULL) {
        return;
    }
    if (g_array_push_back == NULL) {
        uint64_t name_storage = 0;
        kanama_ios_init_string_name(&name_storage, "push_back");
        g_array_push_back = g_variant_get_ptr_builtin_method(
            KANAMA_IOS_VARIANT_TYPE_ARRAY,
            (GDExtensionConstStringNamePtr)&name_storage,
            (GDExtensionInt)KANAMA_IOS_ARRAY_PUSH_BACK_HASH
        );
        kanama_ios_destroy_string_name(&name_storage);
    }
    if (g_callable_bindv == NULL) {
        uint64_t name_storage = 0;
        kanama_ios_init_string_name(&name_storage, "bindv");
        g_callable_bindv = g_variant_get_ptr_builtin_method(
            KANAMA_IOS_VARIANT_TYPE_CALLABLE,
            (GDExtensionConstStringNamePtr)&name_storage,
            (GDExtensionInt)KANAMA_IOS_CALLABLE_BINDV_HASH
        );
        kanama_ios_destroy_string_name(&name_storage);
    }
}

// Build Callable(target, method).bindv([PT-tagged args...]) into out_bound_callable (24 bytes;
// the caller destroys it via g_callable_destructor). Returns 1 on success, 0 if the Array/bindv
// globals aren't resolved. Shared by connect_bound / disconnect_bound so the bound Callable they
// build is equal (CallableCustomBound hashes base + bound args — disconnect must match connect).
static int kanama_ios_build_bound_callable(
    int64_t target_object,
    const char *method_name,
    const int32_t *arg_tags,
    const void *const *arg_ptrs,
    int32_t arg_count,
    uint8_t out_bound_callable[24]
) {
    kanama_ios_cache_array_methods();
    kanama_ios_cache_bound_callable_methods();
    if (g_array_constructor == NULL || g_array_push_back == NULL || g_callable_bindv == NULL ||
        g_callable_object_method_constructor == NULL) {
        return 0;
    }
    if (arg_count < 0) {
        arg_count = 0;
    }
    if (arg_count > KANAMA_IOS_PTRCALL_MAX_ARGS) {
        arg_count = KANAMA_IOS_PTRCALL_MAX_ARGS;
    }

    // Callable(target, method).
    uint64_t method_name_storage = 0;
    kanama_ios_init_string_name(&method_name_storage, method_name);
    GDExtensionObjectPtr target = (GDExtensionObjectPtr)(intptr_t)target_object;
    const void *callable_args[2] = { &target, &method_name_storage };
    uint8_t base_callable[24];
    memset(base_callable, 0, sizeof(base_callable));
    g_callable_object_method_constructor(base_callable, callable_args);

    // Array of the bound args (Array opaque is 8 bytes on 64-bit, like the ret-object-array slot).
    uint64_t bound_array = 0;
    g_array_constructor((GDExtensionUninitializedTypePtr)&bound_array, NULL);
    uint8_t arg_variants[KANAMA_IOS_PTRCALL_MAX_ARGS][24];
    uint64_t arg_cells[KANAMA_IOS_PTRCALL_MAX_ARGS];
    int arg_cell_kind[KANAMA_IOS_PTRCALL_MAX_ARGS];
    for (int32_t i = 0; i < arg_count; i++) {
        int32_t tag = (arg_tags != NULL) ? arg_tags[i] : KANAMA_IOS_PT_VOID;
        const void *p = (arg_ptrs != NULL) ? arg_ptrs[i] : NULL;
        kanama_ios_pt_arg_to_variant(tag, p, arg_variants[i], &arg_cells[i], &arg_cell_kind[i]);
        const GDExtensionConstTypePtr push_args[1] = { (GDExtensionConstTypePtr)arg_variants[i] };
        g_array_push_back((GDExtensionTypePtr)&bound_array, push_args, NULL, 1);
    }

    // out_bound_callable = base_callable.bindv(bound_array).
    memset(out_bound_callable, 0, 24);
    const GDExtensionConstTypePtr bindv_args[1] = { (GDExtensionConstTypePtr)&bound_array };
    g_callable_bindv(
        (GDExtensionTypePtr)base_callable, bindv_args, (GDExtensionTypePtr)out_bound_callable, 1);

    for (int32_t i = 0; i < arg_count; i++) {
        g_variant_destroy(arg_variants[i]);
        switch (arg_cell_kind[i]) {
            case KANAMA_IOS_PT_STRING:      kanama_ios_destroy_string(&arg_cells[i]); break;
            case KANAMA_IOS_PT_STRING_NAME: kanama_ios_destroy_string_name(&arg_cells[i]); break;
            case KANAMA_IOS_PT_NODE_PATH:   kanama_ios_destroy_node_path(&arg_cells[i]); break;
            default: break;
        }
    }
    if (g_array_destructor != NULL) {
        g_array_destructor(&bound_array);
    }
    if (g_callable_destructor != NULL) {
        g_callable_destructor(base_callable);
    }
    kanama_ios_destroy_string_name(&method_name_storage);
    return 1;
}

// Object.connect(signal, Callable(target, method).bindv([boundArgs]), flags). The bound args
// arrive PT-tagged (same encoding as object_call). Returns the Godot Error (0 == OK) or -1 if the
// call didn't dispatch / the bound Callable couldn't be built.
int64_t kanama_ios_godot_object_connect_bound(
    int64_t object,
    const char *signal_name,
    int64_t target_object,
    const char *method_name,
    const int32_t *arg_tags,
    const void *const *arg_ptrs,
    int32_t arg_count,
    int64_t flags
) {
    if (!kanama_ios_resolve_godot_api() || object == 0 || target_object == 0 ||
        signal_name == NULL || method_name == NULL) {
        return -1;
    }
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_object_connect_bind, "Object", "connect", KANAMA_IOS_OBJECT_CONNECT_HASH);
    if (method_bind == NULL) {
        return -1;
    }
    uint8_t bound_callable[24];
    if (!kanama_ios_build_bound_callable(
            target_object, method_name, arg_tags, arg_ptrs, arg_count, bound_callable)) {
        return -1;
    }

    uint64_t signal_name_storage = 0;
    kanama_ios_init_string_name(&signal_name_storage, signal_name);
    uint8_t signal_variant[24];
    uint8_t callable_variant[24];
    uint8_t flags_variant[24];
    uint8_t ret_variant[24];
    memset(signal_variant, 0, 24);
    memset(callable_variant, 0, 24);
    memset(flags_variant, 0, 24);
    memset(ret_variant, 0, 24);
    g_variant_from_string_name(signal_variant, &signal_name_storage);
    g_variant_from_callable(callable_variant, bound_callable);
    int64_t flags_cell = flags;
    g_variant_from_int(flags_variant, &flags_cell);
    const GDExtensionConstVariantPtr args[3] = {
        (GDExtensionConstVariantPtr)signal_variant,
        (GDExtensionConstVariantPtr)callable_variant,
        (GDExtensionConstVariantPtr)flags_variant,
    };
    GDExtensionCallError error;
    memset(&error, 0, sizeof(error));
    g_object_method_bind_call(
        method_bind, (GDExtensionObjectPtr)(intptr_t)object, args, 3, ret_variant, &error);
    int call_ok = kanama_ios_check_call_error("Object::connect(bound)", &error);
    int64_t connect_error = 0;
    if (call_ok && g_variant_to_int != NULL) {
        g_variant_to_int(&connect_error, ret_variant);
    }

    g_variant_destroy(ret_variant);
    g_variant_destroy(flags_variant);
    g_variant_destroy(callable_variant);
    g_variant_destroy(signal_variant);
    if (g_callable_destructor != NULL) {
        g_callable_destructor(bound_callable);
    }
    kanama_ios_destroy_string_name(&signal_name_storage);
    return call_ok ? connect_error : -1;
}

// Object.disconnect(signal, Callable(target, method).bindv([boundArgs])). Rebuilds the same bound
// Callable connect_bound produced so it matches. Returns 0 on a clean dispatch, -1 otherwise.
int32_t kanama_ios_godot_object_disconnect_bound(
    int64_t object,
    const char *signal_name,
    int64_t target_object,
    const char *method_name,
    const int32_t *arg_tags,
    const void *const *arg_ptrs,
    int32_t arg_count
) {
    if (!kanama_ios_resolve_godot_api() || object == 0 || target_object == 0 ||
        signal_name == NULL || method_name == NULL) {
        return -1;
    }
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_object_disconnect_bind, "Object", "disconnect", KANAMA_IOS_OBJECT_DISCONNECT_HASH);
    if (method_bind == NULL) {
        return -1;
    }
    uint8_t bound_callable[24];
    if (!kanama_ios_build_bound_callable(
            target_object, method_name, arg_tags, arg_ptrs, arg_count, bound_callable)) {
        return -1;
    }

    uint64_t signal_name_storage = 0;
    kanama_ios_init_string_name(&signal_name_storage, signal_name);
    uint8_t signal_variant[24];
    uint8_t callable_variant[24];
    uint8_t ret_variant[24];
    memset(signal_variant, 0, 24);
    memset(callable_variant, 0, 24);
    memset(ret_variant, 0, 24);
    g_variant_from_string_name(signal_variant, &signal_name_storage);
    g_variant_from_callable(callable_variant, bound_callable);
    const GDExtensionConstVariantPtr args[2] = {
        (GDExtensionConstVariantPtr)signal_variant,
        (GDExtensionConstVariantPtr)callable_variant,
    };
    GDExtensionCallError error;
    memset(&error, 0, sizeof(error));
    g_object_method_bind_call(
        method_bind, (GDExtensionObjectPtr)(intptr_t)object, args, 2, ret_variant, &error);
    int call_ok = kanama_ios_check_call_error("Object::disconnect(bound)", &error);

    g_variant_destroy(ret_variant);
    g_variant_destroy(callable_variant);
    g_variant_destroy(signal_variant);
    if (g_callable_destructor != NULL) {
        g_callable_destructor(bound_callable);
    }
    kanama_ios_destroy_string_name(&signal_name_storage);
    return call_ok ? 0 : -1;
}

// Self-test helper: Object.is_connected(signal, Callable(target, method)) -> 1 / 0 (-1 error).
// Mirrors the connect/disconnect Callable construction; lets the disconnect self-test row be
// fail-loud (assert connected before, not-connected after) rather than a no-crash smoke check.
static int kanama_ios_object_is_connected(
    int64_t object,
    const char *signal_name,
    int64_t target_object,
    const char *method_name
) {
    if (!kanama_ios_resolve_godot_api() || object == 0 || target_object == 0 ||
        signal_name == NULL || method_name == NULL) {
        return -1;
    }
    GDExtensionMethodBindPtr method_bind =
        (GDExtensionMethodBindPtr)(intptr_t)kanama_ios_godot_get_method_bind(
            "Object", "is_connected", 768136979);
    if (method_bind == NULL) {
        return -1;
    }

    uint64_t signal_name_storage = 0;
    uint64_t method_name_storage = 0;
    kanama_ios_init_string_name(&signal_name_storage, signal_name);
    kanama_ios_init_string_name(&method_name_storage, method_name);

    GDExtensionObjectPtr target = (GDExtensionObjectPtr)(intptr_t)target_object;
    const void *callable_args[2] = { &target, &method_name_storage };
    uint8_t callable_value[24];
    uint8_t callable_variant[24];
    uint8_t signal_variant[24];
    uint8_t ret_variant[24];
    memset(callable_value, 0, sizeof(callable_value));
    memset(callable_variant, 0, sizeof(callable_variant));
    memset(signal_variant, 0, sizeof(signal_variant));
    memset(ret_variant, 0, sizeof(ret_variant));
    g_callable_object_method_constructor(callable_value, callable_args);
    g_variant_from_callable(callable_variant, callable_value);
    g_variant_from_string_name(signal_variant, &signal_name_storage);
    const GDExtensionConstVariantPtr args[2] = {
        (GDExtensionConstVariantPtr)signal_variant,
        (GDExtensionConstVariantPtr)callable_variant,
    };
    GDExtensionCallError error;
    memset(&error, 0, sizeof(error));
    g_object_method_bind_call(method_bind, (GDExtensionObjectPtr)(intptr_t)object, args, 2, ret_variant, &error);
    int call_ok = kanama_ios_check_call_error("Object::is_connected", &error);
    int result = -1;
    if (call_ok) {
        uint8_t b = 0;
        g_variant_to_bool(&b, ret_variant);
        result = b ? 1 : 0;
    }
    g_variant_destroy(ret_variant);
    g_variant_destroy(signal_variant);
    g_variant_destroy(callable_variant);
    g_callable_destructor(callable_value);
    kanama_ios_destroy_string_name(&method_name_storage);
    kanama_ios_destroy_string_name(&signal_name_storage);
    return result;
}

int64_t kanama_ios_godot_tween_tween_property_vector2(
    int64_t tween,
    int64_t target,
    const char *property,
    double x,
    double y,
    double duration
) {
    if (!kanama_ios_resolve_godot_api() || tween == 0 || target == 0 || property == NULL) {
        return 0;
    }
    GDExtensionMethodBindPtr mb = kanama_ios_get_method_bind_cached(
        &g_tween_tween_property_bind,
        "Tween",
        "tween_property",
        KANAMA_IOS_TWEEN_TWEEN_PROPERTY_HASH
    );
    if (mb == NULL) return 0;

    GDExtensionObjectPtr tween_obj = (GDExtensionObjectPtr)(intptr_t)tween;
    GDExtensionObjectPtr target_obj = (GDExtensionObjectPtr)(intptr_t)target;

    uint64_t node_path_storage[2] = { 0 };
    kanama_ios_init_node_path(node_path_storage, property);
    float vec[2] = { (float)x, (float)y };
    double dur = duration;

    uint8_t obj_v[24], prop_v[24], val_v[24], dur_v[24], ret_v[24];
    memset(obj_v, 0, 24); memset(prop_v, 0, 24); memset(val_v, 0, 24);
    memset(dur_v, 0, 24); memset(ret_v, 0, 24);

    g_variant_from_object(obj_v, &target_obj);
    g_variant_from_node_path(prop_v, node_path_storage);
    g_variant_from_vector2(val_v, vec);
    g_variant_from_float(dur_v, &dur);
    kanama_ios_check_variant_arg("Tween::tween_property", 0, obj_v, KANAMA_IOS_VARIANT_TYPE_OBJECT);
    kanama_ios_check_variant_arg("Tween::tween_property", 1, prop_v, KANAMA_IOS_VARIANT_TYPE_NODE_PATH);
    kanama_ios_check_variant_arg("Tween::tween_property", 2, val_v, KANAMA_IOS_VARIANT_TYPE_VECTOR2);
    kanama_ios_check_variant_arg("Tween::tween_property", 3, dur_v, KANAMA_IOS_VARIANT_TYPE_FLOAT);

    const GDExtensionConstVariantPtr args[4] = {
        (GDExtensionConstVariantPtr)obj_v,
        (GDExtensionConstVariantPtr)prop_v,
        (GDExtensionConstVariantPtr)val_v,
        (GDExtensionConstVariantPtr)dur_v,
    };
    GDExtensionCallError error;
    memset(&error, 0, sizeof(error));
    g_object_method_bind_call(mb, tween_obj, args, 4, ret_v, &error);
    kanama_ios_check_call_error("Tween::tween_property", &error);

    GDExtensionObjectPtr result = NULL;
    if (g_variant_to_object != NULL && g_variant_get_type != NULL) {
        GDExtensionVariantType rt = g_variant_get_type(ret_v);
        if (rt == KANAMA_IOS_VARIANT_TYPE_OBJECT) {
            g_variant_to_object(&result, ret_v);
        }
    }

    g_variant_destroy(ret_v);
    g_variant_destroy(dur_v);
    g_variant_destroy(val_v);
    g_variant_destroy(prop_v);
    g_variant_destroy(obj_v);
    kanama_ios_destroy_node_path(node_path_storage);
    return (int64_t)(intptr_t)result;
}

int64_t kanama_ios_godot_tween_tween_property_color(
    int64_t tween,
    int64_t target,
    const char *property,
    double r,
    double g,
    double b,
    double a,
    double duration
) {
    if (!kanama_ios_resolve_godot_api() || tween == 0 || target == 0 || property == NULL) {
        return 0;
    }
    GDExtensionMethodBindPtr mb = kanama_ios_get_method_bind_cached(
        &g_tween_tween_property_bind,
        "Tween",
        "tween_property",
        KANAMA_IOS_TWEEN_TWEEN_PROPERTY_HASH
    );
    if (mb == NULL) return 0;

    GDExtensionObjectPtr tween_obj = (GDExtensionObjectPtr)(intptr_t)tween;
    GDExtensionObjectPtr target_obj = (GDExtensionObjectPtr)(intptr_t)target;

    uint64_t node_path_storage[2] = { 0 };
    kanama_ios_init_node_path(node_path_storage, property);
    float color[4] = { (float)r, (float)g, (float)b, (float)a };
    double dur = duration;

    uint8_t obj_v[24], prop_v[24], val_v[24], dur_v[24], ret_v[24];
    memset(obj_v, 0, 24); memset(prop_v, 0, 24); memset(val_v, 0, 24);
    memset(dur_v, 0, 24); memset(ret_v, 0, 24);

    g_variant_from_object(obj_v, &target_obj);
    g_variant_from_node_path(prop_v, node_path_storage);
    g_variant_from_color(val_v, color);
    g_variant_from_float(dur_v, &dur);
    kanama_ios_check_variant_arg("Tween::tween_property", 0, obj_v, KANAMA_IOS_VARIANT_TYPE_OBJECT);
    kanama_ios_check_variant_arg("Tween::tween_property", 1, prop_v, KANAMA_IOS_VARIANT_TYPE_NODE_PATH);
    kanama_ios_check_variant_arg("Tween::tween_property", 2, val_v, KANAMA_IOS_VARIANT_TYPE_COLOR);
    kanama_ios_check_variant_arg("Tween::tween_property", 3, dur_v, KANAMA_IOS_VARIANT_TYPE_FLOAT);

    const GDExtensionConstVariantPtr args[4] = {
        (GDExtensionConstVariantPtr)obj_v,
        (GDExtensionConstVariantPtr)prop_v,
        (GDExtensionConstVariantPtr)val_v,
        (GDExtensionConstVariantPtr)dur_v,
    };
    GDExtensionCallError error;
    memset(&error, 0, sizeof(error));
    g_object_method_bind_call(mb, tween_obj, args, 4, ret_v, &error);
    kanama_ios_check_call_error("Tween::tween_property", &error);

    GDExtensionObjectPtr result = NULL;
    if (g_variant_to_object != NULL && g_variant_get_type != NULL) {
        GDExtensionVariantType rt = g_variant_get_type(ret_v);
        if (rt == KANAMA_IOS_VARIANT_TYPE_OBJECT) {
            g_variant_to_object(&result, ret_v);
        }
    }

    g_variant_destroy(ret_v);
    g_variant_destroy(dur_v);
    g_variant_destroy(val_v);
    g_variant_destroy(prop_v);
    g_variant_destroy(obj_v);
    kanama_ios_destroy_node_path(node_path_storage);
    return (int64_t)(intptr_t)result;
}

// Tween.tween_callback(Callable(target, method)) — builds the method-Callable (same constructor
// the connect path uses), boxes it into a Variant, and calls tween_callback via the variant path
// (Callable args can't be expressed through the audited ptrcall set). Returns the CallbackTweener
// object handle (0 on failure). The demo discards the return; it's wrapped for parity.
int64_t kanama_ios_godot_tween_tween_callback(int64_t tween, int64_t target, const char *method) {
    if (!kanama_ios_resolve_godot_api() || tween == 0 || target == 0 || method == NULL) {
        return 0;
    }
    if (g_callable_object_method_constructor == NULL || g_variant_from_callable == NULL) {
        return 0;
    }
    GDExtensionMethodBindPtr mb = kanama_ios_get_method_bind_cached(
        &g_tween_tween_callback_bind,
        "Tween",
        "tween_callback",
        KANAMA_IOS_TWEEN_TWEEN_CALLBACK_HASH
    );
    if (mb == NULL) return 0;

    GDExtensionObjectPtr tween_obj = (GDExtensionObjectPtr)(intptr_t)tween;

    // Callable(target, method).
    uint64_t method_name_storage = 0;
    kanama_ios_init_string_name(&method_name_storage, method);
    GDExtensionObjectPtr target_obj = (GDExtensionObjectPtr)(intptr_t)target;
    const void *callable_args[2] = { &target_obj, &method_name_storage };
    uint8_t callable[24];
    memset(callable, 0, sizeof(callable));
    g_callable_object_method_constructor(callable, callable_args);

    // Box the Callable into a Variant and call Tween::tween_callback(callable).
    uint8_t cb_v[24], ret_v[24];
    memset(cb_v, 0, 24);
    memset(ret_v, 0, 24);
    g_variant_from_callable(cb_v, callable);
    kanama_ios_check_variant_arg("Tween::tween_callback", 0, cb_v, KANAMA_IOS_VARIANT_TYPE_CALLABLE);

    const GDExtensionConstVariantPtr args[1] = { (GDExtensionConstVariantPtr)cb_v };
    GDExtensionCallError error;
    memset(&error, 0, sizeof(error));
    g_object_method_bind_call(mb, tween_obj, args, 1, ret_v, &error);
    kanama_ios_check_call_error("Tween::tween_callback", &error);

    GDExtensionObjectPtr result = NULL;
    if (g_variant_to_object != NULL && g_variant_get_type != NULL) {
        if (g_variant_get_type(ret_v) == KANAMA_IOS_VARIANT_TYPE_OBJECT) {
            g_variant_to_object(&result, ret_v);
        }
    }

    g_variant_destroy(ret_v);
    g_variant_destroy(cb_v);
    if (g_callable_destructor != NULL) {
        g_callable_destructor(callable);
    }
    kanama_ios_destroy_string_name(&method_name_storage);
    return (int64_t)(intptr_t)result;
}

// Tween.tween_method(Callable(target, method), from, to, duration) — animates a value from->to over
// [duration], calling target.method(value) each frame. Builds the method-Callable (same constructor as
// tween_callback), boxes the Callable + the two FLOAT endpoints + the FLOAT duration into Variants, and
// calls via the variant path (Callable args can't go through the audited ptrcall set). Returns the
// MethodTweener object handle (0 on failure).
int64_t kanama_ios_godot_tween_tween_method(
    int64_t tween,
    int64_t target,
    const char *method,
    double from,
    double to,
    double duration
) {
    if (!kanama_ios_resolve_godot_api() || tween == 0 || target == 0 || method == NULL) {
        return 0;
    }
    if (g_callable_object_method_constructor == NULL || g_variant_from_callable == NULL ||
        g_variant_from_float == NULL) {
        return 0;
    }
    GDExtensionMethodBindPtr mb = kanama_ios_get_method_bind_cached(
        &g_tween_tween_method_bind,
        "Tween",
        "tween_method",
        KANAMA_IOS_TWEEN_TWEEN_METHOD_HASH
    );
    if (mb == NULL) return 0;

    GDExtensionObjectPtr tween_obj = (GDExtensionObjectPtr)(intptr_t)tween;

    // Callable(target, method).
    uint64_t method_name_storage = 0;
    kanama_ios_init_string_name(&method_name_storage, method);
    GDExtensionObjectPtr target_obj = (GDExtensionObjectPtr)(intptr_t)target;
    const void *callable_args[2] = { &target_obj, &method_name_storage };
    uint8_t callable[24];
    memset(callable, 0, sizeof(callable));
    g_callable_object_method_constructor(callable, callable_args);

    double from_d = from, to_d = to, dur_d = duration;
    uint8_t cb_v[24], from_v[24], to_v[24], dur_v[24], ret_v[24];
    memset(cb_v, 0, 24); memset(from_v, 0, 24); memset(to_v, 0, 24);
    memset(dur_v, 0, 24); memset(ret_v, 0, 24);
    g_variant_from_callable(cb_v, callable);
    g_variant_from_float(from_v, &from_d);
    g_variant_from_float(to_v, &to_d);
    g_variant_from_float(dur_v, &dur_d);
    kanama_ios_check_variant_arg("Tween::tween_method", 0, cb_v, KANAMA_IOS_VARIANT_TYPE_CALLABLE);
    kanama_ios_check_variant_arg("Tween::tween_method", 1, from_v, KANAMA_IOS_VARIANT_TYPE_FLOAT);
    kanama_ios_check_variant_arg("Tween::tween_method", 2, to_v, KANAMA_IOS_VARIANT_TYPE_FLOAT);
    kanama_ios_check_variant_arg("Tween::tween_method", 3, dur_v, KANAMA_IOS_VARIANT_TYPE_FLOAT);

    const GDExtensionConstVariantPtr args[4] = {
        (GDExtensionConstVariantPtr)cb_v,
        (GDExtensionConstVariantPtr)from_v,
        (GDExtensionConstVariantPtr)to_v,
        (GDExtensionConstVariantPtr)dur_v,
    };
    GDExtensionCallError error;
    memset(&error, 0, sizeof(error));
    g_object_method_bind_call(mb, tween_obj, args, 4, ret_v, &error);
    kanama_ios_check_call_error("Tween::tween_method", &error);

    GDExtensionObjectPtr result = NULL;
    if (g_variant_to_object != NULL && g_variant_get_type != NULL) {
        if (g_variant_get_type(ret_v) == KANAMA_IOS_VARIANT_TYPE_OBJECT) {
            g_variant_to_object(&result, ret_v);
        }
    }

    g_variant_destroy(ret_v);
    g_variant_destroy(dur_v);
    g_variant_destroy(to_v);
    g_variant_destroy(from_v);
    g_variant_destroy(cb_v);
    if (g_callable_destructor != NULL) {
        g_callable_destructor(callable);
    }
    kanama_ios_destroy_string_name(&method_name_storage);
    return (int64_t)(intptr_t)result;
}

// PropertyTweener.from(Color) — sets the tween's starting value (Variant arg). The demo's only use is a
// Color (modulate), so this boxes a COLOR Variant and calls from() via the variant path. Returns the
// PropertyTweener handle (0 on failure).
int64_t kanama_ios_godot_property_tweener_from_color(
    int64_t tweener,
    double r,
    double g,
    double b,
    double a
) {
    if (!kanama_ios_resolve_godot_api() || tweener == 0) {
        return 0;
    }
    if (g_variant_from_color == NULL) return 0;
    GDExtensionMethodBindPtr mb = kanama_ios_get_method_bind_cached(
        &g_property_tweener_from_bind,
        "PropertyTweener",
        "from",
        KANAMA_IOS_PROPERTY_TWEENER_FROM_HASH
    );
    if (mb == NULL) return 0;

    GDExtensionObjectPtr tweener_obj = (GDExtensionObjectPtr)(intptr_t)tweener;
    float color[4] = { (float)r, (float)g, (float)b, (float)a };
    uint8_t val_v[24], ret_v[24];
    memset(val_v, 0, 24); memset(ret_v, 0, 24);
    g_variant_from_color(val_v, color);
    kanama_ios_check_variant_arg("PropertyTweener::from", 0, val_v, KANAMA_IOS_VARIANT_TYPE_COLOR);

    const GDExtensionConstVariantPtr args[1] = { (GDExtensionConstVariantPtr)val_v };
    GDExtensionCallError error;
    memset(&error, 0, sizeof(error));
    g_object_method_bind_call(mb, tweener_obj, args, 1, ret_v, &error);
    kanama_ios_check_call_error("PropertyTweener::from", &error);

    GDExtensionObjectPtr result = NULL;
    if (g_variant_to_object != NULL && g_variant_get_type != NULL) {
        if (g_variant_get_type(ret_v) == KANAMA_IOS_VARIANT_TYPE_OBJECT) {
            g_variant_to_object(&result, ret_v);
        }
    }

    g_variant_destroy(ret_v);
    g_variant_destroy(val_v);
    return (int64_t)(intptr_t)result;
}

int64_t kanama_ios_godot_tween_set_parallel(int64_t tween, int32_t parallel) {
    GDExtensionMethodBindPtr mb = kanama_ios_get_method_bind_cached(
        &g_tween_set_parallel_bind,
        "Tween",
        "set_parallel",
        KANAMA_IOS_TWEEN_SET_PARALLEL_HASH
    );
    if (mb == NULL || tween == 0) return 0;
    GDExtensionBool b = (GDExtensionBool)parallel;
    const GDExtensionConstTypePtr args[1] = { (GDExtensionConstTypePtr)&b };
    GDExtensionObjectPtr result = NULL;
    g_object_method_bind_ptrcall(mb, (GDExtensionObjectPtr)(intptr_t)tween, args, &result);
    return (int64_t)(intptr_t)result;
}

void kanama_ios_godot_tween_kill(int64_t tween) {
    GDExtensionMethodBindPtr mb = kanama_ios_get_method_bind_cached(
        &g_tween_kill_bind,
        "Tween",
        "kill",
        KANAMA_IOS_TWEEN_KILL_HASH
    );
    if (mb == NULL || tween == 0) return;
    g_object_method_bind_ptrcall(mb, (GDExtensionObjectPtr)(intptr_t)tween, NULL, NULL);
}

int64_t kanama_ios_godot_tweener_set_trans(int64_t tweener, int64_t trans) {
    GDExtensionMethodBindPtr mb = kanama_ios_get_method_bind_cached(
        &g_property_tweener_set_trans_bind,
        "PropertyTweener",
        "set_trans",
        KANAMA_IOS_PROPERTY_TWEENER_SET_TRANS_HASH
    );
    if (mb == NULL || tweener == 0) return 0;
    const GDExtensionConstTypePtr args[1] = { (GDExtensionConstTypePtr)&trans };
    GDExtensionObjectPtr result = NULL;
    g_object_method_bind_ptrcall(mb, (GDExtensionObjectPtr)(intptr_t)tweener, args, &result);
    return (int64_t)(intptr_t)result;
}

int64_t kanama_ios_godot_tweener_set_ease(int64_t tweener, int64_t ease) {
    GDExtensionMethodBindPtr mb = kanama_ios_get_method_bind_cached(
        &g_property_tweener_set_ease_bind,
        "PropertyTweener",
        "set_ease",
        KANAMA_IOS_PROPERTY_TWEENER_SET_EASE_HASH
    );
    if (mb == NULL || tweener == 0) return 0;
    const GDExtensionConstTypePtr args[1] = { (GDExtensionConstTypePtr)&ease };
    GDExtensionObjectPtr result = NULL;
    g_object_method_bind_ptrcall(mb, (GDExtensionObjectPtr)(intptr_t)tweener, args, &result);
    return (int64_t)(intptr_t)result;
}

void kanama_ios_godot_viewport_get_visible_rect(
    int64_t viewport,
    double *x,
    double *y,
    double *width,
    double *height
) {
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_viewport_get_visible_rect_bind,
        "Viewport",
        "get_visible_rect",
        KANAMA_IOS_VIEWPORT_GET_VISIBLE_RECT_HASH
    );
    float ret[4] = {0.0f, 0.0f, 0.0f, 0.0f};
    if (viewport != 0 && method_bind != NULL) {
        g_object_method_bind_ptrcall(method_bind, (GDExtensionObjectPtr)(intptr_t)viewport, NULL, ret);
    }
    if (x != NULL) *x = ret[0];
    if (y != NULL) *y = ret[1];
    if (width != NULL) *width = ret[2];
    if (height != NULL) *height = ret[3];
}

int32_t kanama_ios_godot_set_first_node_in_group_text(
    const char *group_name,
    const char *value
) {
    if (!kanama_ios_resolve_godot_api()) {
        return 0;
    }

    GDExtensionObjectPtr scene_tree = kanama_ios_scene_tree();
    GDExtensionMethodBindPtr get_first_node_in_group = kanama_ios_get_method_bind_cached(
        &g_scene_tree_get_first_node_in_group_bind,
        "SceneTree",
        "get_first_node_in_group",
        KANAMA_IOS_SCENE_TREE_GET_FIRST_NODE_IN_GROUP_HASH
    );
    GDExtensionMethodBindPtr set_text = kanama_ios_get_method_bind_cached(
        &g_label_set_text_bind,
        "Label",
        "set_text",
        KANAMA_IOS_LABEL_SET_TEXT_HASH
    );
    if (scene_tree == NULL || get_first_node_in_group == NULL || set_text == NULL) {
        return 0;
    }

    uint64_t group_name_storage = 0;
    kanama_ios_init_string_name(&group_name_storage, group_name);
    const GDExtensionConstTypePtr args[1] = {
        (GDExtensionConstTypePtr)&group_name_storage,
    };
    GDExtensionObjectPtr label = NULL;
    g_object_method_bind_ptrcall(get_first_node_in_group, scene_tree, args, &label);
    kanama_ios_destroy_string_name(&group_name_storage);
    if (label == NULL) {
        return 0;
    }

    kanama_ios_godot_ptrcall_string_arg((int64_t)(intptr_t)set_text, (int64_t)(intptr_t)label, value);
    return 1;
}

static void kanama_ios_init_empty_packed_string_array(GDExtensionTypePtr out) {
    g_packed_string_array_constructor(out, NULL);
}

static void kanama_ios_init_packed_string_array_one(GDExtensionTypePtr out, const char *value) {
    kanama_ios_init_empty_packed_string_array(out);
    if (g_packed_string_array_push_back == NULL) {
        return;
    }

    uint64_t string_storage = 0;
    kanama_ios_init_string(&string_storage, value);
    const GDExtensionConstTypePtr args[1] = {
        (GDExtensionConstTypePtr)&string_storage,
    };
    uint8_t bool_ret = 0;
    g_packed_string_array_push_back(out, args, &bool_ret, 1);
    kanama_ios_destroy_string(&string_storage);
}

static void kanama_ios_init_empty_array(GDExtensionTypePtr out) {
    g_array_constructor(out, NULL);
}

static void kanama_ios_init_empty_dictionary(GDExtensionTypePtr out) {
    g_dictionary_constructor(out, NULL);
}

static void kanama_ios_init_string_variant(GDExtensionUninitializedVariantPtr out, const char *value) {
    uint64_t string_storage = 0;
    kanama_ios_init_string(&string_storage, value != NULL ? value : "");
    g_variant_from_string(out, &string_storage);
    kanama_ios_destroy_string(&string_storage);
}

static void kanama_ios_init_int_variant(GDExtensionUninitializedVariantPtr out, int64_t value) {
    GDExtensionInt int_storage = (GDExtensionInt)value;
    g_variant_from_int(out, &int_storage);
}

static void kanama_ios_init_bool_variant(GDExtensionUninitializedVariantPtr out, int value) {
    GDExtensionBool bool_storage = value != 0 ? 1 : 0;
    g_variant_from_bool(out, &bool_storage);
}

static void kanama_ios_dictionary_set_variant(
    GDExtensionTypePtr dictionary,
    const char *key,
    GDExtensionConstVariantPtr value_variant
) {
    if (dictionary == NULL || key == NULL || value_variant == NULL || g_dictionary_keyed_setter == NULL) {
        return;
    }

    uint8_t key_variant[24];
    memset(key_variant, 0, sizeof(key_variant));
    kanama_ios_init_string_variant(key_variant, key);
    g_dictionary_keyed_setter(dictionary, key_variant, value_variant);
    g_variant_destroy(key_variant);
}

static void kanama_ios_dictionary_set_int(GDExtensionTypePtr dictionary, const char *key, int64_t value) {
    uint8_t value_variant[24];
    memset(value_variant, 0, sizeof(value_variant));
    kanama_ios_init_int_variant(value_variant, value);
    kanama_ios_dictionary_set_variant(dictionary, key, value_variant);
    g_variant_destroy(value_variant);
}

static void kanama_ios_dictionary_set_bool(GDExtensionTypePtr dictionary, const char *key, int value) {
    uint8_t value_variant[24];
    memset(value_variant, 0, sizeof(value_variant));
    kanama_ios_init_bool_variant(value_variant, value);
    kanama_ios_dictionary_set_variant(dictionary, key, value_variant);
    g_variant_destroy(value_variant);
}

static void kanama_ios_dictionary_set_dictionary(
    GDExtensionTypePtr dictionary,
    const char *key,
    GDExtensionTypePtr value_dictionary
) {
    uint8_t value_variant[24];
    memset(value_variant, 0, sizeof(value_variant));
    g_variant_from_dictionary(value_variant, value_dictionary);
    kanama_ios_dictionary_set_variant(dictionary, key, value_variant);
    g_variant_destroy(value_variant);
}

// Debug-only Dictionary read-back helpers mirroring the setters above. Used by
// kanama_ios_ptrcall_selftest to verify the rpc-config Dictionary built by
// kanama_ios_init_script_rpc_config_variant. They no-op to nil/0 if Godot did not expose the keyed
// getter (it is not part of the mandatory init gate).
static void kanama_ios_dictionary_get_variant(
    GDExtensionConstTypePtr dictionary,
    const char *key,
    GDExtensionUninitializedVariantPtr out
) {
    if (dictionary == NULL || key == NULL || out == NULL || g_dictionary_keyed_getter == NULL) {
        g_variant_new_nil(out);
        return;
    }
    uint8_t key_variant[24];
    memset(key_variant, 0, sizeof(key_variant));
    kanama_ios_init_string_variant(key_variant, key);
    g_dictionary_keyed_getter(dictionary, key_variant, out);
    g_variant_destroy(key_variant);
}

static int64_t kanama_ios_dictionary_get_int(GDExtensionConstTypePtr dictionary, const char *key) {
    uint8_t value_variant[24];
    memset(value_variant, 0, sizeof(value_variant));
    kanama_ios_dictionary_get_variant(dictionary, key, value_variant);
    int64_t result = 0;
    if (g_variant_to_int != NULL) {
        GDExtensionInt decoded = 0;
        g_variant_to_int(&decoded, value_variant);
        result = (int64_t)decoded;
    }
    g_variant_destroy(value_variant);
    return result;
}

static int kanama_ios_dictionary_get_bool(GDExtensionConstTypePtr dictionary, const char *key) {
    uint8_t value_variant[24];
    memset(value_variant, 0, sizeof(value_variant));
    kanama_ios_dictionary_get_variant(dictionary, key, value_variant);
    int result = 0;
    if (g_variant_to_bool != NULL) {
        GDExtensionBool decoded = 0;
        g_variant_to_bool(&decoded, value_variant);
        result = decoded != 0 ? 1 : 0;
    }
    g_variant_destroy(value_variant);
    return result;
}

static void kanama_ios_init_script_rpc_config_variant(
    const KanamaIosExtensionInstance *script,
    GDExtensionUninitializedVariantPtr out
) {
    if (script == NULL || script->script_rpc_config_count <= 0 || script->script_rpc_configs == NULL) {
        g_variant_new_nil(out);
        return;
    }

    uint64_t root_dictionary = 0;
    kanama_ios_init_empty_dictionary(&root_dictionary);
    for (int32_t i = 0; i < script->script_rpc_config_count; i++) {
        const KanamaIosRpcConfig *config = &script->script_rpc_configs[i];
        if (config->method_name_text == NULL || config->method_name_text[0] == '\0') {
            continue;
        }
        uint64_t method_dictionary = 0;
        kanama_ios_init_empty_dictionary(&method_dictionary);
        kanama_ios_dictionary_set_int(&method_dictionary, "rpc_mode", config->mode);
        kanama_ios_dictionary_set_bool(&method_dictionary, "call_local", config->call_local);
        kanama_ios_dictionary_set_int(&method_dictionary, "transfer_mode", config->transfer_mode);
        kanama_ios_dictionary_set_int(&method_dictionary, "channel", config->channel);
        kanama_ios_dictionary_set_dictionary(&root_dictionary, config->method_name_text, &method_dictionary);
        g_dictionary_destructor(&method_dictionary);
    }
    g_variant_from_dictionary(out, &root_dictionary);
    g_dictionary_destructor(&root_dictionary);
}

static void kanama_ios_init_nil_variant(GDExtensionUninitializedVariantPtr out) {
    g_variant_new_nil(out);
}

static void kanama_ios_init_object_variant(GDExtensionUninitializedVariantPtr out, GDExtensionObjectPtr object) {
    GDExtensionObjectPtr object_cell = object;
    g_variant_from_object(out, &object_cell);
}

static GDExtensionScriptInstancePtr kanama_ios_create_script_instance(
    KanamaIosExtensionInstance *script,
    GDExtensionObjectPtr owner_object
);

static void *kanama_ios_virtual_for_language(GDExtensionConstStringNamePtr name) {
    if (kanama_ios_string_name_eq(name, g_name__get_name)) return (void *)KANAMA_IOS_VIRTUAL_STRING_NAME;
    if (kanama_ios_string_name_eq(name, g_name__get_type)) return (void *)KANAMA_IOS_VIRTUAL_STRING_TYPE;
    if (kanama_ios_string_name_eq(name, g_name__get_extension)) return (void *)KANAMA_IOS_VIRTUAL_STRING_EXTENSION;
    if (kanama_ios_string_name_eq(name, g_name__get_recognized_extensions)) return (void *)KANAMA_IOS_VIRTUAL_PACKED_EXTENSIONS;
    if (kanama_ios_string_name_eq(name, g_name__create_script)) return (void *)KANAMA_IOS_VIRTUAL_LANGUAGE_CREATE_SCRIPT;
    if (kanama_ios_string_name_eq(name, g_name__make_template)) return (void *)KANAMA_IOS_VIRTUAL_LANGUAGE_CREATE_SCRIPT;
    if (kanama_ios_string_name_eq(name, g_name__debug_get_current_stack_info)) return (void *)KANAMA_IOS_VIRTUAL_ARRAY_EMPTY;
    return NULL;
}

static void *kanama_ios_virtual_for_script(GDExtensionConstStringNamePtr name) {
    if (kanama_ios_string_name_eq(name, g_name__is_valid)) return (void *)KANAMA_IOS_VIRTUAL_BOOL_TRUE;
    if (kanama_ios_string_name_eq(name, g_name__can_instantiate)) return (void *)KANAMA_IOS_VIRTUAL_BOOL_TRUE;
    if (kanama_ios_string_name_eq(name, g_name__get_language)) return (void *)KANAMA_IOS_VIRTUAL_SCRIPT_GET_LANGUAGE;
    if (kanama_ios_string_name_eq(name, g_name__get_instance_base_type)) {
        return (void *)KANAMA_IOS_VIRTUAL_SCRIPT_INSTANCE_BASE_TYPE;
    }
    if (kanama_ios_string_name_eq(name, g_name__instance_create)) return (void *)KANAMA_IOS_VIRTUAL_SCRIPT_INSTANCE_CREATE;
    if (kanama_ios_string_name_eq(name, g_name__placeholder_instance_create)) return (void *)KANAMA_IOS_VIRTUAL_VARIANT_NIL;
    if (kanama_ios_string_name_eq(name, g_name__has_source_code)) return (void *)KANAMA_IOS_VIRTUAL_BOOL_TRUE;
    if (kanama_ios_string_name_eq(name, g_name__get_source_code)) return (void *)KANAMA_IOS_VIRTUAL_STRING_SOURCE;
    if (kanama_ios_string_name_eq(name, g_name__set_source_code)) return (void *)KANAMA_IOS_VIRTUAL_VOID;
    if (kanama_ios_string_name_eq(name, g_name__reload)) return (void *)KANAMA_IOS_VIRTUAL_INT_ZERO;
    if (kanama_ios_string_name_eq(name, g_name__get_global_name)) return (void *)KANAMA_IOS_VIRTUAL_STRING_NAME_EMPTY;
    if (kanama_ios_string_name_eq(name, g_name__get_doc_class_name)) return (void *)KANAMA_IOS_VIRTUAL_STRING_NAME;
    if (kanama_ios_string_name_eq(name, g_name__inherits_script)) return (void *)KANAMA_IOS_VIRTUAL_BOOL_FALSE;
    if (kanama_ios_string_name_eq(name, g_name__has_method)) return (void *)KANAMA_IOS_VIRTUAL_SCRIPT_HAS_METHOD;
    if (kanama_ios_string_name_eq(name, g_name__has_static_method)) return (void *)KANAMA_IOS_VIRTUAL_BOOL_FALSE;
    if (kanama_ios_string_name_eq(name, g_name__get_script_method_argument_count)) {
        return (void *)KANAMA_IOS_VIRTUAL_SCRIPT_METHOD_ARG_COUNT;
    }
    if (kanama_ios_string_name_eq(name, g_name__get_method_info)) return (void *)KANAMA_IOS_VIRTUAL_DICTIONARY_EMPTY;
    if (kanama_ios_string_name_eq(name, g_name__is_tool)) return (void *)KANAMA_IOS_VIRTUAL_BOOL_FALSE;
    if (kanama_ios_string_name_eq(name, g_name__has_script_signal)) return (void *)KANAMA_IOS_VIRTUAL_SCRIPT_HAS_SIGNAL;
    if (kanama_ios_string_name_eq(name, g_name__has_property_default_value)) return (void *)KANAMA_IOS_VIRTUAL_BOOL_FALSE;
    if (kanama_ios_string_name_eq(name, g_name__get_property_default_value)) return (void *)KANAMA_IOS_VIRTUAL_VARIANT_NIL;
    if (kanama_ios_string_name_eq(name, g_name__update_exports)) return (void *)KANAMA_IOS_VIRTUAL_VOID;
    if (kanama_ios_string_name_eq(name, g_name__get_script_method_list)) return (void *)KANAMA_IOS_VIRTUAL_ARRAY_EMPTY;
    if (kanama_ios_string_name_eq(name, g_name__get_script_property_list)) return (void *)KANAMA_IOS_VIRTUAL_ARRAY_EMPTY;
    if (kanama_ios_string_name_eq(name, g_name__get_script_signal_list)) return (void *)KANAMA_IOS_VIRTUAL_ARRAY_EMPTY;
    if (kanama_ios_string_name_eq(name, g_name__get_constants)) return (void *)KANAMA_IOS_VIRTUAL_DICTIONARY_EMPTY;
    if (kanama_ios_string_name_eq(name, g_name__get_members)) return (void *)KANAMA_IOS_VIRTUAL_PACKED_EMPTY;
    if (kanama_ios_string_name_eq(name, g_name__get_rpc_config)) return (void *)KANAMA_IOS_VIRTUAL_SCRIPT_RPC_CONFIG;
    if (kanama_ios_string_name_eq(name, g_name__instance_has)) return (void *)KANAMA_IOS_VIRTUAL_BOOL_FALSE;
    if (kanama_ios_string_name_eq(name, g_name__is_placeholder_fallback_enabled)) return (void *)KANAMA_IOS_VIRTUAL_BOOL_TRUE;
    return NULL;
}

static void *kanama_ios_virtual_for_resource_loader(GDExtensionConstStringNamePtr name) {
    if (kanama_ios_string_name_eq(name, g_name__get_recognized_extensions)) return (void *)KANAMA_IOS_VIRTUAL_PACKED_EXTENSIONS;
    if (kanama_ios_string_name_eq(name, g_name__handles_type)) return (void *)KANAMA_IOS_VIRTUAL_BOOL_TRUE;
    if (kanama_ios_string_name_eq(name, g_name__get_resource_type)) return (void *)KANAMA_IOS_VIRTUAL_RESOURCE_GET_TYPE;
    if (kanama_ios_string_name_eq(name, g_name__load)) return (void *)KANAMA_IOS_VIRTUAL_RESOURCE_LOAD;
    return NULL;
}

static void *kanama_ios_get_virtual_call_data(
    void *class_userdata,
    GDExtensionConstStringNamePtr name,
    uint32_t hash
) {
    (void)hash;
    KanamaIosClassKind kind = (KanamaIosClassKind)(intptr_t)class_userdata;
    switch (kind) {
        case KANAMA_IOS_CLASS_SCRIPT_LANGUAGE:
            return kanama_ios_virtual_for_language(name);
        case KANAMA_IOS_CLASS_SCRIPT:
            return kanama_ios_virtual_for_script(name);
        case KANAMA_IOS_CLASS_RESOURCE_LOADER:
            return kanama_ios_virtual_for_resource_loader(name);
        default:
            return NULL;
    }
}

static GDExtensionObjectPtr kanama_ios_construct_extension_object(KanamaIosClassKind kind) {
    uint64_t *class_name = NULL;
    switch (kind) {
        case KANAMA_IOS_CLASS_SCRIPT_LANGUAGE:
            class_name = &g_name_KanamaIosScriptLanguage;
            break;
        case KANAMA_IOS_CLASS_SCRIPT:
            class_name = &g_name_KanamaIosScript;
            break;
        case KANAMA_IOS_CLASS_RESOURCE_LOADER:
            class_name = &g_name_KanamaIosResourceFormatLoader;
            break;
        default:
            return NULL;
    }
    return g_classdb_construct_object((GDExtensionConstStringNamePtr)class_name);
}

static void kanama_ios_call_virtual_with_data(
    GDExtensionClassInstancePtr instance,
    GDExtensionConstStringNamePtr name,
    void *virtual_call_userdata,
    const GDExtensionConstTypePtr *args,
    GDExtensionTypePtr ret
) {
    KanamaIosVirtualId id = (KanamaIosVirtualId)(intptr_t)virtual_call_userdata;
    KanamaIosExtensionInstance *extension_instance = (KanamaIosExtensionInstance *)instance;

    switch (id) {
        case KANAMA_IOS_VIRTUAL_STRING_NAME_EMPTY:
            kanama_ios_init_string_name((uint64_t *)ret, "");
            break;
        case KANAMA_IOS_VIRTUAL_STRING_NAME_LABEL:
            kanama_ios_init_string_name((uint64_t *)ret, "Label");
            break;
        case KANAMA_IOS_VIRTUAL_STRING_EMPTY:
            kanama_ios_init_string((uint64_t *)ret, "");
            break;
        case KANAMA_IOS_VIRTUAL_STRING_NAME:
            kanama_ios_init_string((uint64_t *)ret, "Kanama");
            break;
        case KANAMA_IOS_VIRTUAL_STRING_TYPE:
            kanama_ios_init_string((uint64_t *)ret, "Kanama");
            break;
        case KANAMA_IOS_VIRTUAL_STRING_EXTENSION:
            kanama_ios_init_string((uint64_t *)ret, "kt");
            break;
        case KANAMA_IOS_VIRTUAL_STRING_SOURCE:
            kanama_ios_init_string((uint64_t *)ret, "// Kanama iOS Kotlin/Native probe");
            break;
        case KANAMA_IOS_VIRTUAL_PACKED_EXTENSIONS:
            kanama_ios_init_packed_string_array_one(ret, "kt");
            break;
        case KANAMA_IOS_VIRTUAL_PACKED_EMPTY:
            kanama_ios_init_empty_packed_string_array(ret);
            break;
        case KANAMA_IOS_VIRTUAL_ARRAY_EMPTY:
            kanama_ios_init_empty_array(ret);
            break;
        case KANAMA_IOS_VIRTUAL_DICTIONARY_EMPTY:
            kanama_ios_init_empty_dictionary(ret);
            break;
        case KANAMA_IOS_VIRTUAL_VARIANT_NIL:
            kanama_ios_init_nil_variant((GDExtensionUninitializedVariantPtr)ret);
            break;
        case KANAMA_IOS_VIRTUAL_BOOL_TRUE:
            *((uint8_t *)ret) = 1;
            break;
        case KANAMA_IOS_VIRTUAL_BOOL_FALSE:
            *((uint8_t *)ret) = 0;
            break;
        case KANAMA_IOS_VIRTUAL_INT_ZERO:
            *((int32_t *)ret) = 0;
            break;
        case KANAMA_IOS_VIRTUAL_INT_NEGATIVE_ONE:
            *((int32_t *)ret) = -1;
            break;
        case KANAMA_IOS_VIRTUAL_VOID:
            break;
        case KANAMA_IOS_VIRTUAL_LANGUAGE_CREATE_SCRIPT: {
            GDExtensionObjectPtr script_object = kanama_ios_construct_extension_object(KANAMA_IOS_CLASS_SCRIPT);
            *((GDExtensionObjectPtr *)ret) = script_object;
            fprintf(stderr, "[kanama][ios][c] ScriptLanguage created probe script object=%p\n", script_object);
            break;
        }
        case KANAMA_IOS_VIRTUAL_SCRIPT_GET_LANGUAGE:
            *((GDExtensionObjectPtr *)ret) = g_script_language_object;
            break;
        case KANAMA_IOS_VIRTUAL_SCRIPT_INSTANCE_BASE_TYPE:
            kanama_ios_init_string_name(
                (uint64_t *)ret,
                extension_instance != NULL && extension_instance->script_base_type_text != NULL
                    ? extension_instance->script_base_type_text
                    : "Node"
            );
            break;
        case KANAMA_IOS_VIRTUAL_SCRIPT_INSTANCE_CREATE: {
            GDExtensionObjectPtr owner_object = NULL;
            if (args != NULL && args[0] != NULL) {
                owner_object = *((GDExtensionObjectPtr const *)args[0]);
            }
            *((GDExtensionScriptInstancePtr *)ret) =
                kanama_ios_create_script_instance(extension_instance, owner_object);
            break;
        }
        case KANAMA_IOS_VIRTUAL_SCRIPT_HAS_METHOD: {
            uint8_t has_method = 0;
            if (args != NULL && args[0] != NULL) {
                // args[0] is already a pointer to the StringName argument; do not
                // dereference again (see _has_script_signal — the extra deref read
                // into the StringName's _Data struct and compared garbage).
                GDExtensionConstStringNamePtr method_name = (GDExtensionConstStringNamePtr)args[0];
                has_method = kanama_ios_script_method_index(extension_instance, method_name) >= 0 ? 1 : 0;
            }
            *((uint8_t *)ret) = has_method;
            break;
        }
        case KANAMA_IOS_VIRTUAL_SCRIPT_HAS_SIGNAL: {
            // Report script-declared @Signal names so Object::connect accepts them
            // and registers the connection. Without this, connect() fails with
            // ERR_INVALID_PARAMETER and emitted signals reach no handlers.
            uint8_t has_signal = 0;
            if (args != NULL && args[0] != NULL) {
                // args[0] is already a pointer to the StringName argument; do not
                // dereference it again (that would read into the StringName's
                // internal _Data struct and compare garbage).
                GDExtensionConstStringNamePtr signal_name = (GDExtensionConstStringNamePtr)args[0];
                has_signal = kanama_ios_script_signal_index(extension_instance, signal_name) >= 0 ? 1 : 0;
            }
            *((uint8_t *)ret) = has_signal;
            break;
        }
        case KANAMA_IOS_VIRTUAL_SCRIPT_METHOD_ARG_COUNT:
            kanama_ios_init_nil_variant((GDExtensionUninitializedVariantPtr)ret);
            break;
        case KANAMA_IOS_VIRTUAL_SCRIPT_RPC_CONFIG:
            kanama_ios_init_script_rpc_config_variant(
                extension_instance,
                (GDExtensionUninitializedVariantPtr)ret
            );
            break;
        case KANAMA_IOS_VIRTUAL_RESOURCE_GET_TYPE:
            kanama_ios_init_string((uint64_t *)ret, "Script");
            break;
        case KANAMA_IOS_VIRTUAL_RESOURCE_LOAD: {
            char *path = NULL;
            if (args != NULL && args[0] != NULL) {
                path = kanama_ios_string_to_utf8_dup((GDExtensionConstStringPtr)args[0]);
            }
            g_pending_script_resource_path = path != NULL ? path : "res://kanama_ios_probe.kt";
            GDExtensionObjectPtr script_object = kanama_ios_construct_extension_object(KANAMA_IOS_CLASS_SCRIPT);
            g_pending_script_resource_path = NULL;
            free(path);
            kanama_ios_init_object_variant((GDExtensionUninitializedVariantPtr)ret, script_object);
            if (g_kanama_log_lifecycle) {
                fprintf(stderr, "[kanama][ios][c] ResourceFormatLoader loaded probe script object=%p\n", script_object);
            }
            break;
        }
        default:
            break;
    }
}

static GDExtensionObjectPtr kanama_ios_extension_create_instance(
    void *class_userdata,
    GDExtensionBool notify_postinitialize
) {
    KanamaIosClassKind kind = (KanamaIosClassKind)(intptr_t)class_userdata;
    uint64_t *class_name = NULL;
    uint64_t *parent_name = NULL;

    switch (kind) {
        case KANAMA_IOS_CLASS_SCRIPT_LANGUAGE:
            class_name = &g_name_KanamaIosScriptLanguage;
            parent_name = &g_name_ScriptLanguageExtension;
            break;
        case KANAMA_IOS_CLASS_SCRIPT:
            class_name = &g_name_KanamaIosScript;
            parent_name = &g_name_ScriptExtension;
            break;
        case KANAMA_IOS_CLASS_RESOURCE_LOADER:
            class_name = &g_name_KanamaIosResourceFormatLoader;
            parent_name = &g_name_ResourceFormatLoader;
            break;
        default:
            return NULL;
    }

    KanamaIosExtensionInstance *instance = calloc(1, sizeof(KanamaIosExtensionInstance));
    if (instance == NULL) {
        return NULL;
    }
    instance->kind = kind;
    if (kind == KANAMA_IOS_CLASS_SCRIPT) {
        const char *path = g_pending_script_resource_path != NULL
            ? g_pending_script_resource_path
            : "res://kanama_ios_probe.kt";
        instance->script_path = kanama_ios_strdup(path);
        instance->script_handle = kanama_ios_runtime_script_resource_create(path);
        kanama_ios_script_resource_init_metadata(instance);
    }

    GDExtensionObjectPtr object = g_classdb_construct_object((GDExtensionConstStringNamePtr)parent_name);
    if (object == NULL) {
        if (instance->kind == KANAMA_IOS_CLASS_SCRIPT && instance->script_handle != 0) {
            kanama_ios_runtime_script_resource_free(instance->script_handle);
        }
        kanama_ios_script_resource_clear_metadata(instance);
        free(instance);
        return NULL;
    }

    instance->godot_object = object;
    g_object_set_instance(object, (GDExtensionConstStringNamePtr)class_name, instance);
    if (notify_postinitialize) {
        kanama_ios_godot_notify_postinitialize(object);
    }
    return object;
}

static void kanama_ios_extension_free_instance(
    void *class_userdata,
    GDExtensionClassInstancePtr p_instance
) {
    (void)class_userdata;
    KanamaIosExtensionInstance *instance = (KanamaIosExtensionInstance *)p_instance;
    if (instance == NULL) {
        return;
    }
    if (instance->kind == KANAMA_IOS_CLASS_SCRIPT && instance->script_handle != 0) {
        kanama_ios_runtime_script_resource_free(instance->script_handle);
    }
    kanama_ios_script_resource_clear_metadata(instance);
    free(instance);
}

static void kanama_ios_script_instance_set_ok(GDExtensionCallError *error, int ok) {
    if (error != NULL) {
        ((int32_t *)error)[0] = ok ? 0 : 1;
    }
}

static KanamaIosScriptInstance *kanama_ios_script_instance_data(GDExtensionScriptInstanceDataPtr data) {
    return (KanamaIosScriptInstance *)data;
}

static GDExtensionBool kanama_ios_script_instance_false_1(GDExtensionScriptInstanceDataPtr data) {
    (void)data;
    return 0;
}

static GDExtensionBool kanama_ios_script_instance_false_2(
    GDExtensionScriptInstanceDataPtr data,
    GDExtensionConstStringNamePtr name
) {
    (void)data;
    (void)name;
    return 0;
}

static GDExtensionBool kanama_ios_script_instance_false_3(
    GDExtensionScriptInstanceDataPtr data,
    GDExtensionConstStringNamePtr name,
    GDExtensionConstVariantPtr value
) {
    (void)data;
    (void)name;
    (void)value;
    return 0;
}

// Empty String / StringName for property-info fields: a Godot String/StringName is a single
// pointer whose null (zero) value IS the canonical empty value, so a shared zero qword serves
// for every entry's class_name + hint_string without per-entry allocation.
static const uint64_t g_kanama_ios_empty_string_storage = 0;

static const GDExtensionPropertyInfo *kanama_ios_script_instance_get_property_list(
    GDExtensionScriptInstanceDataPtr data,
    uint32_t *count
) {
    KanamaIosScriptInstance *instance = kanama_ios_script_instance_data(data);
    if (instance == NULL || instance->script == NULL
        || instance->script->script_property_count <= 0
        || instance->script->script_property_names == NULL
        || instance->script->script_property_types == NULL) {
        if (count != NULL) {
            *count = 0;
        }
        return NULL;
    }
    int32_t n = instance->script->script_property_count;
    GDExtensionPropertyInfo *list = calloc((size_t)n, sizeof(GDExtensionPropertyInfo));
    if (list == NULL) {
        if (count != NULL) {
            *count = 0;
        }
        return NULL;
    }
    for (int32_t i = 0; i < n; i++) {
        list[i].type = (GDExtensionVariantType)instance->script->script_property_types[i];
        list[i].name = (GDExtensionStringNamePtr)&instance->script->script_property_names[i];
        list[i].class_name = (GDExtensionStringNamePtr)&g_kanama_ios_empty_string_storage;
        list[i].hint = 0;
        list[i].hint_string = (GDExtensionStringPtr)&g_kanama_ios_empty_string_storage;
        // PROPERTY_USAGE_STORAGE | PROPERTY_USAGE_EDITOR — so scene-stored @ScriptProperty
        // values are applied to the instance (and the inspector shows them).
        list[i].usage = 6;
    }
    if (count != NULL) {
        *count = (uint32_t)n;
    }
    return list;
}

static void kanama_ios_script_instance_free_property_list(
    GDExtensionScriptInstanceDataPtr data,
    const GDExtensionPropertyInfo *list,
    uint32_t count
) {
    (void)data;
    (void)count;
    // The per-entry name StringNames are owned by the instance; class_name/hint_string are the
    // shared empty storage. Only the array itself was allocated here.
    free((void *)list);
}

static GDExtensionVariantType kanama_ios_script_instance_get_property_type(
    GDExtensionScriptInstanceDataPtr data,
    GDExtensionConstStringNamePtr name,
    GDExtensionBool *is_valid
) {
    (void)data;
    (void)name;
    if (is_valid != NULL) {
        *is_valid = 0;
    }
    return KANAMA_IOS_VARIANT_TYPE_NIL;
}

static GDExtensionObjectPtr kanama_ios_script_instance_get_owner(GDExtensionScriptInstanceDataPtr data) {
    KanamaIosScriptInstance *instance = kanama_ios_script_instance_data(data);
    return instance != NULL ? instance->owner_object : NULL;
}

static void kanama_ios_script_instance_get_property_state(
    GDExtensionScriptInstanceDataPtr data,
    GDExtensionScriptInstancePropertyStateAdd add_func,
    void *userdata
) {
    (void)data;
    (void)add_func;
    (void)userdata;
}

static const GDExtensionMethodInfo *kanama_ios_script_instance_get_method_list(
    GDExtensionScriptInstanceDataPtr data,
    uint32_t *count
) {
    (void)data;
    if (count != NULL) {
        *count = 0;
    }
    return NULL;
}

static void kanama_ios_script_instance_free_method_list(
    GDExtensionScriptInstanceDataPtr data,
    const GDExtensionMethodInfo *list,
    uint32_t count
) {
    (void)data;
    (void)list;
    (void)count;
}

static GDExtensionBool kanama_ios_script_instance_has_method(
    GDExtensionScriptInstanceDataPtr data,
    GDExtensionConstStringNamePtr name
) {
    KanamaIosScriptInstance *instance = kanama_ios_script_instance_data(data);
    int32_t method_index = kanama_ios_script_method_index(instance != NULL ? instance->script : NULL, name);
    GDExtensionBool result = method_index >= 0 ? 1 : 0;
    return result;
}

static GDExtensionInt kanama_ios_script_instance_get_method_argument_count(
    GDExtensionScriptInstanceDataPtr data,
    GDExtensionConstStringNamePtr name,
    GDExtensionBool *is_valid
) {
    KanamaIosScriptInstance *instance = kanama_ios_script_instance_data(data);
    int32_t method_index = kanama_ios_script_method_index(instance != NULL ? instance->script : NULL, name);
    GDExtensionBool valid = method_index >= 0 ? 1 : 0;
    if (is_valid != NULL) {
        *is_valid = valid;
    }
    return valid ? kanama_ios_script_method_arg_count(instance->script, method_index) : 0;
}

static double kanama_ios_variant_to_double(GDExtensionConstVariantPtr variant) {
    if (variant == NULL || g_variant_to_float == NULL) {
        return 0.0;
    }
    GDExtensionVariantType type = g_variant_get_type != NULL ? g_variant_get_type(variant) : KANAMA_IOS_VARIANT_TYPE_NIL;
    if (type != KANAMA_IOS_VARIANT_TYPE_FLOAT && type != KANAMA_IOS_VARIANT_TYPE_INT) {
        return 0.0;
    }
    double value = 0.0;
    g_variant_to_float(&value, (GDExtensionVariantPtr)variant);
    return value;
}

static GDExtensionObjectPtr kanama_ios_variant_to_object(GDExtensionConstVariantPtr variant) {
    if (variant == NULL || g_variant_to_object == NULL) {
        return NULL;
    }
    GDExtensionVariantType type = g_variant_get_type != NULL ? g_variant_get_type(variant) : KANAMA_IOS_VARIANT_TYPE_NIL;
    if (type != KANAMA_IOS_VARIANT_TYPE_OBJECT) {
        return NULL;
    }
    GDExtensionObjectPtr object = NULL;
    g_variant_to_object(&object, (GDExtensionVariantPtr)variant);
    return object;
}

// Custom-Callable trampoline: invoked by Godot when a signal connected via
// kanama_ios_godot_object_connect_callable fires. Forwards to the Kotlin
// callback registry keyed by callable_userdata (the callback id). Object-typed
// signal arguments are passed through as handles (up to 4); other types arrive
// as 0 and surface as null on the Kotlin side.
static void kanama_ios_callable_trampoline(
    void *callable_userdata,
    const GDExtensionConstVariantPtr *p_args,
    GDExtensionInt p_argument_count,
    GDExtensionVariantPtr r_return,
    GDExtensionCallError *r_error
) {
    int64_t callback_id = (int64_t)(intptr_t)callable_userdata;
    int64_t handles[4] = { 0, 0, 0, 0 };
    int n = (int)p_argument_count;
    if (n > 4) {
        n = 4;
    }
    for (int i = 0; i < n; i++) {
        handles[i] = (int64_t)(intptr_t)kanama_ios_variant_to_object(p_args[i]);
    }
    kanama_ios_runtime_dispatch_callable(
        callback_id, (int32_t)p_argument_count,
        handles[0], handles[1], handles[2], handles[3]);
    if (r_return != NULL) {
        kanama_ios_init_nil_variant((GDExtensionUninitializedVariantPtr)r_return);
    }
    if (r_error != NULL) {
        r_error->error = GDEXTENSION_CALL_OK;
    }
}

// Called by Godot when the last copy of the custom callable is destroyed (e.g.
// the connection is removed or the emitting object is freed). Releases the
// Kotlin-side callback so it can be garbage collected.
static void kanama_ios_callable_free(void *callable_userdata) {
    kanama_ios_runtime_release_callable((int64_t)(intptr_t)callable_userdata);
}

int64_t kanama_ios_godot_object_connect_callable(
    int64_t object,
    const char *signal_name,
    int64_t callback_id,
    int64_t flags
) {
    if (!kanama_ios_resolve_godot_api() || object == 0 || signal_name == NULL ||
        g_callable_custom_create2 == NULL) {
        return -1;
    }
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_object_connect_bind, "Object", "connect", KANAMA_IOS_OBJECT_CONNECT_HASH);
    if (method_bind == NULL) {
        return -1;
    }

    GDExtensionCallableCustomInfo2 info;
    memset(&info, 0, sizeof(info));
    info.callable_userdata = (void *)(intptr_t)callback_id;
    info.token = g_library;
    info.object_id = 0;
    info.call_func = kanama_ios_callable_trampoline;
    info.free_func = kanama_ios_callable_free;
    uint8_t callable_value[24];
    memset(callable_value, 0, sizeof(callable_value));
    g_callable_custom_create2((GDExtensionUninitializedTypePtr)callable_value, &info);

    uint8_t callable_variant[24];
    memset(callable_variant, 0, sizeof(callable_variant));
    g_variant_from_callable(callable_variant, callable_value);

    uint64_t signal_name_storage = 0;
    kanama_ios_init_string_name(&signal_name_storage, signal_name);
    int64_t flags_cell = flags;
    uint8_t signal_variant[24];
    uint8_t flags_variant[24];
    uint8_t ret_variant[24];
    memset(signal_variant, 0, sizeof(signal_variant));
    memset(flags_variant, 0, sizeof(flags_variant));
    memset(ret_variant, 0, sizeof(ret_variant));
    g_variant_from_string_name(signal_variant, &signal_name_storage);
    g_variant_from_int(flags_variant, &flags_cell);
    kanama_ios_check_variant_arg("Object::connect(callable)", 0, signal_variant, KANAMA_IOS_VARIANT_TYPE_STRING_NAME);
    kanama_ios_check_variant_arg("Object::connect(callable)", 1, callable_variant, KANAMA_IOS_VARIANT_TYPE_CALLABLE);
    kanama_ios_check_variant_arg("Object::connect(callable)", 2, flags_variant, KANAMA_IOS_VARIANT_TYPE_INT);
    const GDExtensionConstVariantPtr args[3] = {
        (GDExtensionConstVariantPtr)signal_variant,
        (GDExtensionConstVariantPtr)callable_variant,
        (GDExtensionConstVariantPtr)flags_variant,
    };
    GDExtensionCallError error;
    memset(&error, 0, sizeof(error));
    g_object_method_bind_call(method_bind, (GDExtensionObjectPtr)(intptr_t)object, args, 3, ret_variant, &error);
    int call_ok = kanama_ios_check_call_error("Object::connect(callable)", &error);
    int64_t connect_error = 0;
    if (call_ok && g_variant_to_int != NULL) {
        g_variant_to_int(&connect_error, ret_variant);
        if (connect_error != 0) {
            fprintf(stderr, "[kanama][ios][c] connect(callable) FAILED: signal=%s returned Error=%lld\n",
                    signal_name, (long long)connect_error);
            fflush(stderr);
        }
    }
    g_variant_destroy(ret_variant);
    g_variant_destroy(flags_variant);
    g_variant_destroy(signal_variant);
    g_variant_destroy(callable_variant);
    g_callable_destructor(callable_value);
    kanama_ios_destroy_string_name(&signal_name_storage);
    // If connect failed, Godot never stored a copy, so the callable was fully
    // destroyed above -> free_func already released the callback. On success the
    // registry entry is released when Godot later drops the connection.
    return (call_ok && connect_error == 0) ? 0 : -1;
}

// Object.disconnect(signal, <the lambda Callable for callback_id>). Recreates a custom Callable with
// the SAME call_func + callback_id userdata as kanama_ios_godot_object_connect_callable. With no
// hash/equal funcs, Godot uses (call_func, callable_userdata) as the Callable identity, so the
// recreated Callable compares equal to the connected one and disconnect removes it. free_func fires
// (idempotently — IosCallableRegistry.release is a HashMap.remove) for both the removed original and
// this temp. Returns 0 on a clean dispatch, -1 otherwise.
int32_t kanama_ios_godot_object_disconnect_callable(
    int64_t object,
    const char *signal_name,
    int64_t callback_id
) {
    if (!kanama_ios_resolve_godot_api() || object == 0 || signal_name == NULL ||
        g_callable_custom_create2 == NULL) {
        return -1;
    }
    GDExtensionMethodBindPtr method_bind = kanama_ios_get_method_bind_cached(
        &g_object_disconnect_bind, "Object", "disconnect", KANAMA_IOS_OBJECT_DISCONNECT_HASH);
    if (method_bind == NULL) {
        return -1;
    }

    GDExtensionCallableCustomInfo2 info;
    memset(&info, 0, sizeof(info));
    info.callable_userdata = (void *)(intptr_t)callback_id;
    info.token = g_library;
    info.object_id = 0;
    info.call_func = kanama_ios_callable_trampoline;
    info.free_func = kanama_ios_callable_free;
    uint8_t callable_value[24];
    memset(callable_value, 0, sizeof(callable_value));
    g_callable_custom_create2((GDExtensionUninitializedTypePtr)callable_value, &info);

    uint8_t callable_variant[24];
    uint8_t signal_variant[24];
    uint8_t ret_variant[24];
    memset(callable_variant, 0, sizeof(callable_variant));
    memset(signal_variant, 0, sizeof(signal_variant));
    memset(ret_variant, 0, sizeof(ret_variant));
    g_variant_from_callable(callable_variant, callable_value);
    uint64_t signal_name_storage = 0;
    kanama_ios_init_string_name(&signal_name_storage, signal_name);
    g_variant_from_string_name(signal_variant, &signal_name_storage);
    const GDExtensionConstVariantPtr args[2] = {
        (GDExtensionConstVariantPtr)signal_variant,
        (GDExtensionConstVariantPtr)callable_variant,
    };
    GDExtensionCallError error;
    memset(&error, 0, sizeof(error));
    g_object_method_bind_call(
        method_bind, (GDExtensionObjectPtr)(intptr_t)object, args, 2, ret_variant, &error);
    int call_ok = kanama_ios_check_call_error("Object::disconnect(callable)", &error);

    g_variant_destroy(ret_variant);
    g_variant_destroy(signal_variant);
    g_variant_destroy(callable_variant);
    g_callable_destructor(callable_value);
    kanama_ios_destroy_string_name(&signal_name_storage);
    return call_ok ? 0 : -1;
}

static int64_t kanama_ios_variant_to_int64(GDExtensionConstVariantPtr variant) {
    if (variant == NULL || g_variant_to_int == NULL) {
        return 0;
    }
    GDExtensionVariantType type = g_variant_get_type != NULL ? g_variant_get_type(variant) : KANAMA_IOS_VARIANT_TYPE_NIL;
    if (type != KANAMA_IOS_VARIANT_TYPE_INT) {
        return 0;
    }
    int64_t value = 0;
    g_variant_to_int(&value, (GDExtensionVariantPtr)variant);
    return value;
}

static void kanama_ios_variant_to_vector2i(GDExtensionConstVariantPtr variant, int64_t *x, int64_t *y) {
    *x = 0;
    *y = 0;
    if (variant == NULL || g_variant_to_vector2i == NULL || g_variant_get_type == NULL) {
        return;
    }
    GDExtensionVariantType type = g_variant_get_type(variant);
    if (type != KANAMA_IOS_VARIANT_TYPE_VECTOR2I) {
        return;
    }
    int32_t vec[2] = { 0, 0 };
    g_variant_to_vector2i(vec, (GDExtensionVariantPtr)variant);
    *x = vec[0];
    *y = vec[1];
}

static void kanama_ios_script_instance_call(
    GDExtensionScriptInstanceDataPtr data,
    GDExtensionConstStringNamePtr method,
    const GDExtensionConstVariantPtr *args,
    GDExtensionInt argument_count,
    GDExtensionVariantPtr ret,
    GDExtensionCallError *error
) {
    if (ret != NULL) {
        kanama_ios_init_nil_variant((GDExtensionUninitializedVariantPtr)ret);
    }
    KanamaIosScriptInstance *instance = kanama_ios_script_instance_data(data);
    int32_t method_index = kanama_ios_script_method_index(instance != NULL ? instance->script : NULL, method);
    if (instance != NULL && method_index >= 0) {
        int32_t argc = (int32_t)argument_count;
        if (argc < 0) {
            argc = 0;
        }
        if (argc > KANAMA_IOS_PTRCALL_MAX_ARGS) {
            argc = KANAMA_IOS_PTRCALL_MAX_ARGS;
        }
        // Marshal every arg into a PT-tagged buffer (the inverse of kanama_ios_godot_object_call):
        // POD goes in the i64/f64/fvec/ivec cells; String/NodePath are extracted to a utf8 cell
        // freed after the call. Unaudited Variant types fall through to PT_VOID (the Kotlin decode
        // yields null) so an unsupported arg can't silently route to the wrong typed call.
        int32_t tags[KANAMA_IOS_PTRCALL_MAX_ARGS];
        const void *ptrs[KANAMA_IOS_PTRCALL_MAX_ARGS];
        int64_t i64[KANAMA_IOS_PTRCALL_MAX_ARGS];
        double f64[KANAMA_IOS_PTRCALL_MAX_ARGS];
        float fvec[KANAMA_IOS_PTRCALL_MAX_ARGS][4];
        int32_t ivec[KANAMA_IOS_PTRCALL_MAX_ARGS][2];
        char *strs[KANAMA_IOS_PTRCALL_MAX_ARGS];

        for (int32_t i = 0; i < argc; i++) {
            tags[i] = KANAMA_IOS_PT_VOID;
            ptrs[i] = NULL;
            strs[i] = NULL;
            GDExtensionConstVariantPtr v = (args != NULL) ? args[i] : NULL;
            if (v == NULL) {
                continue;
            }
            GDExtensionVariantType vt = g_variant_get_type != NULL
                ? g_variant_get_type(v)
                : KANAMA_IOS_VARIANT_TYPE_NIL;
            switch (vt) {
                case KANAMA_IOS_VARIANT_TYPE_INT:
                    i64[i] = kanama_ios_variant_to_int64(v);
                    tags[i] = KANAMA_IOS_PT_INT64;
                    ptrs[i] = &i64[i];
                    break;
                case KANAMA_IOS_VARIANT_TYPE_BOOL:
                    if (g_variant_to_bool != NULL) {
                        uint8_t b = 0;
                        g_variant_to_bool(&b, (GDExtensionVariantPtr)(intptr_t)v);
                        i64[i] = (int64_t)b;
                    } else {
                        i64[i] = 0;
                    }
                    tags[i] = KANAMA_IOS_PT_BOOL;
                    ptrs[i] = &i64[i];
                    break;
                case KANAMA_IOS_VARIANT_TYPE_FLOAT:
                    f64[i] = kanama_ios_variant_to_double(v);
                    tags[i] = KANAMA_IOS_PT_FLOAT64;
                    ptrs[i] = &f64[i];
                    break;
                case KANAMA_IOS_VARIANT_TYPE_VECTOR2:
                    if (g_variant_to_vector2 != NULL) {
                        g_variant_to_vector2((GDExtensionUninitializedTypePtr)fvec[i], (GDExtensionVariantPtr)(intptr_t)v);
                        tags[i] = KANAMA_IOS_PT_VECTOR2;
                        ptrs[i] = fvec[i];
                    }
                    break;
                case KANAMA_IOS_VARIANT_TYPE_VECTOR3:
                    if (g_variant_to_vector3 != NULL) {
                        g_variant_to_vector3((GDExtensionUninitializedTypePtr)fvec[i], (GDExtensionVariantPtr)(intptr_t)v);
                        tags[i] = KANAMA_IOS_PT_VECTOR3;
                        ptrs[i] = fvec[i];
                    }
                    break;
                case KANAMA_IOS_VARIANT_TYPE_COLOR:
                    if (g_variant_to_color != NULL) {
                        g_variant_to_color((GDExtensionUninitializedTypePtr)fvec[i], (GDExtensionVariantPtr)(intptr_t)v);
                        tags[i] = KANAMA_IOS_PT_COLOR;
                        ptrs[i] = fvec[i];
                    }
                    break;
                case KANAMA_IOS_VARIANT_TYPE_VECTOR2I: {
                    int64_t vx = 0, vy = 0;
                    kanama_ios_variant_to_vector2i(v, &vx, &vy);
                    ivec[i][0] = (int32_t)vx;
                    ivec[i][1] = (int32_t)vy;
                    tags[i] = KANAMA_IOS_PT_VECTOR2I;
                    ptrs[i] = ivec[i];
                    break;
                }
                case KANAMA_IOS_VARIANT_TYPE_STRING:
                    if (g_variant_to_string != NULL) {
                        uint64_t raw_str = 0;
                        g_variant_to_string(&raw_str, (GDExtensionVariantPtr)(intptr_t)v);
                        strs[i] = kanama_ios_string_to_utf8_dup((GDExtensionConstStringPtr)&raw_str);
                        if (g_string_destructor != NULL) {
                            g_string_destructor((GDExtensionStringPtr)&raw_str);
                        }
                        tags[i] = KANAMA_IOS_PT_STRING;
                        ptrs[i] = (strs[i] != NULL) ? strs[i] : "";
                    }
                    break;
                case KANAMA_IOS_VARIANT_TYPE_NODE_PATH:
                    if (g_variant_to_node_path != NULL && g_string_from_node_path_constructor != NULL) {
                        uint64_t raw_np = 0;
                        g_variant_to_node_path((GDExtensionUninitializedTypePtr)&raw_np, (GDExtensionVariantPtr)(intptr_t)v);
                        uint64_t raw_str = 0;
                        const GDExtensionConstTypePtr np_args[1] = { (GDExtensionConstTypePtr)&raw_np };
                        g_string_from_node_path_constructor((GDExtensionUninitializedTypePtr)&raw_str, np_args);
                        strs[i] = kanama_ios_string_to_utf8_dup((GDExtensionConstStringPtr)&raw_str);
                        kanama_ios_destroy_string(&raw_str);
                        kanama_ios_destroy_node_path(&raw_np);
                        tags[i] = KANAMA_IOS_PT_NODE_PATH;
                        ptrs[i] = (strs[i] != NULL) ? strs[i] : "";
                    }
                    break;
                case KANAMA_IOS_VARIANT_TYPE_OBJECT:
                    i64[i] = (int64_t)(intptr_t)kanama_ios_variant_to_object(v);
                    tags[i] = KANAMA_IOS_PT_OBJECT;
                    ptrs[i] = &i64[i];
                    break;
                default:
                    // Unaudited arg type — leave PT_VOID; the emitter skip+warns the method.
                    break;
            }
        }

        // Phase 5.3b: provide a PT-tagged return scratch. Kotlin sets ret_tag to the return's PT
        // kind (or PT_VOID) and writes its bytes; we then build the engine return Variant.
        int32_t ret_tag = KANAMA_IOS_PT_VOID;
        uint8_t ret_buf[32];
        memset(ret_buf, 0, sizeof(ret_buf));
        int32_t ok = kanama_ios_runtime_script_instance_call_v(
            instance->runtime_handle, method_index, tags, ptrs, argc, &ret_tag, ret_buf);

        if (ret != NULL && ret_tag != KANAMA_IOS_PT_VOID) {
            // Bool/Int/Float/Vector2/Vector2i are POD (bytes inline in ret_buf); String is
            // variable-length (ret_buf holds a char* pointer, freed via destroy-after-read inside
            // the helper). See kanama_ios_pt_return_to_variant.
            kanama_ios_pt_return_to_variant(ret_tag, ret_buf, (uint8_t *)ret);
        }

        for (int32_t i = 0; i < argc; i++) {
            free(strs[i]);
        }

        kanama_ios_script_instance_set_ok(error, ok);
        if (!ok) {
            fprintf(stderr,
                    "[kanama][ios][c] script method call failed runtime=%lld method=%s\n",
                    (long long)instance->runtime_handle,
                    kanama_ios_script_method_name_text(instance->script, method_index));
        }
    } else {
        kanama_ios_script_instance_set_ok(error, 0);
    }
}

static void kanama_ios_script_instance_configure_lifecycle_processing(KanamaIosScriptInstance *instance) {
    if (instance == NULL || instance->script == NULL || instance->owner_object == NULL) {
        return;
    }
    kanama_ios_cache_names();

    if (kanama_ios_script_method_index(instance->script, (GDExtensionConstStringNamePtr)&g_name__physics_process) >= 0) {
        GDExtensionMethodBindPtr set_physics_process = kanama_ios_get_method_bind_cached(
            &g_node_set_physics_process_bind,
            "Node",
            "set_physics_process",
            KANAMA_IOS_NODE_SET_PHYSICS_PROCESS_HASH
        );
        kanama_ios_godot_ptrcall_bool_arg(set_physics_process, instance->owner_object, 1);
    }
    if (kanama_ios_script_method_index(instance->script, (GDExtensionConstStringNamePtr)&g_name__process) >= 0) {
        GDExtensionMethodBindPtr set_process = kanama_ios_get_method_bind_cached(
            &g_node_set_process_bind,
            "Node",
            "set_process",
            KANAMA_IOS_NODE_SET_PROCESS_HASH
        );
        kanama_ios_godot_ptrcall_bool_arg(set_process, instance->owner_object, 1);
    }
    if (kanama_ios_script_method_index(instance->script, (GDExtensionConstStringNamePtr)&g_name__input) >= 0) {
        GDExtensionMethodBindPtr set_process_input = kanama_ios_get_method_bind_cached(
            &g_node_set_process_input_bind,
            "Node",
            "set_process_input",
            KANAMA_IOS_NODE_SET_PROCESS_INPUT_HASH
        );
        kanama_ios_godot_ptrcall_bool_arg(set_process_input, instance->owner_object, 1);
    }
    // set_process_{shortcut,unhandled,unhandled_key}_input share the void(bool) signature, so
    // they reuse KANAMA_IOS_NODE_SET_PROCESS_INPUT_HASH (the GDExtension bind hash is the
    // signature hash, not name-derived — all set_process* binds hash identically).
    if (kanama_ios_script_method_index(instance->script, (GDExtensionConstStringNamePtr)&g_name__unhandled_input) >= 0) {
        GDExtensionMethodBindPtr set_process_unhandled_input = kanama_ios_get_method_bind_cached(
            &g_node_set_process_unhandled_input_bind,
            "Node",
            "set_process_unhandled_input",
            KANAMA_IOS_NODE_SET_PROCESS_INPUT_HASH
        );
        kanama_ios_godot_ptrcall_bool_arg(set_process_unhandled_input, instance->owner_object, 1);
    }
    if (kanama_ios_script_method_index(instance->script, (GDExtensionConstStringNamePtr)&g_name__shortcut_input) >= 0) {
        GDExtensionMethodBindPtr set_process_shortcut_input = kanama_ios_get_method_bind_cached(
            &g_node_set_process_shortcut_input_bind,
            "Node",
            "set_process_shortcut_input",
            KANAMA_IOS_NODE_SET_PROCESS_INPUT_HASH
        );
        kanama_ios_godot_ptrcall_bool_arg(set_process_shortcut_input, instance->owner_object, 1);
    }
    if (kanama_ios_script_method_index(instance->script, (GDExtensionConstStringNamePtr)&g_name__unhandled_key_input) >= 0) {
        GDExtensionMethodBindPtr set_process_unhandled_key_input = kanama_ios_get_method_bind_cached(
            &g_node_set_process_unhandled_key_input_bind,
            "Node",
            "set_process_unhandled_key_input",
            KANAMA_IOS_NODE_SET_PROCESS_INPUT_HASH
        );
        kanama_ios_godot_ptrcall_bool_arg(set_process_unhandled_key_input, instance->owner_object, 1);
    }
}

// Dispatch a no-arg tree-lifecycle virtual (_enter_tree/_exit_tree) through the generic call_v.
// Tree virtuals reach the script instance via this notification callback (not the call callback,
// which Godot uses for the per-frame/input virtuals); when the script declares the method we
// route it through the same PT-tagged path with zero args.
static void kanama_ios_script_instance_dispatch_tree_virtual(
    KanamaIosScriptInstance *instance,
    GDExtensionConstStringNamePtr virtual_name
) {
    if (instance == NULL || instance->script == NULL) {
        return;
    }
    int32_t method_index = kanama_ios_script_method_index(instance->script, virtual_name);
    if (method_index >= 0) {
        // Notification-path virtuals (tree lifecycle) are void — ignore any return.
        kanama_ios_runtime_script_instance_call_v(instance->runtime_handle, method_index, NULL, NULL, 0, NULL, NULL);
    }
}

static void kanama_ios_script_instance_notification(
    GDExtensionScriptInstanceDataPtr data,
    int32_t what,
    GDExtensionBool reversed
) {
    (void)reversed;
    if (what == KANAMA_IOS_NOTIFICATION_ENTER_TREE || what == KANAMA_IOS_NOTIFICATION_READY) {
        KanamaIosScriptInstance *instance = kanama_ios_script_instance_data(data);
        kanama_ios_script_instance_configure_lifecycle_processing(instance);
    }
    if (what == KANAMA_IOS_NOTIFICATION_ENTER_TREE) {
        KanamaIosScriptInstance *instance = kanama_ios_script_instance_data(data);
        kanama_ios_script_instance_dispatch_tree_virtual(
            instance, (GDExtensionConstStringNamePtr)&g_name__enter_tree);
    }
    if (what == KANAMA_IOS_NOTIFICATION_EXIT_TREE) {
        KanamaIosScriptInstance *instance = kanama_ios_script_instance_data(data);
        kanama_ios_script_instance_dispatch_tree_virtual(
            instance, (GDExtensionConstStringNamePtr)&g_name__exit_tree);
    }
    if (what == KANAMA_IOS_NOTIFICATION_READY) {
        KanamaIosScriptInstance *instance = kanama_ios_script_instance_data(data);
        if (instance != NULL) {
            kanama_ios_runtime_script_instance_ready(instance->runtime_handle);
        }
    }
}

static void kanama_ios_script_instance_to_string(
    GDExtensionScriptInstanceDataPtr data,
    GDExtensionBool *is_valid,
    GDExtensionStringPtr out
) {
    (void)data;
    if (is_valid != NULL) {
        *is_valid = 1;
    }
    kanama_ios_init_string((uint64_t *)out, "KanamaIosProbeScript");
}

static void kanama_ios_script_instance_refcount_incremented(GDExtensionScriptInstanceDataPtr data) {
    (void)data;
}

static GDExtensionObjectPtr kanama_ios_script_instance_get_script(GDExtensionScriptInstanceDataPtr data) {
    KanamaIosScriptInstance *instance = kanama_ios_script_instance_data(data);
    return instance != NULL ? instance->script_object : NULL;
}

static GDExtensionBool kanama_ios_script_instance_is_placeholder(GDExtensionScriptInstanceDataPtr data) {
    (void)data;
    return 0;
}

static GDExtensionScriptLanguagePtr kanama_ios_script_instance_get_language(GDExtensionScriptInstanceDataPtr data) {
    (void)data;
    return (GDExtensionScriptLanguagePtr)g_script_language_object;
}

static void kanama_ios_ref_retain(KanamaIosScriptInstance *instance, GDExtensionObjectPtr obj) {
    if (obj == NULL || instance == NULL) return;
    GDExtensionMethodBindPtr bind = kanama_ios_get_method_bind_cached(
        &g_ref_counted_reference_bind, "RefCounted", "reference", KANAMA_IOS_REF_COUNTED_NOARGS_HASH);
    if (bind != NULL) {
        GDExtensionBool result = 0;
        g_object_method_bind_ptrcall(bind, obj, NULL, &result);
    }
    if (instance->referenced_object_count < 16) {
        instance->referenced_objects[instance->referenced_object_count++] = obj;
    }
}

static void kanama_ios_ref_release_all(KanamaIosScriptInstance *instance) {
    if (instance == NULL) return;
    GDExtensionMethodBindPtr bind = kanama_ios_get_method_bind_cached(
        &g_ref_counted_unreference_bind, "RefCounted", "unreference", KANAMA_IOS_REF_COUNTED_NOARGS_HASH);
    for (int i = 0; i < instance->referenced_object_count; i++) {
        if (instance->referenced_objects[i] != NULL && bind != NULL) {
            GDExtensionBool result = 0;
            g_object_method_bind_ptrcall(bind, instance->referenced_objects[i], NULL, &result);
        }
        instance->referenced_objects[i] = NULL;
    }
    instance->referenced_object_count = 0;
}

static void kanama_ios_script_instance_free(GDExtensionScriptInstanceDataPtr data) {
    KanamaIosScriptInstance *instance = kanama_ios_script_instance_data(data);
    if (instance != NULL) {
        kanama_ios_ref_release_all(instance);
        kanama_ios_runtime_script_instance_free(instance->runtime_handle);
        free(instance);
    }
}

static void kanama_ios_cache_array_methods(void) {
    if (g_array_destructor == NULL && g_variant_get_ptr_destructor != NULL) {
        g_array_destructor = g_variant_get_ptr_destructor(KANAMA_IOS_VARIANT_TYPE_ARRAY);
    }
    if (g_array_size_method != NULL && g_array_get_method != NULL) {
        return;
    }
    if (g_variant_get_ptr_builtin_method == NULL) {
        return;
    }
    if (g_array_size_method == NULL) {
        uint64_t name_storage = 0;
        kanama_ios_init_string_name(&name_storage, "size");
        g_array_size_method = g_variant_get_ptr_builtin_method(
            KANAMA_IOS_VARIANT_TYPE_ARRAY,
            (GDExtensionConstStringNamePtr)&name_storage,
            (GDExtensionInt)KANAMA_IOS_ARRAY_SIZE_HASH
        );
        kanama_ios_destroy_string_name(&name_storage);
    }
    if (g_array_get_method == NULL) {
        uint64_t name_storage = 0;
        kanama_ios_init_string_name(&name_storage, "get");
        g_array_get_method = g_variant_get_ptr_builtin_method(
            KANAMA_IOS_VARIANT_TYPE_ARRAY,
            (GDExtensionConstStringNamePtr)&name_storage,
            (GDExtensionInt)KANAMA_IOS_ARRAY_GET_HASH
        );
        kanama_ios_destroy_string_name(&name_storage);
    }
}

// Typed-object-array (Array[Object]) ptrcall return -> object handles. Drives the call through
// the generic dispatcher (so arg-shapes like a single bool are constructed the same way as any
// ptrcall) with ret_out = an 8-byte Array opaque slot (Array is an OPAQUE_8_BYTE_TYPE — NOT 16
// like Packed*Array), then reads each element back via the Array size/get builtins +
// variant_to_object. Element handles are written into out_handles (caller-owned). Two-call length
// protocol like the Packed*Array helpers: pass out_handles=NULL to learn the count, then call
// again with a buffer of that capacity (the ptrcall re-runs each call). Returns the FULL element
// count (negative on resolution failure). cap is an ELEMENT count.
int64_t kanama_ios_godot_ptrcall_ret_object_array(
    int64_t method_bind,
    int64_t instance,
    const int32_t *arg_types,
    const void *const *arg_ptrs,
    int32_t arg_count,
    int64_t *out_handles,
    int64_t cap
) {
    if (!kanama_ios_resolve_godot_api() || method_bind == 0 || instance == 0) {
        return -1;
    }
    kanama_ios_cache_array_methods();
    if (g_array_size_method == NULL || g_array_get_method == NULL || g_variant_to_object == NULL) {
        return -1;
    }

    // Array opaque size is 8 bytes on 64-bit (OPAQUE_8_BYTE_TYPES) — a single uint64_t slot, NOT
    // the 16-byte Packed*Array storage. The generic dispatcher writes the Array opaque into it.
    uint64_t array_storage = 0;
    kanama_ios_godot_ptrcall(
        method_bind, instance, arg_types, arg_ptrs, arg_count,
        KANAMA_IOS_PT_OBJECT /* any non-void ret tag → ret_out is used */, &array_storage);

    int64_t size = 0;
    g_array_size_method(&array_storage, NULL, &size, 0);

    if (out_handles != NULL && cap > 0 && size > 0) {
        int64_t n = (size < cap) ? size : cap;
        for (int64_t i = 0; i < n; i++) {
            // Array.get(i) returns a Variant; read its object pointer (0 for non-Object elements).
            uint8_t ret_variant[24] = {0};
            const GDExtensionConstTypePtr args[1] = { (GDExtensionConstTypePtr)&i };
            g_array_get_method(&array_storage, args, ret_variant, 1);
            int64_t handle = 0;
            GDExtensionVariantType elem_type = g_variant_get_type != NULL
                ? g_variant_get_type((GDExtensionConstVariantPtr)ret_variant)
                : KANAMA_IOS_VARIANT_TYPE_NIL;
            if (elem_type == KANAMA_IOS_VARIANT_TYPE_OBJECT) {
                GDExtensionObjectPtr obj_ptr = NULL;
                g_variant_to_object(&obj_ptr, (GDExtensionVariantPtr)ret_variant);
                handle = (int64_t)(intptr_t)obj_ptr;
            }
            if (g_variant_destroy != NULL) {
                g_variant_destroy((GDExtensionVariantPtr)ret_variant);
            }
            out_handles[i] = handle;
        }
    }

    if (g_array_destructor != NULL) {
        g_array_destructor((GDExtensionTypePtr)&array_storage);
    }
    return size;
}

static GDExtensionBool kanama_ios_script_instance_set_property(
    GDExtensionScriptInstanceDataPtr data,
    GDExtensionConstStringNamePtr name,
    GDExtensionConstVariantPtr value
) {
    if (data == NULL || name == NULL || value == NULL) {
        return 0;
    }
    KanamaIosScriptInstance *instance = (KanamaIosScriptInstance *)data;
    if (instance->script == NULL) {
        return 0;
    }
    int32_t property_index = kanama_ios_script_property_index(instance->script, name);
    if (property_index < 0) {
        return 0;
    }
    GDExtensionVariantType type = g_variant_get_type != NULL
        ? g_variant_get_type(value)
        : KANAMA_IOS_VARIANT_TYPE_NIL;
    int64_t arg = 0;
    if (type == KANAMA_IOS_VARIANT_TYPE_OBJECT) {
        GDExtensionObjectPtr obj = kanama_ios_variant_to_object(value);
        arg = (int64_t)(intptr_t)obj;
        kanama_ios_ref_retain(instance, obj);
    } else if (type == KANAMA_IOS_VARIANT_TYPE_INT) {
        arg = kanama_ios_variant_to_int64(value);
    } else if (type == KANAMA_IOS_VARIANT_TYPE_BOOL && g_variant_to_bool != NULL) {
        uint8_t b = 0;
        g_variant_to_bool(&b, (GDExtensionVariantPtr)(intptr_t)value);
        arg = (int64_t)b;
    } else if (type == KANAMA_IOS_VARIANT_TYPE_FLOAT && g_variant_to_float != NULL) {
        double d = 0.0;
        g_variant_to_float(&d, (GDExtensionVariantPtr)(intptr_t)value);
        memcpy(&arg, &d, sizeof(arg));
    } else if (type == KANAMA_IOS_VARIANT_TYPE_STRING && g_variant_to_string != NULL) {
        uint64_t raw_str = 0;
        g_variant_to_string(&raw_str, (GDExtensionVariantPtr)(intptr_t)value);
        char *utf8 = kanama_ios_string_to_utf8_dup((GDExtensionConstStringPtr)&raw_str);
        if (g_string_destructor != NULL) {
            g_string_destructor((GDExtensionStringPtr)&raw_str);
        }
        int32_t ok = kanama_ios_runtime_script_instance_set_property_string(
            instance->runtime_handle, property_index, utf8 != NULL ? utf8 : "");
        free(utf8);
        return (GDExtensionBool)ok;
    } else if (type == KANAMA_IOS_VARIANT_TYPE_NIL) {
        arg = 0;
    } else if (type == KANAMA_IOS_VARIANT_TYPE_ARRAY && g_variant_to_array != NULL) {
        kanama_ios_cache_array_methods();
        if (g_array_size_method == NULL || g_array_get_method == NULL) { return 0; }
        uint8_t raw_array[8] = {0};
        g_variant_to_array(raw_array, (GDExtensionVariantPtr)(intptr_t)value);
        int64_t size = 0;
        g_array_size_method(raw_array, NULL, &size, 0);
        if (size <= 0) {
            int32_t ok = kanama_ios_runtime_script_instance_set_property_array(
                instance->runtime_handle, property_index, NULL, 0);
            return (GDExtensionBool)ok;
        }
        int64_t *objects = (int64_t *)calloc((size_t)size, sizeof(int64_t));
        if (objects == NULL) { return 0; }
        for (int64_t i = 0; i < size; i++) {
            uint8_t ret_variant[24] = {0};
            const GDExtensionConstTypePtr args[1] = { (GDExtensionConstTypePtr)&i };
            g_array_get_method(raw_array, args, ret_variant, 1);
            GDExtensionVariantType elem_type = g_variant_get_type != NULL
                ? g_variant_get_type((GDExtensionConstVariantPtr)ret_variant)
                : KANAMA_IOS_VARIANT_TYPE_NIL;
            if (elem_type == KANAMA_IOS_VARIANT_TYPE_OBJECT) {
                GDExtensionObjectPtr obj_ptr = NULL;
                g_variant_to_object(&obj_ptr, (GDExtensionVariantPtr)ret_variant);
                objects[i] = (int64_t)(intptr_t)obj_ptr;
                kanama_ios_ref_retain(instance, obj_ptr);
            }
            if (g_variant_destroy != NULL) {
                g_variant_destroy((GDExtensionVariantPtr)ret_variant);
            }
        }
        int32_t ok = kanama_ios_runtime_script_instance_set_property_array(
            instance->runtime_handle, property_index, objects, (int32_t)size);
        free(objects);
        return (GDExtensionBool)ok;
    } else if (type == KANAMA_IOS_VARIANT_TYPE_NODE_PATH
               && g_variant_to_node_path != NULL
               && g_string_from_node_path_constructor != NULL) {
        // NodePath -> String(from NodePath) -> utf8; deliver the path bytes (PT_NODE_PATH).
        uint64_t raw_np = 0;
        g_variant_to_node_path(
            (GDExtensionUninitializedTypePtr)&raw_np, (GDExtensionVariantPtr)(intptr_t)value);
        uint64_t raw_str = 0;
        const GDExtensionConstTypePtr np_args[1] = { (GDExtensionConstTypePtr)&raw_np };
        g_string_from_node_path_constructor((GDExtensionUninitializedTypePtr)&raw_str, np_args);
        char *utf8 = kanama_ios_string_to_utf8_dup((GDExtensionConstStringPtr)&raw_str);
        kanama_ios_destroy_string(&raw_str);
        kanama_ios_destroy_node_path(&raw_np);
        int32_t ok = kanama_ios_runtime_script_instance_set_property_value(
            instance->runtime_handle, property_index, KANAMA_IOS_PT_NODE_PATH,
            (const uint8_t *)(utf8 != NULL ? utf8 : ""),
            (int32_t)(utf8 != NULL ? strlen(utf8) : 0));
        free(utf8);
        return (GDExtensionBool)ok;
    } else if (type == KANAMA_IOS_VARIANT_TYPE_VECTOR2 && g_variant_to_vector2 != NULL) {
        float comps[2] = {0};
        g_variant_to_vector2(
            (GDExtensionUninitializedTypePtr)comps, (GDExtensionVariantPtr)(intptr_t)value);
        int32_t ok = kanama_ios_runtime_script_instance_set_property_value(
            instance->runtime_handle, property_index, KANAMA_IOS_PT_VECTOR2,
            (const uint8_t *)comps, (int32_t)sizeof(comps));
        return (GDExtensionBool)ok;
    } else if (type == KANAMA_IOS_VARIANT_TYPE_VECTOR3 && g_variant_to_vector3 != NULL) {
        float comps[3] = {0};
        g_variant_to_vector3(
            (GDExtensionUninitializedTypePtr)comps, (GDExtensionVariantPtr)(intptr_t)value);
        int32_t ok = kanama_ios_runtime_script_instance_set_property_value(
            instance->runtime_handle, property_index, KANAMA_IOS_PT_VECTOR3,
            (const uint8_t *)comps, (int32_t)sizeof(comps));
        return (GDExtensionBool)ok;
    } else if (type == KANAMA_IOS_VARIANT_TYPE_COLOR && g_variant_to_color != NULL) {
        float comps[4] = {0};
        g_variant_to_color(
            (GDExtensionUninitializedTypePtr)comps, (GDExtensionVariantPtr)(intptr_t)value);
        int32_t ok = kanama_ios_runtime_script_instance_set_property_value(
            instance->runtime_handle, property_index, KANAMA_IOS_PT_COLOR,
            (const uint8_t *)comps, (int32_t)sizeof(comps));
        return (GDExtensionBool)ok;
    } else if (type == KANAMA_IOS_VARIANT_TYPE_PACKED_STRING_ARRAY
               && g_variant_to_packed_string_array != NULL) {
        // List<String> @ScriptProperty delivery. Extract the PackedStringArray from the Variant
        // into its opaque storage, then iterate via the cached size/operator_index_const builtins
        // (same trio the no-arg ptrcall return helper uses), dup each element to utf8, and hand
        // the C string array to the Kotlin runtime entrypoint.
        kanama_ios_cache_packed_string_methods();
        if (g_packed_string_array_size_method == NULL ||
            g_packed_string_array_operator_index_const == NULL) {
            return 0;
        }
        KANAMA_IOS_PACKED_ARRAY_STORAGE(array_storage);
        g_variant_to_packed_string_array(
            (GDExtensionUninitializedTypePtr)array_storage,
            (GDExtensionVariantPtr)(intptr_t)value);
        int64_t count = 0;
        g_packed_string_array_size_method(array_storage, NULL, &count, 0);
        if (count < 0) {
            count = 0;
        }
        const char **strings = NULL;
        if (count > 0) {
            strings = (const char **)calloc((size_t)count, sizeof(char *));
            if (strings == NULL) {
                if (g_packed_string_array_destructor != NULL) {
                    g_packed_string_array_destructor(array_storage);
                }
                return 0;
            }
            for (int64_t i = 0; i < count; i++) {
                GDExtensionStringPtr str = g_packed_string_array_operator_index_const(
                    array_storage, (GDExtensionInt)i);
                strings[i] = (str != NULL)
                    ? kanama_ios_string_to_utf8_dup((GDExtensionConstStringPtr)str)
                    : NULL;
            }
        }
        int32_t ok = kanama_ios_runtime_script_instance_set_property_string_array(
            instance->runtime_handle, property_index, strings, (int32_t)count);
        if (strings != NULL) {
            for (int64_t i = 0; i < count; i++) {
                free((void *)strings[i]);
            }
            free(strings);
        }
        if (g_packed_string_array_destructor != NULL) {
            g_packed_string_array_destructor(array_storage);
        }
        return (GDExtensionBool)ok;
    } else {
        return 0;
    }
    int32_t ok = kanama_ios_runtime_script_instance_set_property(
        instance->runtime_handle,
        property_index,
        arg
    );
    return (GDExtensionBool)ok;
}

static GDExtensionScriptInstanceInfo3 g_script_instance_info = {
    kanama_ios_script_instance_set_property,
    (GDExtensionScriptInstanceGet)kanama_ios_script_instance_false_3,
    kanama_ios_script_instance_get_property_list,
    kanama_ios_script_instance_free_property_list,
    NULL,
    kanama_ios_script_instance_false_2,
    (GDExtensionScriptInstancePropertyGetRevert)kanama_ios_script_instance_false_3,
    kanama_ios_script_instance_get_owner,
    kanama_ios_script_instance_get_property_state,
    kanama_ios_script_instance_get_method_list,
    kanama_ios_script_instance_free_method_list,
    kanama_ios_script_instance_get_property_type,
    (GDExtensionScriptInstanceValidateProperty)kanama_ios_script_instance_false_2,
    kanama_ios_script_instance_has_method,
    kanama_ios_script_instance_get_method_argument_count,
    kanama_ios_script_instance_call,
    kanama_ios_script_instance_notification,
    kanama_ios_script_instance_to_string,
    kanama_ios_script_instance_refcount_incremented,
    kanama_ios_script_instance_false_1,
    kanama_ios_script_instance_get_script,
    kanama_ios_script_instance_is_placeholder,
    kanama_ios_script_instance_set_property,
    (GDExtensionScriptInstanceGet)kanama_ios_script_instance_false_3,
    kanama_ios_script_instance_get_language,
    kanama_ios_script_instance_free,
};

static GDExtensionScriptInstancePtr kanama_ios_create_script_instance(
    KanamaIosExtensionInstance *script,
    GDExtensionObjectPtr owner_object
) {
    if (script == NULL || owner_object == NULL) {
        return NULL;
    }

    KanamaIosScriptInstance *instance = calloc(1, sizeof(KanamaIosScriptInstance));
    if (instance == NULL) {
        return NULL;
    }
    instance->owner_object = owner_object;
    instance->script_object = script->godot_object;
    instance->script = script;
    if (g_kanama_log_lifecycle) {
        fprintf(stderr, "[kanama][ios][c] create_script_instance: script=%lld owner=%p\n",
                (long long)script->script_handle, owner_object);
        fflush(stderr);
    }
    instance->runtime_handle = kanama_ios_runtime_script_instance_create(
        script->script_handle,
        (int64_t)(intptr_t)owner_object
    );
    if (g_kanama_log_lifecycle) {
        fprintf(stderr, "[kanama][ios][c] create_script_instance: runtime_handle=%lld\n",
                (long long)instance->runtime_handle);
        fflush(stderr);
    }
    if (instance->runtime_handle == 0) {
        free(instance);
        return NULL;
    }

    GDExtensionScriptInstancePtr script_instance = g_script_instance_create(&g_script_instance_info, instance);
    if (g_kanama_log_lifecycle) {
        fprintf(stderr,
                "[kanama][ios][c] created ScriptInstance script=%lld owner=%p runtime=%lld ptr=%p\n",
                (long long)script->script_handle,
                owner_object,
                (long long)instance->runtime_handle,
                script_instance);
        fflush(stderr);
    }
    return script_instance;
}

static void kanama_ios_register_extension_class(
    const uint64_t *class_name,
    const uint64_t *parent_name,
    KanamaIosClassKind kind
) {
    GDExtensionClassCreationInfo6 info;
    memset(&info, 0, sizeof(info));
    info.is_exposed = 1;
    info.is_runtime = 1;
    info.create_instance_func = kanama_ios_extension_create_instance;
    info.free_instance_func = kanama_ios_extension_free_instance;
    info.get_virtual_call_data_func = kanama_ios_get_virtual_call_data;
    info.call_virtual_with_data_func = kanama_ios_call_virtual_with_data;
    info.class_userdata = (void *)(intptr_t)kind;
    g_classdb_register_extension_class(
        g_library,
        (GDExtensionConstStringNamePtr)class_name,
        (GDExtensionConstStringNamePtr)parent_name,
        &info
    );
}

static void kanama_ios_register_script_classes(void) {
    if (g_ios_script_classes_registered) {
        return;
    }
    if (!kanama_ios_resolve_godot_api()) {
        return;
    }
    kanama_ios_cache_names();

    kanama_ios_register_extension_class(
        &g_name_KanamaIosScriptLanguage,
        &g_name_ScriptLanguageExtension,
        KANAMA_IOS_CLASS_SCRIPT_LANGUAGE
    );
    kanama_ios_register_extension_class(
        &g_name_KanamaIosScript,
        &g_name_ScriptExtension,
        KANAMA_IOS_CLASS_SCRIPT
    );
    kanama_ios_register_extension_class(
        &g_name_KanamaIosResourceFormatLoader,
        &g_name_ResourceFormatLoader,
        KANAMA_IOS_CLASS_RESOURCE_LOADER
    );
    g_ios_script_classes_registered = 1;
    fprintf(stderr, "[kanama][ios][c] registered iOS script extension classes\n");
}

static void kanama_ios_register_script_language(void) {
    GDExtensionObjectPtr engine = kanama_ios_engine_singleton();
    GDExtensionMethodBindPtr register_bind = kanama_ios_get_method_bind_cached(
        &g_engine_register_script_language_bind,
        "Engine",
        "register_script_language",
        KANAMA_IOS_ENGINE_REGISTER_SCRIPT_LANGUAGE_HASH
    );
    if (engine == NULL || register_bind == NULL) {
        return;
    }

    if (g_script_language_object == NULL) {
        g_script_language_object = kanama_ios_construct_extension_object(KANAMA_IOS_CLASS_SCRIPT_LANGUAGE);
    }
    if (g_script_language_object == NULL) {
        fprintf(stderr, "[kanama][ios][c] error: failed to construct script language object\n");
        return;
    }

    int32_t result = kanama_ios_godot_ptrcall_object_arg_ret_int(
        register_bind,
        engine,
        g_script_language_object
    );
    fprintf(stderr, "[kanama][ios][c] registered script language result=%d object=%p\n",
            result, g_script_language_object);
}

static void kanama_ios_register_resource_loader(void) {
    GDExtensionObjectPtr resource_loader = kanama_ios_resource_loader_singleton();
    GDExtensionMethodBindPtr add_bind = kanama_ios_get_method_bind_cached(
        &g_resource_loader_add_format_loader_bind,
        "ResourceLoader",
        "add_resource_format_loader",
        KANAMA_IOS_RESOURCE_LOADER_ADD_FORMAT_LOADER_HASH
    );
    if (resource_loader == NULL || add_bind == NULL) {
        return;
    }

    if (g_resource_loader_object == NULL) {
        g_resource_loader_object = kanama_ios_construct_extension_object(KANAMA_IOS_CLASS_RESOURCE_LOADER);
    }
    if (g_resource_loader_object == NULL) {
        fprintf(stderr, "[kanama][ios][c] error: failed to construct resource loader object\n");
        return;
    }

    kanama_ios_godot_ptrcall_object_bool_arg(add_bind, resource_loader, g_resource_loader_object, 1);
    fprintf(stderr, "[kanama][ios][c] registered .kt resource loader object=%p\n", g_resource_loader_object);
}

static void kanama_ios_unregister_script_runtime(void) {
    if (!g_ios_script_classes_registered) {
        return;
    }

    GDExtensionObjectPtr resource_loader = kanama_ios_resource_loader_singleton();
    GDExtensionMethodBindPtr remove_bind = kanama_ios_get_method_bind_cached(
        &g_resource_loader_remove_format_loader_bind,
        "ResourceLoader",
        "remove_resource_format_loader",
        KANAMA_IOS_RESOURCE_LOADER_REMOVE_FORMAT_LOADER_HASH
    );
    if (resource_loader != NULL && remove_bind != NULL && g_resource_loader_object != NULL) {
        kanama_ios_godot_ptrcall_object_arg(remove_bind, resource_loader, g_resource_loader_object);
        g_resource_loader_object = NULL;
    }

    GDExtensionObjectPtr engine = kanama_ios_engine_singleton();
    GDExtensionMethodBindPtr unregister_bind = kanama_ios_get_method_bind_cached(
        &g_engine_unregister_script_language_bind,
        "Engine",
        "unregister_script_language",
        KANAMA_IOS_ENGINE_UNREGISTER_SCRIPT_LANGUAGE_HASH
    );
    if (engine != NULL && unregister_bind != NULL && g_script_language_object != NULL) {
        (void)kanama_ios_godot_ptrcall_object_arg_ret_int(unregister_bind, engine, g_script_language_object);
        g_script_language_object = NULL;
    }

    g_classdb_unregister_extension_class(g_library, (GDExtensionConstStringNamePtr)&g_name_KanamaIosResourceFormatLoader);
    g_classdb_unregister_extension_class(g_library, (GDExtensionConstStringNamePtr)&g_name_KanamaIosScript);
    g_classdb_unregister_extension_class(g_library, (GDExtensionConstStringNamePtr)&g_name_KanamaIosScriptLanguage);
    g_ios_script_classes_registered = 0;
    fprintf(stderr, "[kanama][ios][c] unregistered iOS script runtime\n");
}

static void kanama_ios_frame(void) {
    if (!g_main_loop_callbacks_active) {
        return;
    }
    // Persistent per-frame hook: drive the Kotlin runtime frame for the lifetime of the
    // extension. KanamaIosRuntime.frame() pumps MainThread.pumpNextFrame() every frame —
    // which advances awaitNextFrame / postNextFrame / postAfterFrames (e.g. the KillPlane3D
    // deferred respawn) — and self-terminates its one-time startup probe via its own
    // probeLabelUpdated guard. (Previously this callback disabled itself after 30 frames,
    // permanently killing ALL frame-deferred logic; see ios-demo-port-tracker V1.)
    if (g_main_loop_callback_frame_count < INT_MAX) {
        g_main_loop_callback_frame_count++;
    }
    kanama_ios_runtime_frame();
}

static void kanama_ios_register_main_loop_callbacks(void) {
    if (g_main_loop_callbacks_registered) {
        g_main_loop_callbacks_active = 1;
        g_main_loop_callback_frame_count = 0;
        return;
    }
    if (!kanama_ios_resolve_godot_api()) {
        return;
    }

    GDExtensionMainLoopCallbacks callbacks;
    memset(&callbacks, 0, sizeof(callbacks));
    callbacks.frame_func = kanama_ios_frame;
    g_register_main_loop_callbacks(g_library, &callbacks);
    g_main_loop_callbacks_registered = 1;
    g_main_loop_callbacks_active = 1;
    g_main_loop_callback_frame_count = 0;
    fprintf(stderr, "[kanama][ios][c] registered main loop frame callback\n");
}

#if KANAMA_IOS_DEBUG_VARIANT_CHECKS
// Debug-build guardrail: the generic ptrcall dispatch has no runtime error check of
// its own (a wrong type tag silently reads/writes the wrong bytes), so we validate
// it at scene init with a type-coverage round-trip matrix against real Godot —
// every PT tag the platformer needs, with known expected values. Critically covers
// the float32-vs-float64 and int32 width distinctions (the AudioStreamPlayer-class
// bug). Runs once at startup; debug builds only. Test objects are intentionally
// leaked (a handful, one-time) to keep the probe simple and safe.
static void kanama_ios_ptrcall_selftest(void) {
    int pass = 0;
    int fail = 0;
#define KANAMA_IOS_ST_CHECK(label, cond) \
    do { if (cond) { pass++; } else { fail++; \
        fprintf(stderr, "[kanama][ios][c] SELFTEST FAIL: %s\n", (label)); fflush(stderr); } } while (0)

    int64_t node3d = kanama_ios_godot_construct_object("Node3D");
    if (node3d == 0) {
        fprintf(stderr, "[kanama][ios][c] PTRCALL SELFTEST: construct Node3D FAILED — aborting matrix\n");
        fflush(stderr);
        return;
    }

    // bool: Node3D.set_visible(false) -> is_visible()
    {
        uint8_t v = 0;
        const void *a[1] = { &v };
        int32_t t[1] = { KANAMA_IOS_PT_BOOL };
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("Node3D","set_visible",2586408642),
            node3d, t, a, 1, KANAMA_IOS_PT_VOID, NULL);
        uint8_t got = 1;
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("Node3D","is_visible",36873697),
            node3d, NULL, NULL, 0, KANAMA_IOS_PT_BOOL, &got);
        KANAMA_IOS_ST_CHECK("bool", got == 0);
    }

    // Vector3 (3x f32): Node3D.set_position(1,2,3) -> get_position()
    {
        float in[3] = { 1.0f, 2.0f, 3.0f };
        const void *a[1] = { in };
        int32_t t[1] = { KANAMA_IOS_PT_VECTOR3 };
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("Node3D","set_position",3460891852),
            node3d, t, a, 1, KANAMA_IOS_PT_VOID, NULL);
        float out[3] = { 0, 0, 0 };
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("Node3D","get_position",3360562783),
            node3d, NULL, NULL, 0, KANAMA_IOS_PT_VECTOR3, out);
        KANAMA_IOS_ST_CHECK("vector3", out[0]==1.0f && out[1]==2.0f && out[2]==3.0f);
    }

    // int64 return: Object.get_instance_id() -> nonzero
    {
        uint64_t id = 0;
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("Object","get_instance_id",3905245786),
            node3d, NULL, NULL, 0, KANAMA_IOS_PT_INT64, &id);
        KANAMA_IOS_ST_CHECK("int64-ret", id != 0);
    }

    // StringName arg -> bool: Object.has_method("set_visible")==true, ("__nope__")==false
    {
        uint8_t yes = 0, no = 1;
        const void *ay[1] = { "set_visible" };
        const void *an[1] = { "__nope__" };
        int32_t t[1] = { KANAMA_IOS_PT_STRING_NAME };
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("Object","has_method",2619796661),
            node3d, t, ay, 1, KANAMA_IOS_PT_BOOL, &yes);
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("Object","has_method",2619796661),
            node3d, t, an, 1, KANAMA_IOS_PT_BOOL, &no);
        KANAMA_IOS_ST_CHECK("stringname-arg", yes == 1 && no == 0);
    }

    // scalar float: AudioStreamPlayer.set_volume_db(0.5) -> get_volume_db()
    // Godot's PtrToArg<float> == PtrToArgConvert<float,double>, so a SCALAR float arg
    // /return is 8 bytes (double) at the ptrcall boundary (converted float<->double
    // internally). Marshal scalar floats as FLOAT64. (Vector/Color components are
    // real_t and ARE 4 bytes — see those tests.)
    {
        int64_t asp = kanama_ios_godot_construct_object("AudioStreamPlayer");
        if (asp == 0) {
            fprintf(stderr, "[kanama][ios][c] SELFTEST note: AudioStreamPlayer construct returned 0\n");
            fflush(stderr);
        }
        double in = 0.5;
        const void *a[1] = { &in };
        int32_t t[1] = { KANAMA_IOS_PT_FLOAT64 };
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("AudioStreamPlayer","set_volume_db",373806689),
            asp, t, a, 1, KANAMA_IOS_PT_VOID, NULL);
        double out = -1.0;
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("AudioStreamPlayer","get_volume_db",1740695150),
            asp, NULL, NULL, 0, KANAMA_IOS_PT_FLOAT64, &out);
        KANAMA_IOS_ST_CHECK("scalar-float(volume_db) as double", out == 0.5);
    }

    // float64: Timer.set_wait_time(2.5) -> get_wait_time()  (same hashes as the float32
    // pair above — proves meta-driven width, not hash, selects the marshalling)
    {
        int64_t timer = kanama_ios_godot_construct_object("Timer");
        double in = 2.5;
        const void *a[1] = { &in };
        int32_t t[1] = { KANAMA_IOS_PT_FLOAT64 };
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("Timer","set_wait_time",373806689),
            timer, t, a, 1, KANAMA_IOS_PT_VOID, NULL);
        double out = -1.0;
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("Timer","get_wait_time",1740695150),
            timer, NULL, NULL, 0, KANAMA_IOS_PT_FLOAT64, &out);
        KANAMA_IOS_ST_CHECK("float64", out == 2.5);
    }

    // scalar int (GPUParticles3D.set_amount) — 8 bytes at ptrcall
    // (PtrToArg<int32_t>=convert<int32_t,int64_t>), so lay/read int64.
    {
        int64_t parts = kanama_ios_godot_construct_object("GPUParticles3D");
        int64_t in = 1234567;
        const void *a[1] = { &in };
        int32_t t[1] = { KANAMA_IOS_PT_INT64 };
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("GPUParticles3D","set_amount",1286410249),
            parts, t, a, 1, KANAMA_IOS_PT_VOID, NULL);
        int64_t out = 0;
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("GPUParticles3D","get_amount",3905245786),
            parts, NULL, NULL, 0, KANAMA_IOS_PT_INT64, &out);
        KANAMA_IOS_ST_CHECK("scalar-int(amount) as int64", out == 1234567);
    }

    // Vector2 (2x f32): Node2D.set_position(3,4) -> get_position()
    {
        int64_t node2d = kanama_ios_godot_construct_object("Node2D");
        float in[2] = { 3.0f, 4.0f };
        const void *a[1] = { in };
        int32_t t[1] = { KANAMA_IOS_PT_VECTOR2 };
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("Node2D","set_position",743155724),
            node2d, t, a, 1, KANAMA_IOS_PT_VOID, NULL);
        float out[2] = { 0, 0 };
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("Node2D","get_position",3341600327),
            node2d, NULL, NULL, 0, KANAMA_IOS_PT_VECTOR2, out);
        KANAMA_IOS_ST_CHECK("vector2", out[0]==3.0f && out[1]==4.0f);

        // scalar float (Node2D.set_rotation/get_rotation) — also 8-byte double at ptrcall
        double rin = 0.5;
        const void *ra[1] = { &rin };
        int32_t rt[1] = { KANAMA_IOS_PT_FLOAT64 };
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("Node2D","set_rotation",373806689),
            node2d, rt, ra, 1, KANAMA_IOS_PT_VOID, NULL);
        double rout = -1.0;
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("Node2D","get_rotation",1740695150),
            node2d, NULL, NULL, 0, KANAMA_IOS_PT_FLOAT64, &rout);
        KANAMA_IOS_ST_CHECK("scalar-float(rotation) as double", rout == 0.5);

        // Color (4x f32, 16B): CanvasItem.set_modulate -> get_modulate (Node2D is a CanvasItem)
        // Validation-free: modulate is a stored Color with no engine clamping.
        // Values 0.125/0.25/0.5/0.75 are exact in float32 (powers of 2).
        // DEVICE-VALIDATED 2026-06-12 (iPhone 12, iOS 26.5) — Phase 2.2
        float cin[4] = { 0.125f, 0.25f, 0.5f, 0.75f };
        const void *ca[1] = { cin };
        int32_t ct[1] = { KANAMA_IOS_PT_COLOR };
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("CanvasItem","set_modulate",2920490490),
            node2d, ct, ca, 1, KANAMA_IOS_PT_VOID, NULL);
        float cout[4] = { 0, 0, 0, 0 };
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("CanvasItem","get_modulate",3444240500),
            node2d, NULL, NULL, 0, KANAMA_IOS_PT_COLOR, cout);
        KANAMA_IOS_ST_CHECK("color(0.125,0.25,0.5,0.75)", cout[0]==0.125f && cout[1]==0.25f && cout[2]==0.5f && cout[3]==0.75f);
    }

    // Object arg + multi-arg + Object return:
    // parent.add_child(child,false,0) then parent.get_child(0,false) == child
    {
        int64_t parent = kanama_ios_godot_construct_object("Node3D");
        int64_t child = kanama_ios_godot_construct_object("Node3D");
        int64_t child_cell = child;
        uint8_t force_readable = 0;
        int64_t internal_mode = 0; // enum arg — scalar int, 8 bytes at ptrcall
        const void *aca[3] = { &child_cell, &force_readable, &internal_mode };
        int32_t act[3] = { KANAMA_IOS_PT_OBJECT, KANAMA_IOS_PT_BOOL, KANAMA_IOS_PT_INT64 };
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("Node","add_child",3863233950),
            parent, act, aca, 3, KANAMA_IOS_PT_VOID, NULL);
        int64_t idx = 0; // scalar int, 8 bytes at ptrcall
        uint8_t include_internal = 0;
        const void *gca[2] = { &idx, &include_internal };
        int32_t gct[2] = { KANAMA_IOS_PT_INT64, KANAMA_IOS_PT_BOOL };
        int64_t got_child = 0;
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("Node","get_child",541253412),
            parent, gct, gca, 2, KANAMA_IOS_PT_OBJECT, &got_child);
        KANAMA_IOS_ST_CHECK("object-arg+multiarg+object-ret", got_child == child);
    }

    // Vector2i (2x int32, 8B): Sprite2D.set_frame_coords(Vector2i(3,7)) -> get_frame_coords()
    // Width-sensitive: a wrong tag (e.g. treating as 2xfloat32) would misread the
    // returned values. Values chosen so x+y != 0 and are distinguishable.
    // DEVICE-VALIDATED 2026-06-12 (iPhone 12, iOS 26.5) — Phase 2.1
    {
        int64_t sprite2d = kanama_ios_godot_construct_object("Sprite2D");
        if (sprite2d == 0) {
            fprintf(stderr, "[kanama][ios][c] SELFTEST note: Sprite2D construct returned 0\n");
            fflush(stderr);
        }
        // Godot rejects frame_coords outside [0,hframes)x[0,vframes) (defaults 1x1)
        // via ERR_FAIL_INDEX, so widen the frame grid first or (3,7) is a no-op
        // and the row would report a false ABI failure.
        int64_t hframes = 4;
        int64_t vframes = 8;
        const void *fa[1] = { &hframes };
        int32_t ft[1] = { KANAMA_IOS_PT_INT64 };
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("Sprite2D","set_hframes",1286410249),
            sprite2d, ft, fa, 1, KANAMA_IOS_PT_VOID, NULL);
        fa[0] = &vframes;
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("Sprite2D","set_vframes",1286410249),
            sprite2d, ft, fa, 1, KANAMA_IOS_PT_VOID, NULL);
        int32_t in[2] = { 3, 7 };
        const void *a[1] = { in };
        int32_t t[1] = { KANAMA_IOS_PT_VECTOR2I };
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("Sprite2D","set_frame_coords",1130785943),
            sprite2d, t, a, 1, KANAMA_IOS_PT_VOID, NULL);
        int32_t out[2] = { 0, 0 };
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("Sprite2D","get_frame_coords",3690982128),
            sprite2d, NULL, NULL, 0, KANAMA_IOS_PT_VECTOR2I, out);
        KANAMA_IOS_ST_CHECK("vector2i(3,7)", out[0]==3 && out[1]==7);
    }

    // Vector3i (3x int32, 12B): PlaceholderTexture3D.set_size(Vector3i(5,11,17)) -> get_size()
    // Width-sensitive: a wrong tag would misread the 12-byte struct. Values chosen
    // to be individually distinguishable and non-trivially zero.
    // DEVICE-VALIDATED 2026-06-12 (iPhone 12, iOS 26.5) — Phase 2.1
    {
        int64_t tex3d = kanama_ios_godot_construct_object("PlaceholderTexture3D");
        if (tex3d == 0) {
            fprintf(stderr, "[kanama][ios][c] SELFTEST note: PlaceholderTexture3D construct returned 0\n");
            fflush(stderr);
        }
        int32_t in[3] = { 5, 11, 17 };
        const void *a[1] = { in };
        int32_t t[1] = { KANAMA_IOS_PT_VECTOR3I };
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("PlaceholderTexture3D","set_size",560364750),
            tex3d, t, a, 1, KANAMA_IOS_PT_VOID, NULL);
        int32_t out[3] = { 0, 0, 0 };
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("PlaceholderTexture3D","get_size",2785653706),
            tex3d, NULL, NULL, 0, KANAMA_IOS_PT_VECTOR3I, out);
        KANAMA_IOS_ST_CHECK("vector3i(5,11,17)", out[0]==5 && out[1]==11 && out[2]==17);
    }

    // Rect2 (4x f32, 16B): GPUParticles2D.set_visibility_rect -> get_visibility_rect()
    // Validation-free: visibility_rect is a stored Rect2 with no engine clamping.
    // Values 1.5/2.5/3.5/4.5 are exact in float32 (n/2 pattern).
    // Width-sensitive: a wrong tag would misread the 16-byte struct.
    // DEVICE-VALIDATED 2026-06-12 (iPhone 12, iOS 26.5) — Phase 2.2
    {
        int64_t gp2d = kanama_ios_godot_construct_object("GPUParticles2D");
        if (gp2d == 0) {
            fprintf(stderr, "[kanama][ios][c] SELFTEST note: GPUParticles2D construct returned 0\n");
            fflush(stderr);
        }
        float in[4] = { 1.5f, 2.5f, 3.5f, 4.5f };
        const void *a[1] = { in };
        int32_t t[1] = { KANAMA_IOS_PT_RECT2 };
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("GPUParticles2D","set_visibility_rect",2046264180),
            gp2d, t, a, 1, KANAMA_IOS_PT_VOID, NULL);
        float out[4] = { 0, 0, 0, 0 };
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("GPUParticles2D","get_visibility_rect",1639390495),
            gp2d, NULL, NULL, 0, KANAMA_IOS_PT_RECT2, out);
        KANAMA_IOS_ST_CHECK("rect2(1.5,2.5,3.5,4.5)", out[0]==1.5f && out[1]==2.5f && out[2]==3.5f && out[3]==4.5f);
    }

    // String-return: Object.get_class(Node2D) -> "Node2D". Validation-free and
    // deterministic; exercises the dedicated String-return marshalling path
    // (ptrcall -> string_to_utf8_chars -> destruct). get_class is an Object
    // method resolved on the Node2D instance via inheritance.
    // DEVICE-VALIDATED 2026-06-12 (iPhone 12, iOS 26.5) — Phase 2.3a
    {
        int64_t node2d_str = kanama_ios_godot_construct_object("Node2D");
        if (node2d_str == 0) {
            fprintf(stderr, "[kanama][ios][c] SELFTEST note: Node2D construct returned 0\n");
            fflush(stderr);
        }
        char class_buf[64];
        int64_t class_len = kanama_ios_godot_ptrcall_no_args_ret_string(
            kanama_ios_godot_get_method_bind("Object", "get_class", 201670096),
            node2d_str, class_buf, (int64_t)sizeof(class_buf));
        KANAMA_IOS_ST_CHECK("string-ret get_class==Node2D",
            class_len == 6 && strncmp(class_buf, "Node2D", 6) == 0);
    }

    // Virtual String-RETURN marshalling (task 13 — non-POD virtual returns). A value-returning
    // virtual (e.g. Object._to_string) whose Kotlin result is a String hands it back to the engine
    // via kanama_ios_pt_return_to_variant: the PT return scratch holds a char* pointer (NOT inline
    // bytes), the helper builds a Godot String Variant and destroys the temporary. Width-sensitive:
    // the probe string is >32 bytes so a regression that truncated to the fixed ret_buf, or treated
    // the buffer as inline chars, fails here. Read the Variant back to utf8 and compare.
    {
        const char *long_str =
            "kanama-virtual-string-return-selftest-0123456789-abcdefghijklmnopqrstuvwxyz";
        uint8_t rb[32];
        memset(rb, 0, sizeof(rb));
        *(const char **)rb = long_str;
        uint8_t ret_variant[24];
        memset(ret_variant, 0, sizeof(ret_variant));
        kanama_ios_pt_return_to_variant(KANAMA_IOS_PT_STRING, rb, ret_variant);
        uint64_t raw_str = 0;
        char *utf8 = NULL;
        if (g_variant_to_string != NULL) {
            g_variant_to_string(&raw_str, (GDExtensionVariantPtr)ret_variant);
            utf8 = kanama_ios_string_to_utf8_dup((GDExtensionConstStringPtr)&raw_str);
            if (g_string_destructor != NULL) {
                g_string_destructor((GDExtensionStringPtr)&raw_str);
            }
        }
        KANAMA_IOS_ST_CHECK("virtual-string-ret roundtrip(>32B)",
            utf8 != NULL && strcmp(utf8, long_str) == 0);
        free(utf8);
        if (g_variant_destroy != NULL) {
            g_variant_destroy((GDExtensionVariantPtr)ret_variant);
        }
    }

    // StringName-return: Node.set_name("KanamaSN") -> get_name(). Exercises the
    // StringName ptrcall return plus the StringName->String constructor hop. The
    // PT_STRING_NAME arg path constructs the StringName C-side from the C string.
    // DEVICE-VALIDATED 2026-06-13 (iPhone 12, iOS 26.5) — Phase 2.3b
    {
        int64_t node_sn = kanama_ios_godot_construct_object("Node");
        if (node_sn == 0) {
            fprintf(stderr, "[kanama][ios][c] SELFTEST note: Node construct returned 0\n");
            fflush(stderr);
        }
        const char *sn_name = "KanamaSN";
        const void *na[1] = { sn_name };
        int32_t nt[1] = { KANAMA_IOS_PT_STRING_NAME };
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("Node", "set_name", 3304788590),
            node_sn, nt, na, 1, KANAMA_IOS_PT_VOID, NULL);
        char name_buf[64];
        int64_t name_len = kanama_ios_godot_ptrcall_no_args_ret_string_name(
            kanama_ios_godot_get_method_bind("Node", "get_name", 2002593661),
            node_sn, name_buf, (int64_t)sizeof(name_buf));
        KANAMA_IOS_ST_CHECK("string-name-ret get_name==KanamaSN",
            name_len == 8 && strncmp(name_buf, "KanamaSN", 8) == 0);
    }

    // NodePath-return: GPUParticles2D.set_sub_emitter("../Emitter") -> get_sub_emitter().
    // Exercises the NodePath ptrcall return plus the NodePath->String constructor hop (the
    // PT_NODE_PATH arg path constructs the NodePath C-side from the C string). Phase 2.7b.
    {
        int64_t gp_np = kanama_ios_godot_construct_object("GPUParticles2D");
        const char *np_path = "../Emitter";
        const void *npa[1] = { np_path };
        int32_t npt[1] = { KANAMA_IOS_PT_NODE_PATH };
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("GPUParticles2D", "set_sub_emitter", 1348162250),
            gp_np, npt, npa, 1, KANAMA_IOS_PT_VOID, NULL);
        char np_buf[64];
        int64_t np_len = kanama_ios_godot_ptrcall_no_args_ret_node_path(
            kanama_ios_godot_get_method_bind("GPUParticles2D", "get_sub_emitter", 4075236667),
            gp_np, np_buf, (int64_t)sizeof(np_buf));
        KANAMA_IOS_ST_CHECK("node-path-ret get_sub_emitter==../Emitter",
            np_len == 10 && strncmp(np_buf, "../Emitter", 10) == 0);
    }

    // PackedInt32Array-return: MeshLibrary.create_item(7)/create_item(11) -> get_item_list()
    // returns [7, 11] (the item-id map is sorted ascending). Exercises the PackedInt32Array
    // ptrcall return end-to-end (size builtin + operator_index_const + destructor, two-call
    // length protocol) with known, distinct, non-zero element values. MeshLibrary is a plain
    // Resource — safe to construct at extension-init time (no server/tree dependency), unlike
    // the only EMITTED PackedInt32Array getter (CollisionObject2D/3D.get_shape_owners), whose
    // physics-body instance segfaults in its PhysicsServer-touching constructor this early.
    // The buffer is prefilled with a -1 sentinel so real element writes are distinguishable
    // from an untouched buffer. Phase 2.7c.
    {
        int64_t ml = kanama_ios_godot_construct_object("MeshLibrary");
        int64_t bind_create = kanama_ios_godot_get_method_bind("MeshLibrary", "create_item", 1286410249);
        int64_t id7 = 7;
        int64_t id11 = 11;
        int32_t it[1] = { KANAMA_IOS_PT_INT64 };
        const void *a7[1] = { &id7 };
        kanama_ios_godot_ptrcall(bind_create, ml, it, a7, 1, KANAMA_IOS_PT_VOID, NULL);
        const void *a11[1] = { &id11 };
        kanama_ios_godot_ptrcall(bind_create, ml, it, a11, 1, KANAMA_IOS_PT_VOID, NULL);
        int32_t items[8];
        for (int i = 0; i < 8; i++) { items[i] = -1; }
        int64_t item_count = kanama_ios_godot_ptrcall_no_args_ret_packed_int32_array(
            kanama_ios_godot_get_method_bind("MeshLibrary", "get_item_list", 1930428628),
            ml, items, 8);
        KANAMA_IOS_ST_CHECK("packed-int32-ret get_item_list==[7,11]",
            item_count == 2 && items[0] == 7 && items[1] == 11);
    }

    // Typed-object-array return: a fresh parent Node with two child Nodes -> get_children(false)
    // returns [child0, child1] in add order. Exercises the Array[Object] ptrcall return end-to-end
    // (8-byte Array opaque + size/get builtins + variant_to_object, two-call length protocol)
    // driven through the generic dispatcher's bool arg. Plain Node is safe to construct at
    // extension-init time (no tree/server dependency), unlike the physics-body get_overlapping_*
    // getters whose instances segfault this early. The buffer is prefilled with a -1 sentinel so
    // real handle writes are distinguishable from an untouched slot. Phase 2.7d.
    {
        int64_t parent = kanama_ios_godot_construct_object("Node");
        int64_t child0 = kanama_ios_godot_construct_object("Node");
        int64_t child1 = kanama_ios_godot_construct_object("Node");
        int64_t bind_add = kanama_ios_godot_get_method_bind("Node", "add_child", 3863233950);
        uint8_t force_readable = 0;
        int64_t internal_mode = 0; // enum arg — scalar int, 8 bytes at ptrcall
        int32_t act[3] = { KANAMA_IOS_PT_OBJECT, KANAMA_IOS_PT_BOOL, KANAMA_IOS_PT_INT64 };
        int64_t c0 = child0;
        const void *a0[3] = { &c0, &force_readable, &internal_mode };
        kanama_ios_godot_ptrcall(bind_add, parent, act, a0, 3, KANAMA_IOS_PT_VOID, NULL);
        int64_t c1 = child1;
        const void *a1[3] = { &c1, &force_readable, &internal_mode };
        kanama_ios_godot_ptrcall(bind_add, parent, act, a1, 3, KANAMA_IOS_PT_VOID, NULL);

        uint8_t include_internal = 0;
        int32_t gct[1] = { KANAMA_IOS_PT_BOOL };
        const void *gca[1] = { &include_internal };
        int64_t kids[8];
        for (int i = 0; i < 8; i++) { kids[i] = -1; }
        int64_t kid_count = kanama_ios_godot_ptrcall_ret_object_array(
            kanama_ios_godot_get_method_bind("Node", "get_children", 873284517),
            parent, gct, gca, 1, kids, 8);
        KANAMA_IOS_ST_CHECK("typed-object-array-ret get_children==[c0,c1]",
            kid_count == 2 && kids[0] == child0 && kids[1] == child1);
    }

    // (String,String,bool,bool)->Array[Node] return: a parent with two "Coin*"-named children plus
    // one "Door" child; find_children("Coin*", "", recursive=true, owned=false) returns the two coins
    // in tree order. Exercises ret_object_array with a PT_STRING x2 + PT_BOOL x2 arg layout — each
    // Godot String is built C-side from the cstr (and destroyed) by the generic dispatcher, then the
    // same Array read-back runs. owned=false so the unowned (no SceneTree owner) children still match;
    // the "Door" child must be excluded by the name pattern. Plain Node is safe at init. Phase 2.7d-3.
    {
        int64_t parent = kanama_ios_godot_construct_object("Node");
        int64_t bind_add = kanama_ios_godot_get_method_bind("Node", "add_child", 3863233950);
        int64_t bind_set_name = kanama_ios_godot_get_method_bind("Node", "set_name", 3304788590);
        uint8_t force_readable = 0;
        int64_t internal_mode = 0;
        int32_t act[3] = { KANAMA_IOS_PT_OBJECT, KANAMA_IOS_PT_BOOL, KANAMA_IOS_PT_INT64 };
        int32_t snt[1] = { KANAMA_IOS_PT_STRING_NAME };

        int64_t coin0 = kanama_ios_godot_construct_object("Node");
        const char *coin0_name = "Coin0";
        const void *coin0_na[1] = { coin0_name };
        kanama_ios_godot_ptrcall(bind_set_name, coin0, snt, coin0_na, 1, KANAMA_IOS_PT_VOID, NULL);
        int64_t coin1 = kanama_ios_godot_construct_object("Node");
        const char *coin1_name = "Coin1";
        const void *coin1_na[1] = { coin1_name };
        kanama_ios_godot_ptrcall(bind_set_name, coin1, snt, coin1_na, 1, KANAMA_IOS_PT_VOID, NULL);
        int64_t door = kanama_ios_godot_construct_object("Node");
        const char *door_name = "Door";
        const void *door_na[1] = { door_name };
        kanama_ios_godot_ptrcall(bind_set_name, door, snt, door_na, 1, KANAMA_IOS_PT_VOID, NULL);

        int64_t c0 = coin0;
        const void *a0[3] = { &c0, &force_readable, &internal_mode };
        kanama_ios_godot_ptrcall(bind_add, parent, act, a0, 3, KANAMA_IOS_PT_VOID, NULL);
        int64_t c1 = coin1;
        const void *a1[3] = { &c1, &force_readable, &internal_mode };
        kanama_ios_godot_ptrcall(bind_add, parent, act, a1, 3, KANAMA_IOS_PT_VOID, NULL);
        int64_t d0 = door;
        const void *ad[3] = { &d0, &force_readable, &internal_mode };
        kanama_ios_godot_ptrcall(bind_add, parent, act, ad, 3, KANAMA_IOS_PT_VOID, NULL);

        const char *pattern = "Coin*";
        const char *type_filter = "";
        uint8_t recursive = 1;
        uint8_t owned = 0;
        int32_t fct[4] = { KANAMA_IOS_PT_STRING, KANAMA_IOS_PT_STRING, KANAMA_IOS_PT_BOOL, KANAMA_IOS_PT_BOOL };
        const void *fca[4] = { pattern, type_filter, &recursive, &owned };
        int64_t found[8];
        for (int i = 0; i < 8; i++) { found[i] = -1; }
        int64_t found_count = kanama_ios_godot_ptrcall_ret_object_array(
            kanama_ios_godot_get_method_bind("Node", "find_children", 2560337219),
            parent, fct, fca, 4, found, 8);
        KANAMA_IOS_ST_CHECK("typed-object-array-ret find_children(Coin*)==[c0,c1]",
            found_count == 2 && found[0] == coin0 && found[1] == coin1);
    }

    // PackedFloat32Array-return: a freshly constructed Gradient seeds two default color
    // points at offsets 0.0 and 1.0 (set in the C++ constructor), so get_offsets() == [0.0,
    // 1.0]. Exercises the float32 read-back (size builtin + packed_float32 operator_index_const
    // + 16-byte storage). Gradient is a plain Resource — safe to construct at init. The 1.0 is
    // a real non-zero float read; -1 sentinel prefill proves the writes. Phase 2.7c-2.
    {
        int64_t grad = kanama_ios_godot_construct_object("Gradient");
        float offsets[8];
        for (int i = 0; i < 8; i++) { offsets[i] = -1.0f; }
        int64_t off_count = kanama_ios_godot_ptrcall_no_args_ret_packed_float32_array(
            kanama_ios_godot_get_method_bind("Gradient", "get_offsets", 675695659),
            grad, offsets, 8);
        KANAMA_IOS_ST_CHECK("packed-float32-ret get_offsets==[0,1]",
            off_count == 2 && offsets[0] == 0.0f && offsets[1] == 1.0f);
    }

    // PackedFloat32Array-ARG: Gradient.set_offsets([0.25,0.5,0.75]) built from a flat float
    // buffer (constructor + push_back per element), then get_offsets() reads them back.
    // Exercises the build-from-list arg path end to end on a safe Resource (the emitted target
    // Label.set_tab_stops can't anchor — treeless Control segfault). Phase 2.7c-4.
    {
        int64_t grad_a = kanama_ios_godot_construct_object("Gradient");
        float in_offsets[3] = { 0.25f, 0.5f, 0.75f };
        kanama_ios_godot_ptrcall_with_packed_float32_arg(
            kanama_ios_godot_get_method_bind("Gradient", "set_offsets", 2899603908),
            grad_a, in_offsets, 3);
        float back[8];
        for (int i = 0; i < 8; i++) { back[i] = -1.0f; }
        int64_t back_count = kanama_ios_godot_ptrcall_no_args_ret_packed_float32_array(
            kanama_ios_godot_get_method_bind("Gradient", "get_offsets", 675695659),
            grad_a, back, 8);
        KANAMA_IOS_ST_CHECK("packed-float32-arg set/get_offsets==[0.25,0.5,0.75]",
            back_count == 3 && back[0] == 0.25f && back[1] == 0.5f && back[2] == 0.75f);
    }

    // PackedVector2Array-return: Line2D.add_point((1.5,2.5))/add_point((3.5,4.5)) ->
    // get_points() == [(1.5,2.5),(3.5,4.5)]. Exercises the Vector2 element read-back (2
    // float32 per element via packed_vector2 operator_index_const). Line2D is a Node2D —
    // safe to construct at init (CanvasItem, like the other Node2D rows). Phase 2.7c-3.
    {
        int64_t line = kanama_ios_godot_construct_object("Line2D");
        int64_t bind_add = kanama_ios_godot_get_method_bind("Line2D", "add_point", 2654014372);
        float p0[2] = { 1.5f, 2.5f };
        float p1[2] = { 3.5f, 4.5f };
        int64_t at_end = -1;
        int32_t at[2] = { KANAMA_IOS_PT_VECTOR2, KANAMA_IOS_PT_INT64 };
        const void *a0[2] = { p0, &at_end };
        kanama_ios_godot_ptrcall(bind_add, line, at, a0, 2, KANAMA_IOS_PT_VOID, NULL);
        const void *a1[2] = { p1, &at_end };
        kanama_ios_godot_ptrcall(bind_add, line, at, a1, 2, KANAMA_IOS_PT_VOID, NULL);
        float pts[16];
        for (int i = 0; i < 16; i++) { pts[i] = -1.0f; }
        int64_t pt_count = kanama_ios_godot_ptrcall_no_args_ret_packed_vector2_array(
            kanama_ios_godot_get_method_bind("Line2D", "get_points", 2961356807), line, pts, 8);
        KANAMA_IOS_ST_CHECK("packed-vector2-ret get_points==[(1.5,2.5),(3.5,4.5)]",
            pt_count == 2 && pts[0] == 1.5f && pts[1] == 2.5f && pts[2] == 3.5f && pts[3] == 4.5f);
    }

    // PackedColorArray-return: a fresh Gradient seeds default colors black (0,0,0,1) and
    // white (1,1,1,1), so get_colors() == [black, white]. Exercises the Color element
    // read-back (4 float32 per element via packed_color operator_index_const). Phase 2.7c-3.
    {
        int64_t grad_c = kanama_ios_godot_construct_object("Gradient");
        float cols[16];
        for (int i = 0; i < 16; i++) { cols[i] = -1.0f; }
        int64_t col_count = kanama_ios_godot_ptrcall_no_args_ret_packed_color_array(
            kanama_ios_godot_get_method_bind("Gradient", "get_colors", 1392750486), grad_c, cols, 4);
        KANAMA_IOS_ST_CHECK("packed-color-ret get_colors==[black,white]",
            col_count == 2 &&
            cols[0] == 0.0f && cols[1] == 0.0f && cols[2] == 0.0f && cols[3] == 1.0f &&
            cols[4] == 1.0f && cols[5] == 1.0f && cols[6] == 1.0f && cols[7] == 1.0f);
    }

    // PackedStringArray-return: Translation.add_message("hello"/"bye") -> get_message_list()
    // returns the source keys. Exercises the VARIABLE-LENGTH string read-back (per-element
    // operator_index_const String ptr -> utf8, length-prefixed blob). Translation is a plain
    // Resource — safe at init. Order-independent: decode the blob and check both keys present
    // (the message map iteration order isn't guaranteed). Phase 2.7c-5.
    {
        int64_t tr = kanama_ios_godot_construct_object("Translation");
        int64_t bind_add = kanama_ios_godot_get_method_bind("Translation", "add_message", 3898530326);
        const char *empty = "";
        int32_t mt[3] = { KANAMA_IOS_PT_STRING_NAME, KANAMA_IOS_PT_STRING_NAME, KANAMA_IOS_PT_STRING_NAME };
        const char *hello = "hello";
        const void *ma0[3] = { hello, "bonjour", empty };
        kanama_ios_godot_ptrcall(bind_add, tr, mt, ma0, 3, KANAMA_IOS_PT_VOID, NULL);
        const char *bye = "bye";
        const void *ma1[3] = { bye, "au revoir", empty };
        kanama_ios_godot_ptrcall(bind_add, tr, mt, ma1, 3, KANAMA_IOS_PT_VOID, NULL);

        char blob[256];
        int64_t total = kanama_ios_godot_ptrcall_no_args_ret_packed_string_array(
            kanama_ios_godot_get_method_bind("Translation", "get_message_list", 1139954409),
            tr, blob, (int64_t)sizeof(blob));
        // Decode [count][len0][utf8_0][len1][utf8_1]... and check both keys are present.
        int found_hello = 0;
        int found_bye = 0;
        int32_t msg_count = 0;
        if (total >= 4) {
            memcpy(&msg_count, blob, 4);
            int64_t off = 4;
            for (int32_t i = 0; i < msg_count && off + 4 <= total; i++) {
                int32_t len = 0;
                memcpy(&len, blob + off, 4);
                off += 4;
                if (len == 5 && off + 5 <= total && memcmp(blob + off, "hello", 5) == 0) {
                    found_hello = 1;
                }
                if (len == 3 && off + 3 <= total && memcmp(blob + off, "bye", 3) == 0) {
                    found_bye = 1;
                }
                off += len;
            }
        }
        KANAMA_IOS_ST_CHECK("packed-string-ret get_message_list has hello+bye",
            msg_count == 2 && found_hello && found_bye);
    }

    // PackedVector2Array-ARG via the GENERIC dispatch (PT_PACKED_VECTOR2_ARRAY build path):
    // Line2D.set_points([(1.5,2.5),(3.5,4.5)]) built from a descriptor, then get_points reads
    // it back. Validates the dispatch's inline packed-arg building (the machinery the multi-arg
    // CanvasItem.draw_* methods ride on). Line2D is a Node2D, safe at init. Phase 2.7c-6a.
    {
        int64_t line2 = kanama_ios_godot_construct_object("Line2D");
        float pts_in[4] = { 1.5f, 2.5f, 3.5f, 4.5f };
        KanamaIosPackedArgDesc pdesc = { 2, pts_in };
        const void *pa[1] = { &pdesc };
        int32_t pt[1] = { KANAMA_IOS_PT_PACKED_VECTOR2_ARRAY };
        kanama_ios_godot_ptrcall(
            kanama_ios_godot_get_method_bind("Line2D", "set_points", 1509147220),
            line2, pt, pa, 1, KANAMA_IOS_PT_VOID, NULL);
        float pts_out[16];
        for (int i = 0; i < 16; i++) { pts_out[i] = -1.0f; }
        int64_t pn = kanama_ios_godot_ptrcall_no_args_ret_packed_vector2_array(
            kanama_ios_godot_get_method_bind("Line2D", "get_points", 2961356807), line2, pts_out, 8);
        KANAMA_IOS_ST_CHECK("packed-vector2-arg set/get_points==[(1.5,2.5),(3.5,4.5)]",
            pn == 2 && pts_out[0] == 1.5f && pts_out[1] == 2.5f &&
            pts_out[2] == 3.5f && pts_out[3] == 4.5f);
    }

    // PackedColorArray-ARG via the generic dispatch: a fresh Gradient has 2 points, so
    // set_colors([red, green]) matches; get_colors reads them back. Phase 2.7c-6a.
    {
        int64_t grad2 = kanama_ios_godot_construct_object("Gradient");
        float cols_in[8] = { 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f };  // red, green
        KanamaIosPackedArgDesc cdesc = { 2, cols_in };
        const void *ca[1] = { &cdesc };
        int32_t ct[1] = { KANAMA_IOS_PT_PACKED_COLOR_ARRAY };
        kanama_ios_godot_ptrcall(
            kanama_ios_godot_get_method_bind("Gradient", "set_colors", 3546319833),
            grad2, ct, ca, 1, KANAMA_IOS_PT_VOID, NULL);
        float cols_out[16];
        for (int i = 0; i < 16; i++) { cols_out[i] = -1.0f; }
        int64_t cn = kanama_ios_godot_ptrcall_no_args_ret_packed_color_array(
            kanama_ios_godot_get_method_bind("Gradient", "get_colors", 1392750486), grad2, cols_out, 4);
        KANAMA_IOS_ST_CHECK("packed-color-arg set/get_colors==[red,green]",
            cn == 2 &&
            cols_out[0] == 1.0f && cols_out[1] == 0.0f && cols_out[2] == 0.0f && cols_out[3] == 1.0f &&
            cols_out[4] == 0.0f && cols_out[5] == 1.0f && cols_out[6] == 0.0f && cols_out[7] == 1.0f);
    }

    // String arg: Node.set_scene_file_path("HelloKanama") -> get_scene_file_path()
    // round-trip. Exercises PT_STRING construction (init_string from the C string),
    // distinct from the PT_STRING_NAME path; read-back reuses the wired String-return
    // helper. Node (not a Control) is used deliberately: constructing a treeless
    // Control here segfaults in its post-init Theme lookup (ThemeContext has no themes
    // outside a Window) — unrelated to String marshalling.
    // DEVICE-VALIDATED 2026-06-13 (iPhone 12, iOS 26.5) — Phase 2.4
    {
        int64_t snode = kanama_ios_godot_construct_object("Node");
        const char *text_in = "HelloKanama";
        const void *ta[1] = { text_in };
        int32_t tt[1] = { KANAMA_IOS_PT_STRING };
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("Node", "set_scene_file_path", 83702148),
            snode, tt, ta, 1, KANAMA_IOS_PT_VOID, NULL);
        char text_buf[64];
        int64_t text_len = kanama_ios_godot_ptrcall_no_args_ret_string(
            kanama_ios_godot_get_method_bind("Node", "get_scene_file_path", 201670096),
            snode, text_buf, (int64_t)sizeof(text_buf));
        KANAMA_IOS_ST_CHECK("string-arg set/get_scene_file_path==HelloKanama",
            text_len == 11 && strncmp(text_buf, "HelloKanama", 11) == 0);
    }

    // NodePath arg: parent.add_child(child named "KPChild"); then
    // parent.get_node_or_null(NodePath("KPChild")) must return the child. Exercises
    // PT_NODE_PATH construction (init_node_path) — the path string must round-trip
    // through NodePath for the lookup to resolve. get_node_or_null searches own
    // children, so no SceneTree membership is required.
    // DEVICE-VALIDATED 2026-06-13 (iPhone 12, iOS 26.5) — Phase 2.4
    {
        int64_t np_parent = kanama_ios_godot_construct_object("Node");
        int64_t np_child = kanama_ios_godot_construct_object("Node");
        const char *child_name = "KPChild";
        const void *cna[1] = { child_name };
        int32_t cnt[1] = { KANAMA_IOS_PT_STRING_NAME };
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("Node", "set_name", 3304788590),
            np_child, cnt, cna, 1, KANAMA_IOS_PT_VOID, NULL);
        int64_t child_cell = np_child;
        uint8_t force_readable = 0;
        int64_t internal_mode = 0;
        const void *aca[3] = { &child_cell, &force_readable, &internal_mode };
        int32_t act[3] = { KANAMA_IOS_PT_OBJECT, KANAMA_IOS_PT_BOOL, KANAMA_IOS_PT_INT64 };
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("Node", "add_child", 3863233950),
            np_parent, act, aca, 3, KANAMA_IOS_PT_VOID, NULL);
        const char *np = "KPChild";
        const void *npa[1] = { np };
        int32_t npt[1] = { KANAMA_IOS_PT_NODE_PATH };
        int64_t got_node = 0;
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("Node", "get_node_or_null", 2734337346),
            np_parent, npt, npa, 1, KANAMA_IOS_PT_OBJECT, &got_node);
        KANAMA_IOS_ST_CHECK("nodepath-arg get_node_or_null==child", got_node == np_child);
    }

    // Transform3D arg+return: Node3D.set_transform(T) -> get_transform(). 12x float32
    // (9 column-major basis + 3 origin), POD passthrough. Diagonal basis (2,4,0.5) is a
    // pure scale and all components are exact in float32, so equality is stable.
    // DEVICE-VALIDATED 2026-06-13 (iPhone 12, iOS 26.5) — Phase 2.5
    {
        int64_t n3t = kanama_ios_godot_construct_object("Node3D");
        float tin[12] = { 2.0f, 0, 0, 0, 4.0f, 0, 0, 0, 0.5f, 1.25f, 2.5f, 4.75f };
        const void *ta[1] = { tin };
        int32_t tt[1] = { KANAMA_IOS_PT_TRANSFORM3D };
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("Node3D", "set_transform", 2952846383),
            n3t, tt, ta, 1, KANAMA_IOS_PT_VOID, NULL);
        float tout[12] = { 0 };
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("Node3D", "get_transform", 3229777777),
            n3t, NULL, NULL, 0, KANAMA_IOS_PT_TRANSFORM3D, tout);
        int t_ok = 1;
        for (int k = 0; k < 12; k++) {
            if (tout[k] != tin[k]) { t_ok = 0; }
        }
        KANAMA_IOS_ST_CHECK("transform3d set/get_transform round-trip", t_ok);
    }

    // Basis arg+return: Node3D.set_basis(B) -> get_basis(). 9x float32 column-major,
    // POD passthrough. Diagonal (0.5,8,0.25) — pure scale, exact in float32.
    // DEVICE-VALIDATED 2026-06-13 (iPhone 12, iOS 26.5) — Phase 2.5
    {
        int64_t n3b = kanama_ios_godot_construct_object("Node3D");
        float bin[9] = { 0.5f, 0, 0, 0, 8.0f, 0, 0, 0, 0.25f };
        const void *ba[1] = { bin };
        int32_t bt[1] = { KANAMA_IOS_PT_BASIS };
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("Node3D", "set_basis", 1055510324),
            n3b, bt, ba, 1, KANAMA_IOS_PT_VOID, NULL);
        float bout[9] = { 0 };
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("Node3D", "get_basis", 2716978435),
            n3b, NULL, NULL, 0, KANAMA_IOS_PT_BASIS, bout);
        int b_ok = 1;
        for (int k = 0; k < 9; k++) {
            if (bout[k] != bin[k]) { b_ok = 0; }
        }
        KANAMA_IOS_ST_CHECK("basis set/get_basis round-trip", b_ok);
    }

    // Transform2D arg+return: Node2D.set_transform(T) -> get_transform(). 6x float32
    // (columns x, y, origin — each Vector2), POD passthrough. get_transform is declared on
    // CanvasItem but Node2D overrides it to return the local _transform, so it round-trips.
    // x=(2,0) y=(0,4) origin=(1.25,2.5) — all exact in float32; the asymmetric layout also
    // catches a wrong component order. Phase 2.7a.
    {
        int64_t n2 = kanama_ios_godot_construct_object("Node2D");
        float t2in[6] = { 2.0f, 0, 0, 4.0f, 1.25f, 2.5f };
        const void *t2a[1] = { t2in };
        int32_t t2t[1] = { KANAMA_IOS_PT_TRANSFORM2D };
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("Node2D", "set_transform", 2761652528),
            n2, t2t, t2a, 1, KANAMA_IOS_PT_VOID, NULL);
        float t2out[6] = { 0 };
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("CanvasItem", "get_transform", 3814499831),
            n2, NULL, NULL, 0, KANAMA_IOS_PT_TRANSFORM2D, t2out);
        int t2_ok = 1;
        for (int k = 0; k < 6; k++) {
            if (t2out[k] != t2in[k]) { t2_ok = 0; }
        }
        KANAMA_IOS_ST_CHECK("transform2d set/get_transform round-trip", t2_ok);
    }

    // Callable ptrcall arg (Phase 1.4 iOS Callable-args): build object+method Callables and pass them
    // through the PT_CALLABLE ptrcall path. Control has a lightweight constructor (safe at SCENE-init,
    // unlike TextEdit which pulls in the TextServer), and set_drag_forwarding(drag, can_drop, drop)
    // takes three Callables + void return — exercising the multi-cell layout (three PT_CALLABLE cells +
    // three method-name StringName cells built and destroyed in one call). Each Callable targets the
    // Control itself with a valid Object method ("get_class"). Fire-later sink with no getter, so the
    // row asserts construct -> ptrcall -> destroy round-trips without corrupting the heap.
    {
        int64_t ctrl = kanama_ios_godot_construct_object("Control");
        int64_t bind = kanama_ios_godot_get_method_bind("Control", "set_drag_forwarding", 1076571380);
        KanamaIosCallableArgDesc d0 = { ctrl, "get_class" };
        KanamaIosCallableArgDesc d1 = { ctrl, "get_class" };
        KanamaIosCallableArgDesc d2 = { ctrl, "get_class" };
        const void *ca[3] = { &d0, &d1, &d2 };
        int32_t ct[3] = { KANAMA_IOS_PT_CALLABLE, KANAMA_IOS_PT_CALLABLE, KANAMA_IOS_PT_CALLABLE };
        kanama_ios_godot_ptrcall(bind, ctrl, ct, ca, 3, KANAMA_IOS_PT_VOID, NULL);
        KANAMA_IOS_ST_CHECK("callable ptrcall args x3: Control.set_drag_forwarding", ctrl != 0 && bind != 0);
        kanama_ios_godot_object_queue_free(ctrl);
    }

    // RID arg+return: a GPUParticles3D auto-assigns a particles base RID. Read it
    // (get_base), set it back (set_base), read again — round-trips the uint64 through
    // PT_RID arg + return. RID is POD passthrough (8 bytes, like an Object handle).
    // DEVICE-VALIDATED 2026-06-13 (iPhone 12, iOS 26.5) — RID/Quaternion/AABB kinds
    {
        int64_t gp_rid = kanama_ios_godot_construct_object("GPUParticles3D");
        uint64_t rid1 = 0;
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("VisualInstance3D", "get_base", 2944877500),
            gp_rid, NULL, NULL, 0, KANAMA_IOS_PT_RID, &rid1);
        const void *ra[1] = { &rid1 };
        int32_t rt[1] = { KANAMA_IOS_PT_RID };
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("VisualInstance3D", "set_base", 2722037293),
            gp_rid, rt, ra, 1, KANAMA_IOS_PT_VOID, NULL);
        uint64_t rid2 = 0;
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("VisualInstance3D", "get_base", 2944877500),
            gp_rid, NULL, NULL, 0, KANAMA_IOS_PT_RID, &rid2);
        KANAMA_IOS_ST_CHECK("rid get/set_base round-trip", rid1 != 0 && rid2 == rid1);
    }

    // AABB arg+return: GPUParticles3D.set_custom_aabb -> get_custom_aabb. 6x float32
    // (position xyz + size xyz), stored verbatim. Values exact in float32.
    // DEVICE-VALIDATED 2026-06-13 (iPhone 12, iOS 26.5) — RID/Quaternion/AABB kinds
    {
        int64_t gp_aabb = kanama_ios_godot_construct_object("GPUParticles3D");
        float ain[6] = { 1.5f, 2.5f, 3.5f, 4.5f, 5.5f, 6.5f };
        const void *aa[1] = { ain };
        int32_t at[1] = { KANAMA_IOS_PT_AABB };
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("GeometryInstance3D", "set_custom_aabb", 259215842),
            gp_aabb, at, aa, 1, KANAMA_IOS_PT_VOID, NULL);
        float aout[6] = { 0 };
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("GeometryInstance3D", "get_custom_aabb", 1068685055),
            gp_aabb, NULL, NULL, 0, KANAMA_IOS_PT_AABB, aout);
        int a_ok = 1;
        for (int k = 0; k < 6; k++) {
            if (aout[k] != ain[k]) { a_ok = 0; }
        }
        KANAMA_IOS_ST_CHECK("aabb set/get_custom_aabb round-trip", a_ok);
    }

    // Quaternion arg+return: Node3D.set_quaternion -> get_quaternion. 4x float32
    // [x,y,z,w]. Godot stores rotation as a basis and re-derives the quaternion, so the
    // round-trip is float (not bit-exact) — compare with epsilon. (0.5,0.5,0.5,0.5) is a
    // unit quaternion with float32-exact components, isolating the engine conversion.
    // DEVICE-VALIDATED 2026-06-13 (iPhone 12, iOS 26.5) — RID/Quaternion/AABB kinds
    {
        int64_t n3q = kanama_ios_godot_construct_object("Node3D");
        float qin[4] = { 0.5f, 0.5f, 0.5f, 0.5f };
        const void *qa[1] = { qin };
        int32_t qt[1] = { KANAMA_IOS_PT_QUATERNION };
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("Node3D", "set_quaternion", 1727505552),
            n3q, qt, qa, 1, KANAMA_IOS_PT_VOID, NULL);
        float qout[4] = { 0 };
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("Node3D", "get_quaternion", 1222331677),
            n3q, NULL, NULL, 0, KANAMA_IOS_PT_QUATERNION, qout);
        int q_ok = 1;
        for (int k = 0; k < 4; k++) {
            float d = qout[k] - qin[k];
            if (d < 0) { d = -d; }
            if (d > 1e-4f) { q_ok = 0; }
        }
        KANAMA_IOS_ST_CHECK("quaternion set/get_quaternion round-trip", q_ok);
    }

    // Variant Object.call dispatch (string return): Object.call("get_class") -> "Node3D".
    // Exercises kanama_ios_godot_object_call: String arg boxing + String return decode.
    {
        int64_t call_bind = kanama_ios_godot_get_method_bind("Object", "call", 3400424181);
        const void *ca[1] = { "get_class" };
        int32_t ct[1] = { KANAMA_IOS_PT_STRING };
        char buf[64];
        memset(buf, 0, sizeof(buf));
        int64_t slen = 0;
        int32_t rt = kanama_ios_godot_object_call(call_bind, node3d, ct, ca, 1,
            NULL, NULL, buf, (int64_t)sizeof(buf), &slen);
        KANAMA_IOS_ST_CHECK("variant-call get_class==Node3D",
            rt == KANAMA_IOS_VARIANT_TYPE_STRING && slen == 6 && strncmp(buf, "Node3D", 6) == 0);
    }

    // Variant Object.call dispatch (int value arg + int return): set_meta("k",4242) via call,
    // then get_meta("k")==4242. Validates a non-method-name VALUE arg through the boxing path
    // (the same path set_deferred uses) and INT return decode.
    {
        int64_t call_bind = kanama_ios_godot_get_method_bind("Object", "call", 3400424181);
        int64_t meta_val = 4242;
        const void *sa[3] = { "set_meta", "kanama_call", &meta_val };
        int32_t st[3] = { KANAMA_IOS_PT_STRING, KANAMA_IOS_PT_STRING, KANAMA_IOS_PT_INT64 };
        kanama_ios_godot_object_call(call_bind, node3d, st, sa, 3, NULL, NULL, NULL, 0, NULL);
        const void *ga[2] = { "get_meta", "kanama_call" };
        int32_t gt[2] = { KANAMA_IOS_PT_STRING, KANAMA_IOS_PT_STRING };
        int64_t got = 0;
        int32_t rt = kanama_ios_godot_object_call(call_bind, node3d, gt, ga, 2, &got, NULL, NULL, 0, NULL);
        KANAMA_IOS_ST_CHECK("variant-call set_meta/get_meta int round-trip",
            rt == KANAMA_IOS_VARIANT_TYPE_INT && got == 4242);
    }

    // Object.disconnect: connect node3d.property_list_changed -> target.notify_property_list_changed,
    // confirm is_connected, disconnect, confirm not connected. Fail-loud via is_connected.
    {
        int64_t dc_target = kanama_ios_godot_construct_object("Node");
        const char *dc_sig = "property_list_changed";
        const char *dc_method = "notify_property_list_changed";
        kanama_ios_godot_object_connect(node3d, dc_sig, dc_target, dc_method, 0);
        int connected_before = kanama_ios_object_is_connected(node3d, dc_sig, dc_target, dc_method);
        int dc_rc = kanama_ios_godot_object_disconnect(node3d, dc_sig, dc_target, dc_method);
        int connected_after = kanama_ios_object_is_connected(node3d, dc_sig, dc_target, dc_method);
        KANAMA_IOS_ST_CHECK("object disconnect (connected->disconnected)",
            connected_before == 1 && dc_rc == 0 && connected_after == 0);
    }

    // Value-type builtin method: Transform3D.inverse() assumes an orthonormal basis — it
    // TRANSPOSES the basis (diag(2,4,8) stays diag) and sets origin = basisᵀ·(-origin), so
    // diag(2,4,8) + origin(1,2,3) -> diag(2,4,8) + origin(-2,-8,-24). Exact float32; the
    // nonzero origin proves the 12-float layout + that the engine transformed it.
    // Exercises variant_get_ptr_builtin_method + kanama_ios_godot_builtin_call.
    {
        int64_t inv = kanama_ios_godot_get_builtin_method(
            KANAMA_IOS_VARIANT_TYPE_TRANSFORM3D, "inverse", 3816817146);
        float base[12] = { 2,0,0, 0,4,0, 0,0,8, 1,2,3 };
        float out[12] = { 0 };
        kanama_ios_godot_builtin_call(inv, base, NULL, NULL, 0, out);
        int ok = inv != 0 &&
            out[0]==2 && out[4]==4 && out[8]==8 &&
            out[1]==0 && out[2]==0 && out[3]==0 && out[5]==0 && out[6]==0 && out[7]==0 &&
            out[9]==-2 && out[10]==-8 && out[11]==-24;
        KANAMA_IOS_ST_CHECK("builtin Transform3D.inverse orthonormal-transpose", ok);
    }

    // Value-type builtin method: Basis.inverse() of diag(2,4,8) -> diag(0.5,0.25,0.125).
    {
        int64_t inv = kanama_ios_godot_get_builtin_method(
            KANAMA_IOS_VARIANT_TYPE_BASIS, "inverse", 594669093);
        float base[9] = { 2,0,0, 0,4,0, 0,0,8 };
        float out[9] = { 0 };
        kanama_ios_godot_builtin_call(inv, base, NULL, NULL, 0, out);
        int ok = inv != 0 &&
            out[0]==0.5f && out[4]==0.25f && out[8]==0.125f &&
            out[1]==0 && out[2]==0 && out[3]==0 && out[5]==0 && out[6]==0 && out[7]==0;
        KANAMA_IOS_ST_CHECK("builtin Basis.inverse diag", ok);
    }

    // Builtin method with args (Vector3, Vector3, bool): Transform3D.looking_at. A 90°-about-Z
    // base at origin 0, looking at (0,0,-4) up +Y -> IDENTITY basis (rotation discarded),
    // origin 0. Validates the builtin_call arg path (PT_VECTOR3 x2 + PT_BOOL).
    {
        int64_t mb = kanama_ios_godot_get_builtin_method(
            KANAMA_IOS_VARIANT_TYPE_TRANSFORM3D, "looking_at", 90889270);
        float base[12] = { 0,-1,0, 1,0,0, 0,0,1, 0,0,0 };  // column axes x=(0,1,0),y=(-1,0,0),z=(0,0,1)
        float target[3] = { 0,0,-4 };
        float up[3] = { 0,1,0 };
        uint8_t use_model_front = 0;
        const void *a[3] = { target, up, &use_model_front };
        int32_t t[3] = { KANAMA_IOS_PT_VECTOR3, KANAMA_IOS_PT_VECTOR3, KANAMA_IOS_PT_BOOL };
        float out[12] = { 0 };
        kanama_ios_godot_builtin_call(mb, base, t, a, 3, out);
        int ok = mb != 0 &&
            out[0]==1 && out[4]==1 && out[8]==1 &&
            out[1]==0 && out[2]==0 && out[3]==0 && out[5]==0 && out[6]==0 && out[7]==0 &&
            out[9]==0 && out[10]==0 && out[11]==0;
        KANAMA_IOS_ST_CHECK("builtin Transform3D.looking_at -Z->identity", ok);
    }

    // Builtin method with args (Transform3D, float->8-byte double): Transform3D.interpolate_with.
    // base IDENTITY+origin0, to IDENTITY+origin(2,4,8), weight 0.5 -> IDENTITY+origin(1,2,4).
    // Validates the builtin_call arg path (PT_TRANSFORM3D struct + PT_FLOAT64 scalar).
    {
        int64_t mb = kanama_ios_godot_get_builtin_method(
            KANAMA_IOS_VARIANT_TYPE_TRANSFORM3D, "interpolate_with", 1786453358);
        float base[12] = { 1,0,0, 0,1,0, 0,0,1, 0,0,0 };
        float to[12]   = { 1,0,0, 0,1,0, 0,0,1, 2,4,8 };
        double weight = 0.5;
        const void *a[2] = { to, &weight };
        int32_t t[2] = { KANAMA_IOS_PT_TRANSFORM3D, KANAMA_IOS_PT_FLOAT64 };
        float out[12] = { 0 };
        kanama_ios_godot_builtin_call(mb, base, t, a, 2, out);
        int ok = mb != 0 &&
            out[0]==1 && out[4]==1 && out[8]==1 &&
            out[1]==0 && out[2]==0 && out[3]==0 && out[5]==0 && out[6]==0 && out[7]==0 &&
            out[9]==1 && out[10]==2 && out[11]==4;
        KANAMA_IOS_ST_CHECK("builtin Transform3D.interpolate_with 0.5", ok);
    }

    // Builtin method with a Vector3 arg on a Vector3 base: Vector3.cross. x̂×ŷ = ẑ —
    // cross((1,0,0),(0,1,0)) = (0,0,1). Exact float32; exercises VT_VECTOR3 + PT_VECTOR3 arg.
    {
        int64_t mb = kanama_ios_godot_get_builtin_method(
            KANAMA_IOS_VARIANT_TYPE_VECTOR3, "cross", 2923479887);
        float base[3] = { 1,0,0 };
        float with[3] = { 0,1,0 };
        const void *a[1] = { with };
        int32_t t[1] = { KANAMA_IOS_PT_VECTOR3 };
        float out[3] = { 9,9,9 };
        kanama_ios_godot_builtin_call(mb, base, t, a, 1, out);
        int ok = mb != 0 && out[0]==0 && out[1]==0 && out[2]==1;
        KANAMA_IOS_ST_CHECK("builtin Vector3.cross x*y=z", ok);
    }

    // Builtin method with a float arg on a Vector2 base: Vector2.rotated. Rotating (1,0) by
    // π/2 -> (~0,1). Float trig, so tolerance ε; exercises VT_VECTOR2 + PT_FLOAT64 scalar arg.
    {
        int64_t mb = kanama_ios_godot_get_builtin_method(
            KANAMA_IOS_VARIANT_TYPE_VECTOR2, "rotated", 2544004089);
        float base[2] = { 1,0 };
        double angle = 1.5707963267948966;  // π/2
        const void *a[1] = { &angle };
        int32_t t[1] = { KANAMA_IOS_PT_FLOAT64 };
        float out[2] = { 9,9 };
        kanama_ios_godot_builtin_call(mb, base, t, a, 1, out);
        int ok = mb != 0 &&
            out[0] > -1e-4f && out[0] < 1e-4f &&
            out[1] > 1 - 1e-4f && out[1] < 1 + 1e-4f;
        KANAMA_IOS_ST_CHECK("builtin Vector2.rotated pi/2", ok);
    }

    // No-arg->Self builtin on a Quaternion base: Quaternion.inverse(). For a unit quaternion
    // (90° about Z = (0,0,sin45,cos45)) the inverse is the conjugate: z flips sign, w kept.
    // Float divide by length² so tolerance ε; exercises VT_QUATERNION.
    {
        int64_t mb = kanama_ios_godot_get_builtin_method(
            KANAMA_IOS_VARIANT_TYPE_QUATERNION, "inverse", 4274879941);
        float s = 0.70710677f;
        float base[4] = { 0,0, s, s };
        float out[4] = { 9,9,9,9 };
        kanama_ios_godot_builtin_call(mb, base, NULL, NULL, 0, out);
        int ok = mb != 0 &&
            out[0] > -1e-4f && out[0] < 1e-4f &&
            out[1] > -1e-4f && out[1] < 1e-4f &&
            out[2] > -s - 1e-4f && out[2] < -s + 1e-4f &&
            out[3] > s - 1e-4f && out[3] < s + 1e-4f;
        KANAMA_IOS_ST_CHECK("builtin Quaternion.inverse 90z", ok);
    }

    // Builtin method with a Vector3 arg on a Basis base: Basis.scaled. IDENTITY scaled by
    // (2,3,4) -> diag(2,3,4). Exact float32; exercises VT_BASIS + PT_VECTOR3 arg.
    {
        int64_t mb = kanama_ios_godot_get_builtin_method(
            KANAMA_IOS_VARIANT_TYPE_BASIS, "scaled", 3934786792);
        float base[9] = { 1,0,0, 0,1,0, 0,0,1 };
        float scale[3] = { 2,3,4 };
        const void *a[1] = { scale };
        int32_t t[1] = { KANAMA_IOS_PT_VECTOR3 };
        float out[9] = { 0 };
        kanama_ios_godot_builtin_call(mb, base, t, a, 1, out);
        int ok = mb != 0 &&
            out[0]==2 && out[4]==3 && out[8]==4 &&
            out[1]==0 && out[2]==0 && out[3]==0 && out[5]==0 && out[6]==0 && out[7]==0;
        KANAMA_IOS_ST_CHECK("builtin Basis.scaled diag", ok);
    }

    // Builtin method with a Vector3 arg on a Transform3D base: Transform3D.translated.
    // IDENTITY+origin0 translated by (1,2,3) -> IDENTITY+origin(1,2,3). Exact float32;
    // exercises VT_TRANSFORM3D + PT_VECTOR3 arg.
    {
        int64_t mb = kanama_ios_godot_get_builtin_method(
            KANAMA_IOS_VARIANT_TYPE_TRANSFORM3D, "translated", 1405596198);
        float base[12] = { 1,0,0, 0,1,0, 0,0,1, 0,0,0 };
        float offset[3] = { 1,2,3 };
        const void *a[1] = { offset };
        int32_t t[1] = { KANAMA_IOS_PT_VECTOR3 };
        float out[12] = { 0 };
        kanama_ios_godot_builtin_call(mb, base, t, a, 1, out);
        int ok = mb != 0 &&
            out[0]==1 && out[4]==1 && out[8]==1 &&
            out[1]==0 && out[2]==0 && out[3]==0 && out[5]==0 && out[6]==0 && out[7]==0 &&
            out[9]==1 && out[10]==2 && out[11]==3;
        KANAMA_IOS_ST_CHECK("builtin Transform3D.translated offset", ok);
    }

    // Scalar-return builtin, arg path: Vector3.dot. (1,2,3)·(4,5,6) = 4+10+18 = 32. Exact.
    // KEY: a builtin method's scalar `float` (Variant FLOAT) return is encoded as an 8-byte
    // DOUBLE in the GDExtension ptr-ABI, regardless of real_t precision — NOT a real_t/float32
    // like value-type components. Decoding it as a 4-byte float reads the low half of the
    // double and yields 0 (the bug this row guards). Matches desktop BuiltinTypes (JAVA_DOUBLE ret).
    {
        int64_t mb = kanama_ios_godot_get_builtin_method(
            KANAMA_IOS_VARIANT_TYPE_VECTOR3, "dot", 1047977935);
        float base[3] = { 1,2,3 };
        float with[3] = { 4,5,6 };
        const void *a[1] = { with };
        int32_t t[1] = { KANAMA_IOS_PT_VECTOR3 };
        double out = 0;
        kanama_ios_godot_builtin_call(mb, base, t, a, 1, &out);
        int ok = mb != 0 && out == 32;
        KANAMA_IOS_ST_CHECK("builtin Vector3.dot scalar", ok);
    }

    // Scalar-return builtin, no-arg: Basis.determinant of diag(2,4,8) = 2*4*8 = 64. Exact.
    // Scalar return is an 8-byte double (see Vector3.dot above).
    {
        int64_t mb = kanama_ios_godot_get_builtin_method(
            KANAMA_IOS_VARIANT_TYPE_BASIS, "determinant", 466405837);
        float base[9] = { 2,0,0, 0,4,0, 0,0,8 };
        double out = 0;
        kanama_ios_godot_builtin_call(mb, base, NULL, NULL, 0, &out);
        int ok = mb != 0 && out == 64;
        KANAMA_IOS_ST_CHECK("builtin Basis.determinant scalar", ok);
    }

    // Bool-return builtin: Vector3.is_normalized() of (0,0,1) -> true. The ptr-ABI encodes a
    // bool return as a single uint8 (PtrToArg<bool> = uint8), so decode ONE byte (a wider
    // read would catch garbage in the high bytes). Returning true (1) also fails loudly if a
    // wrong-width read yields 0.
    {
        int64_t mb = kanama_ios_godot_get_builtin_method(
            KANAMA_IOS_VARIANT_TYPE_VECTOR3, "is_normalized", 3918633141);
        float base[3] = { 0,0,1 };
        uint8_t out = 0xFF;
        kanama_ios_godot_builtin_call(mb, base, NULL, NULL, 0, &out);
        int ok = mb != 0 && out == 1;
        KANAMA_IOS_ST_CHECK("builtin Vector3.is_normalized bool", ok);
    }

    // Int-return builtin: Vector3.max_axis_index() of (1,5,2) -> 1 (Y). The ptr-ABI encodes an
    // int return as int64 (PtrToArg<int64_t> direct), so decode 8 bytes. Expecting 1 (not 0)
    // catches a wrong-width zero-read.
    {
        int64_t mb = kanama_ios_godot_get_builtin_method(
            KANAMA_IOS_VARIANT_TYPE_VECTOR3, "max_axis_index", 3173160232);
        float base[3] = { 1,5,2 };
        int64_t out = -1;
        kanama_ios_godot_builtin_call(mb, base, NULL, NULL, 0, &out);
        int ok = mb != 0 && out == 1;
        KANAMA_IOS_ST_CHECK("builtin Vector3.max_axis_index int", ok);
    }

    // Value-type @ScriptProperty delivery (Phase 3.2 Step 5 / 2.6): exercise the set-property
    // extraction primitives — the exact converters kanama_ios_script_instance_set_property uses
    // to turn an incoming Variant into the PT-tagged bytes the Kotlin runtime decodes. NodePath
    // goes through variant->NodePath->String(idx3)->utf8; Vector2/Vector3 through variant->float32.
    // NodePath round-trip: build a NodePath Variant, extract it back to a utf8 path string.
    if (g_variant_from_node_path != NULL && g_variant_to_node_path != NULL
        && g_string_from_node_path_constructor != NULL) {
        uint64_t np = 0;
        kanama_ios_init_node_path(&np, "../SceneTarget3D");
        uint8_t variant[24] = {0};
        g_variant_from_node_path((GDExtensionUninitializedVariantPtr)variant, (GDExtensionTypePtr)&np);
        uint64_t np_out = 0;
        g_variant_to_node_path(
            (GDExtensionUninitializedTypePtr)&np_out, (GDExtensionVariantPtr)variant);
        uint64_t str_out = 0;
        const GDExtensionConstTypePtr np_args[1] = { (GDExtensionConstTypePtr)&np_out };
        g_string_from_node_path_constructor((GDExtensionUninitializedTypePtr)&str_out, np_args);
        char *utf8 = kanama_ios_string_to_utf8_dup((GDExtensionConstStringPtr)&str_out);
        KANAMA_IOS_ST_CHECK("setprop-nodepath", utf8 != NULL && strcmp(utf8, "../SceneTarget3D") == 0);
        free(utf8);
        kanama_ios_destroy_string(&str_out);
        kanama_ios_destroy_node_path(&np_out);
        kanama_ios_destroy_node_path(&np);
        if (g_variant_destroy != NULL) { g_variant_destroy((GDExtensionVariantPtr)variant); }
    } else {
        KANAMA_IOS_ST_CHECK("setprop-nodepath", 0);
    }
    // Vector2 round-trip: build a Vector2 Variant, extract its float32 components.
    if (g_variant_from_vector2 != NULL && g_variant_to_vector2 != NULL) {
        float in[2] = { 1.5f, -2.5f };
        uint8_t variant[24] = {0};
        g_variant_from_vector2((GDExtensionUninitializedVariantPtr)variant, (GDExtensionTypePtr)in);
        float out[2] = { 0, 0 };
        g_variant_to_vector2(
            (GDExtensionUninitializedTypePtr)out, (GDExtensionVariantPtr)variant);
        KANAMA_IOS_ST_CHECK("setprop-vector2", out[0] == 1.5f && out[1] == -2.5f);
        if (g_variant_destroy != NULL) { g_variant_destroy((GDExtensionVariantPtr)variant); }
    } else {
        KANAMA_IOS_ST_CHECK("setprop-vector2", 0);
    }
    // Vector3 round-trip: build a Vector3 Variant, extract its float32 components.
    if (g_variant_from_vector3 != NULL && g_variant_to_vector3 != NULL) {
        float in[3] = { 1.0f, 2.0f, 3.0f };
        uint8_t variant[24] = {0};
        g_variant_from_vector3((GDExtensionUninitializedVariantPtr)variant, (GDExtensionTypePtr)in);
        float out[3] = { 0, 0, 0 };
        g_variant_to_vector3(
            (GDExtensionUninitializedTypePtr)out, (GDExtensionVariantPtr)variant);
        KANAMA_IOS_ST_CHECK("setprop-vector3", out[0] == 1.0f && out[1] == 2.0f && out[2] == 3.0f);
        if (g_variant_destroy != NULL) { g_variant_destroy((GDExtensionVariantPtr)variant); }
    } else {
        KANAMA_IOS_ST_CHECK("setprop-vector3", 0);
    }
    // PackedStringArray round-trip: build the same Variant shape Godot delivers for
    // List<String> @ScriptProperty values, extract it through the set-property primitive, and
    // verify the utf8 element path used by kanama_ios_script_instance_set_property.
    if (g_variant_from_packed_string_array != NULL && g_variant_to_packed_string_array != NULL) {
        kanama_ios_cache_packed_string_methods();
        KANAMA_IOS_PACKED_ARRAY_STORAGE(in_array);
        KANAMA_IOS_PACKED_ARRAY_STORAGE(out_array);
        kanama_ios_init_packed_string_array_one(in_array, "walk");
        uint8_t variant[24] = {0};
        g_variant_from_packed_string_array(
            (GDExtensionUninitializedVariantPtr)variant,
            (GDExtensionTypePtr)in_array);
        g_variant_to_packed_string_array(
            (GDExtensionUninitializedTypePtr)out_array,
            (GDExtensionVariantPtr)variant);
        int64_t count = 0;
        if (g_packed_string_array_size_method != NULL) {
            g_packed_string_array_size_method(out_array, NULL, &count, 0);
        }
        GDExtensionStringPtr str = (g_packed_string_array_operator_index_const != NULL && count == 1)
            ? g_packed_string_array_operator_index_const(out_array, 0)
            : NULL;
        char *utf8 = kanama_ios_string_to_utf8_dup((GDExtensionConstStringPtr)str);
        KANAMA_IOS_ST_CHECK("setprop-packed-string-array",
            count == 1 && utf8 != NULL && strcmp(utf8, "walk") == 0);
        free(utf8);
        if (g_packed_string_array_destructor != NULL) {
            g_packed_string_array_destructor(out_array);
            g_packed_string_array_destructor(in_array);
        }
        if (g_variant_destroy != NULL) { g_variant_destroy((GDExtensionVariantPtr)variant); }
    } else {
        KANAMA_IOS_ST_CHECK("setprop-packed-string-array", 0);
    }

    // @Rpc config delivery: build the _get_rpc_config Dictionary via the production helper from a
    // synthetic descriptor, then read it back and assert each method's fields. Distinct non-zero
    // values catch field-swaps (e.g. rpc_mode vs transfer_mode). Needs the debug-only keyed getter
    // and variant->Dictionary; skip cleanly if Godot did not expose them.
    if (g_dictionary_keyed_getter != NULL && g_variant_to_dictionary != NULL) {
        KanamaIosRpcConfig probe_configs[2] = {
            { "net_score", 1, 1, 2, 3 },
            { "sync_pos", 2, 0, 0, 5 },
        };
        KanamaIosExtensionInstance probe;
        memset(&probe, 0, sizeof(probe));
        probe.script_rpc_configs = probe_configs;
        probe.script_rpc_config_count = 2;

        uint8_t root_variant[24];
        memset(root_variant, 0, sizeof(root_variant));
        kanama_ios_init_script_rpc_config_variant(&probe, root_variant);

        uint64_t root_dict = 0;
        g_variant_to_dictionary(&root_dict, root_variant);

        int rpc_ok = 1;
        for (int i = 0; i < 2; i++) {
            const KanamaIosRpcConfig *expected = &probe_configs[i];
            uint8_t method_variant[24];
            memset(method_variant, 0, sizeof(method_variant));
            kanama_ios_dictionary_get_variant(&root_dict, expected->method_name_text, method_variant);
            uint64_t method_dict = 0;
            g_variant_to_dictionary(&method_dict, method_variant);
            rpc_ok = rpc_ok
                && kanama_ios_dictionary_get_int(&method_dict, "rpc_mode") == expected->mode
                && kanama_ios_dictionary_get_bool(&method_dict, "call_local") == expected->call_local
                && kanama_ios_dictionary_get_int(&method_dict, "transfer_mode") == expected->transfer_mode
                && kanama_ios_dictionary_get_int(&method_dict, "channel") == expected->channel;
            g_dictionary_destructor(&method_dict);
            g_variant_destroy(method_variant);
        }
        g_dictionary_destructor(&root_dict);
        g_variant_destroy(root_variant);
        KANAMA_IOS_ST_CHECK("script-rpc-config-dictionary", rpc_ok);
    }

    fprintf(stderr, "[kanama][ios][c] PTRCALL SELFTEST MATRIX: %d passed, %d failed\n", pass, fail);
    fflush(stderr);
#undef KANAMA_IOS_ST_CHECK

    // Also exercise the full Kotlin ObjectCalls path (Kotlin -> generic dispatch -> Godot).
    kanama_ios_runtime_objectcalls_selftest();
}
#endif // KANAMA_IOS_DEBUG_VARIANT_CHECKS

// Force the AVAudioSession category to Ambient so game audio tracks the
// Ring/Silent switch deterministically (plays in Ring, muted in Silent, mixes
// with other apps). Done from the Objective-C runtime to avoid linking
// AVFoundation into the C shim. Runs at SCENE-level init — after Godot has
// brought up and activated its own audio session — so this overrides whatever
// category the engine set rather than being clobbered by a later engine init.
static void kanama_ios_configure_audio_session(void) {
    if (g_kanama_ios_audio_session_configured) {
        return;
    }
    g_kanama_ios_audio_session_configured = 1;

    void *audio_framework = dlopen(
        "/System/Library/Frameworks/AVFAudio.framework/AVFAudio",
        RTLD_LAZY | RTLD_LOCAL
    );
    if (audio_framework == NULL) {
        audio_framework = dlopen(
            "/System/Library/Frameworks/AVFoundation.framework/AVFoundation",
            RTLD_LAZY | RTLD_LOCAL
        );
    }

    Class session_class = objc_getClass("AVAudioSession");
    Class string_class = objc_getClass("NSString");
    if (session_class == Nil || string_class == Nil) {
        fprintf(stderr, "[kanama][ios][c] AVAudioSession unavailable; leaving Godot audio session unchanged\n");
        fflush(stderr);
        return;
    }

    id (*send_id)(id, SEL) = (id (*)(id, SEL))objc_msgSend;
    id (*send_string)(id, SEL, const char *) = (id (*)(id, SEL, const char *))objc_msgSend;
    signed char (*send_bool_id_ptr)(id, SEL, id, id *) =
        (signed char (*)(id, SEL, id, id *))objc_msgSend;

    id session = send_id((id)session_class, sel_registerName("sharedInstance"));
    id category = send_string(
        (id)string_class,
        sel_registerName("stringWithUTF8String:"),
        "AVAudioSessionCategoryAmbient"
    );
    if (session == nil || category == nil) {
        fprintf(stderr, "[kanama][ios][c] AVAudioSession setup skipped: session/category unavailable\n");
        fflush(stderr);
        return;
    }

    signed char category_ok = send_bool_id_ptr(
        session,
        sel_registerName("setCategory:error:"),
        category,
        NULL
    );
    if (!category_ok) {
        fprintf(stderr, "[kanama][ios][c] AVAudioSessionCategoryAmbient setup failed\n");
        fflush(stderr);
        return;
    }

    fprintf(stderr, "[kanama][ios][c] AVAudioSession category set to Ambient\n");
    fflush(stderr);
}

static void kanama_ios_initialize(void *userdata, GDExtensionInitializationLevel level) {
    (void)userdata;
    fprintf(stderr, "[kanama][ios][c] initialize: level=%d\n", (int)level);
    if (level == GDEXTENSION_INITIALIZATION_SCENE) {
        kanama_ios_configure_audio_session();
        kanama_ios_register_script_classes();
        kanama_ios_register_script_language();
        kanama_ios_register_resource_loader();
        kanama_ios_register_main_loop_callbacks();
#if KANAMA_IOS_DEBUG_VARIANT_CHECKS
        kanama_ios_ptrcall_selftest();
#endif
    }
    kanama_ios_runtime_initialize((int32_t)level);
}

static void kanama_ios_deinitialize(void *userdata, GDExtensionInitializationLevel level) {
    (void)userdata;
    fprintf(stderr, "[kanama][ios][c] deinitialize: level=%d\n", (int)level);
    if (level == GDEXTENSION_INITIALIZATION_SCENE) {
        g_main_loop_callbacks_active = 0;
        g_main_loop_callback_frame_count = 0;
        kanama_ios_unregister_script_runtime();
    }
    kanama_ios_runtime_deinitialize((int32_t)level);
}

static void fill_init_struct(GDExtensionInitialization *r_initialization) {
    r_initialization->minimum_initialization_level = GDEXTENSION_INITIALIZATION_SCENE;
    r_initialization->userdata = NULL;
    r_initialization->initialize = kanama_ios_initialize;
    r_initialization->deinitialize = kanama_ios_deinitialize;
}

__attribute__((visibility("default")))
GDExtensionBool kanama_entry(
    GDExtensionInterfaceGetProcAddress p_get_proc_address,
    GDExtensionClassLibraryPtr p_library,
    GDExtensionInitialization *r_initialization
) {
    fprintf(stderr, "[kanama][ios][c] entry: get_proc_address=%p library=%p\n",
            (void *)p_get_proc_address, (void *)p_library);

    g_get_proc_address = p_get_proc_address;
    g_library = p_library;

    fill_init_struct(r_initialization);

    if (g_kanama_ios_initialized) {
        fprintf(stderr, "[kanama][ios][c] re-entry: runtime already initialized\n");
        return 1;
    }

    int32_t initialized = kanama_ios_runtime_entry(
        (uintptr_t)p_get_proc_address,
        (uintptr_t)p_library,
        (uintptr_t)r_initialization
    );
    if (!initialized) {
        fprintf(stderr, "[kanama][ios][c] error: Kotlin/Native runtime init failed\n");
        return 0;
    }

    g_kanama_ios_initialized = 1;
    return 1;
}
