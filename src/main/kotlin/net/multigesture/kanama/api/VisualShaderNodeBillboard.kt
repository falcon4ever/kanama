package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeBillboard
 */
class VisualShaderNodeBillboard(handle: MemorySegment) : VisualShaderNode(handle) {
    var billboardType: Long
        @JvmName("billboardTypeProperty")
        get() = getBillboardType()
        @JvmName("setBillboardTypeProperty")
        set(value) = setBillboardType(value)

    var keepScale: Boolean
        @JvmName("keepScaleProperty")
        get() = isKeepScaleEnabled()
        @JvmName("setKeepScaleProperty")
        set(value) = setKeepScaleEnabled(value)

    fun setBillboardType(billboardType: Long) {
        ObjectCalls.ptrcallWithLongArg(setBillboardTypeBind, handle, billboardType)
    }

    fun getBillboardType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getBillboardTypeBind, handle)
    }

    fun setKeepScaleEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setKeepScaleEnabledBind, handle, enabled)
    }

    fun isKeepScaleEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isKeepScaleEnabledBind, handle)
    }

    companion object {
        const val BILLBOARD_TYPE_DISABLED: Long = 0L
        const val BILLBOARD_TYPE_ENABLED: Long = 1L
        const val BILLBOARD_TYPE_FIXED_Y: Long = 2L
        const val BILLBOARD_TYPE_PARTICLES: Long = 3L
        const val BILLBOARD_TYPE_MAX: Long = 4L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeBillboard? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeBillboard? =
            if (handle.address() == 0L) null else VisualShaderNodeBillboard(handle)

        private const val SET_BILLBOARD_TYPE_HASH = 1227463289L
        private val setBillboardTypeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeBillboard", "set_billboard_type", SET_BILLBOARD_TYPE_HASH)
        }

        private const val GET_BILLBOARD_TYPE_HASH = 3724188517L
        private val getBillboardTypeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeBillboard", "get_billboard_type", GET_BILLBOARD_TYPE_HASH)
        }

        private const val SET_KEEP_SCALE_ENABLED_HASH = 2586408642L
        private val setKeepScaleEnabledBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeBillboard", "set_keep_scale_enabled", SET_KEEP_SCALE_ENABLED_HASH)
        }

        private const val IS_KEEP_SCALE_ENABLED_HASH = 36873697L
        private val isKeepScaleEnabledBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeBillboard", "is_keep_scale_enabled", IS_KEEP_SCALE_ENABLED_HASH)
        }
    }
}
