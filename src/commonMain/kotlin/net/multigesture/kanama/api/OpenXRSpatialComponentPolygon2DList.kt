package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector2

/**
 * Generated from Godot docs: OpenXRSpatialComponentPolygon2DList
 */
class OpenXRSpatialComponentPolygon2DList(handle: GodotHandle) : OpenXRSpatialComponentData(handle) {
    fun getTransform(index: Long): Transform3D {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetTransform3D(Binds.getTransformBind, segment, index)
    }

    fun getVertices(snapshot: RID, index: Long): List<Vector2> {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetPackedVector2List(Binds.getVerticesBind, segment, snapshot, index)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialComponentPolygon2DList? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRSpatialComponentPolygon2DList? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRSpatialComponentPolygon2DList(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRSpatialComponentPolygon2DList? =
            if (handle.address() == 0L) null else OpenXRSpatialComponentPolygon2DList(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_TRANSFORM_HASH = 1965739696L
        @JvmField
        val getTransformBind =
            ObjectCalls.getMethodBind("OpenXRSpatialComponentPolygon2DList", "get_transform", GET_TRANSFORM_HASH)

        private const val GET_VERTICES_HASH = 110850971L
        @JvmField
        val getVerticesBind =
            ObjectCalls.getMethodBind("OpenXRSpatialComponentPolygon2DList", "get_vertices", GET_VERTICES_HASH)
    }
}
