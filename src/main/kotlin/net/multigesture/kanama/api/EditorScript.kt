package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Base script that can be used to add extension functions to the editor.
 *
 * Generated from Godot docs: EditorScript
 */
class EditorScript(handle: MemorySegment) : RefCounted(handle) {
    fun addRootNode(node: Node) {
        ObjectCalls.ptrcallWithObjectArgs(addRootNodeBind, handle, listOf(node.handle))
    }

    fun getScene(): Node? {
        return Node.wrap(ObjectCalls.ptrcallNoArgsRetObject(getSceneBind, handle))
    }

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
