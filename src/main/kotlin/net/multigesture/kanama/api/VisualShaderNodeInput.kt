package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeInput
 */
class VisualShaderNodeInput(handle: MemorySegment) : VisualShaderNode(handle) {
    var inputName: String
        @JvmName("inputNameProperty")
        get() = getInputName()
        @JvmName("setInputNameProperty")
        set(value) = setInputName(value)

    fun setInputName(name: String) {
        ObjectCalls.ptrcallWithStringArg(setInputNameBind, handle, name)
    }

    fun getInputName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getInputNameBind, handle)
    }

    fun getInputRealName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getInputRealNameBind, handle)
    }

    object Signals {
        const val inputTypeChanged: String = "input_type_changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeInput? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeInput? =
            if (handle.address() == 0L) null else VisualShaderNodeInput(handle)

        private const val SET_INPUT_NAME_HASH = 83702148L
        private val setInputNameBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeInput", "set_input_name", SET_INPUT_NAME_HASH)
        }

        private const val GET_INPUT_NAME_HASH = 201670096L
        private val getInputNameBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeInput", "get_input_name", GET_INPUT_NAME_HASH)
        }

        private const val GET_INPUT_REAL_NAME_HASH = 201670096L
        private val getInputRealNameBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeInput", "get_input_real_name", GET_INPUT_REAL_NAME_HASH)
        }
    }
}
