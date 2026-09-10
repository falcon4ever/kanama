package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Transform3D

/**
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

    fun addBlendShape(name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringNameArg(addBlendShapeBind, handle, name)
    }

    fun getBlendShapeCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getBlendShapeCountBind, handle)
    }

    fun getBlendShapeName(index: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetStringName(getBlendShapeNameBind, handle, index)
    }

    fun setBlendShapeName(index: Int, name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndStringNameArg(setBlendShapeNameBind, handle, index, name)
    }

    fun clearBlendShapes() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(clearBlendShapesBind, handle)
    }

    fun setBlendShapeMode(mode: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setBlendShapeModeBind, handle, mode)
    }

    fun getBlendShapeMode(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getBlendShapeModeBind, handle)
    }

    fun clearSurfaces() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(clearSurfacesBind, handle)
    }

    fun surfaceRemove(surfIdx: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(surfaceRemoveBind, handle, surfIdx)
    }

    fun surfaceUpdateVertexRegion(surfIdx: Int, offset: Int, data: ByteArray) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntAndByteArrayArg(surfaceUpdateVertexRegionBind, handle, surfIdx, offset, data)
    }

    fun surfaceUpdateAttributeRegion(surfIdx: Int, offset: Int, data: ByteArray) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntAndByteArrayArg(surfaceUpdateAttributeRegionBind, handle, surfIdx, offset, data)
    }

    fun surfaceUpdateSkinRegion(surfIdx: Int, offset: Int, data: ByteArray) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntAndByteArrayArg(surfaceUpdateSkinRegionBind, handle, surfIdx, offset, data)
    }

    fun surfaceGetArrayLen(surfIdx: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(surfaceGetArrayLenBind, handle, surfIdx)
    }

    fun surfaceGetArrayIndexLen(surfIdx: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(surfaceGetArrayIndexLenBind, handle, surfIdx)
    }

    fun surfaceGetFormat(surfIdx: Int): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetLong(surfaceGetFormatBind, handle, surfIdx)
    }

    fun surfaceGetPrimitiveType(surfIdx: Int): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetLong(surfaceGetPrimitiveTypeBind, handle, surfIdx)
    }

    fun surfaceFindByName(name: String): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetInt(surfaceFindByNameBind, handle, name)
    }

    fun surfaceSetName(surfIdx: Int, name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndStringArg(surfaceSetNameBind, handle, surfIdx, name)
    }

    fun surfaceGetName(surfIdx: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetString(surfaceGetNameBind, handle, surfIdx)
    }

    fun regenNormalMaps() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(regenNormalMapsBind, handle)
    }

    fun lightmapUnwrap(transform: Transform3D, texelSize: Double): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithTransform3DAndDoubleArgRetLong(lightmapUnwrapBind, handle, transform, texelSize)
    }

    fun setCustomAabb(aabb: AABB) {
        checkOpen()
        ObjectCalls.ptrcallWithAABBArg(setCustomAabbBind, handle, aabb)
    }

    fun getCustomAabb(): AABB {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetAABB(getCustomAabbBind, handle)
    }

    fun setShadowMesh(mesh: ArrayMesh?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setShadowMeshBind, handle, listOf(mesh?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getShadowMesh(): ArrayMesh? {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetObject(getShadowMeshBind, handle)
        if (ret.address() == handle.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return ArrayMesh.wrap(ret)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ArrayMesh? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ArrayMesh? =
            if (handle.address() == 0L) null else ArrayMesh(handle)

        // Downcast a Resource/Mesh to ArrayMesh (null if not), mirroring the desktop helper.
        fun fromResource(value: Resource): ArrayMesh? =
            if (value.isClass("ArrayMesh")) ArrayMesh(value.handle) else null

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
