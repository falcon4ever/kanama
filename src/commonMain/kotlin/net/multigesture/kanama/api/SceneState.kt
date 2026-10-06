package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
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
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getPathBind, segment)
    }

    /**
     * Returns the `SceneState` of the scene that this scene inherits from, or `null` if it doesn't
     * inherit from any scene.
     *
     * Generated from Godot docs: SceneState.get_base_scene_state
     */
    fun getBaseSceneState(): SceneState? {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetObject(Binds.getBaseSceneStateBind, segment)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return SceneState.wrapOwned(ret)
    }

    /**
     * Returns the number of nodes in the scene. The `idx` argument used to query node data in other
     * `get_node_*` methods in the interval `[0, get_node_count() - 1]`.
     *
     * Generated from Godot docs: SceneState.get_node_count
     */
    fun getNodeCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getNodeCountBind, segment)
    }

    /**
     * Returns the type of the node at `idx`.
     *
     * Generated from Godot docs: SceneState.get_node_type
     */
    fun getNodeType(idx: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetStringName(Binds.getNodeTypeBind, segment, idx)
    }

    /**
     * Returns the name of the node at `idx`.
     *
     * Generated from Godot docs: SceneState.get_node_name
     */
    fun getNodeName(idx: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetStringName(Binds.getNodeNameBind, segment, idx)
    }

    /**
     * Returns the path to the node at `idx`. If `for_parent` is `true`, returns the path of the `idx`
     * node's parent instead.
     *
     * Generated from Godot docs: SceneState.get_node_path
     */
    fun getNodePath(idx: Int, forParent: Boolean = false): NodePath {
        checkOpen()
        return ObjectCalls.ptrcallWithIntAndBoolArgRetNodePath(Binds.getNodePathBind, segment, idx, forParent)
    }

    /**
     * Returns the path to the owner of the node at `idx`, relative to the root node.
     *
     * Generated from Godot docs: SceneState.get_node_owner_path
     */
    fun getNodeOwnerPath(idx: Int): NodePath {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetNodePath(Binds.getNodeOwnerPathBind, segment, idx)
    }

    /**
     * Returns `true` if the node at `idx` is an `InstancePlaceholder`.
     *
     * Generated from Godot docs: SceneState.is_node_instance_placeholder
     */
    fun isNodeInstancePlaceholder(idx: Int): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.isNodeInstancePlaceholderBind, segment, idx)
    }

    /**
     * Returns the path to the represented scene file if the node at `idx` is an `InstancePlaceholder`.
     *
     * Generated from Godot docs: SceneState.get_node_instance_placeholder
     */
    fun getNodeInstancePlaceholder(idx: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetString(Binds.getNodeInstancePlaceholderBind, segment, idx)
    }

    /**
     * Returns a `PackedScene` for the node at `idx` (i.e. the whole branch starting at this node, with
     * its child nodes and resources), or `null` if the node is not an instance.
     *
     * Generated from Godot docs: SceneState.get_node_instance
     */
    fun getNodeInstance(idx: Int): PackedScene? {
        checkOpen()
        return PackedScene.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getNodeInstanceBind, segment, idx))
    }

    /**
     * Returns the list of group names associated with the node at `idx`.
     *
     * Generated from Godot docs: SceneState.get_node_groups
     */
    fun getNodeGroups(idx: Int): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetPackedStringList(Binds.getNodeGroupsBind, segment, idx)
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
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getNodeIndexBind, segment, idx)
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
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getNodePropertyCountBind, segment, idx)
    }

    /**
     * Returns the name of the property at `prop_idx` for the node at `idx`.
     *
     * Generated from Godot docs: SceneState.get_node_property_name
     */
    fun getNodePropertyName(idx: Int, propIdx: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetStringName(Binds.getNodePropertyNameBind, segment, idx, propIdx)
    }

    /**
     * Returns the value of the property at `prop_idx` for the node at `idx`.
     *
     * Generated from Godot docs: SceneState.get_node_property_value
     */
    fun getNodePropertyValue(idx: Int, propIdx: Int): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetVariantScalar(Binds.getNodePropertyValueBind, segment, idx, propIdx)
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
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getConnectionCountBind, segment)
    }

    /**
     * Returns the path to the node that owns the signal at `idx`, relative to the root node.
     *
     * Generated from Godot docs: SceneState.get_connection_source
     */
    fun getConnectionSource(idx: Int): NodePath {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetNodePath(Binds.getConnectionSourceBind, segment, idx)
    }

    /**
     * Returns the name of the signal at `idx`.
     *
     * Generated from Godot docs: SceneState.get_connection_signal
     */
    fun getConnectionSignal(idx: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetStringName(Binds.getConnectionSignalBind, segment, idx)
    }

    /**
     * Returns the path to the node that owns the method connected to the signal at `idx`, relative to
     * the root node.
     *
     * Generated from Godot docs: SceneState.get_connection_target
     */
    fun getConnectionTarget(idx: Int): NodePath {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetNodePath(Binds.getConnectionTargetBind, segment, idx)
    }

    /**
     * Returns the method connected to the signal at `idx`.
     *
     * Generated from Godot docs: SceneState.get_connection_method
     */
    fun getConnectionMethod(idx: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetStringName(Binds.getConnectionMethodBind, segment, idx)
    }

    /**
     * Returns the connection flags for the signal at `idx`. See `Object.ConnectFlags` constants.
     *
     * Generated from Godot docs: SceneState.get_connection_flags
     */
    fun getConnectionFlags(idx: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getConnectionFlagsBind, segment, idx)
    }

    /**
     * Returns the list of bound parameters for the signal at `idx`.
     *
     * Generated from Godot docs: SceneState.get_connection_binds
     */
    fun getConnectionBinds(idx: Int): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetArray(Binds.getConnectionBindsBind, segment, idx)
    }

    /**
     * Returns the number of unbound parameters for the signal at `idx`.
     *
     * Generated from Godot docs: SceneState.get_connection_unbinds
     */
    fun getConnectionUnbinds(idx: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getConnectionUnbindsBind, segment, idx)
    }

    /**
     * Godot's `SceneState.GenEditState` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`SceneState.GenEditState.<NAME>`).
     *
     * Generated from Godot docs: SceneState.GenEditState
     */
    @JvmInline
    value class GenEditState(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * If passed to `PackedScene.instantiate`, blocks edits to the scene state.
             *
             * Generated from Godot docs: SceneState.GEN_EDIT_STATE_DISABLED
             */
            val DISABLED: GenEditState get() = GenEditState(0L)
            /**
             * If passed to `PackedScene.instantiate`, provides inherited scene resources to the local scene.
             * Note: Only available in editor builds.
             *
             * Generated from Godot docs: SceneState.GEN_EDIT_STATE_INSTANCE
             */
            val INSTANCE: GenEditState get() = GenEditState(1L)
            /**
             * If passed to `PackedScene.instantiate`, provides local scene resources to the local scene. Only
             * the main scene should receive the main edit state. Note: Only available in editor builds.
             *
             * Generated from Godot docs: SceneState.GEN_EDIT_STATE_MAIN
             */
            val MAIN: GenEditState get() = GenEditState(2L)
            /**
             * If passed to `PackedScene.instantiate`, it's similar to `GenEditState.MAIN`, but for the case
             * where the scene is being instantiated to be the base of another one. Note: Only available in
             * editor builds.
             *
             * Generated from Godot docs: SceneState.GEN_EDIT_STATE_MAIN_INHERITED
             */
            val MAIN_INHERITED: GenEditState get() = GenEditState(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): SceneState? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): SceneState? =
            if (handle.address() == 0L) null else RefCounted.owned(SceneState(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): SceneState? =
            if (handle.address() == 0L) null else SceneState(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_PATH_HASH = 201670096L
        @JvmField
        val getPathBind =
            ObjectCalls.getMethodBind("SceneState", "get_path", GET_PATH_HASH)

        private const val GET_BASE_SCENE_STATE_HASH = 3479783971L
        @JvmField
        val getBaseSceneStateBind =
            ObjectCalls.getMethodBind("SceneState", "get_base_scene_state", GET_BASE_SCENE_STATE_HASH)

        private const val GET_NODE_COUNT_HASH = 3905245786L
        @JvmField
        val getNodeCountBind =
            ObjectCalls.getMethodBind("SceneState", "get_node_count", GET_NODE_COUNT_HASH)

        private const val GET_NODE_TYPE_HASH = 659327637L
        @JvmField
        val getNodeTypeBind =
            ObjectCalls.getMethodBind("SceneState", "get_node_type", GET_NODE_TYPE_HASH)

        private const val GET_NODE_NAME_HASH = 659327637L
        @JvmField
        val getNodeNameBind =
            ObjectCalls.getMethodBind("SceneState", "get_node_name", GET_NODE_NAME_HASH)

        private const val GET_NODE_PATH_HASH = 2272487792L
        @JvmField
        val getNodePathBind =
            ObjectCalls.getMethodBind("SceneState", "get_node_path", GET_NODE_PATH_HASH)

        private const val GET_NODE_OWNER_PATH_HASH = 408788394L
        @JvmField
        val getNodeOwnerPathBind =
            ObjectCalls.getMethodBind("SceneState", "get_node_owner_path", GET_NODE_OWNER_PATH_HASH)

        private const val IS_NODE_INSTANCE_PLACEHOLDER_HASH = 1116898809L
        @JvmField
        val isNodeInstancePlaceholderBind =
            ObjectCalls.getMethodBind("SceneState", "is_node_instance_placeholder", IS_NODE_INSTANCE_PLACEHOLDER_HASH)

        private const val GET_NODE_INSTANCE_PLACEHOLDER_HASH = 844755477L
        @JvmField
        val getNodeInstancePlaceholderBind =
            ObjectCalls.getMethodBind("SceneState", "get_node_instance_placeholder", GET_NODE_INSTANCE_PLACEHOLDER_HASH)

        private const val GET_NODE_INSTANCE_HASH = 511017218L
        @JvmField
        val getNodeInstanceBind =
            ObjectCalls.getMethodBind("SceneState", "get_node_instance", GET_NODE_INSTANCE_HASH)

        private const val GET_NODE_GROUPS_HASH = 647634434L
        @JvmField
        val getNodeGroupsBind =
            ObjectCalls.getMethodBind("SceneState", "get_node_groups", GET_NODE_GROUPS_HASH)

        private const val GET_NODE_INDEX_HASH = 923996154L
        @JvmField
        val getNodeIndexBind =
            ObjectCalls.getMethodBind("SceneState", "get_node_index", GET_NODE_INDEX_HASH)

        private const val GET_NODE_PROPERTY_COUNT_HASH = 923996154L
        @JvmField
        val getNodePropertyCountBind =
            ObjectCalls.getMethodBind("SceneState", "get_node_property_count", GET_NODE_PROPERTY_COUNT_HASH)

        private const val GET_NODE_PROPERTY_NAME_HASH = 351665558L
        @JvmField
        val getNodePropertyNameBind =
            ObjectCalls.getMethodBind("SceneState", "get_node_property_name", GET_NODE_PROPERTY_NAME_HASH)

        private const val GET_NODE_PROPERTY_VALUE_HASH = 678354945L
        @JvmField
        val getNodePropertyValueBind =
            ObjectCalls.getMethodBind("SceneState", "get_node_property_value", GET_NODE_PROPERTY_VALUE_HASH)

        private const val GET_CONNECTION_COUNT_HASH = 3905245786L
        @JvmField
        val getConnectionCountBind =
            ObjectCalls.getMethodBind("SceneState", "get_connection_count", GET_CONNECTION_COUNT_HASH)

        private const val GET_CONNECTION_SOURCE_HASH = 408788394L
        @JvmField
        val getConnectionSourceBind =
            ObjectCalls.getMethodBind("SceneState", "get_connection_source", GET_CONNECTION_SOURCE_HASH)

        private const val GET_CONNECTION_SIGNAL_HASH = 659327637L
        @JvmField
        val getConnectionSignalBind =
            ObjectCalls.getMethodBind("SceneState", "get_connection_signal", GET_CONNECTION_SIGNAL_HASH)

        private const val GET_CONNECTION_TARGET_HASH = 408788394L
        @JvmField
        val getConnectionTargetBind =
            ObjectCalls.getMethodBind("SceneState", "get_connection_target", GET_CONNECTION_TARGET_HASH)

        private const val GET_CONNECTION_METHOD_HASH = 659327637L
        @JvmField
        val getConnectionMethodBind =
            ObjectCalls.getMethodBind("SceneState", "get_connection_method", GET_CONNECTION_METHOD_HASH)

        private const val GET_CONNECTION_FLAGS_HASH = 923996154L
        @JvmField
        val getConnectionFlagsBind =
            ObjectCalls.getMethodBind("SceneState", "get_connection_flags", GET_CONNECTION_FLAGS_HASH)

        private const val GET_CONNECTION_BINDS_HASH = 663333327L
        @JvmField
        val getConnectionBindsBind =
            ObjectCalls.getMethodBind("SceneState", "get_connection_binds", GET_CONNECTION_BINDS_HASH)

        private const val GET_CONNECTION_UNBINDS_HASH = 923996154L
        @JvmField
        val getConnectionUnbindsBind =
            ObjectCalls.getMethodBind("SceneState", "get_connection_unbinds", GET_CONNECTION_UNBINDS_HASH)
    }
}
