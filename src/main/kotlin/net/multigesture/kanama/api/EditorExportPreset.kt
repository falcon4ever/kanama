package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Export preset configuration.
 *
 * Generated from Godot docs: EditorExportPreset
 */
class EditorExportPreset(handle: MemorySegment) : RefCounted(handle) {
    /**
     * Returns `true` if the preset has the property named `property`.
     *
     * Generated from Godot docs: EditorExportPreset.has
     */
    fun has(property: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasBind, handle, property)
    }

    /**
     * Returns array of files to export.
     *
     * Generated from Godot docs: EditorExportPreset.get_files_to_export
     */
    fun getFilesToExport(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getFilesToExportBind, handle)
    }

    /**
     * Returns a dictionary of files selected in the "Resources" tab of the export dialog. The
     * dictionary's keys are file paths, and its values are the corresponding export modes: `"strip"`,
     * `"keep"`, or `"remove"`. See also `get_file_export_mode`.
     *
     * Generated from Godot docs: EditorExportPreset.get_customized_files
     */
    fun getCustomizedFiles(): Map<String, Any?> {
        return ObjectCalls.ptrcallNoArgsRetDictionary(getCustomizedFilesBind, handle)
    }

    /**
     * Returns the number of files selected in the "Resources" tab of the export dialog.
     *
     * Generated from Godot docs: EditorExportPreset.get_customized_files_count
     */
    fun getCustomizedFilesCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getCustomizedFilesCountBind, handle)
    }

    /**
     * Returns `true` if the file at the specified `path` will be exported.
     *
     * Generated from Godot docs: EditorExportPreset.has_export_file
     */
    fun hasExportFile(path: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(hasExportFileBind, handle, path)
    }

    /**
     * Returns file export mode for the specified file.
     *
     * Generated from Godot docs: EditorExportPreset.get_file_export_mode
     */
    fun getFileExportMode(path: String, default: Long = 0L): Long {
        return ObjectCalls.ptrcallWithStringAndLongArgRetLong(getFileExportModeBind, handle, path, default)
    }

    /**
     * Returns the value of the setting identified by `name` using export preset feature tag overrides
     * instead of current OS features.
     *
     * Generated from Godot docs: EditorExportPreset.get_project_setting
     */
    fun getProjectSetting(name: String): Any? {
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(getProjectSettingBind, handle, name)
    }

    /**
     * Returns this export preset's name.
     *
     * Generated from Godot docs: EditorExportPreset.get_preset_name
     */
    fun getPresetName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getPresetNameBind, handle)
    }

    /**
     * Returns `true` if the "Runnable" toggle is enabled in the export dialog.
     *
     * Generated from Godot docs: EditorExportPreset.is_runnable
     */
    fun isRunnable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isRunnableBind, handle)
    }

    /**
     * Returns `true` if the "Advanced" toggle is enabled in the export dialog.
     *
     * Generated from Godot docs: EditorExportPreset.are_advanced_options_enabled
     */
    fun areAdvancedOptionsEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(areAdvancedOptionsEnabledBind, handle)
    }

    /**
     * Returns `true` if the dedicated server export mode is selected in the export dialog.
     *
     * Generated from Godot docs: EditorExportPreset.is_dedicated_server
     */
    fun isDedicatedServer(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDedicatedServerBind, handle)
    }

    /**
     * Returns export file filter mode selected in the "Resources" tab of the export dialog.
     *
     * Generated from Godot docs: EditorExportPreset.get_export_filter
     */
    fun getExportFilter(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getExportFilterBind, handle)
    }

    /**
     * Returns file filters to include during export.
     *
     * Generated from Godot docs: EditorExportPreset.get_include_filter
     */
    fun getIncludeFilter(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getIncludeFilterBind, handle)
    }

    /**
     * Returns file filters to exclude during export.
     *
     * Generated from Godot docs: EditorExportPreset.get_exclude_filter
     */
    fun getExcludeFilter(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getExcludeFilterBind, handle)
    }

    /**
     * Returns a comma-separated list of custom features added to this preset, as a string. See Feature
     * tags ($DOCS_URL/tutorials/export/feature_tags.html) in the documentation for more information.
     *
     * Generated from Godot docs: EditorExportPreset.get_custom_features
     */
    fun getCustomFeatures(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getCustomFeaturesBind, handle)
    }

    /**
     * Returns the list of packs on which to base a patch export on.
     *
     * Generated from Godot docs: EditorExportPreset.get_patches
     */
    fun getPatches(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getPatchesBind, handle)
    }

    /**
     * Returns export target path.
     *
     * Generated from Godot docs: EditorExportPreset.get_export_path
     */
    fun getExportPath(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getExportPathBind, handle)
    }

    /**
     * Returns file filters to include during PCK encryption.
     *
     * Generated from Godot docs: EditorExportPreset.get_encryption_in_filter
     */
    fun getEncryptionInFilter(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getEncryptionInFilterBind, handle)
    }

    /**
     * Returns file filters to exclude during PCK encryption.
     *
     * Generated from Godot docs: EditorExportPreset.get_encryption_ex_filter
     */
    fun getEncryptionExFilter(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getEncryptionExFilterBind, handle)
    }

    /**
     * Returns `true` if PCK encryption is enabled in the export dialog.
     *
     * Generated from Godot docs: EditorExportPreset.get_encrypt_pck
     */
    fun getEncryptPck(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getEncryptPckBind, handle)
    }

    /**
     * Returns `true` if PCK directory encryption is enabled in the export dialog.
     *
     * Generated from Godot docs: EditorExportPreset.get_encrypt_directory
     */
    fun getEncryptDirectory(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getEncryptDirectoryBind, handle)
    }

    /**
     * Returns PCK encryption key.
     *
     * Generated from Godot docs: EditorExportPreset.get_encryption_key
     */
    fun getEncryptionKey(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getEncryptionKeyBind, handle)
    }

    /**
     * Returns the export mode used by GDScript files. `0` for "Text", `1` for "Binary tokens", and `2`
     * for "Compressed binary tokens (smaller files)".
     *
     * Generated from Godot docs: EditorExportPreset.get_script_export_mode
     */
    fun getScriptExportMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getScriptExportModeBind, handle)
    }

    /**
     * Returns export option value or value of environment variable if it is set.
     *
     * Generated from Godot docs: EditorExportPreset.get_or_env
     */
    fun getOrEnv(name: String, envVar: String): Any? {
        return ObjectCalls.ptrcallWithStringNameAndStringArgRetVariantScalar(getOrEnvBind, handle, name, envVar)
    }

    /**
     * Returns the preset's version number, or fall back to the
     * `ProjectSettings.application/config/version` project setting if set to an empty string. If
     * `windows_version` is `true`, formats the returned version number to be compatible with Windows
     * executable metadata.
     *
     * Generated from Godot docs: EditorExportPreset.get_version
     */
    fun getVersion(name: String, windowsVersion: Boolean): String {
        return ObjectCalls.ptrcallWithStringNameAndBoolArgRetString(getVersionBind, handle, name, windowsVersion)
    }

    companion object {
        const val EXPORT_ALL_RESOURCES: Long = 0L
        const val EXPORT_SELECTED_SCENES: Long = 1L
        const val EXPORT_SELECTED_RESOURCES: Long = 2L
        const val EXCLUDE_SELECTED_RESOURCES: Long = 3L
        const val EXPORT_CUSTOMIZED: Long = 4L
        const val MODE_FILE_NOT_CUSTOMIZED: Long = 0L
        const val MODE_FILE_STRIP: Long = 1L
        const val MODE_FILE_KEEP: Long = 2L
        const val MODE_FILE_REMOVE: Long = 3L
        const val MODE_SCRIPT_TEXT: Long = 0L
        const val MODE_SCRIPT_BINARY_TOKENS: Long = 1L
        const val MODE_SCRIPT_BINARY_TOKENS_COMPRESSED: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorExportPreset? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorExportPreset? =
            if (handle.address() == 0L) null else EditorExportPreset(handle)

        private const val HAS_HASH = 2619796661L
        private val hasBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPreset", "has", HAS_HASH)
        }

        private const val GET_FILES_TO_EXPORT_HASH = 1139954409L
        private val getFilesToExportBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPreset", "get_files_to_export", GET_FILES_TO_EXPORT_HASH)
        }

        private const val GET_CUSTOMIZED_FILES_HASH = 3102165223L
        private val getCustomizedFilesBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPreset", "get_customized_files", GET_CUSTOMIZED_FILES_HASH)
        }

        private const val GET_CUSTOMIZED_FILES_COUNT_HASH = 3905245786L
        private val getCustomizedFilesCountBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPreset", "get_customized_files_count", GET_CUSTOMIZED_FILES_COUNT_HASH)
        }

        private const val HAS_EXPORT_FILE_HASH = 2323990056L
        private val hasExportFileBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPreset", "has_export_file", HAS_EXPORT_FILE_HASH)
        }

        private const val GET_FILE_EXPORT_MODE_HASH = 407825436L
        private val getFileExportModeBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPreset", "get_file_export_mode", GET_FILE_EXPORT_MODE_HASH)
        }

        private const val GET_PROJECT_SETTING_HASH = 2138907829L
        private val getProjectSettingBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPreset", "get_project_setting", GET_PROJECT_SETTING_HASH)
        }

        private const val GET_PRESET_NAME_HASH = 201670096L
        private val getPresetNameBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPreset", "get_preset_name", GET_PRESET_NAME_HASH)
        }

        private const val IS_RUNNABLE_HASH = 36873697L
        private val isRunnableBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPreset", "is_runnable", IS_RUNNABLE_HASH)
        }

        private const val ARE_ADVANCED_OPTIONS_ENABLED_HASH = 36873697L
        private val areAdvancedOptionsEnabledBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPreset", "are_advanced_options_enabled", ARE_ADVANCED_OPTIONS_ENABLED_HASH)
        }

        private const val IS_DEDICATED_SERVER_HASH = 36873697L
        private val isDedicatedServerBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPreset", "is_dedicated_server", IS_DEDICATED_SERVER_HASH)
        }

        private const val GET_EXPORT_FILTER_HASH = 4227045696L
        private val getExportFilterBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPreset", "get_export_filter", GET_EXPORT_FILTER_HASH)
        }

        private const val GET_INCLUDE_FILTER_HASH = 201670096L
        private val getIncludeFilterBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPreset", "get_include_filter", GET_INCLUDE_FILTER_HASH)
        }

        private const val GET_EXCLUDE_FILTER_HASH = 201670096L
        private val getExcludeFilterBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPreset", "get_exclude_filter", GET_EXCLUDE_FILTER_HASH)
        }

        private const val GET_CUSTOM_FEATURES_HASH = 201670096L
        private val getCustomFeaturesBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPreset", "get_custom_features", GET_CUSTOM_FEATURES_HASH)
        }

        private const val GET_PATCHES_HASH = 1139954409L
        private val getPatchesBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPreset", "get_patches", GET_PATCHES_HASH)
        }

        private const val GET_EXPORT_PATH_HASH = 201670096L
        private val getExportPathBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPreset", "get_export_path", GET_EXPORT_PATH_HASH)
        }

        private const val GET_ENCRYPTION_IN_FILTER_HASH = 201670096L
        private val getEncryptionInFilterBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPreset", "get_encryption_in_filter", GET_ENCRYPTION_IN_FILTER_HASH)
        }

        private const val GET_ENCRYPTION_EX_FILTER_HASH = 201670096L
        private val getEncryptionExFilterBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPreset", "get_encryption_ex_filter", GET_ENCRYPTION_EX_FILTER_HASH)
        }

        private const val GET_ENCRYPT_PCK_HASH = 36873697L
        private val getEncryptPckBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPreset", "get_encrypt_pck", GET_ENCRYPT_PCK_HASH)
        }

        private const val GET_ENCRYPT_DIRECTORY_HASH = 36873697L
        private val getEncryptDirectoryBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPreset", "get_encrypt_directory", GET_ENCRYPT_DIRECTORY_HASH)
        }

        private const val GET_ENCRYPTION_KEY_HASH = 201670096L
        private val getEncryptionKeyBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPreset", "get_encryption_key", GET_ENCRYPTION_KEY_HASH)
        }

        private const val GET_SCRIPT_EXPORT_MODE_HASH = 2835358398L
        private val getScriptExportModeBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPreset", "get_script_export_mode", GET_SCRIPT_EXPORT_MODE_HASH)
        }

        private const val GET_OR_ENV_HASH = 389838787L
        private val getOrEnvBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPreset", "get_or_env", GET_OR_ENV_HASH)
        }

        private const val GET_VERSION_HASH = 1132184663L
        private val getVersionBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPreset", "get_version", GET_VERSION_HASH)
        }
    }
}
