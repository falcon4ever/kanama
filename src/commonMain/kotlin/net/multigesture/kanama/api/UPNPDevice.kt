package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Universal Plug and Play (UPnP) device.
 *
 * Generated from Godot docs: UPNPDevice
 */
class UPNPDevice(handle: GodotHandle) : RefCounted(handle) {
    var descriptionUrl: String
        @JvmName("descriptionUrlProperty")
        get() = getDescriptionUrl()
        @JvmName("setDescriptionUrlProperty")
        set(value) = setDescriptionUrl(value)

    var serviceType: String
        @JvmName("serviceTypeProperty")
        get() = getServiceType()
        @JvmName("setServiceTypeProperty")
        set(value) = setServiceType(value)

    var igdControlUrl: String
        @JvmName("igdControlUrlProperty")
        get() = getIgdControlUrl()
        @JvmName("setIgdControlUrlProperty")
        set(value) = setIgdControlUrl(value)

    var igdServiceType: String
        @JvmName("igdServiceTypeProperty")
        get() = getIgdServiceType()
        @JvmName("setIgdServiceTypeProperty")
        set(value) = setIgdServiceType(value)

    var igdOurAddr: String
        @JvmName("igdOurAddrProperty")
        get() = getIgdOurAddr()
        @JvmName("setIgdOurAddrProperty")
        set(value) = setIgdOurAddr(value)

    var igdStatus: UPNPDevice.IGDStatus
        @JvmName("igdStatusProperty")
        get() = getIgdStatus()
        @JvmName("setIgdStatusProperty")
        set(value) = setIgdStatus(value)

