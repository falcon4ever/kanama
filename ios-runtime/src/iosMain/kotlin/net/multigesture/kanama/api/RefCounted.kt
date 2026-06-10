package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: RefCounted
 */
open class RefCounted(handle: MemorySegment) : GodotObject(handle) {
    fun unreference(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(unreferenceBind, handle)
    }

    fun getReferenceCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getReferenceCountBind, handle)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): RefCounted? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RefCounted? =
            if (handle.address() == 0L) null else RefCounted(handle)

        private const val UNREFERENCE_HASH = 2240911060L
        private val unreferenceBind by lazy {
            ObjectCalls.getMethodBind("RefCounted", "unreference", UNREFERENCE_HASH)
        }

        private const val GET_REFERENCE_COUNT_HASH = 3905245786L
        private val getReferenceCountBind by lazy {
            ObjectCalls.getMethodBind("RefCounted", "get_reference_count", GET_REFERENCE_COUNT_HASH)
        }
    }
}
