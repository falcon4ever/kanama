package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * The OpenXR Future extension allows for asynchronous APIs to be used.
 *
 * Generated from Godot docs: OpenXRFutureExtension
 */
class OpenXRFutureExtension(handle: GodotHandle) : OpenXRExtensionWrapper(handle) {
    /**
     * Returns `true` if futures are available in the OpenXR runtime used. This function will only
     * return a usable result after OpenXR has been initialized.
     *
     * Generated from Godot docs: OpenXRFutureExtension.is_active
     */
    fun isActive(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isActiveBind, segment)
    }

    /**
     * Register an OpenXR Future object so we monitor for completion. `future` must be an `XrFutureEXT`
     * value previously returned by an API that started an asynchronous function. You can optionally
     * specify `on_success`, it will be invoked on successful completion of the future. Or you can use
     * the returned `OpenXRFutureResult` object to `await` its `OpenXRFutureResult.completed` signal.
     *
     * Generated from Godot docs: OpenXRFutureExtension.register_future
     */
    fun registerFuture(future: Long, onSuccess: GodotCallable): OpenXRFutureResult? {
        return OpenXRFutureResult.wrapOwned(ObjectCalls.ptrcallWithLongCallableArgsRetObject(Binds.registerFutureBind, segment, future, onSuccess.target.segment, onSuccess.method))
    }

    /**
     * Cancels an in-progress future. `future` must be an `XrFutureEXT` value previously returned by an
     * API that started an asynchronous function.
     *
     * Generated from Godot docs: OpenXRFutureExtension.cancel_future
     */
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
