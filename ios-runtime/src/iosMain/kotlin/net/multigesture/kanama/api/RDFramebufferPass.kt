package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
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

    fun getColorAttachments(): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getColorAttachmentsBind, handle)
    }

    fun getInputAttachments(): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getInputAttachmentsBind, handle)
    }

    fun getResolveAttachments(): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getResolveAttachmentsBind, handle)
    }

    fun getPreserveAttachments(): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getPreserveAttachmentsBind, handle)
    }

    fun setDepthAttachment(pMember: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setDepthAttachmentBind, handle, pMember)
    }

    fun getDepthAttachment(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getDepthAttachmentBind, handle)
    }

    companion object {
        const val ATTACHMENT_UNUSED: Long = -1L

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
