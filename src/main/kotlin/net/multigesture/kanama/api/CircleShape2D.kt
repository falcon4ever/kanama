package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A 2D circle shape used for physics collision.
 *
 * Generated from Godot docs: CircleShape2D
 */
class CircleShape2D(handle: MemorySegment) : Shape2D(handle) {
    var radius: Double
        @JvmName("radiusProperty")
        get() = getRadius()
        @JvmName("setRadiusProperty")
        set(value) = setRadius(value)

    /**
     * The circle's radius.
     *
     * Generated from Godot docs: CircleShape2D.set_radius
     */
    fun setRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRadiusBind, handle, radius)
    }

    /**
     * The circle's radius.
     *
     * Generated from Godot docs: CircleShape2D.get_radius
     */
    fun getRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRadiusBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): CircleShape2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CircleShape2D? =
            if (handle.address() == 0L) null else CircleShape2D(handle)

        private const val SET_RADIUS_HASH = 373806689L
        private val setRadiusBind by lazy {
            ObjectCalls.getMethodBind("CircleShape2D", "set_radius", SET_RADIUS_HASH)
        }

        private const val GET_RADIUS_HASH = 1740695150L
        private val getRadiusBind by lazy {
            ObjectCalls.getMethodBind("CircleShape2D", "get_radius", GET_RADIUS_HASH)
        }
    }
}
