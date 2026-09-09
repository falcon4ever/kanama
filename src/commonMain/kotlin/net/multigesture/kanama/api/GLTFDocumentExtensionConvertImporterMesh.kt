package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: GLTFDocumentExtensionConvertImporterMesh
 */
class GLTFDocumentExtensionConvertImporterMesh(handle: MemorySegment) : GLTFDocumentExtension(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): GLTFDocumentExtensionConvertImporterMesh? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GLTFDocumentExtensionConvertImporterMesh? =
            if (handle.address() == 0L) null else GLTFDocumentExtensionConvertImporterMesh(handle)

        // No MethodBinds emitted yet.
    }
}
