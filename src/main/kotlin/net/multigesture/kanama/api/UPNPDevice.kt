package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: UPNPDevice
 */
class UPNPDevice(handle: MemorySegment) : RefCounted(handle) {
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

    var igdStatus: Long
        @JvmName("igdStatusProperty")
        get() = getIgdStatus()
        @JvmName("setIgdStatusProperty")
        set(value) = setIgdStatus(value)

    fun isValidGateway(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isValidGatewayBind, handle)
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

    fun setDescriptionUrl(url: String) {
        ObjectCalls.ptrcallWithStringArg(setDescriptionUrlBind, handle, url)
    }

    fun getDescriptionUrl(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getDescriptionUrlBind, handle)
    }

    fun setServiceType(type: String) {
        ObjectCalls.ptrcallWithStringArg(setServiceTypeBind, handle, type)
    }

    fun getServiceType(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getServiceTypeBind, handle)
    }

    fun setIgdControlUrl(url: String) {
        ObjectCalls.ptrcallWithStringArg(setIgdControlUrlBind, handle, url)
    }

    fun getIgdControlUrl(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getIgdControlUrlBind, handle)
    }

    fun setIgdServiceType(type: String) {
        ObjectCalls.ptrcallWithStringArg(setIgdServiceTypeBind, handle, type)
    }

    fun getIgdServiceType(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getIgdServiceTypeBind, handle)
    }

    fun setIgdOurAddr(addr: String) {
        ObjectCalls.ptrcallWithStringArg(setIgdOurAddrBind, handle, addr)
    }

    fun getIgdOurAddr(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getIgdOurAddrBind, handle)
    }

    fun setIgdStatus(status: Long) {
        ObjectCalls.ptrcallWithLongArg(setIgdStatusBind, handle, status)
    }

    fun getIgdStatus(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getIgdStatusBind, handle)
    }

    companion object {
        const val IGD_STATUS_OK: Long = 0L
        const val IGD_STATUS_HTTP_ERROR: Long = 1L
        const val IGD_STATUS_HTTP_EMPTY: Long = 2L
        const val IGD_STATUS_NO_URLS: Long = 3L
        const val IGD_STATUS_NO_IGD: Long = 4L
        const val IGD_STATUS_DISCONNECTED: Long = 5L
        const val IGD_STATUS_UNKNOWN_DEVICE: Long = 6L
        const val IGD_STATUS_INVALID_CONTROL: Long = 7L
        const val IGD_STATUS_MALLOC_ERROR: Long = 8L
        const val IGD_STATUS_UNKNOWN_ERROR: Long = 9L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): UPNPDevice? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): UPNPDevice? =
            if (handle.address() == 0L) null else UPNPDevice(handle)

        private const val IS_VALID_GATEWAY_HASH = 36873697L
        private val isValidGatewayBind by lazy {
            ObjectCalls.getMethodBind("UPNPDevice", "is_valid_gateway", IS_VALID_GATEWAY_HASH)
        }

        private const val QUERY_EXTERNAL_ADDRESS_HASH = 201670096L
        private val queryExternalAddressBind by lazy {
            ObjectCalls.getMethodBind("UPNPDevice", "query_external_address", QUERY_EXTERNAL_ADDRESS_HASH)
        }

        private const val ADD_PORT_MAPPING_HASH = 818314583L
        private val addPortMappingBind by lazy {
            ObjectCalls.getMethodBind("UPNPDevice", "add_port_mapping", ADD_PORT_MAPPING_HASH)
        }

        private const val DELETE_PORT_MAPPING_HASH = 3444187325L
        private val deletePortMappingBind by lazy {
            ObjectCalls.getMethodBind("UPNPDevice", "delete_port_mapping", DELETE_PORT_MAPPING_HASH)
        }

        private const val SET_DESCRIPTION_URL_HASH = 83702148L
        private val setDescriptionUrlBind by lazy {
            ObjectCalls.getMethodBind("UPNPDevice", "set_description_url", SET_DESCRIPTION_URL_HASH)
        }

        private const val GET_DESCRIPTION_URL_HASH = 201670096L
        private val getDescriptionUrlBind by lazy {
            ObjectCalls.getMethodBind("UPNPDevice", "get_description_url", GET_DESCRIPTION_URL_HASH)
        }

        private const val SET_SERVICE_TYPE_HASH = 83702148L
        private val setServiceTypeBind by lazy {
            ObjectCalls.getMethodBind("UPNPDevice", "set_service_type", SET_SERVICE_TYPE_HASH)
        }

        private const val GET_SERVICE_TYPE_HASH = 201670096L
        private val getServiceTypeBind by lazy {
            ObjectCalls.getMethodBind("UPNPDevice", "get_service_type", GET_SERVICE_TYPE_HASH)
        }

        private const val SET_IGD_CONTROL_URL_HASH = 83702148L
        private val setIgdControlUrlBind by lazy {
            ObjectCalls.getMethodBind("UPNPDevice", "set_igd_control_url", SET_IGD_CONTROL_URL_HASH)
        }

        private const val GET_IGD_CONTROL_URL_HASH = 201670096L
        private val getIgdControlUrlBind by lazy {
            ObjectCalls.getMethodBind("UPNPDevice", "get_igd_control_url", GET_IGD_CONTROL_URL_HASH)
        }

        private const val SET_IGD_SERVICE_TYPE_HASH = 83702148L
        private val setIgdServiceTypeBind by lazy {
            ObjectCalls.getMethodBind("UPNPDevice", "set_igd_service_type", SET_IGD_SERVICE_TYPE_HASH)
        }

        private const val GET_IGD_SERVICE_TYPE_HASH = 201670096L
        private val getIgdServiceTypeBind by lazy {
            ObjectCalls.getMethodBind("UPNPDevice", "get_igd_service_type", GET_IGD_SERVICE_TYPE_HASH)
        }

        private const val SET_IGD_OUR_ADDR_HASH = 83702148L
        private val setIgdOurAddrBind by lazy {
            ObjectCalls.getMethodBind("UPNPDevice", "set_igd_our_addr", SET_IGD_OUR_ADDR_HASH)
        }

        private const val GET_IGD_OUR_ADDR_HASH = 201670096L
        private val getIgdOurAddrBind by lazy {
            ObjectCalls.getMethodBind("UPNPDevice", "get_igd_our_addr", GET_IGD_OUR_ADDR_HASH)
        }

        private const val SET_IGD_STATUS_HASH = 519504122L
        private val setIgdStatusBind by lazy {
            ObjectCalls.getMethodBind("UPNPDevice", "set_igd_status", SET_IGD_STATUS_HASH)
        }

        private const val GET_IGD_STATUS_HASH = 180887011L
        private val getIgdStatusBind by lazy {
            ObjectCalls.getMethodBind("UPNPDevice", "get_igd_status", GET_IGD_STATUS_HASH)
        }
    }
}
