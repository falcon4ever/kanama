package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Plugin for adding custom converters from one resource format to another in the editor resource
 * picker context menu; for example, converting a `StandardMaterial3D` to a `ShaderMaterial`.
 *
 * Generated from Godot docs: EditorResourceConversionPlugin
 */
class EditorResourceConversionPlugin(handle: GodotHandle) : RefCounted(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): EditorResourceConversionPlugin? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): EditorResourceConversionPlugin? =
            if (handle.address() == 0L) null else EditorResourceConversionPlugin(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
