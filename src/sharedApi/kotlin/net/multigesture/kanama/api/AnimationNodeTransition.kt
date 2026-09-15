package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * A transition within an `AnimationTree` connecting two `AnimationNode`s.
 *
 * Generated from Godot docs: AnimationNodeTransition
 */
class AnimationNodeTransition(handle: GodotHandle) : AnimationNodeSync(handle) {
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

    /**
     * The number of enabled input ports for this animation node.
     *
     * Generated from Godot docs: AnimationNodeTransition.set_input_count
     */
    fun setInputCount(inputCount: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setInputCountBind, segment, inputCount)
    }

    /**
     * Enables or disables auto-advance for the given `input` index. If enabled, state changes to the
     * next input after playing the animation once. If enabled for the last input state, it loops to
     * the first.
     *
     * Generated from Godot docs: AnimationNodeTransition.set_input_as_auto_advance
     */
    fun setInputAsAutoAdvance(input: Int, enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndBoolArgs(setInputAsAutoAdvanceBind, segment, input, enable)
    }

    /**
     * Returns `true` if auto-advance is enabled for the given `input` index.
     *
     * Generated from Godot docs: AnimationNodeTransition.is_input_set_as_auto_advance
     */
    fun isInputSetAsAutoAdvance(input: Int): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetBool(isInputSetAsAutoAdvanceBind, segment, input)
    }

    /**
     * If `true`, breaks the loop at the end of the loop cycle for transition, even if the animation is
     * looping.
     *
     * Generated from Godot docs: AnimationNodeTransition.set_input_break_loop_at_end
     */
    fun setInputBreakLoopAtEnd(input: Int, enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndBoolArgs(setInputBreakLoopAtEndBind, segment, input, enable)
    }

    /**
     * Returns whether the animation breaks the loop at the end of the loop cycle for transition.
     *
     * Generated from Godot docs: AnimationNodeTransition.is_input_loop_broken_at_end
     */
    fun isInputLoopBrokenAtEnd(input: Int): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetBool(isInputLoopBrokenAtEndBind, segment, input)
    }

    /**
     * If `true`, the destination animation is restarted when the animation transitions.
     *
     * Generated from Godot docs: AnimationNodeTransition.set_input_reset
     */
    fun setInputReset(input: Int, enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndBoolArgs(setInputResetBind, segment, input, enable)
    }

    /**
     * Returns whether the animation restarts when the animation transitions from the other animation.
     *
     * Generated from Godot docs: AnimationNodeTransition.is_input_reset
     */
    fun isInputReset(input: Int): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetBool(isInputResetBind, segment, input)
    }

    /**
     * Cross-fading time (in seconds) between each animation connected to the inputs. Note:
     * `AnimationNodeTransition` transitions the current state immediately after the start of the
     * fading. The precise remaining time can only be inferred from the main animation. When
     * `AnimationNodeOutput` is considered as the most upstream, so the `xfade_time` is not scaled
     * depending on the downstream delta. See also `AnimationNodeOneShot.fadeout_time`.
     *
     * Generated from Godot docs: AnimationNodeTransition.set_xfade_time
     */
    fun setXfadeTime(time: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setXfadeTimeBind, segment, time)
    }

    /**
     * Cross-fading time (in seconds) between each animation connected to the inputs. Note:
     * `AnimationNodeTransition` transitions the current state immediately after the start of the
     * fading. The precise remaining time can only be inferred from the main animation. When
     * `AnimationNodeOutput` is considered as the most upstream, so the `xfade_time` is not scaled
     * depending on the downstream delta. See also `AnimationNodeOneShot.fadeout_time`.
     *
     * Generated from Godot docs: AnimationNodeTransition.get_xfade_time
     */
    fun getXfadeTime(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getXfadeTimeBind, segment)
    }

    /**
     * Determines how cross-fading between animations is eased. If empty, the transition will be
     * linear. Should be a unit `Curve`.
     *
     * Generated from Godot docs: AnimationNodeTransition.set_xfade_curve
     */
    fun setXfadeCurve(curve: Curve?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setXfadeCurveBind, segment, listOf(curve?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Determines how cross-fading between animations is eased. If empty, the transition will be
     * linear. Should be a unit `Curve`.
     *
     * Generated from Godot docs: AnimationNodeTransition.get_xfade_curve
     */
    fun getXfadeCurve(): Curve? {
        checkOpen()
        return Curve.wrap(ObjectCalls.ptrcallNoArgsRetObject(getXfadeCurveBind, segment))
    }

    /**
     * If `true`, allows transition to the self state. When the reset option is enabled in input, the
     * animation is restarted. If `false`, nothing happens on the transition to the self state.
     *
     * Generated from Godot docs: AnimationNodeTransition.set_allow_transition_to_self
     */
    fun setAllowTransitionToSelf(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setAllowTransitionToSelfBind, segment, enable)
    }

    /**
     * If `true`, allows transition to the self state. When the reset option is enabled in input, the
     * animation is restarted. If `false`, nothing happens on the transition to the self state.
     *
     * Generated from Godot docs: AnimationNodeTransition.is_allow_transition_to_self
     */
    fun isAllowTransitionToSelf(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isAllowTransitionToSelfBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AnimationNodeTransition? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AnimationNodeTransition? =
            if (handle.address() == 0L) null else AnimationNodeTransition(GodotHandle(handle))

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
