package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: EditorInspectorPlugin
 */
class EditorInspectorPlugin(handle: MemorySegment) : RefCounted(handle) {
    fun addCustomControl(control: Control) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(addCustomControlBind, handle, listOf(control.handle))
    }

    fun addPropertyEditor(property: String, editor: Control, addToEnd: Boolean = false, label: String = "") {
        checkOpen()
        ObjectCalls.ptrcallWithStringObjectBoolStringArgs(addPropertyEditorBind, handle, property, editor.handle, addToEnd, label)
    }

    companion object {
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
    }
}
