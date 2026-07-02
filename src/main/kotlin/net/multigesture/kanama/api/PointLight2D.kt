package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * Positional 2D light source.
 *
 * Generated from Godot docs: PointLight2D
 */
class PointLight2D(handle: MemorySegment) : Light2D(handle) {
    var texture: Texture2D?
        @JvmName("textureProperty")
        get() = getTexture()
        @JvmName("setTextureProperty")
        set(value) = setTexture(value)

    var offset: Vector2
        @JvmName("offsetProperty")
        get() = getTextureOffset()
        @JvmName("setOffsetProperty")
        set(value) = setTextureOffset(value)

    var textureScale: Double
        @JvmName("textureScaleProperty")
        get() = getTextureScale()
        @JvmName("setTextureScaleProperty")
        set(value) = setTextureScale(value)

    fun setTexture(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setTextureBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTextureBind, handle))
    }

    fun setTextureOffset(textureOffset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setTextureOffsetBind, handle, textureOffset)
    }

    fun getTextureOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getTextureOffsetBind, handle)
    }

    fun setTextureScale(textureScale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTextureScaleBind, handle, textureScale)
    }

    fun getTextureScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTextureScaleBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PointLight2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PointLight2D? =
            if (handle.address() == 0L) null else PointLight2D(handle)

        private const val SET_TEXTURE_HASH = 4051416890L
        private val setTextureBind by lazy {
            ObjectCalls.getMethodBind("PointLight2D", "set_texture", SET_TEXTURE_HASH)
        }

        private const val GET_TEXTURE_HASH = 3635182373L
        private val getTextureBind by lazy {
            ObjectCalls.getMethodBind("PointLight2D", "get_texture", GET_TEXTURE_HASH)
        }

        private const val SET_TEXTURE_OFFSET_HASH = 743155724L
        private val setTextureOffsetBind by lazy {
            ObjectCalls.getMethodBind("PointLight2D", "set_texture_offset", SET_TEXTURE_OFFSET_HASH)
        }

        private const val GET_TEXTURE_OFFSET_HASH = 3341600327L
        private val getTextureOffsetBind by lazy {
            ObjectCalls.getMethodBind("PointLight2D", "get_texture_offset", GET_TEXTURE_OFFSET_HASH)
        }

        private const val SET_TEXTURE_SCALE_HASH = 373806689L
        private val setTextureScaleBind by lazy {
            ObjectCalls.getMethodBind("PointLight2D", "set_texture_scale", SET_TEXTURE_SCALE_HASH)
        }

        private const val GET_TEXTURE_SCALE_HASH = 1740695150L
        private val getTextureScaleBind by lazy {
            ObjectCalls.getMethodBind("PointLight2D", "get_texture_scale", GET_TEXTURE_SCALE_HASH)
        }
    }
}
