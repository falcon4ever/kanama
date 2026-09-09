package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector4

// GENERATED desktop/Android companion for VisualShaderNodeVec4Parameter (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP VisualShaderNodeVec4Parameter waits on: ptrcallNoArgsRetVector4,
//   ptrcallWithVector4Arg
// Index: docs/contributing/ios-shape-gap.md

fun VisualShaderNodeVec4Parameter.setDefaultValue(value: Vector4) {
    checkOpen()
    ObjectCalls.ptrcallWithVector4Arg(setDefaultValueBind, handle, value)
}

fun VisualShaderNodeVec4Parameter.getDefaultValue(): Vector4 {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetVector4(getDefaultValueBind, handle)
}

var VisualShaderNodeVec4Parameter.defaultValue: Vector4
    @JvmName("defaultValueProperty")
    get() = getDefaultValue()
    @JvmName("setDefaultValueProperty")
    set(value) = setDefaultValue(value)

private const val SET_DEFAULT_VALUE_HASH = 643568085L
private val setDefaultValueBind by lazy {
    ObjectCalls.getMethodBind("VisualShaderNodeVec4Parameter", "set_default_value", SET_DEFAULT_VALUE_HASH)
}

private const val GET_DEFAULT_VALUE_HASH = 2435802345L
private val getDefaultValueBind by lazy {
    ObjectCalls.getMethodBind("VisualShaderNodeVec4Parameter", "get_default_value", GET_DEFAULT_VALUE_HASH)
}
