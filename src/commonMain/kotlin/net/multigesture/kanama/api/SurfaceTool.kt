package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Plane
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3

/**
 * Helper tool to create geometry.
 *
 * Generated from Godot docs: SurfaceTool
 */
class SurfaceTool(handle: GodotHandle) : RefCounted(handle) {
    /**
     * Set to `SkinWeightCount.SKIN_8_WEIGHTS` to indicate that up to 8 bone influences per vertex may
     * be used. By default, only 4 bone influences are used (`SkinWeightCount.SKIN_4_WEIGHTS`). Note:
     * This function takes an enum, not the exact number of weights.
     *
     * Generated from Godot docs: SurfaceTool.set_skin_weight_count
     */
    fun setSkinWeightCount(count: SurfaceTool.SkinWeightCount) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setSkinWeightCountBind, segment, count.value)
    }

    /**
     * By default, returns `SkinWeightCount.SKIN_4_WEIGHTS` to indicate only 4 bone influences per
     * vertex are used. Returns `SkinWeightCount.SKIN_8_WEIGHTS` if up to 8 influences are used. Note:
     * This function returns an enum, not the exact number of weights.
     *
     * Generated from Godot docs: SurfaceTool.get_skin_weight_count
     */
    fun getSkinWeightCount(): SurfaceTool.SkinWeightCount {
        checkOpen()
        return SurfaceTool.SkinWeightCount(ObjectCalls.ptrcallNoArgsRetLong(Binds.getSkinWeightCountBind, segment))
    }

    /**
     * Sets the color format for this custom `channel_index`. Use `CustomFormat.MAX` to disable. Must
     * be invoked after `begin` and should be set before `commit` or `commit_to_arrays`.
     *
     * Generated from Godot docs: SurfaceTool.set_custom_format
     */
    fun setCustomFormat(channelIndex: Int, format: SurfaceTool.CustomFormat) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndLongArgs(Binds.setCustomFormatBind, segment, channelIndex, format.value)
    }

    /**
     * Returns the format for custom `channel_index` (currently up to 4). Returns `CustomFormat.MAX` if
     * this custom channel is unused.
     *
     * Generated from Godot docs: SurfaceTool.get_custom_format
     */
    fun getCustomFormat(channelIndex: Int): SurfaceTool.CustomFormat {
        checkOpen()
        return SurfaceTool.CustomFormat(ObjectCalls.ptrcallWithIntArgRetLong(Binds.getCustomFormatBind, segment, channelIndex))
    }

    /**
     * Called before adding any vertices. Takes the primitive type as an argument (e.g.
     * `Mesh.PrimitiveType.TRIANGLES`).
     *
     * Generated from Godot docs: SurfaceTool.begin
     */
    fun begin(primitive: Mesh.PrimitiveType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.beginBind, segment, primitive.value)
    }

    /**
     * Specifies the position of current vertex. Should be called after specifying other vertex
     * properties (e.g. Color, UV).
     *
     * Generated from Godot docs: SurfaceTool.add_vertex
     */
    fun addVertex(vertex: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(Binds.addVertexBind, segment, vertex)
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
        checkOpen()
        ObjectCalls.ptrcallWithColorArg(Binds.setColorBind, segment, color)
    }

    /**
     * Specifies a normal to use for the next vertex. If every vertex needs to have this information
     * set and you fail to submit it for the first vertex, this information may not be used at all.
     *
     * Generated from Godot docs: SurfaceTool.set_normal
     */
    fun setNormal(normal: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(Binds.setNormalBind, segment, normal)
    }

    /**
     * Specifies a tangent to use for the next vertex. If every vertex needs to have this information
     * set and you fail to submit it for the first vertex, this information may not be used at all.
     * Note: Even though `tangent` is a `Plane`, it does not directly represent the tangent plane. Its
     * `Plane.x`, `Plane.y`, and `Plane.z` represent the tangent vector and `Plane.d` should be either
     * `-1` or `1`. See also `Mesh.ArrayType.TANGENT`.
     *
     * Generated from Godot docs: SurfaceTool.set_tangent
     */
    fun setTangent(tangent: Plane) {
        checkOpen()
        ObjectCalls.ptrcallWithPlaneArg(Binds.setTangentBind, segment, tangent)
    }

    /**
     * Specifies a set of UV coordinates to use for the next vertex. If every vertex needs to have this
     * information set and you fail to submit it for the first vertex, this information may not be used
     * at all.
     *
     * Generated from Godot docs: SurfaceTool.set_uv
     */
    fun setUv(uv: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(Binds.setUvBind, segment, uv)
    }

    /**
     * Specifies an optional second set of UV coordinates to use for the next vertex. If every vertex
     * needs to have this information set and you fail to submit it for the first vertex, this
     * information may not be used at all.
     *
     * Generated from Godot docs: SurfaceTool.set_uv2
     */
    fun setUv2(uv2: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(Binds.setUv2Bind, segment, uv2)
    }

    /**
     * Specifies an array of bones to use for the next vertex. `bones` must contain 4 integers.
     *
     * Generated from Godot docs: SurfaceTool.set_bones
     */
    fun setBones(bones: List<Int>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedInt32ListArg(Binds.setBonesBind, segment, bones)
    }

    /**
     * Specifies weight values to use for the next vertex. `weights` must contain 4 values. If every
     * vertex needs to have this information set and you fail to submit it for the first vertex, this
     * information may not be used at all.
     *
     * Generated from Godot docs: SurfaceTool.set_weights
     */
    fun setWeights(weights: List<Float>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedFloat32ListArg(Binds.setWeightsBind, segment, weights)
    }

    /**
     * Sets the custom value on this vertex for `channel_index`. `set_custom_format` must be called
     * first for this `channel_index`. Formats which are not RGBA will ignore other color channels.
     *
     * Generated from Godot docs: SurfaceTool.set_custom
     */
    fun setCustom(channelIndex: Int, customColor: Color) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndColorArg(Binds.setCustomBind, segment, channelIndex, customColor)
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
        checkOpen()
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setSmoothGroupBind, segment, index)
    }

    /**
     * Inserts a triangle fan made of array data into `Mesh` being constructed. Requires the primitive
     * type be set to `Mesh.PrimitiveType.TRIANGLES`.
     *
     * Generated from Godot docs: SurfaceTool.add_triangle_fan
     */
    fun addTriangleFan(vertices: List<Vector3>, uvs: List<Vector2>, colors: List<Color>, uv2s: List<Vector2>, normals: List<Vector3>, tangents: List<Plane>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedVector3ListPackedVector2ListPackedColorListPackedVector2ListPackedVector3ListPlaneListArgs(Binds.addTriangleFanBind, segment, vertices, uvs, colors, uv2s, normals, tangents)
    }

    /**
     * Adds a vertex to index array if you are using indexed vertices. Does not need to be called
     * before adding vertices.
     *
     * Generated from Godot docs: SurfaceTool.add_index
     */
    fun addIndex(index: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.addIndexBind, segment, index)
    }

    /**
     * Shrinks the vertex array by creating an index array. This can improve performance by avoiding
     * vertex reuse.
     *
     * Generated from Godot docs: SurfaceTool.index
     */
    fun index() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.indexBind, segment)
    }

    /**
     * Removes the index array by expanding the vertex array.
     *
     * Generated from Godot docs: SurfaceTool.deindex
     */
    fun deindex() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.deindexBind, segment)
    }

    /**
     * Generates normals from vertices so you do not have to do it manually. If `flip` is `true`, the
     * resulting normals will be inverted. `generate_normals` should be called after generating
     * geometry and before committing the mesh using `commit` or `commit_to_arrays`. For correct
     * display of normal-mapped surfaces, you will also have to generate tangents using
     * `generate_tangents`. Note: `generate_normals` only works if the primitive type is set to
     * `Mesh.PrimitiveType.TRIANGLES`. Note: `generate_normals` takes smooth groups into account. To
     * generate smooth normals, set the smooth group to a value greater than or equal to `0` using
     * `set_smooth_group` or leave the smooth group at the default of `0`. To generate flat normals,
     * set the smooth group to `-1` using `set_smooth_group` prior to adding vertices.
     *
     * Generated from Godot docs: SurfaceTool.generate_normals
     */
    fun generateNormals(flip: Boolean = false) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.generateNormalsBind, segment, flip)
    }

    /**
     * Generates a tangent vector for each vertex. Requires that each vertex already has UVs and
     * normals set (see `generate_normals`).
     *
     * Generated from Godot docs: SurfaceTool.generate_tangents
     */
    fun generateTangents() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.generateTangentsBind, segment)
    }

    /**
     * Optimizes triangle sorting for performance. Requires that `get_primitive_type` is
     * `Mesh.PrimitiveType.TRIANGLES`.
     *
     * Generated from Godot docs: SurfaceTool.optimize_indices_for_cache
     */
    fun optimizeIndicesForCache() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.optimizeIndicesForCacheBind, segment)
    }

    /**
     * Returns the axis-aligned bounding box of the vertex positions.
     *
     * Generated from Godot docs: SurfaceTool.get_aabb
     */
    fun getAabb(): AABB {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetAABB(Binds.getAabbBind, segment)
    }

    /**
     * Generates an LOD for a given `nd_threshold` in linear units (square root of quadric error
     * metric), using at most `target_index_count` indices.
     *
     * Generated from Godot docs: SurfaceTool.generate_lod
     */
    fun generateLod(ndThreshold: Double, targetIndexCount: Int = 3): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallWithDoubleAndIntArgsRetPackedInt32List(Binds.generateLodBind, segment, ndThreshold, targetIndexCount)
    }

    /**
     * Sets `Material` to be used by the `Mesh` you are constructing.
     *
     * Generated from Godot docs: SurfaceTool.set_material
     */
    fun setMaterial(material: Material?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setMaterialBind, segment, listOf(material?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Returns the type of mesh geometry, such as `Mesh.PrimitiveType.TRIANGLES`.
     *
     * Generated from Godot docs: SurfaceTool.get_primitive_type
     */
    fun getPrimitiveType(): Mesh.PrimitiveType {
        checkOpen()
        return Mesh.PrimitiveType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getPrimitiveTypeBind, segment))
    }

    /**
     * Clear all information passed into the surface tool so far.
     *
     * Generated from Godot docs: SurfaceTool.clear
     */
    fun clear() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.clearBind, segment)
    }

    /**
     * Creates a vertex array from an existing `Mesh`.
     *
     * Generated from Godot docs: SurfaceTool.create_from
     */
    fun createFrom(existing: Mesh?, surface: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectAndIntArg(Binds.createFromBind, segment, existing?.requireOpenHandle() ?: NULL_SEGMENT, surface)
    }

    /**
     * Creates this SurfaceTool from existing vertex arrays such as returned by `commit_to_arrays`,
     * `Mesh.surface_get_arrays`, `Mesh.surface_get_blend_shape_arrays`,
     * `ImporterMesh.get_surface_arrays`, and `ImporterMesh.get_surface_blend_shape_arrays`.
     * `primitive_type` controls the type of mesh data, defaulting to `Mesh.PrimitiveType.TRIANGLES`.
     *
     * Generated from Godot docs: SurfaceTool.create_from_arrays
     */
    fun createFromArrays(arrays: List<Any?>, primitiveType: Mesh.PrimitiveType = Mesh.PrimitiveType.TRIANGLES) {
        checkOpen()
        ObjectCalls.ptrcallWithArrayLongArgs(Binds.createFromArraysBind, segment, arrays, primitiveType.value)
    }

    /**
     * Creates a vertex array from the specified blend shape of an existing `Mesh`. This can be used to
     * extract a specific pose from a blend shape.
     *
     * Generated from Godot docs: SurfaceTool.create_from_blend_shape
     */
    fun createFromBlendShape(existing: Mesh?, surface: Int, blendShape: String) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectIntStringArgs(Binds.createFromBlendShapeBind, segment, existing?.requireOpenHandle() ?: NULL_SEGMENT, surface, blendShape)
    }

    /**
     * Append vertices from a given `Mesh` surface onto the current vertex array with specified
     * `Transform3D`.
     *
     * Generated from Godot docs: SurfaceTool.append_from
     */
    fun appendFrom(existing: Mesh?, surface: Int, transform: Transform3D) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectIntTransform3DArgs(Binds.appendFromBind, segment, existing?.requireOpenHandle() ?: NULL_SEGMENT, surface, transform)
    }

    /**
     * Returns a constructed `ArrayMesh` from current information passed in. If an existing `ArrayMesh`
     * is passed in as an argument, will add an extra surface to the existing `ArrayMesh`. The `flags`
     * argument can be the bitwise OR of `Mesh.ArrayFormat.FLAG_USE_DYNAMIC_UPDATE`,
     * `Mesh.ArrayFormat.FLAG_USE_8_BONE_WEIGHTS`, or `Mesh.ArrayFormat.FLAG_USES_EMPTY_VERTEX_ARRAY`.
     *
     * Generated from Godot docs: SurfaceTool.commit
     */
    fun commit(existing: ArrayMesh? = null, flags: Long = 0L): ArrayMesh? {
        checkOpen()
        return ArrayMesh.wrapOwned(ObjectCalls.ptrcallWithObjectAndLongArgsRetObject(Binds.commitBind, segment, existing?.requireOpenHandle() ?: NULL_SEGMENT, flags))
    }

    /**
     * Commits the data to the same format used by `ArrayMesh.add_surface_from_arrays`,
     * `ImporterMesh.add_surface`, and `create_from_arrays`. This way you can further process the mesh
     * data using the `ArrayMesh` or `ImporterMesh` APIs.
     *
     * Generated from Godot docs: SurfaceTool.commit_to_arrays
     */
    fun commitToArrays(): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetArray(Binds.commitToArraysBind, segment)
    }

    /**
     * Godot's `SurfaceTool.CustomFormat` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`SurfaceTool.CustomFormat.<NAME>`).
     *
     * Generated from Godot docs: SurfaceTool.CustomFormat
     */
    @JvmInline
    value class CustomFormat(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Limits range of data passed to `set_custom` to unsigned normalized 0 to 1 stored in 8 bits per
             * channel. See `Mesh.ArrayCustomFormat.RGBA8_UNORM`.
             *
             * Generated from Godot docs: SurfaceTool.CUSTOM_RGBA8_UNORM
             */
            val RGBA8_UNORM: CustomFormat get() = CustomFormat(0L)
            /**
             * Limits range of data passed to `set_custom` to signed normalized -1 to 1 stored in 8 bits per
             * channel. See `Mesh.ArrayCustomFormat.RGBA8_SNORM`.
             *
             * Generated from Godot docs: SurfaceTool.CUSTOM_RGBA8_SNORM
             */
            val RGBA8_SNORM: CustomFormat get() = CustomFormat(1L)
            /**
             * Stores data passed to `set_custom` as half precision floats, and uses only red and green color
             * channels. See `Mesh.ArrayCustomFormat.RG_HALF`.
             *
             * Generated from Godot docs: SurfaceTool.CUSTOM_RG_HALF
             */
            val RG_HALF: CustomFormat get() = CustomFormat(2L)
            /**
             * Stores data passed to `set_custom` as half precision floats and uses all color channels. See
             * `Mesh.ArrayCustomFormat.RGBA_HALF`.
             *
             * Generated from Godot docs: SurfaceTool.CUSTOM_RGBA_HALF
             */
            val RGBA_HALF: CustomFormat get() = CustomFormat(3L)
            /**
             * Stores data passed to `set_custom` as full precision floats, and uses only red color channel.
             * See `Mesh.ArrayCustomFormat.R_FLOAT`.
             *
             * Generated from Godot docs: SurfaceTool.CUSTOM_R_FLOAT
             */
            val R_FLOAT: CustomFormat get() = CustomFormat(4L)
            /**
             * Stores data passed to `set_custom` as full precision floats, and uses only red and green color
             * channels. See `Mesh.ArrayCustomFormat.RG_FLOAT`.
             *
             * Generated from Godot docs: SurfaceTool.CUSTOM_RG_FLOAT
             */
            val RG_FLOAT: CustomFormat get() = CustomFormat(5L)
            /**
             * Stores data passed to `set_custom` as full precision floats, and uses only red, green and blue
             * color channels. See `Mesh.ArrayCustomFormat.RGB_FLOAT`.
             *
             * Generated from Godot docs: SurfaceTool.CUSTOM_RGB_FLOAT
             */
            val RGB_FLOAT: CustomFormat get() = CustomFormat(6L)
            /**
             * Stores data passed to `set_custom` as full precision floats, and uses all color channels. See
             * `Mesh.ArrayCustomFormat.RGBA_FLOAT`.
             *
             * Generated from Godot docs: SurfaceTool.CUSTOM_RGBA_FLOAT
             */
            val RGBA_FLOAT: CustomFormat get() = CustomFormat(7L)
            /**
             * Used to indicate a disabled custom channel.
             *
             * Generated from Godot docs: SurfaceTool.CUSTOM_MAX
             */
            val MAX: CustomFormat get() = CustomFormat(8L)
        }
    }

    /**
     * Godot's `SurfaceTool.SkinWeightCount` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`SurfaceTool.SkinWeightCount.<NAME>`).
     *
     * Generated from Godot docs: SurfaceTool.SkinWeightCount
     */
    @JvmInline
    value class SkinWeightCount(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Each individual vertex can be influenced by only 4 bone weights.
             *
             * Generated from Godot docs: SurfaceTool.SKIN_4_WEIGHTS
             */
            val SKIN_4_WEIGHTS: SkinWeightCount get() = SkinWeightCount(0L)
            /**
             * Each individual vertex can be influenced by up to 8 bone weights.
             *
             * Generated from Godot docs: SurfaceTool.SKIN_8_WEIGHTS
             */
            val SKIN_8_WEIGHTS: SkinWeightCount get() = SkinWeightCount(1L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): SurfaceTool? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): SurfaceTool? =
            if (handle.address() == 0L) null else RefCounted.owned(SurfaceTool(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): SurfaceTool? =
            if (handle.address() == 0L) null else SurfaceTool(GodotHandle(handle))

        // Instantiate a SurfaceTool.
        @JvmStatic
        fun create(): SurfaceTool =
            RefCounted.owned(SurfaceTool(GodotHandle(ObjectCalls.constructObject("SurfaceTool"))))
    }

    private object Binds {
        private const val SET_SKIN_WEIGHT_COUNT_HASH = 618679515L
        @JvmField
        val setSkinWeightCountBind =
            ObjectCalls.getMethodBind("SurfaceTool", "set_skin_weight_count", SET_SKIN_WEIGHT_COUNT_HASH)

        private const val GET_SKIN_WEIGHT_COUNT_HASH = 1072401130L
        @JvmField
        val getSkinWeightCountBind =
            ObjectCalls.getMethodBind("SurfaceTool", "get_skin_weight_count", GET_SKIN_WEIGHT_COUNT_HASH)

        private const val SET_CUSTOM_FORMAT_HASH = 4087759856L
        @JvmField
        val setCustomFormatBind =
            ObjectCalls.getMethodBind("SurfaceTool", "set_custom_format", SET_CUSTOM_FORMAT_HASH)

        private const val GET_CUSTOM_FORMAT_HASH = 839863283L
        @JvmField
        val getCustomFormatBind =
            ObjectCalls.getMethodBind("SurfaceTool", "get_custom_format", GET_CUSTOM_FORMAT_HASH)

        private const val BEGIN_HASH = 2230304113L
        @JvmField
        val beginBind =
            ObjectCalls.getMethodBind("SurfaceTool", "begin", BEGIN_HASH)

        private const val ADD_VERTEX_HASH = 3460891852L
        @JvmField
        val addVertexBind =
            ObjectCalls.getMethodBind("SurfaceTool", "add_vertex", ADD_VERTEX_HASH)

        private const val SET_COLOR_HASH = 2920490490L
        @JvmField
        val setColorBind =
            ObjectCalls.getMethodBind("SurfaceTool", "set_color", SET_COLOR_HASH)

        private const val SET_NORMAL_HASH = 3460891852L
        @JvmField
        val setNormalBind =
            ObjectCalls.getMethodBind("SurfaceTool", "set_normal", SET_NORMAL_HASH)

        private const val SET_TANGENT_HASH = 3505987427L
        @JvmField
        val setTangentBind =
            ObjectCalls.getMethodBind("SurfaceTool", "set_tangent", SET_TANGENT_HASH)

        private const val SET_UV_HASH = 743155724L
        @JvmField
        val setUvBind =
            ObjectCalls.getMethodBind("SurfaceTool", "set_uv", SET_UV_HASH)

        private const val SET_UV2_HASH = 743155724L
        @JvmField
        val setUv2Bind =
            ObjectCalls.getMethodBind("SurfaceTool", "set_uv2", SET_UV2_HASH)

        private const val SET_BONES_HASH = 3614634198L
        @JvmField
        val setBonesBind =
            ObjectCalls.getMethodBind("SurfaceTool", "set_bones", SET_BONES_HASH)

        private const val SET_WEIGHTS_HASH = 2899603908L
        @JvmField
        val setWeightsBind =
            ObjectCalls.getMethodBind("SurfaceTool", "set_weights", SET_WEIGHTS_HASH)

        private const val SET_CUSTOM_HASH = 2878471219L
        @JvmField
        val setCustomBind =
            ObjectCalls.getMethodBind("SurfaceTool", "set_custom", SET_CUSTOM_HASH)

        private const val SET_SMOOTH_GROUP_HASH = 1286410249L
        @JvmField
        val setSmoothGroupBind =
            ObjectCalls.getMethodBind("SurfaceTool", "set_smooth_group", SET_SMOOTH_GROUP_HASH)

        private const val ADD_TRIANGLE_FAN_HASH = 2235017613L
        @JvmField
        val addTriangleFanBind =
            ObjectCalls.getMethodBind("SurfaceTool", "add_triangle_fan", ADD_TRIANGLE_FAN_HASH)

        private const val ADD_INDEX_HASH = 1286410249L
        @JvmField
        val addIndexBind =
            ObjectCalls.getMethodBind("SurfaceTool", "add_index", ADD_INDEX_HASH)

        private const val INDEX_HASH = 3218959716L
        @JvmField
        val indexBind =
            ObjectCalls.getMethodBind("SurfaceTool", "index", INDEX_HASH)

        private const val DEINDEX_HASH = 3218959716L
        @JvmField
        val deindexBind =
            ObjectCalls.getMethodBind("SurfaceTool", "deindex", DEINDEX_HASH)

        private const val GENERATE_NORMALS_HASH = 107499316L
        @JvmField
        val generateNormalsBind =
            ObjectCalls.getMethodBind("SurfaceTool", "generate_normals", GENERATE_NORMALS_HASH)

        private const val GENERATE_TANGENTS_HASH = 3218959716L
        @JvmField
        val generateTangentsBind =
            ObjectCalls.getMethodBind("SurfaceTool", "generate_tangents", GENERATE_TANGENTS_HASH)

        private const val OPTIMIZE_INDICES_FOR_CACHE_HASH = 3218959716L
        @JvmField
        val optimizeIndicesForCacheBind =
            ObjectCalls.getMethodBind("SurfaceTool", "optimize_indices_for_cache", OPTIMIZE_INDICES_FOR_CACHE_HASH)

        private const val GET_AABB_HASH = 1068685055L
        @JvmField
        val getAabbBind =
            ObjectCalls.getMethodBind("SurfaceTool", "get_aabb", GET_AABB_HASH)

        private const val GENERATE_LOD_HASH = 1938056459L
        @JvmField
        val generateLodBind =
            ObjectCalls.getMethodBind("SurfaceTool", "generate_lod", GENERATE_LOD_HASH)

        private const val SET_MATERIAL_HASH = 2757459619L
        @JvmField
        val setMaterialBind =
            ObjectCalls.getMethodBind("SurfaceTool", "set_material", SET_MATERIAL_HASH)

        private const val GET_PRIMITIVE_TYPE_HASH = 768822145L
        @JvmField
        val getPrimitiveTypeBind =
            ObjectCalls.getMethodBind("SurfaceTool", "get_primitive_type", GET_PRIMITIVE_TYPE_HASH)

        private const val CLEAR_HASH = 3218959716L
        @JvmField
        val clearBind =
            ObjectCalls.getMethodBind("SurfaceTool", "clear", CLEAR_HASH)

        private const val CREATE_FROM_HASH = 1767024570L
        @JvmField
        val createFromBind =
            ObjectCalls.getMethodBind("SurfaceTool", "create_from", CREATE_FROM_HASH)

        private const val CREATE_FROM_ARRAYS_HASH = 1894639680L
        @JvmField
        val createFromArraysBind =
            ObjectCalls.getMethodBind("SurfaceTool", "create_from_arrays", CREATE_FROM_ARRAYS_HASH)

        private const val CREATE_FROM_BLEND_SHAPE_HASH = 1306185582L
        @JvmField
        val createFromBlendShapeBind =
            ObjectCalls.getMethodBind("SurfaceTool", "create_from_blend_shape", CREATE_FROM_BLEND_SHAPE_HASH)

        private const val APPEND_FROM_HASH = 2217967155L
        @JvmField
        val appendFromBind =
            ObjectCalls.getMethodBind("SurfaceTool", "append_from", APPEND_FROM_HASH)

        private const val COMMIT_HASH = 4107864055L
        @JvmField
        val commitBind =
            ObjectCalls.getMethodBind("SurfaceTool", "commit", COMMIT_HASH)

        private const val COMMIT_TO_ARRAYS_HASH = 2915620761L
        @JvmField
        val commitToArraysBind =
            ObjectCalls.getMethodBind("SurfaceTool", "commit_to_arrays", COMMIT_TO_ARRAYS_HASH)
    }
}
