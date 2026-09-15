package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.ObjectCalls
import java.lang.foreign.MemorySegment

/**
 * Provides methods for managing directories and their content.
 *
 * Generated from Godot docs: DirAccess
 */
object DirAccess {

    data class Entry(val name: String, val isDirectory: Boolean)

    private const val NOARGS_ERROR_HASH = 166280745L
    private const val NOARGS_INT_HASH = 2455072627L
    private const val NOARGS_STRING_HASH = 201670096L
    private const val STRING_OBJECT_HASH = 1923528528L
    private const val BOOL_STRING_HASH = 1287308131L
    private const val LONG_STRING_HASH = 990163283L
    private const val STRING_STRING_HASH = 1703090593L
    private const val STRING_PACKED_STRING_ARRAY_HASH = 3538744774L
    private const val STRING_BOOL_HASH = 2323990056L
    private const val STRING_BOOL_ALT_HASH = 3927539163L
    private const val BOOL_VOID_HASH = 2586408642L
    private const val TWO_STRING_BOOL_HASH = 820780508L
    private const val STRING_ERROR_HASH = 166001499L
    private const val TWO_STRING_ERROR_HASH = 852856452L
    private const val TWO_STRING_INT_ERROR_HASH = 1063198817L

    private val openBind by lazy {
        ObjectCalls.getMethodBind("DirAccess", "open", STRING_OBJECT_HASH)
    }

    private val createTempBind by lazy {
        ObjectCalls.getMethodBind("DirAccess", "create_temp", 812913566L)
    }

    private val getOpenErrorBind by lazy {
        ObjectCalls.getMethodBind("DirAccess", "get_open_error", NOARGS_ERROR_HASH)
    }

    private val getDriveCountBind by lazy {
        ObjectCalls.getMethodBind("DirAccess", "get_drive_count", NOARGS_INT_HASH)
    }

    private val getDriveNameBind by lazy {
        ObjectCalls.getMethodBind("DirAccess", "get_drive_name", LONG_STRING_HASH)
    }

    private val getFilesAtBind by lazy {
        ObjectCalls.getMethodBind("DirAccess", "get_files_at", STRING_PACKED_STRING_ARRAY_HASH)
    }

    private val getDirectoriesAtBind by lazy {
        ObjectCalls.getMethodBind("DirAccess", "get_directories_at", STRING_PACKED_STRING_ARRAY_HASH)
    }

    private val dirExistsAbsoluteBind by lazy {
        ObjectCalls.getMethodBind("DirAccess", "dir_exists_absolute", STRING_BOOL_HASH)
    }

    private val fileExistsBind by lazy {
        ObjectCalls.getMethodBind("DirAccess", "file_exists", STRING_BOOL_HASH)
    }

    private val dirExistsBind by lazy {
        ObjectCalls.getMethodBind("DirAccess", "dir_exists", STRING_BOOL_HASH)
    }

    private val changeDirBind by lazy {
        ObjectCalls.getMethodBind("DirAccess", "change_dir", STRING_ERROR_HASH)
    }

    private val getCurrentDriveBind by lazy {
        ObjectCalls.getMethodBind("DirAccess", "get_current_drive", NOARGS_INT_HASH)
    }

    private val getCurrentDirBind by lazy {
        ObjectCalls.getMethodBind("DirAccess", "get_current_dir", BOOL_STRING_HASH)
    }

    private val getSpaceLeftBind by lazy {
        ObjectCalls.getMethodBind("DirAccess", "get_space_left", NOARGS_INT_HASH)
    }

    private val getFilesBind by lazy {
        ObjectCalls.getMethodBind("DirAccess", "get_files", 2981934095L)
    }

    private val getDirectoriesBind by lazy {
        ObjectCalls.getMethodBind("DirAccess", "get_directories", 2981934095L)
    }

    private val listDirBeginBind by lazy {
        ObjectCalls.getMethodBind("DirAccess", "list_dir_begin", NOARGS_ERROR_HASH)
    }

    private val getNextBind by lazy {
        ObjectCalls.getMethodBind("DirAccess", "get_next", 2841200299L)
    }

    private val currentIsDirBind by lazy {
        ObjectCalls.getMethodBind("DirAccess", "current_is_dir", 36873697L)
    }

