package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A script that is executed when exporting the project.
 *
 * Generated from Godot docs: EditorExportPlugin
 */
class EditorExportPlugin(handle: MemorySegment) : RefCounted(handle) {
    /**
     * Adds a shared object or a directory containing only shared objects with the given `tags` and
     * destination `path`. Note: In case of macOS exports, those shared objects will be added to
     * `Frameworks` directory of app bundle. In case of a directory code-sign will error if you place
     * non code object in directory.
     *
     * Generated from Godot docs: EditorExportPlugin.add_shared_object
     */
    fun addSharedObject(path: String, tags: List<String>, target: String) {
        ObjectCalls.ptrcallWithStringPackedStringListAndStringArgs(addSharedObjectBind, handle, path, tags, target)
    }

    /**
     * Adds a custom file to be exported. `path` is the virtual path that can be used to load the file,
     * `file` is the binary data of the file. When called inside `_export_file` and `remap` is `true`,
     * the current file will not be exported, but instead remapped to this custom file. `remap` is
     * ignored when called in other places. `file` will not be imported, so consider using
     * `_customize_resource` to remap imported resources.
     *
     * Generated from Godot docs: EditorExportPlugin.add_file
     */
    fun addFile(path: String, file: ByteArray, remap: Boolean) {
        ObjectCalls.ptrcallWithStringByteArrayBoolArgs(addFileBind, handle, path, file, remap)
    }

    /**
     * Adds a static library from the given `path` to the Apple embedded platform project.
     *
     * Generated from Godot docs: EditorExportPlugin.add_apple_embedded_platform_project_static_lib
     */
    fun addAppleEmbeddedPlatformProjectStaticLib(path: String) {
        ObjectCalls.ptrcallWithStringArg(addAppleEmbeddedPlatformProjectStaticLibBind, handle, path)
    }

    /**
     * Adds a static library (*.a) or a dynamic library (*.dylib, *.framework) to the Linking Phase to
     * the Apple embedded platform's Xcode project.
     *
     * Generated from Godot docs: EditorExportPlugin.add_apple_embedded_platform_framework
     */
    fun addAppleEmbeddedPlatformFramework(path: String) {
        ObjectCalls.ptrcallWithStringArg(addAppleEmbeddedPlatformFrameworkBind, handle, path)
    }

    /**
     * Adds a dynamic library (*.dylib, *.framework) to the Linking Phase in the Apple embedded
     * platform's Xcode project and embeds it into the resulting binary. Note: For static libraries
     * (*.a), this works in the same way as `add_apple_embedded_platform_framework`. Note: This method
     * should not be used for System libraries as they are already present on the device.
     *
     * Generated from Godot docs: EditorExportPlugin.add_apple_embedded_platform_embedded_framework
     */
    fun addAppleEmbeddedPlatformEmbeddedFramework(path: String) {
        ObjectCalls.ptrcallWithStringArg(addAppleEmbeddedPlatformEmbeddedFrameworkBind, handle, path)
    }

    /**
     * Adds additional fields to the Apple embedded platform's project Info.plist file.
     *
     * Generated from Godot docs: EditorExportPlugin.add_apple_embedded_platform_plist_content
     */
    fun addAppleEmbeddedPlatformPlistContent(plistContent: String) {
        ObjectCalls.ptrcallWithStringArg(addAppleEmbeddedPlatformPlistContentBind, handle, plistContent)
    }

    /**
     * Adds linker flags for the Apple embedded platform export.
     *
     * Generated from Godot docs: EditorExportPlugin.add_apple_embedded_platform_linker_flags
     */
    fun addAppleEmbeddedPlatformLinkerFlags(flags: String) {
        ObjectCalls.ptrcallWithStringArg(addAppleEmbeddedPlatformLinkerFlagsBind, handle, flags)
    }

    /**
     * Adds an Apple embedded platform bundle file from the given `path` to the exported project.
     *
     * Generated from Godot docs: EditorExportPlugin.add_apple_embedded_platform_bundle_file
     */
    fun addAppleEmbeddedPlatformBundleFile(path: String) {
        ObjectCalls.ptrcallWithStringArg(addAppleEmbeddedPlatformBundleFileBind, handle, path)
    }

    /**
     * Adds C++ code to the Apple embedded platform export. The final code is created from the code
     * appended by each active export plugin.
     *
     * Generated from Godot docs: EditorExportPlugin.add_apple_embedded_platform_cpp_code
     */
    fun addAppleEmbeddedPlatformCppCode(code: String) {
        ObjectCalls.ptrcallWithStringArg(addAppleEmbeddedPlatformCppCodeBind, handle, code)
    }

