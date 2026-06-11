/*
 * Kanama iOS GDExtension bootstrap.
 *
 * iOS cannot use the desktop JVM/Panama bootstrap. This shim keeps the Godot
 * entry point and ABI-sensitive Godot structs in C, and delegates runtime
 * state to a Kotlin/Native static library linked into the same xcframework.
 */

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
extern int32_t kanama_ios_runtime_script_resource_signal_count(int64_t script_handle);
extern void kanama_ios_runtime_script_resource_signal_name(
    int64_t script_handle,
    int32_t signal_index,
    char *buffer,
    int32_t buffer_size
);
extern int64_t kanama_ios_runtime_script_instance_create(int64_t script_handle, int64_t owner_object);
extern void kanama_ios_runtime_script_instance_ready(int64_t instance_handle);
extern int32_t kanama_ios_runtime_script_instance_call(
    int64_t instance_handle,
    int32_t method_index,
    double first_arg
);
extern int32_t kanama_ios_runtime_script_instance_call_object(
    int64_t instance_handle,
    int32_t method_index,
    int64_t object_arg
);
extern int32_t kanama_ios_runtime_script_instance_call_args(
    int64_t instance_handle,
    int32_t method_index,
    int64_t arg1,
    int64_t arg2,
    int64_t arg3
);
extern int32_t kanama_ios_runtime_script_instance_call_vector2i(
    int64_t instance_handle,
    int32_t method_index,
    int64_t x,
    int64_t y
);
extern int32_t kanama_ios_runtime_script_instance_call_long(
    int64_t instance_handle,
    int32_t method_index,
    int64_t value
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
    KANAMA_IOS_VIRTUAL_RESOURCE_GET_TYPE,
    KANAMA_IOS_VIRTUAL_RESOURCE_LOAD,
} KanamaIosVirtualId;

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
    int32_t script_property_count;
    uint64_t *script_signal_names;
    char **script_signal_name_texts;
    int32_t script_signal_count;
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
static GDExtensionInterfaceGetProcAddress g_get_proc_address = NULL;
static GDExtensionClassLibraryPtr g_library = NULL;
static GDExtensionInterfaceCallableCustomCreate2 g_callable_custom_create2 = NULL;

static GDExtensionInterfaceStringNameNewWithUtf8Chars g_string_name_new = NULL;
static GDExtensionInterfaceStringNewWithUtf8Chars g_string_new = NULL;
static GDExtensionInterfaceStringToUtf8Chars g_string_to_utf8_chars = NULL;
static GDExtensionInterfaceVariantGetPtrDestructor g_variant_get_ptr_destructor = NULL;
static GDExtensionInterfaceVariantGetPtrConstructor g_variant_get_ptr_constructor = NULL;
static GDExtensionInterfaceVariantGetPtrBuiltinMethod g_variant_get_ptr_builtin_method = NULL;
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
static GDExtensionMethodBindPtr g_tween_tween_property_bind = NULL;
static GDExtensionMethodBindPtr g_tween_set_parallel_bind = NULL;
static GDExtensionMethodBindPtr g_tween_kill_bind = NULL;
static GDExtensionMethodBindPtr g_property_tweener_set_trans_bind = NULL;
static GDExtensionMethodBindPtr g_property_tweener_set_ease_bind = NULL;
static GDExtensionMethodBindPtr g_viewport_get_visible_rect_bind = NULL;
static GDExtensionPtrConstructor g_node_path_from_string_constructor = NULL;
static GDExtensionPtrConstructor g_packed_string_array_constructor = NULL;
static GDExtensionPtrConstructor g_array_constructor = NULL;
static GDExtensionPtrConstructor g_dictionary_constructor = NULL;
static GDExtensionPtrDestructor g_node_path_destructor = NULL;
static GDExtensionPtrBuiltInMethod g_packed_string_array_push_back = NULL;
static GDExtensionTypeFromVariantConstructorFunc g_variant_to_bool = NULL;
static GDExtensionTypeFromVariantConstructorFunc g_variant_to_string = NULL;
static GDExtensionTypeFromVariantConstructorFunc g_variant_to_float = NULL;
static GDExtensionTypeFromVariantConstructorFunc g_variant_to_object = NULL;
static GDExtensionTypeFromVariantConstructorFunc g_variant_to_int = NULL;
static GDExtensionTypeFromVariantConstructorFunc g_variant_to_vector2i = NULL;
static GDExtensionTypeFromVariantConstructorFunc g_variant_to_array = NULL;
static GDExtensionPtrBuiltInMethod g_array_size_method = NULL;
static GDExtensionPtrBuiltInMethod g_array_get_method = NULL;
static GDExtensionVariantFromTypeConstructorFunc g_variant_from_vector2i = NULL;
static GDExtensionPtrConstructor g_callable_object_method_constructor = NULL;
static GDExtensionVariantFromTypeConstructorFunc g_variant_from_callable = NULL;
static GDExtensionPtrDestructor g_callable_destructor = NULL;
static GDExtensionVariantFromTypeConstructorFunc g_variant_from_vector2 = NULL;
static GDExtensionVariantFromTypeConstructorFunc g_variant_from_color = NULL;
static GDExtensionVariantFromTypeConstructorFunc g_variant_from_node_path = NULL;
static GDExtensionVariantFromTypeConstructorFunc g_variant_from_float = NULL;
static GDExtensionVariantFromTypeConstructorFunc g_variant_from_object = NULL;
static GDExtensionVariantFromTypeConstructorFunc g_variant_from_string_name = NULL;
static GDExtensionVariantFromTypeConstructorFunc g_variant_from_int = NULL;
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
    // CONSTRUCT-tagged: arg ptr is a C string; the dispatch builds the value.
    KANAMA_IOS_PT_STRING_NAME,
    // KANAMA-IOS-STUB: PT_STRING / PT_NODE_PATH arg construction not implemented in the
    // generic dispatch (only PT_STRING_NAME is built C-side). Reserved. Backlog.
    KANAMA_IOS_PT_STRING,      // (arg construction TODO; reserved)
    KANAMA_IOS_PT_NODE_PATH,   // (arg construction TODO; reserved)
};

