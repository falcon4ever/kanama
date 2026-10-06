package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeBillboard
 */
class VisualShaderNodeBillboard(handle: GodotHandle) : VisualShaderNode(handle) {
    var billboardType: VisualShaderNodeBillboard.BillboardType
        @JvmName("billboardTypeProperty")
        get() = getBillboardType()
        @JvmName("setBillboardTypeProperty")
        set(value) = setBillboardType(value)

    var keepScale: Boolean
        @JvmName("keepScaleProperty")
        get() = isKeepScaleEnabled()
        @JvmName("setKeepScaleProperty")
        set(value) = setKeepScaleEnabled(value)

    fun setBillboardType(billboardType: VisualShaderNodeBillboard.BillboardType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setBillboardTypeBind, segment, billboardType.value)
    }

    fun getBillboardType(): VisualShaderNodeBillboard.BillboardType {
        checkOpen()
        return VisualShaderNodeBillboard.BillboardType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getBillboardTypeBind, segment))
    }

    fun setKeepScaleEnabled(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setKeepScaleEnabledBind, segment, enabled)
    }

    fun isKeepScaleEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isKeepScaleEnabledBind, segment)
    }

    @JvmInline
    value class BillboardType(override val value: Long) : GodotEnumValue {
        companion object {
            val DISABLED: BillboardType get() = BillboardType(0L)
            val ENABLED: BillboardType get() = BillboardType(1L)
            val FIXED_Y: BillboardType get() = BillboardType(2L)
            val PARTICLES: BillboardType get() = BillboardType(3L)
            val MAX: BillboardType get() = BillboardType(4L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeBillboard? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeBillboard? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeBillboard(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeBillboard? =
            if (handle.address() == 0L) null else VisualShaderNodeBillboard(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_BILLBOARD_TYPE_HASH = 1227463289L
        @JvmField
        val setBillboardTypeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeBillboard", "set_billboard_type", SET_BILLBOARD_TYPE_HASH)

        private const val GET_BILLBOARD_TYPE_HASH = 3724188517L
        @JvmField
        val getBillboardTypeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeBillboard", "get_billboard_type", GET_BILLBOARD_TYPE_HASH)

        private const val SET_KEEP_SCALE_ENABLED_HASH = 2586408642L
        @JvmField
        val setKeepScaleEnabledBind =
            ObjectCalls.getMethodBind("VisualShaderNodeBillboard", "set_keep_scale_enabled", SET_KEEP_SCALE_ENABLED_HASH)

        private const val IS_KEEP_SCALE_ENABLED_HASH = 36873697L
        @JvmField
        val isKeepScaleEnabledBind =
            ObjectCalls.getMethodBind("VisualShaderNodeBillboard", "is_keep_scale_enabled", IS_KEEP_SCALE_ENABLED_HASH)
    }
}
