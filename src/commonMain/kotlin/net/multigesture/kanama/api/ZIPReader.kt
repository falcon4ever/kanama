package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: ZIPReader
 */
class ZIPReader(handle: GodotHandle) : RefCounted(handle) {
    fun open(path: String): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithStringArgRetLong(Binds.openBind, segment, path))
    }

    fun closeArchive(): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallNoArgsRetLong(Binds.closeArchiveBind, segment))
    }

    fun getFiles(): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(Binds.getFilesBind, segment)
    }

    fun readFile(path: String, caseSensitive: Boolean = true): ByteArray {
        checkOpen()
        return ObjectCalls.ptrcallWithStringAndBoolArgRetByteArray(Binds.readFileBind, segment, path, caseSensitive)
    }

    fun fileExists(path: String, caseSensitive: Boolean = true): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringAndBoolArgRetBool(Binds.fileExistsBind, segment, path, caseSensitive)
    }

    fun getCompressionLevel(path: String, caseSensitive: Boolean = true): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithStringAndBoolArgRetInt(Binds.getCompressionLevelBind, segment, path, caseSensitive)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ZIPReader? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): ZIPReader? =
            if (handle.address() == 0L) null else RefCounted.owned(ZIPReader(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): ZIPReader? =
            if (handle.address() == 0L) null else ZIPReader(GodotHandle(handle))
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
