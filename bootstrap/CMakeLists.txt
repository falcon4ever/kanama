cmake_minimum_required(VERSION 3.22)
project(kanama_bootstrap C)

set(CMAKE_C_STANDARD 11)
set(CMAKE_C_STANDARD_REQUIRED ON)
set(CMAKE_C_VISIBILITY_PRESET hidden)

# We need jni.h for compile, but libjvm is dlopen'd at runtime so there's
# nothing to link against. CMake's FindJNI insists on locating AWT, which
# Temurin doesn't ship on macOS, so we resolve the include dirs directly.
#
# One resolution for macOS, Linux and Windows, first match wins (a candidate counts
# when its include/jni.h exists):
#   1. -DKANAMA_JAVA_HOME=<jdk>  what Gradle passes (configureNativeBootstrap): the JDK 25
#                                toolchain the rest of the build compiles with, so the native
#                                library and the Kotlin it loads agree.
#   2. $ENV{JAVA_HOME}           what setup-java in CI and the docs tell users to set.
#   3. /usr/libexec/java_home -v 25   (macOS only).
#   4. find_package(JNI)         last resort, the system default JDK. Linux/Windows used to
#                                stop here, so a launcher-started editor without JAVA_HOME
#                                compiled bootstrap.c against whatever /usr/lib/jvm default
#                                exists (kanama#277: Java 26 box, JNI_VERSION_21 undeclared).
# Then ONE header check for every OS: `/usr/libexec/java_home -v 25` is NOT a hard match
# (with no JDK 25 registered it prints the default JVM and exits 0, which once compiled
# bootstrap.c against a JDK 8 jni.h), and a Linux system default can be as old. A jni.h
# without JNI_VERSION_21 stops the configure with a message naming the JDK it came from.
# -DKANAMA_JAVA_HOME lands in CMakeCache.txt; it is copied to a plain variable and removed
# from the cache right away, so the next configure without it re-reads JAVA_HOME instead of
# reusing a stale JDK path. Everything below is a plain variable for the same reason.
if(APPLE)
    set(KANAMA_JNI_PLATFORM_DIR darwin)
elseif(WIN32)
    set(KANAMA_JNI_PLATFORM_DIR win32)
elseif(CMAKE_SYSTEM_NAME STREQUAL "Linux")
    set(KANAMA_JNI_PLATFORM_DIR linux)
else()
    # Unknown host: no per-OS include dir is known, so jni_md.h is not checked (below).
    message(WARNING "Unknown host ${CMAKE_SYSTEM_NAME}: not checking for jni_md.h under include/<os>")
    set(KANAMA_JNI_PLATFORM_DIR "")
endif()

set(KANAMA_GRADLE_JDK_HOME "")
if(KANAMA_JAVA_HOME)
    set(KANAMA_GRADLE_JDK_HOME "${KANAMA_JAVA_HOME}")
endif()
unset(KANAMA_JAVA_HOME CACHE)
unset(KANAMA_JAVA_HOME)

set(KANAMA_JDK_HOME "")
set(KANAMA_JDK_ORIGIN "")
set(KANAMA_JDK_TRIED "")
set(KANAMA_JNI_INCLUDE_DIRS "")

set(KANAMA_JDK_CANDIDATES "")
set(KANAMA_JDK_CANDIDATE_ORIGINS "")
if(KANAMA_GRADLE_JDK_HOME)
    list(APPEND KANAMA_JDK_CANDIDATES "${KANAMA_GRADLE_JDK_HOME}")
    list(APPEND KANAMA_JDK_CANDIDATE_ORIGINS "-DKANAMA_JAVA_HOME (the JDK Gradle builds with)")
endif()
if(DEFINED ENV{JAVA_HOME} AND NOT "$ENV{JAVA_HOME}" STREQUAL "")
    list(APPEND KANAMA_JDK_CANDIDATES "$ENV{JAVA_HOME}")
    list(APPEND KANAMA_JDK_CANDIDATE_ORIGINS "JAVA_HOME")
endif()
# KANAMA_SKIP_JAVA_HOME_TOOL exists so the gate (scripts/check_bootstrap_jdk_resolution.sh) can
# reach the find_package(JNI) fallback on a Mac that has a JDK 25 registered.
if(APPLE AND NOT KANAMA_SKIP_JAVA_HOME_TOOL)
    execute_process(
        COMMAND /usr/libexec/java_home -v 25
        OUTPUT_VARIABLE KANAMA_MAC_JAVA_HOME
        OUTPUT_STRIP_TRAILING_WHITESPACE
        RESULT_VARIABLE KANAMA_MAC_JAVA_HOME_RC
        ERROR_QUIET
    )
    if(KANAMA_MAC_JAVA_HOME_RC EQUAL 0 AND KANAMA_MAC_JAVA_HOME)
        list(APPEND KANAMA_JDK_CANDIDATES "${KANAMA_MAC_JAVA_HOME}")
        list(APPEND KANAMA_JDK_CANDIDATE_ORIGINS "/usr/libexec/java_home -v 25")
    endif()
endif()

