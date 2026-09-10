package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Vector2

// GENERATED desktop/Android companion for GraphEdit (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP GraphEdit waits on: ptrcallNoArgsRetDictionary, ptrcallNoArgsRetDictionaryList,
//   ptrcallWithDictionaryArg, ptrcallWithDictionaryListArg, ptrcallWithRect2ArgRetDictionaryList,
//   ptrcallWithStringNameArgRetDictionaryList, ptrcallWithStringNameArgRetStringNameList,
//   ptrcallWithVector2AndDoubleArgRetDictionary
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * The connections between `GraphNode`s. A connection is represented as a `Dictionary` in the form
 * of:
 *
 * Generated from Godot docs: GraphEdit.set_connections
 */
fun GraphEdit.setConnections(connections: List<Map<String, Any?>>) {
    ObjectCalls.ptrcallWithDictionaryListArg(setConnectionsBind, handle, connections)
}

/**
 * The connections between `GraphNode`s. A connection is represented as a `Dictionary` in the form
 * of:
 *
 * Generated from Godot docs: GraphEdit.get_connection_list
 */
fun GraphEdit.getConnectionList(): List<Map<String, Any?>> {
    return ObjectCalls.ptrcallNoArgsRetDictionaryList(getConnectionListBind, handle)
}

/**
 * Returns the closest connection to the given point in screen space. If no connection is found
 * within `max_distance` pixels, an empty `Dictionary` is returned. A connection is represented as
 * a `Dictionary` in the form of:
 *
 * Generated from Godot docs: GraphEdit.get_closest_connection_at_point
 */
fun GraphEdit.getClosestConnectionAtPoint(point: Vector2, maxDistance: Double = 4.0): Map<String, Any?> {
    return ObjectCalls.ptrcallWithVector2AndDoubleArgRetDictionary(getClosestConnectionAtPointBind, handle, point, maxDistance)
}

/**
 * Returns an `Array` containing a list of all connections for `node`. A connection is represented
 * as a `Dictionary` in the form of:
 *
 * Generated from Godot docs: GraphEdit.get_connection_list_from_node
 */
fun GraphEdit.getConnectionListFromNode(node: String): List<Map<String, Any?>> {
    return ObjectCalls.ptrcallWithStringNameArgRetDictionaryList(getConnectionListFromNodeBind, handle, node)
}

/**
 * Returns an `Array` containing the list of connections that intersect with the given `Rect2`. A
 * connection is represented as a `Dictionary` in the form of:
 *
 * Generated from Godot docs: GraphEdit.get_connections_intersecting_with_rect
 */
fun GraphEdit.getConnectionsIntersectingWithRect(rect: Rect2): List<Map<String, Any?>> {
    return ObjectCalls.ptrcallWithRect2ArgRetDictionaryList(getConnectionsIntersectingWithRectBind, handle, rect)
}

/**
 * Returns an array of node names that are attached to the `GraphFrame` with the given name.
 *
 * Generated from Godot docs: GraphEdit.get_attached_nodes_of_frame
 */
fun GraphEdit.getAttachedNodesOfFrame(frame: String): List<String> {
    return ObjectCalls.ptrcallWithStringNameArgRetStringNameList(getAttachedNodesOfFrameBind, handle, frame)
}

/**
 * `Dictionary` of human-readable port type names.
 *
 * Generated from Godot docs: GraphEdit.set_type_names
 */
fun GraphEdit.setTypeNames(typeNames: Map<String, Any?>) {
    ObjectCalls.ptrcallWithDictionaryArg(setTypeNamesBind, handle, typeNames)
}

/**
 * `Dictionary` of human-readable port type names.
 *
 * Generated from Godot docs: GraphEdit.get_type_names
 */
fun GraphEdit.getTypeNames(): Map<String, Any?> {
    return ObjectCalls.ptrcallNoArgsRetDictionary(getTypeNamesBind, handle)
}

var GraphEdit.typeNames: Map<String, Any?>
    @JvmName("typeNamesProperty")
    get() = getTypeNames()
    @JvmName("setTypeNamesProperty")
    set(value) = setTypeNames(value)

var GraphEdit.connections: List<Map<String, Any?>>
    @JvmName("connectionsProperty")
    get() = getConnectionList()
    @JvmName("setConnectionsProperty")
    set(value) = setConnections(value)

private const val SET_CONNECTIONS_HASH = 381264803L
private val setConnectionsBind by lazy {
    ObjectCalls.getMethodBind("GraphEdit", "set_connections", SET_CONNECTIONS_HASH)
}

private const val GET_CONNECTION_LIST_HASH = 3995934104L
private val getConnectionListBind by lazy {
    ObjectCalls.getMethodBind("GraphEdit", "get_connection_list", GET_CONNECTION_LIST_HASH)
}

private const val GET_CLOSEST_CONNECTION_AT_POINT_HASH = 453879819L
private val getClosestConnectionAtPointBind by lazy {
    ObjectCalls.getMethodBind("GraphEdit", "get_closest_connection_at_point", GET_CLOSEST_CONNECTION_AT_POINT_HASH)
}

private const val GET_CONNECTION_LIST_FROM_NODE_HASH = 3147814860L
private val getConnectionListFromNodeBind by lazy {
    ObjectCalls.getMethodBind("GraphEdit", "get_connection_list_from_node", GET_CONNECTION_LIST_FROM_NODE_HASH)
}

private const val GET_CONNECTIONS_INTERSECTING_WITH_RECT_HASH = 2709748719L
private val getConnectionsIntersectingWithRectBind by lazy {
    ObjectCalls.getMethodBind("GraphEdit", "get_connections_intersecting_with_rect", GET_CONNECTIONS_INTERSECTING_WITH_RECT_HASH)
}

private const val GET_ATTACHED_NODES_OF_FRAME_HASH = 689397652L
private val getAttachedNodesOfFrameBind by lazy {
    ObjectCalls.getMethodBind("GraphEdit", "get_attached_nodes_of_frame", GET_ATTACHED_NODES_OF_FRAME_HASH)
}

private const val SET_TYPE_NAMES_HASH = 4155329257L
private val setTypeNamesBind by lazy {
    ObjectCalls.getMethodBind("GraphEdit", "set_type_names", SET_TYPE_NAMES_HASH)
}

private const val GET_TYPE_NAMES_HASH = 3102165223L
private val getTypeNamesBind by lazy {
    ObjectCalls.getMethodBind("GraphEdit", "get_type_names", GET_TYPE_NAMES_HASH)
}
