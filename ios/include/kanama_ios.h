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

int32_t kanama_ios_godot_set_first_node_in_group_text(
    const char *group_name,
    const char *value
);

#ifdef __cplusplus
}
#endif
