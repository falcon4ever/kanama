package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A snippet of shader code to be included in a `Shader` with `#include`.
 *
 * Generated from Godot docs: ShaderInclude
 */
class ShaderInclude(handle: GodotHandle) : Resource(handle) {
    var code: String
        @JvmName("codeProperty")
        get() = getCode()
        @JvmName("setCodeProperty")
        set(value) = setCode(value)

    /**
     * Returns the code of the shader include file. The returned text is what the user has written, not
     * the full generated code used internally.
     *
     * Generated from Godot docs: ShaderInclude.set_code
     */
    fun setCode(code: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setCodeBind, segment, code)
    }

    /**
     * Returns the code of the shader include file. The returned text is what the user has written, not
     * the full generated code used internally.
     *
     * Generated from Godot docs: ShaderInclude.get_code
     */
    fun getCode(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getCodeBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ShaderInclude? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): ShaderInclude? =
            if (handle.address() == 0L) null else RefCounted.owned(ShaderInclude(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): ShaderInclude? =
            if (handle.address() == 0L) null else ShaderInclude(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_CODE_HASH = 83702148L
        @JvmField
        val setCodeBind =
            ObjectCalls.getMethodBind("ShaderInclude", "set_code", SET_CODE_HASH)

        private const val GET_CODE_HASH = 201670096L
        @JvmField
        val getCodeBind =
            ObjectCalls.getMethodBind("ShaderInclude", "get_code", GET_CODE_HASH)
    }
}
