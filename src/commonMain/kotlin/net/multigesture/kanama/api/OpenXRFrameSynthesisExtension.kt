package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: OpenXRFrameSynthesisExtension
 */
class OpenXRFrameSynthesisExtension(handle: GodotHandle) : OpenXRExtensionWrapper(handle) {
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
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isAvailableBind, segment)
    }

    fun isEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isEnabledBind, segment)
    }

    fun setEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setEnabledBind, segment, enable)
    }

    fun getRelaxFrameInterval(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getRelaxFrameIntervalBind, segment)
    }

    fun setRelaxFrameInterval(relaxFrameInterval: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setRelaxFrameIntervalBind, segment, relaxFrameInterval)
    }

    fun skipNextFrame() {
        ObjectCalls.ptrcallNoArgs(Binds.skipNextFrameBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRFrameSynthesisExtension? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRFrameSynthesisExtension? =
            if (handle.address() == 0L) null else OpenXRFrameSynthesisExtension(GodotHandle(handle))
    }

    private object Binds {
        private const val IS_AVAILABLE_HASH = 36873697L
        @JvmField
        val isAvailableBind =
            ObjectCalls.getMethodBind("OpenXRFrameSynthesisExtension", "is_available", IS_AVAILABLE_HASH)

        private const val IS_ENABLED_HASH = 36873697L
        @JvmField
        val isEnabledBind =
            ObjectCalls.getMethodBind("OpenXRFrameSynthesisExtension", "is_enabled", IS_ENABLED_HASH)

        private const val SET_ENABLED_HASH = 2586408642L
        @JvmField
        val setEnabledBind =
            ObjectCalls.getMethodBind("OpenXRFrameSynthesisExtension", "set_enabled", SET_ENABLED_HASH)

        private const val GET_RELAX_FRAME_INTERVAL_HASH = 36873697L
        @JvmField
        val getRelaxFrameIntervalBind =
            ObjectCalls.getMethodBind("OpenXRFrameSynthesisExtension", "get_relax_frame_interval", GET_RELAX_FRAME_INTERVAL_HASH)

        private const val SET_RELAX_FRAME_INTERVAL_HASH = 2586408642L
        @JvmField
        val setRelaxFrameIntervalBind =
            ObjectCalls.getMethodBind("OpenXRFrameSynthesisExtension", "set_relax_frame_interval", SET_RELAX_FRAME_INTERVAL_HASH)

        private const val SKIP_NEXT_FRAME_HASH = 3218959716L
        @JvmField
        val skipNextFrameBind =
            ObjectCalls.getMethodBind("OpenXRFrameSynthesisExtension", "skip_next_frame", SKIP_NEXT_FRAME_HASH)
    }
}
