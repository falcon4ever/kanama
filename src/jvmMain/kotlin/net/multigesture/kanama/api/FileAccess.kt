package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.BuiltinTypes
import net.multigesture.kanama.binding.runtime.ObjectCalls
import java.lang.foreign.MemorySegment

/**
 * Provides methods for file reading and writing operations.
 *
 * Generated from Godot docs: FileAccess
 */
object FileAccess {

    const val READ = 1L
    const val WRITE = 2L
    const val READ_WRITE = 3L
    private const val NOARGS_ERROR_HASH = 166280745L
    private const val NOARGS_VOID_HASH = 3218959716L
    private const val NOARGS_STRING_HASH = 201670096L
    private const val NOARGS_BOOL_ALT_HASH = 36873697L
    private const val NOARGS_LONG_HASH = 3905245786L
    private const val OPEN_HASH = 1247358404L
    private const val STRING_STRING_HASH = 1703090593L
    private const val STRING_BOOL_HASH = 2323990056L
    private const val STRING_LONG_HASH = 1597066294L
    private const val STRING_PERMISSIONS_HASH = 524341837L
    private const val STRING_PACKED_STRING_ARRAY_HASH = 3538744774L
    private const val TWO_STRING_ERROR_HASH = 852856452L

    private val openBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "open", OPEN_HASH)
    }

    private val openEncryptedBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "open_encrypted", 788003459L)
    }

    private val openEncryptedWithPassBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "open_encrypted_with_pass", 790283377L)
    }

    private val openCompressedBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "open_compressed", 3686439335L)
    }

    private val getOpenErrorBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "get_open_error", NOARGS_ERROR_HASH)
    }

    private val createTempBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "create_temp", 171914364L)
    }

    private val closeBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "close", NOARGS_VOID_HASH)
    }

    private val flushBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "flush", NOARGS_VOID_HASH)
    }

    private val getPathBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "get_path", NOARGS_STRING_HASH)
    }

    private val getPathAbsoluteBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "get_path_absolute", NOARGS_STRING_HASH)
    }

    private val isOpenBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "is_open", NOARGS_BOOL_ALT_HASH)
    }

    private val getPositionBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "get_position", NOARGS_LONG_HASH)
    }

    private val getLengthBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "get_length", NOARGS_LONG_HASH)
    }

    private val eofReachedBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "eof_reached", NOARGS_BOOL_ALT_HASH)
    }

    private val getLineBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "get_line", NOARGS_STRING_HASH)
    }

    private val getAsTextBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "get_as_text", NOARGS_STRING_HASH)
    }

    private val getErrorBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "get_error", 3185525595L)
    }

    private val resizeBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "resize", 844576869L)
    }

    private val seekBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "seek", 1286410249L)
    }

    private val seekEndBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "seek_end", 1995695955L)
    }

    private val get8Bind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "get_8", NOARGS_LONG_HASH)
    }

    private val get16Bind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "get_16", NOARGS_LONG_HASH)
    }

    private val get32Bind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "get_32", NOARGS_LONG_HASH)
    }

    private val get64Bind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "get_64", NOARGS_LONG_HASH)
    }

    private val getFloatBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "get_float", 1740695150L)
    }

    private val getHalfBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "get_half", 1740695150L)
    }

    private val getDoubleBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "get_double", 1740695150L)
    }

    private val getRealBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "get_real", 1740695150L)
    }

    private val isBigEndianBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "is_big_endian", NOARGS_BOOL_ALT_HASH)
    }

    private val setBigEndianBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "set_big_endian", 2586408642L)
    }

    private val getCsvLineBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "get_csv_line", 2358116058L)
    }

    private val getPascalStringBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "get_pascal_string", 2841200299L)
    }

    private val getBufferBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "get_buffer", 4131300905L)
    }

    private val getVarBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "get_var", 189129690L)
    }

    private val storeLineBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "store_line", STRING_BOOL_HASH)
    }

    private val storeStringBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "store_string", STRING_BOOL_HASH)
    }

    private val storeBufferBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "store_buffer", 114037665L)
    }

    private val storeVarBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "store_var", 117357437L)
    }

    private val store8Bind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "store_8", 3067735520L)
    }

    private val store16Bind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "store_16", 3067735520L)
    }

    private val store32Bind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "store_32", 3067735520L)
    }

    private val store64Bind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "store_64", 3067735520L)
    }

    private val storeFloatBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "store_float", 330693286L)
    }

    private val storeDoubleBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "store_double", 330693286L)
    }

    private val storeHalfBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "store_half", 330693286L)
    }

    private val storeRealBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "store_real", 330693286L)
    }

    private val storePascalStringBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "store_pascal_string", STRING_BOOL_HASH)
    }

    private val storeCsvLineBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "store_csv_line", 1611473434L)
    }

    private val getFileAsStringBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "get_file_as_string", STRING_STRING_HASH)
    }

    private val getFileAsBytesBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "get_file_as_bytes", 659035735L)
    }

    private val getMd5Bind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "get_md5", STRING_STRING_HASH)
    }

    private val getSha256Bind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "get_sha256", STRING_STRING_HASH)
    }

    private val fileExistsBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "file_exists", STRING_BOOL_HASH)
    }

    private val getModifiedTimeBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "get_modified_time", STRING_LONG_HASH)
    }

    private val getAccessTimeBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "get_access_time", STRING_LONG_HASH)
    }

    private val getSizeBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "get_size", STRING_LONG_HASH)
    }

    private val getUnixPermissionsBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "get_unix_permissions", STRING_PERMISSIONS_HASH)
    }

    private val getHiddenAttributeBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "get_hidden_attribute", STRING_BOOL_HASH)
    }

    private val getReadOnlyAttributeBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "get_read_only_attribute", STRING_BOOL_HASH)
    }

    private val getExtendedAttributesListBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "get_extended_attributes_list", STRING_PACKED_STRING_ARRAY_HASH)
    }

    private val getExtendedAttributeBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "get_extended_attribute", 955893464L)
    }

    private val getExtendedAttributeStringBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "get_extended_attribute_string", 1218461987L)
    }

    private val setExtendedAttributeBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "set_extended_attribute", 2643421469L)
    }

    private val setExtendedAttributeStringBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "set_extended_attribute_string", 699024349L)
    }

    private val removeExtendedAttributeBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "remove_extended_attribute", TWO_STRING_ERROR_HASH)
    }

    private val setHiddenAttributeBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "set_hidden_attribute", 2892558115L)
    }

    private val setReadOnlyAttributeBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "set_read_only_attribute", 2892558115L)
    }

    private val setUnixPermissionsBind by lazy {
        ObjectCalls.getMethodBind("FileAccess", "set_unix_permissions", 846038644L)
    }

    private fun <T> withOpenFileRead(path: String, fallback: T, block: (MemorySegment) -> T): T {
        val file = ObjectCalls.ptrcallWithStringAndLongArgsRetObject(openBind, MemorySegment.NULL, path, READ)
        if (file.address() == 0L) return fallback
        return try {
            block(file)
        } finally {
            ObjectCalls.ptrcallNoArgs(closeBind, file)
            ObjectCalls.destroyObject(file)
        }
    }

    private fun <T> withOpenFile(path: String, mode: Long, fallback: T, block: (MemorySegment) -> T): T {
        val file = ObjectCalls.ptrcallWithStringAndLongArgsRetObject(openBind, MemorySegment.NULL, path, mode)
        if (file.address() == 0L) return fallback
        return try {
            block(file)
        } finally {
            ObjectCalls.ptrcallNoArgs(closeBind, file)
            ObjectCalls.destroyObject(file)
        }
    }

    // Error-returning variant: the open error must be read AFTER the failed open (an eagerly
    // evaluated fallback would capture the error state of a previous, unrelated open).
    private fun withOpenFileOrOpenError(path: String, mode: Long, block: (MemorySegment) -> Long): Long {
        val file = ObjectCalls.ptrcallWithStringAndLongArgsRetObject(openBind, MemorySegment.NULL, path, mode)
        if (file.address() == 0L) return getOpenError()
        return try {
            block(file)
        } finally {
            ObjectCalls.ptrcallNoArgs(closeBind, file)
            ObjectCalls.destroyObject(file)
        }
    }

    internal fun closeHandle(handle: MemorySegment) {
        ObjectCalls.ptrcallNoArgs(closeBind, handle)
    }

    internal fun flushHandle(handle: MemorySegment) {
        ObjectCalls.ptrcallNoArgs(flushBind, handle)
    }

    internal fun getPathHandle(handle: MemorySegment): String =
        ObjectCalls.ptrcallNoArgsRetString(getPathBind, handle)

    internal fun getPathAbsoluteHandle(handle: MemorySegment): String =
        ObjectCalls.ptrcallNoArgsRetString(getPathAbsoluteBind, handle)

    internal fun isOpenHandle(handle: MemorySegment): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isOpenBind, handle)

    internal fun getPositionHandle(handle: MemorySegment): Long =
        ObjectCalls.ptrcallNoArgsRetLong(getPositionBind, handle)

    internal fun getLengthHandle(handle: MemorySegment): Long =
        ObjectCalls.ptrcallNoArgsRetLong(getLengthBind, handle)

    internal fun eofReachedHandle(handle: MemorySegment): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(eofReachedBind, handle)

    internal fun getLineHandle(handle: MemorySegment): String =
        ObjectCalls.ptrcallNoArgsRetString(getLineBind, handle)

    internal fun getAsTextHandle(handle: MemorySegment): String =
        ObjectCalls.ptrcallNoArgsRetString(getAsTextBind, handle)

    internal fun getErrorHandle(handle: MemorySegment): Long =
        ObjectCalls.ptrcallNoArgsRetLong(getErrorBind, handle)

    internal fun storeStringHandle(handle: MemorySegment, text: String): Boolean =
        ObjectCalls.ptrcallWithStringArgRetBool(storeStringBind, handle, text)

    internal fun storeLineHandle(handle: MemorySegment, line: String): Boolean =
        ObjectCalls.ptrcallWithStringArgRetBool(storeLineBind, handle, line)

    internal fun getVarHandle(handle: MemorySegment, allowObjects: Boolean): Any? =
        ObjectCalls.ptrcallWithBoolArgRetVariantScalar(getVarBind, handle, allowObjects)

    internal fun storeVarHandle(handle: MemorySegment, value: Any?, fullObjects: Boolean): Boolean =
        ObjectCalls.ptrcallWithVariantAndBoolArgRetBool(storeVarBind, handle, value, fullObjects)

    /**
     * Returns the result of the last `open` call in the current thread.
     *
     * Generated from Godot docs: FileAccess.get_open_error
     */
    @JvmStatic
    fun getOpenError(): Long =
        ObjectCalls.ptrcallNoArgsRetLong(getOpenErrorBind, MemorySegment.NULL)

    /**
     * Creates a new `FileAccess` object and opens the file for writing or reading, depending on the
     * flags. Returns `null` if opening the file failed. You can use `get_open_error` to check the
     * error that occurred.
     *
     * Generated from Godot docs: FileAccess.open
     */
    @JvmStatic
    fun open(path: String, flags: Long): FileAccessHandle? =
        FileAccessHandle.wrap(ObjectCalls.ptrcallWithStringAndLongArgsRetObject(openBind, MemorySegment.NULL, path, flags))

    /**
     * Creates a new `FileAccess` object and opens an encrypted file in write or read mode. You need to
     * pass a binary key to encrypt/decrypt it. Note: The provided key must be 32 bytes long. Returns
     * `null` if opening the file failed. You can use `get_open_error` to check the error that
     * occurred.
     *
     * Generated from Godot docs: FileAccess.open_encrypted
     */
    @JvmStatic
    fun openEncrypted(
        path: String,
        modeFlags: Long,
        key: ByteArray,
        iv: ByteArray = ByteArray(0),
    ): FileAccessHandle? =
        FileAccessHandle.wrap(
            ObjectCalls.ptrcallWithStringLongByteArrayByteArrayArgsRetObject(
                openEncryptedBind,
                MemorySegment.NULL,
                path,
                modeFlags,
                key,
                iv,
            ),
        )

    /**
     * Creates a new `FileAccess` object and opens an encrypted file in write or read mode. You need to
     * pass a password to encrypt/decrypt it. Returns `null` if opening the file failed. You can use
     * `get_open_error` to check the error that occurred.
     *
     * Generated from Godot docs: FileAccess.open_encrypted_with_pass
     */
    @JvmStatic
    fun openEncryptedWithPass(path: String, modeFlags: Long, pass: String): FileAccessHandle? =
        FileAccessHandle.wrap(
            ObjectCalls.ptrcallWithStringLongStringArgsRetObject(
                openEncryptedWithPassBind,
                MemorySegment.NULL,
                path,
                modeFlags,
                pass,
            ),
        )

    /**
     * Creates a new `FileAccess` object and opens a compressed file for reading or writing. Note:
     * `open_compressed` can only read files that were saved by Godot, not third-party compression
     * formats. See GitHub issue #28999 (https://github.com/godotengine/godot/issues/28999) for a
     * workaround. Returns `null` if opening the file failed. You can use `get_open_error` to check the
     * error that occurred.
     *
     * Generated from Godot docs: FileAccess.open_compressed
     */
    @JvmStatic
    fun openCompressed(path: String, modeFlags: Long, compressionMode: Long = 0L): FileAccessHandle? =
        FileAccessHandle.wrap(
            ObjectCalls.ptrcallWithStringTwoLongArgsRetObject(
                openCompressedBind,
                MemorySegment.NULL,
                path,
                modeFlags,
                compressionMode,
            ),
        )

    /**
     * Creates a temporary file. This file will be freed when the returned `FileAccess` is freed. If
     * `prefix` is not empty, it will be prefixed to the file name, separated by a `-`. If `extension`
     * is not empty, it will be appended to the temporary file name. If `keep` is `true`, the file is
     * not deleted when the returned `FileAccess` is freed. Returns `null` if opening the file failed.
     * You can use `get_open_error` to check the error that occurred.
     *
     * Generated from Godot docs: FileAccess.create_temp
     */
    @JvmStatic
    fun createTemp(modeFlags: Long, prefix: String = "", extension: String = "", keep: Boolean = false): FileAccessHandle? =
        FileAccessHandle.wrap(
            ObjectCalls.ptrcallWithLongTwoStringBoolArgsRetObject(
                createTempBind,
                MemorySegment.NULL,
                modeFlags,
                prefix,
                extension,
                keep,
            ),
        )

    @JvmStatic
    fun getPathFor(path: String): String =
        withOpenFileRead(path, "") { file ->
            ObjectCalls.ptrcallNoArgsRetString(getPathBind, file)
        }

    @JvmStatic
    fun getPathAbsoluteFor(path: String): String =
        withOpenFileRead(path, "") { file ->
            ObjectCalls.ptrcallNoArgsRetString(getPathAbsoluteBind, file)
        }

    @JvmStatic
    fun isOpenFor(path: String): Boolean =
        withOpenFileRead(path, false) { file ->
            ObjectCalls.ptrcallNoArgsRetBool(isOpenBind, file)
        }

    @JvmStatic
    fun getPositionFor(path: String): Long =
        withOpenFileRead(path, 0L) { file ->
            ObjectCalls.ptrcallNoArgsRetLong(getPositionBind, file)
        }

    @JvmStatic
    fun getLengthFor(path: String): Long =
        withOpenFileRead(path, 0L) { file ->
            ObjectCalls.ptrcallNoArgsRetLong(getLengthBind, file)
        }

    @JvmStatic
    fun eofReachedFor(path: String): Boolean =
        withOpenFileRead(path, false) { file ->
            ObjectCalls.ptrcallNoArgsRetBool(eofReachedBind, file)
        }

    @JvmStatic
    fun getLineFor(path: String): String =
        withOpenFileRead(path, "") { file ->
            ObjectCalls.ptrcallNoArgsRetString(getLineBind, file)
        }

    /**
     * Returns the whole file as a `String`. Text is interpreted as being UTF-8 encoded. This ignores
     * the file cursor and does not affect it.
     *
     * Generated from Godot docs: FileAccess.get_as_text
     */
    @JvmStatic
    fun getAsText(path: String): String =
        withOpenFileRead(path, "") { file ->
            ObjectCalls.ptrcallNoArgsRetString(getAsTextBind, file)
        }

    @JvmStatic
    fun getErrorFor(path: String): Long =
        withOpenFileOrOpenError(path, READ) { file ->
            ObjectCalls.ptrcallNoArgsRetLong(getErrorBind, file)
        }

    @JvmStatic
    fun get8At(path: String, position: Long = 0L): Long =
        withOpenFileRead(path, 0L) { file ->
            ObjectCalls.ptrcallWithLongArg(seekBind, file, position)
            ObjectCalls.ptrcallNoArgsRetInt(get8Bind, file).toLong()
        }

    @JvmStatic
    fun get16At(path: String, position: Long = 0L): Long =
        withOpenFileRead(path, 0L) { file ->
            ObjectCalls.ptrcallWithLongArg(seekBind, file, position)
            ObjectCalls.ptrcallNoArgsRetInt(get16Bind, file).toLong()
        }

    @JvmStatic
    fun get32At(path: String, position: Long = 0L): Long =
        withOpenFileRead(path, 0L) { file ->
            ObjectCalls.ptrcallWithLongArg(seekBind, file, position)
            ObjectCalls.ptrcallNoArgsRetUInt32(get32Bind, file)
        }

    @JvmStatic
    fun get64At(path: String, position: Long = 0L): Long =
        withOpenFileRead(path, 0L) { file ->
            ObjectCalls.ptrcallWithLongArg(seekBind, file, position)
            ObjectCalls.ptrcallNoArgsRetLong(get64Bind, file)
        }

    @JvmStatic
    fun getFloatAt(path: String, position: Long = 0L): Double =
        withOpenFileRead(path, 0.0) { file ->
            ObjectCalls.ptrcallWithLongArg(seekBind, file, position)
            ObjectCalls.ptrcallNoArgsRetDouble(getFloatBind, file)
        }

    @JvmStatic
    fun getDoubleAt(path: String, position: Long = 0L): Double =
        withOpenFileRead(path, 0.0) { file ->
            ObjectCalls.ptrcallWithLongArg(seekBind, file, position)
            ObjectCalls.ptrcallNoArgsRetDouble(getDoubleBind, file)
        }

    @JvmStatic
    fun getHalfAt(path: String, position: Long = 0L): Double =
        withOpenFileRead(path, 0.0) { file ->
            ObjectCalls.ptrcallWithLongArg(seekBind, file, position)
            ObjectCalls.ptrcallNoArgsRetDouble(getHalfBind, file)
        }

    @JvmStatic
    fun getRealAt(path: String, position: Long = 0L): Double =
        withOpenFileRead(path, 0.0) { file ->
            ObjectCalls.ptrcallWithLongArg(seekBind, file, position)
            ObjectCalls.ptrcallNoArgsRetDouble(getRealBind, file)
        }

    @JvmStatic
    fun isBigEndianFor(path: String): Boolean =
        withOpenFileRead(path, false) { file ->
            ObjectCalls.ptrcallNoArgsRetBool(isBigEndianBind, file)
        }

    @JvmStatic
    fun getCsvLineAt(path: String, delimiter: String = ","): List<String> =
        withOpenFileRead(path, emptyList()) { file ->
            ObjectCalls.ptrcallWithStringArgRetPackedStringList(getCsvLineBind, file, delimiter)
        }

    @JvmStatic
    fun getPascalStringFor(path: String, position: Long = 0L): String =
        withOpenFileRead(path, "") { file ->
            ObjectCalls.ptrcallWithLongArg(seekBind, file, position)
            ObjectCalls.ptrcallNoArgsRetString(getPascalStringBind, file)
        }

    @JvmStatic
    fun getBufferAt(path: String, length: Long, position: Long = 0L): ByteArray =
        withOpenFileRead(path, ByteArray(0)) { file ->
            ObjectCalls.ptrcallWithLongArg(seekBind, file, position)
            ObjectCalls.ptrcallWithLongArgRetByteArray(getBufferBind, file, length)
        }

    @JvmStatic
    fun getVarAt(path: String, allowObjects: Boolean = false): Any? =
        withOpenFileRead(path, null) { file ->
            ObjectCalls.ptrcallWithBoolArgRetVariantScalar(getVarBind, file, allowObjects)
        }

    @JvmStatic
    fun getPositionFromEnd(path: String, position: Long = 0L): Long =
        withOpenFileRead(path, 0L) { file ->
            ObjectCalls.ptrcallWithLongArg(seekEndBind, file, position)
            ObjectCalls.ptrcallNoArgsRetLong(getPositionBind, file)
        }

    @JvmStatic
    fun writeString(path: String, text: String): Boolean =
        withOpenFile(path, WRITE, false) { file ->
            val ok = ObjectCalls.ptrcallWithStringArgRetBool(storeStringBind, file, text)
            ObjectCalls.ptrcallNoArgs(flushBind, file)
            ok
        }

    @JvmStatic
    fun writeLine(path: String, line: String): Boolean =
        withOpenFile(path, WRITE, false) { file ->
            val ok = ObjectCalls.ptrcallWithStringArgRetBool(storeLineBind, file, line)
            ObjectCalls.ptrcallNoArgs(flushBind, file)
            ok
        }

    @JvmStatic
    fun writeBytes(path: String, bytes: ByteArray): Boolean =
        withOpenFile(path, WRITE, false) { file ->
            val ok = ObjectCalls.ptrcallWithByteArrayArgRetBool(storeBufferBind, file, bytes)
            ObjectCalls.ptrcallNoArgs(flushBind, file)
            ok
        }

    @JvmStatic
    fun write8(path: String, value: Long): Boolean =
        withOpenFile(path, WRITE, false) { file ->
            val ok = ObjectCalls.ptrcallWithIntArgRetBool(store8Bind, file, BuiltinTypes.requireUInt8(value))
            ObjectCalls.ptrcallNoArgs(flushBind, file)
            ok
        }

    @JvmStatic
    fun write16(path: String, value: Long, bigEndian: Boolean = false): Boolean =
        withOpenFile(path, WRITE, false) { file ->
            ObjectCalls.ptrcallWithBoolArg(setBigEndianBind, file, bigEndian)
            val ok = ObjectCalls.ptrcallWithIntArgRetBool(store16Bind, file, BuiltinTypes.requireUInt16(value))
            ObjectCalls.ptrcallNoArgs(flushBind, file)
            ok
        }

    @JvmStatic
    fun write32(path: String, value: Long, bigEndian: Boolean = false): Boolean =
        withOpenFile(path, WRITE, false) { file ->
            ObjectCalls.ptrcallWithBoolArg(setBigEndianBind, file, bigEndian)
            val ok = ObjectCalls.ptrcallWithUInt32ArgRetBool(store32Bind, file, value)
            ObjectCalls.ptrcallNoArgs(flushBind, file)
            ok
        }

    @JvmStatic
    fun write64(path: String, value: Long, bigEndian: Boolean = false): Boolean =
        withOpenFile(path, WRITE, false) { file ->
            ObjectCalls.ptrcallWithBoolArg(setBigEndianBind, file, bigEndian)
            val ok = ObjectCalls.ptrcallWithLongArgRetBool(store64Bind, file, value)
            ObjectCalls.ptrcallNoArgs(flushBind, file)
            ok
        }

    @JvmStatic
    fun writeFloat(path: String, value: Double, bigEndian: Boolean = false): Boolean =
        withOpenFile(path, WRITE, false) { file ->
            ObjectCalls.ptrcallWithBoolArg(setBigEndianBind, file, bigEndian)
            val ok = ObjectCalls.ptrcallWithDoubleArgRetBool(storeFloatBind, file, value)
            ObjectCalls.ptrcallNoArgs(flushBind, file)
            ok
        }

    @JvmStatic
    fun writeDouble(path: String, value: Double, bigEndian: Boolean = false): Boolean =
        withOpenFile(path, WRITE, false) { file ->
            ObjectCalls.ptrcallWithBoolArg(setBigEndianBind, file, bigEndian)
            val ok = ObjectCalls.ptrcallWithDoubleArgRetBool(storeDoubleBind, file, value)
            ObjectCalls.ptrcallNoArgs(flushBind, file)
            ok
        }

    @JvmStatic
    fun writeHalf(path: String, value: Double, bigEndian: Boolean = false): Boolean =
        withOpenFile(path, WRITE, false) { file ->
            ObjectCalls.ptrcallWithBoolArg(setBigEndianBind, file, bigEndian)
            val ok = ObjectCalls.ptrcallWithDoubleArgRetBool(storeHalfBind, file, value)
            ObjectCalls.ptrcallNoArgs(flushBind, file)
            ok
        }

    @JvmStatic
    fun writeReal(path: String, value: Double, bigEndian: Boolean = false): Boolean =
        withOpenFile(path, WRITE, false) { file ->
            ObjectCalls.ptrcallWithBoolArg(setBigEndianBind, file, bigEndian)
            val ok = ObjectCalls.ptrcallWithDoubleArgRetBool(storeRealBind, file, value)
            ObjectCalls.ptrcallNoArgs(flushBind, file)
            ok
        }

    @JvmStatic
    fun writePascalString(path: String, value: String): Boolean =
        withOpenFile(path, WRITE, false) { file ->
            val ok = ObjectCalls.ptrcallWithStringArgRetBool(storePascalStringBind, file, value)
            ObjectCalls.ptrcallNoArgs(flushBind, file)
            ok
        }

    @JvmStatic
    fun writeCsvLine(path: String, values: List<String>, delimiter: String = ","): Boolean =
        withOpenFile(path, WRITE, false) { file ->
            val ok = ObjectCalls.ptrcallWithPackedStringListAndStringArgsRetBool(storeCsvLineBind, file, values, delimiter)
            ObjectCalls.ptrcallNoArgs(flushBind, file)
            ok
        }

    @JvmStatic
    fun writeVar(path: String, value: Any?, fullObjects: Boolean = false): Boolean =
        withOpenFile(path, WRITE, false) { file ->
            val ok = ObjectCalls.ptrcallWithVariantAndBoolArgRetBool(storeVarBind, file, value, fullObjects)
            ObjectCalls.ptrcallNoArgs(flushBind, file)
            ok
        }

    @JvmStatic
    fun resizeFile(path: String, length: Long): Long =
        withOpenFileOrOpenError(path, READ_WRITE) { file ->
            ObjectCalls.ptrcallWithLongArgRetLong(resizeBind, file, length)
        }

    /**
     * Returns the whole `path` file contents as a `String`. Text is interpreted as being UTF-8
     * encoded. Returns an empty `String` if an error occurred while opening the file. You can use
     * `get_open_error` to check the error that occurred.
     *
     * Generated from Godot docs: FileAccess.get_file_as_string
     */
    @JvmStatic
    fun getFileAsString(path: String): String =
        ObjectCalls.ptrcallWithStringArgRetString(getFileAsStringBind, MemorySegment.NULL, path)

    /**
     * Returns the whole `path` file contents as a `PackedByteArray` without any decoding. Returns an
     * empty `PackedByteArray` if an error occurred while opening the file. You can use
     * `get_open_error` to check the error that occurred.
     *
     * Generated from Godot docs: FileAccess.get_file_as_bytes
     */
    @JvmStatic
    fun getFileAsBytes(path: String): ByteArray =
        ObjectCalls.ptrcallWithStringArgRetByteArray(getFileAsBytesBind, MemorySegment.NULL, path)

    /**
     * Returns an MD5 String representing the file at the given path or an empty `String` on failure.
     *
     * Generated from Godot docs: FileAccess.get_md5
     */
    @JvmStatic
    fun getMd5(path: String): String =
        ObjectCalls.ptrcallWithStringArgRetString(getMd5Bind, MemorySegment.NULL, path)

    /**
     * Returns an SHA-256 `String` representing the file at the given path or an empty `String` on
     * failure.
     *
     * Generated from Godot docs: FileAccess.get_sha256
     */
    @JvmStatic
    fun getSha256(path: String): String =
        ObjectCalls.ptrcallWithStringArgRetString(getSha256Bind, MemorySegment.NULL, path)

    /**
     * Returns `true` if the file exists in the given path. Note: Many resources types are imported
     * (e.g. textures or sound files), and their source asset will not be included in the exported
     * game, as only the imported version is used. See `ResourceLoader.exists` for an alternative
     * approach that takes resource remapping into account. For a non-static, relative equivalent, use
     * `DirAccess.file_exists`.
     *
     * Generated from Godot docs: FileAccess.file_exists
     */
    @JvmStatic
    fun fileExists(path: String): Boolean =
        ObjectCalls.ptrcallWithStringArgRetBool(fileExistsBind, MemorySegment.NULL, path)

    /**
     * Returns the last time the `file` was modified in Unix timestamp format, or `0` on error. This
     * Unix timestamp can be converted to another format using the `Time` singleton.
     *
     * Generated from Godot docs: FileAccess.get_modified_time
     */
    @JvmStatic
    fun getModifiedTime(path: String): Long =
        ObjectCalls.ptrcallWithStringArgRetLong(getModifiedTimeBind, MemorySegment.NULL, path)

    /**
     * Returns the last time the `file` was accessed in Unix timestamp format, or `0` on error. This
     * Unix timestamp can be converted to another format using the `Time` singleton.
     *
     * Generated from Godot docs: FileAccess.get_access_time
     */
    @JvmStatic
    fun getAccessTime(path: String): Long =
        ObjectCalls.ptrcallWithStringArgRetLong(getAccessTimeBind, MemorySegment.NULL, path)

    /**
     * Returns the size of the file at the given path, in bytes, or `-1` on error.
     *
     * Generated from Godot docs: FileAccess.get_size
     */
    @JvmStatic
    fun getSize(path: String): Long =
        ObjectCalls.ptrcallWithStringArgRetLong(getSizeBind, MemorySegment.NULL, path)

    /**
     * Returns the UNIX permissions of the file at the given path. Note: This method is implemented on
     * iOS, Linux/BSD, and macOS.
     *
     * Generated from Godot docs: FileAccess.get_unix_permissions
     */
    @JvmStatic
    fun getUnixPermissions(path: String): Long =
        ObjectCalls.ptrcallWithStringArgRetLong(getUnixPermissionsBind, MemorySegment.NULL, path)

    /**
     * Returns `true` if the hidden attribute is set on the file at the given path. Note: This method
     * is implemented on iOS, BSD, macOS, and Windows.
     *
     * Generated from Godot docs: FileAccess.get_hidden_attribute
     */
    @JvmStatic
    fun getHiddenAttribute(path: String): Boolean =
        ObjectCalls.ptrcallWithStringArgRetBool(getHiddenAttributeBind, MemorySegment.NULL, path)

    /**
     * Returns `true` if the read only attribute is set on the file at the given path. Note: This
     * method is implemented on iOS, BSD, macOS, and Windows.
     *
     * Generated from Godot docs: FileAccess.get_read_only_attribute
     */
    @JvmStatic
    fun getReadOnlyAttribute(path: String): Boolean =
        ObjectCalls.ptrcallWithStringArgRetBool(getReadOnlyAttributeBind, MemorySegment.NULL, path)

    /**
     * Returns a list of file extended attributes. Note: This method is implemented on Linux, macOS,
     * and Windows. Note: Extended attributes support depends on the file system. Attributes will be
     * lost when the file is moved between incompatible file systems. Note: On Linux, only "user"
     * namespace attributes are accessible, namespace prefix should not be included. Note: On Windows,
     * alternate data streams are used to store extended attributes.
     *
     * Generated from Godot docs: FileAccess.get_extended_attributes_list
     */
    @JvmStatic
    fun getExtendedAttributesList(path: String): List<String> =
        ObjectCalls.ptrcallWithStringArgRetPackedStringList(getExtendedAttributesListBind, MemorySegment.NULL, path)

    /**
     * Reads the file extended attribute with name `attribute_name` as a byte array. Note: This method
     * is implemented on Linux, macOS, and Windows. Note: Extended attributes support depends on the
     * file system. Attributes will be lost when the file is moved between incompatible file systems.
     * Note: On Linux, only "user" namespace attributes are accessible, namespace prefix should not be
     * included. Note: On Windows, alternate data streams are used to store extended attributes.
     *
     * Generated from Godot docs: FileAccess.get_extended_attribute
     */
    @JvmStatic
    fun getExtendedAttribute(path: String, attribute: String): ByteArray =
        ObjectCalls.ptrcallWithTwoStringArgsRetByteArray(getExtendedAttributeBind, MemorySegment.NULL, path, attribute)

    /**
     * Reads the file extended attribute with name `attribute_name` as a UTF-8 encoded string. Note:
     * This method is implemented on Linux, macOS, and Windows. Note: Extended attributes support
     * depends on the file system. Attributes will be lost when the file is moved between incompatible
     * file systems. Note: On Linux, only "user" namespace attributes are accessible, namespace prefix
     * should not be included. Note: On Windows, alternate data streams are used to store extended
     * attributes.
     *
     * Generated from Godot docs: FileAccess.get_extended_attribute_string
     */
    @JvmStatic
    fun getExtendedAttributeString(path: String, attribute: String): String =
        ObjectCalls.ptrcallWithTwoStringArgsRetString(
            getExtendedAttributeStringBind,
            MemorySegment.NULL,
            path,
            attribute,
        )

    /**
     * Writes file extended attribute with name `attribute_name` as a byte array. Note: This method is
     * implemented on Linux, macOS, and Windows. Note: Extended attributes support depends on the file
     * system. Attributes will be lost when the file is moved between incompatible file systems. Note:
     * On Linux, only "user" namespace attributes are accessible, namespace prefix should not be
     * included. Note: On Windows, alternate data streams are used to store extended attributes.
     *
     * Generated from Godot docs: FileAccess.set_extended_attribute
     */
    @JvmStatic
    fun setExtendedAttribute(path: String, attribute: String, bytes: ByteArray): Long =
        ObjectCalls.ptrcallWithTwoStringAndByteArrayArgsRetLong(
            setExtendedAttributeBind,
            MemorySegment.NULL,
            path,
            attribute,
            bytes,
        )

    /**
     * Writes file extended attribute with name `attribute_name` as a UTF-8 encoded string. Note: This
     * method is implemented on Linux, macOS, and Windows. Note: Extended attributes support depends on
     * the file system. Attributes will be lost when the file is moved between incompatible file
     * systems. Note: On Linux, only "user" namespace attributes are accessible, namespace prefix
     * should not be included. Note: On Windows, alternate data streams are used to store extended
     * attributes.
     *
     * Generated from Godot docs: FileAccess.set_extended_attribute_string
     */
    @JvmStatic
    fun setExtendedAttributeString(path: String, attribute: String, value: String): Long =
        ObjectCalls.ptrcallWithThreeStringArgsRetLong(
            setExtendedAttributeStringBind,
            MemorySegment.NULL,
            path,
            attribute,
            value,
        )

    /**
     * Removes file extended attribute with name `attribute_name`. Note: This method is implemented on
     * Linux, macOS, and Windows. Note: Extended attributes support depends on the file system.
     * Attributes will be lost when the file is moved between incompatible file systems. Note: On
     * Linux, only "user" namespace attributes are accessible, namespace prefix should not be included.
     * Note: On Windows, alternate data streams are used to store extended attributes.
     *
     * Generated from Godot docs: FileAccess.remove_extended_attribute
     */
    @JvmStatic
    fun removeExtendedAttribute(path: String, attribute: String): Long =
        ObjectCalls.ptrcallWithTwoStringArgsRetLong(removeExtendedAttributeBind, MemorySegment.NULL, path, attribute)

    /**
     * Sets file hidden attribute. Note: This method is implemented on iOS, BSD, macOS, and Windows.
     *
     * Generated from Godot docs: FileAccess.set_hidden_attribute
     */
    @JvmStatic
    fun setHiddenAttribute(path: String, hidden: Boolean): Long =
        ObjectCalls.ptrcallWithStringAndBoolArgRetLong(setHiddenAttributeBind, MemorySegment.NULL, path, hidden)

    /**
     * Sets file read only attribute. Note: This method is implemented on iOS, BSD, macOS, and Windows.
     *
     * Generated from Godot docs: FileAccess.set_read_only_attribute
     */
    @JvmStatic
    fun setReadOnlyAttribute(path: String, readOnly: Boolean): Long =
        ObjectCalls.ptrcallWithStringAndBoolArgRetLong(setReadOnlyAttributeBind, MemorySegment.NULL, path, readOnly)

    /**
     * Sets file UNIX permissions. Note: This method is implemented on iOS, Linux/BSD, and macOS.
     *
     * Generated from Godot docs: FileAccess.set_unix_permissions
     */
    @JvmStatic
    fun setUnixPermissions(path: String, permissions: Long): Long =
        ObjectCalls.ptrcallWithStringAndLongArgRetLong(setUnixPermissionsBind, MemorySegment.NULL, path, permissions)

}
