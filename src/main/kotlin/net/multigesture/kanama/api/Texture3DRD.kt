package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

/**
 * Texture for 3D that is bound to a texture created on the `RenderingDevice`.
 *
 * Generated from Godot docs: Texture3DRD
 */
class Texture3DRD(handle: MemorySegment) : Texture3D(handle) {
    var textureRdRid: RID
        @JvmName("textureRdRidProperty")
        get() = getTextureRdRid()
        @JvmName("setTextureRdRidProperty")
        set(value) = setTextureRdRid(value)

    fun setTextureRdRid(textureRdRid: RID) {
        ObjectCalls.ptrcallWithRIDArg(setTextureRdRidBind, handle, textureRdRid)
    }

    fun getTextureRdRid(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getTextureRdRidBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): Texture3DRD? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Texture3DRD? =
            if (handle.address() == 0L) null else Texture3DRD(handle)

        private const val SET_TEXTURE_RD_RID_HASH = 2722037293L
        private val setTextureRdRidBind by lazy {
            ObjectCalls.getMethodBind("Texture3DRD", "set_texture_rd_rid", SET_TEXTURE_RD_RID_HASH)
        }

        private const val GET_TEXTURE_RD_RID_HASH = 2944877500L
        private val getTextureRdRidBind by lazy {
            ObjectCalls.getMethodBind("Texture3DRD", "get_texture_rd_rid", GET_TEXTURE_RD_RID_HASH)
        }
    }
}
