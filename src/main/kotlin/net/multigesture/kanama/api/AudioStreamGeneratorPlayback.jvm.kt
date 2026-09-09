package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

// GENERATED desktop/Android companion for AudioStreamGeneratorPlayback (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP AudioStreamGeneratorPlayback waits on: ptrcallWithPackedVector2ListArgRetBool
// Index: docs/contributing/ios-shape-gap.md

/**
 * Pushes several audio data frames to the buffer. This is usually more efficient than `push_frame`
 * in C# and compiled languages via GDExtension, but `push_buffer` may be less efficient in
 * GDScript.
 *
 * Generated from Godot docs: AudioStreamGeneratorPlayback.push_buffer
 */
fun AudioStreamGeneratorPlayback.pushBuffer(frames: List<Vector2>): Boolean {
    checkOpen()
    return ObjectCalls.ptrcallWithPackedVector2ListArgRetBool(pushBufferBind, handle, frames)
}

private const val PUSH_BUFFER_HASH = 1361156557L
private val pushBufferBind by lazy {
    ObjectCalls.getMethodBind("AudioStreamGeneratorPlayback", "push_buffer", PUSH_BUFFER_HASH)
}
