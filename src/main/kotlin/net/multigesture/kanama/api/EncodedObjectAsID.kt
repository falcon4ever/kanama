package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Holds a reference to an `Object`'s instance ID.
 *
 * Generated from Godot docs: EncodedObjectAsID
 */
class EncodedObjectAsID(handle: MemorySegment) : RefCounted(handle) {
    var objectId: Long
        @JvmName("objectIdProperty")
        get() = getObjectId()
        @JvmName("setObjectIdProperty")
        set(value) = setObjectId(value)

    fun setObjectId(id: Long) {
        ObjectCalls.ptrcallWithLongArg(setObjectIdBind, handle, id)
    }

    fun getObjectId(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getObjectIdBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): EncodedObjectAsID? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EncodedObjectAsID? =
            if (handle.address() == 0L) null else EncodedObjectAsID(handle)

        private const val SET_OBJECT_ID_HASH = 1286410249L
        private val setObjectIdBind by lazy {
            ObjectCalls.getMethodBind("EncodedObjectAsID", "set_object_id", SET_OBJECT_ID_HASH)
        }

        private const val GET_OBJECT_ID_HASH = 3905245786L
        private val getObjectIdBind by lazy {
            ObjectCalls.getMethodBind("EncodedObjectAsID", "get_object_id", GET_OBJECT_ID_HASH)
        }
    }
}
