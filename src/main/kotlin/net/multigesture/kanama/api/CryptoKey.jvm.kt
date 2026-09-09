package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for CryptoKey (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP CryptoKey waits on: ptrcallWithBoolArgRetString
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns a string containing the key in PEM format. If `public_only` is `true`, only the public
 * key will be included.
 *
 * Generated from Godot docs: CryptoKey.save_to_string
 */
fun CryptoKey.saveToString(publicOnly: Boolean = false): String {
    checkOpen()
    return ObjectCalls.ptrcallWithBoolArgRetString(saveToStringBind, handle, publicOnly)
}

private const val SAVE_TO_STRING_HASH = 32795936L
private val saveToStringBind by lazy {
    ObjectCalls.getMethodBind("CryptoKey", "save_to_string", SAVE_TO_STRING_HASH)
}
