package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Result object tracking the asynchronous result of an OpenXR Future object.
 *
 * Generated from Godot docs: OpenXRFutureResult
 */
class OpenXRFutureResult(handle: GodotHandle) : RefCounted(handle) {
    /**
     * Returns the status of this result.
     *
     * Generated from Godot docs: OpenXRFutureResult.get_status
     */
    fun getStatus(): OpenXRFutureResult.ResultStatus {
        checkOpen()
        return OpenXRFutureResult.ResultStatus(ObjectCalls.ptrcallNoArgsRetLong(Binds.getStatusBind, segment))
    }

    /**
     * Return the `XrFutureEXT` value this result relates to.
     *
     * Generated from Godot docs: OpenXRFutureResult.get_future
     */
    fun getFuture(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(Binds.getFutureBind, segment)
    }

    /**
     * Cancel this future, this will interrupt and stop the asynchronous function.
     *
     * Generated from Godot docs: OpenXRFutureResult.cancel_future
     */
    fun cancelFuture() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.cancelFutureBind, segment)
    }

    /**
     * Stores the result value we expose to the user. Note: This method should only be called by an
     * OpenXR extension that implements an asynchronous function.
     *
     * Generated from Godot docs: OpenXRFutureResult.set_result_value
     */
    fun setResultValue(resultValue: Any?) {
        checkOpen()
        ObjectCalls.ptrcallWithVariantArg(Binds.setResultValueBind, segment, resultValue)
    }

    /**
     * Returns the result value of our asynchronous function (if set by the extension). The type of
     * this result value depends on the function being called. Consult the documentation of the
     * relevant function.
     *
     * Generated from Godot docs: OpenXRFutureResult.get_result_value
     */
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

    /**
     * Godot's `OpenXRFutureResult.ResultStatus` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values (`OpenXRFutureResult.ResultStatus.<NAME>`).
     *
     * Generated from Godot docs: OpenXRFutureResult.ResultStatus
     */
    @JvmInline
    value class ResultStatus(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The asynchronous function is running.
             *
             * Generated from Godot docs: OpenXRFutureResult.RESULT_RUNNING
             */
            val RUNNING: ResultStatus get() = ResultStatus(0L)
            /**
             * The asynchronous function has finished.
             *
             * Generated from Godot docs: OpenXRFutureResult.RESULT_FINISHED
             */
            val FINISHED: ResultStatus get() = ResultStatus(1L)
            /**
             * The asynchronous function has been cancelled.
             *
             * Generated from Godot docs: OpenXRFutureResult.RESULT_CANCELLED
             */
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
