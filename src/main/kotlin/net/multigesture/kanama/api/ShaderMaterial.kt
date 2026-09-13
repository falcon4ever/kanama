package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.ObjectCalls
import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName

/**
 * A material defined by a custom `Shader` program and the values of its shader parameters.
 *
 * Generated from Godot docs: ShaderMaterial
 */
class ShaderMaterial internal constructor(handle: GodotHandle) : Material(handle) {

    var shader: Shader?
        @JvmName("shaderProperty")
        get() = getShader()
        @JvmName("setShaderProperty")
        set(value) = setShader(value)

    /**
     * The `Shader` program used to render this material.
     *
     * Generated from Godot docs: ShaderMaterial.set_shader
     */
    fun setShader(shader: Shader?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(
            setShaderBind,
            segment,
            listOf(shader?.requireOpenHandle() ?: MemorySegment.NULL),
        )
    }

    /**
     * The `Shader` program used to render this material.
     *
     * Generated from Godot docs: ShaderMaterial.get_shader
     */
    fun getShader(): Shader? {
        checkOpen()
        return Shader.wrap(ObjectCalls.ptrcallNoArgsRetObject(getShaderBind, segment))
    }

    /**
     * Changes the value set for this material of a uniform in the shader. Note: `param` is
     * case-sensitive and must match the name of the uniform in the code exactly (not the capitalized
     * name in the inspector). Note: Changes to the shader uniform will be effective on all instances
     * using this `ShaderMaterial`. To prevent this, use per-instance uniforms with
     * `CanvasItem.set_instance_shader_parameter`, `GeometryInstance3D.set_instance_shader_parameter`
     * or duplicate the `ShaderMaterial` resource using `Resource.duplicate`. Per-instance uniforms
     * allow for better shader reuse and are therefore faster, so they should be preferred over
     * duplicating the `ShaderMaterial` when possible.
     *
     * Generated from Godot docs: ShaderMaterial.set_shader_parameter
     */
    fun setShaderParameter(param: String, value: Any?) {
        checkOpen()
        ObjectCalls.ptrcallWithStringNameAndVariantArg(setShaderParameterBind, segment, param, value)
    }

    /**
     * Returns the current value set for this material of a uniform in the shader.
     *
     * Generated from Godot docs: ShaderMaterial.get_shader_parameter
     */
    fun getShaderParameter(param: String): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(getShaderParameterBind, segment, param)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ShaderMaterial? =
            wrap(handle.segment)

        @JvmStatic
        fun fromObject(value: GodotObject): ShaderMaterial? =
            if (value.isClass("ShaderMaterial")) ShaderMaterial(value.handle) else null

        @JvmStatic
        fun fromResource(value: Resource): ShaderMaterial? =
            if (value.isClass("ShaderMaterial")) ShaderMaterial(value.handle) else null

        internal fun wrap(handle: MemorySegment): ShaderMaterial? =
            if (handle.address() == 0L) null else ShaderMaterial(GodotHandle(handle))

        private const val SET_SHADER_HASH = 3341921675L
        private const val GET_SHADER_HASH = 2078273437L
        private const val SET_SHADER_PARAMETER_HASH = 3776071444L
        private const val GET_SHADER_PARAMETER_HASH = 2760726917L

        private val setShaderBind by lazy {
            ObjectCalls.getMethodBind("ShaderMaterial", "set_shader", SET_SHADER_HASH)
        }

        private val getShaderBind by lazy {
            ObjectCalls.getMethodBind("ShaderMaterial", "get_shader", GET_SHADER_HASH)
        }

        private val setShaderParameterBind by lazy {
            ObjectCalls.getMethodBind("ShaderMaterial", "set_shader_parameter", SET_SHADER_PARAMETER_HASH)
        }

        private val getShaderParameterBind by lazy {
            ObjectCalls.getMethodBind("ShaderMaterial", "get_shader_parameter", GET_SHADER_PARAMETER_HASH)
        }
    }
}
