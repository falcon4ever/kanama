package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A singleton for managing `NavigationServer2D` implementations.
 *
 * Generated from Godot docs: NavigationServer2DManager
 */
object NavigationServer2DManager {
    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("NavigationServer2DManager")
    }

    /**
     * Registers a `NavigationServer2D` implementation by passing a `name` and a `Callable` that
     * returns a `NavigationServer2D` object.
     *
     * Generated from Godot docs: NavigationServer2DManager.register_server
     */
    @JvmStatic
    fun registerServer(name: String, createCallback: GodotCallable) {
        ObjectCalls.ptrcallWithStringCallableArgs(registerServerBind, singleton, name, createCallback.target.handle, createCallback.method)
    }

    /**
     * Sets the default `NavigationServer2D` implementation to the one identified by `name`, if
     * `priority` is greater than the priority of the current default implementation.
     *
     * Generated from Godot docs: NavigationServer2DManager.set_default_server
     */
    @JvmStatic
    fun setDefaultServer(name: String, priority: Int) {
        ObjectCalls.ptrcallWithStringAndIntArg(setDefaultServerBind, singleton, name, priority)
    }

    @JvmStatic
    fun fromHandle(handle: MemorySegment): NavigationServer2DManager? =
        wrap(handle)

    internal fun wrap(handle: MemorySegment): NavigationServer2DManager? =
        if (handle.address() == 0L) null else this

    private const val REGISTER_SERVER_HASH = 2137474292L
    private val registerServerBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer2DManager", "register_server", REGISTER_SERVER_HASH)
    }

    private const val SET_DEFAULT_SERVER_HASH = 2956805083L
    private val setDefaultServerBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer2DManager", "set_default_server", SET_DEFAULT_SERVER_HASH)
    }
}
