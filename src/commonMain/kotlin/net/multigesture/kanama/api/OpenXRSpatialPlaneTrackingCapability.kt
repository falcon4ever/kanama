package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.RID

/**
 * Generated from Godot docs: OpenXRSpatialPlaneTrackingCapability
 */
class OpenXRSpatialPlaneTrackingCapability(handle: GodotHandle) : OpenXRExtensionWrapper(handle) {
    fun isSupported(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSupportedBind, segment)
    }

    fun startEntityDiscovery(spatialContext: RID, componentData: List<OpenXRSpatialComponentData>, nextSnapshotCreate: OpenXRStructureBase?, nextSnapshotQuery: OpenXRStructureBase?, userCallback: GodotCallable): OpenXRFutureResult? {
        return OpenXRFutureResult.wrap(ObjectCalls.ptrcallWithRIDObjectListTwoObjectCallableArgsRetObject(startEntityDiscoveryBind, segment, spatialContext, componentData, nextSnapshotCreate?.requireOpenHandle() ?: NULL_SEGMENT, nextSnapshotQuery?.requireOpenHandle() ?: NULL_SEGMENT, userCallback.target.segment, userCallback.method))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialPlaneTrackingCapability? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRSpatialPlaneTrackingCapability? =
            if (handle.address() == 0L) null else OpenXRSpatialPlaneTrackingCapability(GodotHandle(handle))

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
