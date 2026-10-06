package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNode
 */
open class VisualShaderNode(handle: GodotHandle) : Resource(handle) {
    var outputPortForPreview: Int
        @JvmName("outputPortForPreviewProperty")
        get() = getOutputPortForPreview()
        @JvmName("setOutputPortForPreviewProperty")
        set(value) = setOutputPortForPreview(value)

    var defaultInputValues: List<Any?>
        @JvmName("defaultInputValuesProperty")
        get() = getDefaultInputValues()
        @JvmName("setDefaultInputValuesProperty")
        set(value) = setDefaultInputValues(value)

    var linkedParentGraphFrame: Int
        @JvmName("linkedParentGraphFrameProperty")
        get() = getFrame()
        @JvmName("setLinkedParentGraphFrameProperty")
        set(value) = setFrame(value)

    fun getDefaultInputPort(type: VisualShaderNode.PortType): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetInt(Binds.getDefaultInputPortBind, segment, type.value)
    }

    fun setOutputPortForPreview(port: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setOutputPortForPreviewBind, segment, port)
    }

    fun getOutputPortForPreview(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getOutputPortForPreviewBind, segment)
    }

    fun setInputPortDefaultValue(port: Int, value: Any?, prevValue: Any? = null) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndTwoVariantArgs(Binds.setInputPortDefaultValueBind, segment, port, value, prevValue)
    }

    fun getInputPortDefaultValue(port: Int): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetVariantScalar(Binds.getInputPortDefaultValueBind, segment, port)
    }

    fun removeInputPortDefaultValue(port: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.removeInputPortDefaultValueBind, segment, port)
    }

    fun clearDefaultInputValues() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.clearDefaultInputValuesBind, segment)
    }

    fun setDefaultInputValues(values: List<Any?>) {
        checkOpen()
        ObjectCalls.ptrcallWithArrayArg(Binds.setDefaultInputValuesBind, segment, values)
    }

    fun getDefaultInputValues(): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetArray(Binds.getDefaultInputValuesBind, segment)
    }

    fun setFrame(frame: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setFrameBind, segment, frame)
    }

    fun getFrame(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getFrameBind, segment)
    }

    @JvmInline
    value class PortType(override val value: Long) : GodotEnumValue {
        companion object {
            val SCALAR: PortType get() = PortType(0L)
            val SCALAR_INT: PortType get() = PortType(1L)
            val SCALAR_UINT: PortType get() = PortType(2L)
            val VECTOR_2D: PortType get() = PortType(3L)
            val VECTOR_3D: PortType get() = PortType(4L)
            val VECTOR_4D: PortType get() = PortType(5L)
            val BOOLEAN: PortType get() = PortType(6L)
            val TRANSFORM: PortType get() = PortType(7L)
            val SAMPLER: PortType get() = PortType(8L)
            val MAX: PortType get() = PortType(9L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNode? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNode? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNode(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNode? =
            if (handle.address() == 0L) null else VisualShaderNode(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_DEFAULT_INPUT_PORT_HASH = 1894493699L
        @JvmField
        val getDefaultInputPortBind =
            ObjectCalls.getMethodBind("VisualShaderNode", "get_default_input_port", GET_DEFAULT_INPUT_PORT_HASH)

        private const val SET_OUTPUT_PORT_FOR_PREVIEW_HASH = 1286410249L
        @JvmField
        val setOutputPortForPreviewBind =
            ObjectCalls.getMethodBind("VisualShaderNode", "set_output_port_for_preview", SET_OUTPUT_PORT_FOR_PREVIEW_HASH)

        private const val GET_OUTPUT_PORT_FOR_PREVIEW_HASH = 3905245786L
        @JvmField
        val getOutputPortForPreviewBind =
            ObjectCalls.getMethodBind("VisualShaderNode", "get_output_port_for_preview", GET_OUTPUT_PORT_FOR_PREVIEW_HASH)

        private const val SET_INPUT_PORT_DEFAULT_VALUE_HASH = 150923387L
        @JvmField
        val setInputPortDefaultValueBind =
            ObjectCalls.getMethodBind("VisualShaderNode", "set_input_port_default_value", SET_INPUT_PORT_DEFAULT_VALUE_HASH)

        private const val GET_INPUT_PORT_DEFAULT_VALUE_HASH = 4227898402L
        @JvmField
        val getInputPortDefaultValueBind =
            ObjectCalls.getMethodBind("VisualShaderNode", "get_input_port_default_value", GET_INPUT_PORT_DEFAULT_VALUE_HASH)

        private const val REMOVE_INPUT_PORT_DEFAULT_VALUE_HASH = 1286410249L
        @JvmField
        val removeInputPortDefaultValueBind =
            ObjectCalls.getMethodBind("VisualShaderNode", "remove_input_port_default_value", REMOVE_INPUT_PORT_DEFAULT_VALUE_HASH)

        private const val CLEAR_DEFAULT_INPUT_VALUES_HASH = 3218959716L
        @JvmField
        val clearDefaultInputValuesBind =
            ObjectCalls.getMethodBind("VisualShaderNode", "clear_default_input_values", CLEAR_DEFAULT_INPUT_VALUES_HASH)

        private const val SET_DEFAULT_INPUT_VALUES_HASH = 381264803L
        @JvmField
        val setDefaultInputValuesBind =
            ObjectCalls.getMethodBind("VisualShaderNode", "set_default_input_values", SET_DEFAULT_INPUT_VALUES_HASH)

        private const val GET_DEFAULT_INPUT_VALUES_HASH = 3995934104L
        @JvmField
        val getDefaultInputValuesBind =
            ObjectCalls.getMethodBind("VisualShaderNode", "get_default_input_values", GET_DEFAULT_INPUT_VALUES_HASH)

        private const val SET_FRAME_HASH = 1286410249L
        @JvmField
        val setFrameBind =
            ObjectCalls.getMethodBind("VisualShaderNode", "set_frame", SET_FRAME_HASH)

        private const val GET_FRAME_HASH = 3905245786L
        @JvmField
        val getFrameBind =
            ObjectCalls.getMethodBind("VisualShaderNode", "get_frame", GET_FRAME_HASH)
    }
}
