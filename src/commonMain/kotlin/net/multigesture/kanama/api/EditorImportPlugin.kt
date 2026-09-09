package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Registers a custom resource importer in the editor. Use the class to parse any file and import
 * it as a new resource type.
 *
 * Generated from Godot docs: EditorImportPlugin
 */
class EditorImportPlugin(handle: MemorySegment) : ResourceImporter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorImportPlugin? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorImportPlugin? =
            if (handle.address() == 0L) null else EditorImportPlugin(handle)

        // No MethodBinds emitted yet.
    }
}
