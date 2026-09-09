package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: GDScript
 */
class GDScript(handle: MemorySegment) : Script(handle) {
    fun new(vararg extraArgs: Any?): Any? {
        checkOpen()
        return ObjectCalls.callWithVariantArgs(newBind, handle, listOf(*extraArgs))
    }

    companion object {
        fun fromHandle(handle: MemorySegment): GDScript? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GDScript? =
            if (handle.address() == 0L) null else GDScript(handle)

        private const val NEW_HASH = 1545262638L
        private val newBind by lazy {
            ObjectCalls.getMethodBind("GDScript", "new", NEW_HASH)
        }
    }
}
