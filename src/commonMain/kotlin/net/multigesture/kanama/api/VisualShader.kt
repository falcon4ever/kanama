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
 * Generated from Godot docs: VisualShader
 */
class VisualShader(handle: GodotHandle) : Shader(handle) {
    var graphOffset: Vector2
        @JvmName("graphOffsetProperty")
        get() = getGraphOffset()
        @JvmName("setGraphOffsetProperty")
        set(value) = setGraphOffset(value)

    fun setMode(mode: Shader.Mode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setModeBind, segment, mode.value)
    }

    fun addNode(type: VisualShader.Type, node: VisualShaderNode?, position: Vector2, id: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithLongObjectVector2IntArgs(Binds.addNodeBind, segment, type.value, node?.requireOpenHandle() ?: NULL_SEGMENT, position, id)
    }

    fun getNode(type: VisualShader.Type, id: Int): VisualShaderNode? {
        checkOpen()
        return VisualShaderNode.wrapOwned(ObjectCalls.ptrcallWithLongAndIntArgsRetObject(Binds.getNodeBind, segment, type.value, id))
    }

    fun setNodePosition(type: VisualShader.Type, id: Int, position: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithLongIntVector2Args(Binds.setNodePositionBind, segment, type.value, id, position)
    }

    fun getNodePosition(type: VisualShader.Type, id: Int): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithLongAndIntArgsRetVector2(Binds.getNodePositionBind, segment, type.value, id)
    }

    fun getNodeList(type: VisualShader.Type): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetPackedInt32List(Binds.getNodeListBind, segment, type.value)
    }

    fun getValidNodeId(type: VisualShader.Type): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetInt(Binds.getValidNodeIdBind, segment, type.value)
    }

    fun removeNode(type: VisualShader.Type, id: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndIntArgs(Binds.removeNodeBind, segment, type.value, id)
    }

    fun replaceNode(type: VisualShader.Type, id: Int, newClass: String) {
        checkOpen()
        ObjectCalls.ptrcallWithLongIntStringNameArgs(Binds.replaceNodeBind, segment, type.value, id, newClass)
    }

    fun isNodeConnection(type: VisualShader.Type, fromNode: Int, fromPort: Int, toNode: Int, toPort: Int): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithLongAndFourIntArgsRetBool(Binds.isNodeConnectionBind, segment, type.value, fromNode, fromPort, toNode, toPort)
    }

    fun canConnectNodes(type: VisualShader.Type, fromNode: Int, fromPort: Int, toNode: Int, toPort: Int): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithLongAndFourIntArgsRetBool(Binds.canConnectNodesBind, segment, type.value, fromNode, fromPort, toNode, toPort)
    }

    fun connectNodes(type: VisualShader.Type, fromNode: Int, fromPort: Int, toNode: Int, toPort: Int): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithLongAndFourIntArgsRetLong(Binds.connectNodesBind, segment, type.value, fromNode, fromPort, toNode, toPort))
    }

    fun disconnectNodes(type: VisualShader.Type, fromNode: Int, fromPort: Int, toNode: Int, toPort: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndFourIntArgs(Binds.disconnectNodesBind, segment, type.value, fromNode, fromPort, toNode, toPort)
    }

    fun connectNodesForced(type: VisualShader.Type, fromNode: Int, fromPort: Int, toNode: Int, toPort: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndFourIntArgs(Binds.connectNodesForcedBind, segment, type.value, fromNode, fromPort, toNode, toPort)
    }

    fun getNodeConnections(type: VisualShader.Type): List<Map<String, Any?>> {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetDictionaryList(Binds.getNodeConnectionsBind, segment, type.value)
    }

    fun attachNodeToFrame(type: VisualShader.Type, id: Int, frame: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndTwoIntArgs(Binds.attachNodeToFrameBind, segment, type.value, id, frame)
    }

    fun detachNodeFromFrame(type: VisualShader.Type, id: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndIntArgs(Binds.detachNodeFromFrameBind, segment, type.value, id)
    }

    fun addVarying(name: String, mode: VisualShader.VaryingMode, type: VisualShader.VaryingType) {
        checkOpen()
        ObjectCalls.ptrcallWithStringTwoLongArgs(Binds.addVaryingBind, segment, name, mode.value, type.value)
    }

    fun removeVarying(name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.removeVaryingBind, segment, name)
    }

    fun hasVarying(name: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetBool(Binds.hasVaryingBind, segment, name)
    }

    fun setGraphOffset(offset: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(Binds.setGraphOffsetBind, segment, offset)
    }

    fun getGraphOffset(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getGraphOffsetBind, segment)
    }

    @JvmInline
    value class Type(override val value: Long) : GodotEnumValue {
        companion object {
            val VERTEX: Type get() = Type(0L)
            val FRAGMENT: Type get() = Type(1L)
            val LIGHT: Type get() = Type(2L)
            val START: Type get() = Type(3L)
            val PROCESS: Type get() = Type(4L)
            val COLLIDE: Type get() = Type(5L)
            val START_CUSTOM: Type get() = Type(6L)
            val PROCESS_CUSTOM: Type get() = Type(7L)
            val SKY: Type get() = Type(8L)
            val FOG: Type get() = Type(9L)
            val TEXTURE_BLIT: Type get() = Type(10L)
            val MAX: Type get() = Type(11L)
        }
    }

    @JvmInline
    value class VaryingMode(override val value: Long) : GodotEnumValue {
        companion object {
            val VERTEX_TO_FRAG_LIGHT: VaryingMode get() = VaryingMode(0L)
            val FRAG_TO_LIGHT: VaryingMode get() = VaryingMode(1L)
            val MAX: VaryingMode get() = VaryingMode(2L)
        }
    }

    @JvmInline
    value class VaryingType(override val value: Long) : GodotEnumValue {
        companion object {
            val FLOAT: VaryingType get() = VaryingType(0L)
            val INT: VaryingType get() = VaryingType(1L)
            val UINT: VaryingType get() = VaryingType(2L)
            val VECTOR_2D: VaryingType get() = VaryingType(3L)
            val VECTOR_3D: VaryingType get() = VaryingType(4L)
            val VECTOR_4D: VaryingType get() = VaryingType(5L)
            val BOOLEAN: VaryingType get() = VaryingType(6L)
            val TRANSFORM: VaryingType get() = VaryingType(7L)
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
