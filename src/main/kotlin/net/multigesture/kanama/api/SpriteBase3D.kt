package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Vector2

/**
 * 2D sprite node in 3D environment.
 *
 * Generated from Godot docs: SpriteBase3D
 */
open class SpriteBase3D(handle: MemorySegment) : GeometryInstance3D(handle) {
    var centered: Boolean
        @JvmName("centeredProperty")
        get() = isCentered()
        @JvmName("setCenteredProperty")
        set(value) = setCentered(value)

    var offset: Vector2
        @JvmName("offsetProperty")
        get() = getOffset()
        @JvmName("setOffsetProperty")
        set(value) = setOffset(value)

    var flipH: Boolean
        @JvmName("flipHProperty")
        get() = isFlippedH()
        @JvmName("setFlipHProperty")
        set(value) = setFlipH(value)

    var flipV: Boolean
        @JvmName("flipVProperty")
        get() = isFlippedV()
        @JvmName("setFlipVProperty")
        set(value) = setFlipV(value)

    var modulate: Color
        @JvmName("modulateProperty")
        get() = getModulate()
        @JvmName("setModulateProperty")
        set(value) = setModulate(value)

    var pixelSize: Double
        @JvmName("pixelSizeProperty")
        get() = getPixelSize()
        @JvmName("setPixelSizeProperty")
        set(value) = setPixelSize(value)

    var axis: Long
        @JvmName("axisProperty")
        get() = getAxis()
        @JvmName("setAxisProperty")
        set(value) = setAxis(value)

    var billboard: Long
        @JvmName("billboardProperty")
        get() = getBillboardMode()
        @JvmName("setBillboardProperty")
        set(value) = setBillboardMode(value)

    var alphaCut: Long
        @JvmName("alphaCutProperty")
        get() = getAlphaCutMode()
        @JvmName("setAlphaCutProperty")
        set(value) = setAlphaCutMode(value)

    var alphaScissorThreshold: Double
        @JvmName("alphaScissorThresholdProperty")
        get() = getAlphaScissorThreshold()
        @JvmName("setAlphaScissorThresholdProperty")
        set(value) = setAlphaScissorThreshold(value)

    var alphaHashScale: Double
        @JvmName("alphaHashScaleProperty")
        get() = getAlphaHashScale()
        @JvmName("setAlphaHashScaleProperty")
        set(value) = setAlphaHashScale(value)

    var alphaAntialiasingMode: Long
        @JvmName("alphaAntialiasingModeProperty")
        get() = getAlphaAntialiasing()
        @JvmName("setAlphaAntialiasingModeProperty")
        set(value) = setAlphaAntialiasing(value)

    var alphaAntialiasingEdge: Double
        @JvmName("alphaAntialiasingEdgeProperty")
        get() = getAlphaAntialiasingEdge()
        @JvmName("setAlphaAntialiasingEdgeProperty")
        set(value) = setAlphaAntialiasingEdge(value)

    var textureFilter: Long
        @JvmName("textureFilterProperty")
        get() = getTextureFilter()
        @JvmName("setTextureFilterProperty")
        set(value) = setTextureFilter(value)

    var renderPriority: Int
        @JvmName("renderPriorityProperty")
        get() = getRenderPriority()
        @JvmName("setRenderPriorityProperty")
        set(value) = setRenderPriority(value)

