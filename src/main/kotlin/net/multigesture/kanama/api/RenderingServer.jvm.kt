package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Plane
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2i
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i
import net.multigesture.kanama.types.Vector3
import net.multigesture.kanama.types.Vector3i

// GENERATED desktop/Android companion for RenderingServer (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP RenderingServer waits on: ptrcallWithAABBRIDArgsRetPackedInt64List,
//   ptrcallWithDictionaryListIntArgsRetRID, ptrcallWithLongThreeIntBoolObjectListArgsRetRID,
//   ptrcallWithObjectListLongArgsRetRID, ptrcallWithPlaneListAndRIDArgsRetPackedInt64List,
//   ptrcallWithRIDAndDictionaryArg, ptrcallWithRIDAndIntArgRetArray,
//   ptrcallWithRIDAndIntArgRetArrayList, ptrcallWithRIDAndIntArgRetDictionary,
//   ptrcallWithRIDAndObjectListArgs, ptrcallWithRIDAndPackedFloat32ListArg,
//   ptrcallWithRIDAndRIDListArgs, ptrcallWithRIDAndStringNameArgRetVariantScalar,
//   ptrcallWithRIDAndTransform3DListArgs, ptrcallWithRIDAndTwoPackedFloat32ListArgs,
//   ptrcallWithRIDArgRetByteArray, ptrcallWithRIDArgRetDictionaryList,
//   ptrcallWithRIDArgRetPackedColorList, ptrcallWithRIDArgRetPackedFloat32List,
//   ptrcallWithRIDArgRetPackedInt32List, ptrcallWithRIDArgRetPackedVector3List,
//   ptrcallWithRIDArgRetString, ptrcallWithRIDArgRetTypedObjectList,
//   ptrcallWithRIDBoolPackedFloat32ListFourDoubleLongFourDoubleRIDArgs,
//   ptrcallWithRIDIntIntAndByteArrayArgs, ptrcallWithRIDListRect2iRIDColorRIDListIntArgs,
//   ptrcallWithRIDLongTwoArrayDictionaryLongArgs,
//   ptrcallWithRIDPackedInt32ListPackedVector2ListPackedColorListPackedVector2ListPackedInt32ListPackedFloat32ListRIDIntArgs,
//   ptrcallWithRIDPackedVector2ListAndBoolArg,
//   ptrcallWithRIDPackedVector2ListPackedColorListDoubleAndBoolArgs,
//   ptrcallWithRIDPackedVector2ListPackedColorListPackedVector2ListAndRIDArgs,
//   ptrcallWithRIDPackedVector3ListPackedColorListTwoPackedInt32ListArgs,
//   ptrcallWithRIDPackedVector3ListPackedInt32ListArgs,
//   ptrcallWithRIDRIDListVector2iArgsRetTypedObjectList, ptrcallWithRIDStringNameAndVariantArgs,
//   ptrcallWithRIDTransform3DAABBVector3iThreeByteArrayPackedInt32ListArgs,
//   ptrcallWithStringNameAndVariantArg, ptrcallWithStringNameLongVariantArgs,
//   ptrcallWithTwoVector3RIDArgsRetPackedInt64List
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Creates a 2-dimensional layered texture and adds it to the RenderingServer. It can be accessed
 * with the RID that is returned. This RID will be used in all `texture_2d_layered_*`
 * RenderingServer functions. Once finished with your RID, you will want to free the RID using the
 * RenderingServer's `free_rid` method. Note: The equivalent resource is `TextureLayered`.
 *
 * Generated from Godot docs: RenderingServer.texture_2d_layered_create
 */
fun RenderingServer.texture2dLayeredCreate(layers: List<Image>, layeredType: Long): RID {
    return ObjectCalls.ptrcallWithObjectListLongArgsRetRID(texture2dLayeredCreateBind, renderingServerSingleton, layers, layeredType)
}

/**
 * Note: The equivalent resource is `Texture3D`.
 *
 * Generated from Godot docs: RenderingServer.texture_3d_create
 */
fun RenderingServer.texture3dCreate(format: Long, width: Int, height: Int, depth: Int, mipmaps: Boolean, data: List<Image>): RID {
    return ObjectCalls.ptrcallWithLongThreeIntBoolObjectListArgsRetRID(texture3dCreateBind, renderingServerSingleton, format, width, height, depth, mipmaps, data)
}

/**
 * Updates the texture specified by the `texture` `RID`'s data with the data in `data`. All the
 * texture's layers must be replaced at once. Note: The `texture` must have the same width, height,
 * depth and format as the current texture data. Otherwise, an error will be printed and the
 * original texture won't be modified. If you need to use different width, height, depth or format,
 * use `texture_replace` instead.
 *
 * Generated from Godot docs: RenderingServer.texture_3d_update
 */
fun RenderingServer.texture3dUpdate(texture: RID, data: List<Image>) {
    ObjectCalls.ptrcallWithRIDAndObjectListArgs(texture3dUpdateBind, renderingServerSingleton, texture, data)
}

/**
 * Draws to `rect` on up to 4 given Drawable `textures`, using a TextureBlit Shader from
 * `material`. `modulate` and up to 4 `source_textures` are uniforms for the Shader to process
 * with. `to_mipmap` can specify to perform this draw to a lower mipmap level. Note: All `textures`
 * must be the same size and format.
 *
 * Generated from Godot docs: RenderingServer.texture_drawable_blit_rect
 */
fun RenderingServer.textureDrawableBlitRect(textures: List<RID>, rect: Rect2i, material: RID, modulate: Color, sourceTextures: List<RID>, toMipmap: Int = 0) {
    ObjectCalls.ptrcallWithRIDListRect2iRIDColorRIDListIntArgs(textureDrawableBlitRectBind, renderingServerSingleton, textures, rect, material, modulate, sourceTextures, toMipmap)
}

/**
 * Returns 3D texture data as an array of `Image`s for the specified texture `RID`.
 *
 * Generated from Godot docs: RenderingServer.texture_3d_get
 */
fun RenderingServer.texture3dGet(texture: RID): List<Image> {
    return ObjectCalls.ptrcallWithRIDArgRetTypedObjectList(texture3dGetBind, renderingServerSingleton, texture, Image::fromHandle)
}

/**
 * Returns the resource path (starting with `res://` or `uid://`) for the specified texture RID.
 * Returns an empty `String` if the resource is built-in. See also `texture_set_path`.
 *
 * Generated from Godot docs: RenderingServer.texture_get_path
 */
fun RenderingServer.textureGetPath(texture: RID): String {
    return ObjectCalls.ptrcallWithRIDArgRetString(textureGetPathBind, renderingServerSingleton, texture)
}

/**
 * Returns a shader's source code as a string.
 *
 * Generated from Godot docs: RenderingServer.shader_get_code
 */
fun RenderingServer.shaderGetCode(shader: RID): String {
    return ObjectCalls.ptrcallWithRIDArgRetString(shaderGetCodeBind, renderingServerSingleton, shader)
}

/**
 * Returns the parameters of a shader.
 *
 * Generated from Godot docs: RenderingServer.get_shader_parameter_list
 */
