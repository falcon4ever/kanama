package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for EditorFeatureProfile (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP EditorFeatureProfile waits on: ptrcallWithLongArgRetString
// Index: docs/contributing/ios-shape-gap.md

/**
 * Returns the specified `feature`'s human-readable name.
 *
 * Generated from Godot docs: EditorFeatureProfile.get_feature_name
 */
fun EditorFeatureProfile.getFeatureName(feature: Long): String {
    checkOpen()
    return ObjectCalls.ptrcallWithLongArgRetString(getFeatureNameBind, handle, feature)
}

private const val GET_FEATURE_NAME_HASH = 3401335809L
private val getFeatureNameBind by lazy {
    ObjectCalls.getMethodBind("EditorFeatureProfile", "get_feature_name", GET_FEATURE_NAME_HASH)
}
