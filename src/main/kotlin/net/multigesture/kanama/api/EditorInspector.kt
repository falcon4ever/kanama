package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A control used to edit properties of an object.
 *
 * Generated from Godot docs: EditorInspector
 */
class EditorInspector(handle: MemorySegment) : ScrollContainer(handle) {
    /**
     * Shows the properties of the given `object` in this inspector for editing. To clear the
     * inspector, call this method with `null`. Note: If you want to edit an object in the editor's
     * main inspector, use the `edit_*` methods in `EditorInterface` instead.
     *
     * Generated from Godot docs: EditorInspector.edit
     */
    fun edit(objectValue: GodotObject) {
        ObjectCalls.ptrcallWithObjectArgs(editBind, handle, listOf(objectValue.handle))
    }

    fun getSelectedPath(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getSelectedPathBind, handle)
    }

    fun getEditedObject(): GodotObject? {
        return GodotObject.wrap(ObjectCalls.ptrcallNoArgsRetObject(getEditedObjectBind, handle))
    }

    fun collapseAllFolding() {
        ObjectCalls.ptrcallNoArgs(collapseAllFoldingBind, handle)
    }

    fun expandAllFolding() {
        ObjectCalls.ptrcallNoArgs(expandAllFoldingBind, handle)
    }

    fun expandRevertable() {
        ObjectCalls.ptrcallNoArgs(expandRevertableBind, handle)
    }

    object Signals {
        const val propertySelected: String = "property_selected"
        const val propertyKeyed: String = "property_keyed"
        const val propertyDeleted: String = "property_deleted"
        const val resourceSelected: String = "resource_selected"
        const val objectIdSelected: String = "object_id_selected"
        const val propertyEdited: String = "property_edited"
        const val propertyToggled: String = "property_toggled"
        const val editedObjectChanged: String = "edited_object_changed"
        const val restartRequested: String = "restart_requested"
    }

    companion object {
        fun instantiatePropertyEditor(objectValue: GodotObject, type: Long, path: String, hint: Long, hintText: String, usage: Long, wide: Boolean = false): EditorProperty? {
            return EditorProperty.wrap(ObjectCalls.ptrcallWithObjectLongStringLongStringUInt32BoolArgsRetObject(instantiatePropertyEditorBind, MemorySegment.NULL, objectValue.handle, type, path, hint, hintText, usage, wide))
        }

        fun createDefaultInspector(filterLineEdit: LineEdit): EditorInspector? {
            return EditorInspector.wrap(ObjectCalls.ptrcallWithObjectArgRetObject(createDefaultInspectorBind, MemorySegment.NULL, filterLineEdit.handle))
        }

        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorInspector? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorInspector? =
            if (handle.address() == 0L) null else EditorInspector(handle)

        private const val EDIT_HASH = 3975164845L
        private val editBind by lazy {
            ObjectCalls.getMethodBind("EditorInspector", "edit", EDIT_HASH)
        }

        private const val GET_SELECTED_PATH_HASH = 201670096L
        private val getSelectedPathBind by lazy {
            ObjectCalls.getMethodBind("EditorInspector", "get_selected_path", GET_SELECTED_PATH_HASH)
        }

        private const val GET_EDITED_OBJECT_HASH = 2050059866L
        private val getEditedObjectBind by lazy {
            ObjectCalls.getMethodBind("EditorInspector", "get_edited_object", GET_EDITED_OBJECT_HASH)
        }

        private const val COLLAPSE_ALL_FOLDING_HASH = 3218959716L
        private val collapseAllFoldingBind by lazy {
            ObjectCalls.getMethodBind("EditorInspector", "collapse_all_folding", COLLAPSE_ALL_FOLDING_HASH)
        }

        private const val EXPAND_ALL_FOLDING_HASH = 3218959716L
        private val expandAllFoldingBind by lazy {
            ObjectCalls.getMethodBind("EditorInspector", "expand_all_folding", EXPAND_ALL_FOLDING_HASH)
        }

        private const val EXPAND_REVERTABLE_HASH = 3218959716L
        private val expandRevertableBind by lazy {
            ObjectCalls.getMethodBind("EditorInspector", "expand_revertable", EXPAND_REVERTABLE_HASH)
        }

        private const val INSTANTIATE_PROPERTY_EDITOR_HASH = 1429914152L
        private val instantiatePropertyEditorBind by lazy {
            ObjectCalls.getMethodBind("EditorInspector", "instantiate_property_editor", INSTANTIATE_PROPERTY_EDITOR_HASH)
        }

        private const val CREATE_DEFAULT_INSPECTOR_HASH = 2419746798L
        private val createDefaultInspectorBind by lazy {
            ObjectCalls.getMethodBind("EditorInspector", "create_default_inspector", CREATE_DEFAULT_INSPECTOR_HASH)
        }
    }
}
