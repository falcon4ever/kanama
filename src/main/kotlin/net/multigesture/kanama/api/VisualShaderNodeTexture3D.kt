package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeTexture3D
 */
class VisualShaderNodeTexture3D(handle: MemorySegment) : VisualShaderNodeSample3D(handle) {
    var texture: Texture3D?
        @JvmName("textureProperty")
        get() = getTexture()
        @JvmName("setTextureProperty")
        set(value) = setTexture(value)

    fun setTexture(value: Texture3D?) {
        ObjectCalls.ptrcallWithObjectArgs(setTextureBind, handle, listOf(value?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getTexture(): Texture3D? {
        return Texture3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTextureBind, handle))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeTexture3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeTexture3D? =
            if (handle.address() == 0L) null else VisualShaderNodeTexture3D(handle)

        private const val SET_TEXTURE_HASH = 1188404210L
        private val setTextureBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeTexture3D", "set_texture", SET_TEXTURE_HASH)
        }

        private const val GET_TEXTURE_HASH = 373985333L
        private val getTextureBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeTexture3D", "get_texture", GET_TEXTURE_HASH)
        }
    }
}
