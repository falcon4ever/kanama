package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color

/**
 * A class used by the editor to define Node3D gizmo types.
 *
 * Generated from Godot docs: EditorNode3DGizmoPlugin
 */
class EditorNode3DGizmoPlugin(handle: MemorySegment) : Resource(handle) {
    /**
     * Creates an unshaded material with its variants (selected and/or editable) and adds them to the
     * internal material list. They can then be accessed with `get_material` and used in
     * `EditorNode3DGizmo.add_mesh` and `EditorNode3DGizmo.add_lines`. Should not be overridden.
     *
     * Generated from Godot docs: EditorNode3DGizmoPlugin.create_material
     */
    fun createMaterial(name: String, color: Color, billboard: Boolean = false, onTop: Boolean = false, useVertexColor: Boolean = false) {
        ObjectCalls.ptrcallWithStringColorThreeBoolArgs(createMaterialBind, handle, name, color, billboard, onTop, useVertexColor)
    }

    /**
     * Creates an icon material with its variants (selected and/or editable) and adds them to the
     * internal material list. They can then be accessed with `get_material` and used in
     * `EditorNode3DGizmo.add_unscaled_billboard`. Should not be overridden.
     *
     * Generated from Godot docs: EditorNode3DGizmoPlugin.create_icon_material
     */
    fun createIconMaterial(name: String, texture: Texture2D?, onTop: Boolean = false, color: Color) {
        ObjectCalls.ptrcallWithStringObjectBoolColorArgs(createIconMaterialBind, handle, name, texture?.requireOpenHandle() ?: MemorySegment.NULL, onTop, color)
    }

    /**
     * Creates a handle material with its variants (selected and/or editable) and adds them to the
     * internal material list. They can then be accessed with `get_material` and used in
     * `EditorNode3DGizmo.add_handles`. Should not be overridden. You can optionally provide a texture
     * to use instead of the default icon.
     *
     * Generated from Godot docs: EditorNode3DGizmoPlugin.create_handle_material
     */
    fun createHandleMaterial(name: String, billboard: Boolean = false, texture: Texture2D?) {
        ObjectCalls.ptrcallWithStringBoolObjectArgs(createHandleMaterialBind, handle, name, billboard, texture?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Adds a new material to the internal material list for the plugin. It can then be accessed with
     * `get_material`. Should not be overridden.
     *
     * Generated from Godot docs: EditorNode3DGizmoPlugin.add_material
     */
    fun addMaterial(name: String, material: StandardMaterial3D?) {
        ObjectCalls.ptrcallWithStringAndObjectArg(addMaterialBind, handle, name, material?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Gets material from the internal list of materials. If an `EditorNode3DGizmo` is provided, it
     * will try to get the corresponding variant (selected and/or editable).
     *
     * Generated from Godot docs: EditorNode3DGizmoPlugin.get_material
     */
    fun getMaterial(name: String, gizmo: EditorNode3DGizmo?): StandardMaterial3D? {
        return StandardMaterial3D.wrap(ObjectCalls.ptrcallWithStringAndObjectArgRetObject(getMaterialBind, handle, name, gizmo?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorNode3DGizmoPlugin? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorNode3DGizmoPlugin? =
            if (handle.address() == 0L) null else EditorNode3DGizmoPlugin(handle)

        private const val CREATE_MATERIAL_HASH = 3486012546L
        private val createMaterialBind by lazy {
            ObjectCalls.getMethodBind("EditorNode3DGizmoPlugin", "create_material", CREATE_MATERIAL_HASH)
        }

        private const val CREATE_ICON_MATERIAL_HASH = 3804976916L
        private val createIconMaterialBind by lazy {
            ObjectCalls.getMethodBind("EditorNode3DGizmoPlugin", "create_icon_material", CREATE_ICON_MATERIAL_HASH)
        }

        private const val CREATE_HANDLE_MATERIAL_HASH = 2486475223L
        private val createHandleMaterialBind by lazy {
            ObjectCalls.getMethodBind("EditorNode3DGizmoPlugin", "create_handle_material", CREATE_HANDLE_MATERIAL_HASH)
        }

        private const val ADD_MATERIAL_HASH = 1374068695L
        private val addMaterialBind by lazy {
            ObjectCalls.getMethodBind("EditorNode3DGizmoPlugin", "add_material", ADD_MATERIAL_HASH)
        }

        private const val GET_MATERIAL_HASH = 974464017L
        private val getMaterialBind by lazy {
            ObjectCalls.getMethodBind("EditorNode3DGizmoPlugin", "get_material", GET_MATERIAL_HASH)
        }
    }
}
