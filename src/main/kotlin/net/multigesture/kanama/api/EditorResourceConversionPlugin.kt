package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Plugin for adding custom converters from one resource format to another in the editor resource
 * picker context menu; for example, converting a `StandardMaterial3D` to a `ShaderMaterial`.
 *
 * Generated from Godot docs: EditorResourceConversionPlugin
 */
class EditorResourceConversionPlugin(handle: MemorySegment) : RefCounted(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorResourceConversionPlugin? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorResourceConversionPlugin? =
            if (handle.address() == 0L) null else EditorResourceConversionPlugin(handle)

        // No MethodBinds emitted yet.
    }
}