enum {
    KANAMA_IOS_VARIANT_TYPE_NIL = 0,
    KANAMA_IOS_VARIANT_TYPE_BOOL = 1,
    KANAMA_IOS_VARIANT_TYPE_INT = 2,
    KANAMA_IOS_VARIANT_TYPE_FLOAT = 3,
    KANAMA_IOS_VARIANT_TYPE_STRING = 4,
    KANAMA_IOS_VARIANT_TYPE_VECTOR2 = 5,
    KANAMA_IOS_VARIANT_TYPE_VECTOR2I = 6,
    KANAMA_IOS_VARIANT_TYPE_RECT2 = 7,
    KANAMA_IOS_VARIANT_TYPE_COLOR = 20,
    KANAMA_IOS_VARIANT_TYPE_STRING_NAME = 21,
    KANAMA_IOS_VARIANT_TYPE_NODE_PATH = 22,
    KANAMA_IOS_VARIANT_TYPE_OBJECT = 24,
    KANAMA_IOS_VARIANT_TYPE_CALLABLE = 25,
    KANAMA_IOS_VARIANT_TYPE_DICTIONARY = 27,
    KANAMA_IOS_VARIANT_TYPE_ARRAY = 28,
    KANAMA_IOS_VARIANT_TYPE_PACKED_STRING_ARRAY = 34,
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
    KANAMA_IOS_TWEEN_TWEEN_PROPERTY_HASH = 4049770449U,
    KANAMA_IOS_TWEEN_SET_PARALLEL_HASH = 1942052223U,
    KANAMA_IOS_TWEEN_KILL_HASH = 3218959716U,
    KANAMA_IOS_PROPERTY_TWEENER_SET_TRANS_HASH = 1899107404U,
    KANAMA_IOS_PROPERTY_TWEENER_SET_EASE_HASH = 1080455622U,
    KANAMA_IOS_VIEWPORT_GET_VISIBLE_RECT_HASH = 1639390495U,
    KANAMA_IOS_PACKED_STRING_ARRAY_PUSH_BACK_HASH = 816187996U,
    KANAMA_IOS_NOTIFICATION_POSTINITIALIZE = 0,
    KANAMA_IOS_NOTIFICATION_ENTER_TREE = 10,
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
    g_node_path_from_string_constructor = g_variant_get_ptr_constructor(KANAMA_IOS_VARIANT_TYPE_NODE_PATH, 2);
    g_packed_string_array_constructor = g_variant_get_ptr_constructor(
        KANAMA_IOS_VARIANT_TYPE_PACKED_STRING_ARRAY,
        0
    );
    g_array_constructor = g_variant_get_ptr_constructor(KANAMA_IOS_VARIANT_TYPE_ARRAY, 0);
    g_dictionary_constructor = g_variant_get_ptr_constructor(KANAMA_IOS_VARIANT_TYPE_DICTIONARY, 0);
    g_variant_to_bool = g_get_variant_to_type_constructor(KANAMA_IOS_VARIANT_TYPE_BOOL);
    g_variant_to_string = g_get_variant_to_type_constructor(KANAMA_IOS_VARIANT_TYPE_STRING);
    g_variant_to_float = g_get_variant_to_type_constructor(KANAMA_IOS_VARIANT_TYPE_FLOAT);
    g_variant_to_object = g_get_variant_to_type_constructor(KANAMA_IOS_VARIANT_TYPE_OBJECT);
    g_variant_to_int = g_get_variant_to_type_constructor(KANAMA_IOS_VARIANT_TYPE_INT);
    g_variant_to_vector2i = g_get_variant_to_type_constructor(KANAMA_IOS_VARIANT_TYPE_VECTOR2I);
    g_variant_to_array = g_get_variant_to_type_constructor(KANAMA_IOS_VARIANT_TYPE_ARRAY);
    g_variant_from_vector2i = g_get_variant_from_type_constructor(KANAMA_IOS_VARIANT_TYPE_VECTOR2I);
    g_callable_object_method_constructor = g_variant_get_ptr_constructor(KANAMA_IOS_VARIANT_TYPE_CALLABLE, 2);
    g_variant_from_callable = g_get_variant_from_type_constructor(KANAMA_IOS_VARIANT_TYPE_CALLABLE);
    g_callable_destructor = g_variant_get_ptr_destructor(KANAMA_IOS_VARIANT_TYPE_CALLABLE);
    g_variant_from_vector2 = g_get_variant_from_type_constructor(KANAMA_IOS_VARIANT_TYPE_VECTOR2);
    g_variant_from_color = g_get_variant_from_type_constructor(KANAMA_IOS_VARIANT_TYPE_COLOR);
    g_variant_from_node_path = g_get_variant_from_type_constructor(KANAMA_IOS_VARIANT_TYPE_NODE_PATH);
    g_variant_from_float = g_get_variant_from_type_constructor(KANAMA_IOS_VARIANT_TYPE_FLOAT);
    g_variant_from_object = g_get_variant_from_type_constructor(KANAMA_IOS_VARIANT_TYPE_OBJECT);
    g_variant_from_string_name = g_get_variant_from_type_constructor(KANAMA_IOS_VARIANT_TYPE_STRING_NAME);
    g_variant_from_int = g_get_variant_from_type_constructor(KANAMA_IOS_VARIANT_TYPE_INT);

