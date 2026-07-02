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

    fun setWidth(width: Int) {
        ObjectCalls.ptrcallWithIntArg(setWidthBind, handle, width)
    }

    fun setCurve(curve: Curve?) {
        ObjectCalls.ptrcallWithObjectArgs(setCurveBind, handle, listOf(curve?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getCurve(): Curve? {
        return Curve.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCurveBind, handle))
    }

    fun setTextureMode(textureMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setTextureModeBind, handle, textureMode)
    }

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
