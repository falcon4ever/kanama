package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath

/**
 * Provides the content of a `Viewport` as a dynamic texture.
 *
 * Generated from Godot docs: ViewportTexture
 */
class ViewportTexture(handle: MemorySegment) : Texture2D(handle) {
    var viewportPath: NodePath
        @JvmName("viewportPathProperty")
        get() = getViewportPathInScene()
        @JvmName("setViewportPathProperty")
        set(value) = setViewportPathInScene(value)

    fun setViewportPathInScene(path: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setViewportPathInSceneBind, handle, path)
    }

    fun getViewportPathInScene(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getViewportPathInSceneBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ViewportTexture? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ViewportTexture? =
            if (handle.address() == 0L) null else ViewportTexture(handle)

        private const val SET_VIEWPORT_PATH_IN_SCENE_HASH = 1348162250L
        private val setViewportPathInSceneBind by lazy {
            ObjectCalls.getMethodBind("ViewportTexture", "set_viewport_path_in_scene", SET_VIEWPORT_PATH_IN_SCENE_HASH)
        }

        private const val GET_VIEWPORT_PATH_IN_SCENE_HASH = 4075236667L
        private val getViewportPathInSceneBind by lazy {
            ObjectCalls.getMethodBind("ViewportTexture", "get_viewport_path_in_scene", GET_VIEWPORT_PATH_IN_SCENE_HASH)
        }
    }
}
