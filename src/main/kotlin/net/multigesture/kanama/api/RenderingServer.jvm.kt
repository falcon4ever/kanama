package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

// GENERATED desktop/Android companion for RenderingServer (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP RenderingServer waits on: ptrcallWithDictionaryListIntArgsRetRID,
//   ptrcallWithRIDAndIntArgRetArrayList
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Creates a new mesh with predefined surfaces for it and adds the mesh to the RenderingServer. It
 * can be accessed with the RID that is returned. This RID will be used in all `mesh_*`
 * RenderingServer functions. This method is more efficient for creating meshes with multiple
 * surfaces compared to creating an empty mesh with `mesh_create` and adding surfaces one by one
 * with `mesh_add_surface`. Each element in the `surfaces` array must follow the same structure as
 * described in `mesh_add_surface`. The `blend_shape_count` parameter must match the blend shape
 * data defined in all surfaces. Once finished with your RID, you will want to free the RID using
 * the RenderingServer's `free_rid` method. To place in a scene, attach this mesh to an instance
 * using `instance_set_base` using the returned RID. Note: The equivalent resource is `Mesh`.
 *
 * Generated from Godot docs: RenderingServer.mesh_create_from_surfaces
 */
fun RenderingServer.meshCreateFromSurfaces(surfaces: List<Map<String, Any?>>, blendShapeCount: Int = 0): RID {
    return ObjectCalls.ptrcallWithDictionaryListIntArgsRetRID(meshCreateFromSurfacesBind, renderingServerSingleton, surfaces, blendShapeCount)
}

/**
 * Returns a mesh's surface's arrays for blend shapes.
 *
 * Generated from Godot docs: RenderingServer.mesh_surface_get_blend_shape_arrays
 */
fun RenderingServer.meshSurfaceGetBlendShapeArrays(mesh: RID, surface: Int): List<List<Any?>> {
    return ObjectCalls.ptrcallWithRIDAndIntArgRetArrayList(meshSurfaceGetBlendShapeArraysBind, renderingServerSingleton, mesh, surface)
}

private val renderingServerSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("RenderingServer")
}

private const val MESH_CREATE_FROM_SURFACES_HASH = 4291747531L
private val meshCreateFromSurfacesBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "mesh_create_from_surfaces", MESH_CREATE_FROM_SURFACES_HASH)
}

private const val MESH_SURFACE_GET_BLEND_SHAPE_ARRAYS_HASH = 1778388067L
private val meshSurfaceGetBlendShapeArraysBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "mesh_surface_get_blend_shape_arrays", MESH_SURFACE_GET_BLEND_SHAPE_ARRAYS_HASH)
}
