package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector2i

/**
 * Generated from Godot docs: ImporterMesh
 */
class ImporterMesh(handle: MemorySegment) : Resource(handle) {
    fun addBlendShape(name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(addBlendShapeBind, handle, name)
    }

    fun getBlendShapeCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getBlendShapeCountBind, handle)
    }

    fun setBlendShapeMode(mode: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setBlendShapeModeBind, handle, mode)
    }

    fun getBlendShapeMode(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getBlendShapeModeBind, handle)
    }

    fun getSurfaceCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getSurfaceCountBind, handle)
    }

    fun getSurfacePrimitiveType(surfaceIdx: Int): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetLong(getSurfacePrimitiveTypeBind, handle, surfaceIdx)
    }

    fun getSurfaceLodCount(surfaceIdx: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(getSurfaceLodCountBind, handle, surfaceIdx)
    }

    fun getSurfaceLodSize(surfaceIdx: Int, lodIdx: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetDouble(getSurfaceLodSizeBind, handle, surfaceIdx, lodIdx)
    }

    fun getSurfaceMaterial(surfaceIdx: Int): Material? {
        checkOpen()
        return Material.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getSurfaceMaterialBind, handle, surfaceIdx))
    }

    fun getSurfaceFormat(surfaceIdx: Int): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetLong(getSurfaceFormatBind, handle, surfaceIdx)
    }

    fun setSurfaceName(surfaceIdx: Int, name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndStringArg(setSurfaceNameBind, handle, surfaceIdx, name)
    }

    fun setSurfaceMaterial(surfaceIdx: Int, material: Material?) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndObjectArg(setSurfaceMaterialBind, handle, surfaceIdx, material?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getMesh(baseMesh: ArrayMesh?): ArrayMesh? {
        checkOpen()
        return ArrayMesh.wrap(ObjectCalls.ptrcallWithObjectArgRetObject(getMeshBind, handle, baseMesh?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun clear() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    fun setLightmapSizeHint(size: Vector2i) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2iArg(setLightmapSizeHintBind, handle, size)
    }

    fun getLightmapSizeHint(): Vector2i {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2i(getLightmapSizeHintBind, handle)
    }

    companion object {
        fun fromMesh(mesh: Mesh?): ImporterMesh? {
            return ImporterMesh.wrap(ObjectCalls.ptrcallWithObjectArgRetObject(fromMeshBind, MemorySegment.NULL, mesh?.requireOpenHandle() ?: MemorySegment.NULL))
        }

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

        private const val SET_BLEND_SHAPE_MODE_HASH = 227983991L
        private val setBlendShapeModeBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "set_blend_shape_mode", SET_BLEND_SHAPE_MODE_HASH)
        }

        private const val GET_BLEND_SHAPE_MODE_HASH = 836485024L
        private val getBlendShapeModeBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "get_blend_shape_mode", GET_BLEND_SHAPE_MODE_HASH)
        }

        private const val GET_SURFACE_COUNT_HASH = 3905245786L
        private val getSurfaceCountBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "get_surface_count", GET_SURFACE_COUNT_HASH)
        }

        private const val GET_SURFACE_PRIMITIVE_TYPE_HASH = 3552571330L
        private val getSurfacePrimitiveTypeBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "get_surface_primitive_type", GET_SURFACE_PRIMITIVE_TYPE_HASH)
        }

        private const val GET_SURFACE_LOD_COUNT_HASH = 923996154L
        private val getSurfaceLodCountBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "get_surface_lod_count", GET_SURFACE_LOD_COUNT_HASH)
        }

        private const val GET_SURFACE_LOD_SIZE_HASH = 3085491603L
        private val getSurfaceLodSizeBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "get_surface_lod_size", GET_SURFACE_LOD_SIZE_HASH)
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
