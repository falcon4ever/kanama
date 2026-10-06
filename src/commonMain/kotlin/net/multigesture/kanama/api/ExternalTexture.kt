package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector2

/**
 * Texture which displays the content of an external buffer.
 *
 * Generated from Godot docs: ExternalTexture
 */
class ExternalTexture(handle: GodotHandle) : Texture2D(handle) {
    /**
     * External texture size.
     *
     * Generated from Godot docs: ExternalTexture.set_size
     */
    fun setSize(size: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(Binds.setSizeBind, segment, size)
    }

    /**
     * Returns the external texture ID. Depending on your use case, you may need to pass this to
     * platform APIs, for example, when creating an `android.graphics.SurfaceTexture` on Android.
     *
     * Generated from Godot docs: ExternalTexture.get_external_texture_id
     */
    fun getExternalTextureId(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(Binds.getExternalTextureIdBind, segment)
    }

    /**
     * Sets the external buffer ID. Depending on your use case, you may need to call this with data
     * received from a platform API, for example, `SurfaceTexture.getHardwareBuffer()` on Android.
     *
     * Generated from Godot docs: ExternalTexture.set_external_buffer_id
     */
    fun setExternalBufferId(externalBufferId: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setExternalBufferIdBind, segment, externalBufferId)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ExternalTexture? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): ExternalTexture? =
            if (handle.address() == 0L) null else RefCounted.owned(ExternalTexture(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): ExternalTexture? =
            if (handle.address() == 0L) null else ExternalTexture(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_SIZE_HASH = 743155724L
        @JvmField
        val setSizeBind =
            ObjectCalls.getMethodBind("ExternalTexture", "set_size", SET_SIZE_HASH)

        private const val GET_EXTERNAL_TEXTURE_ID_HASH = 3905245786L
        @JvmField
        val getExternalTextureIdBind =
            ObjectCalls.getMethodBind("ExternalTexture", "get_external_texture_id", GET_EXTERNAL_TEXTURE_ID_HASH)

        private const val SET_EXTERNAL_BUFFER_ID_HASH = 1286410249L
        @JvmField
        val setExternalBufferIdBind =
            ObjectCalls.getMethodBind("ExternalTexture", "set_external_buffer_id", SET_EXTERNAL_BUFFER_ID_HASH)
    }
}
