package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Allows the creation of ZIP files.
 *
 * Generated from Godot docs: ZIPPacker
 */
class ZIPPacker(handle: GodotHandle) : RefCounted(handle) {
    var compressionLevel: Int
        @JvmName("compressionLevelProperty")
        get() = getCompressionLevel()
        @JvmName("setCompressionLevelProperty")
        set(value) = setCompressionLevel(value)

    /**
     * Opens a zip file for writing at the given path using the specified write mode. This must be
     * called before everything else.
     *
     * Generated from Godot docs: ZIPPacker.open
     */
    fun open(path: String, append: ZIPPacker.ZipAppend = ZIPPacker.ZipAppend.CREATE): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithStringAndLongArgRetLong(Binds.openBind, segment, path, append.value))
    }

    /**
     * The compression level used when `start_file` is called. Use `ZIPPacker.CompressionLevel` as a
     * reference.
     *
     * Generated from Godot docs: ZIPPacker.set_compression_level
     */
    fun setCompressionLevel(compressionLevel: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setCompressionLevelBind, segment, compressionLevel)
    }

    /**
     * The compression level used when `start_file` is called. Use `ZIPPacker.CompressionLevel` as a
     * reference.
     *
     * Generated from Godot docs: ZIPPacker.get_compression_level
     */
    fun getCompressionLevel(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getCompressionLevelBind, segment)
    }

    /**
     * Adds directory to the archive. If `modified_time` is set to `0`, current system time is used.
     * Note: Directories are automatically created when `start_file` is called, use this function
     * before adding files to create directories with custom permissions and modification time.
     *
     * Generated from Godot docs: ZIPPacker.add_directory
     */
    fun addDirectory(path: String, permissions: FileAccess.UnixPermissionFlags = FileAccess.UnixPermissionFlags(493L), modifiedTime: Long = 0L): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithStringTwoLongArgsRetLong(Binds.addDirectoryBind, segment, path, permissions.value, modifiedTime))
    }

    /**
     * Starts writing to a file within the archive. Only one file can be written at the same time. If
     * `modified_time` is set to `0`, current system time is used. Must be called after `open`.
     *
     * Generated from Godot docs: ZIPPacker.start_file
     */
    fun startFile(path: String, permissions: FileAccess.UnixPermissionFlags = FileAccess.UnixPermissionFlags(420L), modifiedTime: Long = 0L): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithStringTwoLongArgsRetLong(Binds.startFileBind, segment, path, permissions.value, modifiedTime))
    }

    /**
     * Write the given `data` to the file. Needs to be called after `start_file`.
     *
     * Generated from Godot docs: ZIPPacker.write_file
     */
    fun writeFile(data: ByteArray): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithByteArrayArgRetLong(Binds.writeFileBind, segment, data))
    }

    /**
     * Stops writing to a file within the archive. It will fail if there is no open file.
     *
     * Generated from Godot docs: ZIPPacker.close_file
     */
    fun closeFile(): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallNoArgsRetLong(Binds.closeFileBind, segment))
    }

    /**
     * Closes the underlying resources used by this instance.
     *
     * Generated from Godot docs: ZIPPacker.close
     */
    fun closeArchive(): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallNoArgsRetLong(Binds.closeArchiveBind, segment))
    }

    /**
     * Godot's `ZIPPacker.ZipAppend` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`ZIPPacker.ZipAppend.<NAME>`).
     *
     * Generated from Godot docs: ZIPPacker.ZipAppend
     */
    @JvmInline
    value class ZipAppend(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Create a new zip archive at the given path.
             *
             * Generated from Godot docs: ZIPPacker.APPEND_CREATE
             */
            val CREATE: ZipAppend get() = ZipAppend(0L)
            /**
             * Append a new zip archive to the end of the already existing file at the given path.
             *
             * Generated from Godot docs: ZIPPacker.APPEND_CREATEAFTER
             */
            val CREATEAFTER: ZipAppend get() = ZipAppend(1L)
            /**
             * Add new files to the existing zip archive at the given path.
             *
             * Generated from Godot docs: ZIPPacker.APPEND_ADDINZIP
             */
            val ADDINZIP: ZipAppend get() = ZipAppend(2L)
        }
    }

    /**
     * Godot's `ZIPPacker.CompressionLevel` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`ZIPPacker.CompressionLevel.<NAME>`).
     *
     * Generated from Godot docs: ZIPPacker.CompressionLevel
     */
    @JvmInline
    value class CompressionLevel(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Start a file with the default Deflate compression level (`6`). This is a good compromise between
             * speed and file size.
             *
             * Generated from Godot docs: ZIPPacker.COMPRESSION_DEFAULT
             */
            val DEFAULT: CompressionLevel get() = CompressionLevel(-1L)
            /**
             * Start a file with no compression. This is also known as the "Store" compression mode and is the
             * fastest method of packing files inside a ZIP archive. Consider using this mode for files that
             * are already compressed (such as JPEG, PNG, MP3, or Ogg Vorbis files).
             *
             * Generated from Godot docs: ZIPPacker.COMPRESSION_NONE
             */
            val NONE: CompressionLevel get() = CompressionLevel(0L)
            /**
             * Start a file with the fastest Deflate compression level (`1`). This is fast to compress, but
             * results in larger file sizes than `CompressionLevel.DEFAULT`. Decompression speed is generally
             * unaffected by the chosen compression level.
             *
             * Generated from Godot docs: ZIPPacker.COMPRESSION_FAST
             */
            val FAST: CompressionLevel get() = CompressionLevel(1L)
            /**
             * Start a file with the best Deflate compression level (`9`). This is slow to compress, but
             * results in smaller file sizes than `CompressionLevel.DEFAULT`. Decompression speed is generally
             * unaffected by the chosen compression level.
             *
             * Generated from Godot docs: ZIPPacker.COMPRESSION_BEST
             */
            val BEST: CompressionLevel get() = CompressionLevel(9L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ZIPPacker? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): ZIPPacker? =
            if (handle.address() == 0L) null else RefCounted.owned(ZIPPacker(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): ZIPPacker? =
            if (handle.address() == 0L) null else ZIPPacker(GodotHandle(handle))
    }

    private object Binds {
        private const val OPEN_HASH = 1936816515L
        @JvmField
        val openBind =
            ObjectCalls.getMethodBind("ZIPPacker", "open", OPEN_HASH)

        private const val SET_COMPRESSION_LEVEL_HASH = 1286410249L
        @JvmField
        val setCompressionLevelBind =
            ObjectCalls.getMethodBind("ZIPPacker", "set_compression_level", SET_COMPRESSION_LEVEL_HASH)

        private const val GET_COMPRESSION_LEVEL_HASH = 3905245786L
        @JvmField
        val getCompressionLevelBind =
            ObjectCalls.getMethodBind("ZIPPacker", "get_compression_level", GET_COMPRESSION_LEVEL_HASH)

        private const val ADD_DIRECTORY_HASH = 934773537L
        @JvmField
        val addDirectoryBind =
            ObjectCalls.getMethodBind("ZIPPacker", "add_directory", ADD_DIRECTORY_HASH)

        private const val START_FILE_HASH = 4260848715L
        @JvmField
        val startFileBind =
            ObjectCalls.getMethodBind("ZIPPacker", "start_file", START_FILE_HASH)

        private const val WRITE_FILE_HASH = 680677267L
        @JvmField
        val writeFileBind =
            ObjectCalls.getMethodBind("ZIPPacker", "write_file", WRITE_FILE_HASH)

        private const val CLOSE_FILE_HASH = 166280745L
        @JvmField
        val closeFileBind =
            ObjectCalls.getMethodBind("ZIPPacker", "close_file", CLOSE_FILE_HASH)

        private const val CLOSE_HASH = 166280745L
        @JvmField
        val closeArchiveBind =
            ObjectCalls.getMethodBind("ZIPPacker", "close", CLOSE_HASH)
    }
}
