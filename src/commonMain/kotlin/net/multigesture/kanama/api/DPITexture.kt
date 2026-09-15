package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector2i

/**
 * An automatically scalable `Texture2D` based on an SVG image.
 *
 * Generated from Godot docs: DPITexture
 */
class DPITexture(handle: GodotHandle) : Texture2D(handle) {
    var fixAlphaBorder: Boolean
        @JvmName("fixAlphaBorderProperty")
        get() = getFixAlphaBorder()
        @JvmName("setFixAlphaBorderProperty")
        set(value) = setFixAlphaBorder(value)

    var premultAlpha: Boolean
        @JvmName("premultAlphaProperty")
        get() = getPremultAlpha()
        @JvmName("setPremultAlphaProperty")
        set(value) = setPremultAlpha(value)

    var baseScale: Double
        @JvmName("baseScaleProperty")
        get() = getBaseScale()
        @JvmName("setBaseScaleProperty")
        set(value) = setBaseScale(value)

    var saturation: Double
        @JvmName("saturationProperty")
        get() = getSaturation()
        @JvmName("setSaturationProperty")
        set(value) = setSaturation(value)

    var colorMap: Map<String, Any?>
        @JvmName("colorMapProperty")
        get() = getColorMap()
        @JvmName("setColorMapProperty")
        set(value) = setColorMap(value)

