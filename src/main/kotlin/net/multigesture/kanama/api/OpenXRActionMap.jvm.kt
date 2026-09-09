package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for OpenXRActionMap (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP OpenXRActionMap waits on: ptrcallWithArrayArg
// Index: docs/contributing/ios-shape-gap.md

fun OpenXRActionMap.setActionSets(actionSets: List<Any?>) {
    checkOpen()
    ObjectCalls.ptrcallWithArrayArg(setActionSetsBind, handle, actionSets)
}

fun OpenXRActionMap.setInteractionProfiles(interactionProfiles: List<Any?>) {
    checkOpen()
    ObjectCalls.ptrcallWithArrayArg(setInteractionProfilesBind, handle, interactionProfiles)
}

private const val SET_ACTION_SETS_HASH = 381264803L
private val setActionSetsBind by lazy {
    ObjectCalls.getMethodBind("OpenXRActionMap", "set_action_sets", SET_ACTION_SETS_HASH)
}

private const val SET_INTERACTION_PROFILES_HASH = 381264803L
private val setInteractionProfilesBind by lazy {
    ObjectCalls.getMethodBind("OpenXRActionMap", "set_interaction_profiles", SET_INTERACTION_PROFILES_HASH)
}
