package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment

/**
 * Instance handle returned by FileAccess factory methods.
 */
class FileAccessHandle internal constructor(handle: MemorySegment) : RefCounted(handle) {
    private var fileClosed = false

    fun getPath(): String {
        checkOpen()
        return FileAccess.getPathHandle(handle)
    }

    fun getPathAbsolute(): String {
        checkOpen()
        return FileAccess.getPathAbsoluteHandle(handle)
    }

    fun isOpen(): Boolean {
        checkOpen()
        return FileAccess.isOpenHandle(handle)
    }

    fun getPosition(): Long {
        checkOpen()
        return FileAccess.getPositionHandle(handle)
    }

    fun getLength(): Long {
        checkOpen()
        return FileAccess.getLengthHandle(handle)
    }

    fun eofReached(): Boolean {
        checkOpen()
        return FileAccess.eofReachedHandle(handle)
    }

    fun getLine(): String {
        checkOpen()
        return FileAccess.getLineHandle(handle)
    }

    fun getAsText(): String {
        checkOpen()
        return FileAccess.getAsTextHandle(handle)
    }

    fun getError(): Long {
        checkOpen()
        return FileAccess.getErrorHandle(handle)
    }

    fun storeString(text: String): Boolean {
        checkOpen()
        return FileAccess.storeStringHandle(handle, text)
    }

    fun storeLine(line: String): Boolean {
        checkOpen()
        return FileAccess.storeLineHandle(handle, line)
    }

    fun getVar(allowObjects: Boolean = false): Any? {
        checkOpen()
        return FileAccess.getVarHandle(handle, allowObjects)
    }

    fun storeVar(value: Any?, fullObjects: Boolean = false): Boolean {
        checkOpen()
        return FileAccess.storeVarHandle(handle, value, fullObjects)
    }

    fun flush() {
        checkOpen()
        FileAccess.flushHandle(handle)
    }

    override fun close() {
        if (!fileClosed) {
            fileClosed = true
            if (FileAccess.isOpenHandle(handle)) {
                FileAccess.closeHandle(handle)
            }
        }
        super.close()
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): FileAccessHandle? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): FileAccessHandle? =
            if (handle.address() == 0L) null else FileAccessHandle(handle)
    }
}
