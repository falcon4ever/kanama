package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: ShaderMaterial
 */
class ShaderMaterial(handle: MemorySegment) : Material(handle) {
    var shader: Shader?
        @JvmName("shaderProperty")
        get() = getShader()
        @JvmName("setShaderProperty")
        set(value) = setShader(value)

    fun setShader(shader: Shader?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setShaderBind, handle, listOf(shader?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getShader(): Shader? {
        checkOpen()
        return Shader.wrap(ObjectCalls.ptrcallNoArgsRetObject(getShaderBind, handle))
    }

    fun getShaderParameter(param: String): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(getShaderParameterBind, handle, param)
    }

    // ShaderMaterial.set_shader_parameter(param, value) via the Variant call path (iOS has no
    // (StringName, Variant) ptrcall shape; call() boxes the value). Matches desktop.
    fun setShaderParameter(param: String, value: Any?) {
        call("set_shader_parameter", param, value)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ShaderMaterial? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ShaderMaterial? =
            if (handle.address() == 0L) null else ShaderMaterial(handle)

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

        private const val GET_SHADER_PARAMETER_HASH = 2760726917L
        private val getShaderParameterBind by lazy {
            ObjectCalls.getMethodBind("ShaderMaterial", "get_shader_parameter", GET_SHADER_PARAMETER_HASH)
        }
    }
}
