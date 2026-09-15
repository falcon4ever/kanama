package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

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
        return ObjectCalls.ptrcallNoArgsRetLong(getStructureTypeBind, segment)
    }

    fun setNext(entity: OpenXRStructureBase?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setNextBind, segment, listOf(entity?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun getNext(): OpenXRStructureBase? {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetObject(getNextBind, segment)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return OpenXRStructureBase.wrap(ret)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRStructureBase? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRStructureBase? =
            if (handle.address() == 0L) null else OpenXRStructureBase(GodotHandle(handle))

        private const val GET_STRUCTURE_TYPE_HASH = 2455072627L
        private val getStructureTypeBind by lazy {
            ObjectCalls.getMethodBind("OpenXRStructureBase", "get_structure_type", GET_STRUCTURE_TYPE_HASH)
        }

        private const val SET_NEXT_HASH = 334698771L
        private val setNextBind by lazy {
            ObjectCalls.getMethodBind("OpenXRStructureBase", "set_next", SET_NEXT_HASH)
        }

        private const val GET_NEXT_HASH = 2798796760L
        private val getNextBind by lazy {
            ObjectCalls.getMethodBind("OpenXRStructureBase", "get_next", GET_NEXT_HASH)
        }
    }
}
