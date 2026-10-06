package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: OpenXRFutureExtension
 */
class OpenXRFutureExtension(handle: GodotHandle) : OpenXRExtensionWrapper(handle) {
    fun isActive(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isActiveBind, segment)
    }

    fun registerFuture(future: Long, onSuccess: GodotCallable): OpenXRFutureResult? {
        return OpenXRFutureResult.wrapOwned(ObjectCalls.ptrcallWithLongCallableArgsRetObject(Binds.registerFutureBind, segment, future, onSuccess.target.segment, onSuccess.method))
    }

    fun cancelFuture(future: Long) {
        ObjectCalls.ptrcallWithLongArg(Binds.cancelFutureBind, segment, future)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRFutureExtension? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRFutureExtension? =
            if (handle.address() == 0L) null else OpenXRFutureExtension(GodotHandle(handle))
    }

    private object Binds {
        private const val IS_ACTIVE_HASH = 36873697L
        @JvmField
        val isActiveBind =
            ObjectCalls.getMethodBind("OpenXRFutureExtension", "is_active", IS_ACTIVE_HASH)

        private const val REGISTER_FUTURE_HASH = 1038012256L
        @JvmField
        val registerFutureBind =
            ObjectCalls.getMethodBind("OpenXRFutureExtension", "register_future", REGISTER_FUTURE_HASH)

        private const val CANCEL_FUTURE_HASH = 1286410249L
        @JvmField
        val cancelFutureBind =
            ObjectCalls.getMethodBind("OpenXRFutureExtension", "cancel_future", CANCEL_FUTURE_HASH)
    }
}
