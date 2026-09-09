package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: EditorScenePostImportPlugin
 */
class EditorScenePostImportPlugin(handle: MemorySegment) : RefCounted(handle) {
    fun getOptionValue(name: String): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(getOptionValueBind, handle, name)
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

        fun fromHandle(handle: MemorySegment): EditorScenePostImportPlugin? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorScenePostImportPlugin? =
            if (handle.address() == 0L) null else EditorScenePostImportPlugin(handle)

        private const val GET_OPTION_VALUE_HASH = 2760726917L
        private val getOptionValueBind by lazy {
            ObjectCalls.getMethodBind("EditorScenePostImportPlugin", "get_option_value", GET_OPTION_VALUE_HASH)
        }
    }
}
