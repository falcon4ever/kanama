package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Transform3D
import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName

/**
 * `Mesh` type that provides utility for constructing a surface from arrays.
 *
 * Generated from Godot docs: ArrayMesh
 */
class ArrayMesh(handle: MemorySegment) : Mesh(handle) {
    var blendShapeMode: Long
        @JvmName("blendShapeModeProperty")
        get() = getBlendShapeMode()
        @JvmName("setBlendShapeModeProperty")
        set(value) = setBlendShapeMode(value)

    var customAabb: AABB
        @JvmName("customAabbProperty")
        get() = getCustomAabb()
        @JvmName("setCustomAabbProperty")
        set(value) = setCustomAabb(value)

    var shadowMesh: ArrayMesh?
        @JvmName("shadowMeshProperty")
        get() = getShadowMesh()
        @JvmName("setShadowMeshProperty")
        set(value) = setShadowMesh(value)

    /**
     * Adds name for a blend shape that will be added with `add_surface_from_arrays`. Must be called
     * before surface is added.
     *
     * Generated from Godot docs: ArrayMesh.add_blend_shape
     */
    fun addBlendShape(name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringNameArg(addBlendShapeBind, handle, name)
    }

    /**
     * Returns the number of blend shapes that the `ArrayMesh` holds.
     *
     * Generated from Godot docs: ArrayMesh.get_blend_shape_count
     */
    fun getBlendShapeCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getBlendShapeCountBind, handle)
    }

    /**
     * Returns the name of the blend shape at this index.
     *
     * Generated from Godot docs: ArrayMesh.get_blend_shape_name
     */
    fun getBlendShapeName(index: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetStringName(getBlendShapeNameBind, handle, index)
    }

    /**
     * Sets the name of the blend shape at this index.
     *
     * Generated from Godot docs: ArrayMesh.set_blend_shape_name
     */
    fun setBlendShapeName(index: Int, name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndStringNameArg(setBlendShapeNameBind, handle, index, name)
    }

    /**
     * Removes all blend shapes from this `ArrayMesh`.
     *
     * Generated from Godot docs: ArrayMesh.clear_blend_shapes
     */
    fun clearBlendShapes() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(clearBlendShapesBind, handle)
    }

    /**
     * The blend shape mode.
     *
     * Generated from Godot docs: ArrayMesh.set_blend_shape_mode
     */
    fun setBlendShapeMode(mode: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setBlendShapeModeBind, handle, mode)
    }

    /**
     * The blend shape mode.
     *
     * Generated from Godot docs: ArrayMesh.get_blend_shape_mode
     */
    fun getBlendShapeMode(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getBlendShapeModeBind, handle)
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
     * Generated from Godot docs: ArrayMesh.add_surface_from_arrays
     */
    fun addSurfaceFromArrays(primitive: Long, arrays: List<Any?>, blendShapes: List<List<Any?>>, lods: Map<String, Any?> = emptyMap(), flags: Long = 0L) {
        ObjectCalls.ptrcallWithLongArrayArrayListDictionaryLongArgs(addSurfaceFromArraysBind, handle, primitive, arrays, blendShapes, lods, flags)
    }

    /**
     * Removes all surfaces from this `ArrayMesh`.
     *
     * Generated from Godot docs: ArrayMesh.clear_surfaces
     */
    fun clearSurfaces() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(clearSurfacesBind, handle)
    }

    /**
     * Removes the surface at the given index from the Mesh, shifting surfaces with higher index down
     * by one.
     *
     * Generated from Godot docs: ArrayMesh.surface_remove
     */
    fun surfaceRemove(surfIdx: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(surfaceRemoveBind, handle, surfIdx)
    }

    /**
     * Updates the vertex buffer of this mesh's surface with the given `data`. The expected data per
     * vertex is 12 or 8 bytes (4 bytes per float, 2 floats per `Vector2`, and 3 floats per `Vector3`)
     * depending on if the mesh is using `Vector3` or `Vector2` vertices. This value can be determined
     * with `RenderingServer.mesh_surface_get_format_vertex_stride`. The starting point of the updates
     * can be changed with `offset`. The value of `offset` should be a multiple of 12 bytes in most
     * cases to align to each vertex. A `PackedVector3Array` of vertex locations can be converted into
     * a `PackedByteArray` using `PackedVector3Array.to_byte_array` for use in `data`.
     *
     * Generated from Godot docs: ArrayMesh.surface_update_vertex_region
     */
    fun surfaceUpdateVertexRegion(surfIdx: Int, offset: Int, data: ByteArray) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntAndByteArrayArg(surfaceUpdateVertexRegionBind, handle, surfIdx, offset, data)
    }

    /**
     * Updates the attribute buffer of this mesh's surface with the given `data`. The expected data per
     * attribute is 12 or 8 bytes (4 bytes per float, 2 floats per `Vector2`, and 3 floats per
     * `Vector3`) depending on if the mesh is using `Vector3` or `Vector2` vertices. This value can be
     * determined with `RenderingServer.mesh_surface_get_format_attribute_stride`. The starting point
     * of the updates can be changed with `offset`. The value of `offset` should be a multiple of 12
     * bytes in most cases to align to each attribute. A `PackedVector3Array` of attribute locations
     * can be converted into a `PackedByteArray` using `PackedVector3Array.to_byte_array` for use in
     * `data`.
     *
     * Generated from Godot docs: ArrayMesh.surface_update_attribute_region
     */
    fun surfaceUpdateAttributeRegion(surfIdx: Int, offset: Int, data: ByteArray) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntAndByteArrayArg(surfaceUpdateAttributeRegionBind, handle, surfIdx, offset, data)
    }

    /**
     * Updates the skin buffer of this mesh's surface with the given `data`. The expected data per skin
     * is 12 or 8 bytes (4 bytes per float, 2 floats per `Vector2`, and 3 floats per `Vector3`)
     * depending on if the mesh is using `Vector3` or `Vector2` vertices. This value can be determined
     * with `RenderingServer.mesh_surface_get_format_skin_stride`. The starting point of the updates
     * can be changed with `offset`. The value of `offset` should be a multiple of 12 bytes in most
     * cases to align to each skin. A `PackedVector3Array` of skin locations can be converted into a
     * `PackedByteArray` using `PackedVector3Array.to_byte_array` for use in `data`.
     *
     * Generated from Godot docs: ArrayMesh.surface_update_skin_region
     */
    fun surfaceUpdateSkinRegion(surfIdx: Int, offset: Int, data: ByteArray) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntAndByteArrayArg(surfaceUpdateSkinRegionBind, handle, surfIdx, offset, data)
    }

    /**
     * Returns the length in vertices of the vertex array in the requested surface (see
     * `add_surface_from_arrays`).
     *
     * Generated from Godot docs: ArrayMesh.surface_get_array_len
     */
    fun surfaceGetArrayLen(surfIdx: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(surfaceGetArrayLenBind, handle, surfIdx)
    }

    /**
     * Returns the length in indices of the index array in the requested surface (see
     * `add_surface_from_arrays`).
     *
     * Generated from Godot docs: ArrayMesh.surface_get_array_index_len
     */
    fun surfaceGetArrayIndexLen(surfIdx: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(surfaceGetArrayIndexLenBind, handle, surfIdx)
    }

    /**
     * Returns the format mask of the requested surface (see `add_surface_from_arrays`).
     *
     * Generated from Godot docs: ArrayMesh.surface_get_format
     */
    fun surfaceGetFormat(surfIdx: Int): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetLong(surfaceGetFormatBind, handle, surfIdx)
    }

    /**
     * Returns the primitive type of the requested surface (see `add_surface_from_arrays`).
     *
     * Generated from Godot docs: ArrayMesh.surface_get_primitive_type
     */
    fun surfaceGetPrimitiveType(surfIdx: Int): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetLong(surfaceGetPrimitiveTypeBind, handle, surfIdx)
    }

    /**
     * Returns the index of the first surface with this name held within this `ArrayMesh`. If none are
     * found, -1 is returned.
     *
     * Generated from Godot docs: ArrayMesh.surface_find_by_name
     */
    fun surfaceFindByName(name: String): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetInt(surfaceFindByNameBind, handle, name)
    }

    /**
     * Sets a name for a given surface.
     *
     * Generated from Godot docs: ArrayMesh.surface_set_name
     */
    fun surfaceSetName(surfIdx: Int, name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndStringArg(surfaceSetNameBind, handle, surfIdx, name)
    }

    /**
     * Gets the name assigned to this surface.
     *
     * Generated from Godot docs: ArrayMesh.surface_get_name
     */
    fun surfaceGetName(surfIdx: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetString(surfaceGetNameBind, handle, surfIdx)
    }

    /**
     * Regenerates tangents for each of the `ArrayMesh`'s surfaces.
     *
     * Generated from Godot docs: ArrayMesh.regen_normal_maps
     */
    fun regenNormalMaps() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(regenNormalMapsBind, handle)
    }

    /**
     * Performs a UV unwrap on the `ArrayMesh` to prepare the mesh for lightmapping.
     *
     * Generated from Godot docs: ArrayMesh.lightmap_unwrap
     */
    fun lightmapUnwrap(transform: Transform3D, texelSize: Double): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithTransform3DAndDoubleArgRetLong(lightmapUnwrapBind, handle, transform, texelSize)
    }

    /**
     * Overrides the `AABB` with one defined by user for use with frustum culling. Especially useful to
     * avoid unexpected culling when using a shader to offset vertices.
     *
     * Generated from Godot docs: ArrayMesh.set_custom_aabb
     */
    fun setCustomAabb(aabb: AABB) {
        checkOpen()
        ObjectCalls.ptrcallWithAABBArg(setCustomAabbBind, handle, aabb)
    }

    /**
     * Overrides the `AABB` with one defined by user for use with frustum culling. Especially useful to
     * avoid unexpected culling when using a shader to offset vertices.
     *
     * Generated from Godot docs: ArrayMesh.get_custom_aabb
     */
    fun getCustomAabb(): AABB {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetAABB(getCustomAabbBind, handle)
    }

    /**
     * An optional mesh which can be used for rendering shadows and the depth prepass. Can be used to
     * increase performance by supplying a mesh with fused vertices and only vertex position data
     * (without normals, UVs, colors, etc.). Note: This mesh must have exactly the same vertex
     * positions as the source mesh (including the source mesh's LODs, if present). If vertex positions
     * differ, then the mesh will not draw correctly.
     *
     * Generated from Godot docs: ArrayMesh.set_shadow_mesh
     */
    fun setShadowMesh(mesh: ArrayMesh?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setShadowMeshBind, handle, listOf(mesh?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * An optional mesh which can be used for rendering shadows and the depth prepass. Can be used to
     * increase performance by supplying a mesh with fused vertices and only vertex position data
     * (without normals, UVs, colors, etc.). Note: This mesh must have exactly the same vertex
     * positions as the source mesh (including the source mesh's LODs, if present). If vertex positions
     * differ, then the mesh will not draw correctly.
     *
     * Generated from Godot docs: ArrayMesh.get_shadow_mesh
     */
    fun getShadowMesh(): ArrayMesh? {
        checkOpen()
        return ArrayMesh.wrap(ObjectCalls.ptrcallNoArgsRetObject(getShadowMeshBind, handle))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ArrayMesh? =
            wrap(handle)

        @JvmStatic
        fun fromResource(value: Resource): ArrayMesh? =
            if (value.isClass("ArrayMesh")) ArrayMesh(value.handle) else null

        internal fun wrap(handle: MemorySegment): ArrayMesh? =
            if (handle.address() == 0L) null else ArrayMesh(handle)

        private const val ADD_BLEND_SHAPE_HASH = 3304788590L
        private val addBlendShapeBind by lazy {
            ObjectCalls.getMethodBind("ArrayMesh", "add_blend_shape", ADD_BLEND_SHAPE_HASH)
        }

        private const val GET_BLEND_SHAPE_COUNT_HASH = 3905245786L
        private val getBlendShapeCountBind by lazy {
            ObjectCalls.getMethodBind("ArrayMesh", "get_blend_shape_count", GET_BLEND_SHAPE_COUNT_HASH)
        }

        private const val GET_BLEND_SHAPE_NAME_HASH = 659327637L
        private val getBlendShapeNameBind by lazy {
            ObjectCalls.getMethodBind("ArrayMesh", "get_blend_shape_name", GET_BLEND_SHAPE_NAME_HASH)
        }

        private const val SET_BLEND_SHAPE_NAME_HASH = 3780747571L
        private val setBlendShapeNameBind by lazy {
            ObjectCalls.getMethodBind("ArrayMesh", "set_blend_shape_name", SET_BLEND_SHAPE_NAME_HASH)
        }

        private const val CLEAR_BLEND_SHAPES_HASH = 3218959716L
        private val clearBlendShapesBind by lazy {
            ObjectCalls.getMethodBind("ArrayMesh", "clear_blend_shapes", CLEAR_BLEND_SHAPES_HASH)
        }

        private const val SET_BLEND_SHAPE_MODE_HASH = 227983991L
        private val setBlendShapeModeBind by lazy {
            ObjectCalls.getMethodBind("ArrayMesh", "set_blend_shape_mode", SET_BLEND_SHAPE_MODE_HASH)
        }

        private const val GET_BLEND_SHAPE_MODE_HASH = 836485024L
        private val getBlendShapeModeBind by lazy {
            ObjectCalls.getMethodBind("ArrayMesh", "get_blend_shape_mode", GET_BLEND_SHAPE_MODE_HASH)
        }

        private const val ADD_SURFACE_FROM_ARRAYS_HASH = 1796411378L
        private val addSurfaceFromArraysBind by lazy {
            ObjectCalls.getMethodBind("ArrayMesh", "add_surface_from_arrays", ADD_SURFACE_FROM_ARRAYS_HASH)
        }

        private const val CLEAR_SURFACES_HASH = 3218959716L
        private val clearSurfacesBind by lazy {
            ObjectCalls.getMethodBind("ArrayMesh", "clear_surfaces", CLEAR_SURFACES_HASH)
        }

        private const val SURFACE_REMOVE_HASH = 1286410249L
        private val surfaceRemoveBind by lazy {
            ObjectCalls.getMethodBind("ArrayMesh", "surface_remove", SURFACE_REMOVE_HASH)
        }

        private const val SURFACE_UPDATE_VERTEX_REGION_HASH = 3837166854L
        private val surfaceUpdateVertexRegionBind by lazy {
            ObjectCalls.getMethodBind("ArrayMesh", "surface_update_vertex_region", SURFACE_UPDATE_VERTEX_REGION_HASH)
        }

        private const val SURFACE_UPDATE_ATTRIBUTE_REGION_HASH = 3837166854L
        private val surfaceUpdateAttributeRegionBind by lazy {
            ObjectCalls.getMethodBind("ArrayMesh", "surface_update_attribute_region", SURFACE_UPDATE_ATTRIBUTE_REGION_HASH)
        }

        private const val SURFACE_UPDATE_SKIN_REGION_HASH = 3837166854L
        private val surfaceUpdateSkinRegionBind by lazy {
            ObjectCalls.getMethodBind("ArrayMesh", "surface_update_skin_region", SURFACE_UPDATE_SKIN_REGION_HASH)
        }

        private const val SURFACE_GET_ARRAY_LEN_HASH = 923996154L
        private val surfaceGetArrayLenBind by lazy {
            ObjectCalls.getMethodBind("ArrayMesh", "surface_get_array_len", SURFACE_GET_ARRAY_LEN_HASH)
        }

        private const val SURFACE_GET_ARRAY_INDEX_LEN_HASH = 923996154L
        private val surfaceGetArrayIndexLenBind by lazy {
            ObjectCalls.getMethodBind("ArrayMesh", "surface_get_array_index_len", SURFACE_GET_ARRAY_INDEX_LEN_HASH)
        }

        private const val SURFACE_GET_FORMAT_HASH = 3718287884L
        private val surfaceGetFormatBind by lazy {
            ObjectCalls.getMethodBind("ArrayMesh", "surface_get_format", SURFACE_GET_FORMAT_HASH)
        }

        private const val SURFACE_GET_PRIMITIVE_TYPE_HASH = 4141943888L
        private val surfaceGetPrimitiveTypeBind by lazy {
            ObjectCalls.getMethodBind("ArrayMesh", "surface_get_primitive_type", SURFACE_GET_PRIMITIVE_TYPE_HASH)
        }

        private const val SURFACE_FIND_BY_NAME_HASH = 1321353865L
        private val surfaceFindByNameBind by lazy {
            ObjectCalls.getMethodBind("ArrayMesh", "surface_find_by_name", SURFACE_FIND_BY_NAME_HASH)
        }

        private const val SURFACE_SET_NAME_HASH = 501894301L
        private val surfaceSetNameBind by lazy {
            ObjectCalls.getMethodBind("ArrayMesh", "surface_set_name", SURFACE_SET_NAME_HASH)
        }

        private const val SURFACE_GET_NAME_HASH = 844755477L
        private val surfaceGetNameBind by lazy {
            ObjectCalls.getMethodBind("ArrayMesh", "surface_get_name", SURFACE_GET_NAME_HASH)
        }

        private const val REGEN_NORMAL_MAPS_HASH = 3218959716L
        private val regenNormalMapsBind by lazy {
            ObjectCalls.getMethodBind("ArrayMesh", "regen_normal_maps", REGEN_NORMAL_MAPS_HASH)
        }

        private const val LIGHTMAP_UNWRAP_HASH = 1476641071L
        private val lightmapUnwrapBind by lazy {
            ObjectCalls.getMethodBind("ArrayMesh", "lightmap_unwrap", LIGHTMAP_UNWRAP_HASH)
        }

        private const val SET_CUSTOM_AABB_HASH = 259215842L
        private val setCustomAabbBind by lazy {
            ObjectCalls.getMethodBind("ArrayMesh", "set_custom_aabb", SET_CUSTOM_AABB_HASH)
        }

        private const val GET_CUSTOM_AABB_HASH = 1068685055L
        private val getCustomAabbBind by lazy {
            ObjectCalls.getMethodBind("ArrayMesh", "get_custom_aabb", GET_CUSTOM_AABB_HASH)
        }

        private const val SET_SHADOW_MESH_HASH = 3377897901L
        private val setShadowMeshBind by lazy {
            ObjectCalls.getMethodBind("ArrayMesh", "set_shadow_mesh", SET_SHADOW_MESH_HASH)
        }

        private const val GET_SHADOW_MESH_HASH = 3206942465L
        private val getShadowMeshBind by lazy {
            ObjectCalls.getMethodBind("ArrayMesh", "get_shadow_mesh", GET_SHADOW_MESH_HASH)
        }
    }
}
