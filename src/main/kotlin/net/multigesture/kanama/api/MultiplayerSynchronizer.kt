package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath

/**
 * Generated from Godot docs: MultiplayerSynchronizer
 */
class MultiplayerSynchronizer(handle: MemorySegment) : Node(handle) {
    var rootPath: NodePath
        @JvmName("rootPathProperty")
        get() = getRootPath()
        @JvmName("setRootPathProperty")
        set(value) = setRootPath(value)

    var replicationInterval: Double
        @JvmName("replicationIntervalProperty")
        get() = getReplicationInterval()
        @JvmName("setReplicationIntervalProperty")
        set(value) = setReplicationInterval(value)

    var deltaInterval: Double
        @JvmName("deltaIntervalProperty")
        get() = getDeltaInterval()
        @JvmName("setDeltaIntervalProperty")
        set(value) = setDeltaInterval(value)

    var replicationConfig: SceneReplicationConfig?
        @JvmName("replicationConfigProperty")
        get() = getReplicationConfig()
        @JvmName("setReplicationConfigProperty")
        set(value) = setReplicationConfig(value)

    var visibilityUpdateMode: Long
        @JvmName("visibilityUpdateModeProperty")
        get() = getVisibilityUpdateMode()
        @JvmName("setVisibilityUpdateModeProperty")
        set(value) = setVisibilityUpdateMode(value)

    var publicVisibility: Boolean
        @JvmName("publicVisibilityProperty")
        get() = isVisibilityPublic()
        @JvmName("setPublicVisibilityProperty")
        set(value) = setVisibilityPublic(value)

