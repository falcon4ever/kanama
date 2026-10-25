package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A `Cubemap` sampling node to be used within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeCubemap
 */
class VisualShaderNodeCubemap(handle: GodotHandle) : VisualShaderNode(handle) {
    var source: VisualShaderNodeCubemap.Source
        @JvmName("sourceProperty")
        get() = getSource()
        @JvmName("setSourceProperty")
        set(value) = setSource(value)

    var cubeMap: TextureLayered?
        @JvmName("cubeMapProperty")
        get() = getCubeMap()
        @JvmName("setCubeMapProperty")
        set(value) = setCubeMap(value)

    var textureType: VisualShaderNodeCubemap.TextureType
        @JvmName("textureTypeProperty")
        get() = getTextureType()
        @JvmName("setTextureTypeProperty")
        set(value) = setTextureType(value)

    /**
     * Defines which source should be used for the sampling.
     *
     * Generated from Godot docs: VisualShaderNodeCubemap.set_source
     */
    fun setSource(value: VisualShaderNodeCubemap.Source) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setSourceBind, segment, value.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Defines which source should be used for the sampling.
     *
     * Generated from Godot docs: VisualShaderNodeCubemap.get_source
     */
    fun getSource(): VisualShaderNodeCubemap.Source {
        checkOpen()
        val ret = VisualShaderNodeCubemap.Source(ObjectCalls.ptrcallNoArgsRetLong(Binds.getSourceBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The `Cubemap` texture to sample when using `Source.TEXTURE` as `source`.
     *
     * Generated from Godot docs: VisualShaderNodeCubemap.set_cube_map
     */
    fun setCubeMap(value: TextureLayered?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setCubeMapBind, segment, listOf(value?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(value)
    }

    /**
     * The `Cubemap` texture to sample when using `Source.TEXTURE` as `source`.
     *
     * Generated from Godot docs: VisualShaderNodeCubemap.get_cube_map
     */
    fun getCubeMap(): TextureLayered? {
        checkOpen()
        val ret = TextureLayered.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getCubeMapBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Defines the type of data provided by the source texture.
     *
     * Generated from Godot docs: VisualShaderNodeCubemap.set_texture_type
     */
    fun setTextureType(value: VisualShaderNodeCubemap.TextureType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setTextureTypeBind, segment, value.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Defines the type of data provided by the source texture.
     *
     * Generated from Godot docs: VisualShaderNodeCubemap.get_texture_type
     */
    fun getTextureType(): VisualShaderNodeCubemap.TextureType {
        checkOpen()
        val ret = VisualShaderNodeCubemap.TextureType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTextureTypeBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Godot's `VisualShaderNodeCubemap.Source` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`VisualShaderNodeCubemap.Source.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeCubemap.Source
     */
    @JvmInline
    value class Source(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Use the `Cubemap` set via `cube_map`. If this is set to `source`, the `samplerCube` port is
             * ignored.
             *
             * Generated from Godot docs: VisualShaderNodeCubemap.SOURCE_TEXTURE
             */
            val TEXTURE: Source get() = Source(0L)
            /**
             * Use the `Cubemap` sampler reference passed via the `samplerCube` port. If this is set to
             * `source`, the `cube_map` texture is ignored.
             *
             * Generated from Godot docs: VisualShaderNodeCubemap.SOURCE_PORT
             */
            val PORT: Source get() = Source(1L)
            /**
             * Represents the size of the `Source` enum.
             *
             * Generated from Godot docs: VisualShaderNodeCubemap.SOURCE_MAX
             */
            val MAX: Source get() = Source(2L)
        }
    }

    /**
     * Godot's `VisualShaderNodeCubemap.TextureType` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`VisualShaderNodeCubemap.TextureType.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeCubemap.TextureType
     */
    @JvmInline
    value class TextureType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * No hints are added to the uniform declaration.
             *
             * Generated from Godot docs: VisualShaderNodeCubemap.TYPE_DATA
             */
            val DATA: TextureType get() = TextureType(0L)
            /**
             * Adds `source_color` as hint to the uniform declaration for proper conversion from nonlinear sRGB
             * encoding to linear encoding.
             *
             * Generated from Godot docs: VisualShaderNodeCubemap.TYPE_COLOR
             */
            val COLOR: TextureType get() = TextureType(1L)
            /**
             * Adds `hint_normal` as hint to the uniform declaration, which internally converts the texture for
             * proper usage as normal map.
             *
             * Generated from Godot docs: VisualShaderNodeCubemap.TYPE_NORMAL_MAP
             */
            val NORMAL_MAP: TextureType get() = TextureType(2L)
            /**
             * Represents the size of the `TextureType` enum.
             *
             * Generated from Godot docs: VisualShaderNodeCubemap.TYPE_MAX
             */
            val MAX: TextureType get() = TextureType(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeCubemap? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeCubemap? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeCubemap(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeCubemap? =
            if (handle.address() == 0L) null else VisualShaderNodeCubemap(GodotHandle(handle))

        /** A new VisualShaderNodeCubemap, GDScript's `VisualShaderNodeCubemap.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeCubemap =
            RefCounted.owned(VisualShaderNodeCubemap(GodotHandle(ObjectCalls.constructObject("VisualShaderNodeCubemap"))))
    }

    private object Binds {
        private const val SET_SOURCE_HASH = 1625400621L
        @JvmField
        val setSourceBind =
            ObjectCalls.getMethodBind("VisualShaderNodeCubemap", "set_source", SET_SOURCE_HASH)

        private const val GET_SOURCE_HASH = 2222048781L
        @JvmField
        val getSourceBind =
            ObjectCalls.getMethodBind("VisualShaderNodeCubemap", "get_source", GET_SOURCE_HASH)

        private const val SET_CUBE_MAP_HASH = 1278366092L
        @JvmField
        val setCubeMapBind =
            ObjectCalls.getMethodBind("VisualShaderNodeCubemap", "set_cube_map", SET_CUBE_MAP_HASH)

        private const val GET_CUBE_MAP_HASH = 3984243839L
        @JvmField
        val getCubeMapBind =
            ObjectCalls.getMethodBind("VisualShaderNodeCubemap", "get_cube_map", GET_CUBE_MAP_HASH)

        private const val SET_TEXTURE_TYPE_HASH = 1899718876L
        @JvmField
        val setTextureTypeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeCubemap", "set_texture_type", SET_TEXTURE_TYPE_HASH)

        private const val GET_TEXTURE_TYPE_HASH = 3356498888L
        @JvmField
        val getTextureTypeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeCubemap", "get_texture_type", GET_TEXTURE_TYPE_HASH)
    }
}
