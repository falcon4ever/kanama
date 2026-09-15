package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeCubemap
 */
class VisualShaderNodeCubemap(handle: GodotHandle) : VisualShaderNode(handle) {
    var source: Long
        @JvmName("sourceProperty")
        get() = getSource()
        @JvmName("setSourceProperty")
        set(value) = setSource(value)

    var cubeMap: TextureLayered?
        @JvmName("cubeMapProperty")
        get() = getCubeMap()
        @JvmName("setCubeMapProperty")
        set(value) = setCubeMap(value)

    var textureType: Long
        @JvmName("textureTypeProperty")
        get() = getTextureType()
        @JvmName("setTextureTypeProperty")
        set(value) = setTextureType(value)

    fun setSource(value: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setSourceBind, segment, value)
    }

    fun getSource(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getSourceBind, segment)
    }

    fun setCubeMap(value: TextureLayered?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setCubeMapBind, segment, listOf(value?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun getCubeMap(): TextureLayered? {
        checkOpen()
        return TextureLayered.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCubeMapBind, segment))
    }

    fun setTextureType(value: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setTextureTypeBind, segment, value)
    }

    fun getTextureType(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getTextureTypeBind, segment)
    }

    companion object {
        const val SOURCE_TEXTURE: Long = 0L
        const val SOURCE_PORT: Long = 1L
        const val SOURCE_MAX: Long = 2L
        const val TYPE_DATA: Long = 0L
        const val TYPE_COLOR: Long = 1L
        const val TYPE_NORMAL_MAP: Long = 2L
        const val TYPE_MAX: Long = 3L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeCubemap? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeCubemap? =
            if (handle.address() == 0L) null else VisualShaderNodeCubemap(GodotHandle(handle))

        private const val SET_SOURCE_HASH = 1625400621L
        private val setSourceBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeCubemap", "set_source", SET_SOURCE_HASH)
        }

        private const val GET_SOURCE_HASH = 2222048781L
        private val getSourceBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeCubemap", "get_source", GET_SOURCE_HASH)
        }

        private const val SET_CUBE_MAP_HASH = 1278366092L
        private val setCubeMapBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeCubemap", "set_cube_map", SET_CUBE_MAP_HASH)
        }

        private const val GET_CUBE_MAP_HASH = 3984243839L
        private val getCubeMapBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeCubemap", "get_cube_map", GET_CUBE_MAP_HASH)
        }

        private const val SET_TEXTURE_TYPE_HASH = 1899718876L
        private val setTextureTypeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeCubemap", "set_texture_type", SET_TEXTURE_TYPE_HASH)
        }

        private const val GET_TEXTURE_TYPE_HASH = 3356498888L
        private val getTextureTypeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeCubemap", "get_texture_type", GET_TEXTURE_TYPE_HASH)
        }
    }
}
