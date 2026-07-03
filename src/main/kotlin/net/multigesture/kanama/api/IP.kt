package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Internet protocol (IP) support functions such as DNS resolution.
 *
 * Generated from Godot docs: IP
 */
object IP {
    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("IP")
    }

    const val RESOLVER_MAX_QUERIES: Long = 256L
    const val RESOLVER_INVALID_ID: Long = -1L
    const val RESOLVER_STATUS_NONE: Long = 0L
    const val RESOLVER_STATUS_WAITING: Long = 1L
    const val RESOLVER_STATUS_DONE: Long = 2L
    const val RESOLVER_STATUS_ERROR: Long = 3L
    const val TYPE_NONE: Long = 0L
    const val TYPE_IPV4: Long = 1L
    const val TYPE_IPV6: Long = 2L
    const val TYPE_ANY: Long = 3L

    /**
     * Returns a given hostname's IPv4 or IPv6 address when resolved (blocking-type method). The
     * address type returned depends on the `Type` constant given as `ip_type`.
     *
     * Generated from Godot docs: IP.resolve_hostname
     */
    @JvmStatic
    fun resolveHostname(host: String, ipType: Long = 3L): String {
        return ObjectCalls.ptrcallWithStringAndLongArgRetString(resolveHostnameBind, singleton, host, ipType)
    }

    /**
     * Resolves a given hostname in a blocking way. Addresses are returned as an `Array` of IPv4 or
     * IPv6 addresses depending on `ip_type`.
     *
     * Generated from Godot docs: IP.resolve_hostname_addresses
     */
    @JvmStatic
    fun resolveHostnameAddresses(host: String, ipType: Long = 3L): List<String> {
        return ObjectCalls.ptrcallWithStringAndLongArgRetPackedStringList(resolveHostnameAddressesBind, singleton, host, ipType)
    }

    /**
     * Creates a queue item to resolve a hostname to an IPv4 or IPv6 address depending on the `Type`
     * constant given as `ip_type`. Returns the queue ID if successful, or `RESOLVER_INVALID_ID` on
     * error.
     *
     * Generated from Godot docs: IP.resolve_hostname_queue_item
     */
    @JvmStatic
    fun resolveHostnameQueueItem(host: String, ipType: Long = 3L): Int {
        return ObjectCalls.ptrcallWithStringAndLongArgRetInt(resolveHostnameQueueItemBind, singleton, host, ipType)
    }

    /**
     * Returns a queued hostname's status as a `ResolverStatus` constant, given its queue `id`.
     *
     * Generated from Godot docs: IP.get_resolve_item_status
     */
    @JvmStatic
    fun getResolveItemStatus(id: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getResolveItemStatusBind, singleton, id)
    }

    /**
     * Returns a queued hostname's IP address, given its queue `id`. Returns an empty string on error
     * or if resolution hasn't happened yet (see `get_resolve_item_status`).
     *
     * Generated from Godot docs: IP.get_resolve_item_address
     */
    @JvmStatic
    fun getResolveItemAddress(id: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getResolveItemAddressBind, singleton, id)
    }

    /**
     * Returns resolved addresses, or an empty array if an error happened or resolution didn't happen
     * yet (see `get_resolve_item_status`).
     *
     * Generated from Godot docs: IP.get_resolve_item_addresses
     */
    @JvmStatic
    fun getResolveItemAddresses(id: Int): List<Any?> {
        return ObjectCalls.ptrcallWithIntArgRetArray(getResolveItemAddressesBind, singleton, id)
    }

    /**
     * Removes a given item `id` from the queue. This should be used to free a queue after it has
     * completed to enable more queries to happen.
     *
     * Generated from Godot docs: IP.erase_resolve_item
     */
    @JvmStatic
    fun eraseResolveItem(id: Int) {
        ObjectCalls.ptrcallWithIntArg(eraseResolveItemBind, singleton, id)
    }

    /**
     * Returns all the user's current IPv4 and IPv6 addresses as an array.
     *
     * Generated from Godot docs: IP.get_local_addresses
     */
    @JvmStatic
    fun getLocalAddresses(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getLocalAddressesBind, singleton)
    }

    /**
     * Returns all network adapters as an array. Each adapter is a dictionary of the form:
     *
     * Generated from Godot docs: IP.get_local_interfaces
     */
    @JvmStatic
    fun getLocalInterfaces(): List<Map<String, Any?>> {
        return ObjectCalls.ptrcallNoArgsRetDictionaryList(getLocalInterfacesBind, singleton)
    }

    /**
     * Removes all of a `hostname`'s cached references. If no `hostname` is given, all cached IP
     * addresses are removed.
     *
     * Generated from Godot docs: IP.clear_cache
     */
    @JvmStatic
    fun clearCache(hostname: String = "") {
        ObjectCalls.ptrcallWithStringArg(clearCacheBind, singleton, hostname)
    }

    @JvmStatic
    fun fromHandle(handle: MemorySegment): IP? =
        wrap(handle)

    internal fun wrap(handle: MemorySegment): IP? =
        if (handle.address() == 0L) null else this

    private const val RESOLVE_HOSTNAME_HASH = 4283295457L
    private val resolveHostnameBind by lazy {
        ObjectCalls.getMethodBind("IP", "resolve_hostname", RESOLVE_HOSTNAME_HASH)
    }

    private const val RESOLVE_HOSTNAME_ADDRESSES_HASH = 773767525L
    private val resolveHostnameAddressesBind by lazy {
        ObjectCalls.getMethodBind("IP", "resolve_hostname_addresses", RESOLVE_HOSTNAME_ADDRESSES_HASH)
    }

    private const val RESOLVE_HOSTNAME_QUEUE_ITEM_HASH = 1749894742L
    private val resolveHostnameQueueItemBind by lazy {
        ObjectCalls.getMethodBind("IP", "resolve_hostname_queue_item", RESOLVE_HOSTNAME_QUEUE_ITEM_HASH)
    }

    private const val GET_RESOLVE_ITEM_STATUS_HASH = 3812250196L
    private val getResolveItemStatusBind by lazy {
        ObjectCalls.getMethodBind("IP", "get_resolve_item_status", GET_RESOLVE_ITEM_STATUS_HASH)
    }

    private const val GET_RESOLVE_ITEM_ADDRESS_HASH = 844755477L
    private val getResolveItemAddressBind by lazy {
        ObjectCalls.getMethodBind("IP", "get_resolve_item_address", GET_RESOLVE_ITEM_ADDRESS_HASH)
    }

    private const val GET_RESOLVE_ITEM_ADDRESSES_HASH = 663333327L
    private val getResolveItemAddressesBind by lazy {
        ObjectCalls.getMethodBind("IP", "get_resolve_item_addresses", GET_RESOLVE_ITEM_ADDRESSES_HASH)
    }

    private const val ERASE_RESOLVE_ITEM_HASH = 1286410249L
    private val eraseResolveItemBind by lazy {
        ObjectCalls.getMethodBind("IP", "erase_resolve_item", ERASE_RESOLVE_ITEM_HASH)
    }

    private const val GET_LOCAL_ADDRESSES_HASH = 1139954409L
    private val getLocalAddressesBind by lazy {
        ObjectCalls.getMethodBind("IP", "get_local_addresses", GET_LOCAL_ADDRESSES_HASH)
    }

    private const val GET_LOCAL_INTERFACES_HASH = 3995934104L
    private val getLocalInterfacesBind by lazy {
        ObjectCalls.getMethodBind("IP", "get_local_interfaces", GET_LOCAL_INTERFACES_HASH)
    }

    private const val CLEAR_CACHE_HASH = 3005725572L
    private val clearCacheBind by lazy {
        ObjectCalls.getMethodBind("IP", "clear_cache", CLEAR_CACHE_HASH)
    }
}
