package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Godot editor's control for selecting the `script` property of a `Node`.
 *
 * Generated from Godot docs: EditorScriptPicker
 */
class EditorScriptPicker(handle: MemorySegment) : EditorResourcePicker(handle) {
    val scriptOwner: Node?
        @JvmName("scriptOwnerProperty")
        get() = getScriptOwner()

    fun setScriptOwner(ownerNode: Node) {
        ObjectCalls.ptrcallWithObjectArgs(setScriptOwnerBind, handle, listOf(ownerNode.handle))
    }

    fun getScriptOwner(): Node? {
        return Node.wrap(ObjectCalls.ptrcallNoArgsRetObject(getScriptOwnerBind, handle))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorScriptPicker? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorScriptPicker? =
            if (handle.address() == 0L) null else EditorScriptPicker(handle)

        private const val SET_SCRIPT_OWNER_HASH = 1078189570L
        private val setScriptOwnerBind by lazy {
            ObjectCalls.getMethodBind("EditorScriptPicker", "set_script_owner", SET_SCRIPT_OWNER_HASH)
        }

        private const val GET_SCRIPT_OWNER_HASH = 3160264692L
        private val getScriptOwnerBind by lazy {
            ObjectCalls.getMethodBind("EditorScriptPicker", "get_script_owner", GET_SCRIPT_OWNER_HASH)
        }
    }
}
