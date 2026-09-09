package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
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

    fun setSwitchMode(mode: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setSwitchModeBind, handle, mode)
    }

    fun getSwitchMode(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getSwitchModeBind, handle)
    }

    fun setAdvanceMode(mode: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setAdvanceModeBind, handle, mode)
    }

    fun getAdvanceMode(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getAdvanceModeBind, handle)
    }

    fun setAdvanceCondition(name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringNameArg(setAdvanceConditionBind, handle, name)
    }

    fun getAdvanceCondition(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetStringName(getAdvanceConditionBind, handle)
    }

    fun setXfadeTime(secs: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setXfadeTimeBind, handle, secs)
    }

    fun getXfadeTime(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getXfadeTimeBind, handle)
    }

    fun setXfadeCurve(curve: Curve?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setXfadeCurveBind, handle, listOf(curve?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getXfadeCurve(): Curve? {
        checkOpen()
        return Curve.wrap(ObjectCalls.ptrcallNoArgsRetObject(getXfadeCurveBind, handle))
    }

    fun setBreakLoopAtEnd(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setBreakLoopAtEndBind, handle, enable)
    }

    fun isLoopBrokenAtEnd(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isLoopBrokenAtEndBind, handle)
    }

    fun setReset(reset: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setResetBind, handle, reset)
    }

    fun isReset(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isResetBind, handle)
    }

    fun setPriority(priority: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setPriorityBind, handle, priority)
    }

    fun getPriority(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getPriorityBind, handle)
    }

    fun setAdvanceExpression(text: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setAdvanceExpressionBind, handle, text)
    }

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
