package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Vector2

// GENERATED desktop/Android companion for TextParagraph (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP TextParagraph waits on: ptrcallWithArrayArg, ptrcallWithIntAndVariantArgRetRect2,
//   ptrcallWithIntArgRetArray, ptrcallWithStringObjectIntStringVariantArgsRetBool,
//   ptrcallWithVariantArgRetBool, ptrcallWithVariantVector2LongDoubleArgsRetBool,
//   ptrcallWithVariantVector2LongIntDoubleArgsRetBool
// Index: docs/contributing/ios-shape-gap.md

/**
 * Overrides BiDi for the structured text. Override ranges should cover full source text without
 * overlaps. BiDi algorithm will be used on each range separately.
 *
 * Generated from Godot docs: TextParagraph.set_bidi_override
 */
fun TextParagraph.setBidiOverride(override: List<Any?>) {
    checkOpen()
    ObjectCalls.ptrcallWithArrayArg(setBidiOverrideBind, handle, override)
}

/**
 * Adds text span and font to draw it.
 *
 * Generated from Godot docs: TextParagraph.add_string
 */
fun TextParagraph.addString(text: String, font: Font?, fontSize: Int, language: String = "", meta: Any? = null): Boolean {
    checkOpen()
    return ObjectCalls.ptrcallWithStringObjectIntStringVariantArgsRetBool(addStringBind, handle, text, font?.requireOpenHandle() ?: MemorySegment.NULL, fontSize, language, meta)
}

/**
 * Adds inline object to the text buffer, `key` must be unique. In the text, object is represented
 * as `length` object replacement characters.
 *
 * Generated from Godot docs: TextParagraph.add_object
 */
fun TextParagraph.addObject(key: Any?, size: Vector2, inlineAlign: Long = 5L, length: Int = 1, baseline: Double = 0.0): Boolean {
    checkOpen()
    return ObjectCalls.ptrcallWithVariantVector2LongIntDoubleArgsRetBool(addObjectBind, handle, key, size, inlineAlign, length, baseline)
}

/**
 * Sets new size and alignment of embedded object.
 *
 * Generated from Godot docs: TextParagraph.resize_object
 */
fun TextParagraph.resizeObject(key: Any?, size: Vector2, inlineAlign: Long = 5L, baseline: Double = 0.0): Boolean {
    checkOpen()
    return ObjectCalls.ptrcallWithVariantVector2LongDoubleArgsRetBool(resizeObjectBind, handle, key, size, inlineAlign, baseline)
}

/**
 * Returns `true` if an object with `key` is embedded in this shaped text buffer.
 *
 * Generated from Godot docs: TextParagraph.has_object
 */
fun TextParagraph.hasObject(key: Any?): Boolean {
    checkOpen()
    return ObjectCalls.ptrcallWithVariantArgRetBool(hasObjectBind, handle, key)
}

/**
 * Returns array of inline objects in the line.
 *
 * Generated from Godot docs: TextParagraph.get_line_objects
 */
fun TextParagraph.getLineObjects(line: Int): List<Any?> {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetArray(getLineObjectsBind, handle, line)
}

/**
 * Returns bounding rectangle of the inline object.
 *
 * Generated from Godot docs: TextParagraph.get_line_object_rect
 */
fun TextParagraph.getLineObjectRect(line: Int, key: Any?): Rect2 {
    checkOpen()
    return ObjectCalls.ptrcallWithIntAndVariantArgRetRect2(getLineObjectRectBind, handle, line, key)
}

private const val SET_BIDI_OVERRIDE_HASH = 381264803L
private val setBidiOverrideBind by lazy {
    ObjectCalls.getMethodBind("TextParagraph", "set_bidi_override", SET_BIDI_OVERRIDE_HASH)
}

private const val ADD_STRING_HASH = 621426851L
private val addStringBind by lazy {
    ObjectCalls.getMethodBind("TextParagraph", "add_string", ADD_STRING_HASH)
}

private const val ADD_OBJECT_HASH = 1316529304L
private val addObjectBind by lazy {
    ObjectCalls.getMethodBind("TextParagraph", "add_object", ADD_OBJECT_HASH)
}

private const val RESIZE_OBJECT_HASH = 2095776372L
private val resizeObjectBind by lazy {
    ObjectCalls.getMethodBind("TextParagraph", "resize_object", RESIZE_OBJECT_HASH)
}

private const val HAS_OBJECT_HASH = 77467830L
private val hasObjectBind by lazy {
    ObjectCalls.getMethodBind("TextParagraph", "has_object", HAS_OBJECT_HASH)
}

private const val GET_LINE_OBJECTS_HASH = 663333327L
private val getLineObjectsBind by lazy {
    ObjectCalls.getMethodBind("TextParagraph", "get_line_objects", GET_LINE_OBJECTS_HASH)
}

private const val GET_LINE_OBJECT_RECT_HASH = 204315017L
private val getLineObjectRectBind by lazy {
    ObjectCalls.getMethodBind("TextParagraph", "get_line_object_rect", GET_LINE_OBJECT_RECT_HASH)
}
