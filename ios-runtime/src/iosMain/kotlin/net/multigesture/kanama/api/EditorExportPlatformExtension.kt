package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: EditorExportPlatformExtension
 */
class EditorExportPlatformExtension(handle: MemorySegment) : EditorExportPlatform(handle) {
    fun setConfigError(errorText: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setConfigErrorBind, handle, errorText)
    }

    fun getConfigError(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getConfigErrorBind, handle)
    }

    fun setConfigMissingTemplates(missingTemplates: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setConfigMissingTemplatesBind, handle, missingTemplates)
    }

    fun getConfigMissingTemplates(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getConfigMissingTemplatesBind, handle)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): EditorExportPlatformExtension? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorExportPlatformExtension? =
            if (handle.address() == 0L) null else EditorExportPlatformExtension(handle)

        private const val SET_CONFIG_ERROR_HASH = 3089850668L
        private val setConfigErrorBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlatformExtension", "set_config_error", SET_CONFIG_ERROR_HASH)
        }

        private const val GET_CONFIG_ERROR_HASH = 201670096L
        private val getConfigErrorBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlatformExtension", "get_config_error", GET_CONFIG_ERROR_HASH)
        }

        private const val SET_CONFIG_MISSING_TEMPLATES_HASH = 1695273946L
        private val setConfigMissingTemplatesBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlatformExtension", "set_config_missing_templates", SET_CONFIG_MISSING_TEMPLATES_HASH)
        }

        private const val GET_CONFIG_MISSING_TEMPLATES_HASH = 36873697L
        private val getConfigMissingTemplatesBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlatformExtension", "get_config_missing_templates", GET_CONFIG_MISSING_TEMPLATES_HASH)
        }
    }
}
