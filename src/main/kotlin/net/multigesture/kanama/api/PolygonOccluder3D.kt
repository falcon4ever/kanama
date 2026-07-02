package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * Flat 2D polygon shape for use with occlusion culling in `OccluderInstance3D`.
 *
 * Generated from Godot docs: PolygonOccluder3D
 */
class PolygonOccluder3D(handle: MemorySegment) : Occluder3D(handle) {
    var polygon: List<Vector2>
        @JvmName("polygonProperty")
        get() = getPolygon()
        @JvmName("setPolygonProperty")
        set(value) = setPolygon(value)

    fun setPolygon(polygon: List<Vector2>) {
        ObjectCalls.ptrcallWithPackedVector2ListArg(setPolygonBind, handle, polygon)
    }

    fun getPolygon(): List<Vector2> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector2List(getPolygonBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PolygonOccluder3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PolygonOccluder3D? =
            if (handle.address() == 0L) null else PolygonOccluder3D(handle)

        private const val SET_POLYGON_HASH = 1509147220L
        private val setPolygonBind by lazy {
            ObjectCalls.getMethodBind("PolygonOccluder3D", "set_polygon", SET_POLYGON_HASH)
        }

        private const val GET_POLYGON_HASH = 2961356807L
        private val getPolygonBind by lazy {
            ObjectCalls.getMethodBind("PolygonOccluder3D", "get_polygon", GET_POLYGON_HASH)
        }
    }
}
