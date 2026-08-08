/*
 * bootstrap.c — Kanama GDExtension native bootstrap.
 *
 * Phase 1b: starts a JVM and hands off to KanamaBinding.kt via the JNI
 * invocation API. This is the only file in the project that touches JNI,
 * and it touches it exactly three times (CreateJavaVM, FindClass,
 * CallStaticVoidMethod). Once `KanamaBinding.init` returns, JNI is done
 * for the lifetime of the process — all further Godot ⇄ JVM traffic
 * goes through Panama (FFM).
 *
 * Layout assumption: kanama.jar lives in the same directory as this
 * dylib. We use dladdr() to find our own dylib path at runtime so the
 * extension is portable.
 *
 * libjvm is dlopen'd at runtime (rather than linked at build time)
 * so the bootstrap library doesn't bake in an absolute path to a
 * specific Java install. An app-relative bundled `runtime/` image
 * (exported games, task 63 / issue #102) is probed first, then
 * JAVA_HOME; platform-specific fallbacks are best-effort only.
 */

#if defined(__linux__) && !defined(_GNU_SOURCE)
#define _GNU_SOURCE
#endif

#include <ctype.h>
#ifdef __APPLE__
#include <crt_externs.h>
#endif
#ifdef _WIN32
#define WIN32_LEAN_AND_MEAN
#include <io.h>
#include <windows.h>
#define access _access
#define F_OK 0
#define PATH_SEP "\\"
/* Declared only when the SDK targets Windows 8+; the values are stable ABI. */
#ifndef LOAD_LIBRARY_SEARCH_DLL_LOAD_DIR
#define LOAD_LIBRARY_SEARCH_DLL_LOAD_DIR 0x00000100
#endif
#ifndef LOAD_LIBRARY_SEARCH_USER_DIRS
#define LOAD_LIBRARY_SEARCH_USER_DIRS 0x00000400
#endif
#ifndef LOAD_LIBRARY_SEARCH_DEFAULT_DIRS
#define LOAD_LIBRARY_SEARCH_DEFAULT_DIRS 0x00001000
#endif
typedef void *(WINAPI *AddDllDirectoryFn)(const wchar_t *);
#else
#include <dlfcn.h>
#include <unistd.h>
#define PATH_SEP "/"
#endif
#include <stdio.h>
#include <stdint.h>
#include <stdlib.h>
#include <string.h>

#include <jni.h>

#include "gdextension_interface.h"

typedef jint (JNICALL *CreateJavaVMFn)(JavaVM **, void **, void *);

static JavaVM *g_jvm = NULL;
static JNIEnv *g_env = NULL;
static int g_kanama_initialized = 0;

#ifdef __ANDROID__
JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved) {
    (void)reserved;
    g_jvm = vm;
    return JNI_VERSION_1_6;
}

JNIEXPORT void JNICALL Java_net_multigesture_kanama_android_KanamaAndroidBootstrap_captureJvm(
    JNIEnv *env,
    jclass cls
) {
    (void)cls;
    if (env != NULL) {
        (*env)->GetJavaVM(env, &g_jvm);
    }
}
#endif

/* ------------------------------------------------------------------ */
/* Path discovery                                                     */
/* ------------------------------------------------------------------ */

static char *path_parent_in_place(char *path) {
    if (!path || !*path) {
        return path;
    }
    size_t len = strlen(path);
    while (len > 0 && (path[len - 1] == '/' || path[len - 1] == '\\')) {
        path[--len] = '\0';
    }
    while (len > 0) {
        char c = path[len - 1];
        if (c == '/' || c == '\\') {
            path[len - 1] = '\0';
            return path;
        }
        len--;
    }
    return path;
}

