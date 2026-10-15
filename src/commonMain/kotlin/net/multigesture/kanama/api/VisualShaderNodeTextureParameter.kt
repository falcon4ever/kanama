package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Performs a uniform texture lookup within the visual shader graph.
 *
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

    /**
     * Defines the type of data provided by the source texture.
     *
     * Generated from Godot docs: VisualShaderNodeTextureParameter.set_texture_type
     */
    fun setTextureType(type: VisualShaderNodeTextureParameter.TextureType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setTextureTypeBind, segment, type.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Defines the type of data provided by the source texture.
     *
     * Generated from Godot docs: VisualShaderNodeTextureParameter.get_texture_type
     */
    fun getTextureType(): VisualShaderNodeTextureParameter.TextureType {
        checkOpen()
        val ret = VisualShaderNodeTextureParameter.TextureType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTextureTypeBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the default color if no texture is assigned to the uniform.
     *
     * Generated from Godot docs: VisualShaderNodeTextureParameter.set_color_default
     */
    fun setColorDefault(color: VisualShaderNodeTextureParameter.ColorDefault) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setColorDefaultBind, segment, color.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Sets the default color if no texture is assigned to the uniform.
     *
     * Generated from Godot docs: VisualShaderNodeTextureParameter.get_color_default
     */
    fun getColorDefault(): VisualShaderNodeTextureParameter.ColorDefault {
        checkOpen()
        val ret = VisualShaderNodeTextureParameter.ColorDefault(ObjectCalls.ptrcallNoArgsRetLong(Binds.getColorDefaultBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the texture filtering mode.
     *
     * Generated from Godot docs: VisualShaderNodeTextureParameter.set_texture_filter
     */
    fun setTextureFilter(filter: VisualShaderNodeTextureParameter.TextureFilter) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setTextureFilterBind, segment, filter.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Sets the texture filtering mode.
     *
     * Generated from Godot docs: VisualShaderNodeTextureParameter.get_texture_filter
     */
    fun getTextureFilter(): VisualShaderNodeTextureParameter.TextureFilter {
        checkOpen()
        val ret = VisualShaderNodeTextureParameter.TextureFilter(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTextureFilterBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the texture repeating mode.
     *
     * Generated from Godot docs: VisualShaderNodeTextureParameter.set_texture_repeat
     */
    fun setTextureRepeat(repeat: VisualShaderNodeTextureParameter.TextureRepeat) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setTextureRepeatBind, segment, repeat.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Sets the texture repeating mode.
     *
     * Generated from Godot docs: VisualShaderNodeTextureParameter.get_texture_repeat
     */
    fun getTextureRepeat(): VisualShaderNodeTextureParameter.TextureRepeat {
        checkOpen()
        val ret = VisualShaderNodeTextureParameter.TextureRepeat(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTextureRepeatBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the texture source mode. Used for reading from the screen, depth, or normal_roughness
     * texture.
     *
     * Generated from Godot docs: VisualShaderNodeTextureParameter.set_texture_source
     */
    fun setTextureSource(source: VisualShaderNodeTextureParameter.TextureSource) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setTextureSourceBind, segment, source.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Sets the texture source mode. Used for reading from the screen, depth, or normal_roughness
     * texture.
     *
     * Generated from Godot docs: VisualShaderNodeTextureParameter.get_texture_source
     */
    fun getTextureSource(): VisualShaderNodeTextureParameter.TextureSource {
        checkOpen()
        val ret = VisualShaderNodeTextureParameter.TextureSource(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTextureSourceBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Godot's `VisualShaderNodeTextureParameter.TextureType` enum as a typed value: `.value` is the
     * raw number Godot uses, and the companion holds the named values
     * (`VisualShaderNodeTextureParameter.TextureType.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeTextureParameter.TextureType
     */
    @JvmInline
    value class TextureType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * No hints are added to the uniform declaration.
             *
             * Generated from Godot docs: VisualShaderNodeTextureParameter.TYPE_DATA
             */
            val DATA: TextureType get() = TextureType(0L)
            /**
             * Adds `source_color` as hint to the uniform declaration for proper conversion from nonlinear sRGB
             * encoding to linear encoding.
             *
             * Generated from Godot docs: VisualShaderNodeTextureParameter.TYPE_COLOR
             */
            val COLOR: TextureType get() = TextureType(1L)
            /**
             * Adds `hint_normal` as hint to the uniform declaration, which internally converts the texture for
             * proper usage as normal map.
             *
             * Generated from Godot docs: VisualShaderNodeTextureParameter.TYPE_NORMAL_MAP
             */
            val NORMAL_MAP: TextureType get() = TextureType(2L)
            /**
             * Adds `hint_anisotropy` as hint to the uniform declaration to use for a flowmap.
             *
             * Generated from Godot docs: VisualShaderNodeTextureParameter.TYPE_ANISOTROPY
             */
            val ANISOTROPY: TextureType get() = TextureType(3L)
            /**
             * Represents the size of the `TextureType` enum.
             *
             * Generated from Godot docs: VisualShaderNodeTextureParameter.TYPE_MAX
             */
            val MAX: TextureType get() = TextureType(4L)
        }
    }

    /**
     * Godot's `VisualShaderNodeTextureParameter.ColorDefault` enum as a typed value: `.value` is the
     * raw number Godot uses, and the companion holds the named values
     * (`VisualShaderNodeTextureParameter.ColorDefault.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeTextureParameter.ColorDefault
     */
    @JvmInline
    value class ColorDefault(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Defaults to fully opaque white color.
             *
             * Generated from Godot docs: VisualShaderNodeTextureParameter.COLOR_DEFAULT_WHITE
             */
            val WHITE: ColorDefault get() = ColorDefault(0L)
            /**
             * Defaults to fully opaque black color.
             *
             * Generated from Godot docs: VisualShaderNodeTextureParameter.COLOR_DEFAULT_BLACK
             */
            val BLACK: ColorDefault get() = ColorDefault(1L)
            /**
             * Defaults to fully transparent black color.
             *
             * Generated from Godot docs: VisualShaderNodeTextureParameter.COLOR_DEFAULT_TRANSPARENT
             */
            val TRANSPARENT: ColorDefault get() = ColorDefault(2L)
            /**
             * Represents the size of the `ColorDefault` enum.
             *
             * Generated from Godot docs: VisualShaderNodeTextureParameter.COLOR_DEFAULT_MAX
             */
            val MAX: ColorDefault get() = ColorDefault(3L)
        }
    }

    /**
     * Godot's `VisualShaderNodeTextureParameter.TextureFilter` enum as a typed value: `.value` is the
     * raw number Godot uses, and the companion holds the named values
     * (`VisualShaderNodeTextureParameter.TextureFilter.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeTextureParameter.TextureFilter
     */
    @JvmInline
    value class TextureFilter(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Sample the texture using the filter determined by the node this shader is attached to.
             *
             * Generated from Godot docs: VisualShaderNodeTextureParameter.FILTER_DEFAULT
             */
            val DEFAULT: TextureFilter get() = TextureFilter(0L)
            /**
             * The texture filter reads from the nearest pixel only. This makes the texture look pixelated from
             * up close, and grainy from a distance (due to mipmaps not being sampled).
             *
             * Generated from Godot docs: VisualShaderNodeTextureParameter.FILTER_NEAREST
             */
            val NEAREST: TextureFilter get() = TextureFilter(1L)
            /**
             * The texture filter blends between the nearest 4 pixels. This makes the texture look smooth from
             * up close, and grainy from a distance (due to mipmaps not being sampled).
             *
             * Generated from Godot docs: VisualShaderNodeTextureParameter.FILTER_LINEAR
             */
            val LINEAR: TextureFilter get() = TextureFilter(2L)
            /**
             * The texture filter reads from the nearest pixel and blends between the nearest 2 mipmaps (or
             * uses the nearest mipmap if
             * `ProjectSettings.rendering/textures/default_filters/use_nearest_mipmap_filter` is `true`). This
             * makes the texture look pixelated from up close, and smooth from a distance. Use this for
             * non-pixel art textures that may be viewed at a low scale (e.g. due to `Camera2D` zoom or sprite
             * scaling), as mipmaps are important to smooth out pixels that are smaller than on-screen pixels.
             *
             * Generated from Godot docs: VisualShaderNodeTextureParameter.FILTER_NEAREST_MIPMAP
             */
            val NEAREST_MIPMAP: TextureFilter get() = TextureFilter(3L)
            /**
             * The texture filter blends between the nearest 4 pixels and between the nearest 2 mipmaps (or
             * uses the nearest mipmap if
             * `ProjectSettings.rendering/textures/default_filters/use_nearest_mipmap_filter` is `true`). This
             * makes the texture look smooth from up close, and smooth from a distance. Use this for non-pixel
             * art textures that may be viewed at a low scale (e.g. due to `Camera2D` zoom or sprite scaling),
             * as mipmaps are important to smooth out pixels that are smaller than on-screen pixels.
             *
             * Generated from Godot docs: VisualShaderNodeTextureParameter.FILTER_LINEAR_MIPMAP
             */
            val LINEAR_MIPMAP: TextureFilter get() = TextureFilter(4L)
            /**
             * The texture filter reads from the nearest pixel and blends between 2 mipmaps (or uses the
             * nearest mipmap if `ProjectSettings.rendering/textures/default_filters/use_nearest_mipmap_filter`
             * is `true`) based on the angle between the surface and the camera view. This makes the texture
             * look pixelated from up close, and smooth from a distance. Anisotropic filtering improves texture
             * quality on surfaces that are almost in line with the camera, but is slightly slower. The
             * anisotropic filtering level can be changed by adjusting
             * `ProjectSettings.rendering/textures/default_filters/anisotropic_filtering_level`. Note: This
             * texture filter is rarely useful in 2D projects. `TextureFilter.NEAREST_MIPMAP` is usually more
             * appropriate in this case.
             *
             * Generated from Godot docs: VisualShaderNodeTextureParameter.FILTER_NEAREST_MIPMAP_ANISOTROPIC
             */
            val NEAREST_MIPMAP_ANISOTROPIC: TextureFilter get() = TextureFilter(5L)
            /**
             * The texture filter blends between the nearest 4 pixels and blends between 2 mipmaps (or uses the
             * nearest mipmap if `ProjectSettings.rendering/textures/default_filters/use_nearest_mipmap_filter`
             * is `true`) based on the angle between the surface and the camera view. This makes the texture
             * look smooth from up close, and smooth from a distance. Anisotropic filtering improves texture
             * quality on surfaces that are almost in line with the camera, but is slightly slower. The
             * anisotropic filtering level can be changed by adjusting
             * `ProjectSettings.rendering/textures/default_filters/anisotropic_filtering_level`. Note: This
             * texture filter is rarely useful in 2D projects. `TextureFilter.LINEAR_MIPMAP` is usually more
             * appropriate in this case.
             *
             * Generated from Godot docs: VisualShaderNodeTextureParameter.FILTER_LINEAR_MIPMAP_ANISOTROPIC
             */
            val LINEAR_MIPMAP_ANISOTROPIC: TextureFilter get() = TextureFilter(6L)
            /**
             * Represents the size of the `TextureFilter` enum.
             *
             * Generated from Godot docs: VisualShaderNodeTextureParameter.FILTER_MAX
             */
            val MAX: TextureFilter get() = TextureFilter(7L)
        }
    }

    /**
     * Godot's `VisualShaderNodeTextureParameter.TextureRepeat` enum as a typed value: `.value` is the
     * raw number Godot uses, and the companion holds the named values
     * (`VisualShaderNodeTextureParameter.TextureRepeat.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeTextureParameter.TextureRepeat
     */
    @JvmInline
    value class TextureRepeat(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Sample the texture using the repeat mode determined by the node this shader is attached to.
             *
             * Generated from Godot docs: VisualShaderNodeTextureParameter.REPEAT_DEFAULT
             */
            val DEFAULT: TextureRepeat get() = TextureRepeat(0L)
            /**
             * Texture will repeat normally.
             *
             * Generated from Godot docs: VisualShaderNodeTextureParameter.REPEAT_ENABLED
             */
            val ENABLED: TextureRepeat get() = TextureRepeat(1L)
            /**
             * Texture will not repeat.
             *
             * Generated from Godot docs: VisualShaderNodeTextureParameter.REPEAT_DISABLED
             */
            val DISABLED: TextureRepeat get() = TextureRepeat(2L)
            /**
             * Represents the size of the `TextureRepeat` enum.
             *
             * Generated from Godot docs: VisualShaderNodeTextureParameter.REPEAT_MAX
             */
            val MAX: TextureRepeat get() = TextureRepeat(3L)
        }
    }

    /**
     * Godot's `VisualShaderNodeTextureParameter.TextureSource` enum as a typed value: `.value` is the
     * raw number Godot uses, and the companion holds the named values
     * (`VisualShaderNodeTextureParameter.TextureSource.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeTextureParameter.TextureSource
     */
    @JvmInline
    value class TextureSource(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The texture source is not specified in the shader.
             *
             * Generated from Godot docs: VisualShaderNodeTextureParameter.SOURCE_NONE
             */
            val NONE: TextureSource get() = TextureSource(0L)
            /**
             * The texture source is the screen texture which captures all opaque objects drawn this frame.
             *
             * Generated from Godot docs: VisualShaderNodeTextureParameter.SOURCE_SCREEN
             */
            val SCREEN: TextureSource get() = TextureSource(1L)
            /**
             * The texture source is the depth texture from the depth prepass.
             *
             * Generated from Godot docs: VisualShaderNodeTextureParameter.SOURCE_DEPTH
             */
            val DEPTH: TextureSource get() = TextureSource(2L)
            /**
             * The texture source is the normal-roughness buffer from the depth prepass.
             *
             * Generated from Godot docs: VisualShaderNodeTextureParameter.SOURCE_NORMAL_ROUGHNESS
             */
            val NORMAL_ROUGHNESS: TextureSource get() = TextureSource(3L)
            /**
             * Represents the size of the `TextureSource` enum.
             *
             * Generated from Godot docs: VisualShaderNodeTextureParameter.SOURCE_MAX
             */
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
    }

    private object Binds {
        private const val SET_TEXTURE_TYPE_HASH = 2227296876L
        @JvmField
        val setTextureTypeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeTextureParameter", "set_texture_type", SET_TEXTURE_TYPE_HASH)

        private const val GET_TEXTURE_TYPE_HASH = 367922070L
        @JvmField
        val getTextureTypeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeTextureParameter", "get_texture_type", GET_TEXTURE_TYPE_HASH)

        private const val SET_COLOR_DEFAULT_HASH = 4217624432L
        @JvmField
        val setColorDefaultBind =
            ObjectCalls.getMethodBind("VisualShaderNodeTextureParameter", "set_color_default", SET_COLOR_DEFAULT_HASH)

        private const val GET_COLOR_DEFAULT_HASH = 3837060134L
        @JvmField
        val getColorDefaultBind =
            ObjectCalls.getMethodBind("VisualShaderNodeTextureParameter", "get_color_default", GET_COLOR_DEFAULT_HASH)

        private const val SET_TEXTURE_FILTER_HASH = 2147684752L
        @JvmField
        val setTextureFilterBind =
            ObjectCalls.getMethodBind("VisualShaderNodeTextureParameter", "set_texture_filter", SET_TEXTURE_FILTER_HASH)

        private const val GET_TEXTURE_FILTER_HASH = 4184490817L
        @JvmField
        val getTextureFilterBind =
            ObjectCalls.getMethodBind("VisualShaderNodeTextureParameter", "get_texture_filter", GET_TEXTURE_FILTER_HASH)

        private const val SET_TEXTURE_REPEAT_HASH = 2036143070L
        @JvmField
        val setTextureRepeatBind =
            ObjectCalls.getMethodBind("VisualShaderNodeTextureParameter", "set_texture_repeat", SET_TEXTURE_REPEAT_HASH)

        private const val GET_TEXTURE_REPEAT_HASH = 1690132794L
        @JvmField
        val getTextureRepeatBind =
            ObjectCalls.getMethodBind("VisualShaderNodeTextureParameter", "get_texture_repeat", GET_TEXTURE_REPEAT_HASH)

        private const val SET_TEXTURE_SOURCE_HASH = 1212687372L
        @JvmField
        val setTextureSourceBind =
            ObjectCalls.getMethodBind("VisualShaderNodeTextureParameter", "set_texture_source", SET_TEXTURE_SOURCE_HASH)

        private const val GET_TEXTURE_SOURCE_HASH = 2039092262L
        @JvmField
        val getTextureSourceBind =
            ObjectCalls.getMethodBind("VisualShaderNodeTextureParameter", "get_texture_source", GET_TEXTURE_SOURCE_HASH)
    }
}
