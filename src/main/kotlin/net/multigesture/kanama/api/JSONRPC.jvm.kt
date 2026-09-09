package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for JSONRPC (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP JSONRPC waits on: ptrcallWithIntStringVariantArgsRetDictionary,
//   ptrcallWithStringAndTwoVariantArgsRetDictionary, ptrcallWithStringAndVariantArgRetDictionary,
//   ptrcallWithTwoVariantArgsRetDictionary, ptrcallWithVariantAndBoolArgRetVariantScalar
// Index: docs/contributing/ios-shape-gap.md

/**
 * Given a Dictionary which takes the form of a JSON-RPC request: unpack the request and run it.
 * Methods are resolved by looking at the field called "method" and looking for an equivalently
 * named function in the JSONRPC object. If one is found that method is called. To add new
 * supported methods extend the JSONRPC class and call `process_action` on your subclass. `action`:
 * The action to be run, as a Dictionary in the form of a JSON-RPC request or notification.
 *
 * Generated from Godot docs: JSONRPC.process_action
 */
fun JSONRPC.processAction(action: Any?, recurse: Boolean = false): Any? {
    return ObjectCalls.ptrcallWithVariantAndBoolArgRetVariantScalar(processActionBind, handle, action, recurse)
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
fun JSONRPC.makeRequest(method: String, params: Any?, id: Any?): Map<String, Any?> {
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
fun JSONRPC.makeResponse(result: Any?, id: Any?): Map<String, Any?> {
    return ObjectCalls.ptrcallWithTwoVariantArgsRetDictionary(makeResponseBind, handle, result, id)
}

/**
 * Returns a dictionary in the form of a JSON-RPC notification. Notifications are one-shot messages
 * which do not expect a response. - `method`: Name of the method being called. - `params`: An
 * array or dictionary of parameters being passed to the method.
 *
 * Generated from Godot docs: JSONRPC.make_notification
 */
fun JSONRPC.makeNotification(method: String, params: Any?): Map<String, Any?> {
    return ObjectCalls.ptrcallWithStringAndVariantArgRetDictionary(makeNotificationBind, handle, method, params)
}

/**
 * Creates a response which indicates a previous reply has failed in some way. - `code`: The error
 * code corresponding to what kind of error this is. See the `ErrorCode` constants. - `message`: A
 * custom message about this error. - `id`: The request this error is a response to.
 *
 * Generated from Godot docs: JSONRPC.make_response_error
 */
fun JSONRPC.makeResponseError(code: Int, message: String, id: Any? = null): Map<String, Any?> {
    return ObjectCalls.ptrcallWithIntStringVariantArgsRetDictionary(makeResponseErrorBind, handle, code, message, id)
}

private const val PROCESS_ACTION_HASH = 2963479484L
private val processActionBind by lazy {
    ObjectCalls.getMethodBind("JSONRPC", "process_action", PROCESS_ACTION_HASH)
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
