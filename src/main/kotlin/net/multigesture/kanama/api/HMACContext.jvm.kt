package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for HMACContext (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP HMACContext waits on: ptrcallNoArgsRetByteArray, ptrcallWithByteArrayArgRetLong,
//   ptrcallWithLongAndByteArrayArgRetLong
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Initializes the HMACContext. This method cannot be called again on the same HMACContext until
 * `finish` has been called.
 *
 * Generated from Godot docs: HMACContext.start
 */
fun HMACContext.start(hashType: Long, key: ByteArray): Long {
    checkOpen()
    return ObjectCalls.ptrcallWithLongAndByteArrayArgRetLong(startBind, handle, hashType, key)
}

/**
 * Updates the message to be HMACed. This can be called multiple times before `finish` is called to
 * append `data` to the message, but cannot be called until `start` has been called.
 *
 * Generated from Godot docs: HMACContext.update
 */
fun HMACContext.update(data: ByteArray): Long {
    checkOpen()
    return ObjectCalls.ptrcallWithByteArrayArgRetLong(updateBind, handle, data)
}

/**
 * Returns the resulting HMAC. If the HMAC failed, an empty `PackedByteArray` is returned.
 *
 * Generated from Godot docs: HMACContext.finish
 */
fun HMACContext.finish(): ByteArray {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetByteArray(finishBind, handle)
}

private const val START_HASH = 3537364598L
private val startBind by lazy {
    ObjectCalls.getMethodBind("HMACContext", "start", START_HASH)
}

private const val UPDATE_HASH = 680677267L
private val updateBind by lazy {
    ObjectCalls.getMethodBind("HMACContext", "update", UPDATE_HASH)
}

private const val FINISH_HASH = 2115431945L
private val finishBind by lazy {
    ObjectCalls.getMethodBind("HMACContext", "finish", FINISH_HASH)
}