    private val listDirEndBind by lazy {
        ObjectCalls.getMethodBind("DirAccess", "list_dir_end", 3218959716L)
    }

    private val setIncludeHiddenBind by lazy {
        ObjectCalls.getMethodBind("DirAccess", "set_include_hidden", BOOL_VOID_HASH)
    }

    private val getIncludeHiddenBind by lazy {
        ObjectCalls.getMethodBind("DirAccess", "get_include_hidden", 36873697L)
    }

    private val setIncludeNavigationalBind by lazy {
        ObjectCalls.getMethodBind("DirAccess", "set_include_navigational", BOOL_VOID_HASH)
    }

    private val getIncludeNavigationalBind by lazy {
        ObjectCalls.getMethodBind("DirAccess", "get_include_navigational", 36873697L)
    }

    private val getFilesystemTypeBind by lazy {
        ObjectCalls.getMethodBind("DirAccess", "get_filesystem_type", NOARGS_STRING_HASH)
    }

    private val isLinkBind by lazy {
        ObjectCalls.getMethodBind("DirAccess", "is_link", STRING_BOOL_HASH)
    }

    private val readLinkBind by lazy {
        ObjectCalls.getMethodBind("DirAccess", "read_link", STRING_STRING_HASH)
    }

    private val isBundleBind by lazy {
        ObjectCalls.getMethodBind("DirAccess", "is_bundle", STRING_BOOL_ALT_HASH)
    }

    private val isCaseSensitiveBind by lazy {
        ObjectCalls.getMethodBind("DirAccess", "is_case_sensitive", STRING_BOOL_ALT_HASH)
    }

    private val isEquivalentBind by lazy {
        ObjectCalls.getMethodBind("DirAccess", "is_equivalent", TWO_STRING_BOOL_HASH)
    }

    private val removeAbsoluteBind by lazy {
        ObjectCalls.getMethodBind("DirAccess", "remove_absolute", STRING_ERROR_HASH)
    }

    private val makeDirAbsoluteBind by lazy {
        ObjectCalls.getMethodBind("DirAccess", "make_dir_absolute", STRING_ERROR_HASH)
    }

    private val makeDirRecursiveAbsoluteBind by lazy {
        ObjectCalls.getMethodBind("DirAccess", "make_dir_recursive_absolute", STRING_ERROR_HASH)
    }

    private val makeDirBind by lazy {
        ObjectCalls.getMethodBind("DirAccess", "make_dir", STRING_ERROR_HASH)
    }

    private val makeDirRecursiveBind by lazy {
        ObjectCalls.getMethodBind("DirAccess", "make_dir_recursive", STRING_ERROR_HASH)
    }

    private val removeBind by lazy {
        ObjectCalls.getMethodBind("DirAccess", "remove", STRING_ERROR_HASH)
    }

    private val copyAbsoluteBind by lazy {
        ObjectCalls.getMethodBind("DirAccess", "copy_absolute", TWO_STRING_INT_ERROR_HASH)
    }

    private val renameAbsoluteBind by lazy {
        ObjectCalls.getMethodBind("DirAccess", "rename_absolute", TWO_STRING_ERROR_HASH)
    }

    private val copyBind by lazy {
        ObjectCalls.getMethodBind("DirAccess", "copy", TWO_STRING_INT_ERROR_HASH)
    }

    private val renameBind by lazy {
        ObjectCalls.getMethodBind("DirAccess", "rename", TWO_STRING_ERROR_HASH)
    }

    private val createLinkBind by lazy {
        ObjectCalls.getMethodBind("DirAccess", "create_link", TWO_STRING_ERROR_HASH)
    }

    private fun <T> withOpenDir(path: String, fallback: T, block: (MemorySegment) -> T): T {
        val dir = ObjectCalls.ptrcallWithStringArgRetObject(openBind, MemorySegment.NULL, path)
        if (dir.address() == 0L) return fallback
        return try {
            block(dir)
        } finally {
            ObjectCalls.destroyObject(dir)
        }
    }

    // Error-returning variant: the open error must be read AFTER the failed open (an eagerly
    // evaluated fallback would capture the error state of a previous, unrelated open).
    private fun withOpenDirOrOpenError(path: String, block: (MemorySegment) -> Long): Long {
        val dir = ObjectCalls.ptrcallWithStringArgRetObject(openBind, MemorySegment.NULL, path)
        if (dir.address() == 0L) return getOpenError()
        return try {
            block(dir)
        } finally {
            ObjectCalls.destroyObject(dir)
        }
    }

