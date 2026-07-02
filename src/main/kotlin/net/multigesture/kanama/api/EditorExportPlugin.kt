package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A script that is executed when exporting the project.
 *
 * Generated from Godot docs: EditorExportPlugin
 */
class EditorExportPlugin(handle: MemorySegment) : RefCounted(handle) {
    fun addSharedObject(path: String, tags: List<String>, target: String) {
        ObjectCalls.ptrcallWithStringPackedStringListAndStringArgs(addSharedObjectBind, handle, path, tags, target)
    }

    fun addFile(path: String, file: ByteArray, remap: Boolean) {
        ObjectCalls.ptrcallWithStringByteArrayBoolArgs(addFileBind, handle, path, file, remap)
    }

    fun addAppleEmbeddedPlatformProjectStaticLib(path: String) {
        ObjectCalls.ptrcallWithStringArg(addAppleEmbeddedPlatformProjectStaticLibBind, handle, path)
    }

    fun addAppleEmbeddedPlatformFramework(path: String) {
        ObjectCalls.ptrcallWithStringArg(addAppleEmbeddedPlatformFrameworkBind, handle, path)
    }

    fun addAppleEmbeddedPlatformEmbeddedFramework(path: String) {
        ObjectCalls.ptrcallWithStringArg(addAppleEmbeddedPlatformEmbeddedFrameworkBind, handle, path)
    }

    fun addAppleEmbeddedPlatformPlistContent(plistContent: String) {
        ObjectCalls.ptrcallWithStringArg(addAppleEmbeddedPlatformPlistContentBind, handle, plistContent)
    }

    fun addAppleEmbeddedPlatformLinkerFlags(flags: String) {
        ObjectCalls.ptrcallWithStringArg(addAppleEmbeddedPlatformLinkerFlagsBind, handle, flags)
    }

    fun addAppleEmbeddedPlatformBundleFile(path: String) {
        ObjectCalls.ptrcallWithStringArg(addAppleEmbeddedPlatformBundleFileBind, handle, path)
    }

    fun addAppleEmbeddedPlatformCppCode(code: String) {
        ObjectCalls.ptrcallWithStringArg(addAppleEmbeddedPlatformCppCodeBind, handle, code)
    }

    fun addIosProjectStaticLib(path: String) {
        ObjectCalls.ptrcallWithStringArg(addIosProjectStaticLibBind, handle, path)
    }

    fun addIosFramework(path: String) {
        ObjectCalls.ptrcallWithStringArg(addIosFrameworkBind, handle, path)
    }

    fun addIosEmbeddedFramework(path: String) {
        ObjectCalls.ptrcallWithStringArg(addIosEmbeddedFrameworkBind, handle, path)
    }

    fun addIosPlistContent(plistContent: String) {
        ObjectCalls.ptrcallWithStringArg(addIosPlistContentBind, handle, plistContent)
    }

    fun addIosLinkerFlags(flags: String) {
        ObjectCalls.ptrcallWithStringArg(addIosLinkerFlagsBind, handle, flags)
    }

    fun addIosBundleFile(path: String) {
        ObjectCalls.ptrcallWithStringArg(addIosBundleFileBind, handle, path)
    }

    fun addIosCppCode(code: String) {
        ObjectCalls.ptrcallWithStringArg(addIosCppCodeBind, handle, code)
    }

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

    fun getOption(name: String): Any? {
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(getOptionBind, handle, name)
    }

    fun getExportPreset(): EditorExportPreset? {
        return EditorExportPreset.wrap(ObjectCalls.ptrcallNoArgsRetObject(getExportPresetBind, handle))
    }

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
