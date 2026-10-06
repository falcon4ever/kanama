package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A helper to handle dictionaries which look like JSONRPC documents.
 *
 * Generated from Godot docs: JSONRPC
 */
open class JSONRPC(handle: GodotHandle) : GodotObject(handle) {
    /**
     * Registers a callback for the given method name. - `name`: The name that clients can use to
     * access the callback. - `callback`: The callback which will handle the specified method.
     *
     * Generated from Godot docs: JSONRPC.set_method
     */
    fun setMethod(name: String, callback: GodotCallable) {
        ObjectCalls.ptrcallWithStringCallableArgs(Binds.setMethodBind, segment, name, callback.target.segment, callback.method)
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
        return ObjectCalls.ptrcallWithVariantAndBoolArgRetVariantScalar(Binds.processActionBind, segment, action, recurse)
    }

    fun processString(action: String): String {
        return ObjectCalls.ptrcallWithStringArgRetString(Binds.processStringBind, segment, action)
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
        return ObjectCalls.ptrcallWithStringAndTwoVariantArgsRetDictionary(Binds.makeRequestBind, segment, method, params, id)
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
        return ObjectCalls.ptrcallWithTwoVariantArgsRetDictionary(Binds.makeResponseBind, segment, result, id)
    }

    /**
     * Returns a dictionary in the form of a JSON-RPC notification. Notifications are one-shot messages
     * which do not expect a response. - `method`: Name of the method being called. - `params`: An
     * array or dictionary of parameters being passed to the method.
     *
     * Generated from Godot docs: JSONRPC.make_notification
     */
    fun makeNotification(method: String, params: Any?): Map<String, Any?> {
        return ObjectCalls.ptrcallWithStringAndVariantArgRetDictionary(Binds.makeNotificationBind, segment, method, params)
    }

    /**
     * Creates a response which indicates a previous reply has failed in some way. - `code`: The error
     * code corresponding to what kind of error this is. See the `ErrorCode` constants. - `message`: A
     * custom message about this error. - `id`: The request this error is a response to.
     *
     * Generated from Godot docs: JSONRPC.make_response_error
     */
    fun makeResponseError(code: Int, message: String, id: Any? = null): Map<String, Any?> {
        return ObjectCalls.ptrcallWithIntStringVariantArgsRetDictionary(Binds.makeResponseErrorBind, segment, code, message, id)
    }

    /**
     * Godot's `JSONRPC.ErrorCode` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`JSONRPC.ErrorCode.<NAME>`).
     *
     * Generated from Godot docs: JSONRPC.ErrorCode
     */
    @JvmInline
    value class ErrorCode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The request could not be parsed as it was not valid by JSON standard (`JSON.parse` failed).
             *
             * Generated from Godot docs: JSONRPC.PARSE_ERROR
             */
            val PARSE_ERROR: ErrorCode get() = ErrorCode(-32700L)
            /**
             * A method call was requested but the request's format is not valid.
             *
             * Generated from Godot docs: JSONRPC.INVALID_REQUEST
             */
            val INVALID_REQUEST: ErrorCode get() = ErrorCode(-32600L)
            /**
             * A method call was requested but no function of that name existed in the JSONRPC subclass.
             *
             * Generated from Godot docs: JSONRPC.METHOD_NOT_FOUND
             */
            val METHOD_NOT_FOUND: ErrorCode get() = ErrorCode(-32601L)
            /**
             * A method call was requested but the given method parameters are not valid. Not used by the
             * built-in JSONRPC.
             *
             * Generated from Godot docs: JSONRPC.INVALID_PARAMS
             */
            val INVALID_PARAMS: ErrorCode get() = ErrorCode(-32602L)
            /**
             * An internal error occurred while processing the request. Not used by the built-in JSONRPC.
             *
             * Generated from Godot docs: JSONRPC.INTERNAL_ERROR
             */
            val INTERNAL_ERROR: ErrorCode get() = ErrorCode(-32603L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): JSONRPC? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): JSONRPC? =
            if (handle.address() == 0L) null else JSONRPC(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_METHOD_HASH = 2137474292L
        @JvmField
        val setMethodBind =
            ObjectCalls.getMethodBind("JSONRPC", "set_method", SET_METHOD_HASH)

        private const val PROCESS_ACTION_HASH = 2963479484L
        @JvmField
        val processActionBind =
            ObjectCalls.getMethodBind("JSONRPC", "process_action", PROCESS_ACTION_HASH)

        private const val PROCESS_STRING_HASH = 1703090593L
        @JvmField
        val processStringBind =
            ObjectCalls.getMethodBind("JSONRPC", "process_string", PROCESS_STRING_HASH)

        private const val MAKE_REQUEST_HASH = 3423508980L
        @JvmField
        val makeRequestBind =
            ObjectCalls.getMethodBind("JSONRPC", "make_request", MAKE_REQUEST_HASH)

        private const val MAKE_RESPONSE_HASH = 5053918L
        @JvmField
        val makeResponseBind =
            ObjectCalls.getMethodBind("JSONRPC", "make_response", MAKE_RESPONSE_HASH)

        private const val MAKE_NOTIFICATION_HASH = 2949127017L
        @JvmField
        val makeNotificationBind =
            ObjectCalls.getMethodBind("JSONRPC", "make_notification", MAKE_NOTIFICATION_HASH)

        private const val MAKE_RESPONSE_ERROR_HASH = 928596297L
        @JvmField
        val makeResponseErrorBind =
            ObjectCalls.getMethodBind("JSONRPC", "make_response_error", MAKE_RESPONSE_ERROR_HASH)
    }
}
