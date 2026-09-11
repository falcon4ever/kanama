package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for ImporterMesh (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP ImporterMesh waits on: ptrcallWithLongArrayArrayListDictionaryObjectStringLongArgs,
//   ptrcallWithTwoDoubleArrayArgs
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Creates a new surface. `Mesh.get_surface_count` will become the `surf_idx` for this new surface.
 * Surfaces are created to be rendered using a `primitive`, which may be any of the values defined
 * in `Mesh.PrimitiveType`. The `arrays` argument is an array of arrays. Each of the
 * `Mesh.ARRAY_MAX` elements contains an array with some of the mesh data for this surface as
 * described by the corresponding member of `Mesh.ArrayType` or `null` if it is not used by the
 * surface. For example, `arrays[0]` is the array of vertices. That first vertex sub-array is
 * always required; the others are optional. Adding an index array puts this surface into "index
 * mode" where the vertex and other arrays become the sources of data and the index array defines
 * the vertex order. All sub-arrays must have the same length as the vertex array (or be an exact
 * multiple of the vertex array's length, when multiple elements of a sub-array correspond to a
 * single vertex) or be empty, except for `Mesh.ARRAY_INDEX` if it is used. The `blend_shapes`
 * argument is an array of vertex data for each blend shape. Each element is an array of the same
 * structure as `arrays`, but `Mesh.ARRAY_VERTEX`, `Mesh.ARRAY_NORMAL`, and `Mesh.ARRAY_TANGENT`
 * are set if and only if they are set in `arrays` and all other entries are `null`. The `lods`
 * argument is a dictionary with `float` keys and `PackedInt32Array` values. Each entry in the
 * dictionary represents an LOD level of the surface, where the value is the `Mesh.ARRAY_INDEX`
 * array to use for the LOD level and the key is roughly proportional to the distance at which the
 * LOD stats being used. I.e., increasing the key of an LOD also increases the distance that the
 * objects has to be from the camera before the LOD is used. The `flags` argument is the bitwise OR
 * of, as required: One value of `Mesh.ArrayCustomFormat` left shifted by
 * `ARRAY_FORMAT_CUSTOMn_SHIFT` for each custom channel in use,
 * `Mesh.ARRAY_FLAG_USE_DYNAMIC_UPDATE`, `Mesh.ARRAY_FLAG_USE_8_BONE_WEIGHTS`, or
 * `Mesh.ARRAY_FLAG_USES_EMPTY_VERTEX_ARRAY`. Note: When using indices, it is recommended to only
 * use points, lines, or triangles.
 *
 * Generated from Godot docs: ImporterMesh.add_surface
 */
fun ImporterMesh.addSurface(primitive: Long, arrays: List<Any?>, blendShapes: List<List<Any?>>, lods: Map<String, Any?> = emptyMap(), material: Material?, name: String = "", flags: Long = 0L) {
    checkOpen()
    ObjectCalls.ptrcallWithLongArrayArrayListDictionaryObjectStringLongArgs(addSurfaceBind, handle, primitive, arrays, blendShapes, lods, material?.requireOpenHandle() ?: MemorySegment.NULL, name, flags)
}

/**
 * Generates all lods for this ImporterMesh. `normal_merge_angle` is in degrees and used in the
 * same way as the importer settings in `lods`. `normal_split_angle` is not used and only remains
 * for compatibility with older versions of the API. The number of generated lods can be accessed
 * using `get_surface_lod_count`, and each LOD is available in `get_surface_lod_size` and
 * `get_surface_lod_indices`. `bone_transform_array` is an `Array` which can be either empty or
 * contain `Transform3D`s which, for each of the mesh's bone IDs, will apply mesh skinning when
 * generating the LOD mesh variations. This is usually used to account for discrepancies in scale
 * between the mesh itself and its skinning data.
 *
 * Generated from Godot docs: ImporterMesh.generate_lods
 */
fun ImporterMesh.generateLods(normalMergeAngle: Double, normalSplitAngle: Double, boneTransformArray: List<Any?>) {
    checkOpen()
    ObjectCalls.ptrcallWithTwoDoubleArrayArgs(generateLodsBind, handle, normalMergeAngle, normalSplitAngle, boneTransformArray)
}

private const val ADD_SURFACE_HASH = 1740448849L
private val addSurfaceBind by lazy {
    ObjectCalls.getMethodBind("ImporterMesh", "add_surface", ADD_SURFACE_HASH)
}

private const val GENERATE_LODS_HASH = 2491878677L
private val generateLodsBind by lazy {
    ObjectCalls.getMethodBind("ImporterMesh", "generate_lods", GENERATE_LODS_HASH)
}
