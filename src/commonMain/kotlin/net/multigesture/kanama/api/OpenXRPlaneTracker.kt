package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector2

/**
 * Spatial entity tracker for our spatial entity plane tracking extension.
 *
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

    /**
     * The bounding size of the plane. This is a 2D size.
     *
     * Generated from Godot docs: OpenXRPlaneTracker.set_bounds_size
     */
    fun setBoundsSize(boundsSize: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(Binds.setBoundsSizeBind, segment, boundsSize)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The bounding size of the plane. This is a 2D size.
     *
     * Generated from Godot docs: OpenXRPlaneTracker.get_bounds_size
     */
    fun getBoundsSize(): Vector2 {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetVector2(Binds.getBoundsSizeBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The main alignment in space of this plane.
     *
     * Generated from Godot docs: OpenXRPlaneTracker.set_plane_alignment
     */
    fun setPlaneAlignment(planeAlignment: OpenXRSpatialComponentPlaneAlignmentList.PlaneAlignment) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setPlaneAlignmentBind, segment, planeAlignment.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The main alignment in space of this plane.
     *
     * Generated from Godot docs: OpenXRPlaneTracker.get_plane_alignment
     */
    fun getPlaneAlignment(): OpenXRSpatialComponentPlaneAlignmentList.PlaneAlignment {
        checkOpen()
        val ret = OpenXRSpatialComponentPlaneAlignmentList.PlaneAlignment(ObjectCalls.ptrcallNoArgsRetLong(Binds.getPlaneAlignmentBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The semantic label for this plane.
     *
     * Generated from Godot docs: OpenXRPlaneTracker.set_plane_label
     */
    fun setPlaneLabel(planeLabel: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setPlaneLabelBind, segment, planeLabel)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The semantic label for this plane.
     *
     * Generated from Godot docs: OpenXRPlaneTracker.get_plane_label
     */
    fun getPlaneLabel(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getPlaneLabelBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the mesh data for this plane. You should only call this if you are handling your own
     * discovery logic.
     *
     * Generated from Godot docs: OpenXRPlaneTracker.set_mesh_data
     */
    fun setMeshData(origin: Transform3D, vertices: List<Vector2>, indices: List<Int>) {
        checkOpen()
        ObjectCalls.ptrcallWithTransform3DPackedVector2ListPackedInt32ListArgs(Binds.setMeshDataBind, segment, origin, vertices, indices)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Clears the mesh data for this tracker. You should only call this if you are handling your own
     * discovery logic.
     *
     * Generated from Godot docs: OpenXRPlaneTracker.clear_mesh_data
     */
    fun clearMeshData() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.clearMeshDataBind, segment)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Gets the transform by which to offset the mesh and collision shape from our pose to display
     * these correctly.
     *
     * Generated from Godot docs: OpenXRPlaneTracker.get_mesh_offset
     */
    fun getMeshOffset(): Transform3D {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetTransform3D(Binds.getMeshOffsetBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Gets a mesh created from either the mesh data or from our bounding size for this plane.
     *
     * Generated from Godot docs: OpenXRPlaneTracker.get_mesh
     */
    fun getMesh(): Mesh? {
        checkOpen()
        val ret = Mesh.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getMeshBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Gets a collision shape built either from the mesh data or from our bounding size for this plane.
     *
     * Generated from Godot docs: OpenXRPlaneTracker.get_shape
     */
    fun getShape(thickness: Double = 0.01): Shape3D? {
        checkOpen()
        val ret = Shape3D.wrapOwned(ObjectCalls.ptrcallWithDoubleArgRetObject(Binds.getShapeBind, segment, thickness))
        ObjectCalls.keepReachable(this)
        return ret
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

        /** A new OpenXRPlaneTracker, GDScript's `OpenXRPlaneTracker.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): OpenXRPlaneTracker =
            RefCounted.owned(OpenXRPlaneTracker(GodotHandle(ObjectCalls.constructObject("OpenXRPlaneTracker"))))
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
