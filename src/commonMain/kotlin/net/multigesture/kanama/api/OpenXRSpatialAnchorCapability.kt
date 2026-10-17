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
 * Implementation for handling spatial entity anchor logic.
 *
 * Generated from Godot docs: OpenXRSpatialAnchorCapability
 */
class OpenXRSpatialAnchorCapability(handle: GodotHandle) : OpenXRExtensionWrapper(handle) {
    /**
     * Returns `true` if spatial anchors are supported by the hardware. Only returns a valid value
     * after OpenXR has been initialized.
     *
     * Generated from Godot docs: OpenXRSpatialAnchorCapability.is_spatial_anchor_supported
     */
    fun isSpatialAnchorSupported(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isSpatialAnchorSupportedBind, segment)
    }

    /**
     * Returns `true` if persistent spatial anchors are supported by the hardware. Only returns a valid
     * value after OpenXR has been initialized.
     *
     * Generated from Godot docs: OpenXRSpatialAnchorCapability.is_spatial_persistence_supported
     */
    fun isSpatialPersistenceSupported(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isSpatialPersistenceSupportedBind, segment)
    }

    /**
     * Returns `true` if this persistence scope is supported by our spatial anchor capability. Note:
     * Only valid after an OpenXR instance has been created.
     *
     * Generated from Godot docs: OpenXRSpatialAnchorCapability.is_persistence_scope_supported
     */
    fun isPersistenceScopeSupported(scope: OpenXRSpatialAnchorCapability.PersistenceScope): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(Binds.isPersistenceScopeSupportedBind, segment, scope.value)
    }

    /**
     * Calls `create_persistence_context` with a configuration that likely works with the XR runtime.
     * `user_callback` is called when the context is created.
     *
     * Generated from Godot docs: OpenXRSpatialAnchorCapability.create_default_persistence_context
     */
    fun createDefaultPersistenceContext(userCallback: GodotCallable): OpenXRFutureResult? {
        val ret = OpenXRFutureResult.wrapOwned(ObjectCalls.ptrcallWithCallableArgRetObject(Binds.createDefaultPersistenceContextBind, segment, userCallback.target.segment, userCallback.method))
        ObjectCalls.keepReachable(userCallback)
        return ret
    }

    /**
     * Creates a new persistence context for storing persistent data. Note: This is an asynchronous
     * method and returns an `OpenXRFutureResult` object with which to track the status, discarding
     * this object will not cancel the creation process. On success `user_callback` will be called if
     * specified. The result value for this function is the `RID` for our persistence context.
     *
     * Generated from Godot docs: OpenXRSpatialAnchorCapability.create_persistence_context
     */
    fun createPersistenceContext(scope: OpenXRSpatialAnchorCapability.PersistenceScope, userCallback: GodotCallable): OpenXRFutureResult? {
        val ret = OpenXRFutureResult.wrapOwned(ObjectCalls.ptrcallWithLongCallableArgsRetObject(Binds.createPersistenceContextBind, segment, scope.value, userCallback.target.segment, userCallback.method))
        ObjectCalls.keepReachable(userCallback)
        return ret
    }

    /**
     * Returns the internal handle for this persistence context. Note: For GDExtension implementations.
     *
     * Generated from Godot docs: OpenXRSpatialAnchorCapability.get_persistence_context_handle
     */
    fun getPersistenceContextHandle(persistenceContext: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(Binds.getPersistenceContextHandleBind, segment, persistenceContext)
    }

    /**
     * Frees a persistence context previously created with `create_persistence_context`.
     *
     * Generated from Godot docs: OpenXRSpatialAnchorCapability.free_persistence_context
     */
    fun freePersistenceContext(persistenceContext: RID) {
        ObjectCalls.ptrcallWithRIDArg(Binds.freePersistenceContextBind, segment, persistenceContext)
    }

    /**
     * Creates a new anchor that will be tracked by the XR runtime. The `transform` should be a
     * transform in the local space of your `XROrigin3D` node. If `spatial_context` is not specified
     * the default will be used, this requires
     * `ProjectSettings.xr/openxr/extensions/spatial_entity/enable_builtin_anchor_detection` to be set.
     * The returned tracker will track the location in case our reference space changes. `next` must be
     * a valid next object for the `XrSpatialAnchorCreateInfoEXT` chain.
     *
     * Generated from Godot docs: OpenXRSpatialAnchorCapability.create_new_anchor
     */
    fun createNewAnchor(transform: Transform3D, spatialContext: RID, next: OpenXRStructureBase? = null): OpenXRAnchorTracker? {
        val ret = OpenXRAnchorTracker.wrapOwned(ObjectCalls.ptrcallWithTransform3DRIDObjectArgsRetObject(Binds.createNewAnchorBind, segment, transform, spatialContext, next?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(next)
        return ret
    }

    /**
     * Remove an anchor previously created with `create_new_anchor`. If this anchor was persistent you
     * must first call `unpersist_anchor` and await its callback.
     *
     * Generated from Godot docs: OpenXRSpatialAnchorCapability.remove_anchor
     */
    fun removeAnchor(anchorTracker: OpenXRAnchorTracker?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.removeAnchorBind, segment, listOf(anchorTracker?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(anchorTracker)
    }

    /**
     * Changes this anchor into a persistent anchor. This means its location will be stored on the
     * device and the anchor will be restored the next time your application starts. If
     * `persistence_context` is not specified the default will be used, this requires
     * `ProjectSettings.xr/openxr/extensions/spatial_entity/enable_builtin_anchor_detection` to be set.
     * Note: This is an asynchronous method and returns an `OpenXRFutureResult` object with which to
     * track the status, discarding this object will not cancel the creation process. On success
     * `user_callback` will be called if specified. The result value for this function is a boolean
     * which will be set to `true` on successful completion.
     *
     * Generated from Godot docs: OpenXRSpatialAnchorCapability.persist_anchor
     */
    fun persistAnchor(anchorTracker: OpenXRAnchorTracker?, persistenceContext: RID, userCallback: GodotCallable): OpenXRFutureResult? {
        val ret = OpenXRFutureResult.wrapOwned(ObjectCalls.ptrcallWithObjectRIDCallableArgsRetObject(Binds.persistAnchorBind, segment, anchorTracker?.requireOpenHandle() ?: NULL_SEGMENT, persistenceContext, userCallback.target.segment, userCallback.method))
        ObjectCalls.keepReachable(anchorTracker)
        ObjectCalls.keepReachable(userCallback)
        return ret
    }

    /**
     * Removes the persistent data from this anchor. The runtime will not recreate the anchor when your
     * application restarts. If `persistence_context` is not specified the default will be used, this
     * requires `ProjectSettings.xr/openxr/extensions/spatial_entity/enabled` to be set. Note: This is
     * an asynchronous method and returns an `OpenXRFutureResult` object with which to track the
     * status, discarding this object will not cancel the creation process. On success `user_callback`
     * will be called if specified. The result value for this function is a boolean which will be set
     * to `true` on successful completion.
     *
     * Generated from Godot docs: OpenXRSpatialAnchorCapability.unpersist_anchor
     */
    fun unpersistAnchor(anchorTracker: OpenXRAnchorTracker?, persistenceContext: RID, userCallback: GodotCallable): OpenXRFutureResult? {
        val ret = OpenXRFutureResult.wrapOwned(ObjectCalls.ptrcallWithObjectRIDCallableArgsRetObject(Binds.unpersistAnchorBind, segment, anchorTracker?.requireOpenHandle() ?: NULL_SEGMENT, persistenceContext, userCallback.target.segment, userCallback.method))
        ObjectCalls.keepReachable(anchorTracker)
        ObjectCalls.keepReachable(userCallback)
        return ret
    }

    /**
     * Calls `OpenXRSpatialEntityExtension.discover_spatial_entities` and
     * `OpenXRSpatialEntityExtension.query_snapshot` with the anchor entities associated with
     * `spatial_context`. `component_data` are the `OpenXRSpatialComponentData`s to discover for this
     * anchor capability. If `next_snapshot_create` is non-null, then pass this to the `next` parameter
     * in `OpenXRSpatialEntityExtension.discover_spatial_entities`. If `next_snapshot_query` is
     * non-null, then pass this to the `next` parameter in
     * `OpenXRSpatialEntityExtension.query_snapshot`. `user_callback`, when non-null, is called with
     * two parameters usually twice. The first parameter is the `RID` of the discovery snapshot and the
     * second parameter is a boolean where `false` indicates the discovery snapshot is about to be
     * processed, and `true` indicates the discovery snapshot has been processed and `component_data`
     * has valid data. The second call is skipped if an error was encountered. The returned
     * `OpenXRFutureResult` is identical to the return from
     * `OpenXRSpatialEntityExtension.discover_spatial_entities`.
     *
     * Generated from Godot docs: OpenXRSpatialAnchorCapability.start_entity_discovery
     */
    fun startEntityDiscovery(spatialContext: RID, componentData: List<OpenXRSpatialComponentData>, nextSnapshotCreate: OpenXRStructureBase? = null, nextSnapshotQuery: OpenXRStructureBase? = null, userCallback: GodotCallable): OpenXRFutureResult? {
        val ret = OpenXRFutureResult.wrapOwned(ObjectCalls.ptrcallWithRIDObjectListTwoObjectCallableArgsRetObject(Binds.startEntityDiscoveryBind, segment, spatialContext, componentData, nextSnapshotCreate?.requireOpenHandle() ?: NULL_SEGMENT, nextSnapshotQuery?.requireOpenHandle() ?: NULL_SEGMENT, userCallback.target.segment, userCallback.method))
        ObjectCalls.keepReachable(nextSnapshotCreate)
        ObjectCalls.keepReachable(nextSnapshotQuery)
        ObjectCalls.keepReachable(userCallback)
        return ret
    }

    /**
     * Calls `OpenXRSpatialEntityExtension.update_spatial_entities` and
     * `OpenXRSpatialEntityExtension.query_snapshot` with the anchor entities associated with
     * `spatial_context`. `component_data` are the `OpenXRSpatialComponentData`s to update for this
     * anchor capability. If `next_snapshot_create` is non-null, then pass this to the `next` parameter
     * in `OpenXRSpatialEntityExtension.update_spatial_entities`. If `next_snapshot_query` is non-null,
     * then pass this to the `next` parameter in `OpenXRSpatialEntityExtension.query_snapshot`.
     *
     * Generated from Godot docs: OpenXRSpatialAnchorCapability.do_entity_update
     */
    fun doEntityUpdate(spatialContext: RID, componentData: List<OpenXRSpatialComponentData>, nextSnapshotCreate: OpenXRStructureBase? = null, nextSnapshotQuery: OpenXRStructureBase? = null) {
        ObjectCalls.ptrcallWithRIDObjectListTwoObjectArgs(Binds.doEntityUpdateBind, segment, spatialContext, componentData, nextSnapshotCreate?.requireOpenHandle() ?: NULL_SEGMENT, nextSnapshotQuery?.requireOpenHandle() ?: NULL_SEGMENT)
        ObjectCalls.keepReachable(nextSnapshotCreate)
        ObjectCalls.keepReachable(nextSnapshotQuery)
    }

    /**
     * Godot's `OpenXRSpatialAnchorCapability.PersistenceScope` enum as a typed value: `.value` is the
     * raw number Godot uses, and the companion holds the named values
     * (`OpenXRSpatialAnchorCapability.PersistenceScope.<NAME>`).
     *
     * Generated from Godot docs: OpenXRSpatialAnchorCapability.PersistenceScope
     */
    @JvmInline
    value class PersistenceScope(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Provides the application with read-only access (i.e. application cannot modify this scope) to
             * spatial entities persisted and managed by the system. The application can use the UUID in the
             * persistence component for this scope to correlate entities across spatial contexts and device
             * reboots.
             *
             * Generated from Godot docs: OpenXRSpatialAnchorCapability.PERSISTENCE_SCOPE_SYSTEM_MANAGED
             */
            val SYSTEM_MANAGED: PersistenceScope get() = PersistenceScope(1L)
            /**
             * Persistence operations and data access is limited to spatial anchors, on the same device, for
             * the same user and same app (using `persist_anchor` and `unpersist_anchor` functions)
             *
             * Generated from Godot docs: OpenXRSpatialAnchorCapability.PERSISTENCE_SCOPE_LOCAL_ANCHORS
             */
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
