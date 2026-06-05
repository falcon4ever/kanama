/*
 * Kanama iOS GDExtension bootstrap.
 *
 * iOS cannot use the desktop JVM/Panama bootstrap. This shim keeps the Godot
 * entry point in C and delegates lifecycle markers to a Kotlin/Native static
 * library linked into the same xcframework.
 */

#include <stdint.h>
#include <stdio.h>
#include <string.h>

#include "gdextension_interface.h"

extern int32_t kanama_ios_runtime_entry(uintptr_t p_get_proc_address, uintptr_t p_library, uintptr_t r_initialization);
extern void kanama_ios_runtime_initialize(int32_t level);
extern void kanama_ios_runtime_deinitialize(int32_t level);
extern void kanama_ios_runtime_frame(void);

static int g_kanama_ios_initialized = 0;
static GDExtensionInterfaceGetProcAddress g_get_proc_address = NULL;
static GDExtensionClassLibraryPtr g_library = NULL;

static GDExtensionInterfaceStringNameNewWithUtf8Chars g_string_name_new = NULL;
static GDExtensionInterfaceStringNewWithUtf8Chars g_string_new = NULL;
static GDExtensionInterfaceVariantGetPtrDestructor g_variant_get_ptr_destructor = NULL;
static GDExtensionPtrDestructor g_string_name_destructor = NULL;
static GDExtensionPtrDestructor g_string_destructor = NULL;
static GDExtensionInterfaceGlobalGetSingleton g_global_get_singleton = NULL;
static GDExtensionInterfaceClassdbGetMethodBind g_classdb_get_method_bind = NULL;
static GDExtensionInterfaceObjectMethodBindPtrcall g_object_method_bind_ptrcall = NULL;
static GDExtensionInterfaceRegisterMainLoopCallbacks g_register_main_loop_callbacks = NULL;

static GDExtensionObjectPtr g_engine_singleton = NULL;
static GDExtensionMethodBindPtr g_engine_get_main_loop_bind = NULL;
static GDExtensionMethodBindPtr g_scene_tree_get_first_node_in_group_bind = NULL;
static GDExtensionMethodBindPtr g_label_set_text_bind = NULL;
static int g_main_loop_callbacks_registered = 0;
static int g_main_loop_callbacks_active = 0;

enum {
    KANAMA_IOS_VARIANT_TYPE_STRING = 4,
    KANAMA_IOS_VARIANT_TYPE_STRING_NAME = 21,
    KANAMA_IOS_ENGINE_GET_MAIN_LOOP_HASH = 1016888095U,
    KANAMA_IOS_SCENE_TREE_GET_FIRST_NODE_IN_GROUP_HASH = 4071044623U,
    KANAMA_IOS_LABEL_SET_TEXT_HASH = 83702148U,
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
    g_variant_get_ptr_destructor = (GDExtensionInterfaceVariantGetPtrDestructor)kanama_ios_lookup(
        "variant_get_ptr_destructor"
    );
    g_global_get_singleton = (GDExtensionInterfaceGlobalGetSingleton)kanama_ios_lookup(
        "global_get_singleton"
    );
    g_classdb_get_method_bind = (GDExtensionInterfaceClassdbGetMethodBind)kanama_ios_lookup(
        "classdb_get_method_bind"
    );
    g_object_method_bind_ptrcall = (GDExtensionInterfaceObjectMethodBindPtrcall)kanama_ios_lookup(
        "object_method_bind_ptrcall"
    );
    g_register_main_loop_callbacks = (GDExtensionInterfaceRegisterMainLoopCallbacks)kanama_ios_lookup(
        "register_main_loop_callbacks"
    );

    if (
        g_string_name_new == NULL ||
        g_string_new == NULL ||
        g_variant_get_ptr_destructor == NULL ||
        g_global_get_singleton == NULL ||
        g_classdb_get_method_bind == NULL ||
        g_object_method_bind_ptrcall == NULL ||
        g_register_main_loop_callbacks == NULL
    ) {
        fprintf(stderr, "[kanama][ios][c] error: failed to resolve required Godot API functions\n");
        return 0;
    }

    g_string_name_destructor = g_variant_get_ptr_destructor(KANAMA_IOS_VARIANT_TYPE_STRING_NAME);
    g_string_destructor = g_variant_get_ptr_destructor(KANAMA_IOS_VARIANT_TYPE_STRING);
    if (g_string_name_destructor == NULL || g_string_destructor == NULL) {
        fprintf(stderr, "[kanama][ios][c] error: failed to resolve Godot string destructors\n");
        return 0;
    }

    return 1;
}

static void kanama_ios_init_string_name(uint64_t *storage, const char *value) {
    *storage = 0;
    g_string_name_new((GDExtensionUninitializedStringNamePtr)storage, value);
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

static GDExtensionObjectPtr kanama_ios_engine_singleton(void) {
    if (g_engine_singleton != NULL) {
        return g_engine_singleton;
    }
    if (!kanama_ios_resolve_godot_api()) {
        return NULL;
    }

    uint64_t engine_name_storage = 0;
    kanama_ios_init_string_name(&engine_name_storage, "Engine");
    g_engine_singleton = g_global_get_singleton((GDExtensionConstStringNamePtr)&engine_name_storage);
    kanama_ios_destroy_string_name(&engine_name_storage);
    return g_engine_singleton;
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

static void kanama_ios_frame(void) {
    if (g_main_loop_callbacks_active) {
        kanama_ios_runtime_frame();
    }
}

static void kanama_ios_register_main_loop_callbacks(void) {
    if (g_main_loop_callbacks_registered) {
        g_main_loop_callbacks_active = 1;
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
    fprintf(stderr, "[kanama][ios][c] registered main loop frame callback\n");
}

static void kanama_ios_initialize(void *userdata, GDExtensionInitializationLevel level) {
    (void)userdata;
    fprintf(stderr, "[kanama][ios][c] initialize: level=%d\n", (int)level);
    if (level == GDEXTENSION_INITIALIZATION_SCENE) {
        kanama_ios_register_main_loop_callbacks();
    }
    kanama_ios_runtime_initialize((int32_t)level);
}

static void kanama_ios_deinitialize(void *userdata, GDExtensionInitializationLevel level) {
    (void)userdata;
    fprintf(stderr, "[kanama][ios][c] deinitialize: level=%d\n", (int)level);
    kanama_ios_runtime_deinitialize((int32_t)level);
    if (level == GDEXTENSION_INITIALIZATION_SCENE) {
        g_main_loop_callbacks_active = 0;
    }
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
