package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

// KANAMA-IOS-HANDWRITTEN: [glue] FileAccess static facade. The desktop shape is hand-shaped
// (static object + FileAccessHandle), so it can't be adopted from the generator. Only the
// subset shared game code uses is wired: open/READ/WRITE/READ_WRITE, getAsText/storeString/
// close on the handle, and static get_file_as_bytes/get_size. FileAccess methods that take
// no path are STATIC in Godot and must dispatch with a NULL instance (ptrcallStatic*);
// get_file_as_bytes sizes its buffer with get_size so the two-call read-back protocol
// collapses to a single file read.
object FileAccess {
    const val READ = 1L
    const val WRITE = 2L
    const val READ_WRITE = 3L

    fun open(path: String, flags: Long): FileAccessHandle? {
        val handle = ObjectCalls.ptrcallStaticWithStringAndLongArgsRetObject(openBind, path, flags)
        return if (handle.address() == 0L) null else FileAccessHandle(handle)
    }

    fun getSize(path: String): Long =
        ObjectCalls.ptrcallStaticWithStringArgRetLong(getSizeBind, path)

    fun getFileAsBytes(path: String): ByteArray {
        val size = ObjectCalls.ptrcallStaticWithStringArgRetLong(getSizeBind, path)
        return ObjectCalls.ptrcallStaticWithStringArgRetByteArray(getFileAsBytesBind, path, size)
    }

    internal fun getAsTextHandle(handle: MemorySegment): String =
        ObjectCalls.ptrcallNoArgsRetString(getAsTextBind, handle)

    internal fun storeStringHandle(handle: MemorySegment, text: String): Boolean =
        ObjectCalls.ptrcallWithStringArgRetBool(storeStringBind, handle, text)

    internal fun isOpenHandle(handle: MemorySegment): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isOpenBind, handle)

    internal fun closeHandle(handle: MemorySegment) {
        ObjectCalls.ptrcallNoArgs(closeBind, handle)
    }

    private val openBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "open", 1247358404L)
    }
    private val getSizeBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "get_size", 1597066294L)
    }
    private val getFileAsBytesBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "get_file_as_bytes", 659035735L)
    }
    private val getAsTextBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "get_as_text", 201670096L)
    }
    private val storeStringBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "store_string", 2323990056L)
    }
    private val isOpenBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "is_open", 36873697L)
    }
    private val closeBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "close", 3218959716L)
    }
}

/**
 * Instance handle returned by FileAccess.open, mirroring the desktop FileAccessHandle
 * subset shared game code uses.
 */
class FileAccessHandle internal constructor(handle: MemorySegment) : RefCounted(handle) {
    private var fileClosed = false

    fun getAsText(): String = FileAccess.getAsTextHandle(handle)

    fun storeString(text: String): Boolean = FileAccess.storeStringHandle(handle, text)

    override fun close() {
        if (!fileClosed) {
            fileClosed = true
            if (FileAccess.isOpenHandle(handle)) {
                FileAccess.closeHandle(handle)
            }
        }
        super.close()
    }
}
