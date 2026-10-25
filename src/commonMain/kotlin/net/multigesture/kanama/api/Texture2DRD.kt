package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed
import net.multigesture.kanama.types.RID

/**
 * Texture for 2D that is bound to a texture created on the `RenderingDevice`.
 *
 * Generated from Godot docs: Texture2DRD
 */
class Texture2DRD(handle: GodotHandle) : Texture2D(handle) {
    var textureRdRid: RID
        @JvmName("textureRdRidProperty")
        get() = getTextureRdRid()
        @JvmName("setTextureRdRidProperty")
        set(value) = setTextureRdRid(value)

    /**
     * The RID of the texture object created on the `RenderingDevice`.
     *
     * Generated from Godot docs: Texture2DRD.set_texture_rd_rid
     */
    fun setTextureRdRid(textureRdRid: RID) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDArg(Binds.setTextureRdRidBind, segment, textureRdRid)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The RID of the texture object created on the `RenderingDevice`.
     *
     * Generated from Godot docs: Texture2DRD.get_texture_rd_rid
     */
    fun getTextureRdRid(): RID {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetRID(Binds.getTextureRdRidBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Texture2DRD? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): Texture2DRD? =
            if (handle.address() == 0L) null else RefCounted.owned(Texture2DRD(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): Texture2DRD? =
            if (handle.address() == 0L) null else Texture2DRD(GodotHandle(handle))

        /** A new Texture2DRD, GDScript's `Texture2DRD.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): Texture2DRD =
            RefCounted.owned(Texture2DRD(GodotHandle(requireConstructed(ObjectCalls.constructObject("Texture2DRD"), "Texture2DRD"))))
    }

    private object Binds {
        private const val SET_TEXTURE_RD_RID_HASH = 2722037293L
        @JvmField
        val setTextureRdRidBind =
            ObjectCalls.getMethodBind("Texture2DRD", "set_texture_rd_rid", SET_TEXTURE_RD_RID_HASH)

        private const val GET_TEXTURE_RD_RID_HASH = 2944877500L
        @JvmField
        val getTextureRdRidBind =
            ObjectCalls.getMethodBind("Texture2DRD", "get_texture_rd_rid", GET_TEXTURE_RD_RID_HASH)
    }
}
