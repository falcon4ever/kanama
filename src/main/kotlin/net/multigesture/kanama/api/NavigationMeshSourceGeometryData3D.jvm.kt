package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector3

// GENERATED desktop/Android companion for NavigationMeshSourceGeometryData3D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP NavigationMeshSourceGeometryData3D waits on: ptrcallWithArrayArg,
//   ptrcallWithArrayTransform3DArgs, ptrcallWithPackedFloat32ListAndPackedInt32ListArgs,
//   ptrcallWithPackedInt32ListArg, ptrcallWithPackedVector3ListAndTransform3DArg,
//   ptrcallWithPackedVector3ListTwoDoubleAndBoolArgs
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Sets the parsed source geometry data indices. The indices need to be matched with appropriated
 * vertices. Warning: Inappropriate data can crash the baking process of the involved third-party
 * libraries.
 *
 * Generated from Godot docs: NavigationMeshSourceGeometryData3D.set_indices
 */
fun NavigationMeshSourceGeometryData3D.setIndices(indices: List<Int>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedInt32ListArg(setIndicesBind, handle, indices)
}

/**
 * Appends arrays of `vertices` and `indices` at the end of the existing arrays. Adds the existing
 * index as an offset to the appended indices.
 *
 * Generated from Godot docs: NavigationMeshSourceGeometryData3D.append_arrays
 */
fun NavigationMeshSourceGeometryData3D.appendArrays(vertices: List<Float>, indices: List<Int>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedFloat32ListAndPackedInt32ListArgs(appendArraysBind, handle, vertices, indices)
}

/**
 * Adds an `Array` the size of `Mesh.ARRAY_MAX` and with vertices at index `Mesh.ARRAY_VERTEX` and
 * indices at index `Mesh.ARRAY_INDEX` to the navigation mesh baking data. The array must have
 * valid triangulated mesh data to be considered. Since `NavigationMesh` resources have no
 * transform, all vertex positions need to be offset by the node's transform using `xform`.
 *
 * Generated from Godot docs: NavigationMeshSourceGeometryData3D.add_mesh_array
 */
fun NavigationMeshSourceGeometryData3D.addMeshArray(meshArray: List<Any?>, xform: Transform3D) {
    checkOpen()
    ObjectCalls.ptrcallWithArrayTransform3DArgs(addMeshArrayBind, handle, meshArray, xform)
}

/**
 * Adds an array of vertex positions to the geometry data for navigation mesh baking to form
 * triangulated faces. For each face the array must have three vertex positions in clockwise
 * winding order. Since `NavigationMesh` resources have no transform, all vertex positions need to
 * be offset by the node's transform using `xform`.
 *
 * Generated from Godot docs: NavigationMeshSourceGeometryData3D.add_faces
 */
fun NavigationMeshSourceGeometryData3D.addFaces(faces: List<Vector3>, xform: Transform3D) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedVector3ListAndTransform3DArg(addFacesBind, handle, faces, xform)
}

/**
 * Adds a projected obstruction shape to the source geometry. The `vertices` are considered
 * projected on an xz-axes plane, placed at the global y-axis `elevation` and extruded by `height`.
 * If `carve` is `true` the carved shape will not be affected by additional offsets (e.g. agent
 * radius) of the navigation mesh baking process.
 *
 * Generated from Godot docs: NavigationMeshSourceGeometryData3D.add_projected_obstruction
 */
fun NavigationMeshSourceGeometryData3D.addProjectedObstruction(vertices: List<Vector3>, elevation: Double, height: Double, carve: Boolean) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedVector3ListTwoDoubleAndBoolArgs(addProjectedObstructionBind, handle, vertices, elevation, height, carve)
}

/**
 * Sets the projected obstructions with an Array of Dictionaries with the following key value
 * pairs:
 *
 * Generated from Godot docs: NavigationMeshSourceGeometryData3D.set_projected_obstructions
 */
fun NavigationMeshSourceGeometryData3D.setProjectedObstructions(projectedObstructions: List<Any?>) {
    checkOpen()
    ObjectCalls.ptrcallWithArrayArg(setProjectedObstructionsBind, handle, projectedObstructions)
}

private const val SET_INDICES_HASH = 3614634198L
private val setIndicesBind by lazy {
    ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData3D", "set_indices", SET_INDICES_HASH)
}

private const val APPEND_ARRAYS_HASH = 3117535015L
private val appendArraysBind by lazy {
    ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData3D", "append_arrays", APPEND_ARRAYS_HASH)
}

private const val ADD_MESH_ARRAY_HASH = 4235710913L
private val addMeshArrayBind by lazy {
    ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData3D", "add_mesh_array", ADD_MESH_ARRAY_HASH)
}

private const val ADD_FACES_HASH = 1440358797L
private val addFacesBind by lazy {
    ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData3D", "add_faces", ADD_FACES_HASH)
}

private const val ADD_PROJECTED_OBSTRUCTION_HASH = 3351846707L
private val addProjectedObstructionBind by lazy {
    ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData3D", "add_projected_obstruction", ADD_PROJECTED_OBSTRUCTION_HASH)
}

private const val SET_PROJECTED_OBSTRUCTIONS_HASH = 381264803L
private val setProjectedObstructionsBind by lazy {
    ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData3D", "set_projected_obstructions", SET_PROJECTED_OBSTRUCTIONS_HASH)
}