    if (
        g_string_name_destructor == NULL ||
        g_string_destructor == NULL ||
        g_node_path_destructor == NULL ||
        g_node_path_from_string_constructor == NULL ||
        g_packed_string_array_constructor == NULL ||
        g_array_constructor == NULL ||
        g_dictionary_constructor == NULL ||
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
        g_variant_from_int == NULL
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

    int32_t method_count = kanama_ios_runtime_script_resource_method_count(instance->script_handle);
    if (method_count <= 0) {
        return;
    }
    instance->script_method_names = calloc((size_t)method_count, sizeof(uint64_t));
    instance->script_method_name_texts = calloc((size_t)method_count, sizeof(char *));
    if (instance->script_method_names == NULL || instance->script_method_name_texts == NULL) {
        free(instance->script_method_names);
        free(instance->script_method_name_texts);
        instance->script_method_names = NULL;
        instance->script_method_name_texts = NULL;
        return;
    }

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

    int32_t property_count = kanama_ios_runtime_script_resource_property_count(instance->script_handle);
    if (property_count > 0) {
        instance->script_property_names = calloc((size_t)property_count, sizeof(uint64_t));
        if (instance->script_property_names == NULL) {
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
    instance->script_method_names = NULL;
    instance->script_method_name_texts = NULL;
    instance->script_base_type_text = NULL;
    instance->script_path = NULL;
    instance->script_property_names = NULL;
    instance->script_property_count = 0;
    instance->script_signal_names = NULL;
    instance->script_signal_name_texts = NULL;
    instance->script_signal_count = 0;
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
    uint64_t string_name_cells[KANAMA_IOS_PTRCALL_MAX_ARGS];
    int constructed[KANAMA_IOS_PTRCALL_MAX_ARGS];

    for (int32_t i = 0; i < arg_count; i++) {
        constructed[i] = 0;
        int32_t tag = (arg_types != NULL) ? arg_types[i] : KANAMA_IOS_PT_VOID;
        switch (tag) {
            case KANAMA_IOS_PT_STRING_NAME: {
                string_name_cells[i] = 0;
                kanama_ios_init_string_name(&string_name_cells[i],
                    arg_ptrs != NULL ? (const char *)arg_ptrs[i] : "");
                args[i] = (const void *)&string_name_cells[i];
                constructed[i] = 1;
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
        if (constructed[i]) {
            kanama_ios_destroy_string_name(&string_name_cells[i]);
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
    if (kanama_ios_string_name_eq(name, g_name__get_rpc_config)) return (void *)KANAMA_IOS_VIRTUAL_VARIANT_NIL;
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

static const GDExtensionPropertyInfo *kanama_ios_script_instance_get_property_list(
    GDExtensionScriptInstanceDataPtr data,
    uint32_t *count
) {
    (void)data;
    if (count != NULL) {
        *count = 0;
    }
    return NULL;
}

static void kanama_ios_script_instance_free_property_list(
    GDExtensionScriptInstanceDataPtr data,
    const GDExtensionPropertyInfo *list,
    uint32_t count
) {
    (void)data;
    (void)list;
    (void)count;
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
    if (method_index < 0 && instance != NULL && instance->script != NULL &&
        kanama_ios_string_name_value(method) == kanama_ios_string_name_value((GDExtensionConstStringNamePtr)&g_name__unhandled_input)) {
        method_index = kanama_ios_script_method_index(instance->script, (GDExtensionConstStringNamePtr)&g_name__input);
    }
    if (instance != NULL && method_index >= 0) {
        double first_arg = 0.0;
        GDExtensionObjectPtr object_arg = NULL;
        int32_t ok = 0;
        if (argument_count >= 3 && args != NULL && args[0] != NULL && args[1] != NULL && args[2] != NULL) {
            GDExtensionObjectPtr arg1 = kanama_ios_variant_to_object(args[0]);
            GDExtensionObjectPtr arg2 = kanama_ios_variant_to_object(args[1]);
            int64_t arg3 = kanama_ios_variant_to_int64(args[2]);
            if (arg1 != NULL && arg2 != NULL) {
                ok = kanama_ios_runtime_script_instance_call_args(
                    instance->runtime_handle,
                    method_index,
                    (int64_t)(intptr_t)arg1,
                    (int64_t)(intptr_t)arg2,
                    arg3
                );
            }
        } else if (argument_count > 0 && args != NULL && args[0] != NULL) {
            GDExtensionVariantType arg_type = g_variant_get_type != NULL
                ? g_variant_get_type(args[0])
                : KANAMA_IOS_VARIANT_TYPE_NIL;
            if (arg_type == KANAMA_IOS_VARIANT_TYPE_VECTOR2I) {
                int64_t vx = 0, vy = 0;
                kanama_ios_variant_to_vector2i(args[0], &vx, &vy);
                ok = kanama_ios_runtime_script_instance_call_vector2i(
                    instance->runtime_handle,
                    method_index,
                    vx,
                    vy
                );
            } else if (arg_type == KANAMA_IOS_VARIANT_TYPE_INT) {
                // Single int/long value arg (e.g. a coin-count signal payload).
                int64_t value = kanama_ios_variant_to_int64(args[0]);
                ok = kanama_ios_runtime_script_instance_call_long(
                    instance->runtime_handle,
                    method_index,
                    value
                );
            } else {
                object_arg = kanama_ios_variant_to_object(args[0]);
                first_arg = kanama_ios_variant_to_double(args[0]);
                ok = object_arg != NULL
                    ? kanama_ios_runtime_script_instance_call_object(
                        instance->runtime_handle,
                        method_index,
                        (int64_t)(intptr_t)object_arg
                    )
                    : kanama_ios_runtime_script_instance_call(instance->runtime_handle, method_index, first_arg);
            }
        } else {
            ok = kanama_ios_runtime_script_instance_call(instance->runtime_handle, method_index, 0.0);
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
    if (g_main_loop_callback_frame_count >= 30) {
        g_main_loop_callbacks_active = 0;
        return;
    }
    g_main_loop_callback_frame_count++;
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

        // Color (4x f32): CanvasItem.set_modulate -> get_modulate (Node2D is a CanvasItem)
        float cin[4] = { 0.25f, 0.5f, 0.75f, 1.0f };
        const void *ca[1] = { cin };
        int32_t ct[1] = { KANAMA_IOS_PT_COLOR };
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("CanvasItem","set_modulate",2920490490),
            node2d, ct, ca, 1, KANAMA_IOS_PT_VOID, NULL);
        float cout[4] = { 0, 0, 0, 0 };
        kanama_ios_godot_ptrcall(kanama_ios_godot_get_method_bind("CanvasItem","get_modulate",3444240500),
            node2d, NULL, NULL, 0, KANAMA_IOS_PT_COLOR, cout);
        KANAMA_IOS_ST_CHECK("color", cout[0]==0.25f && cout[1]==0.5f && cout[2]==0.75f && cout[3]==1.0f);
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

    fprintf(stderr, "[kanama][ios][c] PTRCALL SELFTEST MATRIX: %d passed, %d failed\n", pass, fail);
    fflush(stderr);
#undef KANAMA_IOS_ST_CHECK

    // Also exercise the full Kotlin ObjectCalls path (Kotlin -> generic dispatch -> Godot).
    kanama_ios_runtime_objectcalls_selftest();
}
#endif // KANAMA_IOS_DEBUG_VARIANT_CHECKS

static void kanama_ios_initialize(void *userdata, GDExtensionInitializationLevel level) {
    (void)userdata;
    fprintf(stderr, "[kanama][ios][c] initialize: level=%d\n", (int)level);
    if (level == GDEXTENSION_INITIALIZATION_SCENE) {
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
