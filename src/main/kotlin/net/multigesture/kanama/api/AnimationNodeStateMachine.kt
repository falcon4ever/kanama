package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * A state machine with multiple `AnimationRootNode`s, used by `AnimationTree`.
 *
 * Generated from Godot docs: AnimationNodeStateMachine
 */
class AnimationNodeStateMachine(handle: MemorySegment) : AnimationRootNode(handle) {
    var stateMachineType: Long
        @JvmName("stateMachineTypeProperty")
        get() = getStateMachineType()
        @JvmName("setStateMachineTypeProperty")
        set(value) = setStateMachineType(value)

    var allowTransitionToSelf: Boolean
        @JvmName("allowTransitionToSelfProperty")
        get() = isAllowTransitionToSelf()
        @JvmName("setAllowTransitionToSelfProperty")
        set(value) = setAllowTransitionToSelf(value)

    var resetEnds: Boolean
        @JvmName("resetEndsProperty")
        get() = areEndsReset()
        @JvmName("setResetEndsProperty")
        set(value) = setResetEnds(value)

    /**
     * Adds a new animation node to the graph. The `position` is used for display in the editor.
     *
     * Generated from Godot docs: AnimationNodeStateMachine.add_node
     */
    fun addNode(name: String, node: AnimationNode?, position: Vector2 = Vector2(0f, 0f)) {
        ObjectCalls.ptrcallWithStringNameObjectAndVector2Arg(addNodeBind, handle, name, node?.requireOpenHandle() ?: MemorySegment.NULL, position)
    }

    /**
     * Replaces the given animation node with a new animation node.
     *
     * Generated from Godot docs: AnimationNodeStateMachine.replace_node
     */
    fun replaceNode(name: String, node: AnimationNode?) {
        ObjectCalls.ptrcallWithStringNameAndObjectArg(replaceNodeBind, handle, name, node?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Returns the animation node with the given name.
     *
     * Generated from Godot docs: AnimationNodeStateMachine.get_node
     */
    fun getNode(name: String): AnimationNode? {
        return AnimationNode.wrap(ObjectCalls.ptrcallWithStringNameArgRetObject(getNodeBind, handle, name))
    }

    /**
     * Deletes the given animation node from the graph.
     *
     * Generated from Godot docs: AnimationNodeStateMachine.remove_node
     */
    fun removeNode(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(removeNodeBind, handle, name)
    }

    /**
     * Renames the given animation node.
     *
     * Generated from Godot docs: AnimationNodeStateMachine.rename_node
     */
    fun renameNode(name: String, newName: String) {
        ObjectCalls.ptrcallWithTwoStringNameArgs(renameNodeBind, handle, name, newName)
    }

    /**
     * Returns `true` if the graph contains the given animation node.
     *
     * Generated from Godot docs: AnimationNodeStateMachine.has_node
     */
    fun hasNode(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasNodeBind, handle, name)
    }

    /**
     * Returns the given animation node's name.
     *
     * Generated from Godot docs: AnimationNodeStateMachine.get_node_name
     */
    fun getNodeName(node: AnimationNode?): String {
        return ObjectCalls.ptrcallWithObjectArgRetStringName(getNodeNameBind, handle, node?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Returns a list containing the names of all animation nodes in this state machine.
     *
     * Generated from Godot docs: AnimationNodeStateMachine.get_node_list
     */
    fun getNodeList(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetStringNameList(getNodeListBind, handle)
    }

    /**
     * Sets the animation node's coordinates. Used for display in the editor.
     *
     * Generated from Godot docs: AnimationNodeStateMachine.set_node_position
     */
    fun setNodePosition(name: String, position: Vector2) {
        ObjectCalls.ptrcallWithStringNameAndVector2Arg(setNodePositionBind, handle, name, position)
    }

    /**
     * Returns the given animation node's coordinates. Used for display in the editor.
     *
     * Generated from Godot docs: AnimationNodeStateMachine.get_node_position
     */
    fun getNodePosition(name: String): Vector2 {
        return ObjectCalls.ptrcallWithStringNameArgRetVector2(getNodePositionBind, handle, name)
    }

    /**
     * Returns `true` if there is a transition between the given animation nodes.
     *
     * Generated from Godot docs: AnimationNodeStateMachine.has_transition
     */
    fun hasTransition(from: String, to: String): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(hasTransitionBind, handle, from, to)
    }

    /**
     * Adds a transition between the given animation nodes.
     *
     * Generated from Godot docs: AnimationNodeStateMachine.add_transition
     */
    fun addTransition(from: String, to: String, transition: AnimationNodeStateMachineTransition?) {
        ObjectCalls.ptrcallWithTwoStringNameAndObjectArg(addTransitionBind, handle, from, to, transition?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Returns the given transition.
     *
     * Generated from Godot docs: AnimationNodeStateMachine.get_transition
     */
    fun getTransition(idx: Int): AnimationNodeStateMachineTransition? {
        return AnimationNodeStateMachineTransition.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getTransitionBind, handle, idx))
    }

    /**
     * Returns the given transition's start node.
     *
     * Generated from Godot docs: AnimationNodeStateMachine.get_transition_from
     */
    fun getTransitionFrom(idx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetStringName(getTransitionFromBind, handle, idx)
    }

    /**
     * Returns the given transition's end node.
     *
     * Generated from Godot docs: AnimationNodeStateMachine.get_transition_to
     */
    fun getTransitionTo(idx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetStringName(getTransitionToBind, handle, idx)
    }

    /**
     * Returns the number of connections in the graph.
     *
     * Generated from Godot docs: AnimationNodeStateMachine.get_transition_count
     */
    fun getTransitionCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getTransitionCountBind, handle)
    }