fun RenderingServer.getShaderParameterList(shader: RID): List<Map<String, Any?>> {
    return ObjectCalls.ptrcallWithRIDArgRetDictionaryList(getShaderParameterListBind, renderingServerSingleton, shader)
}

/**
 * Returns the default value for the specified shader uniform. This is usually the value written in
 * the shader source code.
 *
 * Generated from Godot docs: RenderingServer.shader_get_parameter_default
 */
fun RenderingServer.shaderGetParameterDefault(shader: RID, name: String): Any? {
    return ObjectCalls.ptrcallWithRIDAndStringNameArgRetVariantScalar(shaderGetParameterDefaultBind, renderingServerSingleton, shader, name)
}

/**
 * Sets a material's parameter.
 *
 * Generated from Godot docs: RenderingServer.material_set_param
 */
fun RenderingServer.materialSetParam(material: RID, parameter: String, value: Any?) {
    ObjectCalls.ptrcallWithRIDStringNameAndVariantArgs(materialSetParamBind, renderingServerSingleton, material, parameter, value)
}

/**
 * Returns the value of a certain material's parameter.
 *
 * Generated from Godot docs: RenderingServer.material_get_param
 */
fun RenderingServer.materialGetParam(material: RID, parameter: String): Any? {
    return ObjectCalls.ptrcallWithRIDAndStringNameArgRetVariantScalar(materialGetParamBind, renderingServerSingleton, material, parameter)
}

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
 * Creates a new surface on the given `mesh`. Equivalent to `mesh_add_surface_from_arrays`, but
 * takes a single `Dictionary` argument instead of separate arguments. The dictionary must follow
 * this structure:
 *
 * Generated from Godot docs: RenderingServer.mesh_add_surface
 */
fun RenderingServer.meshAddSurface(mesh: RID, surface: Map<String, Any?>) {
    ObjectCalls.ptrcallWithRIDAndDictionaryArg(meshAddSurfaceBind, renderingServerSingleton, mesh, surface)
}

/**
 * Creates a new surface on the given `mesh`. `mesh_get_surface_count` will become the surface
 * index for this new surface. Surfaces are created to be rendered using a `primitive`, which may
 * be any of the values defined in `Mesh.PrimitiveType`. The `arrays` argument is an array of
 * arrays. Each of the `Mesh.ARRAY_MAX` elements contains an array with some of the mesh data for
 * this surface as described by the corresponding member of `Mesh.ArrayType` or `null` if it is not
 * used by the surface. For example, `arrays[0]` is the array of vertices. That first vertex
 * sub-array is always required; the others are optional. Adding an index array puts this surface
 * into "index mode" where the vertex and other arrays become the sources of data and the index
 * array defines the vertex order. All sub-arrays must have the same length as the vertex array (or
 * be an exact multiple of the vertex array's length, when multiple elements of a sub-array
 * correspond to a single vertex) or be empty, except for `Mesh.ARRAY_INDEX` if it is used. The
 * `blend_shapes` argument is an array of vertex data for each blend shape. Each element is an
 * array of the same structure as `arrays`, but `Mesh.ARRAY_VERTEX`, `Mesh.ARRAY_NORMAL`, and
 * `Mesh.ARRAY_TANGENT` are set if and only if they are set in `arrays` and all other entries are
 * `null`. The `lods` argument is a dictionary with `float` keys and `PackedInt32Array` values.
 * Each entry in the dictionary represents an LOD level of the surface, where the value is the
 * `Mesh.ARRAY_INDEX` array to use for the LOD level and the key is roughly proportional to the
 * distance at which the LOD stats being used. I.e., increasing the key of an LOD also increases
 * the distance that the objects has to be from the camera before the LOD is used. The
 * `compress_format` argument is the bitwise OR of, as required: One value of `ArrayFormat` left
 * shifted by `ARRAY_FORMAT_CUSTOMn_SHIFT` for each custom channel in use,
 * `ARRAY_FLAG_USE_DYNAMIC_UPDATE`, `ARRAY_FLAG_USE_8_BONE_WEIGHTS`, or
 * `ARRAY_FLAG_USES_EMPTY_VERTEX_ARRAY`. See `ArrayMesh.add_surface_from_arrays` and
 * `ImporterMesh.add_surface` for higher-level equivalents of this method. Note: When using
 * indices, it is recommended to only use points, lines, or triangles.
 *
 * Generated from Godot docs: RenderingServer.mesh_add_surface_from_arrays
 */
fun RenderingServer.meshAddSurfaceFromArrays(mesh: RID, primitive: Long, arrays: List<Any?>, blendShapes: List<Any?> = emptyList(), lods: Map<String, Any?> = emptyMap(), compressFormat: Long = 0L) {
    ObjectCalls.ptrcallWithRIDLongTwoArrayDictionaryLongArgs(meshAddSurfaceFromArraysBind, renderingServerSingleton, mesh, primitive, arrays, blendShapes, lods, compressFormat)
}

/**
 * Returns a mesh's surface as a dictionary following the same structure as described in
 * `mesh_add_surface`.
 *
 * Generated from Godot docs: RenderingServer.mesh_get_surface
 */
fun RenderingServer.meshGetSurface(mesh: RID, surface: Int): Map<String, Any?> {
    return ObjectCalls.ptrcallWithRIDAndIntArgRetDictionary(meshGetSurfaceBind, renderingServerSingleton, mesh, surface)
}

/**
 * Returns a mesh's surface's buffer arrays.
 *
 * Generated from Godot docs: RenderingServer.mesh_surface_get_arrays
 */
fun RenderingServer.meshSurfaceGetArrays(mesh: RID, surface: Int): List<Any?> {
    return ObjectCalls.ptrcallWithRIDAndIntArgRetArray(meshSurfaceGetArraysBind, renderingServerSingleton, mesh, surface)
}

/**
 * Returns a mesh's surface's arrays for blend shapes.
 *
 * Generated from Godot docs: RenderingServer.mesh_surface_get_blend_shape_arrays
 */
fun RenderingServer.meshSurfaceGetBlendShapeArrays(mesh: RID, surface: Int): List<List<Any?>> {
    return ObjectCalls.ptrcallWithRIDAndIntArgRetArrayList(meshSurfaceGetBlendShapeArraysBind, renderingServerSingleton, mesh, surface)
}

/**
 * Updates the vertex buffer of the mesh surface with the given `data`. The expected data per
 * vertex is 8 or 12 bytes (4 bytes per float, 2 floats per `Vector2`, and 3 floats per `Vector3`)
 * depending on if the mesh is using `Vector2` or `Vector3` vertices. This value can be determined
 * with `mesh_surface_get_format_vertex_stride` instead. The starting point of the updates can be
 * changed with `offset`. The value of `offset` should be a multiple of 12 bytes in most cases to
 * align to each vertex. A `PackedVector3Array` of vertex locations can be converted into a
 * `PackedByteArray` using `PackedVector3Array.to_byte_array` for use in `data`.
 *
 * Generated from Godot docs: RenderingServer.mesh_surface_update_vertex_region
 */
