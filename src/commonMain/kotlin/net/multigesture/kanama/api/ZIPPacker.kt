package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: ZIPPacker
 */
class ZIPPacker(handle: GodotHandle) : RefCounted(handle) {
    var compressionLevel: Int
        @JvmName("compressionLevelProperty")
        get() = getCompressionLevel()
        @JvmName("setCompressionLevelProperty")
        set(value) = setCompressionLevel(value)

    fun open(path: String, append: Long = 0L): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringAndLongArgRetLong(openBind, segment, path, append)
    }

    fun setCompressionLevel(compressionLevel: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setCompressionLevelBind, segment, compressionLevel)
    }

    fun getCompressionLevel(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getCompressionLevelBind, segment)
    }

    fun addDirectory(path: String, permissions: Long = 493L, modifiedTime: Long = 0L): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringTwoLongArgsRetLong(addDirectoryBind, segment, path, permissions, modifiedTime)
    }

    fun startFile(path: String, permissions: Long = 420L, modifiedTime: Long = 0L): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringTwoLongArgsRetLong(startFileBind, segment, path, permissions, modifiedTime)
    }

    fun writeFile(data: ByteArray): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithByteArrayArgRetLong(writeFileBind, segment, data)
    }

    fun closeFile(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(closeFileBind, segment)
    }

    fun closeArchive(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(closeArchiveBind, segment)
    }

    companion object {
        const val APPEND_CREATE: Long = 0L
        const val APPEND_CREATEAFTER: Long = 1L
        const val APPEND_ADDINZIP: Long = 2L
        const val COMPRESSION_DEFAULT: Long = -1L
        const val COMPRESSION_NONE: Long = 0L
        const val COMPRESSION_FAST: Long = 1L
        const val COMPRESSION_BEST: Long = 9L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): ZIPPacker? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): ZIPPacker? =
            if (handle.address() == 0L) null else ZIPPacker(GodotHandle(handle))

        private const val OPEN_HASH = 1936816515L
        private val openBind by lazy {
            ObjectCalls.getMethodBind("ZIPPacker", "open", OPEN_HASH)
        }

        private const val SET_COMPRESSION_LEVEL_HASH = 1286410249L
        private val setCompressionLevelBind by lazy {
            ObjectCalls.getMethodBind("ZIPPacker", "set_compression_level", SET_COMPRESSION_LEVEL_HASH)
        }

        private const val GET_COMPRESSION_LEVEL_HASH = 3905245786L
        private val getCompressionLevelBind by lazy {
            ObjectCalls.getMethodBind("ZIPPacker", "get_compression_level", GET_COMPRESSION_LEVEL_HASH)
        }

        private const val ADD_DIRECTORY_HASH = 934773537L
        private val addDirectoryBind by lazy {
            ObjectCalls.getMethodBind("ZIPPacker", "add_directory", ADD_DIRECTORY_HASH)
        }

        private const val START_FILE_HASH = 4260848715L
        private val startFileBind by lazy {
            ObjectCalls.getMethodBind("ZIPPacker", "start_file", START_FILE_HASH)
        }

        private const val WRITE_FILE_HASH = 680677267L
        private val writeFileBind by lazy {
            ObjectCalls.getMethodBind("ZIPPacker", "write_file", WRITE_FILE_HASH)
        }

        private const val CLOSE_FILE_HASH = 166280745L
        private val closeFileBind by lazy {
            ObjectCalls.getMethodBind("ZIPPacker", "close_file", CLOSE_FILE_HASH)
        }

        private const val CLOSE_HASH = 166280745L
        private val closeArchiveBind by lazy {
            ObjectCalls.getMethodBind("ZIPPacker", "close", CLOSE_HASH)
        }
    }
}
