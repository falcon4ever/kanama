package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.AABB

/**
 * Base class for all primitive meshes. Handles applying a `Material` to a primitive mesh.
 *
 * Generated from Godot docs: PrimitiveMesh
 */
open class PrimitiveMesh(handle: MemorySegment) : Mesh(handle) {
    var material: Material?
        @JvmName("materialProperty")
        get() = getMaterial()
        @JvmName("setMaterialProperty")
        set(value) = setMaterial(value)

    var customAabb: AABB
        @JvmName("customAabbProperty")
        get() = getCustomAabb()
        @JvmName("setCustomAabbProperty")
        set(value) = setCustomAabb(value)

    var flipFaces: Boolean
        @JvmName("flipFacesProperty")
        get() = getFlipFaces()
        @JvmName("setFlipFacesProperty")
        set(value) = setFlipFaces(value)

    var addUv2: Boolean
        @JvmName("addUv2Property")
        get() = getAddUv2()
        @JvmName("setAddUv2Property")
        set(value) = setAddUv2(value)

    var uv2Padding: Double
        @JvmName("uv2PaddingProperty")
        get() = getUv2Padding()
        @JvmName("setUv2PaddingProperty")
        set(value) = setUv2Padding(value)

    fun setMaterial(material: Material?) {
        ObjectCalls.ptrcallWithObjectArgs(setMaterialBind, handle, listOf(material?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getMaterial(): Material? {
        return Material.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMaterialBind, handle))
    }

    fun getMeshArrays(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(getMeshArraysBind, handle)
    }

    fun setCustomAabb(aabb: AABB) {
        ObjectCalls.ptrcallWithAABBArg(setCustomAabbBind, handle, aabb)
    }

    fun getCustomAabb(): AABB {
        return ObjectCalls.ptrcallNoArgsRetAABB(getCustomAabbBind, handle)
    }

    fun setFlipFaces(flipFaces: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFlipFacesBind, handle, flipFaces)
    }

    fun getFlipFaces(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getFlipFacesBind, handle)
    }

    fun setAddUv2(addUv2: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAddUv2Bind, handle, addUv2)
    }

    fun getAddUv2(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getAddUv2Bind, handle)
    }

    fun setUv2Padding(uv2Padding: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setUv2PaddingBind, handle, uv2Padding)
    }

    fun getUv2Padding(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getUv2PaddingBind, handle)
    }

    fun requestUpdate() {
        ObjectCalls.ptrcallNoArgs(requestUpdateBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PrimitiveMesh? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PrimitiveMesh? =
            if (handle.address() == 0L) null else PrimitiveMesh(handle)

        private const val SET_MATERIAL_HASH = 2757459619L
        private val setMaterialBind by lazy {
            ObjectCalls.getMethodBind("PrimitiveMesh", "set_material", SET_MATERIAL_HASH)
        }

        private const val GET_MATERIAL_HASH = 5934680L
        private val getMaterialBind by lazy {
            ObjectCalls.getMethodBind("PrimitiveMesh", "get_material", GET_MATERIAL_HASH)
        }

        private const val GET_MESH_ARRAYS_HASH = 3995934104L
        private val getMeshArraysBind by lazy {
            ObjectCalls.getMethodBind("PrimitiveMesh", "get_mesh_arrays", GET_MESH_ARRAYS_HASH)
        }

        private const val SET_CUSTOM_AABB_HASH = 259215842L
        private val setCustomAabbBind by lazy {
            ObjectCalls.getMethodBind("PrimitiveMesh", "set_custom_aabb", SET_CUSTOM_AABB_HASH)
        }

        private const val GET_CUSTOM_AABB_HASH = 1068685055L
        private val getCustomAabbBind by lazy {
            ObjectCalls.getMethodBind("PrimitiveMesh", "get_custom_aabb", GET_CUSTOM_AABB_HASH)
        }

        private const val SET_FLIP_FACES_HASH = 2586408642L
        private val setFlipFacesBind by lazy {
            ObjectCalls.getMethodBind("PrimitiveMesh", "set_flip_faces", SET_FLIP_FACES_HASH)
        }

        private const val GET_FLIP_FACES_HASH = 36873697L
        private val getFlipFacesBind by lazy {
            ObjectCalls.getMethodBind("PrimitiveMesh", "get_flip_faces", GET_FLIP_FACES_HASH)
        }

        private const val SET_ADD_UV2_HASH = 2586408642L
        private val setAddUv2Bind by lazy {
            ObjectCalls.getMethodBind("PrimitiveMesh", "set_add_uv2", SET_ADD_UV2_HASH)
        }

        private const val GET_ADD_UV2_HASH = 36873697L
        private val getAddUv2Bind by lazy {
            ObjectCalls.getMethodBind("PrimitiveMesh", "get_add_uv2", GET_ADD_UV2_HASH)
        }

        private const val SET_UV2_PADDING_HASH = 373806689L
        private val setUv2PaddingBind by lazy {
            ObjectCalls.getMethodBind("PrimitiveMesh", "set_uv2_padding", SET_UV2_PADDING_HASH)
        }

        private const val GET_UV2_PADDING_HASH = 1740695150L
        private val getUv2PaddingBind by lazy {
            ObjectCalls.getMethodBind("PrimitiveMesh", "get_uv2_padding", GET_UV2_PADDING_HASH)
        }

        private const val REQUEST_UPDATE_HASH = 3218959716L
        private val requestUpdateBind by lazy {
            ObjectCalls.getMethodBind("PrimitiveMesh", "request_update", REQUEST_UPDATE_HASH)
        }
    }
}
