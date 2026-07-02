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

    fun setRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRadiusBind, handle, radius)
    }

    fun getRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRadiusBind, handle)
    }

    fun setHeight(height: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setHeightBind, handle, height)
    }

    fun getHeight(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getHeightBind, handle)
    }

    fun setMidHeight(midHeight: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMidHeightBind, handle, midHeight)
    }

    fun getMidHeight(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMidHeightBind, handle)
    }

    fun setInside(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setInsideBind, handle, enabled)
    }

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
