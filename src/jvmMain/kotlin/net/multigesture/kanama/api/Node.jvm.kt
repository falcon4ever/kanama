package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

// GENERATED desktop/Android companion for Node (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP Node waits on: wrapper Tween
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Creates a new `Tween` and binds it to this node. This is the equivalent of doing:
 *
 * Generated from Godot docs: Node.create_tween
 */
fun Node.createTween(): Tween? {
    return Tween.wrap(ObjectCalls.ptrcallNoArgsRetObject(createTweenBind, segment))
}

private const val CREATE_TWEEN_HASH = 3426978995L
private val createTweenBind by lazy {
    ObjectCalls.getMethodBind("Node", "create_tween", CREATE_TWEEN_HASH)
}
