package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * A set of `AnimationRootNode`s placed on 2D coordinates, crossfading between the three adjacent
 * ones. Used by `AnimationTree`.
 *
 * Generated from Godot docs: AnimationNodeBlendSpace2D
 */
class AnimationNodeBlendSpace2D(handle: MemorySegment) : AnimationRootNode(handle) {
    var autoTriangles: Boolean
        @JvmName("autoTrianglesProperty")
        get() = getAutoTriangles()
        @JvmName("setAutoTrianglesProperty")
        set(value) = setAutoTriangles(value)

    var minSpace: Vector2
        @JvmName("minSpaceProperty")
        get() = getMinSpace()
        @JvmName("setMinSpaceProperty")
        set(value) = setMinSpace(value)

    var maxSpace: Vector2
        @JvmName("maxSpaceProperty")
        get() = getMaxSpace()
        @JvmName("setMaxSpaceProperty")
        set(value) = setMaxSpace(value)

    var snap: Vector2
        @JvmName("snapProperty")
        get() = getSnap()
        @JvmName("setSnapProperty")
        set(value) = setSnap(value)

    var xLabel: String
        @JvmName("xLabelProperty")
        get() = getXLabel()
        @JvmName("setXLabelProperty")
        set(value) = setXLabel(value)

    var yLabel: String
        @JvmName("yLabelProperty")
        get() = getYLabel()
        @JvmName("setYLabelProperty")
        set(value) = setYLabel(value)

    var blendMode: Long
        @JvmName("blendModeProperty")
        get() = getBlendMode()
        @JvmName("setBlendModeProperty")
        set(value) = setBlendMode(value)

    var sync: Boolean
        @JvmName("syncProperty")
        get() = isUsingSync()
        @JvmName("setSyncProperty")
        set(value) = setUseSync(value)

    var syncMode: Long
        @JvmName("syncModeProperty")
        get() = getSyncMode()
        @JvmName("setSyncModeProperty")
        set(value) = setSyncMode(value)

    var cyclicLength: Double
        @JvmName("cyclicLengthProperty")
        get() = getCyclicLength()
        @JvmName("setCyclicLengthProperty")
        set(value) = setCyclicLength(value)

    fun addBlendPoint(node: AnimationRootNode?, pos: Vector2, atIndex: Int = -1, name: String = "") {
        ObjectCalls.ptrcallWithObjectVector2IntStringNameArgs(addBlendPointBind, handle, node?.requireOpenHandle() ?: MemorySegment.NULL, pos, atIndex, name)
    }

    fun setBlendPointPosition(point: Int, pos: Vector2) {
        ObjectCalls.ptrcallWithIntAndVector2Arg(setBlendPointPositionBind, handle, point, pos)
    }

    fun getBlendPointPosition(point: Int): Vector2 {
        return ObjectCalls.ptrcallWithIntArgRetVector2(getBlendPointPositionBind, handle, point)
    }

