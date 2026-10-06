package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Export preset configuration.
 *
 * Generated from Godot docs: EditorExportPreset
 */
class EditorExportPreset(handle: GodotHandle) : RefCounted(handle) {
    /**
     * Returns `true` if the preset has the property named `property`.
     *
     * Generated from Godot docs: EditorExportPreset.has
     */
    fun has(property: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameArgRetBool(Binds.hasBind, segment, property)
    }

    /**
     * Returns array of files to export.
     *
     * Generated from Godot docs: EditorExportPreset.get_files_to_export
     */
    fun getFilesToExport(): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(Binds.getFilesToExportBind, segment)
    }

    /**
     * Returns a dictionary of files selected in the "Resources" tab of the export dialog. The
     * dictionary's keys are file paths, and its values are the corresponding export modes: `"strip"`,
     * `"keep"`, or `"remove"`. See also `get_file_export_mode`.
     *
     * Generated from Godot docs: EditorExportPreset.get_customized_files
     */
    fun getCustomizedFiles(): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDictionary(Binds.getCustomizedFilesBind, segment)
    }

    /**
     * Returns the number of files selected in the "Resources" tab of the export dialog.
     *
     * Generated from Godot docs: EditorExportPreset.get_customized_files_count
     */
    fun getCustomizedFilesCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getCustomizedFilesCountBind, segment)
    }

    /**
     * Returns `true` if the file at the specified `path` will be exported.
     *
     * Generated from Godot docs: EditorExportPreset.has_export_file
     */
    fun hasExportFile(path: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetBool(Binds.hasExportFileBind, segment, path)
    }

    /**
     * Returns file export mode for the specified file.
     *
     * Generated from Godot docs: EditorExportPreset.get_file_export_mode
     */
    fun getFileExportMode(path: String, default: EditorExportPreset.FileExportMode = EditorExportPreset.FileExportMode.NOT_CUSTOMIZED): EditorExportPreset.FileExportMode {
        checkOpen()
        return EditorExportPreset.FileExportMode(ObjectCalls.ptrcallWithStringAndLongArgRetLong(Binds.getFileExportModeBind, segment, path, default.value))
    }

    /**
     * Returns the value of the setting identified by `name` using export preset feature tag overrides
     * instead of current OS features.
     *
     * Generated from Godot docs: EditorExportPreset.get_project_setting
     */
    fun getProjectSetting(name: String): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(Binds.getProjectSettingBind, segment, name)
    }

    /**
     * Returns this export preset's name.
     *
     * Generated from Godot docs: EditorExportPreset.get_preset_name
     */
    fun getPresetName(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getPresetNameBind, segment)
    }

    /**
     * Returns `true` if the "Runnable" toggle is enabled in the export dialog.
     *
     * Generated from Godot docs: EditorExportPreset.is_runnable
     */
    fun isRunnable(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isRunnableBind, segment)
    }

    /**
     * Returns `true` if the "Advanced" toggle is enabled in the export dialog.
     *
     * Generated from Godot docs: EditorExportPreset.are_advanced_options_enabled
     */
    fun areAdvancedOptionsEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.areAdvancedOptionsEnabledBind, segment)
    }

    /**
     * Returns `true` if the dedicated server export mode is selected in the export dialog.
     *
     * Generated from Godot docs: EditorExportPreset.is_dedicated_server
     */
    fun isDedicatedServer(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isDedicatedServerBind, segment)
    }

    /**
     * Returns export file filter mode selected in the "Resources" tab of the export dialog.
     *
     * Generated from Godot docs: EditorExportPreset.get_export_filter
     */
    fun getExportFilter(): EditorExportPreset.ExportFilter {
        checkOpen()
        return EditorExportPreset.ExportFilter(ObjectCalls.ptrcallNoArgsRetLong(Binds.getExportFilterBind, segment))
    }

    /**
     * Returns file filters to include during export.
     *
     * Generated from Godot docs: EditorExportPreset.get_include_filter
     */
    fun getIncludeFilter(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getIncludeFilterBind, segment)
    }

    /**
     * Returns file filters to exclude during export.
     *
     * Generated from Godot docs: EditorExportPreset.get_exclude_filter
     */
    fun getExcludeFilter(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getExcludeFilterBind, segment)
    }

    /**
     * Returns a comma-separated list of custom features added to this preset, as a string. See Feature
     * tags ($DOCS_URL/tutorials/export/feature_tags.html) in the documentation for more information.
     *
     * Generated from Godot docs: EditorExportPreset.get_custom_features
     */
    fun getCustomFeatures(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getCustomFeaturesBind, segment)
    }

    /**
     * Returns the list of packs on which to base a patch export on.
     *
     * Generated from Godot docs: EditorExportPreset.get_patches
     */
    fun getPatches(): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(Binds.getPatchesBind, segment)
    }

    /**
     * Returns export target path.
     *
     * Generated from Godot docs: EditorExportPreset.get_export_path
     */
    fun getExportPath(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getExportPathBind, segment)
    }

    /**
     * Returns file filters to include during PCK encryption.
     *
     * Generated from Godot docs: EditorExportPreset.get_encryption_in_filter
     */
    fun getEncryptionInFilter(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getEncryptionInFilterBind, segment)
    }

    /**
     * Returns file filters to exclude during PCK encryption.
     *
     * Generated from Godot docs: EditorExportPreset.get_encryption_ex_filter
     */
    fun getEncryptionExFilter(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getEncryptionExFilterBind, segment)
    }

    /**
     * Returns `true` if PCK encryption is enabled in the export dialog.
     *
     * Generated from Godot docs: EditorExportPreset.get_encrypt_pck
     */
    fun getEncryptPck(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getEncryptPckBind, segment)
    }

    /**
     * Returns `true` if PCK directory encryption is enabled in the export dialog.
     *
     * Generated from Godot docs: EditorExportPreset.get_encrypt_directory
     */
    fun getEncryptDirectory(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getEncryptDirectoryBind, segment)
    }

    /**
     * Returns PCK encryption key.
     *
     * Generated from Godot docs: EditorExportPreset.get_encryption_key
     */
    fun getEncryptionKey(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getEncryptionKeyBind, segment)
    }

    /**
     * Returns the export mode used by GDScript files. `0` for "Text", `1` for "Binary tokens", and `2`
     * for "Compressed binary tokens (smaller files)".
     *
     * Generated from Godot docs: EditorExportPreset.get_script_export_mode
     */
    fun getScriptExportMode(): EditorExportPreset.ScriptExportMode {
        checkOpen()
        return EditorExportPreset.ScriptExportMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getScriptExportModeBind, segment))
    }

    /**
     * Returns export option value or value of environment variable if it is set.
     *
     * Generated from Godot docs: EditorExportPreset.get_or_env
     */
    fun getOrEnv(name: String, envVar: String): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameAndStringArgRetVariantScalar(Binds.getOrEnvBind, segment, name, envVar)
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
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameAndBoolArgRetString(Binds.getVersionBind, segment, name, windowsVersion)
    }

    /**
     * Godot's `EditorExportPreset.ExportFilter` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values (`EditorExportPreset.ExportFilter.<NAME>`).
     *
     * Generated from Godot docs: EditorExportPreset.ExportFilter
     */
    @JvmInline
    value class ExportFilter(override val value: Long) : GodotEnumValue {
        companion object {
            val EXPORT_ALL_RESOURCES: ExportFilter get() = ExportFilter(0L)
            val EXPORT_SELECTED_SCENES: ExportFilter get() = ExportFilter(1L)
            val EXPORT_SELECTED_RESOURCES: ExportFilter get() = ExportFilter(2L)
            val EXCLUDE_SELECTED_RESOURCES: ExportFilter get() = ExportFilter(3L)
            val EXPORT_CUSTOMIZED: ExportFilter get() = ExportFilter(4L)
        }
    }

    /**
     * Godot's `EditorExportPreset.FileExportMode` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`EditorExportPreset.FileExportMode.<NAME>`).
     *
     * Generated from Godot docs: EditorExportPreset.FileExportMode
     */
    @JvmInline
    value class FileExportMode(override val value: Long) : GodotEnumValue {
        companion object {
            val NOT_CUSTOMIZED: FileExportMode get() = FileExportMode(0L)
            val STRIP: FileExportMode get() = FileExportMode(1L)
            val KEEP: FileExportMode get() = FileExportMode(2L)
            val REMOVE: FileExportMode get() = FileExportMode(3L)
        }
    }

    /**
     * Godot's `EditorExportPreset.ScriptExportMode` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`EditorExportPreset.ScriptExportMode.<NAME>`).
     *
     * Generated from Godot docs: EditorExportPreset.ScriptExportMode
     */
    @JvmInline
    value class ScriptExportMode(override val value: Long) : GodotEnumValue {
        companion object {
            val TEXT: ScriptExportMode get() = ScriptExportMode(0L)
            val BINARY_TOKENS: ScriptExportMode get() = ScriptExportMode(1L)
            val BINARY_TOKENS_COMPRESSED: ScriptExportMode get() = ScriptExportMode(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): EditorExportPreset? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): EditorExportPreset? =
            if (handle.address() == 0L) null else RefCounted.owned(EditorExportPreset(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): EditorExportPreset? =
            if (handle.address() == 0L) null else EditorExportPreset(GodotHandle(handle))
    }

    private object Binds {
        private const val HAS_HASH = 2619796661L
        @JvmField
        val hasBind =
            ObjectCalls.getMethodBind("EditorExportPreset", "has", HAS_HASH)

        private const val GET_FILES_TO_EXPORT_HASH = 1139954409L
        @JvmField
        val getFilesToExportBind =
            ObjectCalls.getMethodBind("EditorExportPreset", "get_files_to_export", GET_FILES_TO_EXPORT_HASH)

        private const val GET_CUSTOMIZED_FILES_HASH = 3102165223L
        @JvmField
        val getCustomizedFilesBind =
            ObjectCalls.getMethodBind("EditorExportPreset", "get_customized_files", GET_CUSTOMIZED_FILES_HASH)

        private const val GET_CUSTOMIZED_FILES_COUNT_HASH = 3905245786L
        @JvmField
        val getCustomizedFilesCountBind =
            ObjectCalls.getMethodBind("EditorExportPreset", "get_customized_files_count", GET_CUSTOMIZED_FILES_COUNT_HASH)

        private const val HAS_EXPORT_FILE_HASH = 2323990056L
        @JvmField
        val hasExportFileBind =
            ObjectCalls.getMethodBind("EditorExportPreset", "has_export_file", HAS_EXPORT_FILE_HASH)

        private const val GET_FILE_EXPORT_MODE_HASH = 407825436L
        @JvmField
        val getFileExportModeBind =
            ObjectCalls.getMethodBind("EditorExportPreset", "get_file_export_mode", GET_FILE_EXPORT_MODE_HASH)

        private const val GET_PROJECT_SETTING_HASH = 2138907829L
        @JvmField
        val getProjectSettingBind =
            ObjectCalls.getMethodBind("EditorExportPreset", "get_project_setting", GET_PROJECT_SETTING_HASH)

        private const val GET_PRESET_NAME_HASH = 201670096L
        @JvmField
        val getPresetNameBind =
            ObjectCalls.getMethodBind("EditorExportPreset", "get_preset_name", GET_PRESET_NAME_HASH)

        private const val IS_RUNNABLE_HASH = 36873697L
        @JvmField
        val isRunnableBind =
            ObjectCalls.getMethodBind("EditorExportPreset", "is_runnable", IS_RUNNABLE_HASH)

        private const val ARE_ADVANCED_OPTIONS_ENABLED_HASH = 36873697L
        @JvmField
        val areAdvancedOptionsEnabledBind =
            ObjectCalls.getMethodBind("EditorExportPreset", "are_advanced_options_enabled", ARE_ADVANCED_OPTIONS_ENABLED_HASH)

        private const val IS_DEDICATED_SERVER_HASH = 36873697L
        @JvmField
        val isDedicatedServerBind =
            ObjectCalls.getMethodBind("EditorExportPreset", "is_dedicated_server", IS_DEDICATED_SERVER_HASH)

        private const val GET_EXPORT_FILTER_HASH = 4227045696L
        @JvmField
        val getExportFilterBind =
            ObjectCalls.getMethodBind("EditorExportPreset", "get_export_filter", GET_EXPORT_FILTER_HASH)

        private const val GET_INCLUDE_FILTER_HASH = 201670096L
        @JvmField
        val getIncludeFilterBind =
            ObjectCalls.getMethodBind("EditorExportPreset", "get_include_filter", GET_INCLUDE_FILTER_HASH)

        private const val GET_EXCLUDE_FILTER_HASH = 201670096L
        @JvmField
        val getExcludeFilterBind =
            ObjectCalls.getMethodBind("EditorExportPreset", "get_exclude_filter", GET_EXCLUDE_FILTER_HASH)

        private const val GET_CUSTOM_FEATURES_HASH = 201670096L
        @JvmField
        val getCustomFeaturesBind =
            ObjectCalls.getMethodBind("EditorExportPreset", "get_custom_features", GET_CUSTOM_FEATURES_HASH)

        private const val GET_PATCHES_HASH = 1139954409L
        @JvmField
        val getPatchesBind =
            ObjectCalls.getMethodBind("EditorExportPreset", "get_patches", GET_PATCHES_HASH)

        private const val GET_EXPORT_PATH_HASH = 201670096L
        @JvmField
        val getExportPathBind =
            ObjectCalls.getMethodBind("EditorExportPreset", "get_export_path", GET_EXPORT_PATH_HASH)

        private const val GET_ENCRYPTION_IN_FILTER_HASH = 201670096L
        @JvmField
        val getEncryptionInFilterBind =
            ObjectCalls.getMethodBind("EditorExportPreset", "get_encryption_in_filter", GET_ENCRYPTION_IN_FILTER_HASH)

        private const val GET_ENCRYPTION_EX_FILTER_HASH = 201670096L
        @JvmField
        val getEncryptionExFilterBind =
            ObjectCalls.getMethodBind("EditorExportPreset", "get_encryption_ex_filter", GET_ENCRYPTION_EX_FILTER_HASH)

        private const val GET_ENCRYPT_PCK_HASH = 36873697L
        @JvmField
        val getEncryptPckBind =
            ObjectCalls.getMethodBind("EditorExportPreset", "get_encrypt_pck", GET_ENCRYPT_PCK_HASH)

        private const val GET_ENCRYPT_DIRECTORY_HASH = 36873697L
        @JvmField
        val getEncryptDirectoryBind =
            ObjectCalls.getMethodBind("EditorExportPreset", "get_encrypt_directory", GET_ENCRYPT_DIRECTORY_HASH)

        private const val GET_ENCRYPTION_KEY_HASH = 201670096L
        @JvmField
        val getEncryptionKeyBind =
            ObjectCalls.getMethodBind("EditorExportPreset", "get_encryption_key", GET_ENCRYPTION_KEY_HASH)

        private const val GET_SCRIPT_EXPORT_MODE_HASH = 2835358398L
        @JvmField
        val getScriptExportModeBind =
            ObjectCalls.getMethodBind("EditorExportPreset", "get_script_export_mode", GET_SCRIPT_EXPORT_MODE_HASH)

        private const val GET_OR_ENV_HASH = 389838787L
        @JvmField
        val getOrEnvBind =
            ObjectCalls.getMethodBind("EditorExportPreset", "get_or_env", GET_OR_ENV_HASH)

        private const val GET_VERSION_HASH = 1132184663L
        @JvmField
        val getVersionBind =
            ObjectCalls.getMethodBind("EditorExportPreset", "get_version", GET_VERSION_HASH)
    }
}
