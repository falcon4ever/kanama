package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * A sub-tree of many type `AnimationNode`s used for complex animations. Used by `AnimationTree`.
 *
 * Generated from Godot docs: AnimationNodeBlendTree
 */
class AnimationNodeBlendTree(handle: MemorySegment) : AnimationRootNode(handle) {
    var graphOffset: Vector2
        @JvmName("graphOffsetProperty")
        get() = getGraphOffset()
        @JvmName("setGraphOffsetProperty")
        set(value) = setGraphOffset(value)

    /**
     * Adds an `AnimationNode` at the given `position`. The `name` is used to identify the created sub
     * animation node later.
     *
     * Generated from Godot docs: AnimationNodeBlendTree.add_node
     */
    fun addNode(name: String, node: AnimationNode?, position: Vector2 = Vector2(0f, 0f)) {
        ObjectCalls.ptrcallWithStringNameObjectAndVector2Arg(addNodeBind, handle, name, node?.requireOpenHandle() ?: MemorySegment.NULL, position)
    }

    /**
     * Returns the sub animation node with the specified `name`.
     *
     * Generated from Godot docs: AnimationNodeBlendTree.get_node
     */
    fun getNode(name: String): AnimationNode? {
        return AnimationNode.wrap(ObjectCalls.ptrcallWithStringNameArgRetObject(getNodeBind, handle, name))
    }

    /**
     * Removes a sub animation node.
     *
     * Generated from Godot docs: AnimationNodeBlendTree.remove_node
     */
    fun removeNode(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(removeNodeBind, handle, name)
    }

    /**
     * Changes the name of a sub animation node.
     *
     * Generated from Godot docs: AnimationNodeBlendTree.rename_node
     */
    fun renameNode(name: String, newName: String) {
        ObjectCalls.ptrcallWithTwoStringNameArgs(renameNodeBind, handle, name, newName)
    }

    /**
     * Returns `true` if a sub animation node with specified `name` exists.
     *
     * Generated from Godot docs: AnimationNodeBlendTree.has_node
     */
    fun hasNode(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasNodeBind, handle, name)
    }

    /**
     * Connects the output of an `AnimationNode` as input for another `AnimationNode`, at the input
     * port specified by `input_index`.
     *
     * Generated from Godot docs: AnimationNodeBlendTree.connect_node
     */
    fun connectNode(inputNode: String, inputIndex: Int, outputNode: String) {
        ObjectCalls.ptrcallWithStringNameIntAndStringNameArgs(connectNodeBind, handle, inputNode, inputIndex, outputNode)
    }

    /**
     * Disconnects the animation node connected to the specified input.
     *
     * Generated from Godot docs: AnimationNodeBlendTree.disconnect_node
     */
    fun disconnectNode(inputNode: String, inputIndex: Int) {
        ObjectCalls.ptrcallWithStringNameAndIntArg(disconnectNodeBind, handle, inputNode, inputIndex)
    }

    /**
     * Returns a list containing the names of all sub animation nodes in this blend tree.
     *
     * Generated from Godot docs: AnimationNodeBlendTree.get_node_list
     */
    fun getNodeList(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetStringNameList(getNodeListBind, handle)
    }

    /**
     * Modifies the position of a sub animation node.
     *
     * Generated from Godot docs: AnimationNodeBlendTree.set_node_position
     */
    fun setNodePosition(name: String, position: Vector2) {
        ObjectCalls.ptrcallWithStringNameAndVector2Arg(setNodePositionBind, handle, name, position)
    }

    /**
     * Returns the position of the sub animation node with the specified `name`.
     *
     * Generated from Godot docs: AnimationNodeBlendTree.get_node_position
     */
    fun getNodePosition(name: String): Vector2 {
        return ObjectCalls.ptrcallWithStringNameArgRetVector2(getNodePositionBind, handle, name)
    }

    /**
     * The global offset of all sub animation nodes.
     *
     * Generated from Godot docs: AnimationNodeBlendTree.set_graph_offset
     */
    fun setGraphOffset(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setGraphOffsetBind, handle, offset)
    }

    /**
     * The global offset of all sub animation nodes.
     *
     * Generated from Godot docs: AnimationNodeBlendTree.get_graph_offset
     */
    fun getGraphOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getGraphOffsetBind, handle)
    }

    object Signals {
        const val nodeChanged: String = "node_changed"
    }

    companion object {
        const val CONNECTION_OK: Long = 0L
        const val CONNECTION_ERROR_NO_INPUT: Long = 1L
        const val CONNECTION_ERROR_NO_INPUT_INDEX: Long = 2L
        const val CONNECTION_ERROR_NO_OUTPUT: Long = 3L
        const val CONNECTION_ERROR_SAME_NODE: Long = 4L
        const val CONNECTION_ERROR_CONNECTION_EXISTS: Long = 5L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): AnimationNodeBlendTree? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AnimationNodeBlendTree? =
            if (handle.address() == 0L) null else AnimationNodeBlendTree(handle)

        private const val ADD_NODE_HASH = 1980270704L
        private val addNodeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendTree", "add_node", ADD_NODE_HASH)
        }

        private const val GET_NODE_HASH = 625644256L
        private val getNodeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendTree", "get_node", GET_NODE_HASH)
        }

        private const val REMOVE_NODE_HASH = 3304788590L
        private val removeNodeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendTree", "remove_node", REMOVE_NODE_HASH)
        }

        private const val RENAME_NODE_HASH = 3740211285L
        private val renameNodeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendTree", "rename_node", RENAME_NODE_HASH)
        }

        private const val HAS_NODE_HASH = 2619796661L
        private val hasNodeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendTree", "has_node", HAS_NODE_HASH)
        }

        private const val CONNECT_NODE_HASH = 2168001410L
        private val connectNodeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendTree", "connect_node", CONNECT_NODE_HASH)
        }

        private const val DISCONNECT_NODE_HASH = 2415702435L
        private val disconnectNodeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendTree", "disconnect_node", DISCONNECT_NODE_HASH)
        }

        private const val GET_NODE_LIST_HASH = 3995934104L
        private val getNodeListBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendTree", "get_node_list", GET_NODE_LIST_HASH)
        }

        private const val SET_NODE_POSITION_HASH = 1999414630L
        private val setNodePositionBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendTree", "set_node_position", SET_NODE_POSITION_HASH)
        }

        private const val GET_NODE_POSITION_HASH = 3100822709L
        private val getNodePositionBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendTree", "get_node_position", GET_NODE_POSITION_HASH)
        }

        private const val SET_GRAPH_OFFSET_HASH = 743155724L
        private val setGraphOffsetBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendTree", "set_graph_offset", SET_GRAPH_OFFSET_HASH)
        }

        private const val GET_GRAPH_OFFSET_HASH = 3341600327L
        private val getGraphOffsetBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeBlendTree", "get_graph_offset", GET_GRAPH_OFFSET_HASH)
        }
    }
}
