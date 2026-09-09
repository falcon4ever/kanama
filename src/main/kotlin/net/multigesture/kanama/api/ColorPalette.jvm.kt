package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color

// GENERATED desktop/Android companion for ColorPalette (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP ColorPalette waits on: ptrcallWithPackedColorListArg
// Index: docs/contributing/ios-shape-gap.md

/**
 * A `PackedColorArray` containing the colors in the palette.
 *
 * Generated from Godot docs: ColorPalette.set_colors
 */
fun ColorPalette.setColors(colors: List<Color>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedColorListArg(setColorsBind, handle, colors)
}

private const val SET_COLORS_HASH = 3546319833L
private val setColorsBind by lazy {
    ObjectCalls.getMethodBind("ColorPalette", "set_colors", SET_COLORS_HASH)
}
