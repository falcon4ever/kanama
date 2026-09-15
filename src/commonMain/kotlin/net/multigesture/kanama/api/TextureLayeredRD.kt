package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.RID

/**
 * Abstract base class for layered texture RD types.
 *
 * Generated from Godot docs: TextureLayeredRD
 */
open class TextureLayeredRD(handle: GodotHandle) : TextureLayered(handle) {
    var textureRdRid: RID
        @JvmName("textureRdRidProperty")
        get() = getTextureRdRid()
        @JvmName("setTextureRdRidProperty")
        set(value) = setTextureRdRid(value)

    /**
     * The RID of the texture object created on the `RenderingDevice`.
     *
     * Generated from Godot docs: TextureLayeredRD.set_texture_rd_rid
     */
    fun setTextureRdRid(textureRdRid: RID) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDArg(setTextureRdRidBind, segment, textureRdRid)
    }

    /**
     * The RID of the texture object created on the `RenderingDevice`.
     *
     * Generated from Godot docs: TextureLayeredRD.get_texture_rd_rid
     */
    fun getTextureRdRid(): RID {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetRID(getTextureRdRidBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): TextureLayeredRD? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): TextureLayeredRD? =
            if (handle.address() == 0L) null else TextureLayeredRD(GodotHandle(handle))

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
