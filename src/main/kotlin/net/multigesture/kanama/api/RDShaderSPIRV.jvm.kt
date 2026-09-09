package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for RDShaderSPIRV (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP RDShaderSPIRV waits on: ptrcallWithLongAndByteArrayArg,
//   ptrcallWithLongArgRetByteArray, ptrcallWithLongArgRetString
// Index: docs/contributing/ios-shape-gap.md

/**
 * The SPIR-V bytecode for the vertex shader stage.
 *
 * Generated from Godot docs: RDShaderSPIRV.set_stage_bytecode
 */
fun RDShaderSPIRV.setStageBytecode(stage: Long, bytecode: ByteArray) {
    checkOpen()
    ObjectCalls.ptrcallWithLongAndByteArrayArg(setStageBytecodeBind, handle, stage, bytecode)
}

/**
 * The SPIR-V bytecode for the vertex shader stage.
 *
 * Generated from Godot docs: RDShaderSPIRV.get_stage_bytecode
 */
fun RDShaderSPIRV.getStageBytecode(stage: Long): ByteArray {
    checkOpen()
    return ObjectCalls.ptrcallWithLongArgRetByteArray(getStageBytecodeBind, handle, stage)
}

/**
 * The compilation error message for the vertex shader stage (set by the SPIR-V compiler and
 * Godot). If empty, shader compilation was successful.
 *
 * Generated from Godot docs: RDShaderSPIRV.get_stage_compile_error
 */
fun RDShaderSPIRV.getStageCompileError(stage: Long): String {
    checkOpen()
    return ObjectCalls.ptrcallWithLongArgRetString(getStageCompileErrorBind, handle, stage)
}

var RDShaderSPIRV.bytecodeVertex: ByteArray
    @JvmName("bytecodeVertexProperty")
    get() = getStageBytecode(0L)
    @JvmName("setBytecodeVertexProperty")
    set(value) = setStageBytecode(0L, value)

var RDShaderSPIRV.bytecodeFragment: ByteArray
    @JvmName("bytecodeFragmentProperty")
    get() = getStageBytecode(1L)
    @JvmName("setBytecodeFragmentProperty")
    set(value) = setStageBytecode(1L, value)

var RDShaderSPIRV.bytecodeTesselationControl: ByteArray
    @JvmName("bytecodeTesselationControlProperty")
    get() = getStageBytecode(2L)
    @JvmName("setBytecodeTesselationControlProperty")
    set(value) = setStageBytecode(2L, value)

var RDShaderSPIRV.bytecodeTesselationEvaluation: ByteArray
    @JvmName("bytecodeTesselationEvaluationProperty")
    get() = getStageBytecode(3L)
    @JvmName("setBytecodeTesselationEvaluationProperty")
    set(value) = setStageBytecode(3L, value)

var RDShaderSPIRV.bytecodeCompute: ByteArray
    @JvmName("bytecodeComputeProperty")
    get() = getStageBytecode(4L)
    @JvmName("setBytecodeComputeProperty")
    set(value) = setStageBytecode(4L, value)

var RDShaderSPIRV.bytecodeRaygen: ByteArray
    @JvmName("bytecodeRaygenProperty")
    get() = getStageBytecode(5L)
    @JvmName("setBytecodeRaygenProperty")
    set(value) = setStageBytecode(5L, value)

var RDShaderSPIRV.bytecodeAnyHit: ByteArray
    @JvmName("bytecodeAnyHitProperty")
    get() = getStageBytecode(6L)
    @JvmName("setBytecodeAnyHitProperty")
    set(value) = setStageBytecode(6L, value)

var RDShaderSPIRV.bytecodeClosestHit: ByteArray
    @JvmName("bytecodeClosestHitProperty")
    get() = getStageBytecode(7L)
    @JvmName("setBytecodeClosestHitProperty")
    set(value) = setStageBytecode(7L, value)

var RDShaderSPIRV.bytecodeMiss: ByteArray
    @JvmName("bytecodeMissProperty")
    get() = getStageBytecode(8L)
    @JvmName("setBytecodeMissProperty")
    set(value) = setStageBytecode(8L, value)

var RDShaderSPIRV.bytecodeIntersection: ByteArray
    @JvmName("bytecodeIntersectionProperty")
    get() = getStageBytecode(9L)
    @JvmName("setBytecodeIntersectionProperty")
    set(value) = setStageBytecode(9L, value)

var RDShaderSPIRV.compileErrorVertex: String
    @JvmName("compileErrorVertexProperty")
    get() = getStageCompileError(0L)
    @JvmName("setCompileErrorVertexProperty")
    set(value) = setStageCompileError(0L, value)

var RDShaderSPIRV.compileErrorFragment: String
    @JvmName("compileErrorFragmentProperty")
    get() = getStageCompileError(1L)
    @JvmName("setCompileErrorFragmentProperty")
    set(value) = setStageCompileError(1L, value)

var RDShaderSPIRV.compileErrorTesselationControl: String
    @JvmName("compileErrorTesselationControlProperty")
    get() = getStageCompileError(2L)
    @JvmName("setCompileErrorTesselationControlProperty")
    set(value) = setStageCompileError(2L, value)

var RDShaderSPIRV.compileErrorTesselationEvaluation: String
    @JvmName("compileErrorTesselationEvaluationProperty")
    get() = getStageCompileError(3L)
    @JvmName("setCompileErrorTesselationEvaluationProperty")
    set(value) = setStageCompileError(3L, value)

var RDShaderSPIRV.compileErrorCompute: String
    @JvmName("compileErrorComputeProperty")
    get() = getStageCompileError(4L)
    @JvmName("setCompileErrorComputeProperty")
    set(value) = setStageCompileError(4L, value)

var RDShaderSPIRV.compileErrorRaygen: String
    @JvmName("compileErrorRaygenProperty")
    get() = getStageCompileError(5L)
    @JvmName("setCompileErrorRaygenProperty")
    set(value) = setStageCompileError(5L, value)

var RDShaderSPIRV.compileErrorAnyHit: String
    @JvmName("compileErrorAnyHitProperty")
    get() = getStageCompileError(6L)
    @JvmName("setCompileErrorAnyHitProperty")
    set(value) = setStageCompileError(6L, value)

var RDShaderSPIRV.compileErrorClosestHit: String
    @JvmName("compileErrorClosestHitProperty")
    get() = getStageCompileError(7L)
    @JvmName("setCompileErrorClosestHitProperty")
    set(value) = setStageCompileError(7L, value)

var RDShaderSPIRV.compileErrorMiss: String
    @JvmName("compileErrorMissProperty")
    get() = getStageCompileError(8L)
    @JvmName("setCompileErrorMissProperty")
    set(value) = setStageCompileError(8L, value)

var RDShaderSPIRV.compileErrorIntersection: String
    @JvmName("compileErrorIntersectionProperty")
    get() = getStageCompileError(9L)
    @JvmName("setCompileErrorIntersectionProperty")
    set(value) = setStageCompileError(9L, value)

private const val SET_STAGE_BYTECODE_HASH = 3514097977L
private val setStageBytecodeBind by lazy {
    ObjectCalls.getMethodBind("RDShaderSPIRV", "set_stage_bytecode", SET_STAGE_BYTECODE_HASH)
}

private const val GET_STAGE_BYTECODE_HASH = 3816765404L
private val getStageBytecodeBind by lazy {
    ObjectCalls.getMethodBind("RDShaderSPIRV", "get_stage_bytecode", GET_STAGE_BYTECODE_HASH)
}

private const val GET_STAGE_COMPILE_ERROR_HASH = 3354920045L
private val getStageCompileErrorBind by lazy {
    ObjectCalls.getMethodBind("RDShaderSPIRV", "get_stage_compile_error", GET_STAGE_COMPILE_ERROR_HASH)
}