    internal fun fileExistsHandle(handle: MemorySegment, path: String): Boolean =
        ObjectCalls.ptrcallWithStringArgRetBool(fileExistsBind, handle, path)

    internal fun dirExistsHandle(handle: MemorySegment, path: String): Boolean =
        ObjectCalls.ptrcallWithStringArgRetBool(dirExistsBind, handle, path)

    internal fun getCurrentDirHandle(handle: MemorySegment, includeDrive: Boolean): String =
        ObjectCalls.ptrcallWithBoolArgRetString(getCurrentDirBind, handle, includeDrive)

    internal fun getFilesHandle(handle: MemorySegment): List<String> =
        ObjectCalls.ptrcallNoArgsRetPackedStringList(getFilesBind, handle)

    internal fun getDirectoriesHandle(handle: MemorySegment): List<String> =
        ObjectCalls.ptrcallNoArgsRetPackedStringList(getDirectoriesBind, handle)

    internal fun createLinkHandle(handle: MemorySegment, source: String, target: String): Long =
        ObjectCalls.ptrcallWithTwoStringArgsRetLong(createLinkBind, handle, source, target)

    /**
     * Returns the result of the last `open` call in the current thread.
     *
     * Generated from Godot docs: DirAccess.get_open_error
     */
    @JvmStatic
    fun getOpenError(): Long =
        ObjectCalls.ptrcallNoArgsRetLong(getOpenErrorBind, MemorySegment.NULL)

    /**
     * Creates a new `DirAccess` object and opens an existing directory of the filesystem. The `path`
     * argument can be within the project tree (`res://folder`), the user directory (`user://folder`)
     * or an absolute path of the user filesystem (e.g. `/tmp/folder` or `C:\tmp\folder`). Returns
     * `null` if opening the directory failed. You can use `get_open_error` to check the error that
     * occurred.
     *
     * Generated from Godot docs: DirAccess.open
     */
    @JvmStatic
    fun open(path: String): DirAccessHandle? =
        DirAccessHandle.wrap(ObjectCalls.ptrcallWithStringArgRetObject(openBind, MemorySegment.NULL, path))

    /**
     * Creates a temporary directory. This directory will be freed when the returned `DirAccess` is
     * freed. If `prefix` is not empty, it will be prefixed to the directory name, separated by a `-`.
     * If `keep` is `true`, the directory is not deleted when the returned `DirAccess` is freed.
     * Returns `null` if opening the directory failed. You can use `get_open_error` to check the error
     * that occurred.
     *
     * Generated from Godot docs: DirAccess.create_temp
     */
    @JvmStatic
    fun createTemp(prefix: String = "", keep: Boolean = false): DirAccessHandle? =
        DirAccessHandle.wrap(
            ObjectCalls.ptrcallWithStringAndBoolArgRetObject(
                createTempBind,
                MemorySegment.NULL,
                prefix,
                keep,
            ),
        )

    /**
     * On Windows, returns the number of drives (partitions) mounted on the current filesystem. On
     * macOS and Android, returns the number of mounted volumes. On Linux, returns the number of
     * mounted volumes and GTK 3 bookmarks. On other platforms, the method returns 0.
     *
     * Generated from Godot docs: DirAccess.get_drive_count
     */
    @JvmStatic
    fun getDriveCount(): Long =
        ObjectCalls.ptrcallNoArgsRetInt(getDriveCountBind, MemorySegment.NULL).toLong()

    /**
     * On Windows, returns the name of the drive (partition) passed as an argument (e.g. `C:`). On
     * macOS, returns the path to the mounted volume passed as an argument. On Linux, returns the path
     * to the mounted volume or GTK 3 bookmark passed as an argument. On Android (API level 30+),
     * returns the path to the mounted volume as an argument. On other platforms, or if the requested
     * drive does not exist, returns an empty String.
     *
     * Generated from Godot docs: DirAccess.get_drive_name
     */
    @JvmStatic
    fun getDriveName(index: Long): String =
        ObjectCalls.ptrcallWithIntArgRetString(getDriveNameBind, MemorySegment.NULL, index.toInt())

