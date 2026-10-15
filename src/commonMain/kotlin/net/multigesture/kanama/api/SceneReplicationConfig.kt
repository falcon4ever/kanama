package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.NodePath

/**
 * Configuration for properties to synchronize with a `MultiplayerSynchronizer`.
 *
 * Generated from Godot docs: SceneReplicationConfig
 */
class SceneReplicationConfig(handle: GodotHandle) : Resource(handle) {
    /**
     * Returns a list of synchronized property `NodePath`s.
     *
     * Generated from Godot docs: SceneReplicationConfig.get_properties
     */
    fun getProperties(): List<NodePath> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetNodePathList(Binds.getPropertiesBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Adds the property identified by the given `path` to the list of the properties being
     * synchronized, optionally passing an `index`. Note: For details on restrictions and limitations
     * on property synchronization, see `MultiplayerSynchronizer`.
     *
     * Generated from Godot docs: SceneReplicationConfig.add_property
     */
    fun addProperty(path: NodePath, index: Int = -1) {
        checkOpen()
        ObjectCalls.ptrcallWithNodePathAndIntArg(Binds.addPropertyBind, segment, path, index)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns `true` if the given `path` is configured for synchronization.
     *
     * Generated from Godot docs: SceneReplicationConfig.has_property
     */
    fun hasProperty(path: NodePath): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithNodePathArgRetBool(Binds.hasPropertyBind, segment, path)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Removes the property identified by the given `path` from the configuration.
     *
     * Generated from Godot docs: SceneReplicationConfig.remove_property
     */
    fun removeProperty(path: NodePath) {
        checkOpen()
        ObjectCalls.ptrcallWithNodePathArg(Binds.removePropertyBind, segment, path)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Finds the index of the given `path`.
     *
     * Generated from Godot docs: SceneReplicationConfig.property_get_index
     */
    fun propertyGetIndex(path: NodePath): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithNodePathArgRetInt(Binds.propertyGetIndexBind, segment, path)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns `true` if the property identified by the given `path` is configured to be synchronized
     * on spawn.
     *
     * Generated from Godot docs: SceneReplicationConfig.property_get_spawn
     */
    fun propertyGetSpawn(path: NodePath): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithNodePathArgRetBool(Binds.propertyGetSpawnBind, segment, path)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets whether the property identified by the given `path` is configured to be synchronized on
     * spawn.
     *
     * Generated from Godot docs: SceneReplicationConfig.property_set_spawn
     */
    fun propertySetSpawn(path: NodePath, enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithNodePathAndBoolArgs(Binds.propertySetSpawnBind, segment, path, enabled)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns the replication mode for the property identified by the given `path`.
     *
     * Generated from Godot docs: SceneReplicationConfig.property_get_replication_mode
     */
    fun propertyGetReplicationMode(path: NodePath): SceneReplicationConfig.ReplicationMode {
        checkOpen()
        val ret = SceneReplicationConfig.ReplicationMode(ObjectCalls.ptrcallWithNodePathArgRetLong(Binds.propertyGetReplicationModeBind, segment, path))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the synchronization mode for the property identified by the given `path`.
     *
     * Generated from Godot docs: SceneReplicationConfig.property_set_replication_mode
     */
    fun propertySetReplicationMode(path: NodePath, mode: SceneReplicationConfig.ReplicationMode) {
        checkOpen()
        ObjectCalls.ptrcallWithNodePathAndLongArg(Binds.propertySetReplicationModeBind, segment, path, mode.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns `true` if the property identified by the given `path` is configured to be synchronized
     * on process.
     *
     * Generated from Godot docs: SceneReplicationConfig.property_get_sync
     */
    fun propertyGetSync(path: NodePath): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithNodePathArgRetBool(Binds.propertyGetSyncBind, segment, path)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets whether the property identified by the given `path` is configured to be synchronized on
     * process.
     *
     * Generated from Godot docs: SceneReplicationConfig.property_set_sync
     */
    fun propertySetSync(path: NodePath, enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithNodePathAndBoolArgs(Binds.propertySetSyncBind, segment, path, enabled)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns `true` if the property identified by the given `path` is configured to be reliably
     * synchronized when changes are detected on process.
     *
     * Generated from Godot docs: SceneReplicationConfig.property_get_watch
     */
    fun propertyGetWatch(path: NodePath): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithNodePathArgRetBool(Binds.propertyGetWatchBind, segment, path)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets whether the property identified by the given `path` is configured to be reliably
     * synchronized when changes are detected on process.
     *
     * Generated from Godot docs: SceneReplicationConfig.property_set_watch
     */
    fun propertySetWatch(path: NodePath, enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithNodePathAndBoolArgs(Binds.propertySetWatchBind, segment, path, enabled)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Godot's `SceneReplicationConfig.ReplicationMode` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`SceneReplicationConfig.ReplicationMode.<NAME>`).
     *
     * Generated from Godot docs: SceneReplicationConfig.ReplicationMode
     */
    @JvmInline
    value class ReplicationMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Do not keep the given property synchronized.
             *
             * Generated from Godot docs: SceneReplicationConfig.REPLICATION_MODE_NEVER
             */
            val NEVER: ReplicationMode get() = ReplicationMode(0L)
            /**
             * Replicate the given property on process by constantly sending updates using unreliable transfer
             * mode.
             *
             * Generated from Godot docs: SceneReplicationConfig.REPLICATION_MODE_ALWAYS
             */
            val ALWAYS: ReplicationMode get() = ReplicationMode(1L)
            /**
             * Replicate the given property on process by sending updates using reliable transfer mode when its
             * value changes.
             *
             * Generated from Godot docs: SceneReplicationConfig.REPLICATION_MODE_ON_CHANGE
             */
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
