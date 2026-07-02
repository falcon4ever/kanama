package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A snippet of shader code to be included in a `Shader` with `#include`.
 *
 * Generated from Godot docs: ShaderInclude
 */
class ShaderInclude(handle: MemorySegment) : Resource(handle) {
    var code: String
        @JvmName("codeProperty")
        get() = getCode()
        @JvmName("setCodeProperty")
        set(value) = setCode(value)

    fun setCode(code: String) {
        ObjectCalls.ptrcallWithStringArg(setCodeBind, handle, code)
    }

    fun getCode(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getCodeBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ShaderInclude? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ShaderInclude? =
            if (handle.address() == 0L) null else ShaderInclude(handle)

        private const val SET_CODE_HASH = 83702148L
        private val setCodeBind by lazy {
            ObjectCalls.getMethodBind("ShaderInclude", "set_code", SET_CODE_HASH)
        }

        private const val GET_CODE_HASH = 201670096L
        private val getCodeBind by lazy {
            ObjectCalls.getMethodBind("ShaderInclude", "get_code", GET_CODE_HASH)
        }
    }
}
