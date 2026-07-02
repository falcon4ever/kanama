package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Placeholder for the root `Node` of a `PackedScene`.
 *
 * Generated from Godot docs: InstancePlaceholder
 */
class InstancePlaceholder(handle: MemorySegment) : Node(handle) {
    fun getStoredValues(withOrder: Boolean = false): Map<String, Any?> {
        return ObjectCalls.ptrcallWithBoolArgRetDictionary(getStoredValuesBind, handle, withOrder)
    }

    fun createInstance(replace: Boolean = false, customScene: PackedScene?): Node? {
        return Node.wrap(ObjectCalls.ptrcallWithBoolObjectArgsRetObject(createInstanceBind, handle, replace, customScene?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getInstancePath(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getInstancePathBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): InstancePlaceholder? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): InstancePlaceholder? =
            if (handle.address() == 0L) null else InstancePlaceholder(handle)

        private const val GET_STORED_VALUES_HASH = 2230153369L
        private val getStoredValuesBind by lazy {
            ObjectCalls.getMethodBind("InstancePlaceholder", "get_stored_values", GET_STORED_VALUES_HASH)
        }

        private const val CREATE_INSTANCE_HASH = 3794612210L
        private val createInstanceBind by lazy {
            ObjectCalls.getMethodBind("InstancePlaceholder", "create_instance", CREATE_INSTANCE_HASH)
        }

        private const val GET_INSTANCE_PATH_HASH = 201670096L
        private val getInstancePathBind by lazy {
            ObjectCalls.getMethodBind("InstancePlaceholder", "get_instance_path", GET_INSTANCE_PATH_HASH)
        }
    }
}
