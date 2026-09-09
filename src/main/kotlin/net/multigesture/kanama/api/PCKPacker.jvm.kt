package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for PCKPacker (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP PCKPacker waits on: ptrcallWithStringByteArrayAndBoolArgRetLong
// Index: docs/contributing/ios-shape-gap.md

/**
 * Adds the `data` to the current PCK package at the `target_path` internal path. The `res://`
 * prefix for `target_path` is optional and stripped internally. File content is immediately
 * written to the PCK.
 *
 * Generated from Godot docs: PCKPacker.add_file_from_buffer
 */
fun PCKPacker.addFileFromBuffer(targetPath: String, data: ByteArray, encrypt: Boolean = false): Long {
    checkOpen()
    return ObjectCalls.ptrcallWithStringByteArrayAndBoolArgRetLong(addFileFromBufferBind, handle, targetPath, data, encrypt)
}

private const val ADD_FILE_FROM_BUFFER_HASH = 1131482346L
private val addFileFromBufferBind by lazy {
    ObjectCalls.getMethodBind("PCKPacker", "add_file_from_buffer", ADD_FILE_FROM_BUFFER_HASH)
}
