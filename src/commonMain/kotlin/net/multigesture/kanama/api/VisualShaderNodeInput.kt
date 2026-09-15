package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeInput
 */
class VisualShaderNodeInput(handle: GodotHandle) : VisualShaderNode(handle) {
    var inputName: String
        @JvmName("inputNameProperty")
        get() = getInputName()
        @JvmName("setInputNameProperty")
        set(value) = setInputName(value)

    fun setInputName(name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setInputNameBind, segment, name)
    }

    fun getInputName(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getInputNameBind, segment)
    }

    fun getInputRealName(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getInputRealNameBind, segment)
    }

    object Signals {
        const val inputTypeChanged: String = "input_type_changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeInput? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeInput? =
            if (handle.address() == 0L) null else VisualShaderNodeInput(GodotHandle(handle))

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
