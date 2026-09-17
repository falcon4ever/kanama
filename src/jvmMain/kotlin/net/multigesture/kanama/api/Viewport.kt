package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Transform2D
import net.multigesture.kanama.types.Vector2
import kotlin.jvm.JvmName

/**
 * Abstract base class for viewports. Encapsulates drawing and interaction with a game world.
 *
 * Generated from Godot docs: Viewport
 */
open class Viewport(handle: GodotHandle) : Node(handle) {
    var disable3d: Boolean
        @JvmName("disable3dProperty")
        get() = is3dDisabled()
        @JvmName("setDisable3dProperty")
        set(value) = setDisable3d(value)

    var useXr: Boolean
        @JvmName("useXrProperty")
        get() = isUsingXr()
        @JvmName("setUseXrProperty")
        set(value) = setUseXr(value)

    var ownWorld3d: Boolean
        @JvmName("ownWorld3dProperty")
        get() = isUsingOwnWorld3d()
        @JvmName("setOwnWorld3dProperty")
        set(value) = setUseOwnWorld3d(value)

    var world3d: World3D?
        @JvmName("world3dProperty")
        get() = getWorld3d()
        @JvmName("setWorld3dProperty")
        set(value) = setWorld3d(value)

    var world2d: World2D?
        @JvmName("world2dProperty")
        get() = getWorld2d()
        @JvmName("setWorld2dProperty")
        set(value) = setWorld2d(value)

    var transparentBg: Boolean
        @JvmName("transparentBgProperty")
        get() = hasTransparentBackground()
        @JvmName("setTransparentBgProperty")
        set(value) = setTransparentBackground(value)

    var handleInputLocally: Boolean
        @JvmName("handleInputLocallyProperty")
        get() = isHandlingInputLocally()
        @JvmName("setHandleInputLocallyProperty")
        set(value) = setHandleInputLocally(value)

    var snap2dTransformsToPixel: Boolean
        @JvmName("snap2dTransformsToPixelProperty")
        get() = isSnap2dTransformsToPixelEnabled()
        @JvmName("setSnap2dTransformsToPixelProperty")
        set(value) = setSnap2dTransformsToPixel(value)

    var snap2dVerticesToPixel: Boolean
        @JvmName("snap2dVerticesToPixelProperty")
        get() = isSnap2dVerticesToPixelEnabled()
        @JvmName("setSnap2dVerticesToPixelProperty")
        set(value) = setSnap2dVerticesToPixel(value)

    var msaa2d: Long
        @JvmName("msaa2dProperty")
        get() = getMsaa2d()
        @JvmName("setMsaa2dProperty")
        set(value) = setMsaa2d(value)

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

    var useTaa: Boolean
        @JvmName("useTaaProperty")
        get() = isUsingTaa()
        @JvmName("setUseTaaProperty")
        set(value) = setUseTaa(value)

    var useDebanding: Boolean
        @JvmName("useDebandingProperty")
        get() = isUsingDebanding()
        @JvmName("setUseDebandingProperty")
        set(value) = setUseDebanding(value)

    var useOcclusionCulling: Boolean
        @JvmName("useOcclusionCullingProperty")
        get() = isUsingOcclusionCulling()
        @JvmName("setUseOcclusionCullingProperty")
        set(value) = setUseOcclusionCulling(value)

    var meshLodThreshold: Double
        @JvmName("meshLodThresholdProperty")
        get() = getMeshLodThreshold()
        @JvmName("setMeshLodThresholdProperty")
        set(value) = setMeshLodThreshold(value)

    var debugDraw: Long
        @JvmName("debugDrawProperty")
        get() = getDebugDraw()
        @JvmName("setDebugDrawProperty")
        set(value) = setDebugDraw(value)

    var useHdr2d: Boolean
        @JvmName("useHdr2dProperty")
        get() = isUsingHdr2d()
        @JvmName("setUseHdr2dProperty")
        set(value) = setUseHdr2d(value)

    var scaling3dMode: Long
        @JvmName("scaling3dModeProperty")
        get() = getScaling3dMode()
        @JvmName("setScaling3dModeProperty")
        set(value) = setScaling3dMode(value)

    var scaling3dScale: Double
        @JvmName("scaling3dScaleProperty")
        get() = getScaling3dScale()
        @JvmName("setScaling3dScaleProperty")
        set(value) = setScaling3dScale(value)

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

    var fsrSharpness: Double
        @JvmName("fsrSharpnessProperty")
        get() = getFsrSharpness()
        @JvmName("setFsrSharpnessProperty")
        set(value) = setFsrSharpness(value)

    var vrsMode: Long
        @JvmName("vrsModeProperty")
        get() = getVrsMode()
        @JvmName("setVrsModeProperty")
        set(value) = setVrsMode(value)

    var vrsUpdateMode: Long
        @JvmName("vrsUpdateModeProperty")
        get() = getVrsUpdateMode()
        @JvmName("setVrsUpdateModeProperty")
        set(value) = setVrsUpdateMode(value)

    var vrsTexture: Texture2D?
        @JvmName("vrsTextureProperty")
        get() = getVrsTexture()
        @JvmName("setVrsTextureProperty")
        set(value) = setVrsTexture(value)

    var canvasItemDefaultTextureFilter: Long
        @JvmName("canvasItemDefaultTextureFilterProperty")
        get() = getDefaultCanvasItemTextureFilter()
        @JvmName("setCanvasItemDefaultTextureFilterProperty")
        set(value) = setDefaultCanvasItemTextureFilter(value)

    var canvasItemDefaultTextureRepeat: Long
        @JvmName("canvasItemDefaultTextureRepeatProperty")
        get() = getDefaultCanvasItemTextureRepeat()
        @JvmName("setCanvasItemDefaultTextureRepeatProperty")
        set(value) = setDefaultCanvasItemTextureRepeat(value)

    var audioListenerEnable2d: Boolean
        @JvmName("audioListenerEnable2dProperty")
        get() = isAudioListener2d()
        @JvmName("setAudioListenerEnable2dProperty")
        set(value) = setAsAudioListener2d(value)

    var audioListenerEnable3d: Boolean
        @JvmName("audioListenerEnable3dProperty")
        get() = isAudioListener3d()
        @JvmName("setAudioListenerEnable3dProperty")
        set(value) = setAsAudioListener3d(value)

    var physicsObjectPicking: Boolean
        @JvmName("physicsObjectPickingProperty")
        get() = getPhysicsObjectPicking()
        @JvmName("setPhysicsObjectPickingProperty")
        set(value) = setPhysicsObjectPicking(value)

    var physicsObjectPickingSort: Boolean
        @JvmName("physicsObjectPickingSortProperty")
        get() = getPhysicsObjectPickingSort()
        @JvmName("setPhysicsObjectPickingSortProperty")
        set(value) = setPhysicsObjectPickingSort(value)

    var physicsObjectPickingFirstOnly: Boolean
        @JvmName("physicsObjectPickingFirstOnlyProperty")
        get() = getPhysicsObjectPickingFirstOnly()
        @JvmName("setPhysicsObjectPickingFirstOnlyProperty")
        set(value) = setPhysicsObjectPickingFirstOnly(value)

    var guiDisableInput: Boolean
        @JvmName("guiDisableInputProperty")
        get() = isInputDisabled()
        @JvmName("setGuiDisableInputProperty")
        set(value) = setDisableInput(value)

    var guiSnapControlsToPixels: Boolean
        @JvmName("guiSnapControlsToPixelsProperty")
        get() = isSnapControlsToPixelsEnabled()
        @JvmName("setGuiSnapControlsToPixelsProperty")
        set(value) = setSnapControlsToPixels(value)

    var guiEmbedSubwindows: Boolean
        @JvmName("guiEmbedSubwindowsProperty")
        get() = isEmbeddingSubwindows()
        @JvmName("setGuiEmbedSubwindowsProperty")
        set(value) = setEmbeddingSubwindows(value)

    var guiDragThreshold: Int
        @JvmName("guiDragThresholdProperty")
        get() = getDragThreshold()
        @JvmName("setGuiDragThresholdProperty")
        set(value) = setDragThreshold(value)

    var sdfOversize: Long
        @JvmName("sdfOversizeProperty")
        get() = getSdfOversize()
        @JvmName("setSdfOversizeProperty")
        set(value) = setSdfOversize(value)

    var sdfScale: Long
        @JvmName("sdfScaleProperty")
        get() = getSdfScale()
        @JvmName("setSdfScaleProperty")
        set(value) = setSdfScale(value)

    var positionalShadowAtlasSize: Int
        @JvmName("positionalShadowAtlasSizeProperty")
        get() = getPositionalShadowAtlasSize()
        @JvmName("setPositionalShadowAtlasSizeProperty")
        set(value) = setPositionalShadowAtlasSize(value)

    var positionalShadowAtlas16Bits: Boolean
        @JvmName("positionalShadowAtlas16BitsProperty")
        get() = getPositionalShadowAtlas16Bits()
        @JvmName("setPositionalShadowAtlas16BitsProperty")
        set(value) = setPositionalShadowAtlas16Bits(value)

    var canvasTransform: Transform2D
        @JvmName("canvasTransformProperty")
        get() = getCanvasTransform()
        @JvmName("setCanvasTransformProperty")
        set(value) = setCanvasTransform(value)

    var globalCanvasTransform: Transform2D
        @JvmName("globalCanvasTransformProperty")
        get() = getGlobalCanvasTransform()
        @JvmName("setGlobalCanvasTransformProperty")
        set(value) = setGlobalCanvasTransform(value)

    var canvasCullMask: Long
        @JvmName("canvasCullMaskProperty")
        get() = getCanvasCullMask()
        @JvmName("setCanvasCullMaskProperty")
        set(value) = setCanvasCullMask(value)

    var oversampling: Boolean
        @JvmName("oversamplingProperty")
        get() = isUsingOversampling()
        @JvmName("setOversamplingProperty")
        set(value) = setUseOversampling(value)

    var oversamplingOverride: Double
        @JvmName("oversamplingOverrideProperty")
        get() = getOversamplingOverride()
        @JvmName("setOversamplingOverrideProperty")
        set(value) = setOversamplingOverride(value)

    /**
     * The custom `World2D` which can be used as 2D environment source.
     *
     * Generated from Godot docs: Viewport.set_world_2d
     */
    fun setWorld2d(world2d: World2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setWorld2dBind, segment, listOf(world2d?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The custom `World2D` which can be used as 2D environment source.
     *
     * Generated from Godot docs: Viewport.get_world_2d
     */
    fun getWorld2d(): World2D? {
        return World2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getWorld2dBind, segment))
    }