fun RenderingServer.meshSurfaceUpdateVertexRegion(mesh: RID, surface: Int, offset: Int, data: ByteArray) {
    ObjectCalls.ptrcallWithRIDIntIntAndByteArrayArgs(meshSurfaceUpdateVertexRegionBind, renderingServerSingleton, mesh, surface, offset, data)
}

/**
 * Updates the attribute buffer of the mesh surface with the given `data`. The expected data per
 * attribute is 8 or 12 bytes (4 bytes per float, 2 floats per `Vector2`, and 3 floats per
 * `Vector3`) depending on if the mesh is using `Vector2` or `Vector3` vertices. This value can be
 * determined with `mesh_surface_get_format_attribute_stride` instead. The starting point of the
 * updates can be changed with `offset`. The value of `offset` should be a multiple of 12 bytes in
 * most cases to align to each attribute. A `PackedVector3Array` of attribute locations can be
 * converted into a `PackedByteArray` using `PackedVector3Array.to_byte_array` for use in `data`.
 *
 * Generated from Godot docs: RenderingServer.mesh_surface_update_attribute_region
 */
fun RenderingServer.meshSurfaceUpdateAttributeRegion(mesh: RID, surface: Int, offset: Int, data: ByteArray) {
    ObjectCalls.ptrcallWithRIDIntIntAndByteArrayArgs(meshSurfaceUpdateAttributeRegionBind, renderingServerSingleton, mesh, surface, offset, data)
}

/**
 * Updates the skin buffer of the mesh surface with the given `data`. The expected data per skin is
 * 8 or 12 bytes (4 bytes per float, 2 floats per `Vector2`, and 3 floats per `Vector3`) depending
 * on if the mesh is using `Vector2` or `Vector3` vertices. This value can be determined with
 * `mesh_surface_get_format_skin_stride` instead. The starting point of the updates can be changed
 * with `offset`. The value of `offset` should be a multiple of 12 bytes in most cases to align to
 * each skin. A `PackedVector3Array` of skin locations can be converted into a `PackedByteArray`
 * using `PackedVector3Array.to_byte_array` for use in `data`.
 *
 * Generated from Godot docs: RenderingServer.mesh_surface_update_skin_region
 */
fun RenderingServer.meshSurfaceUpdateSkinRegion(mesh: RID, surface: Int, offset: Int, data: ByteArray) {
    ObjectCalls.ptrcallWithRIDIntIntAndByteArrayArgs(meshSurfaceUpdateSkinRegionBind, renderingServerSingleton, mesh, surface, offset, data)
}

/**
 * Updates the index buffer of the mesh surface with the given `data`. The expected data are 16 or
 * 32-bit unsigned integers, which can be determined with `mesh_surface_get_format_index_stride`.
 *
 * Generated from Godot docs: RenderingServer.mesh_surface_update_index_region
 */
fun RenderingServer.meshSurfaceUpdateIndexRegion(mesh: RID, surface: Int, offset: Int, data: ByteArray) {
    ObjectCalls.ptrcallWithRIDIntIntAndByteArrayArgs(meshSurfaceUpdateIndexRegionBind, renderingServerSingleton, mesh, surface, offset, data)
}

/**
 * Set the entire data to use for drawing the `multimesh` at once to `buffer` (such as instance
 * transforms and colors). `buffer`'s size must match the number of instances multiplied by the
 * per-instance data size (which depends on the enabled MultiMesh fields). Otherwise, an error
 * message is printed and nothing is rendered. See also `multimesh_get_buffer`. The per-instance
 * data size and expected data order is:
 *
 * Generated from Godot docs: RenderingServer.multimesh_set_buffer
 */
fun RenderingServer.multimeshSetBuffer(multimesh: RID, buffer: List<Float>) {
    ObjectCalls.ptrcallWithRIDAndPackedFloat32ListArg(multimeshSetBufferBind, renderingServerSingleton, multimesh, buffer)
}

/**
 * Returns the MultiMesh data (such as instance transforms, colors, etc.). See
 * `multimesh_set_buffer` for details on the returned data. Note: If the buffer is in the engine's
 * internal cache, it will have to be fetched from GPU memory and possibly decompressed. This means
 * `multimesh_get_buffer` is potentially a slow operation and should be avoided whenever possible.
 *
 * Generated from Godot docs: RenderingServer.multimesh_get_buffer
 */
fun RenderingServer.multimeshGetBuffer(multimesh: RID): List<Float> {
    return ObjectCalls.ptrcallWithRIDArgRetPackedFloat32List(multimeshGetBufferBind, renderingServerSingleton, multimesh)
}

/**
 * Alternative version of `multimesh_set_buffer` for use with physics interpolation. Takes both an
 * array of current data and an array of data for the previous physics tick.
 *
 * Generated from Godot docs: RenderingServer.multimesh_set_buffer_interpolated
 */
fun RenderingServer.multimeshSetBufferInterpolated(multimesh: RID, buffer: List<Float>, bufferPrevious: List<Float>) {
    ObjectCalls.ptrcallWithRIDAndTwoPackedFloat32ListArgs(multimeshSetBufferInterpolatedBind, renderingServerSingleton, multimesh, buffer, bufferPrevious)
}

/**
 * Allocates and initializes the voxel GI data for the specified `voxel_gi` RID. `octree_cells`
 * must be a multiple of 32. `octree_cells` must be double the size of `data_cells`. The allocated
 * data can be retrieved later using the various `voxel_gi_get_*` methods.
 *
 * Generated from Godot docs: RenderingServer.voxel_gi_allocate_data
 */
fun RenderingServer.voxelGiAllocateData(voxelGi: RID, toCellXform: Transform3D, aabb: AABB, octreeSize: Vector3i, octreeCells: ByteArray, dataCells: ByteArray, distanceField: ByteArray, levelCounts: List<Int>) {
    ObjectCalls.ptrcallWithRIDTransform3DAABBVector3iThreeByteArrayPackedInt32ListArgs(voxelGiAllocateDataBind, renderingServerSingleton, voxelGi, toCellXform, aabb, octreeSize, octreeCells, dataCells, distanceField, levelCounts)
}

/**
 * Returns the octree cell data for the specified voxel GI data instance. See also
 * `voxel_gi_allocate_data`.
 *
 * Generated from Godot docs: RenderingServer.voxel_gi_get_octree_cells
 */
fun RenderingServer.voxelGiGetOctreeCells(voxelGi: RID): ByteArray {
    return ObjectCalls.ptrcallWithRIDArgRetByteArray(voxelGiGetOctreeCellsBind, renderingServerSingleton, voxelGi)
}

/**
 * Returns the data cells for the specified voxel GI data instance. See also
 * `voxel_gi_allocate_data`.
 *
 * Generated from Godot docs: RenderingServer.voxel_gi_get_data_cells
 */
fun RenderingServer.voxelGiGetDataCells(voxelGi: RID): ByteArray {
    return ObjectCalls.ptrcallWithRIDArgRetByteArray(voxelGiGetDataCellsBind, renderingServerSingleton, voxelGi)
}

/**
 * Returns the distance field data for the specified voxel GI data instance. See also
 * `voxel_gi_allocate_data`.
 *
 * Generated from Godot docs: RenderingServer.voxel_gi_get_distance_field
 */
