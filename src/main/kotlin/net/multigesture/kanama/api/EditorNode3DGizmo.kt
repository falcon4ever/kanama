package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector3

/**
 * Gizmo for editing `Node3D` objects.
 *
 * Generated from Godot docs: EditorNode3DGizmo
 */
class EditorNode3DGizmo(handle: MemorySegment) : Node3DGizmo(handle) {
    fun addLines(lines: List<Vector3>, material: Material?, billboard: Boolean = false, modulate: Color) {
        ObjectCalls.ptrcallWithPackedVector3ListObjectBoolColorArgs(addLinesBind, handle, lines, material?.requireOpenHandle() ?: MemorySegment.NULL, billboard, modulate)
    }

    fun addMesh(mesh: Mesh?, material: Material?, transform: Transform3D, skeleton: SkinReference?) {
        ObjectCalls.ptrcallWithTwoObjectTransform3DObjectArgs(addMeshBind, handle, mesh?.requireOpenHandle() ?: MemorySegment.NULL, material?.requireOpenHandle() ?: MemorySegment.NULL, transform, skeleton?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun addCollisionSegments(segments: List<Vector3>) {
        ObjectCalls.ptrcallWithPackedVector3ListArg(addCollisionSegmentsBind, handle, segments)
    }

    fun addCollisionTriangles(triangles: TriangleMesh?) {
        ObjectCalls.ptrcallWithObjectArgs(addCollisionTrianglesBind, handle, listOf(triangles?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun addUnscaledBillboard(material: Material?, defaultScale: Double = 1.0, modulate: Color) {
        ObjectCalls.ptrcallWithObjectDoubleColorArgs(addUnscaledBillboardBind, handle, material?.requireOpenHandle() ?: MemorySegment.NULL, defaultScale, modulate)
    }

    fun addHandles(handles: List<Vector3>, material: Material?, ids: List<Int>, billboard: Boolean = false, secondary: Boolean = false) {
        ObjectCalls.ptrcallWithPackedVector3ListObjectPackedInt32ListTwoBoolArgs(addHandlesBind, handle, handles, material?.requireOpenHandle() ?: MemorySegment.NULL, ids, billboard, secondary)
    }

    fun setNode3d(node: Node) {
        ObjectCalls.ptrcallWithObjectArgs(setNode3dBind, handle, listOf(node.handle))
    }

    fun getNode3d(): Node3D? {
        return Node3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getNode3dBind, handle))
    }

    fun getPlugin(): EditorNode3DGizmoPlugin? {
        return EditorNode3DGizmoPlugin.wrap(ObjectCalls.ptrcallNoArgsRetObject(getPluginBind, handle))
    }

    /**
     * Removes everything in the gizmo including meshes, collisions and handles.
     *
     * Generated from Godot docs: EditorNode3DGizmo.clear
     */
    fun clear() {
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    fun setHidden(hidden: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setHiddenBind, handle, hidden)
    }

    fun isSubgizmoSelected(id: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isSubgizmoSelectedBind, handle, id)
    }

    fun getSubgizmoSelection(): List<Int> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getSubgizmoSelectionBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorNode3DGizmo? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorNode3DGizmo? =
            if (handle.address() == 0L) null else EditorNode3DGizmo(handle)

        private const val ADD_LINES_HASH = 2910971437L
        private val addLinesBind by lazy {
            ObjectCalls.getMethodBind("EditorNode3DGizmo", "add_lines", ADD_LINES_HASH)
        }

        private const val ADD_MESH_HASH = 1579955111L
        private val addMeshBind by lazy {
            ObjectCalls.getMethodBind("EditorNode3DGizmo", "add_mesh", ADD_MESH_HASH)
        }

        private const val ADD_COLLISION_SEGMENTS_HASH = 334873810L
        private val addCollisionSegmentsBind by lazy {
            ObjectCalls.getMethodBind("EditorNode3DGizmo", "add_collision_segments", ADD_COLLISION_SEGMENTS_HASH)
        }

        private const val ADD_COLLISION_TRIANGLES_HASH = 54901064L
        private val addCollisionTrianglesBind by lazy {
            ObjectCalls.getMethodBind("EditorNode3DGizmo", "add_collision_triangles", ADD_COLLISION_TRIANGLES_HASH)
        }

        private const val ADD_UNSCALED_BILLBOARD_HASH = 520007164L
        private val addUnscaledBillboardBind by lazy {
            ObjectCalls.getMethodBind("EditorNode3DGizmo", "add_unscaled_billboard", ADD_UNSCALED_BILLBOARD_HASH)
        }

        private const val ADD_HANDLES_HASH = 2254560097L
        private val addHandlesBind by lazy {
            ObjectCalls.getMethodBind("EditorNode3DGizmo", "add_handles", ADD_HANDLES_HASH)
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
