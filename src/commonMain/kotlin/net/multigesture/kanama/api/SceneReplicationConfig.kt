package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.NodePath

/**
 * Generated from Godot docs: SceneReplicationConfig
 */
class SceneReplicationConfig(handle: GodotHandle) : Resource(handle) {
    fun getProperties(): List<NodePath> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetNodePathList(getPropertiesBind, segment)
    }

    fun addProperty(path: NodePath, index: Int = -1) {
        checkOpen()
        ObjectCalls.ptrcallWithNodePathAndIntArg(addPropertyBind, segment, path, index)
    }

    fun hasProperty(path: NodePath): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithNodePathArgRetBool(hasPropertyBind, segment, path)
    }

    fun removeProperty(path: NodePath) {
        checkOpen()
        ObjectCalls.ptrcallWithNodePathArg(removePropertyBind, segment, path)
    }

    fun propertyGetIndex(path: NodePath): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithNodePathArgRetInt(propertyGetIndexBind, segment, path)
    }

    fun propertyGetSpawn(path: NodePath): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithNodePathArgRetBool(propertyGetSpawnBind, segment, path)
    }

    fun propertySetSpawn(path: NodePath, enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithNodePathAndBoolArgs(propertySetSpawnBind, segment, path, enabled)
    }

    fun propertyGetReplicationMode(path: NodePath): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithNodePathArgRetLong(propertyGetReplicationModeBind, segment, path)
    }

    fun propertySetReplicationMode(path: NodePath, mode: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithNodePathAndLongArg(propertySetReplicationModeBind, segment, path, mode)
    }

    fun propertyGetSync(path: NodePath): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithNodePathArgRetBool(propertyGetSyncBind, segment, path)
    }

    fun propertySetSync(path: NodePath, enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithNodePathAndBoolArgs(propertySetSyncBind, segment, path, enabled)
    }

    fun propertyGetWatch(path: NodePath): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithNodePathArgRetBool(propertyGetWatchBind, segment, path)
    }

    fun propertySetWatch(path: NodePath, enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithNodePathAndBoolArgs(propertySetWatchBind, segment, path, enabled)
    }

    companion object {
        const val REPLICATION_MODE_NEVER: Long = 0L
        const val REPLICATION_MODE_ALWAYS: Long = 1L
        const val REPLICATION_MODE_ON_CHANGE: Long = 2L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): SceneReplicationConfig? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): SceneReplicationConfig? =
            if (handle.address() == 0L) null else SceneReplicationConfig(GodotHandle(handle))

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
