package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A set of `AnimationRootNode`s placed on a virtual axis, crossfading between the two adjacent
 * ones. Used by `AnimationTree`.
 *
 * Generated from Godot docs: AnimationNodeBlendSpace1D
 */
class AnimationNodeBlendSpace1D(handle: GodotHandle) : AnimationRootNode(handle) {
    var minSpace: Double
        @JvmName("minSpaceProperty")
        get() = getMinSpace()
        @JvmName("setMinSpaceProperty")
        set(value) = setMinSpace(value)

    var maxSpace: Double
        @JvmName("maxSpaceProperty")
        get() = getMaxSpace()
        @JvmName("setMaxSpaceProperty")
        set(value) = setMaxSpace(value)

    var snap: Double
        @JvmName("snapProperty")
        get() = getSnap()
        @JvmName("setSnapProperty")
        set(value) = setSnap(value)

    var valueLabel: String
        @JvmName("valueLabelProperty")
        get() = getValueLabel()
        @JvmName("setValueLabelProperty")
        set(value) = setValueLabel(value)

    var blendMode: AnimationNodeBlendSpace1D.BlendMode
        @JvmName("blendModeProperty")
        get() = getBlendMode()
        @JvmName("setBlendModeProperty")
        set(value) = setBlendMode(value)

    var sync: Boolean
        @JvmName("syncProperty")
        get() = isUsingSync()
        @JvmName("setSyncProperty")
        set(value) = setUseSync(value)

    var syncMode: AnimationNodeBlendSpace1D.SyncMode
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
     * Adds a new point with `name` that represents a `node` on the virtual axis at a given position
     * set by `pos`. You can insert it at a specific index using the `at_index` argument. If you use
     * the default value for `at_index`, the point is inserted at the end of the blend points array.
     * Note: If no name is provided, safe index is used as reference. In the future, empty names will
     * be deprecated, so explicitly passing a name is recommended.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace1D.add_blend_point
     */
    fun addBlendPoint(node: AnimationRootNode?, pos: Double, atIndex: Int = -1, name: String = "") {
        checkOpen()
        ObjectCalls.ptrcallWithObjectDoubleIntStringNameArgs(addBlendPointBind, segment, node?.requireOpenHandle() ?: NULL_SEGMENT, pos, atIndex, name)
    }

    /**
     * Updates the position of the point at index `point` on the blend axis.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace1D.set_blend_point_position
     */
    fun setBlendPointPosition(point: Int, pos: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndDoubleArg(setBlendPointPositionBind, segment, point, pos)
    }

    /**
     * Returns the position of the point at index `point`.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace1D.get_blend_point_position
     */
    fun getBlendPointPosition(point: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDouble(getBlendPointPositionBind, segment, point)
    }

    /**
     * Changes the `AnimationNode` referenced by the point at index `point`.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace1D.set_blend_point_node
     */
    fun setBlendPointNode(point: Int, node: AnimationRootNode?) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndObjectArg(setBlendPointNodeBind, segment, point, node?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Returns the `AnimationNode` referenced by the point at index `point`.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace1D.get_blend_point_node
     */
    fun getBlendPointNode(point: Int): AnimationRootNode? {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetObject(getBlendPointNodeBind, segment, point)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return AnimationRootNode.wrap(ret)
    }

    /**
     * Sets the name of the blend point at index `point`. If the name conflicts with an existing point,
     * a unique name will be generated automatically.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace1D.set_blend_point_name
     */
    fun setBlendPointName(point: Int, name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndStringNameArg(setBlendPointNameBind, segment, point, name)
    }

    /**
     * Returns the name of the blend point at index `point`.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace1D.get_blend_point_name
     */
    fun getBlendPointName(point: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetStringName(getBlendPointNameBind, segment, point)
    }

    /**
     * Returns the index of the blend point with the given `name`. Returns `-1` if no blend point with
     * that name is found.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace1D.find_blend_point_by_name
     */
    fun findBlendPointByName(name: String): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameArgRetInt(findBlendPointByNameBind, segment, name)
    }

    /**
     * Removes the point at index `point` from the blend axis.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace1D.remove_blend_point
     */
    fun removeBlendPoint(point: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(removeBlendPointBind, segment, point)
    }

