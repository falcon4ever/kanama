package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: GLTFCamera
 */
class GLTFCamera(handle: MemorySegment) : Resource(handle) {
    var perspective: Boolean
        @JvmName("perspectiveProperty")
        get() = getPerspective()
        @JvmName("setPerspectiveProperty")
        set(value) = setPerspective(value)

    var fov: Double
        @JvmName("fovProperty")
        get() = getFov()
        @JvmName("setFovProperty")
        set(value) = setFov(value)

    var sizeMag: Double
        @JvmName("sizeMagProperty")
        get() = getSizeMag()
        @JvmName("setSizeMagProperty")
        set(value) = setSizeMag(value)

    var depthFar: Double
        @JvmName("depthFarProperty")
        get() = getDepthFar()
        @JvmName("setDepthFarProperty")
        set(value) = setDepthFar(value)

    var depthNear: Double
        @JvmName("depthNearProperty")
        get() = getDepthNear()
        @JvmName("setDepthNearProperty")
        set(value) = setDepthNear(value)

    fun toNode(): Camera3D? {
        return Camera3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(toNodeBind, handle))
    }

    fun toDictionary(): Map<String, Any?> {
        return ObjectCalls.ptrcallNoArgsRetDictionary(toDictionaryBind, handle)
    }

    fun getPerspective(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getPerspectiveBind, handle)
    }

    fun setPerspective(perspective: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPerspectiveBind, handle, perspective)
    }

    fun getFov(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFovBind, handle)
    }

    fun setFov(fov: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFovBind, handle, fov)
    }

    fun getSizeMag(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSizeMagBind, handle)
    }

    fun setSizeMag(sizeMag: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSizeMagBind, handle, sizeMag)
    }

    fun getDepthFar(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDepthFarBind, handle)
    }

    fun setDepthFar(zdepthFar: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDepthFarBind, handle, zdepthFar)
    }

    fun getDepthNear(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDepthNearBind, handle)
    }

    fun setDepthNear(zdepthNear: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDepthNearBind, handle, zdepthNear)
    }

    companion object {
        fun fromNode(cameraNode: Camera3D): GLTFCamera? {
            return GLTFCamera.wrap(ObjectCalls.ptrcallWithObjectArgRetObject(fromNodeBind, MemorySegment.NULL, cameraNode.handle))
        }

        fun fromDictionary(dictionary: Map<String, Any?>): GLTFCamera? {
            return GLTFCamera.wrap(ObjectCalls.ptrcallWithDictionaryArgRetObject(fromDictionaryBind, MemorySegment.NULL, dictionary))
        }

        @JvmStatic
        fun fromHandle(handle: MemorySegment): GLTFCamera? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GLTFCamera? =
            if (handle.address() == 0L) null else GLTFCamera(handle)

        private const val FROM_NODE_HASH = 237784L
        private val fromNodeBind by lazy {
            ObjectCalls.getMethodBind("GLTFCamera", "from_node", FROM_NODE_HASH)
        }

        private const val TO_NODE_HASH = 2285090890L
        private val toNodeBind by lazy {
            ObjectCalls.getMethodBind("GLTFCamera", "to_node", TO_NODE_HASH)
        }

        private const val FROM_DICTIONARY_HASH = 2495512509L
        private val fromDictionaryBind by lazy {
            ObjectCalls.getMethodBind("GLTFCamera", "from_dictionary", FROM_DICTIONARY_HASH)
        }

        private const val TO_DICTIONARY_HASH = 3102165223L
        private val toDictionaryBind by lazy {
            ObjectCalls.getMethodBind("GLTFCamera", "to_dictionary", TO_DICTIONARY_HASH)
        }

        private const val GET_PERSPECTIVE_HASH = 36873697L
        private val getPerspectiveBind by lazy {
            ObjectCalls.getMethodBind("GLTFCamera", "get_perspective", GET_PERSPECTIVE_HASH)
        }

        private const val SET_PERSPECTIVE_HASH = 2586408642L
        private val setPerspectiveBind by lazy {
            ObjectCalls.getMethodBind("GLTFCamera", "set_perspective", SET_PERSPECTIVE_HASH)
        }

        private const val GET_FOV_HASH = 1740695150L
        private val getFovBind by lazy {
            ObjectCalls.getMethodBind("GLTFCamera", "get_fov", GET_FOV_HASH)
        }

        private const val SET_FOV_HASH = 373806689L
        private val setFovBind by lazy {
            ObjectCalls.getMethodBind("GLTFCamera", "set_fov", SET_FOV_HASH)
        }

        private const val GET_SIZE_MAG_HASH = 1740695150L
        private val getSizeMagBind by lazy {
            ObjectCalls.getMethodBind("GLTFCamera", "get_size_mag", GET_SIZE_MAG_HASH)
        }

        private const val SET_SIZE_MAG_HASH = 373806689L
        private val setSizeMagBind by lazy {
            ObjectCalls.getMethodBind("GLTFCamera", "set_size_mag", SET_SIZE_MAG_HASH)
        }

        private const val GET_DEPTH_FAR_HASH = 1740695150L
        private val getDepthFarBind by lazy {
            ObjectCalls.getMethodBind("GLTFCamera", "get_depth_far", GET_DEPTH_FAR_HASH)
        }

        private const val SET_DEPTH_FAR_HASH = 373806689L
        private val setDepthFarBind by lazy {
            ObjectCalls.getMethodBind("GLTFCamera", "set_depth_far", SET_DEPTH_FAR_HASH)
        }

        private const val GET_DEPTH_NEAR_HASH = 1740695150L
        private val getDepthNearBind by lazy {
            ObjectCalls.getMethodBind("GLTFCamera", "get_depth_near", GET_DEPTH_NEAR_HASH)
        }

        private const val SET_DEPTH_NEAR_HASH = 373806689L
        private val setDepthNearBind by lazy {
            ObjectCalls.getMethodBind("GLTFCamera", "set_depth_near", SET_DEPTH_NEAR_HASH)
        }
    }
}
