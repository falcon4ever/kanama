package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A shader implemented in the Godot shading language.
 *
 * Generated from Godot docs: Shader
 */
open class Shader(handle: GodotHandle) : Resource(handle) {
    var code: String
        @JvmName("codeProperty")
        get() = getCode()
        @JvmName("setCodeProperty")
        set(value) = setCode(value)

    /**
     * Returns the shader mode for the shader.
     *
     * Generated from Godot docs: Shader.get_mode
     */
    fun getMode(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getModeBind, segment)
    }

    /**
     * Returns the shader's code as the user has written it, not the full generated code used
     * internally.
     *
     * Generated from Godot docs: Shader.set_code
     */
    fun setCode(code: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setCodeBind, segment, code)
    }

    /**
     * Returns the shader's code as the user has written it, not the full generated code used
     * internally.
     *
     * Generated from Godot docs: Shader.get_code
     */
    fun getCode(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getCodeBind, segment)
    }

    /**
     * Sets the default texture to be used with a texture uniform. The default is used if a texture is
     * not set in the `ShaderMaterial`. Note: `name` must match the name of the uniform in the code
     * exactly. Note: If the sampler array is used use `index` to access the specified texture.
     *
     * Generated from Godot docs: Shader.set_default_texture_parameter
     */
    fun setDefaultTextureParameter(name: String, texture: Texture?, index: Int = 0) {
        checkOpen()
        ObjectCalls.ptrcallWithStringNameObjectIntArgs(setDefaultTextureParameterBind, segment, name, texture?.requireOpenHandle() ?: NULL_SEGMENT, index)
    }

    /**
     * Returns the texture that is set as default for the specified parameter. Note: `name` must match
     * the name of the uniform in the code exactly. Note: If the sampler array is used use `index` to
     * access the specified texture.
     *
     * Generated from Godot docs: Shader.get_default_texture_parameter
     */
    fun getDefaultTextureParameter(name: String, index: Int = 0): Texture? {
        checkOpen()
        return Texture.wrap(ObjectCalls.ptrcallWithStringNameAndIntArgRetObject(getDefaultTextureParameterBind, segment, name, index))
    }

    /**
     * Returns the list of shader uniforms that can be assigned to a `ShaderMaterial`, for use with
     * `ShaderMaterial.set_shader_parameter` and `ShaderMaterial.get_shader_parameter`. The parameters
     * returned are contained in dictionaries in a similar format to the ones returned by
     * `Object.get_property_list`. If argument `get_groups` is `true`, parameter grouping hints are
     * also included in the list.
     *
     * Generated from Godot docs: Shader.get_shader_uniform_list
     */
    fun getShaderUniformList(getGroups: Boolean = false): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithBoolArgRetArray(getShaderUniformListBind, segment, getGroups)
    }

    /**
     * Only available when running in the editor. Opens a popup that visualizes the generated shader
     * code, including all variants and internal shader code. See also
     * `Material.inspect_native_shader_code`.
     *
     * Generated from Godot docs: Shader.inspect_native_shader_code
     */
    fun inspectNativeShaderCode() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(inspectNativeShaderCodeBind, segment)
    }

    companion object {
        const val MODE_SPATIAL: Long = 0L
        const val MODE_CANVAS_ITEM: Long = 1L
        const val MODE_PARTICLES: Long = 2L
        const val MODE_SKY: Long = 3L
        const val MODE_FOG: Long = 4L
        const val MODE_TEXTURE_BLIT: Long = 5L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): Shader? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): Shader? =
            if (handle.address() == 0L) null else Shader(GodotHandle(handle))

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
