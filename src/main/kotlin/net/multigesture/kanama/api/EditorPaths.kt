package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Editor-only singleton that returns paths to various OS-specific data folders and files.
 *
 * Generated from Godot docs: EditorPaths
 */
class EditorPaths(handle: MemorySegment) : GodotObject(handle) {
    /**
     * Returns the absolute path to the user's data folder. This folder should be used for persistent
     * user data files such as installed export templates. Default paths per platform:
     *
     * Generated from Godot docs: EditorPaths.get_data_dir
     */
    fun getDataDir(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getDataDirBind, handle)
    }

    /**
     * Returns the absolute path to the user's configuration folder. This folder should be used for
     * persistent user configuration files. Default paths per platform:
     *
     * Generated from Godot docs: EditorPaths.get_config_dir
     */
    fun getConfigDir(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getConfigDirBind, handle)
    }

    /**
     * Returns the absolute path to the user's cache folder. This folder should be used for temporary
     * data that can be removed safely whenever the editor is closed (such as generated resource
     * thumbnails). Default paths per platform:
     *
     * Generated from Godot docs: EditorPaths.get_cache_dir
     */
    fun getCacheDir(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getCacheDirBind, handle)
    }

    /**
     * Returns `true` if the editor is marked as self-contained, `false` otherwise. When self-contained
     * mode is enabled, user configuration, data and cache files are saved in an `editor_data/` folder
     * next to the editor binary. This makes portable usage easier and ensures the Godot editor
     * minimizes file writes outside its own folder. Self-contained mode is not available for exported
     * projects. Self-contained mode can be enabled by creating a file named `._sc_` or `_sc_` in the
     * same folder as the editor binary or macOS .app bundle while the editor is not running. See also
     * `get_self_contained_file`. Note: On macOS, quarantine flag should be manually removed before
     * using self-contained mode, see Running on macOS
     * (https://docs.godotengine.org/en/stable/tutorials/export/running_on_macos.html). Note: On macOS,
     * placing `_sc_` or any other file inside .app bundle will break digital signature and make it
     * non-portable, consider placing it in the same folder as the .app bundle instead. Note: The Steam
     * release of Godot uses self-contained mode by default.
     *
     * Generated from Godot docs: EditorPaths.is_self_contained
     */
    fun isSelfContained(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSelfContainedBind, handle)
    }

    /**
     * Returns the absolute path to the self-contained file that makes the current Godot editor
     * instance be considered as self-contained. Returns an empty string if the current Godot editor
     * instance isn't self-contained. See also `is_self_contained`.
     *
     * Generated from Godot docs: EditorPaths.get_self_contained_file
     */
    fun getSelfContainedFile(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getSelfContainedFileBind, handle)
    }

    /**
     * Returns the relative path to the editor settings for this project. This is usually
     * `"res://.godot/editor"`. Projects all have a unique subdirectory inside the settings path where
     * project-specific editor settings are saved.
     *
     * Generated from Godot docs: EditorPaths.get_project_settings_dir
     */
    fun getProjectSettingsDir(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getProjectSettingsDirBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorPaths? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorPaths? =
            if (handle.address() == 0L) null else EditorPaths(handle)

        private const val GET_DATA_DIR_HASH = 201670096L
        private val getDataDirBind by lazy {
            ObjectCalls.getMethodBind("EditorPaths", "get_data_dir", GET_DATA_DIR_HASH)
        }

        private const val GET_CONFIG_DIR_HASH = 201670096L
        private val getConfigDirBind by lazy {
            ObjectCalls.getMethodBind("EditorPaths", "get_config_dir", GET_CONFIG_DIR_HASH)
        }

        private const val GET_CACHE_DIR_HASH = 201670096L
        private val getCacheDirBind by lazy {
            ObjectCalls.getMethodBind("EditorPaths", "get_cache_dir", GET_CACHE_DIR_HASH)
        }

        private const val IS_SELF_CONTAINED_HASH = 36873697L
        private val isSelfContainedBind by lazy {
            ObjectCalls.getMethodBind("EditorPaths", "is_self_contained", IS_SELF_CONTAINED_HASH)
        }

        private const val GET_SELF_CONTAINED_FILE_HASH = 201670096L
        private val getSelfContainedFileBind by lazy {
            ObjectCalls.getMethodBind("EditorPaths", "get_self_contained_file", GET_SELF_CONTAINED_FILE_HASH)
        }

        private const val GET_PROJECT_SETTINGS_DIR_HASH = 201670096L
        private val getProjectSettingsDirBind by lazy {
            ObjectCalls.getMethodBind("EditorPaths", "get_project_settings_dir", GET_PROJECT_SETTINGS_DIR_HASH)
        }
    }
}
