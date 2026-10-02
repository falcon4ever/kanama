package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A 2D circle shape used for physics collision.
 *
 * Generated from Godot docs: CircleShape2D
 */
class CircleShape2D(handle: GodotHandle) : Shape2D(handle) {
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
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setRadiusBind, segment, radius)
    }

    /**
     * The circle's radius.
     *
     * Generated from Godot docs: CircleShape2D.get_radius
     */
    fun getRadius(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getRadiusBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CircleShape2D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): CircleShape2D? =
            if (handle.address() == 0L) null else CircleShape2D(GodotHandle(handle))

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
