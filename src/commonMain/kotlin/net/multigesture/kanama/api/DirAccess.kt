package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Provides methods for managing directories and their content.
 *
 * Generated from Godot docs: DirAccess
 */
class DirAccess(handle: GodotHandle) : RefCounted(handle) {
    var includeNavigational: Boolean
        @JvmName("includeNavigationalProperty")
        get() = getIncludeNavigational()
        @JvmName("setIncludeNavigationalProperty")
        set(value) = setIncludeNavigational(value)

    var includeHidden: Boolean
        @JvmName("includeHiddenProperty")
        get() = getIncludeHidden()
        @JvmName("setIncludeHiddenProperty")
        set(value) = setIncludeHidden(value)

    /**
     * Initializes the stream used to list all files and directories using the `get_next` function,
     * closing the currently opened stream if needed. Once the stream has been processed, it should
     * typically be closed with `list_dir_end`. Affected by `include_hidden` and
     * `include_navigational`. Note: The order of files and directories returned by this method is not
     * deterministic, and can vary between operating systems. If you want a list of all files or
     * folders sorted alphabetically, use `get_files` or `get_directories`.
     *
     * Generated from Godot docs: DirAccess.list_dir_begin
     */
    fun listDirBegin(): GodotError {
        checkOpen()
        val ret = GodotError(ObjectCalls.ptrcallNoArgsRetLong(Binds.listDirBeginBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the next element (file or directory) in the current directory. The name of the file or
     * directory is returned (and not its full path). Once the stream has been fully processed, the
     * method returns an empty `String` and closes the stream automatically (i.e. `list_dir_end` would
     * not be mandatory in such a case).
     *
     * Generated from Godot docs: DirAccess.get_next
     */
    fun getNext(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getNextBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns whether the current item processed with the last `get_next` call is a directory (`.` and
     * `..` are considered directories).
     *
     * Generated from Godot docs: DirAccess.current_is_dir
     */
    fun currentIsDir(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.currentIsDirBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Closes the current stream opened with `list_dir_begin` (whether it has been fully processed with
     * `get_next` does not matter).
     *
     * Generated from Godot docs: DirAccess.list_dir_end
     */
    fun listDirEnd() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.listDirEndBind, segment)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns a `PackedStringArray` containing filenames of the directory contents, excluding
     * directories. The array is sorted alphabetically. Affected by `include_hidden`. Note: When used
     * on a `res://` path in an exported project, only the files actually included in the PCK at the
     * given folder level are returned. In practice, this means that since imported resources are
     * stored in a top-level `.godot/` folder, only paths to `*.gd` and `*.import` files are returned
     * (plus a few files such as `project.godot` or `project.binary` and the project icon). In an
     * exported project, the list of returned files will also vary depending on whether
     * `ProjectSettings.editor/export/convert_text_resources_to_binary` is `true`.
     *
     * Generated from Godot docs: DirAccess.get_files
     */
    fun getFiles(): List<String> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetPackedStringList(Binds.getFilesBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns a `PackedStringArray` containing filenames of the directory contents, excluding files.
     * The array is sorted alphabetically. Affected by `include_hidden` and `include_navigational`.
     * Note: The returned directories in the editor and after exporting in the `res://` directory may
     * differ as some files are converted to engine-specific formats when exported.
     *
     * Generated from Godot docs: DirAccess.get_directories
     */
    fun getDirectories(): List<String> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetPackedStringList(Binds.getDirectoriesBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the currently opened directory's drive index. See `get_drive_name` to convert returned
     * index to the name of the drive.
     *
     * Generated from Godot docs: DirAccess.get_current_drive
     */
    fun getCurrentDrive(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getCurrentDriveBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Changes the currently opened directory to the one passed as an argument. The argument can be
     * relative to the current directory (e.g. `newdir` or `../newdir`), or an absolute path (e.g.
     * `/tmp/newdir` or `res://somedir/newdir`). Returns one of the `Error` code constants
     * (`GodotError.OK` on success). Note: The new directory must be within the same scope, e.g. when
     * you had opened a directory inside `res://`, you can't change it to `user://` directory. If you
     * need to open a directory in another access scope, use `open` to create a new instance instead.
     *
     * Generated from Godot docs: DirAccess.change_dir
     */
    fun changeDir(toDir: String): GodotError {
        checkOpen()
        val ret = GodotError(ObjectCalls.ptrcallWithStringArgRetLong(Binds.changeDirBind, segment, toDir))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the absolute path to the currently opened directory (e.g. `res://folder` or
     * `C:\tmp\folder`).
     *
     * Generated from Godot docs: DirAccess.get_current_dir
     */
    fun getCurrentDir(includeDrive: Boolean = true): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithBoolArgRetString(Binds.getCurrentDirBind, segment, includeDrive)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Creates a directory. The argument can be relative to the current directory, or an absolute path.
     * The target directory should be placed in an already existing directory (to create the full path
     * recursively, see `make_dir_recursive`). Returns one of the `Error` code constants
     * (`GodotError.OK` on success).
     *
     * Generated from Godot docs: DirAccess.make_dir
     */
    fun makeDir(path: String): GodotError {
        checkOpen()
        val ret = GodotError(ObjectCalls.ptrcallWithStringArgRetLong(Binds.makeDirBind, segment, path))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Creates a target directory and all necessary intermediate directories in its path, by calling
     * `make_dir` recursively. The argument can be relative to the current directory, or an absolute
     * path. Returns one of the `Error` code constants (`GodotError.OK` on success).
     *
     * Generated from Godot docs: DirAccess.make_dir_recursive
     */
    fun makeDirRecursive(path: String): GodotError {
        checkOpen()
        val ret = GodotError(ObjectCalls.ptrcallWithStringArgRetLong(Binds.makeDirRecursiveBind, segment, path))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns whether the target file exists. The argument can be relative to the current directory,
     * or an absolute path. For a static equivalent, use `FileAccess.file_exists`. Note: Many resources
     * types are imported (e.g. textures or sound files), and their source asset will not be included
     * in the exported game, as only the imported version is used. See `ResourceLoader.exists` for an
     * alternative approach that takes resource remapping into account.
     *
     * Generated from Godot docs: DirAccess.file_exists
     */
    fun fileExists(path: String): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithStringArgRetBool(Binds.fileExistsBind, segment, path)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns whether the target directory exists. The argument can be relative to the current
     * directory, or an absolute path. Note: The returned `bool` in the editor and after exporting when
     * used on a path in the `res://` directory may be different. Some files are converted to
     * engine-specific formats when exported, potentially changing the directory structure.
     *
     * Generated from Godot docs: DirAccess.dir_exists
     */
    fun dirExists(path: String): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithStringArgRetBool(Binds.dirExistsBind, segment, path)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the available space on the current directory's disk, in bytes. Returns `0` if the
     * platform-specific method to query the available space fails.
     *
     * Generated from Godot docs: DirAccess.get_space_left
     */
    fun getSpaceLeft(): Long {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetLong(Binds.getSpaceLeftBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Copies the `from` file to the `to` destination. Both arguments should be paths to files, either
     * relative or absolute. If the destination file exists and is not access-protected, it will be
     * overwritten. If `chmod_flags` is different than `-1`, the Unix permissions for the destination
     * path will be set to the provided value, if available on the current operating system. Returns
     * one of the `Error` code constants (`GodotError.OK` on success).
     *
     * Generated from Godot docs: DirAccess.copy
     */
    fun copy(from: String, to: String, chmodFlags: Int = -1): GodotError {
        checkOpen()
        val ret = GodotError(ObjectCalls.ptrcallWithTwoStringAndIntArgsRetLong(Binds.copyBind, segment, from, to, chmodFlags))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Renames (move) the `from` file or directory to the `to` destination. Both arguments should be
     * paths to files or directories, either relative or absolute. If the destination file or directory
     * exists and is not access-protected, it will be overwritten. Returns one of the `Error` code
     * constants (`GodotError.OK` on success).
     *
     * Generated from Godot docs: DirAccess.rename
     */
    fun rename(from: String, to: String): GodotError {
        checkOpen()
        val ret = GodotError(ObjectCalls.ptrcallWithTwoStringArgsRetLong(Binds.renameBind, segment, from, to))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Permanently deletes the target file or an empty directory. The argument can be relative to the
     * current directory, or an absolute path. If the target directory is not empty, the operation will
     * fail. If you don't want to delete the file/directory permanently, use `OS.move_to_trash`
     * instead. Returns one of the `Error` code constants (`GodotError.OK` on success).
     *
     * Generated from Godot docs: DirAccess.remove
     */
    fun remove(path: String): GodotError {
        checkOpen()
        val ret = GodotError(ObjectCalls.ptrcallWithStringArgRetLong(Binds.removeBind, segment, path))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns `true` if the file or directory is a symbolic link, directory junction, or other reparse
     * point. Note: This method is implemented on macOS, Linux, and Windows.
     *
     * Generated from Godot docs: DirAccess.is_link
     */
    fun isLink(path: String): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithStringArgRetBool(Binds.isLinkBind, segment, path)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns target of the symbolic link. Note: This method is implemented on macOS, Linux, and
     * Windows.
     *
     * Generated from Godot docs: DirAccess.read_link
     */
    fun readLink(path: String): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithStringArgRetString(Binds.readLinkBind, segment, path)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Creates symbolic link between files or folders. Note: On Windows, this method works only if the
     * application is running with elevated privileges or Developer Mode is enabled. Note: This method
     * is implemented on macOS, Linux, and Windows.
     *
     * Generated from Godot docs: DirAccess.create_link
     */
    fun createLink(source: String, target: String): GodotError {
        checkOpen()
        val ret = GodotError(ObjectCalls.ptrcallWithTwoStringArgsRetLong(Binds.createLinkBind, segment, source, target))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns `true` if the directory is a macOS bundle. Note: This method is implemented on macOS.
     *
     * Generated from Godot docs: DirAccess.is_bundle
     */
    fun isBundle(path: String): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithStringArgRetBool(Binds.isBundleBind, segment, path)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * If `true`, `.` and `..` are included when navigating the directory. Affects `list_dir_begin` and
     * `get_directories`.
     *
     * Generated from Godot docs: DirAccess.set_include_navigational
     */
    fun setIncludeNavigational(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setIncludeNavigationalBind, segment, enable)
        ObjectCalls.keepReachable(this)
    }

    /**
     * If `true`, `.` and `..` are included when navigating the directory. Affects `list_dir_begin` and
     * `get_directories`.
     *
     * Generated from Godot docs: DirAccess.get_include_navigational
     */
    fun getIncludeNavigational(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.getIncludeNavigationalBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * If `true`, hidden files are included when navigating the directory. Affects `list_dir_begin`,
     * `get_directories` and `get_files`.
     *
     * Generated from Godot docs: DirAccess.set_include_hidden
     */
    fun setIncludeHidden(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setIncludeHiddenBind, segment, enable)
        ObjectCalls.keepReachable(this)
    }

    /**
     * If `true`, hidden files are included when navigating the directory. Affects `list_dir_begin`,
     * `get_directories` and `get_files`.
     *
     * Generated from Godot docs: DirAccess.get_include_hidden
     */
    fun getIncludeHidden(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.getIncludeHiddenBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns file system type name of the current directory's disk. Returned values are uppercase
     * strings like `NTFS`, `FAT32`, `EXFAT`, `APFS`, `EXT4`, `BTRFS`, and so on. Note: This method is
     * implemented on macOS, Linux, Windows and for PCK virtual file system.
     *
     * Generated from Godot docs: DirAccess.get_filesystem_type
     */
    fun getFilesystemType(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getFilesystemTypeBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns `true` if the file system or directory use case sensitive file names. Note: This method
     * is implemented on macOS, Linux (for EXT4 and F2FS filesystems only) and Windows. On other
     * platforms, it always returns `true`.
     *
     * Generated from Godot docs: DirAccess.is_case_sensitive
     */
    fun isCaseSensitive(path: String): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithStringArgRetBool(Binds.isCaseSensitiveBind, segment, path)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns `true` if paths `path_a` and `path_b` resolve to the same file system object. Returns
     * `false` otherwise, even if the files are bit-for-bit identical (e.g., identical copies of the
     * file that are not symbolic links).
     *
     * Generated from Godot docs: DirAccess.is_equivalent
     */
    fun isEquivalent(pathA: String, pathB: String): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithTwoStringArgsRetBool(Binds.isEquivalentBind, segment, pathA, pathB)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        /**
         * Creates a new `DirAccess` object and opens an existing directory of the filesystem. The `path`
         * argument can be within the project tree (`res://folder`), the user directory (`user://folder`)
         * or an absolute path of the user filesystem (e.g. `/tmp/folder` or `C:\tmp\folder`). Returns
         * `null` if opening the directory failed. You can use `get_open_error` to check the error that
         * occurred.
         *
         * Generated from Godot docs: DirAccess.open
         */
        fun open(path: String): DirAccess? {
            return DirAccess.wrapOwned(ObjectCalls.ptrcallWithStringArgRetObject(Binds.openBind, NULL_SEGMENT, path))
        }

        /**
         * Returns the result of the last `open` call in the current thread.
         *
         * Generated from Godot docs: DirAccess.get_open_error
         */
        fun getOpenError(): GodotError {
            return GodotError(ObjectCalls.ptrcallNoArgsRetLong(Binds.getOpenErrorBind, NULL_SEGMENT))
        }

        /**
         * Creates a temporary directory. This directory will be freed when the returned `DirAccess` is
         * freed. If `prefix` is not empty, it will be prefixed to the directory name, separated by a `-`.
         * If `keep` is `true`, the directory is not deleted when the returned `DirAccess` is freed.
         * Returns `null` if opening the directory failed. You can use `get_open_error` to check the error
         * that occurred.
         *
         * Generated from Godot docs: DirAccess.create_temp
         */
        fun createTemp(prefix: String = "", keep: Boolean = false): DirAccess? {
            return DirAccess.wrapOwned(ObjectCalls.ptrcallWithStringAndBoolArgRetObject(Binds.createTempBind, NULL_SEGMENT, prefix, keep))
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
        fun getFilesAt(path: String): List<String> {
            return ObjectCalls.ptrcallWithStringArgRetPackedStringList(Binds.getFilesAtBind, NULL_SEGMENT, path)
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
        fun getDirectoriesAt(path: String): List<String> {
            return ObjectCalls.ptrcallWithStringArgRetPackedStringList(Binds.getDirectoriesAtBind, NULL_SEGMENT, path)
        }

        /**
         * On Windows, returns the number of drives (partitions) mounted on the current filesystem. On
         * macOS and Android, returns the number of mounted volumes. On Linux, returns the number of
         * mounted volumes and GTK 3 bookmarks. On other platforms, the method returns 0.
         *
         * Generated from Godot docs: DirAccess.get_drive_count
         */
        fun getDriveCount(): Int {
            return ObjectCalls.ptrcallNoArgsRetInt(Binds.getDriveCountBind, NULL_SEGMENT)
        }

        /**
         * On Windows, returns the name of the drive (partition) passed as an argument (e.g. `C:`). On
         * macOS, returns the path to the mounted volume passed as an argument. On Linux, returns the path
         * to the mounted volume or GTK 3 bookmark passed as an argument. On Android (API level 30+),
         * returns the path to the mounted volume as an argument. On other platforms, or if the requested
         * drive does not exist, returns an empty String.
         *
         * Generated from Godot docs: DirAccess.get_drive_name
         */
        fun getDriveName(idx: Int): String {
            return ObjectCalls.ptrcallWithIntArgRetString(Binds.getDriveNameBind, NULL_SEGMENT, idx)
        }

        /**
         * On Windows, returns the label of the drive (partition) passed as an argument. On other
         * platforms, or if the requested drive does not exist, returns an empty String.
         *
         * Generated from Godot docs: DirAccess.get_drive_label
         */
        fun getDriveLabel(idx: Int): String {
            return ObjectCalls.ptrcallWithIntArgRetString(Binds.getDriveLabelBind, NULL_SEGMENT, idx)
        }

        /**
         * Static version of `make_dir`. Supports only absolute paths.
         *
         * Generated from Godot docs: DirAccess.make_dir_absolute
         */
        fun makeDirAbsolute(path: String): GodotError {
            return GodotError(ObjectCalls.ptrcallWithStringArgRetLong(Binds.makeDirAbsoluteBind, NULL_SEGMENT, path))
        }

        /**
         * Static version of `make_dir_recursive`. Supports only absolute paths.
         *
         * Generated from Godot docs: DirAccess.make_dir_recursive_absolute
         */
        fun makeDirRecursiveAbsolute(path: String): GodotError {
            return GodotError(ObjectCalls.ptrcallWithStringArgRetLong(Binds.makeDirRecursiveAbsoluteBind, NULL_SEGMENT, path))
        }

        /**
         * Static version of `dir_exists`. Supports only absolute paths. Note: The returned `bool` in the
         * editor and after exporting when used on a path in the `res://` directory may be different. Some
         * files are converted to engine-specific formats when exported, potentially changing the directory
         * structure.
         *
         * Generated from Godot docs: DirAccess.dir_exists_absolute
         */
        fun dirExistsAbsolute(path: String): Boolean {
            return ObjectCalls.ptrcallWithStringArgRetBool(Binds.dirExistsAbsoluteBind, NULL_SEGMENT, path)
        }

        /**
         * Static version of `copy`. Supports only absolute paths.
         *
         * Generated from Godot docs: DirAccess.copy_absolute
         */
        fun copyAbsolute(from: String, to: String, chmodFlags: Int = -1): GodotError {
            return GodotError(ObjectCalls.ptrcallWithTwoStringAndIntArgsRetLong(Binds.copyAbsoluteBind, NULL_SEGMENT, from, to, chmodFlags))
        }

        /**
         * Static version of `rename`. Supports only absolute paths.
         *
         * Generated from Godot docs: DirAccess.rename_absolute
         */
        fun renameAbsolute(from: String, to: String): GodotError {
            return GodotError(ObjectCalls.ptrcallWithTwoStringArgsRetLong(Binds.renameAbsoluteBind, NULL_SEGMENT, from, to))
        }

        /**
         * Static version of `remove`. Supports only absolute paths.
         *
         * Generated from Godot docs: DirAccess.remove_absolute
         */
        fun removeAbsolute(path: String): GodotError {
            return GodotError(ObjectCalls.ptrcallWithStringArgRetLong(Binds.removeAbsoluteBind, NULL_SEGMENT, path))
        }

        @JvmStatic
        fun fromHandle(handle: GodotHandle): DirAccess? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): DirAccess? =
            if (handle.address() == 0L) null else RefCounted.owned(DirAccess(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): DirAccess? =
            if (handle.address() == 0L) null else DirAccess(GodotHandle(handle))
    }

    private object Binds {
        private const val OPEN_HASH = 1923528528L
        @JvmField
        val openBind =
            ObjectCalls.getMethodBind("DirAccess", "open", OPEN_HASH)

        private const val GET_OPEN_ERROR_HASH = 166280745L
        @JvmField
        val getOpenErrorBind =
            ObjectCalls.getMethodBind("DirAccess", "get_open_error", GET_OPEN_ERROR_HASH)

        private const val CREATE_TEMP_HASH = 812913566L
        @JvmField
        val createTempBind =
            ObjectCalls.getMethodBind("DirAccess", "create_temp", CREATE_TEMP_HASH)

        private const val LIST_DIR_BEGIN_HASH = 166280745L
        @JvmField
        val listDirBeginBind =
            ObjectCalls.getMethodBind("DirAccess", "list_dir_begin", LIST_DIR_BEGIN_HASH)

        private const val GET_NEXT_HASH = 2841200299L
        @JvmField
        val getNextBind =
            ObjectCalls.getMethodBind("DirAccess", "get_next", GET_NEXT_HASH)

        private const val CURRENT_IS_DIR_HASH = 36873697L
        @JvmField
        val currentIsDirBind =
            ObjectCalls.getMethodBind("DirAccess", "current_is_dir", CURRENT_IS_DIR_HASH)

        private const val LIST_DIR_END_HASH = 3218959716L
        @JvmField
        val listDirEndBind =
            ObjectCalls.getMethodBind("DirAccess", "list_dir_end", LIST_DIR_END_HASH)

        private const val GET_FILES_HASH = 2981934095L
        @JvmField
        val getFilesBind =
            ObjectCalls.getMethodBind("DirAccess", "get_files", GET_FILES_HASH)

        private const val GET_FILES_AT_HASH = 3538744774L
        @JvmField
        val getFilesAtBind =
            ObjectCalls.getMethodBind("DirAccess", "get_files_at", GET_FILES_AT_HASH)

        private const val GET_DIRECTORIES_HASH = 2981934095L
        @JvmField
        val getDirectoriesBind =
            ObjectCalls.getMethodBind("DirAccess", "get_directories", GET_DIRECTORIES_HASH)

        private const val GET_DIRECTORIES_AT_HASH = 3538744774L
        @JvmField
        val getDirectoriesAtBind =
            ObjectCalls.getMethodBind("DirAccess", "get_directories_at", GET_DIRECTORIES_AT_HASH)

        private const val GET_DRIVE_COUNT_HASH = 2455072627L
        @JvmField
        val getDriveCountBind =
            ObjectCalls.getMethodBind("DirAccess", "get_drive_count", GET_DRIVE_COUNT_HASH)

        private const val GET_DRIVE_NAME_HASH = 990163283L
        @JvmField
        val getDriveNameBind =
            ObjectCalls.getMethodBind("DirAccess", "get_drive_name", GET_DRIVE_NAME_HASH)

        private const val GET_DRIVE_LABEL_HASH = 990163283L
        @JvmField
        val getDriveLabelBind =
            ObjectCalls.getMethodBind("DirAccess", "get_drive_label", GET_DRIVE_LABEL_HASH)

        private const val GET_CURRENT_DRIVE_HASH = 2455072627L
        @JvmField
        val getCurrentDriveBind =
            ObjectCalls.getMethodBind("DirAccess", "get_current_drive", GET_CURRENT_DRIVE_HASH)

        private const val CHANGE_DIR_HASH = 166001499L
        @JvmField
        val changeDirBind =
            ObjectCalls.getMethodBind("DirAccess", "change_dir", CHANGE_DIR_HASH)

        private const val GET_CURRENT_DIR_HASH = 1287308131L
        @JvmField
        val getCurrentDirBind =
            ObjectCalls.getMethodBind("DirAccess", "get_current_dir", GET_CURRENT_DIR_HASH)

        private const val MAKE_DIR_HASH = 166001499L
        @JvmField
        val makeDirBind =
            ObjectCalls.getMethodBind("DirAccess", "make_dir", MAKE_DIR_HASH)

        private const val MAKE_DIR_ABSOLUTE_HASH = 166001499L
        @JvmField
        val makeDirAbsoluteBind =
            ObjectCalls.getMethodBind("DirAccess", "make_dir_absolute", MAKE_DIR_ABSOLUTE_HASH)

        private const val MAKE_DIR_RECURSIVE_HASH = 166001499L
        @JvmField
        val makeDirRecursiveBind =
            ObjectCalls.getMethodBind("DirAccess", "make_dir_recursive", MAKE_DIR_RECURSIVE_HASH)

        private const val MAKE_DIR_RECURSIVE_ABSOLUTE_HASH = 166001499L
        @JvmField
        val makeDirRecursiveAbsoluteBind =
            ObjectCalls.getMethodBind("DirAccess", "make_dir_recursive_absolute", MAKE_DIR_RECURSIVE_ABSOLUTE_HASH)

        private const val FILE_EXISTS_HASH = 2323990056L
        @JvmField
        val fileExistsBind =
            ObjectCalls.getMethodBind("DirAccess", "file_exists", FILE_EXISTS_HASH)

        private const val DIR_EXISTS_HASH = 2323990056L
        @JvmField
        val dirExistsBind =
            ObjectCalls.getMethodBind("DirAccess", "dir_exists", DIR_EXISTS_HASH)

        private const val DIR_EXISTS_ABSOLUTE_HASH = 2323990056L
        @JvmField
        val dirExistsAbsoluteBind =
            ObjectCalls.getMethodBind("DirAccess", "dir_exists_absolute", DIR_EXISTS_ABSOLUTE_HASH)

        private const val GET_SPACE_LEFT_HASH = 2455072627L
        @JvmField
        val getSpaceLeftBind =
            ObjectCalls.getMethodBind("DirAccess", "get_space_left", GET_SPACE_LEFT_HASH)

        private const val COPY_HASH = 1063198817L
        @JvmField
        val copyBind =
            ObjectCalls.getMethodBind("DirAccess", "copy", COPY_HASH)

        private const val COPY_ABSOLUTE_HASH = 1063198817L
        @JvmField
        val copyAbsoluteBind =
            ObjectCalls.getMethodBind("DirAccess", "copy_absolute", COPY_ABSOLUTE_HASH)

        private const val RENAME_HASH = 852856452L
        @JvmField
        val renameBind =
            ObjectCalls.getMethodBind("DirAccess", "rename", RENAME_HASH)

        private const val RENAME_ABSOLUTE_HASH = 852856452L
        @JvmField
        val renameAbsoluteBind =
            ObjectCalls.getMethodBind("DirAccess", "rename_absolute", RENAME_ABSOLUTE_HASH)

        private const val REMOVE_HASH = 166001499L
        @JvmField
        val removeBind =
            ObjectCalls.getMethodBind("DirAccess", "remove", REMOVE_HASH)

        private const val REMOVE_ABSOLUTE_HASH = 166001499L
        @JvmField
        val removeAbsoluteBind =
            ObjectCalls.getMethodBind("DirAccess", "remove_absolute", REMOVE_ABSOLUTE_HASH)

        private const val IS_LINK_HASH = 2323990056L
        @JvmField
        val isLinkBind =
            ObjectCalls.getMethodBind("DirAccess", "is_link", IS_LINK_HASH)

        private const val READ_LINK_HASH = 1703090593L
        @JvmField
        val readLinkBind =
            ObjectCalls.getMethodBind("DirAccess", "read_link", READ_LINK_HASH)

        private const val CREATE_LINK_HASH = 852856452L
        @JvmField
        val createLinkBind =
            ObjectCalls.getMethodBind("DirAccess", "create_link", CREATE_LINK_HASH)

        private const val IS_BUNDLE_HASH = 3927539163L
        @JvmField
        val isBundleBind =
            ObjectCalls.getMethodBind("DirAccess", "is_bundle", IS_BUNDLE_HASH)

        private const val SET_INCLUDE_NAVIGATIONAL_HASH = 2586408642L
        @JvmField
        val setIncludeNavigationalBind =
            ObjectCalls.getMethodBind("DirAccess", "set_include_navigational", SET_INCLUDE_NAVIGATIONAL_HASH)

        private const val GET_INCLUDE_NAVIGATIONAL_HASH = 36873697L
        @JvmField
        val getIncludeNavigationalBind =
            ObjectCalls.getMethodBind("DirAccess", "get_include_navigational", GET_INCLUDE_NAVIGATIONAL_HASH)

        private const val SET_INCLUDE_HIDDEN_HASH = 2586408642L
        @JvmField
        val setIncludeHiddenBind =
            ObjectCalls.getMethodBind("DirAccess", "set_include_hidden", SET_INCLUDE_HIDDEN_HASH)

        private const val GET_INCLUDE_HIDDEN_HASH = 36873697L
        @JvmField
        val getIncludeHiddenBind =
            ObjectCalls.getMethodBind("DirAccess", "get_include_hidden", GET_INCLUDE_HIDDEN_HASH)

        private const val GET_FILESYSTEM_TYPE_HASH = 201670096L
        @JvmField
        val getFilesystemTypeBind =
            ObjectCalls.getMethodBind("DirAccess", "get_filesystem_type", GET_FILESYSTEM_TYPE_HASH)

        private const val IS_CASE_SENSITIVE_HASH = 3927539163L
        @JvmField
        val isCaseSensitiveBind =
            ObjectCalls.getMethodBind("DirAccess", "is_case_sensitive", IS_CASE_SENSITIVE_HASH)

        private const val IS_EQUIVALENT_HASH = 820780508L
        @JvmField
        val isEquivalentBind =
            ObjectCalls.getMethodBind("DirAccess", "is_equivalent", IS_EQUIVALENT_HASH)
    }
}