fun RenderingServer.voxelGiGetDistanceField(voxelGi: RID): ByteArray {
    return ObjectCalls.ptrcallWithRIDArgRetByteArray(voxelGiGetDistanceFieldBind, renderingServerSingleton, voxelGi)
}

/**
 * Returns the level counts for the specified voxel GI data instance. See also
 * `voxel_gi_allocate_data`.
 *
 * Generated from Godot docs: RenderingServer.voxel_gi_get_level_counts
 */
fun RenderingServer.voxelGiGetLevelCounts(voxelGi: RID): List<Int> {
    return ObjectCalls.ptrcallWithRIDArgRetPackedInt32List(voxelGiGetLevelCountsBind, renderingServerSingleton, voxelGi)
}

/**
 * Sets the probe capture data for the given lightmap instance. See
 * `lightmap_get_probe_capture_points`, `lightmap_get_probe_capture_sh`,
 * `lightmap_get_probe_capture_tetrahedra`, and `lightmap_get_probe_capture_bsp_tree` for the
 * expected data formats.
 *
 * Generated from Godot docs: RenderingServer.lightmap_set_probe_capture_data
 */
fun RenderingServer.lightmapSetProbeCaptureData(lightmap: RID, points: List<Vector3>, pointSh: List<Color>, tetrahedra: List<Int>, bspTree: List<Int>) {
    ObjectCalls.ptrcallWithRIDPackedVector3ListPackedColorListTwoPackedInt32ListArgs(lightmapSetProbeCaptureDataBind, renderingServerSingleton, lightmap, points, pointSh, tetrahedra, bspTree)
}

/**
 * Returns the local space positions of each lightmap probe capture point. Keep in mind the
 * lightmap instance may have a non-zero transform, which will affect the position of the probe
 * capture points. See also `lightmap_set_probe_capture_data`.
 *
 * Generated from Godot docs: RenderingServer.lightmap_get_probe_capture_points
 */
fun RenderingServer.lightmapGetProbeCapturePoints(lightmap: RID): List<Vector3> {
    return ObjectCalls.ptrcallWithRIDArgRetPackedVector3List(lightmapGetProbeCapturePointsBind, renderingServerSingleton, lightmap)
}

/**
 * Returns the L0, L1, and L2 spherical harmonics
 * (https://en.wikipedia.org/wiki/Spherical_harmonics) data for each lightmap probe capture point.
 * This is specified as 9 `Color` values per probe, which means the size of the returned data is
 * always 9 times the number of probe points. See also `lightmap_set_probe_capture_data`.
 *
 * Generated from Godot docs: RenderingServer.lightmap_get_probe_capture_sh
 */
fun RenderingServer.lightmapGetProbeCaptureSh(lightmap: RID): List<Color> {
    return ObjectCalls.ptrcallWithRIDArgRetPackedColorList(lightmapGetProbeCaptureShBind, renderingServerSingleton, lightmap)
}

/**
 * Returns the tetrahedralization data used for interpolating between lightmap probe capture
 * points. Each tetrahedron is specified as a series of 4 numbers, each being an index into the
 * probe capture points array returned by `lightmap_get_probe_capture_points`. See also
 * `lightmap_set_probe_capture_data`.
 *
 * Generated from Godot docs: RenderingServer.lightmap_get_probe_capture_tetrahedra
 */
fun RenderingServer.lightmapGetProbeCaptureTetrahedra(lightmap: RID): List<Int> {
    return ObjectCalls.ptrcallWithRIDArgRetPackedInt32List(lightmapGetProbeCaptureTetrahedraBind, renderingServerSingleton, lightmap)
}

/**
 * Returns the BSP tree data used for accelerating probe lookups. The BSP data is structured as a
 * series of six signed 32-bit values per BSP node in this order: `float plane_x`, `float plane_y`,
 * `float plane_z`, `float plane_distance`, `int32_t over`, `int32_t under`. An empty leaf is
 * denoted by the value `-2147483648` (the minimum 32-bit signed integer). See also
 * `lightmap_set_probe_capture_data`.
 *
 * Generated from Godot docs: RenderingServer.lightmap_get_probe_capture_bsp_tree
 */
fun RenderingServer.lightmapGetProbeCaptureBspTree(lightmap: RID): List<Int> {
    return ObjectCalls.ptrcallWithRIDArgRetPackedInt32List(lightmapGetProbeCaptureBspTreeBind, renderingServerSingleton, lightmap)
}

/**
 * Sets the trail bind poses for the particle system. This specified as an array of `Transform3D`s
 * representing the bind pose for each draw pass. See `GPUParticles3D.draw_skin`,
 * `Skin.get_bind_count`, and `Skin.get_bind_pose`. Set the value for each draw pass to
 * `Transform3D.IDENTITY` to use the default behavior, which is what built-in trails use
 * (`RibbonTrailMesh` and `TubeTrailMesh`).
 *
 * Generated from Godot docs: RenderingServer.particles_set_trail_bind_poses
 */
fun RenderingServer.particlesSetTrailBindPoses(particles: RID, bindPoses: List<Transform3D>) {
    ObjectCalls.ptrcallWithRIDAndTransform3DListArgs(particlesSetTrailBindPosesBind, renderingServerSingleton, particles, bindPoses)
}

/**
 * Sets the mesh data for the given occluder RID, which controls the shape of the occlusion culling
 * that will be performed.
 *
 * Generated from Godot docs: RenderingServer.occluder_set_mesh
 */
fun RenderingServer.occluderSetMesh(occluder: RID, vertices: List<Vector3>, indices: List<Int>) {
    ObjectCalls.ptrcallWithRIDPackedVector3ListPackedInt32ListArgs(occluderSetMeshBind, renderingServerSingleton, occluder, vertices, indices)
}

/**
 * Sets the compositor effects for the specified compositor RID. `effects` should be an array
 * containing RIDs created with `compositor_effect_create`.
 *
 * Generated from Godot docs: RenderingServer.compositor_set_compositor_effects
 */
fun RenderingServer.compositorSetCompositorEffects(compositor: RID, effects: List<RID>) {
    ObjectCalls.ptrcallWithRIDAndRIDListArgs(compositorSetCompositorEffectsBind, renderingServerSingleton, compositor, effects)
}

/**
 * Configures glow for the specified environment RID. See `glow_*` properties in `Environment` for
 * more information.
 *
 * Generated from Godot docs: RenderingServer.environment_set_glow
 */
fun RenderingServer.environmentSetGlow(env: RID, enable: Boolean, levels: List<Float>, intensity: Double, strength: Double, mix: Double, bloomThreshold: Double, blendMode: Long, hdrBleedThreshold: Double, hdrBleedScale: Double, hdrLuminanceCap: Double, glowMapStrength: Double, glowMap: RID) {
    ObjectCalls.ptrcallWithRIDBoolPackedFloat32ListFourDoubleLongFourDoubleRIDArgs(environmentSetGlowBind, renderingServerSingleton, env, enable, levels, intensity, strength, mix, bloomThreshold, blendMode, hdrBleedThreshold, hdrBleedScale, hdrLuminanceCap, glowMapStrength, glowMap)
}

