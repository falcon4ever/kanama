package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector2

/**
 * Generated from Godot docs: PolygonOccluder3D
 */
class PolygonOccluder3D(handle: MemorySegment) : Occluder3D(handle) {
    val polygon: List<Vector2>
        @JvmName("polygonProperty")
        get() = getPolygon()

    fun getPolygon(): List<Vector2> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedVector2List(getPolygonBind, handle)
    }

    companion object {
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
