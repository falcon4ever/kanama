#pragma once

#include <stdint.h>

/*
 * Public C surface for Kanama's experimental iOS static xcframework.
 *
 * Godot discovers the actual GDExtension entry point through the exported
 * `kanama_entry` symbol and the project's `.gdextension` descriptor.
 *
 * The helpers below are intentionally tiny and C-shaped. Kotlin/Native calls
 * them through cinterop so ABI-sensitive Godot details stay on the C side.
 */

#ifdef __cplusplus
extern "C" {
#endif

void kanama_ios_godot_ptrcall(
    int64_t method_bind,
    int64_t instance,
    const int32_t *arg_types,
    const void *const *arg_ptrs,
    int32_t arg_count,
    int32_t ret_type,
    void *ret_out
);

/*
 * Static-method variant of kanama_ios_godot_ptrcall: same arg/ret handling, NULL
 * instance (Godot static methods ptrcall with a null object). The instance entry
 * point above rejects instance == 0, so statics must come through here.
 */
void kanama_ios_godot_ptrcall_static(
    int64_t method_bind,
    const int32_t *arg_types,
    const void *const *arg_ptrs,
    int32_t arg_count,
    int32_t ret_type,
    void *ret_out
);

int64_t kanama_ios_godot_get_method_bind(
    const char *class_name,
    const char *method_name,
    int64_t hash
);

/*
 * Resolve a builtin (value-type) method pointer (variant_get_ptr_builtin_method) —
 * the value-type analogue of get_method_bind. Returns the function pointer as int64
 * (0 on failure); cache it Kotlin-side like a MethodBind.
 */
int64_t kanama_ios_godot_get_builtin_method(
    int32_t variant_type,
    const char *method,
    int64_t hash
);

/*
 * Call a builtin (value-type) method: method_ptr(base, args, ret, argc). base and
 * ret_out are raw value byte buffers (e.g. float32 components); args are raw TYPE
 * bytes like kanama_ios_godot_ptrcall (POD passthrough; String/StringName/NodePath
 * built C-side from a C string via the arg_types tags). ret_out may be NULL for a
 * void return.
 */
void kanama_ios_godot_builtin_call(
    int64_t method_ptr,
    void *base,
    const int32_t *arg_types,
    const void *const *arg_ptrs,
    int32_t arg_count,
    void *ret_out
);

void kanama_ios_godot_ptrcall_string_arg(
    int64_t method_bind,
    int64_t instance,
    const char *value
);

/*
 * No-arg ptrcall returning a Godot String, marshalled to UTF-8.
 *
 * The String return value cannot flow through the fixed-size ret_out of the
 * generic ptrcall, so it gets its own helper: the call runs once, the returned
 * String is UTF-8 encoded into out_buf (no null terminator written), and the
 * String is destroyed. Returns the full encoded length in bytes (NOT including a
 * terminator), even when it exceeds buf_size. Pass out_buf=NULL / buf_size=0 to
 * measure the length only; the caller then allocates and calls again (safe — the
 * wired methods are pure getters). Returns -1 on a null method/instance or if the
 * Godot API is unavailable.
 */
int64_t kanama_ios_godot_ptrcall_no_args_ret_string(
    int64_t method_bind,
    int64_t instance,
    char *out_buf,
    int64_t buf_size
);

/*
 * No-arg ptrcall returning a Godot StringName, marshalled to UTF-8.
 *
 * GDExtension has no StringName->utf8, so the returned StringName is converted to a
 * String via the String(from: StringName) constructor and UTF-8 encoded from there.
 * Same buffer/length contract as kanama_ios_godot_ptrcall_no_args_ret_string.
 */
int64_t kanama_ios_godot_ptrcall_no_args_ret_string_name(
    int64_t method_bind,
    int64_t instance,
    char *out_buf,
    int64_t buf_size
);

/*
 * No-arg ptrcall returning a Godot NodePath, marshalled to UTF-8.
 *
 * GDExtension has no NodePath->utf8, so the returned NodePath is converted to a String
 * via the String(from: NodePath) constructor and UTF-8 encoded from there. Same
 * buffer/length contract as kanama_ios_godot_ptrcall_no_args_ret_string.
 */
int64_t kanama_ios_godot_ptrcall_no_args_ret_node_path(
    int64_t method_bind,
    int64_t instance,
    char *out_buf,
    int64_t buf_size
);

/*
 * No-arg ptrcall returning a Godot PackedInt32Array, read back into int32 elements.
 *
 * ptrcall writes the returned array; its element count comes from the "size" builtin
 * method and each element from operator_index_const. Two-call length protocol like the
 * String/NodePath helpers: pass out_buf=NULL to learn the count, then call again with a
 * buffer of that capacity. Writes up to buf_cap elements; returns the FULL element count
 * (negative on resolution failure). buf_cap is an ELEMENT count, not a byte size.
 */
int64_t kanama_ios_godot_ptrcall_no_args_ret_packed_int32_array(
    int64_t method_bind,
    int64_t instance,
    int32_t *out_buf,
    int64_t buf_cap
);

/*
 * No-arg ptrcall returning a Godot PackedFloat32Array, read into float32 elements.
 * Same buffer/length contract as the PackedInt32Array variant (buf_cap is an ELEMENT
 * count); element type is float32.
 */
int64_t kanama_ios_godot_ptrcall_no_args_ret_packed_float32_array(
    int64_t method_bind,
    int64_t instance,
    float *out_buf,
    int64_t buf_cap
);

/*
 * No-arg ptrcall returning a Godot PackedVector2Array / PackedColorArray, read into a flat
 * float32 buffer. buf_cap is an ELEMENT count; out_buf receives buf_cap*2 (Vector2) or
 * buf_cap*4 (Color) floats. Element components are float32 on iOS (real_t single precision).
 */
int64_t kanama_ios_godot_ptrcall_no_args_ret_packed_vector2_array(
    int64_t method_bind,
    int64_t instance,
    float *out_buf,
    int64_t buf_cap
);
int64_t kanama_ios_godot_ptrcall_no_args_ret_packed_color_array(
    int64_t method_bind,
    int64_t instance,
    float *out_buf,
    int64_t buf_cap
);

/*
 * PackedByteArray return with optional PT-tagged args and an optional-static instance
 * (pass instance = 0 for static methods like FileAccess.get_file_as_bytes). Args use the
 * generic-dispatch tag set. Read-back is one contiguous memcpy from element 0. Two-call
 * length protocol like the other packed read-backs; buf_cap is an ELEMENT (byte) count.
 * Returns the FULL element count (negative on resolution failure).
 */
int64_t kanama_ios_godot_ptrcall_ret_packed_byte_array(
    int64_t method_bind,
    int64_t instance,
    const int32_t *arg_types,
    const void *const *arg_ptrs,
    int32_t arg_count,
    uint8_t *out_buf,
    int64_t buf_cap
);

/*
 * Single-arg, void-return ptrcall taking a Godot PackedFloat32Array built from the caller's
 * flat float buffer (the inverse of the read-back: constructor + push_back per element).
 */
void kanama_ios_godot_ptrcall_with_packed_float32_arg(
    int64_t method_bind,
    int64_t instance,
    const float *elems,
    int64_t count
);

/*
 * No-arg ptrcall returning a Godot PackedStringArray, serialized into out_buf as a length-
 * prefixed blob: [count:int32][len0:int32][utf8_0][len1:int32][utf8_1]...  Two-call protocol:
 * pass out_buf=NULL to learn the total byte size, then call again with a buffer of that size.
 * Returns the FULL byte size (negative on resolution failure).
 */
int64_t kanama_ios_godot_ptrcall_no_args_ret_packed_string_array(
    int64_t method_bind,
    int64_t instance,
    char *out_buf,
    int64_t buf_size
);

/*
 * ptrcall (no args) -> a typed builtin Array, serialized to a length-prefixed blob
 * [int32 count]([int32 byteLen][bytes])*. elem_kind (KANAMA_IOS_PT_*) selects the element
 * decode: INT32/INT64 -> 8-byte int64; STRING/STRING_NAME/NODE_PATH -> utf8. Two-call length
 * protocol (out_buf=NULL/buf_size=0 measures). Returns total bytes (>= 4), or -1 on error.
 */
int64_t kanama_ios_godot_ptrcall_no_args_ret_typed_array_blob(
    int64_t method_bind,
    int64_t instance,
    int32_t elem_kind,
    char *out_buf,
    int64_t buf_size
);

/*
 * ptrcall (with PT-tagged args) -> a generic Array, serialized to a self-describing blob
 * [int32 count]([int32 variant_type][int32 byteLen][bytes])*. Scalar elements
 * (bool/int/float/object-handle/String|StringName|NodePath utf8) are decoded; non-scalar
 * elements keep their type tag with byteLen 0 (Kotlin surfaces null). Two-call length protocol.
 */
int64_t kanama_ios_godot_ptrcall_ret_variant_array_blob(
    int64_t method_bind,
    int64_t instance,
    const int32_t *arg_types,
    const void *const *arg_ptrs,
    int32_t arg_count,
    char *out_buf,
    int64_t buf_size
);

/*
 * PhysicsDirectSpaceState3D.intersect_ray-style call (one PhysicsRayQueryParameters3D object arg,
 * laid out as for kanama_ios_godot_ptrcall) -> the result Dictionary decoded into a fixed blob:
 *   [int32 hit]; if hit: [3x float32 position][3x float32 normal][int64 collider][int64 collider_id][int64 shape].
 * Returns bytes written (4 no-hit, 52 hit) or -1. out_buf must hold >= 52 bytes.
 */
int64_t kanama_ios_godot_ptrcall_ret_raycast_dict(
    int64_t method_bind,
    int64_t instance,
    const int32_t *arg_types,
    const void *const *arg_ptrs,
    int32_t arg_count,
    char *out_buf,
    int64_t buf_size
);

/*
 * Build a Godot Array of RIDs (each a uint64 id) and pass it as the single ptrcall argument, e.g.
 * PhysicsRayQueryParameters3D.set_exclude. Best-effort: no-op if the Array/RID primitives are missing.
 */
void kanama_ios_godot_ptrcall_with_rid_array_arg(
    int64_t method_bind,
    int64_t instance,
    const int64_t *rids,
    int32_t count
);

/*
 * ResourceLoader.load_threaded_get_status(path, progress) ptrcall with the optional progress
 * out-Array actually supplied; the engine's element 0 (a float in [0,1]) is written to
 * *out_progress when present. Returns the ThreadLoadStatus enum, or -1 on error.
 */
int64_t kanama_ios_godot_ptrcall_load_status_with_progress(
    int64_t method_bind,
    int64_t instance,
    const char *path,
    double *out_progress
);

/*
 * Descriptor for a BUILD-tagged Packed*Array argument passed through the generic ptrcall
 * dispatcher (KANAMA_IOS_PT_PACKED_* tags). `data` is a flat element buffer: float32 pairs
 * for Vector2 (count*2 floats), float32 quads for Color (count*4 floats). The dispatch
 * builds the Godot array from it (constructor + push_back) and destroys it after the call.
 */
typedef struct {
    int64_t count;
    const void *data;
} KanamaIosPackedArgDesc;

/*
 * Descriptor for a Callable argument passed through the generic ptrcall dispatcher
 * (KANAMA_IOS_PT_CALLABLE tag). `object_handle` is the target GodotObject pointer as an
 * int64 and `method` is its method name as a C string. The dispatch builds an object+method
 * Callable (Callable constructor index 2, the same one BuiltinTypes.initCallable pins on
 * desktop) into a cell, passes it to ptrcall, and destroys the cell after the call. No
 * Kotlin-side state outlives the call, so there is nothing to leak regardless of how long
 * the engine retains its copy (see docs/internals/historical/callable-args-design.md,
 * Decisions 1-3, 5).
 */
typedef struct {
    int64_t object_handle;
    const char *method;
} KanamaIosCallableArgDesc;

/*
 * Typed-object-array (Array[Object]) ptrcall return -> object handles. Drives the call through
 * the generic dispatcher (arg_types/arg_ptrs/arg_count laid out as for kanama_ios_godot_ptrcall),
 * returning an Array whose elements' object pointers are written into out_handles. Two-call length
 * protocol: pass out_handles=NULL to learn the count, then call again with a buffer of that
 * capacity (cap is an ELEMENT count). Returns the FULL element count (negative on resolution
 * failure). Non-Object elements yield a 0 handle.
 */
int64_t kanama_ios_godot_ptrcall_ret_object_array(
    int64_t method_bind,
    int64_t instance,
    const int32_t *arg_types,
    const void *const *arg_ptrs,
    int32_t arg_count,
    int64_t *out_handles,
    int64_t cap
);

int64_t kanama_ios_godot_construct_object(const char *class_name);

int64_t kanama_ios_godot_get_singleton(const char *name);

/*
 * Destroy an engine Object immediately (GDExtension object_destroy). RefCounted
 * release primitive (task 31 iOS mirror): call only after RefCounted.unreference()
 * returned true (refcount hit zero).
 */
void kanama_ios_godot_object_destroy(int64_t object);

void kanama_ios_godot_object_queue_free(int64_t object);

void kanama_ios_godot_node_add_child(int64_t parent, int64_t child);

void kanama_ios_godot_node_remove_child(int64_t parent, int64_t child);

int64_t kanama_ios_godot_node_get_child_count(int64_t node);

int64_t kanama_ios_godot_node_get_child(int64_t node, int32_t index);

int32_t kanama_ios_godot_object_is_class(int64_t object, const char *class_name);

int32_t kanama_ios_godot_node_is_in_group(int64_t node, const char *group_name);

int32_t kanama_ios_godot_input_event_is_pressed(int64_t event);

int32_t kanama_ios_godot_input_event_is_released(int64_t event);

int64_t kanama_ios_godot_input_event_mouse_button_get_button_index(int64_t event);

int64_t kanama_ios_godot_node_get_node_or_null(int64_t node, const char *path);

int64_t kanama_ios_godot_node_get_tree(int64_t node);

int64_t kanama_ios_godot_node_get_viewport(int64_t node);

int64_t kanama_ios_godot_node_create_tween(int64_t node);

void kanama_ios_godot_node_set_process_input(int64_t node, int32_t enabled);

void kanama_ios_godot_node_set_process_unhandled_input(int64_t node, int32_t enabled);

void kanama_ios_godot_node2d_get_position(int64_t node, double *x, double *y);

void kanama_ios_godot_node2d_set_position(int64_t node, double x, double y);

void kanama_ios_godot_node2d_get_scale(int64_t node, double *x, double *y);

void kanama_ios_godot_node2d_set_scale(int64_t node, double x, double y);

void kanama_ios_godot_node3d_get_position(int64_t node, double *x, double *y, double *z);

void kanama_ios_godot_node3d_set_position(int64_t node, double x, double y, double z);

void kanama_ios_godot_node3d_get_rotation(int64_t node, double *x, double *y, double *z);

void kanama_ios_godot_node3d_set_rotation(int64_t node, double x, double y, double z);

void kanama_ios_godot_node3d_get_scale(int64_t node, double *x, double *y, double *z);

void kanama_ios_godot_node3d_set_scale(int64_t node, double x, double y, double z);

void kanama_ios_godot_node3d_get_global_position(int64_t node, double *x, double *y, double *z);

void kanama_ios_godot_node3d_set_global_position(int64_t node, double x, double y, double z);

void kanama_ios_godot_node3d_rotate_y(int64_t node, double angle);

void kanama_ios_godot_canvas_item_get_viewport_rect(
    int64_t object,
    double *x,
    double *y,
    double *width,
    double *height
);

void kanama_ios_godot_canvas_item_get_local_mouse_position(int64_t object, double *x, double *y);

void kanama_ios_godot_canvas_item_hide(int64_t object);

void kanama_ios_godot_canvas_item_show(int64_t object);

void kanama_ios_godot_canvas_item_set_modulate(
    int64_t object,
    double r,
    double g,
    double b,
    double a
);

int64_t kanama_ios_godot_packed_scene_instantiate(int64_t packed_scene, int64_t edit_state);

void kanama_ios_godot_gpu_particles2d_set_emitting(int64_t particles, int32_t value);

void kanama_ios_godot_gpu_particles2d_set_lifetime(int64_t particles, double value);

void kanama_ios_godot_gpu_particles2d_restart(int64_t particles, int32_t keep_seed);

void kanama_ios_godot_gpu_particles3d_set_emitting(int64_t particles, int32_t value);

void kanama_ios_godot_gpu_particles3d_restart(int64_t particles, int32_t keep_seed);

void kanama_ios_godot_collision_shape3d_set_disabled(int64_t shape, int32_t disabled);

int64_t kanama_ios_godot_resource_loader_load(const char *path, const char *type_hint);

void kanama_ios_godot_sprite2d_set_texture(int64_t sprite, int64_t texture);

void kanama_ios_godot_audio_stream_player_set_stream(int64_t player, int64_t stream);
void kanama_ios_godot_audio_stream_player_set_volume_db(int64_t player, double volume_db);
void kanama_ios_godot_audio_stream_player_set_pitch_scale(int64_t player, double pitch_scale);
void kanama_ios_godot_audio_stream_player_set_bus(int64_t player, const char *bus);
void kanama_ios_godot_audio_stream_player_set_stream_paused(int64_t player, int32_t paused);
void kanama_ios_godot_audio_stream_player_play(int64_t player, double from_position);

int32_t kanama_ios_godot_object_emit_signal_int(
    int64_t object,
    const char *signal_name,
    int64_t value
);

int32_t kanama_ios_godot_object_emit_signal_vector2i(
    int64_t object,
    const char *signal_name,
    int64_t x,
    int64_t y
);

int64_t kanama_ios_godot_object_connect(
    int64_t object,
    const char *signal_name,
    int64_t target_object,
    const char *method_name,
    int64_t flags
);

int64_t kanama_ios_godot_object_connect_callable(
    int64_t object,
    const char *signal_name,
    int64_t target_object,
    int64_t callback_id,
    int64_t flags
);

/*
 * Object.disconnect(signal, <the lambda Callable for callback_id>) — teardown for a lambda
 * connected via object_connect_callable. Recreates the identity-equal custom Callable and
 * disconnects it. Returns 0 on a clean dispatch, -1 otherwise.
 */
int32_t kanama_ios_godot_object_disconnect_callable(
    int64_t object,
    const char *signal_name,
    int64_t callback_id
);

/*
 * Generic Variant Object.call dispatch (the varargs / dynamic path ptrcall can't
 * express). arg_tags[i] is a KANAMA_IOS_PT_* tag and arg_ptrs[i] points to that
 * arg's payload (uint8 bool / int64 / double / C-string for String-family / int64
 * handle for Object / laid-out float32|int32 for Vector2/Vector2i/Color). The args
 * are boxed into Variants, method_bind is invoked via object_method_bind_call, and a
 * SCALAR return is decoded: bool/int/Object-handle into *out_int, float into
 * *out_double, String UTF-8 into out_str (no terminator; *out_str_len gets the full
 * byte length, pass out_str=NULL/out_str_size=0 to measure). Returns the decoded
 * Variant type tag (KANAMA_IOS_VARIANT_TYPE_*), or -1 if the call did not dispatch.
 */
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
);

