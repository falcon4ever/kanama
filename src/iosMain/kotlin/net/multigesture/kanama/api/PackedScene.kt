package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: PackedScene
 */
class PackedScene(handle: GodotHandle) : Resource(handle) {
    fun pack(path: Node): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectArgRetLong(packBind, segment, path.segment)
    }

    fun instantiate(editState: Long = 0L): Node? {
        checkOpen()
        return Node.wrap(ObjectCalls.ptrcallWithLongArgRetObject(instantiateBind, segment, editState))
    }

    fun canInstantiate(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(canInstantiateBind, segment)
    }

    fun getState(): SceneState? {
        checkOpen()
        return SceneState.wrap(ObjectCalls.ptrcallNoArgsRetObject(getStateBind, segment))
    }

    companion object {
        const val GEN_EDIT_STATE_DISABLED: Long = 0L
        const val GEN_EDIT_STATE_INSTANCE: Long = 1L
        const val GEN_EDIT_STATE_MAIN: Long = 2L
        const val GEN_EDIT_STATE_MAIN_INHERITED: Long = 3L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): PackedScene? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): PackedScene? =
            if (handle.address() == 0L) null else PackedScene(GodotHandle(handle))

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
