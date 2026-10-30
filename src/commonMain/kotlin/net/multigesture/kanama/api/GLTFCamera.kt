package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Represents a glTF camera.
 *
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

    /**
     * Converts this GLTFCamera instance into a Godot `Camera3D` node.
     *
     * Generated from Godot docs: GLTFCamera.to_node
     */
    fun toNode(): Camera3D? {
        checkOpen()
        val ret = Camera3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.toNodeBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Serializes this GLTFCamera instance into a `Dictionary`.
     *
     * Generated from Godot docs: GLTFCamera.to_dictionary
     */
    fun toDictionary(): Map<String, Any?> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDictionary(Binds.toDictionaryBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * If `true`, the camera is in perspective mode. Otherwise, the camera is in
     * orthographic/orthogonal mode. This maps to glTF's camera `type` property. See
     * `Camera3D.projection` and the glTF spec for more information.
     *
     * Generated from Godot docs: GLTFCamera.get_perspective
     */
    fun getPerspective(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.getPerspectiveBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * If `true`, the camera is in perspective mode. Otherwise, the camera is in
     * orthographic/orthogonal mode. This maps to glTF's camera `type` property. See
     * `Camera3D.projection` and the glTF spec for more information.
     *
     * Generated from Godot docs: GLTFCamera.set_perspective
     */
    fun setPerspective(perspective: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setPerspectiveBind, segment, perspective)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The FOV of the camera. This class and glTF define the camera FOV in radians, while Godot uses
     * degrees. This maps to glTF's `yfov` property. This value is only used for perspective cameras,
     * when `perspective` is `true`.
     *
     * Generated from Godot docs: GLTFCamera.get_fov
     */
    fun getFov(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getFovBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The FOV of the camera. This class and glTF define the camera FOV in radians, while Godot uses
     * degrees. This maps to glTF's `yfov` property. This value is only used for perspective cameras,
     * when `perspective` is `true`.
     *
     * Generated from Godot docs: GLTFCamera.set_fov
     */
    fun setFov(fov: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setFovBind, segment, fov)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The size of the camera. This class and glTF define the camera size magnitude as a radius in
     * meters, while Godot defines it as a diameter in meters. This maps to glTF's `ymag` property.
     * This value is only used for orthographic/orthogonal cameras, when `perspective` is `false`.
     *
     * Generated from Godot docs: GLTFCamera.get_size_mag
     */
    fun getSizeMag(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getSizeMagBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The size of the camera. This class and glTF define the camera size magnitude as a radius in
     * meters, while Godot defines it as a diameter in meters. This maps to glTF's `ymag` property.
     * This value is only used for orthographic/orthogonal cameras, when `perspective` is `false`.
     *
     * Generated from Godot docs: GLTFCamera.set_size_mag
     */
    fun setSizeMag(sizeMag: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setSizeMagBind, segment, sizeMag)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The distance to the far culling boundary for this camera relative to its local Z axis, in
     * meters. This maps to glTF's `zfar` property.
     *
     * Generated from Godot docs: GLTFCamera.get_depth_far
     */
    fun getDepthFar(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDepthFarBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The distance to the far culling boundary for this camera relative to its local Z axis, in
     * meters. This maps to glTF's `zfar` property.
     *
     * Generated from Godot docs: GLTFCamera.set_depth_far
     */
    fun setDepthFar(zdepthFar: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDepthFarBind, segment, zdepthFar)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The distance to the near culling boundary for this camera relative to its local Z axis, in
     * meters. This maps to glTF's `znear` property.
     *
     * Generated from Godot docs: GLTFCamera.get_depth_near
     */
    fun getDepthNear(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDepthNearBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The distance to the near culling boundary for this camera relative to its local Z axis, in
     * meters. This maps to glTF's `znear` property.
     *
     * Generated from Godot docs: GLTFCamera.set_depth_near
     */
    fun setDepthNear(zdepthNear: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDepthNearBind, segment, zdepthNear)
        ObjectCalls.keepReachable(this)
    }

    companion object {
        /**
         * Create a new GLTFCamera instance from the given Godot `Camera3D` node.
         *
         * Generated from Godot docs: GLTFCamera.from_node
         */
        fun fromNode(cameraNode: Camera3D): GLTFCamera? {
            val ret = GLTFCamera.wrapOwned(ObjectCalls.ptrcallWithObjectArgRetObject(Binds.fromNodeBind, NULL_SEGMENT, cameraNode.segment))
            ObjectCalls.keepReachable(cameraNode)
            return ret
        }

        /**
         * Creates a new GLTFCamera instance by parsing the given `Dictionary`.
         *
         * Generated from Godot docs: GLTFCamera.from_dictionary
         */
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

        /** A new GLTFCamera, GDScript's `GLTFCamera.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): GLTFCamera =
            RefCounted.owned(GLTFCamera(GodotHandle(requireConstructed(ObjectCalls.constructObject("GLTFCamera"), "GLTFCamera"))))
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
