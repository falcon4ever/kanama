package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Helper class for creating and parsing JSON data.
 *
 * Generated from Godot docs: JSON
 */
class JSON(handle: MemorySegment) : Resource(handle) {
    val data: Any?
        @JvmName("dataProperty")
        get() = getData()

    /**
     * Attempts to parse the `json_text` provided. Returns an `Error`. If the parse was successful, it
     * returns `OK` and the result can be retrieved using `data`. If unsuccessful, use `get_error_line`
     * and `get_error_message` to identify the source of the failure. Non-static variant of
     * `parse_string`, if you want custom error handling. The optional `keep_text` argument instructs
     * the parser to keep a copy of the original text. This text can be obtained later by using the
     * `get_parsed_text` function and is used when saving the resource (instead of generating new text
     * from `data`).
     *
     * Generated from Godot docs: JSON.parse
     */
    fun parse(jsonText: String, keepText: Boolean = false): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringAndBoolArgRetLong(parseBind, handle, jsonText, keepText)
    }

    /**
     * Contains the parsed JSON data in `Variant` form.
     *
     * Generated from Godot docs: JSON.get_data
     */
    fun getData(): Any? {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVariantScalar(getDataBind, handle)
    }

    /**
     * Return the text parsed by `parse` (requires passing `keep_text` to `parse`).
     *
     * Generated from Godot docs: JSON.get_parsed_text
     */
    fun getParsedText(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getParsedTextBind, handle)
    }

    /**
     * Returns `0` if the last call to `parse` was successful, or the line number where the parse
     * failed.
     *
     * Generated from Godot docs: JSON.get_error_line
     */
    fun getErrorLine(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getErrorLineBind, handle)
    }

    /**
     * Returns an empty string if the last call to `parse` was successful, or the error message if it
     * failed.
     *
     * Generated from Godot docs: JSON.get_error_message
     */
    fun getErrorMessage(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getErrorMessageBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): JSON? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): JSON? =
            if (handle.address() == 0L) null else JSON(handle)

        private const val PARSE_HASH = 885841341L
        private val parseBind by lazy {
            ObjectCalls.getMethodBind("JSON", "parse", PARSE_HASH)
        }

        private const val GET_DATA_HASH = 1214101251L
        private val getDataBind by lazy {
            ObjectCalls.getMethodBind("JSON", "get_data", GET_DATA_HASH)
        }

        private const val GET_PARSED_TEXT_HASH = 201670096L
        private val getParsedTextBind by lazy {
            ObjectCalls.getMethodBind("JSON", "get_parsed_text", GET_PARSED_TEXT_HASH)
        }

        private const val GET_ERROR_LINE_HASH = 3905245786L
        private val getErrorLineBind by lazy {
            ObjectCalls.getMethodBind("JSON", "get_error_line", GET_ERROR_LINE_HASH)
        }

        private const val GET_ERROR_MESSAGE_HASH = 201670096L
        private val getErrorMessageBind by lazy {
            ObjectCalls.getMethodBind("JSON", "get_error_message", GET_ERROR_MESSAGE_HASH)
        }
    }
}
