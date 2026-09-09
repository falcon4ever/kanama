package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath

// GENERATED desktop/Android companion for SplineIK3D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP SplineIK3D waits on: ptrcallWithIntArgRetNodePath
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns the node path of the `Path3D` which is describing the path.
 *
 * Generated from Godot docs: SplineIK3D.get_path_3d
 */
fun SplineIK3D.getPath3d(index: Int): NodePath {
    return ObjectCalls.ptrcallWithIntArgRetNodePath(getPath3dBind, handle, index)
}

private const val GET_PATH_3D_HASH = 408788394L
private val getPath3dBind by lazy {
    ObjectCalls.getMethodBind("SplineIK3D", "get_path_3d", GET_PATH_3D_HASH)
}
