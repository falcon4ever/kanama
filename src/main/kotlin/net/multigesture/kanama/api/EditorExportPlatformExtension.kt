package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Base class for custom `EditorExportPlatform` implementations (plugins).
 *
 * Generated from Godot docs: EditorExportPlatformExtension
 */
class EditorExportPlatformExtension(handle: MemorySegment) : EditorExportPlatform(handle) {
    /**
     * Sets current configuration error message text. This method should be called only from the
     * `_can_export`, `_has_valid_export_configuration`, or `_has_valid_project_configuration`
     * implementations.
     *
     * Generated from Godot docs: EditorExportPlatformExtension.set_config_error
     */
    fun setConfigError(errorText: String) {
        ObjectCalls.ptrcallWithStringArg(setConfigErrorBind, handle, errorText)
    }

    /**
     * Returns current configuration error message text. This method should be called only from the
     * `_can_export`, `_has_valid_export_configuration`, or `_has_valid_project_configuration`
     * implementations.
     *
     * Generated from Godot docs: EditorExportPlatformExtension.get_config_error
     */
    fun getConfigError(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getConfigErrorBind, handle)
    }

    /**
     * Set to `true` is export templates are missing from the current configuration. This method should
     * be called only from the `_can_export`, `_has_valid_export_configuration`, or
     * `_has_valid_project_configuration` implementations.
     *
     * Generated from Godot docs: EditorExportPlatformExtension.set_config_missing_templates
     */
    fun setConfigMissingTemplates(missingTemplates: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setConfigMissingTemplatesBind, handle, missingTemplates)
    }

    /**
     * Returns `true` is export templates are missing from the current configuration. This method
     * should be called only from the `_can_export`, `_has_valid_export_configuration`, or
     * `_has_valid_project_configuration` implementations.
     *
     * Generated from Godot docs: EditorExportPlatformExtension.get_config_missing_templates
     */
    fun getConfigMissingTemplates(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getConfigMissingTemplatesBind, handle)
    }

    companion object {
        @JvmStatic
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
