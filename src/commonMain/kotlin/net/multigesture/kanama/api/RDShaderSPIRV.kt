package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * SPIR-V intermediate representation as part of an `RDShaderFile` (used by `RenderingDevice`).
 *
 * Generated from Godot docs: RDShaderSPIRV
 */
class RDShaderSPIRV(handle: GodotHandle) : Resource(handle) {
    var bytecodeVertex: ByteArray
        @JvmName("bytecodeVertexProperty")
        get() = getStageBytecode(RenderingDevice.ShaderStage.VERTEX)
        @JvmName("setBytecodeVertexProperty")
        set(value) = setStageBytecode(RenderingDevice.ShaderStage.VERTEX, value)

    var bytecodeFragment: ByteArray
        @JvmName("bytecodeFragmentProperty")
        get() = getStageBytecode(RenderingDevice.ShaderStage.FRAGMENT)
        @JvmName("setBytecodeFragmentProperty")
        set(value) = setStageBytecode(RenderingDevice.ShaderStage.FRAGMENT, value)

    var bytecodeTesselationControl: ByteArray
        @JvmName("bytecodeTesselationControlProperty")
        get() = getStageBytecode(RenderingDevice.ShaderStage.TESSELATION_CONTROL)
        @JvmName("setBytecodeTesselationControlProperty")
        set(value) = setStageBytecode(RenderingDevice.ShaderStage.TESSELATION_CONTROL, value)

    var bytecodeTesselationEvaluation: ByteArray
        @JvmName("bytecodeTesselationEvaluationProperty")
        get() = getStageBytecode(RenderingDevice.ShaderStage.TESSELATION_EVALUATION)
        @JvmName("setBytecodeTesselationEvaluationProperty")
        set(value) = setStageBytecode(RenderingDevice.ShaderStage.TESSELATION_EVALUATION, value)

    var bytecodeCompute: ByteArray
        @JvmName("bytecodeComputeProperty")
        get() = getStageBytecode(RenderingDevice.ShaderStage.COMPUTE)
        @JvmName("setBytecodeComputeProperty")
        set(value) = setStageBytecode(RenderingDevice.ShaderStage.COMPUTE, value)

    var bytecodeRaygen: ByteArray
        @JvmName("bytecodeRaygenProperty")
        get() = getStageBytecode(RenderingDevice.ShaderStage.RAYGEN)
        @JvmName("setBytecodeRaygenProperty")
        set(value) = setStageBytecode(RenderingDevice.ShaderStage.RAYGEN, value)

    var bytecodeAnyHit: ByteArray
        @JvmName("bytecodeAnyHitProperty")
        get() = getStageBytecode(RenderingDevice.ShaderStage.ANY_HIT)
        @JvmName("setBytecodeAnyHitProperty")
        set(value) = setStageBytecode(RenderingDevice.ShaderStage.ANY_HIT, value)

    var bytecodeClosestHit: ByteArray
        @JvmName("bytecodeClosestHitProperty")
        get() = getStageBytecode(RenderingDevice.ShaderStage.CLOSEST_HIT)
        @JvmName("setBytecodeClosestHitProperty")
        set(value) = setStageBytecode(RenderingDevice.ShaderStage.CLOSEST_HIT, value)

    var bytecodeMiss: ByteArray
        @JvmName("bytecodeMissProperty")
        get() = getStageBytecode(RenderingDevice.ShaderStage.MISS)
        @JvmName("setBytecodeMissProperty")
        set(value) = setStageBytecode(RenderingDevice.ShaderStage.MISS, value)

    var bytecodeIntersection: ByteArray
        @JvmName("bytecodeIntersectionProperty")
        get() = getStageBytecode(RenderingDevice.ShaderStage.INTERSECTION)
        @JvmName("setBytecodeIntersectionProperty")
        set(value) = setStageBytecode(RenderingDevice.ShaderStage.INTERSECTION, value)

    var compileErrorVertex: String
        @JvmName("compileErrorVertexProperty")
        get() = getStageCompileError(RenderingDevice.ShaderStage.VERTEX)
        @JvmName("setCompileErrorVertexProperty")
        set(value) = setStageCompileError(RenderingDevice.ShaderStage.VERTEX, value)

    var compileErrorFragment: String
        @JvmName("compileErrorFragmentProperty")
        get() = getStageCompileError(RenderingDevice.ShaderStage.FRAGMENT)
        @JvmName("setCompileErrorFragmentProperty")
        set(value) = setStageCompileError(RenderingDevice.ShaderStage.FRAGMENT, value)

    var compileErrorTesselationControl: String
        @JvmName("compileErrorTesselationControlProperty")
        get() = getStageCompileError(RenderingDevice.ShaderStage.TESSELATION_CONTROL)
        @JvmName("setCompileErrorTesselationControlProperty")
        set(value) = setStageCompileError(RenderingDevice.ShaderStage.TESSELATION_CONTROL, value)

    var compileErrorTesselationEvaluation: String
        @JvmName("compileErrorTesselationEvaluationProperty")
        get() = getStageCompileError(RenderingDevice.ShaderStage.TESSELATION_EVALUATION)
        @JvmName("setCompileErrorTesselationEvaluationProperty")
        set(value) = setStageCompileError(RenderingDevice.ShaderStage.TESSELATION_EVALUATION, value)

