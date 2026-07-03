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
    /**
     * Adds lines to the gizmo (as sets of 2 points), with a given material. The lines are used for
     * visualizing the gizmo. Call this method during `_redraw`.
     *
     * Generated from Godot docs: EditorNode3DGizmo.add_lines
     */
    fun addLines(lines: List<Vector3>, material: Material?, billboard: Boolean = false, modulate: Color) {
        ObjectCalls.ptrcallWithPackedVector3ListObjectBoolColorArgs(addLinesBind, handle, lines, material?.requireOpenHandle() ?: MemorySegment.NULL, billboard, modulate)
    }

    /**
     * Adds a mesh to the gizmo with the specified `material`, local `transform` and `skeleton`. Call
     * this method during `_redraw`.
     *
     * Generated from Godot docs: EditorNode3DGizmo.add_mesh
     */
    fun addMesh(mesh: Mesh?, material: Material?, transform: Transform3D, skeleton: SkinReference?) {
        ObjectCalls.ptrcallWithTwoObjectTransform3DObjectArgs(addMeshBind, handle, mesh?.requireOpenHandle() ?: MemorySegment.NULL, material?.requireOpenHandle() ?: MemorySegment.NULL, transform, skeleton?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Adds the specified `segments` to the gizmo's collision shape for picking. Call this method
     * during `_redraw`.
     *
     * Generated from Godot docs: EditorNode3DGizmo.add_collision_segments
     */
    fun addCollisionSegments(segments: List<Vector3>) {
        ObjectCalls.ptrcallWithPackedVector3ListArg(addCollisionSegmentsBind, handle, segments)
    }

    /**
     * Adds collision triangles to the gizmo for picking. A `TriangleMesh` can be generated from a
     * regular `Mesh` too. Call this method during `_redraw`.
     *
     * Generated from Godot docs: EditorNode3DGizmo.add_collision_triangles
     */
    fun addCollisionTriangles(triangles: TriangleMesh?) {
        ObjectCalls.ptrcallWithObjectArgs(addCollisionTrianglesBind, handle, listOf(triangles?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Adds an unscaled billboard for visualization and selection. Call this method during `_redraw`.
     *
     * Generated from Godot docs: EditorNode3DGizmo.add_unscaled_billboard
     */
    fun addUnscaledBillboard(material: Material?, defaultScale: Double = 1.0, modulate: Color) {
        ObjectCalls.ptrcallWithObjectDoubleColorArgs(addUnscaledBillboardBind, handle, material?.requireOpenHandle() ?: MemorySegment.NULL, defaultScale, modulate)
    }

    /**
     * Adds a list of handles (points) which can be used to edit the properties of the gizmo's
     * `Node3D`. The `ids` argument can be used to specify a custom identifier for each handle, if an
     * empty array is passed, the ids will be assigned automatically from the `handles` argument order.
     * The `secondary` argument marks the added handles as secondary, meaning they will normally have
     * lower selection priority than regular handles. When the user is holding the shift key secondary
     * handles will switch to have higher priority than regular handles. This change in priority can be
     * used to place multiple handles at the same point while still giving the user control on their
     * selection. There are virtual methods which will be called upon editing of these handles. Call
     * this method during `_redraw`.
     *
     * Generated from Godot docs: EditorNode3DGizmo.add_handles
     */
    fun addHandles(handles: List<Vector3>, material: Material?, ids: List<Int>, billboard: Boolean = false, secondary: Boolean = false) {
        ObjectCalls.ptrcallWithPackedVector3ListObjectPackedInt32ListTwoBoolArgs(addHandlesBind, handle, handles, material?.requireOpenHandle() ?: MemorySegment.NULL, ids, billboard, secondary)
    }

    /**
     * Sets the reference `Node3D` node for the gizmo. `node` must inherit from `Node3D`.
     *
     * Generated from Godot docs: EditorNode3DGizmo.set_node_3d
     */
    fun setNode3d(node: Node) {
        ObjectCalls.ptrcallWithObjectArgs(setNode3dBind, handle, listOf(node.handle))
    }

    /**
     * Returns the `Node3D` node associated with this gizmo.
     *
     * Generated from Godot docs: EditorNode3DGizmo.get_node_3d
     */
    fun getNode3d(): Node3D? {
        return Node3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getNode3dBind, handle))
    }

    /**
     * Returns the `EditorNode3DGizmoPlugin` that owns this gizmo. It's useful to retrieve materials
     * using `EditorNode3DGizmoPlugin.get_material`.
     *
     * Generated from Godot docs: EditorNode3DGizmo.get_plugin
     */
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

    /**
     * Sets the gizmo's hidden state. If `true`, the gizmo will be hidden. If `false`, it will be
     * shown.
     *
     * Generated from Godot docs: EditorNode3DGizmo.set_hidden
     */
    fun setHidden(hidden: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setHiddenBind, handle, hidden)
    }

    /**
     * Returns `true` if the given subgizmo is currently selected. Can be used to highlight selected
     * elements during `_redraw`.
     *
     * Generated from Godot docs: EditorNode3DGizmo.is_subgizmo_selected
     */
    fun isSubgizmoSelected(id: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isSubgizmoSelectedBind, handle, id)
    }

    /**
     * Returns a list of the currently selected subgizmos. Can be used to highlight selected elements
     * during `_redraw`.
     *
     * Generated from Godot docs: EditorNode3DGizmo.get_subgizmo_selection
     */
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
