package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Object for storing OpenXR spatial entity component data.
 *
 * Generated from Godot docs: OpenXRSpatialComponentData
 */
open class OpenXRSpatialComponentData(handle: GodotHandle) : RefCounted(handle) {
    /**
     * Sets the expected capacity as provided by the spatial entities query system. Buffers should be
     * initialized with the correct storage.
     *
     * Generated from Godot docs: OpenXRSpatialComponentData.set_capacity
     */
    fun setCapacity(capacity: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setCapacityBind, segment, capacity)
    }

    /**
     * Gets this `OpenXRSpatialComponentData`'s `XrSpatialComponentTypeEXT`.
     *
     * Generated from Godot docs: OpenXRSpatialComponentData.get_component_type
     */
    fun getComponentType(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(Binds.getComponentTypeBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialComponentData? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRSpatialComponentData? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRSpatialComponentData(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRSpatialComponentData? =
            if (handle.address() == 0L) null else OpenXRSpatialComponentData(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_CAPACITY_HASH = 1286410249L
        @JvmField
        val setCapacityBind =
            ObjectCalls.getMethodBind("OpenXRSpatialComponentData", "set_capacity", SET_CAPACITY_HASH)

        private const val GET_COMPONENT_TYPE_HASH = 3905245786L
        @JvmField
        val getComponentTypeBind =
            ObjectCalls.getMethodBind("OpenXRSpatialComponentData", "get_component_type", GET_COMPONENT_TYPE_HASH)
    }
}
