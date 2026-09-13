package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Vector2i
import net.multigesture.kanama.types.Vector3
import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName

/**
 * A `Resource` that contains vertex array-based geometry.
 *
 * Generated from Godot docs: Mesh
 */
open class Mesh internal constructor(handle: GodotHandle) : Resource(handle) {
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
        ObjectCalls.ptrcallWithIntAndObjectArg(
            surfaceSetMaterialBind,
            segment,
            surfIdx,
            material?.requireOpenHandle() ?: MemorySegment.NULL,
        )
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
        return Material.wrap(ObjectCalls.ptrcallWithIntArgRetObject(surfaceGetMaterialBind, segment, surfIdx))
    }

    /**
     * Creates a placeholder version of this resource (`PlaceholderMesh`).
     *
     * Generated from Godot docs: Mesh.create_placeholder
     */
    fun createPlaceholder(): Resource? {
        checkOpen()
        return Resource.wrap(ObjectCalls.ptrcallNoArgsRetObject(createPlaceholderBind, segment))
    }

    /**
     * Calculate a `ConcavePolygonShape3D` from the mesh.
     *
     * Generated from Godot docs: Mesh.create_trimesh_shape
     */
    fun createTrimeshShape(): ConcavePolygonShape3D? {
        checkOpen()
        return ConcavePolygonShape3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(createTrimeshShapeBind, segment))
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
        return ConvexPolygonShape3D.wrap(
            ObjectCalls.ptrcallWithTwoBoolArgsRetObject(createConvexShapeBind, segment, clean, simplify),
        )
    }

    /**
     * Calculate an outline mesh at a defined offset (margin) from the original mesh. Note: This method
     * typically returns the vertices in reverse order (e.g. clockwise to counterclockwise).
     *
     * Generated from Godot docs: Mesh.create_outline
     */
    fun createOutline(margin: Double): Mesh? {
        checkOpen()
        return wrap(ObjectCalls.ptrcallWithDoubleArgRetObject(createOutlineBind, segment, margin))
    }

    /**
     * Generate a `TriangleMesh` from the mesh. Considers only surfaces using one of these primitive
     * types: `PRIMITIVE_TRIANGLES`, `PRIMITIVE_TRIANGLE_STRIP`.
     *
     * Generated from Godot docs: Mesh.generate_triangle_mesh
     */
    fun generateTriangleMesh(): TriangleMesh? {
        checkOpen()
        return TriangleMesh.wrap(ObjectCalls.ptrcallNoArgsRetObject(generateTriangleMeshBind, segment))
    }

    companion object {
        const val PRIMITIVE_POINTS = 0L
        const val PRIMITIVE_LINES = 1L
        const val PRIMITIVE_LINE_STRIP = 2L
        const val PRIMITIVE_TRIANGLES = 3L
        const val PRIMITIVE_TRIANGLE_STRIP = 4L
        const val ARRAY_VERTEX = 0L
        const val ARRAY_NORMAL = 1L
        const val ARRAY_TANGENT = 2L
        const val ARRAY_COLOR = 3L
        const val ARRAY_TEX_UV = 4L
        const val ARRAY_TEX_UV2 = 5L
        const val ARRAY_CUSTOM0 = 6L
        const val ARRAY_CUSTOM1 = 7L
        const val ARRAY_CUSTOM2 = 8L
        const val ARRAY_CUSTOM3 = 9L
        const val ARRAY_BONES = 10L
        const val ARRAY_WEIGHTS = 11L
        const val ARRAY_INDEX = 12L
        const val ARRAY_MAX = 13L
        const val ARRAY_CUSTOM_RGBA8_UNORM = 0L
        const val ARRAY_CUSTOM_RGBA8_SNORM = 1L
        const val ARRAY_CUSTOM_RG_HALF = 2L
        const val ARRAY_CUSTOM_RGBA_HALF = 3L
        const val ARRAY_CUSTOM_R_FLOAT = 4L
        const val ARRAY_CUSTOM_RG_FLOAT = 5L
        const val ARRAY_CUSTOM_RGB_FLOAT = 6L
        const val ARRAY_CUSTOM_RGBA_FLOAT = 7L
        const val ARRAY_CUSTOM_MAX = 8L
        const val ARRAY_FORMAT_VERTEX = 1L
        const val ARRAY_FORMAT_NORMAL = 2L
        const val ARRAY_FORMAT_TANGENT = 4L
        const val ARRAY_FORMAT_COLOR = 8L
        const val ARRAY_FORMAT_TEX_UV = 16L
        const val ARRAY_FORMAT_TEX_UV2 = 32L
        const val ARRAY_FORMAT_CUSTOM0 = 64L
        const val ARRAY_FORMAT_CUSTOM1 = 128L
        const val ARRAY_FORMAT_CUSTOM2 = 256L
        const val ARRAY_FORMAT_CUSTOM3 = 512L
        const val ARRAY_FORMAT_BONES = 1024L
        const val ARRAY_FORMAT_WEIGHTS = 2048L
        const val ARRAY_FORMAT_INDEX = 4096L
        const val ARRAY_FORMAT_BLEND_SHAPE_MASK = 7L
        const val ARRAY_FORMAT_CUSTOM_BASE = 13L
        const val ARRAY_FORMAT_CUSTOM_BITS = 3L
        const val ARRAY_FORMAT_CUSTOM0_SHIFT = 13L
        const val ARRAY_FORMAT_CUSTOM1_SHIFT = 16L
        const val ARRAY_FORMAT_CUSTOM2_SHIFT = 19L
        const val ARRAY_FORMAT_CUSTOM3_SHIFT = 22L
        const val ARRAY_FORMAT_CUSTOM_MASK = 7L
        const val ARRAY_COMPRESS_FLAGS_BASE = 25L
        const val ARRAY_FLAG_USE_2D_VERTICES = 33554432L
        const val ARRAY_FLAG_USE_DYNAMIC_UPDATE = 67108864L
        const val ARRAY_FLAG_USE_8_BONE_WEIGHTS = 134217728L
        const val ARRAY_FLAG_USES_EMPTY_VERTEX_ARRAY = 268435456L
        const val ARRAY_FLAG_COMPRESS_ATTRIBUTES = 536870912L
        const val BLEND_SHAPE_MODE_NORMALIZED = 0L
        const val BLEND_SHAPE_MODE_RELATIVE = 1L

        @JvmStatic
        fun fromObject(value: GodotObject): Mesh? =
            if (value.isClass("Mesh")) Mesh(value.handle) else null

        @JvmStatic
        fun fromHandle(handle: GodotHandle): Mesh? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): Mesh? =
            if (handle.address() == 0L) null else Mesh(GodotHandle(handle))

        private const val SET_LIGHTMAP_SIZE_HINT_HASH = 1130785943L
        private const val GET_LIGHTMAP_SIZE_HINT_HASH = 3690982128L
        private const val GET_AABB_HASH = 1068685055L
        private const val GET_FACES_HASH = 497664490L
        private const val GET_SURFACE_COUNT_HASH = 3905245786L
        private const val SURFACE_GET_ARRAYS_HASH = 663333327L
        private const val SURFACE_GET_BLEND_SHAPE_ARRAYS_HASH = 663333327L
        private const val SURFACE_SET_MATERIAL_HASH = 3671737478L
        private const val SURFACE_GET_MATERIAL_HASH = 2897466400L
        private const val CREATE_PLACEHOLDER_HASH = 121922552L
        private const val CREATE_TRIMESH_SHAPE_HASH = 4160111210L
        private const val CREATE_CONVEX_SHAPE_HASH = 2529984628L
        private const val CREATE_OUTLINE_HASH = 1208642001L
        private const val GENERATE_TRIANGLE_MESH_HASH = 3476533166L

        private val setLightmapSizeHintBind by lazy {
            ObjectCalls.getMethodBind("Mesh", "set_lightmap_size_hint", SET_LIGHTMAP_SIZE_HINT_HASH)
        }

        private val getLightmapSizeHintBind by lazy {
            ObjectCalls.getMethodBind("Mesh", "get_lightmap_size_hint", GET_LIGHTMAP_SIZE_HINT_HASH)
        }

        private val getAabbBind by lazy {
            ObjectCalls.getMethodBind("Mesh", "get_aabb", GET_AABB_HASH)
        }

        private val getFacesBind by lazy {
            ObjectCalls.getMethodBind("Mesh", "get_faces", GET_FACES_HASH)
        }

        private val getSurfaceCountBind by lazy {
            ObjectCalls.getMethodBind("Mesh", "get_surface_count", GET_SURFACE_COUNT_HASH)
        }

        private val surfaceGetArraysBind by lazy {
            ObjectCalls.getMethodBind("Mesh", "surface_get_arrays", SURFACE_GET_ARRAYS_HASH)
        }

        private val surfaceGetBlendShapeArraysBind by lazy {
            ObjectCalls.getMethodBind("Mesh", "surface_get_blend_shape_arrays", SURFACE_GET_BLEND_SHAPE_ARRAYS_HASH)
        }

        private val surfaceSetMaterialBind by lazy {
            ObjectCalls.getMethodBind("Mesh", "surface_set_material", SURFACE_SET_MATERIAL_HASH)
        }

        private val surfaceGetMaterialBind by lazy {
            ObjectCalls.getMethodBind("Mesh", "surface_get_material", SURFACE_GET_MATERIAL_HASH)
        }

        private val createPlaceholderBind by lazy {
            ObjectCalls.getMethodBind("Mesh", "create_placeholder", CREATE_PLACEHOLDER_HASH)
        }

        private val createTrimeshShapeBind by lazy {
            ObjectCalls.getMethodBind("Mesh", "create_trimesh_shape", CREATE_TRIMESH_SHAPE_HASH)
        }

        private val createConvexShapeBind by lazy {
            ObjectCalls.getMethodBind("Mesh", "create_convex_shape", CREATE_CONVEX_SHAPE_HASH)
        }

        private val createOutlineBind by lazy {
            ObjectCalls.getMethodBind("Mesh", "create_outline", CREATE_OUTLINE_HASH)
        }

        private val generateTriangleMeshBind by lazy {
            ObjectCalls.getMethodBind("Mesh", "generate_triangle_mesh", GENERATE_TRIANGLE_MESH_HASH)
        }
    }
}
