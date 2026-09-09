package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for AudioStreamInteractive (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP AudioStreamInteractive waits on: ptrcallWithIntArgRetStringName
// Index: docs/reference/generated/ios-shape-gap.md

fun AudioStreamInteractive.getClipName(clipIndex: Int): String {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetStringName(getClipNameBind, handle, clipIndex)
}

private const val GET_CLIP_NAME_HASH = 659327637L
private val getClipNameBind by lazy {
    ObjectCalls.getMethodBind("AudioStreamInteractive", "get_clip_name", GET_CLIP_NAME_HASH)
}
