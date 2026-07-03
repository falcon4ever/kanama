package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Godot editor's control for selecting `Resource` type properties.
 *
 * Generated from Godot docs: EditorResourcePicker
 */
open class EditorResourcePicker(handle: MemorySegment) : HBoxContainer(handle) {
    var baseType: String
        @JvmName("baseTypeProperty")
        get() = getBaseType()
        @JvmName("setBaseTypeProperty")
        set(value) = setBaseType(value)

    var editedResource: Resource?
        @JvmName("editedResourceProperty")
        get() = getEditedResource()
        @JvmName("setEditedResourceProperty")
        set(value) = setEditedResource(value)

    var editable: Boolean
        @JvmName("editableProperty")
        get() = isEditable()
        @JvmName("setEditableProperty")
        set(value) = setEditable(value)

    var toggleMode: Boolean
        @JvmName("toggleModeProperty")
        get() = isToggleMode()
        @JvmName("setToggleModeProperty")
        set(value) = setToggleMode(value)

    /**
     * The base type of allowed resource types. Can be a comma-separated list of several options.
     *
     * Generated from Godot docs: EditorResourcePicker.set_base_type
     */
    fun setBaseType(baseType: String) {
        ObjectCalls.ptrcallWithStringArg(setBaseTypeBind, handle, baseType)
    }

    /**
     * The base type of allowed resource types. Can be a comma-separated list of several options.
     *
     * Generated from Godot docs: EditorResourcePicker.get_base_type
     */
    fun getBaseType(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getBaseTypeBind, handle)
    }

    /**
     * Returns a list of all allowed types and subtypes corresponding to the `base_type`. If the
     * `base_type` is empty, an empty list is returned.
     *
     * Generated from Godot docs: EditorResourcePicker.get_allowed_types
     */
    fun getAllowedTypes(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getAllowedTypesBind, handle)
    }

    /**
     * The edited resource value.
     *
     * Generated from Godot docs: EditorResourcePicker.set_edited_resource
     */
    fun setEditedResource(resource: Resource?) {
        ObjectCalls.ptrcallWithObjectArgs(setEditedResourceBind, handle, listOf(resource?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The edited resource value.
     *
     * Generated from Godot docs: EditorResourcePicker.get_edited_resource
     */
    fun getEditedResource(): Resource? {
        return Resource.wrap(ObjectCalls.ptrcallNoArgsRetObject(getEditedResourceBind, handle))
    }

    /**
     * If `true`, the main button with the resource preview works in the toggle mode. Use
     * `set_toggle_pressed` to manually set the state.
     *
     * Generated from Godot docs: EditorResourcePicker.set_toggle_mode
     */
    fun setToggleMode(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setToggleModeBind, handle, enable)
    }

    /**
     * If `true`, the main button with the resource preview works in the toggle mode. Use
     * `set_toggle_pressed` to manually set the state.
     *
     * Generated from Godot docs: EditorResourcePicker.is_toggle_mode
     */
    fun isToggleMode(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isToggleModeBind, handle)
    }

    /**
     * Sets the toggle mode state for the main button. Works only if `toggle_mode` is set to `true`.
     *
     * Generated from Godot docs: EditorResourcePicker.set_toggle_pressed
     */
    fun setTogglePressed(pressed: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setTogglePressedBind, handle, pressed)
    }

    /**
     * If `true`, the value can be selected and edited.
     *
     * Generated from Godot docs: EditorResourcePicker.set_editable
     */
    fun setEditable(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEditableBind, handle, enable)
    }

    /**
     * If `true`, the value can be selected and edited.
     *
     * Generated from Godot docs: EditorResourcePicker.is_editable
     */
    fun isEditable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEditableBind, handle)
    }

    object Signals {
        const val resourceSelected: String = "resource_selected"
        const val resourceChanged: String = "resource_changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorResourcePicker? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorResourcePicker? =
            if (handle.address() == 0L) null else EditorResourcePicker(handle)

        private const val SET_BASE_TYPE_HASH = 83702148L
        private val setBaseTypeBind by lazy {
            ObjectCalls.getMethodBind("EditorResourcePicker", "set_base_type", SET_BASE_TYPE_HASH)
        }

        private const val GET_BASE_TYPE_HASH = 201670096L
        private val getBaseTypeBind by lazy {
            ObjectCalls.getMethodBind("EditorResourcePicker", "get_base_type", GET_BASE_TYPE_HASH)
        }

        private const val GET_ALLOWED_TYPES_HASH = 1139954409L
        private val getAllowedTypesBind by lazy {
            ObjectCalls.getMethodBind("EditorResourcePicker", "get_allowed_types", GET_ALLOWED_TYPES_HASH)
        }

        private const val SET_EDITED_RESOURCE_HASH = 968641751L
        private val setEditedResourceBind by lazy {
            ObjectCalls.getMethodBind("EditorResourcePicker", "set_edited_resource", SET_EDITED_RESOURCE_HASH)
        }

        private const val GET_EDITED_RESOURCE_HASH = 2674603643L
        private val getEditedResourceBind by lazy {
            ObjectCalls.getMethodBind("EditorResourcePicker", "get_edited_resource", GET_EDITED_RESOURCE_HASH)
        }

        private const val SET_TOGGLE_MODE_HASH = 2586408642L
        private val setToggleModeBind by lazy {
            ObjectCalls.getMethodBind("EditorResourcePicker", "set_toggle_mode", SET_TOGGLE_MODE_HASH)
        }

        private const val IS_TOGGLE_MODE_HASH = 36873697L
        private val isToggleModeBind by lazy {
            ObjectCalls.getMethodBind("EditorResourcePicker", "is_toggle_mode", IS_TOGGLE_MODE_HASH)
        }

        private const val SET_TOGGLE_PRESSED_HASH = 2586408642L
        private val setTogglePressedBind by lazy {
            ObjectCalls.getMethodBind("EditorResourcePicker", "set_toggle_pressed", SET_TOGGLE_PRESSED_HASH)
        }

        private const val SET_EDITABLE_HASH = 2586408642L
        private val setEditableBind by lazy {
            ObjectCalls.getMethodBind("EditorResourcePicker", "set_editable", SET_EDITABLE_HASH)
        }

        private const val IS_EDITABLE_HASH = 36873697L
        private val isEditableBind by lazy {
            ObjectCalls.getMethodBind("EditorResourcePicker", "is_editable", IS_EDITABLE_HASH)
        }
    }
}
