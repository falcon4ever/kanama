package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for Translation (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP Translation waits on: ptrcallWithStringNamePackedStringListAndStringNameArgs,
//   ptrcallWithTwoStringNameArgsRetStringName,
//   ptrcallWithTwoStringNameIntStringNameArgsRetStringName
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

/**
 * Returns a message's translation.
 *
 * Generated from Godot docs: Translation.get_message
 */
fun Translation.getMessage(srcMessage: String, context: String = ""): String {
    checkOpen()
    return ObjectCalls.ptrcallWithTwoStringNameArgsRetStringName(getMessageBind, handle, srcMessage, context)
}

/**
 * Returns a message's translation involving plurals. The number `n` is the number or quantity of
 * the plural object. It will be used to guide the translation system to fetch the correct plural
 * form for the selected language. Note: Plurals are only supported in gettext-based translations
 * (PO) ($DOCS_URL/tutorials/i18n/localization_using_gettext.html), not CSV.
 *
 * Generated from Godot docs: Translation.get_plural_message
 */
fun Translation.getPluralMessage(srcMessage: String, srcPluralMessage: String, n: Int, context: String = ""): String {
    checkOpen()
    return ObjectCalls.ptrcallWithTwoStringNameIntStringNameArgsRetStringName(getPluralMessageBind, handle, srcMessage, srcPluralMessage, n, context)
}

private const val ADD_PLURAL_MESSAGE_HASH = 2356982266L
private val addPluralMessageBind by lazy {
    ObjectCalls.getMethodBind("Translation", "add_plural_message", ADD_PLURAL_MESSAGE_HASH)
}

private const val GET_MESSAGE_HASH = 1829228469L
private val getMessageBind by lazy {
    ObjectCalls.getMethodBind("Translation", "get_message", GET_MESSAGE_HASH)
}

private const val GET_PLURAL_MESSAGE_HASH = 229954002L
private val getPluralMessageBind by lazy {
    ObjectCalls.getMethodBind("Translation", "get_plural_message", GET_PLURAL_MESSAGE_HASH)
}
