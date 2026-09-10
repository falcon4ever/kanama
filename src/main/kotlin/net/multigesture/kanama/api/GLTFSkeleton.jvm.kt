package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for GLTFSkeleton (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP GLTFSkeleton waits on: ptrcallWithDictionaryArg, ptrcallWithTypedStringListArg
// Index: docs/reference/generated/ios-shape-gap.md

fun GLTFSkeleton.setUniqueNames(uniqueNames: List<String>) {
    checkOpen()
    ObjectCalls.ptrcallWithTypedStringListArg(setUniqueNamesBind, handle, uniqueNames)
}

fun GLTFSkeleton.setGodotBoneNode(godotBoneNode: Map<String, Any?>) {
    checkOpen()
    ObjectCalls.ptrcallWithDictionaryArg(setGodotBoneNodeBind, handle, godotBoneNode)
}

private const val SET_UNIQUE_NAMES_HASH = 381264803L
private val setUniqueNamesBind by lazy {
    ObjectCalls.getMethodBind("GLTFSkeleton", "set_unique_names", SET_UNIQUE_NAMES_HASH)
}

private const val SET_GODOT_BONE_NODE_HASH = 4155329257L
private val setGodotBoneNodeBind by lazy {
    ObjectCalls.getMethodBind("GLTFSkeleton", "set_godot_bone_node", SET_GODOT_BONE_NODE_HASH)
}
