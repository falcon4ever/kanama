package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed
import net.multigesture.kanama.types.Vector2

/**
 * A custom shader program with a visual editor.
 *
 * Generated from Godot docs: VisualShader
 */
class VisualShader(handle: GodotHandle) : Shader(handle) {
    var graphOffset: Vector2
        @JvmName("graphOffsetProperty")
        get() = getGraphOffset()
        @JvmName("setGraphOffsetProperty")
        set(value) = setGraphOffset(value)

    /**
     * Sets the mode of this shader.
     *
     * Generated from Godot docs: VisualShader.set_mode
     */
    fun setMode(mode: Shader.Mode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setModeBind, segment, mode.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Adds the specified `node` to the shader.
     *
     * Generated from Godot docs: VisualShader.add_node
     */
    fun addNode(type: VisualShader.Type, node: VisualShaderNode?, position: Vector2, id: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithLongObjectVector2IntArgs(Binds.addNodeBind, segment, type.value, node?.requireOpenHandle() ?: NULL_SEGMENT, position, id)
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(node)
    }

    /**
     * Returns the shader node instance with specified `type` and `id`.
     *
     * Generated from Godot docs: VisualShader.get_node
     */
    fun getNode(type: VisualShader.Type, id: Int): VisualShaderNode? {
        checkOpen()
        val ret = VisualShaderNode.wrapOwned(ObjectCalls.ptrcallWithLongAndIntArgsRetObject(Binds.getNodeBind, segment, type.value, id))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the position of the specified node.
     *
     * Generated from Godot docs: VisualShader.set_node_position
     */
    fun setNodePosition(type: VisualShader.Type, id: Int, position: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithLongIntVector2Args(Binds.setNodePositionBind, segment, type.value, id, position)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns the position of the specified node within the shader graph.
     *
     * Generated from Godot docs: VisualShader.get_node_position
     */
    fun getNodePosition(type: VisualShader.Type, id: Int): Vector2 {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithLongAndIntArgsRetVector2(Binds.getNodePositionBind, segment, type.value, id)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the list of all nodes in the shader with the specified type.
     *
     * Generated from Godot docs: VisualShader.get_node_list
     */
    fun getNodeList(type: VisualShader.Type): List<Int> {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithLongArgRetPackedInt32List(Binds.getNodeListBind, segment, type.value)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns next valid node ID that can be added to the shader graph.
     *
     * Generated from Godot docs: VisualShader.get_valid_node_id
     */
    fun getValidNodeId(type: VisualShader.Type): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithLongArgRetInt(Binds.getValidNodeIdBind, segment, type.value)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Removes the specified node from the shader.
     *
     * Generated from Godot docs: VisualShader.remove_node
     */
    fun removeNode(type: VisualShader.Type, id: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndIntArgs(Binds.removeNodeBind, segment, type.value, id)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Replaces the specified node with a node of new class type.
     *
     * Generated from Godot docs: VisualShader.replace_node
     */
    fun replaceNode(type: VisualShader.Type, id: Int, newClass: String) {
        checkOpen()
        ObjectCalls.ptrcallWithLongIntStringNameArgs(Binds.replaceNodeBind, segment, type.value, id, newClass)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns `true` if the specified node and port connection exist.
     *
     * Generated from Godot docs: VisualShader.is_node_connection
     */
    fun isNodeConnection(type: VisualShader.Type, fromNode: Int, fromPort: Int, toNode: Int, toPort: Int): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithLongAndFourIntArgsRetBool(Binds.isNodeConnectionBind, segment, type.value, fromNode, fromPort, toNode, toPort)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns `true` if the specified nodes and ports can be connected together.
     *
     * Generated from Godot docs: VisualShader.can_connect_nodes
     */
    fun canConnectNodes(type: VisualShader.Type, fromNode: Int, fromPort: Int, toNode: Int, toPort: Int): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithLongAndFourIntArgsRetBool(Binds.canConnectNodesBind, segment, type.value, fromNode, fromPort, toNode, toPort)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Connects the specified nodes and ports.
     *
     * Generated from Godot docs: VisualShader.connect_nodes
     */
    fun connectNodes(type: VisualShader.Type, fromNode: Int, fromPort: Int, toNode: Int, toPort: Int): GodotError {
        checkOpen()
        val ret = GodotError(ObjectCalls.ptrcallWithLongAndFourIntArgsRetLong(Binds.connectNodesBind, segment, type.value, fromNode, fromPort, toNode, toPort))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Connects the specified nodes and ports.
     *
     * Generated from Godot docs: VisualShader.disconnect_nodes
     */
    fun disconnectNodes(type: VisualShader.Type, fromNode: Int, fromPort: Int, toNode: Int, toPort: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndFourIntArgs(Binds.disconnectNodesBind, segment, type.value, fromNode, fromPort, toNode, toPort)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Connects the specified nodes and ports, even if they can't be connected. Such connection is
     * invalid and will not function properly.
     *
     * Generated from Godot docs: VisualShader.connect_nodes_forced
     */
    fun connectNodesForced(type: VisualShader.Type, fromNode: Int, fromPort: Int, toNode: Int, toPort: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndFourIntArgs(Binds.connectNodesForcedBind, segment, type.value, fromNode, fromPort, toNode, toPort)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns the list of connected nodes with the specified type.
     *
     * Generated from Godot docs: VisualShader.get_node_connections
     */
    fun getNodeConnections(type: VisualShader.Type): List<Map<String, Any?>> {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithLongArgRetDictionaryList(Binds.getNodeConnectionsBind, segment, type.value)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Attaches the given node to the given frame.
     *
     * Generated from Godot docs: VisualShader.attach_node_to_frame
     */
    fun attachNodeToFrame(type: VisualShader.Type, id: Int, frame: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndTwoIntArgs(Binds.attachNodeToFrameBind, segment, type.value, id, frame)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Detaches the given node from the frame it is attached to.
     *
     * Generated from Godot docs: VisualShader.detach_node_from_frame
     */
    fun detachNodeFromFrame(type: VisualShader.Type, id: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndIntArgs(Binds.detachNodeFromFrameBind, segment, type.value, id)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Adds a new varying value node to the shader.
     *
     * Generated from Godot docs: VisualShader.add_varying
     */
    fun addVarying(name: String, mode: VisualShader.VaryingMode, type: VisualShader.VaryingType) {
        checkOpen()
        ObjectCalls.ptrcallWithStringTwoLongArgs(Binds.addVaryingBind, segment, name, mode.value, type.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Removes a varying value node with the given `name`. Prints an error if a node with this name is
     * not found.
     *
     * Generated from Godot docs: VisualShader.remove_varying
     */
    fun removeVarying(name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.removeVaryingBind, segment, name)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns `true` if the shader has a varying with the given `name`.
     *
     * Generated from Godot docs: VisualShader.has_varying
     */
    fun hasVarying(name: String): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithStringArgRetBool(Binds.hasVaryingBind, segment, name)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Deprecated.
     *
     * Generated from Godot docs: VisualShader.set_graph_offset
     */
    fun setGraphOffset(offset: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(Binds.setGraphOffsetBind, segment, offset)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Deprecated.
     *
     * Generated from Godot docs: VisualShader.get_graph_offset
     */
    fun getGraphOffset(): Vector2 {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetVector2(Binds.getGraphOffsetBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Godot's `VisualShader.Type` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`VisualShader.Type.<NAME>`).
     *
     * Generated from Godot docs: VisualShader.Type
     */
    @JvmInline
    value class Type(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * A vertex shader, operating on vertices.
             *
             * Generated from Godot docs: VisualShader.TYPE_VERTEX
             */
            val VERTEX: Type get() = Type(0L)
            /**
             * A fragment shader, operating on fragments (pixels).
             *
             * Generated from Godot docs: VisualShader.TYPE_FRAGMENT
             */
            val FRAGMENT: Type get() = Type(1L)
            /**
             * A shader for light calculations.
             *
             * Generated from Godot docs: VisualShader.TYPE_LIGHT
             */
            val LIGHT: Type get() = Type(2L)
            /**
             * A function for the "start" stage of particle shader.
             *
             * Generated from Godot docs: VisualShader.TYPE_START
             */
            val START: Type get() = Type(3L)
            /**
             * A function for the "process" stage of particle shader.
             *
             * Generated from Godot docs: VisualShader.TYPE_PROCESS
             */
            val PROCESS: Type get() = Type(4L)
            /**
             * A function for the "collide" stage (particle collision handler) of particle shader.
             *
             * Generated from Godot docs: VisualShader.TYPE_COLLIDE
             */
            val COLLIDE: Type get() = Type(5L)
            /**
             * A function for the "start" stage of particle shader, with customized output.
             *
             * Generated from Godot docs: VisualShader.TYPE_START_CUSTOM
             */
            val START_CUSTOM: Type get() = Type(6L)
            /**
             * A function for the "process" stage of particle shader, with customized output.
             *
             * Generated from Godot docs: VisualShader.TYPE_PROCESS_CUSTOM
             */
            val PROCESS_CUSTOM: Type get() = Type(7L)
            /**
             * A shader for 3D environment's sky.
             *
             * Generated from Godot docs: VisualShader.TYPE_SKY
             */
            val SKY: Type get() = Type(8L)
            /**
             * A compute shader that runs for each froxel of the volumetric fog map.
             *
             * Generated from Godot docs: VisualShader.TYPE_FOG
             */
            val FOG: Type get() = Type(9L)
            /**
             * A shader used to process blit calls to a DrawableTexture.
             *
             * Generated from Godot docs: VisualShader.TYPE_TEXTURE_BLIT
             */
            val TEXTURE_BLIT: Type get() = Type(10L)
            /**
             * Represents the size of the `Type` enum.
             *
             * Generated from Godot docs: VisualShader.TYPE_MAX
             */
            val MAX: Type get() = Type(11L)
        }
    }

    /**
     * Godot's `VisualShader.VaryingMode` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`VisualShader.VaryingMode.<NAME>`).
     *
     * Generated from Godot docs: VisualShader.VaryingMode
     */
    @JvmInline
    value class VaryingMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Varying is passed from `Vertex` function to `Fragment` and `Light` functions.
             *
             * Generated from Godot docs: VisualShader.VARYING_MODE_VERTEX_TO_FRAG_LIGHT
             */
            val VERTEX_TO_FRAG_LIGHT: VaryingMode get() = VaryingMode(0L)
            /**
             * Varying is passed from `Fragment` function to `Light` function.
             *
             * Generated from Godot docs: VisualShader.VARYING_MODE_FRAG_TO_LIGHT
             */
            val FRAG_TO_LIGHT: VaryingMode get() = VaryingMode(1L)
            /**
             * Represents the size of the `VaryingMode` enum.
             *
             * Generated from Godot docs: VisualShader.VARYING_MODE_MAX
             */
            val MAX: VaryingMode get() = VaryingMode(2L)
        }
    }

    /**
     * Godot's `VisualShader.VaryingType` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`VisualShader.VaryingType.<NAME>`).
     *
     * Generated from Godot docs: VisualShader.VaryingType
     */
    @JvmInline
    value class VaryingType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Varying is of type `float`.
             *
             * Generated from Godot docs: VisualShader.VARYING_TYPE_FLOAT
             */
            val FLOAT: VaryingType get() = VaryingType(0L)
            /**
             * Varying is of type `int`.
             *
             * Generated from Godot docs: VisualShader.VARYING_TYPE_INT
             */
            val INT: VaryingType get() = VaryingType(1L)
            /**
             * Varying is of type unsigned `int`.
             *
             * Generated from Godot docs: VisualShader.VARYING_TYPE_UINT
             */
            val UINT: VaryingType get() = VaryingType(2L)
            /**
             * Varying is of type `Vector2`.
             *
             * Generated from Godot docs: VisualShader.VARYING_TYPE_VECTOR_2D
             */
            val VECTOR_2D: VaryingType get() = VaryingType(3L)
            /**
             * Varying is of type `Vector3`.
             *
             * Generated from Godot docs: VisualShader.VARYING_TYPE_VECTOR_3D
             */
            val VECTOR_3D: VaryingType get() = VaryingType(4L)
            /**
             * Varying is of type `Vector4`.
             *
             * Generated from Godot docs: VisualShader.VARYING_TYPE_VECTOR_4D
             */
            val VECTOR_4D: VaryingType get() = VaryingType(5L)
            /**
             * Varying is of type `bool`.
             *
             * Generated from Godot docs: VisualShader.VARYING_TYPE_BOOLEAN
             */
            val BOOLEAN: VaryingType get() = VaryingType(6L)
            /**
             * Varying is of type `Transform3D`.
             *
             * Generated from Godot docs: VisualShader.VARYING_TYPE_TRANSFORM
             */
            val TRANSFORM: VaryingType get() = VaryingType(7L)
            /**
             * Represents the size of the `VaryingType` enum.
             *
             * Generated from Godot docs: VisualShader.VARYING_TYPE_MAX
             */
            val MAX: VaryingType get() = VaryingType(8L)
        }
    }

    companion object {
        const val NODE_ID_INVALID: Long = -1L
        const val NODE_ID_OUTPUT: Long = 0L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShader? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShader? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShader(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShader? =
            if (handle.address() == 0L) null else VisualShader(GodotHandle(handle))

        /** A new VisualShader, GDScript's `VisualShader.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShader =
            RefCounted.owned(VisualShader(GodotHandle(requireConstructed(ObjectCalls.constructObject("VisualShader"), "VisualShader"))))
    }

    private object Binds {
        private const val SET_MODE_HASH = 3978014962L
        @JvmField
        val setModeBind =
            ObjectCalls.getMethodBind("VisualShader", "set_mode", SET_MODE_HASH)

        private const val ADD_NODE_HASH = 1560769431L
        @JvmField
        val addNodeBind =
            ObjectCalls.getMethodBind("VisualShader", "add_node", ADD_NODE_HASH)

        private const val GET_NODE_HASH = 3784670312L
        @JvmField
        val getNodeBind =
            ObjectCalls.getMethodBind("VisualShader", "get_node", GET_NODE_HASH)

        private const val SET_NODE_POSITION_HASH = 2726660721L
        @JvmField
        val setNodePositionBind =
            ObjectCalls.getMethodBind("VisualShader", "set_node_position", SET_NODE_POSITION_HASH)

        private const val GET_NODE_POSITION_HASH = 2175036082L
        @JvmField
        val getNodePositionBind =
            ObjectCalls.getMethodBind("VisualShader", "get_node_position", GET_NODE_POSITION_HASH)

        private const val GET_NODE_LIST_HASH = 2370592410L
        @JvmField
        val getNodeListBind =
            ObjectCalls.getMethodBind("VisualShader", "get_node_list", GET_NODE_LIST_HASH)

        private const val GET_VALID_NODE_ID_HASH = 629467342L
        @JvmField
        val getValidNodeIdBind =
            ObjectCalls.getMethodBind("VisualShader", "get_valid_node_id", GET_VALID_NODE_ID_HASH)

        private const val REMOVE_NODE_HASH = 844050912L
        @JvmField
        val removeNodeBind =
            ObjectCalls.getMethodBind("VisualShader", "remove_node", REMOVE_NODE_HASH)

        private const val REPLACE_NODE_HASH = 3144735253L
        @JvmField
        val replaceNodeBind =
            ObjectCalls.getMethodBind("VisualShader", "replace_node", REPLACE_NODE_HASH)

        private const val IS_NODE_CONNECTION_HASH = 3922381898L
        @JvmField
        val isNodeConnectionBind =
            ObjectCalls.getMethodBind("VisualShader", "is_node_connection", IS_NODE_CONNECTION_HASH)

        private const val CAN_CONNECT_NODES_HASH = 3922381898L
        @JvmField
        val canConnectNodesBind =
            ObjectCalls.getMethodBind("VisualShader", "can_connect_nodes", CAN_CONNECT_NODES_HASH)

        private const val CONNECT_NODES_HASH = 3081049573L
        @JvmField
        val connectNodesBind =
            ObjectCalls.getMethodBind("VisualShader", "connect_nodes", CONNECT_NODES_HASH)

        private const val DISCONNECT_NODES_HASH = 2268060358L
        @JvmField
        val disconnectNodesBind =
            ObjectCalls.getMethodBind("VisualShader", "disconnect_nodes", DISCONNECT_NODES_HASH)

        private const val CONNECT_NODES_FORCED_HASH = 2268060358L
        @JvmField
        val connectNodesForcedBind =
            ObjectCalls.getMethodBind("VisualShader", "connect_nodes_forced", CONNECT_NODES_FORCED_HASH)

        private const val GET_NODE_CONNECTIONS_HASH = 1441964831L
        @JvmField
        val getNodeConnectionsBind =
            ObjectCalls.getMethodBind("VisualShader", "get_node_connections", GET_NODE_CONNECTIONS_HASH)

        private const val ATTACH_NODE_TO_FRAME_HASH = 2479945279L
        @JvmField
        val attachNodeToFrameBind =
            ObjectCalls.getMethodBind("VisualShader", "attach_node_to_frame", ATTACH_NODE_TO_FRAME_HASH)

        private const val DETACH_NODE_FROM_FRAME_HASH = 844050912L
        @JvmField
        val detachNodeFromFrameBind =
            ObjectCalls.getMethodBind("VisualShader", "detach_node_from_frame", DETACH_NODE_FROM_FRAME_HASH)

        private const val ADD_VARYING_HASH = 2084110726L
        @JvmField
        val addVaryingBind =
            ObjectCalls.getMethodBind("VisualShader", "add_varying", ADD_VARYING_HASH)

        private const val REMOVE_VARYING_HASH = 83702148L
        @JvmField
        val removeVaryingBind =
            ObjectCalls.getMethodBind("VisualShader", "remove_varying", REMOVE_VARYING_HASH)

        private const val HAS_VARYING_HASH = 3927539163L
        @JvmField
        val hasVaryingBind =
            ObjectCalls.getMethodBind("VisualShader", "has_varying", HAS_VARYING_HASH)

        private const val SET_GRAPH_OFFSET_HASH = 743155724L
        @JvmField
        val setGraphOffsetBind =
            ObjectCalls.getMethodBind("VisualShader", "set_graph_offset", SET_GRAPH_OFFSET_HASH)

        private const val GET_GRAPH_OFFSET_HASH = 3341600327L
        @JvmField
        val getGraphOffsetBind =
            ObjectCalls.getMethodBind("VisualShader", "get_graph_offset", GET_GRAPH_OFFSET_HASH)
    }
}