    /**
     * If `true`, texture will be centered.
     *
     * Generated from Godot docs: SpriteBase3D.set_centered
     */
    fun setCentered(centered: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCenteredBind, handle, centered)
    }

    /**
     * If `true`, texture will be centered.
     *
     * Generated from Godot docs: SpriteBase3D.is_centered
     */
    fun isCentered(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCenteredBind, handle)
    }

    /**
     * The texture's drawing offset. Note: When you increase `offset`.y in Sprite3D, the sprite moves
     * upward in world space (i.e., +Y is up).
     *
     * Generated from Godot docs: SpriteBase3D.set_offset
     */
    fun setOffset(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setOffsetBind, handle, offset)
    }

    /**
     * The texture's drawing offset. Note: When you increase `offset`.y in Sprite3D, the sprite moves
     * upward in world space (i.e., +Y is up).
     *
     * Generated from Godot docs: SpriteBase3D.get_offset
     */
    fun getOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getOffsetBind, handle)
    }

    /**
     * If `true`, texture is flipped horizontally.
     *
     * Generated from Godot docs: SpriteBase3D.set_flip_h
     */
    fun setFlipH(flipH: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFlipHBind, handle, flipH)
    }

    /**
     * If `true`, texture is flipped horizontally.
     *
     * Generated from Godot docs: SpriteBase3D.is_flipped_h
     */
    fun isFlippedH(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFlippedHBind, handle)
    }

    /**
     * If `true`, texture is flipped vertically.
     *
     * Generated from Godot docs: SpriteBase3D.set_flip_v
     */
    fun setFlipV(flipV: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFlipVBind, handle, flipV)
    }

    /**
     * If `true`, texture is flipped vertically.
     *
     * Generated from Godot docs: SpriteBase3D.is_flipped_v
     */
    fun isFlippedV(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFlippedVBind, handle)
    }

    /**
     * A color value used to multiply the texture's colors. Can be used for mood-coloring or to
     * simulate the color of ambient light. Note: Unlike `CanvasItem.modulate` for 2D, colors with
     * values above `1.0` (overbright) are not supported. Note: If a
     * `GeometryInstance3D.material_override` is defined on the `SpriteBase3D`, the material override
     * must be configured to take vertex colors into account for albedo. Otherwise, the color defined
     * in `modulate` will be ignored. For a `BaseMaterial3D`,
     * `BaseMaterial3D.vertex_color_use_as_albedo` must be `true`. For a `ShaderMaterial`, `ALBEDO *=
     * COLOR.rgb;` must be inserted in the shader's `fragment()` function.
     *
     * Generated from Godot docs: SpriteBase3D.set_modulate
     */
    fun setModulate(modulate: Color) {
        ObjectCalls.ptrcallWithColorArg(setModulateBind, handle, modulate)
    }

    /**
     * A color value used to multiply the texture's colors. Can be used for mood-coloring or to
     * simulate the color of ambient light. Note: Unlike `CanvasItem.modulate` for 2D, colors with
     * values above `1.0` (overbright) are not supported. Note: If a
     * `GeometryInstance3D.material_override` is defined on the `SpriteBase3D`, the material override
     * must be configured to take vertex colors into account for albedo. Otherwise, the color defined
     * in `modulate` will be ignored. For a `BaseMaterial3D`,
     * `BaseMaterial3D.vertex_color_use_as_albedo` must be `true`. For a `ShaderMaterial`, `ALBEDO *=
     * COLOR.rgb;` must be inserted in the shader's `fragment()` function.
     *
     * Generated from Godot docs: SpriteBase3D.get_modulate
     */
    fun getModulate(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getModulateBind, handle)
    }

    /**
     * Sets the render priority for the sprite. Higher priority objects will be sorted in front of
     * lower priority objects. Note: This only applies if `alpha_cut` is set to `ALPHA_CUT_DISABLED`
     * (default value). Note: This only applies to sorting of transparent objects. This will not impact
     * how transparent objects are sorted relative to opaque objects. This is because opaque objects
     * are not sorted, while transparent objects are sorted from back to front (subject to priority).
     *
     * Generated from Godot docs: SpriteBase3D.set_render_priority
     */
    fun setRenderPriority(priority: Int) {
        ObjectCalls.ptrcallWithIntArg(setRenderPriorityBind, handle, priority)
    }

    /**
     * Sets the render priority for the sprite. Higher priority objects will be sorted in front of
     * lower priority objects. Note: This only applies if `alpha_cut` is set to `ALPHA_CUT_DISABLED`
     * (default value). Note: This only applies to sorting of transparent objects. This will not impact
     * how transparent objects are sorted relative to opaque objects. This is because opaque objects
     * are not sorted, while transparent objects are sorted from back to front (subject to priority).
     *
     * Generated from Godot docs: SpriteBase3D.get_render_priority
     */
    fun getRenderPriority(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getRenderPriorityBind, handle)
    }

    /**
     * The size of one pixel's width on the sprite to scale it in 3D.
     *
     * Generated from Godot docs: SpriteBase3D.set_pixel_size
     */
    fun setPixelSize(pixelSize: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPixelSizeBind, handle, pixelSize)
    }

    /**
     * The size of one pixel's width on the sprite to scale it in 3D.
     *
     * Generated from Godot docs: SpriteBase3D.get_pixel_size
     */
    fun getPixelSize(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPixelSizeBind, handle)
    }

    /**
     * The direction in which the front of the texture faces.
     *
     * Generated from Godot docs: SpriteBase3D.set_axis
     */
    fun setAxis(axis: Long) {
        ObjectCalls.ptrcallWithLongArg(setAxisBind, handle, axis)
    }

    /**
     * The direction in which the front of the texture faces.
     *
     * Generated from Godot docs: SpriteBase3D.get_axis
     */
    fun getAxis(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAxisBind, handle)
    }

    /**
     * If `true`, the texture's transparency and the opacity are used to make those parts of the sprite
     * invisible.
     *
     * Generated from Godot docs: SpriteBase3D.set_draw_flag
     */
    fun setDrawFlag(flag: Long, enabled: Boolean) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(setDrawFlagBind, handle, flag, enabled)
    }

    /**
     * If `true`, the texture's transparency and the opacity are used to make those parts of the sprite
     * invisible.
     *
     * Generated from Godot docs: SpriteBase3D.get_draw_flag
     */
    fun getDrawFlag(flag: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(getDrawFlagBind, handle, flag)
    }

    /**
     * The alpha cutting mode to use for the sprite.
     *
     * Generated from Godot docs: SpriteBase3D.set_alpha_cut_mode
     */
    fun setAlphaCutMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setAlphaCutModeBind, handle, mode)
    }

    /**
     * The alpha cutting mode to use for the sprite.
     *
     * Generated from Godot docs: SpriteBase3D.get_alpha_cut_mode
     */
    fun getAlphaCutMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAlphaCutModeBind, handle)
    }

    /**
     * Threshold at which the alpha scissor will discard values.
     *
     * Generated from Godot docs: SpriteBase3D.set_alpha_scissor_threshold
     */
    fun setAlphaScissorThreshold(threshold: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAlphaScissorThresholdBind, handle, threshold)
    }

    /**
     * Threshold at which the alpha scissor will discard values.
     *
     * Generated from Godot docs: SpriteBase3D.get_alpha_scissor_threshold
     */
    fun getAlphaScissorThreshold(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAlphaScissorThresholdBind, handle)
    }

    /**
     * The hashing scale for Alpha Hash. Recommended values between `0` and `2`.
     *
     * Generated from Godot docs: SpriteBase3D.set_alpha_hash_scale
     */
    fun setAlphaHashScale(threshold: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAlphaHashScaleBind, handle, threshold)
    }

    /**
     * The hashing scale for Alpha Hash. Recommended values between `0` and `2`.
     *
     * Generated from Godot docs: SpriteBase3D.get_alpha_hash_scale
     */
    fun getAlphaHashScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAlphaHashScaleBind, handle)
    }

    /**
     * The type of alpha antialiasing to apply.
     *
     * Generated from Godot docs: SpriteBase3D.set_alpha_antialiasing
     */
    fun setAlphaAntialiasing(alphaAa: Long) {
        ObjectCalls.ptrcallWithLongArg(setAlphaAntialiasingBind, handle, alphaAa)
    }

    /**
     * The type of alpha antialiasing to apply.
     *
     * Generated from Godot docs: SpriteBase3D.get_alpha_antialiasing
     */
    fun getAlphaAntialiasing(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAlphaAntialiasingBind, handle)
    }

    /**
     * Threshold at which antialiasing will be applied on the alpha channel.
     *
     * Generated from Godot docs: SpriteBase3D.set_alpha_antialiasing_edge
     */
    fun setAlphaAntialiasingEdge(edge: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAlphaAntialiasingEdgeBind, handle, edge)
    }

    /**
     * Threshold at which antialiasing will be applied on the alpha channel.
     *
     * Generated from Godot docs: SpriteBase3D.get_alpha_antialiasing_edge
     */
    fun getAlphaAntialiasingEdge(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAlphaAntialiasingEdgeBind, handle)
    }

    /**
     * The billboard mode to use for the sprite. Note: When billboarding is enabled and the material
     * also casts shadows, billboards will face the camera in the scene when rendering shadows. In
     * scenes with multiple cameras, the intended shadow cannot be determined and this will result in
     * undefined behavior. See GitHub Pull Request #72638
     * (https://github.com/godotengine/godot/pull/72638) for details.
     *
     * Generated from Godot docs: SpriteBase3D.set_billboard_mode
     */
    fun setBillboardMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setBillboardModeBind, handle, mode)
    }

    /**
     * The billboard mode to use for the sprite. Note: When billboarding is enabled and the material
     * also casts shadows, billboards will face the camera in the scene when rendering shadows. In
     * scenes with multiple cameras, the intended shadow cannot be determined and this will result in
     * undefined behavior. See GitHub Pull Request #72638
     * (https://github.com/godotengine/godot/pull/72638) for details.
     *
     * Generated from Godot docs: SpriteBase3D.get_billboard_mode
     */
    fun getBillboardMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getBillboardModeBind, handle)
    }

    /**
     * Filter flags for the texture. Note: Linear filtering may cause artifacts around the edges, which
     * are especially noticeable on opaque textures. To prevent this, use textures with transparent or
     * identical colors around the edges.
     *
     * Generated from Godot docs: SpriteBase3D.set_texture_filter
     */
    fun setTextureFilter(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setTextureFilterBind, handle, mode)
    }

    /**
     * Filter flags for the texture. Note: Linear filtering may cause artifacts around the edges, which
     * are especially noticeable on opaque textures. To prevent this, use textures with transparent or
     * identical colors around the edges.
     *
     * Generated from Godot docs: SpriteBase3D.get_texture_filter
     */
    fun getTextureFilter(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTextureFilterBind, handle)
    }

    /**
     * Returns the rectangle representing this sprite.
     *
     * Generated from Godot docs: SpriteBase3D.get_item_rect
     */
    fun getItemRect(): Rect2 {
        return ObjectCalls.ptrcallNoArgsRetRect2(getItemRectBind, handle)
    }

    /**
     * Returns a `TriangleMesh` with the sprite's vertices following its current configuration (such as
     * its `axis` and `pixel_size`).
     *
     * Generated from Godot docs: SpriteBase3D.generate_triangle_mesh
     */
    fun generateTriangleMesh(): TriangleMesh? {
        return TriangleMesh.wrap(ObjectCalls.ptrcallNoArgsRetObject(generateTriangleMeshBind, handle))
    }

    companion object {
        const val FLAG_TRANSPARENT: Long = 0L
        const val FLAG_SHADED: Long = 1L
        const val FLAG_DOUBLE_SIDED: Long = 2L
        const val FLAG_DISABLE_DEPTH_TEST: Long = 3L
        const val FLAG_FIXED_SIZE: Long = 4L
        const val FLAG_MAX: Long = 5L
        const val ALPHA_CUT_DISABLED: Long = 0L
        const val ALPHA_CUT_DISCARD: Long = 1L
        const val ALPHA_CUT_OPAQUE_PREPASS: Long = 2L
        const val ALPHA_CUT_HASH: Long = 3L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): SpriteBase3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SpriteBase3D? =
            if (handle.address() == 0L) null else SpriteBase3D(handle)

        private const val SET_CENTERED_HASH = 2586408642L
        private val setCenteredBind by lazy {
            ObjectCalls.getMethodBind("SpriteBase3D", "set_centered", SET_CENTERED_HASH)
        }

        private const val IS_CENTERED_HASH = 36873697L
        private val isCenteredBind by lazy {
            ObjectCalls.getMethodBind("SpriteBase3D", "is_centered", IS_CENTERED_HASH)
        }

        private const val SET_OFFSET_HASH = 743155724L
        private val setOffsetBind by lazy {
            ObjectCalls.getMethodBind("SpriteBase3D", "set_offset", SET_OFFSET_HASH)
        }

        private const val GET_OFFSET_HASH = 3341600327L
        private val getOffsetBind by lazy {
            ObjectCalls.getMethodBind("SpriteBase3D", "get_offset", GET_OFFSET_HASH)
        }

        private const val SET_FLIP_H_HASH = 2586408642L
        private val setFlipHBind by lazy {
            ObjectCalls.getMethodBind("SpriteBase3D", "set_flip_h", SET_FLIP_H_HASH)
        }

        private const val IS_FLIPPED_H_HASH = 36873697L
        private val isFlippedHBind by lazy {
            ObjectCalls.getMethodBind("SpriteBase3D", "is_flipped_h", IS_FLIPPED_H_HASH)
        }

        private const val SET_FLIP_V_HASH = 2586408642L
        private val setFlipVBind by lazy {
            ObjectCalls.getMethodBind("SpriteBase3D", "set_flip_v", SET_FLIP_V_HASH)
        }

        private const val IS_FLIPPED_V_HASH = 36873697L
        private val isFlippedVBind by lazy {
            ObjectCalls.getMethodBind("SpriteBase3D", "is_flipped_v", IS_FLIPPED_V_HASH)
        }

        private const val SET_MODULATE_HASH = 2920490490L
        private val setModulateBind by lazy {
            ObjectCalls.getMethodBind("SpriteBase3D", "set_modulate", SET_MODULATE_HASH)
        }

        private const val GET_MODULATE_HASH = 3444240500L
        private val getModulateBind by lazy {
            ObjectCalls.getMethodBind("SpriteBase3D", "get_modulate", GET_MODULATE_HASH)
        }

        private const val SET_RENDER_PRIORITY_HASH = 1286410249L
        private val setRenderPriorityBind by lazy {
            ObjectCalls.getMethodBind("SpriteBase3D", "set_render_priority", SET_RENDER_PRIORITY_HASH)
        }

        private const val GET_RENDER_PRIORITY_HASH = 3905245786L
        private val getRenderPriorityBind by lazy {
            ObjectCalls.getMethodBind("SpriteBase3D", "get_render_priority", GET_RENDER_PRIORITY_HASH)
        }

        private const val SET_PIXEL_SIZE_HASH = 373806689L
        private val setPixelSizeBind by lazy {
            ObjectCalls.getMethodBind("SpriteBase3D", "set_pixel_size", SET_PIXEL_SIZE_HASH)
        }

        private const val GET_PIXEL_SIZE_HASH = 1740695150L
        private val getPixelSizeBind by lazy {
            ObjectCalls.getMethodBind("SpriteBase3D", "get_pixel_size", GET_PIXEL_SIZE_HASH)
        }

        private const val SET_AXIS_HASH = 1144690656L
        private val setAxisBind by lazy {
            ObjectCalls.getMethodBind("SpriteBase3D", "set_axis", SET_AXIS_HASH)
        }

        private const val GET_AXIS_HASH = 3050976882L
        private val getAxisBind by lazy {
            ObjectCalls.getMethodBind("SpriteBase3D", "get_axis", GET_AXIS_HASH)
        }

        private const val SET_DRAW_FLAG_HASH = 1135633219L
        private val setDrawFlagBind by lazy {
            ObjectCalls.getMethodBind("SpriteBase3D", "set_draw_flag", SET_DRAW_FLAG_HASH)
        }

        private const val GET_DRAW_FLAG_HASH = 1733036628L
        private val getDrawFlagBind by lazy {
            ObjectCalls.getMethodBind("SpriteBase3D", "get_draw_flag", GET_DRAW_FLAG_HASH)
        }

        private const val SET_ALPHA_CUT_MODE_HASH = 227561226L
        private val setAlphaCutModeBind by lazy {
            ObjectCalls.getMethodBind("SpriteBase3D", "set_alpha_cut_mode", SET_ALPHA_CUT_MODE_HASH)
        }

        private const val GET_ALPHA_CUT_MODE_HASH = 336003791L
        private val getAlphaCutModeBind by lazy {
            ObjectCalls.getMethodBind("SpriteBase3D", "get_alpha_cut_mode", GET_ALPHA_CUT_MODE_HASH)
        }

        private const val SET_ALPHA_SCISSOR_THRESHOLD_HASH = 373806689L
        private val setAlphaScissorThresholdBind by lazy {
            ObjectCalls.getMethodBind("SpriteBase3D", "set_alpha_scissor_threshold", SET_ALPHA_SCISSOR_THRESHOLD_HASH)
        }

        private const val GET_ALPHA_SCISSOR_THRESHOLD_HASH = 1740695150L
        private val getAlphaScissorThresholdBind by lazy {
            ObjectCalls.getMethodBind("SpriteBase3D", "get_alpha_scissor_threshold", GET_ALPHA_SCISSOR_THRESHOLD_HASH)
        }

        private const val SET_ALPHA_HASH_SCALE_HASH = 373806689L
        private val setAlphaHashScaleBind by lazy {
            ObjectCalls.getMethodBind("SpriteBase3D", "set_alpha_hash_scale", SET_ALPHA_HASH_SCALE_HASH)
        }

        private const val GET_ALPHA_HASH_SCALE_HASH = 1740695150L
        private val getAlphaHashScaleBind by lazy {
            ObjectCalls.getMethodBind("SpriteBase3D", "get_alpha_hash_scale", GET_ALPHA_HASH_SCALE_HASH)
        }

        private const val SET_ALPHA_ANTIALIASING_HASH = 3212649852L
        private val setAlphaAntialiasingBind by lazy {
            ObjectCalls.getMethodBind("SpriteBase3D", "set_alpha_antialiasing", SET_ALPHA_ANTIALIASING_HASH)
        }

        private const val GET_ALPHA_ANTIALIASING_HASH = 2889939400L
        private val getAlphaAntialiasingBind by lazy {
            ObjectCalls.getMethodBind("SpriteBase3D", "get_alpha_antialiasing", GET_ALPHA_ANTIALIASING_HASH)
        }

        private const val SET_ALPHA_ANTIALIASING_EDGE_HASH = 373806689L
        private val setAlphaAntialiasingEdgeBind by lazy {
            ObjectCalls.getMethodBind("SpriteBase3D", "set_alpha_antialiasing_edge", SET_ALPHA_ANTIALIASING_EDGE_HASH)
        }

        private const val GET_ALPHA_ANTIALIASING_EDGE_HASH = 1740695150L
        private val getAlphaAntialiasingEdgeBind by lazy {
            ObjectCalls.getMethodBind("SpriteBase3D", "get_alpha_antialiasing_edge", GET_ALPHA_ANTIALIASING_EDGE_HASH)
        }

        private const val SET_BILLBOARD_MODE_HASH = 4202036497L
        private val setBillboardModeBind by lazy {
            ObjectCalls.getMethodBind("SpriteBase3D", "set_billboard_mode", SET_BILLBOARD_MODE_HASH)
        }

        private const val GET_BILLBOARD_MODE_HASH = 1283840139L
        private val getBillboardModeBind by lazy {
            ObjectCalls.getMethodBind("SpriteBase3D", "get_billboard_mode", GET_BILLBOARD_MODE_HASH)
        }

        private const val SET_TEXTURE_FILTER_HASH = 22904437L
        private val setTextureFilterBind by lazy {
            ObjectCalls.getMethodBind("SpriteBase3D", "set_texture_filter", SET_TEXTURE_FILTER_HASH)
        }

        private const val GET_TEXTURE_FILTER_HASH = 3289213076L
        private val getTextureFilterBind by lazy {
            ObjectCalls.getMethodBind("SpriteBase3D", "get_texture_filter", GET_TEXTURE_FILTER_HASH)
        }

        private const val GET_ITEM_RECT_HASH = 1639390495L
        private val getItemRectBind by lazy {
            ObjectCalls.getMethodBind("SpriteBase3D", "get_item_rect", GET_ITEM_RECT_HASH)
        }

        private const val GENERATE_TRIANGLE_MESH_HASH = 3476533166L
        private val generateTriangleMeshBind by lazy {
            ObjectCalls.getMethodBind("SpriteBase3D", "generate_triangle_mesh", GENERATE_TRIANGLE_MESH_HASH)
        }
    }
}
