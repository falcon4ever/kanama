package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.NodePath

/**
 * Provides access to a scene file's information.
 *
 * Generated from Godot docs: SceneState
 */
class SceneState(handle: GodotHandle) : RefCounted(handle) {
    /**
     * Returns the resource path to the represented `PackedScene`.
     *
     * Generated from Godot docs: SceneState.get_path
     */
    fun getPath(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getPathBind, segment)
    }

    /**
     * Returns the `SceneState` of the scene that this scene inherits from, or `null` if it doesn't
     * inherit from any scene.
     *
     * Generated from Godot docs: SceneState.get_base_scene_state
     */
    fun getBaseSceneState(): SceneState? {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetObject(getBaseSceneStateBind, segment)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return SceneState.wrap(ret)
    }

    /**
     * Returns the number of nodes in the scene. The `idx` argument used to query node data in other
     * `get_node_*` methods in the interval `[0, get_node_count() - 1]`.
     *
     * Generated from Godot docs: SceneState.get_node_count
     */
    fun getNodeCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getNodeCountBind, segment)
    }

    /**
     * Returns the type of the node at `idx`.
     *
     * Generated from Godot docs: SceneState.get_node_type
     */
    fun getNodeType(idx: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetStringName(getNodeTypeBind, segment, idx)
    }

    /**
     * Returns the name of the node at `idx`.
     *
     * Generated from Godot docs: SceneState.get_node_name
     */
    fun getNodeName(idx: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetStringName(getNodeNameBind, segment, idx)
    }

    /**
     * Returns the path to the node at `idx`. If `for_parent` is `true`, returns the path of the `idx`
     * node's parent instead.
     *
     * Generated from Godot docs: SceneState.get_node_path
     */
    fun getNodePath(idx: Int, forParent: Boolean = false): NodePath {
        checkOpen()
        return ObjectCalls.ptrcallWithIntAndBoolArgRetNodePath(getNodePathBind, segment, idx, forParent)
    }

    /**
     * Returns the path to the owner of the node at `idx`, relative to the root node.
     *
     * Generated from Godot docs: SceneState.get_node_owner_path
     */
    fun getNodeOwnerPath(idx: Int): NodePath {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetNodePath(getNodeOwnerPathBind, segment, idx)
    }

    /**
     * Returns `true` if the node at `idx` is an `InstancePlaceholder`.
     *
     * Generated from Godot docs: SceneState.is_node_instance_placeholder
     */
    fun isNodeInstancePlaceholder(idx: Int): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetBool(isNodeInstancePlaceholderBind, segment, idx)
    }

    /**
     * Returns the path to the represented scene file if the node at `idx` is an `InstancePlaceholder`.
     *
     * Generated from Godot docs: SceneState.get_node_instance_placeholder
     */
    fun getNodeInstancePlaceholder(idx: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetString(getNodeInstancePlaceholderBind, segment, idx)
    }

    /**
     * Returns a `PackedScene` for the node at `idx` (i.e. the whole branch starting at this node, with
     * its child nodes and resources), or `null` if the node is not an instance.
     *
     * Generated from Godot docs: SceneState.get_node_instance
     */
    fun getNodeInstance(idx: Int): PackedScene? {
        checkOpen()
        return PackedScene.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getNodeInstanceBind, segment, idx))
    }

    /**
     * Returns the list of group names associated with the node at `idx`.
     *
     * Generated from Godot docs: SceneState.get_node_groups
     */
    fun getNodeGroups(idx: Int): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetPackedStringList(getNodeGroupsBind, segment, idx)
    }

    /**
     * Returns the node's index, which is its position relative to its siblings. This is only relevant
     * and saved in scenes for cases where new nodes are added to an instantiated or inherited scene
     * among siblings from the base scene. Despite the name, this index is not related to the `idx`
     * argument used here and in other methods.
     *
     * Generated from Godot docs: SceneState.get_node_index
     */
    fun getNodeIndex(idx: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(getNodeIndexBind, segment, idx)
    }

    /**
     * Returns the number of exported or overridden properties for the node at `idx`. The `prop_idx`
     * argument used to query node property data in other `get_node_property_*` methods in the interval
     * `[0, get_node_property_count() - 1]`.
     *
     * Generated from Godot docs: SceneState.get_node_property_count
     */
    fun getNodePropertyCount(idx: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(getNodePropertyCountBind, segment, idx)
    }

    /**
     * Returns the name of the property at `prop_idx` for the node at `idx`.
     *
     * Generated from Godot docs: SceneState.get_node_property_name
     */
    fun getNodePropertyName(idx: Int, propIdx: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetStringName(getNodePropertyNameBind, segment, idx, propIdx)
    }

    /**
     * Returns the value of the property at `prop_idx` for the node at `idx`.
     *
     * Generated from Godot docs: SceneState.get_node_property_value
     */
    fun getNodePropertyValue(idx: Int, propIdx: Int): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetVariantScalar(getNodePropertyValueBind, segment, idx, propIdx)
    }

    /**
     * Returns the number of signal connections in the scene. The `idx` argument used to query
     * connection metadata in other `get_connection_*` methods in the interval `[0,
     * get_connection_count() - 1]`.
     *
     * Generated from Godot docs: SceneState.get_connection_count
     */
    fun getConnectionCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getConnectionCountBind, segment)
    }

    /**
     * Returns the path to the node that owns the signal at `idx`, relative to the root node.
     *
     * Generated from Godot docs: SceneState.get_connection_source
     */
    fun getConnectionSource(idx: Int): NodePath {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetNodePath(getConnectionSourceBind, segment, idx)
    }

    /**
     * Returns the name of the signal at `idx`.
     *
     * Generated from Godot docs: SceneState.get_connection_signal
     */
    fun getConnectionSignal(idx: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetStringName(getConnectionSignalBind, segment, idx)
    }

    /**
     * Returns the path to the node that owns the method connected to the signal at `idx`, relative to
     * the root node.
     *
     * Generated from Godot docs: SceneState.get_connection_target
     */
    fun getConnectionTarget(idx: Int): NodePath {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetNodePath(getConnectionTargetBind, segment, idx)
    }

    /**
     * Returns the method connected to the signal at `idx`.
     *
     * Generated from Godot docs: SceneState.get_connection_method
     */
    fun getConnectionMethod(idx: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetStringName(getConnectionMethodBind, segment, idx)
    }

    /**
     * Returns the connection flags for the signal at `idx`. See `Object.ConnectFlags` constants.
     *
     * Generated from Godot docs: SceneState.get_connection_flags
     */
    fun getConnectionFlags(idx: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(getConnectionFlagsBind, segment, idx)
    }

    /**
     * Returns the list of bound parameters for the signal at `idx`.
     *
     * Generated from Godot docs: SceneState.get_connection_binds
     */
    fun getConnectionBinds(idx: Int): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetArray(getConnectionBindsBind, segment, idx)
    }

    /**
     * Returns the number of unbound parameters for the signal at `idx`.
     *
     * Generated from Godot docs: SceneState.get_connection_unbinds
     */
    fun getConnectionUnbinds(idx: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(getConnectionUnbindsBind, segment, idx)
    }

    companion object {
        const val GEN_EDIT_STATE_DISABLED: Long = 0L
        const val GEN_EDIT_STATE_INSTANCE: Long = 1L
        const val GEN_EDIT_STATE_MAIN: Long = 2L
        const val GEN_EDIT_STATE_MAIN_INHERITED: Long = 3L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): SceneState? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): SceneState? =
            if (handle.address() == 0L) null else SceneState(GodotHandle(handle))

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
