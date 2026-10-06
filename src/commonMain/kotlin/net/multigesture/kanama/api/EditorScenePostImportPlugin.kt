package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Plugin to control and modifying the process of importing a scene.
 *
 * Generated from Godot docs: EditorScenePostImportPlugin
 */
class EditorScenePostImportPlugin(handle: GodotHandle) : RefCounted(handle) {
    /**
     * Query the value of an option. This function can only be called from those querying visibility,
     * or processing.
     *
     * Generated from Godot docs: EditorScenePostImportPlugin.get_option_value
     */
    fun getOptionValue(name: String): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(Binds.getOptionValueBind, segment, name)
    }

    /**
     * Add a specific import option (name and default value only). This function can only be called
     * from `_get_import_options` and `_get_internal_import_options`.
     *
     * Generated from Godot docs: EditorScenePostImportPlugin.add_import_option
     */
    fun addImportOption(name: String, value: Any?) {
        checkOpen()
        ObjectCalls.ptrcallWithStringAndVariantArg(Binds.addImportOptionBind, segment, name, value)
    }

    /**
     * Add a specific import option. This function can only be called from `_get_import_options` and
     * `_get_internal_import_options`.
     *
     * Generated from Godot docs: EditorScenePostImportPlugin.add_import_option_advanced
     */
    fun addImportOptionAdvanced(type: VariantType, name: String, defaultValue: Any?, hint: GodotPropertyHint = GodotPropertyHint.NONE, hintString: String = "", usageFlags: Int = 6) {
        checkOpen()
        ObjectCalls.ptrcallWithLongStringVariantLongStringIntArgs(Binds.addImportOptionAdvancedBind, segment, type.value, name, defaultValue, hint.value, hintString, usageFlags)
    }

    /**
     * Godot's `EditorScenePostImportPlugin.InternalImportCategory` enum as a typed value: `.value` is
     * the raw number Godot uses, and the companion holds the named values
     * (`EditorScenePostImportPlugin.InternalImportCategory.<NAME>`).
     *
     * Generated from Godot docs: EditorScenePostImportPlugin.InternalImportCategory
     */
    @JvmInline
    value class InternalImportCategory(override val value: Long) : GodotEnumValue {
        companion object {
            val NODE: InternalImportCategory get() = InternalImportCategory(0L)
            val MESH_3D_NODE: InternalImportCategory get() = InternalImportCategory(1L)
            val MESH: InternalImportCategory get() = InternalImportCategory(2L)
            val MATERIAL: InternalImportCategory get() = InternalImportCategory(3L)
            val ANIMATION: InternalImportCategory get() = InternalImportCategory(4L)
            val ANIMATION_NODE: InternalImportCategory get() = InternalImportCategory(5L)
            val SKELETON_3D_NODE: InternalImportCategory get() = InternalImportCategory(6L)
            val MAX: InternalImportCategory get() = InternalImportCategory(7L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): EditorScenePostImportPlugin? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): EditorScenePostImportPlugin? =
            if (handle.address() == 0L) null else RefCounted.owned(EditorScenePostImportPlugin(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): EditorScenePostImportPlugin? =
            if (handle.address() == 0L) null else EditorScenePostImportPlugin(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_OPTION_VALUE_HASH = 2760726917L
        @JvmField
        val getOptionValueBind =
            ObjectCalls.getMethodBind("EditorScenePostImportPlugin", "get_option_value", GET_OPTION_VALUE_HASH)

        private const val ADD_IMPORT_OPTION_HASH = 402577236L
        @JvmField
        val addImportOptionBind =
            ObjectCalls.getMethodBind("EditorScenePostImportPlugin", "add_import_option", ADD_IMPORT_OPTION_HASH)

        private const val ADD_IMPORT_OPTION_ADVANCED_HASH = 3674075649L
        @JvmField
        val addImportOptionAdvancedBind =
            ObjectCalls.getMethodBind("EditorScenePostImportPlugin", "add_import_option_advanced", ADD_IMPORT_OPTION_ADVANCED_HASH)
    }
}
