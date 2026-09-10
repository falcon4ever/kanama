package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector3

// GENERATED desktop/Android companion for ConcavePolygonShape3D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP ConcavePolygonShape3D waits on: ptrcallWithPackedVector3ListArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Sets the faces of the trimesh shape from an array of vertices. The `faces` array should be
 * composed of triples such that each triple of vertices defines a triangle.
 *
 * Generated from Godot docs: ConcavePolygonShape3D.set_faces
 */
fun ConcavePolygonShape3D.setFaces(faces: List<Vector3>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedVector3ListArg(setFacesBind, handle, faces)
}

private const val SET_FACES_HASH = 334873810L
private val setFacesBind by lazy {
    ObjectCalls.getMethodBind("ConcavePolygonShape3D", "set_faces", SET_FACES_HASH)
}
