package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for AnimationMixer (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND.

// Desktop/Android-only sugar (DESKTOP_EXTENSION_SECTIONS['AnimationMixer']).
fun AnimationMixer.setParameter(path: String, value: Any?) {
    setIndexed(path, value)
}

fun AnimationMixer.getParameter(path: String): Any? =
    getIndexed(path)

fun AnimationMixer.getStateMachinePlayback(path: String): AnimationNodeStateMachinePlayback {
    val value = getParameter(path)
    val playback = when (value) {
        is Resource -> AnimationNodeStateMachinePlayback.fromHandle(value.handle)
        is GodotObject -> AnimationNodeStateMachinePlayback.fromHandle(value.handle)
        else -> null
    }
    return playback ?: error("AnimationMixer parameter '$path' is not an AnimationNodeStateMachinePlayback")
}