    /**
     * Returns the first valid `World2D` for this viewport, searching the `world_2d` property of itself
     * and any Viewport ancestor.
     *
     * Generated from Godot docs: Viewport.find_world_2d
     */
    fun findWorld2d(): World2D? {
        return World2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(findWorld2dBind, segment))
    }

    /**
     * The canvas transform of the viewport, useful for changing the on-screen positions of all child
     * `CanvasItem`s. This is relative to the global canvas transform of the viewport.
     *
     * Generated from Godot docs: Viewport.set_canvas_transform
     */
    fun setCanvasTransform(xform: Transform2D) {
        ObjectCalls.ptrcallWithTransform2DArg(setCanvasTransformBind, segment, xform)
    }

    /**
     * The canvas transform of the viewport, useful for changing the on-screen positions of all child
     * `CanvasItem`s. This is relative to the global canvas transform of the viewport.
     *
     * Generated from Godot docs: Viewport.get_canvas_transform
     */
    fun getCanvasTransform(): Transform2D {
        return ObjectCalls.ptrcallNoArgsRetTransform2D(getCanvasTransformBind, segment)
    }

    /**
     * The global canvas transform of the viewport. The canvas transform is relative to this.
     *
     * Generated from Godot docs: Viewport.set_global_canvas_transform
     */
    fun setGlobalCanvasTransform(xform: Transform2D) {
        ObjectCalls.ptrcallWithTransform2DArg(setGlobalCanvasTransformBind, segment, xform)
    }

    /**
     * The global canvas transform of the viewport. The canvas transform is relative to this.
     *
     * Generated from Godot docs: Viewport.get_global_canvas_transform
     */
    fun getGlobalCanvasTransform(): Transform2D {
        return ObjectCalls.ptrcallNoArgsRetTransform2D(getGlobalCanvasTransformBind, segment)
    }

    /**
     * Returns the automatically computed 2D stretch transform, taking the `Viewport`'s stretch
     * settings into account. The final value is multiplied by `Window.content_scale_factor`, but only
     * for the root viewport. If this method is called on a `SubViewport` (e.g., in a scene tree with
     * `SubViewportContainer` and `SubViewport`), the scale factor of the root window will not be
     * applied. Using `Transform2D.get_scale` on the returned value, this can be used to compensate for
     * scaling when zooming a `Camera2D` node, or to scale down a `TextureRect` to be pixel-perfect
     * regardless of the automatically computed scale factor. Note: Due to how pixel scaling works, the
     * returned transform's X and Y scale may differ slightly, even when `Window.content_scale_aspect`
     * is set to a mode that preserves the pixels' aspect ratio. If `Window.content_scale_aspect` is
     * `Window.CONTENT_SCALE_ASPECT_IGNORE`, the X and Y scale may differ significantly.
     *
     * Generated from Godot docs: Viewport.get_stretch_transform
     */
    fun getStretchTransform(): Transform2D {
        return ObjectCalls.ptrcallNoArgsRetTransform2D(getStretchTransformBind, segment)
    }

    /**
     * Returns the transform from the viewport's coordinate system to the embedder's coordinate system.
     *
     * Generated from Godot docs: Viewport.get_final_transform
     */
    fun getFinalTransform(): Transform2D {
        return ObjectCalls.ptrcallNoArgsRetTransform2D(getFinalTransformBind, segment)
    }

    /**
     * Returns the transform from the Viewport's coordinates to the screen coordinates of the
     * containing window manager window.
     *
     * Generated from Godot docs: Viewport.get_screen_transform
     */
    fun getScreenTransform(): Transform2D {
        return ObjectCalls.ptrcallNoArgsRetTransform2D(getScreenTransformBind, segment)
    }

    /**
     * Returns the visible rectangle in global screen coordinates.
     *
     * Generated from Godot docs: Viewport.get_visible_rect
     */
    fun getVisibleRect(): Rect2 {
        return ObjectCalls.ptrcallNoArgsRetRect2(getVisibleRectBind, segment)
    }

    /**
     * If `true`, the viewport should render its background as transparent. Note: Due to technical
     * limitations, certain rendering features are disabled when a viewport has a transparent
     * background. This currently applies to screen-space reflections, subsurface scattering, and depth
     * of field.
     *
     * Generated from Godot docs: Viewport.set_transparent_background
     */
    fun setTransparentBackground(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setTransparentBackgroundBind, segment, enable)
    }

    /**
     * If `true`, the viewport should render its background as transparent. Note: Due to technical
     * limitations, certain rendering features are disabled when a viewport has a transparent
     * background. This currently applies to screen-space reflections, subsurface scattering, and depth
     * of field.
     *
     * Generated from Godot docs: Viewport.has_transparent_background
     */
    fun hasTransparentBackground(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasTransparentBackgroundBind, segment)
    }

    /**
     * If `true`, 2D rendering will use a high dynamic range (HDR) `RGBA16` format framebuffer.
     * Additionally, 2D rendering will be performed on linear values and will be converted using the
     * appropriate transfer function immediately before blitting to the screen (if the Viewport is
     * attached to the screen). Practically speaking, this means that the end result of the Viewport
     * will not be clamped to the `0-1` range and can be used in 3D rendering without color encoding
     * adjustments. This allows 2D rendering to take advantage of effects requiring high dynamic range
     * (e.g. 2D glow) as well as substantially improves the appearance of effects requiring highly
     * detailed gradients.
     *
     * Generated from Godot docs: Viewport.set_use_hdr_2d
     */
    fun setUseHdr2d(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseHdr2dBind, segment, enable)
    }

    /**
     * If `true`, 2D rendering will use a high dynamic range (HDR) `RGBA16` format framebuffer.
     * Additionally, 2D rendering will be performed on linear values and will be converted using the
     * appropriate transfer function immediately before blitting to the screen (if the Viewport is
     * attached to the screen). Practically speaking, this means that the end result of the Viewport
     * will not be clamped to the `0-1` range and can be used in 3D rendering without color encoding
     * adjustments. This allows 2D rendering to take advantage of effects requiring high dynamic range
     * (e.g. 2D glow) as well as substantially improves the appearance of effects requiring highly
     * detailed gradients.
     *
     * Generated from Godot docs: Viewport.is_using_hdr_2d
     */
    fun isUsingHdr2d(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingHdr2dBind, segment)
    }

    /**
     * The multisample antialiasing mode for 2D/Canvas rendering. A higher number results in smoother
     * edges at the cost of significantly worse performance. A value of `Viewport.MSAA_2X` or
     * `Viewport.MSAA_4X` is best unless targeting very high-end systems. This has no effect on
     * shader-induced aliasing or texture aliasing. See also
     * `ProjectSettings.rendering/anti_aliasing/quality/msaa_2d` and
     * `RenderingServer.viewport_set_msaa_2d`.
     *
     * Generated from Godot docs: Viewport.set_msaa_2d
     */
    fun setMsaa2d(msaa: Long) {
        ObjectCalls.ptrcallWithLongArg(setMsaa2dBind, segment, msaa)
    }

    /**
     * The multisample antialiasing mode for 2D/Canvas rendering. A higher number results in smoother
     * edges at the cost of significantly worse performance. A value of `Viewport.MSAA_2X` or
     * `Viewport.MSAA_4X` is best unless targeting very high-end systems. This has no effect on
     * shader-induced aliasing or texture aliasing. See also
     * `ProjectSettings.rendering/anti_aliasing/quality/msaa_2d` and
     * `RenderingServer.viewport_set_msaa_2d`.
     *
     * Generated from Godot docs: Viewport.get_msaa_2d
     */
    fun getMsaa2d(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getMsaa2dBind, segment)
    }

    /**
     * The multisample antialiasing mode for 3D rendering. A higher number results in smoother edges at
     * the cost of significantly worse performance. A value of `Viewport.MSAA_2X` or `Viewport.MSAA_4X`
     * is best unless targeting very high-end systems. See also bilinear scaling 3D `scaling_3d_mode`
     * for supersampling, which provides higher quality but is much more expensive. This has no effect
     * on shader-induced aliasing or texture aliasing. See also
     * `ProjectSettings.rendering/anti_aliasing/quality/msaa_3d` and
     * `RenderingServer.viewport_set_msaa_3d`.
     *
     * Generated from Godot docs: Viewport.set_msaa_3d
     */
    fun setMsaa3d(msaa: Long) {
        ObjectCalls.ptrcallWithLongArg(setMsaa3dBind, segment, msaa)
    }

    /**
     * The multisample antialiasing mode for 3D rendering. A higher number results in smoother edges at
     * the cost of significantly worse performance. A value of `Viewport.MSAA_2X` or `Viewport.MSAA_4X`
     * is best unless targeting very high-end systems. See also bilinear scaling 3D `scaling_3d_mode`
     * for supersampling, which provides higher quality but is much more expensive. This has no effect
     * on shader-induced aliasing or texture aliasing. See also
     * `ProjectSettings.rendering/anti_aliasing/quality/msaa_3d` and
     * `RenderingServer.viewport_set_msaa_3d`.
     *
     * Generated from Godot docs: Viewport.get_msaa_3d
     */
    fun getMsaa3d(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getMsaa3dBind, segment)
    }

    /**
     * Sets the screen-space antialiasing method used. Screen-space antialiasing works by selectively
     * blurring edges in a post-process shader. It differs from MSAA which takes multiple coverage
     * samples while rendering objects. Screen-space AA methods are typically faster than MSAA and will
     * smooth out specular aliasing, but tend to make scenes appear blurry. See also
     * `ProjectSettings.rendering/anti_aliasing/quality/screen_space_aa` and
     * `RenderingServer.viewport_set_screen_space_aa`.
     *
     * Generated from Godot docs: Viewport.set_screen_space_aa
     */
    fun setScreenSpaceAa(screenSpaceAa: Long) {
        ObjectCalls.ptrcallWithLongArg(setScreenSpaceAaBind, segment, screenSpaceAa)
    }

    /**
     * Sets the screen-space antialiasing method used. Screen-space antialiasing works by selectively
     * blurring edges in a post-process shader. It differs from MSAA which takes multiple coverage
     * samples while rendering objects. Screen-space AA methods are typically faster than MSAA and will
     * smooth out specular aliasing, but tend to make scenes appear blurry. See also
     * `ProjectSettings.rendering/anti_aliasing/quality/screen_space_aa` and
     * `RenderingServer.viewport_set_screen_space_aa`.
     *
     * Generated from Godot docs: Viewport.get_screen_space_aa
     */
    fun getScreenSpaceAa(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getScreenSpaceAaBind, segment)
    }

    /**
     * Enables temporal antialiasing for this viewport. TAA works by jittering the camera and
     * accumulating the images of the last rendered frames, motion vector rendering is used to account
     * for camera and object motion. Note: The implementation is not complete yet, some visual
     * instances such as particles and skinned meshes may show artifacts. See also
     * `ProjectSettings.rendering/anti_aliasing/quality/use_taa` and
     * `RenderingServer.viewport_set_use_taa`.
     *
     * Generated from Godot docs: Viewport.set_use_taa
     */
    fun setUseTaa(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseTaaBind, segment, enable)
    }

    /**
     * Enables temporal antialiasing for this viewport. TAA works by jittering the camera and
     * accumulating the images of the last rendered frames, motion vector rendering is used to account
     * for camera and object motion. Note: The implementation is not complete yet, some visual
     * instances such as particles and skinned meshes may show artifacts. See also
     * `ProjectSettings.rendering/anti_aliasing/quality/use_taa` and
     * `RenderingServer.viewport_set_use_taa`.
     *
     * Generated from Godot docs: Viewport.is_using_taa
     */
    fun isUsingTaa(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingTaaBind, segment)
    }

    /**
     * When using the Mobile or Forward+ renderers, set `use_debanding` to enable or disable the
     * debanding feature of this `Viewport`. If `use_hdr_2d` is `false`, 2D rendering is not affected
     * by debanding unless the `Environment.background_mode` is `Environment.BG_CANVAS`. If
     * `use_hdr_2d` is `true`, debanding will only be applied if this is the root `Viewport` and will
     * affect all 2D and 3D rendering, including canvas items. `use_debanding` has no effect when using
     * the Compatibility rendering method. The Mobile renderer can also use material debanding, which
     * can be set with `RenderingServer.material_set_use_debanding` or configured with
     * `ProjectSettings.rendering/anti_aliasing/quality/use_debanding`. See also
     * `ProjectSettings.rendering/anti_aliasing/quality/use_debanding`,
     * `RenderingServer.material_set_use_debanding`, and `RenderingServer.viewport_set_use_debanding`.
     *
     * Generated from Godot docs: Viewport.set_use_debanding
     */
    fun setUseDebanding(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseDebandingBind, segment, enable)
    }

    /**
     * When using the Mobile or Forward+ renderers, set `use_debanding` to enable or disable the
     * debanding feature of this `Viewport`. If `use_hdr_2d` is `false`, 2D rendering is not affected
     * by debanding unless the `Environment.background_mode` is `Environment.BG_CANVAS`. If
     * `use_hdr_2d` is `true`, debanding will only be applied if this is the root `Viewport` and will
     * affect all 2D and 3D rendering, including canvas items. `use_debanding` has no effect when using
     * the Compatibility rendering method. The Mobile renderer can also use material debanding, which
     * can be set with `RenderingServer.material_set_use_debanding` or configured with
     * `ProjectSettings.rendering/anti_aliasing/quality/use_debanding`. See also
     * `ProjectSettings.rendering/anti_aliasing/quality/use_debanding`,
     * `RenderingServer.material_set_use_debanding`, and `RenderingServer.viewport_set_use_debanding`.
     *
     * Generated from Godot docs: Viewport.is_using_debanding
     */
    fun isUsingDebanding(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingDebandingBind, segment)
    }

    /**
     * If `true`, `OccluderInstance3D` nodes will be usable for occlusion culling in 3D for this
     * viewport. For the root viewport,
     * `ProjectSettings.rendering/occlusion_culling/use_occlusion_culling` must be set to `true`
     * instead. Note: Enabling occlusion culling has a cost on the CPU. Only enable occlusion culling
     * if you actually plan to use it, and think whether your scene can actually benefit from occlusion
     * culling. Large, open scenes with few or no objects blocking the view will generally not benefit
     * much from occlusion culling. Large open scenes generally benefit more from mesh LOD and
     * visibility ranges (`GeometryInstance3D.visibility_range_begin` and
     * `GeometryInstance3D.visibility_range_end`) compared to occlusion culling. Note: Due to memory
     * constraints, occlusion culling is not supported by default in Web export templates. It can be
     * enabled by compiling custom Web export templates with `module_raycast_enabled=yes`.
     *
     * Generated from Godot docs: Viewport.set_use_occlusion_culling
     */
    fun setUseOcclusionCulling(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseOcclusionCullingBind, segment, enable)
    }

    /**
     * If `true`, `OccluderInstance3D` nodes will be usable for occlusion culling in 3D for this
     * viewport. For the root viewport,
     * `ProjectSettings.rendering/occlusion_culling/use_occlusion_culling` must be set to `true`
     * instead. Note: Enabling occlusion culling has a cost on the CPU. Only enable occlusion culling
     * if you actually plan to use it, and think whether your scene can actually benefit from occlusion
     * culling. Large, open scenes with few or no objects blocking the view will generally not benefit
     * much from occlusion culling. Large open scenes generally benefit more from mesh LOD and
     * visibility ranges (`GeometryInstance3D.visibility_range_begin` and
     * `GeometryInstance3D.visibility_range_end`) compared to occlusion culling. Note: Due to memory
     * constraints, occlusion culling is not supported by default in Web export templates. It can be
     * enabled by compiling custom Web export templates with `module_raycast_enabled=yes`.
     *
     * Generated from Godot docs: Viewport.is_using_occlusion_culling
     */
    fun isUsingOcclusionCulling(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingOcclusionCullingBind, segment)
    }

    /**
     * The overlay mode for test rendered geometry in debug purposes.
     *
     * Generated from Godot docs: Viewport.set_debug_draw
     */
    fun setDebugDraw(debugDraw: Long) {
        ObjectCalls.ptrcallWithLongArg(setDebugDrawBind, segment, debugDraw)
    }

    /**
     * The overlay mode for test rendered geometry in debug purposes.
     *
     * Generated from Godot docs: Viewport.get_debug_draw
     */
    fun getDebugDraw(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDebugDrawBind, segment)
    }

    /**
     * If `true` and one of the following conditions are true: `SubViewport.size_2d_override_stretch`
     * and `SubViewport.size_2d_override` are set, `Window.content_scale_factor` is set and scaling is
     * enabled, `oversampling_override` is set, font and `DPITexture` oversampling are enabled.
     *
     * Generated from Godot docs: Viewport.set_use_oversampling
     */
    fun setUseOversampling(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseOversamplingBind, segment, enable)
    }

    /**
     * If `true` and one of the following conditions are true: `SubViewport.size_2d_override_stretch`
     * and `SubViewport.size_2d_override` are set, `Window.content_scale_factor` is set and scaling is
     * enabled, `oversampling_override` is set, font and `DPITexture` oversampling are enabled.
     *
     * Generated from Godot docs: Viewport.is_using_oversampling
     */
    fun isUsingOversampling(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingOversamplingBind, segment)
    }

    /**
     * If greater than zero, this value is used as the font oversampling factor, otherwise oversampling
     * is equal to viewport scale.
     *
     * Generated from Godot docs: Viewport.set_oversampling_override
     */
    fun setOversamplingOverride(oversampling: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setOversamplingOverrideBind, segment, oversampling)
    }

    /**
     * If greater than zero, this value is used as the font oversampling factor, otherwise oversampling
     * is equal to viewport scale.
     *
     * Generated from Godot docs: Viewport.get_oversampling_override
     */
    fun getOversamplingOverride(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getOversamplingOverrideBind, segment)
    }

    /**
     * Returns viewport oversampling factor.
     *
     * Generated from Godot docs: Viewport.get_oversampling
     */
    fun getOversampling(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getOversamplingBind, segment)
    }

    /**
     * Returns rendering statistics of the given type.
     *
     * Generated from Godot docs: Viewport.get_render_info
     */
    fun getRenderInfo(type: Long, info: Long): Int {
        return ObjectCalls.ptrcallWithTwoLongArgsRetInt(getRenderInfoBind, segment, type, info)
    }

    /**
     * Returns the viewport's texture. Note: When trying to store the current texture (e.g. in a file),
     * it might be completely black or outdated if used too early, especially when used in e.g.
     * `Node._ready`. To make sure the texture you get is correct, you can await
     * `RenderingServer.frame_post_draw` signal.
     *
     * Generated from Godot docs: Viewport.get_texture
     */
    fun getTexture(): ViewportTexture? {
        return ViewportTexture.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTextureBind, segment))
    }

    /**
     * If `true`, the objects rendered by viewport become subjects of mouse picking process. Note: The
     * number of simultaneously pickable objects is limited to 64 and they are selected in a
     * non-deterministic order, which can be different in each picking process.
     *
     * Generated from Godot docs: Viewport.set_physics_object_picking
     */
    fun setPhysicsObjectPicking(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPhysicsObjectPickingBind, segment, enable)
    }

    /**
     * If `true`, the objects rendered by viewport become subjects of mouse picking process. Note: The
     * number of simultaneously pickable objects is limited to 64 and they are selected in a
     * non-deterministic order, which can be different in each picking process.
     *
     * Generated from Godot docs: Viewport.get_physics_object_picking
     */
    fun getPhysicsObjectPicking(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getPhysicsObjectPickingBind, segment)
    }

    /**
     * If `true`, objects receive mouse picking events sorted primarily by their `CanvasItem.z_index`
     * and secondarily by their position in the scene tree. If `false`, the order is undetermined.
     * Note: This setting is disabled by default because of its potential expensive computational cost.
     * Note: Sorting happens after selecting the pickable objects. Because of the limitation of 64
     * simultaneously pickable objects, it is not guaranteed that the object with the highest
     * `CanvasItem.z_index` receives the picking event.
     *
     * Generated from Godot docs: Viewport.set_physics_object_picking_sort
     */
    fun setPhysicsObjectPickingSort(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPhysicsObjectPickingSortBind, segment, enable)
    }

    /**
     * If `true`, objects receive mouse picking events sorted primarily by their `CanvasItem.z_index`
     * and secondarily by their position in the scene tree. If `false`, the order is undetermined.
     * Note: This setting is disabled by default because of its potential expensive computational cost.
     * Note: Sorting happens after selecting the pickable objects. Because of the limitation of 64
     * simultaneously pickable objects, it is not guaranteed that the object with the highest
     * `CanvasItem.z_index` receives the picking event.
     *
     * Generated from Godot docs: Viewport.get_physics_object_picking_sort
     */
    fun getPhysicsObjectPickingSort(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getPhysicsObjectPickingSortBind, segment)
    }

    /**
     * If `true`, the input_event signal will only be sent to one physics object in the mouse picking
     * process. If you want to get the top object only, you must also enable
     * `physics_object_picking_sort`. If `false`, an input_event signal will be sent to all physics
     * objects in the mouse picking process. This applies to 2D CanvasItem object picking only.
     *
     * Generated from Godot docs: Viewport.set_physics_object_picking_first_only
     */
    fun setPhysicsObjectPickingFirstOnly(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPhysicsObjectPickingFirstOnlyBind, segment, enable)
    }

    /**
     * If `true`, the input_event signal will only be sent to one physics object in the mouse picking
     * process. If you want to get the top object only, you must also enable
     * `physics_object_picking_sort`. If `false`, an input_event signal will be sent to all physics
     * objects in the mouse picking process. This applies to 2D CanvasItem object picking only.
     *
     * Generated from Godot docs: Viewport.get_physics_object_picking_first_only
     */
    fun getPhysicsObjectPickingFirstOnly(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getPhysicsObjectPickingFirstOnlyBind, segment)
    }

    /**
     * Returns the viewport's RID from the `RenderingServer`.
     *
     * Generated from Godot docs: Viewport.get_viewport_rid
     */
    fun getViewportRid(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getViewportRidBind, segment)
    }

    /**
     * Helper method which calls the `set_text()` method on the currently focused `Control`, provided
     * that it is defined (e.g. if the focused Control is `Button` or `LineEdit`).
     *
     * Generated from Godot docs: Viewport.push_text_input
     */
    fun pushTextInput(text: String) {
        ObjectCalls.ptrcallWithStringArg(pushTextInputBind, segment, text)
    }

    /**
     * Triggers the given `event` in this `Viewport`. This can be used to pass an `InputEvent` between
     * viewports, or to locally apply inputs that were sent over the network or saved to a file. If
     * `in_local_coords` is `false`, the event's position is in the embedder's coordinates and will be
     * converted to viewport coordinates. If `in_local_coords` is `true`, the event's position is in
     * viewport coordinates. While this method serves a similar purpose as `Input.parse_input_event`,
     * it does not remap the specified `event` based on project settings like
     * `ProjectSettings.input_devices/pointing/emulate_touch_from_mouse`. Calling this method will
     * propagate calls to child nodes for following methods in the given order: - `Node._input` -
     * `Control._gui_input` for `Control` nodes - `Node._shortcut_input` - `Node._unhandled_key_input`
     * - `Node._unhandled_input` If an earlier method marks the input as handled via
     * `set_input_as_handled`, any later method in this list will not be called. If none of the methods
     * handle the event and `physics_object_picking` is `true`, the event is used for physics object
     * picking.
     *
     * Generated from Godot docs: Viewport.push_input
     */
    fun pushInput(event: InputEvent?, inLocalCoords: Boolean = false) {
        ObjectCalls.ptrcallWithObjectAndBoolArg(pushInputBind, segment, event?.requireOpenHandle() ?: NULL_SEGMENT, inLocalCoords)
    }

    /**
     * Triggers the given `event` in this `Viewport`. This can be used to pass an `InputEvent` between
     * viewports, or to locally apply inputs that were sent over the network or saved to a file. If
     * `in_local_coords` is `false`, the event's position is in the embedder's coordinates and will be
     * converted to viewport coordinates. If `in_local_coords` is `true`, the event's position is in
     * viewport coordinates. Calling this method will propagate calls to child nodes for following
     * methods in the given order: - `Node._shortcut_input` - `Node._unhandled_key_input` -
     * `Node._unhandled_input` If an earlier method marks the input as handled via
     * `set_input_as_handled`, any later method in this list will not be called. If none of the methods
     * handle the event and `physics_object_picking` is `true`, the event is used for physics object
     * picking. Note: This method doesn't propagate input events to embedded `Window`s or
     * `SubViewport`s.
     *
     * Generated from Godot docs: Viewport.push_unhandled_input
     */
    fun pushUnhandledInput(event: InputEvent?, inLocalCoords: Boolean = false) {
        ObjectCalls.ptrcallWithObjectAndBoolArg(pushUnhandledInputBind, segment, event?.requireOpenHandle() ?: NULL_SEGMENT, inLocalCoords)
    }

    /**
     * Inform the Viewport that the mouse has entered its area. Use this function before sending an
     * `InputEventMouseButton` or `InputEventMouseMotion` to the `Viewport` with `Viewport.push_input`.
     * See also `notify_mouse_exited`. Note: In most cases, it is not necessary to call this function
     * because `SubViewport` nodes that are children of `SubViewportContainer` are notified
     * automatically. This is only necessary when interacting with viewports in non-default ways, for
     * example as textures in `TextureRect` or with an `Area3D` that forwards input events.
     *
     * Generated from Godot docs: Viewport.notify_mouse_entered
     */
    fun notifyMouseEntered() {
        ObjectCalls.ptrcallNoArgs(notifyMouseEnteredBind, segment)
    }

    /**
     * Inform the Viewport that the mouse has left its area. Use this function when the node that
     * displays the viewport notices the mouse has left the area of the displayed viewport. See also
     * `notify_mouse_entered`. Note: In most cases, it is not necessary to call this function because
     * `SubViewport` nodes that are children of `SubViewportContainer` are notified automatically. This
     * is only necessary when interacting with viewports in non-default ways, for example as textures
     * in `TextureRect` or with an `Area3D` that forwards input events.
     *
     * Generated from Godot docs: Viewport.notify_mouse_exited
     */
    fun notifyMouseExited() {
        ObjectCalls.ptrcallNoArgs(notifyMouseExitedBind, segment)
    }

    /**
     * Returns the mouse's position in this `Viewport` using the coordinate system of this `Viewport`.
     *
     * Generated from Godot docs: Viewport.get_mouse_position
     */
    fun getMousePosition(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getMousePositionBind, segment)
    }

    /**
     * Moves the mouse pointer to the specified position in this `Viewport` using the coordinate system
     * of this `Viewport`. Note: `warp_mouse` is only supported on Windows, macOS and Linux. It has no
     * effect on Android, iOS and Web.
     *
     * Generated from Godot docs: Viewport.warp_mouse
     */
    fun warpMouse(position: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(warpMouseBind, segment, position)
    }

    /**
     * Force instantly updating the display based on the current mouse cursor position. This includes
     * updating the mouse cursor shape and sending necessary `Control.mouse_entered`,
     * `CollisionObject2D.mouse_entered`, `CollisionObject3D.mouse_entered` and `Window.mouse_entered`
     * signals and their respective `mouse_exited` counterparts.
     *
     * Generated from Godot docs: Viewport.update_mouse_cursor_state
     */
    fun updateMouseCursorState() {
        ObjectCalls.ptrcallNoArgs(updateMouseCursorStateBind, segment)
    }

    /**
     * Cancels the drag operation that was previously started through `Control._get_drag_data` or
     * forced with `Control.force_drag`.
     *
     * Generated from Godot docs: Viewport.gui_cancel_drag
     */
    fun guiCancelDrag() {
        ObjectCalls.ptrcallNoArgs(guiCancelDragBind, segment)
    }

    /**
     * Returns the drag data from the GUI, that was previously returned by `Control._get_drag_data`.
     *
     * Generated from Godot docs: Viewport.gui_get_drag_data
     */
    fun guiGetDragData(): Any? {
        return ObjectCalls.ptrcallNoArgsRetVariantScalar(guiGetDragDataBind, segment)
    }

    /**
     * Returns the human-readable description of the drag data, used for assistive apps.
     *
     * Generated from Godot docs: Viewport.gui_get_drag_description
     */
    fun guiGetDragDescription(): String {
        return ObjectCalls.ptrcallNoArgsRetString(guiGetDragDescriptionBind, segment)
    }

    /**
     * Sets the human-readable description of the drag data to `description`, used for assistive apps.
     *
     * Generated from Godot docs: Viewport.gui_set_drag_description
     */
    fun guiSetDragDescription(description: String) {
        ObjectCalls.ptrcallWithStringArg(guiSetDragDescriptionBind, segment, description)
    }

    /**
     * Returns `true` if a drag operation is currently ongoing and where the drop action could happen
     * in this viewport. Alternative to `Node.NOTIFICATION_DRAG_BEGIN` and `Node.NOTIFICATION_DRAG_END`
     * when you prefer polling the value.
     *
     * Generated from Godot docs: Viewport.gui_is_dragging
     */
    fun guiIsDragging(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(guiIsDraggingBind, segment)
    }

    /**
     * Returns `true` if the drag operation is successful.
     *
     * Generated from Godot docs: Viewport.gui_is_drag_successful
     */
    fun guiIsDragSuccessful(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(guiIsDragSuccessfulBind, segment)
    }

    /**
     * Removes the focus from the currently focused `Control` within this viewport. If no `Control` has
     * the focus, does nothing.
     *
     * Generated from Godot docs: Viewport.gui_release_focus
     */
    fun guiReleaseFocus() {
        ObjectCalls.ptrcallNoArgs(guiReleaseFocusBind, segment)
    }

    /**
     * Returns the currently focused `Control` within this viewport. If no `Control` is focused,
     * returns `null`.
     *
     * Generated from Godot docs: Viewport.gui_get_focus_owner
     */
    fun guiGetFocusOwner(): Control? {
        return Control.wrap(ObjectCalls.ptrcallNoArgsRetObject(guiGetFocusOwnerBind, segment))
    }

    /**
     * Returns the `Control` that the mouse is currently hovering over in this viewport. If no
     * `Control` has the cursor, returns `null`. Typically the leaf `Control` node or deepest level of
     * the subtree which claims hover. This is very useful when used together with
     * `Node.is_ancestor_of` to find if the mouse is within a control tree.
     *
     * Generated from Godot docs: Viewport.gui_get_hovered_control
     */
    fun guiGetHoveredControl(): Control? {
        return Control.wrap(ObjectCalls.ptrcallNoArgsRetObject(guiGetHoveredControlBind, segment))
    }

    /**
     * If `true`, the viewport will not receive input events.
     *
     * Generated from Godot docs: Viewport.set_disable_input
     */
    fun setDisableInput(disable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDisableInputBind, segment, disable)
    }

    /**
     * If `true`, the viewport will not receive input events.
     *
     * Generated from Godot docs: Viewport.is_input_disabled
     */
    fun isInputDisabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isInputDisabledBind, segment)
    }

    /**
     * The shadow atlas' resolution (used for omni and spot lights). The value is rounded up to the
     * nearest power of 2. Note: If this is set to `0`, no positional shadows will be visible at all.
     * This can improve performance significantly on low-end systems by reducing both the CPU and GPU
     * load (as fewer draw calls are needed to draw the scene without shadows).
     *
     * Generated from Godot docs: Viewport.set_positional_shadow_atlas_size
     */
    fun setPositionalShadowAtlasSize(size: Int) {
        ObjectCalls.ptrcallWithIntArg(setPositionalShadowAtlasSizeBind, segment, size)
    }

    /**
     * The shadow atlas' resolution (used for omni and spot lights). The value is rounded up to the
     * nearest power of 2. Note: If this is set to `0`, no positional shadows will be visible at all.
     * This can improve performance significantly on low-end systems by reducing both the CPU and GPU
     * load (as fewer draw calls are needed to draw the scene without shadows).
     *
     * Generated from Godot docs: Viewport.get_positional_shadow_atlas_size
     */
    fun getPositionalShadowAtlasSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getPositionalShadowAtlasSizeBind, segment)
    }

    /**
     * Use 16 bits for the omni/spot shadow depth map. Enabling this results in shadows having less
     * precision and may result in shadow acne, but can lead to performance improvements on some
     * devices.
     *
     * Generated from Godot docs: Viewport.set_positional_shadow_atlas_16_bits
     */
    fun setPositionalShadowAtlas16Bits(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPositionalShadowAtlas16BitsBind, segment, enable)
    }

    /**
     * Use 16 bits for the omni/spot shadow depth map. Enabling this results in shadows having less
     * precision and may result in shadow acne, but can lead to performance improvements on some
     * devices.
     *
     * Generated from Godot docs: Viewport.get_positional_shadow_atlas_16_bits
     */
    fun getPositionalShadowAtlas16Bits(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getPositionalShadowAtlas16BitsBind, segment)
    }

    /**
     * If `true`, the GUI controls on the viewport will lay pixel perfectly.
     *
     * Generated from Godot docs: Viewport.set_snap_controls_to_pixels
     */
    fun setSnapControlsToPixels(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSnapControlsToPixelsBind, segment, enabled)
    }

    /**
     * If `true`, the GUI controls on the viewport will lay pixel perfectly.
     *
     * Generated from Godot docs: Viewport.is_snap_controls_to_pixels_enabled
     */
    fun isSnapControlsToPixelsEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSnapControlsToPixelsEnabledBind, segment)
    }

    /**
     * If `true`, `CanvasItem` nodes will internally snap to full pixels. Their position can still be
     * sub-pixel, but the decimals will not have effect. This can lead to a crisper appearance at the
     * cost of less smooth movement, especially when `Camera2D` smoothing is enabled.
     *
     * Generated from Godot docs: Viewport.set_snap_2d_transforms_to_pixel
     */
    fun setSnap2dTransformsToPixel(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSnap2dTransformsToPixelBind, segment, enabled)
    }

    /**
     * If `true`, `CanvasItem` nodes will internally snap to full pixels. Their position can still be
     * sub-pixel, but the decimals will not have effect. This can lead to a crisper appearance at the
     * cost of less smooth movement, especially when `Camera2D` smoothing is enabled.
     *
     * Generated from Godot docs: Viewport.is_snap_2d_transforms_to_pixel_enabled
     */
    fun isSnap2dTransformsToPixelEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSnap2dTransformsToPixelEnabledBind, segment)
    }

    /**
     * If `true`, vertices of `CanvasItem` nodes will snap to full pixels. Only affects the final
     * vertex positions, not the transforms. This can lead to a crisper appearance at the cost of less
     * smooth movement, especially when `Camera2D` smoothing is enabled.
     *
     * Generated from Godot docs: Viewport.set_snap_2d_vertices_to_pixel
     */
    fun setSnap2dVerticesToPixel(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSnap2dVerticesToPixelBind, segment, enabled)
    }

    /**
     * If `true`, vertices of `CanvasItem` nodes will snap to full pixels. Only affects the final
     * vertex positions, not the transforms. This can lead to a crisper appearance at the cost of less
     * smooth movement, especially when `Camera2D` smoothing is enabled.
     *
     * Generated from Godot docs: Viewport.is_snap_2d_vertices_to_pixel_enabled
     */
    fun isSnap2dVerticesToPixelEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSnap2dVerticesToPixelEnabledBind, segment)
    }

    /**
     * The subdivision amount of the fourth quadrant on the shadow atlas.
     *
     * Generated from Godot docs: Viewport.set_positional_shadow_atlas_quadrant_subdiv
     */
    fun setPositionalShadowAtlasQuadrantSubdiv(quadrant: Int, subdiv: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setPositionalShadowAtlasQuadrantSubdivBind, segment, quadrant, subdiv)
    }

    /**
     * The subdivision amount of the fourth quadrant on the shadow atlas.
     *
     * Generated from Godot docs: Viewport.get_positional_shadow_atlas_quadrant_subdiv
     */
    fun getPositionalShadowAtlasQuadrantSubdiv(quadrant: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getPositionalShadowAtlasQuadrantSubdivBind, segment, quadrant)
    }

    /**
     * Stops the input from propagating further up the `SceneTree`. Note: This does not affect the
     * methods in `Input`, only the way events are propagated.
     *
     * Generated from Godot docs: Viewport.set_input_as_handled
     */
    fun setInputAsHandled() {
        ObjectCalls.ptrcallNoArgs(setInputAsHandledBind, segment)
    }

    /**
     * Returns whether the current `InputEvent` has been handled. Input events are not handled until
     * `set_input_as_handled` has been called during the lifetime of an `InputEvent`. This is usually
     * done as part of input handling methods like `Node._input`, `Control._gui_input` or others, as
     * well as in corresponding signal handlers. If `handle_input_locally` is set to `false`, this
     * method will try finding the first parent viewport that is set to handle input locally, and
     * return its value for `is_input_handled` instead.
     *
     * Generated from Godot docs: Viewport.is_input_handled
     */
    fun isInputHandled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isInputHandledBind, segment)
    }

    /**
     * If `true`, this viewport will mark incoming input events as handled by itself. If `false`, this
     * is instead done by the first parent viewport that is set to handle input locally. A
     * `SubViewportContainer` will automatically set this property to `false` for the `Viewport`
     * contained inside of it. See also `set_input_as_handled` and `is_input_handled`.
     *
     * Generated from Godot docs: Viewport.set_handle_input_locally
     */
    fun setHandleInputLocally(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setHandleInputLocallyBind, segment, enable)
    }

    /**
     * If `true`, this viewport will mark incoming input events as handled by itself. If `false`, this
     * is instead done by the first parent viewport that is set to handle input locally. A
     * `SubViewportContainer` will automatically set this property to `false` for the `Viewport`
     * contained inside of it. See also `set_input_as_handled` and `is_input_handled`.
     *
     * Generated from Godot docs: Viewport.is_handling_input_locally
     */
    fun isHandlingInputLocally(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isHandlingInputLocallyBind, segment)
    }

    /**
     * The default filter mode used by `CanvasItem` nodes in this viewport.
     *
     * Generated from Godot docs: Viewport.set_default_canvas_item_texture_filter
     */
    fun setDefaultCanvasItemTextureFilter(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setDefaultCanvasItemTextureFilterBind, segment, mode)
    }

    /**
     * The default filter mode used by `CanvasItem` nodes in this viewport.
     *
     * Generated from Godot docs: Viewport.get_default_canvas_item_texture_filter
     */
    fun getDefaultCanvasItemTextureFilter(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDefaultCanvasItemTextureFilterBind, segment)
    }

    /**
     * If `true`, sub-windows (popups and dialogs) will be embedded inside application window as
     * control-like nodes. If `false`, they will appear as separate windows handled by the operating
     * system.
     *
     * Generated from Godot docs: Viewport.set_embedding_subwindows
     */
    fun setEmbeddingSubwindows(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEmbeddingSubwindowsBind, segment, enable)
    }

    /**
     * If `true`, sub-windows (popups and dialogs) will be embedded inside application window as
     * control-like nodes. If `false`, they will appear as separate windows handled by the operating
     * system.
     *
     * Generated from Godot docs: Viewport.is_embedding_subwindows
     */
    fun isEmbeddingSubwindows(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEmbeddingSubwindowsBind, segment)
    }

    /**
     * Returns a list of the visible embedded `Window`s inside the viewport. Note: `Window`s inside
     * other viewports will not be listed.
     *
     * Generated from Godot docs: Viewport.get_embedded_subwindows
     */
    fun getEmbeddedSubwindows(): List<Window> {
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getEmbeddedSubwindowsBind, segment, Window::wrap)
    }

    /**
     * The minimum distance the mouse cursor must move while pressed before a drag operation begins.
     *
     * Generated from Godot docs: Viewport.set_drag_threshold
     */
    fun setDragThreshold(threshold: Int) {
        ObjectCalls.ptrcallWithIntArg(setDragThresholdBind, segment, threshold)
    }

    /**
     * The minimum distance the mouse cursor must move while pressed before a drag operation begins.
     *
     * Generated from Godot docs: Viewport.get_drag_threshold
     */
    fun getDragThreshold(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getDragThresholdBind, segment)
    }

    /**
     * The rendering layers in which this `Viewport` renders `CanvasItem` nodes. Note: A `CanvasItem`
     * does not inherit its parents' visibility layers. See `CanvasItem.visibility_layer`'s description
     * for details.
     *
     * Generated from Godot docs: Viewport.set_canvas_cull_mask
     */
    fun setCanvasCullMask(mask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setCanvasCullMaskBind, segment, mask)
    }

    /**
     * The rendering layers in which this `Viewport` renders `CanvasItem` nodes. Note: A `CanvasItem`
     * does not inherit its parents' visibility layers. See `CanvasItem.visibility_layer`'s description
     * for details.
     *
     * Generated from Godot docs: Viewport.get_canvas_cull_mask
     */
    fun getCanvasCullMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getCanvasCullMaskBind, segment)
    }

    /**
     * Set/clear individual bits on the rendering layer mask. This simplifies editing this `Viewport`'s
     * layers.
     *
     * Generated from Godot docs: Viewport.set_canvas_cull_mask_bit
     */
    fun setCanvasCullMaskBit(layer: Long, enable: Boolean) {
        ObjectCalls.ptrcallWithUInt32AndBoolArgs(setCanvasCullMaskBitBind, segment, layer, enable)
    }

    /**
     * Returns an individual bit on the rendering layer mask.
     *
     * Generated from Godot docs: Viewport.get_canvas_cull_mask_bit
     */
    fun getCanvasCullMaskBit(layer: Long): Boolean {
        return ObjectCalls.ptrcallWithUInt32ArgRetBool(getCanvasCullMaskBitBind, segment, layer)
    }

    /**
     * The default repeat mode used by `CanvasItem` nodes in this viewport.
     *
     * Generated from Godot docs: Viewport.set_default_canvas_item_texture_repeat
     */
    fun setDefaultCanvasItemTextureRepeat(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setDefaultCanvasItemTextureRepeatBind, segment, mode)
    }

    /**
     * The default repeat mode used by `CanvasItem` nodes in this viewport.
     *
     * Generated from Godot docs: Viewport.get_default_canvas_item_texture_repeat
     */
    fun getDefaultCanvasItemTextureRepeat(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDefaultCanvasItemTextureRepeatBind, segment)
    }

    /**
     * Controls how much of the original viewport's size should be covered by the 2D signed distance
     * field. This SDF can be sampled in `CanvasItem` shaders and is also used for `GPUParticles2D`
     * collision. Higher values allow portions of occluders located outside the viewport to still be
     * taken into account in the generated signed distance field, at the cost of performance. If you
     * notice particles falling through `LightOccluder2D`s as the occluders leave the viewport,
     * increase this setting. The percentage is added on each axis and on both sides. For example, with
     * the default `SDF_OVERSIZE_120_PERCENT`, the signed distance field will cover 20% of the
     * viewport's size outside the viewport on each side (top, right, bottom, left).
     *
     * Generated from Godot docs: Viewport.set_sdf_oversize
     */
    fun setSdfOversize(oversize: Long) {
        ObjectCalls.ptrcallWithLongArg(setSdfOversizeBind, segment, oversize)
    }

    /**
     * Controls how much of the original viewport's size should be covered by the 2D signed distance
     * field. This SDF can be sampled in `CanvasItem` shaders and is also used for `GPUParticles2D`
     * collision. Higher values allow portions of occluders located outside the viewport to still be
     * taken into account in the generated signed distance field, at the cost of performance. If you
     * notice particles falling through `LightOccluder2D`s as the occluders leave the viewport,
     * increase this setting. The percentage is added on each axis and on both sides. For example, with
     * the default `SDF_OVERSIZE_120_PERCENT`, the signed distance field will cover 20% of the
     * viewport's size outside the viewport on each side (top, right, bottom, left).
     *
     * Generated from Godot docs: Viewport.get_sdf_oversize
     */
    fun getSdfOversize(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSdfOversizeBind, segment)
    }

    /**
     * The resolution scale to use for the 2D signed distance field. Higher values lead to a more
     * precise and more stable signed distance field as the camera moves, at the cost of performance.
     *
     * Generated from Godot docs: Viewport.set_sdf_scale
     */
    fun setSdfScale(scale: Long) {
        ObjectCalls.ptrcallWithLongArg(setSdfScaleBind, segment, scale)
    }

    /**
     * The resolution scale to use for the 2D signed distance field. Higher values lead to a more
     * precise and more stable signed distance field as the camera moves, at the cost of performance.
     *
     * Generated from Godot docs: Viewport.get_sdf_scale
     */
    fun getSdfScale(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSdfScaleBind, segment)
    }

    /**
     * The automatic LOD bias to use for meshes rendered within the `Viewport` (this is analogous to
     * `ReflectionProbe.mesh_lod_threshold`). Higher values will use less detailed versions of meshes
     * that have LOD variations generated. If set to `0.0`, automatic LOD is disabled. Increase
     * `mesh_lod_threshold` to improve performance at the cost of geometry detail. To control this
     * property on the root viewport, set the
     * `ProjectSettings.rendering/mesh_lod/lod_change/threshold_pixels` project setting. Note:
     * Depending on the mesh's attributes (vertex colors, blend shapes, ...), a mesh may have fewer
     * levels of LOD generated to avoid visible distortion of the mesh once it is affected by vertex
     * colors or blend shapes. Meshes with a very low vertex count will also not have any LODs
     * generated, which means this setting will not affect them at all. In general, this setting makes
     * the largest impact on static meshes with a high vertex count. Note: `mesh_lod_threshold` does
     * not affect `GeometryInstance3D` visibility ranges (also known as "manual" LOD or hierarchical
     * LOD).
     *
     * Generated from Godot docs: Viewport.set_mesh_lod_threshold
     */
    fun setMeshLodThreshold(pixels: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMeshLodThresholdBind, segment, pixels)
    }

    /**
     * The automatic LOD bias to use for meshes rendered within the `Viewport` (this is analogous to
     * `ReflectionProbe.mesh_lod_threshold`). Higher values will use less detailed versions of meshes
     * that have LOD variations generated. If set to `0.0`, automatic LOD is disabled. Increase
     * `mesh_lod_threshold` to improve performance at the cost of geometry detail. To control this
     * property on the root viewport, set the
     * `ProjectSettings.rendering/mesh_lod/lod_change/threshold_pixels` project setting. Note:
     * Depending on the mesh's attributes (vertex colors, blend shapes, ...), a mesh may have fewer
     * levels of LOD generated to avoid visible distortion of the mesh once it is affected by vertex
     * colors or blend shapes. Meshes with a very low vertex count will also not have any LODs
     * generated, which means this setting will not affect them at all. In general, this setting makes
     * the largest impact on static meshes with a high vertex count. Note: `mesh_lod_threshold` does
     * not affect `GeometryInstance3D` visibility ranges (also known as "manual" LOD or hierarchical
     * LOD).
     *
     * Generated from Godot docs: Viewport.get_mesh_lod_threshold
     */
    fun getMeshLodThreshold(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMeshLodThresholdBind, segment)
    }

    /**
     * If `true`, the viewport will process 2D audio streams.
     *
     * Generated from Godot docs: Viewport.set_as_audio_listener_2d
     */
    fun setAsAudioListener2d(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAsAudioListener2dBind, segment, enable)
    }

    /**
     * If `true`, the viewport will process 2D audio streams.
     *
     * Generated from Godot docs: Viewport.is_audio_listener_2d
     */
    fun isAudioListener2d(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAudioListener2dBind, segment)
    }

    /**
     * Returns the currently active 2D audio listener. Returns `null` if there are no active 2D audio
     * listeners, in which case the active 2D camera will be treated as listener.
     *
     * Generated from Godot docs: Viewport.get_audio_listener_2d
     */
    fun getAudioListener2d(): AudioListener2D? {
        return AudioListener2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getAudioListener2dBind, segment))
    }

    /**
     * Returns the currently active 2D camera. Returns `null` if there are no active cameras. Note: If
     * called while the Camera Override system is active in editor, this will return the internally
     * managed override camera. It is therefore advised to avoid caching the return value, or to check
     * that the cached value is still a valid instance and is the current camera before use. See
     * `@GlobalScope.is_instance_valid` and `Camera2D.is_current`.
     *
     * Generated from Godot docs: Viewport.get_camera_2d
     */
    fun getCamera2d(): Camera2D? {
        return Camera2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCamera2dBind, segment))
    }

    /**
     * The custom `World3D` which can be used as 3D environment source.
     *
     * Generated from Godot docs: Viewport.set_world_3d
     */
    fun setWorld3d(world3d: World3D?) {
        ObjectCalls.ptrcallWithObjectArgs(setWorld3dBind, segment, listOf(world3d?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The custom `World3D` which can be used as 3D environment source.
     *
     * Generated from Godot docs: Viewport.get_world_3d
     */
    fun getWorld3d(): World3D? {
        return World3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getWorld3dBind, segment))
    }

    /**
     * Returns the first valid `World3D` for this viewport, searching the `world_3d` property of itself
     * and any Viewport ancestor.
     *
     * Generated from Godot docs: Viewport.find_world_3d
     */
    fun findWorld3d(): World3D? {
        return World3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(findWorld3dBind, segment))
    }

    /**
     * If `true`, the viewport will use a unique copy of the `World3D` defined in `world_3d`.
     *
     * Generated from Godot docs: Viewport.set_use_own_world_3d
     */
    fun setUseOwnWorld3d(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseOwnWorld3dBind, segment, enable)
    }

    /**
     * If `true`, the viewport will use a unique copy of the `World3D` defined in `world_3d`.
     *
     * Generated from Godot docs: Viewport.is_using_own_world_3d
     */
    fun isUsingOwnWorld3d(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingOwnWorld3dBind, segment)
    }

    /**
     * Returns the currently active 3D audio listener. Returns `null` if there are no active 3D audio
     * listeners, in which case the active 3D camera will be treated as listener.
     *
     * Generated from Godot docs: Viewport.get_audio_listener_3d
     */
    fun getAudioListener3d(): AudioListener3D? {
        return AudioListener3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getAudioListener3dBind, segment))
    }

    /**
     * Returns the currently active 3D camera. Returns `null` if there are no active cameras. Note: If
     * called while the Camera Override system is active in editor, this will return the internally
     * managed override camera. It is therefore advised to avoid caching the return value, or to check
     * that the cached value is a valid instance and is the current camera before use. See
     * `@GlobalScope.is_instance_valid` and `Camera3D.current`.
     *
     * Generated from Godot docs: Viewport.get_camera_3d
     */
    fun getCamera3d(): Camera3D? {
        return Camera3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCamera3dBind, segment))
    }

    fun getCamera3D(): Camera3D? =
        getCamera3d()

    /**
     * If `true`, the viewport will process 3D audio streams.
     *
     * Generated from Godot docs: Viewport.set_as_audio_listener_3d
     */
    fun setAsAudioListener3d(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAsAudioListener3dBind, segment, enable)
    }

    /**
     * If `true`, the viewport will process 3D audio streams.
     *
     * Generated from Godot docs: Viewport.is_audio_listener_3d
     */
    fun isAudioListener3d(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAudioListener3dBind, segment)
    }

    /**
     * Disable 3D rendering (but keep 2D rendering).
     *
     * Generated from Godot docs: Viewport.set_disable_3d
     */
    fun setDisable3d(disable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDisable3dBind, segment, disable)
    }

    /**
     * Disable 3D rendering (but keep 2D rendering).
     *
     * Generated from Godot docs: Viewport.is_3d_disabled
     */
    fun is3dDisabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(is3dDisabledBind, segment)
    }

    /**
     * If `true`, the viewport will use the primary XR interface to render XR output. When applicable
     * this can result in a stereoscopic image and the resulting render being output to a headset.
     *
     * Generated from Godot docs: Viewport.set_use_xr
     */
    fun setUseXr(use: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseXrBind, segment, use)
    }

    /**
     * If `true`, the viewport will use the primary XR interface to render XR output. When applicable
     * this can result in a stereoscopic image and the resulting render being output to a headset.
     *
     * Generated from Godot docs: Viewport.is_using_xr
     */
    fun isUsingXr(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingXrBind, segment)
    }

    /**
     * Sets scaling 3D mode. Bilinear scaling renders at different resolution to either undersample or
     * supersample the viewport. FidelityFX Super Resolution 1.0, abbreviated to FSR, is an upscaling
     * technology that produces high quality images at fast framerates by using a spatially aware
     * upscaling algorithm. FSR is slightly more expensive than bilinear, but it produces significantly
     * higher image quality. FSR should be used where possible. To control this property on the root
     * viewport, set the `ProjectSettings.rendering/scaling_3d/mode` project setting.
     *
     * Generated from Godot docs: Viewport.set_scaling_3d_mode
     */
    fun setScaling3dMode(scaling3dMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setScaling3dModeBind, segment, scaling3dMode)
    }

    /**
     * Sets scaling 3D mode. Bilinear scaling renders at different resolution to either undersample or
     * supersample the viewport. FidelityFX Super Resolution 1.0, abbreviated to FSR, is an upscaling
     * technology that produces high quality images at fast framerates by using a spatially aware
     * upscaling algorithm. FSR is slightly more expensive than bilinear, but it produces significantly
     * higher image quality. FSR should be used where possible. To control this property on the root
     * viewport, set the `ProjectSettings.rendering/scaling_3d/mode` project setting.
     *
     * Generated from Godot docs: Viewport.get_scaling_3d_mode
     */
    fun getScaling3dMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getScaling3dModeBind, segment)
    }

    /**
     * Scales the 3D render buffer based on the viewport size uses an image filter specified in
     * `ProjectSettings.rendering/scaling_3d/mode` to scale the output image to the full viewport size.
     * Values lower than `1.0` can be used to speed up 3D rendering at the cost of quality
     * (undersampling). Values greater than `1.0` are only valid for bilinear mode and can be used to
     * improve 3D rendering quality at a high performance cost (supersampling). See also
     * `ProjectSettings.rendering/anti_aliasing/quality/msaa_3d` for multi-sample antialiasing, which
     * is significantly cheaper but only smooths the edges of polygons. When using FSR upscaling, AMD
     * recommends exposing the following values as preset options to users "Ultra Quality: 0.77",
     * "Quality: 0.67", "Balanced: 0.59", "Performance: 0.5" instead of exposing the entire scale. To
     * control this property on the root viewport, set the `ProjectSettings.rendering/scaling_3d/scale`
     * project setting.
     *
     * Generated from Godot docs: Viewport.set_scaling_3d_scale
     */
    fun setScaling3dScale(scale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setScaling3dScaleBind, segment, scale)
    }

    /**
     * Scales the 3D render buffer based on the viewport size uses an image filter specified in
     * `ProjectSettings.rendering/scaling_3d/mode` to scale the output image to the full viewport size.
     * Values lower than `1.0` can be used to speed up 3D rendering at the cost of quality
     * (undersampling). Values greater than `1.0` are only valid for bilinear mode and can be used to
     * improve 3D rendering quality at a high performance cost (supersampling). See also
     * `ProjectSettings.rendering/anti_aliasing/quality/msaa_3d` for multi-sample antialiasing, which
     * is significantly cheaper but only smooths the edges of polygons. When using FSR upscaling, AMD
     * recommends exposing the following values as preset options to users "Ultra Quality: 0.77",
     * "Quality: 0.67", "Balanced: 0.59", "Performance: 0.5" instead of exposing the entire scale. To
     * control this property on the root viewport, set the `ProjectSettings.rendering/scaling_3d/scale`
     * project setting.
     *
     * Generated from Godot docs: Viewport.get_scaling_3d_scale
     */
    fun getScaling3dScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getScaling3dScaleBind, segment)
    }

    /**
     * Determines how sharp the upscaled image will be when using the FSR upscaling mode. Sharpness
     * halves with every whole number. Values go from 0.0 (sharpest) to 2.0. Values above 2.0 won't
     * make a visible difference. To control this property on the root viewport, set the
     * `ProjectSettings.rendering/scaling_3d/fsr_sharpness` project setting.
     *
     * Generated from Godot docs: Viewport.set_fsr_sharpness
     */
    fun setFsrSharpness(fsrSharpness: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFsrSharpnessBind, segment, fsrSharpness)
    }

    /**
     * Determines how sharp the upscaled image will be when using the FSR upscaling mode. Sharpness
     * halves with every whole number. Values go from 0.0 (sharpest) to 2.0. Values above 2.0 won't
     * make a visible difference. To control this property on the root viewport, set the
     * `ProjectSettings.rendering/scaling_3d/fsr_sharpness` project setting.
     *
     * Generated from Godot docs: Viewport.get_fsr_sharpness
     */
    fun getFsrSharpness(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFsrSharpnessBind, segment)
    }

    /**
     * Affects the final texture sharpness by reading from a lower or higher mipmap (also called
     * "texture LOD bias"). Negative values make mipmapped textures sharper but grainier when viewed at
     * a distance, while positive values make mipmapped textures blurrier (even when up close).
     * Enabling temporal antialiasing (`use_taa`) will automatically apply a `-0.5` offset to this
     * value, while enabling FXAA (`screen_space_aa`) will automatically apply a `-0.25` offset to this
     * value. If both TAA and FXAA are enabled at the same time, an offset of `-0.75` is applied to
     * this value. To control this property on the root viewport, set the
     * `ProjectSettings.rendering/textures/default_filters/texture_mipmap_bias` project setting. Note:
     * If `scaling_3d_scale` is lower than `1.0` (exclusive), `texture_mipmap_bias` is used to adjust
     * the automatic mipmap bias which is calculated internally based on the scale factor. The formula
     * for this is `log2(scaling_3d_scale) + mipmap_bias`. Note: This property is only supported in the
     * Forward+ and Mobile renderers, not Compatibility. In Compatibility, this property is always
     * treated as if it was set to `0.0`.
     *
     * Generated from Godot docs: Viewport.set_texture_mipmap_bias
     */
    fun setTextureMipmapBias(textureMipmapBias: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTextureMipmapBiasBind, segment, textureMipmapBias)
    }

    /**
     * Affects the final texture sharpness by reading from a lower or higher mipmap (also called
     * "texture LOD bias"). Negative values make mipmapped textures sharper but grainier when viewed at
     * a distance, while positive values make mipmapped textures blurrier (even when up close).
     * Enabling temporal antialiasing (`use_taa`) will automatically apply a `-0.5` offset to this
     * value, while enabling FXAA (`screen_space_aa`) will automatically apply a `-0.25` offset to this
     * value. If both TAA and FXAA are enabled at the same time, an offset of `-0.75` is applied to
     * this value. To control this property on the root viewport, set the
     * `ProjectSettings.rendering/textures/default_filters/texture_mipmap_bias` project setting. Note:
     * If `scaling_3d_scale` is lower than `1.0` (exclusive), `texture_mipmap_bias` is used to adjust
     * the automatic mipmap bias which is calculated internally based on the scale factor. The formula
     * for this is `log2(scaling_3d_scale) + mipmap_bias`. Note: This property is only supported in the
     * Forward+ and Mobile renderers, not Compatibility. In Compatibility, this property is always
     * treated as if it was set to `0.0`.
     *
     * Generated from Godot docs: Viewport.get_texture_mipmap_bias
     */
    fun getTextureMipmapBias(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTextureMipmapBiasBind, segment)
    }

    /**
     * Sets the maximum number of samples to take when using anisotropic filtering on textures (as a
     * power of two). A higher sample count will result in sharper textures at oblique angles, but is
     * more expensive to compute. A value of `0` forcibly disables anisotropic filtering, even on
     * materials where it is enabled. The anisotropic filtering level also affects decals and light
     * projectors if they are configured to use anisotropic filtering. See
     * `ProjectSettings.rendering/textures/decals/filter` and
     * `ProjectSettings.rendering/textures/light_projectors/filter`. Note: In 3D, for this setting to
     * have an effect, set `BaseMaterial3D.texture_filter` to
     * `BaseMaterial3D.TEXTURE_FILTER_LINEAR_WITH_MIPMAPS_ANISOTROPIC` or
     * `BaseMaterial3D.TEXTURE_FILTER_NEAREST_WITH_MIPMAPS_ANISOTROPIC` on materials. Note: In 2D, for
     * this setting to have an effect, set `CanvasItem.texture_filter` to
     * `CanvasItem.TEXTURE_FILTER_LINEAR_WITH_MIPMAPS_ANISOTROPIC` or
     * `CanvasItem.TEXTURE_FILTER_NEAREST_WITH_MIPMAPS_ANISOTROPIC` on the `CanvasItem` node displaying
     * the texture (or in `CanvasTexture`). However, anisotropic filtering is rarely useful in 2D, so
     * only enable it for textures in 2D if it makes a meaningful visual difference.
     *
     * Generated from Godot docs: Viewport.set_anisotropic_filtering_level
     */
    fun setAnisotropicFilteringLevel(anisotropicFilteringLevel: Long) {
        ObjectCalls.ptrcallWithLongArg(setAnisotropicFilteringLevelBind, segment, anisotropicFilteringLevel)
    }

    /**
     * Sets the maximum number of samples to take when using anisotropic filtering on textures (as a
     * power of two). A higher sample count will result in sharper textures at oblique angles, but is
     * more expensive to compute. A value of `0` forcibly disables anisotropic filtering, even on
     * materials where it is enabled. The anisotropic filtering level also affects decals and light
     * projectors if they are configured to use anisotropic filtering. See
     * `ProjectSettings.rendering/textures/decals/filter` and
     * `ProjectSettings.rendering/textures/light_projectors/filter`. Note: In 3D, for this setting to
     * have an effect, set `BaseMaterial3D.texture_filter` to
     * `BaseMaterial3D.TEXTURE_FILTER_LINEAR_WITH_MIPMAPS_ANISOTROPIC` or
     * `BaseMaterial3D.TEXTURE_FILTER_NEAREST_WITH_MIPMAPS_ANISOTROPIC` on materials. Note: In 2D, for
     * this setting to have an effect, set `CanvasItem.texture_filter` to
     * `CanvasItem.TEXTURE_FILTER_LINEAR_WITH_MIPMAPS_ANISOTROPIC` or
     * `CanvasItem.TEXTURE_FILTER_NEAREST_WITH_MIPMAPS_ANISOTROPIC` on the `CanvasItem` node displaying
     * the texture (or in `CanvasTexture`). However, anisotropic filtering is rarely useful in 2D, so
     * only enable it for textures in 2D if it makes a meaningful visual difference.
     *
     * Generated from Godot docs: Viewport.get_anisotropic_filtering_level
     */
    fun getAnisotropicFilteringLevel(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAnisotropicFilteringLevelBind, segment)
    }

    /**
     * The Variable Rate Shading (VRS) mode that is used for this viewport. Note, if hardware does not
     * support VRS this property is ignored.
     *
     * Generated from Godot docs: Viewport.set_vrs_mode
     */
    fun setVrsMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setVrsModeBind, segment, mode)
    }

    /**
     * The Variable Rate Shading (VRS) mode that is used for this viewport. Note, if hardware does not
     * support VRS this property is ignored.
     *
     * Generated from Godot docs: Viewport.get_vrs_mode
     */
    fun getVrsMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getVrsModeBind, segment)
    }

    /**
     * Sets the update mode for Variable Rate Shading (VRS) for the viewport. VRS requires the input
     * texture to be converted to the format usable by the VRS method supported by the hardware. The
     * update mode defines how often this happens. If the GPU does not support VRS, or VRS is not
     * enabled, this property is ignored.
     *
     * Generated from Godot docs: Viewport.set_vrs_update_mode
     */
    fun setVrsUpdateMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setVrsUpdateModeBind, segment, mode)
    }

    /**
     * Sets the update mode for Variable Rate Shading (VRS) for the viewport. VRS requires the input
     * texture to be converted to the format usable by the VRS method supported by the hardware. The
     * update mode defines how often this happens. If the GPU does not support VRS, or VRS is not
     * enabled, this property is ignored.
     *
     * Generated from Godot docs: Viewport.get_vrs_update_mode
     */
    fun getVrsUpdateMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getVrsUpdateModeBind, segment)
    }

    /**
     * Texture to use when `vrs_mode` is set to `Viewport.VRS_TEXTURE`. The texture must use a lossless
     * compression format so that colors can be matched precisely. The following VRS densities are
     * mapped to various colors, with brighter colors representing a lower level of shading precision:
     *
     * Generated from Godot docs: Viewport.set_vrs_texture
     */
    fun setVrsTexture(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setVrsTextureBind, segment, listOf(texture?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Texture to use when `vrs_mode` is set to `Viewport.VRS_TEXTURE`. The texture must use a lossless
     * compression format so that colors can be matched precisely. The following VRS densities are
     * mapped to various colors, with brighter colors representing a lower level of shading precision:
     *
     * Generated from Godot docs: Viewport.get_vrs_texture
     */
    fun getVrsTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getVrsTextureBind, segment))
    }

    object Signals {
        const val sizeChanged: String = "size_changed"
        const val guiFocusChanged: String = "gui_focus_changed"
    }

    companion object {
        const val SHADOW_ATLAS_QUADRANT_SUBDIV_DISABLED: Long = 0L
        const val SHADOW_ATLAS_QUADRANT_SUBDIV_1: Long = 1L
        const val SHADOW_ATLAS_QUADRANT_SUBDIV_4: Long = 2L
        const val SHADOW_ATLAS_QUADRANT_SUBDIV_16: Long = 3L
        const val SHADOW_ATLAS_QUADRANT_SUBDIV_64: Long = 4L
        const val SHADOW_ATLAS_QUADRANT_SUBDIV_256: Long = 5L
        const val SHADOW_ATLAS_QUADRANT_SUBDIV_1024: Long = 6L
        const val SHADOW_ATLAS_QUADRANT_SUBDIV_MAX: Long = 7L
        const val SCALING_3D_MODE_BILINEAR: Long = 0L
        const val SCALING_3D_MODE_FSR: Long = 1L
        const val SCALING_3D_MODE_FSR2: Long = 2L
        const val SCALING_3D_MODE_METALFX_SPATIAL: Long = 3L
        const val SCALING_3D_MODE_METALFX_TEMPORAL: Long = 4L
        const val SCALING_3D_MODE_MAX: Long = 5L
        const val MSAA_DISABLED: Long = 0L
        const val MSAA_2X: Long = 1L
        const val MSAA_4X: Long = 2L
        const val MSAA_8X: Long = 3L
        const val MSAA_MAX: Long = 4L
        const val ANISOTROPY_DISABLED: Long = 0L
        const val ANISOTROPY_2X: Long = 1L
        const val ANISOTROPY_4X: Long = 2L
        const val ANISOTROPY_8X: Long = 3L
        const val ANISOTROPY_16X: Long = 4L
        const val ANISOTROPY_MAX: Long = 5L
        const val SCREEN_SPACE_AA_DISABLED: Long = 0L
        const val SCREEN_SPACE_AA_FXAA: Long = 1L
        const val SCREEN_SPACE_AA_SMAA: Long = 2L
        const val SCREEN_SPACE_AA_MAX: Long = 3L
        const val RENDER_INFO_OBJECTS_IN_FRAME: Long = 0L
        const val RENDER_INFO_PRIMITIVES_IN_FRAME: Long = 1L
        const val RENDER_INFO_DRAW_CALLS_IN_FRAME: Long = 2L
        const val RENDER_INFO_MAX: Long = 3L
        const val RENDER_INFO_TYPE_VISIBLE: Long = 0L
        const val RENDER_INFO_TYPE_SHADOW: Long = 1L
        const val RENDER_INFO_TYPE_CANVAS: Long = 2L
        const val RENDER_INFO_TYPE_MAX: Long = 3L
        const val DEBUG_DRAW_DISABLED: Long = 0L
        const val DEBUG_DRAW_UNSHADED: Long = 1L
        const val DEBUG_DRAW_LIGHTING: Long = 2L
        const val DEBUG_DRAW_OVERDRAW: Long = 3L
        const val DEBUG_DRAW_WIREFRAME: Long = 4L
        const val DEBUG_DRAW_NORMAL_BUFFER: Long = 5L
        const val DEBUG_DRAW_VOXEL_GI_ALBEDO: Long = 6L
        const val DEBUG_DRAW_VOXEL_GI_LIGHTING: Long = 7L
        const val DEBUG_DRAW_VOXEL_GI_EMISSION: Long = 8L
        const val DEBUG_DRAW_SHADOW_ATLAS: Long = 9L
        const val DEBUG_DRAW_DIRECTIONAL_SHADOW_ATLAS: Long = 10L
        const val DEBUG_DRAW_SCENE_LUMINANCE: Long = 11L
        const val DEBUG_DRAW_SSAO: Long = 12L
        const val DEBUG_DRAW_SSIL: Long = 13L
        const val DEBUG_DRAW_PSSM_SPLITS: Long = 14L
        const val DEBUG_DRAW_DECAL_ATLAS: Long = 15L
        const val DEBUG_DRAW_SDFGI: Long = 16L
        const val DEBUG_DRAW_SDFGI_PROBES: Long = 17L
        const val DEBUG_DRAW_GI_BUFFER: Long = 18L
        const val DEBUG_DRAW_DISABLE_LOD: Long = 19L
        const val DEBUG_DRAW_CLUSTER_OMNI_LIGHTS: Long = 20L
        const val DEBUG_DRAW_CLUSTER_SPOT_LIGHTS: Long = 21L
        const val DEBUG_DRAW_CLUSTER_DECALS: Long = 22L
        const val DEBUG_DRAW_CLUSTER_REFLECTION_PROBES: Long = 23L
        const val DEBUG_DRAW_OCCLUDERS: Long = 24L
        const val DEBUG_DRAW_MOTION_VECTORS: Long = 25L
        const val DEBUG_DRAW_INTERNAL_BUFFER: Long = 26L
        const val DEFAULT_CANVAS_ITEM_TEXTURE_FILTER_NEAREST: Long = 0L
        const val DEFAULT_CANVAS_ITEM_TEXTURE_FILTER_LINEAR: Long = 1L
        const val DEFAULT_CANVAS_ITEM_TEXTURE_FILTER_LINEAR_WITH_MIPMAPS: Long = 2L
        const val DEFAULT_CANVAS_ITEM_TEXTURE_FILTER_NEAREST_WITH_MIPMAPS: Long = 3L
        const val DEFAULT_CANVAS_ITEM_TEXTURE_FILTER_MAX: Long = 4L
        const val DEFAULT_CANVAS_ITEM_TEXTURE_REPEAT_DISABLED: Long = 0L
        const val DEFAULT_CANVAS_ITEM_TEXTURE_REPEAT_ENABLED: Long = 1L
        const val DEFAULT_CANVAS_ITEM_TEXTURE_REPEAT_MIRROR: Long = 2L
        const val DEFAULT_CANVAS_ITEM_TEXTURE_REPEAT_MAX: Long = 3L
        const val SDF_OVERSIZE_100_PERCENT: Long = 0L
        const val SDF_OVERSIZE_120_PERCENT: Long = 1L
        const val SDF_OVERSIZE_150_PERCENT: Long = 2L
        const val SDF_OVERSIZE_200_PERCENT: Long = 3L
        const val SDF_OVERSIZE_MAX: Long = 4L
        const val SDF_SCALE_100_PERCENT: Long = 0L
        const val SDF_SCALE_50_PERCENT: Long = 1L
        const val SDF_SCALE_25_PERCENT: Long = 2L
        const val SDF_SCALE_MAX: Long = 3L
        const val VRS_DISABLED: Long = 0L
        const val VRS_TEXTURE: Long = 1L
        const val VRS_XR: Long = 2L
        const val VRS_MAX: Long = 3L
        const val VRS_UPDATE_DISABLED: Long = 0L
        const val VRS_UPDATE_ONCE: Long = 1L
        const val VRS_UPDATE_ALWAYS: Long = 2L
        const val VRS_UPDATE_MAX: Long = 3L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): Viewport? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): Viewport? =
            if (handle.address() == 0L) null else Viewport(GodotHandle(handle))

        private const val SET_WORLD_2D_HASH = 2736080068L
        private val setWorld2dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_world_2d", SET_WORLD_2D_HASH)
        }

        private const val GET_WORLD_2D_HASH = 2339128592L
        private val getWorld2dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_world_2d", GET_WORLD_2D_HASH)
        }

        private const val FIND_WORLD_2D_HASH = 2339128592L
        private val findWorld2dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "find_world_2d", FIND_WORLD_2D_HASH)
        }

        private const val SET_CANVAS_TRANSFORM_HASH = 2761652528L
        private val setCanvasTransformBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_canvas_transform", SET_CANVAS_TRANSFORM_HASH)
        }

        private const val GET_CANVAS_TRANSFORM_HASH = 3814499831L
        private val getCanvasTransformBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_canvas_transform", GET_CANVAS_TRANSFORM_HASH)
        }

        private const val SET_GLOBAL_CANVAS_TRANSFORM_HASH = 2761652528L
        private val setGlobalCanvasTransformBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_global_canvas_transform", SET_GLOBAL_CANVAS_TRANSFORM_HASH)
        }

        private const val GET_GLOBAL_CANVAS_TRANSFORM_HASH = 3814499831L
        private val getGlobalCanvasTransformBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_global_canvas_transform", GET_GLOBAL_CANVAS_TRANSFORM_HASH)
        }

        private const val GET_STRETCH_TRANSFORM_HASH = 3814499831L
        private val getStretchTransformBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_stretch_transform", GET_STRETCH_TRANSFORM_HASH)
        }

        private const val GET_FINAL_TRANSFORM_HASH = 3814499831L
        private val getFinalTransformBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_final_transform", GET_FINAL_TRANSFORM_HASH)
        }

        private const val GET_SCREEN_TRANSFORM_HASH = 3814499831L
        private val getScreenTransformBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_screen_transform", GET_SCREEN_TRANSFORM_HASH)
        }

        private const val GET_VISIBLE_RECT_HASH = 1639390495L
        private val getVisibleRectBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_visible_rect", GET_VISIBLE_RECT_HASH)
        }

        private const val SET_TRANSPARENT_BACKGROUND_HASH = 2586408642L
        private val setTransparentBackgroundBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_transparent_background", SET_TRANSPARENT_BACKGROUND_HASH)
        }

        private const val HAS_TRANSPARENT_BACKGROUND_HASH = 36873697L
        private val hasTransparentBackgroundBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "has_transparent_background", HAS_TRANSPARENT_BACKGROUND_HASH)
        }

        private const val SET_USE_HDR_2D_HASH = 2586408642L
        private val setUseHdr2dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_use_hdr_2d", SET_USE_HDR_2D_HASH)
        }

        private const val IS_USING_HDR_2D_HASH = 36873697L
        private val isUsingHdr2dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "is_using_hdr_2d", IS_USING_HDR_2D_HASH)
        }

        private const val SET_MSAA_2D_HASH = 3330258708L
        private val setMsaa2dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_msaa_2d", SET_MSAA_2D_HASH)
        }

        private const val GET_MSAA_2D_HASH = 2542055527L
        private val getMsaa2dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_msaa_2d", GET_MSAA_2D_HASH)
        }

        private const val SET_MSAA_3D_HASH = 3330258708L
        private val setMsaa3dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_msaa_3d", SET_MSAA_3D_HASH)
        }

        private const val GET_MSAA_3D_HASH = 2542055527L
        private val getMsaa3dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_msaa_3d", GET_MSAA_3D_HASH)
        }

        private const val SET_SCREEN_SPACE_AA_HASH = 3544169389L
        private val setScreenSpaceAaBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_screen_space_aa", SET_SCREEN_SPACE_AA_HASH)
        }

        private const val GET_SCREEN_SPACE_AA_HASH = 1390814124L
        private val getScreenSpaceAaBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_screen_space_aa", GET_SCREEN_SPACE_AA_HASH)
        }

        private const val SET_USE_TAA_HASH = 2586408642L
        private val setUseTaaBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_use_taa", SET_USE_TAA_HASH)
        }

        private const val IS_USING_TAA_HASH = 36873697L
        private val isUsingTaaBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "is_using_taa", IS_USING_TAA_HASH)
        }

        private const val SET_USE_DEBANDING_HASH = 2586408642L
        private val setUseDebandingBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_use_debanding", SET_USE_DEBANDING_HASH)
        }

        private const val IS_USING_DEBANDING_HASH = 36873697L
        private val isUsingDebandingBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "is_using_debanding", IS_USING_DEBANDING_HASH)
        }

        private const val SET_USE_OCCLUSION_CULLING_HASH = 2586408642L
        private val setUseOcclusionCullingBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_use_occlusion_culling", SET_USE_OCCLUSION_CULLING_HASH)
        }

        private const val IS_USING_OCCLUSION_CULLING_HASH = 36873697L
        private val isUsingOcclusionCullingBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "is_using_occlusion_culling", IS_USING_OCCLUSION_CULLING_HASH)
        }

        private const val SET_DEBUG_DRAW_HASH = 1970246205L
        private val setDebugDrawBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_debug_draw", SET_DEBUG_DRAW_HASH)
        }

        private const val GET_DEBUG_DRAW_HASH = 579191299L
        private val getDebugDrawBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_debug_draw", GET_DEBUG_DRAW_HASH)
        }

        private const val SET_USE_OVERSAMPLING_HASH = 2586408642L
        private val setUseOversamplingBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_use_oversampling", SET_USE_OVERSAMPLING_HASH)
        }

        private const val IS_USING_OVERSAMPLING_HASH = 36873697L
        private val isUsingOversamplingBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "is_using_oversampling", IS_USING_OVERSAMPLING_HASH)
        }

        private const val SET_OVERSAMPLING_OVERRIDE_HASH = 373806689L
        private val setOversamplingOverrideBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_oversampling_override", SET_OVERSAMPLING_OVERRIDE_HASH)
        }

        private const val GET_OVERSAMPLING_OVERRIDE_HASH = 1740695150L
        private val getOversamplingOverrideBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_oversampling_override", GET_OVERSAMPLING_OVERRIDE_HASH)
        }

        private const val GET_OVERSAMPLING_HASH = 1740695150L
        private val getOversamplingBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_oversampling", GET_OVERSAMPLING_HASH)
        }

        private const val GET_RENDER_INFO_HASH = 481977019L
        private val getRenderInfoBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_render_info", GET_RENDER_INFO_HASH)
        }

        private const val GET_TEXTURE_HASH = 1746695840L
        private val getTextureBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_texture", GET_TEXTURE_HASH)
        }

        private const val SET_PHYSICS_OBJECT_PICKING_HASH = 2586408642L
        private val setPhysicsObjectPickingBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_physics_object_picking", SET_PHYSICS_OBJECT_PICKING_HASH)
        }

        private const val GET_PHYSICS_OBJECT_PICKING_HASH = 2240911060L
        private val getPhysicsObjectPickingBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_physics_object_picking", GET_PHYSICS_OBJECT_PICKING_HASH)
        }

        private const val SET_PHYSICS_OBJECT_PICKING_SORT_HASH = 2586408642L
        private val setPhysicsObjectPickingSortBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_physics_object_picking_sort", SET_PHYSICS_OBJECT_PICKING_SORT_HASH)
        }

        private const val GET_PHYSICS_OBJECT_PICKING_SORT_HASH = 2240911060L
        private val getPhysicsObjectPickingSortBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_physics_object_picking_sort", GET_PHYSICS_OBJECT_PICKING_SORT_HASH)
        }

        private const val SET_PHYSICS_OBJECT_PICKING_FIRST_ONLY_HASH = 2586408642L
        private val setPhysicsObjectPickingFirstOnlyBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_physics_object_picking_first_only", SET_PHYSICS_OBJECT_PICKING_FIRST_ONLY_HASH)
        }

        private const val GET_PHYSICS_OBJECT_PICKING_FIRST_ONLY_HASH = 2240911060L
        private val getPhysicsObjectPickingFirstOnlyBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_physics_object_picking_first_only", GET_PHYSICS_OBJECT_PICKING_FIRST_ONLY_HASH)
        }

        private const val GET_VIEWPORT_RID_HASH = 2944877500L
        private val getViewportRidBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_viewport_rid", GET_VIEWPORT_RID_HASH)
        }

        private const val PUSH_TEXT_INPUT_HASH = 83702148L
        private val pushTextInputBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "push_text_input", PUSH_TEXT_INPUT_HASH)
        }

        private const val PUSH_INPUT_HASH = 3644664830L
        private val pushInputBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "push_input", PUSH_INPUT_HASH)
        }

        private const val PUSH_UNHANDLED_INPUT_HASH = 3644664830L
        private val pushUnhandledInputBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "push_unhandled_input", PUSH_UNHANDLED_INPUT_HASH)
        }

        private const val NOTIFY_MOUSE_ENTERED_HASH = 3218959716L
        private val notifyMouseEnteredBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "notify_mouse_entered", NOTIFY_MOUSE_ENTERED_HASH)
        }

        private const val NOTIFY_MOUSE_EXITED_HASH = 3218959716L
        private val notifyMouseExitedBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "notify_mouse_exited", NOTIFY_MOUSE_EXITED_HASH)
        }

        private const val GET_MOUSE_POSITION_HASH = 3341600327L
        private val getMousePositionBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_mouse_position", GET_MOUSE_POSITION_HASH)
        }

        private const val WARP_MOUSE_HASH = 743155724L
        private val warpMouseBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "warp_mouse", WARP_MOUSE_HASH)
        }

        private const val UPDATE_MOUSE_CURSOR_STATE_HASH = 3218959716L
        private val updateMouseCursorStateBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "update_mouse_cursor_state", UPDATE_MOUSE_CURSOR_STATE_HASH)
        }

        private const val GUI_CANCEL_DRAG_HASH = 3218959716L
        private val guiCancelDragBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "gui_cancel_drag", GUI_CANCEL_DRAG_HASH)
        }

        private const val GUI_GET_DRAG_DATA_HASH = 1214101251L
        private val guiGetDragDataBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "gui_get_drag_data", GUI_GET_DRAG_DATA_HASH)
        }

        private const val GUI_GET_DRAG_DESCRIPTION_HASH = 201670096L
        private val guiGetDragDescriptionBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "gui_get_drag_description", GUI_GET_DRAG_DESCRIPTION_HASH)
        }

        private const val GUI_SET_DRAG_DESCRIPTION_HASH = 83702148L
        private val guiSetDragDescriptionBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "gui_set_drag_description", GUI_SET_DRAG_DESCRIPTION_HASH)
        }

        private const val GUI_IS_DRAGGING_HASH = 36873697L
        private val guiIsDraggingBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "gui_is_dragging", GUI_IS_DRAGGING_HASH)
        }

        private const val GUI_IS_DRAG_SUCCESSFUL_HASH = 36873697L
        private val guiIsDragSuccessfulBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "gui_is_drag_successful", GUI_IS_DRAG_SUCCESSFUL_HASH)
        }

        private const val GUI_RELEASE_FOCUS_HASH = 3218959716L
        private val guiReleaseFocusBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "gui_release_focus", GUI_RELEASE_FOCUS_HASH)
        }

        private const val GUI_GET_FOCUS_OWNER_HASH = 2783021301L
        private val guiGetFocusOwnerBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "gui_get_focus_owner", GUI_GET_FOCUS_OWNER_HASH)
        }

        private const val GUI_GET_HOVERED_CONTROL_HASH = 2783021301L
        private val guiGetHoveredControlBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "gui_get_hovered_control", GUI_GET_HOVERED_CONTROL_HASH)
        }

        private const val SET_DISABLE_INPUT_HASH = 2586408642L
        private val setDisableInputBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_disable_input", SET_DISABLE_INPUT_HASH)
        }

        private const val IS_INPUT_DISABLED_HASH = 36873697L
        private val isInputDisabledBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "is_input_disabled", IS_INPUT_DISABLED_HASH)
        }

        private const val SET_POSITIONAL_SHADOW_ATLAS_SIZE_HASH = 1286410249L
        private val setPositionalShadowAtlasSizeBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_positional_shadow_atlas_size", SET_POSITIONAL_SHADOW_ATLAS_SIZE_HASH)
        }

        private const val GET_POSITIONAL_SHADOW_ATLAS_SIZE_HASH = 3905245786L
        private val getPositionalShadowAtlasSizeBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_positional_shadow_atlas_size", GET_POSITIONAL_SHADOW_ATLAS_SIZE_HASH)
        }

        private const val SET_POSITIONAL_SHADOW_ATLAS_16_BITS_HASH = 2586408642L
        private val setPositionalShadowAtlas16BitsBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_positional_shadow_atlas_16_bits", SET_POSITIONAL_SHADOW_ATLAS_16_BITS_HASH)
        }

        private const val GET_POSITIONAL_SHADOW_ATLAS_16_BITS_HASH = 36873697L
        private val getPositionalShadowAtlas16BitsBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_positional_shadow_atlas_16_bits", GET_POSITIONAL_SHADOW_ATLAS_16_BITS_HASH)
        }

        private const val SET_SNAP_CONTROLS_TO_PIXELS_HASH = 2586408642L
        private val setSnapControlsToPixelsBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_snap_controls_to_pixels", SET_SNAP_CONTROLS_TO_PIXELS_HASH)
        }

        private const val IS_SNAP_CONTROLS_TO_PIXELS_ENABLED_HASH = 36873697L
        private val isSnapControlsToPixelsEnabledBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "is_snap_controls_to_pixels_enabled", IS_SNAP_CONTROLS_TO_PIXELS_ENABLED_HASH)
        }

        private const val SET_SNAP_2D_TRANSFORMS_TO_PIXEL_HASH = 2586408642L
        private val setSnap2dTransformsToPixelBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_snap_2d_transforms_to_pixel", SET_SNAP_2D_TRANSFORMS_TO_PIXEL_HASH)
        }

        private const val IS_SNAP_2D_TRANSFORMS_TO_PIXEL_ENABLED_HASH = 36873697L
        private val isSnap2dTransformsToPixelEnabledBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "is_snap_2d_transforms_to_pixel_enabled", IS_SNAP_2D_TRANSFORMS_TO_PIXEL_ENABLED_HASH)
        }

        private const val SET_SNAP_2D_VERTICES_TO_PIXEL_HASH = 2586408642L
        private val setSnap2dVerticesToPixelBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_snap_2d_vertices_to_pixel", SET_SNAP_2D_VERTICES_TO_PIXEL_HASH)
        }

        private const val IS_SNAP_2D_VERTICES_TO_PIXEL_ENABLED_HASH = 36873697L
        private val isSnap2dVerticesToPixelEnabledBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "is_snap_2d_vertices_to_pixel_enabled", IS_SNAP_2D_VERTICES_TO_PIXEL_ENABLED_HASH)
        }

        private const val SET_POSITIONAL_SHADOW_ATLAS_QUADRANT_SUBDIV_HASH = 2596956071L
        private val setPositionalShadowAtlasQuadrantSubdivBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_positional_shadow_atlas_quadrant_subdiv", SET_POSITIONAL_SHADOW_ATLAS_QUADRANT_SUBDIV_HASH)
        }

        private const val GET_POSITIONAL_SHADOW_ATLAS_QUADRANT_SUBDIV_HASH = 2676778355L
        private val getPositionalShadowAtlasQuadrantSubdivBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_positional_shadow_atlas_quadrant_subdiv", GET_POSITIONAL_SHADOW_ATLAS_QUADRANT_SUBDIV_HASH)
        }

        private const val SET_INPUT_AS_HANDLED_HASH = 3218959716L
        private val setInputAsHandledBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_input_as_handled", SET_INPUT_AS_HANDLED_HASH)
        }

        private const val IS_INPUT_HANDLED_HASH = 36873697L
        private val isInputHandledBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "is_input_handled", IS_INPUT_HANDLED_HASH)
        }

        private const val SET_HANDLE_INPUT_LOCALLY_HASH = 2586408642L
        private val setHandleInputLocallyBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_handle_input_locally", SET_HANDLE_INPUT_LOCALLY_HASH)
        }

        private const val IS_HANDLING_INPUT_LOCALLY_HASH = 36873697L
        private val isHandlingInputLocallyBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "is_handling_input_locally", IS_HANDLING_INPUT_LOCALLY_HASH)
        }

        private const val SET_DEFAULT_CANVAS_ITEM_TEXTURE_FILTER_HASH = 2815160100L
        private val setDefaultCanvasItemTextureFilterBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_default_canvas_item_texture_filter", SET_DEFAULT_CANVAS_ITEM_TEXTURE_FILTER_HASH)
        }

        private const val GET_DEFAULT_CANVAS_ITEM_TEXTURE_FILTER_HASH = 896601198L
        private val getDefaultCanvasItemTextureFilterBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_default_canvas_item_texture_filter", GET_DEFAULT_CANVAS_ITEM_TEXTURE_FILTER_HASH)
        }

        private const val SET_EMBEDDING_SUBWINDOWS_HASH = 2586408642L
        private val setEmbeddingSubwindowsBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_embedding_subwindows", SET_EMBEDDING_SUBWINDOWS_HASH)
        }

        private const val IS_EMBEDDING_SUBWINDOWS_HASH = 36873697L
        private val isEmbeddingSubwindowsBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "is_embedding_subwindows", IS_EMBEDDING_SUBWINDOWS_HASH)
        }

        private const val GET_EMBEDDED_SUBWINDOWS_HASH = 3995934104L
        private val getEmbeddedSubwindowsBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_embedded_subwindows", GET_EMBEDDED_SUBWINDOWS_HASH)
        }

        private const val SET_DRAG_THRESHOLD_HASH = 1286410249L
        private val setDragThresholdBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_drag_threshold", SET_DRAG_THRESHOLD_HASH)
        }

        private const val GET_DRAG_THRESHOLD_HASH = 3905245786L
        private val getDragThresholdBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_drag_threshold", GET_DRAG_THRESHOLD_HASH)
        }

        private const val SET_CANVAS_CULL_MASK_HASH = 1286410249L
        private val setCanvasCullMaskBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_canvas_cull_mask", SET_CANVAS_CULL_MASK_HASH)
        }

        private const val GET_CANVAS_CULL_MASK_HASH = 3905245786L
        private val getCanvasCullMaskBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_canvas_cull_mask", GET_CANVAS_CULL_MASK_HASH)
        }

        private const val SET_CANVAS_CULL_MASK_BIT_HASH = 300928843L
        private val setCanvasCullMaskBitBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_canvas_cull_mask_bit", SET_CANVAS_CULL_MASK_BIT_HASH)
        }

        private const val GET_CANVAS_CULL_MASK_BIT_HASH = 1116898809L
        private val getCanvasCullMaskBitBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_canvas_cull_mask_bit", GET_CANVAS_CULL_MASK_BIT_HASH)
        }

        private const val SET_DEFAULT_CANVAS_ITEM_TEXTURE_REPEAT_HASH = 1658513413L
        private val setDefaultCanvasItemTextureRepeatBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_default_canvas_item_texture_repeat", SET_DEFAULT_CANVAS_ITEM_TEXTURE_REPEAT_HASH)
        }

        private const val GET_DEFAULT_CANVAS_ITEM_TEXTURE_REPEAT_HASH = 4049774160L
        private val getDefaultCanvasItemTextureRepeatBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_default_canvas_item_texture_repeat", GET_DEFAULT_CANVAS_ITEM_TEXTURE_REPEAT_HASH)
        }

        private const val SET_SDF_OVERSIZE_HASH = 2574159017L
        private val setSdfOversizeBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_sdf_oversize", SET_SDF_OVERSIZE_HASH)
        }

        private const val GET_SDF_OVERSIZE_HASH = 2631427510L
        private val getSdfOversizeBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_sdf_oversize", GET_SDF_OVERSIZE_HASH)
        }

        private const val SET_SDF_SCALE_HASH = 1402773951L
        private val setSdfScaleBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_sdf_scale", SET_SDF_SCALE_HASH)
        }

        private const val GET_SDF_SCALE_HASH = 3162688184L
        private val getSdfScaleBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_sdf_scale", GET_SDF_SCALE_HASH)
        }

        private const val SET_MESH_LOD_THRESHOLD_HASH = 373806689L
        private val setMeshLodThresholdBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_mesh_lod_threshold", SET_MESH_LOD_THRESHOLD_HASH)
        }

        private const val GET_MESH_LOD_THRESHOLD_HASH = 1740695150L
        private val getMeshLodThresholdBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_mesh_lod_threshold", GET_MESH_LOD_THRESHOLD_HASH)
        }

        private const val SET_AS_AUDIO_LISTENER_2D_HASH = 2586408642L
        private val setAsAudioListener2dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_as_audio_listener_2d", SET_AS_AUDIO_LISTENER_2D_HASH)
        }

        private const val IS_AUDIO_LISTENER_2D_HASH = 36873697L
        private val isAudioListener2dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "is_audio_listener_2d", IS_AUDIO_LISTENER_2D_HASH)
        }

        private const val GET_AUDIO_LISTENER_2D_HASH = 1840977180L
        private val getAudioListener2dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_audio_listener_2d", GET_AUDIO_LISTENER_2D_HASH)
        }

        private const val GET_CAMERA_2D_HASH = 3551466917L
        private val getCamera2dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_camera_2d", GET_CAMERA_2D_HASH)
        }

        private const val SET_WORLD_3D_HASH = 1400875337L
        private val setWorld3dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_world_3d", SET_WORLD_3D_HASH)
        }

        private const val GET_WORLD_3D_HASH = 317588385L
        private val getWorld3dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_world_3d", GET_WORLD_3D_HASH)
        }

        private const val FIND_WORLD_3D_HASH = 317588385L
        private val findWorld3dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "find_world_3d", FIND_WORLD_3D_HASH)
        }

        private const val SET_USE_OWN_WORLD_3D_HASH = 2586408642L
        private val setUseOwnWorld3dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_use_own_world_3d", SET_USE_OWN_WORLD_3D_HASH)
        }

        private const val IS_USING_OWN_WORLD_3D_HASH = 36873697L
        private val isUsingOwnWorld3dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "is_using_own_world_3d", IS_USING_OWN_WORLD_3D_HASH)
        }

        private const val GET_AUDIO_LISTENER_3D_HASH = 3472246991L
        private val getAudioListener3dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_audio_listener_3d", GET_AUDIO_LISTENER_3D_HASH)
        }

        private const val GET_CAMERA_3D_HASH = 2285090890L
        private val getCamera3dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_camera_3d", GET_CAMERA_3D_HASH)
        }

        private const val SET_AS_AUDIO_LISTENER_3D_HASH = 2586408642L
        private val setAsAudioListener3dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_as_audio_listener_3d", SET_AS_AUDIO_LISTENER_3D_HASH)
        }

        private const val IS_AUDIO_LISTENER_3D_HASH = 36873697L
        private val isAudioListener3dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "is_audio_listener_3d", IS_AUDIO_LISTENER_3D_HASH)
        }

        private const val SET_DISABLE_3D_HASH = 2586408642L
        private val setDisable3dBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_disable_3d", SET_DISABLE_3D_HASH)
        }

        private const val IS_3D_DISABLED_HASH = 36873697L
        private val is3dDisabledBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "is_3d_disabled", IS_3D_DISABLED_HASH)
        }

        private const val SET_USE_XR_HASH = 2586408642L
        private val setUseXrBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_use_xr", SET_USE_XR_HASH)
        }

        private const val IS_USING_XR_HASH = 2240911060L
        private val isUsingXrBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "is_using_xr", IS_USING_XR_HASH)
        }

        private const val SET_SCALING_3D_MODE_HASH = 1531597597L
        private val setScaling3dModeBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_scaling_3d_mode", SET_SCALING_3D_MODE_HASH)
        }

        private const val GET_SCALING_3D_MODE_HASH = 2597660574L
        private val getScaling3dModeBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_scaling_3d_mode", GET_SCALING_3D_MODE_HASH)
        }

        private const val SET_SCALING_3D_SCALE_HASH = 373806689L
        private val setScaling3dScaleBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_scaling_3d_scale", SET_SCALING_3D_SCALE_HASH)
        }

        private const val GET_SCALING_3D_SCALE_HASH = 1740695150L
        private val getScaling3dScaleBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_scaling_3d_scale", GET_SCALING_3D_SCALE_HASH)
        }

        private const val SET_FSR_SHARPNESS_HASH = 373806689L
        private val setFsrSharpnessBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_fsr_sharpness", SET_FSR_SHARPNESS_HASH)
        }

        private const val GET_FSR_SHARPNESS_HASH = 1740695150L
        private val getFsrSharpnessBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_fsr_sharpness", GET_FSR_SHARPNESS_HASH)
        }

        private const val SET_TEXTURE_MIPMAP_BIAS_HASH = 373806689L
        private val setTextureMipmapBiasBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_texture_mipmap_bias", SET_TEXTURE_MIPMAP_BIAS_HASH)
        }

        private const val GET_TEXTURE_MIPMAP_BIAS_HASH = 1740695150L
        private val getTextureMipmapBiasBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_texture_mipmap_bias", GET_TEXTURE_MIPMAP_BIAS_HASH)
        }

        private const val SET_ANISOTROPIC_FILTERING_LEVEL_HASH = 3445583046L
        private val setAnisotropicFilteringLevelBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_anisotropic_filtering_level", SET_ANISOTROPIC_FILTERING_LEVEL_HASH)
        }

        private const val GET_ANISOTROPIC_FILTERING_LEVEL_HASH = 3991528932L
        private val getAnisotropicFilteringLevelBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_anisotropic_filtering_level", GET_ANISOTROPIC_FILTERING_LEVEL_HASH)
        }

        private const val SET_VRS_MODE_HASH = 2749867817L
        private val setVrsModeBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_vrs_mode", SET_VRS_MODE_HASH)
        }

        private const val GET_VRS_MODE_HASH = 349660525L
        private val getVrsModeBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_vrs_mode", GET_VRS_MODE_HASH)
        }

        private const val SET_VRS_UPDATE_MODE_HASH = 3182412319L
        private val setVrsUpdateModeBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_vrs_update_mode", SET_VRS_UPDATE_MODE_HASH)
        }

        private const val GET_VRS_UPDATE_MODE_HASH = 2255951583L
        private val getVrsUpdateModeBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_vrs_update_mode", GET_VRS_UPDATE_MODE_HASH)
        }

        private const val SET_VRS_TEXTURE_HASH = 4051416890L
        private val setVrsTextureBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "set_vrs_texture", SET_VRS_TEXTURE_HASH)
        }

        private const val GET_VRS_TEXTURE_HASH = 3635182373L
        private val getVrsTextureBind by lazy {
            ObjectCalls.getMethodBind("Viewport", "get_vrs_texture", GET_VRS_TEXTURE_HASH)
        }
    }
}
