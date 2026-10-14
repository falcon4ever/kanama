package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * The OpenXR Frame synthesis extension allows for advanced reprojection at low(er) framerates.
 *
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

    /**
     * Returns `true` if frame synthesis is enabled in the project settings and the current XR runtime
     * supports frame synthesis. The value returned will only be valid once OpenXR has been
     * initialized.
     *
     * Generated from Godot docs: OpenXRFrameSynthesisExtension.is_available
     */
    fun isAvailable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isAvailableBind, segment)
    }

    /**
     * Enable frame synthesis. When `true` motion vector and depth data is provided to the XR runtime.
     *
     * Generated from Godot docs: OpenXRFrameSynthesisExtension.is_enabled
     */
    fun isEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isEnabledBind, segment)
    }

    /**
     * Enable frame synthesis. When `true` motion vector and depth data is provided to the XR runtime.
     *
     * Generated from Godot docs: OpenXRFrameSynthesisExtension.set_enabled
     */
    fun setEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setEnabledBind, segment, enable)
    }

    /**
     * If `true` this informs the XR runtime we will be providing frames at a greatly reduced rate.
     * Enable this when you expect your application to run at low framerates and wish to inject
     * multiple reprojected frames.
     *
     * Generated from Godot docs: OpenXRFrameSynthesisExtension.get_relax_frame_interval
     */
    fun getRelaxFrameInterval(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getRelaxFrameIntervalBind, segment)
    }

    /**
     * If `true` this informs the XR runtime we will be providing frames at a greatly reduced rate.
     * Enable this when you expect your application to run at low framerates and wish to inject
     * multiple reprojected frames.
     *
     * Generated from Godot docs: OpenXRFrameSynthesisExtension.set_relax_frame_interval
     */
    fun setRelaxFrameInterval(relaxFrameInterval: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setRelaxFrameIntervalBind, segment, relaxFrameInterval)
    }

    /**
     * Queues the next frame to be skipped when supplying motion vector and depth data. Call this after
     * teleporting your player or a similar action has moved the player to prevent incorrect
     * reprojection results due to this movement.
     *
     * Generated from Godot docs: OpenXRFrameSynthesisExtension.skip_next_frame
     */
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
