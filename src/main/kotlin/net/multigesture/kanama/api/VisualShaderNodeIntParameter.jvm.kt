package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for VisualShaderNodeIntParameter (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP VisualShaderNodeIntParameter waits on: ptrcallWithPackedStringListArg
// Index: docs/reference/generated/ios-shape-gap.md

fun VisualShaderNodeIntParameter.setEnumNames(names: List<String>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedStringListArg(setEnumNamesBind, handle, names)
}

private const val SET_ENUM_NAMES_HASH = 4015028928L
private val setEnumNamesBind by lazy {
    ObjectCalls.getMethodBind("VisualShaderNodeIntParameter", "set_enum_names", SET_ENUM_NAMES_HASH)
}
