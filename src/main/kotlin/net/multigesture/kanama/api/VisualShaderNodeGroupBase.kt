package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeGroupBase
 */
open class VisualShaderNodeGroupBase(handle: MemorySegment) : VisualShaderNodeResizableBase(handle) {
    fun setInputs(inputs: String) {
        ObjectCalls.ptrcallWithStringArg(setInputsBind, handle, inputs)
    }

    fun getInputs(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getInputsBind, handle)
    }

    fun setOutputs(outputs: String) {
        ObjectCalls.ptrcallWithStringArg(setOutputsBind, handle, outputs)
    }

    fun getOutputs(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getOutputsBind, handle)
    }

    fun isValidPortName(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(isValidPortNameBind, handle, name)
    }

    fun addInputPort(id: Int, type: Int, name: String) {
        ObjectCalls.ptrcallWithTwoIntAndStringArgs(addInputPortBind, handle, id, type, name)
    }

    fun removeInputPort(id: Int) {
        ObjectCalls.ptrcallWithIntArg(removeInputPortBind, handle, id)
    }

    fun getInputPortCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getInputPortCountBind, handle)
    }

    fun hasInputPort(id: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(hasInputPortBind, handle, id)
    }

    fun clearInputPorts() {
        ObjectCalls.ptrcallNoArgs(clearInputPortsBind, handle)
    }

    fun addOutputPort(id: Int, type: Int, name: String) {
        ObjectCalls.ptrcallWithTwoIntAndStringArgs(addOutputPortBind, handle, id, type, name)
    }

    fun removeOutputPort(id: Int) {
        ObjectCalls.ptrcallWithIntArg(removeOutputPortBind, handle, id)
    }

    fun getOutputPortCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getOutputPortCountBind, handle)
    }

    fun hasOutputPort(id: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(hasOutputPortBind, handle, id)
    }

    fun clearOutputPorts() {
        ObjectCalls.ptrcallNoArgs(clearOutputPortsBind, handle)
    }

    fun setInputPortName(id: Int, name: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setInputPortNameBind, handle, id, name)
    }

    fun setInputPortType(id: Int, type: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setInputPortTypeBind, handle, id, type)
    }

    fun setOutputPortName(id: Int, name: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setOutputPortNameBind, handle, id, name)
    }

    fun setOutputPortType(id: Int, type: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setOutputPortTypeBind, handle, id, type)
    }

    fun getFreeInputPortId(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getFreeInputPortIdBind, handle)
    }

    fun getFreeOutputPortId(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getFreeOutputPortIdBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeGroupBase? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeGroupBase? =
            if (handle.address() == 0L) null else VisualShaderNodeGroupBase(handle)

        private const val SET_INPUTS_HASH = 83702148L
        private val setInputsBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "set_inputs", SET_INPUTS_HASH)
        }

        private const val GET_INPUTS_HASH = 201670096L
        private val getInputsBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "get_inputs", GET_INPUTS_HASH)
        }

        private const val SET_OUTPUTS_HASH = 83702148L
        private val setOutputsBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "set_outputs", SET_OUTPUTS_HASH)
        }

        private const val GET_OUTPUTS_HASH = 201670096L
        private val getOutputsBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "get_outputs", GET_OUTPUTS_HASH)
        }

        private const val IS_VALID_PORT_NAME_HASH = 3927539163L
        private val isValidPortNameBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "is_valid_port_name", IS_VALID_PORT_NAME_HASH)
        }

        private const val ADD_INPUT_PORT_HASH = 2285447957L
        private val addInputPortBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "add_input_port", ADD_INPUT_PORT_HASH)
        }

        private const val REMOVE_INPUT_PORT_HASH = 1286410249L
        private val removeInputPortBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "remove_input_port", REMOVE_INPUT_PORT_HASH)
        }

        private const val GET_INPUT_PORT_COUNT_HASH = 3905245786L
        private val getInputPortCountBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "get_input_port_count", GET_INPUT_PORT_COUNT_HASH)
        }

        private const val HAS_INPUT_PORT_HASH = 1116898809L
        private val hasInputPortBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "has_input_port", HAS_INPUT_PORT_HASH)
        }

        private const val CLEAR_INPUT_PORTS_HASH = 3218959716L
        private val clearInputPortsBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "clear_input_ports", CLEAR_INPUT_PORTS_HASH)
        }

        private const val ADD_OUTPUT_PORT_HASH = 2285447957L
        private val addOutputPortBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "add_output_port", ADD_OUTPUT_PORT_HASH)
        }

        private const val REMOVE_OUTPUT_PORT_HASH = 1286410249L
        private val removeOutputPortBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "remove_output_port", REMOVE_OUTPUT_PORT_HASH)
        }

        private const val GET_OUTPUT_PORT_COUNT_HASH = 3905245786L
        private val getOutputPortCountBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "get_output_port_count", GET_OUTPUT_PORT_COUNT_HASH)
        }

        private const val HAS_OUTPUT_PORT_HASH = 1116898809L
        private val hasOutputPortBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "has_output_port", HAS_OUTPUT_PORT_HASH)
        }

        private const val CLEAR_OUTPUT_PORTS_HASH = 3218959716L
        private val clearOutputPortsBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "clear_output_ports", CLEAR_OUTPUT_PORTS_HASH)
        }

        private const val SET_INPUT_PORT_NAME_HASH = 501894301L
        private val setInputPortNameBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "set_input_port_name", SET_INPUT_PORT_NAME_HASH)
        }

        private const val SET_INPUT_PORT_TYPE_HASH = 3937882851L
        private val setInputPortTypeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "set_input_port_type", SET_INPUT_PORT_TYPE_HASH)
        }

        private const val SET_OUTPUT_PORT_NAME_HASH = 501894301L
        private val setOutputPortNameBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "set_output_port_name", SET_OUTPUT_PORT_NAME_HASH)
        }

        private const val SET_OUTPUT_PORT_TYPE_HASH = 3937882851L
        private val setOutputPortTypeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "set_output_port_type", SET_OUTPUT_PORT_TYPE_HASH)
        }

        private const val GET_FREE_INPUT_PORT_ID_HASH = 3905245786L
        private val getFreeInputPortIdBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "get_free_input_port_id", GET_FREE_INPUT_PORT_ID_HASH)
        }

        private const val GET_FREE_OUTPUT_PORT_ID_HASH = 3905245786L
        private val getFreeOutputPortIdBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "get_free_output_port_id", GET_FREE_OUTPUT_PORT_ID_HASH)
        }
    }
}