    /**
     * Adds a static library from the given `path` to the iOS project.
     *
     * Generated from Godot docs: EditorExportPlugin.add_ios_project_static_lib
     */
    fun addIosProjectStaticLib(path: String) {
        ObjectCalls.ptrcallWithStringArg(addIosProjectStaticLibBind, handle, path)
    }

    /**
     * Adds a static library (*.a) or a dynamic library (*.dylib, *.framework) to the Linking Phase to
     * the iOS Xcode project.
     *
     * Generated from Godot docs: EditorExportPlugin.add_ios_framework
     */
    fun addIosFramework(path: String) {
        ObjectCalls.ptrcallWithStringArg(addIosFrameworkBind, handle, path)
    }

    /**
     * Adds a dynamic library (*.dylib, *.framework) to Linking Phase in iOS's Xcode project and embeds
     * it into resulting binary. Note: For static libraries (*.a), this works the in same way as
     * `add_apple_embedded_platform_framework`. Note: This method should not be used for System
     * libraries as they are already present on the device.
     *
     * Generated from Godot docs: EditorExportPlugin.add_ios_embedded_framework
     */
    fun addIosEmbeddedFramework(path: String) {
        ObjectCalls.ptrcallWithStringArg(addIosEmbeddedFrameworkBind, handle, path)
    }

    /**
     * Adds additional fields to the iOS project Info.plist file.
     *
     * Generated from Godot docs: EditorExportPlugin.add_ios_plist_content
     */
    fun addIosPlistContent(plistContent: String) {
        ObjectCalls.ptrcallWithStringArg(addIosPlistContentBind, handle, plistContent)
    }

    /**
     * Adds linker flags for the iOS export.
     *
     * Generated from Godot docs: EditorExportPlugin.add_ios_linker_flags
     */
    fun addIosLinkerFlags(flags: String) {
        ObjectCalls.ptrcallWithStringArg(addIosLinkerFlagsBind, handle, flags)
    }

    /**
     * Adds an iOS bundle file from the given `path` to the exported project.
     *
     * Generated from Godot docs: EditorExportPlugin.add_ios_bundle_file
     */
    fun addIosBundleFile(path: String) {
        ObjectCalls.ptrcallWithStringArg(addIosBundleFileBind, handle, path)
    }

    /**
     * Adds C++ code to the iOS export. The final code is created from the code appended by each active
     * export plugin.
     *
     * Generated from Godot docs: EditorExportPlugin.add_ios_cpp_code
     */
    fun addIosCppCode(code: String) {
        ObjectCalls.ptrcallWithStringArg(addIosCppCodeBind, handle, code)
    }

    /**
     * Adds file or directory matching `path` to `PlugIns` directory of macOS app bundle. Note: This is
     * useful only for macOS exports.
     *
     * Generated from Godot docs: EditorExportPlugin.add_macos_plugin_file
     */
    fun addMacosPluginFile(path: String) {
        ObjectCalls.ptrcallWithStringArg(addMacosPluginFileBind, handle, path)
    }

    /**
     * To be called inside `_export_file`. Skips the current file, so it's not included in the export.
     *
     * Generated from Godot docs: EditorExportPlugin.skip
     */
    fun skip() {
        ObjectCalls.ptrcallNoArgs(skipBind, handle)
    }

    /**
     * Returns the current value of an export option supplied by `_get_export_options`.
     *
     * Generated from Godot docs: EditorExportPlugin.get_option
     */
    fun getOption(name: String): Any? {
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(getOptionBind, handle, name)
    }

    /**
     * Returns currently used export preset.
     *
     * Generated from Godot docs: EditorExportPlugin.get_export_preset
     */
    fun getExportPreset(): EditorExportPreset? {
        return EditorExportPreset.wrap(ObjectCalls.ptrcallNoArgsRetObject(getExportPresetBind, handle))
    }

    /**
     * Returns currently used export platform.
     *
     * Generated from Godot docs: EditorExportPlugin.get_export_platform
     */
    fun getExportPlatform(): EditorExportPlatform? {
        return EditorExportPlatform.wrap(ObjectCalls.ptrcallNoArgsRetObject(getExportPlatformBind, handle))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorExportPlugin? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorExportPlugin? =
            if (handle.address() == 0L) null else EditorExportPlugin(handle)

        private const val ADD_SHARED_OBJECT_HASH = 3098291045L
        private val addSharedObjectBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlugin", "add_shared_object", ADD_SHARED_OBJECT_HASH)
        }

