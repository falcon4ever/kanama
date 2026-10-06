package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.NodePath

/**
 * Generated from Godot docs: MultiplayerSynchronizer
 */
class MultiplayerSynchronizer(handle: GodotHandle) : Node(handle) {
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

    var visibilityUpdateMode: MultiplayerSynchronizer.VisibilityUpdateMode
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
        ObjectCalls.ptrcallWithNodePathArg(Binds.setRootPathBind, segment, path)
    }

    fun getRootPath(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(Binds.getRootPathBind, segment)
    }

    fun setReplicationInterval(milliseconds: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setReplicationIntervalBind, segment, milliseconds)
    }

    fun getReplicationInterval(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getReplicationIntervalBind, segment)
    }

    fun setDeltaInterval(milliseconds: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDeltaIntervalBind, segment, milliseconds)
    }

    fun getDeltaInterval(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDeltaIntervalBind, segment)
    }

    fun setReplicationConfig(config: SceneReplicationConfig?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setReplicationConfigBind, segment, listOf(config?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun getReplicationConfig(): SceneReplicationConfig? {
        return SceneReplicationConfig.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getReplicationConfigBind, segment))
    }

    fun setVisibilityUpdateMode(mode: MultiplayerSynchronizer.VisibilityUpdateMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setVisibilityUpdateModeBind, segment, mode.value)
    }

    fun getVisibilityUpdateMode(): MultiplayerSynchronizer.VisibilityUpdateMode {
        return MultiplayerSynchronizer.VisibilityUpdateMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getVisibilityUpdateModeBind, segment))
    }

    fun updateVisibility(forPeer: Int = 0) {
        ObjectCalls.ptrcallWithIntArg(Binds.updateVisibilityBind, segment, forPeer)
    }

    fun setVisibilityPublic(visible: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setVisibilityPublicBind, segment, visible)
    }

    fun isVisibilityPublic(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isVisibilityPublicBind, segment)
    }

    fun addVisibilityFilter(filter: GodotCallable) {
        ObjectCalls.ptrcallWithCallableArg(Binds.addVisibilityFilterBind, segment, filter.target.segment, filter.method)
    }

    fun removeVisibilityFilter(filter: GodotCallable) {
        ObjectCalls.ptrcallWithCallableArg(Binds.removeVisibilityFilterBind, segment, filter.target.segment, filter.method)
    }

    fun setVisibilityFor(peer: Int, visible: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setVisibilityForBind, segment, peer, visible)
    }

    fun getVisibilityFor(peer: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.getVisibilityForBind, segment, peer)
    }

    /** Signal `synchronized()`; see [TypedSignal]. */
    val synchronized: Signal0
        @JvmName("synchronizedTypedSignal")
        get() = Signal0(this, "synchronized")

    /** Signal `delta_synchronized()`; see [TypedSignal]. */
    val deltaSynchronized: Signal0
        @JvmName("deltaSynchronizedTypedSignal")
        get() = Signal0(this, "delta_synchronized")

    /** Signal `visibility_changed(for_peer: int)`; see [TypedSignal]. */
    val visibilityChanged: Signal1<Long>
        @JvmName("visibilityChangedTypedSignal")
        get() = Signal1(this, "visibility_changed", SignalArgType.LONG)

    object Signals {
        const val synchronized: String = "synchronized"
        const val deltaSynchronized: String = "delta_synchronized"
        const val visibilityChanged: String = "visibility_changed"
    }

    @JvmInline
    value class VisibilityUpdateMode(override val value: Long) : GodotEnumValue {
        companion object {
            val IDLE: VisibilityUpdateMode get() = VisibilityUpdateMode(0L)
            val PHYSICS: VisibilityUpdateMode get() = VisibilityUpdateMode(1L)
            val NONE: VisibilityUpdateMode get() = VisibilityUpdateMode(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): MultiplayerSynchronizer? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): MultiplayerSynchronizer? =
            if (handle.address() == 0L) null else MultiplayerSynchronizer(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_ROOT_PATH_HASH = 1348162250L
        @JvmField
        val setRootPathBind =
            ObjectCalls.getMethodBind("MultiplayerSynchronizer", "set_root_path", SET_ROOT_PATH_HASH)

        private const val GET_ROOT_PATH_HASH = 4075236667L
        @JvmField
        val getRootPathBind =
            ObjectCalls.getMethodBind("MultiplayerSynchronizer", "get_root_path", GET_ROOT_PATH_HASH)

        private const val SET_REPLICATION_INTERVAL_HASH = 373806689L
        @JvmField
        val setReplicationIntervalBind =
            ObjectCalls.getMethodBind("MultiplayerSynchronizer", "set_replication_interval", SET_REPLICATION_INTERVAL_HASH)

        private const val GET_REPLICATION_INTERVAL_HASH = 1740695150L
        @JvmField
        val getReplicationIntervalBind =
            ObjectCalls.getMethodBind("MultiplayerSynchronizer", "get_replication_interval", GET_REPLICATION_INTERVAL_HASH)

        private const val SET_DELTA_INTERVAL_HASH = 373806689L
        @JvmField
        val setDeltaIntervalBind =
            ObjectCalls.getMethodBind("MultiplayerSynchronizer", "set_delta_interval", SET_DELTA_INTERVAL_HASH)

        private const val GET_DELTA_INTERVAL_HASH = 1740695150L
        @JvmField
        val getDeltaIntervalBind =
            ObjectCalls.getMethodBind("MultiplayerSynchronizer", "get_delta_interval", GET_DELTA_INTERVAL_HASH)

        private const val SET_REPLICATION_CONFIG_HASH = 3889206742L
        @JvmField
        val setReplicationConfigBind =
            ObjectCalls.getMethodBind("MultiplayerSynchronizer", "set_replication_config", SET_REPLICATION_CONFIG_HASH)

        private const val GET_REPLICATION_CONFIG_HASH = 3200254614L
        @JvmField
        val getReplicationConfigBind =
            ObjectCalls.getMethodBind("MultiplayerSynchronizer", "get_replication_config", GET_REPLICATION_CONFIG_HASH)

        private const val SET_VISIBILITY_UPDATE_MODE_HASH = 3494860300L
        @JvmField
        val setVisibilityUpdateModeBind =
            ObjectCalls.getMethodBind("MultiplayerSynchronizer", "set_visibility_update_mode", SET_VISIBILITY_UPDATE_MODE_HASH)

        private const val GET_VISIBILITY_UPDATE_MODE_HASH = 3352241418L
        @JvmField
        val getVisibilityUpdateModeBind =
            ObjectCalls.getMethodBind("MultiplayerSynchronizer", "get_visibility_update_mode", GET_VISIBILITY_UPDATE_MODE_HASH)

        private const val UPDATE_VISIBILITY_HASH = 1995695955L
        @JvmField
        val updateVisibilityBind =
            ObjectCalls.getMethodBind("MultiplayerSynchronizer", "update_visibility", UPDATE_VISIBILITY_HASH)

        private const val SET_VISIBILITY_PUBLIC_HASH = 2586408642L
        @JvmField
        val setVisibilityPublicBind =
            ObjectCalls.getMethodBind("MultiplayerSynchronizer", "set_visibility_public", SET_VISIBILITY_PUBLIC_HASH)

        private const val IS_VISIBILITY_PUBLIC_HASH = 36873697L
        @JvmField
        val isVisibilityPublicBind =
            ObjectCalls.getMethodBind("MultiplayerSynchronizer", "is_visibility_public", IS_VISIBILITY_PUBLIC_HASH)

        private const val ADD_VISIBILITY_FILTER_HASH = 1611583062L
        @JvmField
        val addVisibilityFilterBind =
            ObjectCalls.getMethodBind("MultiplayerSynchronizer", "add_visibility_filter", ADD_VISIBILITY_FILTER_HASH)

        private const val REMOVE_VISIBILITY_FILTER_HASH = 1611583062L
        @JvmField
        val removeVisibilityFilterBind =
            ObjectCalls.getMethodBind("MultiplayerSynchronizer", "remove_visibility_filter", REMOVE_VISIBILITY_FILTER_HASH)

        private const val SET_VISIBILITY_FOR_HASH = 300928843L
        @JvmField
        val setVisibilityForBind =
            ObjectCalls.getMethodBind("MultiplayerSynchronizer", "set_visibility_for", SET_VISIBILITY_FOR_HASH)

        private const val GET_VISIBILITY_FOR_HASH = 1116898809L
        @JvmField
        val getVisibilityForBind =
            ObjectCalls.getMethodBind("MultiplayerSynchronizer", "get_visibility_for", GET_VISIBILITY_FOR_HASH)
    }
}
