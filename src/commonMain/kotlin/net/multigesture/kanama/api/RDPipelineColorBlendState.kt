package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color

/**
 * Pipeline color blend state (used by `RenderingDevice`).
 *
 * Generated from Godot docs: RDPipelineColorBlendState
 */
class RDPipelineColorBlendState(handle: GodotHandle) : RefCounted(handle) {
    var enableLogicOp: Boolean
        @JvmName("enableLogicOpProperty")
        get() = getEnableLogicOp()
        @JvmName("setEnableLogicOpProperty")
        set(value) = setEnableLogicOp(value)

    var logicOp: RenderingDevice.LogicOperation
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

    /**
     * If `true`, performs the logic operation defined in `logic_op`.
     *
     * Generated from Godot docs: RDPipelineColorBlendState.set_enable_logic_op
     */
    fun setEnableLogicOp(pMember: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setEnableLogicOpBind, segment, pMember)
    }

    /**
     * If `true`, performs the logic operation defined in `logic_op`.
     *
     * Generated from Godot docs: RDPipelineColorBlendState.get_enable_logic_op
     */
    fun getEnableLogicOp(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getEnableLogicOpBind, segment)
    }

    /**
     * The logic operation to perform for blending. Only effective if `enable_logic_op` is `true`.
     *
     * Generated from Godot docs: RDPipelineColorBlendState.set_logic_op
     */
    fun setLogicOp(pMember: RenderingDevice.LogicOperation) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setLogicOpBind, segment, pMember.value)
    }

    /**
     * The logic operation to perform for blending. Only effective if `enable_logic_op` is `true`.
     *
     * Generated from Godot docs: RDPipelineColorBlendState.get_logic_op
     */
    fun getLogicOp(): RenderingDevice.LogicOperation {
        checkOpen()
        return RenderingDevice.LogicOperation(ObjectCalls.ptrcallNoArgsRetLong(Binds.getLogicOpBind, segment))
    }

    /**
     * The constant color to blend with. See also `RenderingDevice.draw_list_set_blend_constants`.
     *
     * Generated from Godot docs: RDPipelineColorBlendState.set_blend_constant
     */
    fun setBlendConstant(pMember: Color) {
        checkOpen()
        ObjectCalls.ptrcallWithColorArg(Binds.setBlendConstantBind, segment, pMember)
    }

    /**
     * The constant color to blend with. See also `RenderingDevice.draw_list_set_blend_constants`.
     *
     * Generated from Godot docs: RDPipelineColorBlendState.get_blend_constant
     */
    fun getBlendConstant(): Color {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetColor(Binds.getBlendConstantBind, segment)
    }

    /**
     * The attachments that are blended together.
     *
     * Generated from Godot docs: RDPipelineColorBlendState.set_attachments
     */
    fun setAttachments(attachments: List<RDPipelineColorBlendStateAttachment>) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectListArg(Binds.setAttachmentsBind, segment, attachments)
    }

    /**
     * The attachments that are blended together.
     *
     * Generated from Godot docs: RDPipelineColorBlendState.get_attachments
     */
    fun getAttachments(): List<RDPipelineColorBlendStateAttachment> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(Binds.getAttachmentsBind, segment, RDPipelineColorBlendStateAttachment::wrapBorrowed)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): RDPipelineColorBlendState? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): RDPipelineColorBlendState? =
            if (handle.address() == 0L) null else RefCounted.owned(RDPipelineColorBlendState(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): RDPipelineColorBlendState? =
            if (handle.address() == 0L) null else RDPipelineColorBlendState(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_ENABLE_LOGIC_OP_HASH = 2586408642L
        @JvmField
        val setEnableLogicOpBind =
            ObjectCalls.getMethodBind("RDPipelineColorBlendState", "set_enable_logic_op", SET_ENABLE_LOGIC_OP_HASH)

        private const val GET_ENABLE_LOGIC_OP_HASH = 36873697L
        @JvmField
        val getEnableLogicOpBind =
            ObjectCalls.getMethodBind("RDPipelineColorBlendState", "get_enable_logic_op", GET_ENABLE_LOGIC_OP_HASH)

        private const val SET_LOGIC_OP_HASH = 3610841058L
        @JvmField
        val setLogicOpBind =
            ObjectCalls.getMethodBind("RDPipelineColorBlendState", "set_logic_op", SET_LOGIC_OP_HASH)

        private const val GET_LOGIC_OP_HASH = 988254690L
        @JvmField
        val getLogicOpBind =
            ObjectCalls.getMethodBind("RDPipelineColorBlendState", "get_logic_op", GET_LOGIC_OP_HASH)

        private const val SET_BLEND_CONSTANT_HASH = 2920490490L
        @JvmField
        val setBlendConstantBind =
            ObjectCalls.getMethodBind("RDPipelineColorBlendState", "set_blend_constant", SET_BLEND_CONSTANT_HASH)

        private const val GET_BLEND_CONSTANT_HASH = 3444240500L
        @JvmField
        val getBlendConstantBind =
            ObjectCalls.getMethodBind("RDPipelineColorBlendState", "get_blend_constant", GET_BLEND_CONSTANT_HASH)

        private const val SET_ATTACHMENTS_HASH = 381264803L
        @JvmField
        val setAttachmentsBind =
            ObjectCalls.getMethodBind("RDPipelineColorBlendState", "set_attachments", SET_ATTACHMENTS_HASH)

        private const val GET_ATTACHMENTS_HASH = 3995934104L
        @JvmField
        val getAttachmentsBind =
            ObjectCalls.getMethodBind("RDPipelineColorBlendState", "get_attachments", GET_ATTACHMENTS_HASH)
    }
}
