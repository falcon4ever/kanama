package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeCurveXYZTexture
 */
class VisualShaderNodeCurveXYZTexture(handle: GodotHandle) : VisualShaderNodeResizableBase(handle) {
    var texture: CurveXYZTexture?
        @JvmName("textureProperty")
        get() = getTexture()
        @JvmName("setTextureProperty")
        set(value) = setTexture(value)

    fun setTexture(texture: CurveXYZTexture?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setTextureBind, segment, listOf(texture?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun getTexture(): CurveXYZTexture? {
        checkOpen()
        return CurveXYZTexture.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getTextureBind, segment))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeCurveXYZTexture? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeCurveXYZTexture? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeCurveXYZTexture(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeCurveXYZTexture? =
            if (handle.address() == 0L) null else VisualShaderNodeCurveXYZTexture(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_TEXTURE_HASH = 8031783L
        @JvmField
        val setTextureBind =
            ObjectCalls.getMethodBind("VisualShaderNodeCurveXYZTexture", "set_texture", SET_TEXTURE_HASH)

        private const val GET_TEXTURE_HASH = 1950275015L
        @JvmField
        val getTextureBind =
            ObjectCalls.getMethodBind("VisualShaderNodeCurveXYZTexture", "get_texture", GET_TEXTURE_HASH)
    }
}
