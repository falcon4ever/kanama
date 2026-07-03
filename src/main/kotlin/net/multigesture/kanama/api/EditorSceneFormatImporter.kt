package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Imports scenes from third-parties' 3D files.
 *
 * Generated from Godot docs: EditorSceneFormatImporter
 */
open class EditorSceneFormatImporter(handle: MemorySegment) : RefCounted(handle) {
    /**
     * Add a specific import option (name and default value only). This function can only be called
     * from `_get_import_options`.
     *
     * Generated from Godot docs: EditorSceneFormatImporter.add_import_option
     */
    fun addImportOption(name: String, value: Any?) {
        ObjectCalls.ptrcallWithStringAndVariantArg(addImportOptionBind, handle, name, value)
    }

    /**
     * Add a specific import option. This function can only be called from `_get_import_options`.
     *
     * Generated from Godot docs: EditorSceneFormatImporter.add_import_option_advanced
     */
    fun addImportOptionAdvanced(type: Long, name: String, defaultValue: Any?, hint: Long = 0L, hintString: String = "", usageFlags: Int = 6) {
        ObjectCalls.ptrcallWithLongStringVariantLongStringIntArgs(addImportOptionAdvancedBind, handle, type, name, defaultValue, hint, hintString, usageFlags)
    }

    companion object {
        const val IMPORT_SCENE: Long = 1L
        const val IMPORT_ANIMATION: Long = 2L
        const val IMPORT_FAIL_ON_MISSING_DEPENDENCIES: Long = 4L
        const val IMPORT_GENERATE_TANGENT_ARRAYS: Long = 8L
        const val IMPORT_USE_NAMED_SKIN_BINDS: Long = 16L
        const val IMPORT_DISCARD_MESHES_AND_MATERIALS: Long = 32L
        const val IMPORT_FORCE_DISABLE_MESH_COMPRESSION: Long = 64L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorSceneFormatImporter? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorSceneFormatImporter? =
            if (handle.address() == 0L) null else EditorSceneFormatImporter(handle)

        private const val ADD_IMPORT_OPTION_HASH = 402577236L
        private val addImportOptionBind by lazy {
            ObjectCalls.getMethodBind("EditorSceneFormatImporter", "add_import_option", ADD_IMPORT_OPTION_HASH)
        }

        private const val ADD_IMPORT_OPTION_ADVANCED_HASH = 3674075649L
        private val addImportOptionAdvancedBind by lazy {
            ObjectCalls.getMethodBind("EditorSceneFormatImporter", "add_import_option_advanced", ADD_IMPORT_OPTION_ADVANCED_HASH)
        }
    }
}
