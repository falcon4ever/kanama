package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: GLTFCamera
 */
class GLTFCamera(handle: GodotHandle) : Resource(handle) {
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
        checkOpen()
        return Camera3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.toNodeBind, segment))
    }

    fun toDictionary(): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDictionary(Binds.toDictionaryBind, segment)
    }

    fun getPerspective(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getPerspectiveBind, segment)
    }

    fun setPerspective(perspective: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setPerspectiveBind, segment, perspective)
    }

    fun getFov(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getFovBind, segment)
    }

    fun setFov(fov: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setFovBind, segment, fov)
    }

    fun getSizeMag(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getSizeMagBind, segment)
    }

    fun setSizeMag(sizeMag: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setSizeMagBind, segment, sizeMag)
    }

    fun getDepthFar(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDepthFarBind, segment)
    }

    fun setDepthFar(zdepthFar: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDepthFarBind, segment, zdepthFar)
    }

    fun getDepthNear(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDepthNearBind, segment)
    }

    fun setDepthNear(zdepthNear: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDepthNearBind, segment, zdepthNear)
    }

    companion object {
        fun fromNode(cameraNode: Camera3D): GLTFCamera? {
            return GLTFCamera.wrapOwned(ObjectCalls.ptrcallWithObjectArgRetObject(Binds.fromNodeBind, NULL_SEGMENT, cameraNode.segment))
        }

        fun fromDictionary(dictionary: Map<String, Any?>): GLTFCamera? {
            return GLTFCamera.wrapOwned(ObjectCalls.ptrcallWithDictionaryArgRetObject(Binds.fromDictionaryBind, NULL_SEGMENT, dictionary))
        }

        @JvmStatic
        fun fromHandle(handle: GodotHandle): GLTFCamera? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): GLTFCamera? =
            if (handle.address() == 0L) null else RefCounted.owned(GLTFCamera(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): GLTFCamera? =
            if (handle.address() == 0L) null else GLTFCamera(GodotHandle(handle))
    }

    private object Binds {
        private const val FROM_NODE_HASH = 237784L
        @JvmField
        val fromNodeBind =
            ObjectCalls.getMethodBind("GLTFCamera", "from_node", FROM_NODE_HASH)

        private const val TO_NODE_HASH = 2285090890L
        @JvmField
        val toNodeBind =
            ObjectCalls.getMethodBind("GLTFCamera", "to_node", TO_NODE_HASH)

        private const val FROM_DICTIONARY_HASH = 2495512509L
        @JvmField
        val fromDictionaryBind =
            ObjectCalls.getMethodBind("GLTFCamera", "from_dictionary", FROM_DICTIONARY_HASH)

        private const val TO_DICTIONARY_HASH = 3102165223L
        @JvmField
        val toDictionaryBind =
            ObjectCalls.getMethodBind("GLTFCamera", "to_dictionary", TO_DICTIONARY_HASH)

        private const val GET_PERSPECTIVE_HASH = 36873697L
        @JvmField
        val getPerspectiveBind =
            ObjectCalls.getMethodBind("GLTFCamera", "get_perspective", GET_PERSPECTIVE_HASH)

        private const val SET_PERSPECTIVE_HASH = 2586408642L
        @JvmField
        val setPerspectiveBind =
            ObjectCalls.getMethodBind("GLTFCamera", "set_perspective", SET_PERSPECTIVE_HASH)

        private const val GET_FOV_HASH = 1740695150L
        @JvmField
        val getFovBind =
            ObjectCalls.getMethodBind("GLTFCamera", "get_fov", GET_FOV_HASH)

        private const val SET_FOV_HASH = 373806689L
        @JvmField
        val setFovBind =
            ObjectCalls.getMethodBind("GLTFCamera", "set_fov", SET_FOV_HASH)

        private const val GET_SIZE_MAG_HASH = 1740695150L
        @JvmField
        val getSizeMagBind =
            ObjectCalls.getMethodBind("GLTFCamera", "get_size_mag", GET_SIZE_MAG_HASH)

        private const val SET_SIZE_MAG_HASH = 373806689L
        @JvmField
        val setSizeMagBind =
            ObjectCalls.getMethodBind("GLTFCamera", "set_size_mag", SET_SIZE_MAG_HASH)

        private const val GET_DEPTH_FAR_HASH = 1740695150L
        @JvmField
        val getDepthFarBind =
            ObjectCalls.getMethodBind("GLTFCamera", "get_depth_far", GET_DEPTH_FAR_HASH)

        private const val SET_DEPTH_FAR_HASH = 373806689L
        @JvmField
        val setDepthFarBind =
            ObjectCalls.getMethodBind("GLTFCamera", "set_depth_far", SET_DEPTH_FAR_HASH)

        private const val GET_DEPTH_NEAR_HASH = 1740695150L
        @JvmField
        val getDepthNearBind =
            ObjectCalls.getMethodBind("GLTFCamera", "get_depth_near", GET_DEPTH_NEAR_HASH)

        private const val SET_DEPTH_NEAR_HASH = 373806689L
        @JvmField
        val setDepthNearBind =
            ObjectCalls.getMethodBind("GLTFCamera", "set_depth_near", SET_DEPTH_NEAR_HASH)
    }
}
