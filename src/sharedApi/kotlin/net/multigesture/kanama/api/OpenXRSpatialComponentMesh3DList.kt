package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Transform3D

/**
 * Generated from Godot docs: OpenXRSpatialComponentMesh3DList
 */
class OpenXRSpatialComponentMesh3DList(handle: GodotHandle) : OpenXRSpatialComponentData(handle) {
    fun getTransform(index: Long): Transform3D {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetTransform3D(getTransformBind, segment, index)
    }

    fun getMesh(index: Long): Mesh? {
        checkOpen()
        return Mesh.wrap(ObjectCalls.ptrcallWithLongArgRetObject(getMeshBind, segment, index))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialComponentMesh3DList? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRSpatialComponentMesh3DList? =
            if (handle.address() == 0L) null else OpenXRSpatialComponentMesh3DList(GodotHandle(handle))

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
