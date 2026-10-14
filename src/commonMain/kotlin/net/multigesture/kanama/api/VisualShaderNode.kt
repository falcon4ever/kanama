package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Base class for `VisualShader` nodes. Not related to scene nodes.
 *
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

    /**
     * Returns the input port which should be connected by default when this node is created as a
     * result of dragging a connection from an existing node to the empty space on the graph.
     *
     * Generated from Godot docs: VisualShaderNode.get_default_input_port
     */
    fun getDefaultInputPort(type: VisualShaderNode.PortType): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetInt(Binds.getDefaultInputPortBind, segment, type.value)
    }

    /**
     * Sets the output port index which will be showed for preview. If set to `-1` no port will be open
     * for preview.
     *
     * Generated from Godot docs: VisualShaderNode.set_output_port_for_preview
     */
    fun setOutputPortForPreview(port: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setOutputPortForPreviewBind, segment, port)
    }

    /**
     * Sets the output port index which will be showed for preview. If set to `-1` no port will be open
     * for preview.
     *
     * Generated from Godot docs: VisualShaderNode.get_output_port_for_preview
     */
    fun getOutputPortForPreview(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getOutputPortForPreviewBind, segment)
    }

    /**
     * Sets the default `value` for the selected input `port`.
     *
     * Generated from Godot docs: VisualShaderNode.set_input_port_default_value
     */
    fun setInputPortDefaultValue(port: Int, value: Any?, prevValue: Any? = null) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndTwoVariantArgs(Binds.setInputPortDefaultValueBind, segment, port, value, prevValue)
    }

    /**
     * Returns the default value of the input `port`.
     *
     * Generated from Godot docs: VisualShaderNode.get_input_port_default_value
     */
    fun getInputPortDefaultValue(port: Int): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetVariantScalar(Binds.getInputPortDefaultValueBind, segment, port)
    }

    /**
     * Removes the default value of the input `port`.
     *
     * Generated from Godot docs: VisualShaderNode.remove_input_port_default_value
     */
    fun removeInputPortDefaultValue(port: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.removeInputPortDefaultValueBind, segment, port)
    }

    /**
     * Clears the default input ports value.
     *
     * Generated from Godot docs: VisualShaderNode.clear_default_input_values
     */
    fun clearDefaultInputValues() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.clearDefaultInputValuesBind, segment)
    }

    /**
     * Sets the default input ports values using an `Array` of the form `[index0, value0, index1,
     * value1, ...]`. For example: `[0, Vector3(0, 0, 0), 1, Vector3(0, 0, 0)]`.
     *
     * Generated from Godot docs: VisualShaderNode.set_default_input_values
     */
    fun setDefaultInputValues(values: List<Any?>) {
        checkOpen()
        ObjectCalls.ptrcallWithArrayArg(Binds.setDefaultInputValuesBind, segment, values)
    }

    /**
     * Returns an `Array` containing default values for all of the input ports of the node in the form
     * `[index0, value0, index1, value1, ...]`.
     *
     * Generated from Godot docs: VisualShaderNode.get_default_input_values
     */
    fun getDefaultInputValues(): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetArray(Binds.getDefaultInputValuesBind, segment)
    }

    /**
     * Represents the index of the frame this node is linked to. If set to `-1` the node is not linked
     * to any frame.
     *
     * Generated from Godot docs: VisualShaderNode.set_frame
     */
    fun setFrame(frame: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setFrameBind, segment, frame)
    }

    /**
     * Represents the index of the frame this node is linked to. If set to `-1` the node is not linked
     * to any frame.
     *
     * Generated from Godot docs: VisualShaderNode.get_frame
     */
    fun getFrame(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getFrameBind, segment)
    }

    /**
     * Godot's `VisualShaderNode.PortType` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`VisualShaderNode.PortType.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNode.PortType
     */
    @JvmInline
    value class PortType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Floating-point scalar. Translated to `float` type in shader code.
             *
             * Generated from Godot docs: VisualShaderNode.PORT_TYPE_SCALAR
             */
            val SCALAR: PortType get() = PortType(0L)
            /**
             * Integer scalar. Translated to `int` type in shader code.
             *
             * Generated from Godot docs: VisualShaderNode.PORT_TYPE_SCALAR_INT
             */
            val SCALAR_INT: PortType get() = PortType(1L)
            /**
             * Unsigned integer scalar. Translated to `uint` type in shader code.
             *
             * Generated from Godot docs: VisualShaderNode.PORT_TYPE_SCALAR_UINT
             */
            val SCALAR_UINT: PortType get() = PortType(2L)
            /**
             * 2D vector of floating-point values. Translated to `vec2` type in shader code.
             *
             * Generated from Godot docs: VisualShaderNode.PORT_TYPE_VECTOR_2D
             */
            val VECTOR_2D: PortType get() = PortType(3L)
            /**
             * 3D vector of floating-point values. Translated to `vec3` type in shader code.
             *
             * Generated from Godot docs: VisualShaderNode.PORT_TYPE_VECTOR_3D
             */
            val VECTOR_3D: PortType get() = PortType(4L)
            /**
             * 4D vector of floating-point values. Translated to `vec4` type in shader code.
             *
             * Generated from Godot docs: VisualShaderNode.PORT_TYPE_VECTOR_4D
             */
            val VECTOR_4D: PortType get() = PortType(5L)
            /**
             * Boolean type. Translated to `bool` type in shader code.
             *
             * Generated from Godot docs: VisualShaderNode.PORT_TYPE_BOOLEAN
             */
            val BOOLEAN: PortType get() = PortType(6L)
            /**
             * Transform type. Translated to `mat4` type in shader code.
             *
             * Generated from Godot docs: VisualShaderNode.PORT_TYPE_TRANSFORM
             */
            val TRANSFORM: PortType get() = PortType(7L)
            /**
             * Sampler type. Translated to reference of sampler uniform in shader code. Can only be used for
             * input ports in non-uniform nodes.
             *
             * Generated from Godot docs: VisualShaderNode.PORT_TYPE_SAMPLER
             */
            val SAMPLER: PortType get() = PortType(8L)
            /**
             * Represents the size of the `PortType` enum.
             *
             * Generated from Godot docs: VisualShaderNode.PORT_TYPE_MAX
             */
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
