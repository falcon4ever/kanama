package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * A transition within an `AnimationNodeStateMachine` connecting two `AnimationRootNode`s.
 *
 * Generated from Godot docs: AnimationNodeStateMachineTransition
 */
class AnimationNodeStateMachineTransition(handle: MemorySegment) : Resource(handle) {
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

    var breakLoopAtEnd: Boolean
        @JvmName("breakLoopAtEndProperty")
        get() = isLoopBrokenAtEnd()
        @JvmName("setBreakLoopAtEndProperty")
        set(value) = setBreakLoopAtEnd(value)

    var reset: Boolean
        @JvmName("resetProperty")
        get() = isReset()
        @JvmName("setResetProperty")
        set(value) = setReset(value)

    var priority: Int
        @JvmName("priorityProperty")
        get() = getPriority()
        @JvmName("setPriorityProperty")
        set(value) = setPriority(value)

    var switchMode: Long
        @JvmName("switchModeProperty")
        get() = getSwitchMode()
        @JvmName("setSwitchModeProperty")
        set(value) = setSwitchMode(value)

    var advanceMode: Long
        @JvmName("advanceModeProperty")
        get() = getAdvanceMode()
        @JvmName("setAdvanceModeProperty")
        set(value) = setAdvanceMode(value)

    var advanceCondition: String
        @JvmName("advanceConditionProperty")
        get() = getAdvanceCondition()
        @JvmName("setAdvanceConditionProperty")
        set(value) = setAdvanceCondition(value)

    var advanceExpression: String
        @JvmName("advanceExpressionProperty")
        get() = getAdvanceExpression()
        @JvmName("setAdvanceExpressionProperty")
        set(value) = setAdvanceExpression(value)

