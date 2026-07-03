package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Compiled shader file in SPIR-V form (used by `RenderingDevice`). Not to be confused with Godot's
 * own `Shader`.
 *
 * Generated from Godot docs: RDShaderFile
 */
class RDShaderFile(handle: MemorySegment) : Resource(handle) {
    var baseError: String
        @JvmName("baseErrorProperty")
        get() = getBaseError()
        @JvmName("setBaseErrorProperty")
        set(value) = setBaseError(value)

    /**
     * Sets the SPIR-V `bytecode` that will be compiled for the specified `version`.
     *
     * Generated from Godot docs: RDShaderFile.set_bytecode
     */
    fun setBytecode(bytecode: RDShaderSPIRV?, version: String = "") {
        ObjectCalls.ptrcallWithObjectAndStringNameArg(setBytecodeBind, handle, bytecode?.requireOpenHandle() ?: MemorySegment.NULL, version)
    }

    /**
     * Returns the SPIR-V intermediate representation for the specified shader `version`.
     *
     * Generated from Godot docs: RDShaderFile.get_spirv
     */
    fun getSpirv(version: String = ""): RDShaderSPIRV? {
        return RDShaderSPIRV.wrap(ObjectCalls.ptrcallWithStringNameArgRetObject(getSpirvBind, handle, version))
    }

    /**
     * Returns the list of compiled versions for this shader.
     *
     * Generated from Godot docs: RDShaderFile.get_version_list
     */
    fun getVersionList(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetStringNameList(getVersionListBind, handle)
    }

    /**
     * The base compilation error message, which indicates errors not related to a specific shader
     * stage if non-empty. If empty, shader compilation is not necessarily successful (check
     * `RDShaderSPIRV`'s error message members).
     *
     * Generated from Godot docs: RDShaderFile.set_base_error
     */
    fun setBaseError(error: String) {
        ObjectCalls.ptrcallWithStringArg(setBaseErrorBind, handle, error)
    }

    /**
     * The base compilation error message, which indicates errors not related to a specific shader
     * stage if non-empty. If empty, shader compilation is not necessarily successful (check
     * `RDShaderSPIRV`'s error message members).
     *
     * Generated from Godot docs: RDShaderFile.get_base_error
     */
    fun getBaseError(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getBaseErrorBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): RDShaderFile? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RDShaderFile? =
            if (handle.address() == 0L) null else RDShaderFile(handle)

        private const val SET_BYTECODE_HASH = 1526857008L
        private val setBytecodeBind by lazy {
            ObjectCalls.getMethodBind("RDShaderFile", "set_bytecode", SET_BYTECODE_HASH)
        }

        private const val GET_SPIRV_HASH = 2689310080L
        private val getSpirvBind by lazy {
            ObjectCalls.getMethodBind("RDShaderFile", "get_spirv", GET_SPIRV_HASH)
        }

        private const val GET_VERSION_LIST_HASH = 3995934104L
        private val getVersionListBind by lazy {
            ObjectCalls.getMethodBind("RDShaderFile", "get_version_list", GET_VERSION_LIST_HASH)
        }

        private const val SET_BASE_ERROR_HASH = 83702148L
        private val setBaseErrorBind by lazy {
            ObjectCalls.getMethodBind("RDShaderFile", "set_base_error", SET_BASE_ERROR_HASH)
        }

        private const val GET_BASE_ERROR_HASH = 201670096L
        private val getBaseErrorBind by lazy {
            ObjectCalls.getMethodBind("RDShaderFile", "get_base_error", GET_BASE_ERROR_HASH)
        }
    }
}
