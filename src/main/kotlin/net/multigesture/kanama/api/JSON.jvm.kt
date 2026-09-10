package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for JSON (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP JSON waits on: ptrcallWithVariantAndBoolArgRetVariantScalar, ptrcallWithVariantArg,
//   ptrcallWithVariantStringTwoBoolArgsRetString
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Converts a `Variant` var to JSON text and returns the result. Useful for serializing data to
 * store or send over the network. Note: The JSON specification does not define integer or float
 * types, but only a number type. Therefore, converting a Variant to JSON text will convert all
 * numerical values to `float` types. Note: If `full_precision` is `true`, when stringifying
 * floats, the unreliable digits are stringified in addition to the reliable digits to guarantee
 * exact decoding. The `indent` parameter controls if and how something is indented; its contents
 * will be used where there should be an indent in the output. Even spaces like `" "` will work.
 * `\t` and `\n` can also be used for a tab indent, or to make a newline for each indent
 * respectively. Warning: Non-finite numbers are not supported in JSON. Any occurrences of
 * `@GDScript.INF` will be replaced with `1e99999`, and negative `@GDScript.INF` will be replaced
 * with `-1e99999`, but they will be interpreted correctly as infinity by most JSON parsers.
 * `@GDScript.NAN` will be replaced with `null`, and it will not be interpreted as NaN in JSON
 * parsers. If you expect non-finite numbers, consider passing your data through `from_native`
 * first. Example output:
 *
 * Generated from Godot docs: JSON.stringify
 */
fun JSON.Companion.stringify(data: Any?, indent: String = "", sortKeys: Boolean = true, fullPrecision: Boolean = false): String {
    return ObjectCalls.ptrcallWithVariantStringTwoBoolArgsRetString(stringifyBind, MemorySegment.NULL, data, indent, sortKeys, fullPrecision)
}

/**
 * Contains the parsed JSON data in `Variant` form.
 *
 * Generated from Godot docs: JSON.set_data
 */
fun JSON.setData(data: Any?) {
    checkOpen()
    ObjectCalls.ptrcallWithVariantArg(setDataBind, handle, data)
}

/**
 * Converts a native engine type to a JSON-compliant value. By default, objects are ignored for
 * security reasons, unless `full_objects` is `true`. You can convert a native value to a JSON
 * string like this:
 *
 * Generated from Godot docs: JSON.from_native
 */
fun JSON.Companion.fromNative(variant: Any?, fullObjects: Boolean = false): Any? {
    return ObjectCalls.ptrcallWithVariantAndBoolArgRetVariantScalar(fromNativeBind, MemorySegment.NULL, variant, fullObjects)
}

/**
 * Converts a JSON-compliant value that was created with `from_native` back to native engine types.
 * By default, objects are ignored for security reasons, unless `allow_objects` is `true`. You can
 * convert a JSON string back to a native value like this:
 *
 * Generated from Godot docs: JSON.to_native
 */
fun JSON.Companion.toNative(json: Any?, allowObjects: Boolean = false): Any? {
    return ObjectCalls.ptrcallWithVariantAndBoolArgRetVariantScalar(toNativeBind, MemorySegment.NULL, json, allowObjects)
}

private const val STRINGIFY_HASH = 462733549L
private val stringifyBind by lazy {
    ObjectCalls.getMethodBind("JSON", "stringify", STRINGIFY_HASH)
}

private const val SET_DATA_HASH = 1114965689L
private val setDataBind by lazy {
    ObjectCalls.getMethodBind("JSON", "set_data", SET_DATA_HASH)
}

private const val FROM_NATIVE_HASH = 2963479484L
private val fromNativeBind by lazy {
    ObjectCalls.getMethodBind("JSON", "from_native", FROM_NATIVE_HASH)
}

private const val TO_NATIVE_HASH = 2963479484L
private val toNativeBind by lazy {
    ObjectCalls.getMethodBind("JSON", "to_native", TO_NATIVE_HASH)
}
