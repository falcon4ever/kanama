package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Class for interacting with the 2D editor's Scene Paint Mode.
 *
 * Generated from Godot docs: ScenePaint2DEditor
 */
class ScenePaint2DEditor(handle: GodotHandle) : Control(handle) {
    /**
     * Registers a custom scene provider. When the scene picker tool is active and the user clicks on
     * `control`, the `callback` is invoked and must return a `Node2D`. The returned scene becomes the
     * new painting sample.
     *
     * Generated from Godot docs: ScenePaint2DEditor.register_scene_provider
     */
    fun registerSceneProvider(control: Control, callback: GodotCallable) {
        ObjectCalls.ptrcallWithObjectCallableArgs(Binds.registerSceneProviderBind, segment, control.segment, callback.target.segment, callback.method)
        ObjectCalls.keepReachable(control)
        ObjectCalls.keepReachable(callback)
    }

    /**
     * Removes a previously registered scene provider.
     *
     * Generated from Godot docs: ScenePaint2DEditor.unregister_scene_provider
     */
    fun unregisterSceneProvider(control: Control) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.unregisterSceneProviderBind, segment, listOf(control.segment))
        ObjectCalls.keepReachable(control)
    }

    /**
     * Sets the scene sample to be painted.
     *
     * Generated from Godot docs: ScenePaint2DEditor.set_painted_scene
     */
    fun setPaintedScene(scene: Node2D) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setPaintedSceneBind, segment, listOf(scene.segment))
        ObjectCalls.keepReachable(scene)
    }

    /**
     * Returns the painted scene, or `null` if none is selected. The returned scene's properties can be
     * edited. Warning: Removing and freeing this node will render the editor useless and may cause a
     * crash.
     *
     * Generated from Godot docs: ScenePaint2DEditor.get_painted_scene
     */
    fun getPaintedScene(): Node2D? {
        return Node2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getPaintedSceneBind, segment))
    }

    /** Signal `scene_painted(node: Node2D)`; see [TypedSignal]. */
    val scenePainted: Signal1<Node2D>
        @JvmName("scenePaintedTypedSignal")
        get() = Signal1(this, "scene_painted", SignalArgType.objectOf("Node2D") { Node2D(it) })

    object Signals {
        const val scenePainted: String = "scene_painted"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ScenePaint2DEditor? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): ScenePaint2DEditor? =
            if (handle.address() == 0L) null else ScenePaint2DEditor(GodotHandle(handle))
    }

    private object Binds {
        private const val REGISTER_SCENE_PROVIDER_HASH = 2575427985L
        @JvmField
        val registerSceneProviderBind =
            ObjectCalls.getMethodBind("ScenePaint2DEditor", "register_scene_provider", REGISTER_SCENE_PROVIDER_HASH)

        private const val UNREGISTER_SCENE_PROVIDER_HASH = 1496901182L
        @JvmField
        val unregisterSceneProviderBind =
            ObjectCalls.getMethodBind("ScenePaint2DEditor", "unregister_scene_provider", UNREGISTER_SCENE_PROVIDER_HASH)

        private const val SET_PAINTED_SCENE_HASH = 2735608809L
        @JvmField
        val setPaintedSceneBind =
            ObjectCalls.getMethodBind("ScenePaint2DEditor", "set_painted_scene", SET_PAINTED_SCENE_HASH)

        private const val GET_PAINTED_SCENE_HASH = 3536679914L
        @JvmField
        val getPaintedSceneBind =
            ObjectCalls.getMethodBind("ScenePaint2DEditor", "get_painted_scene", GET_PAINTED_SCENE_HASH)
    }
}
