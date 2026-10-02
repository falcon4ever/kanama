package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * An abstraction of a serialized scene.
 *
 * Generated from Godot docs: PackedScene
 */
class PackedScene(handle: GodotHandle) : Resource(handle) {
    /**
     * Packs the `path` node, and all owned sub-nodes, into this `PackedScene`. Any existing data will
     * be cleared. See `Node.owner`.
     *
     * Generated from Godot docs: PackedScene.pack
     */
    fun pack(path: Node): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithObjectArgRetLong(packBind, segment, path.segment))
    }

    /**
     * Instantiates the scene's node hierarchy. Triggers child scene instantiation(s). Triggers a
     * `Node.NOTIFICATION_SCENE_INSTANTIATED` notification on the root node.
     *
     * Generated from Godot docs: PackedScene.instantiate
     */
    fun instantiate(editState: PackedScene.GenEditState = PackedScene.GenEditState.DISABLED): Node? {
        checkOpen()
        return Node.wrap(ObjectCalls.ptrcallWithLongArgRetObject(instantiateBind, segment, editState.value))
    }

    /**
     * Returns `true` if the scene file has nodes.
     *
     * Generated from Godot docs: PackedScene.can_instantiate
     */
    fun canInstantiate(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(canInstantiateBind, segment)
    }

    /**
     * Returns the `SceneState` representing the scene file contents.
     *
     * Generated from Godot docs: PackedScene.get_state
     */
    fun getState(): SceneState? {
        checkOpen()
        return SceneState.wrap(ObjectCalls.ptrcallNoArgsRetObject(getStateBind, segment))
    }

    @JvmInline
    value class GenEditState(val value: Long) {
        companion object {
            /**
             * If passed to `instantiate`, blocks edits to the scene state.
             *
             * Generated from Godot docs: PackedScene.GEN_EDIT_STATE_DISABLED
             */
            val DISABLED: GenEditState get() = GenEditState(0L)
            /**
             * If passed to `instantiate`, provides local scene resources to the local scene. Note: Only
             * available in editor builds.
             *
             * Generated from Godot docs: PackedScene.GEN_EDIT_STATE_INSTANCE
             */
            val INSTANCE: GenEditState get() = GenEditState(1L)
            /**
             * If passed to `instantiate`, provides local scene resources to the local scene. Only the main
             * scene should receive the main edit state. Note: Only available in editor builds.
             *
             * Generated from Godot docs: PackedScene.GEN_EDIT_STATE_MAIN
             */
            val MAIN: GenEditState get() = GenEditState(2L)
            /**
             * It's similar to `GEN_EDIT_STATE_MAIN`, but for the case where the scene is being instantiated to
             * be the base of another one. Note: Only available in editor builds.
             *
             * Generated from Godot docs: PackedScene.GEN_EDIT_STATE_MAIN_INHERITED
             */
            val MAIN_INHERITED: GenEditState get() = GenEditState(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PackedScene? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): PackedScene? =
            if (handle.address() == 0L) null else PackedScene(GodotHandle(handle))

        // Instantiate a PackedScene.
        @JvmStatic
        fun create(): PackedScene =
            PackedScene(GodotHandle(ObjectCalls.constructObject("PackedScene")))

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
