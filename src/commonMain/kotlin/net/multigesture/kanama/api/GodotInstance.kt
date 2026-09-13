package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

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
        return ObjectCalls.ptrcallNoArgsRetBool(startBind, segment)
    }

    /**
     * Returns `true` if this instance has been fully started.
     *
     * Generated from Godot docs: GodotInstance.is_started
     */
    fun isStarted(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isStartedBind, segment)
    }

    /**
     * Runs a single iteration of the main loop. Returns `true` if the engine is attempting to quit.
     *
     * Generated from Godot docs: GodotInstance.iteration
     */
    fun iteration(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(iterationBind, segment)
    }

    /**
     * Notifies the instance that it is now in focus.
     *
     * Generated from Godot docs: GodotInstance.focus_in
     */
    fun focusIn() {
        ObjectCalls.ptrcallNoArgs(focusInBind, segment)
    }

    /**
     * Notifies the instance that it is now not in focus.
     *
     * Generated from Godot docs: GodotInstance.focus_out
     */
    fun focusOut() {
        ObjectCalls.ptrcallNoArgs(focusOutBind, segment)
    }

    /**
     * Notifies the instance that it is going to be paused.
     *
     * Generated from Godot docs: GodotInstance.pause
     */
    fun pause() {
        ObjectCalls.ptrcallNoArgs(pauseBind, segment)
    }

    /**
     * Notifies the instance that it is being resumed.
     *
     * Generated from Godot docs: GodotInstance.resume
     */
    fun resume() {
        ObjectCalls.ptrcallNoArgs(resumeBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): GodotInstance? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): GodotInstance? =
            if (handle.address() == 0L) null else GodotInstance(GodotHandle(handle))

        private const val START_HASH = 2240911060L
        private val startBind by lazy {
            ObjectCalls.getMethodBind("GodotInstance", "start", START_HASH)
        }

        private const val IS_STARTED_HASH = 2240911060L
        private val isStartedBind by lazy {
            ObjectCalls.getMethodBind("GodotInstance", "is_started", IS_STARTED_HASH)
        }

        private const val ITERATION_HASH = 2240911060L
        private val iterationBind by lazy {
            ObjectCalls.getMethodBind("GodotInstance", "iteration", ITERATION_HASH)
        }

        private const val FOCUS_IN_HASH = 3218959716L
        private val focusInBind by lazy {
            ObjectCalls.getMethodBind("GodotInstance", "focus_in", FOCUS_IN_HASH)
        }

        private const val FOCUS_OUT_HASH = 3218959716L
        private val focusOutBind by lazy {
            ObjectCalls.getMethodBind("GodotInstance", "focus_out", FOCUS_OUT_HASH)
        }

        private const val PAUSE_HASH = 3218959716L
        private val pauseBind by lazy {
            ObjectCalls.getMethodBind("GodotInstance", "pause", PAUSE_HASH)
        }

        private const val RESUME_HASH = 3218959716L
        private val resumeBind by lazy {
            ObjectCalls.getMethodBind("GodotInstance", "resume", RESUME_HASH)
        }
    }
}
