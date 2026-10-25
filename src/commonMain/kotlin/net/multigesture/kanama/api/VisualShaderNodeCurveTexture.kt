package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Performs a `CurveTexture` lookup within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeCurveTexture
 */
class VisualShaderNodeCurveTexture(handle: GodotHandle) : VisualShaderNodeResizableBase(handle) {
    var texture: CurveTexture?
        @JvmName("textureProperty")
        get() = getTexture()
        @JvmName("setTextureProperty")
        set(value) = setTexture(value)

    /**
     * The source texture.
     *
     * Generated from Godot docs: VisualShaderNodeCurveTexture.set_texture
     */
    fun setTexture(texture: CurveTexture?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setTextureBind, segment, listOf(texture?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(texture)
    }

    /**
     * The source texture.
     *
     * Generated from Godot docs: VisualShaderNodeCurveTexture.get_texture
     */
    fun getTexture(): CurveTexture? {
        checkOpen()
        val ret = CurveTexture.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getTextureBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeCurveTexture? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeCurveTexture? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeCurveTexture(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeCurveTexture? =
            if (handle.address() == 0L) null else VisualShaderNodeCurveTexture(GodotHandle(handle))

        /** A new VisualShaderNodeCurveTexture, GDScript's `VisualShaderNodeCurveTexture.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeCurveTexture =
            RefCounted.owned(VisualShaderNodeCurveTexture(GodotHandle(ObjectCalls.constructObject("VisualShaderNodeCurveTexture"))))
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
