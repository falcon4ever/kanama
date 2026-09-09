package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Transform3D

/**
 * Generated from Godot docs: OpenXRSpatialAnchorCapability
 */
class OpenXRSpatialAnchorCapability(handle: MemorySegment) : OpenXRExtensionWrapper(handle) {
    fun isSpatialAnchorSupported(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSpatialAnchorSupportedBind, handle)
    }

    fun isSpatialPersistenceSupported(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSpatialPersistenceSupportedBind, handle)
    }

    fun isPersistenceScopeSupported(scope: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(isPersistenceScopeSupportedBind, handle, scope)
    }

    fun createDefaultPersistenceContext(userCallback: GodotCallable): OpenXRFutureResult? {
        return OpenXRFutureResult.wrap(ObjectCalls.ptrcallWithCallableArgRetObject(createDefaultPersistenceContextBind, handle, userCallback.target.handle, userCallback.method))
    }

    fun createPersistenceContext(scope: Long, userCallback: GodotCallable): OpenXRFutureResult? {
        return OpenXRFutureResult.wrap(ObjectCalls.ptrcallWithLongCallableArgsRetObject(createPersistenceContextBind, handle, scope, userCallback.target.handle, userCallback.method))
    }

    fun getPersistenceContextHandle(persistenceContext: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(getPersistenceContextHandleBind, handle, persistenceContext)
    }

    fun freePersistenceContext(persistenceContext: RID) {
        ObjectCalls.ptrcallWithRIDArg(freePersistenceContextBind, handle, persistenceContext)
    }

    fun createNewAnchor(transform: Transform3D, spatialContext: RID, next: OpenXRStructureBase?): OpenXRAnchorTracker? {
        return OpenXRAnchorTracker.wrap(ObjectCalls.ptrcallWithTransform3DRIDObjectArgsRetObject(createNewAnchorBind, handle, transform, spatialContext, next?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun removeAnchor(anchorTracker: OpenXRAnchorTracker?) {
        ObjectCalls.ptrcallWithObjectArgs(removeAnchorBind, handle, listOf(anchorTracker?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun persistAnchor(anchorTracker: OpenXRAnchorTracker?, persistenceContext: RID, userCallback: GodotCallable): OpenXRFutureResult? {
        return OpenXRFutureResult.wrap(ObjectCalls.ptrcallWithObjectRIDCallableArgsRetObject(persistAnchorBind, handle, anchorTracker?.requireOpenHandle() ?: MemorySegment.NULL, persistenceContext, userCallback.target.handle, userCallback.method))
    }

    fun unpersistAnchor(anchorTracker: OpenXRAnchorTracker?, persistenceContext: RID, userCallback: GodotCallable): OpenXRFutureResult? {
        return OpenXRFutureResult.wrap(ObjectCalls.ptrcallWithObjectRIDCallableArgsRetObject(unpersistAnchorBind, handle, anchorTracker?.requireOpenHandle() ?: MemorySegment.NULL, persistenceContext, userCallback.target.handle, userCallback.method))
    }

    companion object {
        const val PERSISTENCE_SCOPE_SYSTEM_MANAGED: Long = 1L
        const val PERSISTENCE_SCOPE_LOCAL_ANCHORS: Long = 1000781000L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRSpatialAnchorCapability? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRSpatialAnchorCapability? =
            if (handle.address() == 0L) null else OpenXRSpatialAnchorCapability(handle)

        private const val IS_SPATIAL_ANCHOR_SUPPORTED_HASH = 2240911060L
        private val isSpatialAnchorSupportedBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialAnchorCapability", "is_spatial_anchor_supported", IS_SPATIAL_ANCHOR_SUPPORTED_HASH)
        }

        private const val IS_SPATIAL_PERSISTENCE_SUPPORTED_HASH = 2240911060L
        private val isSpatialPersistenceSupportedBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialAnchorCapability", "is_spatial_persistence_supported", IS_SPATIAL_PERSISTENCE_SUPPORTED_HASH)
        }

        private const val IS_PERSISTENCE_SCOPE_SUPPORTED_HASH = 3651771626L
        private val isPersistenceScopeSupportedBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialAnchorCapability", "is_persistence_scope_supported", IS_PERSISTENCE_SCOPE_SUPPORTED_HASH)
        }

        private const val CREATE_DEFAULT_PERSISTENCE_CONTEXT_HASH = 1401033661L
        private val createDefaultPersistenceContextBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialAnchorCapability", "create_default_persistence_context", CREATE_DEFAULT_PERSISTENCE_CONTEXT_HASH)
        }

        private const val CREATE_PERSISTENCE_CONTEXT_HASH = 856276630L
        private val createPersistenceContextBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialAnchorCapability", "create_persistence_context", CREATE_PERSISTENCE_CONTEXT_HASH)
        }

        private const val GET_PERSISTENCE_CONTEXT_HANDLE_HASH = 2198884583L
        private val getPersistenceContextHandleBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialAnchorCapability", "get_persistence_context_handle", GET_PERSISTENCE_CONTEXT_HANDLE_HASH)
        }

        private const val FREE_PERSISTENCE_CONTEXT_HASH = 2722037293L
        private val freePersistenceContextBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialAnchorCapability", "free_persistence_context", FREE_PERSISTENCE_CONTEXT_HASH)
        }

        private const val CREATE_NEW_ANCHOR_HASH = 4088043487L
        private val createNewAnchorBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialAnchorCapability", "create_new_anchor", CREATE_NEW_ANCHOR_HASH)
        }

        private const val REMOVE_ANCHOR_HASH = 3579451518L
        private val removeAnchorBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialAnchorCapability", "remove_anchor", REMOVE_ANCHOR_HASH)
        }

        private const val PERSIST_ANCHOR_HASH = 4244202513L
        private val persistAnchorBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialAnchorCapability", "persist_anchor", PERSIST_ANCHOR_HASH)
        }

        private const val UNPERSIST_ANCHOR_HASH = 4244202513L
        private val unpersistAnchorBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialAnchorCapability", "unpersist_anchor", UNPERSIST_ANCHOR_HASH)
        }
    }
}
