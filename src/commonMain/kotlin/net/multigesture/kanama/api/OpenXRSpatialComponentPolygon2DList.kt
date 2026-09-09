package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Transform3D

/**
 * Generated from Godot docs: OpenXRSpatialComponentPolygon2DList
 */
class OpenXRSpatialComponentPolygon2DList(handle: MemorySegment) : OpenXRSpatialComponentData(handle) {
    fun getTransform(index: Long): Transform3D {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetTransform3D(getTransformBind, handle, index)
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
    }
}
