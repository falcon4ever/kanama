package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

// GENERATED desktop/Android companion for AudioStreamPlayback (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP AudioStreamPlayback waits on: ptrcallWithDoubleAndIntArgsRetPackedVector2List
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Mixes up to `frames` of audio from the stream from the current position, at a rate of
 * `rate_scale`, advancing the stream. Returns a `PackedVector2Array` where each element holds the
 * left and right channel volume levels of each frame. Note: Can return fewer frames than
 * requested, make sure to use the size of the return value.
 *
 * Generated from Godot docs: AudioStreamPlayback.mix_audio
 */
fun AudioStreamPlayback.mixAudio(rateScale: Double, frames: Int): List<Vector2> {
    checkOpen()
    return ObjectCalls.ptrcallWithDoubleAndIntArgsRetPackedVector2List(mixAudioBind, handle, rateScale, frames)
}

private const val MIX_AUDIO_HASH = 3341291446L
private val mixAudioBind by lazy {
    ObjectCalls.getMethodBind("AudioStreamPlayback", "mix_audio", MIX_AUDIO_HASH)
}
