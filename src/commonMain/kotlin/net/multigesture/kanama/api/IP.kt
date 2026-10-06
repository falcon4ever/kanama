package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Internet protocol (IP) support functions such as DNS resolution.
 *
 * Generated from Godot docs: IP
 */
object IP {
    private inline val singleton: RawSegment
        get() = Binds.singleton

    const val RESOLVER_MAX_QUERIES: Long = 256L
    const val RESOLVER_INVALID_ID: Long = -1L

    /**
     * Returns a given hostname's IPv4 or IPv6 address when resolved (blocking-type method). The
     * address type returned depends on the `Type` constant given as `ip_type`.
     *
     * Generated from Godot docs: IP.resolve_hostname
     */
    @JvmStatic
    fun resolveHostname(host: String, ipType: IP.Type = IP.Type.ANY): String {
        return ObjectCalls.ptrcallWithStringAndLongArgRetString(Binds.resolveHostnameBind, singleton, host, ipType.value)
    }

    /**
     * Resolves a given hostname in a blocking way. Addresses are returned as an `Array` of IPv4 or
     * IPv6 addresses depending on `ip_type`.
     *
     * Generated from Godot docs: IP.resolve_hostname_addresses
     */
    @JvmStatic
    fun resolveHostnameAddresses(host: String, ipType: IP.Type = IP.Type.ANY): List<String> {
        return ObjectCalls.ptrcallWithStringAndLongArgRetPackedStringList(Binds.resolveHostnameAddressesBind, singleton, host, ipType.value)
    }

    /**
     * Creates a queue item to resolve a hostname to an IPv4 or IPv6 address depending on the `Type`
     * constant given as `ip_type`. Returns the queue ID if successful, or `RESOLVER_INVALID_ID` on
     * error.
     *
     * Generated from Godot docs: IP.resolve_hostname_queue_item
     */
    @JvmStatic
    fun resolveHostnameQueueItem(host: String, ipType: IP.Type = IP.Type.ANY): Int {
        return ObjectCalls.ptrcallWithStringAndLongArgRetInt(Binds.resolveHostnameQueueItemBind, singleton, host, ipType.value)
    }

    /**
     * Returns a queued hostname's status as a `ResolverStatus` constant, given its queue `id`.
     *
     * Generated from Godot docs: IP.get_resolve_item_status
     */
    @JvmStatic
    fun getResolveItemStatus(id: Int): IP.ResolverStatus {
        return IP.ResolverStatus(ObjectCalls.ptrcallWithIntArgRetLong(Binds.getResolveItemStatusBind, singleton, id))
    }

    /**
     * Returns a queued hostname's IP address, given its queue `id`. Returns an empty string on error
     * or if resolution hasn't happened yet (see `get_resolve_item_status`).
     *
     * Generated from Godot docs: IP.get_resolve_item_address
     */
    @JvmStatic
    fun getResolveItemAddress(id: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(Binds.getResolveItemAddressBind, singleton, id)
    }

    /**
     * Returns resolved addresses, or an empty array if an error happened or resolution didn't happen
     * yet (see `get_resolve_item_status`).
     *
     * Generated from Godot docs: IP.get_resolve_item_addresses
     */
    @JvmStatic
    fun getResolveItemAddresses(id: Int): List<Any?> {
        return ObjectCalls.ptrcallWithIntArgRetArray(Binds.getResolveItemAddressesBind, singleton, id)
    }

