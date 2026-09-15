package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

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
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): JavaScriptObject? =
            if (handle.address() == 0L) null else JavaScriptObject(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
