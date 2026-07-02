package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeTextureParameter
 */
open class VisualShaderNodeTextureParameter(handle: MemorySegment) : VisualShaderNodeParameter(handle) {
    var textureType: Long
        @JvmName("textureTypeProperty")
        get() = getTextureType()
        @JvmName("setTextureTypeProperty")
        set(value) = setTextureType(value)

    var colorDefault: Long
        @JvmName("colorDefaultProperty")
        get() = getColorDefault()
        @JvmName("setColorDefaultProperty")
        set(value) = setColorDefault(value)

    var textureFilter: Long
        @JvmName("textureFilterProperty")
        get() = getTextureFilter()
        @JvmName("setTextureFilterProperty")
        set(value) = setTextureFilter(value)

    var textureRepeat: Long
        @JvmName("textureRepeatProperty")
        get() = getTextureRepeat()
        @JvmName("setTextureRepeatProperty")
        set(value) = setTextureRepeat(value)

    var textureSource: Long
        @JvmName("textureSourceProperty")
        get() = getTextureSource()
        @JvmName("setTextureSourceProperty")
        set(value) = setTextureSource(value)

    fun setTextureType(type: Long) {
        ObjectCalls.ptrcallWithLongArg(setTextureTypeBind, handle, type)
    }

    fun getTextureType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTextureTypeBind, handle)
    }

    fun setColorDefault(color: Long) {
        ObjectCalls.ptrcallWithLongArg(setColorDefaultBind, handle, color)
    }

    fun getColorDefault(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getColorDefaultBind, handle)
    }

    fun setTextureFilter(filter: Long) {
        ObjectCalls.ptrcallWithLongArg(setTextureFilterBind, handle, filter)
    }

    fun getTextureFilter(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTextureFilterBind, handle)
    }

    fun setTextureRepeat(repeat: Long) {
        ObjectCalls.ptrcallWithLongArg(setTextureRepeatBind, handle, repeat)
    }

    fun getTextureRepeat(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTextureRepeatBind, handle)
    }

    fun setTextureSource(source: Long) {
        ObjectCalls.ptrcallWithLongArg(setTextureSourceBind, handle, source)
    }

    fun getTextureSource(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTextureSourceBind, handle)
    }

    companion object {
        const val TYPE_DATA: Long = 0L
        const val TYPE_COLOR: Long = 1L
        const val TYPE_NORMAL_MAP: Long = 2L
        const val TYPE_ANISOTROPY: Long = 3L
        const val TYPE_MAX: Long = 4L
        const val COLOR_DEFAULT_WHITE: Long = 0L
        const val COLOR_DEFAULT_BLACK: Long = 1L
        const val COLOR_DEFAULT_TRANSPARENT: Long = 2L
        const val COLOR_DEFAULT_MAX: Long = 3L
        const val FILTER_DEFAULT: Long = 0L
        const val FILTER_NEAREST: Long = 1L
        const val FILTER_LINEAR: Long = 2L
        const val FILTER_NEAREST_MIPMAP: Long = 3L
        const val FILTER_LINEAR_MIPMAP: Long = 4L
        const val FILTER_NEAREST_MIPMAP_ANISOTROPIC: Long = 5L
        const val FILTER_LINEAR_MIPMAP_ANISOTROPIC: Long = 6L
        const val FILTER_MAX: Long = 7L
        const val REPEAT_DEFAULT: Long = 0L
        const val REPEAT_ENABLED: Long = 1L
        const val REPEAT_DISABLED: Long = 2L
        const val REPEAT_MAX: Long = 3L
        const val SOURCE_NONE: Long = 0L
        const val SOURCE_SCREEN: Long = 1L
        const val SOURCE_DEPTH: Long = 2L
        const val SOURCE_NORMAL_ROUGHNESS: Long = 3L
        const val SOURCE_MAX: Long = 4L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeTextureParameter? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeTextureParameter? =
            if (handle.address() == 0L) null else VisualShaderNodeTextureParameter(handle)

        private const val SET_TEXTURE_TYPE_HASH = 2227296876L
        private val setTextureTypeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeTextureParameter", "set_texture_type", SET_TEXTURE_TYPE_HASH)
        }

        private const val GET_TEXTURE_TYPE_HASH = 367922070L
        private val getTextureTypeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeTextureParameter", "get_texture_type", GET_TEXTURE_TYPE_HASH)
        }

        private const val SET_COLOR_DEFAULT_HASH = 4217624432L
        private val setColorDefaultBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeTextureParameter", "set_color_default", SET_COLOR_DEFAULT_HASH)
        }

        private const val GET_COLOR_DEFAULT_HASH = 3837060134L
        private val getColorDefaultBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeTextureParameter", "get_color_default", GET_COLOR_DEFAULT_HASH)
        }

        private const val SET_TEXTURE_FILTER_HASH = 2147684752L
        private val setTextureFilterBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeTextureParameter", "set_texture_filter", SET_TEXTURE_FILTER_HASH)
        }

        private const val GET_TEXTURE_FILTER_HASH = 4184490817L
        private val getTextureFilterBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeTextureParameter", "get_texture_filter", GET_TEXTURE_FILTER_HASH)
        }

        private const val SET_TEXTURE_REPEAT_HASH = 2036143070L
        private val setTextureRepeatBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeTextureParameter", "set_texture_repeat", SET_TEXTURE_REPEAT_HASH)
        }

        private const val GET_TEXTURE_REPEAT_HASH = 1690132794L
        private val getTextureRepeatBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeTextureParameter", "get_texture_repeat", GET_TEXTURE_REPEAT_HASH)
        }

        private const val SET_TEXTURE_SOURCE_HASH = 1212687372L
        private val setTextureSourceBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeTextureParameter", "set_texture_source", SET_TEXTURE_SOURCE_HASH)
        }

        private const val GET_TEXTURE_SOURCE_HASH = 2039092262L
        private val getTextureSourceBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeTextureParameter", "get_texture_source", GET_TEXTURE_SOURCE_HASH)
        }
    }
}
