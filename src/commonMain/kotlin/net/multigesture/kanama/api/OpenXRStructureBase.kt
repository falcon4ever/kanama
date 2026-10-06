package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: OpenXRStructureBase
 */
open class OpenXRStructureBase(handle: GodotHandle) : RefCounted(handle) {
    var next: OpenXRStructureBase?
        @JvmName("nextProperty")
        get() = getNext()
        @JvmName("setNextProperty")
        set(value) = setNext(value)

    fun getStructureType(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(Binds.getStructureTypeBind, segment)
    }

    fun setNext(entity: OpenXRStructureBase?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setNextBind, segment, listOf(entity?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun getNext(): OpenXRStructureBase? {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetObject(Binds.getNextBind, segment)
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
