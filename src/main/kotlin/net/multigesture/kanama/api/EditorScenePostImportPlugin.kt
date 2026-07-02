package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Plugin to control and modifying the process of importing a scene.
 *
 * Generated from Godot docs: EditorScenePostImportPlugin
 */
class EditorScenePostImportPlugin(handle: MemorySegment) : RefCounted(handle) {
    fun getOptionValue(name: String): Any? {
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(getOptionValueBind, handle, name)
    }

    fun addImportOption(name: String, value: Any?) {
        ObjectCalls.ptrcallWithStringAndVariantArg(addImportOptionBind, handle, name, value)
    }

    fun addImportOptionAdvanced(type: Long, name: String, defaultValue: Any?, hint: Long = 0L, hintString: String = "", usageFlags: Int = 6) {
        ObjectCalls.ptrcallWithLongStringVariantLongStringIntArgs(addImportOptionAdvancedBind, handle, type, name, defaultValue, hint, hintString, usageFlags)
    }

    companion object {
        const val INTERNAL_IMPORT_CATEGORY_NODE: Long = 0L
        const val INTERNAL_IMPORT_CATEGORY_MESH_3D_NODE: Long = 1L
        const val INTERNAL_IMPORT_CATEGORY_MESH: Long = 2L
        const val INTERNAL_IMPORT_CATEGORY_MATERIAL: Long = 3L
        const val INTERNAL_IMPORT_CATEGORY_ANIMATION: Long = 4L
        const val INTERNAL_IMPORT_CATEGORY_ANIMATION_NODE: Long = 5L
        const val INTERNAL_IMPORT_CATEGORY_SKELETON_3D_NODE: Long = 6L
        const val INTERNAL_IMPORT_CATEGORY_MAX: Long = 7L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorScenePostImportPlugin? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorScenePostImportPlugin? =
            if (handle.address() == 0L) null else EditorScenePostImportPlugin(handle)

        private const val GET_OPTION_VALUE_HASH = 2760726917L
        private val getOptionValueBind by lazy {
            ObjectCalls.getMethodBind("EditorScenePostImportPlugin", "get_option_value", GET_OPTION_VALUE_HASH)
        }

        private const val ADD_IMPORT_OPTION_HASH = 402577236L
        private val addImportOptionBind by lazy {
            ObjectCalls.getMethodBind("EditorScenePostImportPlugin", "add_import_option", ADD_IMPORT_OPTION_HASH)
        }

        private const val ADD_IMPORT_OPTION_ADVANCED_HASH = 3674075649L
        private val addImportOptionAdvancedBind by lazy {
            ObjectCalls.getMethodBind("EditorScenePostImportPlugin", "add_import_option_advanced", ADD_IMPORT_OPTION_ADVANCED_HASH)
        }
    }
}
