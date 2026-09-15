package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: FBXDocument
 */
class FBXDocument(handle: GodotHandle) : GLTFDocument(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): FBXDocument? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): FBXDocument? =
            if (handle.address() == 0L) null else FBXDocument(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
