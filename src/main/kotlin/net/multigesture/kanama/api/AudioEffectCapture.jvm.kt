package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

// GENERATED desktop/Android companion for AudioEffectCapture (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP AudioEffectCapture waits on: ptrcallWithIntArgRetPackedVector2List
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Gets the next `frames` samples from the internal ring buffer. Returns a `PackedVector2Array`
 * containing exactly `frames` samples if available, or an empty `PackedVector2Array` if
 * insufficient data was available. The samples are signed floating-point PCM between `-1` and `1`.
 * You will have to scale them if you want to use them as 8 or 16-bit integer samples. (`v = 0x7fff
 * * samples[0].x`)
 *
 * Generated from Godot docs: AudioEffectCapture.get_buffer
 */
fun AudioEffectCapture.getBuffer(frames: Int): List<Vector2> {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetPackedVector2List(getBufferBind, handle, frames)
}

private const val GET_BUFFER_HASH = 2649534757L
private val getBufferBind by lazy {
    ObjectCalls.getMethodBind("AudioEffectCapture", "get_buffer", GET_BUFFER_HASH)
}
