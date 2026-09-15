package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Base script that can be used to add extension functions to the editor.
 *
 * Generated from Godot docs: EditorScript
 */
class EditorScript(handle: GodotHandle) : RefCounted(handle) {
    /**
     * Makes `node` root of the currently opened scene. Only works if the scene is empty. If the `node`
     * is a scene instance, an inheriting scene will be created.
     *
     * Generated from Godot docs: EditorScript.add_root_node
     */
    fun addRootNode(node: Node) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(addRootNodeBind, segment, listOf(node.segment))
    }

    /**
     * Returns the edited (current) scene's root `Node`. Equivalent of
     * `EditorInterface.get_edited_scene_root`.
     *
     * Generated from Godot docs: EditorScript.get_scene
     */
    fun getScene(): Node? {
        checkOpen()
        return Node.wrap(ObjectCalls.ptrcallNoArgsRetObject(getSceneBind, segment))
    }

    /**
     * Returns the `EditorInterface` singleton instance.
     *
     * Generated from Godot docs: EditorScript.get_editor_interface
     */
    fun getEditorInterface(): EditorInterface? {
        checkOpen()
        return EditorInterface.wrap(ObjectCalls.ptrcallNoArgsRetObject(getEditorInterfaceBind, segment))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): EditorScript? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): EditorScript? =
            if (handle.address() == 0L) null else EditorScript(GodotHandle(handle))

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