#ifdef _WIN32
/* Convert a wide Windows path to the process ANSI code page, which is what the
 * rest of this file speaks (and what the JVM's char* option strings take).
 * A path that is not representable in the ANSI code page — a player whose
 * profile is C:\Users\Müller on a non-Latin-1 system, say — would come back
 * with '?' substitutions and then fail every access() probe, so fall back to
 * the 8.3 short path, which is always ASCII. When the ANSI code page IS UTF-8
 * (Windows 10 1903+ "Use Unicode UTF-8" or a UTF-8 manifest) nothing is lossy
 * and WideCharToMultiByte rejects the lpUsedDefaultChar argument, so skip it. */
static int win_wide_to_ansi_path(const wchar_t *wide, char *out, size_t out_size) {
    UINT code_page = GetACP();
    BOOL lossy = FALSE;
    BOOL *lossy_out = (code_page == CP_UTF8) ? NULL : &lossy;
    int written = WideCharToMultiByte(code_page, 0, wide, -1, out, (int)out_size, NULL, lossy_out);
    if (written > 0 && !lossy) {
        return 0;
    }
    wchar_t short_path[1024];
    DWORD short_len =
        GetShortPathNameW(wide, short_path, (DWORD)(sizeof short_path / sizeof short_path[0]));
    if (short_len == 0 || short_len >= sizeof short_path / sizeof short_path[0]) {
        /* No 8.3 alias (short names can be disabled per volume): keep the
         * lossy conversion so the diagnostics at least name a path. */
        return written > 0 ? 0 : -1;
    }
    written = WideCharToMultiByte(code_page, 0, short_path, -1, out, (int)out_size, NULL, NULL);
    return written > 0 ? 0 : -1;
}

static int win_ansi_to_wide_path(const char *path, wchar_t *out, size_t out_count) {
    int written = MultiByteToWideChar(GetACP(), 0, path, -1, out, (int)out_count);
    return written > 0 ? 0 : -1;
}
#endif

static const char *jvm_relative_lib_path(void) {
#ifdef _WIN32
    /* Windows keeps the server JVM under bin\server, not lib/server. */
    return "bin\\server\\jvm.dll";
#elif defined(__APPLE__)
    return "lib/server/libjvm.dylib";
#else
    return "lib/server/libjvm.so";
#endif
}

static void build_java_home_jvm_path(char *path, size_t path_size, const char *java_home) {
#ifdef _WIN32
    snprintf(path, path_size, "%s\\%s", java_home, jvm_relative_lib_path());
#else
    snprintf(path, path_size, "%s/%s", java_home, jvm_relative_lib_path());
#endif
}

static void print_missing_jvm_diagnostic(void) {
#ifndef __ANDROID__
    const char *java_home = getenv("JAVA_HOME");
    fprintf(stderr, "[kanama] error: libjvm not found. Kanama desktop runtime requires a JDK 25+ install.\n");
    fprintf(stderr, "[kanama] no bundled runtime%s%s found next to the Kanama bootstrap library.\n",
            PATH_SEP, jvm_relative_lib_path());
    if (java_home && *java_home) {
        char expected[1024];
        build_java_home_jvm_path(expected, sizeof expected, java_home);
        fprintf(stderr, "[kanama] checked JAVA_HOME=%s but did not find %s\n", java_home, expected);
    } else {
        fprintf(stderr, "[kanama] JAVA_HOME is not set. Set it to a JDK 25+ home directory.\n");
    }
    fprintf(stderr, "[kanama] expected libjvm relative path: %s\n", jvm_relative_lib_path());
    fprintf(stderr, "[kanama] install hint: use Temurin 25+ or another JDK 25+ build that includes libjvm.\n");
#endif
}

/* Directory containing this bootstrap library. Anchor on the library, not
 * the executable: kanama.gdextension already anchors everything on it, and
 * Godot decides where the executable lives per platform. */
