package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3

/**
 * 2D sprite node in 3D environment.
 *
 * Generated from Godot docs: SpriteBase3D
 */
open class SpriteBase3D(handle: GodotHandle) : GeometryInstance3D(handle) {
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

    var axis: Vector3.Axis
        @JvmName("axisProperty")
        get() = getAxis()
        @JvmName("setAxisProperty")
        set(value) = setAxis(value)

    var billboard: BaseMaterial3D.BillboardMode
        @JvmName("billboardProperty")
        get() = getBillboardMode()
        @JvmName("setBillboardProperty")
        set(value) = setBillboardMode(value)

    var transparent: Boolean
        @JvmName("transparentProperty")
        get() = getDrawFlag(SpriteBase3D.DrawFlags.TRANSPARENT)
        @JvmName("setTransparentProperty")
        set(value) = setDrawFlag(SpriteBase3D.DrawFlags.TRANSPARENT, value)

    var shaded: Boolean
        @JvmName("shadedProperty")
        get() = getDrawFlag(SpriteBase3D.DrawFlags.SHADED)
        @JvmName("setShadedProperty")
        set(value) = setDrawFlag(SpriteBase3D.DrawFlags.SHADED, value)

    var doubleSided: Boolean
        @JvmName("doubleSidedProperty")
        get() = getDrawFlag(SpriteBase3D.DrawFlags.DOUBLE_SIDED)
        @JvmName("setDoubleSidedProperty")
        set(value) = setDrawFlag(SpriteBase3D.DrawFlags.DOUBLE_SIDED, value)

    var noDepthTest: Boolean
        @JvmName("noDepthTestProperty")
        get() = getDrawFlag(SpriteBase3D.DrawFlags.DISABLE_DEPTH_TEST)
        @JvmName("setNoDepthTestProperty")
        set(value) = setDrawFlag(SpriteBase3D.DrawFlags.DISABLE_DEPTH_TEST, value)

    var fixedSize: Boolean
        @JvmName("fixedSizeProperty")
        get() = getDrawFlag(SpriteBase3D.DrawFlags.FIXED_SIZE)
        @JvmName("setFixedSizeProperty")
        set(value) = setDrawFlag(SpriteBase3D.DrawFlags.FIXED_SIZE, value)

    var alphaCut: SpriteBase3D.AlphaCutMode
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

    var alphaAntialiasingMode: BaseMaterial3D.AlphaAntiAliasing
        @JvmName("alphaAntialiasingModeProperty")
        get() = getAlphaAntialiasing()
        @JvmName("setAlphaAntialiasingModeProperty")
        set(value) = setAlphaAntialiasing(value)

    var alphaAntialiasingEdge: Double
        @JvmName("alphaAntialiasingEdgeProperty")
        get() = getAlphaAntialiasingEdge()
        @JvmName("setAlphaAntialiasingEdgeProperty")
        set(value) = setAlphaAntialiasingEdge(value)

