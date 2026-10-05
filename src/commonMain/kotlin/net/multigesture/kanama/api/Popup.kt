package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Base class for contextual windows and panels with fixed position.
 *
 * Generated from Godot docs: Popup
 */
open class Popup(handle: GodotHandle) : Window(handle) {
    // No conservative instance methods emitted yet.

    /** Signal `popup_hide()`; see [TypedSignal]. */
    val popupHide: Signal0
        @JvmName("popupHideTypedSignal")
        get() = Signal0(this, "popup_hide")

    object Signals {
        const val popupHide: String = "popup_hide"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Popup? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): Popup? =
            if (handle.address() == 0L) null else Popup(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