static int find_self_library_dir(char *out, size_t out_size) {
#ifdef _WIN32
    HMODULE module = NULL;
    if (!GetModuleHandleExW(
            GET_MODULE_HANDLE_EX_FLAG_FROM_ADDRESS | GET_MODULE_HANDLE_EX_FLAG_UNCHANGED_REFCOUNT,
            (LPCWSTR)(void *)find_self_library_dir,
            &module
        )) {
        return -1;
    }
    /* Wide, then converted once: the exported game may sit under a player
     * profile whose name has no ANSI representation (see
     * win_wide_to_ansi_path). GetModuleFileNameA would silently hand back a
     * path full of '?' and every probe below would miss. */
    wchar_t wide_lib_path[1024];
    DWORD len = GetModuleFileNameW(
        module, wide_lib_path, (DWORD)(sizeof wide_lib_path / sizeof wide_lib_path[0]));
    if (len == 0 || len >= sizeof wide_lib_path / sizeof wide_lib_path[0]) {
        return -1;
    }
    char lib_path[1024];
    if (win_wide_to_ansi_path(wide_lib_path, lib_path, sizeof lib_path) != 0) {
        return -1;
    }
#else
    Dl_info info;
    if (dladdr((void *)find_self_library_dir, &info) == 0 || info.dli_fname == NULL) {
        return -1;
    }
    char lib_path[1024];
    strncpy(lib_path, info.dli_fname, sizeof lib_path - 1);
    lib_path[sizeof lib_path - 1] = '\0';
#endif
    path_parent_in_place(lib_path);
    int n = snprintf(out, out_size, "%s", lib_path);
    if (n < 0 || (size_t)n >= out_size) {
        return -1;
    }
    return 0;
}

/* Task 63 (issue #102): exported games ship a jlink-trimmed `runtime/` image
 * that the bootstrap finds app-relative, so players never install a JDK.
 * Probe `runtime/<platform jvm layout>` in the bootstrap library's own
 * directory and up to two parents (mirroring the kanama.jar walk), plus a
 * `Resources/` subdirectory at each level, BEFORE the JAVA_HOME/dev-fallback
 * chain: a bundled runtime, when present, always wins; with none present the
 * dev workflow is unchanged. Expected layouts:
 *   windows: <dir>\runtime\bin\server\jvm.dll
 *   linux:   <dir>/runtime/lib/server/libjvm.so
 *   macOS:   <dir>/runtime/lib/server/libjvm.dylib
 *            (exported .app: the dylib is in Contents/Frameworks and the
 *            payload in Contents/Resources/ next to the .pck — the parent
 *            walk's Resources probe finds it there. codesign seals
 *            Resources/ by hash but rejects jars/loose runtime files as
 *            "nested code" under Frameworks/ or Contents/, so Resources is
 *            the only .app location that stays re-sealable after assembly.)
 */
static const char *find_bundled_runtime_jvm(void) {
    static char path[2048];
    char search_dir[1024];
    if (find_self_library_dir(search_dir, sizeof search_dir) != 0) {
        return NULL;
    }
    for (int depth = 0; depth < 3; depth++) {
        int n = snprintf(path, sizeof path, "%s%sruntime%s%s",
                         search_dir, PATH_SEP, PATH_SEP, jvm_relative_lib_path());
        if (n < 0 || (size_t)n >= sizeof path) {
            return NULL;
        }
        if (access(path, F_OK) == 0) {
            return path;
        }
        n = snprintf(path, sizeof path, "%s%sResources%sruntime%s%s",
                     search_dir, PATH_SEP, PATH_SEP, PATH_SEP, jvm_relative_lib_path());
        if (n < 0 || (size_t)n >= sizeof path) {
            return NULL;
        }
        if (access(path, F_OK) == 0) {
            return path;
        }
        char before_parent[1024];
        strncpy(before_parent, search_dir, sizeof before_parent - 1);
        before_parent[sizeof before_parent - 1] = '\0';
        path_parent_in_place(search_dir);
        if (strcmp(search_dir, before_parent) == 0 || search_dir[0] == '\0') {
            break;
        }
    }
    return NULL;
}

