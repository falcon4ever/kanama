package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
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
    fun getMode(): Shader.Mode {
        checkOpen()
        return Shader.Mode(ObjectCalls.ptrcallNoArgsRetLong(getModeBind, segment))
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
        return Texture.wrapOwned(ObjectCalls.ptrcallWithStringNameAndIntArgRetObject(getDefaultTextureParameterBind, segment, name, index))
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

    /**
     * Godot's `Shader.Mode` enum as a typed value: `.value` is the raw number Godot uses, and the
     * companion holds the named values (`Shader.Mode.<NAME>`).
     *
     * Generated from Godot docs: Shader.Mode
     */
    @JvmInline
    value class Mode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Mode used to draw all 3D objects.
             *
             * Generated from Godot docs: Shader.MODE_SPATIAL
             */
            val SPATIAL: Mode get() = Mode(0L)
            /**
             * Mode used to draw all 2D objects.
             *
             * Generated from Godot docs: Shader.MODE_CANVAS_ITEM
             */
            val CANVAS_ITEM: Mode get() = Mode(1L)
            /**
             * Mode used to calculate particle information on a per-particle basis. Not used for drawing.
             *
             * Generated from Godot docs: Shader.MODE_PARTICLES
             */
            val PARTICLES: Mode get() = Mode(2L)
            /**
             * Mode used for drawing skies. Only works with shaders attached to `Sky` objects.
             *
             * Generated from Godot docs: Shader.MODE_SKY
             */
            val SKY: Mode get() = Mode(3L)
            /**
             * Mode used for setting the color and density of volumetric fog effect.
             *
             * Generated from Godot docs: Shader.MODE_FOG
             */
            val FOG: Mode get() = Mode(4L)
            /**
             * Mode used for drawing to DrawableTexture resources via blit calls.
             *
             * Generated from Godot docs: Shader.MODE_TEXTURE_BLIT
             */
            val TEXTURE_BLIT: Mode get() = Mode(5L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Shader? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): Shader? =
            if (handle.address() == 0L) null else RefCounted.owned(Shader(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): Shader? =
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
