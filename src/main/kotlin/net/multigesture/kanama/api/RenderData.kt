package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

/**
 * Abstract render data object, holds frame data related to rendering a single frame of a viewport.
 *
 * Generated from Godot docs: RenderData
 */
open class RenderData(handle: MemorySegment) : GodotObject(handle) {
    /**
     * Returns the `RenderSceneBuffers` object managing the scene buffers for rendering this viewport.
     *
     * Generated from Godot docs: RenderData.get_render_scene_buffers
     */
    fun getRenderSceneBuffers(): RenderSceneBuffers? {
        return RenderSceneBuffers.wrap(ObjectCalls.ptrcallNoArgsRetObject(getRenderSceneBuffersBind, handle))
    }

    /**
     * Returns the `RenderSceneData` object managing this frames scene data.
     *
     * Generated from Godot docs: RenderData.get_render_scene_data
     */
    fun getRenderSceneData(): RenderSceneData? {
        return RenderSceneData.wrap(ObjectCalls.ptrcallNoArgsRetObject(getRenderSceneDataBind, handle))
    }

    /**
     * Returns the `RID` of the environment object in the `RenderingServer` being used to render this
     * viewport.
     *
     * Generated from Godot docs: RenderData.get_environment
     */
    fun getEnvironment(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getEnvironmentBind, handle)
    }

    /**
     * Returns the `RID` of the camera attributes object in the `RenderingServer` being used to render
     * this viewport.
     *
     * Generated from Godot docs: RenderData.get_camera_attributes
     */
    fun getCameraAttributes(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getCameraAttributesBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): RenderData? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RenderData? =
            if (handle.address() == 0L) null else RenderData(handle)

        private const val GET_RENDER_SCENE_BUFFERS_HASH = 2793216201L
        private val getRenderSceneBuffersBind by lazy {
            ObjectCalls.getMethodBind("RenderData", "get_render_scene_buffers", GET_RENDER_SCENE_BUFFERS_HASH)
        }

        private const val GET_RENDER_SCENE_DATA_HASH = 1288715698L
        private val getRenderSceneDataBind by lazy {
            ObjectCalls.getMethodBind("RenderData", "get_render_scene_data", GET_RENDER_SCENE_DATA_HASH)
        }

        private const val GET_ENVIRONMENT_HASH = 2944877500L
        private val getEnvironmentBind by lazy {
            ObjectCalls.getMethodBind("RenderData", "get_environment", GET_ENVIRONMENT_HASH)
        }

        private const val GET_CAMERA_ATTRIBUTES_HASH = 2944877500L
        private val getCameraAttributesBind by lazy {
            ObjectCalls.getMethodBind("RenderData", "get_camera_attributes", GET_CAMERA_ATTRIBUTES_HASH)
        }
    }
}
