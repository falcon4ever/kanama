package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Abstract base class for scrollbars.
 *
 * Generated from Godot docs: ScrollBar
 */
open class ScrollBar(handle: GodotHandle) : Range(handle) {
    var customStep: Double
        @JvmName("customStepProperty")
        get() = getCustomStep()
        @JvmName("setCustomStepProperty")
        set(value) = setCustomStep(value)

    /**
     * Overrides the step used when clicking increment and decrement buttons or when using arrow keys
     * when the `ScrollBar` is focused.
     *
     * Generated from Godot docs: ScrollBar.set_custom_step
     */
    fun setCustomStep(step: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setCustomStepBind, segment, step)
    }

    /**
     * Overrides the step used when clicking increment and decrement buttons or when using arrow keys
     * when the `ScrollBar` is focused.
     *
     * Generated from Godot docs: ScrollBar.get_custom_step
     */
    fun getCustomStep(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getCustomStepBind, segment)
    }

    object Signals {
        const val scrolling: String = "scrolling"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ScrollBar? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): ScrollBar? =
            if (handle.address() == 0L) null else ScrollBar(GodotHandle(handle))

        private const val SET_CUSTOM_STEP_HASH = 373806689L
        private val setCustomStepBind by lazy {
            ObjectCalls.getMethodBind("ScrollBar", "set_custom_step", SET_CUSTOM_STEP_HASH)
        }

        private const val GET_CUSTOM_STEP_HASH = 1740695150L
        private val getCustomStepBind by lazy {
            ObjectCalls.getMethodBind("ScrollBar", "get_custom_step", GET_CUSTOM_STEP_HASH)
        }
    }
}
