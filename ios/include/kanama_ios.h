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

int64_t kanama_ios_godot_get_method_bind(
    const char *class_name,
    const char *method_name,
    int64_t hash
);

void kanama_ios_godot_ptrcall_string_arg(
    int64_t method_bind,
    int64_t instance,
    const char *value
);

int64_t kanama_ios_godot_construct_object(const char *class_name);

int64_t kanama_ios_godot_get_singleton(const char *name);

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
    int64_t callback_id,
    int64_t flags
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
