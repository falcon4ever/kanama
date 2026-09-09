package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for GLTFSkeleton (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP GLTFSkeleton waits on: ptrcallNoArgsRetDictionary, ptrcallNoArgsRetTypedStringList,
//   ptrcallWithDictionaryArg, ptrcallWithPackedInt32ListArg, ptrcallWithTypedStringListArg
// Index: docs/contributing/ios-shape-gap.md

fun GLTFSkeleton.setJoints(joints: List<Int>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedInt32ListArg(setJointsBind, handle, joints)
}

fun GLTFSkeleton.setRoots(roots: List<Int>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedInt32ListArg(setRootsBind, handle, roots)
}

fun GLTFSkeleton.getUniqueNames(): List<String> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetTypedStringList(getUniqueNamesBind, handle)
}

fun GLTFSkeleton.setUniqueNames(uniqueNames: List<String>) {
    checkOpen()
    ObjectCalls.ptrcallWithTypedStringListArg(setUniqueNamesBind, handle, uniqueNames)
}

fun GLTFSkeleton.getGodotBoneNode(): Map<String, Any?> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetDictionary(getGodotBoneNodeBind, handle)
}

fun GLTFSkeleton.setGodotBoneNode(godotBoneNode: Map<String, Any?>) {
    checkOpen()
    ObjectCalls.ptrcallWithDictionaryArg(setGodotBoneNodeBind, handle, godotBoneNode)
}

var GLTFSkeleton.uniqueNames: List<String>
    @JvmName("uniqueNamesProperty")
    get() = getUniqueNames()
    @JvmName("setUniqueNamesProperty")
    set(value) = setUniqueNames(value)

var GLTFSkeleton.godotBoneNode: Map<String, Any?>
    @JvmName("godotBoneNodeProperty")
    get() = getGodotBoneNode()
    @JvmName("setGodotBoneNodeProperty")
    set(value) = setGodotBoneNode(value)

private const val SET_JOINTS_HASH = 3614634198L
private val setJointsBind by lazy {
    ObjectCalls.getMethodBind("GLTFSkeleton", "set_joints", SET_JOINTS_HASH)
}

private const val SET_ROOTS_HASH = 3614634198L
private val setRootsBind by lazy {
    ObjectCalls.getMethodBind("GLTFSkeleton", "set_roots", SET_ROOTS_HASH)
}

private const val GET_UNIQUE_NAMES_HASH = 2915620761L
private val getUniqueNamesBind by lazy {
    ObjectCalls.getMethodBind("GLTFSkeleton", "get_unique_names", GET_UNIQUE_NAMES_HASH)
}

private const val SET_UNIQUE_NAMES_HASH = 381264803L
private val setUniqueNamesBind by lazy {
    ObjectCalls.getMethodBind("GLTFSkeleton", "set_unique_names", SET_UNIQUE_NAMES_HASH)
}

private const val GET_GODOT_BONE_NODE_HASH = 2382534195L
private val getGodotBoneNodeBind by lazy {
    ObjectCalls.getMethodBind("GLTFSkeleton", "get_godot_bone_node", GET_GODOT_BONE_NODE_HASH)
}

private const val SET_GODOT_BONE_NODE_HASH = 4155329257L
private val setGodotBoneNodeBind by lazy {
    ObjectCalls.getMethodBind("GLTFSkeleton", "set_godot_bone_node", SET_GODOT_BONE_NODE_HASH)
}