    /**
     * Sets this SVG texture's source code.
     *
     * Generated from Godot docs: DPITexture.set_source
     */
    fun setSource(source: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setSourceBind, segment, source)
    }

    /**
     * Returns this SVG texture's source code.
     *
     * Generated from Godot docs: DPITexture.get_source
     */
    fun getSource(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getSourceBind, segment)
    }

    /**
     * If `true`, puts pixels of the same surrounding color in transition from transparent to opaque
     * areas. For textures displayed with bilinear filtering, this helps to reduce the outline effect
     * when exporting images from an image editor.
     *
     * Generated from Godot docs: DPITexture.set_fix_alpha_border
     */
    fun setFixAlphaBorder(fixAlphaBorder: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setFixAlphaBorderBind, segment, fixAlphaBorder)
    }

    /**
     * If `true`, puts pixels of the same surrounding color in transition from transparent to opaque
     * areas. For textures displayed with bilinear filtering, this helps to reduce the outline effect
     * when exporting images from an image editor.
     *
     * Generated from Godot docs: DPITexture.get_fix_alpha_border
     */
    fun getFixAlphaBorder(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getFixAlphaBorderBind, segment)
    }

    /**
     * An alternative to fixing darkened borders with `fix_alpha_border` is to use premultiplied alpha.
     * By enabling this option, the texture will be converted to this format. A premultiplied alpha
     * texture requires specific materials to be displayed correctly: - In 2D, a `CanvasItemMaterial`
     * will need to be created and configured to use the `CanvasItemMaterial.BLEND_MODE_PREMULT_ALPHA`
     * blend mode on `CanvasItem`s that use this texture. In custom `canvas_item` shaders, `render_mode
     * blend_premul_alpha;` should be used. - In 3D, a `BaseMaterial3D` will need to be created and
     * configured to use the `BaseMaterial3D.BLEND_MODE_PREMULT_ALPHA` blend mode on materials that use
     * this texture. In custom `spatial` shaders, `render_mode blend_premul_alpha;` should be used.
     *
     * Generated from Godot docs: DPITexture.set_premult_alpha
     */
    fun setPremultAlpha(premultAlpha: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setPremultAlphaBind, segment, premultAlpha)
    }

    /**
     * An alternative to fixing darkened borders with `fix_alpha_border` is to use premultiplied alpha.
     * By enabling this option, the texture will be converted to this format. A premultiplied alpha
     * texture requires specific materials to be displayed correctly: - In 2D, a `CanvasItemMaterial`
     * will need to be created and configured to use the `CanvasItemMaterial.BLEND_MODE_PREMULT_ALPHA`
     * blend mode on `CanvasItem`s that use this texture. In custom `canvas_item` shaders, `render_mode
     * blend_premul_alpha;` should be used. - In 3D, a `BaseMaterial3D` will need to be created and
     * configured to use the `BaseMaterial3D.BLEND_MODE_PREMULT_ALPHA` blend mode on materials that use
     * this texture. In custom `spatial` shaders, `render_mode blend_premul_alpha;` should be used.
     *
     * Generated from Godot docs: DPITexture.get_premult_alpha
     */
    fun getPremultAlpha(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getPremultAlphaBind, segment)
    }

    /**
     * Texture scale. `1.0` is the original SVG size. Higher values result in a larger image.
     *
     * Generated from Godot docs: DPITexture.set_base_scale
     */
    fun setBaseScale(baseScale: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setBaseScaleBind, segment, baseScale)
    }

    /**
     * Texture scale. `1.0` is the original SVG size. Higher values result in a larger image.
     *
     * Generated from Godot docs: DPITexture.get_base_scale
     */
    fun getBaseScale(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getBaseScaleBind, segment)
    }

    /**
     * Overrides texture saturation.
     *
     * Generated from Godot docs: DPITexture.set_saturation
     */
    fun setSaturation(saturation: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setSaturationBind, segment, saturation)
    }

    /**
     * Overrides texture saturation.
     *
     * Generated from Godot docs: DPITexture.get_saturation
     */
    fun getSaturation(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getSaturationBind, segment)
    }

    /**
     * If set, remaps texture colors according to `Color`-`Color` map.
     *
     * Generated from Godot docs: DPITexture.set_color_map
     */
    fun setColorMap(colorMap: Map<String, Any?>) {
        checkOpen()
        ObjectCalls.ptrcallWithDictionaryArg(setColorMapBind, segment, colorMap)
    }

    /**
     * If set, remaps texture colors according to `Color`-`Color` map.
     *
     * Generated from Godot docs: DPITexture.get_color_map
     */
    fun getColorMap(): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDictionary(getColorMapBind, segment)
    }

    /**
     * Resizes the texture to the specified dimensions.
     *
     * Generated from Godot docs: DPITexture.set_size_override
     */
    fun setSizeOverride(size: Vector2i) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2iArg(setSizeOverrideBind, segment, size)
    }

    /**
     * Returns the `RID` of the texture rasterized to match the oversampling of the currently drawn
     * canvas item.
     *
     * Generated from Godot docs: DPITexture.get_scaled_rid
     */
    fun getScaledRid(): RID {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetRID(getScaledRidBind, segment)
    }

    companion object {
        /**
         * Creates a new `DPITexture` and initializes it by allocating and setting the SVG data to
         * `source`.
         *
         * Generated from Godot docs: DPITexture.create_from_string
         */
        fun createFromString(source: String, scale: Double = 1.0, saturation: Double = 1.0, colorMap: Map<String, Any?> = emptyMap()): DPITexture? {
            return DPITexture.wrap(ObjectCalls.ptrcallWithStringTwoDoubleDictionaryArgsRetObject(createFromStringBind, NULL_SEGMENT, source, scale, saturation, colorMap))
        }

        @JvmStatic
        fun fromHandle(handle: GodotHandle): DPITexture? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): DPITexture? =
            if (handle.address() == 0L) null else DPITexture(GodotHandle(handle))

        private const val CREATE_FROM_STRING_HASH = 755140520L
        private val createFromStringBind by lazy {
            ObjectCalls.getMethodBind("DPITexture", "create_from_string", CREATE_FROM_STRING_HASH)
        }

        private const val SET_SOURCE_HASH = 83702148L
        private val setSourceBind by lazy {
            ObjectCalls.getMethodBind("DPITexture", "set_source", SET_SOURCE_HASH)
        }

        private const val GET_SOURCE_HASH = 201670096L
        private val getSourceBind by lazy {
            ObjectCalls.getMethodBind("DPITexture", "get_source", GET_SOURCE_HASH)
        }

        private const val SET_FIX_ALPHA_BORDER_HASH = 2586408642L
        private val setFixAlphaBorderBind by lazy {
            ObjectCalls.getMethodBind("DPITexture", "set_fix_alpha_border", SET_FIX_ALPHA_BORDER_HASH)
        }

        private const val GET_FIX_ALPHA_BORDER_HASH = 36873697L
        private val getFixAlphaBorderBind by lazy {
            ObjectCalls.getMethodBind("DPITexture", "get_fix_alpha_border", GET_FIX_ALPHA_BORDER_HASH)
        }

        private const val SET_PREMULT_ALPHA_HASH = 2586408642L
        private val setPremultAlphaBind by lazy {
            ObjectCalls.getMethodBind("DPITexture", "set_premult_alpha", SET_PREMULT_ALPHA_HASH)
        }

        private const val GET_PREMULT_ALPHA_HASH = 36873697L
        private val getPremultAlphaBind by lazy {
            ObjectCalls.getMethodBind("DPITexture", "get_premult_alpha", GET_PREMULT_ALPHA_HASH)
        }

        private const val SET_BASE_SCALE_HASH = 373806689L
        private val setBaseScaleBind by lazy {
            ObjectCalls.getMethodBind("DPITexture", "set_base_scale", SET_BASE_SCALE_HASH)
        }

        private const val GET_BASE_SCALE_HASH = 1740695150L
        private val getBaseScaleBind by lazy {
            ObjectCalls.getMethodBind("DPITexture", "get_base_scale", GET_BASE_SCALE_HASH)
        }

        private const val SET_SATURATION_HASH = 373806689L
        private val setSaturationBind by lazy {
            ObjectCalls.getMethodBind("DPITexture", "set_saturation", SET_SATURATION_HASH)
        }

        private const val GET_SATURATION_HASH = 1740695150L
        private val getSaturationBind by lazy {
            ObjectCalls.getMethodBind("DPITexture", "get_saturation", GET_SATURATION_HASH)
        }

        private const val SET_COLOR_MAP_HASH = 4155329257L
        private val setColorMapBind by lazy {
            ObjectCalls.getMethodBind("DPITexture", "set_color_map", SET_COLOR_MAP_HASH)
        }

        private const val GET_COLOR_MAP_HASH = 3102165223L
        private val getColorMapBind by lazy {
            ObjectCalls.getMethodBind("DPITexture", "get_color_map", GET_COLOR_MAP_HASH)
        }

        private const val SET_SIZE_OVERRIDE_HASH = 1130785943L
        private val setSizeOverrideBind by lazy {
            ObjectCalls.getMethodBind("DPITexture", "set_size_override", SET_SIZE_OVERRIDE_HASH)
        }

        private const val GET_SCALED_RID_HASH = 2944877500L
        private val getScaledRidBind by lazy {
            ObjectCalls.getMethodBind("DPITexture", "get_scaled_rid", GET_SCALED_RID_HASH)
        }
    }
}
