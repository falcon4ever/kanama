package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * A material that processes blit calls to a DrawableTexture.
 *
 * Generated from Godot docs: BlitMaterial
 */
class BlitMaterial(handle: MemorySegment) : Material(handle) {
    var blendMode: Long
        @JvmName("blendModeProperty")
        get() = getBlendMode()
        @JvmName("setBlendModeProperty")
        set(value) = setBlendMode(value)

    /**
     * The manner in which the newly blitted texture is blended with the original DrawableTexture.
     *
     * Generated from Godot docs: BlitMaterial.set_blend_mode
     */
    fun setBlendMode(blendMode: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setBlendModeBind, handle, blendMode)
    }

    /**
     * The manner in which the newly blitted texture is blended with the original DrawableTexture.
     *
     * Generated from Godot docs: BlitMaterial.get_blend_mode
     */
    fun getBlendMode(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getBlendModeBind, handle)
    }

    companion object {
        const val BLEND_MODE_MIX: Long = 0L
        const val BLEND_MODE_ADD: Long = 1L
        const val BLEND_MODE_SUB: Long = 2L
        const val BLEND_MODE_MUL: Long = 3L
        const val BLEND_MODE_DISABLED: Long = 4L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): BlitMaterial? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): BlitMaterial? =
            if (handle.address() == 0L) null else BlitMaterial(handle)

        private const val SET_BLEND_MODE_HASH = 80206916L
        private val setBlendModeBind by lazy {
            ObjectCalls.getMethodBind("BlitMaterial", "set_blend_mode", SET_BLEND_MODE_HASH)
        }

        private const val GET_BLEND_MODE_HASH = 4234246416L
        private val getBlendModeBind by lazy {
            ObjectCalls.getMethodBind("BlitMaterial", "get_blend_mode", GET_BLEND_MODE_HASH)
        }
    }
}