    /**
     * Returns a `PackedStringArray` containing filenames of the directory contents, excluding
     * directories, at the given `path`. The array is sorted alphabetically. Use `get_files` if you
     * want more control of what gets included. Note: When used on a `res://` path in an exported
     * project, only the files included in the PCK at the given folder level are returned. In practice,
     * this means that since imported resources are stored in a top-level `.godot/` folder, only paths
     * to `.gd` and `.import` files are returned (plus a few other files, such as `project.godot` or
     * `project.binary` and the project icon). In an exported project, the list of returned files will
     * also vary depending on `ProjectSettings.editor/export/convert_text_resources_to_binary`.
     *
     * Generated from Godot docs: DirAccess.get_files_at
     */
    @JvmStatic
    fun getFilesAt(path: String): List<String> =
        ObjectCalls.ptrcallWithStringArgRetPackedStringList(getFilesAtBind, MemorySegment.NULL, path)

    /**
     * Returns a `PackedStringArray` containing filenames of the directory contents, excluding files,
     * at the given `path`. The array is sorted alphabetically. Use `get_directories` if you want more
     * control of what gets included. Note: The returned directories in the editor and after exporting
     * in the `res://` directory may differ as some files are converted to engine-specific formats when
     * exported.
     *
     * Generated from Godot docs: DirAccess.get_directories_at
     */
    @JvmStatic
    fun getDirectoriesAt(path: String): List<String> =
        ObjectCalls.ptrcallWithStringArgRetPackedStringList(getDirectoriesAtBind, MemorySegment.NULL, path)

    /**
     * Static version of `dir_exists`. Supports only absolute paths. Note: The returned `bool` in the
     * editor and after exporting when used on a path in the `res://` directory may be different. Some
     * files are converted to engine-specific formats when exported, potentially changing the directory
     * structure.
     *
     * Generated from Godot docs: DirAccess.dir_exists_absolute
     */
    @JvmStatic
    fun dirExistsAbsolute(path: String): Boolean =
        ObjectCalls.ptrcallWithStringArgRetBool(dirExistsAbsoluteBind, MemorySegment.NULL, path)

    @JvmStatic
    fun fileExistsAt(directoryPath: String, filePath: String): Boolean =
        withOpenDir(directoryPath, false) { dir ->
            ObjectCalls.ptrcallWithStringArgRetBool(fileExistsBind, dir, filePath)
        }

    @JvmStatic
    fun dirExistsAt(directoryPath: String, path: String): Boolean =
        withOpenDir(directoryPath, false) { dir ->
            ObjectCalls.ptrcallWithStringArgRetBool(dirExistsBind, dir, path)
        }

    @JvmStatic
    fun changeDirAt(directoryPath: String, toDir: String): Long =
        withOpenDirOrOpenError(directoryPath) { dir ->
            ObjectCalls.ptrcallWithStringArgRetLong(changeDirBind, dir, toDir)
        }

    @JvmStatic
    fun getCurrentDriveAt(directoryPath: String): Long =
        withOpenDir(directoryPath, -1L) { dir ->
            ObjectCalls.ptrcallNoArgsRetInt(getCurrentDriveBind, dir).toLong()
        }

    @JvmStatic
    fun getCurrentDirAt(directoryPath: String, includeDrive: Boolean = true): String =
        withOpenDir(directoryPath, "") { dir ->
            ObjectCalls.ptrcallWithBoolArgRetString(getCurrentDirBind, dir, includeDrive)
        }

    @JvmStatic
    fun getSpaceLeftAt(directoryPath: String): Long =
        withOpenDir(directoryPath, 0L) { dir ->
            ObjectCalls.ptrcallNoArgsRetLong(getSpaceLeftBind, dir)
        }

