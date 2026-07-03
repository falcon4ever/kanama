package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color

/**
 * Texture with optional normal and specular maps for use in 2D rendering.
 *
 * Generated from Godot docs: CanvasTexture
 */
class CanvasTexture(handle: MemorySegment) : Texture2D(handle) {
    var diffuseTexture: Texture2D?
        @JvmName("diffuseTextureProperty")
        get() = getDiffuseTexture()
        @JvmName("setDiffuseTextureProperty")
        set(value) = setDiffuseTexture(value)

    var normalTexture: Texture2D?
        @JvmName("normalTextureProperty")
        get() = getNormalTexture()
        @JvmName("setNormalTextureProperty")
        set(value) = setNormalTexture(value)

    var specularTexture: Texture2D?
        @JvmName("specularTextureProperty")
        get() = getSpecularTexture()
        @JvmName("setSpecularTextureProperty")
        set(value) = setSpecularTexture(value)

    var specularColor: Color
        @JvmName("specularColorProperty")
        get() = getSpecularColor()
        @JvmName("setSpecularColorProperty")
        set(value) = setSpecularColor(value)

    var specularShininess: Double
        @JvmName("specularShininessProperty")
        get() = getSpecularShininess()
        @JvmName("setSpecularShininessProperty")
        set(value) = setSpecularShininess(value)

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

