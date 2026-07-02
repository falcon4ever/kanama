package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector2

/**
 * Generated from Godot docs: OpenXRSpatialComponentPolygon2DList
 */
class OpenXRSpatialComponentPolygon2DList(handle: MemorySegment) : OpenXRSpatialComponentData(handle) {
    fun getTransform(index: Long): Transform3D {
        return ObjectCalls.ptrcallWithLongArgRetTransform3D(getTransformBind, handle, index)
    }

    fun getVertices(snapshot: RID, index: Long): List<Vector2> {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetPackedVector2List(getVerticesBind, handle, snapshot, index)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRSpatialComponentPolygon2DList? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRSpatialComponentPolygon2DList? =
            if (handle.address() == 0L) null else OpenXRSpatialComponentPolygon2DList(handle)

        private const val GET_TRANSFORM_HASH = 1965739696L
        private val getTransformBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialComponentPolygon2DList", "get_transform", GET_TRANSFORM_HASH)
        }

        private const val GET_VERTICES_HASH = 110850971L
        private val getVerticesBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialComponentPolygon2DList", "get_vertices", GET_VERTICES_HASH)
        }
    }
}
