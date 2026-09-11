package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for GLTFObjectModelProperty (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP GLTFObjectModelProperty waits on: ptrcallWithPackedStringListListArg
// Index: docs/reference/generated/ios-shape-gap.md

fun GLTFObjectModelProperty.setJsonPointers(jsonPointers: List<List<String>>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedStringListListArg(setJsonPointersBind, handle, jsonPointers)
}

private const val SET_JSON_POINTERS_HASH = 381264803L
private val setJsonPointersBind by lazy {
    ObjectCalls.getMethodBind("GLTFObjectModelProperty", "set_json_pointers", SET_JSON_POINTERS_HASH)
}
