package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Framebuffer pass attachment description (used by `RenderingDevice`).
 *
 * Generated from Godot docs: RDFramebufferPass
 */
class RDFramebufferPass(handle: MemorySegment) : RefCounted(handle) {
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

    fun setColorAttachments(pMember: List<Int>) {
        ObjectCalls.ptrcallWithPackedInt32ListArg(setColorAttachmentsBind, handle, pMember)
    }

    fun getColorAttachments(): List<Int> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getColorAttachmentsBind, handle)
    }

    fun setInputAttachments(pMember: List<Int>) {
        ObjectCalls.ptrcallWithPackedInt32ListArg(setInputAttachmentsBind, handle, pMember)
    }

    fun getInputAttachments(): List<Int> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getInputAttachmentsBind, handle)
    }

    fun setResolveAttachments(pMember: List<Int>) {
        ObjectCalls.ptrcallWithPackedInt32ListArg(setResolveAttachmentsBind, handle, pMember)
    }

    fun getResolveAttachments(): List<Int> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getResolveAttachmentsBind, handle)
    }

    fun setPreserveAttachments(pMember: List<Int>) {
        ObjectCalls.ptrcallWithPackedInt32ListArg(setPreserveAttachmentsBind, handle, pMember)
    }

    fun getPreserveAttachments(): List<Int> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getPreserveAttachmentsBind, handle)
    }

    fun setDepthAttachment(pMember: Int) {
        ObjectCalls.ptrcallWithIntArg(setDepthAttachmentBind, handle, pMember)
    }

    fun getDepthAttachment(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getDepthAttachmentBind, handle)
    }

    companion object {
        const val ATTACHMENT_UNUSED: Long = -1L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): RDFramebufferPass? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RDFramebufferPass? =
            if (handle.address() == 0L) null else RDFramebufferPass(handle)

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