static const char *find_jvm_lib(void) {
    static char path[1024];
    const char *bundled = find_bundled_runtime_jvm();
    if (bundled) {
        fprintf(stderr, "[kanama] bundled runtime: %s\n", bundled);
        return bundled;
    }
    const char *java_home = getenv("JAVA_HOME");
    if (java_home && *java_home) {
        build_java_home_jvm_path(path, sizeof path, java_home);
        if (access(path, F_OK) == 0) {
            return path;
        }
    }

#ifdef _WIN32
    static const char *fallback_paths[] = {
        "C:\\Program Files\\Eclipse Adoptium\\jdk-25\\bin\\server\\jvm.dll",
    };
#elif defined(__APPLE__)
    static const char *fallback_paths[] = {
        "/Library/Java/JavaVirtualMachines/temurin-25.jdk/Contents/Home/lib/server/libjvm.dylib",
    };
#elif defined(__aarch64__)
    static const char *fallback_paths[] = {
        "/usr/lib/jvm/temurin-25-jdk-arm64/lib/server/libjvm.so",
        "/usr/lib/jvm/temurin-25-jdk/lib/server/libjvm.so",
        "/usr/lib/jvm/java-25-openjdk-arm64/lib/server/libjvm.so",
        "/usr/lib/jvm/java-25-openjdk/lib/server/libjvm.so",
    };
#elif defined(__x86_64__)
    static const char *fallback_paths[] = {
        "/usr/lib/jvm/temurin-25-jdk-amd64/lib/server/libjvm.so",
        "/usr/lib/jvm/temurin-25-jdk/lib/server/libjvm.so",
        "/usr/lib/jvm/java-25-openjdk-amd64/lib/server/libjvm.so",
        "/usr/lib/jvm/java-25-openjdk/lib/server/libjvm.so",
    };
#else
    static const char *fallback_paths[] = {
        "/usr/lib/jvm/temurin-25-jdk/lib/server/libjvm.so",
        "/usr/lib/jvm/java-25-openjdk/lib/server/libjvm.so",
    };
#endif
    for (size_t i = 0; i < sizeof(fallback_paths) / sizeof(fallback_paths[0]); i++) {
        if (access(fallback_paths[i], F_OK) == 0) {
            return fallback_paths[i];
        }
    }
    return NULL;
}

/* Find kanama.jar next to our own native library or in the addon root.
 * Mirrors find_bundled_runtime_jvm's walk: each level is probed directly and
 * through a `Resources/` subdirectory (exported macOS .app payload location,
 * see the task 63 comment above). */
static int find_jar_next_to_self(char *out, size_t out_size) {
    char search_dir[1024];
    if (find_self_library_dir(search_dir, sizeof search_dir) != 0) {
        return -1;
    }

    for (int depth = 0; depth < 3; depth++) {
        int n = snprintf(out, out_size, "%s%skanama.jar", search_dir, PATH_SEP);
        if (n < 0 || (size_t)n >= out_size) {
            return -1;
        }
        if (access(out, F_OK) == 0) {
            return 0;
        }
        n = snprintf(out, out_size, "%s%sResources%skanama.jar", search_dir, PATH_SEP, PATH_SEP);
        if (n < 0 || (size_t)n >= out_size) {
            return -1;
        }
        if (access(out, F_OK) == 0) {
            return 0;
        }
        char before_parent[1024];
        strncpy(before_parent, search_dir, sizeof before_parent - 1);
        before_parent[sizeof before_parent - 1] = '\0';
        path_parent_in_place(search_dir);
        if (strcmp(search_dir, before_parent) == 0 || search_dir[0] == '\0') {
            break;
        }
    }

    fprintf(stderr, "[kanama] error: kanama.jar not found near native bootstrap; last checked %s\n", out);
    return -1;
}