        private const val ADD_FILE_HASH = 527928637L
        private val addFileBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlugin", "add_file", ADD_FILE_HASH)
        }

        private const val ADD_APPLE_EMBEDDED_PLATFORM_PROJECT_STATIC_LIB_HASH = 83702148L
        private val addAppleEmbeddedPlatformProjectStaticLibBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlugin", "add_apple_embedded_platform_project_static_lib", ADD_APPLE_EMBEDDED_PLATFORM_PROJECT_STATIC_LIB_HASH)
        }

        private const val ADD_APPLE_EMBEDDED_PLATFORM_FRAMEWORK_HASH = 83702148L
        private val addAppleEmbeddedPlatformFrameworkBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlugin", "add_apple_embedded_platform_framework", ADD_APPLE_EMBEDDED_PLATFORM_FRAMEWORK_HASH)
        }

        private const val ADD_APPLE_EMBEDDED_PLATFORM_EMBEDDED_FRAMEWORK_HASH = 83702148L
        private val addAppleEmbeddedPlatformEmbeddedFrameworkBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlugin", "add_apple_embedded_platform_embedded_framework", ADD_APPLE_EMBEDDED_PLATFORM_EMBEDDED_FRAMEWORK_HASH)
        }

        private const val ADD_APPLE_EMBEDDED_PLATFORM_PLIST_CONTENT_HASH = 83702148L
        private val addAppleEmbeddedPlatformPlistContentBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlugin", "add_apple_embedded_platform_plist_content", ADD_APPLE_EMBEDDED_PLATFORM_PLIST_CONTENT_HASH)
        }

        private const val ADD_APPLE_EMBEDDED_PLATFORM_LINKER_FLAGS_HASH = 83702148L
        private val addAppleEmbeddedPlatformLinkerFlagsBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlugin", "add_apple_embedded_platform_linker_flags", ADD_APPLE_EMBEDDED_PLATFORM_LINKER_FLAGS_HASH)
        }

        private const val ADD_APPLE_EMBEDDED_PLATFORM_BUNDLE_FILE_HASH = 83702148L
        private val addAppleEmbeddedPlatformBundleFileBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlugin", "add_apple_embedded_platform_bundle_file", ADD_APPLE_EMBEDDED_PLATFORM_BUNDLE_FILE_HASH)
        }

        private const val ADD_APPLE_EMBEDDED_PLATFORM_CPP_CODE_HASH = 83702148L
        private val addAppleEmbeddedPlatformCppCodeBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlugin", "add_apple_embedded_platform_cpp_code", ADD_APPLE_EMBEDDED_PLATFORM_CPP_CODE_HASH)
        }

        private const val ADD_IOS_PROJECT_STATIC_LIB_HASH = 83702148L
        private val addIosProjectStaticLibBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlugin", "add_ios_project_static_lib", ADD_IOS_PROJECT_STATIC_LIB_HASH)
        }

        private const val ADD_IOS_FRAMEWORK_HASH = 83702148L
        private val addIosFrameworkBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlugin", "add_ios_framework", ADD_IOS_FRAMEWORK_HASH)
        }

        private const val ADD_IOS_EMBEDDED_FRAMEWORK_HASH = 83702148L
        private val addIosEmbeddedFrameworkBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlugin", "add_ios_embedded_framework", ADD_IOS_EMBEDDED_FRAMEWORK_HASH)
        }

        private const val ADD_IOS_PLIST_CONTENT_HASH = 83702148L
        private val addIosPlistContentBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlugin", "add_ios_plist_content", ADD_IOS_PLIST_CONTENT_HASH)
        }

        private const val ADD_IOS_LINKER_FLAGS_HASH = 83702148L
        private val addIosLinkerFlagsBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlugin", "add_ios_linker_flags", ADD_IOS_LINKER_FLAGS_HASH)
        }

        private const val ADD_IOS_BUNDLE_FILE_HASH = 83702148L
        private val addIosBundleFileBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlugin", "add_ios_bundle_file", ADD_IOS_BUNDLE_FILE_HASH)
        }

        private const val ADD_IOS_CPP_CODE_HASH = 83702148L
        private val addIosCppCodeBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlugin", "add_ios_cpp_code", ADD_IOS_CPP_CODE_HASH)
        }

        private const val ADD_MACOS_PLUGIN_FILE_HASH = 83702148L
        private val addMacosPluginFileBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlugin", "add_macos_plugin_file", ADD_MACOS_PLUGIN_FILE_HASH)
        }

        private const val SKIP_HASH = 3218959716L
        private val skipBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlugin", "skip", SKIP_HASH)
        }

        private const val GET_OPTION_HASH = 2760726917L
        private val getOptionBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlugin", "get_option", GET_OPTION_HASH)
        }

        private const val GET_EXPORT_PRESET_HASH = 1610607222L
        private val getExportPresetBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlugin", "get_export_preset", GET_EXPORT_PRESET_HASH)
        }

        private const val GET_EXPORT_PLATFORM_HASH = 282254641L
        private val getExportPlatformBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlugin", "get_export_platform", GET_EXPORT_PLATFORM_HASH)
        }
    }
}
