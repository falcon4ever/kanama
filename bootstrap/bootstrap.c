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
 * (exported games, task 63 / issue #102) is probed first, then the JDK the
 * editor plugin recorded from the `kanama/build/jdk_path` setting, then
 * JAVA_HOME, then the newest JDK 25+ found in the usual install locations
 * (kanama#277; the plugin's preflight mirrors exactly this lookup).
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
#ifndef _WIN32
#include <dirent.h>
#include <sys/stat.h>
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

#ifndef __ANDROID__
static void build_java_home_jvm_path(char *path, size_t path_size, const char *java_home) {
#ifdef _WIN32
    snprintf(path, path_size, "%s\\%s", java_home, jvm_relative_lib_path());
#else
    snprintf(path, path_size, "%s/%s", java_home, jvm_relative_lib_path());
#endif
}
#endif /* !__ANDROID__ */

static char *trim_ascii(char *s);

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

#ifndef __ANDROID__
/* ------------------------------------------------------------------ */
/* JDK lookup (kanama#277)                                            */
/* ------------------------------------------------------------------ */
/*
 * With no bundled runtime, the JVM comes from, in order:
 *   1. the JDK in <project>/.godot/kanama_jdk_home, which the editor plugin writes
 *      from the explicit `kanama/build/jdk_path` editor setting (and deletes when
 *      the setting is empty or invalid);
 *   2. JAVA_HOME;
 *   3. the best JDK in the install locations below.
 * A candidate counts when its `release` file says JAVA_VERSION >= 25 and its
 * libjvm exists. Among install-location candidates: GA before EA, then exactly 25
 * before newer majors, then the newer version, then the smaller path. The plugin
 * (addons/kanama_tools/plugin.gd) implements the same lookup for its preflight,
 * and scripts/check_jdk_locations_parity.py holds the two location tables equal.
 *
 * KANAMA_TEST_JDK_SEARCH_DIRS (path-list of directories whose children are JDK homes)
 * replaces the built-in location table; it exists so tests can lay out fake JDKs.
 */
#define KANAMA_MIN_JDK_MAJOR 25
#define JDK_PATH_MAX 1100
#define JDK_REJECTED_MAX 8

typedef struct {
    int parts[8];
    int count;
    int ea;
} JdkVersion;

typedef struct {
    int found;
    JdkVersion version;
    char home[JDK_PATH_MAX];
    char libjvm[JDK_PATH_MAX];
} JdkBest;

typedef struct {
    const char *os;      /* "linux" | "macos" | "windows" | "all" */
    const char *parent;  /* "~" = home dir, "$ProgramFiles" = %ProgramFiles% */
    const char *suffix;  /* below each child, e.g. Contents/Home */
    const char *prefix;  /* only children whose name starts with this */
} JdkLocation;

/* KANAMA_JDK_LOCATIONS_BEGIN (same table, same order, in plugin.gd) */
static const JdkLocation k_jdk_locations[] = {
    {"linux", "/usr/lib/jvm", "", ""},
    {"linux", "/usr/lib64/jvm", "", ""},
    {"linux", "/usr/java", "", ""},
    {"linux", "/usr/local/java", "", ""},
    {"linux", "/opt/java", "", ""},
    {"linux", "/opt/jdk", "", ""},
    {"macos", "/Library/Java/JavaVirtualMachines", "Contents/Home", ""},
    {"macos", "~/Library/Java/JavaVirtualMachines", "Contents/Home", ""},
    {"macos", "/opt/homebrew/opt", "libexec/openjdk.jdk/Contents/Home", "openjdk"},
    {"macos", "/usr/local/opt", "libexec/openjdk.jdk/Contents/Home", "openjdk"},
    {"windows", "$ProgramFiles/Java", "", ""},
    {"windows", "$ProgramFiles/Eclipse Adoptium", "", ""},
    {"windows", "$ProgramFiles/Microsoft", "", ""},
    {"windows", "$ProgramFiles/Zulu", "", ""},
    {"windows", "$ProgramFiles/BellSoft", "", ""},
    {"windows", "$ProgramFiles/Amazon Corretto", "", ""},
    {"windows", "$ProgramFiles/Semeru", "", ""},
    {"windows", "$ProgramFiles/Temurin", "", ""},
    {"windows", "~/scoop/apps", "current", ""},
    {"all", "~/.jdks", "", ""},
    {"all", "~/.sdkman/candidates/java", "", ""},
};
/* KANAMA_JDK_LOCATIONS_END */

static const char *jdk_host_os(void) {
#ifdef _WIN32
    return "windows";
#elif defined(__APPLE__)
    return "macos";
#else
    return "linux";
#endif
}

static char g_jdk_rejected[JDK_REJECTED_MAX][JDK_PATH_MAX + 32];
static int g_jdk_rejected_count = 0;
static const char *g_jvm_source = "";
static char g_jvm_path[JDK_PATH_MAX];

static void jdk_note_rejected(const char *home, const char *why) {
    if (g_jdk_rejected_count < JDK_REJECTED_MAX) {
        snprintf(g_jdk_rejected[g_jdk_rejected_count], sizeof g_jdk_rejected[0], "%s (%s)", home, why);
        g_jdk_rejected_count++;
    }
}

/* "25.0.4.1" -> {25,0,4,1}; "26-ea" -> {26}, ea; "1.8.0_302" -> {8,0,302}.
 * A component saturates at 999999999 (the plugin's parse_java_version does the same). */
static void jdk_parse_version(const char *text, JdkVersion *out) {
    memset(out, 0, sizeof *out);
    out->ea = strstr(text, "-ea") != NULL;
    int digits = 0;
    int have_digits = 0;
    for (const char *c = text; *c; c++) {
        if (*c >= '0' && *c <= '9') {
            digits = digits < 100000000 ? digits * 10 + (*c - '0') : 999999999;
            have_digits = 1;
            continue;
        }
        if (have_digits) {
            if (out->count < 8) {
                out->parts[out->count++] = digits;
            }
            digits = 0;
            have_digits = 0;
        }
        if (*c != '.' && *c != '_' && *c != '+') {
            break;
        }
    }
    if (have_digits && out->count < 8) {
        out->parts[out->count++] = digits;
    }
    if (out->count >= 2 && out->parts[0] == 1) {
        for (int i = 1; i < out->count; i++) {
            out->parts[i - 1] = out->parts[i];
        }
        out->count--;
    }
}

static int jdk_compare_parts(const JdkVersion *a, const JdkVersion *b) {
    int count = a->count > b->count ? a->count : b->count;
    for (int i = 0; i < count; i++) {
        int left = i < a->count ? a->parts[i] : 0;
        int right = i < b->count ? b->parts[i] : 0;
        if (left != right) {
            return left > right ? 1 : -1;
        }
    }
    return 0;
}

/* Reads <home>/release's JAVA_VERSION. 0 on success, -1 no readable release file,
 * -2 a release file without a usable JAVA_VERSION. */
static int jdk_read_version(const char *home, JdkVersion *out) {
    char release_path[JDK_PATH_MAX + 16];
    snprintf(release_path, sizeof release_path, "%s%srelease", home, PATH_SEP);
    FILE *file = fopen(release_path, "r");
    if (!file) {
        return -1;
    }
    char line[512];
    char version[128];
    version[0] = '\0';
    while (fgets(line, sizeof line, file)) {
        if (strncmp(line, "JAVA_VERSION=", 13) == 0) {
            char *value = trim_ascii(line + 13);
            size_t len = strlen(value);
            if (len >= 2 && value[0] == '"' && value[len - 1] == '"') {
                value[len - 1] = '\0';
                value++;
            }
            snprintf(version, sizeof version, "%s", value);
            break;
        }
    }
    fclose(file);
    jdk_parse_version(version, out);
    return out->count > 0 ? 0 : -2;
}

/* A home is usable when its release says JDK >= 25 and its libjvm exists. */
static int jdk_usable(const char *home, JdkVersion *version, char *libjvm, size_t libjvm_size) {
    int read_rc = jdk_read_version(home, version);
    if (read_rc != 0) {
        jdk_note_rejected(home, read_rc == -1 ? "no readable release file" : "release file has no usable JAVA_VERSION");
        return 0;
    }
    if (version->parts[0] < KANAMA_MIN_JDK_MAJOR) {
        char why[48];
        snprintf(why, sizeof why, "JDK %d, need %d+", version->parts[0], KANAMA_MIN_JDK_MAJOR);
        jdk_note_rejected(home, why);
        return 0;
    }
    build_java_home_jvm_path(libjvm, libjvm_size, home);
    if (access(libjvm, F_OK) != 0) {
        jdk_note_rejected(home, "no libjvm");
        return 0;
    }
    return 1;
}

static int jdk_better(const JdkVersion *a, const char *a_home, const JdkVersion *b, const char *b_home) {
    if (a->ea != b->ea) {
        return !a->ea;
    }
    int a_exact = a->parts[0] == KANAMA_MIN_JDK_MAJOR;
    int b_exact = b->parts[0] == KANAMA_MIN_JDK_MAJOR;
    if (a_exact != b_exact) {
        return a_exact;
    }
    int cmp = jdk_compare_parts(a, b);
    if (cmp != 0) {
        return cmp > 0;
    }
    return strcmp(a_home, b_home) < 0;
}

static void jdk_consider(const char *home, JdkBest *best) {
    JdkVersion version;
    char libjvm[JDK_PATH_MAX];
    if (!jdk_usable(home, &version, libjvm, sizeof libjvm)) {
        return;
    }
    if (!best->found || jdk_better(&version, home, &best->version, best->home)) {
        best->found = 1;
        best->version = version;
        snprintf(best->home, sizeof best->home, "%s", home);
        snprintf(best->libjvm, sizeof best->libjvm, "%s", libjvm);
    }
}

static void jdk_normalize_separators(char *path) {
#ifdef _WIN32
    for (char *c = path; *c; c++) {
        if (*c == '/') {
            *c = '\\';
        }
    }
#else
    (void)path;
#endif
}

static const char *jdk_user_home(void) {
#ifdef _WIN32
    return getenv("USERPROFILE");
#else
    return getenv("HOME");
#endif
}

/* Expands a table parent ("~/x", "$ProgramFiles/x", "/abs") into out. 0 on success. */
static int jdk_expand_parent(const char *parent, char *out, size_t out_size) {
    int n;
    if (parent[0] == '~' && (parent[1] == '/' || parent[1] == '\0')) {
        const char *home = jdk_user_home();
        if (!home || !*home) {
            return -1;
        }
        n = snprintf(out, out_size, "%s%s", home, parent + 1);
    } else if (strncmp(parent, "$ProgramFiles", 13) == 0) {
        const char *pf = getenv("ProgramFiles");
        n = snprintf(out, out_size, "%s%s", (pf && *pf) ? pf : "C:\\Program Files", parent + 13);
    } else {
        n = snprintf(out, out_size, "%s", parent);
    }
    if (n < 0 || (size_t)n >= out_size) {
        return -1;
    }
    jdk_normalize_separators(out);
    return 0;
}

/* Considers every child of `parent` (name filtered by `prefix`) as a JDK home. */
static void jdk_scan_parent(const char *parent, const char *prefix, const char *suffix, JdkBest *best) {
    char suffix_native[128];
    snprintf(suffix_native, sizeof suffix_native, "%s", suffix);
    jdk_normalize_separators(suffix_native);
    size_t prefix_len = strlen(prefix);
    char home[JDK_PATH_MAX];
#ifdef _WIN32
    char pattern[JDK_PATH_MAX];
    snprintf(pattern, sizeof pattern, "%s\\*", parent);
    WIN32_FIND_DATAA entry;
    HANDLE find = FindFirstFileA(pattern, &entry);
    if (find == INVALID_HANDLE_VALUE) {
        return;
    }
    do {
        const char *name = entry.cFileName;
#else
    DIR *dir = opendir(parent);
    if (!dir) {
        return;
    }
    struct dirent *entry;
    while ((entry = readdir(dir)) != NULL) {
        const char *name = entry->d_name;
#endif
        if (name[0] == '.') {
            continue; /* hidden entries, "." and ".." (the plugin's directory listing skips them too) */
        }
#ifdef _WIN32
        if (!(entry.dwFileAttributes & FILE_ATTRIBUTE_DIRECTORY)) {
            continue;
        }
#endif
        if (prefix_len > 0 && strncmp(name, prefix, prefix_len) != 0) {
            continue;
        }
        int n = suffix_native[0]
            ? snprintf(home, sizeof home, "%s%s%s%s%s", parent, PATH_SEP, name, PATH_SEP, suffix_native)
            : snprintf(home, sizeof home, "%s%s%s", parent, PATH_SEP, name);
        if (n < 0 || (size_t)n >= sizeof home) {
            continue;
        }
#ifndef _WIN32
        {
            /* Only directories (a symlink to one counts), like the plugin's listing. */
            char child[JDK_PATH_MAX];
            struct stat st;
            snprintf(child, sizeof child, "%s%s%s", parent, PATH_SEP, name);
            if (stat(child, &st) != 0 || !S_ISDIR(st.st_mode)) {
                continue;
            }
        }
#endif
        jdk_consider(home, best);
#ifdef _WIN32
    } while (FindNextFileA(find, &entry));
    FindClose(find);
#else
    }
    closedir(dir);
#endif
}

static void jdk_scan_install_locations(JdkBest *best) {
    const char *override = getenv("KANAMA_TEST_JDK_SEARCH_DIRS");
    if (override && *override) {
#ifdef _WIN32
        const char list_sep = ';';
#else
        const char list_sep = ':';
#endif
        char copy[2048];
        snprintf(copy, sizeof copy, "%s", override);
        char *start = copy;
        while (start && *start) {
            char *end = strchr(start, list_sep);
            if (end) {
                *end = '\0';
            }
            if (*start) {
                jdk_scan_parent(start, "", "", best);
            }
            start = end ? end + 1 : NULL;
        }
        return;
    }
    const char *host = jdk_host_os();
    for (size_t i = 0; i < sizeof k_jdk_locations / sizeof k_jdk_locations[0]; i++) {
        const JdkLocation *loc = &k_jdk_locations[i];
        if (strcmp(loc->os, "all") != 0 && strcmp(loc->os, host) != 0) {
            continue;
        }
        char parent[JDK_PATH_MAX];
        if (jdk_expand_parent(loc->parent, parent, sizeof parent) != 0) {
            continue;
        }
        jdk_scan_parent(parent, loc->prefix, loc->suffix, best);
    }
}

/* <project>/.godot/kanama_jdk_home, or -1. The project is the first of the library's
 * directory and its two parents that holds a project.godot (addons/kanama is two below). */
static int jdk_read_plugin_hint(char *out, size_t out_size) {
    char dir[1024];
    if (find_self_library_dir(dir, sizeof dir) != 0) {
        return -1;
    }
    for (int depth = 0; depth < 3; depth++) {
        char probe[1100];
        snprintf(probe, sizeof probe, "%s%sproject.godot", dir, PATH_SEP);
        if (access(probe, F_OK) == 0) {
            snprintf(probe, sizeof probe, "%s%s.godot%skanama_jdk_home", dir, PATH_SEP, PATH_SEP);
            FILE *file = fopen(probe, "r");
            if (!file) {
                return -1;
            }
            char line[JDK_PATH_MAX];
            char *got = fgets(line, sizeof line, file);
            fclose(file);
            if (!got) {
                return -1;
            }
            char *value = trim_ascii(line);
            if (!*value) {
                return -1;
            }
            snprintf(out, out_size, "%s", value);
            return 0;
        }
        char before[1024];
        snprintf(before, sizeof before, "%s", dir);
        path_parent_in_place(dir);
        if (strcmp(dir, before) == 0 || dir[0] == '\0') {
            break;
        }
    }
    return -1;
}

static int jdk_try_home(const char *home, const char *source) {
    JdkVersion version;
    if (!jdk_usable(home, &version, g_jvm_path, sizeof g_jvm_path)) {
        return 0;
    }
    g_jvm_source = source;
    return 1;
}
#endif /* !__ANDROID__ */

static void print_missing_jvm_diagnostic(void) {
#ifndef __ANDROID__
    const char *java_home = getenv("JAVA_HOME");
    fprintf(stderr, "[kanama] error: libjvm not found. Kanama desktop runtime requires a JDK 25+ install.\n");
    fprintf(stderr, "[kanama] no bundled runtime%s%s found next to the Kanama bootstrap library.\n",
            PATH_SEP, jvm_relative_lib_path());
    if (java_home && *java_home) {
        char expected[1024];
        build_java_home_jvm_path(expected, sizeof expected, java_home);
        fprintf(stderr, "[kanama] checked JAVA_HOME=%s but it is not a usable JDK 25+ (looked for %s)\n", java_home, expected);
    } else {
        fprintf(stderr, "[kanama] JAVA_HOME is not set (a Godot started from a desktop launcher does not inherit your shell's JAVA_HOME).\n");
    }
    for (int i = 0; i < g_jdk_rejected_count; i++) {
        fprintf(stderr, "[kanama] skipped %s\n", g_jdk_rejected[i]);
    }
    const char *test_dirs = getenv("KANAMA_TEST_JDK_SEARCH_DIRS");
    if (test_dirs && *test_dirs) {
        fprintf(stderr, "[kanama] searched KANAMA_TEST_JDK_SEARCH_DIRS=%s for the best JDK 25+ (exactly 25 preferred, GA over EA) and found none.\n", test_dirs);
    } else {
        char locations[1024];
        size_t used = 0;
        locations[0] = '\0';
        for (size_t i = 0; i < sizeof k_jdk_locations / sizeof k_jdk_locations[0]; i++) {
            const JdkLocation *loc = &k_jdk_locations[i];
            if (strcmp(loc->os, "all") != 0 && strcmp(loc->os, jdk_host_os()) != 0) {
                continue;
            }
            int n = snprintf(locations + used, sizeof locations - used, "%s%s", used ? ", " : "", loc->parent);
            if (n < 0 || (size_t)n >= sizeof locations - used) {
                break;
            }
            used += (size_t)n;
        }
        fprintf(stderr, "[kanama] searched the install locations (%s) for the best JDK 25+ (exactly 25 preferred, GA over EA) and found none.\n", locations);
    }
    fprintf(stderr, "[kanama] fix: set JAVA_HOME to a JDK 25+ home directory, or set the 'kanama/build/jdk_path' editor setting and restart the editor.\n");
    fprintf(stderr, "[kanama] expected libjvm relative path: %s\n", jvm_relative_lib_path());
    fprintf(stderr, "[kanama] install hint: use Temurin 25+ or another JDK 25+ build that includes libjvm.\n");
#endif
}

static const char *find_jvm_lib(void) {
    const char *bundled = find_bundled_runtime_jvm();
    if (bundled) {
        fprintf(stderr, "[kanama] bundled runtime: %s\n", bundled);
        return bundled;
    }
#ifdef __ANDROID__
    return NULL;
#else
    g_jdk_rejected_count = 0;
    char hint[JDK_PATH_MAX];
    if (jdk_read_plugin_hint(hint, sizeof hint) == 0) {
        if (jdk_try_home(hint, "the kanama/build/jdk_path editor setting")) {
            return g_jvm_path;
        }
        /* A stale hint (the JDK was removed or replaced) must not be silent: say so, then fall through. */
        fprintf(stderr, "[kanama] ignoring .godot/kanama_jdk_home=%s: %s\n", hint,
                g_jdk_rejected_count > 0 ? g_jdk_rejected[g_jdk_rejected_count - 1] : "not a usable JDK 25+");
    }
    const char *java_home = getenv("JAVA_HOME");
    if (java_home && *java_home && jdk_try_home(java_home, "JAVA_HOME")) {
        return g_jvm_path;
    }
    JdkBest best;
    memset(&best, 0, sizeof best);
    jdk_scan_install_locations(&best);
    if (best.found) {
        snprintf(g_jvm_path, sizeof g_jvm_path, "%s", best.libjvm);
        g_jvm_source = "the best JDK 25+ in the install locations (exactly 25 preferred, GA over EA)";
        return g_jvm_path;
    }
    return NULL;
#endif
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

#ifndef __ANDROID__
/* JVM-initiated process exit -- SIGTERM/SIGINT/SIGHUP through the JVM's own signal handlers, or
 * System.exit / exitProcess from Kotlin. HotSpot runs the Java shutdown hooks, then calls this
 * from the VM thread at its final safepoint, right before it would call libc exit(). Godot's
 * Main::cleanup never ran on this path, so exit() would run Godot's static destructors with the
 * engine still live, on the VM thread, and they call back into Kanama (ScriptLanguage::get_name)
 * through Panama upcall stubs that thread cannot enter: SIGBUS in UpcallStub, or a hang. End the
 * process the way an unhandled SIGTERM ends a plain Godot run instead: flush stdio, then _exit
 * with the JVM's exit code. A normal Godot quit never comes here (the JVM is not shut down). */
static void JNICALL kanama_jvm_exit_hook(jint code) {
    fprintf(stderr, "[kanama] JVM exit (code=%d): ending the process without static destructors\n",
            (int)code);
    fflush(NULL);
    _exit((int)code);
}
#endif

/* ------------------------------------------------------------------ */
/* Embedded JVM options (task 131 item 18)                            */
/* ------------------------------------------------------------------ */

/* Kanama adds no heap or GC options of its own: HotSpot's ergonomics apply.
 * KANAMA_JVM_OPTIONS (whitespace-separated) is passed after Kanama's own
 * options as JNI options, which HotSpot treats as command-line ones -- unlike
 * JAVA_TOOL_OPTIONS, whose -XX:MaxNewSize/NewSize G1 discards. Games that
 * spawn many objects at once can cap the young generation there
 * (-XX:MaxNewSize=128m): see docs/exporting/desktop.md "JVM Options" for the
 * post-spawn slow phase it ends and what it costs. */
#define KANAMA_MAX_USER_JVM_OPTIONS 32

/* Splits KANAMA_JVM_OPTIONS on whitespace into [storage] (one copy of the
 * string) and points [out] at each option. Returns the count. */
static int split_user_jvm_options(const char *options, char *storage, size_t storage_size,
                                  char **out, int max_out) {
    int count = 0;
    if (!options || !*options || storage_size == 0) {
        return 0;
    }
    snprintf(storage, storage_size, "%s", options);
    char *p = storage;
    while (*p && count < max_out) {
        while (*p && isspace((unsigned char)*p)) {
            *p++ = '\0';
        }
        if (!*p) {
            break;
        }
        out[count++] = p;
        while (*p && !isspace((unsigned char)*p)) {
            p++;
        }
    }
    if (*p) {
        fprintf(stderr, "[kanama] warning: KANAMA_JVM_OPTIONS has more than %d options; "
                        "the rest are ignored\n", max_out);
    }
    return count;
}

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
    if (g_jvm_source[0] != '\0') {
        fprintf(stderr, "[kanama] libjvm chosen from %s\n", g_jvm_source);
    }

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
    int n_opts = 3;
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
        n_opts = 4;
        fprintf(stderr, "[kanama] JDWP debug agent enabled at %s\n", jdwp_address);
    }

    /* HotSpot applies JNI options after JAVA_TOOL_OPTIONS, and a later option
     * wins, so KANAMA_JVM_OPTIONS overrides JAVA_TOOL_OPTIONS. */
    const char *user_options = getenv("KANAMA_JVM_OPTIONS");
    char user_storage[2048];
    char *user_split[KANAMA_MAX_USER_JVM_OPTIONS];
    int n_user = split_user_jvm_options(user_options, user_storage, sizeof user_storage,
                                        user_split, KANAMA_MAX_USER_JVM_OPTIONS);

    JavaVMOption options[4 + KANAMA_MAX_USER_JVM_OPTIONS];
    int n = 0;
    options[n].optionString = classpath_opt;
    options[n++].extraInfo = NULL;
    options[n].optionString = (char *)"--enable-native-access=ALL-UNNAMED";
    options[n++].extraInfo = NULL;
    /* The JNI invocation API's "exit" hook; see kanama_jvm_exit_hook. */
    options[n].optionString = (char *)"exit";
    options[n++].extraInfo = (void *)kanama_jvm_exit_hook;
    if (n_opts == 4) {
        options[n].optionString = jdwp_opt;
        options[n++].extraInfo = NULL;
    }
    for (int i = 0; i < n_user; i++) {
        options[n].optionString = user_split[i];
        options[n++].extraInfo = NULL;
    }
    if (n_user > 0) {
        fprintf(stderr, "[kanama] KANAMA_JVM_OPTIONS: %s\n", user_options);
    }

    JavaVMInitArgs vm_args;
    vm_args.version = JNI_VERSION_21;
    vm_args.nOptions = n;
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
