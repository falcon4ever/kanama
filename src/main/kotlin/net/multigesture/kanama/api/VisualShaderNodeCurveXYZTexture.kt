package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeCurveXYZTexture
 */
class VisualShaderNodeCurveXYZTexture(handle: MemorySegment) : VisualShaderNodeResizableBase(handle) {
    var texture: CurveXYZTexture?
        @JvmName("textureProperty")
        get() = getTexture()
        @JvmName("setTextureProperty")
        set(value) = setTexture(value)

    fun setTexture(texture: CurveXYZTexture?) {
        ObjectCalls.ptrcallWithObjectArgs(setTextureBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getTexture(): CurveXYZTexture? {
        return CurveXYZTexture.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTextureBind, handle))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeCurveXYZTexture? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeCurveXYZTexture? =
            if (handle.address() == 0L) null else VisualShaderNodeCurveXYZTexture(handle)

        private const val SET_TEXTURE_HASH = 8031783L
        private val setTextureBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeCurveXYZTexture", "set_texture", SET_TEXTURE_HASH)
        }

        private const val GET_TEXTURE_HASH = 1950275015L
        private val getTextureBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeCurveXYZTexture", "get_texture", GET_TEXTURE_HASH)
        }
    }
}
