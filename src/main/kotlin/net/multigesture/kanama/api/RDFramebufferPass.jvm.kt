package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for RDFramebufferPass (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP RDFramebufferPass waits on: ptrcallWithPackedInt32ListArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Color attachments in order starting from 0. If this attachment is not used by the shader, pass
 * ATTACHMENT_UNUSED to skip.
 *
 * Generated from Godot docs: RDFramebufferPass.set_color_attachments
 */
fun RDFramebufferPass.setColorAttachments(pMember: List<Int>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedInt32ListArg(setColorAttachmentsBind, handle, pMember)
}

/**
 * Used for multipass framebuffers (more than one render pass). Converts an attachment to an input.
 * Make sure to also supply it properly in the `RDUniform` for the uniform set.
 *
 * Generated from Godot docs: RDFramebufferPass.set_input_attachments
 */
fun RDFramebufferPass.setInputAttachments(pMember: List<Int>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedInt32ListArg(setInputAttachmentsBind, handle, pMember)
}

/**
 * If the color attachments are multisampled, non-multisampled resolve attachments can be provided.
 *
 * Generated from Godot docs: RDFramebufferPass.set_resolve_attachments
 */
fun RDFramebufferPass.setResolveAttachments(pMember: List<Int>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedInt32ListArg(setResolveAttachmentsBind, handle, pMember)
}

/**
 * Attachments to preserve in this pass (otherwise they are erased).
 *
 * Generated from Godot docs: RDFramebufferPass.set_preserve_attachments
 */
fun RDFramebufferPass.setPreserveAttachments(pMember: List<Int>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedInt32ListArg(setPreserveAttachmentsBind, handle, pMember)
}

private const val SET_COLOR_ATTACHMENTS_HASH = 3614634198L
private val setColorAttachmentsBind by lazy {
    ObjectCalls.getMethodBind("RDFramebufferPass", "set_color_attachments", SET_COLOR_ATTACHMENTS_HASH)
}

private const val SET_INPUT_ATTACHMENTS_HASH = 3614634198L
private val setInputAttachmentsBind by lazy {
    ObjectCalls.getMethodBind("RDFramebufferPass", "set_input_attachments", SET_INPUT_ATTACHMENTS_HASH)
}

private const val SET_RESOLVE_ATTACHMENTS_HASH = 3614634198L
private val setResolveAttachmentsBind by lazy {
    ObjectCalls.getMethodBind("RDFramebufferPass", "set_resolve_attachments", SET_RESOLVE_ATTACHMENTS_HASH)
}

private const val SET_PRESERVE_ATTACHMENTS_HASH = 3614634198L
private val setPreserveAttachmentsBind by lazy {
    ObjectCalls.getMethodBind("RDFramebufferPass", "set_preserve_attachments", SET_PRESERVE_ATTACHMENTS_HASH)
}
