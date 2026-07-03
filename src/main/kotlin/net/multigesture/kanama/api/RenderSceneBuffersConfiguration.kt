package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector2i

/**
 * Configuration object used to setup a `RenderSceneBuffers` object.
 *
 * Generated from Godot docs: RenderSceneBuffersConfiguration
 */
class RenderSceneBuffersConfiguration(handle: MemorySegment) : RefCounted(handle) {
    var renderTarget: RID
        @JvmName("renderTargetProperty")
        get() = getRenderTarget()
        @JvmName("setRenderTargetProperty")
        set(value) = setRenderTarget(value)

    var internalSize: Vector2i
        @JvmName("internalSizeProperty")
        get() = getInternalSize()
        @JvmName("setInternalSizeProperty")
        set(value) = setInternalSize(value)

    var targetSize: Vector2i
        @JvmName("targetSizeProperty")
        get() = getTargetSize()
        @JvmName("setTargetSizeProperty")
        set(value) = setTargetSize(value)

    var viewCount: Long
        @JvmName("viewCountProperty")
        get() = getViewCount()
        @JvmName("setViewCountProperty")
        set(value) = setViewCount(value)

    var scaling3dMode: Long
        @JvmName("scaling3dModeProperty")
        get() = getScaling3dMode()
        @JvmName("setScaling3dModeProperty")
        set(value) = setScaling3dMode(value)

    var msaa3d: Long
        @JvmName("msaa3dProperty")
        get() = getMsaa3d()
        @JvmName("setMsaa3dProperty")
        set(value) = setMsaa3d(value)

    var screenSpaceAa: Long
        @JvmName("screenSpaceAaProperty")
        get() = getScreenSpaceAa()
        @JvmName("setScreenSpaceAaProperty")
        set(value) = setScreenSpaceAa(value)

    var fsrSharpness: Double
        @JvmName("fsrSharpnessProperty")
        get() = getFsrSharpness()
        @JvmName("setFsrSharpnessProperty")
        set(value) = setFsrSharpness(value)

    var textureMipmapBias: Double
        @JvmName("textureMipmapBiasProperty")
        get() = getTextureMipmapBias()
        @JvmName("setTextureMipmapBiasProperty")
        set(value) = setTextureMipmapBias(value)

    var anisotropicFilteringLevel: Long
        @JvmName("anisotropicFilteringLevelProperty")
        get() = getAnisotropicFilteringLevel()
        @JvmName("setAnisotropicFilteringLevelProperty")
        set(value) = setAnisotropicFilteringLevel(value)

