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
    /**
     * Godot's `FileAccess.ModeFlags` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`FileAccess.ModeFlags.<NAME>`).
     *
     * Generated from Godot docs: FileAccess.ModeFlags
     */
    actual value class ModeFlags
    actual constructor(
        actual override val value: Long,
    ) : GodotEnumValue {
        actual companion object {
            /**
             * Opens the file for read operations. The file cursor is positioned at the beginning of the file.
             *
             * Generated from Godot docs: FileAccess.READ
             */
            actual val READ: ModeFlags get() = ModeFlags(1L)
            /**
             * Opens the file for write operations. If the file exists, it is truncated to zero length and its
             * contents are cleared. Otherwise, it is created. Note: When creating a file it must be in an
             * already existing directory. To recursively create directories for a file path, see
             * `DirAccess.make_dir_recursive`.
             *
             * Generated from Godot docs: FileAccess.WRITE
             */
            actual val WRITE: ModeFlags get() = ModeFlags(2L)
            /**
             * Opens the file for read and write operations. Does not truncate the file. The file cursor is
             * positioned at the beginning of the file.
             *
             * Generated from Godot docs: FileAccess.READ_WRITE
             */
            actual val READ_WRITE: ModeFlags get() = ModeFlags(3L)
            /**
             * Opens the file for read and write operations. If the file exists, it is truncated to zero length
             * and its contents are cleared. Otherwise, it is created. The file cursor is positioned at the
             * beginning of the file. Note: When creating a file it must be in an already existing directory.
             * To recursively create directories for a file path, see `DirAccess.make_dir_recursive`.
             *
             * Generated from Godot docs: FileAccess.WRITE_READ
             */
            actual val WRITE_READ: ModeFlags get() = ModeFlags(7L)
        }
    }

    /**
     * Godot's `FileAccess.CompressionMode` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`FileAccess.CompressionMode.<NAME>`).
     *
     * Generated from Godot docs: FileAccess.CompressionMode
     */
    actual value class CompressionMode
    actual constructor(
        actual override val value: Long,
    ) : GodotEnumValue {
        actual companion object {
            /**
             * Uses the FastLZ (https://fastlz.org/) compression method.
             *
             * Generated from Godot docs: FileAccess.COMPRESSION_FASTLZ
             */
            actual val FASTLZ: CompressionMode get() = CompressionMode(0L)
            /**
             * Uses the DEFLATE (https://en.wikipedia.org/wiki/DEFLATE) compression method.
             *
             * Generated from Godot docs: FileAccess.COMPRESSION_DEFLATE
             */
            actual val DEFLATE: CompressionMode get() = CompressionMode(1L)
            /**
             * Uses the Zstandard (https://facebook.github.io/zstd/) compression method.
             *
             * Generated from Godot docs: FileAccess.COMPRESSION_ZSTD
             */
            actual val ZSTD: CompressionMode get() = CompressionMode(2L)
            /**
             * Uses the gzip (https://www.gzip.org/) compression method.
             *
             * Generated from Godot docs: FileAccess.COMPRESSION_GZIP
             */
            actual val GZIP: CompressionMode get() = CompressionMode(3L)
            /**
             * Uses the brotli (https://github.com/google/brotli) compression method (only decompression is
             * supported).
             *
             * Generated from Godot docs: FileAccess.COMPRESSION_BROTLI
             */
            actual val BROTLI: CompressionMode get() = CompressionMode(4L)
        }
    }

    /**
     * Godot's `FileAccess.UnixPermissionFlags` bitfield as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values (`FileAccess.UnixPermissionFlags.<NAME>`).
     *
     * Generated from Godot docs: FileAccess.UnixPermissionFlags
     */
    actual value class UnixPermissionFlags
    actual constructor(
        actual override val value: Long,
    ) : GodotEnumValue {
        actual infix fun or(other: UnixPermissionFlags): UnixPermissionFlags = UnixPermissionFlags(value or other.value)

        actual infix fun and(other: UnixPermissionFlags): UnixPermissionFlags = UnixPermissionFlags(value and other.value)

        actual infix fun xor(other: UnixPermissionFlags): UnixPermissionFlags = UnixPermissionFlags(value xor other.value)

        actual fun inv(): UnixPermissionFlags = UnixPermissionFlags(value.inv())

        actual operator fun contains(other: UnixPermissionFlags): Boolean = (value and other.value) == other.value

        actual companion object {
            /**
             * Read for owner bit.
             *
             * Generated from Godot docs: FileAccess.UNIX_READ_OWNER
             */
            actual val READ_OWNER: UnixPermissionFlags get() = UnixPermissionFlags(256L)
            /**
             * Write for owner bit.
             *
             * Generated from Godot docs: FileAccess.UNIX_WRITE_OWNER
             */
            actual val WRITE_OWNER: UnixPermissionFlags get() = UnixPermissionFlags(128L)
            /**
             * Execute for owner bit.
             *
             * Generated from Godot docs: FileAccess.UNIX_EXECUTE_OWNER
             */
            actual val EXECUTE_OWNER: UnixPermissionFlags get() = UnixPermissionFlags(64L)
            /**
             * Read for group bit.
             *
             * Generated from Godot docs: FileAccess.UNIX_READ_GROUP
             */
            actual val READ_GROUP: UnixPermissionFlags get() = UnixPermissionFlags(32L)
            /**
             * Write for group bit.
             *
             * Generated from Godot docs: FileAccess.UNIX_WRITE_GROUP
             */
            actual val WRITE_GROUP: UnixPermissionFlags get() = UnixPermissionFlags(16L)
            /**
             * Execute for group bit.
             *
             * Generated from Godot docs: FileAccess.UNIX_EXECUTE_GROUP
             */
            actual val EXECUTE_GROUP: UnixPermissionFlags get() = UnixPermissionFlags(8L)
            /**
             * Read for other bit.
             *
             * Generated from Godot docs: FileAccess.UNIX_READ_OTHER
             */
            actual val READ_OTHER: UnixPermissionFlags get() = UnixPermissionFlags(4L)
            /**
             * Write for other bit.
             *
             * Generated from Godot docs: FileAccess.UNIX_WRITE_OTHER
             */
            actual val WRITE_OTHER: UnixPermissionFlags get() = UnixPermissionFlags(2L)
            /**
             * Execute for other bit.
             *
             * Generated from Godot docs: FileAccess.UNIX_EXECUTE_OTHER
             */
            actual val EXECUTE_OTHER: UnixPermissionFlags get() = UnixPermissionFlags(1L)
            /**
             * Set user id on execution bit.
             *
             * Generated from Godot docs: FileAccess.UNIX_SET_USER_ID
             */
            actual val SET_USER_ID: UnixPermissionFlags get() = UnixPermissionFlags(2048L)
            /**
             * Set group id on execution bit.
             *
             * Generated from Godot docs: FileAccess.UNIX_SET_GROUP_ID
             */
            actual val SET_GROUP_ID: UnixPermissionFlags get() = UnixPermissionFlags(1024L)
            /**
             * Restricted deletion (sticky) bit.
             *
             * Generated from Godot docs: FileAccess.UNIX_RESTRICTED_DELETE
             */
            actual val RESTRICTED_DELETE: UnixPermissionFlags get() = UnixPermissionFlags(512L)
        }
    }
    // ===== END GENERATED ENUMS: FileAccess =====


    fun open(path: String, flags: FileAccess.ModeFlags): FileAccessHandle? {
        val segment = ObjectCalls.ptrcallStaticWithStringAndLongArgsRetObject(openBind, path, flags.value)
        return if (segment.address() == 0L) null else RefCounted.owned(FileAccessHandle(GodotHandle(segment)))
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
