package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * `GLTFDocument` extension class.
 *
 * Generated from Godot docs: GLTFDocumentExtension
 */
open class GLTFDocumentExtension(handle: GodotHandle) : Resource(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): GLTFDocumentExtension? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): GLTFDocumentExtension? =
            if (handle.address() == 0L) null else RefCounted.owned(GLTFDocumentExtension(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): GLTFDocumentExtension? =
            if (handle.address() == 0L) null else GLTFDocumentExtension(GodotHandle(handle))

        /** A new GLTFDocumentExtension, GDScript's `GLTFDocumentExtension.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): GLTFDocumentExtension =
            RefCounted.owned(GLTFDocumentExtension(GodotHandle(requireConstructed(ObjectCalls.constructObject("GLTFDocumentExtension"), "GLTFDocumentExtension"))))
    }
}
