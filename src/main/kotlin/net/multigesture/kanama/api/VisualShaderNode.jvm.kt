package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for VisualShaderNode (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP VisualShaderNode waits on: ptrcallWithArrayArg, ptrcallWithIntAndTwoVariantArgs,
//   ptrcallWithIntArgRetVariantScalar
// Index: docs/reference/generated/ios-shape-gap.md

fun VisualShaderNode.setInputPortDefaultValue(port: Int, value: Any?, prevValue: Any? = null) {
    checkOpen()
    ObjectCalls.ptrcallWithIntAndTwoVariantArgs(setInputPortDefaultValueBind, handle, port, value, prevValue)
}

fun VisualShaderNode.getInputPortDefaultValue(port: Int): Any? {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetVariantScalar(getInputPortDefaultValueBind, handle, port)
}

fun VisualShaderNode.setDefaultInputValues(values: List<Any?>) {
    checkOpen()
    ObjectCalls.ptrcallWithArrayArg(setDefaultInputValuesBind, handle, values)
}

private const val SET_INPUT_PORT_DEFAULT_VALUE_HASH = 150923387L
private val setInputPortDefaultValueBind by lazy {
    ObjectCalls.getMethodBind("VisualShaderNode", "set_input_port_default_value", SET_INPUT_PORT_DEFAULT_VALUE_HASH)
}

private const val GET_INPUT_PORT_DEFAULT_VALUE_HASH = 4227898402L
private val getInputPortDefaultValueBind by lazy {
    ObjectCalls.getMethodBind("VisualShaderNode", "get_input_port_default_value", GET_INPUT_PORT_DEFAULT_VALUE_HASH)
}

private const val SET_DEFAULT_INPUT_VALUES_HASH = 381264803L
private val setDefaultInputValuesBind by lazy {
    ObjectCalls.getMethodBind("VisualShaderNode", "set_default_input_values", SET_DEFAULT_INPUT_VALUES_HASH)
}