/**
 * Sets the per-instance shader uniform on the specified 3D geometry instance. Equivalent to
 * `GeometryInstance3D.set_instance_shader_parameter`.
 *
 * Generated from Godot docs: RenderingServer.instance_geometry_set_shader_parameter
 */
fun RenderingServer.instanceGeometrySetShaderParameter(instance: RID, parameter: String, value: Any?) {
    ObjectCalls.ptrcallWithRIDStringNameAndVariantArgs(instanceGeometrySetShaderParameterBind, renderingServerSingleton, instance, parameter, value)
}

/**
 * Returns the value of the per-instance shader uniform from the specified 3D geometry instance.
 * Equivalent to `GeometryInstance3D.get_instance_shader_parameter`. Note: Per-instance shader
 * parameter names are case-sensitive.
 *
 * Generated from Godot docs: RenderingServer.instance_geometry_get_shader_parameter
 */
fun RenderingServer.instanceGeometryGetShaderParameter(instance: RID, parameter: String): Any? {
    return ObjectCalls.ptrcallWithRIDAndStringNameArgRetVariantScalar(instanceGeometryGetShaderParameterBind, renderingServerSingleton, instance, parameter)
}

/**
 * Returns the default value of the per-instance shader uniform from the specified 3D geometry
 * instance. Equivalent to `GeometryInstance3D.get_instance_shader_parameter`.
 *
 * Generated from Godot docs: RenderingServer.instance_geometry_get_shader_parameter_default_value
 */
fun RenderingServer.instanceGeometryGetShaderParameterDefaultValue(instance: RID, parameter: String): Any? {
    return ObjectCalls.ptrcallWithRIDAndStringNameArgRetVariantScalar(instanceGeometryGetShaderParameterDefaultValueBind, renderingServerSingleton, instance, parameter)
}

/**
 * Returns a dictionary of per-instance shader uniform names of the per-instance shader uniform
 * from the specified 3D geometry instance. The returned dictionary is in PropertyInfo format, with
 * the keys `name`, `class_name`, `type`, `hint`, `hint_string` and `usage`. Equivalent to
 * `GeometryInstance3D.get_instance_shader_parameter`.
 *
 * Generated from Godot docs: RenderingServer.instance_geometry_get_shader_parameter_list
 */
fun RenderingServer.instanceGeometryGetShaderParameterList(instance: RID): List<Map<String, Any?>> {
    return ObjectCalls.ptrcallWithRIDArgRetDictionaryList(instanceGeometryGetShaderParameterListBind, renderingServerSingleton, instance)
}

/**
 * Returns an array of object IDs intersecting with the provided AABB. Only 3D nodes that inherit
 * from `VisualInstance3D` are considered, such as `MeshInstance3D` or `DirectionalLight3D`. Use
 * `@GlobalScope.instance_from_id` to obtain the actual nodes. A scenario RID must be provided,
 * which is available in the `World3D` you want to query. This forces an update for all resources
 * queued to update. Warning: This function is primarily intended for editor usage. For in-game use
 * cases, prefer physics collision.
 *
 * Generated from Godot docs: RenderingServer.instances_cull_aabb
 */
fun RenderingServer.instancesCullAabb(aabb: AABB, scenario: RID): List<Long> {
    return ObjectCalls.ptrcallWithAABBRIDArgsRetPackedInt64List(instancesCullAabbBind, renderingServerSingleton, aabb, scenario)
}

/**
 * Returns an array of object IDs intersecting with the provided 3D ray. Only 3D nodes that inherit
 * from `VisualInstance3D` are considered, such as `MeshInstance3D` or `DirectionalLight3D`. Use
 * `@GlobalScope.instance_from_id` to obtain the actual nodes. A scenario RID must be provided,
 * which is available in the `World3D` you want to query. This forces an update for all resources
 * queued to update. Warning: This function is primarily intended for editor usage. For in-game use
 * cases, prefer physics collision.
 *
 * Generated from Godot docs: RenderingServer.instances_cull_ray
 */
fun RenderingServer.instancesCullRay(from: Vector3, to: Vector3, scenario: RID): List<Long> {
    return ObjectCalls.ptrcallWithTwoVector3RIDArgsRetPackedInt64List(instancesCullRayBind, renderingServerSingleton, from, to, scenario)
}

/**
 * Returns an array of object IDs intersecting with the provided convex shape. Only 3D nodes that
 * inherit from `VisualInstance3D` are considered, such as `MeshInstance3D` or
 * `DirectionalLight3D`. Use `@GlobalScope.instance_from_id` to obtain the actual nodes. A scenario
 * RID must be provided, which is available in the `World3D` you want to query. This forces an
 * update for all resources queued to update. Warning: This function is primarily intended for
 * editor usage. For in-game use cases, prefer physics collision.
 *
 * Generated from Godot docs: RenderingServer.instances_cull_convex
 */
fun RenderingServer.instancesCullConvex(convex: List<Plane>, scenario: RID): List<Long> {
    return ObjectCalls.ptrcallWithPlaneListAndRIDArgsRetPackedInt64List(instancesCullConvexBind, renderingServerSingleton, convex, scenario)
}

/**
 * Bakes the material data of the Mesh passed in the `base` parameter with optional
 * `material_overrides` to a set of `Image`s of size `image_size`. Returns an array of `Image`s
 * containing material properties as specified in `BakeChannels`.
 *
 * Generated from Godot docs: RenderingServer.bake_render_uv2
 */
fun RenderingServer.bakeRenderUv2(base: RID, materialOverrides: List<RID>, imageSize: Vector2i): List<Image> {
    return ObjectCalls.ptrcallWithRIDRIDListVector2iArgsRetTypedObjectList(bakeRenderUv2Bind, renderingServerSingleton, base, materialOverrides, imageSize, Image::fromHandle)
}

/**
 * Draws a 2D polyline on the `CanvasItem` pointed to by the `item` `RID`. See also
 * `CanvasItem.draw_polyline` and `CanvasItem.draw_polyline_colors`.
 *
 * Generated from Godot docs: RenderingServer.canvas_item_add_polyline
 */
fun RenderingServer.canvasItemAddPolyline(item: RID, points: List<Vector2>, colors: List<Color>, width: Double = -1.0, antialiased: Boolean = false) {
    ObjectCalls.ptrcallWithRIDPackedVector2ListPackedColorListDoubleAndBoolArgs(canvasItemAddPolylineBind, renderingServerSingleton, item, points, colors, width, antialiased)
}

/**
 * Draws a 2D multiline on the `CanvasItem` pointed to by the `item` `RID`. See also
 * `CanvasItem.draw_multiline` and `CanvasItem.draw_multiline_colors`.
 *
 * Generated from Godot docs: RenderingServer.canvas_item_add_multiline
 */
fun RenderingServer.canvasItemAddMultiline(item: RID, points: List<Vector2>, colors: List<Color>, width: Double = -1.0, antialiased: Boolean = false) {
    ObjectCalls.ptrcallWithRIDPackedVector2ListPackedColorListDoubleAndBoolArgs(canvasItemAddMultilineBind, renderingServerSingleton, item, points, colors, width, antialiased)
}

