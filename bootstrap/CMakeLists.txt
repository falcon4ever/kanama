cmake_minimum_required(VERSION 3.22)
project(kanama_bootstrap C)

set(CMAKE_C_STANDARD 11)
set(CMAKE_C_STANDARD_REQUIRED ON)
set(CMAKE_C_VISIBILITY_PRESET hidden)

# We need jni.h for compile, but libjvm is dlopen'd at runtime so there's
# nothing to link against. CMake's FindJNI insists on locating AWT, which
# Temurin doesn't ship on macOS, so we resolve the include dirs directly.
#
# Prefer an explicit JAVA_HOME (what setup-java in CI and the docs tell users to
# set); fall back to the macOS JDK registry. `/usr/libexec/java_home -v 25` is NOT
# a hard match: with no JDK 25 registered it prints the default JVM and exits 0,
# which once compiled bootstrap.c against a JDK 8 jni.h and failed on
# JNI_VERSION_21 with nothing naming the real cause. So verify the header.
if(APPLE)
    set(JAVA_HOME_DIR "$ENV{JAVA_HOME}")
    if(NOT JAVA_HOME_DIR OR NOT EXISTS "${JAVA_HOME_DIR}/include/jni.h")
        execute_process(
            COMMAND /usr/libexec/java_home -v 25
            OUTPUT_VARIABLE JAVA_HOME_DIR
            OUTPUT_STRIP_TRAILING_WHITESPACE
            RESULT_VARIABLE JAVA_HOME_RC
        )
        if(NOT JAVA_HOME_RC EQUAL 0 OR NOT JAVA_HOME_DIR)
            message(FATAL_ERROR "Could not locate a JDK: set JAVA_HOME to a JDK 25+ (or register one so /usr/libexec/java_home -v 25 finds it)")
        endif()
    endif()
    if(NOT EXISTS "${JAVA_HOME_DIR}/include/jni.h")
        message(FATAL_ERROR "No include/jni.h under ${JAVA_HOME_DIR}; set JAVA_HOME to a JDK 25+")
    endif()
    file(STRINGS "${JAVA_HOME_DIR}/include/jni.h" JNI_HAS_VERSION_21 REGEX "JNI_VERSION_21")
    if(NOT JNI_HAS_VERSION_21)
        message(FATAL_ERROR "jni.h under ${JAVA_HOME_DIR} predates JDK 21 (no JNI_VERSION_21). Kanama needs a JDK 25+ header: set JAVA_HOME to one, or register it so /usr/libexec/java_home -v 25 resolves to it (it returns the default JVM when no JDK 25 is registered).")
    endif()
    set(JNI_INCLUDE_DIRS "${JAVA_HOME_DIR}/include" "${JAVA_HOME_DIR}/include/darwin")
else()
    find_package(JNI REQUIRED)
endif()

add_library(kanama_bootstrap SHARED bootstrap.c)
target_include_directories(kanama_bootstrap PRIVATE
    ${CMAKE_CURRENT_SOURCE_DIR}/../gdextension
    ${JNI_INCLUDE_DIRS}
)

# After build, copy the bootstrap library into example_project/addons/kanama/.
# That dir is the source-of-truth installAddonJar reads from, and native libs are
# gitignored, so without this step a clean clone + cmake build leaves the
# example project unable to load the extension.
set(KANAMA_EXAMPLE_ADDON_DIR
    "${CMAKE_CURRENT_SOURCE_DIR}/../example_project/addons/kanama"
)
add_custom_command(TARGET kanama_bootstrap POST_BUILD
    COMMAND ${CMAKE_COMMAND} -E make_directory "${KANAMA_EXAMPLE_ADDON_DIR}"
    COMMAND ${CMAKE_COMMAND} -E copy_if_different
        "$<TARGET_FILE:kanama_bootstrap>"
        "${KANAMA_EXAMPLE_ADDON_DIR}/$<TARGET_FILE_NAME:kanama_bootstrap>"
    COMMENT "Copying kanama_bootstrap into example_project/addons/kanama"
)