    /**
     * The render target associated with these buffer.
     *
     * Generated from Godot docs: RenderSceneBuffersConfiguration.get_render_target
     */
    fun getRenderTarget(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getRenderTargetBind, handle)
    }

    /**
     * The render target associated with these buffer.
     *
     * Generated from Godot docs: RenderSceneBuffersConfiguration.set_render_target
     */
    fun setRenderTarget(renderTarget: RID) {
        ObjectCalls.ptrcallWithRIDArg(setRenderTargetBind, handle, renderTarget)
    }

    /**
     * The size of the 3D render buffer used for rendering.
     *
     * Generated from Godot docs: RenderSceneBuffersConfiguration.get_internal_size
     */
    fun getInternalSize(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(getInternalSizeBind, handle)
    }

    /**
     * The size of the 3D render buffer used for rendering.
     *
     * Generated from Godot docs: RenderSceneBuffersConfiguration.set_internal_size
     */
    fun setInternalSize(internalSize: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(setInternalSizeBind, handle, internalSize)
    }

    /**
     * The target (upscale) size if scaling is used.
     *
     * Generated from Godot docs: RenderSceneBuffersConfiguration.get_target_size
     */
    fun getTargetSize(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(getTargetSizeBind, handle)
    }

    /**
     * The target (upscale) size if scaling is used.
     *
     * Generated from Godot docs: RenderSceneBuffersConfiguration.set_target_size
     */
    fun setTargetSize(targetSize: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(setTargetSizeBind, handle, targetSize)
    }

    /**
     * The number of views we're rendering.
     *
     * Generated from Godot docs: RenderSceneBuffersConfiguration.get_view_count
     */
    fun getViewCount(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getViewCountBind, handle)
    }

    /**
     * The number of views we're rendering.
     *
     * Generated from Godot docs: RenderSceneBuffersConfiguration.set_view_count
     */
    fun setViewCount(viewCount: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setViewCountBind, handle, viewCount)
    }

    /**
     * The requested scaling mode with which we upscale/downscale if `internal_size` and `target_size`
     * are not equal.
     *
     * Generated from Godot docs: RenderSceneBuffersConfiguration.get_scaling_3d_mode
     */
    fun getScaling3dMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getScaling3dModeBind, handle)
    }

    /**
     * The requested scaling mode with which we upscale/downscale if `internal_size` and `target_size`
     * are not equal.
     *
     * Generated from Godot docs: RenderSceneBuffersConfiguration.set_scaling_3d_mode
     */
    fun setScaling3dMode(scaling3dMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setScaling3dModeBind, handle, scaling3dMode)
    }

    /**
     * The MSAA mode we're using for 3D rendering.
     *
     * Generated from Godot docs: RenderSceneBuffersConfiguration.get_msaa_3d
     */
    fun getMsaa3d(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getMsaa3dBind, handle)
    }

    /**
     * The MSAA mode we're using for 3D rendering.
     *
     * Generated from Godot docs: RenderSceneBuffersConfiguration.set_msaa_3d
     */
    fun setMsaa3d(msaa3d: Long) {
        ObjectCalls.ptrcallWithLongArg(setMsaa3dBind, handle, msaa3d)
    }

    /**
     * The requested screen space AA applied in post processing.
     *
     * Generated from Godot docs: RenderSceneBuffersConfiguration.get_screen_space_aa
     */
    fun getScreenSpaceAa(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getScreenSpaceAaBind, handle)
    }

    /**
     * The requested screen space AA applied in post processing.
     *
     * Generated from Godot docs: RenderSceneBuffersConfiguration.set_screen_space_aa
     */
    fun setScreenSpaceAa(screenSpaceAa: Long) {
        ObjectCalls.ptrcallWithLongArg(setScreenSpaceAaBind, handle, screenSpaceAa)
    }

    /**
     * FSR Sharpness applicable if FSR upscaling is used.
     *
     * Generated from Godot docs: RenderSceneBuffersConfiguration.get_fsr_sharpness
     */
    fun getFsrSharpness(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFsrSharpnessBind, handle)
    }

    /**
     * FSR Sharpness applicable if FSR upscaling is used.
     *
     * Generated from Godot docs: RenderSceneBuffersConfiguration.set_fsr_sharpness
     */
    fun setFsrSharpness(fsrSharpness: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFsrSharpnessBind, handle, fsrSharpness)
    }

    /**
     * Bias applied to mipmaps. Note: This property is only supported in the Forward+ and Mobile
     * renderers, not Compatibility. In Compatibility, this property is always treated as if it was set
     * to `0.0`.
     *
     * Generated from Godot docs: RenderSceneBuffersConfiguration.get_texture_mipmap_bias
     */
    fun getTextureMipmapBias(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTextureMipmapBiasBind, handle)
    }

    /**
     * Bias applied to mipmaps. Note: This property is only supported in the Forward+ and Mobile
     * renderers, not Compatibility. In Compatibility, this property is always treated as if it was set
     * to `0.0`.
     *
     * Generated from Godot docs: RenderSceneBuffersConfiguration.set_texture_mipmap_bias
     */
    fun setTextureMipmapBias(textureMipmapBias: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTextureMipmapBiasBind, handle, textureMipmapBias)
    }

    /**
     * Level of the anisotropic filter.
     *
     * Generated from Godot docs: RenderSceneBuffersConfiguration.get_anisotropic_filtering_level
     */
    fun getAnisotropicFilteringLevel(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAnisotropicFilteringLevelBind, handle)
    }

    /**
     * Level of the anisotropic filter.
     *
     * Generated from Godot docs: RenderSceneBuffersConfiguration.set_anisotropic_filtering_level
     */
    fun setAnisotropicFilteringLevel(anisotropicFilteringLevel: Long) {
        ObjectCalls.ptrcallWithLongArg(setAnisotropicFilteringLevelBind, handle, anisotropicFilteringLevel)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): RenderSceneBuffersConfiguration? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RenderSceneBuffersConfiguration? =
            if (handle.address() == 0L) null else RenderSceneBuffersConfiguration(handle)

        private const val GET_RENDER_TARGET_HASH = 2944877500L
        private val getRenderTargetBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersConfiguration", "get_render_target", GET_RENDER_TARGET_HASH)
        }

        private const val SET_RENDER_TARGET_HASH = 2722037293L
        private val setRenderTargetBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersConfiguration", "set_render_target", SET_RENDER_TARGET_HASH)
        }

        private const val GET_INTERNAL_SIZE_HASH = 3690982128L
        private val getInternalSizeBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersConfiguration", "get_internal_size", GET_INTERNAL_SIZE_HASH)
        }

        private const val SET_INTERNAL_SIZE_HASH = 1130785943L
        private val setInternalSizeBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersConfiguration", "set_internal_size", SET_INTERNAL_SIZE_HASH)
        }

        private const val GET_TARGET_SIZE_HASH = 3690982128L
        private val getTargetSizeBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersConfiguration", "get_target_size", GET_TARGET_SIZE_HASH)
        }

        private const val SET_TARGET_SIZE_HASH = 1130785943L
        private val setTargetSizeBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersConfiguration", "set_target_size", SET_TARGET_SIZE_HASH)
        }

        private const val GET_VIEW_COUNT_HASH = 3905245786L
        private val getViewCountBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersConfiguration", "get_view_count", GET_VIEW_COUNT_HASH)
        }

        private const val SET_VIEW_COUNT_HASH = 1286410249L
        private val setViewCountBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersConfiguration", "set_view_count", SET_VIEW_COUNT_HASH)
        }

        private const val GET_SCALING_3D_MODE_HASH = 976778074L
        private val getScaling3dModeBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersConfiguration", "get_scaling_3d_mode", GET_SCALING_3D_MODE_HASH)
        }

        private const val SET_SCALING_3D_MODE_HASH = 447477857L
        private val setScaling3dModeBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersConfiguration", "set_scaling_3d_mode", SET_SCALING_3D_MODE_HASH)
        }

        private const val GET_MSAA_3D_HASH = 3109158617L
        private val getMsaa3dBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersConfiguration", "get_msaa_3d", GET_MSAA_3D_HASH)
        }

        private const val SET_MSAA_3D_HASH = 3952630748L
        private val setMsaa3dBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersConfiguration", "set_msaa_3d", SET_MSAA_3D_HASH)
        }

        private const val GET_SCREEN_SPACE_AA_HASH = 641513172L
        private val getScreenSpaceAaBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersConfiguration", "get_screen_space_aa", GET_SCREEN_SPACE_AA_HASH)
        }

        private const val SET_SCREEN_SPACE_AA_HASH = 139543108L
        private val setScreenSpaceAaBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersConfiguration", "set_screen_space_aa", SET_SCREEN_SPACE_AA_HASH)
        }

        private const val GET_FSR_SHARPNESS_HASH = 1740695150L
        private val getFsrSharpnessBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersConfiguration", "get_fsr_sharpness", GET_FSR_SHARPNESS_HASH)
        }

        private const val SET_FSR_SHARPNESS_HASH = 373806689L
        private val setFsrSharpnessBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersConfiguration", "set_fsr_sharpness", SET_FSR_SHARPNESS_HASH)
        }

        private const val GET_TEXTURE_MIPMAP_BIAS_HASH = 1740695150L
        private val getTextureMipmapBiasBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersConfiguration", "get_texture_mipmap_bias", GET_TEXTURE_MIPMAP_BIAS_HASH)
        }

        private const val SET_TEXTURE_MIPMAP_BIAS_HASH = 373806689L
        private val setTextureMipmapBiasBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersConfiguration", "set_texture_mipmap_bias", SET_TEXTURE_MIPMAP_BIAS_HASH)
        }

        private const val GET_ANISOTROPIC_FILTERING_LEVEL_HASH = 1617414954L
        private val getAnisotropicFilteringLevelBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersConfiguration", "get_anisotropic_filtering_level", GET_ANISOTROPIC_FILTERING_LEVEL_HASH)
        }

        private const val SET_ANISOTROPIC_FILTERING_LEVEL_HASH = 2559658741L
        private val setAnisotropicFilteringLevelBind by lazy {
            ObjectCalls.getMethodBind("RenderSceneBuffersConfiguration", "set_anisotropic_filtering_level", SET_ANISOTROPIC_FILTERING_LEVEL_HASH)
        }
    }
}