static int find_project_godot_from_jar(const char *jar_path, char *out, size_t out_size) {
    char path[2048];
    strncpy(path, jar_path, sizeof path - 1);
    path[sizeof path - 1] = '\0';

    char *kanama_dir = path_parent_in_place(path);
    if (!kanama_dir || !*kanama_dir) {
        return -1;
    }
    char addons_path[2048];
    strncpy(addons_path, kanama_dir, sizeof addons_path - 1);
    addons_path[sizeof addons_path - 1] = '\0';

    char *addons_dir = path_parent_in_place(addons_path);
    if (!addons_dir || !*addons_dir) {
        return -1;
    }
    char project_path[2048];
    strncpy(project_path, addons_dir, sizeof project_path - 1);
    project_path[sizeof project_path - 1] = '\0';

    char *project_dir = path_parent_in_place(project_path);
    if (!project_dir || !*project_dir) {
        return -1;
    }
    int n = snprintf(out, out_size, "%s%sproject.godot", project_dir, PATH_SEP);
    if (n < 0 || (size_t)n >= out_size) {
        return -1;
    }
    return access(out, F_OK) == 0 ? 0 : -1;
}

static char *trim_ascii(char *s) {
    while (*s && isspace((unsigned char)*s)) {
        s++;
    }
    char *end = s + strlen(s);
    while (end > s && isspace((unsigned char)*(end - 1))) {
        *(--end) = '\0';
    }
    return s;
}

static int parse_positive_port(const char *value) {
    if (!value || !*value) {
        return 0;
    }
    char *end = NULL;
    long port = strtol(value, &end, 10);
    if (end == value || port <= 0 || port > 65535) {
        return 0;
    }
    while (end && *end) {
        if (!isspace((unsigned char)*end)) {
            return 0;
        }
        end++;
    }
    return (int)port;
}

static int read_setting_from_project(const char *jar_path, const char *key, char *out, size_t out_size) {
    if (!out || out_size == 0) {
        return 0;
    }
    out[0] = '\0';
    char project_file[2048];
    if (find_project_godot_from_jar(jar_path, project_file, sizeof project_file) != 0) {
        return 0;
    }
    FILE *f = fopen(project_file, "r");
    if (!f) {
        return 0;
    }

    int in_kanama_section = 0;
    char line[1024];
    while (fgets(line, sizeof line, f)) {
        char *s = trim_ascii(line);
        if (*s == '\0' || *s == ';' || *s == '#') {
            continue;
        }
        if (*s == '[') {
            in_kanama_section = strcmp(s, "[kanama]") == 0;
            continue;
        }
        if (!in_kanama_section) {
            continue;
        }
        size_t key_len = strlen(key);
        if (strncmp(s, key, key_len) != 0) {
            continue;
        }
        s += key_len;
        s = trim_ascii(s);
        if (*s != '=') {
            continue;
        }
        s = trim_ascii(s + 1);
        snprintf(out, out_size, "%s", s);
        break;
    }
    fclose(f);
    return out[0] != '\0';
}

static int read_bool_setting_from_project(const char *jar_path, const char *key) {
    char value[64];
    if (!read_setting_from_project(jar_path, key, value, sizeof value)) {
        return 0;
    }
    return strcmp(value, "true") == 0 || strcmp(value, "True") == 0 || strcmp(value, "1") == 0;
}

static int read_port_setting_from_project(const char *jar_path, const char *key) {
    char value[64];
    if (!read_setting_from_project(jar_path, key, value, sizeof value)) {
        return 0;
    }
    return parse_positive_port(value);
}

static int is_editor_process(void) {
#ifdef __APPLE__
    int argc = *_NSGetArgc();
    char **argv = *_NSGetArgv();
    for (int i = 1; i < argc; i++) {
        if (strcmp(argv[i], "--editor") == 0 || strcmp(argv[i], "-e") == 0) {
            return 1;
        }
    }
#endif
    return 0;
}

static int is_numeric_port_string(const char *value) {
    if (!value || !*value) {
        return 0;
    }
    for (const char *p = value; *p; p++) {
        if (!isdigit((unsigned char)*p)) {
            return 0;
        }
    }
    return 1;
}

/* ------------------------------------------------------------------ */
/* JVM startup + Kotlin handoff                                       */
/* ------------------------------------------------------------------ */

