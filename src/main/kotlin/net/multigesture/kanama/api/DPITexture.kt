package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector2i

/**
 * An automatically scalable `Texture2D` based on an SVG image.
 *
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

    var colorMap: Map<String, Any?>
        @JvmName("colorMapProperty")
        get() = getColorMap()
        @JvmName("setColorMapProperty")
        set(value) = setColorMap(value)

    fun setSource(source: String) {
        ObjectCalls.ptrcallWithStringArg(setSourceBind, handle, source)
    }

    fun getSource(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getSourceBind, handle)
    }

    fun setFixAlphaBorder(fixAlphaBorder: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFixAlphaBorderBind, handle, fixAlphaBorder)
    }

    fun getFixAlphaBorder(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getFixAlphaBorderBind, handle)
    }

    fun setPremultAlpha(premultAlpha: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPremultAlphaBind, handle, premultAlpha)
    }

    fun getPremultAlpha(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getPremultAlphaBind, handle)
    }

    fun setBaseScale(baseScale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setBaseScaleBind, handle, baseScale)
    }

    fun getBaseScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getBaseScaleBind, handle)
    }

    fun setSaturation(saturation: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSaturationBind, handle, saturation)
    }

    fun getSaturation(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSaturationBind, handle)
    }

    fun setColorMap(colorMap: Map<String, Any?>) {
        ObjectCalls.ptrcallWithDictionaryArg(setColorMapBind, handle, colorMap)
    }

    fun getColorMap(): Map<String, Any?> {
        return ObjectCalls.ptrcallNoArgsRetDictionary(getColorMapBind, handle)
    }

    fun setSizeOverride(size: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(setSizeOverrideBind, handle, size)
    }

    fun getScaledRid(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getScaledRidBind, handle)
    }

    companion object {
        fun createFromString(source: String, scale: Double = 1.0, saturation: Double = 1.0, colorMap: Map<String, Any?> = emptyMap()): DPITexture? {
            return DPITexture.wrap(ObjectCalls.ptrcallWithStringTwoDoubleDictionaryArgsRetObject(createFromStringBind, MemorySegment.NULL, source, scale, saturation, colorMap))
        }

        @JvmStatic
        fun fromHandle(handle: MemorySegment): DPITexture? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): DPITexture? =
            if (handle.address() == 0L) null else DPITexture(handle)

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
