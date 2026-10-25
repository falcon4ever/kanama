package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Holds a reference to an `Object`'s instance ID.
 *
 * Generated from Godot docs: EncodedObjectAsID
 */
class EncodedObjectAsID(handle: GodotHandle) : RefCounted(handle) {
    var objectId: Long
        @JvmName("objectIdProperty")
        get() = getObjectId()
        @JvmName("setObjectIdProperty")
        set(value) = setObjectId(value)

    /**
     * The `Object` identifier stored in this `EncodedObjectAsID` instance. The object instance can be
     * retrieved with `@GlobalScope.instance_from_id`.
     *
     * Generated from Godot docs: EncodedObjectAsID.set_object_id
     */
    fun setObjectId(id: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setObjectIdBind, segment, id)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The `Object` identifier stored in this `EncodedObjectAsID` instance. The object instance can be
     * retrieved with `@GlobalScope.instance_from_id`.
     *
     * Generated from Godot docs: EncodedObjectAsID.get_object_id
     */
    fun getObjectId(): Long {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetLong(Binds.getObjectIdBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): EncodedObjectAsID? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): EncodedObjectAsID? =
            if (handle.address() == 0L) null else RefCounted.owned(EncodedObjectAsID(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): EncodedObjectAsID? =
            if (handle.address() == 0L) null else EncodedObjectAsID(GodotHandle(handle))

        /** A new EncodedObjectAsID, GDScript's `EncodedObjectAsID.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): EncodedObjectAsID =
            RefCounted.owned(EncodedObjectAsID(GodotHandle(ObjectCalls.constructObject("EncodedObjectAsID"))))
    }

    private object Binds {
        private const val SET_OBJECT_ID_HASH = 1286410249L
        @JvmField
        val setObjectIdBind =
            ObjectCalls.getMethodBind("EncodedObjectAsID", "set_object_id", SET_OBJECT_ID_HASH)

        private const val GET_OBJECT_ID_HASH = 3905245786L
        @JvmField
        val getObjectIdBind =
            ObjectCalls.getMethodBind("EncodedObjectAsID", "get_object_id", GET_OBJECT_ID_HASH)
    }
}