/*
 * task 43 — owned ClassDB.instantiate call: the return Variant holds the sole reference
 * to the freshly minted object, so the generic object_call would free a RefCounted
 * instance when it destroys the Variant. Retains RefCounted results before the Variant
 * destroy (*out_is_refcounted = 1: the caller owns that +1; release via
 * RefCounted close()/unreference). Non-RefCounted results come back borrowed.
 * Returns the object handle, or 0 on failure.
 */
int64_t kanama_ios_classdb_instantiate_owned(
    int64_t method_bind,
    int64_t instance,
    const char *class_name,
    int32_t *out_is_refcounted
);

/* Object.disconnect(signal, Callable(target, method)) — symmetric to object_connect. */
int32_t kanama_ios_godot_object_disconnect(
    int64_t object,
    const char *signal_name,
    int64_t target_object,
    const char *method_name
);

/*
 * Object.connect(signal, Callable(target, method).bindv([boundArgs]), flags) — connect with
 * extra bound args appended to the Callable. boundArgs arrive PT-tagged like object_call.
 * Returns the Godot Error (0 == OK), or -1 if the call didn't dispatch.
 */
int64_t kanama_ios_godot_object_connect_bound(
    int64_t object,
    const char *signal_name,
    int64_t target_object,
    const char *method_name,
    const int32_t *arg_tags,
    const void *const *arg_ptrs,
    int32_t arg_count,
    int64_t flags
);

