package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.AABB

/**
 * A box-shaped region of 3D space that detects whether it is visible on screen.
 *
 * Generated from Godot docs: VisibleOnScreenNotifier3D
 */
open class VisibleOnScreenNotifier3D(handle: MemorySegment) : VisualInstance3D(handle) {
    fun setAabb(rect: AABB) {
        ObjectCalls.ptrcallWithAABBArg(setAabbBind, handle, rect)
    }

    fun isOnScreen(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isOnScreenBind, handle)
    }

    object Signals {
        const val screenEntered: String = "screen_entered"
        const val screenExited: String = "screen_exited"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisibleOnScreenNotifier3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisibleOnScreenNotifier3D? =
            if (handle.address() == 0L) null else VisibleOnScreenNotifier3D(handle)

        private const val SET_AABB_HASH = 259215842L
        private val setAabbBind by lazy {
            ObjectCalls.getMethodBind("VisibleOnScreenNotifier3D", "set_aabb", SET_AABB_HASH)
        }

        private const val IS_ON_SCREEN_HASH = 36873697L
        private val isOnScreenBind by lazy {
            ObjectCalls.getMethodBind("VisibleOnScreenNotifier3D", "is_on_screen", IS_ON_SCREEN_HASH)
        }
    }
}
