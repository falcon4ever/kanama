package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: OpenXRFutureResult
 */
class OpenXRFutureResult(handle: GodotHandle) : RefCounted(handle) {
    fun getStatus(): OpenXRFutureResult.ResultStatus {
        checkOpen()
        return OpenXRFutureResult.ResultStatus(ObjectCalls.ptrcallNoArgsRetLong(Binds.getStatusBind, segment))
    }

    fun getFuture(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(Binds.getFutureBind, segment)
    }

    fun cancelFuture() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.cancelFutureBind, segment)
    }

    fun setResultValue(resultValue: Any?) {
        checkOpen()
        ObjectCalls.ptrcallWithVariantArg(Binds.setResultValueBind, segment, resultValue)
    }

    fun getResultValue(): Any? {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVariantScalar(Binds.getResultValueBind, segment)
    }

    /** Signal `completed(result: OpenXRFutureResult)`; see [TypedSignal]. */
    val completed: Signal1<Any?>
        @JvmName("completedTypedSignal")
        get() = Signal1(this, "completed", SignalArgType.VARIANT)

    object Signals {
        const val completed: String = "completed"
    }

    @JvmInline
    value class ResultStatus(override val value: Long) : GodotEnumValue {
        companion object {
            val RUNNING: ResultStatus get() = ResultStatus(0L)
            val FINISHED: ResultStatus get() = ResultStatus(1L)
            val CANCELLED: ResultStatus get() = ResultStatus(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRFutureResult? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRFutureResult? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRFutureResult(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRFutureResult? =
            if (handle.address() == 0L) null else OpenXRFutureResult(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_STATUS_HASH = 2023607463L
        @JvmField
        val getStatusBind =
            ObjectCalls.getMethodBind("OpenXRFutureResult", "get_status", GET_STATUS_HASH)

        private const val GET_FUTURE_HASH = 3905245786L
        @JvmField
        val getFutureBind =
            ObjectCalls.getMethodBind("OpenXRFutureResult", "get_future", GET_FUTURE_HASH)

        private const val CANCEL_FUTURE_HASH = 3218959716L
        @JvmField
        val cancelFutureBind =
            ObjectCalls.getMethodBind("OpenXRFutureResult", "cancel_future", CANCEL_FUTURE_HASH)

        private const val SET_RESULT_VALUE_HASH = 1114965689L
        @JvmField
        val setResultValueBind =
            ObjectCalls.getMethodBind("OpenXRFutureResult", "set_result_value", SET_RESULT_VALUE_HASH)

        private const val GET_RESULT_VALUE_HASH = 1214101251L
        @JvmField
        val getResultValueBind =
            ObjectCalls.getMethodBind("OpenXRFutureResult", "get_result_value", GET_RESULT_VALUE_HASH)
    }
}
