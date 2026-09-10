package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for GLTFDocument (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP GLTFDocument waits on: ptrcallWithPackedByteArrayStringObjectUInt32ArgsRetLong
// Index: docs/reference/generated/ios-shape-gap.md

fun GLTFDocument.appendFromBuffer(bytes: ByteArray, basePath: String, state: GLTFState?, flags: Long = 0L): Long {
    checkOpen()
    return ObjectCalls.ptrcallWithPackedByteArrayStringObjectUInt32ArgsRetLong(appendFromBufferBind, handle, bytes, basePath, state?.requireOpenHandle() ?: MemorySegment.NULL, flags)
}

private const val APPEND_FROM_BUFFER_HASH = 1616081266L
private val appendFromBufferBind by lazy {
    ObjectCalls.getMethodBind("GLTFDocument", "append_from_buffer", APPEND_FROM_BUFFER_HASH)
}
