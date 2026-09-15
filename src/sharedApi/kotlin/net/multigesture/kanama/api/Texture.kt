package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Base class for all texture types.
 *
 * Generated from Godot docs: Texture
 */
open class Texture(handle: GodotHandle) : Resource(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Texture? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): Texture? =
            if (handle.address() == 0L) null else Texture(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
