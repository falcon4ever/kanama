package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for Translation (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP Translation waits on: ptrcallWithStringNamePackedStringListAndStringNameArgs
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Adds a message involving plural translation if nonexistent, followed by its translation. An
 * additional context could be used to specify the translation context or differentiate polysemic
 * words.
 *
 * Generated from Godot docs: Translation.add_plural_message
 */
fun Translation.addPluralMessage(srcMessage: String, xlatedMessages: List<String>, context: String = "") {
    checkOpen()
    ObjectCalls.ptrcallWithStringNamePackedStringListAndStringNameArgs(addPluralMessageBind, handle, srcMessage, xlatedMessages, context)
}

private const val ADD_PLURAL_MESSAGE_HASH = 2356982266L
private val addPluralMessageBind by lazy {
    ObjectCalls.getMethodBind("Translation", "add_plural_message", ADD_PLURAL_MESSAGE_HASH)
}
