package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Object for storing OpenXR structure data.
 *
 * Generated from Godot docs: OpenXRStructureBase
 */
open class OpenXRStructureBase(handle: GodotHandle) : RefCounted(handle) {
    var next: OpenXRStructureBase?
        @JvmName("nextProperty")
        get() = getNext()
        @JvmName("setNextProperty")
        set(value) = setNext(value)

    /**
     * Returns the structure type (OpenXR `XrStructureType`) used for this structure.
     *
     * Generated from Godot docs: OpenXRStructureBase.get_structure_type
     */
    fun getStructureType(): Long {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetLong(Binds.getStructureTypeBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Setting another structure object here chains these structures together to extend the API
     * functionality. Consult the OpenXR documentation for which structures can be used with a given
     * API call.
     *
     * Generated from Godot docs: OpenXRStructureBase.set_next
     */
    fun setNext(entity: OpenXRStructureBase?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setNextBind, segment, listOf(entity?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(entity)
    }

    /**
     * Setting another structure object here chains these structures together to extend the API
     * functionality. Consult the OpenXR documentation for which structures can be used with a given
     * API call.
     *
     * Generated from Godot docs: OpenXRStructureBase.get_next
     */
    fun getNext(): OpenXRStructureBase? {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetObject(Binds.getNextBind, segment)
        ObjectCalls.keepReachable(this)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return OpenXRStructureBase.wrapOwned(ret)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRStructureBase? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRStructureBase? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRStructureBase(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRStructureBase? =
            if (handle.address() == 0L) null else OpenXRStructureBase(GodotHandle(handle))

        /** A new OpenXRStructureBase, GDScript's `OpenXRStructureBase.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): OpenXRStructureBase =
            RefCounted.owned(OpenXRStructureBase(GodotHandle(ObjectCalls.constructObject("OpenXRStructureBase"))))
    }

    private object Binds {
        private const val GET_STRUCTURE_TYPE_HASH = 2455072627L
        @JvmField
        val getStructureTypeBind =
            ObjectCalls.getMethodBind("OpenXRStructureBase", "get_structure_type", GET_STRUCTURE_TYPE_HASH)

        private const val SET_NEXT_HASH = 334698771L
        @JvmField
        val setNextBind =
            ObjectCalls.getMethodBind("OpenXRStructureBase", "set_next", SET_NEXT_HASH)

        private const val GET_NEXT_HASH = 2798796760L
        @JvmField
        val getNextBind =
            ObjectCalls.getMethodBind("OpenXRStructureBase", "get_next", GET_NEXT_HASH)
    }
}
