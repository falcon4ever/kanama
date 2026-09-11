package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for CharFXTransform (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP CharFXTransform waits on: ptrcallWithDictionaryArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Contains the arguments passed in the opening BBCode tag. By default, arguments are strings; if
 * their contents match a type such as `bool`, `int` or `float`, they will be converted
 * automatically. Color codes in the form `#rrggbb` or `#rgb` will be converted to an opaque
 * `Color`. String arguments may not contain spaces, even if they're quoted. If present, quotes
 * will also be present in the final string. For example, the opening BBCode tag `[example
 * foo=hello bar=true baz=42 color=#ffffff]` will map to the following `Dictionary`:
 *
 * Generated from Godot docs: CharFXTransform.set_environment
 */
fun CharFXTransform.setEnvironment(environment: Map<String, Any?>) {
    checkOpen()
    ObjectCalls.ptrcallWithDictionaryArg(setEnvironmentBind, handle, environment)
}

private const val SET_ENVIRONMENT_HASH = 4155329257L
private val setEnvironmentBind by lazy {
    ObjectCalls.getMethodBind("CharFXTransform", "set_environment", SET_ENVIRONMENT_HASH)
}
