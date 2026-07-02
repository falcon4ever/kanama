package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/** Resource base for glTF document extensions. Generated from Godot docs: GLTFDocumentExtension */
open class GLTFDocumentExtension(handle: MemorySegment) : Resource(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): GLTFDocumentExtension? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GLTFDocumentExtension? =
            if (handle.address() == 0L) null else GLTFDocumentExtension(handle)

        // No MethodBinds emitted yet.
    }
}
