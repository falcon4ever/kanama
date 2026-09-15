package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Holds an `Object`. If the object is `RefCounted`, it doesn't update the reference count.
 *
 * Generated from Godot docs: WeakRef
 */
class WeakRef(handle: GodotHandle) : RefCounted(handle) {
    /**
     * Returns the `Object` this weakref is referring to. Returns `null` if that object no longer
     * exists.
     *
     * Generated from Godot docs: WeakRef.get_ref
     */
    fun getRef(): Any? {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVariantScalar(getRefBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): WeakRef? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): WeakRef? =
            if (handle.address() == 0L) null else WeakRef(GodotHandle(handle))

        private const val GET_REF_HASH = 1214101251L
        private val getRefBind by lazy {
            ObjectCalls.getMethodBind("WeakRef", "get_ref", GET_REF_HASH)
        }
    }
}
