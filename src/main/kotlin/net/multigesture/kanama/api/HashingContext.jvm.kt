package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for HashingContext (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP HashingContext waits on: ptrcallNoArgsRetByteArray, ptrcallWithByteArrayArgRetLong
// Index: docs/contributing/ios-shape-gap.md

/**
 * Updates the computation with the given `chunk` of data.
 *
 * Generated from Godot docs: HashingContext.update
 */
fun HashingContext.update(chunk: ByteArray): Long {
    checkOpen()
    return ObjectCalls.ptrcallWithByteArrayArgRetLong(updateBind, handle, chunk)
}

/**
 * Closes the current context, and return the computed hash.
 *
 * Generated from Godot docs: HashingContext.finish
 */
fun HashingContext.finish(): ByteArray {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetByteArray(finishBind, handle)
}

private const val UPDATE_HASH = 680677267L
private val updateBind by lazy {
    ObjectCalls.getMethodBind("HashingContext", "update", UPDATE_HASH)
}

private const val FINISH_HASH = 2115431945L
private val finishBind by lazy {
    ObjectCalls.getMethodBind("HashingContext", "finish", FINISH_HASH)
}
