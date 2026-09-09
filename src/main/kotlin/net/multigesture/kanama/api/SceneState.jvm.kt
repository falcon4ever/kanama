package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath

// GENERATED desktop/Android companion for SceneState (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP SceneState waits on: ptrcallWithIntAndBoolArgRetNodePath,
//   ptrcallWithIntArgRetArray, ptrcallWithIntArgRetNodePath, ptrcallWithIntArgRetPackedStringList,
//   ptrcallWithIntArgRetString, ptrcallWithIntArgRetStringName, ptrcallWithTwoIntArgsRetStringName,
//   ptrcallWithTwoIntArgsRetVariantScalar
// Index: docs/contributing/ios-shape-gap.md

/**
 * Returns the type of the node at `idx`.
 *
 * Generated from Godot docs: SceneState.get_node_type
 */
fun SceneState.getNodeType(idx: Int): String {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetStringName(getNodeTypeBind, handle, idx)
}

/**
 * Returns the name of the node at `idx`.
 *
 * Generated from Godot docs: SceneState.get_node_name
 */
fun SceneState.getNodeName(idx: Int): String {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetStringName(getNodeNameBind, handle, idx)
}

/**
 * Returns the path to the node at `idx`. If `for_parent` is `true`, returns the path of the `idx`
 * node's parent instead.
 *
 * Generated from Godot docs: SceneState.get_node_path
 */
fun SceneState.getNodePath(idx: Int, forParent: Boolean = false): NodePath {
    checkOpen()
    return ObjectCalls.ptrcallWithIntAndBoolArgRetNodePath(getNodePathBind, handle, idx, forParent)
}

/**
 * Returns the path to the owner of the node at `idx`, relative to the root node.
 *
 * Generated from Godot docs: SceneState.get_node_owner_path
 */
fun SceneState.getNodeOwnerPath(idx: Int): NodePath {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetNodePath(getNodeOwnerPathBind, handle, idx)
}

/**
 * Returns the path to the represented scene file if the node at `idx` is an `InstancePlaceholder`.
 *
 * Generated from Godot docs: SceneState.get_node_instance_placeholder
 */
fun SceneState.getNodeInstancePlaceholder(idx: Int): String {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetString(getNodeInstancePlaceholderBind, handle, idx)
}

/**
 * Returns the list of group names associated with the node at `idx`.
 *
 * Generated from Godot docs: SceneState.get_node_groups
 */
fun SceneState.getNodeGroups(idx: Int): List<String> {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetPackedStringList(getNodeGroupsBind, handle, idx)
}

/**
 * Returns the name of the property at `prop_idx` for the node at `idx`.
 *
 * Generated from Godot docs: SceneState.get_node_property_name
 */
fun SceneState.getNodePropertyName(idx: Int, propIdx: Int): String {
    checkOpen()
    return ObjectCalls.ptrcallWithTwoIntArgsRetStringName(getNodePropertyNameBind, handle, idx, propIdx)
}

/**
 * Returns the value of the property at `prop_idx` for the node at `idx`.
 *
 * Generated from Godot docs: SceneState.get_node_property_value
 */
fun SceneState.getNodePropertyValue(idx: Int, propIdx: Int): Any? {
    checkOpen()
    return ObjectCalls.ptrcallWithTwoIntArgsRetVariantScalar(getNodePropertyValueBind, handle, idx, propIdx)
}

/**
 * Returns the path to the node that owns the signal at `idx`, relative to the root node.
 *
 * Generated from Godot docs: SceneState.get_connection_source
 */
fun SceneState.getConnectionSource(idx: Int): NodePath {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetNodePath(getConnectionSourceBind, handle, idx)
}

/**
 * Returns the name of the signal at `idx`.
 *
 * Generated from Godot docs: SceneState.get_connection_signal
 */
fun SceneState.getConnectionSignal(idx: Int): String {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetStringName(getConnectionSignalBind, handle, idx)
}

/**
 * Returns the path to the node that owns the method connected to the signal at `idx`, relative to
 * the root node.
 *
 * Generated from Godot docs: SceneState.get_connection_target
 */
