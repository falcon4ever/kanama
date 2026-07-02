package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath

/**
 * Generated from Godot docs: SceneReplicationConfig
 */
class SceneReplicationConfig(handle: MemorySegment) : Resource(handle) {
    fun getProperties(): List<NodePath> {
        return ObjectCalls.ptrcallNoArgsRetNodePathList(getPropertiesBind, handle)
    }

    fun addProperty(path: NodePath, index: Int = -1) {
        ObjectCalls.ptrcallWithNodePathAndIntArg(addPropertyBind, handle, path, index)
    }

    fun hasProperty(path: NodePath): Boolean {
        return ObjectCalls.ptrcallWithNodePathArgRetBool(hasPropertyBind, handle, path)
    }

    fun removeProperty(path: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(removePropertyBind, handle, path)
    }

    fun propertyGetIndex(path: NodePath): Int {
        return ObjectCalls.ptrcallWithNodePathArgRetInt(propertyGetIndexBind, handle, path)
    }

    fun propertyGetSpawn(path: NodePath): Boolean {
        return ObjectCalls.ptrcallWithNodePathArgRetBool(propertyGetSpawnBind, handle, path)
    }

    fun propertySetSpawn(path: NodePath, enabled: Boolean) {
        ObjectCalls.ptrcallWithNodePathAndBoolArgs(propertySetSpawnBind, handle, path, enabled)
    }

    fun propertyGetReplicationMode(path: NodePath): Long {
        return ObjectCalls.ptrcallWithNodePathArgRetLong(propertyGetReplicationModeBind, handle, path)
    }

    fun propertySetReplicationMode(path: NodePath, mode: Long) {
        ObjectCalls.ptrcallWithNodePathAndLongArg(propertySetReplicationModeBind, handle, path, mode)
    }

    fun propertyGetSync(path: NodePath): Boolean {
        return ObjectCalls.ptrcallWithNodePathArgRetBool(propertyGetSyncBind, handle, path)
    }

    fun propertySetSync(path: NodePath, enabled: Boolean) {
        ObjectCalls.ptrcallWithNodePathAndBoolArgs(propertySetSyncBind, handle, path, enabled)
    }

    fun propertyGetWatch(path: NodePath): Boolean {
        return ObjectCalls.ptrcallWithNodePathArgRetBool(propertyGetWatchBind, handle, path)
    }

    fun propertySetWatch(path: NodePath, enabled: Boolean) {
        ObjectCalls.ptrcallWithNodePathAndBoolArgs(propertySetWatchBind, handle, path, enabled)
    }

    companion object {
        const val REPLICATION_MODE_NEVER: Long = 0L
        const val REPLICATION_MODE_ALWAYS: Long = 1L
        const val REPLICATION_MODE_ON_CHANGE: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): SceneReplicationConfig? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SceneReplicationConfig? =
            if (handle.address() == 0L) null else SceneReplicationConfig(handle)

        private const val GET_PROPERTIES_HASH = 3995934104L
        private val getPropertiesBind by lazy {
            ObjectCalls.getMethodBind("SceneReplicationConfig", "get_properties", GET_PROPERTIES_HASH)
        }

        private const val ADD_PROPERTY_HASH = 4094619021L
        private val addPropertyBind by lazy {
            ObjectCalls.getMethodBind("SceneReplicationConfig", "add_property", ADD_PROPERTY_HASH)
        }

        private const val HAS_PROPERTY_HASH = 861721659L
        private val hasPropertyBind by lazy {
            ObjectCalls.getMethodBind("SceneReplicationConfig", "has_property", HAS_PROPERTY_HASH)
        }

        private const val REMOVE_PROPERTY_HASH = 1348162250L
        private val removePropertyBind by lazy {
            ObjectCalls.getMethodBind("SceneReplicationConfig", "remove_property", REMOVE_PROPERTY_HASH)
        }

        private const val PROPERTY_GET_INDEX_HASH = 1382022557L
        private val propertyGetIndexBind by lazy {
            ObjectCalls.getMethodBind("SceneReplicationConfig", "property_get_index", PROPERTY_GET_INDEX_HASH)
        }

        private const val PROPERTY_GET_SPAWN_HASH = 3456846888L
        private val propertyGetSpawnBind by lazy {
            ObjectCalls.getMethodBind("SceneReplicationConfig", "property_get_spawn", PROPERTY_GET_SPAWN_HASH)
        }

        private const val PROPERTY_SET_SPAWN_HASH = 3868023870L
        private val propertySetSpawnBind by lazy {
            ObjectCalls.getMethodBind("SceneReplicationConfig", "property_set_spawn", PROPERTY_SET_SPAWN_HASH)
        }

        private const val PROPERTY_GET_REPLICATION_MODE_HASH = 2870606336L
        private val propertyGetReplicationModeBind by lazy {
            ObjectCalls.getMethodBind("SceneReplicationConfig", "property_get_replication_mode", PROPERTY_GET_REPLICATION_MODE_HASH)
        }

        private const val PROPERTY_SET_REPLICATION_MODE_HASH = 3200083865L
        private val propertySetReplicationModeBind by lazy {
            ObjectCalls.getMethodBind("SceneReplicationConfig", "property_set_replication_mode", PROPERTY_SET_REPLICATION_MODE_HASH)
        }

        private const val PROPERTY_GET_SYNC_HASH = 3456846888L
        private val propertyGetSyncBind by lazy {
            ObjectCalls.getMethodBind("SceneReplicationConfig", "property_get_sync", PROPERTY_GET_SYNC_HASH)
        }

        private const val PROPERTY_SET_SYNC_HASH = 3868023870L
        private val propertySetSyncBind by lazy {
            ObjectCalls.getMethodBind("SceneReplicationConfig", "property_set_sync", PROPERTY_SET_SYNC_HASH)
        }

        private const val PROPERTY_GET_WATCH_HASH = 3456846888L
        private val propertyGetWatchBind by lazy {
            ObjectCalls.getMethodBind("SceneReplicationConfig", "property_get_watch", PROPERTY_GET_WATCH_HASH)
        }

        private const val PROPERTY_SET_WATCH_HASH = 3868023870L
        private val propertySetWatchBind by lazy {
            ObjectCalls.getMethodBind("SceneReplicationConfig", "property_set_watch", PROPERTY_SET_WATCH_HASH)
        }
    }
}
