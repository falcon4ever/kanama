package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A transition within an `AnimationTree` connecting two `AnimationNode`s.
 *
 * Generated from Godot docs: AnimationNodeTransition
 */
class AnimationNodeTransition(handle: MemorySegment) : AnimationNodeSync(handle) {
    var xfadeTime: Double
        @JvmName("xfadeTimeProperty")
        get() = getXfadeTime()
        @JvmName("setXfadeTimeProperty")
        set(value) = setXfadeTime(value)

    var xfadeCurve: Curve?
        @JvmName("xfadeCurveProperty")
        get() = getXfadeCurve()
        @JvmName("setXfadeCurveProperty")
        set(value) = setXfadeCurve(value)

    var allowTransitionToSelf: Boolean
        @JvmName("allowTransitionToSelfProperty")
        get() = isAllowTransitionToSelf()
        @JvmName("setAllowTransitionToSelfProperty")
        set(value) = setAllowTransitionToSelf(value)

    fun setInputCount(inputCount: Int) {
        ObjectCalls.ptrcallWithIntArg(setInputCountBind, handle, inputCount)
    }

    fun setInputAsAutoAdvance(input: Int, enable: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setInputAsAutoAdvanceBind, handle, input, enable)
    }

    fun isInputSetAsAutoAdvance(input: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isInputSetAsAutoAdvanceBind, handle, input)
    }

    fun setInputBreakLoopAtEnd(input: Int, enable: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setInputBreakLoopAtEndBind, handle, input, enable)
    }

    fun isInputLoopBrokenAtEnd(input: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isInputLoopBrokenAtEndBind, handle, input)
    }

    fun setInputReset(input: Int, enable: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setInputResetBind, handle, input, enable)
    }

    fun isInputReset(input: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isInputResetBind, handle, input)
    }

    fun setXfadeTime(time: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setXfadeTimeBind, handle, time)
    }

    fun getXfadeTime(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getXfadeTimeBind, handle)
    }

    fun setXfadeCurve(curve: Curve?) {
        ObjectCalls.ptrcallWithObjectArgs(setXfadeCurveBind, handle, listOf(curve?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getXfadeCurve(): Curve? {
        return Curve.wrap(ObjectCalls.ptrcallNoArgsRetObject(getXfadeCurveBind, handle))
    }

    fun setAllowTransitionToSelf(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAllowTransitionToSelfBind, handle, enable)
    }

    fun isAllowTransitionToSelf(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAllowTransitionToSelfBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AnimationNodeTransition? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AnimationNodeTransition? =
            if (handle.address() == 0L) null else AnimationNodeTransition(handle)

        private const val SET_INPUT_COUNT_HASH = 1286410249L
        private val setInputCountBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeTransition", "set_input_count", SET_INPUT_COUNT_HASH)
        }

        private const val SET_INPUT_AS_AUTO_ADVANCE_HASH = 300928843L
        private val setInputAsAutoAdvanceBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeTransition", "set_input_as_auto_advance", SET_INPUT_AS_AUTO_ADVANCE_HASH)
        }

        private const val IS_INPUT_SET_AS_AUTO_ADVANCE_HASH = 1116898809L
        private val isInputSetAsAutoAdvanceBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeTransition", "is_input_set_as_auto_advance", IS_INPUT_SET_AS_AUTO_ADVANCE_HASH)
        }

        private const val SET_INPUT_BREAK_LOOP_AT_END_HASH = 300928843L
        private val setInputBreakLoopAtEndBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeTransition", "set_input_break_loop_at_end", SET_INPUT_BREAK_LOOP_AT_END_HASH)
        }

        private const val IS_INPUT_LOOP_BROKEN_AT_END_HASH = 1116898809L
        private val isInputLoopBrokenAtEndBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeTransition", "is_input_loop_broken_at_end", IS_INPUT_LOOP_BROKEN_AT_END_HASH)
        }

        private const val SET_INPUT_RESET_HASH = 300928843L
        private val setInputResetBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeTransition", "set_input_reset", SET_INPUT_RESET_HASH)
        }

        private const val IS_INPUT_RESET_HASH = 1116898809L
        private val isInputResetBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeTransition", "is_input_reset", IS_INPUT_RESET_HASH)
        }

        private const val SET_XFADE_TIME_HASH = 373806689L
        private val setXfadeTimeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeTransition", "set_xfade_time", SET_XFADE_TIME_HASH)
        }

        private const val GET_XFADE_TIME_HASH = 1740695150L
        private val getXfadeTimeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeTransition", "get_xfade_time", GET_XFADE_TIME_HASH)
        }

        private const val SET_XFADE_CURVE_HASH = 270443179L
        private val setXfadeCurveBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeTransition", "set_xfade_curve", SET_XFADE_CURVE_HASH)
        }

        private const val GET_XFADE_CURVE_HASH = 2460114913L
        private val getXfadeCurveBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeTransition", "get_xfade_curve", GET_XFADE_CURVE_HASH)
        }

        private const val SET_ALLOW_TRANSITION_TO_SELF_HASH = 2586408642L
        private val setAllowTransitionToSelfBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeTransition", "set_allow_transition_to_self", SET_ALLOW_TRANSITION_TO_SELF_HASH)
        }

        private const val IS_ALLOW_TRANSITION_TO_SELF_HASH = 36873697L
        private val isAllowTransitionToSelfBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeTransition", "is_allow_transition_to_self", IS_ALLOW_TRANSITION_TO_SELF_HASH)
        }
    }
}
