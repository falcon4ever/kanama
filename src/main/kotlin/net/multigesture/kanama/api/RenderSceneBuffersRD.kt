package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector2i

/**
 * Render scene buffer implementation for the RenderingDevice based renderers.
 *
 * Generated from Godot docs: RenderSceneBuffersRD
 */
class RenderSceneBuffersRD(handle: MemorySegment) : RenderSceneBuffers(handle) {
    /**
     * Returns `true` if a cached texture exists for this name.
     *
     * Generated from Godot docs: RenderSceneBuffersRD.has_texture
     */
    fun hasTexture(context: String, name: String): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(hasTextureBind, handle, context, name)
    }

    /**
     * Create a new texture with the given definition and cache this under the given name. Will return
     * the existing texture if it already exists.
     *
     * Generated from Godot docs: RenderSceneBuffersRD.create_texture
     */
    fun createTexture(context: String, name: String, dataFormat: Long, usageBits: Long, textureSamples: Long, size: Vector2i, layers: Long, mipmaps: Long, unique: Boolean, discardable: Boolean): RID {
        return ObjectCalls.ptrcallWithTwoStringNameLongUInt32LongVector2iTwoUInt32TwoBoolArgsRetRID(createTextureBind, handle, context, name, dataFormat, usageBits, textureSamples, size, layers, mipmaps, unique, discardable)
    }

    /**
     * Create a new texture using the given format and view and cache this under the given name. Will
     * return the existing texture if it already exists.
     *
     * Generated from Godot docs: RenderSceneBuffersRD.create_texture_from_format
     */
    fun createTextureFromFormat(context: String, name: String, format: RDTextureFormat?, view: RDTextureView?, unique: Boolean): RID {
        return ObjectCalls.ptrcallWithTwoStringNameTwoObjectBoolArgsRetRID(createTextureFromFormatBind, handle, context, name, format?.requireOpenHandle() ?: MemorySegment.NULL, view?.requireOpenHandle() ?: MemorySegment.NULL, unique)
    }

    /**
     * Create a new texture view for an existing texture and cache this under the given `view_name`.
     * Will return the existing texture view if it already exists. Will error if the source texture
     * doesn't exist.
     *
     * Generated from Godot docs: RenderSceneBuffersRD.create_texture_view
     */
    fun createTextureView(context: String, name: String, viewName: String, view: RDTextureView?): RID {
        return ObjectCalls.ptrcallWithThreeStringNameObjectArgsRetRID(createTextureViewBind, handle, context, name, viewName, view?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Returns a cached texture with this name.
     *
     * Generated from Godot docs: RenderSceneBuffersRD.get_texture
     */
    fun getTexture(context: String, name: String): RID {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetRID(getTextureBind, handle, context, name)
    }

    /**
     * Returns the texture format information with which a cached texture was created.
     *
     * Generated from Godot docs: RenderSceneBuffersRD.get_texture_format
     */
    fun getTextureFormat(context: String, name: String): RDTextureFormat? {
        return RDTextureFormat.wrap(ObjectCalls.ptrcallWithTwoStringNameArgsRetObject(getTextureFormatBind, handle, context, name))
    }

    /**
     * Returns a specific slice (layer or mipmap) for a cached texture.
     *
     * Generated from Godot docs: RenderSceneBuffersRD.get_texture_slice
     */
    fun getTextureSlice(context: String, name: String, layer: Long, mipmap: Long, layers: Long, mipmaps: Long): RID {
        return ObjectCalls.ptrcallWithTwoStringNameFourUInt32ArgsRetRID(getTextureSliceBind, handle, context, name, layer, mipmap, layers, mipmaps)
    }

    /**
     * Returns a specific view of a slice (layer or mipmap) for a cached texture.
     *
     * Generated from Godot docs: RenderSceneBuffersRD.get_texture_slice_view
     */
    fun getTextureSliceView(context: String, name: String, layer: Long, mipmap: Long, layers: Long, mipmaps: Long, view: RDTextureView?): RID {
        return ObjectCalls.ptrcallWithTwoStringNameFourUInt32ObjectArgsRetRID(getTextureSliceViewBind, handle, context, name, layer, mipmap, layers, mipmaps, view?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Returns the texture size of a given slice of a cached texture.
     *
     * Generated from Godot docs: RenderSceneBuffersRD.get_texture_slice_size
     */
    fun getTextureSliceSize(context: String, name: String, mipmap: Long): Vector2i {
        return ObjectCalls.ptrcallWithTwoStringNameUInt32ArgRetVector2i(getTextureSliceSizeBind, handle, context, name, mipmap)
    }

    /**
     * Frees all buffers related to this context.
     *
     * Generated from Godot docs: RenderSceneBuffersRD.clear_context
     */
    fun clearContext(context: String) {
        ObjectCalls.ptrcallWithStringNameArg(clearContextBind, handle, context)
    }

    /**
     * Returns the color texture we are rendering 3D content to. If multiview is used this will be a
     * texture array with all views. If `msaa` is `true` and MSAA is enabled, this returns the MSAA
     * variant of the buffer.
     *
     * Generated from Godot docs: RenderSceneBuffersRD.get_color_texture
     */
    fun getColorTexture(msaa: Boolean = false): RID {
        return ObjectCalls.ptrcallWithBoolArgRetRID(getColorTextureBind, handle, msaa)
    }

    /**
     * Returns the specified layer from the color texture we are rendering 3D content to. If `msaa` is
     * `true` and MSAA is enabled, this returns the MSAA variant of the buffer.
     *
     * Generated from Godot docs: RenderSceneBuffersRD.get_color_layer
     */
    fun getColorLayer(layer: Long, msaa: Boolean = false): RID {
        return ObjectCalls.ptrcallWithUInt32AndBoolArgRetRID(getColorLayerBind, handle, layer, msaa)
    }

    /**
     * Returns the depth texture we are rendering 3D content to. If multiview is used this will be a
     * texture array with all views. If `msaa` is `true` and MSAA is enabled, this returns the MSAA
     * variant of the buffer.
     *
     * Generated from Godot docs: RenderSceneBuffersRD.get_depth_texture
     */
    fun getDepthTexture(msaa: Boolean = false): RID {
        return ObjectCalls.ptrcallWithBoolArgRetRID(getDepthTextureBind, handle, msaa)
    }

    /**
     * Returns the specified layer from the depth texture we are rendering 3D content to. If `msaa` is
     * `true` and MSAA is enabled, this returns the MSAA variant of the buffer.
     *
     * Generated from Godot docs: RenderSceneBuffersRD.get_depth_layer
     */
    fun getDepthLayer(layer: Long, msaa: Boolean = false): RID {
        return ObjectCalls.ptrcallWithUInt32AndBoolArgRetRID(getDepthLayerBind, handle, layer, msaa)
    }

    /**
     * Returns the velocity texture we are rendering 3D content to. If multiview is used this will be a
     * texture array with all views. If `msaa` is true and MSAA is enabled, this returns the MSAA
     * variant of the buffer.
     *
     * Generated from Godot docs: RenderSceneBuffersRD.get_velocity_texture
     */
    fun getVelocityTexture(msaa: Boolean = false): RID {
        return ObjectCalls.ptrcallWithBoolArgRetRID(getVelocityTextureBind, handle, msaa)
    }

    /**
     * Returns the specified layer from the velocity texture we are rendering 3D content to.
     *
     * Generated from Godot docs: RenderSceneBuffersRD.get_velocity_layer
     */
    fun getVelocityLayer(layer: Long, msaa: Boolean = false): RID {
        return ObjectCalls.ptrcallWithUInt32AndBoolArgRetRID(getVelocityLayerBind, handle, layer, msaa)
    }

    /**
     * Returns the render target associated with this buffers object.
     *
     * Generated from Godot docs: RenderSceneBuffersRD.get_render_target
     */
    fun getRenderTarget(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getRenderTargetBind, handle)
    }

    /**
     * Returns the view count for the associated viewport.
     *
     * Generated from Godot docs: RenderSceneBuffersRD.get_view_count
     */
    fun getViewCount(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getViewCountBind, handle)
    }

    /**
     * Returns the internal size of the render buffer (size before upscaling) with which textures are
     * created by default.
     *
     * Generated from Godot docs: RenderSceneBuffersRD.get_internal_size
     */
    fun getInternalSize(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(getInternalSizeBind, handle)
    }

    /**
     * Returns the target size of the render buffer (size after upscaling).
     *
     * Generated from Godot docs: RenderSceneBuffersRD.get_target_size
     */
    fun getTargetSize(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(getTargetSizeBind, handle)
    }

    /**
     * Returns the scaling mode used for upscaling.
     *
     * Generated from Godot docs: RenderSceneBuffersRD.get_scaling_3d_mode
     */
    fun getScaling3dMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getScaling3dModeBind, handle)
    }

    /**
     * Returns the FSR sharpness value used while rendering the 3D content (if `get_scaling_3d_mode` is
     * an FSR mode).
     *
     * Generated from Godot docs: RenderSceneBuffersRD.get_fsr_sharpness
     */
    fun getFsrSharpness(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFsrSharpnessBind, handle)
    }

    /**
     * Returns the applied 3D MSAA mode for this viewport.
     *
     * Generated from Godot docs: RenderSceneBuffersRD.get_msaa_3d
     */
    fun getMsaa3d(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getMsaa3dBind, handle)
    }

    /**
     * Returns the number of MSAA samples used.
     *
     * Generated from Godot docs: RenderSceneBuffersRD.get_texture_samples
     */
    fun getTextureSamples(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTextureSamplesBind, handle)
    }

    /**
     * Returns the screen-space antialiasing method applied.
     *
     * Generated from Godot docs: RenderSceneBuffersRD.get_screen_space_aa
     */
    fun getScreenSpaceAa(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getScreenSpaceAaBind, handle)
    }

    /**
     * Returns `true` if TAA is enabled.
     *
     * Generated from Godot docs: RenderSceneBuffersRD.get_use_taa
     */
    fun getUseTaa(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUseTaaBind, handle)
    }

    /**
     * Returns `true` if debanding is enabled.
     *
     * Generated from Godot docs: RenderSceneBuffersRD.get_use_debanding
     */
    fun getUseDebanding(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUseDebandingBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): RenderSceneBuffersRD? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RenderSceneBuffersRD? =
            if (handle.address() == 0L) null else RenderSceneBuffersRD(handle)

        private const val HAS_TEXTURE_HASH = 471820014L
        private val hasTextureBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersRD", "has_texture", HAS_TEXTURE_HASH)
        }

        private const val CREATE_TEXTURE_HASH = 2950875024L
        private val createTextureBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersRD", "create_texture", CREATE_TEXTURE_HASH)
        }

        private const val CREATE_TEXTURE_FROM_FORMAT_HASH = 3344669382L
        private val createTextureFromFormatBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersRD", "create_texture_from_format", CREATE_TEXTURE_FROM_FORMAT_HASH)
        }

        private const val CREATE_TEXTURE_VIEW_HASH = 283055834L
        private val createTextureViewBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersRD", "create_texture_view", CREATE_TEXTURE_VIEW_HASH)
        }

        private const val GET_TEXTURE_HASH = 750006389L
        private val getTextureBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersRD", "get_texture", GET_TEXTURE_HASH)
        }

        private const val GET_TEXTURE_FORMAT_HASH = 371461758L
        private val getTextureFormatBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersRD", "get_texture_format", GET_TEXTURE_FORMAT_HASH)
        }

        private const val GET_TEXTURE_SLICE_HASH = 588440706L
        private val getTextureSliceBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersRD", "get_texture_slice", GET_TEXTURE_SLICE_HASH)
        }

        private const val GET_TEXTURE_SLICE_VIEW_HASH = 682451778L
        private val getTextureSliceViewBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersRD", "get_texture_slice_view", GET_TEXTURE_SLICE_VIEW_HASH)
        }

        private const val GET_TEXTURE_SLICE_SIZE_HASH = 2617625368L
        private val getTextureSliceSizeBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersRD", "get_texture_slice_size", GET_TEXTURE_SLICE_SIZE_HASH)
        }

        private const val CLEAR_CONTEXT_HASH = 3304788590L
        private val clearContextBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersRD", "clear_context", CLEAR_CONTEXT_HASH)
        }

        private const val GET_COLOR_TEXTURE_HASH = 3050822880L
        private val getColorTextureBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersRD", "get_color_texture", GET_COLOR_TEXTURE_HASH)
        }

        private const val GET_COLOR_LAYER_HASH = 3087988589L
        private val getColorLayerBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersRD", "get_color_layer", GET_COLOR_LAYER_HASH)
        }

        private const val GET_DEPTH_TEXTURE_HASH = 3050822880L
        private val getDepthTextureBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersRD", "get_depth_texture", GET_DEPTH_TEXTURE_HASH)
        }

        private const val GET_DEPTH_LAYER_HASH = 3087988589L
        private val getDepthLayerBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersRD", "get_depth_layer", GET_DEPTH_LAYER_HASH)
        }

        private const val GET_VELOCITY_TEXTURE_HASH = 3050822880L
        private val getVelocityTextureBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersRD", "get_velocity_texture", GET_VELOCITY_TEXTURE_HASH)
        }

        private const val GET_VELOCITY_LAYER_HASH = 3087988589L
        private val getVelocityLayerBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersRD", "get_velocity_layer", GET_VELOCITY_LAYER_HASH)
        }

        private const val GET_RENDER_TARGET_HASH = 2944877500L
        private val getRenderTargetBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersRD", "get_render_target", GET_RENDER_TARGET_HASH)
        }

        private const val GET_VIEW_COUNT_HASH = 3905245786L
        private val getViewCountBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersRD", "get_view_count", GET_VIEW_COUNT_HASH)
        }

        private const val GET_INTERNAL_SIZE_HASH = 3690982128L
        private val getInternalSizeBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersRD", "get_internal_size", GET_INTERNAL_SIZE_HASH)
        }

        private const val GET_TARGET_SIZE_HASH = 3690982128L
        private val getTargetSizeBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersRD", "get_target_size", GET_TARGET_SIZE_HASH)
        }

        private const val GET_SCALING_3D_MODE_HASH = 976778074L
        private val getScaling3dModeBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersRD", "get_scaling_3d_mode", GET_SCALING_3D_MODE_HASH)
        }

        private const val GET_FSR_SHARPNESS_HASH = 1740695150L
        private val getFsrSharpnessBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersRD", "get_fsr_sharpness", GET_FSR_SHARPNESS_HASH)
        }

        private const val GET_MSAA_3D_HASH = 3109158617L
        private val getMsaa3dBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersRD", "get_msaa_3d", GET_MSAA_3D_HASH)
        }

        private const val GET_TEXTURE_SAMPLES_HASH = 407791724L
        private val getTextureSamplesBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersRD", "get_texture_samples", GET_TEXTURE_SAMPLES_HASH)
        }

        private const val GET_SCREEN_SPACE_AA_HASH = 641513172L
        private val getScreenSpaceAaBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersRD", "get_screen_space_aa", GET_SCREEN_SPACE_AA_HASH)
        }

        private const val GET_USE_TAA_HASH = 36873697L
        private val getUseTaaBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersRD", "get_use_taa", GET_USE_TAA_HASH)
        }

        private const val GET_USE_DEBANDING_HASH = 36873697L
        private val getUseDebandingBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersRD", "get_use_debanding", GET_USE_DEBANDING_HASH)
        }
    }
}
