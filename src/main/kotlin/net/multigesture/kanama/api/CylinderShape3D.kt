package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A 3D cylinder shape used for physics collision.
 *
 * Generated from Godot docs: CylinderShape3D
 */
class CylinderShape3D(handle: MemorySegment) : Shape3D(handle) {
    var height: Double
        @JvmName("heightProperty")
        get() = getHeight()
        @JvmName("setHeightProperty")
        set(value) = setHeight(value)

    var radius: Double
        @JvmName("radiusProperty")
        get() = getRadius()
        @JvmName("setRadiusProperty")
        set(value) = setRadius(value)

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

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): CylinderShape3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CylinderShape3D? =
            if (handle.address() == 0L) null else CylinderShape3D(handle)

        private const val SET_RADIUS_HASH = 373806689L
        private val setRadiusBind by lazy {
            ObjectCalls.getMethodBind("CylinderShape3D", "set_radius", SET_RADIUS_HASH)
        }

        private const val GET_RADIUS_HASH = 1740695150L
        private val getRadiusBind by lazy {
            ObjectCalls.getMethodBind("CylinderShape3D", "get_radius", GET_RADIUS_HASH)
        }

        private const val SET_HEIGHT_HASH = 373806689L
        private val setHeightBind by lazy {
            ObjectCalls.getMethodBind("CylinderShape3D", "set_height", SET_HEIGHT_HASH)
        }

        private const val GET_HEIGHT_HASH = 1740695150L
        private val getHeightBind by lazy {
            ObjectCalls.getMethodBind("CylinderShape3D", "get_height", GET_HEIGHT_HASH)
        }
    }
}
