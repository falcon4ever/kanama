package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeReroute
 */
class VisualShaderNodeReroute(handle: GodotHandle) : VisualShaderNode(handle) {
    val portType: VisualShaderNode.PortType
        @JvmName("portTypeProperty")
        get() = getPortType()

    fun getPortType(): VisualShaderNode.PortType {
        checkOpen()
        return VisualShaderNode.PortType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getPortTypeBind, segment))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeReroute? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeReroute? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeReroute(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeReroute? =
            if (handle.address() == 0L) null else VisualShaderNodeReroute(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_PORT_TYPE_HASH = 1287173294L
        @JvmField
        val getPortTypeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeReroute", "get_port_type", GET_PORT_TYPE_HASH)
    }
}
