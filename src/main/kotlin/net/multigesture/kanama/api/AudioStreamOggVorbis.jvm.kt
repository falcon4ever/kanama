package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for AudioStreamOggVorbis (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP AudioStreamOggVorbis waits on: ptrcallNoArgsRetDictionary,
//   ptrcallWithByteArrayArgRetObject, ptrcallWithDictionaryArg
// Index: docs/contributing/ios-shape-gap.md

fun AudioStreamOggVorbis.Companion.loadFromBuffer(streamData: ByteArray): AudioStreamOggVorbis? {
    return AudioStreamOggVorbis.wrap(ObjectCalls.ptrcallWithByteArrayArgRetObject(loadFromBufferBind, MemorySegment.NULL, streamData))
}

fun AudioStreamOggVorbis.setTags(tags: Map<String, Any?>) {
    checkOpen()
    ObjectCalls.ptrcallWithDictionaryArg(setTagsBind, handle, tags)
}

fun AudioStreamOggVorbis.getTags(): Map<String, Any?> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetDictionary(getTagsBind, handle)
}

var AudioStreamOggVorbis.tags: Map<String, Any?>
    @JvmName("tagsProperty")
    get() = getTags()
    @JvmName("setTagsProperty")
    set(value) = setTags(value)

private const val LOAD_FROM_BUFFER_HASH = 354904730L
private val loadFromBufferBind by lazy {
    ObjectCalls.getMethodBind("AudioStreamOggVorbis", "load_from_buffer", LOAD_FROM_BUFFER_HASH)
}

private const val SET_TAGS_HASH = 4155329257L
private val setTagsBind by lazy {
    ObjectCalls.getMethodBind("AudioStreamOggVorbis", "set_tags", SET_TAGS_HASH)
}

private const val GET_TAGS_HASH = 3102165223L
private val getTagsBind by lazy {
    ObjectCalls.getMethodBind("AudioStreamOggVorbis", "get_tags", GET_TAGS_HASH)
}
