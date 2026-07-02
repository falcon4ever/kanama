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

    fun addNode(name: String, node: AnimationNode?, position: Vector2 = Vector2(0f, 0f)) {
        ObjectCalls.ptrcallWithStringNameObjectAndVector2Arg(addNodeBind, handle, name, node?.requireOpenHandle() ?: MemorySegment.NULL, position)
    }

    fun replaceNode(name: String, node: AnimationNode?) {
        ObjectCalls.ptrcallWithStringNameAndObjectArg(replaceNodeBind, handle, name, node?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getNode(name: String): AnimationNode? {
        return AnimationNode.wrap(ObjectCalls.ptrcallWithStringNameArgRetObject(getNodeBind, handle, name))
    }

    fun removeNode(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(removeNodeBind, handle, name)
    }

    fun renameNode(name: String, newName: String) {
        ObjectCalls.ptrcallWithTwoStringNameArgs(renameNodeBind, handle, name, newName)
    }

    fun hasNode(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasNodeBind, handle, name)
    }

    fun getNodeName(node: AnimationNode?): String {
        return ObjectCalls.ptrcallWithObjectArgRetStringName(getNodeNameBind, handle, node?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getNodeList(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetStringNameList(getNodeListBind, handle)
    }

    fun setNodePosition(name: String, position: Vector2) {
        ObjectCalls.ptrcallWithStringNameAndVector2Arg(setNodePositionBind, handle, name, position)
    }

    fun getNodePosition(name: String): Vector2 {
        return ObjectCalls.ptrcallWithStringNameArgRetVector2(getNodePositionBind, handle, name)
    }

    fun hasTransition(from: String, to: String): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(hasTransitionBind, handle, from, to)
    }

    fun addTransition(from: String, to: String, transition: AnimationNodeStateMachineTransition?) {
        ObjectCalls.ptrcallWithTwoStringNameAndObjectArg(addTransitionBind, handle, from, to, transition?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getTransition(idx: Int): AnimationNodeStateMachineTransition? {
        return AnimationNodeStateMachineTransition.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getTransitionBind, handle, idx))
    }

    fun getTransitionFrom(idx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetStringName(getTransitionFromBind, handle, idx)
    }

    fun getTransitionTo(idx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetStringName(getTransitionToBind, handle, idx)
    }

    fun getTransitionCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getTransitionCountBind, handle)
    }

    fun removeTransitionByIndex(idx: Int) {
        ObjectCalls.ptrcallWithIntArg(removeTransitionByIndexBind, handle, idx)
    }

    fun removeTransition(from: String, to: String) {
        ObjectCalls.ptrcallWithTwoStringNameArgs(removeTransitionBind, handle, from, to)
    }

    fun setGraphOffset(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setGraphOffsetBind, handle, offset)
    }

    fun getGraphOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getGraphOffsetBind, handle)
    }

    fun setStateMachineType(stateMachineType: Long) {
        ObjectCalls.ptrcallWithLongArg(setStateMachineTypeBind, handle, stateMachineType)
    }

    fun getStateMachineType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getStateMachineTypeBind, handle)
    }

    fun setAllowTransitionToSelf(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAllowTransitionToSelfBind, handle, enable)
    }

    fun isAllowTransitionToSelf(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAllowTransitionToSelfBind, handle)
    }

    fun setResetEnds(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setResetEndsBind, handle, enable)
    }

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
