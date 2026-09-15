package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeReroute
 */
class VisualShaderNodeReroute(handle: GodotHandle) : VisualShaderNode(handle) {
    val portType: Long
        @JvmName("portTypeProperty")
        get() = getPortType()

    fun getPortType(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getPortTypeBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeReroute? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeReroute? =
            if (handle.address() == 0L) null else VisualShaderNodeReroute(GodotHandle(handle))

        private const val GET_PORT_TYPE_HASH = 1287173294L
        private val getPortTypeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeReroute", "get_port_type", GET_PORT_TYPE_HASH)
        }
    }
}
