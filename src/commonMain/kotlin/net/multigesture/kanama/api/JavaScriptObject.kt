package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A wrapper class for web native JavaScript objects.
 *
 * Generated from Godot docs: JavaScriptObject
 */
class JavaScriptObject(handle: GodotHandle) : RefCounted(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): JavaScriptObject? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): JavaScriptObject? =
            if (handle.address() == 0L) null else RefCounted.owned(JavaScriptObject(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): JavaScriptObject? =
            if (handle.address() == 0L) null else JavaScriptObject(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