    /**
     * The diffuse (color) texture to use. This is the main texture you want to set in most cases.
     *
     * Generated from Godot docs: CanvasTexture.set_diffuse_texture
     */
    fun setDiffuseTexture(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setDiffuseTextureBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The diffuse (color) texture to use. This is the main texture you want to set in most cases.
     *
     * Generated from Godot docs: CanvasTexture.get_diffuse_texture
     */
    fun getDiffuseTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getDiffuseTextureBind, handle))
    }

    /**
     * The normal map texture to use. Only has a visible effect if `Light2D`s are affecting this
     * `CanvasTexture`. Note: Godot expects the normal map to use X+, Y+, and Z+ coordinates. See this
     * page (http://wiki.polycount.com/wiki/Normal_Map_Technical_Details#Common_Swizzle_Coordinates)
     * for a comparison of normal map coordinates expected by popular engines.
     *
     * Generated from Godot docs: CanvasTexture.set_normal_texture
     */
    fun setNormalTexture(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setNormalTextureBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The normal map texture to use. Only has a visible effect if `Light2D`s are affecting this
     * `CanvasTexture`. Note: Godot expects the normal map to use X+, Y+, and Z+ coordinates. See this
     * page (http://wiki.polycount.com/wiki/Normal_Map_Technical_Details#Common_Swizzle_Coordinates)
     * for a comparison of normal map coordinates expected by popular engines.
     *
     * Generated from Godot docs: CanvasTexture.get_normal_texture
     */
    fun getNormalTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getNormalTextureBind, handle))
    }

    /**
     * The specular map to use for `Light2D` specular reflections. This should be a grayscale or
     * colored texture, with brighter areas resulting in a higher `specular_shininess` value. Using a
     * colored `specular_texture` allows controlling specular shininess on a per-channel basis. Only
     * has a visible effect if `Light2D`s are affecting this `CanvasTexture`.
     *
     * Generated from Godot docs: CanvasTexture.set_specular_texture
     */
    fun setSpecularTexture(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setSpecularTextureBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The specular map to use for `Light2D` specular reflections. This should be a grayscale or
     * colored texture, with brighter areas resulting in a higher `specular_shininess` value. Using a
     * colored `specular_texture` allows controlling specular shininess on a per-channel basis. Only
     * has a visible effect if `Light2D`s are affecting this `CanvasTexture`.
     *
     * Generated from Godot docs: CanvasTexture.get_specular_texture
     */
    fun getSpecularTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getSpecularTextureBind, handle))
    }

    /**
     * The multiplier for specular reflection colors. The `Light2D`'s color is also taken into account
     * when determining the reflection color. Only has a visible effect if `Light2D`s are affecting
     * this `CanvasTexture`.
     *
     * Generated from Godot docs: CanvasTexture.set_specular_color
     */
    fun setSpecularColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setSpecularColorBind, handle, color)
    }

    /**
     * The multiplier for specular reflection colors. The `Light2D`'s color is also taken into account
     * when determining the reflection color. Only has a visible effect if `Light2D`s are affecting
     * this `CanvasTexture`.
     *
     * Generated from Godot docs: CanvasTexture.get_specular_color
     */
    fun getSpecularColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getSpecularColorBind, handle)
    }

    /**
     * The specular exponent for `Light2D` specular reflections. Higher values result in a more
     * glossy/"wet" look, with reflections becoming more localized and less visible overall. The
     * default value of `1.0` disables specular reflections entirely. Only has a visible effect if
     * `Light2D`s are affecting this `CanvasTexture`.
     *
     * Generated from Godot docs: CanvasTexture.set_specular_shininess
     */
    fun setSpecularShininess(shininess: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSpecularShininessBind, handle, shininess)
    }

    /**
     * The specular exponent for `Light2D` specular reflections. Higher values result in a more
     * glossy/"wet" look, with reflections becoming more localized and less visible overall. The
     * default value of `1.0` disables specular reflections entirely. Only has a visible effect if
     * `Light2D`s are affecting this `CanvasTexture`.
     *
     * Generated from Godot docs: CanvasTexture.get_specular_shininess
     */
    fun getSpecularShininess(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSpecularShininessBind, handle)
    }

    /**
     * The texture filtering mode to use when drawing this `CanvasTexture`.
     *
     * Generated from Godot docs: CanvasTexture.set_texture_filter
     */
    fun setTextureFilter(filter: Long) {
        ObjectCalls.ptrcallWithLongArg(setTextureFilterBind, handle, filter)
    }

    /**
     * The texture filtering mode to use when drawing this `CanvasTexture`.
     *
     * Generated from Godot docs: CanvasTexture.get_texture_filter
     */
    fun getTextureFilter(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTextureFilterBind, handle)
    }

    /**
     * The texture repeat mode to use when drawing this `CanvasTexture`.
     *
     * Generated from Godot docs: CanvasTexture.set_texture_repeat
     */
    fun setTextureRepeat(repeat: Long) {
        ObjectCalls.ptrcallWithLongArg(setTextureRepeatBind, handle, repeat)
    }

    /**
     * The texture repeat mode to use when drawing this `CanvasTexture`.
     *
     * Generated from Godot docs: CanvasTexture.get_texture_repeat
     */
    fun getTextureRepeat(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTextureRepeatBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): CanvasTexture? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CanvasTexture? =
            if (handle.address() == 0L) null else CanvasTexture(handle)

        private const val SET_DIFFUSE_TEXTURE_HASH = 4051416890L
        private val setDiffuseTextureBind by lazy {
            ObjectCalls.getMethodBind("CanvasTexture", "set_diffuse_texture", SET_DIFFUSE_TEXTURE_HASH)
        }

        private const val GET_DIFFUSE_TEXTURE_HASH = 3635182373L
        private val getDiffuseTextureBind by lazy {
            ObjectCalls.getMethodBind("CanvasTexture", "get_diffuse_texture", GET_DIFFUSE_TEXTURE_HASH)
        }

        private const val SET_NORMAL_TEXTURE_HASH = 4051416890L
        private val setNormalTextureBind by lazy {
            ObjectCalls.getMethodBind("CanvasTexture", "set_normal_texture", SET_NORMAL_TEXTURE_HASH)
        }

        private const val GET_NORMAL_TEXTURE_HASH = 3635182373L
        private val getNormalTextureBind by lazy {
            ObjectCalls.getMethodBind("CanvasTexture", "get_normal_texture", GET_NORMAL_TEXTURE_HASH)
        }

        private const val SET_SPECULAR_TEXTURE_HASH = 4051416890L
        private val setSpecularTextureBind by lazy {
            ObjectCalls.getMethodBind("CanvasTexture", "set_specular_texture", SET_SPECULAR_TEXTURE_HASH)
        }

        private const val GET_SPECULAR_TEXTURE_HASH = 3635182373L
        private val getSpecularTextureBind by lazy {
            ObjectCalls.getMethodBind("CanvasTexture", "get_specular_texture", GET_SPECULAR_TEXTURE_HASH)
        }

        private const val SET_SPECULAR_COLOR_HASH = 2920490490L
        private val setSpecularColorBind by lazy {
            ObjectCalls.getMethodBind("CanvasTexture", "set_specular_color", SET_SPECULAR_COLOR_HASH)
        }

        private const val GET_SPECULAR_COLOR_HASH = 3444240500L
        private val getSpecularColorBind by lazy {
            ObjectCalls.getMethodBind("CanvasTexture", "get_specular_color", GET_SPECULAR_COLOR_HASH)
        }

        private const val SET_SPECULAR_SHININESS_HASH = 373806689L
        private val setSpecularShininessBind by lazy {
            ObjectCalls.getMethodBind("CanvasTexture", "set_specular_shininess", SET_SPECULAR_SHININESS_HASH)
        }

        private const val GET_SPECULAR_SHININESS_HASH = 1740695150L
        private val getSpecularShininessBind by lazy {
            ObjectCalls.getMethodBind("CanvasTexture", "get_specular_shininess", GET_SPECULAR_SHININESS_HASH)
        }

        private const val SET_TEXTURE_FILTER_HASH = 1037999706L
        private val setTextureFilterBind by lazy {
            ObjectCalls.getMethodBind("CanvasTexture", "set_texture_filter", SET_TEXTURE_FILTER_HASH)
        }

        private const val GET_TEXTURE_FILTER_HASH = 121960042L
        private val getTextureFilterBind by lazy {
            ObjectCalls.getMethodBind("CanvasTexture", "get_texture_filter", GET_TEXTURE_FILTER_HASH)
        }

        private const val SET_TEXTURE_REPEAT_HASH = 1716472974L
        private val setTextureRepeatBind by lazy {
            ObjectCalls.getMethodBind("CanvasTexture", "set_texture_repeat", SET_TEXTURE_REPEAT_HASH)
        }

        private const val GET_TEXTURE_REPEAT_HASH = 2667158319L
        private val getTextureRepeatBind by lazy {
            ObjectCalls.getMethodBind("CanvasTexture", "get_texture_repeat", GET_TEXTURE_REPEAT_HASH)
        }
    }
}
