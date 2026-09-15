package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeTexture2DArray
 */
class VisualShaderNodeTexture2DArray(handle: GodotHandle) : VisualShaderNodeSample3D(handle) {
    var textureArray: TextureLayered?
        @JvmName("textureArrayProperty")
        get() = getTextureArray()
        @JvmName("setTextureArrayProperty")
        set(value) = setTextureArray(value)

    fun setTextureArray(value: TextureLayered?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setTextureArrayBind, segment, listOf(value?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun getTextureArray(): TextureLayered? {
        checkOpen()
        return TextureLayered.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTextureArrayBind, segment))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeTexture2DArray? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeTexture2DArray? =
            if (handle.address() == 0L) null else VisualShaderNodeTexture2DArray(GodotHandle(handle))

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
