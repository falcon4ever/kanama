package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Editor-only singleton that returns paths to various OS-specific data folders and files.
 *
 * Generated from Godot docs: EditorPaths
 */
class EditorPaths(handle: MemorySegment) : GodotObject(handle) {
    fun getDataDir(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getDataDirBind, handle)
    }

    fun getConfigDir(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getConfigDirBind, handle)
    }

    fun getCacheDir(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getCacheDirBind, handle)
    }

    fun isSelfContained(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSelfContainedBind, handle)
    }

    fun getSelfContainedFile(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getSelfContainedFileBind, handle)
    }

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
