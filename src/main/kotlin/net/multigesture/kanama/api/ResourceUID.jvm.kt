package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for ResourceUID (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP ResourceUID waits on: ptrcallWithLongArgRetString
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Converts the given UID to a `uid://` string value.
 *
 * Generated from Godot docs: ResourceUID.id_to_text
 */
fun ResourceUID.idToText(id: Long): String {
    return ObjectCalls.ptrcallWithLongArgRetString(idToTextBind, resourceUIDSingleton, id)
}

/**
 * Returns the path that the given UID value refers to. Fails with an error if the UID does not
 * exist, so be sure to check `has_id` beforehand.
 *
 * Generated from Godot docs: ResourceUID.get_id_path
 */
fun ResourceUID.getIdPath(id: Long): String {
    return ObjectCalls.ptrcallWithLongArgRetString(getIdPathBind, resourceUIDSingleton, id)
}

private val resourceUIDSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("ResourceUID")
}

private const val ID_TO_TEXT_HASH = 844755477L
private val idToTextBind by lazy {
    ObjectCalls.getMethodBind("ResourceUID", "id_to_text", ID_TO_TEXT_HASH)
}

private const val GET_ID_PATH_HASH = 844755477L
private val getIdPathBind by lazy {
    ObjectCalls.getMethodBind("ResourceUID", "get_id_path", GET_ID_PATH_HASH)
}
