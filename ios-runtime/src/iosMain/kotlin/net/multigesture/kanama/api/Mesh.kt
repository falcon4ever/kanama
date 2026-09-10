package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Vector2i
import net.multigesture.kanama.types.Vector3

/**
 * Generated from Godot docs: Mesh
 */
open class Mesh(handle: MemorySegment) : Resource(handle) {
    var lightmapSizeHint: Vector2i
        @JvmName("lightmapSizeHintProperty")
        get() = getLightmapSizeHint()
        @JvmName("setLightmapSizeHintProperty")
        set(value) = setLightmapSizeHint(value)

    fun setLightmapSizeHint(size: Vector2i) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2iArg(setLightmapSizeHintBind, handle, size)
    }

    fun getLightmapSizeHint(): Vector2i {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2i(getLightmapSizeHintBind, handle)
    }

    fun getAabb(): AABB {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetAABB(getAabbBind, handle)
    }

    fun getFaces(): List<Vector3> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedVector3List(getFacesBind, handle)
    }

    fun getSurfaceCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getSurfaceCountBind, handle)
    }

    fun surfaceSetMaterial(surfIdx: Int, material: Material?) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndObjectArg(surfaceSetMaterialBind, handle, surfIdx, material?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun surfaceGetMaterial(surfIdx: Int): Material? {
        checkOpen()
        return Material.wrap(ObjectCalls.ptrcallWithIntArgRetObject(surfaceGetMaterialBind, handle, surfIdx))
    }

    fun createPlaceholder(): Resource? {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetObject(createPlaceholderBind, handle)
        if (ret.address() == handle.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return Resource.wrap(ret)
    }

    fun createTrimeshShape(): ConcavePolygonShape3D? {
        checkOpen()
        return ConcavePolygonShape3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(createTrimeshShapeBind, handle))
    }

    fun createConvexShape(clean: Boolean = true, simplify: Boolean = false): ConvexPolygonShape3D? {
        checkOpen()
        return ConvexPolygonShape3D.wrap(ObjectCalls.ptrcallWithTwoBoolArgsRetObject(createConvexShapeBind, handle, clean, simplify))
    }

    fun createOutline(margin: Double): Mesh? {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithDoubleArgRetObject(createOutlineBind, handle, margin)
        if (ret.address() == handle.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return Mesh.wrap(ret)
    }

    fun generateTriangleMesh(): TriangleMesh? {
        checkOpen()
        return TriangleMesh.wrap(ObjectCalls.ptrcallNoArgsRetObject(generateTriangleMeshBind, handle))
    }

    companion object {
        const val PRIMITIVE_POINTS: Long = 0L
        const val PRIMITIVE_LINES: Long = 1L
        const val PRIMITIVE_LINE_STRIP: Long = 2L
        const val PRIMITIVE_TRIANGLES: Long = 3L
        const val PRIMITIVE_TRIANGLE_STRIP: Long = 4L
        const val ARRAY_VERTEX: Long = 0L
        const val ARRAY_NORMAL: Long = 1L
        const val ARRAY_TANGENT: Long = 2L
        const val ARRAY_COLOR: Long = 3L
        const val ARRAY_TEX_UV: Long = 4L
        const val ARRAY_TEX_UV2: Long = 5L
        const val ARRAY_CUSTOM0: Long = 6L
        const val ARRAY_CUSTOM1: Long = 7L
        const val ARRAY_CUSTOM2: Long = 8L
        const val ARRAY_CUSTOM3: Long = 9L
        const val ARRAY_BONES: Long = 10L
        const val ARRAY_WEIGHTS: Long = 11L
        const val ARRAY_INDEX: Long = 12L
        const val ARRAY_MAX: Long = 13L
        const val ARRAY_CUSTOM_RGBA8_UNORM: Long = 0L
        const val ARRAY_CUSTOM_RGBA8_SNORM: Long = 1L
        const val ARRAY_CUSTOM_RG_HALF: Long = 2L
        const val ARRAY_CUSTOM_RGBA_HALF: Long = 3L
        const val ARRAY_CUSTOM_R_FLOAT: Long = 4L
        const val ARRAY_CUSTOM_RG_FLOAT: Long = 5L
        const val ARRAY_CUSTOM_RGB_FLOAT: Long = 6L
        const val ARRAY_CUSTOM_RGBA_FLOAT: Long = 7L
        const val ARRAY_CUSTOM_MAX: Long = 8L
        const val ARRAY_FORMAT_VERTEX: Long = 1L
        const val ARRAY_FORMAT_NORMAL: Long = 2L
        const val ARRAY_FORMAT_TANGENT: Long = 4L
        const val ARRAY_FORMAT_COLOR: Long = 8L
        const val ARRAY_FORMAT_TEX_UV: Long = 16L
        const val ARRAY_FORMAT_TEX_UV2: Long = 32L
        const val ARRAY_FORMAT_CUSTOM0: Long = 64L
        const val ARRAY_FORMAT_CUSTOM1: Long = 128L
        const val ARRAY_FORMAT_CUSTOM2: Long = 256L
        const val ARRAY_FORMAT_CUSTOM3: Long = 512L
        const val ARRAY_FORMAT_BONES: Long = 1024L
        const val ARRAY_FORMAT_WEIGHTS: Long = 2048L
        const val ARRAY_FORMAT_INDEX: Long = 4096L
        const val ARRAY_FORMAT_BLEND_SHAPE_MASK: Long = 7L
        const val ARRAY_FORMAT_CUSTOM_BASE: Long = 13L
        const val ARRAY_FORMAT_CUSTOM_BITS: Long = 3L
        const val ARRAY_FORMAT_CUSTOM0_SHIFT: Long = 13L
        const val ARRAY_FORMAT_CUSTOM1_SHIFT: Long = 16L
        const val ARRAY_FORMAT_CUSTOM2_SHIFT: Long = 19L
        const val ARRAY_FORMAT_CUSTOM3_SHIFT: Long = 22L
        const val ARRAY_FORMAT_CUSTOM_MASK: Long = 7L
        const val ARRAY_COMPRESS_FLAGS_BASE: Long = 25L
        const val ARRAY_FLAG_USE_2D_VERTICES: Long = 33554432L
        const val ARRAY_FLAG_USE_DYNAMIC_UPDATE: Long = 67108864L
        const val ARRAY_FLAG_USE_8_BONE_WEIGHTS: Long = 134217728L
        const val ARRAY_FLAG_USES_EMPTY_VERTEX_ARRAY: Long = 268435456L
        const val ARRAY_FLAG_COMPRESS_ATTRIBUTES: Long = 536870912L
        const val BLEND_SHAPE_MODE_NORMALIZED: Long = 0L
        const val BLEND_SHAPE_MODE_RELATIVE: Long = 1L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): Mesh? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Mesh? =
            if (handle.address() == 0L) null else Mesh(handle)

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