    /**
     * Returns a `PackedStringArray` containing filenames of the directory contents, excluding
     * directories, at the given `path`. The array is sorted alphabetically. Use `get_files` if you
     * want more control of what gets included. Note: When used on a `res://` path in an exported
     * project, only the files included in the PCK at the given folder level are returned. In practice,
     * this means that since imported resources are stored in a top-level `.godot/` folder, only paths
     * to `.gd` and `.import` files are returned (plus a few other files, such as `project.godot` or
     * `project.binary` and the project icon). In an exported project, the list of returned files will
     * also vary depending on `ProjectSettings.editor/export/convert_text_resources_to_binary`.
     *
     * Generated from Godot docs: DirAccess.get_files_at
     */
    @JvmStatic
    fun getFilesAt(directoryPath: String, includeHidden: Boolean, includeNavigational: Boolean = false): List<String> =
        withOpenDir(directoryPath, emptyList()) { dir ->
            ObjectCalls.ptrcallWithBoolArg(setIncludeHiddenBind, dir, includeHidden)
            ObjectCalls.ptrcallWithBoolArg(setIncludeNavigationalBind, dir, includeNavigational)
            ObjectCalls.ptrcallNoArgsRetPackedStringList(getFilesBind, dir)
        }

    /**
     * Returns a `PackedStringArray` containing filenames of the directory contents, excluding files,
     * at the given `path`. The array is sorted alphabetically. Use `get_directories` if you want more
     * control of what gets included. Note: The returned directories in the editor and after exporting
     * in the `res://` directory may differ as some files are converted to engine-specific formats when
     * exported.
     *
     * Generated from Godot docs: DirAccess.get_directories_at
     */
    @JvmStatic
    fun getDirectoriesAt(
        directoryPath: String,
        includeHidden: Boolean,
        includeNavigational: Boolean = false,
    ): List<String> =
        withOpenDir(directoryPath, emptyList()) { dir ->
            ObjectCalls.ptrcallWithBoolArg(setIncludeHiddenBind, dir, includeHidden)
            ObjectCalls.ptrcallWithBoolArg(setIncludeNavigationalBind, dir, includeNavigational)
            ObjectCalls.ptrcallNoArgsRetPackedStringList(getDirectoriesBind, dir)
        }

    @JvmStatic
    fun getIncludeHiddenAt(directoryPath: String): Boolean =
        withOpenDir(directoryPath, false) { dir ->
            ObjectCalls.ptrcallNoArgsRetBool(getIncludeHiddenBind, dir)
        }

    @JvmStatic
    fun getIncludeNavigationalAt(directoryPath: String): Boolean =
        withOpenDir(directoryPath, false) { dir ->
            ObjectCalls.ptrcallNoArgsRetBool(getIncludeNavigationalBind, dir)
        }

    @JvmStatic
    fun listEntriesAt(
        directoryPath: String,
        includeHidden: Boolean = false,
        includeNavigational: Boolean = false,
    ): List<Entry> =
        withOpenDir(directoryPath, emptyList()) { dir ->
            ObjectCalls.ptrcallWithBoolArg(setIncludeHiddenBind, dir, includeHidden)
            ObjectCalls.ptrcallWithBoolArg(setIncludeNavigationalBind, dir, includeNavigational)
            val error = ObjectCalls.ptrcallNoArgsRetLong(listDirBeginBind, dir)
            if (error != 0L) {
                return@withOpenDir emptyList()
            }
            val entries = mutableListOf<Entry>()
            try {
                while (true) {
                    val name = ObjectCalls.ptrcallNoArgsRetString(getNextBind, dir)
                    if (name.isEmpty()) break
                    entries += Entry(name, ObjectCalls.ptrcallNoArgsRetBool(currentIsDirBind, dir))
                }
            } finally {
                ObjectCalls.ptrcallNoArgs(listDirEndBind, dir)
            }
            entries
        }

    @JvmStatic
    fun getFilesystemTypeAt(directoryPath: String): String =
        withOpenDir(directoryPath, "") { dir ->
            ObjectCalls.ptrcallNoArgsRetString(getFilesystemTypeBind, dir)
        }

    @JvmStatic
    fun isLinkAt(directoryPath: String, path: String): Boolean =
        withOpenDir(directoryPath, false) { dir ->
            ObjectCalls.ptrcallWithStringArgRetBool(isLinkBind, dir, path)
        }

    @JvmStatic
    fun readLinkAt(directoryPath: String, path: String): String =
        withOpenDir(directoryPath, "") { dir ->
            ObjectCalls.ptrcallWithStringArgRetString(readLinkBind, dir, path)
        }

    @JvmStatic
    fun isBundleAt(directoryPath: String, path: String): Boolean =
        withOpenDir(directoryPath, false) { dir ->
            ObjectCalls.ptrcallWithStringArgRetBool(isBundleBind, dir, path)
        }

