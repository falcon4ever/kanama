package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
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

    var variationOpentype: Map<String, Any?>
        @JvmName("variationOpentypeProperty")
        get() = getVariationOpentype()
        @JvmName("setVariationOpentypeProperty")
        set(value) = setVariationOpentype(value)

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

    var paletteCustomColors: List<Color>
        @JvmName("paletteCustomColorsProperty")
        get() = getPaletteCustomColors()
        @JvmName("setPaletteCustomColorsProperty")
        set(value) = setPaletteCustomColors(value)

    fun setBaseFont(font: Font?) {
        ObjectCalls.ptrcallWithObjectArgs(setBaseFontBind, handle, listOf(font?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getBaseFont(): Font? {
        return Font.wrap(ObjectCalls.ptrcallNoArgsRetObject(getBaseFontBind, handle))
    }

    fun setVariationOpentype(coords: Map<String, Any?>) {
        ObjectCalls.ptrcallWithDictionaryArg(setVariationOpentypeBind, handle, coords)
    }

    fun getVariationOpentype(): Map<String, Any?> {
        return ObjectCalls.ptrcallNoArgsRetDictionary(getVariationOpentypeBind, handle)
    }

    fun setVariationEmbolden(strength: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVariationEmboldenBind, handle, strength)
    }

    fun getVariationEmbolden(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVariationEmboldenBind, handle)
    }

    fun setVariationFaceIndex(faceIndex: Int) {
        ObjectCalls.ptrcallWithIntArg(setVariationFaceIndexBind, handle, faceIndex)
    }

    fun getVariationFaceIndex(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getVariationFaceIndexBind, handle)
    }

    fun setVariationTransform(transform: Transform2D) {
        ObjectCalls.ptrcallWithTransform2DArg(setVariationTransformBind, handle, transform)
    }

    fun getVariationTransform(): Transform2D {
        return ObjectCalls.ptrcallNoArgsRetTransform2D(getVariationTransformBind, handle)
    }

    fun setOpentypeFeatures(features: Map<String, Any?>) {
        ObjectCalls.ptrcallWithDictionaryArg(setOpentypeFeaturesBind, handle, features)
    }

    fun setSpacing(spacing: Long, value: Int) {
        ObjectCalls.ptrcallWithLongAndIntArgs(setSpacingBind, handle, spacing, value)
    }

    fun setBaselineOffset(baselineOffset: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setBaselineOffsetBind, handle, baselineOffset)
    }

    fun getBaselineOffset(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getBaselineOffsetBind, handle)
    }

    fun getPaletteIndex(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPaletteIndexBind, handle)
    }

    fun setPaletteIndex(paletteIndex: Long) {
        ObjectCalls.ptrcallWithLongArg(setPaletteIndexBind, handle, paletteIndex)
    }

    fun getPaletteCustomColors(): List<Color> {
        return ObjectCalls.ptrcallNoArgsRetPackedColorList(getPaletteCustomColorsBind, handle)
    }

    fun setPaletteCustomColors(colors: List<Color>) {
        ObjectCalls.ptrcallWithPackedColorListArg(setPaletteCustomColorsBind, handle, colors)
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

        private const val SET_VARIATION_OPENTYPE_HASH = 4155329257L
        private val setVariationOpentypeBind by lazy {
            ObjectCalls.getMethodBind("FontVariation", "set_variation_opentype", SET_VARIATION_OPENTYPE_HASH)
        }

        private const val GET_VARIATION_OPENTYPE_HASH = 3102165223L
        private val getVariationOpentypeBind by lazy {
            ObjectCalls.getMethodBind("FontVariation", "get_variation_opentype", GET_VARIATION_OPENTYPE_HASH)
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

        private const val SET_OPENTYPE_FEATURES_HASH = 4155329257L
        private val setOpentypeFeaturesBind by lazy {
            ObjectCalls.getMethodBind("FontVariation", "set_opentype_features", SET_OPENTYPE_FEATURES_HASH)
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

        private const val SET_PALETTE_CUSTOM_COLORS_HASH = 3546319833L
        private val setPaletteCustomColorsBind by lazy {
            ObjectCalls.getMethodBind("FontVariation", "set_palette_custom_colors", SET_PALETTE_CUSTOM_COLORS_HASH)
        }
    }
}
