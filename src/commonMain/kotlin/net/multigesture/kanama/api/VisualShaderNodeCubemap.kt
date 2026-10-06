package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
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

    fun setSource(value: VisualShaderNodeCubemap.Source) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setSourceBind, segment, value.value)
    }

    fun getSource(): VisualShaderNodeCubemap.Source {
        checkOpen()
        return VisualShaderNodeCubemap.Source(ObjectCalls.ptrcallNoArgsRetLong(Binds.getSourceBind, segment))
    }

    fun setCubeMap(value: TextureLayered?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setCubeMapBind, segment, listOf(value?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun getCubeMap(): TextureLayered? {
        checkOpen()
        return TextureLayered.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getCubeMapBind, segment))
    }

    fun setTextureType(value: VisualShaderNodeCubemap.TextureType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setTextureTypeBind, segment, value.value)
    }

    fun getTextureType(): VisualShaderNodeCubemap.TextureType {
        checkOpen()
        return VisualShaderNodeCubemap.TextureType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTextureTypeBind, segment))
    }

    @JvmInline
    value class Source(override val value: Long) : GodotEnumValue {
        companion object {
            val TEXTURE: Source get() = Source(0L)
            val PORT: Source get() = Source(1L)
            val MAX: Source get() = Source(2L)
        }
    }

    @JvmInline
    value class TextureType(override val value: Long) : GodotEnumValue {
        companion object {
            val DATA: TextureType get() = TextureType(0L)
            val COLOR: TextureType get() = TextureType(1L)
            val NORMAL_MAP: TextureType get() = TextureType(2L)
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