    /**
     * Removes a given item `id` from the queue. This should be used to free a queue after it has
     * completed to enable more queries to happen.
     *
     * Generated from Godot docs: IP.erase_resolve_item
     */
    @JvmStatic
    fun eraseResolveItem(id: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.eraseResolveItemBind, singleton, id)
    }

    /**
     * Returns all the user's current IPv4 and IPv6 addresses as an array.
     *
     * Generated from Godot docs: IP.get_local_addresses
     */
    @JvmStatic
    fun getLocalAddresses(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(Binds.getLocalAddressesBind, singleton)
    }

    /**
     * Returns all network adapters as an array.
     *
     * Generated from Godot docs: IP.get_local_interfaces
     */
    @JvmStatic
    fun getLocalInterfaces(): List<Map<String, Any?>> {
        return ObjectCalls.ptrcallNoArgsRetDictionaryList(Binds.getLocalInterfacesBind, singleton)
    }

    /**
     * Removes all of a `hostname`'s cached references. If no `hostname` is given, all cached IP
     * addresses are removed.
     *
     * Generated from Godot docs: IP.clear_cache
     */
    @JvmStatic
    fun clearCache(hostname: String = "") {
        ObjectCalls.ptrcallWithStringArg(Binds.clearCacheBind, singleton, hostname)
    }

    /**
     * Godot's `IP.ResolverStatus` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`IP.ResolverStatus.<NAME>`).
     *
     * Generated from Godot docs: IP.ResolverStatus
     */
    @JvmInline
    value class ResolverStatus(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * DNS hostname resolver status: No status.
             *
             * Generated from Godot docs: IP.RESOLVER_STATUS_NONE
             */
            val NONE: ResolverStatus get() = ResolverStatus(0L)
            /**
             * DNS hostname resolver status: Waiting.
             *
             * Generated from Godot docs: IP.RESOLVER_STATUS_WAITING
             */
            val WAITING: ResolverStatus get() = ResolverStatus(1L)
            /**
             * DNS hostname resolver status: Done.
             *
             * Generated from Godot docs: IP.RESOLVER_STATUS_DONE
             */
            val DONE: ResolverStatus get() = ResolverStatus(2L)
            /**
             * DNS hostname resolver status: Error.
             *
             * Generated from Godot docs: IP.RESOLVER_STATUS_ERROR
             */
            val ERROR: ResolverStatus get() = ResolverStatus(3L)
        }
    }

    /**
     * Godot's `IP.Type` enum as a typed value: `.value` is the raw number Godot uses, and the
     * companion holds the named values (`IP.Type.<NAME>`).
     *
     * Generated from Godot docs: IP.Type
     */
    @JvmInline
    value class Type(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Address type: None.
             *
             * Generated from Godot docs: IP.TYPE_NONE
             */
            val NONE: Type get() = Type(0L)
            /**
             * Address type: Internet protocol version 4 (IPv4).
             *
             * Generated from Godot docs: IP.TYPE_IPV4
             */
            val IPV4: Type get() = Type(1L)
            /**
             * Address type: Internet protocol version 6 (IPv6).
             *
             * Generated from Godot docs: IP.TYPE_IPV6
             */
            val IPV6: Type get() = Type(2L)
            /**
             * Address type: Any.
             *
             * Generated from Godot docs: IP.TYPE_ANY
             */
            val ANY: Type get() = Type(3L)
        }
    }

    @JvmStatic
    fun fromHandle(handle: GodotHandle): IP? =
        wrap(handle.segment)

    internal fun wrap(handle: RawSegment): IP? =
        if (handle.address() == 0L) null else this

    private object Binds {
        @JvmField
        val singleton = ObjectCalls.getSingleton("IP")

        private const val RESOLVE_HOSTNAME_HASH = 4283295457L
        @JvmField
        val resolveHostnameBind =
            ObjectCalls.getMethodBind("IP", "resolve_hostname", RESOLVE_HOSTNAME_HASH)

        private const val RESOLVE_HOSTNAME_ADDRESSES_HASH = 773767525L
        @JvmField
        val resolveHostnameAddressesBind =
            ObjectCalls.getMethodBind("IP", "resolve_hostname_addresses", RESOLVE_HOSTNAME_ADDRESSES_HASH)

        private const val RESOLVE_HOSTNAME_QUEUE_ITEM_HASH = 1749894742L
        @JvmField
        val resolveHostnameQueueItemBind =
            ObjectCalls.getMethodBind("IP", "resolve_hostname_queue_item", RESOLVE_HOSTNAME_QUEUE_ITEM_HASH)

        private const val GET_RESOLVE_ITEM_STATUS_HASH = 3812250196L
        @JvmField
        val getResolveItemStatusBind =
            ObjectCalls.getMethodBind("IP", "get_resolve_item_status", GET_RESOLVE_ITEM_STATUS_HASH)

        private const val GET_RESOLVE_ITEM_ADDRESS_HASH = 844755477L
        @JvmField
        val getResolveItemAddressBind =
            ObjectCalls.getMethodBind("IP", "get_resolve_item_address", GET_RESOLVE_ITEM_ADDRESS_HASH)

        private const val GET_RESOLVE_ITEM_ADDRESSES_HASH = 663333327L
        @JvmField
        val getResolveItemAddressesBind =
            ObjectCalls.getMethodBind("IP", "get_resolve_item_addresses", GET_RESOLVE_ITEM_ADDRESSES_HASH)

        private const val ERASE_RESOLVE_ITEM_HASH = 1286410249L
        @JvmField
        val eraseResolveItemBind =
            ObjectCalls.getMethodBind("IP", "erase_resolve_item", ERASE_RESOLVE_ITEM_HASH)

        private const val GET_LOCAL_ADDRESSES_HASH = 1139954409L
        @JvmField
        val getLocalAddressesBind =
            ObjectCalls.getMethodBind("IP", "get_local_addresses", GET_LOCAL_ADDRESSES_HASH)

        private const val GET_LOCAL_INTERFACES_HASH = 3995934104L
        @JvmField
        val getLocalInterfacesBind =
            ObjectCalls.getMethodBind("IP", "get_local_interfaces", GET_LOCAL_INTERFACES_HASH)

        private const val CLEAR_CACHE_HASH = 3005725572L
        @JvmField
        val clearCacheBind =
            ObjectCalls.getMethodBind("IP", "clear_cache", CLEAR_CACHE_HASH)
    }
}
