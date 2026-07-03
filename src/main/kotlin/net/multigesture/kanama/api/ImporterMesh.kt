package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2i

/**
 * A `Resource` that contains vertex array-based geometry during the import process.
 *
 * Generated from Godot docs: ImporterMesh
 */
class ImporterMesh(handle: MemorySegment) : Resource(handle) {
    /**
     * Adds name for a blend shape that will be added with `add_surface`. Must be called before surface
     * is added.
     *
     * Generated from Godot docs: ImporterMesh.add_blend_shape
     */
    fun addBlendShape(name: String) {
        ObjectCalls.ptrcallWithStringArg(addBlendShapeBind, handle, name)
    }

    /**
     * Returns the number of blend shapes that the mesh holds.
     *
     * Generated from Godot docs: ImporterMesh.get_blend_shape_count
     */
    fun getBlendShapeCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getBlendShapeCountBind, handle)
    }

    /**
     * Returns the name of the blend shape at this index.
     *
     * Generated from Godot docs: ImporterMesh.get_blend_shape_name
     */
    fun getBlendShapeName(blendShapeIdx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getBlendShapeNameBind, handle, blendShapeIdx)
    }

    /**
     * Sets the blend shape mode.
     *
     * Generated from Godot docs: ImporterMesh.set_blend_shape_mode
     */
    fun setBlendShapeMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setBlendShapeModeBind, handle, mode)
    }

    /**
     * Returns the blend shape mode for this Mesh.
     *
     * Generated from Godot docs: ImporterMesh.get_blend_shape_mode
     */
    fun getBlendShapeMode(): Long {
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
     * Generated from Godot docs: ImporterMesh.add_surface
     */
    fun addSurface(primitive: Long, arrays: List<Any?>, blendShapes: List<List<Any?>>, lods: Map<String, Any?> = emptyMap(), material: Material?, name: String = "", flags: Long = 0L) {
        ObjectCalls.ptrcallWithLongArrayArrayListDictionaryObjectStringLongArgs(addSurfaceBind, handle, primitive, arrays, blendShapes, lods, material?.requireOpenHandle() ?: MemorySegment.NULL, name, flags)
    }

    /**
     * Returns the number of surfaces that the mesh holds.
     *
     * Generated from Godot docs: ImporterMesh.get_surface_count
     */
    fun getSurfaceCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSurfaceCountBind, handle)
    }

    /**
     * Returns the primitive type of the requested surface (see `add_surface`).
     *
     * Generated from Godot docs: ImporterMesh.get_surface_primitive_type
     */
    fun getSurfacePrimitiveType(surfaceIdx: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getSurfacePrimitiveTypeBind, handle, surfaceIdx)
    }

    /**
     * Gets the name assigned to this surface.
     *
     * Generated from Godot docs: ImporterMesh.get_surface_name
     */
    fun getSurfaceName(surfaceIdx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getSurfaceNameBind, handle, surfaceIdx)
    }

    /**
     * Returns the arrays for the vertices, normals, UVs, etc. that make up the requested surface. See
     * `add_surface`.
     *
     * Generated from Godot docs: ImporterMesh.get_surface_arrays
     */
    fun getSurfaceArrays(surfaceIdx: Int): List<Any?> {
        return ObjectCalls.ptrcallWithIntArgRetArray(getSurfaceArraysBind, handle, surfaceIdx)
    }

    /**
     * Returns a single set of blend shape arrays for the requested blend shape index for a surface.
     *
     * Generated from Godot docs: ImporterMesh.get_surface_blend_shape_arrays
     */
    fun getSurfaceBlendShapeArrays(surfaceIdx: Int, blendShapeIdx: Int): List<Any?> {
        return ObjectCalls.ptrcallWithTwoIntArgsRetArray(getSurfaceBlendShapeArraysBind, handle, surfaceIdx, blendShapeIdx)
    }

    /**
     * Returns the number of lods that the mesh holds on a given surface.
     *
     * Generated from Godot docs: ImporterMesh.get_surface_lod_count
     */
    fun getSurfaceLodCount(surfaceIdx: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getSurfaceLodCountBind, handle, surfaceIdx)
    }

    /**
     * Returns the screen ratio which activates a lod for a surface.
     *
     * Generated from Godot docs: ImporterMesh.get_surface_lod_size
     */
    fun getSurfaceLodSize(surfaceIdx: Int, lodIdx: Int): Double {
        return ObjectCalls.ptrcallWithTwoIntArgsRetDouble(getSurfaceLodSizeBind, handle, surfaceIdx, lodIdx)
    }

    /**
     * Returns the index buffer of a lod for a surface.
     *
     * Generated from Godot docs: ImporterMesh.get_surface_lod_indices
     */
    fun getSurfaceLodIndices(surfaceIdx: Int, lodIdx: Int): List<Int> {
        return ObjectCalls.ptrcallWithTwoIntArgsRetPackedInt32List(getSurfaceLodIndicesBind, handle, surfaceIdx, lodIdx)
    }

    /**
     * Returns a `Material` in a given surface. Surface is rendered using this material.
     *
     * Generated from Godot docs: ImporterMesh.get_surface_material
     */
    fun getSurfaceMaterial(surfaceIdx: Int): Material? {
        return Material.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getSurfaceMaterialBind, handle, surfaceIdx))
    }

    /**
     * Returns the format of the surface that the mesh holds.
     *
     * Generated from Godot docs: ImporterMesh.get_surface_format
     */
    fun getSurfaceFormat(surfaceIdx: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getSurfaceFormatBind, handle, surfaceIdx)
    }

    /**
     * Sets a name for a given surface.
     *
     * Generated from Godot docs: ImporterMesh.set_surface_name
     */
    fun setSurfaceName(surfaceIdx: Int, name: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setSurfaceNameBind, handle, surfaceIdx, name)
    }

    /**
     * Sets a `Material` for a given surface. Surface will be rendered using this material.
     *
     * Generated from Godot docs: ImporterMesh.set_surface_material
     */
    fun setSurfaceMaterial(surfaceIdx: Int, material: Material?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setSurfaceMaterialBind, handle, surfaceIdx, material?.requireOpenHandle() ?: MemorySegment.NULL)
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
    fun generateLods(normalMergeAngle: Double, normalSplitAngle: Double, boneTransformArray: List<Any?>) {
        ObjectCalls.ptrcallWithTwoDoubleArrayArgs(generateLodsBind, handle, normalMergeAngle, normalSplitAngle, boneTransformArray)
    }

    /**
     * Returns the mesh data represented by this `ImporterMesh` as a usable `ArrayMesh`. This method
     * caches the returned mesh, and subsequent calls will return the cached data until `clear` is
     * called. If not yet cached and `base_mesh` is provided, `base_mesh` will be used and mutated.
     *
     * Generated from Godot docs: ImporterMesh.get_mesh
     */
    fun getMesh(baseMesh: ArrayMesh?): ArrayMesh? {
        return ArrayMesh.wrap(ObjectCalls.ptrcallWithObjectArgRetObject(getMeshBind, handle, baseMesh?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Removes all surfaces and blend shapes from this `ImporterMesh`.
     *
     * Generated from Godot docs: ImporterMesh.clear
     */
    fun clear() {
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    /**
     * Sets the size hint of this mesh for lightmap-unwrapping in UV-space.
     *
     * Generated from Godot docs: ImporterMesh.set_lightmap_size_hint
     */
    fun setLightmapSizeHint(size: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(setLightmapSizeHintBind, handle, size)
    }

    /**
     * Returns the size hint of this mesh for lightmap-unwrapping in UV-space.
     *
     * Generated from Godot docs: ImporterMesh.get_lightmap_size_hint
     */
    fun getLightmapSizeHint(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(getLightmapSizeHintBind, handle)
    }

    companion object {
        /**
         * Converts the given `Mesh` into an `ImporterMesh` by copying all its surfaces, blend shapes,
         * materials, and metadata into a new `ImporterMesh` object.
         *
         * Generated from Godot docs: ImporterMesh.from_mesh
         */
        fun fromMesh(mesh: Mesh?): ImporterMesh? {
            return ImporterMesh.wrap(ObjectCalls.ptrcallWithObjectArgRetObject(fromMeshBind, MemorySegment.NULL, mesh?.requireOpenHandle() ?: MemorySegment.NULL))
        }

        @JvmStatic
        fun fromHandle(handle: MemorySegment): ImporterMesh? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ImporterMesh? =
            if (handle.address() == 0L) null else ImporterMesh(handle)

        private const val ADD_BLEND_SHAPE_HASH = 83702148L
        private val addBlendShapeBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "add_blend_shape", ADD_BLEND_SHAPE_HASH)
        }

        private const val GET_BLEND_SHAPE_COUNT_HASH = 3905245786L
        private val getBlendShapeCountBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "get_blend_shape_count", GET_BLEND_SHAPE_COUNT_HASH)
        }

        private const val GET_BLEND_SHAPE_NAME_HASH = 844755477L
        private val getBlendShapeNameBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "get_blend_shape_name", GET_BLEND_SHAPE_NAME_HASH)
        }

        private const val SET_BLEND_SHAPE_MODE_HASH = 227983991L
        private val setBlendShapeModeBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "set_blend_shape_mode", SET_BLEND_SHAPE_MODE_HASH)
        }

        private const val GET_BLEND_SHAPE_MODE_HASH = 836485024L
        private val getBlendShapeModeBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "get_blend_shape_mode", GET_BLEND_SHAPE_MODE_HASH)
        }

        private const val ADD_SURFACE_HASH = 1740448849L
        private val addSurfaceBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "add_surface", ADD_SURFACE_HASH)
        }

        private const val GET_SURFACE_COUNT_HASH = 3905245786L
        private val getSurfaceCountBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "get_surface_count", GET_SURFACE_COUNT_HASH)
        }

        private const val GET_SURFACE_PRIMITIVE_TYPE_HASH = 3552571330L
        private val getSurfacePrimitiveTypeBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "get_surface_primitive_type", GET_SURFACE_PRIMITIVE_TYPE_HASH)
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

        private const val GET_SURFACE_LOD_COUNT_HASH = 923996154L
        private val getSurfaceLodCountBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "get_surface_lod_count", GET_SURFACE_LOD_COUNT_HASH)
        }

        private const val GET_SURFACE_LOD_SIZE_HASH = 3085491603L
        private val getSurfaceLodSizeBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "get_surface_lod_size", GET_SURFACE_LOD_SIZE_HASH)
        }

        private const val GET_SURFACE_LOD_INDICES_HASH = 1265128013L
        private val getSurfaceLodIndicesBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "get_surface_lod_indices", GET_SURFACE_LOD_INDICES_HASH)
        }

        private const val GET_SURFACE_MATERIAL_HASH = 2897466400L
        private val getSurfaceMaterialBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "get_surface_material", GET_SURFACE_MATERIAL_HASH)
        }

        private const val GET_SURFACE_FORMAT_HASH = 923996154L
        private val getSurfaceFormatBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "get_surface_format", GET_SURFACE_FORMAT_HASH)
        }

        private const val SET_SURFACE_NAME_HASH = 501894301L
        private val setSurfaceNameBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "set_surface_name", SET_SURFACE_NAME_HASH)
        }

        private const val SET_SURFACE_MATERIAL_HASH = 3671737478L
        private val setSurfaceMaterialBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "set_surface_material", SET_SURFACE_MATERIAL_HASH)
        }

        private const val GENERATE_LODS_HASH = 2491878677L
        private val generateLodsBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "generate_lods", GENERATE_LODS_HASH)
        }

        private const val GET_MESH_HASH = 1457573577L
        private val getMeshBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "get_mesh", GET_MESH_HASH)
        }

        private const val FROM_MESH_HASH = 283226343L
        private val fromMeshBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "from_mesh", FROM_MESH_HASH)
        }

        private const val CLEAR_HASH = 3218959716L
        private val clearBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "clear", CLEAR_HASH)
        }

        private const val SET_LIGHTMAP_SIZE_HINT_HASH = 1130785943L
        private val setLightmapSizeHintBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "set_lightmap_size_hint", SET_LIGHTMAP_SIZE_HINT_HASH)
        }

        private const val GET_LIGHTMAP_SIZE_HINT_HASH = 3690982128L
        private val getLightmapSizeHintBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "get_lightmap_size_hint", GET_LIGHTMAP_SIZE_HINT_HASH)
        }
    }
}
