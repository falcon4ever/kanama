package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A node that controls how the object faces the camera to be used within the visual shader graph.
 *
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

    /**
     * Controls how the object faces the camera.
     *
     * Generated from Godot docs: VisualShaderNodeBillboard.set_billboard_type
     */
    fun setBillboardType(billboardType: VisualShaderNodeBillboard.BillboardType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setBillboardTypeBind, segment, billboardType.value)
    }

    /**
     * Controls how the object faces the camera.
     *
     * Generated from Godot docs: VisualShaderNodeBillboard.get_billboard_type
     */
    fun getBillboardType(): VisualShaderNodeBillboard.BillboardType {
        checkOpen()
        return VisualShaderNodeBillboard.BillboardType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getBillboardTypeBind, segment))
    }

    /**
     * If `true`, the shader will keep the scale set for the mesh. Otherwise, the scale is lost when
     * billboarding.
     *
     * Generated from Godot docs: VisualShaderNodeBillboard.set_keep_scale_enabled
     */
    fun setKeepScaleEnabled(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setKeepScaleEnabledBind, segment, enabled)
    }

    /**
     * If `true`, the shader will keep the scale set for the mesh. Otherwise, the scale is lost when
     * billboarding.
     *
     * Generated from Godot docs: VisualShaderNodeBillboard.is_keep_scale_enabled
     */
    fun isKeepScaleEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isKeepScaleEnabledBind, segment)
    }

    /**
     * Godot's `VisualShaderNodeBillboard.BillboardType` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`VisualShaderNodeBillboard.BillboardType.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeBillboard.BillboardType
     */
    @JvmInline
    value class BillboardType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Billboarding is disabled and the node does nothing.
             *
             * Generated from Godot docs: VisualShaderNodeBillboard.BILLBOARD_TYPE_DISABLED
             */
            val DISABLED: BillboardType get() = BillboardType(0L)
            /**
             * A standard billboarding algorithm is enabled.
             *
             * Generated from Godot docs: VisualShaderNodeBillboard.BILLBOARD_TYPE_ENABLED
             */
            val ENABLED: BillboardType get() = BillboardType(1L)
            /**
             * A billboarding algorithm to rotate around Y-axis is enabled.
             *
             * Generated from Godot docs: VisualShaderNodeBillboard.BILLBOARD_TYPE_FIXED_Y
             */
            val FIXED_Y: BillboardType get() = BillboardType(2L)
            /**
             * A billboarding algorithm designed to use on particles is enabled.
             *
             * Generated from Godot docs: VisualShaderNodeBillboard.BILLBOARD_TYPE_PARTICLES
             */
            val PARTICLES: BillboardType get() = BillboardType(3L)
            /**
             * Represents the size of the `BillboardType` enum.
             *
             * Generated from Godot docs: VisualShaderNodeBillboard.BILLBOARD_TYPE_MAX
             */
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