    /**
     * Returns `true` if this is a valid IGD (InternetGatewayDevice) which potentially supports port
     * forwarding.
     *
     * Generated from Godot docs: UPNPDevice.is_valid_gateway
     */
    fun isValidGateway(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.isValidGatewayBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the external IP address of this `UPNPDevice` or an empty string.
     *
     * Generated from Godot docs: UPNPDevice.query_external_address
     */
    fun queryExternalAddress(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.queryExternalAddressBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Adds a port mapping to forward the given external port on this `UPNPDevice` for the given
     * protocol to the local machine. See `UPNP.add_port_mapping`.
     *
     * Generated from Godot docs: UPNPDevice.add_port_mapping
     */
    fun addPortMapping(port: Int, portInternal: Int = 0, desc: String = "", proto: String = "UDP", duration: Int = 0): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithTwoIntTwoStringAndIntArgsRetInt(Binds.addPortMappingBind, segment, port, portInternal, desc, proto, duration)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Deletes the port mapping identified by the given port and protocol combination on this device.
     * See `UPNP.delete_port_mapping`.
     *
     * Generated from Godot docs: UPNPDevice.delete_port_mapping
     */
    fun deletePortMapping(port: Int, proto: String = "UDP"): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntAndStringArgRetInt(Binds.deletePortMappingBind, segment, port, proto)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * URL to the device description.
     *
     * Generated from Godot docs: UPNPDevice.set_description_url
     */
    fun setDescriptionUrl(url: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setDescriptionUrlBind, segment, url)
        ObjectCalls.keepReachable(this)
    }

    /**
     * URL to the device description.
     *
     * Generated from Godot docs: UPNPDevice.get_description_url
     */
    fun getDescriptionUrl(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getDescriptionUrlBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Service type.
     *
     * Generated from Godot docs: UPNPDevice.set_service_type
     */
    fun setServiceType(type: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setServiceTypeBind, segment, type)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Service type.
     *
     * Generated from Godot docs: UPNPDevice.get_service_type
     */
    fun getServiceType(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getServiceTypeBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * IDG control URL.
     *
     * Generated from Godot docs: UPNPDevice.set_igd_control_url
     */
    fun setIgdControlUrl(url: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setIgdControlUrlBind, segment, url)
        ObjectCalls.keepReachable(this)
    }

    /**
     * IDG control URL.
     *
     * Generated from Godot docs: UPNPDevice.get_igd_control_url
     */
    fun getIgdControlUrl(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getIgdControlUrlBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * IGD service type.
     *
     * Generated from Godot docs: UPNPDevice.set_igd_service_type
     */
    fun setIgdServiceType(type: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setIgdServiceTypeBind, segment, type)
        ObjectCalls.keepReachable(this)
    }

    /**
     * IGD service type.
     *
     * Generated from Godot docs: UPNPDevice.get_igd_service_type
     */
    fun getIgdServiceType(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getIgdServiceTypeBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Address of the local machine in the network connecting it to this `UPNPDevice`.
     *
     * Generated from Godot docs: UPNPDevice.set_igd_our_addr
     */
    fun setIgdOurAddr(addr: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setIgdOurAddrBind, segment, addr)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Address of the local machine in the network connecting it to this `UPNPDevice`.
     *
     * Generated from Godot docs: UPNPDevice.get_igd_our_addr
     */
    fun getIgdOurAddr(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getIgdOurAddrBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * IGD status.
     *
     * Generated from Godot docs: UPNPDevice.set_igd_status
     */
    fun setIgdStatus(status: UPNPDevice.IGDStatus) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setIgdStatusBind, segment, status.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * IGD status.
     *
     * Generated from Godot docs: UPNPDevice.get_igd_status
     */
    fun getIgdStatus(): UPNPDevice.IGDStatus {
        checkOpen()
        val ret = UPNPDevice.IGDStatus(ObjectCalls.ptrcallNoArgsRetLong(Binds.getIgdStatusBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Godot's `UPNPDevice.IGDStatus` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`UPNPDevice.IGDStatus.<NAME>`).
     *
     * Generated from Godot docs: UPNPDevice.IGDStatus
     */
    @JvmInline
    value class IGDStatus(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * OK.
             *
             * Generated from Godot docs: UPNPDevice.IGD_STATUS_OK
             */
            val OK: IGDStatus get() = IGDStatus(0L)
            /**
             * HTTP error.
             *
             * Generated from Godot docs: UPNPDevice.IGD_STATUS_HTTP_ERROR
             */
            val HTTP_ERROR: IGDStatus get() = IGDStatus(1L)
            /**
             * Empty HTTP response.
             *
             * Generated from Godot docs: UPNPDevice.IGD_STATUS_HTTP_EMPTY
             */
            val HTTP_EMPTY: IGDStatus get() = IGDStatus(2L)
            /**
             * Returned response contained no URLs.
             *
             * Generated from Godot docs: UPNPDevice.IGD_STATUS_NO_URLS
             */
            val NO_URLS: IGDStatus get() = IGDStatus(3L)
            /**
             * Not a valid IGD.
             *
             * Generated from Godot docs: UPNPDevice.IGD_STATUS_NO_IGD
             */
            val NO_IGD: IGDStatus get() = IGDStatus(4L)
            /**
             * Disconnected.
             *
             * Generated from Godot docs: UPNPDevice.IGD_STATUS_DISCONNECTED
             */
            val DISCONNECTED: IGDStatus get() = IGDStatus(5L)
            /**
             * Unknown device.
             *
             * Generated from Godot docs: UPNPDevice.IGD_STATUS_UNKNOWN_DEVICE
             */
            val UNKNOWN_DEVICE: IGDStatus get() = IGDStatus(6L)
            /**
             * Invalid control.
             *
             * Generated from Godot docs: UPNPDevice.IGD_STATUS_INVALID_CONTROL
             */
            val INVALID_CONTROL: IGDStatus get() = IGDStatus(7L)
            /**
             * Memory allocation error.
             *
             * Generated from Godot docs: UPNPDevice.IGD_STATUS_MALLOC_ERROR
             */
            val MALLOC_ERROR: IGDStatus get() = IGDStatus(8L)
            /**
             * Unknown error.
             *
             * Generated from Godot docs: UPNPDevice.IGD_STATUS_UNKNOWN_ERROR
             */
            val UNKNOWN_ERROR: IGDStatus get() = IGDStatus(9L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): UPNPDevice? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): UPNPDevice? =
            if (handle.address() == 0L) null else RefCounted.owned(UPNPDevice(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): UPNPDevice? =
            if (handle.address() == 0L) null else UPNPDevice(GodotHandle(handle))

        /** A new UPNPDevice, GDScript's `UPNPDevice.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): UPNPDevice =
            RefCounted.owned(UPNPDevice(GodotHandle(ObjectCalls.constructObject("UPNPDevice"))))
    }

    private object Binds {
        private const val IS_VALID_GATEWAY_HASH = 36873697L
        @JvmField
        val isValidGatewayBind =
            ObjectCalls.getMethodBind("UPNPDevice", "is_valid_gateway", IS_VALID_GATEWAY_HASH)

        private const val QUERY_EXTERNAL_ADDRESS_HASH = 201670096L
        @JvmField
        val queryExternalAddressBind =
            ObjectCalls.getMethodBind("UPNPDevice", "query_external_address", QUERY_EXTERNAL_ADDRESS_HASH)

        private const val ADD_PORT_MAPPING_HASH = 818314583L
        @JvmField
        val addPortMappingBind =
            ObjectCalls.getMethodBind("UPNPDevice", "add_port_mapping", ADD_PORT_MAPPING_HASH)

        private const val DELETE_PORT_MAPPING_HASH = 3444187325L
        @JvmField
        val deletePortMappingBind =
            ObjectCalls.getMethodBind("UPNPDevice", "delete_port_mapping", DELETE_PORT_MAPPING_HASH)

        private const val SET_DESCRIPTION_URL_HASH = 83702148L
        @JvmField
        val setDescriptionUrlBind =
            ObjectCalls.getMethodBind("UPNPDevice", "set_description_url", SET_DESCRIPTION_URL_HASH)

        private const val GET_DESCRIPTION_URL_HASH = 201670096L
        @JvmField
        val getDescriptionUrlBind =
            ObjectCalls.getMethodBind("UPNPDevice", "get_description_url", GET_DESCRIPTION_URL_HASH)

        private const val SET_SERVICE_TYPE_HASH = 83702148L
        @JvmField
        val setServiceTypeBind =
            ObjectCalls.getMethodBind("UPNPDevice", "set_service_type", SET_SERVICE_TYPE_HASH)

        private const val GET_SERVICE_TYPE_HASH = 201670096L
        @JvmField
        val getServiceTypeBind =
            ObjectCalls.getMethodBind("UPNPDevice", "get_service_type", GET_SERVICE_TYPE_HASH)

        private const val SET_IGD_CONTROL_URL_HASH = 83702148L
        @JvmField
        val setIgdControlUrlBind =
            ObjectCalls.getMethodBind("UPNPDevice", "set_igd_control_url", SET_IGD_CONTROL_URL_HASH)

        private const val GET_IGD_CONTROL_URL_HASH = 201670096L
        @JvmField
        val getIgdControlUrlBind =
            ObjectCalls.getMethodBind("UPNPDevice", "get_igd_control_url", GET_IGD_CONTROL_URL_HASH)

        private const val SET_IGD_SERVICE_TYPE_HASH = 83702148L
        @JvmField
        val setIgdServiceTypeBind =
            ObjectCalls.getMethodBind("UPNPDevice", "set_igd_service_type", SET_IGD_SERVICE_TYPE_HASH)

        private const val GET_IGD_SERVICE_TYPE_HASH = 201670096L
        @JvmField
        val getIgdServiceTypeBind =
            ObjectCalls.getMethodBind("UPNPDevice", "get_igd_service_type", GET_IGD_SERVICE_TYPE_HASH)

        private const val SET_IGD_OUR_ADDR_HASH = 83702148L
        @JvmField
        val setIgdOurAddrBind =
            ObjectCalls.getMethodBind("UPNPDevice", "set_igd_our_addr", SET_IGD_OUR_ADDR_HASH)

        private const val GET_IGD_OUR_ADDR_HASH = 201670096L
        @JvmField
        val getIgdOurAddrBind =
            ObjectCalls.getMethodBind("UPNPDevice", "get_igd_our_addr", GET_IGD_OUR_ADDR_HASH)

        private const val SET_IGD_STATUS_HASH = 519504122L
        @JvmField
        val setIgdStatusBind =
            ObjectCalls.getMethodBind("UPNPDevice", "set_igd_status", SET_IGD_STATUS_HASH)

        private const val GET_IGD_STATUS_HASH = 180887011L
        @JvmField
        val getIgdStatusBind =
            ObjectCalls.getMethodBind("UPNPDevice", "get_igd_status", GET_IGD_STATUS_HASH)
    }
}
