package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for SystemFont (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP SystemFont waits on: ptrcallWithPackedStringListArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Array of font family names to search, first matching font found is used.
 *
 * Generated from Godot docs: SystemFont.set_font_names
 */
fun SystemFont.setFontNames(names: List<String>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedStringListArg(setFontNamesBind, handle, names)
}

private const val SET_FONT_NAMES_HASH = 4015028928L
private val setFontNamesBind by lazy {
    ObjectCalls.getMethodBind("SystemFont", "set_font_names", SET_FONT_NAMES_HASH)
}
