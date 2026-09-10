package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for AESContext (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP AESContext waits on: ptrcallWithByteArrayArgRetByteArray,
//   ptrcallWithLongAndTwoByteArrayArgsRetLong
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Start the AES context in the given `mode`. A `key` of either 16 or 32 bytes must always be
 * provided, while an `iv` (initialization vector) of exactly 16 bytes, is only needed when `mode`
 * is either `MODE_CBC_ENCRYPT` or `MODE_CBC_DECRYPT`.
 *
 * Generated from Godot docs: AESContext.start
 */
fun AESContext.start(mode: Long, key: ByteArray, iv: ByteArray): Long {
    checkOpen()
    return ObjectCalls.ptrcallWithLongAndTwoByteArrayArgsRetLong(startBind, handle, mode, key, iv)
}

/**
 * Run the desired operation for this AES context. Will return a `PackedByteArray` containing the
 * result of encrypting (or decrypting) the given `src`. See `start` for mode of operation. Note:
 * The size of `src` must be a multiple of 16. Apply some padding if needed.
 *
 * Generated from Godot docs: AESContext.update
 */
fun AESContext.update(src: ByteArray): ByteArray {
    checkOpen()
    return ObjectCalls.ptrcallWithByteArrayArgRetByteArray(updateBind, handle, src)
}

private const val START_HASH = 3122411423L
private val startBind by lazy {
    ObjectCalls.getMethodBind("AESContext", "start", START_HASH)
}

private const val UPDATE_HASH = 527836100L
private val updateBind by lazy {
    ObjectCalls.getMethodBind("AESContext", "update", UPDATE_HASH)
}
