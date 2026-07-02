package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A helper to handle dictionaries which look like JSONRPC documents.
 *
 * Generated from Godot docs: JSONRPC
 */
open class JSONRPC(handle: MemorySegment) : GodotObject(handle) {
    fun setMethod(name: String, callback: GodotCallable) {
        ObjectCalls.ptrcallWithStringCallableArgs(setMethodBind, handle, name, callback.target.handle, callback.method)
    }

    fun processAction(action: Any?, recurse: Boolean = false): Any? {
        return ObjectCalls.ptrcallWithVariantAndBoolArgRetVariantScalar(processActionBind, handle, action, recurse)
    }

    fun processString(action: String): String {
        return ObjectCalls.ptrcallWithStringArgRetString(processStringBind, handle, action)
    }

    fun makeRequest(method: String, params: Any?, id: Any?): Map<String, Any?> {
        return ObjectCalls.ptrcallWithStringAndTwoVariantArgsRetDictionary(makeRequestBind, handle, method, params, id)
    }

    fun makeResponse(result: Any?, id: Any?): Map<String, Any?> {
        return ObjectCalls.ptrcallWithTwoVariantArgsRetDictionary(makeResponseBind, handle, result, id)
    }

    fun makeNotification(method: String, params: Any?): Map<String, Any?> {
        return ObjectCalls.ptrcallWithStringAndVariantArgRetDictionary(makeNotificationBind, handle, method, params)
    }

    fun makeResponseError(code: Int, message: String, id: Any? = null): Map<String, Any?> {
        return ObjectCalls.ptrcallWithIntStringVariantArgsRetDictionary(makeResponseErrorBind, handle, code, message, id)
    }

    companion object {
        const val PARSE_ERROR: Long = -32700L
        const val INVALID_REQUEST: Long = -32600L
        const val METHOD_NOT_FOUND: Long = -32601L
        const val INVALID_PARAMS: Long = -32602L
        const val INTERNAL_ERROR: Long = -32603L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): JSONRPC? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): JSONRPC? =
            if (handle.address() == 0L) null else JSONRPC(handle)

        private const val SET_METHOD_HASH = 2137474292L
        private val setMethodBind by lazy {
            ObjectCalls.getMethodBind("JSONRPC", "set_method", SET_METHOD_HASH)
        }

        private const val PROCESS_ACTION_HASH = 2963479484L
        private val processActionBind by lazy {
            ObjectCalls.getMethodBind("JSONRPC", "process_action", PROCESS_ACTION_HASH)
        }

        private const val PROCESS_STRING_HASH = 1703090593L
        private val processStringBind by lazy {
            ObjectCalls.getMethodBind("JSONRPC", "process_string", PROCESS_STRING_HASH)
        }

        private const val MAKE_REQUEST_HASH = 3423508980L
        private val makeRequestBind by lazy {
            ObjectCalls.getMethodBind("JSONRPC", "make_request", MAKE_REQUEST_HASH)
        }

        private const val MAKE_RESPONSE_HASH = 5053918L
        private val makeResponseBind by lazy {
            ObjectCalls.getMethodBind("JSONRPC", "make_response", MAKE_RESPONSE_HASH)
        }

        private const val MAKE_NOTIFICATION_HASH = 2949127017L
        private val makeNotificationBind by lazy {
            ObjectCalls.getMethodBind("JSONRPC", "make_notification", MAKE_NOTIFICATION_HASH)
        }

        private const val MAKE_RESPONSE_ERROR_HASH = 928596297L
        private val makeResponseErrorBind by lazy {
            ObjectCalls.getMethodBind("JSONRPC", "make_response_error", MAKE_RESPONSE_ERROR_HASH)
        }
    }
}
