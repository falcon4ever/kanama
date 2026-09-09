package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for ZIPReader (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP ZIPReader waits on: ptrcallWithStringAndBoolArgRetByteArray
// Index: docs/reference/generated/ios-shape-gap.md

fun ZIPReader.readFile(path: String, caseSensitive: Boolean = true): ByteArray {
    checkOpen()
    return ObjectCalls.ptrcallWithStringAndBoolArgRetByteArray(readFileBind, handle, path, caseSensitive)
}

private const val READ_FILE_HASH = 740857591L
private val readFileBind by lazy {
    ObjectCalls.getMethodBind("ZIPReader", "read_file", READ_FILE_HASH)
}
