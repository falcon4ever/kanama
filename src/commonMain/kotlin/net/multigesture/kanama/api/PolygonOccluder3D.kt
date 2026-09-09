package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector2

/**
 * Flat 2D polygon shape for use with occlusion culling in `OccluderInstance3D`.
 *
 * Generated from Godot docs: PolygonOccluder3D
 */
class PolygonOccluder3D(handle: MemorySegment) : Occluder3D(handle) {
    val polygon: List<Vector2>
        @JvmName("polygonProperty")
        get() = getPolygon()

    /**
     * The polygon to use for occlusion culling. The polygon can be convex or concave, but it should
     * have as few points as possible to maximize performance. The polygon must not have intersecting
     * lines. Otherwise, triangulation will fail (with an error message printed).
     *
     * Generated from Godot docs: PolygonOccluder3D.get_polygon
     */
    fun getPolygon(): List<Vector2> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedVector2List(getPolygonBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PolygonOccluder3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PolygonOccluder3D? =
            if (handle.address() == 0L) null else PolygonOccluder3D(handle)

        private const val GET_POLYGON_HASH = 2961356807L
        private val getPolygonBind by lazy {
            ObjectCalls.getMethodBind("PolygonOccluder3D", "get_polygon", GET_POLYGON_HASH)
        }
    }
}