    fun setRootPath(path: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setRootPathBind, handle, path)
    }

    fun getRootPath(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getRootPathBind, handle)
    }

    fun setReplicationInterval(milliseconds: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setReplicationIntervalBind, handle, milliseconds)
    }

    fun getReplicationInterval(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getReplicationIntervalBind, handle)
    }

    fun setDeltaInterval(milliseconds: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDeltaIntervalBind, handle, milliseconds)
    }

    fun getDeltaInterval(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDeltaIntervalBind, handle)
    }

    fun setReplicationConfig(config: SceneReplicationConfig?) {
        ObjectCalls.ptrcallWithObjectArgs(setReplicationConfigBind, handle, listOf(config?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getReplicationConfig(): SceneReplicationConfig? {
        return SceneReplicationConfig.wrap(ObjectCalls.ptrcallNoArgsRetObject(getReplicationConfigBind, handle))
    }

    fun setVisibilityUpdateMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setVisibilityUpdateModeBind, handle, mode)
    }

    fun getVisibilityUpdateMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getVisibilityUpdateModeBind, handle)
    }

    fun updateVisibility(forPeer: Int = 0) {
        ObjectCalls.ptrcallWithIntArg(updateVisibilityBind, handle, forPeer)
    }

    fun setVisibilityPublic(visible: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setVisibilityPublicBind, handle, visible)
    }

    fun isVisibilityPublic(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isVisibilityPublicBind, handle)
    }

    fun addVisibilityFilter(filter: GodotCallable) {
        ObjectCalls.ptrcallWithCallableArg(addVisibilityFilterBind, handle, filter.target.handle, filter.method)
    }

    fun removeVisibilityFilter(filter: GodotCallable) {
        ObjectCalls.ptrcallWithCallableArg(removeVisibilityFilterBind, handle, filter.target.handle, filter.method)
    }

    fun setVisibilityFor(peer: Int, visible: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setVisibilityForBind, handle, peer, visible)
    }

    fun getVisibilityFor(peer: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getVisibilityForBind, handle, peer)
    }

    object Signals {
        const val synchronized: String = "synchronized"
        const val deltaSynchronized: String = "delta_synchronized"
        const val visibilityChanged: String = "visibility_changed"
    }

    companion object {
        const val VISIBILITY_PROCESS_IDLE: Long = 0L
        const val VISIBILITY_PROCESS_PHYSICS: Long = 1L
        const val VISIBILITY_PROCESS_NONE: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): MultiplayerSynchronizer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): MultiplayerSynchronizer? =
            if (handle.address() == 0L) null else MultiplayerSynchronizer(handle)

        private const val SET_ROOT_PATH_HASH = 1348162250L
        private val setRootPathBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerSynchronizer", "set_root_path", SET_ROOT_PATH_HASH)
        }

        private const val GET_ROOT_PATH_HASH = 4075236667L
        private val getRootPathBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerSynchronizer", "get_root_path", GET_ROOT_PATH_HASH)
        }

        private const val SET_REPLICATION_INTERVAL_HASH = 373806689L
        private val setReplicationIntervalBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerSynchronizer", "set_replication_interval", SET_REPLICATION_INTERVAL_HASH)
        }

        private const val GET_REPLICATION_INTERVAL_HASH = 1740695150L
        private val getReplicationIntervalBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerSynchronizer", "get_replication_interval", GET_REPLICATION_INTERVAL_HASH)
        }

        private const val SET_DELTA_INTERVAL_HASH = 373806689L
        private val setDeltaIntervalBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerSynchronizer", "set_delta_interval", SET_DELTA_INTERVAL_HASH)
        }

        private const val GET_DELTA_INTERVAL_HASH = 1740695150L
        private val getDeltaIntervalBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerSynchronizer", "get_delta_interval", GET_DELTA_INTERVAL_HASH)
        }

        private const val SET_REPLICATION_CONFIG_HASH = 3889206742L
        private val setReplicationConfigBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerSynchronizer", "set_replication_config", SET_REPLICATION_CONFIG_HASH)
        }

        private const val GET_REPLICATION_CONFIG_HASH = 3200254614L
        private val getReplicationConfigBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerSynchronizer", "get_replication_config", GET_REPLICATION_CONFIG_HASH)
        }

        private const val SET_VISIBILITY_UPDATE_MODE_HASH = 3494860300L
        private val setVisibilityUpdateModeBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerSynchronizer", "set_visibility_update_mode", SET_VISIBILITY_UPDATE_MODE_HASH)
        }

        private const val GET_VISIBILITY_UPDATE_MODE_HASH = 3352241418L
        private val getVisibilityUpdateModeBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerSynchronizer", "get_visibility_update_mode", GET_VISIBILITY_UPDATE_MODE_HASH)
        }

        private const val UPDATE_VISIBILITY_HASH = 1995695955L
        private val updateVisibilityBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerSynchronizer", "update_visibility", UPDATE_VISIBILITY_HASH)
        }

        private const val SET_VISIBILITY_PUBLIC_HASH = 2586408642L
        private val setVisibilityPublicBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerSynchronizer", "set_visibility_public", SET_VISIBILITY_PUBLIC_HASH)
        }

        private const val IS_VISIBILITY_PUBLIC_HASH = 36873697L
        private val isVisibilityPublicBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerSynchronizer", "is_visibility_public", IS_VISIBILITY_PUBLIC_HASH)
        }

        private const val ADD_VISIBILITY_FILTER_HASH = 1611583062L
        private val addVisibilityFilterBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerSynchronizer", "add_visibility_filter", ADD_VISIBILITY_FILTER_HASH)
        }

        private const val REMOVE_VISIBILITY_FILTER_HASH = 1611583062L
        private val removeVisibilityFilterBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerSynchronizer", "remove_visibility_filter", REMOVE_VISIBILITY_FILTER_HASH)
        }

        private const val SET_VISIBILITY_FOR_HASH = 300928843L
        private val setVisibilityForBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerSynchronizer", "set_visibility_for", SET_VISIBILITY_FOR_HASH)
        }

        private const val GET_VISIBILITY_FOR_HASH = 1116898809L
        private val getVisibilityForBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerSynchronizer", "get_visibility_for", GET_VISIBILITY_FOR_HASH)
        }
    }
}
