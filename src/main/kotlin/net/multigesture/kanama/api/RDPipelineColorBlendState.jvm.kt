package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for RDPipelineColorBlendState (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP RDPipelineColorBlendState waits on: ptrcallWithObjectListArg
// Index: docs/contributing/ios-shape-gap.md

/**
 * The attachments that are blended together.
 *
 * Generated from Godot docs: RDPipelineColorBlendState.set_attachments
 */
fun RDPipelineColorBlendState.setAttachments(attachments: List<RDPipelineColorBlendStateAttachment>) {
    checkOpen()
    ObjectCalls.ptrcallWithObjectListArg(setAttachmentsBind, handle, attachments)
}

private const val SET_ATTACHMENTS_HASH = 381264803L
private val setAttachmentsBind by lazy {
    ObjectCalls.getMethodBind("RDPipelineColorBlendState", "set_attachments", SET_ATTACHMENTS_HASH)
}
