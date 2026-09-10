package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for Marshalls (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP Marshalls waits on: ptrcallWithByteArrayArgRetString,
//   ptrcallWithStringArgRetByteArray, ptrcallWithVariantAndBoolArgRetString
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns a Base64-encoded string of the `Variant` `variant`. If `full_objects` is `true`,
 * encoding objects is allowed (and can potentially include code). Internally, this uses the same
 * encoding mechanism as the `@GlobalScope.var_to_bytes` method.
 *
 * Generated from Godot docs: Marshalls.variant_to_base64
 */
fun Marshalls.variantToBase64(variant: Any?, fullObjects: Boolean = false): String {
    return ObjectCalls.ptrcallWithVariantAndBoolArgRetString(variantToBase64Bind, marshallsSingleton, variant, fullObjects)
}

/**
 * Returns a Base64-encoded string of a given `PackedByteArray`.
 *
 * Generated from Godot docs: Marshalls.raw_to_base64
 */
fun Marshalls.rawToBase64(array: ByteArray): String {
    return ObjectCalls.ptrcallWithByteArrayArgRetString(rawToBase64Bind, marshallsSingleton, array)
}

/**
 * Returns a decoded `PackedByteArray` corresponding to the Base64-encoded string `base64_str`.
 *
 * Generated from Godot docs: Marshalls.base64_to_raw
 */
fun Marshalls.base64ToRaw(base64Str: String): ByteArray {
    return ObjectCalls.ptrcallWithStringArgRetByteArray(base64ToRawBind, marshallsSingleton, base64Str)
}

private val marshallsSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("Marshalls")
}

private const val VARIANT_TO_BASE64_HASH = 3876248563L
private val variantToBase64Bind by lazy {
    ObjectCalls.getMethodBind("Marshalls", "variant_to_base64", VARIANT_TO_BASE64_HASH)
}

private const val RAW_TO_BASE64_HASH = 3999417757L
private val rawToBase64Bind by lazy {
    ObjectCalls.getMethodBind("Marshalls", "raw_to_base64", RAW_TO_BASE64_HASH)
}

private const val BASE64_TO_RAW_HASH = 659035735L
private val base64ToRawBind by lazy {
    ObjectCalls.getMethodBind("Marshalls", "base64_to_raw", BASE64_TO_RAW_HASH)
}
