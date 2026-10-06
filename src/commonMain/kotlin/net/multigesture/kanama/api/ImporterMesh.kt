package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector2i

/**
 * A `Resource` that contains vertex array-based geometry during the import process.
 *
 * Generated from Godot docs: ImporterMesh
 */
class ImporterMesh(handle: GodotHandle) : Resource(handle) {
    /**
     * Adds name for a blend shape that will be added with `add_surface`. Must be called before surface
     * is added.
     *
     * Generated from Godot docs: ImporterMesh.add_blend_shape
     */
    fun addBlendShape(name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.addBlendShapeBind, segment, name)
    }

    /**
     * Returns the number of blend shapes that the mesh holds.
     *
     * Generated from Godot docs: ImporterMesh.get_blend_shape_count
     */
    fun getBlendShapeCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getBlendShapeCountBind, segment)
    }

    /**
     * Returns the name of the blend shape at this index.
     *
     * Generated from Godot docs: ImporterMesh.get_blend_shape_name
     */
    fun getBlendShapeName(blendShapeIdx: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetString(Binds.getBlendShapeNameBind, segment, blendShapeIdx)
    }

    /**
     * Sets the blend shape mode.
     *
     * Generated from Godot docs: ImporterMesh.set_blend_shape_mode
     */
    fun setBlendShapeMode(mode: Mesh.BlendShapeMode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setBlendShapeModeBind, segment, mode.value)
    }

    /**
     * Returns the blend shape mode for this Mesh.
     *
     * Generated from Godot docs: ImporterMesh.get_blend_shape_mode
     */
    fun getBlendShapeMode(): Mesh.BlendShapeMode {
        checkOpen()
        return Mesh.BlendShapeMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getBlendShapeModeBind, segment))
    }

    /**
     * Creates a new surface. `Mesh.get_surface_count` will become the `surf_idx` for this new surface.
     * Surfaces are created to be rendered using a `primitive`, which may be any of the values defined
     * in `Mesh.PrimitiveType`. The `arrays` argument is an array of arrays. Each of the
     * `Mesh.ArrayType.MAX` elements contains an array with some of the mesh data for this surface as
     * described by the corresponding member of `Mesh.ArrayType` or `null` if it is not used by the
     * surface. For example, `arrays[0]` is the array of vertices. That first vertex sub-array is
     * always required; the others are optional. Adding an index array puts this surface into "index
     * mode" where the vertex and other arrays become the sources of data and the index array defines
     * the vertex order. All sub-arrays must have the same length as the vertex array (or be an exact
     * multiple of the vertex array's length, when multiple elements of a sub-array correspond to a
     * single vertex) or be empty, except for `Mesh.ArrayType.INDEX` if it is used. The `blend_shapes`
     * argument is an array of vertex data for each blend shape. Each element is an array of the same
     * structure as `arrays`, but `Mesh.ArrayType.VERTEX`, `Mesh.ArrayType.NORMAL`, and
     * `Mesh.ArrayType.TANGENT` are set if and only if they are set in `arrays` and all other entries
     * are `null`. The `lods` argument is a dictionary with `float` keys and `PackedInt32Array` values.
     * Each entry in the dictionary represents an LOD level of the surface, where the value is the
     * `Mesh.ArrayType.INDEX` array to use for the LOD level and the key is roughly proportional to the
     * distance at which the LOD stats being used. I.e., increasing the key of an LOD also increases
     * the distance that the objects has to be from the camera before the LOD is used. The `flags`
     * argument is the bitwise OR of, as required: One value of `Mesh.ArrayCustomFormat` left shifted
     * by `ARRAY_FORMAT_CUSTOMn_SHIFT` for each custom channel in use,
     * `Mesh.ArrayFormat.FLAG_USE_DYNAMIC_UPDATE`, `Mesh.ArrayFormat.FLAG_USE_8_BONE_WEIGHTS`, or
     * `Mesh.ArrayFormat.FLAG_USES_EMPTY_VERTEX_ARRAY`. Note: When using indices, it is recommended to
     * only use points, lines, or triangles.
     *
     * Generated from Godot docs: ImporterMesh.add_surface
     */
    fun addSurface(primitive: Mesh.PrimitiveType, arrays: List<Any?>, blendShapes: List<List<Any?>>, lods: Map<String, Any?> = emptyMap(), material: Material?, name: String = "", flags: Long = 0L) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArrayArrayListDictionaryObjectStringLongArgs(Binds.addSurfaceBind, segment, primitive.value, arrays, blendShapes, lods, material?.requireOpenHandle() ?: NULL_SEGMENT, name, flags)
    }

    /**
     * Returns the number of surfaces that the mesh holds.
     *
     * Generated from Godot docs: ImporterMesh.get_surface_count
     */
    fun getSurfaceCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSurfaceCountBind, segment)
    }

    /**
     * Returns the primitive type of the requested surface (see `add_surface`).
     *
     * Generated from Godot docs: ImporterMesh.get_surface_primitive_type
     */
    fun getSurfacePrimitiveType(surfaceIdx: Int): Mesh.PrimitiveType {
        checkOpen()
        return Mesh.PrimitiveType(ObjectCalls.ptrcallWithIntArgRetLong(Binds.getSurfacePrimitiveTypeBind, segment, surfaceIdx))
    }

    /**
     * Gets the name assigned to this surface.
     *
     * Generated from Godot docs: ImporterMesh.get_surface_name
     */
    fun getSurfaceName(surfaceIdx: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetString(Binds.getSurfaceNameBind, segment, surfaceIdx)
    }

    /**
     * Returns the arrays for the vertices, normals, UVs, etc. that make up the requested surface. See
     * `add_surface`.
     *
     * Generated from Godot docs: ImporterMesh.get_surface_arrays
     */
    fun getSurfaceArrays(surfaceIdx: Int): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetArray(Binds.getSurfaceArraysBind, segment, surfaceIdx)
    }

    /**
     * Returns a single set of blend shape arrays for the requested blend shape index for a surface.
     *
     * Generated from Godot docs: ImporterMesh.get_surface_blend_shape_arrays
     */
    fun getSurfaceBlendShapeArrays(surfaceIdx: Int, blendShapeIdx: Int): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetArray(Binds.getSurfaceBlendShapeArraysBind, segment, surfaceIdx, blendShapeIdx)
    }

    /**
     * Returns the number of lods that the mesh holds on a given surface.
     *
     * Generated from Godot docs: ImporterMesh.get_surface_lod_count
     */
    fun getSurfaceLodCount(surfaceIdx: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getSurfaceLodCountBind, segment, surfaceIdx)
    }

    /**
     * Returns the screen ratio which activates a lod for a surface.
     *
     * Generated from Godot docs: ImporterMesh.get_surface_lod_size
     */
    fun getSurfaceLodSize(surfaceIdx: Int, lodIdx: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetDouble(Binds.getSurfaceLodSizeBind, segment, surfaceIdx, lodIdx)
    }

    /**
     * Returns the index buffer of a lod for a surface.
     *
     * Generated from Godot docs: ImporterMesh.get_surface_lod_indices
     */
    fun getSurfaceLodIndices(surfaceIdx: Int, lodIdx: Int): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetPackedInt32List(Binds.getSurfaceLodIndicesBind, segment, surfaceIdx, lodIdx)
    }

    /**
     * Returns a `Material` in a given surface. Surface is rendered using this material.
     *
     * Generated from Godot docs: ImporterMesh.get_surface_material
     */
    fun getSurfaceMaterial(surfaceIdx: Int): Material? {
        checkOpen()
        return Material.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getSurfaceMaterialBind, segment, surfaceIdx))
    }

    /**
     * Returns the format of the surface that the mesh holds.
     *
     * Generated from Godot docs: ImporterMesh.get_surface_format
     */
    fun getSurfaceFormat(surfaceIdx: Int): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetLong(Binds.getSurfaceFormatBind, segment, surfaceIdx)
    }

    /**
     * Sets a name for a given surface.
     *
     * Generated from Godot docs: ImporterMesh.set_surface_name
     */
    fun setSurfaceName(surfaceIdx: Int, name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndStringArg(Binds.setSurfaceNameBind, segment, surfaceIdx, name)
    }

    /**
     * Sets a `Material` for a given surface. Surface will be rendered using this material.
     *
     * Generated from Godot docs: ImporterMesh.set_surface_material
     */
    fun setSurfaceMaterial(surfaceIdx: Int, material: Material?) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndObjectArg(Binds.setSurfaceMaterialBind, segment, surfaceIdx, material?.requireOpenHandle() ?: NULL_SEGMENT)
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
        checkOpen()
        ObjectCalls.ptrcallWithTwoDoubleArrayArgs(Binds.generateLodsBind, segment, normalMergeAngle, normalSplitAngle, boneTransformArray)
    }

    /**
     * Returns the mesh data represented by this `ImporterMesh` as a usable `ArrayMesh`. This method
     * caches the returned mesh, and subsequent calls will return the cached data until `clear` is
     * called. If not yet cached and `base_mesh` is provided, `base_mesh` will be used and mutated.
     *
     * Generated from Godot docs: ImporterMesh.get_mesh
     */
    fun getMesh(baseMesh: ArrayMesh?): ArrayMesh? {
        checkOpen()
        return ArrayMesh.wrapOwned(ObjectCalls.ptrcallWithObjectArgRetObject(Binds.getMeshBind, segment, baseMesh?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Removes all surfaces and blend shapes from this `ImporterMesh`.
     *
     * Generated from Godot docs: ImporterMesh.clear
     */
    fun clear() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.clearBind, segment)
    }

    /**
     * Sets the size hint of this mesh for lightmap-unwrapping in UV-space.
     *
     * Generated from Godot docs: ImporterMesh.set_lightmap_size_hint
     */
    fun setLightmapSizeHint(size: Vector2i) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2iArg(Binds.setLightmapSizeHintBind, segment, size)
    }

    /**
     * Returns the size hint of this mesh for lightmap-unwrapping in UV-space.
     *
     * Generated from Godot docs: ImporterMesh.get_lightmap_size_hint
     */
    fun getLightmapSizeHint(): Vector2i {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2i(Binds.getLightmapSizeHintBind, segment)
    }

    companion object {
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
        fun mergeImporterMeshes(importerMeshes: List<ImporterMesh>, relativeTransforms: List<Transform3D>, deduplicateSurfaces: Boolean = true): ImporterMesh? {
            return ImporterMesh.wrapOwned(ObjectCalls.ptrcallWithObjectListTransform3DListBoolArgsRetObject(Binds.mergeImporterMeshesBind, NULL_SEGMENT, importerMeshes, relativeTransforms, deduplicateSurfaces))
        }

        /**
         * Converts the given `Mesh` into an `ImporterMesh` by copying all its surfaces, blend shapes,
         * materials, and metadata into a new `ImporterMesh` object.
         *
         * Generated from Godot docs: ImporterMesh.from_mesh
         */
        fun fromMesh(mesh: Mesh?): ImporterMesh? {
            return ImporterMesh.wrapOwned(ObjectCalls.ptrcallWithObjectArgRetObject(Binds.fromMeshBind, NULL_SEGMENT, mesh?.requireOpenHandle() ?: NULL_SEGMENT))
        }

        @JvmStatic
        fun fromHandle(handle: GodotHandle): ImporterMesh? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): ImporterMesh? =
            if (handle.address() == 0L) null else RefCounted.owned(ImporterMesh(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): ImporterMesh? =
            if (handle.address() == 0L) null else ImporterMesh(GodotHandle(handle))
    }

    private object Binds {
        private const val MERGE_IMPORTER_MESHES_HASH = 1030647649L
        @JvmField
        val mergeImporterMeshesBind =
            ObjectCalls.getMethodBind("ImporterMesh", "merge_importer_meshes", MERGE_IMPORTER_MESHES_HASH)

        private const val ADD_BLEND_SHAPE_HASH = 83702148L
        @JvmField
        val addBlendShapeBind =
            ObjectCalls.getMethodBind("ImporterMesh", "add_blend_shape", ADD_BLEND_SHAPE_HASH)

        private const val GET_BLEND_SHAPE_COUNT_HASH = 3905245786L
        @JvmField
        val getBlendShapeCountBind =
            ObjectCalls.getMethodBind("ImporterMesh", "get_blend_shape_count", GET_BLEND_SHAPE_COUNT_HASH)

        private const val GET_BLEND_SHAPE_NAME_HASH = 844755477L
        @JvmField
        val getBlendShapeNameBind =
            ObjectCalls.getMethodBind("ImporterMesh", "get_blend_shape_name", GET_BLEND_SHAPE_NAME_HASH)

        private const val SET_BLEND_SHAPE_MODE_HASH = 227983991L
        @JvmField
        val setBlendShapeModeBind =
            ObjectCalls.getMethodBind("ImporterMesh", "set_blend_shape_mode", SET_BLEND_SHAPE_MODE_HASH)

        private const val GET_BLEND_SHAPE_MODE_HASH = 836485024L
        @JvmField
        val getBlendShapeModeBind =
            ObjectCalls.getMethodBind("ImporterMesh", "get_blend_shape_mode", GET_BLEND_SHAPE_MODE_HASH)

        private const val ADD_SURFACE_HASH = 1740448849L
        @JvmField
        val addSurfaceBind =
            ObjectCalls.getMethodBind("ImporterMesh", "add_surface", ADD_SURFACE_HASH)

        private const val GET_SURFACE_COUNT_HASH = 3905245786L
        @JvmField
        val getSurfaceCountBind =
            ObjectCalls.getMethodBind("ImporterMesh", "get_surface_count", GET_SURFACE_COUNT_HASH)

        private const val GET_SURFACE_PRIMITIVE_TYPE_HASH = 3552571330L
        @JvmField
        val getSurfacePrimitiveTypeBind =
            ObjectCalls.getMethodBind("ImporterMesh", "get_surface_primitive_type", GET_SURFACE_PRIMITIVE_TYPE_HASH)

        private const val GET_SURFACE_NAME_HASH = 844755477L
        @JvmField
        val getSurfaceNameBind =
            ObjectCalls.getMethodBind("ImporterMesh", "get_surface_name", GET_SURFACE_NAME_HASH)

        private const val GET_SURFACE_ARRAYS_HASH = 663333327L
        @JvmField
        val getSurfaceArraysBind =
            ObjectCalls.getMethodBind("ImporterMesh", "get_surface_arrays", GET_SURFACE_ARRAYS_HASH)

        private const val GET_SURFACE_BLEND_SHAPE_ARRAYS_HASH = 2345056839L
        @JvmField
        val getSurfaceBlendShapeArraysBind =
            ObjectCalls.getMethodBind("ImporterMesh", "get_surface_blend_shape_arrays", GET_SURFACE_BLEND_SHAPE_ARRAYS_HASH)

        private const val GET_SURFACE_LOD_COUNT_HASH = 923996154L
        @JvmField
        val getSurfaceLodCountBind =
            ObjectCalls.getMethodBind("ImporterMesh", "get_surface_lod_count", GET_SURFACE_LOD_COUNT_HASH)

        private const val GET_SURFACE_LOD_SIZE_HASH = 3085491603L
        @JvmField
        val getSurfaceLodSizeBind =
            ObjectCalls.getMethodBind("ImporterMesh", "get_surface_lod_size", GET_SURFACE_LOD_SIZE_HASH)

        private const val GET_SURFACE_LOD_INDICES_HASH = 1265128013L
        @JvmField
        val getSurfaceLodIndicesBind =
            ObjectCalls.getMethodBind("ImporterMesh", "get_surface_lod_indices", GET_SURFACE_LOD_INDICES_HASH)

        private const val GET_SURFACE_MATERIAL_HASH = 2897466400L
        @JvmField
        val getSurfaceMaterialBind =
            ObjectCalls.getMethodBind("ImporterMesh", "get_surface_material", GET_SURFACE_MATERIAL_HASH)

        private const val GET_SURFACE_FORMAT_HASH = 923996154L
        @JvmField
        val getSurfaceFormatBind =
            ObjectCalls.getMethodBind("ImporterMesh", "get_surface_format", GET_SURFACE_FORMAT_HASH)

        private const val SET_SURFACE_NAME_HASH = 501894301L
        @JvmField
        val setSurfaceNameBind =
            ObjectCalls.getMethodBind("ImporterMesh", "set_surface_name", SET_SURFACE_NAME_HASH)

        private const val SET_SURFACE_MATERIAL_HASH = 3671737478L
        @JvmField
        val setSurfaceMaterialBind =
            ObjectCalls.getMethodBind("ImporterMesh", "set_surface_material", SET_SURFACE_MATERIAL_HASH)

        private const val GENERATE_LODS_HASH = 2491878677L
        @JvmField
        val generateLodsBind =
            ObjectCalls.getMethodBind("ImporterMesh", "generate_lods", GENERATE_LODS_HASH)

        private const val GET_MESH_HASH = 1457573577L
        @JvmField
        val getMeshBind =
            ObjectCalls.getMethodBind("ImporterMesh", "get_mesh", GET_MESH_HASH)

        private const val FROM_MESH_HASH = 283226343L
        @JvmField
        val fromMeshBind =
            ObjectCalls.getMethodBind("ImporterMesh", "from_mesh", FROM_MESH_HASH)

        private const val CLEAR_HASH = 3218959716L
        @JvmField
        val clearBind =
            ObjectCalls.getMethodBind("ImporterMesh", "clear", CLEAR_HASH)

        private const val SET_LIGHTMAP_SIZE_HINT_HASH = 1130785943L
        @JvmField
        val setLightmapSizeHintBind =
            ObjectCalls.getMethodBind("ImporterMesh", "set_lightmap_size_hint", SET_LIGHTMAP_SIZE_HINT_HASH)

        private const val GET_LIGHTMAP_SIZE_HINT_HASH = 3690982128L
        @JvmField
        val getLightmapSizeHintBind =
            ObjectCalls.getMethodBind("ImporterMesh", "get_lightmap_size_hint", GET_LIGHTMAP_SIZE_HINT_HASH)
    }
}
