package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector2

/**
 * A set of `AnimationRootNode`s placed on 2D coordinates, crossfading between the three adjacent
 * ones. Used by `AnimationTree`.
 *
 * Generated from Godot docs: AnimationNodeBlendSpace2D
 */
class AnimationNodeBlendSpace2D(handle: GodotHandle) : AnimationRootNode(handle) {
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

    var blendMode: AnimationNodeBlendSpace2D.BlendMode
        @JvmName("blendModeProperty")
        get() = getBlendMode()
        @JvmName("setBlendModeProperty")
        set(value) = setBlendMode(value)

    var sync: Boolean
        @JvmName("syncProperty")
        get() = isUsingSync()
        @JvmName("setSyncProperty")
        set(value) = setUseSync(value)

    var syncMode: AnimationNodeBlendSpace2D.SyncMode
        @JvmName("syncModeProperty")
        get() = getSyncMode()
        @JvmName("setSyncModeProperty")
        set(value) = setSyncMode(value)

    var cyclicLength: Double
        @JvmName("cyclicLengthProperty")
        get() = getCyclicLength()
        @JvmName("setCyclicLengthProperty")
        set(value) = setCyclicLength(value)

    /**
     * Adds a new point with `name` that represents a `node` at the position set by `pos`. You can
     * insert it at a specific index using the `at_index` argument. If you use the default value for
     * `at_index`, the point is inserted at the end of the blend points array. Note: If no name is
     * provided, safe index is used as reference. In the future, empty names will be deprecated, so
     * explicitly passing a name is recommended.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace2D.add_blend_point
     */
    fun addBlendPoint(node: AnimationRootNode?, pos: Vector2, atIndex: Int = -1, name: String = "") {
        checkOpen()
        ObjectCalls.ptrcallWithObjectVector2IntStringNameArgs(Binds.addBlendPointBind, segment, node?.requireOpenHandle() ?: NULL_SEGMENT, pos, atIndex, name)
    }

    /**
     * Updates the position of the point at index `point` in the blend space.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace2D.set_blend_point_position
     */
    fun setBlendPointPosition(point: Int, pos: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndVector2Arg(Binds.setBlendPointPositionBind, segment, point, pos)
    }

