package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeGroupBase
 */
open class VisualShaderNodeGroupBase(handle: GodotHandle) : VisualShaderNodeResizableBase(handle) {
    fun setInputs(inputs: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setInputsBind, segment, inputs)
    }

    fun getInputs(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getInputsBind, segment)
    }

    fun setOutputs(outputs: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setOutputsBind, segment, outputs)
    }

    fun getOutputs(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getOutputsBind, segment)
    }

    fun isValidPortName(name: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetBool(Binds.isValidPortNameBind, segment, name)
    }

    fun addInputPort(id: Int, type: Int, name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntAndStringArgs(Binds.addInputPortBind, segment, id, type, name)
    }

    fun removeInputPort(id: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.removeInputPortBind, segment, id)
    }

    fun getInputPortCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getInputPortCountBind, segment)
    }

    fun hasInputPort(id: Int): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.hasInputPortBind, segment, id)
    }

    fun clearInputPorts() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.clearInputPortsBind, segment)
    }

    fun addOutputPort(id: Int, type: Int, name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntAndStringArgs(Binds.addOutputPortBind, segment, id, type, name)
    }

    fun removeOutputPort(id: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.removeOutputPortBind, segment, id)
    }

    fun getOutputPortCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getOutputPortCountBind, segment)
    }

    fun hasOutputPort(id: Int): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.hasOutputPortBind, segment, id)
    }

    fun clearOutputPorts() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.clearOutputPortsBind, segment)
    }

    fun setInputPortName(id: Int, name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndStringArg(Binds.setInputPortNameBind, segment, id, name)
    }

    fun setInputPortType(id: Int, type: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.setInputPortTypeBind, segment, id, type)
    }

    fun setOutputPortName(id: Int, name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndStringArg(Binds.setOutputPortNameBind, segment, id, name)
    }

    fun setOutputPortType(id: Int, type: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.setOutputPortTypeBind, segment, id, type)
    }

    fun getFreeInputPortId(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getFreeInputPortIdBind, segment)
    }

    fun getFreeOutputPortId(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getFreeOutputPortIdBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeGroupBase? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeGroupBase? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeGroupBase(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeGroupBase? =
            if (handle.address() == 0L) null else VisualShaderNodeGroupBase(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_INPUTS_HASH = 83702148L
        @JvmField
        val setInputsBind =
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "set_inputs", SET_INPUTS_HASH)

        private const val GET_INPUTS_HASH = 201670096L
        @JvmField
        val getInputsBind =
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "get_inputs", GET_INPUTS_HASH)

        private const val SET_OUTPUTS_HASH = 83702148L
        @JvmField
        val setOutputsBind =
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "set_outputs", SET_OUTPUTS_HASH)

        private const val GET_OUTPUTS_HASH = 201670096L
        @JvmField
        val getOutputsBind =
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "get_outputs", GET_OUTPUTS_HASH)

        private const val IS_VALID_PORT_NAME_HASH = 3927539163L
        @JvmField
        val isValidPortNameBind =
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "is_valid_port_name", IS_VALID_PORT_NAME_HASH)

        private const val ADD_INPUT_PORT_HASH = 2285447957L
        @JvmField
        val addInputPortBind =
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "add_input_port", ADD_INPUT_PORT_HASH)

        private const val REMOVE_INPUT_PORT_HASH = 1286410249L
        @JvmField
        val removeInputPortBind =
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "remove_input_port", REMOVE_INPUT_PORT_HASH)

        private const val GET_INPUT_PORT_COUNT_HASH = 3905245786L
        @JvmField
        val getInputPortCountBind =
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "get_input_port_count", GET_INPUT_PORT_COUNT_HASH)

        private const val HAS_INPUT_PORT_HASH = 1116898809L
        @JvmField
        val hasInputPortBind =
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "has_input_port", HAS_INPUT_PORT_HASH)

        private const val CLEAR_INPUT_PORTS_HASH = 3218959716L
        @JvmField
        val clearInputPortsBind =
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "clear_input_ports", CLEAR_INPUT_PORTS_HASH)

        private const val ADD_OUTPUT_PORT_HASH = 2285447957L
        @JvmField
        val addOutputPortBind =
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "add_output_port", ADD_OUTPUT_PORT_HASH)

        private const val REMOVE_OUTPUT_PORT_HASH = 1286410249L
        @JvmField
        val removeOutputPortBind =
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "remove_output_port", REMOVE_OUTPUT_PORT_HASH)

        private const val GET_OUTPUT_PORT_COUNT_HASH = 3905245786L
        @JvmField
        val getOutputPortCountBind =
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "get_output_port_count", GET_OUTPUT_PORT_COUNT_HASH)

        private const val HAS_OUTPUT_PORT_HASH = 1116898809L
        @JvmField
        val hasOutputPortBind =
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "has_output_port", HAS_OUTPUT_PORT_HASH)

        private const val CLEAR_OUTPUT_PORTS_HASH = 3218959716L
        @JvmField
        val clearOutputPortsBind =
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "clear_output_ports", CLEAR_OUTPUT_PORTS_HASH)

        private const val SET_INPUT_PORT_NAME_HASH = 501894301L
        @JvmField
        val setInputPortNameBind =
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "set_input_port_name", SET_INPUT_PORT_NAME_HASH)

        private const val SET_INPUT_PORT_TYPE_HASH = 3937882851L
        @JvmField
        val setInputPortTypeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "set_input_port_type", SET_INPUT_PORT_TYPE_HASH)

        private const val SET_OUTPUT_PORT_NAME_HASH = 501894301L
        @JvmField
        val setOutputPortNameBind =
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "set_output_port_name", SET_OUTPUT_PORT_NAME_HASH)

        private const val SET_OUTPUT_PORT_TYPE_HASH = 3937882851L
        @JvmField
        val setOutputPortTypeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "set_output_port_type", SET_OUTPUT_PORT_TYPE_HASH)

        private const val GET_FREE_INPUT_PORT_ID_HASH = 3905245786L
        @JvmField
        val getFreeInputPortIdBind =
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "get_free_input_port_id", GET_FREE_INPUT_PORT_ID_HASH)

        private const val GET_FREE_OUTPUT_PORT_ID_HASH = 3905245786L
        @JvmField
        val getFreeOutputPortIdBind =
            ObjectCalls.getMethodBind("VisualShaderNodeGroupBase", "get_free_output_port_id", GET_FREE_OUTPUT_PORT_ID_HASH)
    }
}