/**
 * Draws a 2D primitive on the `CanvasItem` pointed to by the `item` `RID`. See also
 * `CanvasItem.draw_primitive`.
 *
 * Generated from Godot docs: RenderingServer.canvas_item_add_primitive
 */
fun RenderingServer.canvasItemAddPrimitive(item: RID, points: List<Vector2>, colors: List<Color>, uvs: List<Vector2>, texture: RID) {
    ObjectCalls.ptrcallWithRIDPackedVector2ListPackedColorListPackedVector2ListAndRIDArgs(canvasItemAddPrimitiveBind, renderingServerSingleton, item, points, colors, uvs, texture)
}

/**
 * Draws a 2D polygon on the `CanvasItem` pointed to by the `item` `RID`. If you need more
 * flexibility (such as being able to use bones), use `canvas_item_add_triangle_array` instead. See
 * also `CanvasItem.draw_polygon`. Note: If you frequently redraw the same polygon with a large
 * number of vertices, consider pre-calculating the triangulation with
 * `Geometry2D.triangulate_polygon` and using `CanvasItem.draw_mesh`, `CanvasItem.draw_multimesh`,
 * or `canvas_item_add_triangle_array`.
 *
 * Generated from Godot docs: RenderingServer.canvas_item_add_polygon
 */
fun RenderingServer.canvasItemAddPolygon(item: RID, points: List<Vector2>, colors: List<Color>, uvs: List<Vector2>, texture: RID) {
    ObjectCalls.ptrcallWithRIDPackedVector2ListPackedColorListPackedVector2ListAndRIDArgs(canvasItemAddPolygonBind, renderingServerSingleton, item, points, colors, uvs, texture)
}

/**
 * Draws a triangle array on the `CanvasItem` pointed to by the `item` `RID`. This is internally
 * used by `Line2D` and `StyleBoxFlat` for rendering. `canvas_item_add_triangle_array` is highly
 * flexible, but more complex to use than `canvas_item_add_polygon`. Note: If `count` is set to a
 * non-negative value, only the first `count * 3` indices (corresponding to `count` triangles) will
 * be drawn. Otherwise, all indices are drawn.
 *
 * Generated from Godot docs: RenderingServer.canvas_item_add_triangle_array
 */
fun RenderingServer.canvasItemAddTriangleArray(item: RID, indices: List<Int>, points: List<Vector2>, colors: List<Color>, uvs: List<Vector2>, bones: List<Int>, weights: List<Float>, texture: RID, count: Int = -1) {
    ObjectCalls.ptrcallWithRIDPackedInt32ListPackedVector2ListPackedColorListPackedVector2ListPackedInt32ListPackedFloat32ListRIDIntArgs(canvasItemAddTriangleArrayBind, renderingServerSingleton, item, indices, points, colors, uvs, bones, weights, texture, count)
}

/**
 * Sets the per-instance shader uniform on the specified canvas item instance. Equivalent to
 * `CanvasItem.set_instance_shader_parameter`.
 *
 * Generated from Godot docs: RenderingServer.canvas_item_set_instance_shader_parameter
 */
fun RenderingServer.canvasItemSetInstanceShaderParameter(instance: RID, parameter: String, value: Any?) {
    ObjectCalls.ptrcallWithRIDStringNameAndVariantArgs(canvasItemSetInstanceShaderParameterBind, renderingServerSingleton, instance, parameter, value)
}

/**
 * Returns the value of the per-instance shader uniform from the specified canvas item instance.
 * Equivalent to `CanvasItem.get_instance_shader_parameter`.
 *
 * Generated from Godot docs: RenderingServer.canvas_item_get_instance_shader_parameter
 */
fun RenderingServer.canvasItemGetInstanceShaderParameter(instance: RID, parameter: String): Any? {
    return ObjectCalls.ptrcallWithRIDAndStringNameArgRetVariantScalar(canvasItemGetInstanceShaderParameterBind, renderingServerSingleton, instance, parameter)
}

/**
 * Returns the default value of the per-instance shader uniform from the specified canvas item
 * instance. Equivalent to `CanvasItem.get_instance_shader_parameter`.
 *
 * Generated from Godot docs: RenderingServer.canvas_item_get_instance_shader_parameter_default_value
 */
fun RenderingServer.canvasItemGetInstanceShaderParameterDefaultValue(instance: RID, parameter: String): Any? {
    return ObjectCalls.ptrcallWithRIDAndStringNameArgRetVariantScalar(canvasItemGetInstanceShaderParameterDefaultValueBind, renderingServerSingleton, instance, parameter)
}

/**
 * Returns a dictionary of per-instance shader uniform names of the per-instance shader uniform
 * from the specified canvas item instance. The returned dictionary is in PropertyInfo format, with
 * the keys `name`, `class_name`, `type`, `hint`, `hint_string`, and `usage`.
 *
 * Generated from Godot docs: RenderingServer.canvas_item_get_instance_shader_parameter_list
 */
fun RenderingServer.canvasItemGetInstanceShaderParameterList(instance: RID): List<Map<String, Any?>> {
    return ObjectCalls.ptrcallWithRIDArgRetDictionaryList(canvasItemGetInstanceShaderParameterListBind, renderingServerSingleton, instance)
}

/**
 * Sets the shape of the occluder polygon.
 *
 * Generated from Godot docs: RenderingServer.canvas_occluder_polygon_set_shape
 */
fun RenderingServer.canvasOccluderPolygonSetShape(occluderPolygon: RID, shape: List<Vector2>, closed: Boolean) {
    ObjectCalls.ptrcallWithRIDPackedVector2ListAndBoolArg(canvasOccluderPolygonSetShapeBind, renderingServerSingleton, occluderPolygon, shape, closed)
}

/**
 * Creates a new global shader uniform. Note: Global shader parameter names are case-sensitive.
 *
 * Generated from Godot docs: RenderingServer.global_shader_parameter_add
 */
fun RenderingServer.globalShaderParameterAdd(name: String, type: Long, defaultValue: Any?) {
    ObjectCalls.ptrcallWithStringNameLongVariantArgs(globalShaderParameterAddBind, renderingServerSingleton, name, type, defaultValue)
}

/**
 * Sets the global shader uniform `name` to `value`.
 *
 * Generated from Godot docs: RenderingServer.global_shader_parameter_set
 */
fun RenderingServer.globalShaderParameterSet(name: String, value: Any?) {
    ObjectCalls.ptrcallWithStringNameAndVariantArg(globalShaderParameterSetBind, renderingServerSingleton, name, value)
}

/**
 * Overrides the global shader uniform `name` with `value`. Equivalent to the
 * `ShaderGlobalsOverride` node.
 *
 * Generated from Godot docs: RenderingServer.global_shader_parameter_set_override
 */
fun RenderingServer.globalShaderParameterSetOverride(name: String, value: Any?) {
    ObjectCalls.ptrcallWithStringNameAndVariantArg(globalShaderParameterSetOverrideBind, renderingServerSingleton, name, value)
}

private val renderingServerSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("RenderingServer")
}

