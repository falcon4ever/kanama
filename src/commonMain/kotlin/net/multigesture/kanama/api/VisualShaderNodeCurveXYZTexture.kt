package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Performs a `CurveXYZTexture` lookup within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeCurveXYZTexture
 */
class VisualShaderNodeCurveXYZTexture(handle: GodotHandle) : VisualShaderNodeResizableBase(handle) {
    var texture: CurveXYZTexture?
        @JvmName("textureProperty")
        get() = getTexture()
        @JvmName("setTextureProperty")
        set(value) = setTexture(value)

    /**
     * The source texture.
     *
     * Generated from Godot docs: VisualShaderNodeCurveXYZTexture.set_texture
     */
    fun setTexture(texture: CurveXYZTexture?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setTextureBind, segment, listOf(texture?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(texture)
    }

    /**
     * The source texture.
     *
     * Generated from Godot docs: VisualShaderNodeCurveXYZTexture.get_texture
     */
    fun getTexture(): CurveXYZTexture? {
        checkOpen()
        val ret = CurveXYZTexture.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getTextureBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeCurveXYZTexture? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeCurveXYZTexture? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeCurveXYZTexture(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeCurveXYZTexture? =
            if (handle.address() == 0L) null else VisualShaderNodeCurveXYZTexture(GodotHandle(handle))

        /** A new VisualShaderNodeCurveXYZTexture, GDScript's `VisualShaderNodeCurveXYZTexture.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeCurveXYZTexture =
            RefCounted.owned(VisualShaderNodeCurveXYZTexture(GodotHandle(requireConstructed(ObjectCalls.constructObject("VisualShaderNodeCurveXYZTexture"), "VisualShaderNodeCurveXYZTexture"))))
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
