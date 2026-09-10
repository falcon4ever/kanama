package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for DPITexture (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP DPITexture waits on: ptrcallWithDictionaryArg,
//   ptrcallWithStringTwoDoubleDictionaryArgsRetObject
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Creates a new `DPITexture` and initializes it by allocating and setting the SVG data to
 * `source`.
 *
 * Generated from Godot docs: DPITexture.create_from_string
 */
fun DPITexture.Companion.createFromString(source: String, scale: Double = 1.0, saturation: Double = 1.0, colorMap: Map<String, Any?> = emptyMap()): DPITexture? {
    return DPITexture.wrap(ObjectCalls.ptrcallWithStringTwoDoubleDictionaryArgsRetObject(createFromStringBind, MemorySegment.NULL, source, scale, saturation, colorMap))
}

/**
 * If set, remaps texture colors according to `Color`-`Color` map.
 *
 * Generated from Godot docs: DPITexture.set_color_map
 */
fun DPITexture.setColorMap(colorMap: Map<String, Any?>) {
    checkOpen()
    ObjectCalls.ptrcallWithDictionaryArg(setColorMapBind, handle, colorMap)
}

private const val CREATE_FROM_STRING_HASH = 755140520L
private val createFromStringBind by lazy {
    ObjectCalls.getMethodBind("DPITexture", "create_from_string", CREATE_FROM_STRING_HASH)
}

private const val SET_COLOR_MAP_HASH = 4155329257L
private val setColorMapBind by lazy {
    ObjectCalls.getMethodBind("DPITexture", "set_color_map", SET_COLOR_MAP_HASH)
}
