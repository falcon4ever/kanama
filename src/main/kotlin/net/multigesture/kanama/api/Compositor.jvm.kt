package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for Compositor (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP Compositor waits on: ptrcallWithObjectListArg
// Index: docs/contributing/ios-shape-gap.md

/**
 * The custom `CompositorEffect`s that are applied during rendering of viewports using this
 * compositor.
 *
 * Generated from Godot docs: Compositor.set_compositor_effects
 */
fun Compositor.setCompositorEffects(compositorEffects: List<CompositorEffect>) {
    checkOpen()
    ObjectCalls.ptrcallWithObjectListArg(setCompositorEffectsBind, handle, compositorEffects)
}

private const val SET_COMPOSITOR_EFFECTS_HASH = 381264803L
private val setCompositorEffectsBind by lazy {
    ObjectCalls.getMethodBind("Compositor", "set_compositor_effects", SET_COMPOSITOR_EFFECTS_HASH)
}
