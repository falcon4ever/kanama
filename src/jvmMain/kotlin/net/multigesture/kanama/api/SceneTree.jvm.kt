package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

// GENERATED desktop/Android companion for SceneTree (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP SceneTree waits on: ptrcallNoArgsRetTypedObjectList, wrapper Tween
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Creates and returns a new `Tween` processed in this tree. The Tween will start automatically on
 * the next process frame or physics frame (depending on its `Tween.TweenProcessMode`). Note: A
 * `Tween` created using this method is not bound to any `Node`. It may keep working until there is
 * nothing left to animate. If you want the `Tween` to be automatically killed when the `Node` is
 * freed, use `Node.create_tween` or `Tween.bind_node`.
 *
 * Generated from Godot docs: SceneTree.create_tween
 */
fun SceneTree.createTween(): Tween? {
    return Tween.wrap(ObjectCalls.ptrcallNoArgsRetObject(createTweenBind, segment))
}

/**
 * Returns an `Array` of currently existing `Tween`s in the tree, including paused tweens.
 *
 * Generated from Godot docs: SceneTree.get_processed_tweens
 */
fun SceneTree.getProcessedTweens(): List<Tween> {
    return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getProcessedTweensBind, segment, Tween::wrap)
}

private const val CREATE_TWEEN_HASH = 3426978995L
private val createTweenBind by lazy {
    ObjectCalls.getMethodBind("SceneTree", "create_tween", CREATE_TWEEN_HASH)
}

private const val GET_PROCESSED_TWEENS_HASH = 2915620761L
private val getProcessedTweensBind by lazy {
    ObjectCalls.getMethodBind("SceneTree", "get_processed_tweens", GET_PROCESSED_TWEENS_HASH)
}

// Desktop/Android-only sugar (DESKTOP_EXTENSION_SECTIONS['SceneTree']).
// The two static Tween entry points of the retired desktop `object SceneTree`. They live here, not
// in the shared companion, because their instance forms are desktop-only too (SceneTree.jvm.kt: iOS
// hosts no Tween wrapper with a `wrap` helper). Import them by name to call them:
// `import net.multigesture.kanama.api.createTween`.
/**
 * Creates and returns a new `Tween` processed in this tree. The Tween will start automatically on
 * the next process frame or physics frame (depending on its `Tween.TweenProcessMode`). Note: A
 * `Tween` created using this method is not bound to any `Node`. It may keep working until there is
 * nothing left to animate. If you want the `Tween` to be automatically killed when the `Node` is
 * freed, use `Node.create_tween` or `Tween.bind_node`.
 *
 * Generated from Godot docs: SceneTree.create_tween
 */
fun SceneTree.Companion.createTween(): Tween? =
    SceneTree.active().createTween()

/**
 * Returns an `Array` of currently existing `Tween`s in the tree, including paused tweens.
 *
 * Generated from Godot docs: SceneTree.get_processed_tweens
 */
fun SceneTree.Companion.getProcessedTweens(): List<Tween> =
    SceneTree.active().getProcessedTweens()
