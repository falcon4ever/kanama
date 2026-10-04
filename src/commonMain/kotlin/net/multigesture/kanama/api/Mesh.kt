package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Vector2i
import net.multigesture.kanama.types.Vector3

/**
 * A `Resource` that contains vertex array-based geometry.
 *
 * Generated from Godot docs: Mesh
 */
open class Mesh(handle: GodotHandle) : Resource(handle) {
    var lightmapSizeHint: Vector2i
        @JvmName("lightmapSizeHintProperty")
        get() = getLightmapSizeHint()
        @JvmName("setLightmapSizeHintProperty")
        set(value) = setLightmapSizeHint(value)

    /**
     * Sets a hint to be used for lightmap resolution.
     *
     * Generated from Godot docs: Mesh.set_lightmap_size_hint
     */
    fun setLightmapSizeHint(size: Vector2i) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2iArg(setLightmapSizeHintBind, segment, size)
    }

    /**
     * Sets a hint to be used for lightmap resolution.
     *
     * Generated from Godot docs: Mesh.get_lightmap_size_hint
     */
    fun getLightmapSizeHint(): Vector2i {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2i(getLightmapSizeHintBind, segment)
    }

    /**
     * Returns the smallest `AABB` enclosing this mesh in local space. Not affected by `custom_aabb`.
     * Note: This is only implemented for `ArrayMesh` and `PrimitiveMesh`.
     *
     * Generated from Godot docs: Mesh.get_aabb
     */
    fun getAabb(): AABB {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetAABB(getAabbBind, segment)
    }

    /**
     * Returns all the vertices that make up the faces of the mesh. Each three vertices represent one
     * triangle.
     *
     * Generated from Godot docs: Mesh.get_faces
     */
    fun getFaces(): List<Vector3> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedVector3List(getFacesBind, segment)
    }

    /**
     * Returns the number of surfaces that the `Mesh` holds. This is equivalent to
     * `MeshInstance3D.get_surface_override_material_count`.
     *
     * Generated from Godot docs: Mesh.get_surface_count
     */
    fun getSurfaceCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getSurfaceCountBind, segment)
    }

    /**
     * Returns the arrays for the vertices, normals, UVs, etc. that make up the requested surface (see
     * `ArrayMesh.add_surface_from_arrays`).
     *
     * Generated from Godot docs: Mesh.surface_get_arrays
     */
    fun surfaceGetArrays(surfIdx: Int): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetArray(surfaceGetArraysBind, segment, surfIdx)
    }

    /**
     * Returns the blend shape arrays for the requested surface.
     *
     * Generated from Godot docs: Mesh.surface_get_blend_shape_arrays
     */
    fun surfaceGetBlendShapeArrays(surfIdx: Int): List<List<Any?>> {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetArrayList(surfaceGetBlendShapeArraysBind, segment, surfIdx)
    }

    /**
     * Sets a `Material` for a given surface. Surface will be rendered using this material. Note: This
     * assigns the material within the `Mesh` resource, not the `Material` associated to the
     * `MeshInstance3D`'s Surface Material Override properties. To set the `Material` associated to the
     * `MeshInstance3D`'s Surface Material Override properties, use
     * `MeshInstance3D.set_surface_override_material` instead.
     *
     * Generated from Godot docs: Mesh.surface_set_material
     */
    fun surfaceSetMaterial(surfIdx: Int, material: Material?) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndObjectArg(surfaceSetMaterialBind, segment, surfIdx, material?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Returns a `Material` in a given surface. Surface is rendered using this material. Note: This
     * returns the material within the `Mesh` resource, not the `Material` associated to the
     * `MeshInstance3D`'s Surface Material Override properties. To get the `Material` associated to the
     * `MeshInstance3D`'s Surface Material Override properties, use
     * `MeshInstance3D.get_surface_override_material` instead.
     *
     * Generated from Godot docs: Mesh.surface_get_material
     */
    fun surfaceGetMaterial(surfIdx: Int): Material? {
        checkOpen()
        return Material.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(surfaceGetMaterialBind, segment, surfIdx))
    }

    /**
     * Creates a placeholder version of this resource (`PlaceholderMesh`).
     *
     * Generated from Godot docs: Mesh.create_placeholder
     */
    fun createPlaceholder(): Resource? {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetObject(createPlaceholderBind, segment)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return Resource.wrapOwned(ret)
    }

    /**
     * Calculate a `ConcavePolygonShape3D` from the mesh.
     *
     * Generated from Godot docs: Mesh.create_trimesh_shape
     */
    fun createTrimeshShape(): ConcavePolygonShape3D? {
        checkOpen()
        return ConcavePolygonShape3D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(createTrimeshShapeBind, segment))
    }

    /**
     * Calculate a `ConvexPolygonShape3D` from the mesh. If `clean` is `true` (default), duplicate and
     * interior vertices are removed automatically. You can set it to `false` to make the process
     * faster if not needed. If `simplify` is `true`, the geometry can be further simplified to reduce
     * the number of vertices. Disabled by default.
     *
     * Generated from Godot docs: Mesh.create_convex_shape
     */
    fun createConvexShape(clean: Boolean = true, simplify: Boolean = false): ConvexPolygonShape3D? {
        checkOpen()
        return ConvexPolygonShape3D.wrapOwned(ObjectCalls.ptrcallWithTwoBoolArgsRetObject(createConvexShapeBind, segment, clean, simplify))
    }

    /**
     * Calculate an outline mesh at a defined offset (margin) from the original mesh. Note: This method
     * typically returns the vertices in reverse order (e.g. clockwise to counterclockwise).
     *
     * Generated from Godot docs: Mesh.create_outline
     */
    fun createOutline(margin: Double): Mesh? {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithDoubleArgRetObject(createOutlineBind, segment, margin)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return Mesh.wrapOwned(ret)
    }

    /**
     * Generate a `TriangleMesh` from the mesh. Considers only surfaces using one of these primitive
     * types: `PrimitiveType.TRIANGLES`, `PrimitiveType.TRIANGLE_STRIP`.
     *
     * Generated from Godot docs: Mesh.generate_triangle_mesh
     */
    fun generateTriangleMesh(): TriangleMesh? {
        checkOpen()
        return TriangleMesh.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(generateTriangleMeshBind, segment))
    }

    /**
     * Godot's `Mesh.PrimitiveType` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`Mesh.PrimitiveType.<NAME>`).
     *
     * Generated from Godot docs: Mesh.PrimitiveType
     */
    @JvmInline
    value class PrimitiveType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Render array as points (one vertex equals one point).
             *
             * Generated from Godot docs: Mesh.PRIMITIVE_POINTS
             */
            val POINTS: PrimitiveType get() = PrimitiveType(0L)
            /**
             * Render array as lines (every two vertices a line is created).
             *
             * Generated from Godot docs: Mesh.PRIMITIVE_LINES
             */
            val LINES: PrimitiveType get() = PrimitiveType(1L)
            /**
             * Render array as line strip.
             *
             * Generated from Godot docs: Mesh.PRIMITIVE_LINE_STRIP
             */
            val LINE_STRIP: PrimitiveType get() = PrimitiveType(2L)
            /**
             * Render array as triangles (every three vertices a triangle is created).
             *
             * Generated from Godot docs: Mesh.PRIMITIVE_TRIANGLES
             */
            val TRIANGLES: PrimitiveType get() = PrimitiveType(3L)
            /**
             * Render array as triangle strips.
             *
             * Generated from Godot docs: Mesh.PRIMITIVE_TRIANGLE_STRIP
             */
            val TRIANGLE_STRIP: PrimitiveType get() = PrimitiveType(4L)
        }
    }

    /**
     * Godot's `Mesh.ArrayType` enum as a typed value: `.value` is the raw number Godot uses, and the
     * companion holds the named values (`Mesh.ArrayType.<NAME>`).
     *
     * Generated from Godot docs: Mesh.ArrayType
     */
    @JvmInline
    value class ArrayType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * `PackedVector3Array`, `PackedVector2Array`, or `Array` of vertex positions.
             *
             * Generated from Godot docs: Mesh.ARRAY_VERTEX
             */
            val VERTEX: ArrayType get() = ArrayType(0L)
            /**
             * `PackedVector3Array` of vertex normals. Note: The array has to consist of normal vectors,
             * otherwise they will be normalized by the engine, potentially causing visual discrepancies.
             *
             * Generated from Godot docs: Mesh.ARRAY_NORMAL
             */
            val NORMAL: ArrayType get() = ArrayType(1L)
            /**
             * `PackedFloat32Array` of vertex tangents. Each element in groups of 4 floats, first 3 floats
             * determine the tangent, and the last the binormal direction as -1 or 1.
             *
             * Generated from Godot docs: Mesh.ARRAY_TANGENT
             */
            val TANGENT: ArrayType get() = ArrayType(2L)
            /**
             * `PackedColorArray` of vertex colors.
             *
             * Generated from Godot docs: Mesh.ARRAY_COLOR
             */
            val COLOR: ArrayType get() = ArrayType(3L)
            /**
             * `PackedVector2Array` for UV coordinates.
             *
             * Generated from Godot docs: Mesh.ARRAY_TEX_UV
             */
            val TEX_UV: ArrayType get() = ArrayType(4L)
            /**
             * `PackedVector2Array` for second UV coordinates.
             *
             * Generated from Godot docs: Mesh.ARRAY_TEX_UV2
             */
            val TEX_UV2: ArrayType get() = ArrayType(5L)
            /**
             * Contains custom color channel 0. `PackedByteArray` if `(format >>
             * Mesh.ARRAY_FORMAT_CUSTOM0_SHIFT) & Mesh.ARRAY_FORMAT_CUSTOM_MASK` is
             * `ArrayCustomFormat.RGBA8_UNORM`, `ArrayCustomFormat.RGBA8_SNORM`, `ArrayCustomFormat.RG_HALF`,
             * or `ArrayCustomFormat.RGBA_HALF`. `PackedFloat32Array` otherwise.
             *
             * Generated from Godot docs: Mesh.ARRAY_CUSTOM0
             */
            val CUSTOM0: ArrayType get() = ArrayType(6L)
            /**
             * Contains custom color channel 1. `PackedByteArray` if `(format >>
             * Mesh.ARRAY_FORMAT_CUSTOM1_SHIFT) & Mesh.ARRAY_FORMAT_CUSTOM_MASK` is
             * `ArrayCustomFormat.RGBA8_UNORM`, `ArrayCustomFormat.RGBA8_SNORM`, `ArrayCustomFormat.RG_HALF`,
             * or `ArrayCustomFormat.RGBA_HALF`. `PackedFloat32Array` otherwise.
             *
             * Generated from Godot docs: Mesh.ARRAY_CUSTOM1
             */
            val CUSTOM1: ArrayType get() = ArrayType(7L)
            /**
             * Contains custom color channel 2. `PackedByteArray` if `(format >>
             * Mesh.ARRAY_FORMAT_CUSTOM2_SHIFT) & Mesh.ARRAY_FORMAT_CUSTOM_MASK` is
             * `ArrayCustomFormat.RGBA8_UNORM`, `ArrayCustomFormat.RGBA8_SNORM`, `ArrayCustomFormat.RG_HALF`,
             * or `ArrayCustomFormat.RGBA_HALF`. `PackedFloat32Array` otherwise.
             *
             * Generated from Godot docs: Mesh.ARRAY_CUSTOM2
             */
            val CUSTOM2: ArrayType get() = ArrayType(8L)
            /**
             * Contains custom color channel 3. `PackedByteArray` if `(format >>
             * Mesh.ARRAY_FORMAT_CUSTOM3_SHIFT) & Mesh.ARRAY_FORMAT_CUSTOM_MASK` is
             * `ArrayCustomFormat.RGBA8_UNORM`, `ArrayCustomFormat.RGBA8_SNORM`, `ArrayCustomFormat.RG_HALF`,
             * or `ArrayCustomFormat.RGBA_HALF`. `PackedFloat32Array` otherwise.
             *
             * Generated from Godot docs: Mesh.ARRAY_CUSTOM3
             */
            val CUSTOM3: ArrayType get() = ArrayType(9L)
            /**
             * `PackedFloat32Array` or `PackedInt32Array` of bone indices. Contains either 4 or 8 numbers per
             * vertex depending on the presence of the `ArrayFormat.FLAG_USE_8_BONE_WEIGHTS` flag.
             *
             * Generated from Godot docs: Mesh.ARRAY_BONES
             */
            val BONES: ArrayType get() = ArrayType(10L)
            /**
             * `PackedFloat32Array` or `PackedFloat64Array` of bone weights in the range `0.0` to `1.0`
             * (inclusive). Contains either 4 or 8 numbers per vertex depending on the presence of the
             * `ArrayFormat.FLAG_USE_8_BONE_WEIGHTS` flag.
             *
             * Generated from Godot docs: Mesh.ARRAY_WEIGHTS
             */
            val WEIGHTS: ArrayType get() = ArrayType(11L)
            /**
             * `PackedInt32Array` of integers used as indices referencing vertices, colors, normals, tangents,
             * and textures. All of those arrays must have the same number of elements as the vertex array. No
             * index can be beyond the vertex array size. When this index array is present, it puts the
             * function into "index mode," where the index selects the i'th vertex, normal, tangent, color, UV,
             * etc. This means if you want to have different normals or colors along an edge, you have to
             * duplicate the vertices. For triangles, the index array is interpreted as triples, referring to
             * the vertices of each triangle. For lines, the index array is in pairs indicating the start and
             * end of each line.
             *
             * Generated from Godot docs: Mesh.ARRAY_INDEX
             */
            val INDEX: ArrayType get() = ArrayType(12L)
            /**
             * Represents the size of the `ArrayType` enum.
             *
             * Generated from Godot docs: Mesh.ARRAY_MAX
             */
            val MAX: ArrayType get() = ArrayType(13L)
        }
    }

    /**
     * Godot's `Mesh.ArrayCustomFormat` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`Mesh.ArrayCustomFormat.<NAME>`).
     *
     * Generated from Godot docs: Mesh.ArrayCustomFormat
     */
    @JvmInline
    value class ArrayCustomFormat(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Indicates this custom channel contains unsigned normalized byte colors from 0 to 1, encoded as
             * `PackedByteArray`.
             *
             * Generated from Godot docs: Mesh.ARRAY_CUSTOM_RGBA8_UNORM
             */
            val RGBA8_UNORM: ArrayCustomFormat get() = ArrayCustomFormat(0L)
            /**
             * Indicates this custom channel contains signed normalized byte colors from -1 to 1, encoded as
             * `PackedByteArray`.
             *
             * Generated from Godot docs: Mesh.ARRAY_CUSTOM_RGBA8_SNORM
             */
            val RGBA8_SNORM: ArrayCustomFormat get() = ArrayCustomFormat(1L)
            /**
             * Indicates this custom channel contains half precision float colors, encoded as
             * `PackedByteArray`. Only red and green channels are used.
             *
             * Generated from Godot docs: Mesh.ARRAY_CUSTOM_RG_HALF
             */
            val RG_HALF: ArrayCustomFormat get() = ArrayCustomFormat(2L)
            /**
             * Indicates this custom channel contains half precision float colors, encoded as
             * `PackedByteArray`.
             *
             * Generated from Godot docs: Mesh.ARRAY_CUSTOM_RGBA_HALF
             */
            val RGBA_HALF: ArrayCustomFormat get() = ArrayCustomFormat(3L)
            /**
             * Indicates this custom channel contains full float colors, in a `PackedFloat32Array`. Only the
             * red channel is used.
             *
             * Generated from Godot docs: Mesh.ARRAY_CUSTOM_R_FLOAT
             */
            val R_FLOAT: ArrayCustomFormat get() = ArrayCustomFormat(4L)
            /**
             * Indicates this custom channel contains full float colors, in a `PackedFloat32Array`. Only red
             * and green channels are used.
             *
             * Generated from Godot docs: Mesh.ARRAY_CUSTOM_RG_FLOAT
             */
            val RG_FLOAT: ArrayCustomFormat get() = ArrayCustomFormat(5L)
            /**
             * Indicates this custom channel contains full float colors, in a `PackedFloat32Array`. Only red,
             * green and blue channels are used.
             *
             * Generated from Godot docs: Mesh.ARRAY_CUSTOM_RGB_FLOAT
             */
            val RGB_FLOAT: ArrayCustomFormat get() = ArrayCustomFormat(6L)
            /**
             * Indicates this custom channel contains full float colors, in a `PackedFloat32Array`.
             *
             * Generated from Godot docs: Mesh.ARRAY_CUSTOM_RGBA_FLOAT
             */
            val RGBA_FLOAT: ArrayCustomFormat get() = ArrayCustomFormat(7L)
            /**
             * Represents the size of the `ArrayCustomFormat` enum.
             *
             * Generated from Godot docs: Mesh.ARRAY_CUSTOM_MAX
             */
            val MAX: ArrayCustomFormat get() = ArrayCustomFormat(8L)
        }
    }

    /**
     * Godot's `Mesh.ArrayFormat` bitfield as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`Mesh.ArrayFormat.<NAME>`).
     *
     * Generated from Godot docs: Mesh.ArrayFormat
     */
    @JvmInline
    value class ArrayFormat(override val value: Long) : GodotEnumValue {
        infix fun or(other: ArrayFormat): ArrayFormat = ArrayFormat(value or other.value)

        infix fun and(other: ArrayFormat): ArrayFormat = ArrayFormat(value and other.value)

        infix fun xor(other: ArrayFormat): ArrayFormat = ArrayFormat(value xor other.value)

        fun inv(): ArrayFormat = ArrayFormat(value.inv())

        operator fun contains(other: ArrayFormat): Boolean = (value and other.value) == other.value

        companion object {
            /**
             * Mesh array contains vertices. All meshes require a vertex array so this should always be
             * present.
             *
             * Generated from Godot docs: Mesh.ARRAY_FORMAT_VERTEX
             */
            val FORMAT_VERTEX: ArrayFormat get() = ArrayFormat(1L)
            /**
             * Mesh array contains normals.
             *
             * Generated from Godot docs: Mesh.ARRAY_FORMAT_NORMAL
             */
            val FORMAT_NORMAL: ArrayFormat get() = ArrayFormat(2L)
            /**
             * Mesh array contains tangents.
             *
             * Generated from Godot docs: Mesh.ARRAY_FORMAT_TANGENT
             */
            val FORMAT_TANGENT: ArrayFormat get() = ArrayFormat(4L)
            /**
             * Mesh array contains colors.
             *
             * Generated from Godot docs: Mesh.ARRAY_FORMAT_COLOR
             */
            val FORMAT_COLOR: ArrayFormat get() = ArrayFormat(8L)
            /**
             * Mesh array contains UVs.
             *
             * Generated from Godot docs: Mesh.ARRAY_FORMAT_TEX_UV
             */
            val FORMAT_TEX_UV: ArrayFormat get() = ArrayFormat(16L)
            /**
             * Mesh array contains second UV.
             *
             * Generated from Godot docs: Mesh.ARRAY_FORMAT_TEX_UV2
             */
            val FORMAT_TEX_UV2: ArrayFormat get() = ArrayFormat(32L)
            /**
             * Mesh array contains custom channel index 0.
             *
             * Generated from Godot docs: Mesh.ARRAY_FORMAT_CUSTOM0
             */
            val FORMAT_CUSTOM0: ArrayFormat get() = ArrayFormat(64L)
            /**
             * Mesh array contains custom channel index 1.
             *
             * Generated from Godot docs: Mesh.ARRAY_FORMAT_CUSTOM1
             */
            val FORMAT_CUSTOM1: ArrayFormat get() = ArrayFormat(128L)
            /**
             * Mesh array contains custom channel index 2.
             *
             * Generated from Godot docs: Mesh.ARRAY_FORMAT_CUSTOM2
             */
            val FORMAT_CUSTOM2: ArrayFormat get() = ArrayFormat(256L)
            /**
             * Mesh array contains custom channel index 3.
             *
             * Generated from Godot docs: Mesh.ARRAY_FORMAT_CUSTOM3
             */
            val FORMAT_CUSTOM3: ArrayFormat get() = ArrayFormat(512L)
            /**
             * Mesh array contains bones.
             *
             * Generated from Godot docs: Mesh.ARRAY_FORMAT_BONES
             */
            val FORMAT_BONES: ArrayFormat get() = ArrayFormat(1024L)
            /**
             * Mesh array contains bone weights.
             *
             * Generated from Godot docs: Mesh.ARRAY_FORMAT_WEIGHTS
             */
            val FORMAT_WEIGHTS: ArrayFormat get() = ArrayFormat(2048L)
            /**
             * Mesh array uses indices.
             *
             * Generated from Godot docs: Mesh.ARRAY_FORMAT_INDEX
             */
            val FORMAT_INDEX: ArrayFormat get() = ArrayFormat(4096L)
            /**
             * Mask of mesh channels permitted in blend shapes.
             *
             * Generated from Godot docs: Mesh.ARRAY_FORMAT_BLEND_SHAPE_MASK
             */
            val FORMAT_BLEND_SHAPE_MASK: ArrayFormat get() = ArrayFormat(7L)
            /**
             * Shift of first custom channel.
             *
             * Generated from Godot docs: Mesh.ARRAY_FORMAT_CUSTOM_BASE
             */
            val FORMAT_CUSTOM_BASE: ArrayFormat get() = ArrayFormat(13L)
            /**
             * Number of format bits per custom channel. See `ArrayCustomFormat`.
             *
             * Generated from Godot docs: Mesh.ARRAY_FORMAT_CUSTOM_BITS
             */
            val FORMAT_CUSTOM_BITS: ArrayFormat get() = ArrayFormat(3L)
            /**
             * Amount to shift `ArrayCustomFormat` for custom channel index 0.
             *
             * Generated from Godot docs: Mesh.ARRAY_FORMAT_CUSTOM0_SHIFT
             */
            val FORMAT_CUSTOM0_SHIFT: ArrayFormat get() = ArrayFormat(13L)
            /**
             * Amount to shift `ArrayCustomFormat` for custom channel index 1.
             *
             * Generated from Godot docs: Mesh.ARRAY_FORMAT_CUSTOM1_SHIFT
             */
            val FORMAT_CUSTOM1_SHIFT: ArrayFormat get() = ArrayFormat(16L)
            /**
             * Amount to shift `ArrayCustomFormat` for custom channel index 2.
             *
             * Generated from Godot docs: Mesh.ARRAY_FORMAT_CUSTOM2_SHIFT
             */
            val FORMAT_CUSTOM2_SHIFT: ArrayFormat get() = ArrayFormat(19L)
            /**
             * Amount to shift `ArrayCustomFormat` for custom channel index 3.
             *
             * Generated from Godot docs: Mesh.ARRAY_FORMAT_CUSTOM3_SHIFT
             */
            val FORMAT_CUSTOM3_SHIFT: ArrayFormat get() = ArrayFormat(22L)
            /**
             * Mask of custom format bits per custom channel. Must be shifted by one of the SHIFT constants.
             * See `ArrayCustomFormat`.
             *
             * Generated from Godot docs: Mesh.ARRAY_FORMAT_CUSTOM_MASK
             */
            val FORMAT_CUSTOM_MASK: ArrayFormat get() = ArrayFormat(7L)
            /**
             * Shift of first compress flag. Compress flags should be passed to
             * `ArrayMesh.add_surface_from_arrays` and `SurfaceTool.commit`.
             *
             * Generated from Godot docs: Mesh.ARRAY_COMPRESS_FLAGS_BASE
             */
            val COMPRESS_FLAGS_BASE: ArrayFormat get() = ArrayFormat(25L)
            /**
             * Flag used to mark that the array contains 2D vertices.
             *
             * Generated from Godot docs: Mesh.ARRAY_FLAG_USE_2D_VERTICES
             */
            val FLAG_USE_2D_VERTICES: ArrayFormat get() = ArrayFormat(33554432L)
            /**
             * Flag used to mark that the mesh data will use `GL_DYNAMIC_DRAW` on GLES. Unused on Vulkan.
             *
             * Generated from Godot docs: Mesh.ARRAY_FLAG_USE_DYNAMIC_UPDATE
             */
            val FLAG_USE_DYNAMIC_UPDATE: ArrayFormat get() = ArrayFormat(67108864L)
            /**
             * Flag used to mark that the mesh contains up to 8 bone influences per vertex. This flag indicates
             * that `ArrayType.BONES` and `ArrayType.WEIGHTS` elements will have double length.
             *
             * Generated from Godot docs: Mesh.ARRAY_FLAG_USE_8_BONE_WEIGHTS
             */
            val FLAG_USE_8_BONE_WEIGHTS: ArrayFormat get() = ArrayFormat(134217728L)
            /**
             * Flag used to mark that the mesh intentionally contains no vertex array.
             *
             * Generated from Godot docs: Mesh.ARRAY_FLAG_USES_EMPTY_VERTEX_ARRAY
             */
            val FLAG_USES_EMPTY_VERTEX_ARRAY: ArrayFormat get() = ArrayFormat(268435456L)
            /**
             * Flag used to mark that a mesh is using compressed attributes (vertices, normals, tangents, UVs).
             * When this form of compression is enabled, vertex positions will be packed into an RGBA16UNORM
             * attribute and scaled in the vertex shader. The normal and tangent will be packed into an
             * RG16UNORM representing an axis, and a 16-bit float stored in the A-channel of the vertex. UVs
             * will use 16-bit normalized floats instead of full 32-bit signed floats. When using this
             * compression mode you must use either vertices, normals, and tangents or only vertices. You
             * cannot use normals without tangents. Importers will automatically enable this compression if
             * they can.
             *
             * Generated from Godot docs: Mesh.ARRAY_FLAG_COMPRESS_ATTRIBUTES
             */
            val FLAG_COMPRESS_ATTRIBUTES: ArrayFormat get() = ArrayFormat(536870912L)
        }
    }

    /**
     * Godot's `Mesh.BlendShapeMode` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`Mesh.BlendShapeMode.<NAME>`).
     *
     * Generated from Godot docs: Mesh.BlendShapeMode
     */
    @JvmInline
    value class BlendShapeMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Blend shapes are normalized.
             *
             * Generated from Godot docs: Mesh.BLEND_SHAPE_MODE_NORMALIZED
             */
            val NORMALIZED: BlendShapeMode get() = BlendShapeMode(0L)
            /**
             * Blend shapes are relative to base weight.
             *
             * Generated from Godot docs: Mesh.BLEND_SHAPE_MODE_RELATIVE
             */
            val RELATIVE: BlendShapeMode get() = BlendShapeMode(1L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Mesh? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): Mesh? =
            if (handle.address() == 0L) null else RefCounted.owned(Mesh(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): Mesh? =
            if (handle.address() == 0L) null else Mesh(GodotHandle(handle))

        // Downcast a GodotObject to Mesh (null if not).
        @JvmStatic
        fun fromObject(value: GodotObject): Mesh? =
            if (value.isClass("Mesh")) RefCounted.retained(Mesh(value.handle)) else null

        private const val SET_LIGHTMAP_SIZE_HINT_HASH = 1130785943L
        private val setLightmapSizeHintBind by lazy {
            ObjectCalls.getMethodBind("Mesh", "set_lightmap_size_hint", SET_LIGHTMAP_SIZE_HINT_HASH)
        }

        private const val GET_LIGHTMAP_SIZE_HINT_HASH = 3690982128L
        private val getLightmapSizeHintBind by lazy {
            ObjectCalls.getMethodBind("Mesh", "get_lightmap_size_hint", GET_LIGHTMAP_SIZE_HINT_HASH)
        }

        private const val GET_AABB_HASH = 1068685055L
        private val getAabbBind by lazy {
            ObjectCalls.getMethodBind("Mesh", "get_aabb", GET_AABB_HASH)
        }

        private const val GET_FACES_HASH = 497664490L
        private val getFacesBind by lazy {
            ObjectCalls.getMethodBind("Mesh", "get_faces", GET_FACES_HASH)
        }

        private const val GET_SURFACE_COUNT_HASH = 3905245786L
        private val getSurfaceCountBind by lazy {
            ObjectCalls.getMethodBind("Mesh", "get_surface_count", GET_SURFACE_COUNT_HASH)
        }

        private const val SURFACE_GET_ARRAYS_HASH = 663333327L
        private val surfaceGetArraysBind by lazy {
            ObjectCalls.getMethodBind("Mesh", "surface_get_arrays", SURFACE_GET_ARRAYS_HASH)
        }

        private const val SURFACE_GET_BLEND_SHAPE_ARRAYS_HASH = 663333327L
        private val surfaceGetBlendShapeArraysBind by lazy {
            ObjectCalls.getMethodBind("Mesh", "surface_get_blend_shape_arrays", SURFACE_GET_BLEND_SHAPE_ARRAYS_HASH)
        }

        private const val SURFACE_SET_MATERIAL_HASH = 3671737478L
        private val surfaceSetMaterialBind by lazy {
            ObjectCalls.getMethodBind("Mesh", "surface_set_material", SURFACE_SET_MATERIAL_HASH)
        }

        private const val SURFACE_GET_MATERIAL_HASH = 2897466400L
        private val surfaceGetMaterialBind by lazy {
            ObjectCalls.getMethodBind("Mesh", "surface_get_material", SURFACE_GET_MATERIAL_HASH)
        }

        private const val CREATE_PLACEHOLDER_HASH = 121922552L
        private val createPlaceholderBind by lazy {
            ObjectCalls.getMethodBind("Mesh", "create_placeholder", CREATE_PLACEHOLDER_HASH)
        }

        private const val CREATE_TRIMESH_SHAPE_HASH = 4160111210L
        private val createTrimeshShapeBind by lazy {
            ObjectCalls.getMethodBind("Mesh", "create_trimesh_shape", CREATE_TRIMESH_SHAPE_HASH)
        }

        private const val CREATE_CONVEX_SHAPE_HASH = 2529984628L
        private val createConvexShapeBind by lazy {
            ObjectCalls.getMethodBind("Mesh", "create_convex_shape", CREATE_CONVEX_SHAPE_HASH)
        }

        private const val CREATE_OUTLINE_HASH = 1208642001L
        private val createOutlineBind by lazy {
            ObjectCalls.getMethodBind("Mesh", "create_outline", CREATE_OUTLINE_HASH)
        }

        private const val GENERATE_TRIANGLE_MESH_HASH = 3476533166L
        private val generateTriangleMeshBind by lazy {
            ObjectCalls.getMethodBind("Mesh", "generate_triangle_mesh", GENERATE_TRIANGLE_MESH_HASH)
        }
    }
}
