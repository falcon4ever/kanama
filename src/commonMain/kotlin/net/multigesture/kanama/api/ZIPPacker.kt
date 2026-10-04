package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: ZIPPacker
 */
class ZIPPacker(handle: GodotHandle) : RefCounted(handle) {
    var compressionLevel: Int
        @JvmName("compressionLevelProperty")
        get() = getCompressionLevel()
        @JvmName("setCompressionLevelProperty")
        set(value) = setCompressionLevel(value)

    fun open(path: String, append: ZIPPacker.ZipAppend = ZIPPacker.ZipAppend.CREATE): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithStringAndLongArgRetLong(openBind, segment, path, append.value))
    }

    fun setCompressionLevel(compressionLevel: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setCompressionLevelBind, segment, compressionLevel)
    }

    fun getCompressionLevel(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getCompressionLevelBind, segment)
    }

    fun addDirectory(path: String, permissions: FileAccess.UnixPermissionFlags = FileAccess.UnixPermissionFlags(493L), modifiedTime: Long = 0L): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithStringTwoLongArgsRetLong(addDirectoryBind, segment, path, permissions.value, modifiedTime))
    }

    fun startFile(path: String, permissions: FileAccess.UnixPermissionFlags = FileAccess.UnixPermissionFlags(420L), modifiedTime: Long = 0L): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithStringTwoLongArgsRetLong(startFileBind, segment, path, permissions.value, modifiedTime))
    }

    fun writeFile(data: ByteArray): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithByteArrayArgRetLong(writeFileBind, segment, data))
    }

    fun closeFile(): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallNoArgsRetLong(closeFileBind, segment))
    }

    fun closeArchive(): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallNoArgsRetLong(closeArchiveBind, segment))
    }

    @JvmInline
    value class ZipAppend(override val value: Long) : GodotEnumValue {
        companion object {
            val CREATE: ZipAppend get() = ZipAppend(0L)
            val CREATEAFTER: ZipAppend get() = ZipAppend(1L)
            val ADDINZIP: ZipAppend get() = ZipAppend(2L)
        }
    }

    @JvmInline
    value class CompressionLevel(override val value: Long) : GodotEnumValue {
        companion object {
            val DEFAULT: CompressionLevel get() = CompressionLevel(-1L)
            val NONE: CompressionLevel get() = CompressionLevel(0L)
            val FAST: CompressionLevel get() = CompressionLevel(1L)
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
