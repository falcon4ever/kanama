package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector3

// GENERATED desktop/Android companion for ConcavePolygonShape3D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP ConcavePolygonShape3D waits on: ptrcallNoArgsRetPackedVector3List,
//   ptrcallWithPackedVector3ListArg
// Index: docs/contributing/ios-shape-gap.md

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

/**
 * Returns the faces of the trimesh shape as an array of vertices. The array (of length divisible
 * by three) is naturally divided into triples; each triple of vertices defines a triangle.
 *
 * Generated from Godot docs: ConcavePolygonShape3D.get_faces
 */
fun ConcavePolygonShape3D.getFaces(): List<Vector3> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetPackedVector3List(getFacesBind, handle)
}

var ConcavePolygonShape3D.data: List<Vector3>
    @JvmName("dataProperty")
    get() = getFaces()
    @JvmName("setDataProperty")
    set(value) = setFaces(value)

private const val SET_FACES_HASH = 334873810L
private val setFacesBind by lazy {
    ObjectCalls.getMethodBind("ConcavePolygonShape3D", "set_faces", SET_FACES_HASH)
}

private const val GET_FACES_HASH = 497664490L
private val getFacesBind by lazy {
    ObjectCalls.getMethodBind("ConcavePolygonShape3D", "get_faces", GET_FACES_HASH)
}
