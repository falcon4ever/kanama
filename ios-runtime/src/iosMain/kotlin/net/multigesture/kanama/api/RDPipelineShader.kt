package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.RID

/**
 * Generated from Godot docs: RDPipelineShader
 */
class RDPipelineShader(handle: MemorySegment) : RefCounted(handle) {
    var shader: RID
        @JvmName("shaderProperty")
        get() = getShader()
        @JvmName("setShaderProperty")
        set(value) = setShader(value)

    val specializationConstants: List<RDPipelineSpecializationConstant>
        @JvmName("specializationConstantsProperty")
        get() = getSpecializationConstants()

    fun setShader(pMember: RID) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDArg(setShaderBind, handle, pMember)
    }

    fun getShader(): RID {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetRID(getShaderBind, handle)
    }

    fun getSpecializationConstants(): List<RDPipelineSpecializationConstant> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getSpecializationConstantsBind, handle, RDPipelineSpecializationConstant::fromHandle)
    }

    companion object {
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

        private const val GET_SPECIALIZATION_CONSTANTS_HASH = 3995934104L
        private val getSpecializationConstantsBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineShader", "get_specialization_constants", GET_SPECIALIZATION_CONSTANTS_HASH)
        }
    }
}
