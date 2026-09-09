package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: JSON
 */
class JSON(handle: MemorySegment) : Resource(handle) {
    val data: Any?
        @JvmName("dataProperty")
        get() = getData()

    fun parse(jsonText: String, keepText: Boolean = false): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringAndBoolArgRetLong(parseBind, handle, jsonText, keepText)
    }

    fun getData(): Any? {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVariantScalar(getDataBind, handle)
    }

    fun getParsedText(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getParsedTextBind, handle)
    }

    fun getErrorLine(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getErrorLineBind, handle)
    }

    fun getErrorMessage(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getErrorMessageBind, handle)
    }

    companion object {
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
