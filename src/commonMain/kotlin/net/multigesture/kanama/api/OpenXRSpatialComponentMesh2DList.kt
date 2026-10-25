package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector2

/**
 * Object for storing the queries mesh2d result data.
 *
 * Generated from Godot docs: OpenXRSpatialComponentMesh2DList
 */
class OpenXRSpatialComponentMesh2DList(handle: GodotHandle) : OpenXRSpatialComponentData(handle) {
    /**
     * Returns the transform for positioning our mesh for the entity at this `index`.
     *
     * Generated from Godot docs: OpenXRSpatialComponentMesh2DList.get_transform
     */
    fun getTransform(index: Long): Transform3D {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithLongArgRetTransform3D(Binds.getTransformBind, segment, index)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the mesh vertices for the entity at this `index`.
     *
     * Generated from Godot docs: OpenXRSpatialComponentMesh2DList.get_vertices
     */
    fun getVertices(snapshot: RID, index: Long): List<Vector2> {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithRIDAndLongArgRetPackedVector2List(Binds.getVerticesBind, segment, snapshot, index)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the mesh indices for the entity at this `index`.
     *
     * Generated from Godot docs: OpenXRSpatialComponentMesh2DList.get_indices
     */
    fun getIndices(snapshot: RID, index: Long): List<Int> {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithRIDAndLongArgRetPackedInt32List(Binds.getIndicesBind, segment, snapshot, index)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialComponentMesh2DList? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRSpatialComponentMesh2DList? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRSpatialComponentMesh2DList(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRSpatialComponentMesh2DList? =
            if (handle.address() == 0L) null else OpenXRSpatialComponentMesh2DList(GodotHandle(handle))

        /** A new OpenXRSpatialComponentMesh2DList, GDScript's `OpenXRSpatialComponentMesh2DList.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): OpenXRSpatialComponentMesh2DList =
            RefCounted.owned(OpenXRSpatialComponentMesh2DList(GodotHandle(requireConstructed(ObjectCalls.constructObject("OpenXRSpatialComponentMesh2DList"), "OpenXRSpatialComponentMesh2DList"))))
    }

    private object Binds {
        private const val GET_TRANSFORM_HASH = 1965739696L
        @JvmField
        val getTransformBind =
            ObjectCalls.getMethodBind("OpenXRSpatialComponentMesh2DList", "get_transform", GET_TRANSFORM_HASH)

        private const val GET_VERTICES_HASH = 110850971L
        @JvmField
        val getVerticesBind =
            ObjectCalls.getMethodBind("OpenXRSpatialComponentMesh2DList", "get_vertices", GET_VERTICES_HASH)

        private const val GET_INDICES_HASH = 3393655756L
        @JvmField
        val getIndicesBind =
            ObjectCalls.getMethodBind("OpenXRSpatialComponentMesh2DList", "get_indices", GET_INDICES_HASH)
    }
}
