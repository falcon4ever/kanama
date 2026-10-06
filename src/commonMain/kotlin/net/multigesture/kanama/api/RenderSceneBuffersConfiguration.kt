package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector2i

/**
 * Configuration object used to setup a `RenderSceneBuffers` object.
 *
 * Generated from Godot docs: RenderSceneBuffersConfiguration
 */
class RenderSceneBuffersConfiguration(handle: GodotHandle) : RefCounted(handle) {
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

    var scaling3dMode: RenderingServer.ViewportScaling3DMode
        @JvmName("scaling3dModeProperty")
        get() = getScaling3dMode()
        @JvmName("setScaling3dModeProperty")
        set(value) = setScaling3dMode(value)

    var msaa3d: RenderingServer.ViewportMSAA
        @JvmName("msaa3dProperty")
        get() = getMsaa3d()
        @JvmName("setMsaa3dProperty")
        set(value) = setMsaa3d(value)

    var screenSpaceAa: RenderingServer.ViewportScreenSpaceAA
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

    var anisotropicFilteringLevel: RenderingServer.ViewportAnisotropicFiltering
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
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetRID(Binds.getRenderTargetBind, segment)
    }

    /**
     * The render target associated with these buffer.
     *
     * Generated from Godot docs: RenderSceneBuffersConfiguration.set_render_target
     */
    fun setRenderTarget(renderTarget: RID) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDArg(Binds.setRenderTargetBind, segment, renderTarget)
    }

    /**
     * The size of the 3D render buffer used for rendering.
     *
     * Generated from Godot docs: RenderSceneBuffersConfiguration.get_internal_size
     */
    fun getInternalSize(): Vector2i {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2i(Binds.getInternalSizeBind, segment)
    }

    /**
     * The size of the 3D render buffer used for rendering.
     *
     * Generated from Godot docs: RenderSceneBuffersConfiguration.set_internal_size
     */
    fun setInternalSize(internalSize: Vector2i) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2iArg(Binds.setInternalSizeBind, segment, internalSize)
    }

    /**
     * The target (upscale) size if scaling is used.
     *
     * Generated from Godot docs: RenderSceneBuffersConfiguration.get_target_size
     */
    fun getTargetSize(): Vector2i {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2i(Binds.getTargetSizeBind, segment)
    }

    /**
     * The target (upscale) size if scaling is used.
     *
     * Generated from Godot docs: RenderSceneBuffersConfiguration.set_target_size
     */
    fun setTargetSize(targetSize: Vector2i) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2iArg(Binds.setTargetSizeBind, segment, targetSize)
    }

    /**
     * The number of views we're rendering.
     *
     * Generated from Godot docs: RenderSceneBuffersConfiguration.get_view_count
     */
    fun getViewCount(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getViewCountBind, segment)
    }

    /**
     * The number of views we're rendering.
     *
     * Generated from Godot docs: RenderSceneBuffersConfiguration.set_view_count
     */
    fun setViewCount(viewCount: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setViewCountBind, segment, viewCount)
    }

    /**
     * The requested scaling mode with which we upscale/downscale if `internal_size` and `target_size`
     * are not equal.
     *
     * Generated from Godot docs: RenderSceneBuffersConfiguration.get_scaling_3d_mode
     */
    fun getScaling3dMode(): RenderingServer.ViewportScaling3DMode {
        checkOpen()
        return RenderingServer.ViewportScaling3DMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getScaling3dModeBind, segment))
    }

    /**
     * The requested scaling mode with which we upscale/downscale if `internal_size` and `target_size`
     * are not equal.
     *
     * Generated from Godot docs: RenderSceneBuffersConfiguration.set_scaling_3d_mode
     */
    fun setScaling3dMode(scaling3dMode: RenderingServer.ViewportScaling3DMode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setScaling3dModeBind, segment, scaling3dMode.value)
    }

    /**
     * The MSAA mode we're using for 3D rendering.
     *
     * Generated from Godot docs: RenderSceneBuffersConfiguration.get_msaa_3d
     */
    fun getMsaa3d(): RenderingServer.ViewportMSAA {
        checkOpen()
        return RenderingServer.ViewportMSAA(ObjectCalls.ptrcallNoArgsRetLong(Binds.getMsaa3dBind, segment))
    }

    /**
     * The MSAA mode we're using for 3D rendering.
     *
     * Generated from Godot docs: RenderSceneBuffersConfiguration.set_msaa_3d
     */
    fun setMsaa3d(msaa3d: RenderingServer.ViewportMSAA) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setMsaa3dBind, segment, msaa3d.value)
    }

    /**
     * The requested screen space AA applied in post processing.
     *
     * Generated from Godot docs: RenderSceneBuffersConfiguration.get_screen_space_aa
     */
    fun getScreenSpaceAa(): RenderingServer.ViewportScreenSpaceAA {
        checkOpen()
        return RenderingServer.ViewportScreenSpaceAA(ObjectCalls.ptrcallNoArgsRetLong(Binds.getScreenSpaceAaBind, segment))
    }

    /**
     * The requested screen space AA applied in post processing.
     *
     * Generated from Godot docs: RenderSceneBuffersConfiguration.set_screen_space_aa
     */
    fun setScreenSpaceAa(screenSpaceAa: RenderingServer.ViewportScreenSpaceAA) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setScreenSpaceAaBind, segment, screenSpaceAa.value)
    }

    /**
     * FSR Sharpness applicable if FSR upscaling is used.
     *
     * Generated from Godot docs: RenderSceneBuffersConfiguration.get_fsr_sharpness
     */
    fun getFsrSharpness(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getFsrSharpnessBind, segment)
    }

    /**
     * FSR Sharpness applicable if FSR upscaling is used.
     *
     * Generated from Godot docs: RenderSceneBuffersConfiguration.set_fsr_sharpness
     */
    fun setFsrSharpness(fsrSharpness: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setFsrSharpnessBind, segment, fsrSharpness)
    }

    /**
     * Bias applied to mipmaps. Note: This property is only supported in the Forward+ and Mobile
     * renderers, not Compatibility. In Compatibility, this property is always treated as if it was set
     * to `0.0`.
     *
     * Generated from Godot docs: RenderSceneBuffersConfiguration.get_texture_mipmap_bias
     */
    fun getTextureMipmapBias(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getTextureMipmapBiasBind, segment)
    }

    /**
     * Bias applied to mipmaps. Note: This property is only supported in the Forward+ and Mobile
     * renderers, not Compatibility. In Compatibility, this property is always treated as if it was set
     * to `0.0`.
     *
     * Generated from Godot docs: RenderSceneBuffersConfiguration.set_texture_mipmap_bias
     */
    fun setTextureMipmapBias(textureMipmapBias: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setTextureMipmapBiasBind, segment, textureMipmapBias)
    }

    /**
     * Level of the anisotropic filter.
     *
     * Generated from Godot docs: RenderSceneBuffersConfiguration.get_anisotropic_filtering_level
     */
    fun getAnisotropicFilteringLevel(): RenderingServer.ViewportAnisotropicFiltering {
        checkOpen()
        return RenderingServer.ViewportAnisotropicFiltering(ObjectCalls.ptrcallNoArgsRetLong(Binds.getAnisotropicFilteringLevelBind, segment))
    }

    /**
     * Level of the anisotropic filter.
     *
     * Generated from Godot docs: RenderSceneBuffersConfiguration.set_anisotropic_filtering_level
     */
    fun setAnisotropicFilteringLevel(anisotropicFilteringLevel: RenderingServer.ViewportAnisotropicFiltering) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setAnisotropicFilteringLevelBind, segment, anisotropicFilteringLevel.value)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): RenderSceneBuffersConfiguration? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): RenderSceneBuffersConfiguration? =
            if (handle.address() == 0L) null else RefCounted.owned(RenderSceneBuffersConfiguration(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): RenderSceneBuffersConfiguration? =
            if (handle.address() == 0L) null else RenderSceneBuffersConfiguration(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_RENDER_TARGET_HASH = 2944877500L
        @JvmField
        val getRenderTargetBind =
            ObjectCalls.getMethodBind("RenderSceneBuffersConfiguration", "get_render_target", GET_RENDER_TARGET_HASH)

        private const val SET_RENDER_TARGET_HASH = 2722037293L
        @JvmField
        val setRenderTargetBind =
            ObjectCalls.getMethodBind("RenderSceneBuffersConfiguration", "set_render_target", SET_RENDER_TARGET_HASH)

        private const val GET_INTERNAL_SIZE_HASH = 3690982128L
        @JvmField
        val getInternalSizeBind =
            ObjectCalls.getMethodBind("RenderSceneBuffersConfiguration", "get_internal_size", GET_INTERNAL_SIZE_HASH)

        private const val SET_INTERNAL_SIZE_HASH = 1130785943L
        @JvmField
        val setInternalSizeBind =
            ObjectCalls.getMethodBind("RenderSceneBuffersConfiguration", "set_internal_size", SET_INTERNAL_SIZE_HASH)

        private const val GET_TARGET_SIZE_HASH = 3690982128L
        @JvmField
        val getTargetSizeBind =
            ObjectCalls.getMethodBind("RenderSceneBuffersConfiguration", "get_target_size", GET_TARGET_SIZE_HASH)

        private const val SET_TARGET_SIZE_HASH = 1130785943L
        @JvmField
        val setTargetSizeBind =
            ObjectCalls.getMethodBind("RenderSceneBuffersConfiguration", "set_target_size", SET_TARGET_SIZE_HASH)

        private const val GET_VIEW_COUNT_HASH = 3905245786L
        @JvmField
        val getViewCountBind =
            ObjectCalls.getMethodBind("RenderSceneBuffersConfiguration", "get_view_count", GET_VIEW_COUNT_HASH)

        private const val SET_VIEW_COUNT_HASH = 1286410249L
        @JvmField
        val setViewCountBind =
            ObjectCalls.getMethodBind("RenderSceneBuffersConfiguration", "set_view_count", SET_VIEW_COUNT_HASH)

        private const val GET_SCALING_3D_MODE_HASH = 976778074L
        @JvmField
        val getScaling3dModeBind =
            ObjectCalls.getMethodBind("RenderSceneBuffersConfiguration", "get_scaling_3d_mode", GET_SCALING_3D_MODE_HASH)

        private const val SET_SCALING_3D_MODE_HASH = 447477857L
        @JvmField
        val setScaling3dModeBind =
            ObjectCalls.getMethodBind("RenderSceneBuffersConfiguration", "set_scaling_3d_mode", SET_SCALING_3D_MODE_HASH)

        private const val GET_MSAA_3D_HASH = 3109158617L
        @JvmField
        val getMsaa3dBind =
            ObjectCalls.getMethodBind("RenderSceneBuffersConfiguration", "get_msaa_3d", GET_MSAA_3D_HASH)

        private const val SET_MSAA_3D_HASH = 3952630748L
        @JvmField
        val setMsaa3dBind =
            ObjectCalls.getMethodBind("RenderSceneBuffersConfiguration", "set_msaa_3d", SET_MSAA_3D_HASH)

        private const val GET_SCREEN_SPACE_AA_HASH = 641513172L
        @JvmField
        val getScreenSpaceAaBind =
            ObjectCalls.getMethodBind("RenderSceneBuffersConfiguration", "get_screen_space_aa", GET_SCREEN_SPACE_AA_HASH)

        private const val SET_SCREEN_SPACE_AA_HASH = 139543108L
        @JvmField
        val setScreenSpaceAaBind =
            ObjectCalls.getMethodBind("RenderSceneBuffersConfiguration", "set_screen_space_aa", SET_SCREEN_SPACE_AA_HASH)

        private const val GET_FSR_SHARPNESS_HASH = 1740695150L
        @JvmField
        val getFsrSharpnessBind =
            ObjectCalls.getMethodBind("RenderSceneBuffersConfiguration", "get_fsr_sharpness", GET_FSR_SHARPNESS_HASH)

        private const val SET_FSR_SHARPNESS_HASH = 373806689L
        @JvmField
        val setFsrSharpnessBind =
            ObjectCalls.getMethodBind("RenderSceneBuffersConfiguration", "set_fsr_sharpness", SET_FSR_SHARPNESS_HASH)

        private const val GET_TEXTURE_MIPMAP_BIAS_HASH = 1740695150L
        @JvmField
        val getTextureMipmapBiasBind =
            ObjectCalls.getMethodBind("RenderSceneBuffersConfiguration", "get_texture_mipmap_bias", GET_TEXTURE_MIPMAP_BIAS_HASH)

        private const val SET_TEXTURE_MIPMAP_BIAS_HASH = 373806689L
        @JvmField
        val setTextureMipmapBiasBind =
            ObjectCalls.getMethodBind("RenderSceneBuffersConfiguration", "set_texture_mipmap_bias", SET_TEXTURE_MIPMAP_BIAS_HASH)

        private const val GET_ANISOTROPIC_FILTERING_LEVEL_HASH = 1617414954L
        @JvmField
        val getAnisotropicFilteringLevelBind =
            ObjectCalls.getMethodBind("RenderSceneBuffersConfiguration", "get_anisotropic_filtering_level", GET_ANISOTROPIC_FILTERING_LEVEL_HASH)

        private const val SET_ANISOTROPIC_FILTERING_LEVEL_HASH = 2559658741L
        @JvmField
        val setAnisotropicFilteringLevelBind =
            ObjectCalls.getMethodBind("RenderSceneBuffersConfiguration", "set_anisotropic_filtering_level", SET_ANISOTROPIC_FILTERING_LEVEL_HASH)
    }
}
