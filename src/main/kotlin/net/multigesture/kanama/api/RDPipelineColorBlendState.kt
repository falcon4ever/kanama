package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color

/**
 * Pipeline color blend state (used by `RenderingDevice`).
 *
 * Generated from Godot docs: RDPipelineColorBlendState
 */
class RDPipelineColorBlendState(handle: MemorySegment) : RefCounted(handle) {
    var enableLogicOp: Boolean
        @JvmName("enableLogicOpProperty")
        get() = getEnableLogicOp()
        @JvmName("setEnableLogicOpProperty")
        set(value) = setEnableLogicOp(value)

    var logicOp: Long
        @JvmName("logicOpProperty")
        get() = getLogicOp()
        @JvmName("setLogicOpProperty")
        set(value) = setLogicOp(value)

    var blendConstant: Color
        @JvmName("blendConstantProperty")
        get() = getBlendConstant()
        @JvmName("setBlendConstantProperty")
        set(value) = setBlendConstant(value)

    var attachments: List<RDPipelineColorBlendStateAttachment>
        @JvmName("attachmentsProperty")
        get() = getAttachments()
        @JvmName("setAttachmentsProperty")
        set(value) = setAttachments(value)

    fun setEnableLogicOp(pMember: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEnableLogicOpBind, handle, pMember)
    }

    fun getEnableLogicOp(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getEnableLogicOpBind, handle)
    }

    fun setLogicOp(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setLogicOpBind, handle, pMember)
    }

    fun getLogicOp(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getLogicOpBind, handle)
    }

    fun setBlendConstant(pMember: Color) {
        ObjectCalls.ptrcallWithColorArg(setBlendConstantBind, handle, pMember)
    }

    fun getBlendConstant(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getBlendConstantBind, handle)
    }

    fun setAttachments(attachments: List<RDPipelineColorBlendStateAttachment>) {
        ObjectCalls.ptrcallWithObjectListArg(setAttachmentsBind, handle, attachments)
    }

    fun getAttachments(): List<RDPipelineColorBlendStateAttachment> {
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getAttachmentsBind, handle, RDPipelineColorBlendStateAttachment::fromHandle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): RDPipelineColorBlendState? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RDPipelineColorBlendState? =
            if (handle.address() == 0L) null else RDPipelineColorBlendState(handle)

        private const val SET_ENABLE_LOGIC_OP_HASH = 2586408642L
        private val setEnableLogicOpBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineColorBlendState", "set_enable_logic_op", SET_ENABLE_LOGIC_OP_HASH)
        }

        private const val GET_ENABLE_LOGIC_OP_HASH = 36873697L
        private val getEnableLogicOpBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineColorBlendState", "get_enable_logic_op", GET_ENABLE_LOGIC_OP_HASH)
        }

        private const val SET_LOGIC_OP_HASH = 3610841058L
        private val setLogicOpBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineColorBlendState", "set_logic_op", SET_LOGIC_OP_HASH)
        }

        private const val GET_LOGIC_OP_HASH = 988254690L
        private val getLogicOpBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineColorBlendState", "get_logic_op", GET_LOGIC_OP_HASH)
        }

        private const val SET_BLEND_CONSTANT_HASH = 2920490490L
        private val setBlendConstantBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineColorBlendState", "set_blend_constant", SET_BLEND_CONSTANT_HASH)
        }

        private const val GET_BLEND_CONSTANT_HASH = 3444240500L
        private val getBlendConstantBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineColorBlendState", "get_blend_constant", GET_BLEND_CONSTANT_HASH)
        }

        private const val SET_ATTACHMENTS_HASH = 381264803L
        private val setAttachmentsBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineColorBlendState", "set_attachments", SET_ATTACHMENTS_HASH)
        }

        private const val GET_ATTACHMENTS_HASH = 3995934104L
        private val getAttachmentsBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineColorBlendState", "get_attachments", GET_ATTACHMENTS_HASH)
        }
    }
}
