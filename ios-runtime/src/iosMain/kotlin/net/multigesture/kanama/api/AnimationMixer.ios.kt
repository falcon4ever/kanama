package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

// GENERATED iOS companion for AnimationMixer (scripts/generate_api_wrapper.py --write-tree, from
// IOS_EXTENSION_SECTIONS). DO NOT EDIT BY HAND. iOS-only sugar over the shared wrapper: it
// uses C-shim helpers desktop/Android do not have, so it cannot live in the shared file.

// AnimationTree parameters are exposed as `parameters/...` engine properties, so route through
// set()/get() (no NodePath set_indexed needed). Matches the desktop AnimationMixer helpers.
fun AnimationMixer.setParameter(path: String, value: Any?) {
    set(path, value)
}

fun AnimationMixer.getParameter(path: String): Any? =
    get(path)

fun AnimationMixer.getStateMachinePlayback(path: String): AnimationNodeStateMachinePlayback {
    val value = getParameter(path)
    val playback = when (value) {
        is AnimationNodeStateMachinePlayback -> value
        // iOS decodes a Variant Object return as a raw handle (MemorySegment), not a wrapper.
        is MemorySegment -> if (value.address() != 0L) AnimationNodeStateMachinePlayback(value) else null
        is Resource -> AnimationNodeStateMachinePlayback.fromHandle(value.handle)
        is GodotObject -> AnimationNodeStateMachinePlayback.fromHandle(value.handle)
        else -> null
    }
    return playback ?: error("AnimationMixer parameter '$path' is not an AnimationNodeStateMachinePlayback")
}
