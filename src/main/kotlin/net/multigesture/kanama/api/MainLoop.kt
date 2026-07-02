package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Abstract base class for the game's main loop.
 *
 * Generated from Godot docs: MainLoop
 */
open class MainLoop(handle: MemorySegment) : GodotObject(handle) {
    // No conservative instance methods emitted yet.

    object Signals {
        const val onRequestPermissionsResult: String = "on_request_permissions_result"
    }

    companion object {
        const val NOTIFICATION_OS_MEMORY_WARNING: Long = 2009L
        const val NOTIFICATION_TRANSLATION_CHANGED: Long = 2010L
        const val NOTIFICATION_WM_ABOUT: Long = 2011L
        const val NOTIFICATION_CRASH: Long = 2012L
        const val NOTIFICATION_OS_IME_UPDATE: Long = 2013L
        const val NOTIFICATION_APPLICATION_RESUMED: Long = 2014L
        const val NOTIFICATION_APPLICATION_PAUSED: Long = 2015L
        const val NOTIFICATION_APPLICATION_FOCUS_IN: Long = 2016L
        const val NOTIFICATION_APPLICATION_FOCUS_OUT: Long = 2017L
        const val NOTIFICATION_TEXT_SERVER_CHANGED: Long = 2018L
        const val NOTIFICATION_APPLICATION_PIP_MODE_ENTERED: Long = 2019L
        const val NOTIFICATION_APPLICATION_PIP_MODE_EXITED: Long = 2020L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): MainLoop? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): MainLoop? =
            if (handle.address() == 0L) null else MainLoop(handle)

        // No MethodBinds emitted yet.
    }
}
