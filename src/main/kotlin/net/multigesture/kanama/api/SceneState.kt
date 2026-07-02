package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath

/**
 * Provides access to a scene file's information.
 *
 * Generated from Godot docs: SceneState
 */
class SceneState(handle: MemorySegment) : RefCounted(handle) {
    fun getPath(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getPathBind, handle)
    }

    fun getBaseSceneState(): SceneState? {
        return SceneState.wrap(ObjectCalls.ptrcallNoArgsRetObject(getBaseSceneStateBind, handle))
    }

    fun getNodeCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getNodeCountBind, handle)
    }

    fun getNodeType(idx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetStringName(getNodeTypeBind, handle, idx)
    }

    fun getNodeName(idx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetStringName(getNodeNameBind, handle, idx)
    }

    fun getNodePath(idx: Int, forParent: Boolean = false): NodePath {
        return ObjectCalls.ptrcallWithIntAndBoolArgRetNodePath(getNodePathBind, handle, idx, forParent)
    }

    fun getNodeOwnerPath(idx: Int): NodePath {
        return ObjectCalls.ptrcallWithIntArgRetNodePath(getNodeOwnerPathBind, handle, idx)
    }

    fun isNodeInstancePlaceholder(idx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isNodeInstancePlaceholderBind, handle, idx)
    }

    fun getNodeInstancePlaceholder(idx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getNodeInstancePlaceholderBind, handle, idx)
    }

    fun getNodeInstance(idx: Int): PackedScene? {
        return PackedScene.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getNodeInstanceBind, handle, idx))
    }

    fun getNodeGroups(idx: Int): List<String> {
        return ObjectCalls.ptrcallWithIntArgRetPackedStringList(getNodeGroupsBind, handle, idx)
    }

    fun getNodeIndex(idx: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getNodeIndexBind, handle, idx)
    }

    fun getNodePropertyCount(idx: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getNodePropertyCountBind, handle, idx)
    }

    fun getNodePropertyName(idx: Int, propIdx: Int): String {
        return ObjectCalls.ptrcallWithTwoIntArgsRetStringName(getNodePropertyNameBind, handle, idx, propIdx)
    }

    fun getNodePropertyValue(idx: Int, propIdx: Int): Any? {
        return ObjectCalls.ptrcallWithTwoIntArgsRetVariantScalar(getNodePropertyValueBind, handle, idx, propIdx)
    }

    fun getConnectionCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getConnectionCountBind, handle)
    }

    fun getConnectionSource(idx: Int): NodePath {
        return ObjectCalls.ptrcallWithIntArgRetNodePath(getConnectionSourceBind, handle, idx)
    }

    fun getConnectionSignal(idx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetStringName(getConnectionSignalBind, handle, idx)
    }

    fun getConnectionTarget(idx: Int): NodePath {
        return ObjectCalls.ptrcallWithIntArgRetNodePath(getConnectionTargetBind, handle, idx)
    }

    fun getConnectionMethod(idx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetStringName(getConnectionMethodBind, handle, idx)
    }

    fun getConnectionFlags(idx: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getConnectionFlagsBind, handle, idx)
    }

    fun getConnectionBinds(idx: Int): List<Any?> {
        return ObjectCalls.ptrcallWithIntArgRetArray(getConnectionBindsBind, handle, idx)
    }

    fun getConnectionUnbinds(idx: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getConnectionUnbindsBind, handle, idx)
    }

    companion object {
        const val GEN_EDIT_STATE_DISABLED: Long = 0L
        const val GEN_EDIT_STATE_INSTANCE: Long = 1L
        const val GEN_EDIT_STATE_MAIN: Long = 2L
        const val GEN_EDIT_STATE_MAIN_INHERITED: Long = 3L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): SceneState? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SceneState? =
            if (handle.address() == 0L) null else SceneState(handle)

        private const val GET_PATH_HASH = 201670096L
        private val getPathBind by lazy {
            ObjectCalls.getMethodBind("SceneState", "get_path", GET_PATH_HASH)
        }

        private const val GET_BASE_SCENE_STATE_HASH = 3479783971L
        private val getBaseSceneStateBind by lazy {
            ObjectCalls.getMethodBind("SceneState", "get_base_scene_state", GET_BASE_SCENE_STATE_HASH)
        }

        private const val GET_NODE_COUNT_HASH = 3905245786L
        private val getNodeCountBind by lazy {
            ObjectCalls.getMethodBind("SceneState", "get_node_count", GET_NODE_COUNT_HASH)
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

        private const val IS_NODE_INSTANCE_PLACEHOLDER_HASH = 1116898809L
        private val isNodeInstancePlaceholderBind by lazy {
            ObjectCalls.getMethodBind("SceneState", "is_node_instance_placeholder", IS_NODE_INSTANCE_PLACEHOLDER_HASH)
        }

        private const val GET_NODE_INSTANCE_PLACEHOLDER_HASH = 844755477L
        private val getNodeInstancePlaceholderBind by lazy {
            ObjectCalls.getMethodBind("SceneState", "get_node_instance_placeholder", GET_NODE_INSTANCE_PLACEHOLDER_HASH)
        }

        private const val GET_NODE_INSTANCE_HASH = 511017218L
        private val getNodeInstanceBind by lazy {
            ObjectCalls.getMethodBind("SceneState", "get_node_instance", GET_NODE_INSTANCE_HASH)
        }

        private const val GET_NODE_GROUPS_HASH = 647634434L
        private val getNodeGroupsBind by lazy {
            ObjectCalls.getMethodBind("SceneState", "get_node_groups", GET_NODE_GROUPS_HASH)
        }

        private const val GET_NODE_INDEX_HASH = 923996154L
        private val getNodeIndexBind by lazy {
            ObjectCalls.getMethodBind("SceneState", "get_node_index", GET_NODE_INDEX_HASH)
        }

        private const val GET_NODE_PROPERTY_COUNT_HASH = 923996154L
        private val getNodePropertyCountBind by lazy {
            ObjectCalls.getMethodBind("SceneState", "get_node_property_count", GET_NODE_PROPERTY_COUNT_HASH)
        }

        private const val GET_NODE_PROPERTY_NAME_HASH = 351665558L
        private val getNodePropertyNameBind by lazy {
            ObjectCalls.getMethodBind("SceneState", "get_node_property_name", GET_NODE_PROPERTY_NAME_HASH)
        }

        private const val GET_NODE_PROPERTY_VALUE_HASH = 678354945L
        private val getNodePropertyValueBind by lazy {
            ObjectCalls.getMethodBind("SceneState", "get_node_property_value", GET_NODE_PROPERTY_VALUE_HASH)
        }

        private const val GET_CONNECTION_COUNT_HASH = 3905245786L
        private val getConnectionCountBind by lazy {
            ObjectCalls.getMethodBind("SceneState", "get_connection_count", GET_CONNECTION_COUNT_HASH)
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

        private const val GET_CONNECTION_FLAGS_HASH = 923996154L
        private val getConnectionFlagsBind by lazy {
            ObjectCalls.getMethodBind("SceneState", "get_connection_flags", GET_CONNECTION_FLAGS_HASH)
        }

        private const val GET_CONNECTION_BINDS_HASH = 663333327L
        private val getConnectionBindsBind by lazy {
            ObjectCalls.getMethodBind("SceneState", "get_connection_binds", GET_CONNECTION_BINDS_HASH)
        }

        private const val GET_CONNECTION_UNBINDS_HASH = 923996154L
        private val getConnectionUnbindsBind by lazy {
            ObjectCalls.getMethodBind("SceneState", "get_connection_unbinds", GET_CONNECTION_UNBINDS_HASH)
        }
    }
}
