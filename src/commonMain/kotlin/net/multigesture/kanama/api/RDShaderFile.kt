package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Compiled shader file in SPIR-V form (used by `RenderingDevice`). Not to be confused with Godot's
 * own `Shader`.
 *
 * Generated from Godot docs: RDShaderFile
 */
class RDShaderFile(handle: GodotHandle) : Resource(handle) {
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
        checkOpen()
        ObjectCalls.ptrcallWithObjectAndStringNameArg(Binds.setBytecodeBind, segment, bytecode?.requireOpenHandle() ?: NULL_SEGMENT, version)
    }

    /**
     * Returns the SPIR-V intermediate representation for the specified shader `version`.
     *
     * Generated from Godot docs: RDShaderFile.get_spirv
     */
    fun getSpirv(version: String = ""): RDShaderSPIRV? {
        checkOpen()
        return RDShaderSPIRV.wrapOwned(ObjectCalls.ptrcallWithStringNameArgRetObject(Binds.getSpirvBind, segment, version))
    }

    /**
     * Returns the list of compiled versions for this shader.
     *
     * Generated from Godot docs: RDShaderFile.get_version_list
     */
    fun getVersionList(): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetStringNameList(Binds.getVersionListBind, segment)
    }

    /**
     * The base compilation error message, which indicates errors not related to a specific shader
     * stage if non-empty. If empty, shader compilation is not necessarily successful (check
     * `RDShaderSPIRV`'s error message members).
     *
     * Generated from Godot docs: RDShaderFile.set_base_error
     */
    fun setBaseError(error: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setBaseErrorBind, segment, error)
    }

    /**
     * The base compilation error message, which indicates errors not related to a specific shader
     * stage if non-empty. If empty, shader compilation is not necessarily successful (check
     * `RDShaderSPIRV`'s error message members).
     *
     * Generated from Godot docs: RDShaderFile.get_base_error
     */
    fun getBaseError(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getBaseErrorBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): RDShaderFile? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): RDShaderFile? =
            if (handle.address() == 0L) null else RefCounted.owned(RDShaderFile(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): RDShaderFile? =
            if (handle.address() == 0L) null else RDShaderFile(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_BYTECODE_HASH = 1526857008L
        @JvmField
        val setBytecodeBind =
            ObjectCalls.getMethodBind("RDShaderFile", "set_bytecode", SET_BYTECODE_HASH)

        private const val GET_SPIRV_HASH = 2689310080L
        @JvmField
        val getSpirvBind =
            ObjectCalls.getMethodBind("RDShaderFile", "get_spirv", GET_SPIRV_HASH)

        private const val GET_VERSION_LIST_HASH = 3995934104L
        @JvmField
        val getVersionListBind =
            ObjectCalls.getMethodBind("RDShaderFile", "get_version_list", GET_VERSION_LIST_HASH)

        private const val SET_BASE_ERROR_HASH = 83702148L
        @JvmField
        val setBaseErrorBind =
            ObjectCalls.getMethodBind("RDShaderFile", "set_base_error", SET_BASE_ERROR_HASH)

        private const val GET_BASE_ERROR_HASH = 201670096L
        @JvmField
        val getBaseErrorBind =
            ObjectCalls.getMethodBind("RDShaderFile", "get_base_error", GET_BASE_ERROR_HASH)
    }
}
