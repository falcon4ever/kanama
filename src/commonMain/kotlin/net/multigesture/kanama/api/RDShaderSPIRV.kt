package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * SPIR-V intermediate representation as part of an `RDShaderFile` (used by `RenderingDevice`).
 *
 * Generated from Godot docs: RDShaderSPIRV
 */
class RDShaderSPIRV(handle: MemorySegment) : Resource(handle) {
    /**
     * The compilation error message for the vertex shader stage (set by the SPIR-V compiler and
     * Godot). If empty, shader compilation was successful.
     *
     * Generated from Godot docs: RDShaderSPIRV.set_stage_compile_error
     */
    fun setStageCompileError(stage: Long, compileError: String) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndStringArg(setStageCompileErrorBind, handle, stage, compileError)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): RDShaderSPIRV? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RDShaderSPIRV? =
            if (handle.address() == 0L) null else RDShaderSPIRV(handle)

        private const val SET_STAGE_COMPILE_ERROR_HASH = 620821314L
        private val setStageCompileErrorBind by lazy {
            ObjectCalls.getMethodBind("RDShaderSPIRV", "set_stage_compile_error", SET_STAGE_COMPILE_ERROR_HASH)
        }
    }
}
