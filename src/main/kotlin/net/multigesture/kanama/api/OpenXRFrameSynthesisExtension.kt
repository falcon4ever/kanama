package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: OpenXRFrameSynthesisExtension
 */
class OpenXRFrameSynthesisExtension(handle: MemorySegment) : OpenXRExtensionWrapper(handle) {
    var enabled: Boolean
        @JvmName("enabledProperty")
        get() = isEnabled()
        @JvmName("setEnabledProperty")
        set(value) = setEnabled(value)

    var relaxFrameInterval: Boolean
        @JvmName("relaxFrameIntervalProperty")
        get() = getRelaxFrameInterval()
        @JvmName("setRelaxFrameIntervalProperty")
        set(value) = setRelaxFrameInterval(value)

    fun isAvailable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAvailableBind, handle)
    }

    fun isEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEnabledBind, handle)
    }

    fun setEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEnabledBind, handle, enable)
    }

    fun getRelaxFrameInterval(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getRelaxFrameIntervalBind, handle)
    }

    fun setRelaxFrameInterval(relaxFrameInterval: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setRelaxFrameIntervalBind, handle, relaxFrameInterval)
    }

    fun skipNextFrame() {
        ObjectCalls.ptrcallNoArgs(skipNextFrameBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRFrameSynthesisExtension? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRFrameSynthesisExtension? =
            if (handle.address() == 0L) null else OpenXRFrameSynthesisExtension(handle)

        private const val IS_AVAILABLE_HASH = 36873697L
        private val isAvailableBind by lazy {
            ObjectCalls.getMethodBind("OpenXRFrameSynthesisExtension", "is_available", IS_AVAILABLE_HASH)
        }

        private const val IS_ENABLED_HASH = 36873697L
        private val isEnabledBind by lazy {
            ObjectCalls.getMethodBind("OpenXRFrameSynthesisExtension", "is_enabled", IS_ENABLED_HASH)
        }

        private const val SET_ENABLED_HASH = 2586408642L
        private val setEnabledBind by lazy {
            ObjectCalls.getMethodBind("OpenXRFrameSynthesisExtension", "set_enabled", SET_ENABLED_HASH)
        }

        private const val GET_RELAX_FRAME_INTERVAL_HASH = 36873697L
        private val getRelaxFrameIntervalBind by lazy {
            ObjectCalls.getMethodBind("OpenXRFrameSynthesisExtension", "get_relax_frame_interval", GET_RELAX_FRAME_INTERVAL_HASH)
        }

        private const val SET_RELAX_FRAME_INTERVAL_HASH = 2586408642L
        private val setRelaxFrameIntervalBind by lazy {
            ObjectCalls.getMethodBind("OpenXRFrameSynthesisExtension", "set_relax_frame_interval", SET_RELAX_FRAME_INTERVAL_HASH)
        }

        private const val SKIP_NEXT_FRAME_HASH = 3218959716L
        private val skipNextFrameBind by lazy {
            ObjectCalls.getMethodBind("OpenXRFrameSynthesisExtension", "skip_next_frame", SKIP_NEXT_FRAME_HASH)
        }
    }
}
