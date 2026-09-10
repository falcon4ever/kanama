package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for XMLParser (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP XMLParser waits on: ptrcallWithByteArrayArgRetLong
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Opens an XML raw `buffer` for parsing. This method returns an error code.
 *
 * Generated from Godot docs: XMLParser.open_buffer
 */
fun XMLParser.openBuffer(buffer: ByteArray): Long {
    checkOpen()
    return ObjectCalls.ptrcallWithByteArrayArgRetLong(openBufferBind, handle, buffer)
}

private const val OPEN_BUFFER_HASH = 680677267L
private val openBufferBind by lazy {
    ObjectCalls.getMethodBind("XMLParser", "open_buffer", OPEN_BUFFER_HASH)
}
