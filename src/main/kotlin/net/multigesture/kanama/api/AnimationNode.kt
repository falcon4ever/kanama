package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath

/**
 * Base class for `AnimationTree` nodes. Not related to scene nodes.
 *
 * Generated from Godot docs: AnimationNode
 */
open class AnimationNode(handle: MemorySegment) : Resource(handle) {
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
        return ObjectCalls.ptrcallWithStringArgRetBool(addInputBind, handle, name)
    }

    /**
     * Removes an input, call this only when inactive.
     *
     * Generated from Godot docs: AnimationNode.remove_input
     */
    fun removeInput(index: Int) {
        ObjectCalls.ptrcallWithIntArg(removeInputBind, handle, index)
    }

    /**
     * Sets the name of the input at the given `input` index. If the setting fails, returns `false`.
     *
     * Generated from Godot docs: AnimationNode.set_input_name
     */
    fun setInputName(input: Int, name: String): Boolean {
        return ObjectCalls.ptrcallWithIntAndStringArgRetBool(setInputNameBind, handle, input, name)
    }

    /**
     * Gets the name of an input by index.
     *
     * Generated from Godot docs: AnimationNode.get_input_name
     */
    fun getInputName(input: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getInputNameBind, handle, input)
    }

    /**
     * Amount of inputs in this animation node, only useful for animation nodes that go into
     * `AnimationNodeBlendTree`.
     *
     * Generated from Godot docs: AnimationNode.get_input_count
     */
    fun getInputCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getInputCountBind, handle)
    }

    /**
     * Returns the input index which corresponds to `name`. If not found, returns `-1`.
     *
     * Generated from Godot docs: AnimationNode.find_input
     */
    fun findInput(name: String): Int {
        return ObjectCalls.ptrcallWithStringArgRetInt(findInputBind, handle, name)
    }

    /**
     * Adds or removes a path for the filter.
     *
     * Generated from Godot docs: AnimationNode.set_filter_path
     */
    fun setFilterPath(path: NodePath, enable: Boolean) {
        ObjectCalls.ptrcallWithNodePathAndBoolArgs(setFilterPathBind, handle, path, enable)
    }

    /**
     * Returns `true` if the given path is filtered.
     *
     * Generated from Godot docs: AnimationNode.is_path_filtered
     */
    fun isPathFiltered(path: NodePath): Boolean {
        return ObjectCalls.ptrcallWithNodePathArgRetBool(isPathFilteredBind, handle, path)
    }

    /**
     * If `true`, filtering is enabled.
     *
     * Generated from Godot docs: AnimationNode.set_filter_enabled
     */
    fun setFilterEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFilterEnabledBind, handle, enable)
    }

    /**
     * If `true`, filtering is enabled.
     *
     * Generated from Godot docs: AnimationNode.is_filter_enabled
     */
    fun isFilterEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFilterEnabledBind, handle)
    }

    /**
     * Returns the object id of the `AnimationTree` that owns this node. Note: This method should only
     * be called from within the `AnimationNodeExtension._process_animation_node` method, and will
     * return an invalid id otherwise.
     *
     * Generated from Godot docs: AnimationNode.get_processing_animation_tree_instance_id
     */
    fun getProcessingAnimationTreeInstanceId(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getProcessingAnimationTreeInstanceIdBind, handle)
    }

    /**
     * Returns `true` if this animation node is being processed in test-only mode.
     *
     * Generated from Godot docs: AnimationNode.is_process_testing
     */
    fun isProcessTesting(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isProcessTestingBind, handle)
    }

    /**
     * Blends an animation by `blend` amount (name must be valid in the linked `AnimationPlayer`). A
     * `time` and `delta` may be passed, as well as whether `seeked` happened. A `looped_flag` is used
     * by internal processing immediately after the loop.
     *
     * Generated from Godot docs: AnimationNode.blend_animation
     */
    fun blendAnimation(animation: String, time: Double, delta: Double, seeked: Boolean, isExternalSeeking: Boolean, blend: Double, loopedFlag: Long = 0L) {
        ObjectCalls.ptrcallWithStringNameTwoDoubleTwoBoolDoubleLongArgs(blendAnimationBind, handle, animation, time, delta, seeked, isExternalSeeking, blend, loopedFlag)
    }

    /**
     * Blend another animation node (in case this animation node contains child animation nodes). This
     * function is only useful if you inherit from `AnimationRootNode` instead, otherwise editors will
     * not display your animation node for addition.
     *
     * Generated from Godot docs: AnimationNode.blend_node
     */
    fun blendNode(name: String, node: AnimationNode?, time: Double, seek: Boolean, isExternalSeeking: Boolean, blend: Double, filter: Long = 0L, sync: Boolean = true, testOnly: Boolean = false): Double {
        return ObjectCalls.ptrcallWithStringNameObjectDoubleTwoBoolDoubleLongTwoBoolArgsRetDouble(blendNodeBind, handle, name, node?.requireOpenHandle() ?: MemorySegment.NULL, time, seek, isExternalSeeking, blend, filter, sync, testOnly)
    }

    /**
     * Blends an input. This is only useful for animation nodes created for an
     * `AnimationNodeBlendTree`. The `time` parameter is a relative delta, unless `seek` is `true`, in
     * which case it is absolute. A filter mode may be optionally passed.
     *
     * Generated from Godot docs: AnimationNode.blend_input
     */
    fun blendInput(inputIndex: Int, time: Double, seek: Boolean, isExternalSeeking: Boolean, blend: Double, filter: Long = 0L, sync: Boolean = true, testOnly: Boolean = false): Double {
        return ObjectCalls.ptrcallWithIntDoubleTwoBoolDoubleLongTwoBoolArgsRetDouble(blendInputBind, handle, inputIndex, time, seek, isExternalSeeking, blend, filter, sync, testOnly)
    }

    /**
     * Sets a custom parameter. These are used as local memory, because resources can be reused across
     * the tree or scenes.
     *
     * Generated from Godot docs: AnimationNode.set_parameter
     */
    fun setParameter(name: String, value: Any?) {
        ObjectCalls.ptrcallWithStringNameAndVariantArg(setParameterBind, handle, name, value)
    }

    /**
     * Gets the value of a parameter. Parameters are custom local memory used for your animation nodes,
     * given a resource can be reused in multiple trees.
     *
     * Generated from Godot docs: AnimationNode.get_parameter
     */
    fun getParameter(name: String): Any? {
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(getParameterBind, handle, name)
    }

    object Signals {
        const val treeChanged: String = "tree_changed"
        const val nodeUpdated: String = "node_updated"
        const val animationNodeRenamed: String = "animation_node_renamed"
        const val animationNodeRemoved: String = "animation_node_removed"
    }

    companion object {
        const val FILTER_IGNORE: Long = 0L
        const val FILTER_PASS: Long = 1L
        const val FILTER_STOP: Long = 2L
        const val FILTER_BLEND: Long = 3L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): AnimationNode? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AnimationNode? =
            if (handle.address() == 0L) null else AnimationNode(handle)

        private const val ADD_INPUT_HASH = 2323990056L
        private val addInputBind by lazy {
            ObjectCalls.getMethodBind("AnimationNode", "add_input", ADD_INPUT_HASH)
        }

        private const val REMOVE_INPUT_HASH = 1286410249L
        private val removeInputBind by lazy {
            ObjectCalls.getMethodBind("AnimationNode", "remove_input", REMOVE_INPUT_HASH)
        }

        private const val SET_INPUT_NAME_HASH = 215573526L
        private val setInputNameBind by lazy {
            ObjectCalls.getMethodBind("AnimationNode", "set_input_name", SET_INPUT_NAME_HASH)
        }

        private const val GET_INPUT_NAME_HASH = 844755477L
        private val getInputNameBind by lazy {
            ObjectCalls.getMethodBind("AnimationNode", "get_input_name", GET_INPUT_NAME_HASH)
        }

        private const val GET_INPUT_COUNT_HASH = 3905245786L
        private val getInputCountBind by lazy {
            ObjectCalls.getMethodBind("AnimationNode", "get_input_count", GET_INPUT_COUNT_HASH)
        }

        private const val FIND_INPUT_HASH = 1321353865L
        private val findInputBind by lazy {
            ObjectCalls.getMethodBind("AnimationNode", "find_input", FIND_INPUT_HASH)
        }

        private const val SET_FILTER_PATH_HASH = 3868023870L
        private val setFilterPathBind by lazy {
            ObjectCalls.getMethodBind("AnimationNode", "set_filter_path", SET_FILTER_PATH_HASH)
        }

        private const val IS_PATH_FILTERED_HASH = 861721659L
        private val isPathFilteredBind by lazy {
            ObjectCalls.getMethodBind("AnimationNode", "is_path_filtered", IS_PATH_FILTERED_HASH)
        }

        private const val SET_FILTER_ENABLED_HASH = 2586408642L
        private val setFilterEnabledBind by lazy {
            ObjectCalls.getMethodBind("AnimationNode", "set_filter_enabled", SET_FILTER_ENABLED_HASH)
        }

        private const val IS_FILTER_ENABLED_HASH = 36873697L
        private val isFilterEnabledBind by lazy {
            ObjectCalls.getMethodBind("AnimationNode", "is_filter_enabled", IS_FILTER_ENABLED_HASH)
        }

        private const val GET_PROCESSING_ANIMATION_TREE_INSTANCE_ID_HASH = 3905245786L
        private val getProcessingAnimationTreeInstanceIdBind by lazy {
            ObjectCalls.getMethodBind("AnimationNode", "get_processing_animation_tree_instance_id", GET_PROCESSING_ANIMATION_TREE_INSTANCE_ID_HASH)
        }

        private const val IS_PROCESS_TESTING_HASH = 36873697L
        private val isProcessTestingBind by lazy {
            ObjectCalls.getMethodBind("AnimationNode", "is_process_testing", IS_PROCESS_TESTING_HASH)
        }

        private const val BLEND_ANIMATION_HASH = 1630801826L
        private val blendAnimationBind by lazy {
            ObjectCalls.getMethodBind("AnimationNode", "blend_animation", BLEND_ANIMATION_HASH)
        }

        private const val BLEND_NODE_HASH = 1746075988L
        private val blendNodeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNode", "blend_node", BLEND_NODE_HASH)
        }

        private const val BLEND_INPUT_HASH = 1361527350L
        private val blendInputBind by lazy {
            ObjectCalls.getMethodBind("AnimationNode", "blend_input", BLEND_INPUT_HASH)
        }

        private const val SET_PARAMETER_HASH = 3776071444L
        private val setParameterBind by lazy {
            ObjectCalls.getMethodBind("AnimationNode", "set_parameter", SET_PARAMETER_HASH)
        }

        private const val GET_PARAMETER_HASH = 2760726917L
        private val getParameterBind by lazy {
            ObjectCalls.getMethodBind("AnimationNode", "get_parameter", GET_PARAMETER_HASH)
        }
    }
}
