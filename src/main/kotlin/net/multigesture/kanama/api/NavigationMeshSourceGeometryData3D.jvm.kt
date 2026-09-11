package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Transform3D

// GENERATED desktop/Android companion for NavigationMeshSourceGeometryData3D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP NavigationMeshSourceGeometryData3D waits on: ptrcallWithArrayArg,
//   ptrcallWithArrayTransform3DArgs
// Index: docs/reference/generated/ios-shape-gap.md

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
 * Sets the projected obstructions with an Array of Dictionaries with the following key value
 * pairs:
 *
 * Generated from Godot docs: NavigationMeshSourceGeometryData3D.set_projected_obstructions
 */
fun NavigationMeshSourceGeometryData3D.setProjectedObstructions(projectedObstructions: List<Any?>) {
    checkOpen()
    ObjectCalls.ptrcallWithArrayArg(setProjectedObstructionsBind, handle, projectedObstructions)
}

private const val ADD_MESH_ARRAY_HASH = 4235710913L
private val addMeshArrayBind by lazy {
    ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData3D", "add_mesh_array", ADD_MESH_ARRAY_HASH)
}

private const val SET_PROJECTED_OBSTRUCTIONS_HASH = 381264803L
private val setProjectedObstructionsBind by lazy {
    ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData3D", "set_projected_obstructions", SET_PROJECTED_OBSTRUCTIONS_HASH)
}