    @JvmStatic
    fun isCaseSensitiveAt(directoryPath: String, path: String): Boolean =
        withOpenDir(directoryPath, false) { dir ->
            ObjectCalls.ptrcallWithStringArgRetBool(isCaseSensitiveBind, dir, path)
        }

    @JvmStatic
    fun isEquivalentAt(directoryPath: String, pathA: String, pathB: String): Boolean =
        withOpenDir(directoryPath, false) { dir ->
            ObjectCalls.ptrcallWithTwoStringArgsRetBool(isEquivalentBind, dir, pathA, pathB)
        }

    /**
     * Static version of `remove`. Supports only absolute paths.
     *
     * Generated from Godot docs: DirAccess.remove_absolute
     */
    @JvmStatic
    fun removeAbsolute(path: String): Long =
        ObjectCalls.ptrcallWithStringArgRetLong(removeAbsoluteBind, MemorySegment.NULL, path)

    /**
     * Static version of `make_dir`. Supports only absolute paths.
     *
     * Generated from Godot docs: DirAccess.make_dir_absolute
     */
    @JvmStatic
    fun makeDirAbsolute(path: String): Long =
        ObjectCalls.ptrcallWithStringArgRetLong(makeDirAbsoluteBind, MemorySegment.NULL, path)

    /**
     * Static version of `make_dir_recursive`. Supports only absolute paths.
     *
     * Generated from Godot docs: DirAccess.make_dir_recursive_absolute
     */
    @JvmStatic
    fun makeDirRecursiveAbsolute(path: String): Long =
        ObjectCalls.ptrcallWithStringArgRetLong(makeDirRecursiveAbsoluteBind, MemorySegment.NULL, path)

    @JvmStatic
    fun makeDirAt(directoryPath: String, path: String): Long =
        withOpenDirOrOpenError(directoryPath) { dir ->
            ObjectCalls.ptrcallWithStringArgRetLong(makeDirBind, dir, path)
        }

    @JvmStatic
    fun makeDirRecursiveAt(directoryPath: String, path: String): Long =
        withOpenDirOrOpenError(directoryPath) { dir ->
            ObjectCalls.ptrcallWithStringArgRetLong(makeDirRecursiveBind, dir, path)
        }

    @JvmStatic
    fun removeAt(directoryPath: String, path: String): Long =
        withOpenDirOrOpenError(directoryPath) { dir ->
            ObjectCalls.ptrcallWithStringArgRetLong(removeBind, dir, path)
        }

    /**
     * Static version of `copy`. Supports only absolute paths.
     *
     * Generated from Godot docs: DirAccess.copy_absolute
     */
    @JvmStatic
    fun copyAbsolute(from: String, to: String, chmodFlags: Int = -1): Long =
        ObjectCalls.ptrcallWithTwoStringAndIntArgsRetLong(copyAbsoluteBind, MemorySegment.NULL, from, to, chmodFlags)

    /**
     * Static version of `rename`. Supports only absolute paths.
     *
     * Generated from Godot docs: DirAccess.rename_absolute
     */
    @JvmStatic
    fun renameAbsolute(from: String, to: String): Long =
        ObjectCalls.ptrcallWithTwoStringArgsRetLong(renameAbsoluteBind, MemorySegment.NULL, from, to)

    @JvmStatic
    fun copyAt(directoryPath: String, from: String, to: String, chmodFlags: Int = -1): Long =
        withOpenDirOrOpenError(directoryPath) { dir ->
            ObjectCalls.ptrcallWithTwoStringAndIntArgsRetLong(copyBind, dir, from, to, chmodFlags)
        }

    @JvmStatic
    fun renameAt(directoryPath: String, from: String, to: String): Long =
        withOpenDirOrOpenError(directoryPath) { dir ->
            ObjectCalls.ptrcallWithTwoStringArgsRetLong(renameBind, dir, from, to)
        }

    @JvmStatic
    fun createLinkAt(directoryPath: String, source: String, target: String): Long =
        withOpenDirOrOpenError(directoryPath) { dir ->
            ObjectCalls.ptrcallWithTwoStringArgsRetLong(createLinkBind, dir, source, target)
        }

}
