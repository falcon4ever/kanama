package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * A node that allows rerouting a connection within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeReroute
 */
class VisualShaderNodeReroute(handle: GodotHandle) : VisualShaderNode(handle) {
    val portType: VisualShaderNode.PortType
        @JvmName("portTypeProperty")
        get() = getPortType()

    /**
     * Returns the port type of the reroute node.
     *
     * Generated from Godot docs: VisualShaderNodeReroute.get_port_type
     */
    fun getPortType(): VisualShaderNode.PortType {
        checkOpen()
        val ret = VisualShaderNode.PortType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getPortTypeBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeReroute? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeReroute? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeReroute(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeReroute? =
            if (handle.address() == 0L) null else VisualShaderNodeReroute(GodotHandle(handle))

        /** A new VisualShaderNodeReroute, GDScript's `VisualShaderNodeReroute.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeReroute =
            RefCounted.owned(VisualShaderNodeReroute(GodotHandle(requireConstructed(ObjectCalls.constructObject("VisualShaderNodeReroute"), "VisualShaderNodeReroute"))))
    }

    private object Binds {
        private const val GET_PORT_TYPE_HASH = 1287173294L
        @JvmField
        val getPortTypeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeReroute", "get_port_type", GET_PORT_TYPE_HASH)
    }
}
