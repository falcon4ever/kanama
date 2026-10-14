package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Universal Plug and Play (UPnP) functions for network device discovery, querying and port
 * forwarding.
 *
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

    /**
     * Returns the number of discovered `UPNPDevice`s.
     *
     * Generated from Godot docs: UPNP.get_device_count
     */
    fun getDeviceCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getDeviceCountBind, segment)
    }

    /**
     * Returns the `UPNPDevice` at the given `index`.
     *
     * Generated from Godot docs: UPNP.get_device
     */
    fun getDevice(index: Int): UPNPDevice? {
        checkOpen()
        return UPNPDevice.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getDeviceBind, segment, index))
    }

    /**
     * Adds the given `UPNPDevice` to the list of discovered devices.
     *
     * Generated from Godot docs: UPNP.add_device
     */
    fun addDevice(device: UPNPDevice?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.addDeviceBind, segment, listOf(device?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Sets the device at `index` from the list of discovered devices to `device`.
     *
     * Generated from Godot docs: UPNP.set_device
     */
    fun setDevice(index: Int, device: UPNPDevice?) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndObjectArg(Binds.setDeviceBind, segment, index, device?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Removes the device at `index` from the list of discovered devices.
     *
     * Generated from Godot docs: UPNP.remove_device
     */
    fun removeDevice(index: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.removeDeviceBind, segment, index)
    }

    /**
     * Clears the list of discovered devices.
     *
     * Generated from Godot docs: UPNP.clear_devices
     */
    fun clearDevices() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.clearDevicesBind, segment)
    }

    /**
     * Returns the default gateway. That is the first discovered `UPNPDevice` that is also a valid IGD
     * (InternetGatewayDevice).
     *
     * Generated from Godot docs: UPNP.get_gateway
     */
    fun getGateway(): UPNPDevice? {
        checkOpen()
        return UPNPDevice.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getGatewayBind, segment))
    }

    /**
     * Discovers local `UPNPDevice`s. Clears the list of previously discovered devices. Filters for IGD
     * (InternetGatewayDevice) type devices by default, as those manage port forwarding. `timeout` is
     * the time to wait for responses in milliseconds. `ttl` is the time-to-live; only touch this if
     * you know what you're doing. See `UPNPResult` for possible return values.
     *
     * Generated from Godot docs: UPNP.discover
     */
    fun discover(timeout: Int = 2000, ttl: Int = 2, deviceFilter: String = "InternetGatewayDevice"): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntStringArgsRetInt(Binds.discoverBind, segment, timeout, ttl, deviceFilter)
    }

    /**
     * Returns the external `IP` address of the default gateway (see `get_gateway`) as string. Returns
     * an empty string on error.
     *
     * Generated from Godot docs: UPNP.query_external_address
     */
    fun queryExternalAddress(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.queryExternalAddressBind, segment)
    }

    /**
     * Adds a mapping to forward the external `port` (between 1 and 65535, although recommended to use
     * port 1024 or above) on the default gateway (see `get_gateway`) to the `port_internal` on the
     * local machine for the given protocol `proto` (either `"TCP"` or `"UDP"`, with UDP being the
     * default). If a port mapping for the given port and protocol combination already exists on that
     * gateway device, this method tries to overwrite it. If that is not desired, you can retrieve the
     * gateway manually with `get_gateway` and call `add_port_mapping` on it, if any. Note that
     * forwarding a well-known port (below 1024) with UPnP may fail depending on the device. Depending
     * on the gateway device, if a mapping for that port already exists, it will either be updated or
     * it will refuse this command due to that conflict, especially if the existing mapping for that
     * port wasn't created via UPnP or points to a different network address (or device) than this one.
     * If `port_internal` is `0` (the default), the same port number is used for both the external and
     * the internal port (the `port` value). The description (`desc`) is shown in some routers
     * management UIs and can be used to point out which application added the mapping. The mapping's
     * lease `duration` can be limited by specifying a duration in seconds. The default of `0` means no
     * duration, i.e. a permanent lease and notably some devices only support these permanent leases.
     * Note that whether permanent or not, this is only a request and the gateway may still decide at
     * any point to remove the mapping (which usually happens on a reboot of the gateway, when its
     * external IP address changes, or on some models when it detects a port mapping has become
     * inactive, i.e. had no traffic for multiple minutes). If not `0` (permanent), the allowed range
     * according to spec is between `120` (2 minutes) and `86400` seconds (24 hours). See `UPNPResult`
     * for possible return values.
     *
     * Generated from Godot docs: UPNP.add_port_mapping
     */
    fun addPortMapping(port: Int, portInternal: Int = 0, desc: String = "", proto: String = "UDP", duration: Int = 0): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntTwoStringAndIntArgsRetInt(Binds.addPortMappingBind, segment, port, portInternal, desc, proto, duration)
    }

    /**
     * Deletes the port mapping for the given port and protocol combination on the default gateway (see
     * `get_gateway`) if one exists. `port` must be a valid port between 1 and 65535, `proto` can be
     * either `"TCP"` or `"UDP"`. May be refused for mappings pointing to addresses other than this
     * one, for well-known ports (below 1024), or for mappings not added via UPnP. See `UPNPResult` for
     * possible return values.
     *
     * Generated from Godot docs: UPNP.delete_port_mapping
     */
    fun deletePortMapping(port: Int, proto: String = "UDP"): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntAndStringArgRetInt(Binds.deletePortMappingBind, segment, port, proto)
    }

    /**
     * Multicast interface to use for discovery. Uses the default multicast interface if empty.
     *
     * Generated from Godot docs: UPNP.set_discover_multicast_if
     */
    fun setDiscoverMulticastIf(mIf: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setDiscoverMulticastIfBind, segment, mIf)
    }

    /**
     * Multicast interface to use for discovery. Uses the default multicast interface if empty.
     *
     * Generated from Godot docs: UPNP.get_discover_multicast_if
     */
    fun getDiscoverMulticastIf(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getDiscoverMulticastIfBind, segment)
    }

    /**
     * If `0`, the local port to use for discovery is chosen automatically by the system. If `1`,
     * discovery will be done from the source port 1900 (same as destination port). Otherwise, the
     * value will be used as the port.
     *
     * Generated from Godot docs: UPNP.set_discover_local_port
     */
    fun setDiscoverLocalPort(port: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setDiscoverLocalPortBind, segment, port)
    }

    /**
     * If `0`, the local port to use for discovery is chosen automatically by the system. If `1`,
     * discovery will be done from the source port 1900 (same as destination port). Otherwise, the
     * value will be used as the port.
     *
     * Generated from Godot docs: UPNP.get_discover_local_port
     */
    fun getDiscoverLocalPort(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getDiscoverLocalPortBind, segment)
    }

    /**
     * If `true`, IPv6 is used for `UPNPDevice` discovery.
     *
     * Generated from Godot docs: UPNP.set_discover_ipv6
     */
    fun setDiscoverIpv6(ipv6: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setDiscoverIpv6Bind, segment, ipv6)
    }

    /**
     * If `true`, IPv6 is used for `UPNPDevice` discovery.
     *
     * Generated from Godot docs: UPNP.is_discover_ipv6
     */
    fun isDiscoverIpv6(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isDiscoverIpv6Bind, segment)
    }

    /**
     * Godot's `UPNP.UPNPResult` enum as a typed value: `.value` is the raw number Godot uses, and the
     * companion holds the named values (`UPNP.UPNPResult.<NAME>`).
     *
     * Generated from Godot docs: UPNP.UPNPResult
     */
    @JvmInline
    value class UPNPResult(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * UPNP command or discovery was successful.
             *
             * Generated from Godot docs: UPNP.UPNP_RESULT_SUCCESS
             */
            val SUCCESS: UPNPResult get() = UPNPResult(0L)
            /**
             * Not authorized to use the command on the `UPNPDevice`. May be returned when the user disabled
             * UPNP on their router.
             *
             * Generated from Godot docs: UPNP.UPNP_RESULT_NOT_AUTHORIZED
             */
            val NOT_AUTHORIZED: UPNPResult get() = UPNPResult(1L)
            /**
             * No port mapping was found for the given port, protocol combination on the given `UPNPDevice`.
             *
             * Generated from Godot docs: UPNP.UPNP_RESULT_PORT_MAPPING_NOT_FOUND
             */
            val PORT_MAPPING_NOT_FOUND: UPNPResult get() = UPNPResult(2L)
            /**
             * Inconsistent parameters.
             *
             * Generated from Godot docs: UPNP.UPNP_RESULT_INCONSISTENT_PARAMETERS
             */
            val INCONSISTENT_PARAMETERS: UPNPResult get() = UPNPResult(3L)
            /**
             * No such entry in array. May be returned if a given port, protocol combination is not found on a
             * `UPNPDevice`.
             *
             * Generated from Godot docs: UPNP.UPNP_RESULT_NO_SUCH_ENTRY_IN_ARRAY
             */
            val NO_SUCH_ENTRY_IN_ARRAY: UPNPResult get() = UPNPResult(4L)
            /**
             * The action failed.
             *
             * Generated from Godot docs: UPNP.UPNP_RESULT_ACTION_FAILED
             */
            val ACTION_FAILED: UPNPResult get() = UPNPResult(5L)
            /**
             * The `UPNPDevice` does not allow wildcard values for the source IP address.
             *
             * Generated from Godot docs: UPNP.UPNP_RESULT_SRC_IP_WILDCARD_NOT_PERMITTED
             */
            val SRC_IP_WILDCARD_NOT_PERMITTED: UPNPResult get() = UPNPResult(6L)
            /**
             * The `UPNPDevice` does not allow wildcard values for the external port.
             *
             * Generated from Godot docs: UPNP.UPNP_RESULT_EXT_PORT_WILDCARD_NOT_PERMITTED
             */
            val EXT_PORT_WILDCARD_NOT_PERMITTED: UPNPResult get() = UPNPResult(7L)
            /**
             * The `UPNPDevice` does not allow wildcard values for the internal port.
             *
             * Generated from Godot docs: UPNP.UPNP_RESULT_INT_PORT_WILDCARD_NOT_PERMITTED
             */
            val INT_PORT_WILDCARD_NOT_PERMITTED: UPNPResult get() = UPNPResult(8L)
            /**
             * The remote host value must be a wildcard.
             *
             * Generated from Godot docs: UPNP.UPNP_RESULT_REMOTE_HOST_MUST_BE_WILDCARD
             */
            val REMOTE_HOST_MUST_BE_WILDCARD: UPNPResult get() = UPNPResult(9L)
            /**
             * The external port value must be a wildcard.
             *
             * Generated from Godot docs: UPNP.UPNP_RESULT_EXT_PORT_MUST_BE_WILDCARD
             */
            val EXT_PORT_MUST_BE_WILDCARD: UPNPResult get() = UPNPResult(10L)
            /**
             * No port maps are available. May also be returned if port mapping functionality is not available.
             *
             * Generated from Godot docs: UPNP.UPNP_RESULT_NO_PORT_MAPS_AVAILABLE
             */
            val NO_PORT_MAPS_AVAILABLE: UPNPResult get() = UPNPResult(11L)
            /**
             * Conflict with other mechanism. May be returned instead of
             * `UPNPResult.CONFLICT_WITH_OTHER_MAPPING` if a port mapping conflicts with an existing one.
             *
             * Generated from Godot docs: UPNP.UPNP_RESULT_CONFLICT_WITH_OTHER_MECHANISM
             */
            val CONFLICT_WITH_OTHER_MECHANISM: UPNPResult get() = UPNPResult(12L)
            /**
             * Conflict with an existing port mapping.
             *
             * Generated from Godot docs: UPNP.UPNP_RESULT_CONFLICT_WITH_OTHER_MAPPING
             */
            val CONFLICT_WITH_OTHER_MAPPING: UPNPResult get() = UPNPResult(13L)
            /**
             * External and internal port values must be the same.
             *
             * Generated from Godot docs: UPNP.UPNP_RESULT_SAME_PORT_VALUES_REQUIRED
             */
            val SAME_PORT_VALUES_REQUIRED: UPNPResult get() = UPNPResult(14L)
            /**
             * Only permanent leases are supported. Do not use the `duration` parameter when adding port
             * mappings.
             *
             * Generated from Godot docs: UPNP.UPNP_RESULT_ONLY_PERMANENT_LEASE_SUPPORTED
             */
            val ONLY_PERMANENT_LEASE_SUPPORTED: UPNPResult get() = UPNPResult(15L)
            /**
             * Invalid gateway.
             *
             * Generated from Godot docs: UPNP.UPNP_RESULT_INVALID_GATEWAY
             */
            val INVALID_GATEWAY: UPNPResult get() = UPNPResult(16L)
            /**
             * Invalid port.
             *
             * Generated from Godot docs: UPNP.UPNP_RESULT_INVALID_PORT
             */
            val INVALID_PORT: UPNPResult get() = UPNPResult(17L)
            /**
             * Invalid protocol.
             *
             * Generated from Godot docs: UPNP.UPNP_RESULT_INVALID_PROTOCOL
             */
            val INVALID_PROTOCOL: UPNPResult get() = UPNPResult(18L)
            /**
             * Invalid duration.
             *
             * Generated from Godot docs: UPNP.UPNP_RESULT_INVALID_DURATION
             */
            val INVALID_DURATION: UPNPResult get() = UPNPResult(19L)
            /**
             * Invalid arguments.
             *
             * Generated from Godot docs: UPNP.UPNP_RESULT_INVALID_ARGS
             */
            val INVALID_ARGS: UPNPResult get() = UPNPResult(20L)
            /**
             * Invalid response.
             *
             * Generated from Godot docs: UPNP.UPNP_RESULT_INVALID_RESPONSE
             */
            val INVALID_RESPONSE: UPNPResult get() = UPNPResult(21L)
            /**
             * Invalid parameter.
             *
             * Generated from Godot docs: UPNP.UPNP_RESULT_INVALID_PARAM
             */
            val INVALID_PARAM: UPNPResult get() = UPNPResult(22L)
            /**
             * HTTP error.
             *
             * Generated from Godot docs: UPNP.UPNP_RESULT_HTTP_ERROR
             */
            val HTTP_ERROR: UPNPResult get() = UPNPResult(23L)
            /**
             * Socket error.
             *
             * Generated from Godot docs: UPNP.UPNP_RESULT_SOCKET_ERROR
             */
            val SOCKET_ERROR: UPNPResult get() = UPNPResult(24L)
            /**
             * Error allocating memory.
             *
             * Generated from Godot docs: UPNP.UPNP_RESULT_MEM_ALLOC_ERROR
             */
            val MEM_ALLOC_ERROR: UPNPResult get() = UPNPResult(25L)
            /**
             * No gateway available. You may need to call `discover` first, or discovery didn't detect any
             * valid IGDs (InternetGatewayDevices).
             *
             * Generated from Godot docs: UPNP.UPNP_RESULT_NO_GATEWAY
             */
            val NO_GATEWAY: UPNPResult get() = UPNPResult(26L)
            /**
             * No devices available. You may need to call `discover` first, or discovery didn't detect any
             * valid `UPNPDevice`s.
             *
             * Generated from Godot docs: UPNP.UPNP_RESULT_NO_DEVICES
             */
            val NO_DEVICES: UPNPResult get() = UPNPResult(27L)
            /**
             * Unknown error.
             *
             * Generated from Godot docs: UPNP.UPNP_RESULT_UNKNOWN_ERROR
             */
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
