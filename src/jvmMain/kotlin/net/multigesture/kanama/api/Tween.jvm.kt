package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireGodotReturn

// GENERATED desktop/Android companion for Tween (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP Tween waits on: ptrcallWithSignalArgRetObject
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Creates and appends an `AwaitTweener`. This method can be used to await a signal to be emitted
 * and create asynchronous animations or cutscenes. The animation will not progress to the next
 * step until the awaited signal is emitted or the connection becomes invalid (e.g. as a result of
 * freeing the target object). If you know that the emission may not happen, use
 * `AwaitTweener.set_timeout`. Note: The awaited signal should be emitted during the step when
 * `AwaitTweener` is active.
 *
 * Generated from Godot docs: Tween.tween_await
 */
fun Tween.tweenAwait(signal: GodotSignal): AwaitTweener {
    checkOpen()
    return requireGodotReturn(AwaitTweener.wrapOwned(ObjectCalls.ptrcallWithSignalArgRetObject(tweenAwaitBind, segment, signal.owner.segment, signal.name)), "Tween.tween_await")
}

private const val TWEEN_AWAIT_HASH = 2242837462L
private val tweenAwaitBind by lazy {
    ObjectCalls.getMethodBind("Tween", "tween_await", TWEEN_AWAIT_HASH)
}
