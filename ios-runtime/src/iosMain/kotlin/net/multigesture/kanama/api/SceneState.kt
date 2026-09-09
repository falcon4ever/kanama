package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: SceneState
 */
class SceneState(handle: MemorySegment) : RefCounted(handle) {
    fun getPath(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getPathBind, handle)
    }

    fun getBaseSceneState(): SceneState? {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetObject(getBaseSceneStateBind, handle)
        if (ret.address() == handle.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return SceneState.wrap(ret)
    }

    fun getNodeCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getNodeCountBind, handle)
    }

    fun isNodeInstancePlaceholder(idx: Int): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetBool(isNodeInstancePlaceholderBind, handle, idx)
    }

    fun getNodeInstance(idx: Int): PackedScene? {
        checkOpen()
        return PackedScene.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getNodeInstanceBind, handle, idx))
    }

    fun getNodeIndex(idx: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(getNodeIndexBind, handle, idx)
    }

    fun getNodePropertyCount(idx: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(getNodePropertyCountBind, handle, idx)
    }

    fun getConnectionCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getConnectionCountBind, handle)
    }

    fun getConnectionFlags(idx: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(getConnectionFlagsBind, handle, idx)
    }

    fun getConnectionUnbinds(idx: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(getConnectionUnbindsBind, handle, idx)
    }

    companion object {
        const val GEN_EDIT_STATE_DISABLED: Long = 0L
        const val GEN_EDIT_STATE_INSTANCE: Long = 1L
        const val GEN_EDIT_STATE_MAIN: Long = 2L
        const val GEN_EDIT_STATE_MAIN_INHERITED: Long = 3L

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
