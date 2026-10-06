package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Registers a custom resource importer in the editor. Use the class to parse any file and import
 * it as a new resource type.
 *
 * Generated from Godot docs: EditorImportPlugin
 */
class EditorImportPlugin(handle: GodotHandle) : ResourceImporter(handle) {
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
    fun appendImportExternalResource(path: String, customOptions: Map<String, Any?> = emptyMap(), customImporter: String = "", generatorParameters: Any? = null): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithStringDictionaryStringVariantArgsRetLong(Binds.appendImportExternalResourceBind, segment, path, customOptions, customImporter, generatorParameters))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): EditorImportPlugin? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): EditorImportPlugin? =
            if (handle.address() == 0L) null else RefCounted.owned(EditorImportPlugin(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): EditorImportPlugin? =
            if (handle.address() == 0L) null else EditorImportPlugin(GodotHandle(handle))
    }

    private object Binds {
        private const val APPEND_IMPORT_EXTERNAL_RESOURCE_HASH = 320493106L
        @JvmField
        val appendImportExternalResourceBind =
            ObjectCalls.getMethodBind("EditorImportPlugin", "append_import_external_resource", APPEND_IMPORT_EXTERNAL_RESOURCE_HASH)
    }
}