set(KANAMA_JDK_INDEX 0)
foreach(KANAMA_CANDIDATE IN LISTS KANAMA_JDK_CANDIDATES)
    list(GET KANAMA_JDK_CANDIDATE_ORIGINS ${KANAMA_JDK_INDEX} KANAMA_CANDIDATE_ORIGIN)
    math(EXPR KANAMA_JDK_INDEX "${KANAMA_JDK_INDEX} + 1")
    file(TO_CMAKE_PATH "${KANAMA_CANDIDATE}" KANAMA_CANDIDATE)
    if(EXISTS "${KANAMA_CANDIDATE}/include/jni.h")
        set(KANAMA_JDK_HOME "${KANAMA_CANDIDATE}")
        set(KANAMA_JDK_ORIGIN "${KANAMA_CANDIDATE_ORIGIN}")
        set(KANAMA_JNI_HEADER "${KANAMA_CANDIDATE}/include/jni.h")
        set(KANAMA_JNI_INCLUDE_DIRS "${KANAMA_CANDIDATE}/include")
        if(KANAMA_JNI_PLATFORM_DIR)
            list(APPEND KANAMA_JNI_INCLUDE_DIRS "${KANAMA_CANDIDATE}/include/${KANAMA_JNI_PLATFORM_DIR}")
        endif()
        break()
    endif()
    string(APPEND KANAMA_JDK_TRIED "\n  - ${KANAMA_CANDIDATE} (${KANAMA_CANDIDATE_ORIGIN}): no include/jni.h (a JRE, or not a JDK)")
endforeach()

if(NOT KANAMA_JDK_HOME)
    # Last resort. No REQUIRED and no AWT component: we only need the headers, and
    # FindJNI's default demands libjawt, which a headless JDK does not ship.
    find_package(JNI QUIET)
    if(JAVA_INCLUDE_PATH AND EXISTS "${JAVA_INCLUDE_PATH}/jni.h")
        set(KANAMA_JDK_HOME "${JAVA_INCLUDE_PATH}/..")
        get_filename_component(KANAMA_JDK_HOME "${KANAMA_JDK_HOME}" ABSOLUTE)
        set(KANAMA_JDK_ORIGIN "find_package(JNI), the system default JDK")
        set(KANAMA_JNI_HEADER "${JAVA_INCLUDE_PATH}/jni.h")
        set(KANAMA_JNI_INCLUDE_DIRS "${JAVA_INCLUDE_PATH}")
        if(JAVA_INCLUDE_PATH2)
            list(APPEND KANAMA_JNI_INCLUDE_DIRS "${JAVA_INCLUDE_PATH2}")
        endif()
    endif()
endif()

if(NOT KANAMA_JDK_HOME)
    message(FATAL_ERROR "Could not locate a JDK with include/jni.h. Install a JDK 25+ and set JAVA_HOME to its home directory (or run the build through Gradle on JDK 25, which passes it as -DKANAMA_JAVA_HOME).${KANAMA_JDK_TRIED}")
endif()
file(STRINGS "${KANAMA_JNI_HEADER}" JNI_HAS_VERSION_21 REGEX "JNI_VERSION_21")
if(NOT JNI_HAS_VERSION_21)
    message(FATAL_ERROR "jni.h under ${KANAMA_JDK_HOME} (found via ${KANAMA_JDK_ORIGIN}) predates JDK 21 (no JNI_VERSION_21). Kanama needs a JDK 25+ header: set JAVA_HOME to a JDK 25+ home directory (a desktop launcher does not inherit your shell's JAVA_HOME; in the Godot editor set the project setting kanama/build/jdk_path), or run the build through Gradle on JDK 25.")
endif()
# jni.h includes jni_md.h from the per-OS dir (include/darwin, include/linux, include/win32).
set(KANAMA_JNI_MD_FOUND FALSE)
foreach(KANAMA_JNI_DIR IN LISTS KANAMA_JNI_INCLUDE_DIRS)
    if(EXISTS "${KANAMA_JNI_DIR}/jni_md.h")
        set(KANAMA_JNI_MD_FOUND TRUE)
    endif()
endforeach()
if(NOT KANAMA_JNI_MD_FOUND AND KANAMA_JNI_PLATFORM_DIR)
    message(FATAL_ERROR "No jni_md.h under ${KANAMA_JDK_HOME}/include/${KANAMA_JNI_PLATFORM_DIR} (found via ${KANAMA_JDK_ORIGIN}); set JAVA_HOME to a full JDK 25+ for this host (${CMAKE_SYSTEM_NAME})")
endif()
message(STATUS "Kanama bootstrap JDK: ${KANAMA_JDK_HOME} (via ${KANAMA_JDK_ORIGIN})")
set(JNI_INCLUDE_DIRS ${KANAMA_JNI_INCLUDE_DIRS})

add_library(kanama_bootstrap SHARED bootstrap.c)
target_include_directories(kanama_bootstrap PRIVATE
    ${CMAKE_CURRENT_SOURCE_DIR}/../gdextension
    ${JNI_INCLUDE_DIRS}
)

# Install the bootstrap library into example_project/addons/kanama/.
# That dir is the source-of-truth installAddonJar reads from, and native libs are
# gitignored, so without this step a clean clone + cmake build leaves the
# example project unable to load the extension.
#
# This is its own ALL target, not a POST_BUILD hook on kanama_bootstrap: a
# POST_BUILD step only runs when the library is relinked, so deleting or
# truncating just the installed copy was never repaired ("Built target", no
# copy). A custom target runs on every build, and copy_if_different compares
# content, so a missing or truncated copy is recreated while an intact one is
# left alone.
set(KANAMA_EXAMPLE_ADDON_DIR
    "${CMAKE_CURRENT_SOURCE_DIR}/../example_project/addons/kanama"
)
add_custom_target(kanama_bootstrap_install ALL
    COMMAND ${CMAKE_COMMAND} -E make_directory "${KANAMA_EXAMPLE_ADDON_DIR}"
    COMMAND ${CMAKE_COMMAND} -E copy_if_different
        "$<TARGET_FILE:kanama_bootstrap>"
        "${KANAMA_EXAMPLE_ADDON_DIR}/$<TARGET_FILE_NAME:kanama_bootstrap>"
    DEPENDS kanama_bootstrap
    COMMENT "Copying kanama_bootstrap into example_project/addons/kanama"
)
add_dependencies(kanama_bootstrap_install kanama_bootstrap)
