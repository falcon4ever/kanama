package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for Expression (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP Expression waits on: ptrcallWithArrayObjectTwoBoolArgsRetVariantScalar,
//   ptrcallWithStringAndPackedStringListArgRetLong
// Index: docs/contributing/ios-shape-gap.md

/**
 * Parses the expression and returns an `Error` code. You can optionally specify names of variables
 * that may appear in the expression with `input_names`, so that you can bind them when it gets
 * executed.
 *
 * Generated from Godot docs: Expression.parse
 */
fun Expression.parse(expression: String, inputNames: List<String>): Long {
    checkOpen()
    return ObjectCalls.ptrcallWithStringAndPackedStringListArgRetLong(parseBind, handle, expression, inputNames)
}

/**
 * Executes the expression that was previously parsed by `parse` and returns the result. Before you
 * use the returned object, you should check if the method failed by calling `has_execute_failed`.
 * If you defined input variables in `parse`, you can specify their values in the inputs array, in
 * the same order.
 *
 * Generated from Godot docs: Expression.execute
 */
fun Expression.execute(inputs: List<Any?> = emptyList(), baseInstance: GodotObject, showError: Boolean = true, constCallsOnly: Boolean = false): Any? {
    checkOpen()
    return ObjectCalls.ptrcallWithArrayObjectTwoBoolArgsRetVariantScalar(executeBind, handle, inputs, baseInstance.handle, showError, constCallsOnly)
}

private const val PARSE_HASH = 3069722906L
private val parseBind by lazy {
    ObjectCalls.getMethodBind("Expression", "parse", PARSE_HASH)
}

private const val EXECUTE_HASH = 3712471238L
private val executeBind by lazy {
    ObjectCalls.getMethodBind("Expression", "execute", EXECUTE_HASH)
}
