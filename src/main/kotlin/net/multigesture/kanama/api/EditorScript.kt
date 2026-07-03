package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Base script that can be used to add extension functions to the editor.
 *
 * Generated from Godot docs: EditorScript
 */
class EditorScript(handle: MemorySegment) : RefCounted(handle) {
    /**
     * Makes `node` root of the currently opened scene. Only works if the scene is empty. If the `node`
     * is a scene instance, an inheriting scene will be created.
     *
     * Generated from Godot docs: EditorScript.add_root_node
     */
    fun addRootNode(node: Node) {
        ObjectCalls.ptrcallWithObjectArgs(addRootNodeBind, handle, listOf(node.handle))
    }

    /**
     * Returns the edited (current) scene's root `Node`. Equivalent of
     * `EditorInterface.get_edited_scene_root`.
     *
     * Generated from Godot docs: EditorScript.get_scene
     */
    fun getScene(): Node? {
        return Node.wrap(ObjectCalls.ptrcallNoArgsRetObject(getSceneBind, handle))
    }

    /**
     * Returns the `EditorInterface` singleton instance.
     *
     * Generated from Godot docs: EditorScript.get_editor_interface
     */
    fun getEditorInterface(): EditorInterface? {
        return EditorInterface.wrap(ObjectCalls.ptrcallNoArgsRetObject(getEditorInterfaceBind, handle))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorScript? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorScript? =
            if (handle.address() == 0L) null else EditorScript(handle)

        private const val ADD_ROOT_NODE_HASH = 1078189570L
        private val addRootNodeBind by lazy {
            ObjectCalls.getMethodBind("EditorScript", "add_root_node", ADD_ROOT_NODE_HASH)
        }

        private const val GET_SCENE_HASH = 3160264692L
        private val getSceneBind by lazy {
            ObjectCalls.getMethodBind("EditorScript", "get_scene", GET_SCENE_HASH)
        }

        private const val GET_EDITOR_INTERFACE_HASH = 1976662476L
        private val getEditorInterfaceBind by lazy {
            ObjectCalls.getMethodBind("EditorScript", "get_editor_interface", GET_EDITOR_INTERFACE_HASH)
        }
    }
}
