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
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): Texture? =
            if (handle.address() == 0L) null else RefCounted.owned(Texture(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): Texture? =
            if (handle.address() == 0L) null else Texture(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
