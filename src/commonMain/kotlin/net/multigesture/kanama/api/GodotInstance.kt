package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Provides access to an embedded Godot instance.
 *
 * Generated from Godot docs: GodotInstance
 */
class GodotInstance(handle: GodotHandle) : GodotObject(handle) {
    /**
     * Finishes this instance's startup sequence. Returns `true` on success.
     *
     * Generated from Godot docs: GodotInstance.start
     */
    fun start(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.startBind, segment)
    }

    /**
     * Returns `true` if this instance has been fully started.
     *
     * Generated from Godot docs: GodotInstance.is_started
     */
    fun isStarted(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isStartedBind, segment)
    }

    /**
     * Runs a single iteration of the main loop. Returns `true` if the engine is attempting to quit.
     *
     * Generated from Godot docs: GodotInstance.iteration
     */
    fun iteration(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.iterationBind, segment)
    }

    /**
     * Notifies the instance that it is now in focus.
     *
     * Generated from Godot docs: GodotInstance.focus_in
     */
    fun focusIn() {
        ObjectCalls.ptrcallNoArgs(Binds.focusInBind, segment)
    }

    /**
     * Notifies the instance that it is now not in focus.
     *
     * Generated from Godot docs: GodotInstance.focus_out
     */
    fun focusOut() {
        ObjectCalls.ptrcallNoArgs(Binds.focusOutBind, segment)
    }

    /**
     * Notifies the instance that it is going to be paused.
     *
     * Generated from Godot docs: GodotInstance.pause
     */
    fun pause() {
        ObjectCalls.ptrcallNoArgs(Binds.pauseBind, segment)
    }

    /**
     * Notifies the instance that it is being resumed.
     *
     * Generated from Godot docs: GodotInstance.resume
     */
    fun resume() {
        ObjectCalls.ptrcallNoArgs(Binds.resumeBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): GodotInstance? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): GodotInstance? =
            if (handle.address() == 0L) null else GodotInstance(GodotHandle(handle))
    }

    private object Binds {
        private const val START_HASH = 2240911060L
        @JvmField
        val startBind =
            ObjectCalls.getMethodBind("GodotInstance", "start", START_HASH)

        private const val IS_STARTED_HASH = 2240911060L
        @JvmField
        val isStartedBind =
            ObjectCalls.getMethodBind("GodotInstance", "is_started", IS_STARTED_HASH)

        private const val ITERATION_HASH = 2240911060L
        @JvmField
        val iterationBind =
            ObjectCalls.getMethodBind("GodotInstance", "iteration", ITERATION_HASH)

        private const val FOCUS_IN_HASH = 3218959716L
        @JvmField
        val focusInBind =
            ObjectCalls.getMethodBind("GodotInstance", "focus_in", FOCUS_IN_HASH)

        private const val FOCUS_OUT_HASH = 3218959716L
        @JvmField
        val focusOutBind =
            ObjectCalls.getMethodBind("GodotInstance", "focus_out", FOCUS_OUT_HASH)

        private const val PAUSE_HASH = 3218959716L
        @JvmField
        val pauseBind =
            ObjectCalls.getMethodBind("GodotInstance", "pause", PAUSE_HASH)

        private const val RESUME_HASH = 3218959716L
        @JvmField
        val resumeBind =
            ObjectCalls.getMethodBind("GodotInstance", "resume", RESUME_HASH)
    }
}
