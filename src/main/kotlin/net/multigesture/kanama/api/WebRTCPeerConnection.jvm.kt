package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for WebRTCPeerConnection (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP WebRTCPeerConnection waits on: ptrcallWithDictionaryArgRetLong,
//   ptrcallWithStringAndDictionaryArgRetObject
// Index: docs/reference/generated/ios-shape-gap.md

fun WebRTCPeerConnection.initialize(configuration: Map<String, Any?> = emptyMap()): Long {
    checkOpen()
    return ObjectCalls.ptrcallWithDictionaryArgRetLong(initializeBind, handle, configuration)
}

fun WebRTCPeerConnection.createDataChannel(label: String, options: Map<String, Any?> = emptyMap()): WebRTCDataChannel? {
    checkOpen()
    return WebRTCDataChannel.wrap(ObjectCalls.ptrcallWithStringAndDictionaryArgRetObject(createDataChannelBind, handle, label, options))
}

private const val INITIALIZE_HASH = 2625064318L
private val initializeBind by lazy {
    ObjectCalls.getMethodBind("WebRTCPeerConnection", "initialize", INITIALIZE_HASH)
}

private const val CREATE_DATA_CHANNEL_HASH = 1288557393L
private val createDataChannelBind by lazy {
    ObjectCalls.getMethodBind("WebRTCPeerConnection", "create_data_channel", CREATE_DATA_CHANNEL_HASH)
}
