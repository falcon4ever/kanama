package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
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

    fun setSource(value: VisualShaderNodeTexture.Source) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setSourceBind, segment, value.value)
    }

    fun getSource(): VisualShaderNodeTexture.Source {
        checkOpen()
        return VisualShaderNodeTexture.Source(ObjectCalls.ptrcallNoArgsRetLong(getSourceBind, segment))
    }

    fun setTexture(value: Texture2D?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setTextureBind, segment, listOf(value?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun getTexture(): Texture2D? {
        checkOpen()
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTextureBind, segment))
    }

    fun setTextureType(value: VisualShaderNodeTexture.TextureType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setTextureTypeBind, segment, value.value)
    }

    fun getTextureType(): VisualShaderNodeTexture.TextureType {
        checkOpen()
        return VisualShaderNodeTexture.TextureType(ObjectCalls.ptrcallNoArgsRetLong(getTextureTypeBind, segment))
    }

    @JvmInline
    value class Source(val value: Long) {
        companion object {
            val TEXTURE: Source get() = Source(0L)
            val SCREEN: Source get() = Source(1L)
            val SOURCE_2D_TEXTURE: Source get() = Source(2L)
            val SOURCE_2D_NORMAL: Source get() = Source(3L)
            val DEPTH: Source get() = Source(4L)
            val PORT: Source get() = Source(5L)
            val SOURCE_3D_NORMAL: Source get() = Source(6L)
            val ROUGHNESS: Source get() = Source(7L)
            val MAX: Source get() = Source(8L)
        }
    }

    @JvmInline
    value class TextureType(val value: Long) {
        companion object {
            val DATA: TextureType get() = TextureType(0L)
            val COLOR: TextureType get() = TextureType(1L)
            val NORMAL_MAP: TextureType get() = TextureType(2L)
            val MAX: TextureType get() = TextureType(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeTexture? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeTexture? =
            if (handle.address() == 0L) null else VisualShaderNodeTexture(GodotHandle(handle))

        private const val SET_SOURCE_HASH = 905262939L
        private val setSourceBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeTexture", "set_source", SET_SOURCE_HASH)
        }

        private const val GET_SOURCE_HASH = 2896297444L
        private val getSourceBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeTexture", "get_source", GET_SOURCE_HASH)
        }

        private const val SET_TEXTURE_HASH = 4051416890L
        private val setTextureBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeTexture", "set_texture", SET_TEXTURE_HASH)
        }

        private const val GET_TEXTURE_HASH = 3635182373L
        private val getTextureBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeTexture", "get_texture", GET_TEXTURE_HASH)
        }

        private const val SET_TEXTURE_TYPE_HASH = 986314081L
        private val setTextureTypeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeTexture", "set_texture_type", SET_TEXTURE_TYPE_HASH)
        }

        private const val GET_TEXTURE_TYPE_HASH = 3290430153L
        private val getTextureTypeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeTexture", "get_texture_type", GET_TEXTURE_TYPE_HASH)
        }
    }
}
