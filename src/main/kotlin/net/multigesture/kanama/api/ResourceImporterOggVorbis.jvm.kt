package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for ResourceImporterOggVorbis (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP ResourceImporterOggVorbis waits on: ptrcallWithByteArrayArgRetObject
// Index: docs/reference/generated/ios-shape-gap.md

fun ResourceImporterOggVorbis.Companion.loadFromBuffer(streamData: ByteArray): AudioStreamOggVorbis? {
    return AudioStreamOggVorbis.wrap(ObjectCalls.ptrcallWithByteArrayArgRetObject(loadFromBufferBind, MemorySegment.NULL, streamData))
}

private const val LOAD_FROM_BUFFER_HASH = 354904730L
private val loadFromBufferBind by lazy {
    ObjectCalls.getMethodBind("ResourceImporterOggVorbis", "load_from_buffer", LOAD_FROM_BUFFER_HASH)
}
