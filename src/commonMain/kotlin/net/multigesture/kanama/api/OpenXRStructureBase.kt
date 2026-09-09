package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: OpenXRStructureBase
 */
open class OpenXRStructureBase(handle: MemorySegment) : RefCounted(handle) {
    var next: OpenXRStructureBase?
        @JvmName("nextProperty")
        get() = getNext()
        @JvmName("setNextProperty")
        set(value) = setNext(value)

    fun getStructureType(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getStructureTypeBind, handle)
    }

    fun setNext(entity: OpenXRStructureBase?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setNextBind, handle, listOf(entity?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getNext(): OpenXRStructureBase? {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetObject(getNextBind, handle)
        if (ret.address() == handle.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return OpenXRStructureBase.wrap(ret)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRStructureBase? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRStructureBase? =
            if (handle.address() == 0L) null else OpenXRStructureBase(handle)

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
