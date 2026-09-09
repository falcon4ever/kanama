package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Transform3D

/**
 * Generated from Godot docs: EditorNode3DGizmo
 */
class EditorNode3DGizmo(handle: MemorySegment) : Node3DGizmo(handle) {
    fun addMesh(mesh: Mesh?, material: Material?, transform: Transform3D, skeleton: SkinReference?) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoObjectTransform3DObjectArgs(addMeshBind, handle, mesh?.requireOpenHandle() ?: MemorySegment.NULL, material?.requireOpenHandle() ?: MemorySegment.NULL, transform, skeleton?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun addCollisionTriangles(triangles: TriangleMesh?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(addCollisionTrianglesBind, handle, listOf(triangles?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun addUnscaledBillboard(material: Material?, defaultScale: Double = 1.0, modulate: Color) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectDoubleColorArgs(addUnscaledBillboardBind, handle, material?.requireOpenHandle() ?: MemorySegment.NULL, defaultScale, modulate)
    }

    fun setNode3d(node: Node) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setNode3dBind, handle, listOf(node.handle))
    }

    fun getNode3d(): Node3D? {
        checkOpen()
        return Node3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getNode3dBind, handle))
    }

    fun getPlugin(): EditorNode3DGizmoPlugin? {
        checkOpen()
        return EditorNode3DGizmoPlugin.wrap(ObjectCalls.ptrcallNoArgsRetObject(getPluginBind, handle))
    }

    fun clear() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    fun setHidden(hidden: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setHiddenBind, handle, hidden)
    }

    fun isSubgizmoSelected(id: Int): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetBool(isSubgizmoSelectedBind, handle, id)
    }

    fun getSubgizmoSelection(): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getSubgizmoSelectionBind, handle)
    }

    companion object {
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
