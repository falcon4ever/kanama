package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Transform3D

// GENERATED desktop/Android companion for ImporterMesh (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP ImporterMesh waits on: ptrcallWithIntArgRetArray, ptrcallWithIntArgRetString,
//   ptrcallWithLongArrayArrayListDictionaryObjectStringLongArgs,
//   ptrcallWithObjectListTransform3DListBoolArgsRetObject, ptrcallWithTwoDoubleArrayArgs,
//   ptrcallWithTwoIntArgsRetArray, ptrcallWithTwoIntArgsRetPackedInt32List
// Index: docs/contributing/ios-shape-gap.md

/**
 * Merges multiple `ImporterMesh`es into a single `ImporterMesh`. Each input mesh is transformed by
 * the corresponding `Transform3D` in the `relative_transforms` array, which must be the same size
 * as `importer_meshes`. Negative scales are supported, and the winding order in the mesh data will
 * be corrected to account for this. If `deduplicate_surfaces` is `true` and multiple meshes have
 * surfaces with the same names and formats, the surfaces will be merged together when the meshes
 * are merged, and will use the material from the first matching surface. This is useful for
 * reducing the number of surfaces in the resulting mesh, and avoids duplicating materials.
 * Surfaces with bone weights will never be deduplicated. If `deduplicate_surfaces` is `false`, the
 * surfaces will always be kept separate, and will be given unique names. Warning: Blend shapes and
 * LODs are not supported and will be discarded. Do not use this function to discard blend shapes
 * and LODs, as support for these may be added in the future.
 *
 * Generated from Godot docs: ImporterMesh.merge_importer_meshes
 */
fun ImporterMesh.Companion.mergeImporterMeshes(importerMeshes: List<ImporterMesh>, relativeTransforms: List<Transform3D>, deduplicateSurfaces: Boolean = true): ImporterMesh? {
    return ImporterMesh.wrap(ObjectCalls.ptrcallWithObjectListTransform3DListBoolArgsRetObject(mergeImporterMeshesBind, MemorySegment.NULL, importerMeshes, relativeTransforms, deduplicateSurfaces))
}

/**
 * Returns the name of the blend shape at this index.
 *
 * Generated from Godot docs: ImporterMesh.get_blend_shape_name
 */
fun ImporterMesh.getBlendShapeName(blendShapeIdx: Int): String {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetString(getBlendShapeNameBind, handle, blendShapeIdx)
}

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
 * Gets the name assigned to this surface.
 *
 * Generated from Godot docs: ImporterMesh.get_surface_name
 */
fun ImporterMesh.getSurfaceName(surfaceIdx: Int): String {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetString(getSurfaceNameBind, handle, surfaceIdx)
}

/**
 * Returns the arrays for the vertices, normals, UVs, etc. that make up the requested surface. See
 * `add_surface`.
 *
 * Generated from Godot docs: ImporterMesh.get_surface_arrays
 */
fun ImporterMesh.getSurfaceArrays(surfaceIdx: Int): List<Any?> {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetArray(getSurfaceArraysBind, handle, surfaceIdx)
}

/**
 * Returns a single set of blend shape arrays for the requested blend shape index for a surface.
 *
 * Generated from Godot docs: ImporterMesh.get_surface_blend_shape_arrays
 */
fun ImporterMesh.getSurfaceBlendShapeArrays(surfaceIdx: Int, blendShapeIdx: Int): List<Any?> {
    checkOpen()
    return ObjectCalls.ptrcallWithTwoIntArgsRetArray(getSurfaceBlendShapeArraysBind, handle, surfaceIdx, blendShapeIdx)
}

/**
 * Returns the index buffer of a lod for a surface.
 *
 * Generated from Godot docs: ImporterMesh.get_surface_lod_indices
 */
fun ImporterMesh.getSurfaceLodIndices(surfaceIdx: Int, lodIdx: Int): List<Int> {
    checkOpen()
    return ObjectCalls.ptrcallWithTwoIntArgsRetPackedInt32List(getSurfaceLodIndicesBind, handle, surfaceIdx, lodIdx)
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

private const val MERGE_IMPORTER_MESHES_HASH = 1030647649L
private val mergeImporterMeshesBind by lazy {
    ObjectCalls.getMethodBind("ImporterMesh", "merge_importer_meshes", MERGE_IMPORTER_MESHES_HASH)
}

private const val GET_BLEND_SHAPE_NAME_HASH = 844755477L
private val getBlendShapeNameBind by lazy {
    ObjectCalls.getMethodBind("ImporterMesh", "get_blend_shape_name", GET_BLEND_SHAPE_NAME_HASH)
}

private const val ADD_SURFACE_HASH = 1740448849L
private val addSurfaceBind by lazy {
    ObjectCalls.getMethodBind("ImporterMesh", "add_surface", ADD_SURFACE_HASH)
}

private const val GET_SURFACE_NAME_HASH = 844755477L
private val getSurfaceNameBind by lazy {
    ObjectCalls.getMethodBind("ImporterMesh", "get_surface_name", GET_SURFACE_NAME_HASH)
}

private const val GET_SURFACE_ARRAYS_HASH = 663333327L
private val getSurfaceArraysBind by lazy {
    ObjectCalls.getMethodBind("ImporterMesh", "get_surface_arrays", GET_SURFACE_ARRAYS_HASH)
}

private const val GET_SURFACE_BLEND_SHAPE_ARRAYS_HASH = 2345056839L
private val getSurfaceBlendShapeArraysBind by lazy {
    ObjectCalls.getMethodBind("ImporterMesh", "get_surface_blend_shape_arrays", GET_SURFACE_BLEND_SHAPE_ARRAYS_HASH)
}

private const val GET_SURFACE_LOD_INDICES_HASH = 1265128013L
private val getSurfaceLodIndicesBind by lazy {
    ObjectCalls.getMethodBind("ImporterMesh", "get_surface_lod_indices", GET_SURFACE_LOD_INDICES_HASH)
}

private const val GENERATE_LODS_HASH = 2491878677L
private val generateLodsBind by lazy {
    ObjectCalls.getMethodBind("ImporterMesh", "generate_lods", GENERATE_LODS_HASH)
}
