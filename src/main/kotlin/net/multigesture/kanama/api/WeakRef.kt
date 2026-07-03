package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Holds an `Object`. If the object is `RefCounted`, it doesn't update the reference count.
 *
 * Generated from Godot docs: WeakRef
 */
class WeakRef(handle: MemorySegment) : RefCounted(handle) {
    /**
     * Returns the `Object` this weakref is referring to. Returns `null` if that object no longer
     * exists.
     *
     * Generated from Godot docs: WeakRef.get_ref
     */
    fun getRef(): Any? {
        return ObjectCalls.ptrcallNoArgsRetVariantScalar(getRefBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): WeakRef? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): WeakRef? =
            if (handle.address() == 0L) null else WeakRef(handle)

        private const val GET_REF_HASH = 1214101251L
        private val getRefBind by lazy {
            ObjectCalls.getMethodBind("WeakRef", "get_ref", GET_REF_HASH)
        }
    }
}
