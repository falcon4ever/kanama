package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*
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

    var planeAlignment: Long
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
        ObjectCalls.ptrcallWithVector2Arg(setBoundsSizeBind, segment, boundsSize)
    }

    fun getBoundsSize(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(getBoundsSizeBind, segment)
    }

    fun setPlaneAlignment(planeAlignment: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setPlaneAlignmentBind, segment, planeAlignment)
    }

    fun getPlaneAlignment(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getPlaneAlignmentBind, segment)
    }

    fun setPlaneLabel(planeLabel: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setPlaneLabelBind, segment, planeLabel)
    }

    fun getPlaneLabel(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getPlaneLabelBind, segment)
    }

    fun setMeshData(origin: Transform3D, vertices: List<Vector2>, indices: List<Int>) {
        checkOpen()
        ObjectCalls.ptrcallWithTransform3DPackedVector2ListPackedInt32ListArgs(setMeshDataBind, segment, origin, vertices, indices)
    }

    fun clearMeshData() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(clearMeshDataBind, segment)
    }

    fun getMeshOffset(): Transform3D {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTransform3D(getMeshOffsetBind, segment)
    }

    fun getMesh(): Mesh? {
        checkOpen()
        return Mesh.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMeshBind, segment))
    }

    fun getShape(thickness: Double = 0.01): Shape3D? {
        checkOpen()
        return Shape3D.wrap(ObjectCalls.ptrcallWithDoubleArgRetObject(getShapeBind, segment, thickness))
    }

    object Signals {
        const val meshChanged: String = "mesh_changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRPlaneTracker? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRPlaneTracker? =
            if (handle.address() == 0L) null else OpenXRPlaneTracker(GodotHandle(handle))

        private const val SET_BOUNDS_SIZE_HASH = 743155724L
        private val setBoundsSizeBind by lazy {
            ObjectCalls.getMethodBind("OpenXRPlaneTracker", "set_bounds_size", SET_BOUNDS_SIZE_HASH)
        }

        private const val GET_BOUNDS_SIZE_HASH = 3341600327L
        private val getBoundsSizeBind by lazy {
            ObjectCalls.getMethodBind("OpenXRPlaneTracker", "get_bounds_size", GET_BOUNDS_SIZE_HASH)
        }

        private const val SET_PLANE_ALIGNMENT_HASH = 1214382230L
        private val setPlaneAlignmentBind by lazy {
            ObjectCalls.getMethodBind("OpenXRPlaneTracker", "set_plane_alignment", SET_PLANE_ALIGNMENT_HASH)
        }

        private const val GET_PLANE_ALIGNMENT_HASH = 845541441L
        private val getPlaneAlignmentBind by lazy {
            ObjectCalls.getMethodBind("OpenXRPlaneTracker", "get_plane_alignment", GET_PLANE_ALIGNMENT_HASH)
        }

        private const val SET_PLANE_LABEL_HASH = 83702148L
        private val setPlaneLabelBind by lazy {
            ObjectCalls.getMethodBind("OpenXRPlaneTracker", "set_plane_label", SET_PLANE_LABEL_HASH)
        }

        private const val GET_PLANE_LABEL_HASH = 201670096L
        private val getPlaneLabelBind by lazy {
            ObjectCalls.getMethodBind("OpenXRPlaneTracker", "get_plane_label", GET_PLANE_LABEL_HASH)
        }

        private const val SET_MESH_DATA_HASH = 1877193149L
        private val setMeshDataBind by lazy {
            ObjectCalls.getMethodBind("OpenXRPlaneTracker", "set_mesh_data", SET_MESH_DATA_HASH)
        }

        private const val CLEAR_MESH_DATA_HASH = 3218959716L
        private val clearMeshDataBind by lazy {
            ObjectCalls.getMethodBind("OpenXRPlaneTracker", "clear_mesh_data", CLEAR_MESH_DATA_HASH)
        }

        private const val GET_MESH_OFFSET_HASH = 3229777777L
        private val getMeshOffsetBind by lazy {
            ObjectCalls.getMethodBind("OpenXRPlaneTracker", "get_mesh_offset", GET_MESH_OFFSET_HASH)
        }

        private const val GET_MESH_HASH = 4081188045L
        private val getMeshBind by lazy {
            ObjectCalls.getMethodBind("OpenXRPlaneTracker", "get_mesh", GET_MESH_HASH)
        }

        private const val GET_SHAPE_HASH = 3358509884L
        private val getShapeBind by lazy {
            ObjectCalls.getMethodBind("OpenXRPlaneTracker", "get_shape", GET_SHAPE_HASH)
        }
    }
}
