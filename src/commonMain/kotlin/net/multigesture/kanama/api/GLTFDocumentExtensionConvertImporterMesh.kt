package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: GLTFDocumentExtensionConvertImporterMesh
 */
class GLTFDocumentExtensionConvertImporterMesh(handle: GodotHandle) : GLTFDocumentExtension(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): GLTFDocumentExtensionConvertImporterMesh? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): GLTFDocumentExtensionConvertImporterMesh? =
            if (handle.address() == 0L) null else GLTFDocumentExtensionConvertImporterMesh(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