#ifdef _WIN32
/* Task 63: a bundled Windows runtime keeps the server JVM in
 * <runtime>\bin\server\jvm.dll, but jvm.dll's own dependencies — ucrtbase.dll,
 * vcruntime140.dll, vcruntime140_1.dll, msvcp140.dll and the api-ms-win-* set —
 * ship one level up in <runtime>\bin. Windows resolves a loaded DLL's
 * dependencies against the *executable's* directory (Godot's), System32, and
 * PATH; it never looks next to the DLL being loaded. A plain LoadLibrary would
 * therefore boot only on machines that already have the Visual C++
 * redistributable installed — exactly the "install this first" failure a
 * bundled runtime exists to remove, and one that hides on developer machines.
 *
 * Register <runtime>\bin as a user search directory and load with the explicit
 * search flags. Only loads that pass those flags consult the user directories,
 * so Godot's own DLL resolution is untouched. Once jvm.dll is in, the JVM's
 * later loads of java.dll/net.dll/nio.dll/syslookup.dll bind to the same
 * already-resolved CRT modules by name. */
static HMODULE win_load_jvm_library(const char *jvm_lib) {
    wchar_t wide_jvm[1024];
    if (win_ansi_to_wide_path(jvm_lib, wide_jvm, sizeof wide_jvm / sizeof wide_jvm[0]) != 0) {
        return LoadLibraryA(jvm_lib);
    }

    char bin_dir[1024];
    snprintf(bin_dir, sizeof bin_dir, "%s", jvm_lib);
    path_parent_in_place(bin_dir); /* <runtime>\bin\server */
    path_parent_in_place(bin_dir); /* <runtime>\bin        */

    HMODULE kernel32 = GetModuleHandleW(L"kernel32.dll");
    AddDllDirectoryFn add_dll_directory =
        kernel32 ? (AddDllDirectoryFn)GetProcAddress(kernel32, "AddDllDirectory") : NULL;
    wchar_t wide_bin[1024];
    if (add_dll_directory && bin_dir[0] != '\0' &&
        win_ansi_to_wide_path(bin_dir, wide_bin, sizeof wide_bin / sizeof wide_bin[0]) == 0) {
        add_dll_directory(wide_bin);
    }

    HMODULE handle = LoadLibraryExW(
        wide_jvm,
        NULL,
        LOAD_LIBRARY_SEARCH_DEFAULT_DIRS | LOAD_LIBRARY_SEARCH_USER_DIRS |
            LOAD_LIBRARY_SEARCH_DLL_LOAD_DIR
    );
    if (!handle) {
        /* Pre-KB2533623 systems reject the search flags outright. */
        handle = LoadLibraryA(jvm_lib);
    }
    return handle;
}
#endif

