package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: OpenXRFutureExtension
 */
class OpenXRFutureExtension(handle: GodotHandle) : OpenXRExtensionWrapper(handle) {
    fun isActive(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isActiveBind, segment)
    }

    fun registerFuture(future: Long, onSuccess: GodotCallable): OpenXRFutureResult? {
        return OpenXRFutureResult.wrap(ObjectCalls.ptrcallWithLongCallableArgsRetObject(registerFutureBind, segment, future, onSuccess.target.segment, onSuccess.method))
    }

    fun cancelFuture(future: Long) {
        ObjectCalls.ptrcallWithLongArg(cancelFutureBind, segment, future)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRFutureExtension? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRFutureExtension? =
            if (handle.address() == 0L) null else OpenXRFutureExtension(GodotHandle(handle))

        private const val IS_ACTIVE_HASH = 36873697L
        private val isActiveBind by lazy {
            ObjectCalls.getMethodBind("OpenXRFutureExtension", "is_active", IS_ACTIVE_HASH)
        }

        private const val REGISTER_FUTURE_HASH = 1038012256L
        private val registerFutureBind by lazy {
            ObjectCalls.getMethodBind("OpenXRFutureExtension", "register_future", REGISTER_FUTURE_HASH)
        }

        private const val CANCEL_FUTURE_HASH = 1286410249L
        private val cancelFutureBind by lazy {
            ObjectCalls.getMethodBind("OpenXRFutureExtension", "cancel_future", CANCEL_FUTURE_HASH)
        }
    }
}
