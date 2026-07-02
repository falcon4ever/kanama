package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

/**
 * Generated from Godot docs: OpenXRSpatialPlaneTrackingCapability
 */
class OpenXRSpatialPlaneTrackingCapability(handle: MemorySegment) : OpenXRExtensionWrapper(handle) {
    fun isSupported(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSupportedBind, handle)
    }

    fun startEntityDiscovery(spatialContext: RID, componentData: List<OpenXRSpatialComponentData>, nextSnapshotCreate: OpenXRStructureBase?, nextSnapshotQuery: OpenXRStructureBase?, userCallback: GodotCallable): OpenXRFutureResult? {
        return OpenXRFutureResult.wrap(ObjectCalls.ptrcallWithRIDObjectListTwoObjectCallableArgsRetObject(startEntityDiscoveryBind, handle, spatialContext, componentData, nextSnapshotCreate?.requireOpenHandle() ?: MemorySegment.NULL, nextSnapshotQuery?.requireOpenHandle() ?: MemorySegment.NULL, userCallback.target.handle, userCallback.method))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRSpatialPlaneTrackingCapability? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRSpatialPlaneTrackingCapability? =
            if (handle.address() == 0L) null else OpenXRSpatialPlaneTrackingCapability(handle)

        private const val IS_SUPPORTED_HASH = 2240911060L
        private val isSupportedBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialPlaneTrackingCapability", "is_supported", IS_SUPPORTED_HASH)
        }

        private const val START_ENTITY_DISCOVERY_HASH = 3452714169L
        private val startEntityDiscoveryBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialPlaneTrackingCapability", "start_entity_discovery", START_ENTITY_DISCOVERY_HASH)
        }
    }
}
