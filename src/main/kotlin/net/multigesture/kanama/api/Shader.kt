package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A shader implemented in the Godot shading language.
 *
 * Generated from Godot docs: Shader
 */
open class Shader(handle: MemorySegment) : Resource(handle) {
    var code: String
        @JvmName("codeProperty")
        get() = getCode()
        @JvmName("setCodeProperty")
        set(value) = setCode(value)

    fun getMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getModeBind, handle)
    }

    fun setCode(code: String) {
        ObjectCalls.ptrcallWithStringArg(setCodeBind, handle, code)
    }

    fun getCode(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getCodeBind, handle)
    }

    fun setDefaultTextureParameter(name: String, texture: Texture?, index: Int = 0) {
        ObjectCalls.ptrcallWithStringNameObjectIntArgs(setDefaultTextureParameterBind, handle, name, texture?.requireOpenHandle() ?: MemorySegment.NULL, index)
    }

    fun getDefaultTextureParameter(name: String, index: Int = 0): Texture? {
        return Texture.wrap(ObjectCalls.ptrcallWithStringNameAndIntArgRetObject(getDefaultTextureParameterBind, handle, name, index))
    }

    fun getShaderUniformList(getGroups: Boolean = false): List<Any?> {
        return ObjectCalls.ptrcallWithBoolArgRetArray(getShaderUniformListBind, handle, getGroups)
    }

    fun inspectNativeShaderCode() {
        ObjectCalls.ptrcallNoArgs(inspectNativeShaderCodeBind, handle)
    }

    companion object {
        const val MODE_SPATIAL: Long = 0L
        const val MODE_CANVAS_ITEM: Long = 1L
        const val MODE_PARTICLES: Long = 2L
        const val MODE_SKY: Long = 3L
        const val MODE_FOG: Long = 4L
        const val MODE_TEXTURE_BLIT: Long = 5L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): Shader? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Shader? =
            if (handle.address() == 0L) null else Shader(handle)

        private const val GET_MODE_HASH = 3392948163L
        private val getModeBind by lazy {
            ObjectCalls.getMethodBind("Shader", "get_mode", GET_MODE_HASH)
        }

        private const val SET_CODE_HASH = 83702148L
        private val setCodeBind by lazy {
            ObjectCalls.getMethodBind("Shader", "set_code", SET_CODE_HASH)
        }

        private const val GET_CODE_HASH = 201670096L
        private val getCodeBind by lazy {
            ObjectCalls.getMethodBind("Shader", "get_code", GET_CODE_HASH)
        }

        private const val SET_DEFAULT_TEXTURE_PARAMETER_HASH = 3850209648L
        private val setDefaultTextureParameterBind by lazy {
            ObjectCalls.getMethodBind("Shader", "set_default_texture_parameter", SET_DEFAULT_TEXTURE_PARAMETER_HASH)
        }

        private const val GET_DEFAULT_TEXTURE_PARAMETER_HASH = 4213877425L
        private val getDefaultTextureParameterBind by lazy {
            ObjectCalls.getMethodBind("Shader", "get_default_texture_parameter", GET_DEFAULT_TEXTURE_PARAMETER_HASH)
        }

        private const val GET_SHADER_UNIFORM_LIST_HASH = 1230511656L
        private val getShaderUniformListBind by lazy {
            ObjectCalls.getMethodBind("Shader", "get_shader_uniform_list", GET_SHADER_UNIFORM_LIST_HASH)
        }

        private const val INSPECT_NATIVE_SHADER_CODE_HASH = 3218959716L
        private val inspectNativeShaderCodeBind by lazy {
            ObjectCalls.getMethodBind("Shader", "inspect_native_shader_code", INSPECT_NATIVE_SHADER_CODE_HASH)
        }
    }
}
