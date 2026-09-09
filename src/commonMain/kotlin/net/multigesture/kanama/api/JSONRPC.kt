package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * A helper to handle dictionaries which look like JSONRPC documents.
 *
 * Generated from Godot docs: JSONRPC
 */
open class JSONRPC(handle: MemorySegment) : GodotObject(handle) {
    /**
     * Registers a callback for the given method name. - `name`: The name that clients can use to
     * access the callback. - `callback`: The callback which will handle the specified method.
     *
     * Generated from Godot docs: JSONRPC.set_method
     */
    fun setMethod(name: String, callback: GodotCallable) {
        ObjectCalls.ptrcallWithStringCallableArgs(setMethodBind, handle, name, callback.target.handle, callback.method)
    }

    fun processString(action: String): String {
        return ObjectCalls.ptrcallWithStringArgRetString(processStringBind, handle, action)
    }

    companion object {
        const val PARSE_ERROR: Long = -32700L
        const val INVALID_REQUEST: Long = -32600L
        const val METHOD_NOT_FOUND: Long = -32601L
        const val INVALID_PARAMS: Long = -32602L
        const val INTERNAL_ERROR: Long = -32603L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): JSONRPC? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): JSONRPC? =
            if (handle.address() == 0L) null else JSONRPC(handle)

        private const val SET_METHOD_HASH = 2137474292L
        private val setMethodBind by lazy {
            ObjectCalls.getMethodBind("JSONRPC", "set_method", SET_METHOD_HASH)
        }

        private const val PROCESS_STRING_HASH = 1703090593L
        private val processStringBind by lazy {
            ObjectCalls.getMethodBind("JSONRPC", "process_string", PROCESS_STRING_HASH)
        }
    }
}
