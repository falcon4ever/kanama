package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Overrides the location sounds are heard from.
 *
 * Generated from Godot docs: AudioListener2D
 */
class AudioListener2D(handle: MemorySegment) : Node2D(handle) {
    fun makeCurrent() {
        ObjectCalls.ptrcallNoArgs(makeCurrentBind, handle)
    }

    fun clearCurrent() {
        ObjectCalls.ptrcallNoArgs(clearCurrentBind, handle)
    }

    fun isCurrent(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCurrentBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioListener2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioListener2D? =
            if (handle.address() == 0L) null else AudioListener2D(handle)

        private const val MAKE_CURRENT_HASH = 3218959716L
        private val makeCurrentBind by lazy {
            ObjectCalls.getMethodBind("AudioListener2D", "make_current", MAKE_CURRENT_HASH)
        }

        private const val CLEAR_CURRENT_HASH = 3218959716L
        private val clearCurrentBind by lazy {
            ObjectCalls.getMethodBind("AudioListener2D", "clear_current", CLEAR_CURRENT_HASH)
        }

        private const val IS_CURRENT_HASH = 36873697L
        private val isCurrentBind by lazy {
            ObjectCalls.getMethodBind("AudioListener2D", "is_current", IS_CURRENT_HASH)
        }
    }
}
