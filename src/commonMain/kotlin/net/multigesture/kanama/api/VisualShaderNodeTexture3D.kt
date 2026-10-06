package net.multigesture.kanama.api

import kotlin.jvm.JvmField
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
        ObjectCalls.ptrcallWithObjectArgs(Binds.setTextureBind, segment, listOf(value?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun getTexture(): Texture3D? {
        checkOpen()
        return Texture3D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getTextureBind, segment))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeTexture3D? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeTexture3D? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeTexture3D(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeTexture3D? =
            if (handle.address() == 0L) null else VisualShaderNodeTexture3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_TEXTURE_HASH = 1188404210L
        @JvmField
        val setTextureBind =
            ObjectCalls.getMethodBind("VisualShaderNodeTexture3D", "set_texture", SET_TEXTURE_HASH)

        private const val GET_TEXTURE_HASH = 373985333L
        @JvmField
        val getTextureBind =
            ObjectCalls.getMethodBind("VisualShaderNodeTexture3D", "get_texture", GET_TEXTURE_HASH)
    }
}
