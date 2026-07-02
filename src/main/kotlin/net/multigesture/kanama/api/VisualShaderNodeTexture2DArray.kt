package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeTexture2DArray
 */
class VisualShaderNodeTexture2DArray(handle: MemorySegment) : VisualShaderNodeSample3D(handle) {
    var textureArray: TextureLayered?
        @JvmName("textureArrayProperty")
        get() = getTextureArray()
        @JvmName("setTextureArrayProperty")
        set(value) = setTextureArray(value)

    fun setTextureArray(value: TextureLayered?) {
        ObjectCalls.ptrcallWithObjectArgs(setTextureArrayBind, handle, listOf(value?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getTextureArray(): TextureLayered? {
        return TextureLayered.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTextureArrayBind, handle))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeTexture2DArray? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeTexture2DArray? =
            if (handle.address() == 0L) null else VisualShaderNodeTexture2DArray(handle)

        private const val SET_TEXTURE_ARRAY_HASH = 1278366092L
        private val setTextureArrayBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeTexture2DArray", "set_texture_array", SET_TEXTURE_ARRAY_HASH)
        }

        private const val GET_TEXTURE_ARRAY_HASH = 3984243839L
        private val getTextureArrayBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeTexture2DArray", "get_texture_array", GET_TEXTURE_ARRAY_HASH)
        }
    }
}
