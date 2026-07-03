package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

/**
 * Pipeline shader (used by `RenderingDevice`).
 *
 * Generated from Godot docs: RDPipelineShader
 */
class RDPipelineShader(handle: MemorySegment) : RefCounted(handle) {
    var shader: RID
        @JvmName("shaderProperty")
        get() = getShader()
        @JvmName("setShaderProperty")
        set(value) = setShader(value)

    var specializationConstants: List<RDPipelineSpecializationConstant>
        @JvmName("specializationConstantsProperty")
        get() = getSpecializationConstants()
        @JvmName("setSpecializationConstantsProperty")
        set(value) = setSpecializationConstants(value)

    /**
     * Shader resource. The required stage is selected by the pipeline.
     *
     * Generated from Godot docs: RDPipelineShader.set_shader
     */
    fun setShader(pMember: RID) {
        ObjectCalls.ptrcallWithRIDArg(setShaderBind, handle, pMember)
    }

    /**
     * Shader resource. The required stage is selected by the pipeline.
     *
     * Generated from Godot docs: RDPipelineShader.get_shader
     */
    fun getShader(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getShaderBind, handle)
    }

    /**
     * Specialization constants applied to the selected shader stage at pipeline creation time.
     *
     * Generated from Godot docs: RDPipelineShader.set_specialization_constants
     */
    fun setSpecializationConstants(specializationConstants: List<RDPipelineSpecializationConstant>) {
        ObjectCalls.ptrcallWithObjectListArg(setSpecializationConstantsBind, handle, specializationConstants)
    }

    /**
     * Specialization constants applied to the selected shader stage at pipeline creation time.
     *
     * Generated from Godot docs: RDPipelineShader.get_specialization_constants
     */
    fun getSpecializationConstants(): List<RDPipelineSpecializationConstant> {
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getSpecializationConstantsBind, handle, RDPipelineSpecializationConstant::fromHandle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): RDPipelineShader? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RDPipelineShader? =
            if (handle.address() == 0L) null else RDPipelineShader(handle)

        private const val SET_SHADER_HASH = 2722037293L
        private val setShaderBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineShader", "set_shader", SET_SHADER_HASH)
        }

        private const val GET_SHADER_HASH = 2944877500L
        private val getShaderBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineShader", "get_shader", GET_SHADER_HASH)
        }

        private const val SET_SPECIALIZATION_CONSTANTS_HASH = 381264803L
        private val setSpecializationConstantsBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineShader", "set_specialization_constants", SET_SPECIALIZATION_CONSTANTS_HASH)
        }

        private const val GET_SPECIALIZATION_CONSTANTS_HASH = 3995934104L
        private val getSpecializationConstantsBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineShader", "get_specialization_constants", GET_SPECIALIZATION_CONSTANTS_HASH)
        }
    }
}
