package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Performs a 2D texture lookup within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeTexture
 */
class VisualShaderNodeTexture(handle: GodotHandle) : VisualShaderNode(handle) {
    var source: VisualShaderNodeTexture.Source
        @JvmName("sourceProperty")
        get() = getSource()
        @JvmName("setSourceProperty")
        set(value) = setSource(value)

    var texture: Texture2D?
        @JvmName("textureProperty")
        get() = getTexture()
        @JvmName("setTextureProperty")
        set(value) = setTexture(value)

    var textureType: VisualShaderNodeTexture.TextureType
        @JvmName("textureTypeProperty")
        get() = getTextureType()
        @JvmName("setTextureTypeProperty")
        set(value) = setTextureType(value)

    /**
     * Determines the source for the lookup.
     *
     * Generated from Godot docs: VisualShaderNodeTexture.set_source
     */
    fun setSource(value: VisualShaderNodeTexture.Source) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setSourceBind, segment, value.value)
    }

    /**
     * Determines the source for the lookup.
     *
     * Generated from Godot docs: VisualShaderNodeTexture.get_source
     */
    fun getSource(): VisualShaderNodeTexture.Source {
        checkOpen()
        return VisualShaderNodeTexture.Source(ObjectCalls.ptrcallNoArgsRetLong(Binds.getSourceBind, segment))
    }

    /**
     * The source texture, if needed for the selected `source`.
     *
     * Generated from Godot docs: VisualShaderNodeTexture.set_texture
     */
    fun setTexture(value: Texture2D?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setTextureBind, segment, listOf(value?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The source texture, if needed for the selected `source`.
     *
     * Generated from Godot docs: VisualShaderNodeTexture.get_texture
     */
    fun getTexture(): Texture2D? {
        checkOpen()
        return Texture2D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getTextureBind, segment))
    }

    /**
     * Specifies the type of the texture if `source` is set to `Source.TEXTURE`.
     *
     * Generated from Godot docs: VisualShaderNodeTexture.set_texture_type
     */
    fun setTextureType(value: VisualShaderNodeTexture.TextureType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setTextureTypeBind, segment, value.value)
    }

    /**
     * Specifies the type of the texture if `source` is set to `Source.TEXTURE`.
     *
     * Generated from Godot docs: VisualShaderNodeTexture.get_texture_type
     */
    fun getTextureType(): VisualShaderNodeTexture.TextureType {
        checkOpen()
        return VisualShaderNodeTexture.TextureType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTextureTypeBind, segment))
    }

    /**
     * Godot's `VisualShaderNodeTexture.Source` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`VisualShaderNodeTexture.Source.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeTexture.Source
     */
    @JvmInline
    value class Source(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Use the texture given as an argument for this function.
             *
             * Generated from Godot docs: VisualShaderNodeTexture.SOURCE_TEXTURE
             */
            val TEXTURE: Source get() = Source(0L)
            /**
             * Use the current viewport's texture as the source.
             *
             * Generated from Godot docs: VisualShaderNodeTexture.SOURCE_SCREEN
             */
            val SCREEN: Source get() = Source(1L)
            /**
             * Use the texture from this shader's texture built-in (e.g. a texture of a `Sprite2D`).
             *
             * Generated from Godot docs: VisualShaderNodeTexture.SOURCE_2D_TEXTURE
             */
            val SOURCE_2D_TEXTURE: Source get() = Source(2L)
            /**
             * Use the texture from this shader's normal map built-in.
             *
             * Generated from Godot docs: VisualShaderNodeTexture.SOURCE_2D_NORMAL
             */
            val SOURCE_2D_NORMAL: Source get() = Source(3L)
            /**
             * Use the depth texture captured during the depth prepass. Only available when the depth prepass
             * is used (i.e. in spatial shaders and in the forward_plus or gl_compatibility renderers).
             *
             * Generated from Godot docs: VisualShaderNodeTexture.SOURCE_DEPTH
             */
            val DEPTH: Source get() = Source(4L)
            /**
             * Use the texture provided in the input port for this function.
             *
             * Generated from Godot docs: VisualShaderNodeTexture.SOURCE_PORT
             */
            val PORT: Source get() = Source(5L)
            /**
             * Use the normal buffer captured during the depth prepass. Only available when the
             * normal-roughness buffer is available (i.e. in spatial shaders and in the forward_plus renderer).
             *
             * Generated from Godot docs: VisualShaderNodeTexture.SOURCE_3D_NORMAL
             */
            val SOURCE_3D_NORMAL: Source get() = Source(6L)
            /**
             * Use the roughness buffer captured during the depth prepass. Only available when the
             * normal-roughness buffer is available (i.e. in spatial shaders and in the forward_plus renderer).
             *
             * Generated from Godot docs: VisualShaderNodeTexture.SOURCE_ROUGHNESS
             */
            val ROUGHNESS: Source get() = Source(7L)
            /**
             * Represents the size of the `Source` enum.
             *
             * Generated from Godot docs: VisualShaderNodeTexture.SOURCE_MAX
             */
            val MAX: Source get() = Source(8L)
        }
    }

    /**
     * Godot's `VisualShaderNodeTexture.TextureType` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`VisualShaderNodeTexture.TextureType.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeTexture.TextureType
     */
    @JvmInline
    value class TextureType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * No hints are added to the uniform declaration.
             *
             * Generated from Godot docs: VisualShaderNodeTexture.TYPE_DATA
             */
            val DATA: TextureType get() = TextureType(0L)
            /**
             * Adds `source_color` as hint to the uniform declaration for proper conversion from nonlinear sRGB
             * encoding to linear encoding.
             *
             * Generated from Godot docs: VisualShaderNodeTexture.TYPE_COLOR
             */
            val COLOR: TextureType get() = TextureType(1L)
            /**
             * Adds `hint_normal` as hint to the uniform declaration, which internally converts the texture for
             * proper usage as normal map.
             *
             * Generated from Godot docs: VisualShaderNodeTexture.TYPE_NORMAL_MAP
             */
            val NORMAL_MAP: TextureType get() = TextureType(2L)
            /**
             * Represents the size of the `TextureType` enum.
             *
             * Generated from Godot docs: VisualShaderNodeTexture.TYPE_MAX
             */
            val MAX: TextureType get() = TextureType(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeTexture? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeTexture? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeTexture(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeTexture? =
            if (handle.address() == 0L) null else VisualShaderNodeTexture(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_SOURCE_HASH = 905262939L
        @JvmField
        val setSourceBind =
            ObjectCalls.getMethodBind("VisualShaderNodeTexture", "set_source", SET_SOURCE_HASH)

        private const val GET_SOURCE_HASH = 2896297444L
        @JvmField
        val getSourceBind =
            ObjectCalls.getMethodBind("VisualShaderNodeTexture", "get_source", GET_SOURCE_HASH)

        private const val SET_TEXTURE_HASH = 4051416890L
        @JvmField
        val setTextureBind =
            ObjectCalls.getMethodBind("VisualShaderNodeTexture", "set_texture", SET_TEXTURE_HASH)

        private const val GET_TEXTURE_HASH = 3635182373L
        @JvmField
        val getTextureBind =
            ObjectCalls.getMethodBind("VisualShaderNodeTexture", "get_texture", GET_TEXTURE_HASH)

        private const val SET_TEXTURE_TYPE_HASH = 986314081L
        @JvmField
        val setTextureTypeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeTexture", "set_texture_type", SET_TEXTURE_TYPE_HASH)

        private const val GET_TEXTURE_TYPE_HASH = 3290430153L
        @JvmField
        val getTextureTypeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeTexture", "get_texture_type", GET_TEXTURE_TYPE_HASH)
    }
}
