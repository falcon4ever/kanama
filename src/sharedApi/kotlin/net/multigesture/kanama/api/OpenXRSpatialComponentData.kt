package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: OpenXRSpatialComponentData
 */
open class OpenXRSpatialComponentData(handle: GodotHandle) : RefCounted(handle) {
    fun setCapacity(capacity: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithUInt32Arg(setCapacityBind, segment, capacity)
    }

    fun getComponentType(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getComponentTypeBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialComponentData? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRSpatialComponentData? =
            if (handle.address() == 0L) null else OpenXRSpatialComponentData(GodotHandle(handle))

        private const val SET_CAPACITY_HASH = 1286410249L
        private val setCapacityBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialComponentData", "set_capacity", SET_CAPACITY_HASH)
        }

        private const val GET_COMPONENT_TYPE_HASH = 3905245786L
        private val getComponentTypeBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialComponentData", "get_component_type", GET_COMPONENT_TYPE_HASH)
        }
    }
}
