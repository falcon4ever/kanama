package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Provides methods for file reading and writing operations.
 *
 * In Kanama, `FileAccess.open(...)` returns an owned `FileAccess?`. `close()` closes the file, as
 * GDScript's `file.close()` does, and releases your reference; `use { }` calls it, so
 * `FileAccess.open(path, mode)?.use { ... }` is the idiom. A file you neither close nor `use`
 * stays open, and its writes unflushed, until the garbage collector drops the wrapper and the next
 * frame releases it on the main thread. `closeFile()` is Godot's `close()` alone: it closes the
 * file and keeps the wrapper's reference.
 *
 * Generated from Godot docs: FileAccess
 */
class FileAccess(handle: GodotHandle) : RefCounted(handle) {
    var bigEndian: Boolean
        @JvmName("bigEndianProperty")
        get() = isBigEndian()
        @JvmName("setBigEndianProperty")
        set(value) = setBigEndian(value)

    /**
     * Resizes the file to a specified length. The file must be open in a mode that permits writing. If
     * the file is extended, NUL characters are appended. If the file is truncated, all data from the
     * end file to the original length of the file is lost.
     *
     * Generated from Godot docs: FileAccess.resize
     */
    fun resize(length: Long): GodotError {
        checkOpen()
        val ret = GodotError(ObjectCalls.ptrcallWithLongArgRetLong(Binds.resizeBind, segment, length))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Writes the file's buffer to disk. Flushing is automatically performed when the file is closed.
     * This means you don't need to call `flush` manually before closing a file. Still, calling `flush`
     * can be used to ensure the data is safe even if the project crashes instead of being closed
     * gracefully. Note: Only call `flush` when you actually need it. Otherwise, it will decrease
     * performance due to constant disk writes.
     *
     * Generated from Godot docs: FileAccess.flush
     */
    fun flush() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.flushBind, segment)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns the path as a `String` for the current open file.
     *
     * Generated from Godot docs: FileAccess.get_path
     */
    fun getPath(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getPathBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the absolute path as a `String` for the current open file.
     *
     * Generated from Godot docs: FileAccess.get_path_absolute
     */
    fun getPathAbsolute(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getPathAbsoluteBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns `true` if the file is currently opened.
     *
     * Generated from Godot docs: FileAccess.is_open
     */
    fun isOpen(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.isOpenBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the file cursor to the specified position in bytes, from the beginning of the file. This
     * changes the value returned by `get_position`.
     *
     * Generated from Godot docs: FileAccess.seek
     */
    fun seek(position: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.seekBind, segment, position)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Sets the file cursor to the specified position in bytes, from the end of the file. This changes
     * the value returned by `get_position`. Note: This is an offset, so you should use negative
     * numbers otherwise the file cursor will move past the end of the file.
     *
     * Generated from Godot docs: FileAccess.seek_end
     */
    fun seekEnd(position: Long = 0L) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.seekEndBind, segment, position)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns the file cursor's position in bytes from the beginning of the file. This is the file
     * reading/writing cursor set by `seek` or `seek_end` and advanced by read/write operations.
     *
     * Generated from Godot docs: FileAccess.get_position
     */
    fun getPosition(): Long {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetLong(Binds.getPositionBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the size of the file in bytes. For a pipe, returns the number of bytes available for
     * reading from the pipe.
     *
     * Generated from Godot docs: FileAccess.get_length
     */
    fun getLength(): Long {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetLong(Binds.getLengthBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns `true` if the file cursor has already read past the end of the file. Note:
     * `eof_reached() == false` cannot be used to check whether there is more data available.
     *
     * Generated from Godot docs: FileAccess.eof_reached
     */
    fun eofReached(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.eofReachedBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the next 8 bits from the file as an integer. This advances the file cursor by 1 byte.
     * See `store_8` for details on what values can be stored and retrieved this way.
     *
     * Generated from Godot docs: FileAccess.get_8
     */
    fun get8(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.get8Bind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the next 16 bits from the file as an integer. This advances the file cursor by 2 bytes.
     * See `store_16` for details on what values can be stored and retrieved this way.
     *
     * Generated from Godot docs: FileAccess.get_16
     */
    fun get16(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.get16Bind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the next 32 bits from the file as an integer. This advances the file cursor by 4 bytes.
     * See `store_32` for details on what values can be stored and retrieved this way.
     *
     * Generated from Godot docs: FileAccess.get_32
     */
    fun get32(): Long {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetUInt32(Binds.get32Bind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the next 64 bits from the file as an integer. This advances the file cursor by 8 bytes.
     * See `store_64` for details on what values can be stored and retrieved this way.
     *
     * Generated from Godot docs: FileAccess.get_64
     */
    fun get64(): Long {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetLong(Binds.get64Bind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the next 16 bits from the file as a half-precision floating-point number. This advances
     * the file cursor by 2 bytes.
     *
     * Generated from Godot docs: FileAccess.get_half
     */
    fun getHalf(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getHalfBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the next 32 bits from the file as a floating-point number. This advances the file cursor
     * by 4 bytes.
     *
     * Generated from Godot docs: FileAccess.get_float
     */
    fun getFloat(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getFloatBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the next 64 bits from the file as a floating-point number. This advances the file cursor
     * by 8 bytes.
     *
     * Generated from Godot docs: FileAccess.get_double
     */
    fun getDouble(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDoubleBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the next bits from the file as a floating-point number. This advances the file cursor by
     * either 4 or 8 bytes, depending on the precision used by the Godot build that saved the file. If
     * the file was saved by a Godot build compiled with the `precision=single` option (the default),
     * the number of read bits for that file is 32. Otherwise, if compiled with the `precision=double`
     * option, the number of read bits is 64.
     *
     * Generated from Godot docs: FileAccess.get_real
     */
    fun getReal(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getRealBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns next `length` bytes of the file as a `PackedByteArray`. This advances the file cursor by
     * `length` bytes.
     *
     * Generated from Godot docs: FileAccess.get_buffer
     */
    fun getBuffer(length: Long): ByteArray {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithLongArgRetByteArray(Binds.getBufferBind, segment, length)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the next line of the file as a `String`. The returned string doesn't include newline
     * (`\n`) or carriage return (`\r`) characters, but does include any other leading or trailing
     * whitespace. This advances the file cursor to after the newline character at the end of the line.
     * Text is interpreted as being UTF-8 encoded.
     *
     * Generated from Godot docs: FileAccess.get_line
     */
    fun getLine(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getLineBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the next value of the file in CSV (Comma-Separated Values) format. You can pass a
     * different delimiter `delim` to use other than the default `","` (comma). This delimiter must be
     * one-character long, and cannot be a double quotation mark. Text is interpreted as being UTF-8
     * encoded. Text values must be enclosed in double quotes if they include the delimiter character.
     * Double quotes within a text value can be escaped by doubling their occurrence. This advances the
     * file cursor to after the newline character at the end of the line.
     *
     * Generated from Godot docs: FileAccess.get_csv_line
     */
    fun getCsvLine(delim: String = ","): List<String> {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithStringArgRetPackedStringList(Binds.getCsvLineBind, segment, delim)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the whole file as a `String`. Text is interpreted as being UTF-8 encoded. This ignores
     * the file cursor and does not affect it.
     *
     * Generated from Godot docs: FileAccess.get_as_text
     */
    fun getAsText(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getAsTextBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * If `true`, the file is read with big-endian endianness
     * (https://en.wikipedia.org/wiki/Endianness). If `false`, the file is read with little-endian
     * endianness. If in doubt, leave this to `false` as most files are written with little-endian
     * endianness. Note: This is always reset to system endianness, which is little-endian on all
     * supported platforms, whenever you open the file. Therefore, you must set `big_endian` after
     * opening the file, not before.
     *
     * Generated from Godot docs: FileAccess.is_big_endian
     */
    fun isBigEndian(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.isBigEndianBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * If `true`, the file is read with big-endian endianness
     * (https://en.wikipedia.org/wiki/Endianness). If `false`, the file is read with little-endian
     * endianness. If in doubt, leave this to `false` as most files are written with little-endian
     * endianness. Note: This is always reset to system endianness, which is little-endian on all
     * supported platforms, whenever you open the file. Therefore, you must set `big_endian` after
     * opening the file, not before.
     *
     * Generated from Godot docs: FileAccess.set_big_endian
     */
    fun setBigEndian(bigEndian: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setBigEndianBind, segment, bigEndian)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns the last error that happened when trying to perform operations. Compare with the
     * `ERR_FILE_*` constants from `Error`.
     *
     * Generated from Godot docs: FileAccess.get_error
     */
    fun getError(): GodotError {
        checkOpen()
        val ret = GodotError(ObjectCalls.ptrcallNoArgsRetLong(Binds.getErrorBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the next `Variant` value from the file. If `allow_objects` is `true`, decoding objects
     * is allowed. This advances the file cursor by the number of bytes read. Internally, this uses the
     * same decoding mechanism as the `@GlobalScope.bytes_to_var` method, as described in the Binary
     * serialization API ($DOCS_URL/tutorials/io/binary_serialization_api.html) documentation. Warning:
     * Deserialized objects can contain code which gets executed. Do not use this option if the
     * serialized object comes from untrusted sources to avoid potential security threats such as
     * remote code execution.
     *
     * Generated from Godot docs: FileAccess.get_var
     */
    fun getVar(allowObjects: Boolean = false): Any? {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithBoolArgRetVariantScalar(Binds.getVarBind, segment, allowObjects)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Stores an integer as 8 bits in the file. This advances the file cursor by 1 byte. Returns `true`
     * if the operation is successful. Note: The `value` should lie in the interval `[0, 255]`. Any
     * other value will overflow and wrap around. Note: If an error occurs, the resulting value of the
     * file position indicator is indeterminate. To store a signed integer, use `store_64`, or convert
     * it manually (see `store_16` for an example).
     *
     * Generated from Godot docs: FileAccess.store_8
     */
    fun store8(value: Int): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetBool(Binds.store8Bind, segment, value)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Stores an integer as 16 bits in the file. This advances the file cursor by 2 bytes. Returns
     * `true` if the operation is successful. Note: The `value` should lie in the interval `[0, 2^16 -
     * 1]`. Any other value will overflow and wrap around. Note: If an error occurs, the resulting
     * value of the file position indicator is indeterminate. To store a signed integer, use `store_64`
     * or store a signed integer from the interval `[-2^15, 2^15 - 1]` (i.e. keeping one bit for the
     * signedness) and compute its sign manually when reading.
     *
     * Generated from Godot docs: FileAccess.store_16
     */
    fun store16(value: Int): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetBool(Binds.store16Bind, segment, value)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Stores an integer as 32 bits in the file. This advances the file cursor by 4 bytes. Returns
     * `true` if the operation is successful. Note: The `value` should lie in the interval `[0, 2^32 -
     * 1]`. Any other value will overflow and wrap around. Note: If an error occurs, the resulting
     * value of the file position indicator is indeterminate. To store a signed integer, use
     * `store_64`, or convert it manually (see `store_16` for an example).
     *
     * Generated from Godot docs: FileAccess.store_32
     */
    fun store32(value: Long): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithUInt32ArgRetBool(Binds.store32Bind, segment, value)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Stores an integer as 64 bits in the file. This advances the file cursor by 8 bytes. Returns
     * `true` if the operation is successful. Note: The `value` must lie in the interval `[-2^63, 2^63
     * - 1]` (i.e. be a valid `int` value). Note: If an error occurs, the resulting value of the file
     * position indicator is indeterminate.
     *
     * Generated from Godot docs: FileAccess.store_64
     */
    fun store64(value: Long): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithLongArgRetBool(Binds.store64Bind, segment, value)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Stores a half-precision floating-point number as 16 bits in the file. This advances the file
     * cursor by 2 bytes. Returns `true` if the operation is successful. Note: If an error occurs, the
     * resulting value of the file position indicator is indeterminate.
     *
     * Generated from Godot docs: FileAccess.store_half
     */
    fun storeHalf(value: Double): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithDoubleArgRetBool(Binds.storeHalfBind, segment, value)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Stores a floating-point number as 32 bits in the file. This advances the file cursor by 4 bytes.
     * Returns `true` if the operation is successful. Note: If an error occurs, the resulting value of
     * the file position indicator is indeterminate.
     *
     * Generated from Godot docs: FileAccess.store_float
     */
    fun storeFloat(value: Double): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithDoubleArgRetBool(Binds.storeFloatBind, segment, value)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Stores a floating-point number as 64 bits in the file. This advances the file cursor by 8 bytes.
     * Returns `true` if the operation is successful. Note: If an error occurs, the resulting value of
     * the file position indicator is indeterminate.
     *
     * Generated from Godot docs: FileAccess.store_double
     */
    fun storeDouble(value: Double): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithDoubleArgRetBool(Binds.storeDoubleBind, segment, value)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Stores a floating-point number in the file. This advances the file cursor by either 4 or 8
     * bytes, depending on the precision used by the current Godot build. If using a Godot build
     * compiled with the `precision=single` option (the default), this method will save a 32-bit float.
     * Otherwise, if compiled with the `precision=double` option, this will save a 64-bit float.
     * Returns `true` if the operation is successful. Note: If an error occurs, the resulting value of
     * the file position indicator is indeterminate.
     *
     * Generated from Godot docs: FileAccess.store_real
     */
    fun storeReal(value: Double): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithDoubleArgRetBool(Binds.storeRealBind, segment, value)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Stores the given array of bytes in the file. This advances the file cursor by the number of
     * bytes written. Returns `true` if the operation is successful. Note: If an error occurs, the
     * resulting value of the file position indicator is indeterminate.
     *
     * Generated from Godot docs: FileAccess.store_buffer
     */
    fun storeBuffer(buffer: ByteArray): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithByteArrayArgRetBool(Binds.storeBufferBind, segment, buffer)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Stores `line` in the file followed by a newline character (`\n`), encoding the text as UTF-8.
     * This advances the file cursor by the length of the line, after the newline character. The amount
     * of bytes written depends on the UTF-8 encoded bytes, which may be different from `String.length`
     * which counts the number of UTF-32 codepoints. Returns `true` if the operation is successful.
     * Note: If an error occurs, the resulting value of the file position indicator is indeterminate.
     *
     * Generated from Godot docs: FileAccess.store_line
     */
    fun storeLine(line: String): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithStringArgRetBool(Binds.storeLineBind, segment, line)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Stores the given `PackedStringArray` in the file as a line formatted in the CSV (Comma-Separated
     * Values) format. You can pass a different delimiter `delim` to use other than the default `","`
     * (comma). This delimiter must be one-character long. Text will be encoded as UTF-8. Returns
     * `true` if the operation is successful. Note: If an error occurs, the resulting value of the file
     * position indicator is indeterminate.
     *
     * Generated from Godot docs: FileAccess.store_csv_line
     */
    fun storeCsvLine(values: List<String>, delim: String = ","): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithPackedStringListAndStringArgsRetBool(Binds.storeCsvLineBind, segment, values, delim)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Stores `string` in the file without a newline character (`\n`), encoding the text as UTF-8. This
     * advances the file cursor by the length of the string in UTF-8 encoded bytes, which may be
     * different from `String.length` which counts the number of UTF-32 codepoints. Returns `true` if
     * the operation is successful. Note: This method is intended to be used to write text files. The
     * string is stored as a UTF-8 encoded buffer without string length or terminating zero, which
     * means that it can't be loaded back easily. If you want to store a retrievable string in a binary
     * file, consider using `store_pascal_string` instead. For retrieving strings from a text file, you
     * can use `get_buffer(length).get_string_from_utf8()` (if you know the length) or `get_as_text`.
     * Note: If an error occurs, the resulting value of the file position indicator is indeterminate.
     *
     * Generated from Godot docs: FileAccess.store_string
     */
    fun storeString(string: String): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithStringArgRetBool(Binds.storeStringBind, segment, string)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Stores any Variant value in the file. If `full_objects` is `true`, encoding objects is allowed
     * (and can potentially include code). This advances the file cursor by the number of bytes
     * written. Returns `true` if the operation is successful. Internally, this uses the same encoding
     * mechanism as the `@GlobalScope.var_to_bytes` method, as described in the Binary serialization
     * API ($DOCS_URL/tutorials/io/binary_serialization_api.html) documentation. Note: Not all
     * properties are included. Only properties that are configured with the
     * `PropertyUsageFlags.STORAGE` flag set will be serialized. You can add a new usage flag to a
     * property by overriding the `Object._get_property_list` method in your class. You can also check
     * how property usage is configured by calling `Object._get_property_list`. See
     * `PropertyUsageFlags` for the possible usage flags. Note: If an error occurs, the resulting value
     * of the file position indicator is indeterminate.
     *
     * Generated from Godot docs: FileAccess.store_var
     */
    fun storeVar(value: Any?, fullObjects: Boolean = false): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithVariantAndBoolArgRetBool(Binds.storeVarBind, segment, value, fullObjects)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Stores the given `String` as a line in the file in Pascal format (i.e. also store the length of
     * the string). Text will be encoded as UTF-8. This advances the file cursor by the number of bytes
     * written depending on the UTF-8 encoded bytes, which may be different from `String.length` which
     * counts the number of UTF-32 codepoints. Returns `true` if the operation is successful. Note: If
     * an error occurs, the resulting value of the file position indicator is indeterminate.
     *
     * Generated from Godot docs: FileAccess.store_pascal_string
     */
    fun storePascalString(string: String): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithStringArgRetBool(Binds.storePascalStringBind, segment, string)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns a `String` saved in Pascal format from the file, meaning that the length of the string
     * is explicitly stored at the start. See `store_pascal_string`. This may include newline
     * characters. The file cursor is advanced after the bytes read. Text is interpreted as being UTF-8
     * encoded.
     *
     * Generated from Godot docs: FileAccess.get_pascal_string
     */
    fun getPascalString(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getPascalStringBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Closes the currently opened file and prevents subsequent read/write operations. Use `flush` to
     * persist the data to disk without closing the file. Note: `FileAccess` will automatically close
     * when it's freed, which happens when it goes out of scope or when it gets assigned with `null`.
     * In C# the reference must be disposed after we are done using it, this can be done with the
     * `using` statement or calling the `Dispose` method directly.
     *
     * Generated from Godot docs: FileAccess.close
     */
    fun closeFile() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.closeFileBind, segment)
        ObjectCalls.keepReachable(this)
    }

    private var fileClosed = false

    /**
     * Closes the file, as GDScript's `file.close()` does (Godot's `close()`, here [closeFile]), then
     * releases this wrapper's reference like `RefCounted.close()`. The file is closed even when this
     * is a borrowed view (`fromHandle`, a `FileAccess` passed in from GDScript), which releases
     * nothing, or when something else still references the object. `use { }` calls this.
     */
    override fun close() {
        if (!fileClosed) {
            fileClosed = true
            if (isOpen()) closeFile()
        }
        if (owned) super.close()
    }

    /**
     * Godot's `FileAccess.ModeFlags` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`FileAccess.ModeFlags.<NAME>`).
     *
     * Generated from Godot docs: FileAccess.ModeFlags
     */
    @JvmInline
    value class ModeFlags(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Opens the file for read operations. The file cursor is positioned at the beginning of the file.
             *
             * Generated from Godot docs: FileAccess.READ
             */
            val READ: ModeFlags get() = ModeFlags(1L)
            /**
             * Opens the file for write operations. If the file exists, it is truncated to zero length and its
             * contents are cleared. Otherwise, it is created. Note: When creating a file it must be in an
             * already existing directory. To recursively create directories for a file path, see
             * `DirAccess.make_dir_recursive`.
             *
             * Generated from Godot docs: FileAccess.WRITE
             */
            val WRITE: ModeFlags get() = ModeFlags(2L)
            /**
             * Opens the file for read and write operations. Does not truncate the file. The file cursor is
             * positioned at the beginning of the file.
             *
             * Generated from Godot docs: FileAccess.READ_WRITE
             */
            val READ_WRITE: ModeFlags get() = ModeFlags(3L)
            /**
             * Opens the file for read and write operations. If the file exists, it is truncated to zero length
             * and its contents are cleared. Otherwise, it is created. The file cursor is positioned at the
             * beginning of the file. Note: When creating a file it must be in an already existing directory.
             * To recursively create directories for a file path, see `DirAccess.make_dir_recursive`.
             *
             * Generated from Godot docs: FileAccess.WRITE_READ
             */
            val WRITE_READ: ModeFlags get() = ModeFlags(7L)
        }
    }

    /**
     * Godot's `FileAccess.CompressionMode` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`FileAccess.CompressionMode.<NAME>`).
     *
     * Generated from Godot docs: FileAccess.CompressionMode
     */
    @JvmInline
    value class CompressionMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Uses the FastLZ (https://fastlz.org/) compression method.
             *
             * Generated from Godot docs: FileAccess.COMPRESSION_FASTLZ
             */
            val FASTLZ: CompressionMode get() = CompressionMode(0L)
            /**
             * Uses the DEFLATE (https://en.wikipedia.org/wiki/DEFLATE) compression method.
             *
             * Generated from Godot docs: FileAccess.COMPRESSION_DEFLATE
             */
            val DEFLATE: CompressionMode get() = CompressionMode(1L)
            /**
             * Uses the Zstandard (https://facebook.github.io/zstd/) compression method.
             *
             * Generated from Godot docs: FileAccess.COMPRESSION_ZSTD
             */
            val ZSTD: CompressionMode get() = CompressionMode(2L)
            /**
             * Uses the gzip (https://www.gzip.org/) compression method.
             *
             * Generated from Godot docs: FileAccess.COMPRESSION_GZIP
             */
            val GZIP: CompressionMode get() = CompressionMode(3L)
            /**
             * Uses the brotli (https://github.com/google/brotli) compression method (only decompression is
             * supported).
             *
             * Generated from Godot docs: FileAccess.COMPRESSION_BROTLI
             */
            val BROTLI: CompressionMode get() = CompressionMode(4L)
        }
    }

    /**
     * Godot's `FileAccess.UnixPermissionFlags` bitfield as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values (`FileAccess.UnixPermissionFlags.<NAME>`).
     *
     * Generated from Godot docs: FileAccess.UnixPermissionFlags
     */
    @JvmInline
    value class UnixPermissionFlags(override val value: Long) : GodotEnumValue {
        infix fun or(other: UnixPermissionFlags): UnixPermissionFlags = UnixPermissionFlags(value or other.value)

        infix fun and(other: UnixPermissionFlags): UnixPermissionFlags = UnixPermissionFlags(value and other.value)

        infix fun xor(other: UnixPermissionFlags): UnixPermissionFlags = UnixPermissionFlags(value xor other.value)

        fun inv(): UnixPermissionFlags = UnixPermissionFlags(value.inv())

        operator fun contains(other: UnixPermissionFlags): Boolean = (value and other.value) == other.value

        companion object {
            /**
             * Read for owner bit.
             *
             * Generated from Godot docs: FileAccess.UNIX_READ_OWNER
             */
            val READ_OWNER: UnixPermissionFlags get() = UnixPermissionFlags(256L)
            /**
             * Write for owner bit.
             *
             * Generated from Godot docs: FileAccess.UNIX_WRITE_OWNER
             */
            val WRITE_OWNER: UnixPermissionFlags get() = UnixPermissionFlags(128L)
            /**
             * Execute for owner bit.
             *
             * Generated from Godot docs: FileAccess.UNIX_EXECUTE_OWNER
             */
            val EXECUTE_OWNER: UnixPermissionFlags get() = UnixPermissionFlags(64L)
            /**
             * Read for group bit.
             *
             * Generated from Godot docs: FileAccess.UNIX_READ_GROUP
             */
            val READ_GROUP: UnixPermissionFlags get() = UnixPermissionFlags(32L)
            /**
             * Write for group bit.
             *
             * Generated from Godot docs: FileAccess.UNIX_WRITE_GROUP
             */
            val WRITE_GROUP: UnixPermissionFlags get() = UnixPermissionFlags(16L)
            /**
             * Execute for group bit.
             *
             * Generated from Godot docs: FileAccess.UNIX_EXECUTE_GROUP
             */
            val EXECUTE_GROUP: UnixPermissionFlags get() = UnixPermissionFlags(8L)
            /**
             * Read for other bit.
             *
             * Generated from Godot docs: FileAccess.UNIX_READ_OTHER
             */
            val READ_OTHER: UnixPermissionFlags get() = UnixPermissionFlags(4L)
            /**
             * Write for other bit.
             *
             * Generated from Godot docs: FileAccess.UNIX_WRITE_OTHER
             */
            val WRITE_OTHER: UnixPermissionFlags get() = UnixPermissionFlags(2L)
            /**
             * Execute for other bit.
             *
             * Generated from Godot docs: FileAccess.UNIX_EXECUTE_OTHER
             */
            val EXECUTE_OTHER: UnixPermissionFlags get() = UnixPermissionFlags(1L)
            /**
             * Set user id on execution bit.
             *
             * Generated from Godot docs: FileAccess.UNIX_SET_USER_ID
             */
            val SET_USER_ID: UnixPermissionFlags get() = UnixPermissionFlags(2048L)
            /**
             * Set group id on execution bit.
             *
             * Generated from Godot docs: FileAccess.UNIX_SET_GROUP_ID
             */
            val SET_GROUP_ID: UnixPermissionFlags get() = UnixPermissionFlags(1024L)
            /**
             * Restricted deletion (sticky) bit.
             *
             * Generated from Godot docs: FileAccess.UNIX_RESTRICTED_DELETE
             */
            val RESTRICTED_DELETE: UnixPermissionFlags get() = UnixPermissionFlags(512L)
        }
    }

    companion object {
        /**
         * Creates a new `FileAccess` object and opens the file for writing or reading, depending on the
         * flags. Returns `null` if opening the file failed. You can use `get_open_error` to check the
         * error that occurred.
         *
         * Generated from Godot docs: FileAccess.open
         */
        fun open(path: String, flags: FileAccess.ModeFlags): FileAccess? {
            return FileAccess.wrapOwned(ObjectCalls.ptrcallWithStringAndLongArgsRetObject(Binds.openBind, NULL_SEGMENT, path, flags.value))
        }

        /**
         * Creates a new `FileAccess` object and opens an encrypted file in write or read mode. You need to
         * pass a binary key to encrypt/decrypt it. Note: The provided key must be 32 bytes long. Returns
         * `null` if opening the file failed. You can use `get_open_error` to check the error that
         * occurred.
         *
         * Generated from Godot docs: FileAccess.open_encrypted
         */
        fun openEncrypted(path: String, modeFlags: FileAccess.ModeFlags, key: ByteArray, iv: ByteArray = ByteArray(0)): FileAccess? {
            return FileAccess.wrapOwned(ObjectCalls.ptrcallWithStringLongByteArrayByteArrayArgsRetObject(Binds.openEncryptedBind, NULL_SEGMENT, path, modeFlags.value, key, iv))
        }

        /**
         * Creates a new `FileAccess` object and opens an encrypted file in write or read mode. You need to
         * pass a password to encrypt/decrypt it. Returns `null` if opening the file failed. You can use
         * `get_open_error` to check the error that occurred.
         *
         * Generated from Godot docs: FileAccess.open_encrypted_with_pass
         */
        fun openEncryptedWithPass(path: String, modeFlags: FileAccess.ModeFlags, pass: String): FileAccess? {
            return FileAccess.wrapOwned(ObjectCalls.ptrcallWithStringLongStringArgsRetObject(Binds.openEncryptedWithPassBind, NULL_SEGMENT, path, modeFlags.value, pass))
        }

        /**
         * Creates a new `FileAccess` object and opens a compressed file for reading or writing. Note:
         * `open_compressed` can only read files that were saved by Godot, not third-party compression
         * formats. See GitHub issue #28999 (https://github.com/godotengine/godot/issues/28999) for a
         * workaround. Returns `null` if opening the file failed. You can use `get_open_error` to check the
         * error that occurred.
         *
         * Generated from Godot docs: FileAccess.open_compressed
         */
        fun openCompressed(path: String, modeFlags: FileAccess.ModeFlags, compressionMode: FileAccess.CompressionMode = FileAccess.CompressionMode.FASTLZ): FileAccess? {
            return FileAccess.wrapOwned(ObjectCalls.ptrcallWithStringTwoLongArgsRetObject(Binds.openCompressedBind, NULL_SEGMENT, path, modeFlags.value, compressionMode.value))
        }

        /**
         * Returns the result of the last `open` call in the current thread.
         *
         * Generated from Godot docs: FileAccess.get_open_error
         */
        fun getOpenError(): GodotError {
            return GodotError(ObjectCalls.ptrcallNoArgsRetLong(Binds.getOpenErrorBind, NULL_SEGMENT))
        }

        /**
         * Creates a temporary file. This file will be freed when the returned `FileAccess` is freed. If
         * `prefix` is not empty, it will be prefixed to the file name, separated by a `-`. If `extension`
         * is not empty, it will be appended to the temporary file name. If `keep` is `true`, the file is
         * not deleted when the returned `FileAccess` is freed. Returns `null` if opening the file failed.
         * You can use `get_open_error` to check the error that occurred.
         *
         * Generated from Godot docs: FileAccess.create_temp
         */
        fun createTemp(modeFlags: FileAccess.ModeFlags, prefix: String = "", extension: String = "", keep: Boolean = false): FileAccess? {
            return FileAccess.wrapOwned(ObjectCalls.ptrcallWithLongTwoStringBoolArgsRetObject(Binds.createTempBind, NULL_SEGMENT, modeFlags.value, prefix, extension, keep))
        }

        /**
         * Returns the whole `path` file contents as a `PackedByteArray` without any decoding. Returns an
         * empty `PackedByteArray` if an error occurred while opening the file. You can use
         * `get_open_error` to check the error that occurred.
         *
         * Generated from Godot docs: FileAccess.get_file_as_bytes
         */
        fun getFileAsBytes(path: String): ByteArray {
            return ObjectCalls.ptrcallWithStringArgRetByteArray(Binds.getFileAsBytesBind, NULL_SEGMENT, path)
        }

        /**
         * Returns the whole `path` file contents as a `String`. Text is interpreted as being UTF-8
         * encoded. Returns an empty `String` if an error occurred while opening the file. You can use
         * `get_open_error` to check the error that occurred.
         *
         * Generated from Godot docs: FileAccess.get_file_as_string
         */
        fun getFileAsString(path: String): String {
            return ObjectCalls.ptrcallWithStringArgRetString(Binds.getFileAsStringBind, NULL_SEGMENT, path)
        }

        /**
         * Returns an MD5 String representing the file at the given path or an empty `String` on failure.
         *
         * Generated from Godot docs: FileAccess.get_md5
         */
        fun getMd5(path: String): String {
            return ObjectCalls.ptrcallWithStringArgRetString(Binds.getMd5Bind, NULL_SEGMENT, path)
        }

        /**
         * Returns an SHA-256 `String` representing the file at the given path or an empty `String` on
         * failure.
         *
         * Generated from Godot docs: FileAccess.get_sha256
         */
        fun getSha256(path: String): String {
            return ObjectCalls.ptrcallWithStringArgRetString(Binds.getSha256Bind, NULL_SEGMENT, path)
        }

        /**
         * Returns `true` if the file exists in the given path. Note: Many resources types are imported
         * (e.g. textures or sound files), and their source asset will not be included in the exported
         * game, as only the imported version is used. See `ResourceLoader.exists` for an alternative
         * approach that takes resource remapping into account. For a non-static, relative equivalent, use
         * `DirAccess.file_exists`.
         *
         * Generated from Godot docs: FileAccess.file_exists
         */
        fun fileExists(path: String): Boolean {
            return ObjectCalls.ptrcallWithStringArgRetBool(Binds.fileExistsBind, NULL_SEGMENT, path)
        }

        /**
         * Returns the last time the `file` was modified in Unix timestamp format, or `0` on error. This
         * Unix timestamp can be converted to another format using the `Time` singleton.
         *
         * Generated from Godot docs: FileAccess.get_modified_time
         */
        fun getModifiedTime(file: String): Long {
            return ObjectCalls.ptrcallWithStringArgRetLong(Binds.getModifiedTimeBind, NULL_SEGMENT, file)
        }

        /**
         * Returns the last time the `file` was accessed in Unix timestamp format, or `0` on error. This
         * Unix timestamp can be converted to another format using the `Time` singleton.
         *
         * Generated from Godot docs: FileAccess.get_access_time
         */
        fun getAccessTime(file: String): Long {
            return ObjectCalls.ptrcallWithStringArgRetLong(Binds.getAccessTimeBind, NULL_SEGMENT, file)
        }

        /**
         * Returns the size of the file at the given path, in bytes, or `-1` on error.
         *
         * Generated from Godot docs: FileAccess.get_size
         */
        fun getSize(file: String): Long {
            return ObjectCalls.ptrcallWithStringArgRetLong(Binds.getSizeBind, NULL_SEGMENT, file)
        }

        /**
         * Returns the UNIX permissions of the file at the given path. Note: This method is implemented on
         * iOS, Linux/BSD, and macOS.
         *
         * Generated from Godot docs: FileAccess.get_unix_permissions
         */
        fun getUnixPermissions(file: String): FileAccess.UnixPermissionFlags {
            return FileAccess.UnixPermissionFlags(ObjectCalls.ptrcallWithStringArgRetLong(Binds.getUnixPermissionsBind, NULL_SEGMENT, file))
        }

        /**
         * Sets file UNIX permissions. Note: This method is implemented on iOS, Linux/BSD, and macOS.
         *
         * Generated from Godot docs: FileAccess.set_unix_permissions
         */
        fun setUnixPermissions(file: String, permissions: FileAccess.UnixPermissionFlags): GodotError {
            return GodotError(ObjectCalls.ptrcallWithStringAndLongArgRetLong(Binds.setUnixPermissionsBind, NULL_SEGMENT, file, permissions.value))
        }

        /**
         * Returns `true` if the hidden attribute is set on the file at the given path. Note: This method
         * is implemented on iOS, BSD, macOS, and Windows.
         *
         * Generated from Godot docs: FileAccess.get_hidden_attribute
         */
        fun getHiddenAttribute(file: String): Boolean {
            return ObjectCalls.ptrcallWithStringArgRetBool(Binds.getHiddenAttributeBind, NULL_SEGMENT, file)
        }

        /**
         * Sets file hidden attribute. Note: This method is implemented on iOS, BSD, macOS, and Windows.
         *
         * Generated from Godot docs: FileAccess.set_hidden_attribute
         */
        fun setHiddenAttribute(file: String, hidden: Boolean): GodotError {
            return GodotError(ObjectCalls.ptrcallWithStringAndBoolArgRetLong(Binds.setHiddenAttributeBind, NULL_SEGMENT, file, hidden))
        }

        /**
         * Sets file read only attribute. Note: This method is implemented on iOS, BSD, macOS, and Windows.
         *
         * Generated from Godot docs: FileAccess.set_read_only_attribute
         */
        fun setReadOnlyAttribute(file: String, ro: Boolean): GodotError {
            return GodotError(ObjectCalls.ptrcallWithStringAndBoolArgRetLong(Binds.setReadOnlyAttributeBind, NULL_SEGMENT, file, ro))
        }

        /**
         * Returns `true` if the read only attribute is set on the file at the given path. Note: This
         * method is implemented on iOS, BSD, macOS, and Windows.
         *
         * Generated from Godot docs: FileAccess.get_read_only_attribute
         */
        fun getReadOnlyAttribute(file: String): Boolean {
            return ObjectCalls.ptrcallWithStringArgRetBool(Binds.getReadOnlyAttributeBind, NULL_SEGMENT, file)
        }

        /**
         * Reads the file extended attribute with name `attribute_name` as a byte array. Note: This method
         * is implemented on Linux, macOS, and Windows. Note: Extended attributes support depends on the
         * file system. Attributes will be lost when the file is moved between incompatible file systems.
         * Note: On Linux, only "user" namespace attributes are accessible, namespace prefix should not be
         * included. Note: On Windows, alternate data streams are used to store extended attributes.
         *
         * Generated from Godot docs: FileAccess.get_extended_attribute
         */
        fun getExtendedAttribute(file: String, attributeName: String): ByteArray {
            return ObjectCalls.ptrcallWithTwoStringArgsRetByteArray(Binds.getExtendedAttributeBind, NULL_SEGMENT, file, attributeName)
        }

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
        fun getExtendedAttributeString(file: String, attributeName: String): String {
            return ObjectCalls.ptrcallWithTwoStringArgsRetString(Binds.getExtendedAttributeStringBind, NULL_SEGMENT, file, attributeName)
        }

        /**
         * Writes file extended attribute with name `attribute_name` as a byte array. Note: This method is
         * implemented on Linux, macOS, and Windows. Note: Extended attributes support depends on the file
         * system. Attributes will be lost when the file is moved between incompatible file systems. Note:
         * On Linux, only "user" namespace attributes are accessible, namespace prefix should not be
         * included. Note: On Windows, alternate data streams are used to store extended attributes.
         *
         * Generated from Godot docs: FileAccess.set_extended_attribute
         */
        fun setExtendedAttribute(file: String, attributeName: String, data: ByteArray): GodotError {
            return GodotError(ObjectCalls.ptrcallWithTwoStringAndByteArrayArgsRetLong(Binds.setExtendedAttributeBind, NULL_SEGMENT, file, attributeName, data))
        }

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
        fun setExtendedAttributeString(file: String, attributeName: String, data: String): GodotError {
            return GodotError(ObjectCalls.ptrcallWithThreeStringArgsRetLong(Binds.setExtendedAttributeStringBind, NULL_SEGMENT, file, attributeName, data))
        }

        /**
         * Removes file extended attribute with name `attribute_name`. Note: This method is implemented on
         * Linux, macOS, and Windows. Note: Extended attributes support depends on the file system.
         * Attributes will be lost when the file is moved between incompatible file systems. Note: On
         * Linux, only "user" namespace attributes are accessible, namespace prefix should not be included.
         * Note: On Windows, alternate data streams are used to store extended attributes.
         *
         * Generated from Godot docs: FileAccess.remove_extended_attribute
         */
        fun removeExtendedAttribute(file: String, attributeName: String): GodotError {
            return GodotError(ObjectCalls.ptrcallWithTwoStringArgsRetLong(Binds.removeExtendedAttributeBind, NULL_SEGMENT, file, attributeName))
        }

        /**
         * Returns a list of file extended attributes. Note: This method is implemented on Linux, macOS,
         * and Windows. Note: Extended attributes support depends on the file system. Attributes will be
         * lost when the file is moved between incompatible file systems. Note: On Linux, only "user"
         * namespace attributes are accessible, namespace prefix should not be included. Note: On Windows,
         * alternate data streams are used to store extended attributes.
         *
         * Generated from Godot docs: FileAccess.get_extended_attributes_list
         */
        fun getExtendedAttributesList(file: String): List<String> {
            return ObjectCalls.ptrcallWithStringArgRetPackedStringList(Binds.getExtendedAttributesListBind, NULL_SEGMENT, file)
        }

        @JvmStatic
        fun fromHandle(handle: GodotHandle): FileAccess? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): FileAccess? =
            if (handle.address() == 0L) null else RefCounted.owned(FileAccess(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): FileAccess? =
            if (handle.address() == 0L) null else FileAccess(GodotHandle(handle))
    }

    private object Binds {
        private const val OPEN_HASH = 1247358404L
        @JvmField
        val openBind =
            ObjectCalls.getMethodBind("FileAccess", "open", OPEN_HASH)

        private const val OPEN_ENCRYPTED_HASH = 788003459L
        @JvmField
        val openEncryptedBind =
            ObjectCalls.getMethodBind("FileAccess", "open_encrypted", OPEN_ENCRYPTED_HASH)

        private const val OPEN_ENCRYPTED_WITH_PASS_HASH = 790283377L
        @JvmField
        val openEncryptedWithPassBind =
            ObjectCalls.getMethodBind("FileAccess", "open_encrypted_with_pass", OPEN_ENCRYPTED_WITH_PASS_HASH)

        private const val OPEN_COMPRESSED_HASH = 3686439335L
        @JvmField
        val openCompressedBind =
            ObjectCalls.getMethodBind("FileAccess", "open_compressed", OPEN_COMPRESSED_HASH)

        private const val GET_OPEN_ERROR_HASH = 166280745L
        @JvmField
        val getOpenErrorBind =
            ObjectCalls.getMethodBind("FileAccess", "get_open_error", GET_OPEN_ERROR_HASH)

        private const val CREATE_TEMP_HASH = 171914364L
        @JvmField
        val createTempBind =
            ObjectCalls.getMethodBind("FileAccess", "create_temp", CREATE_TEMP_HASH)

        private const val GET_FILE_AS_BYTES_HASH = 659035735L
        @JvmField
        val getFileAsBytesBind =
            ObjectCalls.getMethodBind("FileAccess", "get_file_as_bytes", GET_FILE_AS_BYTES_HASH)

        private const val GET_FILE_AS_STRING_HASH = 1703090593L
        @JvmField
        val getFileAsStringBind =
            ObjectCalls.getMethodBind("FileAccess", "get_file_as_string", GET_FILE_AS_STRING_HASH)

        private const val RESIZE_HASH = 844576869L
        @JvmField
        val resizeBind =
            ObjectCalls.getMethodBind("FileAccess", "resize", RESIZE_HASH)

        private const val FLUSH_HASH = 3218959716L
        @JvmField
        val flushBind =
            ObjectCalls.getMethodBind("FileAccess", "flush", FLUSH_HASH)

        private const val GET_PATH_HASH = 201670096L
        @JvmField
        val getPathBind =
            ObjectCalls.getMethodBind("FileAccess", "get_path", GET_PATH_HASH)

        private const val GET_PATH_ABSOLUTE_HASH = 201670096L
        @JvmField
        val getPathAbsoluteBind =
            ObjectCalls.getMethodBind("FileAccess", "get_path_absolute", GET_PATH_ABSOLUTE_HASH)

        private const val IS_OPEN_HASH = 36873697L
        @JvmField
        val isOpenBind =
            ObjectCalls.getMethodBind("FileAccess", "is_open", IS_OPEN_HASH)

        private const val SEEK_HASH = 1286410249L
        @JvmField
        val seekBind =
            ObjectCalls.getMethodBind("FileAccess", "seek", SEEK_HASH)

        private const val SEEK_END_HASH = 1995695955L
        @JvmField
        val seekEndBind =
            ObjectCalls.getMethodBind("FileAccess", "seek_end", SEEK_END_HASH)

        private const val GET_POSITION_HASH = 3905245786L
        @JvmField
        val getPositionBind =
            ObjectCalls.getMethodBind("FileAccess", "get_position", GET_POSITION_HASH)

        private const val GET_LENGTH_HASH = 3905245786L
        @JvmField
        val getLengthBind =
            ObjectCalls.getMethodBind("FileAccess", "get_length", GET_LENGTH_HASH)

        private const val EOF_REACHED_HASH = 36873697L
        @JvmField
        val eofReachedBind =
            ObjectCalls.getMethodBind("FileAccess", "eof_reached", EOF_REACHED_HASH)

        private const val GET_8_HASH = 3905245786L
        @JvmField
        val get8Bind =
            ObjectCalls.getMethodBind("FileAccess", "get_8", GET_8_HASH)

        private const val GET_16_HASH = 3905245786L
        @JvmField
        val get16Bind =
            ObjectCalls.getMethodBind("FileAccess", "get_16", GET_16_HASH)

        private const val GET_32_HASH = 3905245786L
        @JvmField
        val get32Bind =
            ObjectCalls.getMethodBind("FileAccess", "get_32", GET_32_HASH)

        private const val GET_64_HASH = 3905245786L
        @JvmField
        val get64Bind =
            ObjectCalls.getMethodBind("FileAccess", "get_64", GET_64_HASH)

        private const val GET_HALF_HASH = 1740695150L
        @JvmField
        val getHalfBind =
            ObjectCalls.getMethodBind("FileAccess", "get_half", GET_HALF_HASH)

        private const val GET_FLOAT_HASH = 1740695150L
        @JvmField
        val getFloatBind =
            ObjectCalls.getMethodBind("FileAccess", "get_float", GET_FLOAT_HASH)

        private const val GET_DOUBLE_HASH = 1740695150L
        @JvmField
        val getDoubleBind =
            ObjectCalls.getMethodBind("FileAccess", "get_double", GET_DOUBLE_HASH)

        private const val GET_REAL_HASH = 1740695150L
        @JvmField
        val getRealBind =
            ObjectCalls.getMethodBind("FileAccess", "get_real", GET_REAL_HASH)

        private const val GET_BUFFER_HASH = 4131300905L
        @JvmField
        val getBufferBind =
            ObjectCalls.getMethodBind("FileAccess", "get_buffer", GET_BUFFER_HASH)

        private const val GET_LINE_HASH = 201670096L
        @JvmField
        val getLineBind =
            ObjectCalls.getMethodBind("FileAccess", "get_line", GET_LINE_HASH)

        private const val GET_CSV_LINE_HASH = 2358116058L
        @JvmField
        val getCsvLineBind =
            ObjectCalls.getMethodBind("FileAccess", "get_csv_line", GET_CSV_LINE_HASH)

        private const val GET_AS_TEXT_HASH = 201670096L
        @JvmField
        val getAsTextBind =
            ObjectCalls.getMethodBind("FileAccess", "get_as_text", GET_AS_TEXT_HASH)

        private const val GET_MD5_HASH = 1703090593L
        @JvmField
        val getMd5Bind =
            ObjectCalls.getMethodBind("FileAccess", "get_md5", GET_MD5_HASH)

        private const val GET_SHA256_HASH = 1703090593L
        @JvmField
        val getSha256Bind =
            ObjectCalls.getMethodBind("FileAccess", "get_sha256", GET_SHA256_HASH)

        private const val IS_BIG_ENDIAN_HASH = 36873697L
        @JvmField
        val isBigEndianBind =
            ObjectCalls.getMethodBind("FileAccess", "is_big_endian", IS_BIG_ENDIAN_HASH)

        private const val SET_BIG_ENDIAN_HASH = 2586408642L
        @JvmField
        val setBigEndianBind =
            ObjectCalls.getMethodBind("FileAccess", "set_big_endian", SET_BIG_ENDIAN_HASH)

        private const val GET_ERROR_HASH = 3185525595L
        @JvmField
        val getErrorBind =
            ObjectCalls.getMethodBind("FileAccess", "get_error", GET_ERROR_HASH)

        private const val GET_VAR_HASH = 189129690L
        @JvmField
        val getVarBind =
            ObjectCalls.getMethodBind("FileAccess", "get_var", GET_VAR_HASH)

        private const val STORE_8_HASH = 3067735520L
        @JvmField
        val store8Bind =
            ObjectCalls.getMethodBind("FileAccess", "store_8", STORE_8_HASH)

        private const val STORE_16_HASH = 3067735520L
        @JvmField
        val store16Bind =
            ObjectCalls.getMethodBind("FileAccess", "store_16", STORE_16_HASH)

        private const val STORE_32_HASH = 3067735520L
        @JvmField
        val store32Bind =
            ObjectCalls.getMethodBind("FileAccess", "store_32", STORE_32_HASH)

        private const val STORE_64_HASH = 3067735520L
        @JvmField
        val store64Bind =
            ObjectCalls.getMethodBind("FileAccess", "store_64", STORE_64_HASH)

        private const val STORE_HALF_HASH = 330693286L
        @JvmField
        val storeHalfBind =
            ObjectCalls.getMethodBind("FileAccess", "store_half", STORE_HALF_HASH)

        private const val STORE_FLOAT_HASH = 330693286L
        @JvmField
        val storeFloatBind =
            ObjectCalls.getMethodBind("FileAccess", "store_float", STORE_FLOAT_HASH)

        private const val STORE_DOUBLE_HASH = 330693286L
        @JvmField
        val storeDoubleBind =
            ObjectCalls.getMethodBind("FileAccess", "store_double", STORE_DOUBLE_HASH)

        private const val STORE_REAL_HASH = 330693286L
        @JvmField
        val storeRealBind =
            ObjectCalls.getMethodBind("FileAccess", "store_real", STORE_REAL_HASH)

        private const val STORE_BUFFER_HASH = 114037665L
        @JvmField
        val storeBufferBind =
            ObjectCalls.getMethodBind("FileAccess", "store_buffer", STORE_BUFFER_HASH)

        private const val STORE_LINE_HASH = 2323990056L
        @JvmField
        val storeLineBind =
            ObjectCalls.getMethodBind("FileAccess", "store_line", STORE_LINE_HASH)

        private const val STORE_CSV_LINE_HASH = 1611473434L
        @JvmField
        val storeCsvLineBind =
            ObjectCalls.getMethodBind("FileAccess", "store_csv_line", STORE_CSV_LINE_HASH)

        private const val STORE_STRING_HASH = 2323990056L
        @JvmField
        val storeStringBind =
            ObjectCalls.getMethodBind("FileAccess", "store_string", STORE_STRING_HASH)

        private const val STORE_VAR_HASH = 117357437L
        @JvmField
        val storeVarBind =
            ObjectCalls.getMethodBind("FileAccess", "store_var", STORE_VAR_HASH)

        private const val STORE_PASCAL_STRING_HASH = 2323990056L
        @JvmField
        val storePascalStringBind =
            ObjectCalls.getMethodBind("FileAccess", "store_pascal_string", STORE_PASCAL_STRING_HASH)

        private const val GET_PASCAL_STRING_HASH = 2841200299L
        @JvmField
        val getPascalStringBind =
            ObjectCalls.getMethodBind("FileAccess", "get_pascal_string", GET_PASCAL_STRING_HASH)

        private const val CLOSE_HASH = 3218959716L
        @JvmField
        val closeFileBind =
            ObjectCalls.getMethodBind("FileAccess", "close", CLOSE_HASH)

        private const val FILE_EXISTS_HASH = 2323990056L
        @JvmField
        val fileExistsBind =
            ObjectCalls.getMethodBind("FileAccess", "file_exists", FILE_EXISTS_HASH)

        private const val GET_MODIFIED_TIME_HASH = 1597066294L
        @JvmField
        val getModifiedTimeBind =
            ObjectCalls.getMethodBind("FileAccess", "get_modified_time", GET_MODIFIED_TIME_HASH)

        private const val GET_ACCESS_TIME_HASH = 1597066294L
        @JvmField
        val getAccessTimeBind =
            ObjectCalls.getMethodBind("FileAccess", "get_access_time", GET_ACCESS_TIME_HASH)

        private const val GET_SIZE_HASH = 1597066294L
        @JvmField
        val getSizeBind =
            ObjectCalls.getMethodBind("FileAccess", "get_size", GET_SIZE_HASH)

        private const val GET_UNIX_PERMISSIONS_HASH = 524341837L
        @JvmField
        val getUnixPermissionsBind =
            ObjectCalls.getMethodBind("FileAccess", "get_unix_permissions", GET_UNIX_PERMISSIONS_HASH)

        private const val SET_UNIX_PERMISSIONS_HASH = 846038644L
        @JvmField
        val setUnixPermissionsBind =
            ObjectCalls.getMethodBind("FileAccess", "set_unix_permissions", SET_UNIX_PERMISSIONS_HASH)

        private const val GET_HIDDEN_ATTRIBUTE_HASH = 2323990056L
        @JvmField
        val getHiddenAttributeBind =
            ObjectCalls.getMethodBind("FileAccess", "get_hidden_attribute", GET_HIDDEN_ATTRIBUTE_HASH)

        private const val SET_HIDDEN_ATTRIBUTE_HASH = 2892558115L
        @JvmField
        val setHiddenAttributeBind =
            ObjectCalls.getMethodBind("FileAccess", "set_hidden_attribute", SET_HIDDEN_ATTRIBUTE_HASH)

        private const val SET_READ_ONLY_ATTRIBUTE_HASH = 2892558115L
        @JvmField
        val setReadOnlyAttributeBind =
            ObjectCalls.getMethodBind("FileAccess", "set_read_only_attribute", SET_READ_ONLY_ATTRIBUTE_HASH)

        private const val GET_READ_ONLY_ATTRIBUTE_HASH = 2323990056L
        @JvmField
        val getReadOnlyAttributeBind =
            ObjectCalls.getMethodBind("FileAccess", "get_read_only_attribute", GET_READ_ONLY_ATTRIBUTE_HASH)

        private const val GET_EXTENDED_ATTRIBUTE_HASH = 955893464L
        @JvmField
        val getExtendedAttributeBind =
            ObjectCalls.getMethodBind("FileAccess", "get_extended_attribute", GET_EXTENDED_ATTRIBUTE_HASH)

        private const val GET_EXTENDED_ATTRIBUTE_STRING_HASH = 1218461987L
        @JvmField
        val getExtendedAttributeStringBind =
            ObjectCalls.getMethodBind("FileAccess", "get_extended_attribute_string", GET_EXTENDED_ATTRIBUTE_STRING_HASH)

        private const val SET_EXTENDED_ATTRIBUTE_HASH = 2643421469L
        @JvmField
        val setExtendedAttributeBind =
            ObjectCalls.getMethodBind("FileAccess", "set_extended_attribute", SET_EXTENDED_ATTRIBUTE_HASH)

        private const val SET_EXTENDED_ATTRIBUTE_STRING_HASH = 699024349L
        @JvmField
        val setExtendedAttributeStringBind =
            ObjectCalls.getMethodBind("FileAccess", "set_extended_attribute_string", SET_EXTENDED_ATTRIBUTE_STRING_HASH)

        private const val REMOVE_EXTENDED_ATTRIBUTE_HASH = 852856452L
        @JvmField
        val removeExtendedAttributeBind =
            ObjectCalls.getMethodBind("FileAccess", "remove_extended_attribute", REMOVE_EXTENDED_ATTRIBUTE_HASH)

        private const val GET_EXTENDED_ATTRIBUTES_LIST_HASH = 3538744774L
        @JvmField
        val getExtendedAttributesListBind =
            ObjectCalls.getMethodBind("FileAccess", "get_extended_attributes_list", GET_EXTENDED_ATTRIBUTES_LIST_HASH)
    }
}
