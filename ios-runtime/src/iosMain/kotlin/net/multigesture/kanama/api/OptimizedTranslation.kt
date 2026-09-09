package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: OptimizedTranslation
 */
class OptimizedTranslation(handle: MemorySegment) : Translation(handle) {
    fun generate(from: Translation?): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectArgRetBool(generateBind, handle, from?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): OptimizedTranslation? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OptimizedTranslation? =
            if (handle.address() == 0L) null else OptimizedTranslation(handle)

        private const val GENERATE_HASH = 2141509306L
        private val generateBind by lazy {
            ObjectCalls.getMethodBind("OptimizedTranslation", "generate", GENERATE_HASH)
        }
    }
}
