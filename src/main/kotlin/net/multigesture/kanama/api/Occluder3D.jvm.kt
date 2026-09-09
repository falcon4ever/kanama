package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector3

// GENERATED desktop/Android companion for Occluder3D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP Occluder3D waits on: ptrcallNoArgsRetPackedVector3List
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns the occluder shape's vertex positions.
 *
 * Generated from Godot docs: Occluder3D.get_vertices
 */
fun Occluder3D.getVertices(): List<Vector3> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetPackedVector3List(getVerticesBind, handle)
}

private const val GET_VERTICES_HASH = 497664490L
private val getVerticesBind by lazy {
    ObjectCalls.getMethodBind("Occluder3D", "get_vertices", GET_VERTICES_HASH)
}
