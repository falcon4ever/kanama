package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Transform2D

/**
 * A variation of a font with additional settings.
 *
 * Generated from Godot docs: FontVariation
 */
class FontVariation(handle: MemorySegment) : Font(handle) {
    var baseFont: Font?
        @JvmName("baseFontProperty")
        get() = getBaseFont()
        @JvmName("setBaseFontProperty")
        set(value) = setBaseFont(value)

    var variationFaceIndex: Int
        @JvmName("variationFaceIndexProperty")
        get() = getVariationFaceIndex()
        @JvmName("setVariationFaceIndexProperty")
        set(value) = setVariationFaceIndex(value)

    var variationEmbolden: Double
        @JvmName("variationEmboldenProperty")
        get() = getVariationEmbolden()
        @JvmName("setVariationEmboldenProperty")
        set(value) = setVariationEmbolden(value)

    var variationTransform: Transform2D
        @JvmName("variationTransformProperty")
        get() = getVariationTransform()
        @JvmName("setVariationTransformProperty")
        set(value) = setVariationTransform(value)

    var spacingGlyph: Int
        @JvmName("spacingGlyphProperty")
        get() = getSpacing(0L)
        @JvmName("setSpacingGlyphProperty")
        set(value) = setSpacing(0L, value)

    var spacingSpace: Int
        @JvmName("spacingSpaceProperty")
        get() = getSpacing(1L)
        @JvmName("setSpacingSpaceProperty")
        set(value) = setSpacing(1L, value)

    var spacingTop: Int
        @JvmName("spacingTopProperty")
        get() = getSpacing(2L)
        @JvmName("setSpacingTopProperty")
        set(value) = setSpacing(2L, value)

    var spacingBottom: Int
        @JvmName("spacingBottomProperty")
        get() = getSpacing(3L)
        @JvmName("setSpacingBottomProperty")
        set(value) = setSpacing(3L, value)

    var baselineOffset: Double
        @JvmName("baselineOffsetProperty")
        get() = getBaselineOffset()
        @JvmName("setBaselineOffsetProperty")
        set(value) = setBaselineOffset(value)

    var paletteIndex: Long
        @JvmName("paletteIndexProperty")
        get() = getPaletteIndex()
        @JvmName("setPaletteIndexProperty")
        set(value) = setPaletteIndex(value)

    val paletteCustomColors: List<Color>
        @JvmName("paletteCustomColorsProperty")
        get() = getPaletteCustomColors()

    /**
     * Base font used to create a variation. If not set, default `Theme` font is used.
     *
     * Generated from Godot docs: FontVariation.set_base_font
     */
    fun setBaseFont(font: Font?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setBaseFontBind, handle, listOf(font?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Base font used to create a variation. If not set, default `Theme` font is used.
     *
     * Generated from Godot docs: FontVariation.get_base_font
     */
    fun getBaseFont(): Font? {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetObject(getBaseFontBind, handle)
        if (ret.address() == handle.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return Font.wrap(ret)
    }

    /**
     * If is not equal to zero, emboldens the font outlines. Negative values reduce the outline
     * thickness. Note: Emboldened fonts might have self-intersecting outlines, which will prevent MSDF
     * fonts and `TextMesh` from working correctly.
     *
     * Generated from Godot docs: FontVariation.set_variation_embolden
     */
    fun setVariationEmbolden(strength: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setVariationEmboldenBind, handle, strength)
    }

    /**
     * If is not equal to zero, emboldens the font outlines. Negative values reduce the outline
     * thickness. Note: Emboldened fonts might have self-intersecting outlines, which will prevent MSDF
     * fonts and `TextMesh` from working correctly.
     *
     * Generated from Godot docs: FontVariation.get_variation_embolden
     */
    fun getVariationEmbolden(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getVariationEmboldenBind, handle)
    }

    /**
     * Active face index in the TrueType / OpenType collection file.
     *
     * Generated from Godot docs: FontVariation.set_variation_face_index
     */
    fun setVariationFaceIndex(faceIndex: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setVariationFaceIndexBind, handle, faceIndex)
    }