    var compileErrorCompute: String
        @JvmName("compileErrorComputeProperty")
        get() = getStageCompileError(RenderingDevice.ShaderStage.COMPUTE)
        @JvmName("setCompileErrorComputeProperty")
        set(value) = setStageCompileError(RenderingDevice.ShaderStage.COMPUTE, value)

    var compileErrorRaygen: String
        @JvmName("compileErrorRaygenProperty")
        get() = getStageCompileError(RenderingDevice.ShaderStage.RAYGEN)
        @JvmName("setCompileErrorRaygenProperty")
        set(value) = setStageCompileError(RenderingDevice.ShaderStage.RAYGEN, value)

    var compileErrorAnyHit: String
        @JvmName("compileErrorAnyHitProperty")
        get() = getStageCompileError(RenderingDevice.ShaderStage.ANY_HIT)
        @JvmName("setCompileErrorAnyHitProperty")
        set(value) = setStageCompileError(RenderingDevice.ShaderStage.ANY_HIT, value)

    var compileErrorClosestHit: String
        @JvmName("compileErrorClosestHitProperty")
        get() = getStageCompileError(RenderingDevice.ShaderStage.CLOSEST_HIT)
        @JvmName("setCompileErrorClosestHitProperty")
        set(value) = setStageCompileError(RenderingDevice.ShaderStage.CLOSEST_HIT, value)

    var compileErrorMiss: String
        @JvmName("compileErrorMissProperty")
        get() = getStageCompileError(RenderingDevice.ShaderStage.MISS)
        @JvmName("setCompileErrorMissProperty")
        set(value) = setStageCompileError(RenderingDevice.ShaderStage.MISS, value)

    var compileErrorIntersection: String
        @JvmName("compileErrorIntersectionProperty")
        get() = getStageCompileError(RenderingDevice.ShaderStage.INTERSECTION)
        @JvmName("setCompileErrorIntersectionProperty")
        set(value) = setStageCompileError(RenderingDevice.ShaderStage.INTERSECTION, value)

    /**
     * The SPIR-V bytecode for the vertex shader stage.
     *
     * Generated from Godot docs: RDShaderSPIRV.set_stage_bytecode
     */
    fun setStageBytecode(stage: RenderingDevice.ShaderStage, bytecode: ByteArray) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndByteArrayArg(setStageBytecodeBind, segment, stage.value, bytecode)
    }

    /**
     * The SPIR-V bytecode for the vertex shader stage.
     *
     * Generated from Godot docs: RDShaderSPIRV.get_stage_bytecode
     */
    fun getStageBytecode(stage: RenderingDevice.ShaderStage): ByteArray {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetByteArray(getStageBytecodeBind, segment, stage.value)
    }

    /**
     * The compilation error message for the vertex shader stage (set by the SPIR-V compiler and
     * Godot). If empty, shader compilation was successful.
     *
     * Generated from Godot docs: RDShaderSPIRV.set_stage_compile_error
     */
    fun setStageCompileError(stage: RenderingDevice.ShaderStage, compileError: String) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndStringArg(setStageCompileErrorBind, segment, stage.value, compileError)
    }

    /**
     * The compilation error message for the vertex shader stage (set by the SPIR-V compiler and
     * Godot). If empty, shader compilation was successful.
     *
     * Generated from Godot docs: RDShaderSPIRV.get_stage_compile_error
     */
    fun getStageCompileError(stage: RenderingDevice.ShaderStage): String {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetString(getStageCompileErrorBind, segment, stage.value)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): RDShaderSPIRV? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): RDShaderSPIRV? =
            if (handle.address() == 0L) null else RefCounted.owned(RDShaderSPIRV(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): RDShaderSPIRV? =
            if (handle.address() == 0L) null else RDShaderSPIRV(GodotHandle(handle))

        private const val SET_STAGE_BYTECODE_HASH = 3514097977L
        private val setStageBytecodeBind by lazy {
            ObjectCalls.getMethodBind("RDShaderSPIRV", "set_stage_bytecode", SET_STAGE_BYTECODE_HASH)
        }

        private const val GET_STAGE_BYTECODE_HASH = 3816765404L
        private val getStageBytecodeBind by lazy {
            ObjectCalls.getMethodBind("RDShaderSPIRV", "get_stage_bytecode", GET_STAGE_BYTECODE_HASH)
        }

        private const val SET_STAGE_COMPILE_ERROR_HASH = 620821314L
        private val setStageCompileErrorBind by lazy {
            ObjectCalls.getMethodBind("RDShaderSPIRV", "set_stage_compile_error", SET_STAGE_COMPILE_ERROR_HASH)
        }

        private const val GET_STAGE_COMPILE_ERROR_HASH = 3354920045L
        private val getStageCompileErrorBind by lazy {
            ObjectCalls.getMethodBind("RDShaderSPIRV", "get_stage_compile_error", GET_STAGE_COMPILE_ERROR_HASH)
        }
    }
}