private const val TEXTURE_2D_LAYERED_CREATE_HASH = 913689023L
private val texture2dLayeredCreateBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "texture_2d_layered_create", TEXTURE_2D_LAYERED_CREATE_HASH)
}

private const val TEXTURE_3D_CREATE_HASH = 4036838706L
private val texture3dCreateBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "texture_3d_create", TEXTURE_3D_CREATE_HASH)
}

private const val TEXTURE_3D_UPDATE_HASH = 684822712L
private val texture3dUpdateBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "texture_3d_update", TEXTURE_3D_UPDATE_HASH)
}

private const val TEXTURE_DRAWABLE_BLIT_RECT_HASH = 4077763890L
private val textureDrawableBlitRectBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "texture_drawable_blit_rect", TEXTURE_DRAWABLE_BLIT_RECT_HASH)
}

private const val TEXTURE_3D_GET_HASH = 2684255073L
private val texture3dGetBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "texture_3d_get", TEXTURE_3D_GET_HASH)
}

private const val TEXTURE_GET_PATH_HASH = 642473191L
private val textureGetPathBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "texture_get_path", TEXTURE_GET_PATH_HASH)
}

private const val SHADER_GET_CODE_HASH = 642473191L
private val shaderGetCodeBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "shader_get_code", SHADER_GET_CODE_HASH)
}

private const val GET_SHADER_PARAMETER_LIST_HASH = 2684255073L
private val getShaderParameterListBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "get_shader_parameter_list", GET_SHADER_PARAMETER_LIST_HASH)
}

private const val SHADER_GET_PARAMETER_DEFAULT_HASH = 2621281810L
private val shaderGetParameterDefaultBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "shader_get_parameter_default", SHADER_GET_PARAMETER_DEFAULT_HASH)
}

private const val MATERIAL_SET_PARAM_HASH = 3477296213L
private val materialSetParamBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "material_set_param", MATERIAL_SET_PARAM_HASH)
}

private const val MATERIAL_GET_PARAM_HASH = 2621281810L
private val materialGetParamBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "material_get_param", MATERIAL_GET_PARAM_HASH)
}

private const val MESH_CREATE_FROM_SURFACES_HASH = 4291747531L
private val meshCreateFromSurfacesBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "mesh_create_from_surfaces", MESH_CREATE_FROM_SURFACES_HASH)
}

private const val MESH_ADD_SURFACE_HASH = 1217542888L
private val meshAddSurfaceBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "mesh_add_surface", MESH_ADD_SURFACE_HASH)
}

private const val MESH_ADD_SURFACE_FROM_ARRAYS_HASH = 2342446560L
private val meshAddSurfaceFromArraysBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "mesh_add_surface_from_arrays", MESH_ADD_SURFACE_FROM_ARRAYS_HASH)
}

private const val MESH_GET_SURFACE_HASH = 186674697L
private val meshGetSurfaceBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "mesh_get_surface", MESH_GET_SURFACE_HASH)
}

private const val MESH_SURFACE_GET_ARRAYS_HASH = 1778388067L
private val meshSurfaceGetArraysBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "mesh_surface_get_arrays", MESH_SURFACE_GET_ARRAYS_HASH)
}

private const val MESH_SURFACE_GET_BLEND_SHAPE_ARRAYS_HASH = 1778388067L
private val meshSurfaceGetBlendShapeArraysBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "mesh_surface_get_blend_shape_arrays", MESH_SURFACE_GET_BLEND_SHAPE_ARRAYS_HASH)
}

private const val MESH_SURFACE_UPDATE_VERTEX_REGION_HASH = 2900195149L
private val meshSurfaceUpdateVertexRegionBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "mesh_surface_update_vertex_region", MESH_SURFACE_UPDATE_VERTEX_REGION_HASH)
}

private const val MESH_SURFACE_UPDATE_ATTRIBUTE_REGION_HASH = 2900195149L
private val meshSurfaceUpdateAttributeRegionBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "mesh_surface_update_attribute_region", MESH_SURFACE_UPDATE_ATTRIBUTE_REGION_HASH)
}

private const val MESH_SURFACE_UPDATE_SKIN_REGION_HASH = 2900195149L
private val meshSurfaceUpdateSkinRegionBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "mesh_surface_update_skin_region", MESH_SURFACE_UPDATE_SKIN_REGION_HASH)
}

private const val MESH_SURFACE_UPDATE_INDEX_REGION_HASH = 2900195149L
private val meshSurfaceUpdateIndexRegionBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "mesh_surface_update_index_region", MESH_SURFACE_UPDATE_INDEX_REGION_HASH)
}

private const val MULTIMESH_SET_BUFFER_HASH = 2960552364L
private val multimeshSetBufferBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "multimesh_set_buffer", MULTIMESH_SET_BUFFER_HASH)
}

private const val MULTIMESH_GET_BUFFER_HASH = 3964669176L
private val multimeshGetBufferBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "multimesh_get_buffer", MULTIMESH_GET_BUFFER_HASH)
}

private const val MULTIMESH_SET_BUFFER_INTERPOLATED_HASH = 659844711L
private val multimeshSetBufferInterpolatedBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "multimesh_set_buffer_interpolated", MULTIMESH_SET_BUFFER_INTERPOLATED_HASH)
}

private const val VOXEL_GI_ALLOCATE_DATA_HASH = 4108223027L
private val voxelGiAllocateDataBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "voxel_gi_allocate_data", VOXEL_GI_ALLOCATE_DATA_HASH)
}

private const val VOXEL_GI_GET_OCTREE_CELLS_HASH = 3348040486L
private val voxelGiGetOctreeCellsBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "voxel_gi_get_octree_cells", VOXEL_GI_GET_OCTREE_CELLS_HASH)
}

private const val VOXEL_GI_GET_DATA_CELLS_HASH = 3348040486L
private val voxelGiGetDataCellsBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "voxel_gi_get_data_cells", VOXEL_GI_GET_DATA_CELLS_HASH)
}

private const val VOXEL_GI_GET_DISTANCE_FIELD_HASH = 3348040486L
private val voxelGiGetDistanceFieldBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "voxel_gi_get_distance_field", VOXEL_GI_GET_DISTANCE_FIELD_HASH)
}

private const val VOXEL_GI_GET_LEVEL_COUNTS_HASH = 788230395L
private val voxelGiGetLevelCountsBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "voxel_gi_get_level_counts", VOXEL_GI_GET_LEVEL_COUNTS_HASH)
}

private const val LIGHTMAP_SET_PROBE_CAPTURE_DATA_HASH = 3217845880L
private val lightmapSetProbeCaptureDataBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "lightmap_set_probe_capture_data", LIGHTMAP_SET_PROBE_CAPTURE_DATA_HASH)
}

private const val LIGHTMAP_GET_PROBE_CAPTURE_POINTS_HASH = 808965560L
private val lightmapGetProbeCapturePointsBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "lightmap_get_probe_capture_points", LIGHTMAP_GET_PROBE_CAPTURE_POINTS_HASH)
}

private const val LIGHTMAP_GET_PROBE_CAPTURE_SH_HASH = 1569415609L
private val lightmapGetProbeCaptureShBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "lightmap_get_probe_capture_sh", LIGHTMAP_GET_PROBE_CAPTURE_SH_HASH)
}