fun SceneState.getConnectionTarget(idx: Int): NodePath {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetNodePath(getConnectionTargetBind, handle, idx)
}

/**
 * Returns the method connected to the signal at `idx`.
 *
 * Generated from Godot docs: SceneState.get_connection_method
 */
fun SceneState.getConnectionMethod(idx: Int): String {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetStringName(getConnectionMethodBind, handle, idx)
}

/**
 * Returns the list of bound parameters for the signal at `idx`.
 *
 * Generated from Godot docs: SceneState.get_connection_binds
 */
fun SceneState.getConnectionBinds(idx: Int): List<Any?> {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetArray(getConnectionBindsBind, handle, idx)
}

private const val GET_NODE_TYPE_HASH = 659327637L
private val getNodeTypeBind by lazy {
    ObjectCalls.getMethodBind("SceneState", "get_node_type", GET_NODE_TYPE_HASH)
}

private const val GET_NODE_NAME_HASH = 659327637L
private val getNodeNameBind by lazy {
    ObjectCalls.getMethodBind("SceneState", "get_node_name", GET_NODE_NAME_HASH)
}

private const val GET_NODE_PATH_HASH = 2272487792L
private val getNodePathBind by lazy {
    ObjectCalls.getMethodBind("SceneState", "get_node_path", GET_NODE_PATH_HASH)
}

private const val GET_NODE_OWNER_PATH_HASH = 408788394L
private val getNodeOwnerPathBind by lazy {
    ObjectCalls.getMethodBind("SceneState", "get_node_owner_path", GET_NODE_OWNER_PATH_HASH)
}

private const val GET_NODE_INSTANCE_PLACEHOLDER_HASH = 844755477L
private val getNodeInstancePlaceholderBind by lazy {
    ObjectCalls.getMethodBind("SceneState", "get_node_instance_placeholder", GET_NODE_INSTANCE_PLACEHOLDER_HASH)
}

private const val GET_NODE_GROUPS_HASH = 647634434L
private val getNodeGroupsBind by lazy {
    ObjectCalls.getMethodBind("SceneState", "get_node_groups", GET_NODE_GROUPS_HASH)
}

private const val GET_NODE_PROPERTY_NAME_HASH = 351665558L
private val getNodePropertyNameBind by lazy {
    ObjectCalls.getMethodBind("SceneState", "get_node_property_name", GET_NODE_PROPERTY_NAME_HASH)
}

private const val GET_NODE_PROPERTY_VALUE_HASH = 678354945L
private val getNodePropertyValueBind by lazy {
    ObjectCalls.getMethodBind("SceneState", "get_node_property_value", GET_NODE_PROPERTY_VALUE_HASH)
}

private const val GET_CONNECTION_SOURCE_HASH = 408788394L
private val getConnectionSourceBind by lazy {
    ObjectCalls.getMethodBind("SceneState", "get_connection_source", GET_CONNECTION_SOURCE_HASH)
}

private const val GET_CONNECTION_SIGNAL_HASH = 659327637L
private val getConnectionSignalBind by lazy {
    ObjectCalls.getMethodBind("SceneState", "get_connection_signal", GET_CONNECTION_SIGNAL_HASH)
}

private const val GET_CONNECTION_TARGET_HASH = 408788394L
private val getConnectionTargetBind by lazy {
    ObjectCalls.getMethodBind("SceneState", "get_connection_target", GET_CONNECTION_TARGET_HASH)
}

private const val GET_CONNECTION_METHOD_HASH = 659327637L
private val getConnectionMethodBind by lazy {
    ObjectCalls.getMethodBind("SceneState", "get_connection_method", GET_CONNECTION_METHOD_HASH)
}

private const val GET_CONNECTION_BINDS_HASH = 663333327L
private val getConnectionBindsBind by lazy {
    ObjectCalls.getMethodBind("SceneState", "get_connection_binds", GET_CONNECTION_BINDS_HASH)
}
