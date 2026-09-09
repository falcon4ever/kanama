package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: PackedScene
 */
class PackedScene(handle: MemorySegment) : Resource(handle) {
    fun pack(path: Node): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectArgRetLong(packBind, handle, path.handle)
    }

    fun instantiate(editState: Long = 0L): Node? {
        checkOpen()
        return Node.wrap(ObjectCalls.ptrcallWithLongArgRetObject(instantiateBind, handle, editState))
    }

    fun canInstantiate(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(canInstantiateBind, handle)
    }

    fun getState(): SceneState? {
        checkOpen()
        return SceneState.wrap(ObjectCalls.ptrcallNoArgsRetObject(getStateBind, handle))
    }

    companion object {
        const val GEN_EDIT_STATE_DISABLED: Long = 0L
        const val GEN_EDIT_STATE_INSTANCE: Long = 1L
        const val GEN_EDIT_STATE_MAIN: Long = 2L
        const val GEN_EDIT_STATE_MAIN_INHERITED: Long = 3L

        fun fromHandle(handle: MemorySegment): PackedScene? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PackedScene? =
            if (handle.address() == 0L) null else PackedScene(handle)

        private const val PACK_HASH = 2584678054L
        private val packBind by lazy {
            ObjectCalls.getMethodBind("PackedScene", "pack", PACK_HASH)
        }

        private const val INSTANTIATE_HASH = 2628778455L
        private val instantiateBind by lazy {
            ObjectCalls.getMethodBind("PackedScene", "instantiate", INSTANTIATE_HASH)
        }

        private const val CAN_INSTANTIATE_HASH = 36873697L
        private val canInstantiateBind by lazy {
            ObjectCalls.getMethodBind("PackedScene", "can_instantiate", CAN_INSTANTIATE_HASH)
        }

        private const val GET_STATE_HASH = 3479783971L
        private val getStateBind by lazy {
            ObjectCalls.getMethodBind("PackedScene", "get_state", GET_STATE_HASH)
        }
    }
}
