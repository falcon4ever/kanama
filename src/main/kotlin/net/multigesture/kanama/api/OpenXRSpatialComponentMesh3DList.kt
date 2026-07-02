package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Transform3D

/**
 * Generated from Godot docs: OpenXRSpatialComponentMesh3DList
 */
class OpenXRSpatialComponentMesh3DList(handle: MemorySegment) : OpenXRSpatialComponentData(handle) {
    fun getTransform(index: Long): Transform3D {
        return ObjectCalls.ptrcallWithLongArgRetTransform3D(getTransformBind, handle, index)
    }

    fun getMesh(index: Long): Mesh? {
        return Mesh.wrap(ObjectCalls.ptrcallWithLongArgRetObject(getMeshBind, handle, index))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRSpatialComponentMesh3DList? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRSpatialComponentMesh3DList? =
            if (handle.address() == 0L) null else OpenXRSpatialComponentMesh3DList(handle)

        private const val GET_TRANSFORM_HASH = 1965739696L
        private val getTransformBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialComponentMesh3DList", "get_transform", GET_TRANSFORM_HASH)
        }

        private const val GET_MESH_HASH = 1576363275L
        private val getMeshBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialComponentMesh3DList", "get_mesh", GET_MESH_HASH)
        }
    }
}
