package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for VideoStreamPlayback (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP VideoStreamPlayback waits on: ptrcallWithIntPackedFloat32ListAndIntArgsRetInt
// Index: docs/contributing/ios-shape-gap.md

/**
 * Render `num_frames` audio frames (of `_get_channels` floats each) from `buffer`, starting from
 * index `offset` in the array. Returns the number of audio frames rendered, or -1 on error.
 *
 * Generated from Godot docs: VideoStreamPlayback.mix_audio
 */
fun VideoStreamPlayback.mixAudio(numFrames: Int, buffer: List<Float>, offset: Int = 0): Int {
    checkOpen()
    return ObjectCalls.ptrcallWithIntPackedFloat32ListAndIntArgsRetInt(mixAudioBind, handle, numFrames, buffer, offset)
}

private const val MIX_AUDIO_HASH = 93876830L
private val mixAudioBind by lazy {
    ObjectCalls.getMethodBind("VideoStreamPlayback", "mix_audio", MIX_AUDIO_HASH)
}
