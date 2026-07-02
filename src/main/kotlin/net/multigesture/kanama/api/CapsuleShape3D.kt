package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A 3D capsule shape used for physics collision.
 *
 * Generated from Godot docs: CapsuleShape3D
 */
class CapsuleShape3D(handle: MemorySegment) : Shape3D(handle) {
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

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): CapsuleShape3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CapsuleShape3D? =
            if (handle.address() == 0L) null else CapsuleShape3D(handle)

        private const val SET_RADIUS_HASH = 373806689L
        private val setRadiusBind by lazy {
            ObjectCalls.getMethodBind("CapsuleShape3D", "set_radius", SET_RADIUS_HASH)
        }

        private const val GET_RADIUS_HASH = 1740695150L
        private val getRadiusBind by lazy {
            ObjectCalls.getMethodBind("CapsuleShape3D", "get_radius", GET_RADIUS_HASH)
        }

        private const val SET_HEIGHT_HASH = 373806689L
        private val setHeightBind by lazy {
            ObjectCalls.getMethodBind("CapsuleShape3D", "set_height", SET_HEIGHT_HASH)
        }

        private const val GET_HEIGHT_HASH = 1740695150L
        private val getHeightBind by lazy {
            ObjectCalls.getMethodBind("CapsuleShape3D", "get_height", GET_HEIGHT_HASH)
        }

        private const val SET_MID_HEIGHT_HASH = 373806689L
        private val setMidHeightBind by lazy {
            ObjectCalls.getMethodBind("CapsuleShape3D", "set_mid_height", SET_MID_HEIGHT_HASH)
        }

        private const val GET_MID_HEIGHT_HASH = 1740695150L
        private val getMidHeightBind by lazy {
            ObjectCalls.getMethodBind("CapsuleShape3D", "get_mid_height", GET_MID_HEIGHT_HASH)
        }
    }
}
