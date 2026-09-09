package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Base class used for extending the `MultiplayerAPI`.
 *
 * Generated from Godot docs: MultiplayerAPIExtension
 */
class MultiplayerAPIExtension(handle: MemorySegment) : MultiplayerAPI(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): MultiplayerAPIExtension? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): MultiplayerAPIExtension? =
            if (handle.address() == 0L) null else MultiplayerAPIExtension(handle)

        // No MethodBinds emitted yet.
    }
}
