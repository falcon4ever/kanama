package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for JavaScriptBridge (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP JavaScriptBridge waits on: ptrcallWithByteArrayTwoStringArgs,
//   ptrcallWithObjectArgRetByteArray
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns a copy of `javascript_buffer`'s contents as a `PackedByteArray`. See also
 * `is_js_buffer`.
 *
 * Generated from Godot docs: JavaScriptBridge.js_buffer_to_packed_byte_array
 */
fun JavaScriptBridge.jsBufferToPackedByteArray(javascriptBuffer: JavaScriptObject?): ByteArray {
    return ObjectCalls.ptrcallWithObjectArgRetByteArray(jsBufferToPackedByteArrayBind, javaScriptBridgeSingleton, javascriptBuffer?.requireOpenHandle() ?: MemorySegment.NULL)
}

/**
 * Prompts the user to download a file containing the specified `buffer`. The file will have the
 * given `name` and `mime` type. Note: The browser may override the MIME type
 * (https://en.wikipedia.org/wiki/Media_type) provided based on the file `name`'s extension. Note:
 * Browsers might block the download if `download_buffer` is not being called from a user
 * interaction (e.g. button click). Note: Browsers might ask the user for permission or block the
 * download if multiple download requests are made in a quick succession.
 *
 * Generated from Godot docs: JavaScriptBridge.download_buffer
 */
fun JavaScriptBridge.downloadBuffer(buffer: ByteArray, name: String, mime: String = "application/octet-stream") {
    ObjectCalls.ptrcallWithByteArrayTwoStringArgs(downloadBufferBind, javaScriptBridgeSingleton, buffer, name, mime)
}

private val javaScriptBridgeSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("JavaScriptBridge")
}

private const val JS_BUFFER_TO_PACKED_BYTE_ARRAY_HASH = 64409880L
private val jsBufferToPackedByteArrayBind by lazy {
    ObjectCalls.getMethodBind("JavaScriptBridge", "js_buffer_to_packed_byte_array", JS_BUFFER_TO_PACKED_BYTE_ARRAY_HASH)
}

private const val DOWNLOAD_BUFFER_HASH = 3352272093L
private val downloadBufferBind by lazy {
    ObjectCalls.getMethodBind("JavaScriptBridge", "download_buffer", DOWNLOAD_BUFFER_HASH)
}
