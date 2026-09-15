package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: EditorSceneFormatImporterUFBX
 */
class EditorSceneFormatImporterUFBX(handle: GodotHandle) : EditorSceneFormatImporter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): EditorSceneFormatImporterUFBX? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): EditorSceneFormatImporterUFBX? =
            if (handle.address() == 0L) null else EditorSceneFormatImporterUFBX(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
