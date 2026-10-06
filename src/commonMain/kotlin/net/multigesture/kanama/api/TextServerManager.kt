package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A singleton for managing `TextServer` implementations.
 *
 * Generated from Godot docs: TextServerManager
 */
object TextServerManager {
    private inline val singleton: RawSegment
        get() = Binds.singleton

    /**
     * Registers a `TextServer` interface.
     *
     * Generated from Godot docs: TextServerManager.add_interface
     */
    @JvmStatic
    fun addInterface(interfaceValue: TextServer?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.addInterfaceBind, singleton, listOf(interfaceValue?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Returns the number of interfaces currently registered.
     *
     * Generated from Godot docs: TextServerManager.get_interface_count
     */
    @JvmStatic
    fun getInterfaceCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getInterfaceCountBind, singleton)
    }

    /**
     * Removes an interface. All fonts and shaped text caches should be freed before removing an
     * interface.
     *
     * Generated from Godot docs: TextServerManager.remove_interface
     */
    @JvmStatic
    fun removeInterface(interfaceValue: TextServer?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.removeInterfaceBind, singleton, listOf(interfaceValue?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Returns the interface registered at a given index.
     *
     * Generated from Godot docs: TextServerManager.get_interface
     */
    @JvmStatic
    fun getInterface(idx: Int): TextServer? {
        return TextServer.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getInterfaceBind, singleton, idx))
    }

    /**
     * Returns a list of available interfaces, with the index and name of each interface.
     *
     * Generated from Godot docs: TextServerManager.get_interfaces
     */
    @JvmStatic
    fun getInterfaces(): List<Map<String, Any?>> {
        return ObjectCalls.ptrcallNoArgsRetDictionaryList(Binds.getInterfacesBind, singleton)
    }

    /**
     * Finds an interface by its `name`.
     *
     * Generated from Godot docs: TextServerManager.find_interface
     */
    @JvmStatic
    fun findInterface(name: String): TextServer? {
        return TextServer.wrapOwned(ObjectCalls.ptrcallWithStringArgRetObject(Binds.findInterfaceBind, singleton, name))
    }

    /**
     * Sets the primary `TextServer` interface.
     *
     * Generated from Godot docs: TextServerManager.set_primary_interface
     */
    @JvmStatic
    fun setPrimaryInterface(index: TextServer?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setPrimaryInterfaceBind, singleton, listOf(index?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Returns the primary `TextServer` interface currently in use.
     *
     * Generated from Godot docs: TextServerManager.get_primary_interface
     */
    @JvmStatic
    fun getPrimaryInterface(): TextServer? {
        return TextServer.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getPrimaryInterfaceBind, singleton))
    }

    /** Signal `interface_added(interface_name: StringName)`; see [TypedSignal]. */
    val interfaceAdded: Signal1<String>
        @JvmName("interfaceAddedTypedSignal")
        get() = Signal1(GodotObject(GodotHandle(singleton)), "interface_added", SignalArgType.STRING)

    /** Signal `interface_removed(interface_name: StringName)`; see [TypedSignal]. */
    val interfaceRemoved: Signal1<String>
        @JvmName("interfaceRemovedTypedSignal")
        get() = Signal1(GodotObject(GodotHandle(singleton)), "interface_removed", SignalArgType.STRING)

    object Signals {
        const val interfaceAdded: String = "interface_added"
        const val interfaceRemoved: String = "interface_removed"
    }

    @JvmStatic
    fun fromHandle(handle: GodotHandle): TextServerManager? =
        wrap(handle.segment)

    internal fun wrap(handle: RawSegment): TextServerManager? =
        if (handle.address() == 0L) null else this

    private object Binds {
        @JvmField
        val singleton = ObjectCalls.getSingleton("TextServerManager")

        private const val ADD_INTERFACE_HASH = 1799689403L
        @JvmField
        val addInterfaceBind =
            ObjectCalls.getMethodBind("TextServerManager", "add_interface", ADD_INTERFACE_HASH)

        private const val GET_INTERFACE_COUNT_HASH = 3905245786L
        @JvmField
        val getInterfaceCountBind =
            ObjectCalls.getMethodBind("TextServerManager", "get_interface_count", GET_INTERFACE_COUNT_HASH)

        private const val REMOVE_INTERFACE_HASH = 1799689403L
        @JvmField
        val removeInterfaceBind =
            ObjectCalls.getMethodBind("TextServerManager", "remove_interface", REMOVE_INTERFACE_HASH)

        private const val GET_INTERFACE_HASH = 1672475555L
        @JvmField
        val getInterfaceBind =
            ObjectCalls.getMethodBind("TextServerManager", "get_interface", GET_INTERFACE_HASH)

        private const val GET_INTERFACES_HASH = 3995934104L
        @JvmField
        val getInterfacesBind =
            ObjectCalls.getMethodBind("TextServerManager", "get_interfaces", GET_INTERFACES_HASH)

        private const val FIND_INTERFACE_HASH = 2240905781L
        @JvmField
        val findInterfaceBind =
            ObjectCalls.getMethodBind("TextServerManager", "find_interface", FIND_INTERFACE_HASH)

        private const val SET_PRIMARY_INTERFACE_HASH = 1799689403L
        @JvmField
        val setPrimaryInterfaceBind =
            ObjectCalls.getMethodBind("TextServerManager", "set_primary_interface", SET_PRIMARY_INTERFACE_HASH)

        private const val GET_PRIMARY_INTERFACE_HASH = 905850878L
        @JvmField
        val getPrimaryInterfaceBind =
            ObjectCalls.getMethodBind("TextServerManager", "get_primary_interface", GET_PRIMARY_INTERFACE_HASH)
    }
}
