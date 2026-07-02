package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A directory for the resource filesystem.
 *
 * Generated from Godot docs: EditorFileSystemDirectory
 */
class EditorFileSystemDirectory(handle: MemorySegment) : GodotObject(handle) {
    fun getSubdirCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSubdirCountBind, handle)
    }

    fun getSubdir(idx: Int): EditorFileSystemDirectory? {
        return EditorFileSystemDirectory.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getSubdirBind, handle, idx))
    }

    fun getFileCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getFileCountBind, handle)
    }

    fun getFile(idx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getFileBind, handle, idx)
    }

    fun getFilePath(idx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getFilePathBind, handle, idx)
    }

    fun getFileType(idx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetStringName(getFileTypeBind, handle, idx)
    }

    fun getFileScriptClassName(idx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getFileScriptClassNameBind, handle, idx)
    }

    fun getFileScriptClassExtends(idx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getFileScriptClassExtendsBind, handle, idx)
    }

    fun getFileImportIsValid(idx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getFileImportIsValidBind, handle, idx)
    }

    fun getName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getNameBind, handle)
    }

    fun getPath(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getPathBind, handle)
    }

    fun getParent(): EditorFileSystemDirectory? {
        return EditorFileSystemDirectory.wrap(ObjectCalls.ptrcallNoArgsRetObject(getParentBind, handle))
    }

    fun findFileIndex(name: String): Int {
        return ObjectCalls.ptrcallWithStringArgRetInt(findFileIndexBind, handle, name)
    }

    fun findDirIndex(name: String): Int {
        return ObjectCalls.ptrcallWithStringArgRetInt(findDirIndexBind, handle, name)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorFileSystemDirectory? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorFileSystemDirectory? =
            if (handle.address() == 0L) null else EditorFileSystemDirectory(handle)

        private const val GET_SUBDIR_COUNT_HASH = 3905245786L
        private val getSubdirCountBind by lazy {
            ObjectCalls.getMethodBind("EditorFileSystemDirectory", "get_subdir_count", GET_SUBDIR_COUNT_HASH)
        }

        private const val GET_SUBDIR_HASH = 2330964164L
        private val getSubdirBind by lazy {
            ObjectCalls.getMethodBind("EditorFileSystemDirectory", "get_subdir", GET_SUBDIR_HASH)
        }

        private const val GET_FILE_COUNT_HASH = 3905245786L
        private val getFileCountBind by lazy {
            ObjectCalls.getMethodBind("EditorFileSystemDirectory", "get_file_count", GET_FILE_COUNT_HASH)
        }

        private const val GET_FILE_HASH = 844755477L
        private val getFileBind by lazy {
            ObjectCalls.getMethodBind("EditorFileSystemDirectory", "get_file", GET_FILE_HASH)
        }

        private const val GET_FILE_PATH_HASH = 844755477L
        private val getFilePathBind by lazy {
            ObjectCalls.getMethodBind("EditorFileSystemDirectory", "get_file_path", GET_FILE_PATH_HASH)
        }

        private const val GET_FILE_TYPE_HASH = 659327637L
        private val getFileTypeBind by lazy {
            ObjectCalls.getMethodBind("EditorFileSystemDirectory", "get_file_type", GET_FILE_TYPE_HASH)
        }

        private const val GET_FILE_SCRIPT_CLASS_NAME_HASH = 844755477L
        private val getFileScriptClassNameBind by lazy {
            ObjectCalls.getMethodBind("EditorFileSystemDirectory", "get_file_script_class_name", GET_FILE_SCRIPT_CLASS_NAME_HASH)
        }

        private const val GET_FILE_SCRIPT_CLASS_EXTENDS_HASH = 844755477L
        private val getFileScriptClassExtendsBind by lazy {
            ObjectCalls.getMethodBind("EditorFileSystemDirectory", "get_file_script_class_extends", GET_FILE_SCRIPT_CLASS_EXTENDS_HASH)
        }

        private const val GET_FILE_IMPORT_IS_VALID_HASH = 1116898809L
        private val getFileImportIsValidBind by lazy {
            ObjectCalls.getMethodBind("EditorFileSystemDirectory", "get_file_import_is_valid", GET_FILE_IMPORT_IS_VALID_HASH)
        }

        private const val GET_NAME_HASH = 2841200299L
        private val getNameBind by lazy {
            ObjectCalls.getMethodBind("EditorFileSystemDirectory", "get_name", GET_NAME_HASH)
        }

        private const val GET_PATH_HASH = 201670096L
        private val getPathBind by lazy {
            ObjectCalls.getMethodBind("EditorFileSystemDirectory", "get_path", GET_PATH_HASH)
        }

        private const val GET_PARENT_HASH = 842323275L
        private val getParentBind by lazy {
            ObjectCalls.getMethodBind("EditorFileSystemDirectory", "get_parent", GET_PARENT_HASH)
        }

        private const val FIND_FILE_INDEX_HASH = 1321353865L
        private val findFileIndexBind by lazy {
            ObjectCalls.getMethodBind("EditorFileSystemDirectory", "find_file_index", FIND_FILE_INDEX_HASH)
        }

        private const val FIND_DIR_INDEX_HASH = 1321353865L
        private val findDirIndexBind by lazy {
            ObjectCalls.getMethodBind("EditorFileSystemDirectory", "find_dir_index", FIND_DIR_INDEX_HASH)
        }
    }
}
