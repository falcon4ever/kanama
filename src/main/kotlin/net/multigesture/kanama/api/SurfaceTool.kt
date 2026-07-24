package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Plane
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3
import java.lang.foreign.MemorySegment

/**
 * Helper tool to create geometry.
 *
 * Generated from Godot docs: SurfaceTool
 */
class SurfaceTool(handle: MemorySegment) : RefCounted(handle) {
    /**
     * Set to `SKIN_8_WEIGHTS` to indicate that up to 8 bone influences per vertex may be used. By
     * default, only 4 bone influences are used (`SKIN_4_WEIGHTS`). Note: This function takes an enum,
     * not the exact number of weights.
     *
     * Generated from Godot docs: SurfaceTool.set_skin_weight_count
     */
    fun setSkinWeightCount(count: Long) {
        ObjectCalls.ptrcallWithLongArg(setSkinWeightCountBind, handle, count)
    }

    /**
     * By default, returns `SKIN_4_WEIGHTS` to indicate only 4 bone influences per vertex are used.
     * Returns `SKIN_8_WEIGHTS` if up to 8 influences are used. Note: This function returns an enum,
     * not the exact number of weights.
     *
     * Generated from Godot docs: SurfaceTool.get_skin_weight_count
     */
    fun getSkinWeightCount(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSkinWeightCountBind, handle)
    }

    /**
     * Sets the color format for this custom `channel_index`. Use `CUSTOM_MAX` to disable. Must be
     * invoked after `begin` and should be set before `commit` or `commit_to_arrays`.
     *
     * Generated from Godot docs: SurfaceTool.set_custom_format
     */
    fun setCustomFormat(channelIndex: Int, format: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setCustomFormatBind, handle, channelIndex, format)
    }

    /**
     * Returns the format for custom `channel_index` (currently up to 4). Returns `CUSTOM_MAX` if this
     * custom channel is unused.
     *
     * Generated from Godot docs: SurfaceTool.get_custom_format
     */
    fun getCustomFormat(channelIndex: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getCustomFormatBind, handle, channelIndex)
    }

    /**
     * Called before adding any vertices. Takes the primitive type as an argument (e.g.
     * `Mesh.PRIMITIVE_TRIANGLES`).
     *
     * Generated from Godot docs: SurfaceTool.begin
     */
    fun begin(primitive: Long) {
        ObjectCalls.ptrcallWithLongArg(beginBind, handle, primitive)
    }

    /**
     * Specifies the position of current vertex. Should be called after specifying other vertex
     * properties (e.g. Color, UV).
     *
     * Generated from Godot docs: SurfaceTool.add_vertex
     */
    fun addVertex(vertex: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(addVertexBind, handle, vertex)
    }

    /**
     * Specifies a `Color` to use for the next vertex. If every vertex needs to have this information
     * set and you fail to submit it for the first vertex, this information may not be used at all.
     * Note: The material must have `BaseMaterial3D.vertex_color_use_as_albedo` enabled for the vertex
     * color to be visible.
     *
     * Generated from Godot docs: SurfaceTool.set_color
     */
    fun setColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setColorBind, handle, color)
    }

    /**
     * Specifies a normal to use for the next vertex. If every vertex needs to have this information
     * set and you fail to submit it for the first vertex, this information may not be used at all.
     *
     * Generated from Godot docs: SurfaceTool.set_normal
     */
    fun setNormal(normal: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setNormalBind, handle, normal)
    }

    /**
     * Specifies a tangent to use for the next vertex. If every vertex needs to have this information
     * set and you fail to submit it for the first vertex, this information may not be used at all.
     * Note: Even though `tangent` is a `Plane`, it does not directly represent the tangent plane. Its
     * `Plane.x`, `Plane.y`, and `Plane.z` represent the tangent vector and `Plane.d` should be either
     * `-1` or `1`. See also `Mesh.ARRAY_TANGENT`.
     *
     * Generated from Godot docs: SurfaceTool.set_tangent
     */
    fun setTangent(tangent: Plane) {
        ObjectCalls.ptrcallWithPlaneArg(setTangentBind, handle, tangent)
    }

    /**
     * Specifies a set of UV coordinates to use for the next vertex. If every vertex needs to have this
     * information set and you fail to submit it for the first vertex, this information may not be used
     * at all.
     *
     * Generated from Godot docs: SurfaceTool.set_uv
     */
    fun setUv(uv: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setUvBind, handle, uv)
    }

    /**
     * Specifies an optional second set of UV coordinates to use for the next vertex. If every vertex
     * needs to have this information set and you fail to submit it for the first vertex, this
     * information may not be used at all.
     *
     * Generated from Godot docs: SurfaceTool.set_uv2
     */
    fun setUv2(uv2: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setUv2Bind, handle, uv2)
    }

    /**
     * Specifies an array of bones to use for the next vertex. `bones` must contain 4 integers.
     *
     * Generated from Godot docs: SurfaceTool.set_bones
     */
    fun setBones(bones: List<Int>) {
        ObjectCalls.ptrcallWithPackedInt32ListArg(setBonesBind, handle, bones)
    }

    /**
     * Specifies weight values to use for the next vertex. `weights` must contain 4 values. If every
     * vertex needs to have this information set and you fail to submit it for the first vertex, this
     * information may not be used at all.
     *
     * Generated from Godot docs: SurfaceTool.set_weights
     */
    fun setWeights(weights: List<Float>) {
        ObjectCalls.ptrcallWithPackedFloat32ListArg(setWeightsBind, handle, weights)
    }

    /**
     * Sets the custom value on this vertex for `channel_index`. `set_custom_format` must be called
     * first for this `channel_index`. Formats which are not RGBA will ignore other color channels.
     *
     * Generated from Godot docs: SurfaceTool.set_custom
     */
    fun setCustom(channelIndex: Int, customColor: Color) {
        ObjectCalls.ptrcallWithIntAndColorArg(setCustomBind, handle, channelIndex, customColor)
    }

    /**
     * Specifies the smooth group to use for the next vertex. If this is never called, all vertices
     * will have the default smooth group of `0` and will be smoothed with adjacent vertices of the
     * same group. To produce a mesh with flat normals, set the smooth group to `-1`. Note: This
     * function actually takes a `uint32_t`, so C# users should use `uint32.MaxValue` instead of `-1`
     * to produce a mesh with flat normals.
     *
     * Generated from Godot docs: SurfaceTool.set_smooth_group
     */
    fun setSmoothGroup(index: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setSmoothGroupBind, handle, index)
    }

    /**
     * Inserts a triangle fan made of array data into `Mesh` being constructed. Requires the primitive
     * type be set to `Mesh.PRIMITIVE_TRIANGLES`.
     *
     * Generated from Godot docs: SurfaceTool.add_triangle_fan
     */
    fun addTriangleFan(vertices: List<Vector3>, uvs: List<Vector2>, colors: List<Color>, uv2s: List<Vector2>, normals: List<Vector3>, tangents: List<Plane>) {
        ObjectCalls.ptrcallWithPackedVector3ListPackedVector2ListPackedColorListPackedVector2ListPackedVector3ListPlaneListArgs(addTriangleFanBind, handle, vertices, uvs, colors, uv2s, normals, tangents)
    }

    /**
     * Adds a vertex to index array if you are using indexed vertices. Does not need to be called
     * before adding vertices.
     *
     * Generated from Godot docs: SurfaceTool.add_index
     */
    fun addIndex(index: Int) {
        ObjectCalls.ptrcallWithIntArg(addIndexBind, handle, index)
    }

    /**
     * Shrinks the vertex array by creating an index array. This can improve performance by avoiding
     * vertex reuse.
     *
     * Generated from Godot docs: SurfaceTool.index
     */
    fun index() {
        ObjectCalls.ptrcallNoArgs(indexBind, handle)
    }

    /**
     * Removes the index array by expanding the vertex array.
     *
     * Generated from Godot docs: SurfaceTool.deindex
     */
    fun deindex() {
        ObjectCalls.ptrcallNoArgs(deindexBind, handle)
    }

    /**
     * Generates normals from vertices so you do not have to do it manually. If `flip` is `true`, the
     * resulting normals will be inverted. `generate_normals` should be called after generating
     * geometry and before committing the mesh using `commit` or `commit_to_arrays`. For correct
     * display of normal-mapped surfaces, you will also have to generate tangents using
     * `generate_tangents`. Note: `generate_normals` only works if the primitive type is set to
     * `Mesh.PRIMITIVE_TRIANGLES`. Note: `generate_normals` takes smooth groups into account. To
     * generate smooth normals, set the smooth group to a value greater than or equal to `0` using
     * `set_smooth_group` or leave the smooth group at the default of `0`. To generate flat normals,
     * set the smooth group to `-1` using `set_smooth_group` prior to adding vertices.
     *
     * Generated from Godot docs: SurfaceTool.generate_normals
     */
    fun generateNormals(flip: Boolean = false) {
        ObjectCalls.ptrcallWithBoolArg(generateNormalsBind, handle, flip)
    }

    /**
     * Generates a tangent vector for each vertex. Requires that each vertex already has UVs and
     * normals set (see `generate_normals`).
     *
     * Generated from Godot docs: SurfaceTool.generate_tangents
     */
    fun generateTangents() {
        ObjectCalls.ptrcallNoArgs(generateTangentsBind, handle)
    }

    /**
     * Optimizes triangle sorting for performance. Requires that `get_primitive_type` is
     * `Mesh.PRIMITIVE_TRIANGLES`.
     *
     * Generated from Godot docs: SurfaceTool.optimize_indices_for_cache
     */
    fun optimizeIndicesForCache() {
        ObjectCalls.ptrcallNoArgs(optimizeIndicesForCacheBind, handle)
    }

    /**
     * Returns the axis-aligned bounding box of the vertex positions.
     *
     * Generated from Godot docs: SurfaceTool.get_aabb
     */
    fun getAabb(): AABB {
        return ObjectCalls.ptrcallNoArgsRetAABB(getAabbBind, handle)
    }

    /**
     * Generates an LOD for a given `nd_threshold` in linear units (square root of quadric error
     * metric), using at most `target_index_count` indices.
     *
     * Generated from Godot docs: SurfaceTool.generate_lod
     */
    fun generateLod(ndThreshold: Double, targetIndexCount: Int = 3): List<Int> {
        return ObjectCalls.ptrcallWithDoubleAndIntArgsRetPackedInt32List(generateLodBind, handle, ndThreshold, targetIndexCount)
    }

    /**
     * Sets `Material` to be used by the `Mesh` you are constructing.
     *
     * Generated from Godot docs: SurfaceTool.set_material
     */
    fun setMaterial(material: Material?) {
        ObjectCalls.ptrcallWithObjectArgs(setMaterialBind, handle, listOf(material?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Returns the type of mesh geometry, such as `Mesh.PRIMITIVE_TRIANGLES`.
     *
     * Generated from Godot docs: SurfaceTool.get_primitive_type
     */
    fun getPrimitiveType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPrimitiveTypeBind, handle)
    }

    /**
     * Clear all information passed into the surface tool so far.
     *
     * Generated from Godot docs: SurfaceTool.clear
     */
    fun clear() {
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    /**
     * Creates a vertex array from an existing `Mesh`.
     *
     * Generated from Godot docs: SurfaceTool.create_from
     */
    fun createFrom(existing: Mesh?, surface: Int) {
        ObjectCalls.ptrcallWithObjectAndIntArg(createFromBind, handle, existing?.requireOpenHandle() ?: MemorySegment.NULL, surface)
    }

    /**
     * Creates this SurfaceTool from existing vertex arrays such as returned by `commit_to_arrays`,
     * `Mesh.surface_get_arrays`, `Mesh.surface_get_blend_shape_arrays`,
     * `ImporterMesh.get_surface_arrays`, and `ImporterMesh.get_surface_blend_shape_arrays`.
     * `primitive_type` controls the type of mesh data, defaulting to `Mesh.PRIMITIVE_TRIANGLES`.
     *
     * Generated from Godot docs: SurfaceTool.create_from_arrays
     */
    fun createFromArrays(arrays: List<Any?>, primitiveType: Long = 3L) {
        ObjectCalls.ptrcallWithArrayLongArgs(createFromArraysBind, handle, arrays, primitiveType)
    }

    /**
     * Creates a vertex array from the specified blend shape of an existing `Mesh`. This can be used to
     * extract a specific pose from a blend shape.
     *
     * Generated from Godot docs: SurfaceTool.create_from_blend_shape
     */
    fun createFromBlendShape(existing: Mesh?, surface: Int, blendShape: String) {
        ObjectCalls.ptrcallWithObjectIntStringArgs(createFromBlendShapeBind, handle, existing?.requireOpenHandle() ?: MemorySegment.NULL, surface, blendShape)
    }

    /**
     * Append vertices from a given `Mesh` surface onto the current vertex array with specified
     * `Transform3D`.
     *
     * Generated from Godot docs: SurfaceTool.append_from
     */
    fun appendFrom(existing: Mesh?, surface: Int, transform: Transform3D) {
        ObjectCalls.ptrcallWithObjectIntTransform3DArgs(appendFromBind, handle, existing?.requireOpenHandle() ?: MemorySegment.NULL, surface, transform)
    }

    /**
     * Returns a constructed `ArrayMesh` from current information passed in. If an existing `ArrayMesh`
     * is passed in as an argument, will add an extra surface to the existing `ArrayMesh`. The `flags`
     * argument can be the bitwise OR of `Mesh.ARRAY_FLAG_USE_DYNAMIC_UPDATE`,
     * `Mesh.ARRAY_FLAG_USE_8_BONE_WEIGHTS`, or `Mesh.ARRAY_FLAG_USES_EMPTY_VERTEX_ARRAY`.
     *
     * Generated from Godot docs: SurfaceTool.commit
     */
    fun commit(existing: ArrayMesh? = null, flags: Long = 0L): ArrayMesh? {
        return ArrayMesh.wrap(ObjectCalls.ptrcallWithObjectAndLongArgsRetObject(commitBind, handle, existing?.requireOpenHandle() ?: MemorySegment.NULL, flags))
    }

    /**
     * Commits the data to the same format used by `ArrayMesh.add_surface_from_arrays`,
     * `ImporterMesh.add_surface`, and `create_from_arrays`. This way you can further process the mesh
     * data using the `ArrayMesh` or `ImporterMesh` APIs.
     *
     * Generated from Godot docs: SurfaceTool.commit_to_arrays
     */
    fun commitToArrays(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(commitToArraysBind, handle)
    }

    companion object {
        const val CUSTOM_RGBA8_UNORM: Long = 0L
        const val CUSTOM_RGBA8_SNORM: Long = 1L
        const val CUSTOM_RG_HALF: Long = 2L
        const val CUSTOM_RGBA_HALF: Long = 3L
        const val CUSTOM_R_FLOAT: Long = 4L
        const val CUSTOM_RG_FLOAT: Long = 5L
        const val CUSTOM_RGB_FLOAT: Long = 6L
        const val CUSTOM_RGBA_FLOAT: Long = 7L
        const val CUSTOM_MAX: Long = 8L
        const val SKIN_4_WEIGHTS: Long = 0L
        const val SKIN_8_WEIGHTS: Long = 1L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): SurfaceTool? =
            wrap(handle)

        @JvmStatic
        fun create(): SurfaceTool =
            SurfaceTool(ObjectCalls.constructObject("SurfaceTool"))

        internal fun wrap(handle: MemorySegment): SurfaceTool? =
            if (handle.address() == 0L) null else SurfaceTool(handle)

        private const val SET_SKIN_WEIGHT_COUNT_HASH = 618679515L
        private val setSkinWeightCountBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "set_skin_weight_count", SET_SKIN_WEIGHT_COUNT_HASH)
        }

        private const val GET_SKIN_WEIGHT_COUNT_HASH = 1072401130L
        private val getSkinWeightCountBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "get_skin_weight_count", GET_SKIN_WEIGHT_COUNT_HASH)
        }

        private const val SET_CUSTOM_FORMAT_HASH = 4087759856L
        private val setCustomFormatBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "set_custom_format", SET_CUSTOM_FORMAT_HASH)
        }

        private const val GET_CUSTOM_FORMAT_HASH = 839863283L
        private val getCustomFormatBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "get_custom_format", GET_CUSTOM_FORMAT_HASH)
        }

        private const val BEGIN_HASH = 2230304113L
        private val beginBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "begin", BEGIN_HASH)
        }

        private const val ADD_VERTEX_HASH = 3460891852L
        private val addVertexBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "add_vertex", ADD_VERTEX_HASH)
        }

        private const val SET_COLOR_HASH = 2920490490L
        private val setColorBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "set_color", SET_COLOR_HASH)
        }

        private const val SET_NORMAL_HASH = 3460891852L
        private val setNormalBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "set_normal", SET_NORMAL_HASH)
        }

        private const val SET_TANGENT_HASH = 3505987427L
        private val setTangentBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "set_tangent", SET_TANGENT_HASH)
        }

        private const val SET_UV_HASH = 743155724L
        private val setUvBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "set_uv", SET_UV_HASH)
        }

        private const val SET_UV2_HASH = 743155724L
        private val setUv2Bind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "set_uv2", SET_UV2_HASH)
        }

        private const val SET_BONES_HASH = 3614634198L
        private val setBonesBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "set_bones", SET_BONES_HASH)
        }

        private const val SET_WEIGHTS_HASH = 2899603908L
        private val setWeightsBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "set_weights", SET_WEIGHTS_HASH)
        }

        private const val SET_CUSTOM_HASH = 2878471219L
        private val setCustomBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "set_custom", SET_CUSTOM_HASH)
        }

        private const val SET_SMOOTH_GROUP_HASH = 1286410249L
        private val setSmoothGroupBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "set_smooth_group", SET_SMOOTH_GROUP_HASH)
        }

        private const val ADD_TRIANGLE_FAN_HASH = 2235017613L
        private val addTriangleFanBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "add_triangle_fan", ADD_TRIANGLE_FAN_HASH)
        }

        private const val ADD_INDEX_HASH = 1286410249L
        private val addIndexBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "add_index", ADD_INDEX_HASH)
        }

        private const val INDEX_HASH = 3218959716L
        private val indexBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "index", INDEX_HASH)
        }

        private const val DEINDEX_HASH = 3218959716L
        private val deindexBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "deindex", DEINDEX_HASH)
        }

        private const val GENERATE_NORMALS_HASH = 107499316L
        private val generateNormalsBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "generate_normals", GENERATE_NORMALS_HASH)
        }

        private const val GENERATE_TANGENTS_HASH = 3218959716L
        private val generateTangentsBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "generate_tangents", GENERATE_TANGENTS_HASH)
        }

        private const val OPTIMIZE_INDICES_FOR_CACHE_HASH = 3218959716L
        private val optimizeIndicesForCacheBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "optimize_indices_for_cache", OPTIMIZE_INDICES_FOR_CACHE_HASH)
        }

        private const val GET_AABB_HASH = 1068685055L
        private val getAabbBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "get_aabb", GET_AABB_HASH)
        }

        private const val GENERATE_LOD_HASH = 1938056459L
        private val generateLodBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "generate_lod", GENERATE_LOD_HASH)
        }

        private const val SET_MATERIAL_HASH = 2757459619L
        private val setMaterialBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "set_material", SET_MATERIAL_HASH)
        }

        private const val GET_PRIMITIVE_TYPE_HASH = 768822145L
        private val getPrimitiveTypeBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "get_primitive_type", GET_PRIMITIVE_TYPE_HASH)
        }

        private const val CLEAR_HASH = 3218959716L
        private val clearBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "clear", CLEAR_HASH)
        }

        private const val CREATE_FROM_HASH = 1767024570L
        private val createFromBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "create_from", CREATE_FROM_HASH)
        }

        private const val CREATE_FROM_ARRAYS_HASH = 1894639680L
        private val createFromArraysBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "create_from_arrays", CREATE_FROM_ARRAYS_HASH)
        }

        private const val CREATE_FROM_BLEND_SHAPE_HASH = 1306185582L
        private val createFromBlendShapeBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "create_from_blend_shape", CREATE_FROM_BLEND_SHAPE_HASH)
        }

        private const val APPEND_FROM_HASH = 2217967155L
        private val appendFromBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "append_from", APPEND_FROM_HASH)
        }

        private const val COMMIT_HASH = 4107864055L
        private val commitBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "commit", COMMIT_HASH)
        }

        private const val COMMIT_TO_ARRAYS_HASH = 2915620761L
        private val commitToArraysBind by lazy {
            ObjectCalls.getMethodBind("SurfaceTool", "commit_to_arrays", COMMIT_TO_ARRAYS_HASH)
        }
    }
}
