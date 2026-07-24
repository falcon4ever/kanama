package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.ObjectCalls
import java.lang.foreign.MemorySegment

/**
 * An abstraction of a serialized scene.
 *
 * Generated from Godot docs: PackedScene
 */
class PackedScene internal constructor(
    handle: MemorySegment,
) : Resource(handle) {

    /**
     * Packs the `path` node, and all owned sub-nodes, into this `PackedScene`. Any existing data will
     * be cleared. See `Node.owner`.
     *
     * Generated from Godot docs: PackedScene.pack
     */
    fun pack(node: Node): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectArgsRetLong(packBind, handle, listOf(node.handle))
    }

    /**
     * Instantiates the scene's node hierarchy. Triggers child scene instantiation(s). Triggers a
     * `Node.NOTIFICATION_SCENE_INSTANTIATED` notification on the root node.
     *
     * Generated from Godot docs: PackedScene.instantiate
     */
    fun instantiate(editState: Long = GEN_EDIT_STATE_DISABLED): Node? {
        checkOpen()
        val node = ObjectCalls.ptrcallWithLongArgRetObject(instantiateBind, handle, editState)
        return if (node.address() == 0L) null else Node(node)
    }

    /**
     * Returns `true` if the scene file has nodes.
     *
     * Generated from Godot docs: PackedScene.can_instantiate
     */
    fun canInstantiate(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(canInstantiateBind, handle)
    }

    /**
     * Returns the `SceneState` representing the scene file contents.
     *
     * Generated from Godot docs: PackedScene.get_state
     */
    fun getState(): SceneState? {
        checkOpen()
        return SceneState.wrap(ObjectCalls.ptrcallNoArgsRetObject(getStateBind, handle))
    }

    companion object {
        const val GEN_EDIT_STATE_DISABLED = 0L
        const val GEN_EDIT_STATE_INSTANCE = 1L
        const val GEN_EDIT_STATE_MAIN = 2L
        const val GEN_EDIT_STATE_MAIN_INHERITED = 3L

        private const val PACK_HASH = 2584678054L
        private const val INSTANTIATE_HASH = 2628778455L
        private const val NOARGS_BOOL_HASH = 36873697L
        private const val GET_STATE_HASH = 3479783971L

        private val packBind by lazy {
            ObjectCalls.getMethodBind("PackedScene", "pack", PACK_HASH)
        }

        private val instantiateBind by lazy {
            ObjectCalls.getMethodBind("PackedScene", "instantiate", INSTANTIATE_HASH)
        }

        private val canInstantiateBind by lazy {
            ObjectCalls.getMethodBind("PackedScene", "can_instantiate", NOARGS_BOOL_HASH)
        }

        private val getStateBind by lazy {
            ObjectCalls.getMethodBind("PackedScene", "get_state", GET_STATE_HASH)
        }

        @JvmStatic
        fun create(): PackedScene =
            PackedScene(ObjectCalls.constructObject("PackedScene"))

        @JvmStatic
        fun fromHandle(handle: MemorySegment): PackedScene? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PackedScene? =
            if (handle.address() == 0L) null else PackedScene(handle)
    }
}
