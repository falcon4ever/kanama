package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: GDScript
 */
class GDScript(handle: GodotHandle) : Script(handle) {
    fun new(vararg extraArgs: Any?): Any? {
        checkOpen()
        return ObjectCalls.callWithVariantArgs(Binds.newBind, segment, listOf(*extraArgs))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): GDScript? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): GDScript? =
            if (handle.address() == 0L) null else RefCounted.owned(GDScript(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): GDScript? =
            if (handle.address() == 0L) null else GDScript(GodotHandle(handle))
    }

    private object Binds {
        private const val NEW_HASH = 1545262638L
        @JvmField
        val newBind =
            ObjectCalls.getMethodBind("GDScript", "new", NEW_HASH)
    }
}
