package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector2i

// GENERATED desktop/Android companion for RenderingServer (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP RenderingServer waits on: ptrcallWithDictionaryListIntArgsRetRID,
//   ptrcallWithRIDAndDictionaryArg, ptrcallWithRIDAndIntArgRetArrayList,
//   ptrcallWithRIDArgRetTypedObjectList, ptrcallWithRIDLongTwoArrayDictionaryLongArgs,
//   ptrcallWithRIDRIDListVector2iArgsRetTypedObjectList, ptrcallWithRIDStringNameAndVariantArgs,
//   ptrcallWithStringNameAndVariantArg, ptrcallWithStringNameLongVariantArgs
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns 3D texture data as an array of `Image`s for the specified texture `RID`.
 *
 * Generated from Godot docs: RenderingServer.texture_3d_get
 */
fun RenderingServer.texture3dGet(texture: RID): List<Image> {
    return ObjectCalls.ptrcallWithRIDArgRetTypedObjectList(texture3dGetBind, renderingServerSingleton, texture, Image::fromHandle)
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
 * Returns a mesh's surface's arrays for blend shapes.
 *
 * Generated from Godot docs: RenderingServer.mesh_surface_get_blend_shape_arrays
 */
fun RenderingServer.meshSurfaceGetBlendShapeArrays(mesh: RID, surface: Int): List<List<Any?>> {
    return ObjectCalls.ptrcallWithRIDAndIntArgRetArrayList(meshSurfaceGetBlendShapeArraysBind, renderingServerSingleton, mesh, surface)
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
 * Sets the per-instance shader uniform on the specified canvas item instance. Equivalent to
 * `CanvasItem.set_instance_shader_parameter`.
 *
 * Generated from Godot docs: RenderingServer.canvas_item_set_instance_shader_parameter
 */
fun RenderingServer.canvasItemSetInstanceShaderParameter(instance: RID, parameter: String, value: Any?) {
    ObjectCalls.ptrcallWithRIDStringNameAndVariantArgs(canvasItemSetInstanceShaderParameterBind, renderingServerSingleton, instance, parameter, value)
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

private const val TEXTURE_3D_GET_HASH = 2684255073L
private val texture3dGetBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "texture_3d_get", TEXTURE_3D_GET_HASH)
}

private const val MATERIAL_SET_PARAM_HASH = 3477296213L
private val materialSetParamBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "material_set_param", MATERIAL_SET_PARAM_HASH)
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

private const val MESH_SURFACE_GET_BLEND_SHAPE_ARRAYS_HASH = 1778388067L
private val meshSurfaceGetBlendShapeArraysBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "mesh_surface_get_blend_shape_arrays", MESH_SURFACE_GET_BLEND_SHAPE_ARRAYS_HASH)
}

private const val INSTANCE_GEOMETRY_SET_SHADER_PARAMETER_HASH = 3477296213L
private val instanceGeometrySetShaderParameterBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "instance_geometry_set_shader_parameter", INSTANCE_GEOMETRY_SET_SHADER_PARAMETER_HASH)
}

private const val BAKE_RENDER_UV2_HASH = 1904608558L
private val bakeRenderUv2Bind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "bake_render_uv2", BAKE_RENDER_UV2_HASH)
}

private const val CANVAS_ITEM_SET_INSTANCE_SHADER_PARAMETER_HASH = 3477296213L
private val canvasItemSetInstanceShaderParameterBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_instance_shader_parameter", CANVAS_ITEM_SET_INSTANCE_SHADER_PARAMETER_HASH)
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
