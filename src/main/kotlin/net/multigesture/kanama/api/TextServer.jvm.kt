package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

// GENERATED desktop/Android companion for TextServer (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP TextServer waits on: ptrcallWithRIDLongRIDListLongDictionaryArgs,
//   ptrcallWithRIDStringRIDListLongDictionaryStringVariantArgsRetBool
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Adds text span and font to draw it to the text buffer.
 *
 * Generated from Godot docs: TextServer.shaped_text_add_string
 */
fun TextServer.shapedTextAddString(shaped: RID, text: String, fonts: List<RID>, size: Long, opentypeFeatures: Map<String, Any?> = emptyMap(), language: String = "", meta: Any? = null): Boolean {
    checkOpen()
    return ObjectCalls.ptrcallWithRIDStringRIDListLongDictionaryStringVariantArgsRetBool(shapedTextAddStringBind, handle, shaped, text, fonts, size, opentypeFeatures, language, meta)
}

/**
 * Changes text span font, font size, and OpenType features, without changing the text.
 *
 * Generated from Godot docs: TextServer.shaped_set_span_update_font
 */
fun TextServer.shapedSetSpanUpdateFont(shaped: RID, index: Long, fonts: List<RID>, size: Long, opentypeFeatures: Map<String, Any?> = emptyMap()) {
    checkOpen()
    ObjectCalls.ptrcallWithRIDLongRIDListLongDictionaryArgs(shapedSetSpanUpdateFontBind, handle, shaped, index, fonts, size, opentypeFeatures)
}

private const val SHAPED_TEXT_ADD_STRING_HASH = 623473029L
private val shapedTextAddStringBind by lazy {
    ObjectCalls.getMethodBind("TextServer", "shaped_text_add_string", SHAPED_TEXT_ADD_STRING_HASH)
}

private const val SHAPED_SET_SPAN_UPDATE_FONT_HASH = 2022725822L
private val shapedSetSpanUpdateFontBind by lazy {
    ObjectCalls.getMethodBind("TextServer", "shaped_set_span_update_font", SHAPED_SET_SPAN_UPDATE_FONT_HASH)
}
