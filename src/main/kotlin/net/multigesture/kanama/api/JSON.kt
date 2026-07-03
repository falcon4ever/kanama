package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Helper class for creating and parsing JSON data.
 *
 * Generated from Godot docs: JSON
 */
class JSON(handle: MemorySegment) : Resource(handle) {
    var data: Any?
        @JvmName("dataProperty")
        get() = getData()
        @JvmName("setDataProperty")
        set(value) = setData(value)

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
        return ObjectCalls.ptrcallWithStringAndBoolArgRetLong(parseBind, handle, jsonText, keepText)
    }

    /**
     * Contains the parsed JSON data in `Variant` form.
     *
     * Generated from Godot docs: JSON.get_data
     */
    fun getData(): Any? {
        return ObjectCalls.ptrcallNoArgsRetVariantScalar(getDataBind, handle)
    }

    /**
     * Contains the parsed JSON data in `Variant` form.
     *
     * Generated from Godot docs: JSON.set_data
     */
    fun setData(data: Any?) {
        ObjectCalls.ptrcallWithVariantArg(setDataBind, handle, data)
    }

    /**
     * Return the text parsed by `parse` (requires passing `keep_text` to `parse`).
     *
     * Generated from Godot docs: JSON.get_parsed_text
     */
    fun getParsedText(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getParsedTextBind, handle)
    }

    /**
     * Returns `0` if the last call to `parse` was successful, or the line number where the parse
     * failed.
     *
     * Generated from Godot docs: JSON.get_error_line
     */
    fun getErrorLine(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getErrorLineBind, handle)
    }

    /**
     * Returns an empty string if the last call to `parse` was successful, or the error message if it
     * failed.
     *
     * Generated from Godot docs: JSON.get_error_message
     */
    fun getErrorMessage(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getErrorMessageBind, handle)
    }

    companion object {
        /**
         * Converts a `Variant` var to JSON text and returns the result. Useful for serializing data to
         * store or send over the network. Note: The JSON specification does not define integer or float
         * types, but only a number type. Therefore, converting a Variant to JSON text will convert all
         * numerical values to `float` types. Note: If `full_precision` is `true`, when stringifying
         * floats, the unreliable digits are stringified in addition to the reliable digits to guarantee
         * exact decoding. The `indent` parameter controls if and how something is indented; its contents
         * will be used where there should be an indent in the output. Even spaces like `" "` will work.
         * `\t` and `\n` can also be used for a tab indent, or to make a newline for each indent
         * respectively. Warning: Non-finite numbers are not supported in JSON. Any occurrences of
         * `@GDScript.INF` will be replaced with `1e99999`, and negative `@GDScript.INF` will be replaced
         * with `-1e99999`, but they will be interpreted correctly as infinity by most JSON parsers.
         * `@GDScript.NAN` will be replaced with `null`, and it will not be interpreted as NaN in JSON
         * parsers. If you expect non-finite numbers, consider passing your data through `from_native`
         * first. Example output:
         *
         * Generated from Godot docs: JSON.stringify
         */
        fun stringify(data: Any?, indent: String = "", sortKeys: Boolean = true, fullPrecision: Boolean = false): String {
            return ObjectCalls.ptrcallWithVariantStringTwoBoolArgsRetString(stringifyBind, MemorySegment.NULL, data, indent, sortKeys, fullPrecision)
        }

        /**
         * Attempts to parse the `json_string` provided and returns the parsed data. Returns `null` if
         * parse failed.
         *
         * Generated from Godot docs: JSON.parse_string
         */
        fun parseString(jsonString: String): Any? {
            return ObjectCalls.ptrcallWithStringArgRetVariantScalar(parseStringBind, MemorySegment.NULL, jsonString)
        }

        /**
         * Converts a native engine type to a JSON-compliant value. By default, objects are ignored for
         * security reasons, unless `full_objects` is `true`. You can convert a native value to a JSON
         * string like this:
         *
         * Generated from Godot docs: JSON.from_native
         */
        fun fromNative(variant: Any?, fullObjects: Boolean = false): Any? {
            return ObjectCalls.ptrcallWithVariantAndBoolArgRetVariantScalar(fromNativeBind, MemorySegment.NULL, variant, fullObjects)
        }

        /**
         * Converts a JSON-compliant value that was created with `from_native` back to native engine types.
         * By default, objects are ignored for security reasons, unless `allow_objects` is `true`. You can
         * convert a JSON string back to a native value like this:
         *
         * Generated from Godot docs: JSON.to_native
         */
        fun toNative(json: Any?, allowObjects: Boolean = false): Any? {
            return ObjectCalls.ptrcallWithVariantAndBoolArgRetVariantScalar(toNativeBind, MemorySegment.NULL, json, allowObjects)
        }

        @JvmStatic
        fun fromHandle(handle: MemorySegment): JSON? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): JSON? =
            if (handle.address() == 0L) null else JSON(handle)

        private const val STRINGIFY_HASH = 462733549L
        private val stringifyBind by lazy {
            ObjectCalls.getMethodBind("JSON", "stringify", STRINGIFY_HASH)
        }

        private const val PARSE_STRING_HASH = 309047738L
        private val parseStringBind by lazy {
            ObjectCalls.getMethodBind("JSON", "parse_string", PARSE_STRING_HASH)
        }

        private const val PARSE_HASH = 885841341L
        private val parseBind by lazy {
            ObjectCalls.getMethodBind("JSON", "parse", PARSE_HASH)
        }

        private const val GET_DATA_HASH = 1214101251L
        private val getDataBind by lazy {
            ObjectCalls.getMethodBind("JSON", "get_data", GET_DATA_HASH)
        }

        private const val SET_DATA_HASH = 1114965689L
        private val setDataBind by lazy {
            ObjectCalls.getMethodBind("JSON", "set_data", SET_DATA_HASH)
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

        private const val FROM_NATIVE_HASH = 2963479484L
        private val fromNativeBind by lazy {
            ObjectCalls.getMethodBind("JSON", "from_native", FROM_NATIVE_HASH)
        }

        private const val TO_NATIVE_HASH = 2963479484L
        private val toNativeBind by lazy {
            ObjectCalls.getMethodBind("JSON", "to_native", TO_NATIVE_HASH)
        }
    }
}