static int start_jvm(const char *jar_path) {
#ifdef __ANDROID__
    (void)jar_path;
    if (!g_jvm) {
        fprintf(stderr, "[kanama] error: Android JavaVM* is not available; "
                        "load the Kanama Android plugin before the GDExtension\n");
        return -1;
    }

    JNIEnv *env = NULL;
    jint rc = (*g_jvm)->GetEnv(g_jvm, (void **)&env, JNI_VERSION_1_6);
    if (rc == JNI_EDETACHED) {
        rc = (*g_jvm)->AttachCurrentThread(g_jvm, &env, NULL);
    }
    if (rc != JNI_OK || env == NULL) {
        fprintf(stderr, "[kanama] error: AttachCurrentThread/GetEnv failed on Android: %d\n", (int)rc);
        return -1;
    }
    g_env = env;
    fprintf(stderr, "[kanama] Android ART thread attached\n");
    return 0;
#else
    const char *jvm_lib = find_jvm_lib();
    if (!jvm_lib) {
        print_missing_jvm_diagnostic();
        return -1;
    }
    fprintf(stderr, "[kanama] using libjvm: %s\n", jvm_lib);

#ifdef _WIN32
    HMODULE handle = win_load_jvm_library(jvm_lib);
    if (!handle) {
        fprintf(stderr, "[kanama] error: LoadLibrary failed for %s: %lu\n", jvm_lib, GetLastError());
        return -1;
    }
    CreateJavaVMFn create_vm = (CreateJavaVMFn)GetProcAddress(handle, "JNI_CreateJavaVM");
#else
    void *handle = dlopen(jvm_lib, RTLD_NOW | RTLD_GLOBAL);
    if (!handle) {
        fprintf(stderr, "[kanama] error: dlopen failed: %s\n", dlerror());
        return -1;
    }
    CreateJavaVMFn create_vm = (CreateJavaVMFn)dlsym(handle, "JNI_CreateJavaVM");
#endif
    if (!create_vm) {
        fprintf(stderr, "[kanama] error: JNI_CreateJavaVM symbol not found in libjvm\n");
        return -1;
    }

    char classpath_opt[2200];
    snprintf(classpath_opt, sizeof classpath_opt, "-Djava.class.path=%s", jar_path);

    char jdwp_opt[256] = {0};
    char jdwp_address[128] = {0};
    int n_opts = 2;
    const char *jdwp_port = getenv("KANAMA_JDWP_PORT");
    if (jdwp_port && *jdwp_port) {
        if (is_numeric_port_string(jdwp_port)) {
            snprintf(jdwp_address, sizeof jdwp_address, "*:%s", jdwp_port);
        } else {
            snprintf(jdwp_address, sizeof jdwp_address, "%s", jdwp_port);
        }
    } else {
        int editor = is_editor_process();
        if (editor) {
            int runtime_enabled = read_bool_setting_from_project(jar_path, "debug/jdwp_enabled");
            if (runtime_enabled && read_port_setting_from_project(jar_path, "debug/jdwp_port") > 0) {
                fprintf(stderr, "[kanama] JDWP runtime port configured; skipping editor process "
                                "(use KANAMA_JDWP_PORT for editor-side debugging)\n");
            }
        } else {
            int runtime_enabled = read_bool_setting_from_project(jar_path, "debug/jdwp_enabled");
            int project_port = runtime_enabled
                ? read_port_setting_from_project(jar_path, "debug/jdwp_port")
                : 0;
            if (runtime_enabled && project_port > 0) {
                snprintf(jdwp_address, sizeof jdwp_address, "*:%d", project_port);
            }
        }
    }
    if (jdwp_address[0] != '\0') {
        snprintf(jdwp_opt, sizeof jdwp_opt,
            "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=%s", jdwp_address);
        n_opts = 3;
        fprintf(stderr, "[kanama] JDWP debug agent enabled at %s\n", jdwp_address);
    }

    JavaVMOption options[3];
    options[0].optionString = classpath_opt;
    options[0].extraInfo = NULL;
    options[1].optionString = (char *)"--enable-native-access=ALL-UNNAMED";
    options[1].extraInfo = NULL;
    options[2].optionString = jdwp_opt;
    options[2].extraInfo = NULL;

    JavaVMInitArgs vm_args;
    vm_args.version = JNI_VERSION_21;
    vm_args.nOptions = n_opts;
    vm_args.options = options;
    vm_args.ignoreUnrecognized = JNI_FALSE;

    jint rc = create_vm(&g_jvm, (void **)&g_env, &vm_args);
    if (rc != JNI_OK) {
        fprintf(stderr, "[kanama] error: JNI_CreateJavaVM returned %d\n", (int)rc);
        return -1;
    }
    fprintf(stderr, "[kanama] JVM started, classpath=%s\n", jar_path);
    return 0;
#endif
}

