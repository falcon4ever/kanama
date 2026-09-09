package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

// GENERATED desktop/Android companion for RDUniform (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP RDUniform waits on: ptrcallNoArgsRetRIDList
// Index: docs/contributing/ios-shape-gap.md

/**
 * Returns an array of all ids currently bound to the uniform.
 *
 * Generated from Godot docs: RDUniform.get_ids
 */
fun RDUniform.getIds(): List<RID> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetRIDList(getIdsBind, handle)
}

private const val GET_IDS_HASH = 3995934104L
private val getIdsBind by lazy {
    ObjectCalls.getMethodBind("RDUniform", "get_ids", GET_IDS_HASH)
}