    var textureFilter: BaseMaterial3D.TextureFilter
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
        ObjectCalls.ptrcallWithBoolArg(Binds.setCenteredBind, segment, centered)
    }

    /**
     * If `true`, texture will be centered.
     *
     * Generated from Godot docs: SpriteBase3D.is_centered
     */
    fun isCentered(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isCenteredBind, segment)
    }

    /**
     * The texture's drawing offset. Note: When you increase `offset`.y in Sprite3D, the sprite moves
     * upward in world space (i.e., +Y is up).
     *
     * Generated from Godot docs: SpriteBase3D.set_offset
     */
    fun setOffset(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(Binds.setOffsetBind, segment, offset)
    }

    /**
     * The texture's drawing offset. Note: When you increase `offset`.y in Sprite3D, the sprite moves
     * upward in world space (i.e., +Y is up).
     *
     * Generated from Godot docs: SpriteBase3D.get_offset
     */
    fun getOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getOffsetBind, segment)
    }

    /**
     * If `true`, texture is flipped horizontally.
     *
     * Generated from Godot docs: SpriteBase3D.set_flip_h
     */
    fun setFlipH(flipH: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setFlipHBind, segment, flipH)
    }

    /**
     * If `true`, texture is flipped horizontally.
     *
     * Generated from Godot docs: SpriteBase3D.is_flipped_h
     */
    fun isFlippedH(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isFlippedHBind, segment)
    }

    /**
     * If `true`, texture is flipped vertically.
     *
     * Generated from Godot docs: SpriteBase3D.set_flip_v
     */
    fun setFlipV(flipV: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setFlipVBind, segment, flipV)
    }

    /**
     * If `true`, texture is flipped vertically.
     *
     * Generated from Godot docs: SpriteBase3D.is_flipped_v
     */
    fun isFlippedV(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isFlippedVBind, segment)
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
        ObjectCalls.ptrcallWithColorArg(Binds.setModulateBind, segment, modulate)
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
        return ObjectCalls.ptrcallNoArgsRetColor(Binds.getModulateBind, segment)
    }

    /**
     * Sets the render priority for the sprite. Higher priority objects will be sorted in front of
     * lower priority objects. Note: This only applies if `alpha_cut` is set to `AlphaCutMode.DISABLED`
     * (default value). Note: This only applies to sorting of transparent objects. This will not impact
     * how transparent objects are sorted relative to opaque objects. This is because opaque objects
     * are not sorted, while transparent objects are sorted from back to front (subject to priority).
     *
     * Generated from Godot docs: SpriteBase3D.set_render_priority
     */
    fun setRenderPriority(priority: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setRenderPriorityBind, segment, priority)
    }

    /**
     * Sets the render priority for the sprite. Higher priority objects will be sorted in front of
     * lower priority objects. Note: This only applies if `alpha_cut` is set to `AlphaCutMode.DISABLED`
     * (default value). Note: This only applies to sorting of transparent objects. This will not impact
     * how transparent objects are sorted relative to opaque objects. This is because opaque objects
     * are not sorted, while transparent objects are sorted from back to front (subject to priority).
     *
     * Generated from Godot docs: SpriteBase3D.get_render_priority
     */
    fun getRenderPriority(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getRenderPriorityBind, segment)
    }

    /**
     * The size of one pixel's width on the sprite to scale it in 3D.
     *
     * Generated from Godot docs: SpriteBase3D.set_pixel_size
     */
    fun setPixelSize(pixelSize: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setPixelSizeBind, segment, pixelSize)
    }

    /**
     * The size of one pixel's width on the sprite to scale it in 3D.
     *
     * Generated from Godot docs: SpriteBase3D.get_pixel_size
     */
    fun getPixelSize(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getPixelSizeBind, segment)
    }

    /**
     * The direction in which the front of the texture faces.
     *
     * Generated from Godot docs: SpriteBase3D.set_axis
     */
    fun setAxis(axis: Vector3.Axis) {
        ObjectCalls.ptrcallWithLongArg(Binds.setAxisBind, segment, axis.value)
    }

    /**
     * The direction in which the front of the texture faces.
     *
     * Generated from Godot docs: SpriteBase3D.get_axis
     */
    fun getAxis(): Vector3.Axis {
        return Vector3.Axis(ObjectCalls.ptrcallNoArgsRetLong(Binds.getAxisBind, segment))
    }

    /**
     * If `true`, the texture's transparency and the opacity are used to make those parts of the sprite
     * invisible.
     *
     * Generated from Godot docs: SpriteBase3D.set_draw_flag
     */
    fun setDrawFlag(flag: SpriteBase3D.DrawFlags, enabled: Boolean) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(Binds.setDrawFlagBind, segment, flag.value, enabled)
    }

    /**
     * If `true`, the texture's transparency and the opacity are used to make those parts of the sprite
     * invisible.
     *
     * Generated from Godot docs: SpriteBase3D.get_draw_flag
     */
    fun getDrawFlag(flag: SpriteBase3D.DrawFlags): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(Binds.getDrawFlagBind, segment, flag.value)
    }

    /**
     * The alpha cutting mode to use for the sprite.
     *
     * Generated from Godot docs: SpriteBase3D.set_alpha_cut_mode
     */
    fun setAlphaCutMode(mode: SpriteBase3D.AlphaCutMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setAlphaCutModeBind, segment, mode.value)
    }

    /**
     * The alpha cutting mode to use for the sprite.
     *
     * Generated from Godot docs: SpriteBase3D.get_alpha_cut_mode
     */
    fun getAlphaCutMode(): SpriteBase3D.AlphaCutMode {
        return SpriteBase3D.AlphaCutMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getAlphaCutModeBind, segment))
    }

    /**
     * Threshold at which the alpha scissor will discard values.
     *
     * Generated from Godot docs: SpriteBase3D.set_alpha_scissor_threshold
     */
    fun setAlphaScissorThreshold(threshold: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setAlphaScissorThresholdBind, segment, threshold)
    }

    /**
     * Threshold at which the alpha scissor will discard values.
     *
     * Generated from Godot docs: SpriteBase3D.get_alpha_scissor_threshold
     */
    fun getAlphaScissorThreshold(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getAlphaScissorThresholdBind, segment)
    }

    /**
     * The hashing scale for Alpha Hash. Recommended values between `0` and `2`.
     *
     * Generated from Godot docs: SpriteBase3D.set_alpha_hash_scale
     */
    fun setAlphaHashScale(threshold: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setAlphaHashScaleBind, segment, threshold)
    }

    /**
     * The hashing scale for Alpha Hash. Recommended values between `0` and `2`.
     *
     * Generated from Godot docs: SpriteBase3D.get_alpha_hash_scale
     */
    fun getAlphaHashScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getAlphaHashScaleBind, segment)
    }

    /**
     * The type of alpha antialiasing to apply.
     *
     * Generated from Godot docs: SpriteBase3D.set_alpha_antialiasing
     */
    fun setAlphaAntialiasing(alphaAa: BaseMaterial3D.AlphaAntiAliasing) {
        ObjectCalls.ptrcallWithLongArg(Binds.setAlphaAntialiasingBind, segment, alphaAa.value)
    }

    /**
     * The type of alpha antialiasing to apply.
     *
     * Generated from Godot docs: SpriteBase3D.get_alpha_antialiasing
     */
    fun getAlphaAntialiasing(): BaseMaterial3D.AlphaAntiAliasing {
        return BaseMaterial3D.AlphaAntiAliasing(ObjectCalls.ptrcallNoArgsRetLong(Binds.getAlphaAntialiasingBind, segment))
    }

    /**
     * Threshold at which antialiasing will be applied on the alpha channel.
     *
     * Generated from Godot docs: SpriteBase3D.set_alpha_antialiasing_edge
     */
    fun setAlphaAntialiasingEdge(edge: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setAlphaAntialiasingEdgeBind, segment, edge)
    }

    /**
     * Threshold at which antialiasing will be applied on the alpha channel.
     *
     * Generated from Godot docs: SpriteBase3D.get_alpha_antialiasing_edge
     */
    fun getAlphaAntialiasingEdge(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getAlphaAntialiasingEdgeBind, segment)
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
    fun setBillboardMode(mode: BaseMaterial3D.BillboardMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setBillboardModeBind, segment, mode.value)
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
    fun getBillboardMode(): BaseMaterial3D.BillboardMode {
        return BaseMaterial3D.BillboardMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getBillboardModeBind, segment))
    }

    /**
     * Filter flags for the texture. Note: Linear filtering may cause artifacts around the edges, which
     * are especially noticeable on opaque textures. To prevent this, use textures with transparent or
     * identical colors around the edges.
     *
     * Generated from Godot docs: SpriteBase3D.set_texture_filter
     */
    fun setTextureFilter(mode: BaseMaterial3D.TextureFilter) {
        ObjectCalls.ptrcallWithLongArg(Binds.setTextureFilterBind, segment, mode.value)
    }

    /**
     * Filter flags for the texture. Note: Linear filtering may cause artifacts around the edges, which
     * are especially noticeable on opaque textures. To prevent this, use textures with transparent or
     * identical colors around the edges.
     *
     * Generated from Godot docs: SpriteBase3D.get_texture_filter
     */
    fun getTextureFilter(): BaseMaterial3D.TextureFilter {
        return BaseMaterial3D.TextureFilter(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTextureFilterBind, segment))
    }

    /**
     * Returns the rectangle representing this sprite.
     *
     * Generated from Godot docs: SpriteBase3D.get_item_rect
     */
    fun getItemRect(): Rect2 {
        return ObjectCalls.ptrcallNoArgsRetRect2(Binds.getItemRectBind, segment)
    }

    /**
     * Returns a `TriangleMesh` with the sprite's vertices following its current configuration (such as
     * its `axis` and `pixel_size`).
     *
     * Generated from Godot docs: SpriteBase3D.generate_triangle_mesh
     */
    fun generateTriangleMesh(): TriangleMesh? {
        return TriangleMesh.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.generateTriangleMeshBind, segment))
    }

    /**
     * Godot's `SpriteBase3D.DrawFlags` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`SpriteBase3D.DrawFlags.<NAME>`).
     *
     * Generated from Godot docs: SpriteBase3D.DrawFlags
     */
    @JvmInline
    value class DrawFlags(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * If set, the texture's transparency and the opacity are used to make those parts of the sprite
             * invisible.
             *
             * Generated from Godot docs: SpriteBase3D.FLAG_TRANSPARENT
             */
            val TRANSPARENT: DrawFlags get() = DrawFlags(0L)
            /**
             * If set, lights in the environment affect the sprite.
             *
             * Generated from Godot docs: SpriteBase3D.FLAG_SHADED
             */
            val SHADED: DrawFlags get() = DrawFlags(1L)
            /**
             * If set, texture can be seen from the back as well. If not, the texture is invisible when looking
             * at it from behind.
             *
             * Generated from Godot docs: SpriteBase3D.FLAG_DOUBLE_SIDED
             */
            val DOUBLE_SIDED: DrawFlags get() = DrawFlags(2L)
            /**
             * Disables the depth test, so this object is drawn on top of all others. However, objects drawn
             * after it in the draw order may cover it.
             *
             * Generated from Godot docs: SpriteBase3D.FLAG_DISABLE_DEPTH_TEST
             */
            val DISABLE_DEPTH_TEST: DrawFlags get() = DrawFlags(3L)
            /**
             * Label is scaled by depth so that it always appears the same size on screen.
             *
             * Generated from Godot docs: SpriteBase3D.FLAG_FIXED_SIZE
             */
            val FIXED_SIZE: DrawFlags get() = DrawFlags(4L)
            /**
             * Represents the size of the `DrawFlags` enum.
             *
             * Generated from Godot docs: SpriteBase3D.FLAG_MAX
             */
            val MAX: DrawFlags get() = DrawFlags(5L)
        }
    }

    /**
     * Godot's `SpriteBase3D.AlphaCutMode` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`SpriteBase3D.AlphaCutMode.<NAME>`).
     *
     * Generated from Godot docs: SpriteBase3D.AlphaCutMode
     */
    @JvmInline
    value class AlphaCutMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * This mode performs standard alpha blending. It can display translucent areas, but transparency
             * sorting issues may be visible when multiple transparent materials are overlapping.
             *
             * Generated from Godot docs: SpriteBase3D.ALPHA_CUT_DISABLED
             */
            val DISABLED: AlphaCutMode get() = AlphaCutMode(0L)
            /**
             * This mode only allows fully transparent or fully opaque pixels. Harsh edges will be visible
             * unless some form of screen-space antialiasing is enabled (see
             * `ProjectSettings.rendering/anti_aliasing/quality/screen_space_aa`). On the bright side, this
             * mode doesn't suffer from transparency sorting issues when multiple transparent materials are
             * overlapping. This mode is also known as alpha testing or 1-bit transparency.
             *
             * Generated from Godot docs: SpriteBase3D.ALPHA_CUT_DISCARD
             */
            val DISCARD: AlphaCutMode get() = AlphaCutMode(1L)
            /**
             * This mode draws fully opaque pixels in the depth prepass. This is slower than
             * `AlphaCutMode.DISABLED` or `AlphaCutMode.DISCARD`, but it allows displaying translucent areas
             * and smooth edges while using proper sorting.
             *
             * Generated from Godot docs: SpriteBase3D.ALPHA_CUT_OPAQUE_PREPASS
             */
            val OPAQUE_PREPASS: AlphaCutMode get() = AlphaCutMode(2L)
            /**
             * This mode draws cuts off all values below a spatially-deterministic threshold, the rest will
             * remain opaque.
             *
             * Generated from Godot docs: SpriteBase3D.ALPHA_CUT_HASH
             */
            val HASH: AlphaCutMode get() = AlphaCutMode(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): SpriteBase3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): SpriteBase3D? =
            if (handle.address() == 0L) null else SpriteBase3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_CENTERED_HASH = 2586408642L
        @JvmField
        val setCenteredBind =
            ObjectCalls.getMethodBind("SpriteBase3D", "set_centered", SET_CENTERED_HASH)

        private const val IS_CENTERED_HASH = 36873697L
        @JvmField
        val isCenteredBind =
            ObjectCalls.getMethodBind("SpriteBase3D", "is_centered", IS_CENTERED_HASH)

        private const val SET_OFFSET_HASH = 743155724L
        @JvmField
        val setOffsetBind =
            ObjectCalls.getMethodBind("SpriteBase3D", "set_offset", SET_OFFSET_HASH)

        private const val GET_OFFSET_HASH = 3341600327L
        @JvmField
        val getOffsetBind =
            ObjectCalls.getMethodBind("SpriteBase3D", "get_offset", GET_OFFSET_HASH)

        private const val SET_FLIP_H_HASH = 2586408642L
        @JvmField
        val setFlipHBind =
            ObjectCalls.getMethodBind("SpriteBase3D", "set_flip_h", SET_FLIP_H_HASH)

        private const val IS_FLIPPED_H_HASH = 36873697L
        @JvmField
        val isFlippedHBind =
            ObjectCalls.getMethodBind("SpriteBase3D", "is_flipped_h", IS_FLIPPED_H_HASH)

        private const val SET_FLIP_V_HASH = 2586408642L
        @JvmField
        val setFlipVBind =
            ObjectCalls.getMethodBind("SpriteBase3D", "set_flip_v", SET_FLIP_V_HASH)

        private const val IS_FLIPPED_V_HASH = 36873697L
        @JvmField
        val isFlippedVBind =
            ObjectCalls.getMethodBind("SpriteBase3D", "is_flipped_v", IS_FLIPPED_V_HASH)

        private const val SET_MODULATE_HASH = 2920490490L
        @JvmField
        val setModulateBind =
            ObjectCalls.getMethodBind("SpriteBase3D", "set_modulate", SET_MODULATE_HASH)

        private const val GET_MODULATE_HASH = 3444240500L
        @JvmField
        val getModulateBind =
            ObjectCalls.getMethodBind("SpriteBase3D", "get_modulate", GET_MODULATE_HASH)

        private const val SET_RENDER_PRIORITY_HASH = 1286410249L
        @JvmField
        val setRenderPriorityBind =
            ObjectCalls.getMethodBind("SpriteBase3D", "set_render_priority", SET_RENDER_PRIORITY_HASH)

        private const val GET_RENDER_PRIORITY_HASH = 3905245786L
        @JvmField
        val getRenderPriorityBind =
            ObjectCalls.getMethodBind("SpriteBase3D", "get_render_priority", GET_RENDER_PRIORITY_HASH)

        private const val SET_PIXEL_SIZE_HASH = 373806689L
        @JvmField
        val setPixelSizeBind =
            ObjectCalls.getMethodBind("SpriteBase3D", "set_pixel_size", SET_PIXEL_SIZE_HASH)

        private const val GET_PIXEL_SIZE_HASH = 1740695150L
        @JvmField
        val getPixelSizeBind =
            ObjectCalls.getMethodBind("SpriteBase3D", "get_pixel_size", GET_PIXEL_SIZE_HASH)

        private const val SET_AXIS_HASH = 1144690656L
        @JvmField
        val setAxisBind =
            ObjectCalls.getMethodBind("SpriteBase3D", "set_axis", SET_AXIS_HASH)

        private const val GET_AXIS_HASH = 3050976882L
        @JvmField
        val getAxisBind =
            ObjectCalls.getMethodBind("SpriteBase3D", "get_axis", GET_AXIS_HASH)

        private const val SET_DRAW_FLAG_HASH = 1135633219L
        @JvmField
        val setDrawFlagBind =
            ObjectCalls.getMethodBind("SpriteBase3D", "set_draw_flag", SET_DRAW_FLAG_HASH)

        private const val GET_DRAW_FLAG_HASH = 1733036628L
        @JvmField
        val getDrawFlagBind =
            ObjectCalls.getMethodBind("SpriteBase3D", "get_draw_flag", GET_DRAW_FLAG_HASH)

        private const val SET_ALPHA_CUT_MODE_HASH = 227561226L
        @JvmField
        val setAlphaCutModeBind =
            ObjectCalls.getMethodBind("SpriteBase3D", "set_alpha_cut_mode", SET_ALPHA_CUT_MODE_HASH)

        private const val GET_ALPHA_CUT_MODE_HASH = 336003791L
        @JvmField
        val getAlphaCutModeBind =
            ObjectCalls.getMethodBind("SpriteBase3D", "get_alpha_cut_mode", GET_ALPHA_CUT_MODE_HASH)

        private const val SET_ALPHA_SCISSOR_THRESHOLD_HASH = 373806689L
        @JvmField
        val setAlphaScissorThresholdBind =
            ObjectCalls.getMethodBind("SpriteBase3D", "set_alpha_scissor_threshold", SET_ALPHA_SCISSOR_THRESHOLD_HASH)

        private const val GET_ALPHA_SCISSOR_THRESHOLD_HASH = 1740695150L
        @JvmField
        val getAlphaScissorThresholdBind =
            ObjectCalls.getMethodBind("SpriteBase3D", "get_alpha_scissor_threshold", GET_ALPHA_SCISSOR_THRESHOLD_HASH)

        private const val SET_ALPHA_HASH_SCALE_HASH = 373806689L
        @JvmField
        val setAlphaHashScaleBind =
            ObjectCalls.getMethodBind("SpriteBase3D", "set_alpha_hash_scale", SET_ALPHA_HASH_SCALE_HASH)

        private const val GET_ALPHA_HASH_SCALE_HASH = 1740695150L
        @JvmField
        val getAlphaHashScaleBind =
            ObjectCalls.getMethodBind("SpriteBase3D", "get_alpha_hash_scale", GET_ALPHA_HASH_SCALE_HASH)

        private const val SET_ALPHA_ANTIALIASING_HASH = 3212649852L
        @JvmField
        val setAlphaAntialiasingBind =
            ObjectCalls.getMethodBind("SpriteBase3D", "set_alpha_antialiasing", SET_ALPHA_ANTIALIASING_HASH)

        private const val GET_ALPHA_ANTIALIASING_HASH = 2889939400L
        @JvmField
        val getAlphaAntialiasingBind =
            ObjectCalls.getMethodBind("SpriteBase3D", "get_alpha_antialiasing", GET_ALPHA_ANTIALIASING_HASH)

        private const val SET_ALPHA_ANTIALIASING_EDGE_HASH = 373806689L
        @JvmField
        val setAlphaAntialiasingEdgeBind =
            ObjectCalls.getMethodBind("SpriteBase3D", "set_alpha_antialiasing_edge", SET_ALPHA_ANTIALIASING_EDGE_HASH)

        private const val GET_ALPHA_ANTIALIASING_EDGE_HASH = 1740695150L
        @JvmField
        val getAlphaAntialiasingEdgeBind =
            ObjectCalls.getMethodBind("SpriteBase3D", "get_alpha_antialiasing_edge", GET_ALPHA_ANTIALIASING_EDGE_HASH)

        private const val SET_BILLBOARD_MODE_HASH = 4202036497L
        @JvmField
        val setBillboardModeBind =
            ObjectCalls.getMethodBind("SpriteBase3D", "set_billboard_mode", SET_BILLBOARD_MODE_HASH)

        private const val GET_BILLBOARD_MODE_HASH = 1283840139L
        @JvmField
        val getBillboardModeBind =
            ObjectCalls.getMethodBind("SpriteBase3D", "get_billboard_mode", GET_BILLBOARD_MODE_HASH)

        private const val SET_TEXTURE_FILTER_HASH = 22904437L
        @JvmField
        val setTextureFilterBind =
            ObjectCalls.getMethodBind("SpriteBase3D", "set_texture_filter", SET_TEXTURE_FILTER_HASH)

        private const val GET_TEXTURE_FILTER_HASH = 3289213076L
        @JvmField
        val getTextureFilterBind =
            ObjectCalls.getMethodBind("SpriteBase3D", "get_texture_filter", GET_TEXTURE_FILTER_HASH)

        private const val GET_ITEM_RECT_HASH = 1639390495L
        @JvmField
        val getItemRectBind =
            ObjectCalls.getMethodBind("SpriteBase3D", "get_item_rect", GET_ITEM_RECT_HASH)

        private const val GENERATE_TRIANGLE_MESH_HASH = 3476533166L
        @JvmField
        val generateTriangleMeshBind =
            ObjectCalls.getMethodBind("SpriteBase3D", "generate_triangle_mesh", GENERATE_TRIANGLE_MESH_HASH)
    }
}
