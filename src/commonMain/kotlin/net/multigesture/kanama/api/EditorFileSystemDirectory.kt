package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A directory for the resource filesystem.
 *
 * Generated from Godot docs: EditorFileSystemDirectory
 */
class EditorFileSystemDirectory(handle: GodotHandle) : GodotObject(handle) {
    /**
     * Returns the number of subdirectories in this directory.
     *
     * Generated from Godot docs: EditorFileSystemDirectory.get_subdir_count
     */
    fun getSubdirCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSubdirCountBind, segment)
    }

    /**
     * Returns the subdirectory at index `idx`.
     *
     * Generated from Godot docs: EditorFileSystemDirectory.get_subdir
     */
    fun getSubdir(idx: Int): EditorFileSystemDirectory? {
        return EditorFileSystemDirectory.wrap(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getSubdirBind, segment, idx))
    }

    /**
     * Returns the number of files in this directory.
     *
     * Generated from Godot docs: EditorFileSystemDirectory.get_file_count
     */
    fun getFileCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getFileCountBind, segment)
    }

    /**
     * Returns the name of the file at index `idx`.
     *
     * Generated from Godot docs: EditorFileSystemDirectory.get_file
     */
    fun getFile(idx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(Binds.getFileBind, segment, idx)
    }

    /**
     * Returns the path to the file at index `idx`.
     *
     * Generated from Godot docs: EditorFileSystemDirectory.get_file_path
     */
    fun getFilePath(idx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(Binds.getFilePathBind, segment, idx)
    }

    /**
     * Returns the resource type of the file at index `idx`. This returns a string such as `"Resource"`
     * or `"GDScript"`, not a file extension such as `".gd"`.
     *
     * Generated from Godot docs: EditorFileSystemDirectory.get_file_type
     */
    fun getFileType(idx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetStringName(Binds.getFileTypeBind, segment, idx)
    }

    /**
     * Returns the name of the script class defined in the file at index `idx`. If the file doesn't
     * define a script class using the `class_name` syntax, this will return an empty string.
     *
     * Generated from Godot docs: EditorFileSystemDirectory.get_file_script_class_name
     */
    fun getFileScriptClassName(idx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(Binds.getFileScriptClassNameBind, segment, idx)
    }

    /**
     * Returns the base class of the script class defined in the file at index `idx`. If the file
     * doesn't define a script class using the `class_name` syntax, this will return an empty string.
     *
     * Generated from Godot docs: EditorFileSystemDirectory.get_file_script_class_extends
     */
    fun getFileScriptClassExtends(idx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(Binds.getFileScriptClassExtendsBind, segment, idx)
    }

    /**
     * Returns `true` if the file at index `idx` imported properly.
     *
     * Generated from Godot docs: EditorFileSystemDirectory.get_file_import_is_valid
     */
    fun getFileImportIsValid(idx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.getFileImportIsValidBind, segment, idx)
    }

    /**
     * Returns the name of this directory.
     *
     * Generated from Godot docs: EditorFileSystemDirectory.get_name
     */
    fun getName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getNameBind, segment)
    }

    /**
     * Returns the path to this directory.
     *
     * Generated from Godot docs: EditorFileSystemDirectory.get_path
     */
    fun getPath(): String {
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getPathBind, segment)
    }

    /**
     * Returns the parent directory for this directory or `null` if called on a directory at `res://`
     * or `user://`.
     *
     * Generated from Godot docs: EditorFileSystemDirectory.get_parent
     */
    fun getParent(): EditorFileSystemDirectory? {
        return EditorFileSystemDirectory.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getParentBind, segment))
    }

    /**
     * Returns the index of the file with name `name` or `-1` if not found.
     *
     * Generated from Godot docs: EditorFileSystemDirectory.find_file_index
     */
    fun findFileIndex(name: String): Int {
        return ObjectCalls.ptrcallWithStringArgRetInt(Binds.findFileIndexBind, segment, name)
    }

    /**
     * Returns the index of the directory with name `name` or `-1` if not found.
     *
     * Generated from Godot docs: EditorFileSystemDirectory.find_dir_index
     */
    fun findDirIndex(name: String): Int {
        return ObjectCalls.ptrcallWithStringArgRetInt(Binds.findDirIndexBind, segment, name)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): EditorFileSystemDirectory? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): EditorFileSystemDirectory? =
            if (handle.address() == 0L) null else EditorFileSystemDirectory(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_SUBDIR_COUNT_HASH = 3905245786L
        @JvmField
        val getSubdirCountBind =
            ObjectCalls.getMethodBind("EditorFileSystemDirectory", "get_subdir_count", GET_SUBDIR_COUNT_HASH)

        private const val GET_SUBDIR_HASH = 2330964164L
        @JvmField
        val getSubdirBind =
            ObjectCalls.getMethodBind("EditorFileSystemDirectory", "get_subdir", GET_SUBDIR_HASH)

        private const val GET_FILE_COUNT_HASH = 3905245786L
        @JvmField
        val getFileCountBind =
            ObjectCalls.getMethodBind("EditorFileSystemDirectory", "get_file_count", GET_FILE_COUNT_HASH)

        private const val GET_FILE_HASH = 844755477L
        @JvmField
        val getFileBind =
            ObjectCalls.getMethodBind("EditorFileSystemDirectory", "get_file", GET_FILE_HASH)

        private const val GET_FILE_PATH_HASH = 844755477L
        @JvmField
        val getFilePathBind =
            ObjectCalls.getMethodBind("EditorFileSystemDirectory", "get_file_path", GET_FILE_PATH_HASH)

        private const val GET_FILE_TYPE_HASH = 659327637L
        @JvmField
        val getFileTypeBind =
            ObjectCalls.getMethodBind("EditorFileSystemDirectory", "get_file_type", GET_FILE_TYPE_HASH)

        private const val GET_FILE_SCRIPT_CLASS_NAME_HASH = 844755477L
        @JvmField
        val getFileScriptClassNameBind =
            ObjectCalls.getMethodBind("EditorFileSystemDirectory", "get_file_script_class_name", GET_FILE_SCRIPT_CLASS_NAME_HASH)

        private const val GET_FILE_SCRIPT_CLASS_EXTENDS_HASH = 844755477L
        @JvmField
        val getFileScriptClassExtendsBind =
            ObjectCalls.getMethodBind("EditorFileSystemDirectory", "get_file_script_class_extends", GET_FILE_SCRIPT_CLASS_EXTENDS_HASH)

        private const val GET_FILE_IMPORT_IS_VALID_HASH = 1116898809L
        @JvmField
        val getFileImportIsValidBind =
            ObjectCalls.getMethodBind("EditorFileSystemDirectory", "get_file_import_is_valid", GET_FILE_IMPORT_IS_VALID_HASH)

        private const val GET_NAME_HASH = 2841200299L
        @JvmField
        val getNameBind =
            ObjectCalls.getMethodBind("EditorFileSystemDirectory", "get_name", GET_NAME_HASH)

        private const val GET_PATH_HASH = 201670096L
        @JvmField
        val getPathBind =
            ObjectCalls.getMethodBind("EditorFileSystemDirectory", "get_path", GET_PATH_HASH)

        private const val GET_PARENT_HASH = 842323275L
        @JvmField
        val getParentBind =
            ObjectCalls.getMethodBind("EditorFileSystemDirectory", "get_parent", GET_PARENT_HASH)

        private const val FIND_FILE_INDEX_HASH = 1321353865L
        @JvmField
        val findFileIndexBind =
            ObjectCalls.getMethodBind("EditorFileSystemDirectory", "find_file_index", FIND_FILE_INDEX_HASH)

        private const val FIND_DIR_INDEX_HASH = 1321353865L
        @JvmField
        val findDirIndexBind =
            ObjectCalls.getMethodBind("EditorFileSystemDirectory", "find_dir_index", FIND_DIR_INDEX_HASH)
    }
}
