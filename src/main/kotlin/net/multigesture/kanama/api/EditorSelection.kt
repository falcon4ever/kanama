package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Manages the SceneTree selection in the editor.
 *
 * Generated from Godot docs: EditorSelection
 */
class EditorSelection(handle: MemorySegment) : GodotObject(handle) {
    /**
     * Clear the selection.
     *
     * Generated from Godot docs: EditorSelection.clear
     */
    fun clear() {
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    /**
     * Adds a node to the selection. Note: The newly selected node will not be automatically edited in
     * the inspector. If you want to edit a node, use `EditorInterface.edit_node`.
     *
     * Generated from Godot docs: EditorSelection.add_node
     */
    fun addNode(node: Node) {
        ObjectCalls.ptrcallWithObjectArgs(addNodeBind, handle, listOf(node.handle))
    }

    /**
     * Removes a node from the selection.
     *
     * Generated from Godot docs: EditorSelection.remove_node
     */
    fun removeNode(node: Node) {
        ObjectCalls.ptrcallWithObjectArgs(removeNodeBind, handle, listOf(node.handle))
    }

    /**
     * Returns the list of selected nodes.
     *
     * Generated from Godot docs: EditorSelection.get_selected_nodes
     */
    fun getSelectedNodes(): List<Node> {
        return ObjectCalls.ptrcallNoArgsRetTypedNodeList(getSelectedNodesBind, handle)
    }

    /**
     * Returns the list of top selected nodes only, excluding any children. This is useful for
     * performing transform operations (moving them, rotating, etc.). For example, if there is a node A
     * with a child B and a sibling C, then selecting all three will cause this method to return only A
     * and C. Changing the global transform of A will affect the global transform of B, so there is no
     * need to change B separately.
     *
     * Generated from Godot docs: EditorSelection.get_top_selected_nodes
     */
    fun getTopSelectedNodes(): List<Node> {
        return ObjectCalls.ptrcallNoArgsRetTypedNodeList(getTopSelectedNodesBind, handle)
    }

    /**
     * Returns the list of top selected nodes only, excluding any children. This is useful for
     * performing transform operations (moving them, rotating, etc.). See `get_top_selected_nodes`.
     *
     * Generated from Godot docs: EditorSelection.get_transformable_selected_nodes
     */
    fun getTransformableSelectedNodes(): List<Node> {
        return ObjectCalls.ptrcallNoArgsRetTypedNodeList(getTransformableSelectedNodesBind, handle)
    }

    object Signals {
        const val selectionChanged: String = "selection_changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorSelection? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorSelection? =
            if (handle.address() == 0L) null else EditorSelection(handle)

        private const val CLEAR_HASH = 3218959716L
        private val clearBind by lazy {
            ObjectCalls.getMethodBind("EditorSelection", "clear", CLEAR_HASH)
        }

        private const val ADD_NODE_HASH = 1078189570L
        private val addNodeBind by lazy {
            ObjectCalls.getMethodBind("EditorSelection", "add_node", ADD_NODE_HASH)
        }

        private const val REMOVE_NODE_HASH = 1078189570L
        private val removeNodeBind by lazy {
            ObjectCalls.getMethodBind("EditorSelection", "remove_node", REMOVE_NODE_HASH)
        }

        private const val GET_SELECTED_NODES_HASH = 2915620761L
        private val getSelectedNodesBind by lazy {
            ObjectCalls.getMethodBind("EditorSelection", "get_selected_nodes", GET_SELECTED_NODES_HASH)
        }

        private const val GET_TOP_SELECTED_NODES_HASH = 2915620761L
        private val getTopSelectedNodesBind by lazy {
            ObjectCalls.getMethodBind("EditorSelection", "get_top_selected_nodes", GET_TOP_SELECTED_NODES_HASH)
        }

        private const val GET_TRANSFORMABLE_SELECTED_NODES_HASH = 2915620761L
        private val getTransformableSelectedNodesBind by lazy {
            ObjectCalls.getMethodBind("EditorSelection", "get_transformable_selected_nodes", GET_TRANSFORMABLE_SELECTED_NODES_HASH)
        }
    }
}