    fun setBlendPointNode(point: Int, node: AnimationRootNode?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setBlendPointNodeBind, handle, point, node?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getBlendPointNode(point: Int): AnimationRootNode? {
        return AnimationRootNode.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getBlendPointNodeBind, handle, point))
    }

    fun setBlendPointName(point: Int, name: String) {
        ObjectCalls.ptrcallWithIntAndStringNameArg(setBlendPointNameBind, handle, point, name)
    }

    fun getBlendPointName(point: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetStringName(getBlendPointNameBind, handle, point)
    }

    fun findBlendPointByName(name: String): Int {
        return ObjectCalls.ptrcallWithStringNameArgRetInt(findBlendPointByNameBind, handle, name)
    }

    fun removeBlendPoint(point: Int) {
        ObjectCalls.ptrcallWithIntArg(removeBlendPointBind, handle, point)
    }

    fun getBlendPointCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getBlendPointCountBind, handle)
    }

    fun reorderBlendPoint(fromIndex: Int, toIndex: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(reorderBlendPointBind, handle, fromIndex, toIndex)
    }

    fun addTriangle(x: Int, y: Int, z: Int, atIndex: Int = -1) {
        ObjectCalls.ptrcallWithFourIntArgs(addTriangleBind, handle, x, y, z, atIndex)
    }

    fun getTrianglePoint(triangle: Int, point: Int): Int {
        return ObjectCalls.ptrcallWithTwoIntArgsRetInt(getTrianglePointBind, handle, triangle, point)
    }

    fun removeTriangle(triangle: Int) {
        ObjectCalls.ptrcallWithIntArg(removeTriangleBind, handle, triangle)
    }

    fun getTriangleCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getTriangleCountBind, handle)
    }

    fun setMinSpace(minSpace: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setMinSpaceBind, handle, minSpace)
    }

    fun getMinSpace(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getMinSpaceBind, handle)
    }

    fun setMaxSpace(maxSpace: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setMaxSpaceBind, handle, maxSpace)
    }

    fun getMaxSpace(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getMaxSpaceBind, handle)
    }

    fun setSnap(snap: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setSnapBind, handle, snap)
    }

    fun getSnap(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getSnapBind, handle)
    }

    fun setXLabel(text: String) {
        ObjectCalls.ptrcallWithStringArg(setXLabelBind, handle, text)
    }

    fun getXLabel(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getXLabelBind, handle)
    }

    fun setYLabel(text: String) {
        ObjectCalls.ptrcallWithStringArg(setYLabelBind, handle, text)
    }

    fun getYLabel(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getYLabelBind, handle)
    }

    fun setAutoTriangles(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAutoTrianglesBind, handle, enable)
    }

    fun getAutoTriangles(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getAutoTrianglesBind, handle)
    }

    fun setBlendMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setBlendModeBind, handle, mode)
    }

    fun getBlendMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getBlendModeBind, handle)
    }

    fun setUseSync(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseSyncBind, handle, enable)
    }

    fun isUsingSync(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingSyncBind, handle)
    }

    fun setSyncMode(syncMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setSyncModeBind, handle, syncMode)
    }

    fun getSyncMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSyncModeBind, handle)
    }

    fun setCyclicLength(length: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setCyclicLengthBind, handle, length)
    }

    fun getCyclicLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getCyclicLengthBind, handle)
    }

    object Signals {
        const val trianglesUpdated: String = "triangles_updated"
    }

    companion object {
        const val BLEND_MODE_INTERPOLATED: Long = 0L
        const val BLEND_MODE_DISCRETE: Long = 1L
        const val BLEND_MODE_DISCRETE_CARRY: Long = 2L
        const val SYNC_MODE_NONE: Long = 0L
        const val SYNC_MODE_INDEPENDENT: Long = 1L
        const val SYNC_MODE_CYCLIC_MUTABLE: Long = 2L
        const val SYNC_MODE_CYCLIC_CONSTANT: Long = 3L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): AnimationNodeBlendSpace2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AnimationNodeBlendSpace2D? =
            if (handle.address() == 0L) null else AnimationNodeBlendSpace2D(handle)

        private const val ADD_BLEND_POINT_HASH = 768750458L
        private val addBlendPointBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "add_blend_point", ADD_BLEND_POINT_HASH)
        }

        private const val SET_BLEND_POINT_POSITION_HASH = 163021252L
        private val setBlendPointPositionBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "set_blend_point_position", SET_BLEND_POINT_POSITION_HASH)
        }

        private const val GET_BLEND_POINT_POSITION_HASH = 2299179447L
        private val getBlendPointPositionBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "get_blend_point_position", GET_BLEND_POINT_POSITION_HASH)
        }

        private const val SET_BLEND_POINT_NODE_HASH = 4240341528L
        private val setBlendPointNodeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "set_blend_point_node", SET_BLEND_POINT_NODE_HASH)
        }

        private const val GET_BLEND_POINT_NODE_HASH = 665599029L
        private val getBlendPointNodeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "get_blend_point_node", GET_BLEND_POINT_NODE_HASH)
        }

        private const val SET_BLEND_POINT_NAME_HASH = 3780747571L
        private val setBlendPointNameBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "set_blend_point_name", SET_BLEND_POINT_NAME_HASH)
        }

        private const val GET_BLEND_POINT_NAME_HASH = 659327637L
        private val getBlendPointNameBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "get_blend_point_name", GET_BLEND_POINT_NAME_HASH)
        }

        private const val FIND_BLEND_POINT_BY_NAME_HASH = 2458036349L
        private val findBlendPointByNameBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "find_blend_point_by_name", FIND_BLEND_POINT_BY_NAME_HASH)
        }

        private const val REMOVE_BLEND_POINT_HASH = 1286410249L
        private val removeBlendPointBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "remove_blend_point", REMOVE_BLEND_POINT_HASH)
        }

        private const val GET_BLEND_POINT_COUNT_HASH = 3905245786L
        private val getBlendPointCountBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "get_blend_point_count", GET_BLEND_POINT_COUNT_HASH)
        }

        private const val REORDER_BLEND_POINT_HASH = 3937882851L
        private val reorderBlendPointBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "reorder_blend_point", REORDER_BLEND_POINT_HASH)
        }

        private const val ADD_TRIANGLE_HASH = 753017335L
        private val addTriangleBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "add_triangle", ADD_TRIANGLE_HASH)
        }

        private const val GET_TRIANGLE_POINT_HASH = 50157827L
        private val getTrianglePointBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "get_triangle_point", GET_TRIANGLE_POINT_HASH)
        }

        private const val REMOVE_TRIANGLE_HASH = 1286410249L
        private val removeTriangleBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "remove_triangle", REMOVE_TRIANGLE_HASH)
        }

        private const val GET_TRIANGLE_COUNT_HASH = 3905245786L
        private val getTriangleCountBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "get_triangle_count", GET_TRIANGLE_COUNT_HASH)
        }

        private const val SET_MIN_SPACE_HASH = 743155724L
        private val setMinSpaceBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "set_min_space", SET_MIN_SPACE_HASH)
        }

        private const val GET_MIN_SPACE_HASH = 3341600327L
        private val getMinSpaceBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "get_min_space", GET_MIN_SPACE_HASH)
        }

        private const val SET_MAX_SPACE_HASH = 743155724L
        private val setMaxSpaceBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "set_max_space", SET_MAX_SPACE_HASH)
        }

        private const val GET_MAX_SPACE_HASH = 3341600327L
        private val getMaxSpaceBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "get_max_space", GET_MAX_SPACE_HASH)
        }

        private const val SET_SNAP_HASH = 743155724L
        private val setSnapBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "set_snap", SET_SNAP_HASH)
        }

        private const val GET_SNAP_HASH = 3341600327L
        private val getSnapBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "get_snap", GET_SNAP_HASH)
        }

        private const val SET_X_LABEL_HASH = 83702148L
        private val setXLabelBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "set_x_label", SET_X_LABEL_HASH)
        }

        private const val GET_X_LABEL_HASH = 201670096L
        private val getXLabelBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "get_x_label", GET_X_LABEL_HASH)
        }

        private const val SET_Y_LABEL_HASH = 83702148L
        private val setYLabelBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "set_y_label", SET_Y_LABEL_HASH)
        }

        private const val GET_Y_LABEL_HASH = 201670096L
        private val getYLabelBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "get_y_label", GET_Y_LABEL_HASH)
        }

        private const val SET_AUTO_TRIANGLES_HASH = 2586408642L
        private val setAutoTrianglesBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "set_auto_triangles", SET_AUTO_TRIANGLES_HASH)
        }

        private const val GET_AUTO_TRIANGLES_HASH = 36873697L
        private val getAutoTrianglesBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "get_auto_triangles", GET_AUTO_TRIANGLES_HASH)
        }

        private const val SET_BLEND_MODE_HASH = 81193520L
        private val setBlendModeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "set_blend_mode", SET_BLEND_MODE_HASH)
        }

        private const val GET_BLEND_MODE_HASH = 1398433632L
        private val getBlendModeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "get_blend_mode", GET_BLEND_MODE_HASH)
        }

        private const val SET_USE_SYNC_HASH = 2586408642L
        private val setUseSyncBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "set_use_sync", SET_USE_SYNC_HASH)
        }

        private const val IS_USING_SYNC_HASH = 36873697L
        private val isUsingSyncBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "is_using_sync", IS_USING_SYNC_HASH)
        }

        private const val SET_SYNC_MODE_HASH = 2615784488L
        private val setSyncModeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "set_sync_mode", SET_SYNC_MODE_HASH)
        }

        private const val GET_SYNC_MODE_HASH = 242032665L
        private val getSyncModeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "get_sync_mode", GET_SYNC_MODE_HASH)
        }

        private const val SET_CYCLIC_LENGTH_HASH = 373806689L
        private val setCyclicLengthBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "set_cyclic_length", SET_CYCLIC_LENGTH_HASH)
        }

        private const val GET_CYCLIC_LENGTH_HASH = 1740695150L
        private val getCyclicLengthBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "get_cyclic_length", GET_CYCLIC_LENGTH_HASH)
        }
    }
}
