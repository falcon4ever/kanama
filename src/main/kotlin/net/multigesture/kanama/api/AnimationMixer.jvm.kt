package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for AnimationMixer (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP AnimationMixer waits on: ptrcallWithObjectArgRetStringName
// Index: docs/contributing/ios-shape-gap.md

/**
 * Returns the key of `animation` or an empty `StringName` if not found.
 *
 * Generated from Godot docs: AnimationMixer.find_animation
 */
fun AnimationMixer.findAnimation(animation: Animation?): String {
    return ObjectCalls.ptrcallWithObjectArgRetStringName(findAnimationBind, handle, animation?.requireOpenHandle() ?: MemorySegment.NULL)
}

/**
 * Returns the key for the `AnimationLibrary` that contains `animation` or an empty `StringName` if
 * not found.
 *
 * Generated from Godot docs: AnimationMixer.find_animation_library
 */
fun AnimationMixer.findAnimationLibrary(animation: Animation?): String {
    return ObjectCalls.ptrcallWithObjectArgRetStringName(findAnimationLibraryBind, handle, animation?.requireOpenHandle() ?: MemorySegment.NULL)
}

private const val FIND_ANIMATION_HASH = 1559484580L
private val findAnimationBind by lazy {
    ObjectCalls.getMethodBind("AnimationMixer", "find_animation", FIND_ANIMATION_HASH)
}

private const val FIND_ANIMATION_LIBRARY_HASH = 1559484580L
private val findAnimationLibraryBind by lazy {
    ObjectCalls.getMethodBind("AnimationMixer", "find_animation_library", FIND_ANIMATION_LIBRARY_HASH)
}

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
