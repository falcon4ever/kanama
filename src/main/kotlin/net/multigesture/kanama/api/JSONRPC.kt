package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A helper to handle dictionaries which look like JSONRPC documents.
 *
 * Generated from Godot docs: JSONRPC
 */
open class JSONRPC(handle: MemorySegment) : GodotObject(handle) {
    /**
     * Registers a callback for the given method name. - `name`: The name that clients can use to
     * access the callback. - `callback`: The callback which will handle the specified method.
     *
     * Generated from Godot docs: JSONRPC.set_method
     */
    fun setMethod(name: String, callback: GodotCallable) {
        ObjectCalls.ptrcallWithStringCallableArgs(setMethodBind, handle, name, callback.target.handle, callback.method)
    }

    /**
     * Given a Dictionary which takes the form of a JSON-RPC request: unpack the request and run it.
     * Methods are resolved by looking at the field called "method" and looking for an equivalently
     * named function in the JSONRPC object. If one is found that method is called. To add new
     * supported methods extend the JSONRPC class and call `process_action` on your subclass. `action`:
     * The action to be run, as a Dictionary in the form of a JSON-RPC request or notification.
     *
     * Generated from Godot docs: JSONRPC.process_action
     */
    fun processAction(action: Any?, recurse: Boolean = false): Any? {
        return ObjectCalls.ptrcallWithVariantAndBoolArgRetVariantScalar(processActionBind, handle, action, recurse)
    }

    fun processString(action: String): String {
        return ObjectCalls.ptrcallWithStringArgRetString(processStringBind, handle, action)
    }

    /**
     * Returns a dictionary in the form of a JSON-RPC request. Requests are sent to a server with the
     * expectation of a response. The ID field is used for the server to specify which exact request it
     * is responding to. - `method`: Name of the method being called. - `params`: An array or
     * dictionary of parameters being passed to the method. - `id`: Uniquely identifies this request.
     * The server is expected to send a response with the same ID.
     *
     * Generated from Godot docs: JSONRPC.make_request
     */
    fun makeRequest(method: String, params: Any?, id: Any?): Map<String, Any?> {
        return ObjectCalls.ptrcallWithStringAndTwoVariantArgsRetDictionary(makeRequestBind, handle, method, params, id)
    }

    /**
     * When a server has received and processed a request, it is expected to send a response. If you
     * did not want a response then you need to have sent a Notification instead. - `result`: The
     * return value of the function which was called. - `id`: The ID of the request this response is
     * targeted to.
     *
     * Generated from Godot docs: JSONRPC.make_response
     */
    fun makeResponse(result: Any?, id: Any?): Map<String, Any?> {
        return ObjectCalls.ptrcallWithTwoVariantArgsRetDictionary(makeResponseBind, handle, result, id)
    }

    /**
     * Returns a dictionary in the form of a JSON-RPC notification. Notifications are one-shot messages
     * which do not expect a response. - `method`: Name of the method being called. - `params`: An
     * array or dictionary of parameters being passed to the method.
     *
     * Generated from Godot docs: JSONRPC.make_notification
     */
    fun makeNotification(method: String, params: Any?): Map<String, Any?> {
        return ObjectCalls.ptrcallWithStringAndVariantArgRetDictionary(makeNotificationBind, handle, method, params)
    }

    /**
     * Creates a response which indicates a previous reply has failed in some way. - `code`: The error
     * code corresponding to what kind of error this is. See the `ErrorCode` constants. - `message`: A
     * custom message about this error. - `id`: The request this error is a response to.
     *
     * Generated from Godot docs: JSONRPC.make_response_error
     */
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
