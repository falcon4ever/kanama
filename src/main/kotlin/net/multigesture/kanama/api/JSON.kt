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

    fun getData(): Any? {
        return ObjectCalls.ptrcallNoArgsRetVariantScalar(getDataBind, handle)
    }

    fun setData(data: Any?) {
        ObjectCalls.ptrcallWithVariantArg(setDataBind, handle, data)
    }

    fun getParsedText(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getParsedTextBind, handle)
    }

    fun getErrorLine(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getErrorLineBind, handle)
    }

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

        fun parseString(jsonString: String): Any? {
            return ObjectCalls.ptrcallWithStringArgRetVariantScalar(parseStringBind, MemorySegment.NULL, jsonString)
        }

        fun fromNative(variant: Any?, fullObjects: Boolean = false): Any? {
            return ObjectCalls.ptrcallWithVariantAndBoolArgRetVariantScalar(fromNativeBind, MemorySegment.NULL, variant, fullObjects)
        }

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
