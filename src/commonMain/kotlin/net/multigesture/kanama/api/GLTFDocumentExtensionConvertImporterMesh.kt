package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Generated from Godot docs: GLTFDocumentExtensionConvertImporterMesh
 */
class GLTFDocumentExtensionConvertImporterMesh(handle: GodotHandle) : GLTFDocumentExtension(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): GLTFDocumentExtensionConvertImporterMesh? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): GLTFDocumentExtensionConvertImporterMesh? =
            if (handle.address() == 0L) null else RefCounted.owned(GLTFDocumentExtensionConvertImporterMesh(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): GLTFDocumentExtensionConvertImporterMesh? =
            if (handle.address() == 0L) null else GLTFDocumentExtensionConvertImporterMesh(GodotHandle(handle))

        /** A new GLTFDocumentExtensionConvertImporterMesh, GDScript's `GLTFDocumentExtensionConvertImporterMesh.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): GLTFDocumentExtensionConvertImporterMesh =
            RefCounted.owned(GLTFDocumentExtensionConvertImporterMesh(GodotHandle(requireConstructed(ObjectCalls.constructObject("GLTFDocumentExtensionConvertImporterMesh"), "GLTFDocumentExtensionConvertImporterMesh"))))
    }
}
