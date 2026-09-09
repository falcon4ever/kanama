package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Framebuffer pass attachment description (used by `RenderingDevice`).
 *
 * Generated from Godot docs: RDFramebufferPass
 */
class RDFramebufferPass(handle: MemorySegment) : RefCounted(handle) {
    val colorAttachments: List<Int>
        @JvmName("colorAttachmentsProperty")
        get() = getColorAttachments()

    val inputAttachments: List<Int>
        @JvmName("inputAttachmentsProperty")
        get() = getInputAttachments()

    val resolveAttachments: List<Int>
        @JvmName("resolveAttachmentsProperty")
        get() = getResolveAttachments()

    val preserveAttachments: List<Int>
        @JvmName("preserveAttachmentsProperty")
        get() = getPreserveAttachments()

    var depthAttachment: Int
        @JvmName("depthAttachmentProperty")
        get() = getDepthAttachment()
        @JvmName("setDepthAttachmentProperty")
        set(value) = setDepthAttachment(value)

    /**
     * Color attachments in order starting from 0. If this attachment is not used by the shader, pass
     * ATTACHMENT_UNUSED to skip.
     *
     * Generated from Godot docs: RDFramebufferPass.get_color_attachments
     */
    fun getColorAttachments(): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getColorAttachmentsBind, handle)
    }

    /**
     * Used for multipass framebuffers (more than one render pass). Converts an attachment to an input.
     * Make sure to also supply it properly in the `RDUniform` for the uniform set.
     *
     * Generated from Godot docs: RDFramebufferPass.get_input_attachments
     */
    fun getInputAttachments(): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getInputAttachmentsBind, handle)
    }

    /**
     * If the color attachments are multisampled, non-multisampled resolve attachments can be provided.
     *
     * Generated from Godot docs: RDFramebufferPass.get_resolve_attachments
     */
    fun getResolveAttachments(): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getResolveAttachmentsBind, handle)
    }

    /**
     * Attachments to preserve in this pass (otherwise they are erased).
     *
     * Generated from Godot docs: RDFramebufferPass.get_preserve_attachments
     */
    fun getPreserveAttachments(): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getPreserveAttachmentsBind, handle)
    }

    /**
     * Depth attachment. ATTACHMENT_UNUSED should be used if no depth buffer is required for this pass.
     *
     * Generated from Godot docs: RDFramebufferPass.set_depth_attachment
     */
    fun setDepthAttachment(pMember: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setDepthAttachmentBind, handle, pMember)
    }

    /**
     * Depth attachment. ATTACHMENT_UNUSED should be used if no depth buffer is required for this pass.
     *
     * Generated from Godot docs: RDFramebufferPass.get_depth_attachment
     */
    fun getDepthAttachment(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getDepthAttachmentBind, handle)
    }

    companion object {
        const val ATTACHMENT_UNUSED: Long = -1L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): RDFramebufferPass? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RDFramebufferPass? =
            if (handle.address() == 0L) null else RDFramebufferPass(handle)

        private const val GET_COLOR_ATTACHMENTS_HASH = 1930428628L
        private val getColorAttachmentsBind by lazy {
            ObjectCalls.getMethodBind("RDFramebufferPass", "get_color_attachments", GET_COLOR_ATTACHMENTS_HASH)
        }

        private const val GET_INPUT_ATTACHMENTS_HASH = 1930428628L
        private val getInputAttachmentsBind by lazy {
            ObjectCalls.getMethodBind("RDFramebufferPass", "get_input_attachments", GET_INPUT_ATTACHMENTS_HASH)
        }

        private const val GET_RESOLVE_ATTACHMENTS_HASH = 1930428628L
        private val getResolveAttachmentsBind by lazy {
            ObjectCalls.getMethodBind("RDFramebufferPass", "get_resolve_attachments", GET_RESOLVE_ATTACHMENTS_HASH)
        }

        private const val GET_PRESERVE_ATTACHMENTS_HASH = 1930428628L
        private val getPreserveAttachmentsBind by lazy {
            ObjectCalls.getMethodBind("RDFramebufferPass", "get_preserve_attachments", GET_PRESERVE_ATTACHMENTS_HASH)
        }

        private const val SET_DEPTH_ATTACHMENT_HASH = 1286410249L
        private val setDepthAttachmentBind by lazy {
            ObjectCalls.getMethodBind("RDFramebufferPass", "set_depth_attachment", SET_DEPTH_ATTACHMENT_HASH)
        }

        private const val GET_DEPTH_ATTACHMENT_HASH = 3905245786L
        private val getDepthAttachmentBind by lazy {
            ObjectCalls.getMethodBind("RDFramebufferPass", "get_depth_attachment", GET_DEPTH_ATTACHMENT_HASH)
        }
    }
}
