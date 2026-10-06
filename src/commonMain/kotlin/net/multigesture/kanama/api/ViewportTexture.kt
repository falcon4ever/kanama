package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.NodePath

/**
 * Provides the content of a `Viewport` as a dynamic texture.
 *
 * Generated from Godot docs: ViewportTexture
 */
class ViewportTexture(handle: GodotHandle) : Texture2D(handle) {
    var viewportPath: NodePath
        @JvmName("viewportPathProperty")
        get() = getViewportPathInScene()
        @JvmName("setViewportPathProperty")
        set(value) = setViewportPathInScene(value)

    /**
     * The path to the `Viewport` node to display. This is relative to the local scene root (see
     * `Resource.get_local_scene`), not to the nodes that use this texture. Note: In the editor, this
     * path is automatically updated when the target viewport or one of its ancestors is renamed or
     * moved. At runtime, this path may not automatically update if the scene root cannot be found.
     *
     * Generated from Godot docs: ViewportTexture.set_viewport_path_in_scene
     */
    fun setViewportPathInScene(path: NodePath) {
        checkOpen()
        ObjectCalls.ptrcallWithNodePathArg(Binds.setViewportPathInSceneBind, segment, path)
    }

    /**
     * The path to the `Viewport` node to display. This is relative to the local scene root (see
     * `Resource.get_local_scene`), not to the nodes that use this texture. Note: In the editor, this
     * path is automatically updated when the target viewport or one of its ancestors is renamed or
     * moved. At runtime, this path may not automatically update if the scene root cannot be found.
     *
     * Generated from Godot docs: ViewportTexture.get_viewport_path_in_scene
     */
    fun getViewportPathInScene(): NodePath {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetNodePath(Binds.getViewportPathInSceneBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ViewportTexture? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): ViewportTexture? =
            if (handle.address() == 0L) null else RefCounted.owned(ViewportTexture(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): ViewportTexture? =
            if (handle.address() == 0L) null else ViewportTexture(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_VIEWPORT_PATH_IN_SCENE_HASH = 1348162250L
        @JvmField
        val setViewportPathInSceneBind =
            ObjectCalls.getMethodBind("ViewportTexture", "set_viewport_path_in_scene", SET_VIEWPORT_PATH_IN_SCENE_HASH)

        private const val GET_VIEWPORT_PATH_IN_SCENE_HASH = 4075236667L
        @JvmField
        val getViewportPathInSceneBind =
            ObjectCalls.getMethodBind("ViewportTexture", "get_viewport_path_in_scene", GET_VIEWPORT_PATH_IN_SCENE_HASH)
    }
}
