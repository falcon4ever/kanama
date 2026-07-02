package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: ZIPReader
 */
class ZIPReader(handle: MemorySegment) : RefCounted(handle) {
    fun open(path: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(openBind, handle, path)
    }

    fun closeArchive(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(closeArchiveBind, handle)
    }

    fun getFiles(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getFilesBind, handle)
    }

    fun readFile(path: String, caseSensitive: Boolean = true): ByteArray {
        return ObjectCalls.ptrcallWithStringAndBoolArgRetByteArray(readFileBind, handle, path, caseSensitive)
    }

    fun fileExists(path: String, caseSensitive: Boolean = true): Boolean {
        return ObjectCalls.ptrcallWithStringAndBoolArgRetBool(fileExistsBind, handle, path, caseSensitive)
    }

    fun getCompressionLevel(path: String, caseSensitive: Boolean = true): Int {
        return ObjectCalls.ptrcallWithStringAndBoolArgRetInt(getCompressionLevelBind, handle, path, caseSensitive)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ZIPReader? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ZIPReader? =
            if (handle.address() == 0L) null else ZIPReader(handle)

        private const val OPEN_HASH = 166001499L
        private val openBind by lazy {
            ObjectCalls.getMethodBind("ZIPReader", "open", OPEN_HASH)
        }

        private const val CLOSE_HASH = 166280745L
        private val closeArchiveBind by lazy {
            ObjectCalls.getMethodBind("ZIPReader", "close", CLOSE_HASH)
        }

        private const val GET_FILES_HASH = 2981934095L
        private val getFilesBind by lazy {
            ObjectCalls.getMethodBind("ZIPReader", "get_files", GET_FILES_HASH)
        }

        private const val READ_FILE_HASH = 740857591L
        private val readFileBind by lazy {
            ObjectCalls.getMethodBind("ZIPReader", "read_file", READ_FILE_HASH)
        }

        private const val FILE_EXISTS_HASH = 35364943L
        private val fileExistsBind by lazy {
            ObjectCalls.getMethodBind("ZIPReader", "file_exists", FILE_EXISTS_HASH)
        }

        private const val GET_COMPRESSION_LEVEL_HASH = 3694577386L
        private val getCompressionLevelBind by lazy {
            ObjectCalls.getMethodBind("ZIPReader", "get_compression_level", GET_COMPRESSION_LEVEL_HASH)
        }
    }
}
