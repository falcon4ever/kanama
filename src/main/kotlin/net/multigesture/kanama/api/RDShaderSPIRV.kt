package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * SPIR-V intermediate representation as part of an `RDShaderFile` (used by `RenderingDevice`).
 *
 * Generated from Godot docs: RDShaderSPIRV
 */
class RDShaderSPIRV(handle: MemorySegment) : Resource(handle) {
    /**
     * The SPIR-V bytecode for the vertex shader stage.
     *
     * Generated from Godot docs: RDShaderSPIRV.set_stage_bytecode
     */
    fun setStageBytecode(stage: Long, bytecode: ByteArray) {
        ObjectCalls.ptrcallWithLongAndByteArrayArg(setStageBytecodeBind, handle, stage, bytecode)
    }

    /**
     * The SPIR-V bytecode for the vertex shader stage.
     *
     * Generated from Godot docs: RDShaderSPIRV.get_stage_bytecode
     */
    fun getStageBytecode(stage: Long): ByteArray {
        return ObjectCalls.ptrcallWithLongArgRetByteArray(getStageBytecodeBind, handle, stage)
    }

    /**
     * The compilation error message for the vertex shader stage (set by the SPIR-V compiler and
     * Godot). If empty, shader compilation was successful.
     *
     * Generated from Godot docs: RDShaderSPIRV.set_stage_compile_error
     */
    fun setStageCompileError(stage: Long, compileError: String) {
        ObjectCalls.ptrcallWithLongAndStringArg(setStageCompileErrorBind, handle, stage, compileError)
    }

    /**
     * The compilation error message for the vertex shader stage (set by the SPIR-V compiler and
     * Godot). If empty, shader compilation was successful.
     *
     * Generated from Godot docs: RDShaderSPIRV.get_stage_compile_error
     */
    fun getStageCompileError(stage: Long): String {
        return ObjectCalls.ptrcallWithLongArgRetString(getStageCompileErrorBind, handle, stage)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): RDShaderSPIRV? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RDShaderSPIRV? =
            if (handle.address() == 0L) null else RDShaderSPIRV(handle)

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