    /**
     * Deletes the given transition by index.
     *
     * Generated from Godot docs: AnimationNodeStateMachine.remove_transition_by_index
     */
    fun removeTransitionByIndex(idx: Int) {
        ObjectCalls.ptrcallWithIntArg(removeTransitionByIndexBind, handle, idx)
    }

    /**
     * Deletes the transition between the two specified animation nodes.
     *
     * Generated from Godot docs: AnimationNodeStateMachine.remove_transition
     */
    fun removeTransition(from: String, to: String) {
        ObjectCalls.ptrcallWithTwoStringNameArgs(removeTransitionBind, handle, from, to)
    }

    /**
     * Sets the draw offset of the graph. Used for display in the editor.
     *
     * Generated from Godot docs: AnimationNodeStateMachine.set_graph_offset
     */
    fun setGraphOffset(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setGraphOffsetBind, handle, offset)
    }

    /**
     * Returns the draw offset of the graph. Used for display in the editor.
     *
     * Generated from Godot docs: AnimationNodeStateMachine.get_graph_offset
     */
    fun getGraphOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getGraphOffsetBind, handle)
    }

    /**
     * This property can define the process of transitions for different use cases. See also
     * `AnimationNodeStateMachine.StateMachineType`.
     *
     * Generated from Godot docs: AnimationNodeStateMachine.set_state_machine_type
     */
    fun setStateMachineType(stateMachineType: Long) {
        ObjectCalls.ptrcallWithLongArg(setStateMachineTypeBind, handle, stateMachineType)
    }

    /**
     * This property can define the process of transitions for different use cases. See also
     * `AnimationNodeStateMachine.StateMachineType`.
     *
     * Generated from Godot docs: AnimationNodeStateMachine.get_state_machine_type
     */
    fun getStateMachineType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getStateMachineTypeBind, handle)
    }

    /**
     * If `true`, allows teleport to the self state with `AnimationNodeStateMachinePlayback.travel`.
     * When the reset option is enabled in `AnimationNodeStateMachinePlayback.travel`, the animation is
     * restarted. If `false`, nothing happens on the teleportation to the self state.
     *
     * Generated from Godot docs: AnimationNodeStateMachine.set_allow_transition_to_self
     */
    fun setAllowTransitionToSelf(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAllowTransitionToSelfBind, handle, enable)
    }

    /**
     * If `true`, allows teleport to the self state with `AnimationNodeStateMachinePlayback.travel`.
     * When the reset option is enabled in `AnimationNodeStateMachinePlayback.travel`, the animation is
     * restarted. If `false`, nothing happens on the teleportation to the self state.
     *
     * Generated from Godot docs: AnimationNodeStateMachine.is_allow_transition_to_self
     */
    fun isAllowTransitionToSelf(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAllowTransitionToSelfBind, handle)
    }

    /**
     * If `true`, treat the cross-fade to the start and end nodes as a blend with the RESET animation.
     * In most cases, when additional cross-fades are performed in the parent `AnimationNode` of the
     * state machine, setting this property to `false` and matching the cross-fade time of the parent
     * `AnimationNode` and the state machine's start node and end node gives good results.
     *
     * Generated from Godot docs: AnimationNodeStateMachine.set_reset_ends
     */
    fun setResetEnds(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setResetEndsBind, handle, enable)
    }

    /**
     * If `true`, treat the cross-fade to the start and end nodes as a blend with the RESET animation.
     * In most cases, when additional cross-fades are performed in the parent `AnimationNode` of the
     * state machine, setting this property to `false` and matching the cross-fade time of the parent
     * `AnimationNode` and the state machine's start node and end node gives good results.
     *
     * Generated from Godot docs: AnimationNodeStateMachine.are_ends_reset
     */
    fun areEndsReset(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(areEndsResetBind, handle)
    }

    companion object {
        const val STATE_MACHINE_TYPE_ROOT: Long = 0L
        const val STATE_MACHINE_TYPE_NESTED: Long = 1L
        const val STATE_MACHINE_TYPE_GROUPED: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): AnimationNodeStateMachine? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AnimationNodeStateMachine? =
            if (handle.address() == 0L) null else AnimationNodeStateMachine(handle)

        private const val ADD_NODE_HASH = 1980270704L
        private val addNodeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachine", "add_node", ADD_NODE_HASH)
        }

        private const val REPLACE_NODE_HASH = 2559412862L
        private val replaceNodeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachine", "replace_node", REPLACE_NODE_HASH)
        }

        private const val GET_NODE_HASH = 625644256L
        private val getNodeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachine", "get_node", GET_NODE_HASH)
        }

        private const val REMOVE_NODE_HASH = 3304788590L
        private val removeNodeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachine", "remove_node", REMOVE_NODE_HASH)
        }

        private const val RENAME_NODE_HASH = 3740211285L
        private val renameNodeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachine", "rename_node", RENAME_NODE_HASH)
        }

        private const val HAS_NODE_HASH = 2619796661L
        private val hasNodeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachine", "has_node", HAS_NODE_HASH)
        }

        private const val GET_NODE_NAME_HASH = 739213945L
        private val getNodeNameBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachine", "get_node_name", GET_NODE_NAME_HASH)
        }

        private const val GET_NODE_LIST_HASH = 3995934104L
        private val getNodeListBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachine", "get_node_list", GET_NODE_LIST_HASH)
        }

        private const val SET_NODE_POSITION_HASH = 1999414630L
        private val setNodePositionBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachine", "set_node_position", SET_NODE_POSITION_HASH)
        }

        private const val GET_NODE_POSITION_HASH = 3100822709L
        private val getNodePositionBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachine", "get_node_position", GET_NODE_POSITION_HASH)
        }

        private const val HAS_TRANSITION_HASH = 471820014L
        private val hasTransitionBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachine", "has_transition", HAS_TRANSITION_HASH)
        }

        private const val ADD_TRANSITION_HASH = 795486887L
        private val addTransitionBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachine", "add_transition", ADD_TRANSITION_HASH)
        }

        private const val GET_TRANSITION_HASH = 4192381260L
        private val getTransitionBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachine", "get_transition", GET_TRANSITION_HASH)
        }

        private const val GET_TRANSITION_FROM_HASH = 659327637L
        private val getTransitionFromBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachine", "get_transition_from", GET_TRANSITION_FROM_HASH)
        }

        private const val GET_TRANSITION_TO_HASH = 659327637L
        private val getTransitionToBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachine", "get_transition_to", GET_TRANSITION_TO_HASH)
        }

        private const val GET_TRANSITION_COUNT_HASH = 3905245786L
        private val getTransitionCountBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachine", "get_transition_count", GET_TRANSITION_COUNT_HASH)
        }

        private const val REMOVE_TRANSITION_BY_INDEX_HASH = 1286410249L
        private val removeTransitionByIndexBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachine", "remove_transition_by_index", REMOVE_TRANSITION_BY_INDEX_HASH)
        }

        private const val REMOVE_TRANSITION_HASH = 3740211285L
        private val removeTransitionBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachine", "remove_transition", REMOVE_TRANSITION_HASH)
        }

        private const val SET_GRAPH_OFFSET_HASH = 743155724L
        private val setGraphOffsetBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachine", "set_graph_offset", SET_GRAPH_OFFSET_HASH)
        }

        private const val GET_GRAPH_OFFSET_HASH = 3341600327L
        private val getGraphOffsetBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachine", "get_graph_offset", GET_GRAPH_OFFSET_HASH)
        }

        private const val SET_STATE_MACHINE_TYPE_HASH = 2584759088L
        private val setStateMachineTypeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachine", "set_state_machine_type", SET_STATE_MACHINE_TYPE_HASH)
        }

        private const val GET_STATE_MACHINE_TYPE_HASH = 1140726469L
        private val getStateMachineTypeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachine", "get_state_machine_type", GET_STATE_MACHINE_TYPE_HASH)
        }

        private const val SET_ALLOW_TRANSITION_TO_SELF_HASH = 2586408642L
        private val setAllowTransitionToSelfBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachine", "set_allow_transition_to_self", SET_ALLOW_TRANSITION_TO_SELF_HASH)
        }

        private const val IS_ALLOW_TRANSITION_TO_SELF_HASH = 36873697L
        private val isAllowTransitionToSelfBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachine", "is_allow_transition_to_self", IS_ALLOW_TRANSITION_TO_SELF_HASH)
        }

        private const val SET_RESET_ENDS_HASH = 2586408642L
        private val setResetEndsBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachine", "set_reset_ends", SET_RESET_ENDS_HASH)
        }

        private const val ARE_ENDS_RESET_HASH = 36873697L
        private val areEndsResetBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachine", "are_ends_reset", ARE_ENDS_RESET_HASH)
        }
    }
}
