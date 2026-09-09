package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Transform3D

/**
 * Gizmo for editing `Node3D` objects.
 *
 * Generated from Godot docs: EditorNode3DGizmo
 */
class EditorNode3DGizmo(handle: MemorySegment) : Node3DGizmo(handle) {
    /**
     * Adds a mesh to the gizmo with the specified `material`, local `transform` and `skeleton`. Call
     * this method during `_redraw`.
     *
     * Generated from Godot docs: EditorNode3DGizmo.add_mesh
     */
    fun addMesh(mesh: Mesh?, material: Material?, transform: Transform3D, skeleton: SkinReference?) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoObjectTransform3DObjectArgs(addMeshBind, handle, mesh?.requireOpenHandle() ?: MemorySegment.NULL, material?.requireOpenHandle() ?: MemorySegment.NULL, transform, skeleton?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Adds collision triangles to the gizmo for picking. A `TriangleMesh` can be generated from a
     * regular `Mesh` too. Call this method during `_redraw`.
     *
     * Generated from Godot docs: EditorNode3DGizmo.add_collision_triangles
     */
    fun addCollisionTriangles(triangles: TriangleMesh?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(addCollisionTrianglesBind, handle, listOf(triangles?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Adds an unscaled billboard for visualization and selection. Call this method during `_redraw`.
     *
     * Generated from Godot docs: EditorNode3DGizmo.add_unscaled_billboard
     */
    fun addUnscaledBillboard(material: Material?, defaultScale: Double = 1.0, modulate: Color) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectDoubleColorArgs(addUnscaledBillboardBind, handle, material?.requireOpenHandle() ?: MemorySegment.NULL, defaultScale, modulate)
    }

    /**
     * Sets the reference `Node3D` node for the gizmo. `node` must inherit from `Node3D`.
     *
     * Generated from Godot docs: EditorNode3DGizmo.set_node_3d
     */
    fun setNode3d(node: Node) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setNode3dBind, handle, listOf(node.handle))
    }

    /**
     * Returns the `Node3D` node associated with this gizmo.
     *
     * Generated from Godot docs: EditorNode3DGizmo.get_node_3d
     */
    fun getNode3d(): Node3D? {
        checkOpen()
        return Node3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getNode3dBind, handle))
    }

    /**
     * Returns the `EditorNode3DGizmoPlugin` that owns this gizmo. It's useful to retrieve materials
     * using `EditorNode3DGizmoPlugin.get_material`.
     *
     * Generated from Godot docs: EditorNode3DGizmo.get_plugin
     */
    fun getPlugin(): EditorNode3DGizmoPlugin? {
        checkOpen()
        return EditorNode3DGizmoPlugin.wrap(ObjectCalls.ptrcallNoArgsRetObject(getPluginBind, handle))
    }

    /**
     * Removes everything in the gizmo including meshes, collisions and handles.
     *
     * Generated from Godot docs: EditorNode3DGizmo.clear
     */
    fun clear() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    /**
     * Sets the gizmo's hidden state. If `true`, the gizmo will be hidden. If `false`, it will be
     * shown.
     *
     * Generated from Godot docs: EditorNode3DGizmo.set_hidden
     */
    fun setHidden(hidden: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setHiddenBind, handle, hidden)
    }

    /**
     * Returns `true` if the given subgizmo is currently selected. Can be used to highlight selected
     * elements during `_redraw`.
     *
     * Generated from Godot docs: EditorNode3DGizmo.is_subgizmo_selected
     */
    fun isSubgizmoSelected(id: Int): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetBool(isSubgizmoSelectedBind, handle, id)
    }

    /**
     * Returns a list of the currently selected subgizmos. Can be used to highlight selected elements
     * during `_redraw`.
     *
     * Generated from Godot docs: EditorNode3DGizmo.get_subgizmo_selection
     */
    fun getSubgizmoSelection(): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getSubgizmoSelectionBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorNode3DGizmo? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorNode3DGizmo? =
            if (handle.address() == 0L) null else EditorNode3DGizmo(handle)

        private const val ADD_MESH_HASH = 1579955111L
        private val addMeshBind by lazy {
            ObjectCalls.getMethodBind("EditorNode3DGizmo", "add_mesh", ADD_MESH_HASH)
        }

        private const val ADD_COLLISION_TRIANGLES_HASH = 54901064L
        private val addCollisionTrianglesBind by lazy {
            ObjectCalls.getMethodBind("EditorNode3DGizmo", "add_collision_triangles", ADD_COLLISION_TRIANGLES_HASH)
        }

        private const val ADD_UNSCALED_BILLBOARD_HASH = 520007164L
        private val addUnscaledBillboardBind by lazy {
            ObjectCalls.getMethodBind("EditorNode3DGizmo", "add_unscaled_billboard", ADD_UNSCALED_BILLBOARD_HASH)
        }

        private const val SET_NODE_3D_HASH = 1078189570L
        private val setNode3dBind by lazy {
            ObjectCalls.getMethodBind("EditorNode3DGizmo", "set_node_3d", SET_NODE_3D_HASH)
        }

        private const val GET_NODE_3D_HASH = 151077316L
        private val getNode3dBind by lazy {
            ObjectCalls.getMethodBind("EditorNode3DGizmo", "get_node_3d", GET_NODE_3D_HASH)
        }

        private const val GET_PLUGIN_HASH = 4250544552L
        private val getPluginBind by lazy {
            ObjectCalls.getMethodBind("EditorNode3DGizmo", "get_plugin", GET_PLUGIN_HASH)
        }

        private const val CLEAR_HASH = 3218959716L
        private val clearBind by lazy {
            ObjectCalls.getMethodBind("EditorNode3DGizmo", "clear", CLEAR_HASH)
        }

        private const val SET_HIDDEN_HASH = 2586408642L
        private val setHiddenBind by lazy {
            ObjectCalls.getMethodBind("EditorNode3DGizmo", "set_hidden", SET_HIDDEN_HASH)
        }

        private const val IS_SUBGIZMO_SELECTED_HASH = 1116898809L
        private val isSubgizmoSelectedBind by lazy {
            ObjectCalls.getMethodBind("EditorNode3DGizmo", "is_subgizmo_selected", IS_SUBGIZMO_SELECTED_HASH)
        }

        private const val GET_SUBGIZMO_SELECTION_HASH = 1930428628L
        private val getSubgizmoSelectionBind by lazy {
            ObjectCalls.getMethodBind("EditorNode3DGizmo", "get_subgizmo_selection", GET_SUBGIZMO_SELECTION_HASH)
        }
    }
}