    /**
     * The transition type.
     *
     * Generated from Godot docs: AnimationNodeStateMachineTransition.set_switch_mode
     */
    fun setSwitchMode(mode: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setSwitchModeBind, handle, mode)
    }

    /**
     * The transition type.
     *
     * Generated from Godot docs: AnimationNodeStateMachineTransition.get_switch_mode
     */
    fun getSwitchMode(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getSwitchModeBind, handle)
    }

    /**
     * Determines whether the transition should be disabled, enabled when using
     * `AnimationNodeStateMachinePlayback.travel`, or traversed automatically if the
     * `advance_condition` and `advance_expression` checks are `true` (if assigned).
     *
     * Generated from Godot docs: AnimationNodeStateMachineTransition.set_advance_mode
     */
    fun setAdvanceMode(mode: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setAdvanceModeBind, handle, mode)
    }

    /**
     * Determines whether the transition should be disabled, enabled when using
     * `AnimationNodeStateMachinePlayback.travel`, or traversed automatically if the
     * `advance_condition` and `advance_expression` checks are `true` (if assigned).
     *
     * Generated from Godot docs: AnimationNodeStateMachineTransition.get_advance_mode
     */
    fun getAdvanceMode(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getAdvanceModeBind, handle)
    }

    /**
     * Turn on auto advance when this condition is set. The provided name will become a boolean
     * parameter on the `AnimationTree` that can be controlled from code (see Using AnimationTree
     * ($DOCS_URL/tutorials/animation/animation_tree.html#controlling-from-code)). For example, if
     * `AnimationTree.tree_root` is an `AnimationNodeStateMachine` and `advance_condition` is set to
     * `"idle"`:
     *
     * Generated from Godot docs: AnimationNodeStateMachineTransition.set_advance_condition
     */
    fun setAdvanceCondition(name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringNameArg(setAdvanceConditionBind, handle, name)
    }

    /**
     * Turn on auto advance when this condition is set. The provided name will become a boolean
     * parameter on the `AnimationTree` that can be controlled from code (see Using AnimationTree
     * ($DOCS_URL/tutorials/animation/animation_tree.html#controlling-from-code)). For example, if
     * `AnimationTree.tree_root` is an `AnimationNodeStateMachine` and `advance_condition` is set to
     * `"idle"`:
     *
     * Generated from Godot docs: AnimationNodeStateMachineTransition.get_advance_condition
     */
    fun getAdvanceCondition(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetStringName(getAdvanceConditionBind, handle)
    }

    /**
     * The time to cross-fade between this state and the next. Note: `AnimationNodeStateMachine`
     * transitions the current state immediately after the start of the fading. The precise remaining
     * time can only be inferred from the main animation. When `AnimationNodeOutput` is considered as
     * the most upstream, so the `xfade_time` is not scaled depending on the downstream delta. See also
     * `AnimationNodeOneShot.fadeout_time`.
     *
     * Generated from Godot docs: AnimationNodeStateMachineTransition.set_xfade_time
     */
    fun setXfadeTime(secs: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setXfadeTimeBind, handle, secs)
    }

    /**
     * The time to cross-fade between this state and the next. Note: `AnimationNodeStateMachine`
     * transitions the current state immediately after the start of the fading. The precise remaining
     * time can only be inferred from the main animation. When `AnimationNodeOutput` is considered as
     * the most upstream, so the `xfade_time` is not scaled depending on the downstream delta. See also
     * `AnimationNodeOneShot.fadeout_time`.
     *
     * Generated from Godot docs: AnimationNodeStateMachineTransition.get_xfade_time
     */
    fun getXfadeTime(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getXfadeTimeBind, handle)
    }

    /**
     * Ease curve for better control over cross-fade between this state and the next. Should be a unit
     * `Curve`.
     *
     * Generated from Godot docs: AnimationNodeStateMachineTransition.set_xfade_curve
     */
    fun setXfadeCurve(curve: Curve?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setXfadeCurveBind, handle, listOf(curve?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Ease curve for better control over cross-fade between this state and the next. Should be a unit
     * `Curve`.
     *
     * Generated from Godot docs: AnimationNodeStateMachineTransition.get_xfade_curve
     */
    fun getXfadeCurve(): Curve? {
        checkOpen()
        return Curve.wrap(ObjectCalls.ptrcallNoArgsRetObject(getXfadeCurveBind, handle))
    }

    /**
     * If `true`, breaks the loop at the end of the loop cycle for transition, even if the animation is
     * looping.
     *
     * Generated from Godot docs: AnimationNodeStateMachineTransition.set_break_loop_at_end
     */
    fun setBreakLoopAtEnd(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setBreakLoopAtEndBind, handle, enable)
    }

    /**
     * If `true`, breaks the loop at the end of the loop cycle for transition, even if the animation is
     * looping.
     *
     * Generated from Godot docs: AnimationNodeStateMachineTransition.is_loop_broken_at_end
     */
    fun isLoopBrokenAtEnd(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isLoopBrokenAtEndBind, handle)
    }

    /**
     * If `true`, the destination animation is played back from the beginning when switched.
     *
     * Generated from Godot docs: AnimationNodeStateMachineTransition.set_reset
     */
    fun setReset(reset: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setResetBind, handle, reset)
    }

    /**
     * If `true`, the destination animation is played back from the beginning when switched.
     *
     * Generated from Godot docs: AnimationNodeStateMachineTransition.is_reset
     */
    fun isReset(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isResetBind, handle)
    }

    /**
     * Lower priority transitions are preferred when travelling through the tree via
     * `AnimationNodeStateMachinePlayback.travel` or `advance_mode` is set to `ADVANCE_MODE_AUTO`.
     *
     * Generated from Godot docs: AnimationNodeStateMachineTransition.set_priority
     */
    fun setPriority(priority: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setPriorityBind, handle, priority)
    }

    /**
     * Lower priority transitions are preferred when travelling through the tree via
     * `AnimationNodeStateMachinePlayback.travel` or `advance_mode` is set to `ADVANCE_MODE_AUTO`.
     *
     * Generated from Godot docs: AnimationNodeStateMachineTransition.get_priority
     */
    fun getPriority(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getPriorityBind, handle)
    }

    /**
     * Use an expression as a condition for state machine transitions. It is possible to create complex
     * animation advance conditions for switching between states and gives much greater flexibility for
     * creating complex state machines by directly interfacing with the script code.
     *
     * Generated from Godot docs: AnimationNodeStateMachineTransition.set_advance_expression
     */
    fun setAdvanceExpression(text: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setAdvanceExpressionBind, handle, text)
    }

    /**
     * Use an expression as a condition for state machine transitions. It is possible to create complex
     * animation advance conditions for switching between states and gives much greater flexibility for
     * creating complex state machines by directly interfacing with the script code.
     *
     * Generated from Godot docs: AnimationNodeStateMachineTransition.get_advance_expression
     */
    fun getAdvanceExpression(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getAdvanceExpressionBind, handle)
    }

    object Signals {
        const val advanceConditionChanged: String = "advance_condition_changed"
    }

    companion object {
        const val SWITCH_MODE_IMMEDIATE: Long = 0L
        const val SWITCH_MODE_SYNC: Long = 1L
        const val SWITCH_MODE_AT_END: Long = 2L
        const val ADVANCE_MODE_DISABLED: Long = 0L
        const val ADVANCE_MODE_ENABLED: Long = 1L
        const val ADVANCE_MODE_AUTO: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): AnimationNodeStateMachineTransition? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AnimationNodeStateMachineTransition? =
            if (handle.address() == 0L) null else AnimationNodeStateMachineTransition(handle)

        private const val SET_SWITCH_MODE_HASH = 2074906633L
        private val setSwitchModeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachineTransition", "set_switch_mode", SET_SWITCH_MODE_HASH)
        }

        private const val GET_SWITCH_MODE_HASH = 2138562085L
        private val getSwitchModeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachineTransition", "get_switch_mode", GET_SWITCH_MODE_HASH)
        }

        private const val SET_ADVANCE_MODE_HASH = 1210869868L
        private val setAdvanceModeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachineTransition", "set_advance_mode", SET_ADVANCE_MODE_HASH)
        }

        private const val GET_ADVANCE_MODE_HASH = 61101689L
        private val getAdvanceModeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachineTransition", "get_advance_mode", GET_ADVANCE_MODE_HASH)
        }

        private const val SET_ADVANCE_CONDITION_HASH = 3304788590L
        private val setAdvanceConditionBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachineTransition", "set_advance_condition", SET_ADVANCE_CONDITION_HASH)
        }

        private const val GET_ADVANCE_CONDITION_HASH = 2002593661L
        private val getAdvanceConditionBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachineTransition", "get_advance_condition", GET_ADVANCE_CONDITION_HASH)
        }

        private const val SET_XFADE_TIME_HASH = 373806689L
        private val setXfadeTimeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachineTransition", "set_xfade_time", SET_XFADE_TIME_HASH)
        }

        private const val GET_XFADE_TIME_HASH = 1740695150L
        private val getXfadeTimeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachineTransition", "get_xfade_time", GET_XFADE_TIME_HASH)
        }

        private const val SET_XFADE_CURVE_HASH = 270443179L
        private val setXfadeCurveBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachineTransition", "set_xfade_curve", SET_XFADE_CURVE_HASH)
        }

        private const val GET_XFADE_CURVE_HASH = 2460114913L
        private val getXfadeCurveBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachineTransition", "get_xfade_curve", GET_XFADE_CURVE_HASH)
        }

        private const val SET_BREAK_LOOP_AT_END_HASH = 2586408642L
        private val setBreakLoopAtEndBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachineTransition", "set_break_loop_at_end", SET_BREAK_LOOP_AT_END_HASH)
        }

        private const val IS_LOOP_BROKEN_AT_END_HASH = 36873697L
        private val isLoopBrokenAtEndBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachineTransition", "is_loop_broken_at_end", IS_LOOP_BROKEN_AT_END_HASH)
        }

        private const val SET_RESET_HASH = 2586408642L
        private val setResetBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachineTransition", "set_reset", SET_RESET_HASH)
        }

        private const val IS_RESET_HASH = 36873697L
        private val isResetBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachineTransition", "is_reset", IS_RESET_HASH)
        }

        private const val SET_PRIORITY_HASH = 1286410249L
        private val setPriorityBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachineTransition", "set_priority", SET_PRIORITY_HASH)
        }

        private const val GET_PRIORITY_HASH = 3905245786L
        private val getPriorityBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachineTransition", "get_priority", GET_PRIORITY_HASH)
        }

        private const val SET_ADVANCE_EXPRESSION_HASH = 83702148L
        private val setAdvanceExpressionBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachineTransition", "set_advance_expression", SET_ADVANCE_EXPRESSION_HASH)
        }

        private const val GET_ADVANCE_EXPRESSION_HASH = 201670096L
        private val getAdvanceExpressionBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachineTransition", "get_advance_expression", GET_ADVANCE_EXPRESSION_HASH)
        }
    }
}