private const val LIGHTMAP_GET_PROBE_CAPTURE_TETRAHEDRA_HASH = 788230395L
private val lightmapGetProbeCaptureTetrahedraBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "lightmap_get_probe_capture_tetrahedra", LIGHTMAP_GET_PROBE_CAPTURE_TETRAHEDRA_HASH)
}

private const val LIGHTMAP_GET_PROBE_CAPTURE_BSP_TREE_HASH = 788230395L
private val lightmapGetProbeCaptureBspTreeBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "lightmap_get_probe_capture_bsp_tree", LIGHTMAP_GET_PROBE_CAPTURE_BSP_TREE_HASH)
}

private const val PARTICLES_SET_TRAIL_BIND_POSES_HASH = 684822712L
private val particlesSetTrailBindPosesBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "particles_set_trail_bind_poses", PARTICLES_SET_TRAIL_BIND_POSES_HASH)
}

private const val OCCLUDER_SET_MESH_HASH = 3854404263L
private val occluderSetMeshBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "occluder_set_mesh", OCCLUDER_SET_MESH_HASH)
}

private const val COMPOSITOR_SET_COMPOSITOR_EFFECTS_HASH = 684822712L
private val compositorSetCompositorEffectsBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "compositor_set_compositor_effects", COMPOSITOR_SET_COMPOSITOR_EFFECTS_HASH)
}

private const val ENVIRONMENT_SET_GLOW_HASH = 2421724940L
private val environmentSetGlowBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "environment_set_glow", ENVIRONMENT_SET_GLOW_HASH)
}

private const val INSTANCE_GEOMETRY_SET_SHADER_PARAMETER_HASH = 3477296213L
private val instanceGeometrySetShaderParameterBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "instance_geometry_set_shader_parameter", INSTANCE_GEOMETRY_SET_SHADER_PARAMETER_HASH)
}

private const val INSTANCE_GEOMETRY_GET_SHADER_PARAMETER_HASH = 2621281810L
private val instanceGeometryGetShaderParameterBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "instance_geometry_get_shader_parameter", INSTANCE_GEOMETRY_GET_SHADER_PARAMETER_HASH)
}

private const val INSTANCE_GEOMETRY_GET_SHADER_PARAMETER_DEFAULT_VALUE_HASH = 2621281810L
private val instanceGeometryGetShaderParameterDefaultValueBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "instance_geometry_get_shader_parameter_default_value", INSTANCE_GEOMETRY_GET_SHADER_PARAMETER_DEFAULT_VALUE_HASH)
}

private const val INSTANCE_GEOMETRY_GET_SHADER_PARAMETER_LIST_HASH = 2684255073L
private val instanceGeometryGetShaderParameterListBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "instance_geometry_get_shader_parameter_list", INSTANCE_GEOMETRY_GET_SHADER_PARAMETER_LIST_HASH)
}

private const val INSTANCES_CULL_AABB_HASH = 2570105777L
private val instancesCullAabbBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "instances_cull_aabb", INSTANCES_CULL_AABB_HASH)
}

private const val INSTANCES_CULL_RAY_HASH = 2208759584L
private val instancesCullRayBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "instances_cull_ray", INSTANCES_CULL_RAY_HASH)
}

private const val INSTANCES_CULL_CONVEX_HASH = 2488539944L
private val instancesCullConvexBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "instances_cull_convex", INSTANCES_CULL_CONVEX_HASH)
}

private const val BAKE_RENDER_UV2_HASH = 1904608558L
private val bakeRenderUv2Bind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "bake_render_uv2", BAKE_RENDER_UV2_HASH)
}

private const val CANVAS_ITEM_ADD_POLYLINE_HASH = 3098767073L
private val canvasItemAddPolylineBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_polyline", CANVAS_ITEM_ADD_POLYLINE_HASH)
}

private const val CANVAS_ITEM_ADD_MULTILINE_HASH = 3098767073L
private val canvasItemAddMultilineBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_multiline", CANVAS_ITEM_ADD_MULTILINE_HASH)
}

private const val CANVAS_ITEM_ADD_PRIMITIVE_HASH = 3731601077L
private val canvasItemAddPrimitiveBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_primitive", CANVAS_ITEM_ADD_PRIMITIVE_HASH)
}

private const val CANVAS_ITEM_ADD_POLYGON_HASH = 3580000528L
private val canvasItemAddPolygonBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_polygon", CANVAS_ITEM_ADD_POLYGON_HASH)
}

private const val CANVAS_ITEM_ADD_TRIANGLE_ARRAY_HASH = 660261329L
private val canvasItemAddTriangleArrayBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_triangle_array", CANVAS_ITEM_ADD_TRIANGLE_ARRAY_HASH)
}

private const val CANVAS_ITEM_SET_INSTANCE_SHADER_PARAMETER_HASH = 3477296213L
private val canvasItemSetInstanceShaderParameterBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_instance_shader_parameter", CANVAS_ITEM_SET_INSTANCE_SHADER_PARAMETER_HASH)
}

private const val CANVAS_ITEM_GET_INSTANCE_SHADER_PARAMETER_HASH = 2621281810L
private val canvasItemGetInstanceShaderParameterBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "canvas_item_get_instance_shader_parameter", CANVAS_ITEM_GET_INSTANCE_SHADER_PARAMETER_HASH)
}

private const val CANVAS_ITEM_GET_INSTANCE_SHADER_PARAMETER_DEFAULT_VALUE_HASH = 2621281810L
private val canvasItemGetInstanceShaderParameterDefaultValueBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "canvas_item_get_instance_shader_parameter_default_value", CANVAS_ITEM_GET_INSTANCE_SHADER_PARAMETER_DEFAULT_VALUE_HASH)
}

private const val CANVAS_ITEM_GET_INSTANCE_SHADER_PARAMETER_LIST_HASH = 2684255073L
private val canvasItemGetInstanceShaderParameterListBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "canvas_item_get_instance_shader_parameter_list", CANVAS_ITEM_GET_INSTANCE_SHADER_PARAMETER_LIST_HASH)
}

private const val CANVAS_OCCLUDER_POLYGON_SET_SHAPE_HASH = 2103882027L
private val canvasOccluderPolygonSetShapeBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "canvas_occluder_polygon_set_shape", CANVAS_OCCLUDER_POLYGON_SET_SHAPE_HASH)
}

private const val GLOBAL_SHADER_PARAMETER_ADD_HASH = 463390080L
private val globalShaderParameterAddBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "global_shader_parameter_add", GLOBAL_SHADER_PARAMETER_ADD_HASH)
}

private const val GLOBAL_SHADER_PARAMETER_SET_HASH = 3776071444L
private val globalShaderParameterSetBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "global_shader_parameter_set", GLOBAL_SHADER_PARAMETER_SET_HASH)
}

private const val GLOBAL_SHADER_PARAMETER_SET_OVERRIDE_HASH = 3776071444L
private val globalShaderParameterSetOverrideBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "global_shader_parameter_set_override", GLOBAL_SHADER_PARAMETER_SET_OVERRIDE_HASH)
}
