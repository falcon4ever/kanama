package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Transform3D

/**
 * Overrides the location sounds are heard from.
 *
 * Generated from Godot docs: AudioListener3D
 */
class AudioListener3D(handle: MemorySegment) : Node3D(handle) {
    var dopplerTracking: Long
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
        ObjectCalls.ptrcallNoArgs(makeCurrentBind, handle)
    }

    /**
     * Disables the listener to use the current camera's listener instead.
     *
     * Generated from Godot docs: AudioListener3D.clear_current
     */
    fun clearCurrent() {
        ObjectCalls.ptrcallNoArgs(clearCurrentBind, handle)
    }

    /**
     * Returns `true` if the listener was made current using `make_current`, `false` otherwise. Note:
     * There may be more than one AudioListener3D marked as "current" in the scene tree, but only the
     * one that was made current last will be used.
     *
     * Generated from Godot docs: AudioListener3D.is_current
     */
    fun isCurrent(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCurrentBind, handle)
    }

    /**
     * Returns the listener's global orthonormalized `Transform3D`.
     *
     * Generated from Godot docs: AudioListener3D.get_listener_transform
     */
    fun getListenerTransform(): Transform3D {
        return ObjectCalls.ptrcallNoArgsRetTransform3D(getListenerTransformBind, handle)
    }

    /**
     * If not `DOPPLER_TRACKING_DISABLED`, this listener will simulate the Doppler effect
     * (https://en.wikipedia.org/wiki/Doppler_effect) for objects changed in particular `_process`
     * methods. Note: The Doppler effect will only be heard on `AudioStreamPlayer3D`s if
     * `AudioStreamPlayer3D.doppler_tracking` is not set to
     * `AudioStreamPlayer3D.DOPPLER_TRACKING_DISABLED`.
     *
     * Generated from Godot docs: AudioListener3D.set_doppler_tracking
     */
    fun setDopplerTracking(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setDopplerTrackingBind, handle, mode)
    }

    /**
     * If not `DOPPLER_TRACKING_DISABLED`, this listener will simulate the Doppler effect
     * (https://en.wikipedia.org/wiki/Doppler_effect) for objects changed in particular `_process`
     * methods. Note: The Doppler effect will only be heard on `AudioStreamPlayer3D`s if
     * `AudioStreamPlayer3D.doppler_tracking` is not set to
     * `AudioStreamPlayer3D.DOPPLER_TRACKING_DISABLED`.
     *
     * Generated from Godot docs: AudioListener3D.get_doppler_tracking
     */
    fun getDopplerTracking(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDopplerTrackingBind, handle)
    }

    companion object {
        const val DOPPLER_TRACKING_DISABLED: Long = 0L
        const val DOPPLER_TRACKING_IDLE_STEP: Long = 1L
        const val DOPPLER_TRACKING_PHYSICS_STEP: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioListener3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioListener3D? =
            if (handle.address() == 0L) null else AudioListener3D(handle)

        private const val MAKE_CURRENT_HASH = 3218959716L
        private val makeCurrentBind by lazy {
            ObjectCalls.getMethodBind("AudioListener3D", "make_current", MAKE_CURRENT_HASH)
        }

        private const val CLEAR_CURRENT_HASH = 3218959716L
        private val clearCurrentBind by lazy {
            ObjectCalls.getMethodBind("AudioListener3D", "clear_current", CLEAR_CURRENT_HASH)
        }

        private const val IS_CURRENT_HASH = 36873697L
        private val isCurrentBind by lazy {
            ObjectCalls.getMethodBind("AudioListener3D", "is_current", IS_CURRENT_HASH)
        }

        private const val GET_LISTENER_TRANSFORM_HASH = 3229777777L
        private val getListenerTransformBind by lazy {
            ObjectCalls.getMethodBind("AudioListener3D", "get_listener_transform", GET_LISTENER_TRANSFORM_HASH)
        }

        private const val SET_DOPPLER_TRACKING_HASH = 2365921740L
        private val setDopplerTrackingBind by lazy {
            ObjectCalls.getMethodBind("AudioListener3D", "set_doppler_tracking", SET_DOPPLER_TRACKING_HASH)
        }

        private const val GET_DOPPLER_TRACKING_HASH = 550229039L
        private val getDopplerTrackingBind by lazy {
            ObjectCalls.getMethodBind("AudioListener3D", "get_doppler_tracking", GET_DOPPLER_TRACKING_HASH)
        }
    }
}
