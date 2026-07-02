package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Directional 2D light from a distance.
 *
 * Generated from Godot docs: DirectionalLight2D
 */
class DirectionalLight2D(handle: MemorySegment) : Light2D(handle) {
    var maxDistance: Double
        @JvmName("maxDistanceProperty")
        get() = getMaxDistance()
        @JvmName("setMaxDistanceProperty")
        set(value) = setMaxDistance(value)

    fun setMaxDistance(pixels: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMaxDistanceBind, handle, pixels)
    }

    fun getMaxDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMaxDistanceBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): DirectionalLight2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): DirectionalLight2D? =
            if (handle.address() == 0L) null else DirectionalLight2D(handle)

        private const val SET_MAX_DISTANCE_HASH = 373806689L
        private val setMaxDistanceBind by lazy {
            ObjectCalls.getMethodBind("DirectionalLight2D", "set_max_distance", SET_MAX_DISTANCE_HASH)
        }

        private const val GET_MAX_DISTANCE_HASH = 1740695150L
        private val getMaxDistanceBind by lazy {
            ObjectCalls.getMethodBind("DirectionalLight2D", "get_max_distance", GET_MAX_DISTANCE_HASH)
        }
    }
}
