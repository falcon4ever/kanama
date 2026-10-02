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
actual object FileAccess {
    // ===== BEGIN GENERATED ENUMS: FileAccess (scripts/generate_api_wrapper.py — do not edit) =====
    actual value class ModeFlags
    actual constructor(
        actual val value: Long,
    ) {
        actual companion object {
            actual val READ: ModeFlags get() = ModeFlags(1L)
            actual val WRITE: ModeFlags get() = ModeFlags(2L)
            actual val READ_WRITE: ModeFlags get() = ModeFlags(3L)
            actual val WRITE_READ: ModeFlags get() = ModeFlags(7L)
        }
    }

    actual value class CompressionMode
    actual constructor(
        actual val value: Long,
    ) {
        actual companion object {
            actual val FASTLZ: CompressionMode get() = CompressionMode(0L)
            actual val DEFLATE: CompressionMode get() = CompressionMode(1L)
            actual val ZSTD: CompressionMode get() = CompressionMode(2L)
            actual val GZIP: CompressionMode get() = CompressionMode(3L)
            actual val BROTLI: CompressionMode get() = CompressionMode(4L)
        }
    }

    actual value class UnixPermissionFlags
    actual constructor(
        actual val value: Long,
    ) {
        actual infix fun or(other: UnixPermissionFlags): UnixPermissionFlags = UnixPermissionFlags(value or other.value)

        actual infix fun and(other: UnixPermissionFlags): UnixPermissionFlags = UnixPermissionFlags(value and other.value)

        actual infix fun xor(other: UnixPermissionFlags): UnixPermissionFlags = UnixPermissionFlags(value xor other.value)

        actual fun inv(): UnixPermissionFlags = UnixPermissionFlags(value.inv())

        actual operator fun contains(other: UnixPermissionFlags): Boolean = (value and other.value) == other.value

        actual companion object {
            actual val READ_OWNER: UnixPermissionFlags get() = UnixPermissionFlags(256L)
            actual val WRITE_OWNER: UnixPermissionFlags get() = UnixPermissionFlags(128L)
            actual val EXECUTE_OWNER: UnixPermissionFlags get() = UnixPermissionFlags(64L)
            actual val READ_GROUP: UnixPermissionFlags get() = UnixPermissionFlags(32L)
            actual val WRITE_GROUP: UnixPermissionFlags get() = UnixPermissionFlags(16L)
            actual val EXECUTE_GROUP: UnixPermissionFlags get() = UnixPermissionFlags(8L)
            actual val READ_OTHER: UnixPermissionFlags get() = UnixPermissionFlags(4L)
            actual val WRITE_OTHER: UnixPermissionFlags get() = UnixPermissionFlags(2L)
            actual val EXECUTE_OTHER: UnixPermissionFlags get() = UnixPermissionFlags(1L)
            actual val SET_USER_ID: UnixPermissionFlags get() = UnixPermissionFlags(2048L)
            actual val SET_GROUP_ID: UnixPermissionFlags get() = UnixPermissionFlags(1024L)
            actual val RESTRICTED_DELETE: UnixPermissionFlags get() = UnixPermissionFlags(512L)
        }
    }
    // ===== END GENERATED ENUMS: FileAccess =====


    fun open(path: String, flags: FileAccess.ModeFlags): FileAccessHandle? {
        val segment = ObjectCalls.ptrcallStaticWithStringAndLongArgsRetObject(openBind, path, flags.value)
        return if (segment.address() == 0L) null else FileAccessHandle(GodotHandle(segment))
    }

    fun getSize(path: String): Long =
        ObjectCalls.ptrcallStaticWithStringArgRetLong(getSizeBind, path)

    /**
     * Mirrors the desktop `FileAccess.fileExists`, down to the dispatch shape: Godot's
     * `file_exists` is STATIC, so the instance is the `NULL_SEGMENT` static marker and
     * `ptrcallWithStringArgRetBool` routes it to the static C entry point (task 117 P2' D19).
     * Added by follow-up 5 for the self-test's `FileAccess.get_sha256` probe, which must record a
     * FAILURE rather than a skip when its subject file is missing.
     */
    fun fileExists(path: String): Boolean =
        ObjectCalls.ptrcallWithStringArgRetBool(fileExistsBind, NULL_SEGMENT, path)

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
    private val fileExistsBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "file_exists", 2323990056L)
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
class FileAccessHandle internal constructor(handle: GodotHandle) : RefCounted(handle) {
    private var fileClosed = false

    fun getAsText(): String = FileAccess.getAsTextHandle(segment)

    fun storeString(text: String): Boolean = FileAccess.storeStringHandle(segment, text)

    override fun close() {
        if (!fileClosed) {
            fileClosed = true
            if (FileAccess.isOpenHandle(segment)) {
                FileAccess.closeHandle(segment)
            }
        }
        super.close()
    }
}
