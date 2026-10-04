package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeTextureParameter
 */
open class VisualShaderNodeTextureParameter(handle: GodotHandle) : VisualShaderNodeParameter(handle) {
    var textureType: VisualShaderNodeTextureParameter.TextureType
        @JvmName("textureTypeProperty")
        get() = getTextureType()
        @JvmName("setTextureTypeProperty")
        set(value) = setTextureType(value)

    var colorDefault: VisualShaderNodeTextureParameter.ColorDefault
        @JvmName("colorDefaultProperty")
        get() = getColorDefault()
        @JvmName("setColorDefaultProperty")
        set(value) = setColorDefault(value)

    var textureFilter: VisualShaderNodeTextureParameter.TextureFilter
        @JvmName("textureFilterProperty")
        get() = getTextureFilter()
        @JvmName("setTextureFilterProperty")
        set(value) = setTextureFilter(value)

    var textureRepeat: VisualShaderNodeTextureParameter.TextureRepeat
        @JvmName("textureRepeatProperty")
        get() = getTextureRepeat()
        @JvmName("setTextureRepeatProperty")
        set(value) = setTextureRepeat(value)

    var textureSource: VisualShaderNodeTextureParameter.TextureSource
        @JvmName("textureSourceProperty")
        get() = getTextureSource()
        @JvmName("setTextureSourceProperty")
        set(value) = setTextureSource(value)

    fun setTextureType(type: VisualShaderNodeTextureParameter.TextureType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setTextureTypeBind, segment, type.value)
    }

    fun getTextureType(): VisualShaderNodeTextureParameter.TextureType {
        checkOpen()
        return VisualShaderNodeTextureParameter.TextureType(ObjectCalls.ptrcallNoArgsRetLong(getTextureTypeBind, segment))
    }

    fun setColorDefault(color: VisualShaderNodeTextureParameter.ColorDefault) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setColorDefaultBind, segment, color.value)
    }

    fun getColorDefault(): VisualShaderNodeTextureParameter.ColorDefault {
        checkOpen()
        return VisualShaderNodeTextureParameter.ColorDefault(ObjectCalls.ptrcallNoArgsRetLong(getColorDefaultBind, segment))
    }

    fun setTextureFilter(filter: VisualShaderNodeTextureParameter.TextureFilter) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setTextureFilterBind, segment, filter.value)
    }

    fun getTextureFilter(): VisualShaderNodeTextureParameter.TextureFilter {
        checkOpen()
        return VisualShaderNodeTextureParameter.TextureFilter(ObjectCalls.ptrcallNoArgsRetLong(getTextureFilterBind, segment))
    }

    fun setTextureRepeat(repeat: VisualShaderNodeTextureParameter.TextureRepeat) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setTextureRepeatBind, segment, repeat.value)
    }

    fun getTextureRepeat(): VisualShaderNodeTextureParameter.TextureRepeat {
        checkOpen()
        return VisualShaderNodeTextureParameter.TextureRepeat(ObjectCalls.ptrcallNoArgsRetLong(getTextureRepeatBind, segment))
    }

    fun setTextureSource(source: VisualShaderNodeTextureParameter.TextureSource) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setTextureSourceBind, segment, source.value)
    }

    fun getTextureSource(): VisualShaderNodeTextureParameter.TextureSource {
        checkOpen()
        return VisualShaderNodeTextureParameter.TextureSource(ObjectCalls.ptrcallNoArgsRetLong(getTextureSourceBind, segment))
    }

    @JvmInline
    value class TextureType(override val value: Long) : GodotEnumValue {
        companion object {
            val DATA: TextureType get() = TextureType(0L)
            val COLOR: TextureType get() = TextureType(1L)
            val NORMAL_MAP: TextureType get() = TextureType(2L)
            val ANISOTROPY: TextureType get() = TextureType(3L)
            val MAX: TextureType get() = TextureType(4L)
        }
    }

    @JvmInline
    value class ColorDefault(override val value: Long) : GodotEnumValue {
        companion object {
            val WHITE: ColorDefault get() = ColorDefault(0L)
            val BLACK: ColorDefault get() = ColorDefault(1L)
            val TRANSPARENT: ColorDefault get() = ColorDefault(2L)
            val MAX: ColorDefault get() = ColorDefault(3L)
        }
    }

    @JvmInline
    value class TextureFilter(override val value: Long) : GodotEnumValue {
        companion object {
            val DEFAULT: TextureFilter get() = TextureFilter(0L)
            val NEAREST: TextureFilter get() = TextureFilter(1L)
            val LINEAR: TextureFilter get() = TextureFilter(2L)
            val NEAREST_MIPMAP: TextureFilter get() = TextureFilter(3L)
            val LINEAR_MIPMAP: TextureFilter get() = TextureFilter(4L)
            val NEAREST_MIPMAP_ANISOTROPIC: TextureFilter get() = TextureFilter(5L)
            val LINEAR_MIPMAP_ANISOTROPIC: TextureFilter get() = TextureFilter(6L)
            val MAX: TextureFilter get() = TextureFilter(7L)
        }
    }

    @JvmInline
    value class TextureRepeat(override val value: Long) : GodotEnumValue {
        companion object {
            val DEFAULT: TextureRepeat get() = TextureRepeat(0L)
            val ENABLED: TextureRepeat get() = TextureRepeat(1L)
            val DISABLED: TextureRepeat get() = TextureRepeat(2L)
            val MAX: TextureRepeat get() = TextureRepeat(3L)
        }
    }

    @JvmInline
    value class TextureSource(override val value: Long) : GodotEnumValue {
        companion object {
            val NONE: TextureSource get() = TextureSource(0L)
            val SCREEN: TextureSource get() = TextureSource(1L)
            val DEPTH: TextureSource get() = TextureSource(2L)
            val NORMAL_ROUGHNESS: TextureSource get() = TextureSource(3L)
            val MAX: TextureSource get() = TextureSource(4L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeTextureParameter? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeTextureParameter? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeTextureParameter(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeTextureParameter? =
            if (handle.address() == 0L) null else VisualShaderNodeTextureParameter(GodotHandle(handle))

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
