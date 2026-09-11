package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for StreamPeer (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP StreamPeer waits on: ptrcallWithVariantAndBoolArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Puts a Variant into the stream. If `full_objects` is `true` encoding objects is allowed (and can
 * potentially include code). Internally, this uses the same encoding mechanism as the
 * `@GlobalScope.var_to_bytes` method.
 *
 * Generated from Godot docs: StreamPeer.put_var
 */
fun StreamPeer.putVar(value: Any?, fullObjects: Boolean = false) {
    checkOpen()
    ObjectCalls.ptrcallWithVariantAndBoolArg(putVarBind, handle, value, fullObjects)
}

private const val PUT_VAR_HASH = 738511890L
private val putVarBind by lazy {
    ObjectCalls.getMethodBind("StreamPeer", "put_var", PUT_VAR_HASH)
}