static int call_kotlin_init(
    GDExtensionInterfaceGetProcAddress p_get_proc_address,
    GDExtensionClassLibraryPtr p_library,
    GDExtensionInitialization *r_initialization
) {
    jclass cls = (*g_env)->FindClass(g_env, "net/multigesture/kanama/KanamaBinding");
    if (!cls) {
        fprintf(stderr, "[kanama] error: FindClass KanamaBinding failed\n");
        if ((*g_env)->ExceptionCheck(g_env)) {
            (*g_env)->ExceptionDescribe(g_env);
            (*g_env)->ExceptionClear(g_env);
        }
        return -1;
    }
    jmethodID mid = (*g_env)->GetStaticMethodID(g_env, cls, "init", "(JJJ)V");
    if (!mid) {
        fprintf(stderr, "[kanama] error: GetStaticMethodID init(JJJ)V failed\n");
        if ((*g_env)->ExceptionCheck(g_env)) {
            (*g_env)->ExceptionDescribe(g_env);
            (*g_env)->ExceptionClear(g_env);
        }
        return -1;
    }
    (*g_env)->CallStaticVoidMethod(g_env, cls, mid,
        (jlong)(uintptr_t)p_get_proc_address,
        (jlong)(uintptr_t)p_library,
        (jlong)(uintptr_t)r_initialization);
    if ((*g_env)->ExceptionCheck(g_env)) {
        (*g_env)->ExceptionDescribe(g_env);
        (*g_env)->ExceptionClear(g_env);
        return -1;
    }
    return 0;
}

/* ------------------------------------------------------------------ */
/* Godot lifecycle callbacks (still C-side for Phase 1b)              */
/* ------------------------------------------------------------------ */

static void kanama_initialize(void *userdata, GDExtensionInitializationLevel level) {
    (void)userdata;
    fprintf(stderr, "[kanama] initialize: level=%d\n", (int)level);
}

static void kanama_deinitialize(void *userdata, GDExtensionInitializationLevel level) {
    (void)userdata;
    fprintf(stderr, "[kanama] deinitialize: level=%d\n", (int)level);
}

/* ------------------------------------------------------------------ */
/* GDExtension entry point                                            */
/* ------------------------------------------------------------------ */

/* Fill in the GDExtensionInitialization struct. Factored out so both
 * the first call and any idempotent re-entry can use it. */
static void fill_init_struct(GDExtensionInitialization *r_initialization) {
    r_initialization->minimum_initialization_level = GDEXTENSION_INITIALIZATION_SCENE;
    r_initialization->userdata = NULL;
    r_initialization->initialize = kanama_initialize;
    r_initialization->deinitialize = kanama_deinitialize;
}

#ifdef _WIN32
__declspec(dllexport)
#else
__attribute__((visibility("default")))
#endif
GDExtensionBool kanama_entry(
    GDExtensionInterfaceGetProcAddress p_get_proc_address,
    GDExtensionClassLibraryPtr p_library,
    GDExtensionInitialization *r_initialization
) {
    fprintf(stderr, "[kanama] entry: get_proc_address=%p library=%p\n",
            (void *)p_get_proc_address, (void *)p_library);

    /* Godot's loader may call kanama_entry more than once (filesystem
     * scan + actual load, or editor reloads). If Kanama already
     * installed its callbacks, just refill the init struct and return
     * success. On Android, g_jvm can be set earlier by JNI_OnLoad, so it
     * must not be used as the "Kanama initialized" sentinel. */
    if (g_kanama_initialized) {
        fprintf(stderr, "[kanama] re-entry: Kanama already initialized, skipping startup\n");
        fill_init_struct(r_initialization);
        return 1;
    }

#ifdef __ANDROID__
    const char *jar_path = NULL;
    fprintf(stderr, "[kanama] Android bootstrap: using host ART\n");
#else
    char jar_path[2048];
    if (find_jar_next_to_self(jar_path, sizeof jar_path) != 0) {
        return 0;
    }
    fprintf(stderr, "[kanama] jar: %s\n", jar_path);
#endif

    if (start_jvm(jar_path) != 0) {
        return 0;
    }

    /* Pre-populate with C-side defaults so we have a safe fallback
     * if KanamaBinding.init fails to install its own callbacks. */
    fill_init_struct(r_initialization);

    if (call_kotlin_init(p_get_proc_address, p_library, r_initialization) != 0) {
        return 0;
    }
    g_kanama_initialized = 1;

    /* KanamaBinding.init may have overwritten initialize/deinitialize
     * in the struct with Panama upcall stubs; whatever it left in
     * place is what Godot will see. */
    return 1;
}
