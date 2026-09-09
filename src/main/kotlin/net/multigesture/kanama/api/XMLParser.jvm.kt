package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for XMLParser (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP XMLParser waits on: ptrcallWithByteArrayArgRetLong, ptrcallWithIntArgRetString
// Index: docs/contributing/ios-shape-gap.md

/**
 * Returns the name of an attribute of the currently parsed element, specified by the `idx` index.
 *
 * Generated from Godot docs: XMLParser.get_attribute_name
 */
fun XMLParser.getAttributeName(idx: Int): String {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetString(getAttributeNameBind, handle, idx)
}

/**
 * Returns the value of an attribute of the currently parsed element, specified by the `idx` index.
 *
 * Generated from Godot docs: XMLParser.get_attribute_value
 */
fun XMLParser.getAttributeValue(idx: Int): String {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetString(getAttributeValueBind, handle, idx)
}

/**
 * Opens an XML raw `buffer` for parsing. This method returns an error code.
 *
 * Generated from Godot docs: XMLParser.open_buffer
 */
fun XMLParser.openBuffer(buffer: ByteArray): Long {
    checkOpen()
    return ObjectCalls.ptrcallWithByteArrayArgRetLong(openBufferBind, handle, buffer)
}

private const val GET_ATTRIBUTE_NAME_HASH = 844755477L
private val getAttributeNameBind by lazy {
    ObjectCalls.getMethodBind("XMLParser", "get_attribute_name", GET_ATTRIBUTE_NAME_HASH)
}

private const val GET_ATTRIBUTE_VALUE_HASH = 844755477L
private val getAttributeValueBind by lazy {
    ObjectCalls.getMethodBind("XMLParser", "get_attribute_value", GET_ATTRIBUTE_VALUE_HASH)
}

private const val OPEN_BUFFER_HASH = 680677267L
private val openBufferBind by lazy {
    ObjectCalls.getMethodBind("XMLParser", "open_buffer", OPEN_BUFFER_HASH)
}
