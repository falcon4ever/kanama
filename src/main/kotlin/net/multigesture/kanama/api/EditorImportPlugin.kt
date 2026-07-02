package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Registers a custom resource importer in the editor. Use the class to parse any file and import
 * it as a new resource type.
 *
 * Generated from Godot docs: EditorImportPlugin
 */
class EditorImportPlugin(handle: MemorySegment) : ResourceImporter(handle) {
    fun appendImportExternalResource(path: String, customOptions: Map<String, Any?> = emptyMap(), customImporter: String = "", generatorParameters: Any? = null): Long {
        return ObjectCalls.ptrcallWithStringDictionaryStringVariantArgsRetLong(appendImportExternalResourceBind, handle, path, customOptions, customImporter, generatorParameters)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorImportPlugin? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorImportPlugin? =
            if (handle.address() == 0L) null else EditorImportPlugin(handle)

        private const val APPEND_IMPORT_EXTERNAL_RESOURCE_HASH = 320493106L
        private val appendImportExternalResourceBind by lazy {
            ObjectCalls.getMethodBind("EditorImportPlugin", "append_import_external_resource", APPEND_IMPORT_EXTERNAL_RESOURCE_HASH)
        }
    }
}
