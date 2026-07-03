package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Placeholder for the root `Node` of a `PackedScene`.
 *
 * Generated from Godot docs: InstancePlaceholder
 */
class InstancePlaceholder(handle: MemorySegment) : Node(handle) {
    /**
     * Returns the list of properties that will be applied to the node when `create_instance` is
     * called. If `with_order` is `true`, a key named `.order` (note the leading period) is added to
     * the dictionary. This `.order` key is an `Array` of `String` property names specifying the order
     * in which properties will be applied (with index 0 being the first).
     *
     * Generated from Godot docs: InstancePlaceholder.get_stored_values
     */
    fun getStoredValues(withOrder: Boolean = false): Map<String, Any?> {
        return ObjectCalls.ptrcallWithBoolArgRetDictionary(getStoredValuesBind, handle, withOrder)
    }

    /**
     * Call this method to actually load in the node. The created node will be placed as a sibling
     * above the `InstancePlaceholder` in the scene tree. The `Node`'s reference is also returned for
     * convenience. Note: `create_instance` is not thread-safe. Use `Object.call_deferred` if calling
     * from a thread.
     *
     * Generated from Godot docs: InstancePlaceholder.create_instance
     */
    fun createInstance(replace: Boolean = false, customScene: PackedScene?): Node? {
        return Node.wrap(ObjectCalls.ptrcallWithBoolObjectArgsRetObject(createInstanceBind, handle, replace, customScene?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Gets the path to the `PackedScene` resource file that is loaded by default when calling
     * `create_instance`. Not thread-safe. Use `Object.call_deferred` if calling from a thread.
     *
     * Generated from Godot docs: InstancePlaceholder.get_instance_path
     */
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
