package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

/**
 * Abstract base class for layered texture RD types.
 *
 * Generated from Godot docs: TextureLayeredRD
 */
open class TextureLayeredRD(handle: MemorySegment) : TextureLayered(handle) {
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
        fun fromHandle(handle: MemorySegment): TextureLayeredRD? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): TextureLayeredRD? =
            if (handle.address() == 0L) null else TextureLayeredRD(handle)

        private const val SET_TEXTURE_RD_RID_HASH = 2722037293L
        private val setTextureRdRidBind by lazy {
            ObjectCalls.getMethodBind("TextureLayeredRD", "set_texture_rd_rid", SET_TEXTURE_RD_RID_HASH)
        }

        private const val GET_TEXTURE_RD_RID_HASH = 2944877500L
        private val getTextureRdRidBind by lazy {
            ObjectCalls.getMethodBind("TextureLayeredRD", "get_texture_rd_rid", GET_TEXTURE_RD_RID_HASH)
        }
    }
}
