package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: OpenXRFutureResult
 */
class OpenXRFutureResult(handle: GodotHandle) : RefCounted(handle) {
    fun getStatus(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getStatusBind, segment)
    }

    fun getFuture(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getFutureBind, segment)
    }

    fun cancelFuture() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(cancelFutureBind, segment)
    }

    fun setResultValue(resultValue: Any?) {
        checkOpen()
        ObjectCalls.ptrcallWithVariantArg(setResultValueBind, segment, resultValue)
    }

    fun getResultValue(): Any? {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVariantScalar(getResultValueBind, segment)
    }

    object Signals {
        const val completed: String = "completed"
    }

    companion object {
        const val RESULT_RUNNING: Long = 0L
        const val RESULT_FINISHED: Long = 1L
        const val RESULT_CANCELLED: Long = 2L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRFutureResult? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRFutureResult? =
            if (handle.address() == 0L) null else OpenXRFutureResult(GodotHandle(handle))

        private const val GET_STATUS_HASH = 2023607463L
        private val getStatusBind by lazy {
            ObjectCalls.getMethodBind("OpenXRFutureResult", "get_status", GET_STATUS_HASH)
        }

        private const val GET_FUTURE_HASH = 3905245786L
        private val getFutureBind by lazy {
            ObjectCalls.getMethodBind("OpenXRFutureResult", "get_future", GET_FUTURE_HASH)
        }

        private const val CANCEL_FUTURE_HASH = 3218959716L
        private val cancelFutureBind by lazy {
            ObjectCalls.getMethodBind("OpenXRFutureResult", "cancel_future", CANCEL_FUTURE_HASH)
        }

        private const val SET_RESULT_VALUE_HASH = 1114965689L
        private val setResultValueBind by lazy {
            ObjectCalls.getMethodBind("OpenXRFutureResult", "set_result_value", SET_RESULT_VALUE_HASH)
        }

        private const val GET_RESULT_VALUE_HASH = 1214101251L
        private val getResultValueBind by lazy {
            ObjectCalls.getMethodBind("OpenXRFutureResult", "get_result_value", GET_RESULT_VALUE_HASH)
        }
    }
}
