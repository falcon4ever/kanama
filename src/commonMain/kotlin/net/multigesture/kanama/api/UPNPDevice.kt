package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
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

    fun isValidGateway(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isValidGatewayBind, segment)
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

    fun setDescriptionUrl(url: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setDescriptionUrlBind, segment, url)
    }

    fun getDescriptionUrl(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getDescriptionUrlBind, segment)
    }

    fun setServiceType(type: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setServiceTypeBind, segment, type)
    }

    fun getServiceType(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getServiceTypeBind, segment)
    }

    fun setIgdControlUrl(url: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setIgdControlUrlBind, segment, url)
    }

    fun getIgdControlUrl(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getIgdControlUrlBind, segment)
    }

    fun setIgdServiceType(type: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setIgdServiceTypeBind, segment, type)
    }

    fun getIgdServiceType(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getIgdServiceTypeBind, segment)
    }

    fun setIgdOurAddr(addr: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setIgdOurAddrBind, segment, addr)
    }

    fun getIgdOurAddr(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getIgdOurAddrBind, segment)
    }

    fun setIgdStatus(status: UPNPDevice.IGDStatus) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setIgdStatusBind, segment, status.value)
    }

    fun getIgdStatus(): UPNPDevice.IGDStatus {
        checkOpen()
        return UPNPDevice.IGDStatus(ObjectCalls.ptrcallNoArgsRetLong(Binds.getIgdStatusBind, segment))
    }

    @JvmInline
    value class IGDStatus(override val value: Long) : GodotEnumValue {
        companion object {
            val OK: IGDStatus get() = IGDStatus(0L)
            val HTTP_ERROR: IGDStatus get() = IGDStatus(1L)
            val HTTP_EMPTY: IGDStatus get() = IGDStatus(2L)
            val NO_URLS: IGDStatus get() = IGDStatus(3L)
            val NO_IGD: IGDStatus get() = IGDStatus(4L)
            val DISCONNECTED: IGDStatus get() = IGDStatus(5L)
            val UNKNOWN_DEVICE: IGDStatus get() = IGDStatus(6L)
            val INVALID_CONTROL: IGDStatus get() = IGDStatus(7L)
            val MALLOC_ERROR: IGDStatus get() = IGDStatus(8L)
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
