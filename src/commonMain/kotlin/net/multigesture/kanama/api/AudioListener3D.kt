package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Transform3D

/**
 * Overrides the location sounds are heard from.
 *
 * Generated from Godot docs: AudioListener3D
 */
class AudioListener3D(handle: GodotHandle) : Node3D(handle) {
    var dopplerTracking: AudioListener3D.DopplerTracking
        @JvmName("dopplerTrackingProperty")
        get() = getDopplerTracking()
        @JvmName("setDopplerTrackingProperty")
        set(value) = setDopplerTracking(value)

    /**
     * Enables the listener. This will override the current camera's listener.
     *
     * Generated from Godot docs: AudioListener3D.make_current
     */
    fun makeCurrent() {
        ObjectCalls.ptrcallNoArgs(Binds.makeCurrentBind, segment)
    }

    /**
     * Disables the listener to use the current camera's listener instead.
     *
     * Generated from Godot docs: AudioListener3D.clear_current
     */
    fun clearCurrent() {
        ObjectCalls.ptrcallNoArgs(Binds.clearCurrentBind, segment)
    }

    /**
     * Returns `true` if the listener was made current using `make_current`, `false` otherwise. Note:
     * There may be more than one AudioListener3D marked as "current" in the scene tree, but only the
     * one that was made current last will be used.
     *
     * Generated from Godot docs: AudioListener3D.is_current
     */
    fun isCurrent(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isCurrentBind, segment)
    }

    /**
     * Returns the listener's global orthonormalized `Transform3D`.
     *
     * Generated from Godot docs: AudioListener3D.get_listener_transform
     */
    fun getListenerTransform(): Transform3D {
        return ObjectCalls.ptrcallNoArgsRetTransform3D(Binds.getListenerTransformBind, segment)
    }

    /**
     * If not `DopplerTracking.DISABLED`, this listener will simulate the Doppler effect
     * (https://en.wikipedia.org/wiki/Doppler_effect) for objects changed in particular `_process`
     * methods. Note: The Doppler effect will only be heard on `AudioStreamPlayer3D`s if
     * `AudioStreamPlayer3D.doppler_tracking` is not set to
     * `AudioStreamPlayer3D.DopplerTracking.DISABLED`.
     *
     * Generated from Godot docs: AudioListener3D.set_doppler_tracking
     */
    fun setDopplerTracking(mode: AudioListener3D.DopplerTracking) {
        ObjectCalls.ptrcallWithLongArg(Binds.setDopplerTrackingBind, segment, mode.value)
    }

    /**
     * If not `DopplerTracking.DISABLED`, this listener will simulate the Doppler effect
     * (https://en.wikipedia.org/wiki/Doppler_effect) for objects changed in particular `_process`
     * methods. Note: The Doppler effect will only be heard on `AudioStreamPlayer3D`s if
     * `AudioStreamPlayer3D.doppler_tracking` is not set to
     * `AudioStreamPlayer3D.DopplerTracking.DISABLED`.
     *
     * Generated from Godot docs: AudioListener3D.get_doppler_tracking
     */
    fun getDopplerTracking(): AudioListener3D.DopplerTracking {
        return AudioListener3D.DopplerTracking(ObjectCalls.ptrcallNoArgsRetLong(Binds.getDopplerTrackingBind, segment))
    }

    /**
     * Godot's `AudioListener3D.DopplerTracking` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values (`AudioListener3D.DopplerTracking.<NAME>`).
     *
     * Generated from Godot docs: AudioListener3D.DopplerTracking
     */
    @JvmInline
    value class DopplerTracking(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Disables Doppler effect (https://en.wikipedia.org/wiki/Doppler_effect) simulation (default).
             *
             * Generated from Godot docs: AudioListener3D.DOPPLER_TRACKING_DISABLED
             */
            val DISABLED: DopplerTracking get() = DopplerTracking(0L)
            /**
             * Simulate Doppler effect (https://en.wikipedia.org/wiki/Doppler_effect) by tracking positions of
             * objects that are changed in `_process`. Changes in the relative velocity of this listener
             * compared to those objects affect how audio is perceived (changing the audio's
             * `AudioStreamPlayer3D.pitch_scale`).
             *
             * Generated from Godot docs: AudioListener3D.DOPPLER_TRACKING_IDLE_STEP
             */
            val IDLE_STEP: DopplerTracking get() = DopplerTracking(1L)
            /**
             * Simulate Doppler effect (https://en.wikipedia.org/wiki/Doppler_effect) by tracking positions of
             * objects that are changed in `_physics_process`. Changes in the relative velocity of this
             * listener compared to those objects affect how audio is perceived (changing the audio's
             * `AudioStreamPlayer3D.pitch_scale`).
             *
             * Generated from Godot docs: AudioListener3D.DOPPLER_TRACKING_PHYSICS_STEP
             */
            val PHYSICS_STEP: DopplerTracking get() = DopplerTracking(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioListener3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AudioListener3D? =
            if (handle.address() == 0L) null else AudioListener3D(GodotHandle(handle))
    }

    private object Binds {
        private const val MAKE_CURRENT_HASH = 3218959716L
        @JvmField
        val makeCurrentBind =
            ObjectCalls.getMethodBind("AudioListener3D", "make_current", MAKE_CURRENT_HASH)

        private const val CLEAR_CURRENT_HASH = 3218959716L
        @JvmField
        val clearCurrentBind =
            ObjectCalls.getMethodBind("AudioListener3D", "clear_current", CLEAR_CURRENT_HASH)

        private const val IS_CURRENT_HASH = 36873697L
        @JvmField
        val isCurrentBind =
            ObjectCalls.getMethodBind("AudioListener3D", "is_current", IS_CURRENT_HASH)

        private const val GET_LISTENER_TRANSFORM_HASH = 3229777777L
        @JvmField
        val getListenerTransformBind =
            ObjectCalls.getMethodBind("AudioListener3D", "get_listener_transform", GET_LISTENER_TRANSFORM_HASH)

        private const val SET_DOPPLER_TRACKING_HASH = 2365921740L
        @JvmField
        val setDopplerTrackingBind =
            ObjectCalls.getMethodBind("AudioListener3D", "set_doppler_tracking", SET_DOPPLER_TRACKING_HASH)

        private const val GET_DOPPLER_TRACKING_HASH = 550229039L
        @JvmField
        val getDopplerTrackingBind =
            ObjectCalls.getMethodBind("AudioListener3D", "get_doppler_tracking", GET_DOPPLER_TRACKING_HASH)
    }
}
