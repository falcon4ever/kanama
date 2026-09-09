package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: OpenXRSpatialComponentData
 */
open class OpenXRSpatialComponentData(handle: MemorySegment) : RefCounted(handle) {
    fun setCapacity(capacity: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithUInt32Arg(setCapacityBind, handle, capacity)
    }

    fun getComponentType(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getComponentTypeBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRSpatialComponentData? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRSpatialComponentData? =
            if (handle.address() == 0L) null else OpenXRSpatialComponentData(handle)

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
