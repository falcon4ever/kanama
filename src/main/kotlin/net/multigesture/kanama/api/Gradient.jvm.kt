package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color

// GENERATED desktop/Android companion for Gradient (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP Gradient waits on: ptrcallWithPackedColorListArg
// Index: docs/contributing/ios-shape-gap.md

/**
 * Gradient's colors as a `PackedColorArray`. Note: Setting this property updates all colors at
 * once. To update any color individually use `set_color`.
 *
 * Generated from Godot docs: Gradient.set_colors
 */
fun Gradient.setColors(colors: List<Color>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedColorListArg(setColorsBind, handle, colors)
}

private const val SET_COLORS_HASH = 3546319833L
private val setColorsBind by lazy {
    ObjectCalls.getMethodBind("Gradient", "set_colors", SET_COLORS_HASH)
}
