package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeCurveTexture
 */
class VisualShaderNodeCurveTexture(handle: GodotHandle) : VisualShaderNodeResizableBase(handle) {
    var texture: CurveTexture?
        @JvmName("textureProperty")
        get() = getTexture()
        @JvmName("setTextureProperty")
        set(value) = setTexture(value)

    fun setTexture(texture: CurveTexture?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setTextureBind, segment, listOf(texture?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun getTexture(): CurveTexture? {
        checkOpen()
        return CurveTexture.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getTextureBind, segment))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeCurveTexture? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeCurveTexture? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeCurveTexture(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeCurveTexture? =
            if (handle.address() == 0L) null else VisualShaderNodeCurveTexture(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_TEXTURE_HASH = 181872837L
        @JvmField
        val setTextureBind =
            ObjectCalls.getMethodBind("VisualShaderNodeCurveTexture", "set_texture", SET_TEXTURE_HASH)

        private const val GET_TEXTURE_HASH = 2800800579L
        @JvmField
        val getTextureBind =
            ObjectCalls.getMethodBind("VisualShaderNodeCurveTexture", "get_texture", GET_TEXTURE_HASH)
    }
}
