package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A 1D texture where pixel brightness corresponds to points on a curve.
 *
 * Generated from Godot docs: CurveTexture
 */
class CurveTexture(handle: GodotHandle) : Texture2D(handle) {
    var textureMode: CurveTexture.TextureMode
        @JvmName("textureModeProperty")
        get() = getTextureMode()
        @JvmName("setTextureModeProperty")
        set(value) = setTextureMode(value)

    var curve: Curve?
        @JvmName("curveProperty")
        get() = getCurve()
        @JvmName("setCurveProperty")
        set(value) = setCurve(value)

    /**
     * The width of the texture (in pixels). Higher values make it possible to represent high-frequency
     * data better (such as sudden direction changes), at the cost of increased generation time and
     * memory usage.
     *
     * Generated from Godot docs: CurveTexture.set_width
     */
    fun setWidth(width: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setWidthBind, segment, width)
    }

    /**
     * The `Curve` that is rendered onto the texture. Should be a unit `Curve`.
     *
     * Generated from Godot docs: CurveTexture.set_curve
     */
    fun setCurve(curve: Curve?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setCurveBind, segment, listOf(curve?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The `Curve` that is rendered onto the texture. Should be a unit `Curve`.
     *
     * Generated from Godot docs: CurveTexture.get_curve
     */
    fun getCurve(): Curve? {
        checkOpen()
        return Curve.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCurveBind, segment))
    }

    /**
     * The format the texture should be generated with. When passing a CurveTexture as an input to a
     * `Shader`, this may need to be adjusted.
     *
     * Generated from Godot docs: CurveTexture.set_texture_mode
     */
    fun setTextureMode(textureMode: CurveTexture.TextureMode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setTextureModeBind, segment, textureMode.value)
    }

    /**
     * The format the texture should be generated with. When passing a CurveTexture as an input to a
     * `Shader`, this may need to be adjusted.
     *
     * Generated from Godot docs: CurveTexture.get_texture_mode
     */
    fun getTextureMode(): CurveTexture.TextureMode {
        checkOpen()
        return CurveTexture.TextureMode(ObjectCalls.ptrcallNoArgsRetLong(getTextureModeBind, segment))
    }

    /**
     * Godot's `CurveTexture.TextureMode` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`CurveTexture.TextureMode.<NAME>`).
     *
     * Generated from Godot docs: CurveTexture.TextureMode
     */
    @JvmInline
    value class TextureMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Store the curve equally across the red, green and blue channels. This uses more video memory,
             * but is more compatible with shaders that only read the green and blue values.
             *
             * Generated from Godot docs: CurveTexture.TEXTURE_MODE_RGB
             */
            val RGB: TextureMode get() = TextureMode(0L)
            /**
             * Store the curve only in the red channel. This saves video memory, but some custom shaders may
             * not be able to work with this.
             *
             * Generated from Godot docs: CurveTexture.TEXTURE_MODE_RED
             */
            val RED: TextureMode get() = TextureMode(1L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CurveTexture? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): CurveTexture? =
            if (handle.address() == 0L) null else CurveTexture(GodotHandle(handle))

        private const val SET_WIDTH_HASH = 1286410249L
        private val setWidthBind by lazy {
            ObjectCalls.getMethodBind("CurveTexture", "set_width", SET_WIDTH_HASH)
        }

        private const val SET_CURVE_HASH = 270443179L
        private val setCurveBind by lazy {
            ObjectCalls.getMethodBind("CurveTexture", "set_curve", SET_CURVE_HASH)
        }

        private const val GET_CURVE_HASH = 2460114913L
        private val getCurveBind by lazy {
            ObjectCalls.getMethodBind("CurveTexture", "get_curve", GET_CURVE_HASH)
        }

        private const val SET_TEXTURE_MODE_HASH = 1321955367L
        private val setTextureModeBind by lazy {
            ObjectCalls.getMethodBind("CurveTexture", "set_texture_mode", SET_TEXTURE_MODE_HASH)
        }

        private const val GET_TEXTURE_MODE_HASH = 715756376L
        private val getTextureModeBind by lazy {
            ObjectCalls.getMethodBind("CurveTexture", "get_texture_mode", GET_TEXTURE_MODE_HASH)
        }
    }
}