    /**
     * Returns the number of points on the blend axis.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace1D.get_blend_point_count
     */
    fun getBlendPointCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getBlendPointCountBind, segment)
    }

    /**
     * Swaps the blend points at indices `from_index` and `to_index`, exchanging their positions and
     * properties.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace1D.reorder_blend_point
     */
    fun reorderBlendPoint(fromIndex: Int, toIndex: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntArgs(reorderBlendPointBind, segment, fromIndex, toIndex)
    }

    /**
     * The blend space's axis's lower limit for the points' position. See `add_blend_point`.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace1D.set_min_space
     */
    fun setMinSpace(minSpace: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setMinSpaceBind, segment, minSpace)
    }

    /**
     * The blend space's axis's lower limit for the points' position. See `add_blend_point`.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace1D.get_min_space
     */
    fun getMinSpace(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getMinSpaceBind, segment)
    }

    /**
     * The blend space's axis's upper limit for the points' position. See `add_blend_point`.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace1D.set_max_space
     */
    fun setMaxSpace(maxSpace: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setMaxSpaceBind, segment, maxSpace)
    }

    /**
     * The blend space's axis's upper limit for the points' position. See `add_blend_point`.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace1D.get_max_space
     */
    fun getMaxSpace(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getMaxSpaceBind, segment)
    }

    /**
     * Position increment to snap to when moving a point on the axis.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace1D.set_snap
     */
    fun setSnap(snap: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setSnapBind, segment, snap)
    }

    /**
     * Position increment to snap to when moving a point on the axis.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace1D.get_snap
     */
    fun getSnap(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getSnapBind, segment)
    }

    /**
     * Label of the virtual axis of the blend space.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace1D.set_value_label
     */
    fun setValueLabel(text: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setValueLabelBind, segment, text)
    }

    /**
     * Label of the virtual axis of the blend space.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace1D.get_value_label
     */
    fun getValueLabel(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getValueLabelBind, segment)
    }

    /**
     * Controls the interpolation between animations.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace1D.set_blend_mode
     */
    fun setBlendMode(mode: AnimationNodeBlendSpace1D.BlendMode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setBlendModeBind, segment, mode.value)
    }

    /**
     * Controls the interpolation between animations.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace1D.get_blend_mode
     */
    fun getBlendMode(): AnimationNodeBlendSpace1D.BlendMode {
        checkOpen()
        return AnimationNodeBlendSpace1D.BlendMode(ObjectCalls.ptrcallNoArgsRetLong(getBlendModeBind, segment))
    }

    /**
     * If `true`, sync mode is enabled (equivalent to `SyncMode.INDEPENDENT`). This property is kept
     * for backward compatibility.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace1D.set_use_sync
     */
    fun setUseSync(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setUseSyncBind, segment, enable)
    }

    /**
     * If `true`, sync mode is enabled (equivalent to `SyncMode.INDEPENDENT`). This property is kept
     * for backward compatibility.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace1D.is_using_sync
     */
    fun isUsingSync(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingSyncBind, segment)
    }

    /**
     * Controls how animations are synced when blended. See `SyncMode` for available options.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace1D.set_sync_mode
     */
    fun setSyncMode(syncMode: AnimationNodeBlendSpace1D.SyncMode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setSyncModeBind, segment, syncMode.value)
    }

    /**
     * Controls how animations are synced when blended. See `SyncMode` for available options.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace1D.get_sync_mode
     */
    fun getSyncMode(): AnimationNodeBlendSpace1D.SyncMode {
        checkOpen()
        return AnimationNodeBlendSpace1D.SyncMode(ObjectCalls.ptrcallNoArgsRetLong(getSyncModeBind, segment))
    }

    /**
     * The cycle length in seconds used by `SyncMode.CYCLIC_CONSTANT`. All animations are time-scaled
     * so they complete one full cycle in this duration. Must be greater than `0` for cyclic sync to
     * take effect.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace1D.set_cyclic_length
     */
    fun setCyclicLength(length: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setCyclicLengthBind, segment, length)
    }

    /**
     * The cycle length in seconds used by `SyncMode.CYCLIC_CONSTANT`. All animations are time-scaled
     * so they complete one full cycle in this duration. Must be greater than `0` for cyclic sync to
     * take effect.
     *
     * Generated from Godot docs: AnimationNodeBlendSpace1D.get_cyclic_length
     */
    fun getCyclicLength(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getCyclicLengthBind, segment)
    }

    /**
     * Godot's `AnimationNodeBlendSpace1D.BlendMode` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`AnimationNodeBlendSpace1D.BlendMode.<NAME>`).
     *
     * Generated from Godot docs: AnimationNodeBlendSpace1D.BlendMode
     */
    @JvmInline
    value class BlendMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The interpolation between animations is linear.
             *
             * Generated from Godot docs: AnimationNodeBlendSpace1D.BLEND_MODE_INTERPOLATED
             */
            val INTERPOLATED: BlendMode get() = BlendMode(0L)
            /**
             * The blend space plays the animation of the animation node which blending position is closest to.
             * Useful for frame-by-frame 2D animations.
             *
             * Generated from Godot docs: AnimationNodeBlendSpace1D.BLEND_MODE_DISCRETE
             */
            val DISCRETE: BlendMode get() = BlendMode(1L)
            /**
             * Similar to `BlendMode.DISCRETE`, but starts the new animation at the last animation's playback
             * position.
             *
             * Generated from Godot docs: AnimationNodeBlendSpace1D.BLEND_MODE_DISCRETE_CARRY
             */
            val DISCRETE_CARRY: BlendMode get() = BlendMode(2L)
        }
    }

    /**
     * Godot's `AnimationNodeBlendSpace1D.SyncMode` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`AnimationNodeBlendSpace1D.SyncMode.<NAME>`).
     *
     * Generated from Godot docs: AnimationNodeBlendSpace1D.SyncMode
     */
    @JvmInline
    value class SyncMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Inactive animations are frozen and do not advance.
             *
             * Generated from Godot docs: AnimationNodeBlendSpace1D.SYNC_MODE_NONE
             */
            val NONE: SyncMode get() = SyncMode(0L)
            /**
             * Inactive animations advance with a weight of `0`. This is equivalent to the previous `sync =
             * true` behavior.
             *
             * Generated from Godot docs: AnimationNodeBlendSpace1D.SYNC_MODE_INDEPENDENT
             */
            val INDEPENDENT: SyncMode get() = SyncMode(1L)
            /**
             * All animations are time-scaled so they stay in sync, with the cycle length dynamically computed
             * from active blend weights. This is self-normalizing: a solo animation plays at normal speed.
             * Note: If you apply `AnimationNodeTimeSeek` to the result when handling animations of different
             * lengths, synchronization will be broken. In such cases, it is recommended to use
             * `AnimationNodeAnimation.use_custom_timeline` to align the animation lengths.
             *
             * Generated from Godot docs: AnimationNodeBlendSpace1D.SYNC_MODE_CYCLIC_MUTABLE
             */
            val CYCLIC_MUTABLE: SyncMode get() = SyncMode(2L)
            /**
             * All animations are time-scaled so they complete one cycle in `cyclic_length` seconds, keeping
             * them in sync regardless of their individual lengths. Note: If you apply `AnimationNodeTimeSeek`
             * to the result when handling animations of different lengths, synchronization will be broken. In
             * such cases, it is recommended to use `AnimationNodeAnimation.use_custom_timeline` to align the
             * animation lengths.
             *
             * Generated from Godot docs: AnimationNodeBlendSpace1D.SYNC_MODE_CYCLIC_CONSTANT
             */
            val CYCLIC_CONSTANT: SyncMode get() = SyncMode(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AnimationNodeBlendSpace1D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AnimationNodeBlendSpace1D? =
            if (handle.address() == 0L) null else AnimationNodeBlendSpace1D(GodotHandle(handle))

        private const val ADD_BLEND_POINT_HASH = 398361042L
        private val addBlendPointBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace1D", "add_blend_point", ADD_BLEND_POINT_HASH)
        }

        private const val SET_BLEND_POINT_POSITION_HASH = 1602489585L
        private val setBlendPointPositionBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace1D", "set_blend_point_position", SET_BLEND_POINT_POSITION_HASH)
        }

        private const val GET_BLEND_POINT_POSITION_HASH = 2339986948L
        private val getBlendPointPositionBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace1D", "get_blend_point_position", GET_BLEND_POINT_POSITION_HASH)
        }

        private const val SET_BLEND_POINT_NODE_HASH = 4240341528L
        private val setBlendPointNodeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace1D", "set_blend_point_node", SET_BLEND_POINT_NODE_HASH)
        }

        private const val GET_BLEND_POINT_NODE_HASH = 665599029L
        private val getBlendPointNodeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace1D", "get_blend_point_node", GET_BLEND_POINT_NODE_HASH)
        }

        private const val SET_BLEND_POINT_NAME_HASH = 3780747571L
        private val setBlendPointNameBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace1D", "set_blend_point_name", SET_BLEND_POINT_NAME_HASH)
        }

        private const val GET_BLEND_POINT_NAME_HASH = 659327637L
        private val getBlendPointNameBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace1D", "get_blend_point_name", GET_BLEND_POINT_NAME_HASH)
        }

        private const val FIND_BLEND_POINT_BY_NAME_HASH = 2458036349L
        private val findBlendPointByNameBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace1D", "find_blend_point_by_name", FIND_BLEND_POINT_BY_NAME_HASH)
        }

        private const val REMOVE_BLEND_POINT_HASH = 1286410249L
        private val removeBlendPointBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace1D", "remove_blend_point", REMOVE_BLEND_POINT_HASH)
        }

        private const val GET_BLEND_POINT_COUNT_HASH = 3905245786L
        private val getBlendPointCountBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace1D", "get_blend_point_count", GET_BLEND_POINT_COUNT_HASH)
        }

        private const val REORDER_BLEND_POINT_HASH = 3937882851L
        private val reorderBlendPointBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace1D", "reorder_blend_point", REORDER_BLEND_POINT_HASH)
        }

        private const val SET_MIN_SPACE_HASH = 373806689L
        private val setMinSpaceBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace1D", "set_min_space", SET_MIN_SPACE_HASH)
        }

        private const val GET_MIN_SPACE_HASH = 1740695150L
        private val getMinSpaceBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace1D", "get_min_space", GET_MIN_SPACE_HASH)
        }

        private const val SET_MAX_SPACE_HASH = 373806689L
        private val setMaxSpaceBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace1D", "set_max_space", SET_MAX_SPACE_HASH)
        }

        private const val GET_MAX_SPACE_HASH = 1740695150L
        private val getMaxSpaceBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace1D", "get_max_space", GET_MAX_SPACE_HASH)
        }

        private const val SET_SNAP_HASH = 373806689L
        private val setSnapBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace1D", "set_snap", SET_SNAP_HASH)
        }

        private const val GET_SNAP_HASH = 1740695150L
        private val getSnapBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace1D", "get_snap", GET_SNAP_HASH)
        }

        private const val SET_VALUE_LABEL_HASH = 83702148L
        private val setValueLabelBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace1D", "set_value_label", SET_VALUE_LABEL_HASH)
        }

        private const val GET_VALUE_LABEL_HASH = 201670096L
        private val getValueLabelBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace1D", "get_value_label", GET_VALUE_LABEL_HASH)
        }

        private const val SET_BLEND_MODE_HASH = 2600869457L
        private val setBlendModeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace1D", "set_blend_mode", SET_BLEND_MODE_HASH)
        }

        private const val GET_BLEND_MODE_HASH = 1547667849L
        private val getBlendModeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace1D", "get_blend_mode", GET_BLEND_MODE_HASH)
        }

        private const val SET_USE_SYNC_HASH = 2586408642L
        private val setUseSyncBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace1D", "set_use_sync", SET_USE_SYNC_HASH)
        }

        private const val IS_USING_SYNC_HASH = 36873697L
        private val isUsingSyncBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace1D", "is_using_sync", IS_USING_SYNC_HASH)
        }

        private const val SET_SYNC_MODE_HASH = 1065895142L
        private val setSyncModeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace1D", "set_sync_mode", SET_SYNC_MODE_HASH)
        }

        private const val GET_SYNC_MODE_HASH = 132474921L
        private val getSyncModeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace1D", "get_sync_mode", GET_SYNC_MODE_HASH)
        }

        private const val SET_CYCLIC_LENGTH_HASH = 373806689L
        private val setCyclicLengthBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace1D", "set_cyclic_length", SET_CYCLIC_LENGTH_HASH)
        }

        private const val GET_CYCLIC_LENGTH_HASH = 1740695150L
        private val getCyclicLengthBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendSpace1D", "get_cyclic_length", GET_CYCLIC_LENGTH_HASH)
        }
    }
}
