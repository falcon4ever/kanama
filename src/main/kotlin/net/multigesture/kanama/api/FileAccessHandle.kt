package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment

/**
 * Instance handle returned by FileAccess factory methods.
 */
class FileAccessHandle internal constructor(handle: GodotHandle) : RefCounted(handle) {
    private var fileClosed = false

    fun getPath(): String {
        checkOpen()
        return FileAccess.getPathHandle(segment)
    }

    fun getPathAbsolute(): String {
        checkOpen()
        return FileAccess.getPathAbsoluteHandle(segment)
    }

    fun isOpen(): Boolean {
        checkOpen()
        return FileAccess.isOpenHandle(segment)
    }

    fun getPosition(): Long {
        checkOpen()
        return FileAccess.getPositionHandle(segment)
    }

    fun getLength(): Long {
        checkOpen()
        return FileAccess.getLengthHandle(segment)
    }

    fun eofReached(): Boolean {
        checkOpen()
        return FileAccess.eofReachedHandle(segment)
    }

    fun getLine(): String {
        checkOpen()
        return FileAccess.getLineHandle(segment)
    }

    fun getAsText(): String {
        checkOpen()
        return FileAccess.getAsTextHandle(segment)
    }

    fun getError(): Long {
        checkOpen()
        return FileAccess.getErrorHandle(segment)
    }

    fun storeString(text: String): Boolean {
        checkOpen()
        return FileAccess.storeStringHandle(segment, text)
    }

    fun storeLine(line: String): Boolean {
        checkOpen()
        return FileAccess.storeLineHandle(segment, line)
    }

    fun getVar(allowObjects: Boolean = false): Any? {
        checkOpen()
        return FileAccess.getVarHandle(segment, allowObjects)
    }

    fun storeVar(value: Any?, fullObjects: Boolean = false): Boolean {
        checkOpen()
        return FileAccess.storeVarHandle(segment, value, fullObjects)
    }

    fun flush() {
        checkOpen()
        FileAccess.flushHandle(segment)
    }

    override fun close() {
        if (!fileClosed) {
            fileClosed = true
            if (FileAccess.isOpenHandle(segment)) {
                FileAccess.closeHandle(segment)
            }
        }
        super.close()
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): FileAccessHandle? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): FileAccessHandle? =
            if (handle.address() == 0L) null else FileAccessHandle(GodotHandle(handle))
    }
}
