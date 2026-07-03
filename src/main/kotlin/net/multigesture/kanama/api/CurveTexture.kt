package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A 1D texture where pixel brightness corresponds to points on a curve.
 *
 * Generated from Godot docs: CurveTexture
 */
class CurveTexture(handle: MemorySegment) : Texture2D(handle) {
    var textureMode: Long
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
        ObjectCalls.ptrcallWithIntArg(setWidthBind, handle, width)
    }

    /**
     * The `Curve` that is rendered onto the texture. Should be a unit `Curve`.
     *
     * Generated from Godot docs: CurveTexture.set_curve
     */
    fun setCurve(curve: Curve?) {
        ObjectCalls.ptrcallWithObjectArgs(setCurveBind, handle, listOf(curve?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The `Curve` that is rendered onto the texture. Should be a unit `Curve`.
     *
     * Generated from Godot docs: CurveTexture.get_curve
     */
    fun getCurve(): Curve? {
        return Curve.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCurveBind, handle))
    }

    /**
     * The format the texture should be generated with. When passing a CurveTexture as an input to a
     * `Shader`, this may need to be adjusted.
     *
     * Generated from Godot docs: CurveTexture.set_texture_mode
     */
    fun setTextureMode(textureMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setTextureModeBind, handle, textureMode)
    }

    /**
     * The format the texture should be generated with. When passing a CurveTexture as an input to a
     * `Shader`, this may need to be adjusted.
     *
     * Generated from Godot docs: CurveTexture.get_texture_mode
     */
    fun getTextureMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTextureModeBind, handle)
    }

    companion object {
        const val TEXTURE_MODE_RGB: Long = 0L
        const val TEXTURE_MODE_RED: Long = 1L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): CurveTexture? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CurveTexture? =
            if (handle.address() == 0L) null else CurveTexture(handle)

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
