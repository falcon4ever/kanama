package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for VisualShaderNodeFrame (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP VisualShaderNodeFrame waits on: ptrcallWithPackedInt32ListArg
// Index: docs/contributing/ios-shape-gap.md

fun VisualShaderNodeFrame.setAttachedNodes(attachedNodes: List<Int>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedInt32ListArg(setAttachedNodesBind, handle, attachedNodes)
}

private const val SET_ATTACHED_NODES_HASH = 3614634198L
private val setAttachedNodesBind by lazy {
    ObjectCalls.getMethodBind("VisualShaderNodeFrame", "set_attached_nodes", SET_ATTACHED_NODES_HASH)
}
