package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Projection
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector3

/**
 * Abstract render data object, holds scene data related to rendering a single frame of a viewport.
 *
 * Generated from Godot docs: RenderSceneData
 */
open class RenderSceneData(handle: MemorySegment) : GodotObject(handle) {
    /**
     * Returns the camera transform used to render this frame. Note: If more than one view is rendered,
     * this will return a centered transform.
     *
     * Generated from Godot docs: RenderSceneData.get_cam_transform
     */
    fun getCamTransform(): Transform3D {
        return ObjectCalls.ptrcallNoArgsRetTransform3D(getCamTransformBind, handle)
    }

    /**
     * Returns the camera projection used to render this frame. Note: If more than one view is
     * rendered, this will return a combined projection.
     *
     * Generated from Godot docs: RenderSceneData.get_cam_projection
     */
    fun getCamProjection(): Projection {
        return ObjectCalls.ptrcallNoArgsRetProjection(getCamProjectionBind, handle)
    }

    /**
     * Returns the number of views being rendered.
     *
     * Generated from Godot docs: RenderSceneData.get_view_count
     */
    fun getViewCount(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getViewCountBind, handle)
    }

    /**
     * Returns the eye offset per view used to render this frame. This is the offset between our camera
     * transform and the eye transform.
     *
     * Generated from Godot docs: RenderSceneData.get_view_eye_offset
     */
    fun getViewEyeOffset(view: Long): Vector3 {
        return ObjectCalls.ptrcallWithUInt32ArgRetVector3(getViewEyeOffsetBind, handle, view)
    }

    /**
     * Returns the view projection per view used to render this frame. Note: If a single view is
     * rendered, this returns the camera projection. If more than one view is rendered, this will
     * return a projection for the given view including the eye offset.
     *
     * Generated from Godot docs: RenderSceneData.get_view_projection
     */
    fun getViewProjection(view: Long): Projection {
        return ObjectCalls.ptrcallWithUInt32ArgRetProjection(getViewProjectionBind, handle, view)
    }

    /**
     * Return the `RID` of the uniform buffer containing the scene data as a UBO.
     *
     * Generated from Godot docs: RenderSceneData.get_uniform_buffer
     */
    fun getUniformBuffer(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getUniformBufferBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): RenderSceneData? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RenderSceneData? =
            if (handle.address() == 0L) null else RenderSceneData(handle)

        private const val GET_CAM_TRANSFORM_HASH = 3229777777L
        private val getCamTransformBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneData", "get_cam_transform", GET_CAM_TRANSFORM_HASH)
        }

        private const val GET_CAM_PROJECTION_HASH = 2910717950L
        private val getCamProjectionBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneData", "get_cam_projection", GET_CAM_PROJECTION_HASH)
        }

        private const val GET_VIEW_COUNT_HASH = 3905245786L
        private val getViewCountBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneData", "get_view_count", GET_VIEW_COUNT_HASH)
        }

        private const val GET_VIEW_EYE_OFFSET_HASH = 711720468L
        private val getViewEyeOffsetBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneData", "get_view_eye_offset", GET_VIEW_EYE_OFFSET_HASH)
        }

        private const val GET_VIEW_PROJECTION_HASH = 3179846605L
        private val getViewProjectionBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneData", "get_view_projection", GET_VIEW_PROJECTION_HASH)
        }

        private const val GET_UNIFORM_BUFFER_HASH = 2944877500L
        private val getUniformBufferBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneData", "get_uniform_buffer", GET_UNIFORM_BUFFER_HASH)
        }
    }
}
