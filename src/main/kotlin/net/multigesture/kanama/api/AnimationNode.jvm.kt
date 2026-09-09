package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for AnimationNode (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP AnimationNode waits on: ptrcallWithIntArgRetString,
//   ptrcallWithStringNameAndVariantArg
// Index: docs/contributing/ios-shape-gap.md

/**
 * Gets the name of an input by index.
 *
 * Generated from Godot docs: AnimationNode.get_input_name
 */
fun AnimationNode.getInputName(input: Int): String {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetString(getInputNameBind, handle, input)
}

/**
 * Sets a custom parameter. These are used as local memory, because resources can be reused across
 * the tree or scenes.
 *
 * Generated from Godot docs: AnimationNode.set_parameter
 */
fun AnimationNode.setParameter(name: String, value: Any?) {
    checkOpen()
    ObjectCalls.ptrcallWithStringNameAndVariantArg(setParameterBind, handle, name, value)
}

private const val GET_INPUT_NAME_HASH = 844755477L
private val getInputNameBind by lazy {
    ObjectCalls.getMethodBind("AnimationNode", "get_input_name", GET_INPUT_NAME_HASH)
}

private const val SET_PARAMETER_HASH = 3776071444L
private val setParameterBind by lazy {
    ObjectCalls.getMethodBind("AnimationNode", "set_parameter", SET_PARAMETER_HASH)
}
