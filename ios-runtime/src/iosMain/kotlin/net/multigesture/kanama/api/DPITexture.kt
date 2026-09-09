package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector2i

/**
 * Generated from Godot docs: DPITexture
 */
class DPITexture(handle: MemorySegment) : Texture2D(handle) {
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

    fun setSource(source: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setSourceBind, handle, source)
    }

    fun getSource(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getSourceBind, handle)
    }

    fun setFixAlphaBorder(fixAlphaBorder: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setFixAlphaBorderBind, handle, fixAlphaBorder)
    }

    fun getFixAlphaBorder(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getFixAlphaBorderBind, handle)
    }

    fun setPremultAlpha(premultAlpha: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setPremultAlphaBind, handle, premultAlpha)
    }

    fun getPremultAlpha(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getPremultAlphaBind, handle)
    }

    fun setBaseScale(baseScale: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setBaseScaleBind, handle, baseScale)
    }

    fun getBaseScale(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getBaseScaleBind, handle)
    }

    fun setSaturation(saturation: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setSaturationBind, handle, saturation)
    }

    fun getSaturation(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getSaturationBind, handle)
    }

    fun setSizeOverride(size: Vector2i) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2iArg(setSizeOverrideBind, handle, size)
    }

    fun getScaledRid(): RID {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetRID(getScaledRidBind, handle)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): DPITexture? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): DPITexture? =
            if (handle.address() == 0L) null else DPITexture(handle)

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
