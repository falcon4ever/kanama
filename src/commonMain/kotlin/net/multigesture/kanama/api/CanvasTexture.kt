package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color

/**
 * Texture with optional normal and specular maps for use in 2D rendering.
 *
 * Generated from Godot docs: CanvasTexture
 */
class CanvasTexture(handle: GodotHandle) : Texture2D(handle) {
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

    var textureFilter: CanvasItem.TextureFilter
        @JvmName("textureFilterProperty")
        get() = getTextureFilter()
        @JvmName("setTextureFilterProperty")
        set(value) = setTextureFilter(value)

    var textureRepeat: CanvasItem.TextureRepeat
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
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setDiffuseTextureBind, segment, listOf(texture?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The diffuse (color) texture to use. This is the main texture you want to set in most cases.
     *
     * Generated from Godot docs: CanvasTexture.get_diffuse_texture
     */
    fun getDiffuseTexture(): Texture2D? {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetObject(Binds.getDiffuseTextureBind, segment)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return Texture2D.wrapOwned(ret)
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
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setNormalTextureBind, segment, listOf(texture?.requireOpenHandle() ?: NULL_SEGMENT))
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
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetObject(Binds.getNormalTextureBind, segment)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return Texture2D.wrapOwned(ret)
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
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setSpecularTextureBind, segment, listOf(texture?.requireOpenHandle() ?: NULL_SEGMENT))
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
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetObject(Binds.getSpecularTextureBind, segment)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return Texture2D.wrapOwned(ret)
    }

    /**
     * The multiplier for specular reflection colors. The `Light2D`'s color is also taken into account
     * when determining the reflection color. Only has a visible effect if `Light2D`s are affecting
     * this `CanvasTexture`.
     *
     * Generated from Godot docs: CanvasTexture.set_specular_color
     */
    fun setSpecularColor(color: Color) {
        checkOpen()
        ObjectCalls.ptrcallWithColorArg(Binds.setSpecularColorBind, segment, color)
    }

    /**
     * The multiplier for specular reflection colors. The `Light2D`'s color is also taken into account
     * when determining the reflection color. Only has a visible effect if `Light2D`s are affecting
     * this `CanvasTexture`.
     *
     * Generated from Godot docs: CanvasTexture.get_specular_color
     */
    fun getSpecularColor(): Color {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetColor(Binds.getSpecularColorBind, segment)
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
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setSpecularShininessBind, segment, shininess)
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
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getSpecularShininessBind, segment)
    }

    /**
     * The texture filtering mode to use when drawing this `CanvasTexture`.
     *
     * Generated from Godot docs: CanvasTexture.set_texture_filter
     */
    fun setTextureFilter(filter: CanvasItem.TextureFilter) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setTextureFilterBind, segment, filter.value)
    }

    /**
     * The texture filtering mode to use when drawing this `CanvasTexture`.
     *
     * Generated from Godot docs: CanvasTexture.get_texture_filter
     */
    fun getTextureFilter(): CanvasItem.TextureFilter {
        checkOpen()
        return CanvasItem.TextureFilter(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTextureFilterBind, segment))
    }

    /**
     * The texture repeat mode to use when drawing this `CanvasTexture`.
     *
     * Generated from Godot docs: CanvasTexture.set_texture_repeat
     */
    fun setTextureRepeat(repeat: CanvasItem.TextureRepeat) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setTextureRepeatBind, segment, repeat.value)
    }

    /**
     * The texture repeat mode to use when drawing this `CanvasTexture`.
     *
     * Generated from Godot docs: CanvasTexture.get_texture_repeat
     */
    fun getTextureRepeat(): CanvasItem.TextureRepeat {
        checkOpen()
        return CanvasItem.TextureRepeat(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTextureRepeatBind, segment))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CanvasTexture? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): CanvasTexture? =
            if (handle.address() == 0L) null else RefCounted.owned(CanvasTexture(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): CanvasTexture? =
            if (handle.address() == 0L) null else CanvasTexture(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_DIFFUSE_TEXTURE_HASH = 4051416890L
        @JvmField
        val setDiffuseTextureBind =
            ObjectCalls.getMethodBind("CanvasTexture", "set_diffuse_texture", SET_DIFFUSE_TEXTURE_HASH)

        private const val GET_DIFFUSE_TEXTURE_HASH = 3635182373L
        @JvmField
        val getDiffuseTextureBind =
            ObjectCalls.getMethodBind("CanvasTexture", "get_diffuse_texture", GET_DIFFUSE_TEXTURE_HASH)

        private const val SET_NORMAL_TEXTURE_HASH = 4051416890L
        @JvmField
        val setNormalTextureBind =
            ObjectCalls.getMethodBind("CanvasTexture", "set_normal_texture", SET_NORMAL_TEXTURE_HASH)

        private const val GET_NORMAL_TEXTURE_HASH = 3635182373L
        @JvmField
        val getNormalTextureBind =
            ObjectCalls.getMethodBind("CanvasTexture", "get_normal_texture", GET_NORMAL_TEXTURE_HASH)

        private const val SET_SPECULAR_TEXTURE_HASH = 4051416890L
        @JvmField
        val setSpecularTextureBind =
            ObjectCalls.getMethodBind("CanvasTexture", "set_specular_texture", SET_SPECULAR_TEXTURE_HASH)

        private const val GET_SPECULAR_TEXTURE_HASH = 3635182373L
        @JvmField
        val getSpecularTextureBind =
            ObjectCalls.getMethodBind("CanvasTexture", "get_specular_texture", GET_SPECULAR_TEXTURE_HASH)

        private const val SET_SPECULAR_COLOR_HASH = 2920490490L
        @JvmField
        val setSpecularColorBind =
            ObjectCalls.getMethodBind("CanvasTexture", "set_specular_color", SET_SPECULAR_COLOR_HASH)

        private const val GET_SPECULAR_COLOR_HASH = 3444240500L
        @JvmField
        val getSpecularColorBind =
            ObjectCalls.getMethodBind("CanvasTexture", "get_specular_color", GET_SPECULAR_COLOR_HASH)

        private const val SET_SPECULAR_SHININESS_HASH = 373806689L
        @JvmField
        val setSpecularShininessBind =
            ObjectCalls.getMethodBind("CanvasTexture", "set_specular_shininess", SET_SPECULAR_SHININESS_HASH)

        private const val GET_SPECULAR_SHININESS_HASH = 1740695150L
        @JvmField
        val getSpecularShininessBind =
            ObjectCalls.getMethodBind("CanvasTexture", "get_specular_shininess", GET_SPECULAR_SHININESS_HASH)

        private const val SET_TEXTURE_FILTER_HASH = 1037999706L
        @JvmField
        val setTextureFilterBind =
            ObjectCalls.getMethodBind("CanvasTexture", "set_texture_filter", SET_TEXTURE_FILTER_HASH)

        private const val GET_TEXTURE_FILTER_HASH = 121960042L
        @JvmField
        val getTextureFilterBind =
            ObjectCalls.getMethodBind("CanvasTexture", "get_texture_filter", GET_TEXTURE_FILTER_HASH)

        private const val SET_TEXTURE_REPEAT_HASH = 1716472974L
        @JvmField
        val setTextureRepeatBind =
            ObjectCalls.getMethodBind("CanvasTexture", "set_texture_repeat", SET_TEXTURE_REPEAT_HASH)

        private const val GET_TEXTURE_REPEAT_HASH = 2667158319L
        @JvmField
        val getTextureRepeatBind =
            ObjectCalls.getMethodBind("CanvasTexture", "get_texture_repeat", GET_TEXTURE_REPEAT_HASH)
    }
}
