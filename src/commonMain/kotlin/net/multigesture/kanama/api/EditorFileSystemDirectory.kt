package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * A directory for the resource filesystem.
 *
 * Generated from Godot docs: EditorFileSystemDirectory
 */
class EditorFileSystemDirectory(handle: MemorySegment) : GodotObject(handle) {
    /**
     * Returns the number of subdirectories in this directory.
     *
     * Generated from Godot docs: EditorFileSystemDirectory.get_subdir_count
     */
    fun getSubdirCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSubdirCountBind, handle)
    }

    /**
     * Returns the subdirectory at index `idx`.
     *
     * Generated from Godot docs: EditorFileSystemDirectory.get_subdir
     */
    fun getSubdir(idx: Int): EditorFileSystemDirectory? {
        return EditorFileSystemDirectory.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getSubdirBind, handle, idx))
    }

    /**
     * Returns the number of files in this directory.
     *
     * Generated from Godot docs: EditorFileSystemDirectory.get_file_count
     */
    fun getFileCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getFileCountBind, handle)
    }

    /**
     * Returns `true` if the file at index `idx` imported properly.
     *
     * Generated from Godot docs: EditorFileSystemDirectory.get_file_import_is_valid
     */
    fun getFileImportIsValid(idx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getFileImportIsValidBind, handle, idx)
    }

    /**
     * Returns the name of this directory.
     *
     * Generated from Godot docs: EditorFileSystemDirectory.get_name
     */
    fun getName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getNameBind, handle)
    }

    /**
     * Returns the path to this directory.
     *
     * Generated from Godot docs: EditorFileSystemDirectory.get_path
     */
    fun getPath(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getPathBind, handle)
    }

    /**
     * Returns the parent directory for this directory or `null` if called on a directory at `res://`
     * or `user://`.
     *
     * Generated from Godot docs: EditorFileSystemDirectory.get_parent
     */
    fun getParent(): EditorFileSystemDirectory? {
        return EditorFileSystemDirectory.wrap(ObjectCalls.ptrcallNoArgsRetObject(getParentBind, handle))
    }

    /**
     * Returns the index of the file with name `name` or `-1` if not found.
     *
     * Generated from Godot docs: EditorFileSystemDirectory.find_file_index
     */
    fun findFileIndex(name: String): Int {
        return ObjectCalls.ptrcallWithStringArgRetInt(findFileIndexBind, handle, name)
    }

    /**
     * Returns the index of the directory with name `name` or `-1` if not found.
     *
     * Generated from Godot docs: EditorFileSystemDirectory.find_dir_index
     */
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
