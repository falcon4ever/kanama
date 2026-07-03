package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Plugin for adding custom property editors on the inspector.
 *
 * Generated from Godot docs: EditorInspectorPlugin
 */
class EditorInspectorPlugin(handle: MemorySegment) : RefCounted(handle) {
    /**
     * Adds a custom control, which is not necessarily a property editor.
     *
     * Generated from Godot docs: EditorInspectorPlugin.add_custom_control
     */
    fun addCustomControl(control: Control) {
        ObjectCalls.ptrcallWithObjectArgs(addCustomControlBind, handle, listOf(control.handle))
    }

    /**
     * Adds a property editor for an individual property. The `editor` control must extend
     * `EditorProperty`. There can be multiple property editors for a property. If `add_to_end` is
     * `true`, this newly added editor will be displayed after all the other editors of the property
     * whose `add_to_end` is `false`. For example, the editor uses this parameter to add an "Edit
     * Region" button for `Sprite2D.region_rect` below the regular `Rect2` editor. `label` can be used
     * to choose a custom label for the property editor in the inspector. If left empty, the label is
     * computed from the name of the property instead.
     *
     * Generated from Godot docs: EditorInspectorPlugin.add_property_editor
     */
    fun addPropertyEditor(property: String, editor: Control, addToEnd: Boolean = false, label: String = "") {
        ObjectCalls.ptrcallWithStringObjectBoolStringArgs(addPropertyEditorBind, handle, property, editor.handle, addToEnd, label)
    }

    /**
     * Adds an editor that allows modifying multiple properties. The `editor` control must extend
     * `EditorProperty`.
     *
     * Generated from Godot docs: EditorInspectorPlugin.add_property_editor_for_multiple_properties
     */
    fun addPropertyEditorForMultipleProperties(label: String, properties: List<String>, editor: Control) {
        ObjectCalls.ptrcallWithStringPackedStringListAndObjectArgs(addPropertyEditorForMultiplePropertiesBind, handle, label, properties, editor.handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorInspectorPlugin? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorInspectorPlugin? =
            if (handle.address() == 0L) null else EditorInspectorPlugin(handle)

        private const val ADD_CUSTOM_CONTROL_HASH = 1496901182L
        private val addCustomControlBind by lazy {
            ObjectCalls.getMethodBind("EditorInspectorPlugin", "add_custom_control", ADD_CUSTOM_CONTROL_HASH)
        }

        private const val ADD_PROPERTY_EDITOR_HASH = 2042698479L
        private val addPropertyEditorBind by lazy {
            ObjectCalls.getMethodBind("EditorInspectorPlugin", "add_property_editor", ADD_PROPERTY_EDITOR_HASH)
        }

        private const val ADD_PROPERTY_EDITOR_FOR_MULTIPLE_PROPERTIES_HASH = 788598683L
        private val addPropertyEditorForMultiplePropertiesBind by lazy {
            ObjectCalls.getMethodBind("EditorInspectorPlugin", "add_property_editor_for_multiple_properties", ADD_PROPERTY_EDITOR_FOR_MULTIPLE_PROPERTIES_HASH)
        }
    }
}
