package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A capsule shape collision that interacts with `SpringBoneSimulator3D`.
 *
 * Generated from Godot docs: SpringBoneCollisionCapsule3D
 */
class SpringBoneCollisionCapsule3D(handle: MemorySegment) : SpringBoneCollision3D(handle) {
    var radius: Double
        @JvmName("radiusProperty")
        get() = getRadius()
        @JvmName("setRadiusProperty")
        set(value) = setRadius(value)

    var height: Double
        @JvmName("heightProperty")
        get() = getHeight()
        @JvmName("setHeightProperty")
        set(value) = setHeight(value)

    var midHeight: Double
        @JvmName("midHeightProperty")
        get() = getMidHeight()
        @JvmName("setMidHeightProperty")
        set(value) = setMidHeight(value)

    var inside: Boolean
        @JvmName("insideProperty")
        get() = isInside()
        @JvmName("setInsideProperty")
        set(value) = setInside(value)

    /**
     * The capsule's radius. Note: The `radius` of a capsule cannot be greater than half of its
     * `height`. Otherwise, the capsule becomes a sphere. If the `radius` is greater than half of the
     * `height`, the properties adjust to a valid value.
     *
     * Generated from Godot docs: SpringBoneCollisionCapsule3D.set_radius
     */
    fun setRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRadiusBind, handle, radius)
    }

    /**
     * The capsule's radius. Note: The `radius` of a capsule cannot be greater than half of its
     * `height`. Otherwise, the capsule becomes a sphere. If the `radius` is greater than half of the
     * `height`, the properties adjust to a valid value.
     *
     * Generated from Godot docs: SpringBoneCollisionCapsule3D.get_radius
     */
    fun getRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRadiusBind, handle)
    }

    /**
     * The capsule's full height, including the hemispheres. Note: The `height` of a capsule must be at
     * least twice its `radius`. Otherwise, the capsule becomes a sphere. If the `height` is less than
     * twice the `radius`, the properties adjust to a valid value.
     *
     * Generated from Godot docs: SpringBoneCollisionCapsule3D.set_height
     */
    fun setHeight(height: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setHeightBind, handle, height)
    }

    /**
     * The capsule's full height, including the hemispheres. Note: The `height` of a capsule must be at
     * least twice its `radius`. Otherwise, the capsule becomes a sphere. If the `height` is less than
     * twice the `radius`, the properties adjust to a valid value.
     *
     * Generated from Godot docs: SpringBoneCollisionCapsule3D.get_height
     */
    fun getHeight(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getHeightBind, handle)
    }

    /**
     * The capsule's height, excluding the hemispheres. This is the height of the central cylindrical
     * part in the middle of the capsule, and is the distance between the centers of the two
     * hemispheres. This is a wrapper for `height`.
     *
     * Generated from Godot docs: SpringBoneCollisionCapsule3D.set_mid_height
     */
    fun setMidHeight(midHeight: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMidHeightBind, handle, midHeight)
    }

    /**
     * The capsule's height, excluding the hemispheres. This is the height of the central cylindrical
     * part in the middle of the capsule, and is the distance between the centers of the two
     * hemispheres. This is a wrapper for `height`.
     *
     * Generated from Godot docs: SpringBoneCollisionCapsule3D.get_mid_height
     */
    fun getMidHeight(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMidHeightBind, handle)
    }

    /**
     * If `true`, the collision acts to trap the joint within the collision.
     *
     * Generated from Godot docs: SpringBoneCollisionCapsule3D.set_inside
     */
    fun setInside(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setInsideBind, handle, enabled)
    }

    /**
     * If `true`, the collision acts to trap the joint within the collision.
     *
     * Generated from Godot docs: SpringBoneCollisionCapsule3D.is_inside
     */
    fun isInside(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isInsideBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): SpringBoneCollisionCapsule3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SpringBoneCollisionCapsule3D? =
            if (handle.address() == 0L) null else SpringBoneCollisionCapsule3D(handle)

        private const val SET_RADIUS_HASH = 373806689L
        private val setRadiusBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneCollisionCapsule3D", "set_radius", SET_RADIUS_HASH)
        }

        private const val GET_RADIUS_HASH = 1740695150L
        private val getRadiusBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneCollisionCapsule3D", "get_radius", GET_RADIUS_HASH)
        }

        private const val SET_HEIGHT_HASH = 373806689L
        private val setHeightBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneCollisionCapsule3D", "set_height", SET_HEIGHT_HASH)
        }

        private const val GET_HEIGHT_HASH = 1740695150L
        private val getHeightBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneCollisionCapsule3D", "get_height", GET_HEIGHT_HASH)
        }

        private const val SET_MID_HEIGHT_HASH = 373806689L
        private val setMidHeightBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneCollisionCapsule3D", "set_mid_height", SET_MID_HEIGHT_HASH)
        }

        private const val GET_MID_HEIGHT_HASH = 1740695150L
        private val getMidHeightBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneCollisionCapsule3D", "get_mid_height", GET_MID_HEIGHT_HASH)
        }

        private const val SET_INSIDE_HASH = 2586408642L
        private val setInsideBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneCollisionCapsule3D", "set_inside", SET_INSIDE_HASH)
        }

        private const val IS_INSIDE_HASH = 36873697L
        private val isInsideBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneCollisionCapsule3D", "is_inside", IS_INSIDE_HASH)
        }
    }
}
