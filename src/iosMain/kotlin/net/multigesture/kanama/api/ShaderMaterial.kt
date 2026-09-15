package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: ShaderMaterial
 */
class ShaderMaterial(handle: GodotHandle) : Material(handle) {
    var shader: Shader?
        @JvmName("shaderProperty")
        get() = getShader()
        @JvmName("setShaderProperty")
        set(value) = setShader(value)

    fun setShader(shader: Shader?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setShaderBind, segment, listOf(shader?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getShader(): Shader? {
        checkOpen()
        return Shader.wrap(ObjectCalls.ptrcallNoArgsRetObject(getShaderBind, segment))
    }

    fun setShaderParameter(param: String, value: Any?) {
        checkOpen()
        ObjectCalls.ptrcallWithStringNameAndVariantArg(setShaderParameterBind, segment, param, value)
    }

    fun getShaderParameter(param: String): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(getShaderParameterBind, segment, param)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ShaderMaterial? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): ShaderMaterial? =
            if (handle.address() == 0L) null else ShaderMaterial(GodotHandle(handle))

        // Downcast a Resource to ShaderMaterial (null if not), mirroring the desktop helper.
        fun fromResource(value: Resource?): ShaderMaterial? =
            value?.takeIf { it.isClass("ShaderMaterial") }?.let { ShaderMaterial(it.handle) }

        private const val SET_SHADER_HASH = 3341921675L
        private val setShaderBind by lazy {
            ObjectCalls.getMethodBind("ShaderMaterial", "set_shader", SET_SHADER_HASH)
        }

        private const val GET_SHADER_HASH = 2078273437L
        private val getShaderBind by lazy {
            ObjectCalls.getMethodBind("ShaderMaterial", "get_shader", GET_SHADER_HASH)
        }

        private const val SET_SHADER_PARAMETER_HASH = 3776071444L
        private val setShaderParameterBind by lazy {
            ObjectCalls.getMethodBind("ShaderMaterial", "set_shader_parameter", SET_SHADER_PARAMETER_HASH)
        }

        private const val GET_SHADER_PARAMETER_HASH = 2760726917L
        private val getShaderParameterBind by lazy {
            ObjectCalls.getMethodBind("ShaderMaterial", "get_shader_parameter", GET_SHADER_PARAMETER_HASH)
        }
    }
}
