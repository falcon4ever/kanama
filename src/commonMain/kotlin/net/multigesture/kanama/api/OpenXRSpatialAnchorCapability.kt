package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Transform3D

/**
 * Generated from Godot docs: OpenXRSpatialAnchorCapability
 */
class OpenXRSpatialAnchorCapability(handle: GodotHandle) : OpenXRExtensionWrapper(handle) {
    fun isSpatialAnchorSupported(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isSpatialAnchorSupportedBind, segment)
    }

    fun isSpatialPersistenceSupported(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isSpatialPersistenceSupportedBind, segment)
    }

    fun isPersistenceScopeSupported(scope: OpenXRSpatialAnchorCapability.PersistenceScope): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(Binds.isPersistenceScopeSupportedBind, segment, scope.value)
    }

    fun createDefaultPersistenceContext(userCallback: GodotCallable): OpenXRFutureResult? {
        return OpenXRFutureResult.wrapOwned(ObjectCalls.ptrcallWithCallableArgRetObject(Binds.createDefaultPersistenceContextBind, segment, userCallback.target.segment, userCallback.method))
    }

    fun createPersistenceContext(scope: OpenXRSpatialAnchorCapability.PersistenceScope, userCallback: GodotCallable): OpenXRFutureResult? {
        return OpenXRFutureResult.wrapOwned(ObjectCalls.ptrcallWithLongCallableArgsRetObject(Binds.createPersistenceContextBind, segment, scope.value, userCallback.target.segment, userCallback.method))
    }

    fun getPersistenceContextHandle(persistenceContext: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(Binds.getPersistenceContextHandleBind, segment, persistenceContext)
    }

    fun freePersistenceContext(persistenceContext: RID) {
        ObjectCalls.ptrcallWithRIDArg(Binds.freePersistenceContextBind, segment, persistenceContext)
    }

    fun createNewAnchor(transform: Transform3D, spatialContext: RID, next: OpenXRStructureBase?): OpenXRAnchorTracker? {
        return OpenXRAnchorTracker.wrapOwned(ObjectCalls.ptrcallWithTransform3DRIDObjectArgsRetObject(Binds.createNewAnchorBind, segment, transform, spatialContext, next?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun removeAnchor(anchorTracker: OpenXRAnchorTracker?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.removeAnchorBind, segment, listOf(anchorTracker?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun persistAnchor(anchorTracker: OpenXRAnchorTracker?, persistenceContext: RID, userCallback: GodotCallable): OpenXRFutureResult? {
        return OpenXRFutureResult.wrapOwned(ObjectCalls.ptrcallWithObjectRIDCallableArgsRetObject(Binds.persistAnchorBind, segment, anchorTracker?.requireOpenHandle() ?: NULL_SEGMENT, persistenceContext, userCallback.target.segment, userCallback.method))
    }

    fun unpersistAnchor(anchorTracker: OpenXRAnchorTracker?, persistenceContext: RID, userCallback: GodotCallable): OpenXRFutureResult? {
        return OpenXRFutureResult.wrapOwned(ObjectCalls.ptrcallWithObjectRIDCallableArgsRetObject(Binds.unpersistAnchorBind, segment, anchorTracker?.requireOpenHandle() ?: NULL_SEGMENT, persistenceContext, userCallback.target.segment, userCallback.method))
    }

    fun startEntityDiscovery(spatialContext: RID, componentData: List<OpenXRSpatialComponentData>, nextSnapshotCreate: OpenXRStructureBase?, nextSnapshotQuery: OpenXRStructureBase?, userCallback: GodotCallable): OpenXRFutureResult? {
        return OpenXRFutureResult.wrapOwned(ObjectCalls.ptrcallWithRIDObjectListTwoObjectCallableArgsRetObject(Binds.startEntityDiscoveryBind, segment, spatialContext, componentData, nextSnapshotCreate?.requireOpenHandle() ?: NULL_SEGMENT, nextSnapshotQuery?.requireOpenHandle() ?: NULL_SEGMENT, userCallback.target.segment, userCallback.method))
    }

    fun doEntityUpdate(spatialContext: RID, componentData: List<OpenXRSpatialComponentData>, nextSnapshotCreate: OpenXRStructureBase?, nextSnapshotQuery: OpenXRStructureBase?) {
        ObjectCalls.ptrcallWithRIDObjectListTwoObjectArgs(Binds.doEntityUpdateBind, segment, spatialContext, componentData, nextSnapshotCreate?.requireOpenHandle() ?: NULL_SEGMENT, nextSnapshotQuery?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    @JvmInline
    value class PersistenceScope(override val value: Long) : GodotEnumValue {
        companion object {
            val SYSTEM_MANAGED: PersistenceScope get() = PersistenceScope(1L)
            val LOCAL_ANCHORS: PersistenceScope get() = PersistenceScope(1000781000L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialAnchorCapability? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRSpatialAnchorCapability? =
            if (handle.address() == 0L) null else OpenXRSpatialAnchorCapability(GodotHandle(handle))
    }

    private object Binds {
        private const val IS_SPATIAL_ANCHOR_SUPPORTED_HASH = 2240911060L
        @JvmField
        val isSpatialAnchorSupportedBind =
            ObjectCalls.getMethodBind("OpenXRSpatialAnchorCapability", "is_spatial_anchor_supported", IS_SPATIAL_ANCHOR_SUPPORTED_HASH)

        private const val IS_SPATIAL_PERSISTENCE_SUPPORTED_HASH = 2240911060L
        @JvmField
        val isSpatialPersistenceSupportedBind =
            ObjectCalls.getMethodBind("OpenXRSpatialAnchorCapability", "is_spatial_persistence_supported", IS_SPATIAL_PERSISTENCE_SUPPORTED_HASH)

        private const val IS_PERSISTENCE_SCOPE_SUPPORTED_HASH = 3651771626L
        @JvmField
        val isPersistenceScopeSupportedBind =
            ObjectCalls.getMethodBind("OpenXRSpatialAnchorCapability", "is_persistence_scope_supported", IS_PERSISTENCE_SCOPE_SUPPORTED_HASH)

        private const val CREATE_DEFAULT_PERSISTENCE_CONTEXT_HASH = 1401033661L
        @JvmField
        val createDefaultPersistenceContextBind =
            ObjectCalls.getMethodBind("OpenXRSpatialAnchorCapability", "create_default_persistence_context", CREATE_DEFAULT_PERSISTENCE_CONTEXT_HASH)

        private const val CREATE_PERSISTENCE_CONTEXT_HASH = 856276630L
        @JvmField
        val createPersistenceContextBind =
            ObjectCalls.getMethodBind("OpenXRSpatialAnchorCapability", "create_persistence_context", CREATE_PERSISTENCE_CONTEXT_HASH)

        private const val GET_PERSISTENCE_CONTEXT_HANDLE_HASH = 2198884583L
        @JvmField
        val getPersistenceContextHandleBind =
            ObjectCalls.getMethodBind("OpenXRSpatialAnchorCapability", "get_persistence_context_handle", GET_PERSISTENCE_CONTEXT_HANDLE_HASH)

        private const val FREE_PERSISTENCE_CONTEXT_HASH = 2722037293L
        @JvmField
        val freePersistenceContextBind =
            ObjectCalls.getMethodBind("OpenXRSpatialAnchorCapability", "free_persistence_context", FREE_PERSISTENCE_CONTEXT_HASH)

        private const val CREATE_NEW_ANCHOR_HASH = 4088043487L
        @JvmField
        val createNewAnchorBind =
            ObjectCalls.getMethodBind("OpenXRSpatialAnchorCapability", "create_new_anchor", CREATE_NEW_ANCHOR_HASH)

        private const val REMOVE_ANCHOR_HASH = 3579451518L
        @JvmField
        val removeAnchorBind =
            ObjectCalls.getMethodBind("OpenXRSpatialAnchorCapability", "remove_anchor", REMOVE_ANCHOR_HASH)

        private const val PERSIST_ANCHOR_HASH = 4244202513L
        @JvmField
        val persistAnchorBind =
            ObjectCalls.getMethodBind("OpenXRSpatialAnchorCapability", "persist_anchor", PERSIST_ANCHOR_HASH)

        private const val UNPERSIST_ANCHOR_HASH = 4244202513L
        @JvmField
        val unpersistAnchorBind =
            ObjectCalls.getMethodBind("OpenXRSpatialAnchorCapability", "unpersist_anchor", UNPERSIST_ANCHOR_HASH)

        private const val START_ENTITY_DISCOVERY_HASH = 3452714169L
        @JvmField
        val startEntityDiscoveryBind =
            ObjectCalls.getMethodBind("OpenXRSpatialAnchorCapability", "start_entity_discovery", START_ENTITY_DISCOVERY_HASH)

        private const val DO_ENTITY_UPDATE_HASH = 3138044275L
        @JvmField
        val doEntityUpdateBind =
            ObjectCalls.getMethodBind("OpenXRSpatialAnchorCapability", "do_entity_update", DO_ENTITY_UPDATE_HASH)
    }
}