    /**
     * Returns the position of the point at index `point`.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace2D.get_blend_point_position
     */
    fun getBlendPointPosition(point: Int): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetVector2(Binds.getBlendPointPositionBind, segment, point)
    }

    /**
     * Changes the `AnimationNode` referenced by the point at index `point`.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace2D.set_blend_point_node
     */
    fun setBlendPointNode(point: Int, node: AnimationRootNode?) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndObjectArg(Binds.setBlendPointNodeBind, segment, point, node?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Returns the `AnimationRootNode` referenced by the point at index `point`.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace2D.get_blend_point_node
     */
    fun getBlendPointNode(point: Int): AnimationRootNode? {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetObject(Binds.getBlendPointNodeBind, segment, point)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return AnimationRootNode.wrapOwned(ret)
    }

    /**
     * Sets the name of the blend point at index `point`. If the name conflicts with an existing point,
     * a unique name will be generated automatically.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace2D.set_blend_point_name
     */
    fun setBlendPointName(point: Int, name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndStringNameArg(Binds.setBlendPointNameBind, segment, point, name)
    }

    /**
     * Returns the name of the blend point at index `point`.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace2D.get_blend_point_name
     */
    fun getBlendPointName(point: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetStringName(Binds.getBlendPointNameBind, segment, point)
    }

    /**
     * Returns the index of the blend point with the given `name`. Returns `-1` if no blend point with
     * that name is found.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace2D.find_blend_point_by_name
     */
    fun findBlendPointByName(name: String): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameArgRetInt(Binds.findBlendPointByNameBind, segment, name)
    }

    /**
     * Removes the point at index `point` from the blend space.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace2D.remove_blend_point
     */
    fun removeBlendPoint(point: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.removeBlendPointBind, segment, point)
    }

    /**
     * Returns the number of points in the blend space.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace2D.get_blend_point_count
     */
    fun getBlendPointCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getBlendPointCountBind, segment)
    }

    /**
     * Swaps the blend points at indices `from_index` and `to_index`, exchanging their positions and
     * properties.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace2D.reorder_blend_point
     */
    fun reorderBlendPoint(fromIndex: Int, toIndex: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.reorderBlendPointBind, segment, fromIndex, toIndex)
    }

    /**
     * Creates a new triangle using three points `x`, `y`, and `z`. Triangles can overlap. You can
     * insert the triangle at a specific index using the `at_index` argument. If you use the default
     * value for `at_index`, the point is inserted at the end of the blend points array.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace2D.add_triangle
     */
    fun addTriangle(x: Int, y: Int, z: Int, atIndex: Int = -1) {
        checkOpen()
        ObjectCalls.ptrcallWithFourIntArgs(Binds.addTriangleBind, segment, x, y, z, atIndex)
    }

    /**
     * Returns the position of the point at index `point` in the triangle of index `triangle`.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace2D.get_triangle_point
     */
    fun getTrianglePoint(triangle: Int, point: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetInt(Binds.getTrianglePointBind, segment, triangle, point)
    }

    /**
     * Removes the triangle at index `triangle` from the blend space.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace2D.remove_triangle
     */
    fun removeTriangle(triangle: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.removeTriangleBind, segment, triangle)
    }

    /**
     * Returns the number of triangles in the blend space.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace2D.get_triangle_count
     */
    fun getTriangleCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getTriangleCountBind, segment)
    }

    /**
     * The blend space's X and Y axes' lower limit for the points' position. See `add_blend_point`.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace2D.set_min_space
     */
    fun setMinSpace(minSpace: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(Binds.setMinSpaceBind, segment, minSpace)
    }

    /**
     * The blend space's X and Y axes' lower limit for the points' position. See `add_blend_point`.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace2D.get_min_space
     */
    fun getMinSpace(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getMinSpaceBind, segment)
    }

    /**
     * The blend space's X and Y axes' upper limit for the points' position. See `add_blend_point`.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace2D.set_max_space
     */
    fun setMaxSpace(maxSpace: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(Binds.setMaxSpaceBind, segment, maxSpace)
    }

    /**
     * The blend space's X and Y axes' upper limit for the points' position. See `add_blend_point`.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace2D.get_max_space
     */
    fun getMaxSpace(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getMaxSpaceBind, segment)
    }

    /**
     * Position increment to snap to when moving a point.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace2D.set_snap
     */
    fun setSnap(snap: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(Binds.setSnapBind, segment, snap)
    }

    /**
     * Position increment to snap to when moving a point.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace2D.get_snap
     */
    fun getSnap(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getSnapBind, segment)
    }

    /**
     * Name of the blend space's X axis.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace2D.set_x_label
     */
    fun setXLabel(text: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setXLabelBind, segment, text)
    }

    /**
     * Name of the blend space's X axis.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace2D.get_x_label
     */
    fun getXLabel(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getXLabelBind, segment)
    }

    /**
     * Name of the blend space's Y axis.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace2D.set_y_label
     */
    fun setYLabel(text: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setYLabelBind, segment, text)
    }

    /**
     * Name of the blend space's Y axis.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace2D.get_y_label
     */
    fun getYLabel(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getYLabelBind, segment)
    }

    /**
     * If `true`, the blend space is triangulated automatically. The mesh updates every time you add or
     * remove points with `add_blend_point` and `remove_blend_point`.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace2D.set_auto_triangles
     */
    fun setAutoTriangles(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setAutoTrianglesBind, segment, enable)
    }

    /**
     * If `true`, the blend space is triangulated automatically. The mesh updates every time you add or
     * remove points with `add_blend_point` and `remove_blend_point`.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace2D.get_auto_triangles
     */
    fun getAutoTriangles(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getAutoTrianglesBind, segment)
    }

    /**
     * Controls the interpolation between animations.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace2D.set_blend_mode
     */
    fun setBlendMode(mode: AnimationNodeBlendSpace2D.BlendMode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setBlendModeBind, segment, mode.value)
    }

    /**
     * Controls the interpolation between animations.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace2D.get_blend_mode
     */
    fun getBlendMode(): AnimationNodeBlendSpace2D.BlendMode {
        checkOpen()
        return AnimationNodeBlendSpace2D.BlendMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getBlendModeBind, segment))
    }

    /**
     * If `true`, sync mode is enabled (equivalent to `SyncMode.INDEPENDENT`). This property is kept
     * for backward compatibility.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace2D.set_use_sync
     */
    fun setUseSync(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setUseSyncBind, segment, enable)
    }

    /**
     * If `true`, sync mode is enabled (equivalent to `SyncMode.INDEPENDENT`). This property is kept
     * for backward compatibility.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace2D.is_using_sync
     */
    fun isUsingSync(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isUsingSyncBind, segment)
    }

    /**
     * Controls how animations are synced when blended. See `SyncMode` for available options.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace2D.set_sync_mode
     */
    fun setSyncMode(syncMode: AnimationNodeBlendSpace2D.SyncMode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setSyncModeBind, segment, syncMode.value)
    }

    /**
     * Controls how animations are synced when blended. See `SyncMode` for available options.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace2D.get_sync_mode
     */
    fun getSyncMode(): AnimationNodeBlendSpace2D.SyncMode {
        checkOpen()
        return AnimationNodeBlendSpace2D.SyncMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getSyncModeBind, segment))
    }

    /**
     * The cycle length in seconds used by `SyncMode.CYCLIC_CONSTANT`. All animations are time-scaled
     * so they complete one full cycle in this duration. Must be greater than `0` for cyclic sync to
     * take effect.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace2D.set_cyclic_length
     */
    fun setCyclicLength(length: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setCyclicLengthBind, segment, length)
    }

    /**
     * The cycle length in seconds used by `SyncMode.CYCLIC_CONSTANT`. All animations are time-scaled
     * so they complete one full cycle in this duration. Must be greater than `0` for cyclic sync to
     * take effect.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace2D.get_cyclic_length
     */
    fun getCyclicLength(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getCyclicLengthBind, segment)
    }

    /** Signal `triangles_updated()`; see [TypedSignal]. */
    val trianglesUpdated: Signal0
        @JvmName("trianglesUpdatedTypedSignal")
        get() = Signal0(this, "triangles_updated")

    object Signals {
        const val trianglesUpdated: String = "triangles_updated"
    }

    /**
     * Godot's `AnimationNodeBlendSpace2D.BlendMode` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`AnimationNodeBlendSpace2D.BlendMode.<NAME>`).
     *
     * Generated from Godot docs: AnimationNodeBlendSpace2D.BlendMode
     */
    @JvmInline
    value class BlendMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The interpolation between animations is linear.
             *
             * Generated from Godot docs: AnimationNodeBlendSpace2D.BLEND_MODE_INTERPOLATED
             */
            val INTERPOLATED: BlendMode get() = BlendMode(0L)
            /**
             * The blend space plays the animation of the animation node which blending position is closest to.
             * Useful for frame-by-frame 2D animations.
             *
             * Generated from Godot docs: AnimationNodeBlendSpace2D.BLEND_MODE_DISCRETE
             */
            val DISCRETE: BlendMode get() = BlendMode(1L)
            /**
             * Similar to `BlendMode.DISCRETE`, but starts the new animation at the last animation's playback
             * position.
             *
             * Generated from Godot docs: AnimationNodeBlendSpace2D.BLEND_MODE_DISCRETE_CARRY
             */
            val DISCRETE_CARRY: BlendMode get() = BlendMode(2L)
        }
    }

    /**
     * Godot's `AnimationNodeBlendSpace2D.SyncMode` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`AnimationNodeBlendSpace2D.SyncMode.<NAME>`).
     *
     * Generated from Godot docs: AnimationNodeBlendSpace2D.SyncMode
     */
    @JvmInline
    value class SyncMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Inactive animations are frozen and do not advance.
             *
             * Generated from Godot docs: AnimationNodeBlendSpace2D.SYNC_MODE_NONE
             */
            val NONE: SyncMode get() = SyncMode(0L)
            /**
             * Inactive animations advance with a weight of `0`. This is equivalent to the previous `sync =
             * true` behavior.
             *
             * Generated from Godot docs: AnimationNodeBlendSpace2D.SYNC_MODE_INDEPENDENT
             */
            val INDEPENDENT: SyncMode get() = SyncMode(1L)
            /**
             * All animations are time-scaled so they stay in sync, with the cycle length dynamically computed
             * from active blend weights. This is self-normalizing: a solo animation plays at normal speed.
             * Note: If you apply `AnimationNodeTimeSeek` to the result when handling animations of different
             * lengths, synchronization will be broken. In such cases, it is recommended to use
             * `AnimationNodeAnimation.use_custom_timeline` to align the animation lengths.
             *
             * Generated from Godot docs: AnimationNodeBlendSpace2D.SYNC_MODE_CYCLIC_MUTABLE
             */
            val CYCLIC_MUTABLE: SyncMode get() = SyncMode(2L)
            /**
             * All animations are time-scaled so they complete one cycle in `cyclic_length` seconds, keeping
             * them in sync regardless of their individual lengths. Note: If you apply `AnimationNodeTimeSeek`
             * to the result when handling animations of different lengths, synchronization will be broken. In
             * such cases, it is recommended to use `AnimationNodeAnimation.use_custom_timeline` to align the
             * animation lengths.
             *
             * Generated from Godot docs: AnimationNodeBlendSpace2D.SYNC_MODE_CYCLIC_CONSTANT
             */
            val CYCLIC_CONSTANT: SyncMode get() = SyncMode(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AnimationNodeBlendSpace2D? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AnimationNodeBlendSpace2D? =
            if (handle.address() == 0L) null else RefCounted.owned(AnimationNodeBlendSpace2D(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AnimationNodeBlendSpace2D? =
            if (handle.address() == 0L) null else AnimationNodeBlendSpace2D(GodotHandle(handle))
    }

    private object Binds {
        private const val ADD_BLEND_POINT_HASH = 768750458L
        @JvmField
        val addBlendPointBind =
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "add_blend_point", ADD_BLEND_POINT_HASH)

        private const val SET_BLEND_POINT_POSITION_HASH = 163021252L
        @JvmField
        val setBlendPointPositionBind =
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "set_blend_point_position", SET_BLEND_POINT_POSITION_HASH)

        private const val GET_BLEND_POINT_POSITION_HASH = 2299179447L
        @JvmField
        val getBlendPointPositionBind =
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "get_blend_point_position", GET_BLEND_POINT_POSITION_HASH)

        private const val SET_BLEND_POINT_NODE_HASH = 4240341528L
        @JvmField
        val setBlendPointNodeBind =
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "set_blend_point_node", SET_BLEND_POINT_NODE_HASH)

        private const val GET_BLEND_POINT_NODE_HASH = 665599029L
        @JvmField
        val getBlendPointNodeBind =
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "get_blend_point_node", GET_BLEND_POINT_NODE_HASH)

        private const val SET_BLEND_POINT_NAME_HASH = 3780747571L
        @JvmField
        val setBlendPointNameBind =
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "set_blend_point_name", SET_BLEND_POINT_NAME_HASH)

        private const val GET_BLEND_POINT_NAME_HASH = 659327637L
        @JvmField
        val getBlendPointNameBind =
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "get_blend_point_name", GET_BLEND_POINT_NAME_HASH)

        private const val FIND_BLEND_POINT_BY_NAME_HASH = 2458036349L
        @JvmField
        val findBlendPointByNameBind =
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "find_blend_point_by_name", FIND_BLEND_POINT_BY_NAME_HASH)

        private const val REMOVE_BLEND_POINT_HASH = 1286410249L
        @JvmField
        val removeBlendPointBind =
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "remove_blend_point", REMOVE_BLEND_POINT_HASH)

        private const val GET_BLEND_POINT_COUNT_HASH = 3905245786L
        @JvmField
        val getBlendPointCountBind =
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "get_blend_point_count", GET_BLEND_POINT_COUNT_HASH)

        private const val REORDER_BLEND_POINT_HASH = 3937882851L
        @JvmField
        val reorderBlendPointBind =
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "reorder_blend_point", REORDER_BLEND_POINT_HASH)

        private const val ADD_TRIANGLE_HASH = 753017335L
        @JvmField
        val addTriangleBind =
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "add_triangle", ADD_TRIANGLE_HASH)

        private const val GET_TRIANGLE_POINT_HASH = 50157827L
        @JvmField
        val getTrianglePointBind =
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "get_triangle_point", GET_TRIANGLE_POINT_HASH)

        private const val REMOVE_TRIANGLE_HASH = 1286410249L
        @JvmField
        val removeTriangleBind =
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "remove_triangle", REMOVE_TRIANGLE_HASH)

        private const val GET_TRIANGLE_COUNT_HASH = 3905245786L
        @JvmField
        val getTriangleCountBind =
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "get_triangle_count", GET_TRIANGLE_COUNT_HASH)

        private const val SET_MIN_SPACE_HASH = 743155724L
        @JvmField
        val setMinSpaceBind =
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "set_min_space", SET_MIN_SPACE_HASH)

        private const val GET_MIN_SPACE_HASH = 3341600327L
        @JvmField
        val getMinSpaceBind =
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "get_min_space", GET_MIN_SPACE_HASH)

        private const val SET_MAX_SPACE_HASH = 743155724L
        @JvmField
        val setMaxSpaceBind =
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "set_max_space", SET_MAX_SPACE_HASH)

        private const val GET_MAX_SPACE_HASH = 3341600327L
        @JvmField
        val getMaxSpaceBind =
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "get_max_space", GET_MAX_SPACE_HASH)

        private const val SET_SNAP_HASH = 743155724L
        @JvmField
        val setSnapBind =
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "set_snap", SET_SNAP_HASH)

        private const val GET_SNAP_HASH = 3341600327L
        @JvmField
        val getSnapBind =
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "get_snap", GET_SNAP_HASH)

        private const val SET_X_LABEL_HASH = 83702148L
        @JvmField
        val setXLabelBind =
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "set_x_label", SET_X_LABEL_HASH)

        private const val GET_X_LABEL_HASH = 201670096L
        @JvmField
        val getXLabelBind =
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "get_x_label", GET_X_LABEL_HASH)

        private const val SET_Y_LABEL_HASH = 83702148L
        @JvmField
        val setYLabelBind =
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "set_y_label", SET_Y_LABEL_HASH)

        private const val GET_Y_LABEL_HASH = 201670096L
        @JvmField
        val getYLabelBind =
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "get_y_label", GET_Y_LABEL_HASH)

        private const val SET_AUTO_TRIANGLES_HASH = 2586408642L
        @JvmField
        val setAutoTrianglesBind =
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "set_auto_triangles", SET_AUTO_TRIANGLES_HASH)

        private const val GET_AUTO_TRIANGLES_HASH = 36873697L
        @JvmField
        val getAutoTrianglesBind =
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "get_auto_triangles", GET_AUTO_TRIANGLES_HASH)

        private const val SET_BLEND_MODE_HASH = 81193520L
        @JvmField
        val setBlendModeBind =
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "set_blend_mode", SET_BLEND_MODE_HASH)

        private const val GET_BLEND_MODE_HASH = 1398433632L
        @JvmField
        val getBlendModeBind =
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "get_blend_mode", GET_BLEND_MODE_HASH)

        private const val SET_USE_SYNC_HASH = 2586408642L
        @JvmField
        val setUseSyncBind =
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "set_use_sync", SET_USE_SYNC_HASH)

        private const val IS_USING_SYNC_HASH = 36873697L
        @JvmField
        val isUsingSyncBind =
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "is_using_sync", IS_USING_SYNC_HASH)

        private const val SET_SYNC_MODE_HASH = 2615784488L
        @JvmField
        val setSyncModeBind =
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "set_sync_mode", SET_SYNC_MODE_HASH)

        private const val GET_SYNC_MODE_HASH = 242032665L
        @JvmField
        val getSyncModeBind =
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "get_sync_mode", GET_SYNC_MODE_HASH)

        private const val SET_CYCLIC_LENGTH_HASH = 373806689L
        @JvmField
        val setCyclicLengthBind =
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "set_cyclic_length", SET_CYCLIC_LENGTH_HASH)

        private const val GET_CYCLIC_LENGTH_HASH = 1740695150L
        @JvmField
        val getCyclicLengthBind =
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace2D", "get_cyclic_length", GET_CYCLIC_LENGTH_HASH)
    }
}
