package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Abstract base class for scrollbars.
 *
 * Generated from Godot docs: ScrollBar
 */
open class ScrollBar(handle: MemorySegment) : Range(handle) {
    var customStep: Double
        @JvmName("customStepProperty")
        get() = getCustomStep()
        @JvmName("setCustomStepProperty")
        set(value) = setCustomStep(value)

    fun setCustomStep(step: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setCustomStepBind, handle, step)
    }

    fun getCustomStep(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getCustomStepBind, handle)
    }

    object Signals {
        const val scrolling: String = "scrolling"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ScrollBar? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ScrollBar? =
            if (handle.address() == 0L) null else ScrollBar(handle)

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
