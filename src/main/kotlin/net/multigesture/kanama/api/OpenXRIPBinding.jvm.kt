package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for OpenXRIPBinding (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP OpenXRIPBinding waits on: ptrcallWithArrayArg
// Index: docs/reference/generated/ios-shape-gap.md

fun OpenXRIPBinding.setBindingModifiers(bindingModifiers: List<Any?>) {
    checkOpen()
    ObjectCalls.ptrcallWithArrayArg(setBindingModifiersBind, handle, bindingModifiers)
}

private const val SET_BINDING_MODIFIERS_HASH = 381264803L
private val setBindingModifiersBind by lazy {
    ObjectCalls.getMethodBind("OpenXRIPBinding", "set_binding_modifiers", SET_BINDING_MODIFIERS_HASH)
}
