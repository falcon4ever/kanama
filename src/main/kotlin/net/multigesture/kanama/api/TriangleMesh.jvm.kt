package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector3

// GENERATED desktop/Android companion for TriangleMesh (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP TriangleMesh waits on: ptrcallWithPackedVector3ListArgRetBool
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Creates the BVH tree from an array of faces. Each 3 vertices of the input `faces` array
 * represent one triangle (face). Returns `true` if the tree is successfully built, `false`
 * otherwise.
 *
 * Generated from Godot docs: TriangleMesh.create_from_faces
 */
fun TriangleMesh.createFromFaces(faces: List<Vector3>): Boolean {
    checkOpen()
    return ObjectCalls.ptrcallWithPackedVector3ListArgRetBool(createFromFacesBind, handle, faces)
}

private const val CREATE_FROM_FACES_HASH = 2637816732L
private val createFromFacesBind by lazy {
    ObjectCalls.getMethodBind("TriangleMesh", "create_from_faces", CREATE_FROM_FACES_HASH)
}
