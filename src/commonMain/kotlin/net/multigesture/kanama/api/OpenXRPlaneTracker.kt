package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector2

/**
 * Generated from Godot docs: OpenXRPlaneTracker
 */
class OpenXRPlaneTracker(handle: GodotHandle) : OpenXRSpatialEntityTracker(handle) {
    var boundsSize: Vector2
        @JvmName("boundsSizeProperty")
        get() = getBoundsSize()
        @JvmName("setBoundsSizeProperty")
        set(value) = setBoundsSize(value)

    var planeAlignment: OpenXRSpatialComponentPlaneAlignmentList.PlaneAlignment
        @JvmName("planeAlignmentProperty")
        get() = getPlaneAlignment()
        @JvmName("setPlaneAlignmentProperty")
        set(value) = setPlaneAlignment(value)

    var planeLabel: String
        @JvmName("planeLabelProperty")
        get() = getPlaneLabel()
        @JvmName("setPlaneLabelProperty")
        set(value) = setPlaneLabel(value)

    fun setBoundsSize(boundsSize: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(Binds.setBoundsSizeBind, segment, boundsSize)
    }

    fun getBoundsSize(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getBoundsSizeBind, segment)
    }

    fun setPlaneAlignment(planeAlignment: OpenXRSpatialComponentPlaneAlignmentList.PlaneAlignment) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setPlaneAlignmentBind, segment, planeAlignment.value)
    }

    fun getPlaneAlignment(): OpenXRSpatialComponentPlaneAlignmentList.PlaneAlignment {
        checkOpen()
        return OpenXRSpatialComponentPlaneAlignmentList.PlaneAlignment(ObjectCalls.ptrcallNoArgsRetLong(Binds.getPlaneAlignmentBind, segment))
    }

    fun setPlaneLabel(planeLabel: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setPlaneLabelBind, segment, planeLabel)
    }

    fun getPlaneLabel(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getPlaneLabelBind, segment)
    }

    fun setMeshData(origin: Transform3D, vertices: List<Vector2>, indices: List<Int>) {
        checkOpen()
        ObjectCalls.ptrcallWithTransform3DPackedVector2ListPackedInt32ListArgs(Binds.setMeshDataBind, segment, origin, vertices, indices)
    }

    fun clearMeshData() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.clearMeshDataBind, segment)
    }

    fun getMeshOffset(): Transform3D {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTransform3D(Binds.getMeshOffsetBind, segment)
    }

    fun getMesh(): Mesh? {
        checkOpen()
        return Mesh.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getMeshBind, segment))
    }

    fun getShape(thickness: Double = 0.01): Shape3D? {
        checkOpen()
        return Shape3D.wrapOwned(ObjectCalls.ptrcallWithDoubleArgRetObject(Binds.getShapeBind, segment, thickness))
    }

    /** Signal `mesh_changed()`; see [TypedSignal]. */
    val meshChanged: Signal0
        @JvmName("meshChangedTypedSignal")
        get() = Signal0(this, "mesh_changed")

    object Signals {
        const val meshChanged: String = "mesh_changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRPlaneTracker? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRPlaneTracker? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRPlaneTracker(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRPlaneTracker? =
            if (handle.address() == 0L) null else OpenXRPlaneTracker(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_BOUNDS_SIZE_HASH = 743155724L
        @JvmField
        val setBoundsSizeBind =
            ObjectCalls.getMethodBind("OpenXRPlaneTracker", "set_bounds_size", SET_BOUNDS_SIZE_HASH)

        private const val GET_BOUNDS_SIZE_HASH = 3341600327L
        @JvmField
        val getBoundsSizeBind =
            ObjectCalls.getMethodBind("OpenXRPlaneTracker", "get_bounds_size", GET_BOUNDS_SIZE_HASH)

        private const val SET_PLANE_ALIGNMENT_HASH = 1214382230L
        @JvmField
        val setPlaneAlignmentBind =
            ObjectCalls.getMethodBind("OpenXRPlaneTracker", "set_plane_alignment", SET_PLANE_ALIGNMENT_HASH)

        private const val GET_PLANE_ALIGNMENT_HASH = 845541441L
        @JvmField
        val getPlaneAlignmentBind =
            ObjectCalls.getMethodBind("OpenXRPlaneTracker", "get_plane_alignment", GET_PLANE_ALIGNMENT_HASH)

        private const val SET_PLANE_LABEL_HASH = 83702148L
        @JvmField
        val setPlaneLabelBind =
            ObjectCalls.getMethodBind("OpenXRPlaneTracker", "set_plane_label", SET_PLANE_LABEL_HASH)

        private const val GET_PLANE_LABEL_HASH = 201670096L
        @JvmField
        val getPlaneLabelBind =
            ObjectCalls.getMethodBind("OpenXRPlaneTracker", "get_plane_label", GET_PLANE_LABEL_HASH)

        private const val SET_MESH_DATA_HASH = 1877193149L
        @JvmField
        val setMeshDataBind =
            ObjectCalls.getMethodBind("OpenXRPlaneTracker", "set_mesh_data", SET_MESH_DATA_HASH)

        private const val CLEAR_MESH_DATA_HASH = 3218959716L
        @JvmField
        val clearMeshDataBind =
            ObjectCalls.getMethodBind("OpenXRPlaneTracker", "clear_mesh_data", CLEAR_MESH_DATA_HASH)

        private const val GET_MESH_OFFSET_HASH = 3229777777L
        @JvmField
        val getMeshOffsetBind =
            ObjectCalls.getMethodBind("OpenXRPlaneTracker", "get_mesh_offset", GET_MESH_OFFSET_HASH)

        private const val GET_MESH_HASH = 4081188045L
        @JvmField
        val getMeshBind =
            ObjectCalls.getMethodBind("OpenXRPlaneTracker", "get_mesh", GET_MESH_HASH)

        private const val GET_SHAPE_HASH = 3358509884L
        @JvmField
        val getShapeBind =
            ObjectCalls.getMethodBind("OpenXRPlaneTracker", "get_shape", GET_SHAPE_HASH)
    }
}
