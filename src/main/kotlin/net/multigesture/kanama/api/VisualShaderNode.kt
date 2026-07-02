package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNode
 */
open class VisualShaderNode(handle: MemorySegment) : Resource(handle) {
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

    fun getDefaultInputPort(type: Long): Int {
        return ObjectCalls.ptrcallWithLongArgRetInt(getDefaultInputPortBind, handle, type)
    }

    fun setOutputPortForPreview(port: Int) {
        ObjectCalls.ptrcallWithIntArg(setOutputPortForPreviewBind, handle, port)
    }

    fun getOutputPortForPreview(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getOutputPortForPreviewBind, handle)
    }

    fun setInputPortDefaultValue(port: Int, value: Any?, prevValue: Any? = null) {
        ObjectCalls.ptrcallWithIntAndTwoVariantArgs(setInputPortDefaultValueBind, handle, port, value, prevValue)
    }

    fun getInputPortDefaultValue(port: Int): Any? {
        return ObjectCalls.ptrcallWithIntArgRetVariantScalar(getInputPortDefaultValueBind, handle, port)
    }

    fun removeInputPortDefaultValue(port: Int) {
        ObjectCalls.ptrcallWithIntArg(removeInputPortDefaultValueBind, handle, port)
    }

    fun clearDefaultInputValues() {
        ObjectCalls.ptrcallNoArgs(clearDefaultInputValuesBind, handle)
    }

    fun setDefaultInputValues(values: List<Any?>) {
        ObjectCalls.ptrcallWithArrayArg(setDefaultInputValuesBind, handle, values)
    }

    fun getDefaultInputValues(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(getDefaultInputValuesBind, handle)
    }

    fun setFrame(frame: Int) {
        ObjectCalls.ptrcallWithIntArg(setFrameBind, handle, frame)
    }

    fun getFrame(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getFrameBind, handle)
    }

    companion object {
        const val PORT_TYPE_SCALAR: Long = 0L
        const val PORT_TYPE_SCALAR_INT: Long = 1L
        const val PORT_TYPE_SCALAR_UINT: Long = 2L
        const val PORT_TYPE_VECTOR_2D: Long = 3L
        const val PORT_TYPE_VECTOR_3D: Long = 4L
        const val PORT_TYPE_VECTOR_4D: Long = 5L
        const val PORT_TYPE_BOOLEAN: Long = 6L
        const val PORT_TYPE_TRANSFORM: Long = 7L
        const val PORT_TYPE_SAMPLER: Long = 8L
        const val PORT_TYPE_MAX: Long = 9L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNode? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNode? =
            if (handle.address() == 0L) null else VisualShaderNode(handle)

        private const val GET_DEFAULT_INPUT_PORT_HASH = 1894493699L
        private val getDefaultInputPortBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNode", "get_default_input_port", GET_DEFAULT_INPUT_PORT_HASH)
        }

        private const val SET_OUTPUT_PORT_FOR_PREVIEW_HASH = 1286410249L
        private val setOutputPortForPreviewBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNode", "set_output_port_for_preview", SET_OUTPUT_PORT_FOR_PREVIEW_HASH)
        }

        private const val GET_OUTPUT_PORT_FOR_PREVIEW_HASH = 3905245786L
        private val getOutputPortForPreviewBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNode", "get_output_port_for_preview", GET_OUTPUT_PORT_FOR_PREVIEW_HASH)
        }

        private const val SET_INPUT_PORT_DEFAULT_VALUE_HASH = 150923387L
        private val setInputPortDefaultValueBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNode", "set_input_port_default_value", SET_INPUT_PORT_DEFAULT_VALUE_HASH)
        }

        private const val GET_INPUT_PORT_DEFAULT_VALUE_HASH = 4227898402L
        private val getInputPortDefaultValueBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNode", "get_input_port_default_value", GET_INPUT_PORT_DEFAULT_VALUE_HASH)
        }

        private const val REMOVE_INPUT_PORT_DEFAULT_VALUE_HASH = 1286410249L
        private val removeInputPortDefaultValueBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNode", "remove_input_port_default_value", REMOVE_INPUT_PORT_DEFAULT_VALUE_HASH)
        }

        private const val CLEAR_DEFAULT_INPUT_VALUES_HASH = 3218959716L
        private val clearDefaultInputValuesBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNode", "clear_default_input_values", CLEAR_DEFAULT_INPUT_VALUES_HASH)
        }

        private const val SET_DEFAULT_INPUT_VALUES_HASH = 381264803L
        private val setDefaultInputValuesBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNode", "set_default_input_values", SET_DEFAULT_INPUT_VALUES_HASH)
        }

        private const val GET_DEFAULT_INPUT_VALUES_HASH = 3995934104L
        private val getDefaultInputValuesBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNode", "get_default_input_values", GET_DEFAULT_INPUT_VALUES_HASH)
        }

        private const val SET_FRAME_HASH = 1286410249L
        private val setFrameBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNode", "set_frame", SET_FRAME_HASH)
        }

        private const val GET_FRAME_HASH = 3905245786L
        private val getFrameBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNode", "get_frame", GET_FRAME_HASH)
        }
    }
}
