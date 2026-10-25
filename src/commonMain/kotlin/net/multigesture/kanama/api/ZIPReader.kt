package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Allows reading the content of a ZIP file.
 *
 * Generated from Godot docs: ZIPReader
 */
class ZIPReader(handle: GodotHandle) : RefCounted(handle) {
    /**
     * Opens the zip archive at the given `path` and reads its file index.
     *
     * Generated from Godot docs: ZIPReader.open
     */
    fun open(path: String): GodotError {
        checkOpen()
        val ret = GodotError(ObjectCalls.ptrcallWithStringArgRetLong(Binds.openBind, segment, path))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Closes the underlying resources used by this instance.
     *
     * Generated from Godot docs: ZIPReader.close
     */
    fun closeArchive(): GodotError {
        checkOpen()
        val ret = GodotError(ObjectCalls.ptrcallNoArgsRetLong(Binds.closeArchiveBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the list of names of all files in the loaded archive. Must be called after `open`.
     *
     * Generated from Godot docs: ZIPReader.get_files
     */
    fun getFiles(): List<String> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetPackedStringList(Binds.getFilesBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Loads the whole content of a file in the loaded zip archive into memory and returns it. Must be
     * called after `open`.
     *
     * Generated from Godot docs: ZIPReader.read_file
     */
    fun readFile(path: String, caseSensitive: Boolean = true): ByteArray {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithStringAndBoolArgRetByteArray(Binds.readFileBind, segment, path, caseSensitive)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns `true` if the file exists in the loaded zip archive. Must be called after `open`.
     *
     * Generated from Godot docs: ZIPReader.file_exists
     */
    fun fileExists(path: String, caseSensitive: Boolean = true): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithStringAndBoolArgRetBool(Binds.fileExistsBind, segment, path, caseSensitive)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the compression level of the file in the loaded zip archive. Returns `-1` if the file
     * doesn't exist or any other error occurs. Must be called after `open`.
     *
     * Generated from Godot docs: ZIPReader.get_compression_level
     */
    fun getCompressionLevel(path: String, caseSensitive: Boolean = true): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithStringAndBoolArgRetInt(Binds.getCompressionLevelBind, segment, path, caseSensitive)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ZIPReader? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): ZIPReader? =
            if (handle.address() == 0L) null else RefCounted.owned(ZIPReader(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): ZIPReader? =
            if (handle.address() == 0L) null else ZIPReader(GodotHandle(handle))

        /** A new ZIPReader, GDScript's `ZIPReader.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): ZIPReader =
            RefCounted.owned(ZIPReader(GodotHandle(ObjectCalls.constructObject("ZIPReader"))))
    }

    private object Binds {
        private const val OPEN_HASH = 166001499L
        @JvmField
        val openBind =
            ObjectCalls.getMethodBind("ZIPReader", "open", OPEN_HASH)

        private const val CLOSE_HASH = 166280745L
        @JvmField
        val closeArchiveBind =
            ObjectCalls.getMethodBind("ZIPReader", "close", CLOSE_HASH)

        private const val GET_FILES_HASH = 2981934095L
        @JvmField
        val getFilesBind =
            ObjectCalls.getMethodBind("ZIPReader", "get_files", GET_FILES_HASH)

        private const val READ_FILE_HASH = 740857591L
        @JvmField
        val readFileBind =
            ObjectCalls.getMethodBind("ZIPReader", "read_file", READ_FILE_HASH)

        private const val FILE_EXISTS_HASH = 35364943L
        @JvmField
        val fileExistsBind =
            ObjectCalls.getMethodBind("ZIPReader", "file_exists", FILE_EXISTS_HASH)

        private const val GET_COMPRESSION_LEVEL_HASH = 3694577386L
        @JvmField
        val getCompressionLevelBind =
            ObjectCalls.getMethodBind("ZIPReader", "get_compression_level", GET_COMPRESSION_LEVEL_HASH)
    }
}
