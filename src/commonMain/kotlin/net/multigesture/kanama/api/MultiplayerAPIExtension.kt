package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Base class used for extending the `MultiplayerAPI`.
 *
 * Generated from Godot docs: MultiplayerAPIExtension
 */
class MultiplayerAPIExtension(handle: GodotHandle) : MultiplayerAPI(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): MultiplayerAPIExtension? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): MultiplayerAPIExtension? =
            if (handle.address() == 0L) null else MultiplayerAPIExtension(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
