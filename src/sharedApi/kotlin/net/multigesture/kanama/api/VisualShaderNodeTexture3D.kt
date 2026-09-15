package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeTexture3D
 */
class VisualShaderNodeTexture3D(handle: GodotHandle) : VisualShaderNodeSample3D(handle) {
    var texture: Texture3D?
        @JvmName("textureProperty")
        get() = getTexture()
        @JvmName("setTextureProperty")
        set(value) = setTexture(value)

    fun setTexture(value: Texture3D?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setTextureBind, segment, listOf(value?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun getTexture(): Texture3D? {
        checkOpen()
        return Texture3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTextureBind, segment))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeTexture3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeTexture3D? =
            if (handle.address() == 0L) null else VisualShaderNodeTexture3D(GodotHandle(handle))

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
