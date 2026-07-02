package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Spherical shape for use with occlusion culling in `OccluderInstance3D`.
 *
 * Generated from Godot docs: SphereOccluder3D
 */
class SphereOccluder3D(handle: MemorySegment) : Occluder3D(handle) {
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

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): SphereOccluder3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SphereOccluder3D? =
            if (handle.address() == 0L) null else SphereOccluder3D(handle)

        private const val SET_RADIUS_HASH = 373806689L
        private val setRadiusBind by lazy {
            ObjectCalls.getMethodBind("SphereOccluder3D", "set_radius", SET_RADIUS_HASH)
        }

        private const val GET_RADIUS_HASH = 1740695150L
        private val getRadiusBind by lazy {
            ObjectCalls.getMethodBind("SphereOccluder3D", "get_radius", GET_RADIUS_HASH)
        }
    }
}
