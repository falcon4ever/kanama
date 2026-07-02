package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Creates packages that can be loaded into a running project.
 *
 * Generated from Godot docs: PCKPacker
 */
class PCKPacker(handle: MemorySegment) : RefCounted(handle) {
    fun pckStart(pckPath: String, alignment: Int = 32, key: String = "0000000000000000000000000000000000000000000000000000000000000000", encryptDirectory: Boolean = false): Long {
        return ObjectCalls.ptrcallWithStringIntStringBoolArgsRetLong(pckStartBind, handle, pckPath, alignment, key, encryptDirectory)
    }

    fun addFile(targetPath: String, sourcePath: String, encrypt: Boolean = false): Long {
        return ObjectCalls.ptrcallWithTwoStringBoolArgsRetLong(addFileBind, handle, targetPath, sourcePath, encrypt)
    }

    fun addFileFromBuffer(targetPath: String, data: ByteArray, encrypt: Boolean = false): Long {
        return ObjectCalls.ptrcallWithStringByteArrayAndBoolArgRetLong(addFileFromBufferBind, handle, targetPath, data, encrypt)
    }

    fun addFileRemoval(targetPath: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(addFileRemovalBind, handle, targetPath)
    }

    /**
     * Writes the file directory and closes the PCK. If `verbose` is `true`, a list of files added will
     * be printed to the console for easier debugging. Note: `PCKPacker` will automatically flush when
     * it's freed, which happens when it goes out of scope or when it gets assigned with `null`. In C#
     * the reference must be disposed after use, either with the `using` statement or by calling the
     * `Dispose` method directly.
     *
     * Generated from Godot docs: PCKPacker.flush
     */
    fun flush(verbose: Boolean = false): Long {
        return ObjectCalls.ptrcallWithBoolArgRetLong(flushBind, handle, verbose)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PCKPacker? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PCKPacker? =
            if (handle.address() == 0L) null else PCKPacker(handle)

        private const val PCK_START_HASH = 508410629L
        private val pckStartBind by lazy {
            ObjectCalls.getMethodBind("PCKPacker", "pck_start", PCK_START_HASH)
        }

        private const val ADD_FILE_HASH = 2215643711L
        private val addFileBind by lazy {
            ObjectCalls.getMethodBind("PCKPacker", "add_file", ADD_FILE_HASH)
        }

        private const val ADD_FILE_FROM_BUFFER_HASH = 1131482346L
        private val addFileFromBufferBind by lazy {
            ObjectCalls.getMethodBind("PCKPacker", "add_file_from_buffer", ADD_FILE_FROM_BUFFER_HASH)
        }

        private const val ADD_FILE_REMOVAL_HASH = 166001499L
        private val addFileRemovalBind by lazy {
            ObjectCalls.getMethodBind("PCKPacker", "add_file_removal", ADD_FILE_REMOVAL_HASH)
        }

        private const val FLUSH_HASH = 1633102583L
        private val flushBind by lazy {
            ObjectCalls.getMethodBind("PCKPacker", "flush", FLUSH_HASH)
        }
    }
}
