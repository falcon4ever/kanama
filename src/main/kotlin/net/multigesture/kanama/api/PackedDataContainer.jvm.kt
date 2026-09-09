package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for PackedDataContainer (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP PackedDataContainer waits on: ptrcallWithVariantArgRetLong
// Index: docs/contributing/ios-shape-gap.md

/**
 * Packs the given container into a binary representation. The `value` must be either `Array` or
 * `Dictionary`, any other type will result in invalid data error. Note: Subsequent calls to this
 * method will overwrite the existing data.
 *
 * Generated from Godot docs: PackedDataContainer.pack
 */
fun PackedDataContainer.pack(value: Any?): Long {
    checkOpen()
    return ObjectCalls.ptrcallWithVariantArgRetLong(packBind, handle, value)
}

private const val PACK_HASH = 966674026L
private val packBind by lazy {
    ObjectCalls.getMethodBind("PackedDataContainer", "pack", PACK_HASH)
}
