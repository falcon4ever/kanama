package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: UPNP
 */
class UPNP(handle: MemorySegment) : RefCounted(handle) {
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
        return ObjectCalls.ptrcallNoArgsRetInt(getDeviceCountBind, handle)
    }

    fun getDevice(index: Int): UPNPDevice? {
        return UPNPDevice.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getDeviceBind, handle, index))
    }

    fun addDevice(device: UPNPDevice?) {
        ObjectCalls.ptrcallWithObjectArgs(addDeviceBind, handle, listOf(device?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun setDevice(index: Int, device: UPNPDevice?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setDeviceBind, handle, index, device?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun removeDevice(index: Int) {
        ObjectCalls.ptrcallWithIntArg(removeDeviceBind, handle, index)
    }

    fun clearDevices() {
        ObjectCalls.ptrcallNoArgs(clearDevicesBind, handle)
    }

    fun getGateway(): UPNPDevice? {
        return UPNPDevice.wrap(ObjectCalls.ptrcallNoArgsRetObject(getGatewayBind, handle))
    }

    fun discover(timeout: Int = 2000, ttl: Int = 2, deviceFilter: String = "InternetGatewayDevice"): Int {
        return ObjectCalls.ptrcallWithTwoIntStringArgsRetInt(discoverBind, handle, timeout, ttl, deviceFilter)
    }

    fun queryExternalAddress(): String {
        return ObjectCalls.ptrcallNoArgsRetString(queryExternalAddressBind, handle)
    }

    fun addPortMapping(port: Int, portInternal: Int = 0, desc: String = "", proto: String = "UDP", duration: Int = 0): Int {
        return ObjectCalls.ptrcallWithTwoIntTwoStringAndIntArgsRetInt(addPortMappingBind, handle, port, portInternal, desc, proto, duration)
    }

    fun deletePortMapping(port: Int, proto: String = "UDP"): Int {
        return ObjectCalls.ptrcallWithIntAndStringArgRetInt(deletePortMappingBind, handle, port, proto)
    }

    fun setDiscoverMulticastIf(mIf: String) {
        ObjectCalls.ptrcallWithStringArg(setDiscoverMulticastIfBind, handle, mIf)
    }

    fun getDiscoverMulticastIf(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getDiscoverMulticastIfBind, handle)
    }

    fun setDiscoverLocalPort(port: Int) {
        ObjectCalls.ptrcallWithIntArg(setDiscoverLocalPortBind, handle, port)
    }

    fun getDiscoverLocalPort(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getDiscoverLocalPortBind, handle)
    }

    fun setDiscoverIpv6(ipv6: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDiscoverIpv6Bind, handle, ipv6)
    }

    fun isDiscoverIpv6(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDiscoverIpv6Bind, handle)
    }

    companion object {
        const val UPNP_RESULT_SUCCESS: Long = 0L
        const val UPNP_RESULT_NOT_AUTHORIZED: Long = 1L
        const val UPNP_RESULT_PORT_MAPPING_NOT_FOUND: Long = 2L
        const val UPNP_RESULT_INCONSISTENT_PARAMETERS: Long = 3L
        const val UPNP_RESULT_NO_SUCH_ENTRY_IN_ARRAY: Long = 4L
        const val UPNP_RESULT_ACTION_FAILED: Long = 5L
        const val UPNP_RESULT_SRC_IP_WILDCARD_NOT_PERMITTED: Long = 6L
        const val UPNP_RESULT_EXT_PORT_WILDCARD_NOT_PERMITTED: Long = 7L
        const val UPNP_RESULT_INT_PORT_WILDCARD_NOT_PERMITTED: Long = 8L
        const val UPNP_RESULT_REMOTE_HOST_MUST_BE_WILDCARD: Long = 9L
        const val UPNP_RESULT_EXT_PORT_MUST_BE_WILDCARD: Long = 10L
        const val UPNP_RESULT_NO_PORT_MAPS_AVAILABLE: Long = 11L
        const val UPNP_RESULT_CONFLICT_WITH_OTHER_MECHANISM: Long = 12L
        const val UPNP_RESULT_CONFLICT_WITH_OTHER_MAPPING: Long = 13L
        const val UPNP_RESULT_SAME_PORT_VALUES_REQUIRED: Long = 14L
        const val UPNP_RESULT_ONLY_PERMANENT_LEASE_SUPPORTED: Long = 15L
        const val UPNP_RESULT_INVALID_GATEWAY: Long = 16L
        const val UPNP_RESULT_INVALID_PORT: Long = 17L
        const val UPNP_RESULT_INVALID_PROTOCOL: Long = 18L
        const val UPNP_RESULT_INVALID_DURATION: Long = 19L
        const val UPNP_RESULT_INVALID_ARGS: Long = 20L
        const val UPNP_RESULT_INVALID_RESPONSE: Long = 21L
        const val UPNP_RESULT_INVALID_PARAM: Long = 22L
        const val UPNP_RESULT_HTTP_ERROR: Long = 23L
        const val UPNP_RESULT_SOCKET_ERROR: Long = 24L
        const val UPNP_RESULT_MEM_ALLOC_ERROR: Long = 25L
        const val UPNP_RESULT_NO_GATEWAY: Long = 26L
        const val UPNP_RESULT_NO_DEVICES: Long = 27L
        const val UPNP_RESULT_UNKNOWN_ERROR: Long = 28L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): UPNP? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): UPNP? =
            if (handle.address() == 0L) null else UPNP(handle)

        private const val GET_DEVICE_COUNT_HASH = 3905245786L
        private val getDeviceCountBind by lazy {
            ObjectCalls.getMethodBind("UPNP", "get_device_count", GET_DEVICE_COUNT_HASH)
        }

        private const val GET_DEVICE_HASH = 2193290270L
        private val getDeviceBind by lazy {
            ObjectCalls.getMethodBind("UPNP", "get_device", GET_DEVICE_HASH)
        }

        private const val ADD_DEVICE_HASH = 986715920L
        private val addDeviceBind by lazy {
            ObjectCalls.getMethodBind("UPNP", "add_device", ADD_DEVICE_HASH)
        }

        private const val SET_DEVICE_HASH = 3015133723L
        private val setDeviceBind by lazy {
            ObjectCalls.getMethodBind("UPNP", "set_device", SET_DEVICE_HASH)
        }

        private const val REMOVE_DEVICE_HASH = 1286410249L
        private val removeDeviceBind by lazy {
            ObjectCalls.getMethodBind("UPNP", "remove_device", REMOVE_DEVICE_HASH)
        }

        private const val CLEAR_DEVICES_HASH = 3218959716L
        private val clearDevicesBind by lazy {
            ObjectCalls.getMethodBind("UPNP", "clear_devices", CLEAR_DEVICES_HASH)
        }

        private const val GET_GATEWAY_HASH = 2276800779L
        private val getGatewayBind by lazy {
            ObjectCalls.getMethodBind("UPNP", "get_gateway", GET_GATEWAY_HASH)
        }

        private const val DISCOVER_HASH = 1575334765L
        private val discoverBind by lazy {
            ObjectCalls.getMethodBind("UPNP", "discover", DISCOVER_HASH)
        }

        private const val QUERY_EXTERNAL_ADDRESS_HASH = 201670096L
        private val queryExternalAddressBind by lazy {
            ObjectCalls.getMethodBind("UPNP", "query_external_address", QUERY_EXTERNAL_ADDRESS_HASH)
        }

        private const val ADD_PORT_MAPPING_HASH = 818314583L
        private val addPortMappingBind by lazy {
            ObjectCalls.getMethodBind("UPNP", "add_port_mapping", ADD_PORT_MAPPING_HASH)
        }

        private const val DELETE_PORT_MAPPING_HASH = 3444187325L
        private val deletePortMappingBind by lazy {
            ObjectCalls.getMethodBind("UPNP", "delete_port_mapping", DELETE_PORT_MAPPING_HASH)
        }

        private const val SET_DISCOVER_MULTICAST_IF_HASH = 83702148L
        private val setDiscoverMulticastIfBind by lazy {
            ObjectCalls.getMethodBind("UPNP", "set_discover_multicast_if", SET_DISCOVER_MULTICAST_IF_HASH)
        }

        private const val GET_DISCOVER_MULTICAST_IF_HASH = 201670096L
        private val getDiscoverMulticastIfBind by lazy {
            ObjectCalls.getMethodBind("UPNP", "get_discover_multicast_if", GET_DISCOVER_MULTICAST_IF_HASH)
        }

        private const val SET_DISCOVER_LOCAL_PORT_HASH = 1286410249L
        private val setDiscoverLocalPortBind by lazy {
            ObjectCalls.getMethodBind("UPNP", "set_discover_local_port", SET_DISCOVER_LOCAL_PORT_HASH)
        }

        private const val GET_DISCOVER_LOCAL_PORT_HASH = 3905245786L
        private val getDiscoverLocalPortBind by lazy {
            ObjectCalls.getMethodBind("UPNP", "get_discover_local_port", GET_DISCOVER_LOCAL_PORT_HASH)
        }

        private const val SET_DISCOVER_IPV6_HASH = 2586408642L
        private val setDiscoverIpv6Bind by lazy {
            ObjectCalls.getMethodBind("UPNP", "set_discover_ipv6", SET_DISCOVER_IPV6_HASH)
        }

        private const val IS_DISCOVER_IPV6_HASH = 36873697L
        private val isDiscoverIpv6Bind by lazy {
            ObjectCalls.getMethodBind("UPNP", "is_discover_ipv6", IS_DISCOVER_IPV6_HASH)
        }
    }
}
