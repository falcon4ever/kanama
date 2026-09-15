package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Framebuffer pass attachment description (used by `RenderingDevice`).
 *
 * Generated from Godot docs: RDFramebufferPass
 */
class RDFramebufferPass(handle: GodotHandle) : RefCounted(handle) {
    var colorAttachments: List<Int>
        @JvmName("colorAttachmentsProperty")
        get() = getColorAttachments()
        @JvmName("setColorAttachmentsProperty")
        set(value) = setColorAttachments(value)

    var inputAttachments: List<Int>
        @JvmName("inputAttachmentsProperty")
        get() = getInputAttachments()
        @JvmName("setInputAttachmentsProperty")
        set(value) = setInputAttachments(value)

    var resolveAttachments: List<Int>
        @JvmName("resolveAttachmentsProperty")
        get() = getResolveAttachments()
        @JvmName("setResolveAttachmentsProperty")
        set(value) = setResolveAttachments(value)

    var preserveAttachments: List<Int>
        @JvmName("preserveAttachmentsProperty")
        get() = getPreserveAttachments()
        @JvmName("setPreserveAttachmentsProperty")
        set(value) = setPreserveAttachments(value)

    var depthAttachment: Int
        @JvmName("depthAttachmentProperty")
        get() = getDepthAttachment()
        @JvmName("setDepthAttachmentProperty")
        set(value) = setDepthAttachment(value)

    /**
     * Color attachments in order starting from 0. If this attachment is not used by the shader, pass
     * ATTACHMENT_UNUSED to skip.
     *
     * Generated from Godot docs: RDFramebufferPass.set_color_attachments
     */
    fun setColorAttachments(pMember: List<Int>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedInt32ListArg(setColorAttachmentsBind, segment, pMember)
    }

    /**
     * Color attachments in order starting from 0. If this attachment is not used by the shader, pass
     * ATTACHMENT_UNUSED to skip.
     *
     * Generated from Godot docs: RDFramebufferPass.get_color_attachments
     */
    fun getColorAttachments(): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getColorAttachmentsBind, segment)
    }

    /**
     * Used for multipass framebuffers (more than one render pass). Converts an attachment to an input.
     * Make sure to also supply it properly in the `RDUniform` for the uniform set.
     *
     * Generated from Godot docs: RDFramebufferPass.set_input_attachments
     */
    fun setInputAttachments(pMember: List<Int>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedInt32ListArg(setInputAttachmentsBind, segment, pMember)
    }

    /**
     * Used for multipass framebuffers (more than one render pass). Converts an attachment to an input.
     * Make sure to also supply it properly in the `RDUniform` for the uniform set.
     *
     * Generated from Godot docs: RDFramebufferPass.get_input_attachments
     */
    fun getInputAttachments(): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getInputAttachmentsBind, segment)
    }

    /**
     * If the color attachments are multisampled, non-multisampled resolve attachments can be provided.
     *
     * Generated from Godot docs: RDFramebufferPass.set_resolve_attachments
     */
    fun setResolveAttachments(pMember: List<Int>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedInt32ListArg(setResolveAttachmentsBind, segment, pMember)
    }

    /**
     * If the color attachments are multisampled, non-multisampled resolve attachments can be provided.
     *
     * Generated from Godot docs: RDFramebufferPass.get_resolve_attachments
     */
    fun getResolveAttachments(): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getResolveAttachmentsBind, segment)
    }

    /**
     * Attachments to preserve in this pass (otherwise they are erased).
     *
     * Generated from Godot docs: RDFramebufferPass.set_preserve_attachments
     */
    fun setPreserveAttachments(pMember: List<Int>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedInt32ListArg(setPreserveAttachmentsBind, segment, pMember)
    }

    /**
     * Attachments to preserve in this pass (otherwise they are erased).
     *
     * Generated from Godot docs: RDFramebufferPass.get_preserve_attachments
     */
    fun getPreserveAttachments(): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getPreserveAttachmentsBind, segment)
    }

    /**
     * Depth attachment. ATTACHMENT_UNUSED should be used if no depth buffer is required for this pass.
     *
     * Generated from Godot docs: RDFramebufferPass.set_depth_attachment
     */
    fun setDepthAttachment(pMember: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setDepthAttachmentBind, segment, pMember)
    }

    /**
     * Depth attachment. ATTACHMENT_UNUSED should be used if no depth buffer is required for this pass.
     *
     * Generated from Godot docs: RDFramebufferPass.get_depth_attachment
     */
    fun getDepthAttachment(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getDepthAttachmentBind, segment)
    }

    companion object {
        const val ATTACHMENT_UNUSED: Long = -1L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): RDFramebufferPass? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): RDFramebufferPass? =
            if (handle.address() == 0L) null else RDFramebufferPass(GodotHandle(handle))

        private const val SET_COLOR_ATTACHMENTS_HASH = 3614634198L
        private val setColorAttachmentsBind by lazy {
            ObjectCalls.getMethodBind("RDFramebufferPass", "set_color_attachments", SET_COLOR_ATTACHMENTS_HASH)
        }

        private const val GET_COLOR_ATTACHMENTS_HASH = 1930428628L
        private val getColorAttachmentsBind by lazy {
            ObjectCalls.getMethodBind("RDFramebufferPass", "get_color_attachments", GET_COLOR_ATTACHMENTS_HASH)
        }

        private const val SET_INPUT_ATTACHMENTS_HASH = 3614634198L
        private val setInputAttachmentsBind by lazy {
            ObjectCalls.getMethodBind("RDFramebufferPass", "set_input_attachments", SET_INPUT_ATTACHMENTS_HASH)
        }

        private const val GET_INPUT_ATTACHMENTS_HASH = 1930428628L
        private val getInputAttachmentsBind by lazy {
            ObjectCalls.getMethodBind("RDFramebufferPass", "get_input_attachments", GET_INPUT_ATTACHMENTS_HASH)
        }

        private const val SET_RESOLVE_ATTACHMENTS_HASH = 3614634198L
        private val setResolveAttachmentsBind by lazy {
            ObjectCalls.getMethodBind("RDFramebufferPass", "set_resolve_attachments", SET_RESOLVE_ATTACHMENTS_HASH)
        }

        private const val GET_RESOLVE_ATTACHMENTS_HASH = 1930428628L
        private val getResolveAttachmentsBind by lazy {
            ObjectCalls.getMethodBind("RDFramebufferPass", "get_resolve_attachments", GET_RESOLVE_ATTACHMENTS_HASH)
        }

        private const val SET_PRESERVE_ATTACHMENTS_HASH = 3614634198L
        private val setPreserveAttachmentsBind by lazy {
            ObjectCalls.getMethodBind("RDFramebufferPass", "set_preserve_attachments", SET_PRESERVE_ATTACHMENTS_HASH)
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
