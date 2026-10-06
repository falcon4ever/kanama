package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A material defined by a custom `Shader` program and the values of its shader parameters.
 *
 * Generated from Godot docs: ShaderMaterial
 */
class ShaderMaterial(handle: GodotHandle) : Material(handle) {
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
        ObjectCalls.ptrcallWithObjectArgs(Binds.setShaderBind, segment, listOf(shader?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The `Shader` program used to render this material.
     *
     * Generated from Godot docs: ShaderMaterial.get_shader
     */
    fun getShader(): Shader? {
        checkOpen()
        return Shader.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getShaderBind, segment))
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
        ObjectCalls.ptrcallWithStringNameAndVariantArg(Binds.setShaderParameterBind, segment, param, value)
    }

    /**
     * Returns the current value set for this material of a uniform in the shader.
     *
     * Generated from Godot docs: ShaderMaterial.get_shader_parameter
     */
    fun getShaderParameter(param: String): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(Binds.getShaderParameterBind, segment, param)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ShaderMaterial? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): ShaderMaterial? =
            if (handle.address() == 0L) null else RefCounted.owned(ShaderMaterial(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): ShaderMaterial? =
            if (handle.address() == 0L) null else ShaderMaterial(GodotHandle(handle))

        // Downcast a GodotObject to ShaderMaterial (null if not).
        @JvmStatic
        fun fromObject(value: GodotObject): ShaderMaterial? =
            if (value.isClass("ShaderMaterial")) RefCounted.retained(ShaderMaterial(value.handle)) else null

        // Downcast a Resource to ShaderMaterial (null if not).
        @JvmStatic
        fun fromResource(value: Resource): ShaderMaterial? =
            if (value.isClass("ShaderMaterial")) RefCounted.retained(ShaderMaterial(value.handle)) else null
    }

    private object Binds {
        private const val SET_SHADER_HASH = 3341921675L
        @JvmField
        val setShaderBind =
            ObjectCalls.getMethodBind("ShaderMaterial", "set_shader", SET_SHADER_HASH)

        private const val GET_SHADER_HASH = 2078273437L
        @JvmField
        val getShaderBind =
            ObjectCalls.getMethodBind("ShaderMaterial", "get_shader", GET_SHADER_HASH)

        private const val SET_SHADER_PARAMETER_HASH = 3776071444L
        @JvmField
        val setShaderParameterBind =
            ObjectCalls.getMethodBind("ShaderMaterial", "set_shader_parameter", SET_SHADER_PARAMETER_HASH)

        private const val GET_SHADER_PARAMETER_HASH = 2760726917L
        @JvmField
        val getShaderParameterBind =
            ObjectCalls.getMethodBind("ShaderMaterial", "get_shader_parameter", GET_SHADER_PARAMETER_HASH)
    }
}
