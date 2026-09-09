package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for OpenXRInteractionProfile (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP OpenXRInteractionProfile waits on: ptrcallWithArrayArg
// Index: docs/reference/generated/ios-shape-gap.md

fun OpenXRInteractionProfile.setBindings(bindings: List<Any?>) {
    checkOpen()
    ObjectCalls.ptrcallWithArrayArg(setBindingsBind, handle, bindings)
}

fun OpenXRInteractionProfile.setBindingModifiers(bindingModifiers: List<Any?>) {
    checkOpen()
    ObjectCalls.ptrcallWithArrayArg(setBindingModifiersBind, handle, bindingModifiers)
}

private const val SET_BINDINGS_HASH = 381264803L
private val setBindingsBind by lazy {
    ObjectCalls.getMethodBind("OpenXRInteractionProfile", "set_bindings", SET_BINDINGS_HASH)
}

private const val SET_BINDING_MODIFIERS_HASH = 381264803L
private val setBindingModifiersBind by lazy {
    ObjectCalls.getMethodBind("OpenXRInteractionProfile", "set_binding_modifiers", SET_BINDING_MODIFIERS_HASH)
}
