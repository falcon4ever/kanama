package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Provides access to a scene file's information.
 *
 * Generated from Godot docs: SceneState
 */
class SceneState(handle: MemorySegment) : RefCounted(handle) {
    /**
     * Returns the resource path to the represented `PackedScene`.
     *
     * Generated from Godot docs: SceneState.get_path
     */
    fun getPath(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getPathBind, handle)
    }

    /**
     * Returns the `SceneState` of the scene that this scene inherits from, or `null` if it doesn't
     * inherit from any scene.
     *
     * Generated from Godot docs: SceneState.get_base_scene_state
     */
    fun getBaseSceneState(): SceneState? {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetObject(getBaseSceneStateBind, handle)
        if (ret.address() == handle.address()) {
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
        return ObjectCalls.ptrcallNoArgsRetInt(getNodeCountBind, handle)
    }

    /**
     * Returns `true` if the node at `idx` is an `InstancePlaceholder`.
     *
     * Generated from Godot docs: SceneState.is_node_instance_placeholder
     */
    fun isNodeInstancePlaceholder(idx: Int): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetBool(isNodeInstancePlaceholderBind, handle, idx)
    }

    /**
     * Returns a `PackedScene` for the node at `idx` (i.e. the whole branch starting at this node, with
     * its child nodes and resources), or `null` if the node is not an instance.
     *
     * Generated from Godot docs: SceneState.get_node_instance
     */
    fun getNodeInstance(idx: Int): PackedScene? {
        checkOpen()
        return PackedScene.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getNodeInstanceBind, handle, idx))
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
        return ObjectCalls.ptrcallWithIntArgRetInt(getNodeIndexBind, handle, idx)
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
        return ObjectCalls.ptrcallWithIntArgRetInt(getNodePropertyCountBind, handle, idx)
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
        return ObjectCalls.ptrcallNoArgsRetInt(getConnectionCountBind, handle)
    }

    /**
     * Returns the connection flags for the signal at `idx`. See `Object.ConnectFlags` constants.
     *
     * Generated from Godot docs: SceneState.get_connection_flags
     */
    fun getConnectionFlags(idx: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(getConnectionFlagsBind, handle, idx)
    }

    /**
     * Returns the number of unbound parameters for the signal at `idx`.
     *
     * Generated from Godot docs: SceneState.get_connection_unbinds
     */
    fun getConnectionUnbinds(idx: Int): Int {
        checkOpen()
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

        private const val IS_NODE_INSTANCE_PLACEHOLDER_HASH = 1116898809L
        private val isNodeInstancePlaceholderBind by lazy {
            ObjectCalls.getMethodBind("SceneState", "is_node_instance_placeholder", IS_NODE_INSTANCE_PLACEHOLDER_HASH)
        }

        private const val GET_NODE_INSTANCE_HASH = 511017218L
        private val getNodeInstanceBind by lazy {
            ObjectCalls.getMethodBind("SceneState", "get_node_instance", GET_NODE_INSTANCE_HASH)
        }

        private const val GET_NODE_INDEX_HASH = 923996154L
        private val getNodeIndexBind by lazy {
            ObjectCalls.getMethodBind("SceneState", "get_node_index", GET_NODE_INDEX_HASH)
        }

        private const val GET_NODE_PROPERTY_COUNT_HASH = 923996154L
        private val getNodePropertyCountBind by lazy {
            ObjectCalls.getMethodBind("SceneState", "get_node_property_count", GET_NODE_PROPERTY_COUNT_HASH)
        }

        private const val GET_CONNECTION_COUNT_HASH = 3905245786L
        private val getConnectionCountBind by lazy {
            ObjectCalls.getMethodBind("SceneState", "get_connection_count", GET_CONNECTION_COUNT_HASH)
        }

        private const val GET_CONNECTION_FLAGS_HASH = 923996154L
        private val getConnectionFlagsBind by lazy {
            ObjectCalls.getMethodBind("SceneState", "get_connection_flags", GET_CONNECTION_FLAGS_HASH)
        }

        private const val GET_CONNECTION_UNBINDS_HASH = 923996154L
        private val getConnectionUnbindsBind by lazy {
            ObjectCalls.getMethodBind("SceneState", "get_connection_unbinds", GET_CONNECTION_UNBINDS_HASH)
        }
    }
}