/* Symmetric teardown: Object.disconnect(signal, Callable(target, method).bindv([boundArgs])). */
int32_t kanama_ios_godot_object_disconnect_bound(
    int64_t object,
    const char *signal_name,
    int64_t target_object,
    const char *method_name,
    const int32_t *arg_tags,
    const void *const *arg_ptrs,
    int32_t arg_count
);

int64_t kanama_ios_godot_tween_tween_property_vector2(
    int64_t tween,
    int64_t target,
    const char *property,
    double x,
    double y,
    double duration
);

int64_t kanama_ios_godot_tween_tween_property_color(
    int64_t tween,
    int64_t target,
    const char *property,
    double r,
    double g,
    double b,
    double a,
    double duration
);

int64_t kanama_ios_godot_tween_set_parallel(int64_t tween, int32_t parallel);

int64_t kanama_ios_godot_tween_tween_callback(int64_t tween, int64_t target, const char *method);

int64_t kanama_ios_godot_tween_tween_method(
    int64_t tween,
    int64_t target,
    const char *method,
    double from,
    double to,
    double duration
);

int64_t kanama_ios_godot_property_tweener_from_color(
    int64_t tweener,
    double r,
    double g,
    double b,
    double a
);

void kanama_ios_godot_tween_kill(int64_t tween);

int64_t kanama_ios_godot_tweener_set_trans(int64_t tweener, int64_t trans);

int64_t kanama_ios_godot_tweener_set_ease(int64_t tweener, int64_t ease);

void kanama_ios_godot_viewport_get_visible_rect(
    int64_t viewport,
    double *x,
    double *y,
    double *width,
    double *height
);

int32_t kanama_ios_godot_set_first_node_in_group_text(
    const char *group_name,
    const char *value
);

#ifdef __cplusplus
}
#endif
