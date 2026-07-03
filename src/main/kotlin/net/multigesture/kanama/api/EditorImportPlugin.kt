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
    /**
     * This function can only be called during the `_import` callback and it allows manually importing
     * resources from it. This is useful when the imported file generates external resources that
     * require importing (as example, images). Custom parameters for the ".import" file can be passed
     * via the `custom_options`. Additionally, in cases where multiple importers can handle a file, the
     * `custom_importer` can be specified to force a specific one. This function performs a resource
     * import and returns immediately with a success or error code. `generator_parameters` defines
     * optional extra metadata which will be stored as `generator_parameters` in the `remap` section of
     * the `.import` file, for example to store a md5 hash of the source data.
     *
     * Generated from Godot docs: EditorImportPlugin.append_import_external_resource
     */
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
