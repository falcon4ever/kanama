package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed
import net.multigesture.kanama.types.Vector2

/**
 * A reusable group of `VisualShaderNode`s similar to a function in programming.
 *
 * Generated from Godot docs: VisualShaderGroup
 */
class VisualShaderGroup(handle: GodotHandle) : Resource(handle) {
    var groupName: String
        @JvmName("groupNameProperty")
        get() = getGroupName()
        @JvmName("setGroupNameProperty")
        set(value) = setGroupName(value)

    var inputPortCount: Int
        @JvmName("inputPortCountProperty")
        get() = getInputPortCount()
        @JvmName("setInputPortCountProperty")
        set(value) = setInputPortCount(value)

    var outputPortCount: Int
        @JvmName("outputPortCountProperty")
        get() = getOutputPortCount()
        @JvmName("setOutputPortCountProperty")
        set(value) = setOutputPortCount(value)

    /**
     * The display name of this group. This name is shown as the caption of the `VisualShaderNodeGroup`
     * node in the editor.
     *
     * Generated from Godot docs: VisualShaderGroup.set_group_name
     */
    fun setGroupName(name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setGroupNameBind, segment, name)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The display name of this group. This name is shown as the caption of the `VisualShaderNodeGroup`
     * node in the editor.
     *
     * Generated from Godot docs: VisualShaderGroup.get_group_name
     */
    fun getGroupName(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getGroupNameBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Removes the input port at the given `id`.
     *
     * Generated from Godot docs: VisualShaderGroup.remove_input_port
     */
    fun removeInputPort(id: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.removeInputPortBind, segment, id)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Moves the input port at index `from` to index `to`. Connections from input nodes inside the
     * group will be updated accordingly.
     *
     * Generated from Godot docs: VisualShaderGroup.move_input_port
     */
    fun moveInputPort(from: Int, to: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.moveInputPortBind, segment, from, to)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Sets the name of the input port at the given `id`.
     *
     * Generated from Godot docs: VisualShaderGroup.set_input_port_name
     */
    fun setInputPortName(id: Int, name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndStringArg(Binds.setInputPortNameBind, segment, id, name)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Sets the type of the input port at the given `id`.
     *
     * Generated from Godot docs: VisualShaderGroup.set_input_port_type
     */
    fun setInputPortType(id: Int, type: VisualShaderNode.PortType) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndLongArgs(Binds.setInputPortTypeBind, segment, id, type.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The number of input ports defined for this group. Shrinking the array removes the trailing ports
     * along with any connections made to them.
     *
     * Generated from Godot docs: VisualShaderGroup.set_input_port_count
     */
    fun setInputPortCount(count: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setInputPortCountBind, segment, count)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The number of input ports defined for this group. Shrinking the array removes the trailing ports
     * along with any connections made to them.
     *
     * Generated from Godot docs: VisualShaderGroup.get_input_port_count
     */
    fun getInputPortCount(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getInputPortCountBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the name of the input port at the given `id`.
     *
     * Generated from Godot docs: VisualShaderGroup.get_input_port_name
     */
    fun getInputPortName(id: Int): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetString(Binds.getInputPortNameBind, segment, id)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the type of the input port at the given `id`.
     *
     * Generated from Godot docs: VisualShaderGroup.get_input_port_type
     */
    fun getInputPortType(id: Int): VisualShaderNode.PortType {
        checkOpen()
        val ret = VisualShaderNode.PortType(ObjectCalls.ptrcallWithIntArgRetLong(Binds.getInputPortTypeBind, segment, id))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Removes the output port at the given `id`.
     *
     * Generated from Godot docs: VisualShaderGroup.remove_output_port
     */
    fun removeOutputPort(id: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.removeOutputPortBind, segment, id)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Moves the output port at index `from` to index `to`. Connections to output nodes inside the
     * group will be updated accordingly.
     *
     * Generated from Godot docs: VisualShaderGroup.move_output_port
     */
    fun moveOutputPort(from: Int, to: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.moveOutputPortBind, segment, from, to)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Sets the name of the output port at the given `id`.
     *
     * Generated from Godot docs: VisualShaderGroup.set_output_port_name
     */
    fun setOutputPortName(id: Int, name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndStringArg(Binds.setOutputPortNameBind, segment, id, name)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Sets the type of the output port at the given `id`.
     *
     * Generated from Godot docs: VisualShaderGroup.set_output_port_type
     */
    fun setOutputPortType(id: Int, type: VisualShaderNode.PortType) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndLongArgs(Binds.setOutputPortTypeBind, segment, id, type.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The number of output ports defined for this group. Shrinking the array removes the trailing
     * ports along with any connections made to them.
     *
     * Generated from Godot docs: VisualShaderGroup.set_output_port_count
     */
    fun setOutputPortCount(count: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setOutputPortCountBind, segment, count)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The number of output ports defined for this group. Shrinking the array removes the trailing
     * ports along with any connections made to them.
     *
     * Generated from Godot docs: VisualShaderGroup.get_output_port_count
     */
    fun getOutputPortCount(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getOutputPortCountBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the name of the output port at the given `id`.
     *
     * Generated from Godot docs: VisualShaderGroup.get_output_port_name
     */
    fun getOutputPortName(id: Int): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetString(Binds.getOutputPortNameBind, segment, id)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the type of the output port at the given `id`.
     *
     * Generated from Godot docs: VisualShaderGroup.get_output_port_type
     */
    fun getOutputPortType(id: Int): VisualShaderNode.PortType {
        checkOpen()
        val ret = VisualShaderNode.PortType(ObjectCalls.ptrcallWithIntArgRetLong(Binds.getOutputPortTypeBind, segment, id))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Adds the specified `node` to the group.
     *
     * Generated from Godot docs: VisualShaderGroup.add_node
     */
    fun addNode(node: VisualShaderNode?, position: Vector2, id: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectVector2AndIntArgs(Binds.addNodeBind, segment, node?.requireOpenHandle() ?: NULL_SEGMENT, position, id)
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(node)
    }

    /**
     * Returns the shader node instance with the specified `id`.
     *
     * Generated from Godot docs: VisualShaderGroup.get_node
     */
    fun getNode(id: Int): VisualShaderNode? {
        checkOpen()
        val ret = VisualShaderNode.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getNodeBind, segment, id))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the position of the specified node.
     *
     * Generated from Godot docs: VisualShaderGroup.set_node_position
     */
    fun setNodePosition(id: Int, position: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndVector2Arg(Binds.setNodePositionBind, segment, id, position)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns the position of the specified node within the shader graph.
     *
     * Generated from Godot docs: VisualShaderGroup.get_node_position
     */
    fun getNodePosition(id: Int): Vector2 {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetVector2(Binds.getNodePositionBind, segment, id)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the list of all nodes in the group.
     *
     * Generated from Godot docs: VisualShaderGroup.get_node_list
     */
    fun getNodeList(): List<Int> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetPackedInt32List(Binds.getNodeListBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the next valid node ID that can be added to the shader graph.
     *
     * Generated from Godot docs: VisualShaderGroup.get_valid_node_id
     */
    fun getValidNodeId(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getValidNodeIdBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Removes the specified node from the group.
     *
     * Generated from Godot docs: VisualShaderGroup.remove_node
     */
    fun removeNode(id: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.removeNodeBind, segment, id)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Replaces the specified node with a node of the new class type.
     *
     * Generated from Godot docs: VisualShaderGroup.replace_node
     */
    fun replaceNode(id: Int, newClass: String) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndStringNameArg(Binds.replaceNodeBind, segment, id, newClass)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Connects the specified nodes and ports.
     *
     * Generated from Godot docs: VisualShaderGroup.connect_nodes
     */
    fun connectNodes(fromNode: Int, fromPort: Int, toNode: Int, toPort: Int): GodotError {
        checkOpen()
        val ret = GodotError(ObjectCalls.ptrcallWithFourIntArgsRetLong(Binds.connectNodesBind, segment, fromNode, fromPort, toNode, toPort))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Disconnects the specified nodes and ports.
     *
     * Generated from Godot docs: VisualShaderGroup.disconnect_nodes
     */
    fun disconnectNodes(fromNode: Int, fromPort: Int, toNode: Int, toPort: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithFourIntArgs(Binds.disconnectNodesBind, segment, fromNode, fromPort, toNode, toPort)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Connects the specified nodes and ports, even if they can't be connected.
     *
     * Generated from Godot docs: VisualShaderGroup.connect_nodes_forced
     */
    fun connectNodesForced(fromNode: Int, fromPort: Int, toNode: Int, toPort: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithFourIntArgs(Binds.connectNodesForcedBind, segment, fromNode, fromPort, toNode, toPort)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns the list of connected nodes.
     *
     * Generated from Godot docs: VisualShaderGroup.get_node_connections
     */
    fun getNodeConnections(): List<Map<String, Any?>> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDictionaryList(Binds.getNodeConnectionsBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Attaches the given node to the given frame.
     *
     * Generated from Godot docs: VisualShaderGroup.attach_node_to_frame
     */
    fun attachNodeToFrame(id: Int, frame: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.attachNodeToFrameBind, segment, id, frame)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Detaches the given node from the frame it is attached to.
     *
     * Generated from Godot docs: VisualShaderGroup.detach_node_from_frame
     */
    fun detachNodeFromFrame(id: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.detachNodeFromFrameBind, segment, id)
        ObjectCalls.keepReachable(this)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderGroup? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderGroup? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderGroup(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderGroup? =
            if (handle.address() == 0L) null else VisualShaderGroup(GodotHandle(handle))

        /** A new VisualShaderGroup, GDScript's `VisualShaderGroup.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderGroup =
            RefCounted.owned(VisualShaderGroup(GodotHandle(requireConstructed(ObjectCalls.constructObject("VisualShaderGroup"), "VisualShaderGroup"))))
    }

    private object Binds {
        private const val SET_GROUP_NAME_HASH = 83702148L
        @JvmField
        val setGroupNameBind =
            ObjectCalls.getMethodBind("VisualShaderGroup", "set_group_name", SET_GROUP_NAME_HASH)

        private const val GET_GROUP_NAME_HASH = 201670096L
        @JvmField
        val getGroupNameBind =
            ObjectCalls.getMethodBind("VisualShaderGroup", "get_group_name", GET_GROUP_NAME_HASH)

        private const val REMOVE_INPUT_PORT_HASH = 1286410249L
        @JvmField
        val removeInputPortBind =
            ObjectCalls.getMethodBind("VisualShaderGroup", "remove_input_port", REMOVE_INPUT_PORT_HASH)

        private const val MOVE_INPUT_PORT_HASH = 3937882851L
        @JvmField
        val moveInputPortBind =
            ObjectCalls.getMethodBind("VisualShaderGroup", "move_input_port", MOVE_INPUT_PORT_HASH)

        private const val SET_INPUT_PORT_NAME_HASH = 501894301L
        @JvmField
        val setInputPortNameBind =
            ObjectCalls.getMethodBind("VisualShaderGroup", "set_input_port_name", SET_INPUT_PORT_NAME_HASH)

        private const val SET_INPUT_PORT_TYPE_HASH = 1959648900L
        @JvmField
        val setInputPortTypeBind =
            ObjectCalls.getMethodBind("VisualShaderGroup", "set_input_port_type", SET_INPUT_PORT_TYPE_HASH)

        private const val SET_INPUT_PORT_COUNT_HASH = 1286410249L
        @JvmField
        val setInputPortCountBind =
            ObjectCalls.getMethodBind("VisualShaderGroup", "set_input_port_count", SET_INPUT_PORT_COUNT_HASH)

        private const val GET_INPUT_PORT_COUNT_HASH = 3905245786L
        @JvmField
        val getInputPortCountBind =
            ObjectCalls.getMethodBind("VisualShaderGroup", "get_input_port_count", GET_INPUT_PORT_COUNT_HASH)

        private const val GET_INPUT_PORT_NAME_HASH = 844755477L
        @JvmField
        val getInputPortNameBind =
            ObjectCalls.getMethodBind("VisualShaderGroup", "get_input_port_name", GET_INPUT_PORT_NAME_HASH)

        private const val GET_INPUT_PORT_TYPE_HASH = 4102573379L
        @JvmField
        val getInputPortTypeBind =
            ObjectCalls.getMethodBind("VisualShaderGroup", "get_input_port_type", GET_INPUT_PORT_TYPE_HASH)

        private const val REMOVE_OUTPUT_PORT_HASH = 1286410249L
        @JvmField
        val removeOutputPortBind =
            ObjectCalls.getMethodBind("VisualShaderGroup", "remove_output_port", REMOVE_OUTPUT_PORT_HASH)

        private const val MOVE_OUTPUT_PORT_HASH = 3937882851L
        @JvmField
        val moveOutputPortBind =
            ObjectCalls.getMethodBind("VisualShaderGroup", "move_output_port", MOVE_OUTPUT_PORT_HASH)

        private const val SET_OUTPUT_PORT_NAME_HASH = 501894301L
        @JvmField
        val setOutputPortNameBind =
            ObjectCalls.getMethodBind("VisualShaderGroup", "set_output_port_name", SET_OUTPUT_PORT_NAME_HASH)

        private const val SET_OUTPUT_PORT_TYPE_HASH = 1959648900L
        @JvmField
        val setOutputPortTypeBind =
            ObjectCalls.getMethodBind("VisualShaderGroup", "set_output_port_type", SET_OUTPUT_PORT_TYPE_HASH)

        private const val SET_OUTPUT_PORT_COUNT_HASH = 1286410249L
        @JvmField
        val setOutputPortCountBind =
            ObjectCalls.getMethodBind("VisualShaderGroup", "set_output_port_count", SET_OUTPUT_PORT_COUNT_HASH)

        private const val GET_OUTPUT_PORT_COUNT_HASH = 3905245786L
        @JvmField
        val getOutputPortCountBind =
            ObjectCalls.getMethodBind("VisualShaderGroup", "get_output_port_count", GET_OUTPUT_PORT_COUNT_HASH)

        private const val GET_OUTPUT_PORT_NAME_HASH = 844755477L
        @JvmField
        val getOutputPortNameBind =
            ObjectCalls.getMethodBind("VisualShaderGroup", "get_output_port_name", GET_OUTPUT_PORT_NAME_HASH)

        private const val GET_OUTPUT_PORT_TYPE_HASH = 4102573379L
        @JvmField
        val getOutputPortTypeBind =
            ObjectCalls.getMethodBind("VisualShaderGroup", "get_output_port_type", GET_OUTPUT_PORT_TYPE_HASH)

        private const val ADD_NODE_HASH = 3625527446L
        @JvmField
        val addNodeBind =
            ObjectCalls.getMethodBind("VisualShaderGroup", "add_node", ADD_NODE_HASH)

        private const val GET_NODE_HASH = 1177927464L
        @JvmField
        val getNodeBind =
            ObjectCalls.getMethodBind("VisualShaderGroup", "get_node", GET_NODE_HASH)

        private const val SET_NODE_POSITION_HASH = 163021252L
        @JvmField
        val setNodePositionBind =
            ObjectCalls.getMethodBind("VisualShaderGroup", "set_node_position", SET_NODE_POSITION_HASH)

        private const val GET_NODE_POSITION_HASH = 2299179447L
        @JvmField
        val getNodePositionBind =
            ObjectCalls.getMethodBind("VisualShaderGroup", "get_node_position", GET_NODE_POSITION_HASH)

        private const val GET_NODE_LIST_HASH = 1930428628L
        @JvmField
        val getNodeListBind =
            ObjectCalls.getMethodBind("VisualShaderGroup", "get_node_list", GET_NODE_LIST_HASH)

        private const val GET_VALID_NODE_ID_HASH = 3905245786L
        @JvmField
        val getValidNodeIdBind =
            ObjectCalls.getMethodBind("VisualShaderGroup", "get_valid_node_id", GET_VALID_NODE_ID_HASH)

        private const val REMOVE_NODE_HASH = 1286410249L
        @JvmField
        val removeNodeBind =
            ObjectCalls.getMethodBind("VisualShaderGroup", "remove_node", REMOVE_NODE_HASH)

        private const val REPLACE_NODE_HASH = 3780747571L
        @JvmField
        val replaceNodeBind =
            ObjectCalls.getMethodBind("VisualShaderGroup", "replace_node", REPLACE_NODE_HASH)

        private const val CONNECT_NODES_HASH = 3684568301L
        @JvmField
        val connectNodesBind =
            ObjectCalls.getMethodBind("VisualShaderGroup", "connect_nodes", CONNECT_NODES_HASH)

        private const val DISCONNECT_NODES_HASH = 4275841770L
        @JvmField
        val disconnectNodesBind =
            ObjectCalls.getMethodBind("VisualShaderGroup", "disconnect_nodes", DISCONNECT_NODES_HASH)

        private const val CONNECT_NODES_FORCED_HASH = 4275841770L
        @JvmField
        val connectNodesForcedBind =
            ObjectCalls.getMethodBind("VisualShaderGroup", "connect_nodes_forced", CONNECT_NODES_FORCED_HASH)

        private const val GET_NODE_CONNECTIONS_HASH = 3995934104L
        @JvmField
        val getNodeConnectionsBind =
            ObjectCalls.getMethodBind("VisualShaderGroup", "get_node_connections", GET_NODE_CONNECTIONS_HASH)

        private const val ATTACH_NODE_TO_FRAME_HASH = 3937882851L
        @JvmField
        val attachNodeToFrameBind =
            ObjectCalls.getMethodBind("VisualShaderGroup", "attach_node_to_frame", ATTACH_NODE_TO_FRAME_HASH)

        private const val DETACH_NODE_FROM_FRAME_HASH = 1286410249L
        @JvmField
        val detachNodeFromFrameBind =
            ObjectCalls.getMethodBind("VisualShaderGroup", "detach_node_from_frame", DETACH_NODE_FROM_FRAME_HASH)
    }
}
