package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for TextEdit (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP TextEdit waits on: ptrcallWithArrayArg, ptrcallWithTwoIntAndVariantArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Set additional options for BiDi override.
 *
 * Generated from Godot docs: TextEdit.set_structured_text_bidi_override_options
 */
fun TextEdit.setStructuredTextBidiOverrideOptions(args: List<Any?>) {
    ObjectCalls.ptrcallWithArrayArg(setStructuredTextBidiOverrideOptionsBind, handle, args)
}

/**
 * Sets the metadata for `gutter` on `line` to `metadata`.
 *
 * Generated from Godot docs: TextEdit.set_line_gutter_metadata
 */
fun TextEdit.setLineGutterMetadata(line: Int, gutter: Int, metadata: Any?) {
    ObjectCalls.ptrcallWithTwoIntAndVariantArg(setLineGutterMetadataBind, handle, line, gutter, metadata)
}

private const val SET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH = 381264803L
private val setStructuredTextBidiOverrideOptionsBind by lazy {
    ObjectCalls.getMethodBind("TextEdit", "set_structured_text_bidi_override_options", SET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH)
}

private const val SET_LINE_GUTTER_METADATA_HASH = 2060538656L
private val setLineGutterMetadataBind by lazy {
    ObjectCalls.getMethodBind("TextEdit", "set_line_gutter_metadata", SET_LINE_GUTTER_METADATA_HASH)
}
