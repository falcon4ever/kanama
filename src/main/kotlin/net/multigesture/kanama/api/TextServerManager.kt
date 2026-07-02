package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A singleton for managing `TextServer` implementations.
 *
 * Generated from Godot docs: TextServerManager
 */
object TextServerManager {
    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("TextServerManager")
    }

    @JvmStatic
    fun addInterface(interfaceValue: TextServer?) {
        ObjectCalls.ptrcallWithObjectArgs(addInterfaceBind, singleton, listOf(interfaceValue?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    @JvmStatic
    fun getInterfaceCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getInterfaceCountBind, singleton)
    }

    @JvmStatic
    fun removeInterface(interfaceValue: TextServer?) {
        ObjectCalls.ptrcallWithObjectArgs(removeInterfaceBind, singleton, listOf(interfaceValue?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    @JvmStatic
    fun getInterface(idx: Int): TextServer? {
        return TextServer.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getInterfaceBind, singleton, idx))
    }

    @JvmStatic
    fun getInterfaces(): List<Map<String, Any?>> {
        return ObjectCalls.ptrcallNoArgsRetDictionaryList(getInterfacesBind, singleton)
    }

    @JvmStatic
    fun findInterface(name: String): TextServer? {
        return TextServer.wrap(ObjectCalls.ptrcallWithStringArgRetObject(findInterfaceBind, singleton, name))
    }

    @JvmStatic
    fun setPrimaryInterface(index: TextServer?) {
        ObjectCalls.ptrcallWithObjectArgs(setPrimaryInterfaceBind, singleton, listOf(index?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    @JvmStatic
    fun getPrimaryInterface(): TextServer? {
        return TextServer.wrap(ObjectCalls.ptrcallNoArgsRetObject(getPrimaryInterfaceBind, singleton))
    }

    object Signals {
        const val interfaceAdded: String = "interface_added"
        const val interfaceRemoved: String = "interface_removed"
    }

    @JvmStatic
    fun fromHandle(handle: MemorySegment): TextServerManager? =
        wrap(handle)

    internal fun wrap(handle: MemorySegment): TextServerManager? =
        if (handle.address() == 0L) null else this

    private const val ADD_INTERFACE_HASH = 1799689403L
    private val addInterfaceBind by lazy {
        ObjectCalls.getMethodBind("TextServerManager", "add_interface", ADD_INTERFACE_HASH)
    }

    private const val GET_INTERFACE_COUNT_HASH = 3905245786L
    private val getInterfaceCountBind by lazy {
        ObjectCalls.getMethodBind("TextServerManager", "get_interface_count", GET_INTERFACE_COUNT_HASH)
    }

    private const val REMOVE_INTERFACE_HASH = 1799689403L
    private val removeInterfaceBind by lazy {
        ObjectCalls.getMethodBind("TextServerManager", "remove_interface", REMOVE_INTERFACE_HASH)
    }

    private const val GET_INTERFACE_HASH = 1672475555L
    private val getInterfaceBind by lazy {
        ObjectCalls.getMethodBind("TextServerManager", "get_interface", GET_INTERFACE_HASH)
    }

    private const val GET_INTERFACES_HASH = 3995934104L
    private val getInterfacesBind by lazy {
        ObjectCalls.getMethodBind("TextServerManager", "get_interfaces", GET_INTERFACES_HASH)
    }

    private const val FIND_INTERFACE_HASH = 2240905781L
    private val findInterfaceBind by lazy {
        ObjectCalls.getMethodBind("TextServerManager", "find_interface", FIND_INTERFACE_HASH)
    }

    private const val SET_PRIMARY_INTERFACE_HASH = 1799689403L
    private val setPrimaryInterfaceBind by lazy {
        ObjectCalls.getMethodBind("TextServerManager", "set_primary_interface", SET_PRIMARY_INTERFACE_HASH)
    }

    private const val GET_PRIMARY_INTERFACE_HASH = 905850878L
    private val getPrimaryInterfaceBind by lazy {
        ObjectCalls.getMethodBind("TextServerManager", "get_primary_interface", GET_PRIMARY_INTERFACE_HASH)
    }
}
