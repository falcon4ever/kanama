package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for Skin (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP Skin waits on: ptrcallWithIntArgRetStringName
// Index: docs/contributing/ios-shape-gap.md

fun Skin.getBindName(bindIndex: Int): String {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetStringName(getBindNameBind, handle, bindIndex)
}

private const val GET_BIND_NAME_HASH = 659327637L
private val getBindNameBind by lazy {
    ObjectCalls.getMethodBind("Skin", "get_bind_name", GET_BIND_NAME_HASH)
}
