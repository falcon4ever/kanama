package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * Generated from Godot docs: VisualShader
 */
class VisualShader(handle: MemorySegment) : Shader(handle) {
    var graphOffset: Vector2
        @JvmName("graphOffsetProperty")
        get() = getGraphOffset()
        @JvmName("setGraphOffsetProperty")
        set(value) = setGraphOffset(value)

    fun setMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setModeBind, handle, mode)
    }

    fun addNode(type: Long, node: VisualShaderNode?, position: Vector2, id: Int) {
        ObjectCalls.ptrcallWithLongObjectVector2IntArgs(addNodeBind, handle, type, node?.requireOpenHandle() ?: MemorySegment.NULL, position, id)
    }

    fun getNode(type: Long, id: Int): VisualShaderNode? {
        return VisualShaderNode.wrap(ObjectCalls.ptrcallWithLongAndIntArgsRetObject(getNodeBind, handle, type, id))
    }

    fun setNodePosition(type: Long, id: Int, position: Vector2) {
        ObjectCalls.ptrcallWithLongIntVector2Args(setNodePositionBind, handle, type, id, position)
    }

    fun getNodePosition(type: Long, id: Int): Vector2 {
        return ObjectCalls.ptrcallWithLongAndIntArgsRetVector2(getNodePositionBind, handle, type, id)
    }

    fun getNodeList(type: Long): List<Int> {
        return ObjectCalls.ptrcallWithLongArgRetPackedInt32List(getNodeListBind, handle, type)
    }

    fun getValidNodeId(type: Long): Int {
        return ObjectCalls.ptrcallWithLongArgRetInt(getValidNodeIdBind, handle, type)
    }

    fun removeNode(type: Long, id: Int) {
        ObjectCalls.ptrcallWithLongAndIntArgs(removeNodeBind, handle, type, id)
    }

    fun replaceNode(type: Long, id: Int, newClass: String) {
        ObjectCalls.ptrcallWithLongIntStringNameArgs(replaceNodeBind, handle, type, id, newClass)
    }

    fun isNodeConnection(type: Long, fromNode: Int, fromPort: Int, toNode: Int, toPort: Int): Boolean {
        return ObjectCalls.ptrcallWithLongAndFourIntArgsRetBool(isNodeConnectionBind, handle, type, fromNode, fromPort, toNode, toPort)
    }

    fun canConnectNodes(type: Long, fromNode: Int, fromPort: Int, toNode: Int, toPort: Int): Boolean {
        return ObjectCalls.ptrcallWithLongAndFourIntArgsRetBool(canConnectNodesBind, handle, type, fromNode, fromPort, toNode, toPort)
    }

    fun connectNodes(type: Long, fromNode: Int, fromPort: Int, toNode: Int, toPort: Int): Long {
        return ObjectCalls.ptrcallWithLongAndFourIntArgsRetLong(connectNodesBind, handle, type, fromNode, fromPort, toNode, toPort)
    }

    fun disconnectNodes(type: Long, fromNode: Int, fromPort: Int, toNode: Int, toPort: Int) {
        ObjectCalls.ptrcallWithLongAndFourIntArgs(disconnectNodesBind, handle, type, fromNode, fromPort, toNode, toPort)
    }

    fun connectNodesForced(type: Long, fromNode: Int, fromPort: Int, toNode: Int, toPort: Int) {
        ObjectCalls.ptrcallWithLongAndFourIntArgs(connectNodesForcedBind, handle, type, fromNode, fromPort, toNode, toPort)
    }

    fun getNodeConnections(type: Long): List<Map<String, Any?>> {
        return ObjectCalls.ptrcallWithLongArgRetDictionaryList(getNodeConnectionsBind, handle, type)
    }

    fun attachNodeToFrame(type: Long, id: Int, frame: Int) {
        ObjectCalls.ptrcallWithLongAndTwoIntArgs(attachNodeToFrameBind, handle, type, id, frame)
    }

    fun detachNodeFromFrame(type: Long, id: Int) {
        ObjectCalls.ptrcallWithLongAndIntArgs(detachNodeFromFrameBind, handle, type, id)
    }

    fun addVarying(name: String, mode: Long, type: Long) {
        ObjectCalls.ptrcallWithStringTwoLongArgs(addVaryingBind, handle, name, mode, type)
    }

    fun removeVarying(name: String) {
        ObjectCalls.ptrcallWithStringArg(removeVaryingBind, handle, name)
    }

    fun hasVarying(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(hasVaryingBind, handle, name)
    }

    fun setGraphOffset(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setGraphOffsetBind, handle, offset)
    }

    fun getGraphOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getGraphOffsetBind, handle)
    }

    companion object {
        const val NODE_ID_INVALID: Long = -1L
        const val NODE_ID_OUTPUT: Long = 0L
        const val TYPE_VERTEX: Long = 0L
        const val TYPE_FRAGMENT: Long = 1L
        const val TYPE_LIGHT: Long = 2L
        const val TYPE_START: Long = 3L
        const val TYPE_PROCESS: Long = 4L
        const val TYPE_COLLIDE: Long = 5L
        const val TYPE_START_CUSTOM: Long = 6L
        const val TYPE_PROCESS_CUSTOM: Long = 7L
        const val TYPE_SKY: Long = 8L
        const val TYPE_FOG: Long = 9L
        const val TYPE_TEXTURE_BLIT: Long = 10L
        const val TYPE_MAX: Long = 11L
        const val VARYING_MODE_VERTEX_TO_FRAG_LIGHT: Long = 0L
        const val VARYING_MODE_FRAG_TO_LIGHT: Long = 1L
        const val VARYING_MODE_MAX: Long = 2L
        const val VARYING_TYPE_FLOAT: Long = 0L
        const val VARYING_TYPE_INT: Long = 1L
        const val VARYING_TYPE_UINT: Long = 2L
        const val VARYING_TYPE_VECTOR_2D: Long = 3L
        const val VARYING_TYPE_VECTOR_3D: Long = 4L
        const val VARYING_TYPE_VECTOR_4D: Long = 5L
        const val VARYING_TYPE_BOOLEAN: Long = 6L
        const val VARYING_TYPE_TRANSFORM: Long = 7L
        const val VARYING_TYPE_MAX: Long = 8L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShader? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShader? =
            if (handle.address() == 0L) null else VisualShader(handle)

        private const val SET_MODE_HASH = 3978014962L
        private val setModeBind by lazy {
            ObjectCalls.getMethodBind("VisualShader", "set_mode", SET_MODE_HASH)
        }

        private const val ADD_NODE_HASH = 1560769431L
        private val addNodeBind by lazy {
            ObjectCalls.getMethodBind("VisualShader", "add_node", ADD_NODE_HASH)
        }

        private const val GET_NODE_HASH = 3784670312L
        private val getNodeBind by lazy {
            ObjectCalls.getMethodBind("VisualShader", "get_node", GET_NODE_HASH)
        }

        private const val SET_NODE_POSITION_HASH = 2726660721L
        private val setNodePositionBind by lazy {
            ObjectCalls.getMethodBind("VisualShader", "set_node_position", SET_NODE_POSITION_HASH)
        }

        private const val GET_NODE_POSITION_HASH = 2175036082L
        private val getNodePositionBind by lazy {
            ObjectCalls.getMethodBind("VisualShader", "get_node_position", GET_NODE_POSITION_HASH)
        }

        private const val GET_NODE_LIST_HASH = 2370592410L
        private val getNodeListBind by lazy {
            ObjectCalls.getMethodBind("VisualShader", "get_node_list", GET_NODE_LIST_HASH)
        }

        private const val GET_VALID_NODE_ID_HASH = 629467342L
        private val getValidNodeIdBind by lazy {
            ObjectCalls.getMethodBind("VisualShader", "get_valid_node_id", GET_VALID_NODE_ID_HASH)
        }

        private const val REMOVE_NODE_HASH = 844050912L
        private val removeNodeBind by lazy {
            ObjectCalls.getMethodBind("VisualShader", "remove_node", REMOVE_NODE_HASH)
        }

        private const val REPLACE_NODE_HASH = 3144735253L
        private val replaceNodeBind by lazy {
            ObjectCalls.getMethodBind("VisualShader", "replace_node", REPLACE_NODE_HASH)
        }

        private const val IS_NODE_CONNECTION_HASH = 3922381898L
        private val isNodeConnectionBind by lazy {
            ObjectCalls.getMethodBind("VisualShader", "is_node_connection", IS_NODE_CONNECTION_HASH)
        }

        private const val CAN_CONNECT_NODES_HASH = 3922381898L
        private val canConnectNodesBind by lazy {
            ObjectCalls.getMethodBind("VisualShader", "can_connect_nodes", CAN_CONNECT_NODES_HASH)
        }

        private const val CONNECT_NODES_HASH = 3081049573L
        private val connectNodesBind by lazy {
            ObjectCalls.getMethodBind("VisualShader", "connect_nodes", CONNECT_NODES_HASH)
        }

        private const val DISCONNECT_NODES_HASH = 2268060358L
        private val disconnectNodesBind by lazy {
            ObjectCalls.getMethodBind("VisualShader", "disconnect_nodes", DISCONNECT_NODES_HASH)
        }

        private const val CONNECT_NODES_FORCED_HASH = 2268060358L
        private val connectNodesForcedBind by lazy {
            ObjectCalls.getMethodBind("VisualShader", "connect_nodes_forced", CONNECT_NODES_FORCED_HASH)
        }

        private const val GET_NODE_CONNECTIONS_HASH = 1441964831L
        private val getNodeConnectionsBind by lazy {
            ObjectCalls.getMethodBind("VisualShader", "get_node_connections", GET_NODE_CONNECTIONS_HASH)
        }

        private const val ATTACH_NODE_TO_FRAME_HASH = 2479945279L
        private val attachNodeToFrameBind by lazy {
            ObjectCalls.getMethodBind("VisualShader", "attach_node_to_frame", ATTACH_NODE_TO_FRAME_HASH)
        }

        private const val DETACH_NODE_FROM_FRAME_HASH = 844050912L
        private val detachNodeFromFrameBind by lazy {
            ObjectCalls.getMethodBind("VisualShader", "detach_node_from_frame", DETACH_NODE_FROM_FRAME_HASH)
        }

        private const val ADD_VARYING_HASH = 2084110726L
        private val addVaryingBind by lazy {
            ObjectCalls.getMethodBind("VisualShader", "add_varying", ADD_VARYING_HASH)
        }

        private const val REMOVE_VARYING_HASH = 83702148L
        private val removeVaryingBind by lazy {
            ObjectCalls.getMethodBind("VisualShader", "remove_varying", REMOVE_VARYING_HASH)
        }

        private const val HAS_VARYING_HASH = 3927539163L
        private val hasVaryingBind by lazy {
            ObjectCalls.getMethodBind("VisualShader", "has_varying", HAS_VARYING_HASH)
        }

        private const val SET_GRAPH_OFFSET_HASH = 743155724L
        private val setGraphOffsetBind by lazy {
            ObjectCalls.getMethodBind("VisualShader", "set_graph_offset", SET_GRAPH_OFFSET_HASH)
        }

        private const val GET_GRAPH_OFFSET_HASH = 3341600327L
        private val getGraphOffsetBind by lazy {
            ObjectCalls.getMethodBind("VisualShader", "get_graph_offset", GET_GRAPH_OFFSET_HASH)
        }
    }
}
