package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.AABB

/**
 * A box-shaped region of 3D space that detects whether it is visible on screen.
 *
 * Generated from Godot docs: VisibleOnScreenNotifier3D
 */
open class VisibleOnScreenNotifier3D(handle: GodotHandle) : VisualInstance3D(handle) {
    /**
     * The `VisibleOnScreenNotifier3D`'s bounding box.
     *
     * Generated from Godot docs: VisibleOnScreenNotifier3D.set_aabb
     */
    fun setAabb(rect: AABB) {
        ObjectCalls.ptrcallWithAABBArg(Binds.setAabbBind, segment, rect)
    }

    /**
     * Returns `true` if the bounding box is on the screen. Note: It takes one frame for the
     * `VisibleOnScreenNotifier3D`'s visibility to be assessed once added to the scene tree, so this
     * method will always return `false` right after it is instantiated.
     *
     * Generated from Godot docs: VisibleOnScreenNotifier3D.is_on_screen
     */
    fun isOnScreen(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isOnScreenBind, segment)
    }

    /** Signal `screen_entered()`; see [TypedSignal]. */
    val screenEntered: Signal0
        @JvmName("screenEnteredTypedSignal")
        get() = Signal0(this, "screen_entered")

    /** Signal `screen_exited()`; see [TypedSignal]. */
    val screenExited: Signal0
        @JvmName("screenExitedTypedSignal")
        get() = Signal0(this, "screen_exited")

    object Signals {
        const val screenEntered: String = "screen_entered"
        const val screenExited: String = "screen_exited"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisibleOnScreenNotifier3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisibleOnScreenNotifier3D? =
            if (handle.address() == 0L) null else VisibleOnScreenNotifier3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_AABB_HASH = 259215842L
        @JvmField
        val setAabbBind =
            ObjectCalls.getMethodBind("VisibleOnScreenNotifier3D", "set_aabb", SET_AABB_HASH)

        private const val IS_ON_SCREEN_HASH = 36873697L
        @JvmField
        val isOnScreenBind =
            ObjectCalls.getMethodBind("VisibleOnScreenNotifier3D", "is_on_screen", IS_ON_SCREEN_HASH)
    }
}
