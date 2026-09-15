package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: ZIPReader
 */
class ZIPReader(handle: GodotHandle) : RefCounted(handle) {
    fun open(path: String): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetLong(openBind, segment, path)
    }

    fun closeArchive(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(closeArchiveBind, segment)
    }

    fun getFiles(): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getFilesBind, segment)
    }

    fun readFile(path: String, caseSensitive: Boolean = true): ByteArray {
        checkOpen()
        return ObjectCalls.ptrcallWithStringAndBoolArgRetByteArray(readFileBind, segment, path, caseSensitive)
    }

    fun fileExists(path: String, caseSensitive: Boolean = true): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringAndBoolArgRetBool(fileExistsBind, segment, path, caseSensitive)
    }

    fun getCompressionLevel(path: String, caseSensitive: Boolean = true): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithStringAndBoolArgRetInt(getCompressionLevelBind, segment, path, caseSensitive)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ZIPReader? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): ZIPReader? =
            if (handle.address() == 0L) null else ZIPReader(GodotHandle(handle))

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
