package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.NodePath

/**
 * Generated from Godot docs: SceneReplicationConfig
 */
class SceneReplicationConfig(handle: GodotHandle) : Resource(handle) {
    fun getProperties(): List<NodePath> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetNodePathList(Binds.getPropertiesBind, segment)
    }

    fun addProperty(path: NodePath, index: Int = -1) {
        checkOpen()
        ObjectCalls.ptrcallWithNodePathAndIntArg(Binds.addPropertyBind, segment, path, index)
    }

    fun hasProperty(path: NodePath): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithNodePathArgRetBool(Binds.hasPropertyBind, segment, path)
    }

    fun removeProperty(path: NodePath) {
        checkOpen()
        ObjectCalls.ptrcallWithNodePathArg(Binds.removePropertyBind, segment, path)
    }

    fun propertyGetIndex(path: NodePath): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithNodePathArgRetInt(Binds.propertyGetIndexBind, segment, path)
    }

    fun propertyGetSpawn(path: NodePath): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithNodePathArgRetBool(Binds.propertyGetSpawnBind, segment, path)
    }

    fun propertySetSpawn(path: NodePath, enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithNodePathAndBoolArgs(Binds.propertySetSpawnBind, segment, path, enabled)
    }

    fun propertyGetReplicationMode(path: NodePath): SceneReplicationConfig.ReplicationMode {
        checkOpen()
        return SceneReplicationConfig.ReplicationMode(ObjectCalls.ptrcallWithNodePathArgRetLong(Binds.propertyGetReplicationModeBind, segment, path))
    }

    fun propertySetReplicationMode(path: NodePath, mode: SceneReplicationConfig.ReplicationMode) {
        checkOpen()
        ObjectCalls.ptrcallWithNodePathAndLongArg(Binds.propertySetReplicationModeBind, segment, path, mode.value)
    }

    fun propertyGetSync(path: NodePath): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithNodePathArgRetBool(Binds.propertyGetSyncBind, segment, path)
    }

    fun propertySetSync(path: NodePath, enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithNodePathAndBoolArgs(Binds.propertySetSyncBind, segment, path, enabled)
    }

    fun propertyGetWatch(path: NodePath): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithNodePathArgRetBool(Binds.propertyGetWatchBind, segment, path)
    }

    fun propertySetWatch(path: NodePath, enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithNodePathAndBoolArgs(Binds.propertySetWatchBind, segment, path, enabled)
    }

    @JvmInline
    value class ReplicationMode(override val value: Long) : GodotEnumValue {
        companion object {
            val NEVER: ReplicationMode get() = ReplicationMode(0L)
            val ALWAYS: ReplicationMode get() = ReplicationMode(1L)
            val ON_CHANGE: ReplicationMode get() = ReplicationMode(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): SceneReplicationConfig? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): SceneReplicationConfig? =
            if (handle.address() == 0L) null else RefCounted.owned(SceneReplicationConfig(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): SceneReplicationConfig? =
            if (handle.address() == 0L) null else SceneReplicationConfig(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_PROPERTIES_HASH = 3995934104L
        @JvmField
        val getPropertiesBind =
            ObjectCalls.getMethodBind("SceneReplicationConfig", "get_properties", GET_PROPERTIES_HASH)

        private const val ADD_PROPERTY_HASH = 4094619021L
        @JvmField
        val addPropertyBind =
            ObjectCalls.getMethodBind("SceneReplicationConfig", "add_property", ADD_PROPERTY_HASH)

        private const val HAS_PROPERTY_HASH = 861721659L
        @JvmField
        val hasPropertyBind =
            ObjectCalls.getMethodBind("SceneReplicationConfig", "has_property", HAS_PROPERTY_HASH)

        private const val REMOVE_PROPERTY_HASH = 1348162250L
        @JvmField
        val removePropertyBind =
            ObjectCalls.getMethodBind("SceneReplicationConfig", "remove_property", REMOVE_PROPERTY_HASH)

        private const val PROPERTY_GET_INDEX_HASH = 1382022557L
        @JvmField
        val propertyGetIndexBind =
            ObjectCalls.getMethodBind("SceneReplicationConfig", "property_get_index", PROPERTY_GET_INDEX_HASH)

        private const val PROPERTY_GET_SPAWN_HASH = 3456846888L
        @JvmField
        val propertyGetSpawnBind =
            ObjectCalls.getMethodBind("SceneReplicationConfig", "property_get_spawn", PROPERTY_GET_SPAWN_HASH)

        private const val PROPERTY_SET_SPAWN_HASH = 3868023870L
        @JvmField
        val propertySetSpawnBind =
            ObjectCalls.getMethodBind("SceneReplicationConfig", "property_set_spawn", PROPERTY_SET_SPAWN_HASH)

        private const val PROPERTY_GET_REPLICATION_MODE_HASH = 2870606336L
        @JvmField
        val propertyGetReplicationModeBind =
            ObjectCalls.getMethodBind("SceneReplicationConfig", "property_get_replication_mode", PROPERTY_GET_REPLICATION_MODE_HASH)

        private const val PROPERTY_SET_REPLICATION_MODE_HASH = 3200083865L
        @JvmField
        val propertySetReplicationModeBind =
            ObjectCalls.getMethodBind("SceneReplicationConfig", "property_set_replication_mode", PROPERTY_SET_REPLICATION_MODE_HASH)

        private const val PROPERTY_GET_SYNC_HASH = 3456846888L
        @JvmField
        val propertyGetSyncBind =
            ObjectCalls.getMethodBind("SceneReplicationConfig", "property_get_sync", PROPERTY_GET_SYNC_HASH)

        private const val PROPERTY_SET_SYNC_HASH = 3868023870L
        @JvmField
        val propertySetSyncBind =
            ObjectCalls.getMethodBind("SceneReplicationConfig", "property_set_sync", PROPERTY_SET_SYNC_HASH)

        private const val PROPERTY_GET_WATCH_HASH = 3456846888L
        @JvmField
        val propertyGetWatchBind =
            ObjectCalls.getMethodBind("SceneReplicationConfig", "property_get_watch", PROPERTY_GET_WATCH_HASH)

        private const val PROPERTY_SET_WATCH_HASH = 3868023870L
        @JvmField
        val propertySetWatchBind =
            ObjectCalls.getMethodBind("SceneReplicationConfig", "property_set_watch", PROPERTY_SET_WATCH_HASH)
    }
}
