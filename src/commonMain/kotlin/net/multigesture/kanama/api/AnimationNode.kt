package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.NodePath

/**
 * Base class for `AnimationTree` nodes. Not related to scene nodes.
 *
 * Generated from Godot docs: AnimationNode
 */
open class AnimationNode(handle: GodotHandle) : Resource(handle) {
    var filterEnabled: Boolean
        @JvmName("filterEnabledProperty")
        get() = isFilterEnabled()
        @JvmName("setFilterEnabledProperty")
        set(value) = setFilterEnabled(value)

    /**
     * Adds an input to the animation node. This is only useful for animation nodes created for use in
     * an `AnimationNodeBlendTree`. If the addition fails, returns `false`.
     *
     * Generated from Godot docs: AnimationNode.add_input
     */
    fun addInput(name: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetBool(Binds.addInputBind, segment, name)
    }

    /**
     * Removes an input, call this only when inactive.
     *
     * Generated from Godot docs: AnimationNode.remove_input
     */
    fun removeInput(index: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.removeInputBind, segment, index)
    }

    /**
     * Sets the name of the input at the given `input` index. If the setting fails, returns `false`.
     *
     * Generated from Godot docs: AnimationNode.set_input_name
     */
    fun setInputName(input: Int, name: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithIntAndStringArgRetBool(Binds.setInputNameBind, segment, input, name)
    }

    /**
     * Gets the name of an input by index.
     *
     * Generated from Godot docs: AnimationNode.get_input_name
     */
    fun getInputName(input: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetString(Binds.getInputNameBind, segment, input)
    }

    /**
     * Amount of inputs in this animation node, only useful for animation nodes that go into
     * `AnimationNodeBlendTree`.
     *
     * Generated from Godot docs: AnimationNode.get_input_count
     */
    fun getInputCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getInputCountBind, segment)
    }

    /**
     * Returns the input index which corresponds to `name`. If not found, returns `-1`.
     *
     * Generated from Godot docs: AnimationNode.find_input
     */
    fun findInput(name: String): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetInt(Binds.findInputBind, segment, name)
    }

    /**
     * Adds or removes a path for the filter.
     *
     * Generated from Godot docs: AnimationNode.set_filter_path
     */
    fun setFilterPath(path: NodePath, enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithNodePathAndBoolArgs(Binds.setFilterPathBind, segment, path, enable)
    }

    /**
     * Returns `true` if the given path is filtered.
     *
     * Generated from Godot docs: AnimationNode.is_path_filtered
     */
    fun isPathFiltered(path: NodePath): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithNodePathArgRetBool(Binds.isPathFilteredBind, segment, path)
    }

    /**
     * If `true`, filtering is enabled.
     *
     * Generated from Godot docs: AnimationNode.set_filter_enabled
     */
    fun setFilterEnabled(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setFilterEnabledBind, segment, enable)
    }

    /**
     * If `true`, filtering is enabled.
     *
     * Generated from Godot docs: AnimationNode.is_filter_enabled
     */
    fun isFilterEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isFilterEnabledBind, segment)
    }

    /**
     * Returns the object id of the `AnimationTree` that owns this node. Note: This method should only
     * be called from within the `AnimationNodeExtension._process_animation_node` method, and will
     * return an invalid id otherwise.
     *
     * Generated from Godot docs: AnimationNode.get_processing_animation_tree_instance_id
     */
    fun getProcessingAnimationTreeInstanceId(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(Binds.getProcessingAnimationTreeInstanceIdBind, segment)
    }

    /**
     * Returns `true` if this animation node is being processed in test-only mode.
     *
     * Generated from Godot docs: AnimationNode.is_process_testing
     */
    fun isProcessTesting(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isProcessTestingBind, segment)
    }

    /**
     * Blends an animation by `blend` amount (name must be valid in the linked `AnimationPlayer`). A
     * `time` and `delta` may be passed, as well as whether `seeked` happened. A `looped_flag` is used
     * by internal processing immediately after the loop.
     *
     * Generated from Godot docs: AnimationNode.blend_animation
     */
    fun blendAnimation(animation: String, time: Double, delta: Double, seeked: Boolean, isExternalSeeking: Boolean, blend: Double, loopedFlag: Animation.LoopedFlag = Animation.LoopedFlag.NONE) {
        checkOpen()
        ObjectCalls.ptrcallWithStringNameTwoDoubleTwoBoolDoubleLongArgs(Binds.blendAnimationBind, segment, animation, time, delta, seeked, isExternalSeeking, blend, loopedFlag.value)
    }

    /**
     * Blend another animation node (in case this animation node contains child animation nodes). This
     * function is only useful if you inherit from `AnimationRootNode` instead, otherwise editors will
     * not display your animation node for addition.
     *
     * Generated from Godot docs: AnimationNode.blend_node
     */
    fun blendNode(name: String, node: AnimationNode?, time: Double, seek: Boolean, isExternalSeeking: Boolean, blend: Double, filter: AnimationNode.FilterAction = AnimationNode.FilterAction.IGNORE, sync: Boolean = true, testOnly: Boolean = false): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameObjectDoubleTwoBoolDoubleLongTwoBoolArgsRetDouble(Binds.blendNodeBind, segment, name, node?.requireOpenHandle() ?: NULL_SEGMENT, time, seek, isExternalSeeking, blend, filter.value, sync, testOnly)
    }

    /**
     * Blends an input. This is only useful for animation nodes created for an
     * `AnimationNodeBlendTree`. The `time` parameter is a relative delta, unless `seek` is `true`, in
     * which case it is absolute. A filter mode may be optionally passed.
     *
     * Generated from Godot docs: AnimationNode.blend_input
     */
    fun blendInput(inputIndex: Int, time: Double, seek: Boolean, isExternalSeeking: Boolean, blend: Double, filter: AnimationNode.FilterAction = AnimationNode.FilterAction.IGNORE, sync: Boolean = true, testOnly: Boolean = false): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithIntDoubleTwoBoolDoubleLongTwoBoolArgsRetDouble(Binds.blendInputBind, segment, inputIndex, time, seek, isExternalSeeking, blend, filter.value, sync, testOnly)
    }

    /**
     * Sets a custom parameter. These are used as local memory, because resources can be reused across
     * the tree or scenes.
     *
     * Generated from Godot docs: AnimationNode.set_parameter
     */
    fun setParameter(name: String, value: Any?) {
        checkOpen()
        ObjectCalls.ptrcallWithStringNameAndVariantArg(Binds.setParameterBind, segment, name, value)
    }

    /**
     * Gets the value of a parameter. Parameters are custom local memory used for your animation nodes,
     * given a resource can be reused in multiple trees.
     *
     * Generated from Godot docs: AnimationNode.get_parameter
     */
    fun getParameter(name: String): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(Binds.getParameterBind, segment, name)
    }

    /** Signal `tree_changed()`; see [TypedSignal]. */
    val treeChanged: Signal0
        @JvmName("treeChangedTypedSignal")
        get() = Signal0(this, "tree_changed")

    /** Signal `node_updated(object_id: int)`; see [TypedSignal]. */
    val nodeUpdated: Signal1<Long>
        @JvmName("nodeUpdatedTypedSignal")
        get() = Signal1(this, "node_updated", SignalArgType.LONG)

    /** Signal `animation_node_renamed(object_id: int, old_name: String, new_name: String)`; see [TypedSignal]. */
    val animationNodeRenamed: Signal3<Long, String, String>
        @JvmName("animationNodeRenamedTypedSignal")
        get() = Signal3(this, "animation_node_renamed", SignalArgType.LONG, SignalArgType.STRING, SignalArgType.STRING)

    /** Signal `animation_node_removed(object_id: int, node_name: String)`; see [TypedSignal]. */
    val animationNodeRemoved: Signal2<Long, String>
        @JvmName("animationNodeRemovedTypedSignal")
        get() = Signal2(this, "animation_node_removed", SignalArgType.LONG, SignalArgType.STRING)

    object Signals {
        const val treeChanged: String = "tree_changed"
        const val nodeUpdated: String = "node_updated"
        const val animationNodeRenamed: String = "animation_node_renamed"
        const val animationNodeRemoved: String = "animation_node_removed"
    }

    /**
     * Godot's `AnimationNode.FilterAction` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`AnimationNode.FilterAction.<NAME>`).
     *
     * Generated from Godot docs: AnimationNode.FilterAction
     */
    @JvmInline
    value class FilterAction(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Do not use filtering.
             *
             * Generated from Godot docs: AnimationNode.FILTER_IGNORE
             */
            val IGNORE: FilterAction get() = FilterAction(0L)
            /**
             * Paths matching the filter will be allowed to pass.
             *
             * Generated from Godot docs: AnimationNode.FILTER_PASS
             */
            val PASS: FilterAction get() = FilterAction(1L)
            /**
             * Paths matching the filter will be discarded.
             *
             * Generated from Godot docs: AnimationNode.FILTER_STOP
             */
            val STOP: FilterAction get() = FilterAction(2L)
            /**
             * Paths matching the filter will be blended (by the blend value).
             *
             * Generated from Godot docs: AnimationNode.FILTER_BLEND
             */
            val BLEND: FilterAction get() = FilterAction(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AnimationNode? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AnimationNode? =
            if (handle.address() == 0L) null else RefCounted.owned(AnimationNode(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AnimationNode? =
            if (handle.address() == 0L) null else AnimationNode(GodotHandle(handle))
    }

    private object Binds {
        private const val ADD_INPUT_HASH = 2323990056L
        @JvmField
        val addInputBind =
            ObjectCalls.getMethodBind("AnimationNode", "add_input", ADD_INPUT_HASH)

        private const val REMOVE_INPUT_HASH = 1286410249L
        @JvmField
        val removeInputBind =
            ObjectCalls.getMethodBind("AnimationNode", "remove_input", REMOVE_INPUT_HASH)

        private const val SET_INPUT_NAME_HASH = 215573526L
        @JvmField
        val setInputNameBind =
            ObjectCalls.getMethodBind("AnimationNode", "set_input_name", SET_INPUT_NAME_HASH)

        private const val GET_INPUT_NAME_HASH = 844755477L
        @JvmField
        val getInputNameBind =
            ObjectCalls.getMethodBind("AnimationNode", "get_input_name", GET_INPUT_NAME_HASH)

        private const val GET_INPUT_COUNT_HASH = 3905245786L
        @JvmField
        val getInputCountBind =
            ObjectCalls.getMethodBind("AnimationNode", "get_input_count", GET_INPUT_COUNT_HASH)

        private const val FIND_INPUT_HASH = 1321353865L
        @JvmField
        val findInputBind =
            ObjectCalls.getMethodBind("AnimationNode", "find_input", FIND_INPUT_HASH)

        private const val SET_FILTER_PATH_HASH = 3868023870L
        @JvmField
        val setFilterPathBind =
            ObjectCalls.getMethodBind("AnimationNode", "set_filter_path", SET_FILTER_PATH_HASH)

        private const val IS_PATH_FILTERED_HASH = 861721659L
        @JvmField
        val isPathFilteredBind =
            ObjectCalls.getMethodBind("AnimationNode", "is_path_filtered", IS_PATH_FILTERED_HASH)

        private const val SET_FILTER_ENABLED_HASH = 2586408642L
        @JvmField
        val setFilterEnabledBind =
            ObjectCalls.getMethodBind("AnimationNode", "set_filter_enabled", SET_FILTER_ENABLED_HASH)

        private const val IS_FILTER_ENABLED_HASH = 36873697L
        @JvmField
        val isFilterEnabledBind =
            ObjectCalls.getMethodBind("AnimationNode", "is_filter_enabled", IS_FILTER_ENABLED_HASH)

        private const val GET_PROCESSING_ANIMATION_TREE_INSTANCE_ID_HASH = 3905245786L
        @JvmField
        val getProcessingAnimationTreeInstanceIdBind =
            ObjectCalls.getMethodBind("AnimationNode", "get_processing_animation_tree_instance_id", GET_PROCESSING_ANIMATION_TREE_INSTANCE_ID_HASH)

        private const val IS_PROCESS_TESTING_HASH = 36873697L
        @JvmField
        val isProcessTestingBind =
            ObjectCalls.getMethodBind("AnimationNode", "is_process_testing", IS_PROCESS_TESTING_HASH)

        private const val BLEND_ANIMATION_HASH = 1630801826L
        @JvmField
        val blendAnimationBind =
            ObjectCalls.getMethodBind("AnimationNode", "blend_animation", BLEND_ANIMATION_HASH)

        private const val BLEND_NODE_HASH = 1746075988L
        @JvmField
        val blendNodeBind =
            ObjectCalls.getMethodBind("AnimationNode", "blend_node", BLEND_NODE_HASH)

        private const val BLEND_INPUT_HASH = 1361527350L
        @JvmField
        val blendInputBind =
            ObjectCalls.getMethodBind("AnimationNode", "blend_input", BLEND_INPUT_HASH)

        private const val SET_PARAMETER_HASH = 3776071444L
        @JvmField
        val setParameterBind =
            ObjectCalls.getMethodBind("AnimationNode", "set_parameter", SET_PARAMETER_HASH)

        private const val GET_PARAMETER_HASH = 2760726917L
        @JvmField
        val getParameterBind =
            ObjectCalls.getMethodBind("AnimationNode", "get_parameter", GET_PARAMETER_HASH)
    }
}
