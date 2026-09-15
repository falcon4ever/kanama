package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: GDScript
 */
class GDScript(handle: GodotHandle) : Script(handle) {
    fun new(vararg extraArgs: Any?): Any? {
        checkOpen()
        return ObjectCalls.callWithVariantArgs(newBind, segment, listOf(*extraArgs))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): GDScript? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): GDScript? =
            if (handle.address() == 0L) null else GDScript(GodotHandle(handle))

        private const val NEW_HASH = 1545262638L
        private val newBind by lazy {
            ObjectCalls.getMethodBind("GDScript", "new", NEW_HASH)
        }
    }
}