    /**
     * Active face index in the TrueType / OpenType collection file.
     *
     * Generated from Godot docs: FontVariation.get_variation_face_index
     */
    fun getVariationFaceIndex(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getVariationFaceIndexBind, handle)
    }

    /**
     * 2D transform, applied to the font outlines, can be used for slanting, flipping and rotating
     * glyphs. For example, to simulate italic typeface by slanting, apply the following transform
     * `Transform2D(1.0, slant, 0.0, 1.0, 0.0, 0.0)`.
     *
     * Generated from Godot docs: FontVariation.set_variation_transform
     */
    fun setVariationTransform(transform: Transform2D) {
        checkOpen()
        ObjectCalls.ptrcallWithTransform2DArg(setVariationTransformBind, handle, transform)
    }

    /**
     * 2D transform, applied to the font outlines, can be used for slanting, flipping and rotating
     * glyphs. For example, to simulate italic typeface by slanting, apply the following transform
     * `Transform2D(1.0, slant, 0.0, 1.0, 0.0, 0.0)`.
     *
     * Generated from Godot docs: FontVariation.get_variation_transform
     */
    fun getVariationTransform(): Transform2D {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTransform2D(getVariationTransformBind, handle)
    }

    /**
     * Extra spacing at the top of the line in pixels.
     *
     * Generated from Godot docs: FontVariation.set_spacing
     */
    fun setSpacing(spacing: Long, value: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndIntArgs(setSpacingBind, handle, spacing, value)
    }

    /**
     * Extra baseline offset (as a fraction of font height).
     *
     * Generated from Godot docs: FontVariation.set_baseline_offset
     */
    fun setBaselineOffset(baselineOffset: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setBaselineOffsetBind, handle, baselineOffset)
    }

    /**
     * Extra baseline offset (as a fraction of font height).
     *
     * Generated from Godot docs: FontVariation.get_baseline_offset
     */
    fun getBaselineOffset(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getBaselineOffsetBind, handle)
    }

    /**
     * A palette index.
     *
     * Generated from Godot docs: FontVariation.get_palette_index
     */
    fun getPaletteIndex(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getPaletteIndexBind, handle)
    }

    /**
     * A palette index.
     *
     * Generated from Godot docs: FontVariation.set_palette_index
     */
    fun setPaletteIndex(paletteIndex: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setPaletteIndexBind, handle, paletteIndex)
    }

    /**
     * An array of colors to override predefined palette. Use `Color(0, 0, 0, 0)`, to keep predefined
     * palette color at specific position.
     *
     * Generated from Godot docs: FontVariation.get_palette_custom_colors
     */
    fun getPaletteCustomColors(): List<Color> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedColorList(getPaletteCustomColorsBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): FontVariation? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): FontVariation? =
            if (handle.address() == 0L) null else FontVariation(handle)

        private const val SET_BASE_FONT_HASH = 1262170328L
        private val setBaseFontBind by lazy {
            ObjectCalls.getMethodBind("FontVariation", "set_base_font", SET_BASE_FONT_HASH)
        }

        private const val GET_BASE_FONT_HASH = 3229501585L
        private val getBaseFontBind by lazy {
            ObjectCalls.getMethodBind("FontVariation", "get_base_font", GET_BASE_FONT_HASH)
        }

        private const val SET_VARIATION_EMBOLDEN_HASH = 373806689L
        private val setVariationEmboldenBind by lazy {
            ObjectCalls.getMethodBind("FontVariation", "set_variation_embolden", SET_VARIATION_EMBOLDEN_HASH)
        }

        private const val GET_VARIATION_EMBOLDEN_HASH = 1740695150L
        private val getVariationEmboldenBind by lazy {
            ObjectCalls.getMethodBind("FontVariation", "get_variation_embolden", GET_VARIATION_EMBOLDEN_HASH)
        }

        private const val SET_VARIATION_FACE_INDEX_HASH = 1286410249L
        private val setVariationFaceIndexBind by lazy {
            ObjectCalls.getMethodBind("FontVariation", "set_variation_face_index", SET_VARIATION_FACE_INDEX_HASH)
        }

        private const val GET_VARIATION_FACE_INDEX_HASH = 3905245786L
        private val getVariationFaceIndexBind by lazy {
            ObjectCalls.getMethodBind("FontVariation", "get_variation_face_index", GET_VARIATION_FACE_INDEX_HASH)
        }

        private const val SET_VARIATION_TRANSFORM_HASH = 2761652528L
        private val setVariationTransformBind by lazy {
            ObjectCalls.getMethodBind("FontVariation", "set_variation_transform", SET_VARIATION_TRANSFORM_HASH)
        }

        private const val GET_VARIATION_TRANSFORM_HASH = 3814499831L
        private val getVariationTransformBind by lazy {
            ObjectCalls.getMethodBind("FontVariation", "get_variation_transform", GET_VARIATION_TRANSFORM_HASH)
        }

        private const val SET_SPACING_HASH = 3122339690L
        private val setSpacingBind by lazy {
            ObjectCalls.getMethodBind("FontVariation", "set_spacing", SET_SPACING_HASH)
        }

        private const val SET_BASELINE_OFFSET_HASH = 373806689L
        private val setBaselineOffsetBind by lazy {
            ObjectCalls.getMethodBind("FontVariation", "set_baseline_offset", SET_BASELINE_OFFSET_HASH)
        }

        private const val GET_BASELINE_OFFSET_HASH = 1740695150L
        private val getBaselineOffsetBind by lazy {
            ObjectCalls.getMethodBind("FontVariation", "get_baseline_offset", GET_BASELINE_OFFSET_HASH)
        }

        private const val GET_PALETTE_INDEX_HASH = 3905245786L
        private val getPaletteIndexBind by lazy {
            ObjectCalls.getMethodBind("FontVariation", "get_palette_index", GET_PALETTE_INDEX_HASH)
        }

        private const val SET_PALETTE_INDEX_HASH = 1286410249L
        private val setPaletteIndexBind by lazy {
            ObjectCalls.getMethodBind("FontVariation", "set_palette_index", SET_PALETTE_INDEX_HASH)
        }

        private const val GET_PALETTE_CUSTOM_COLORS_HASH = 1392750486L
        private val getPaletteCustomColorsBind by lazy {
            ObjectCalls.getMethodBind("FontVariation", "get_palette_custom_colors", GET_PALETTE_CUSTOM_COLORS_HASH)
        }
    }
}
