package net.multigesture.kanama.api

import kotlin.jvm.JvmField
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
        return ObjectCalls.ptrcallNoArgsRetVariantScalar(Binds.getRefBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): WeakRef? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): WeakRef? =
            if (handle.address() == 0L) null else RefCounted.owned(WeakRef(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): WeakRef? =
            if (handle.address() == 0L) null else WeakRef(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_REF_HASH = 1214101251L
        @JvmField
        val getRefBind =
            ObjectCalls.getMethodBind("WeakRef", "get_ref", GET_REF_HASH)
    }
}
