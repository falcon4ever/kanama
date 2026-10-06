package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: UPNP
 */
class UPNP(handle: GodotHandle) : RefCounted(handle) {
    var discoverMulticastIf: String
        @JvmName("discoverMulticastIfProperty")
        get() = getDiscoverMulticastIf()
        @JvmName("setDiscoverMulticastIfProperty")
        set(value) = setDiscoverMulticastIf(value)

    var discoverLocalPort: Int
        @JvmName("discoverLocalPortProperty")
        get() = getDiscoverLocalPort()
        @JvmName("setDiscoverLocalPortProperty")
        set(value) = setDiscoverLocalPort(value)

    var discoverIpv6: Boolean
        @JvmName("discoverIpv6Property")
        get() = isDiscoverIpv6()
        @JvmName("setDiscoverIpv6Property")
        set(value) = setDiscoverIpv6(value)

    fun getDeviceCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getDeviceCountBind, segment)
    }

    fun getDevice(index: Int): UPNPDevice? {
        checkOpen()
        return UPNPDevice.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getDeviceBind, segment, index))
    }

    fun addDevice(device: UPNPDevice?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.addDeviceBind, segment, listOf(device?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun setDevice(index: Int, device: UPNPDevice?) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndObjectArg(Binds.setDeviceBind, segment, index, device?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    fun removeDevice(index: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.removeDeviceBind, segment, index)
    }

    fun clearDevices() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.clearDevicesBind, segment)
    }

    fun getGateway(): UPNPDevice? {
        checkOpen()
        return UPNPDevice.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getGatewayBind, segment))
    }

    fun discover(timeout: Int = 2000, ttl: Int = 2, deviceFilter: String = "InternetGatewayDevice"): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntStringArgsRetInt(Binds.discoverBind, segment, timeout, ttl, deviceFilter)
    }

    fun queryExternalAddress(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.queryExternalAddressBind, segment)
    }

    fun addPortMapping(port: Int, portInternal: Int = 0, desc: String = "", proto: String = "UDP", duration: Int = 0): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntTwoStringAndIntArgsRetInt(Binds.addPortMappingBind, segment, port, portInternal, desc, proto, duration)
    }

    fun deletePortMapping(port: Int, proto: String = "UDP"): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntAndStringArgRetInt(Binds.deletePortMappingBind, segment, port, proto)
    }

    fun setDiscoverMulticastIf(mIf: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setDiscoverMulticastIfBind, segment, mIf)
    }

    fun getDiscoverMulticastIf(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getDiscoverMulticastIfBind, segment)
    }

    fun setDiscoverLocalPort(port: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setDiscoverLocalPortBind, segment, port)
    }

    fun getDiscoverLocalPort(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getDiscoverLocalPortBind, segment)
    }

    fun setDiscoverIpv6(ipv6: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setDiscoverIpv6Bind, segment, ipv6)
    }

    fun isDiscoverIpv6(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isDiscoverIpv6Bind, segment)
    }

    @JvmInline
    value class UPNPResult(override val value: Long) : GodotEnumValue {
        companion object {
            val SUCCESS: UPNPResult get() = UPNPResult(0L)
            val NOT_AUTHORIZED: UPNPResult get() = UPNPResult(1L)
            val PORT_MAPPING_NOT_FOUND: UPNPResult get() = UPNPResult(2L)
            val INCONSISTENT_PARAMETERS: UPNPResult get() = UPNPResult(3L)
            val NO_SUCH_ENTRY_IN_ARRAY: UPNPResult get() = UPNPResult(4L)
            val ACTION_FAILED: UPNPResult get() = UPNPResult(5L)
            val SRC_IP_WILDCARD_NOT_PERMITTED: UPNPResult get() = UPNPResult(6L)
            val EXT_PORT_WILDCARD_NOT_PERMITTED: UPNPResult get() = UPNPResult(7L)
            val INT_PORT_WILDCARD_NOT_PERMITTED: UPNPResult get() = UPNPResult(8L)
            val REMOTE_HOST_MUST_BE_WILDCARD: UPNPResult get() = UPNPResult(9L)
            val EXT_PORT_MUST_BE_WILDCARD: UPNPResult get() = UPNPResult(10L)
            val NO_PORT_MAPS_AVAILABLE: UPNPResult get() = UPNPResult(11L)
            val CONFLICT_WITH_OTHER_MECHANISM: UPNPResult get() = UPNPResult(12L)
            val CONFLICT_WITH_OTHER_MAPPING: UPNPResult get() = UPNPResult(13L)
            val SAME_PORT_VALUES_REQUIRED: UPNPResult get() = UPNPResult(14L)
            val ONLY_PERMANENT_LEASE_SUPPORTED: UPNPResult get() = UPNPResult(15L)
            val INVALID_GATEWAY: UPNPResult get() = UPNPResult(16L)
            val INVALID_PORT: UPNPResult get() = UPNPResult(17L)
            val INVALID_PROTOCOL: UPNPResult get() = UPNPResult(18L)
            val INVALID_DURATION: UPNPResult get() = UPNPResult(19L)
            val INVALID_ARGS: UPNPResult get() = UPNPResult(20L)
            val INVALID_RESPONSE: UPNPResult get() = UPNPResult(21L)
            val INVALID_PARAM: UPNPResult get() = UPNPResult(22L)
            val HTTP_ERROR: UPNPResult get() = UPNPResult(23L)
            val SOCKET_ERROR: UPNPResult get() = UPNPResult(24L)
            val MEM_ALLOC_ERROR: UPNPResult get() = UPNPResult(25L)
            val NO_GATEWAY: UPNPResult get() = UPNPResult(26L)
            val NO_DEVICES: UPNPResult get() = UPNPResult(27L)
            val UNKNOWN_ERROR: UPNPResult get() = UPNPResult(28L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): UPNP? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): UPNP? =
            if (handle.address() == 0L) null else RefCounted.owned(UPNP(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): UPNP? =
            if (handle.address() == 0L) null else UPNP(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_DEVICE_COUNT_HASH = 3905245786L
        @JvmField
        val getDeviceCountBind =
            ObjectCalls.getMethodBind("UPNP", "get_device_count", GET_DEVICE_COUNT_HASH)

        private const val GET_DEVICE_HASH = 2193290270L
        @JvmField
        val getDeviceBind =
            ObjectCalls.getMethodBind("UPNP", "get_device", GET_DEVICE_HASH)

        private const val ADD_DEVICE_HASH = 986715920L
        @JvmField
        val addDeviceBind =
            ObjectCalls.getMethodBind("UPNP", "add_device", ADD_DEVICE_HASH)

        private const val SET_DEVICE_HASH = 3015133723L
        @JvmField
        val setDeviceBind =
            ObjectCalls.getMethodBind("UPNP", "set_device", SET_DEVICE_HASH)

        private const val REMOVE_DEVICE_HASH = 1286410249L
        @JvmField
        val removeDeviceBind =
            ObjectCalls.getMethodBind("UPNP", "remove_device", REMOVE_DEVICE_HASH)

        private const val CLEAR_DEVICES_HASH = 3218959716L
        @JvmField
        val clearDevicesBind =
            ObjectCalls.getMethodBind("UPNP", "clear_devices", CLEAR_DEVICES_HASH)

        private const val GET_GATEWAY_HASH = 2276800779L
        @JvmField
        val getGatewayBind =
            ObjectCalls.getMethodBind("UPNP", "get_gateway", GET_GATEWAY_HASH)

        private const val DISCOVER_HASH = 1575334765L
        @JvmField
        val discoverBind =
            ObjectCalls.getMethodBind("UPNP", "discover", DISCOVER_HASH)

        private const val QUERY_EXTERNAL_ADDRESS_HASH = 201670096L
        @JvmField
        val queryExternalAddressBind =
            ObjectCalls.getMethodBind("UPNP", "query_external_address", QUERY_EXTERNAL_ADDRESS_HASH)

        private const val ADD_PORT_MAPPING_HASH = 818314583L
        @JvmField
        val addPortMappingBind =
            ObjectCalls.getMethodBind("UPNP", "add_port_mapping", ADD_PORT_MAPPING_HASH)

        private const val DELETE_PORT_MAPPING_HASH = 3444187325L
        @JvmField
        val deletePortMappingBind =
            ObjectCalls.getMethodBind("UPNP", "delete_port_mapping", DELETE_PORT_MAPPING_HASH)

        private const val SET_DISCOVER_MULTICAST_IF_HASH = 83702148L
        @JvmField
        val setDiscoverMulticastIfBind =
            ObjectCalls.getMethodBind("UPNP", "set_discover_multicast_if", SET_DISCOVER_MULTICAST_IF_HASH)

        private const val GET_DISCOVER_MULTICAST_IF_HASH = 201670096L
        @JvmField
        val getDiscoverMulticastIfBind =
            ObjectCalls.getMethodBind("UPNP", "get_discover_multicast_if", GET_DISCOVER_MULTICAST_IF_HASH)

        private const val SET_DISCOVER_LOCAL_PORT_HASH = 1286410249L
        @JvmField
        val setDiscoverLocalPortBind =
            ObjectCalls.getMethodBind("UPNP", "set_discover_local_port", SET_DISCOVER_LOCAL_PORT_HASH)

        private const val GET_DISCOVER_LOCAL_PORT_HASH = 3905245786L
        @JvmField
        val getDiscoverLocalPortBind =
            ObjectCalls.getMethodBind("UPNP", "get_discover_local_port", GET_DISCOVER_LOCAL_PORT_HASH)

        private const val SET_DISCOVER_IPV6_HASH = 2586408642L
        @JvmField
        val setDiscoverIpv6Bind =
            ObjectCalls.getMethodBind("UPNP", "set_discover_ipv6", SET_DISCOVER_IPV6_HASH)

        private const val IS_DISCOVER_IPV6_HASH = 36873697L
        @JvmField
        val isDiscoverIpv6Bind =
            ObjectCalls.getMethodBind("UPNP", "is_discover_ipv6", IS_DISCOVER_IPV6_HASH)
    }
}
