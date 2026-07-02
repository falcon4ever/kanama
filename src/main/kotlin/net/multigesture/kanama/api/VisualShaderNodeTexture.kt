package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeTexture
 */
class VisualShaderNodeTexture(handle: MemorySegment) : VisualShaderNode(handle) {
    var source: Long
        @JvmName("sourceProperty")
        get() = getSource()
        @JvmName("setSourceProperty")
        set(value) = setSource(value)

    var texture: Texture2D?
        @JvmName("textureProperty")
        get() = getTexture()
        @JvmName("setTextureProperty")
        set(value) = setTexture(value)

    var textureType: Long
        @JvmName("textureTypeProperty")
        get() = getTextureType()
        @JvmName("setTextureTypeProperty")
        set(value) = setTextureType(value)

    fun setSource(value: Long) {
        ObjectCalls.ptrcallWithLongArg(setSourceBind, handle, value)
    }

    fun getSource(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSourceBind, handle)
    }

    fun setTexture(value: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setTextureBind, handle, listOf(value?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTextureBind, handle))
    }

    fun setTextureType(value: Long) {
        ObjectCalls.ptrcallWithLongArg(setTextureTypeBind, handle, value)
    }

    fun getTextureType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTextureTypeBind, handle)
    }

    companion object {
        const val SOURCE_TEXTURE: Long = 0L
        const val SOURCE_SCREEN: Long = 1L
        const val SOURCE_2D_TEXTURE: Long = 2L
        const val SOURCE_2D_NORMAL: Long = 3L
        const val SOURCE_DEPTH: Long = 4L
        const val SOURCE_PORT: Long = 5L
        const val SOURCE_3D_NORMAL: Long = 6L
        const val SOURCE_ROUGHNESS: Long = 7L
        const val SOURCE_MAX: Long = 8L
        const val TYPE_DATA: Long = 0L
        const val TYPE_COLOR: Long = 1L
        const val TYPE_NORMAL_MAP: Long = 2L
        const val TYPE_MAX: Long = 3L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeTexture? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeTexture? =
            if (handle.address() == 0L) null else VisualShaderNodeTexture(handle)

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
