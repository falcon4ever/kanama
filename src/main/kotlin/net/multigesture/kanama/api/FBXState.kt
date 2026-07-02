package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: FBXState
 */
class FBXState(handle: MemorySegment) : GLTFState(handle) {
    var allowGeometryHelperNodes: Boolean
        @JvmName("allowGeometryHelperNodesProperty")
        get() = getAllowGeometryHelperNodes()
        @JvmName("setAllowGeometryHelperNodesProperty")
        set(value) = setAllowGeometryHelperNodes(value)

    fun getAllowGeometryHelperNodes(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getAllowGeometryHelperNodesBind, handle)
    }

    fun setAllowGeometryHelperNodes(allow: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAllowGeometryHelperNodesBind, handle, allow)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): FBXState? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): FBXState? =
            if (handle.address() == 0L) null else FBXState(handle)

        private const val GET_ALLOW_GEOMETRY_HELPER_NODES_HASH = 2240911060L
        private val getAllowGeometryHelperNodesBind by lazy {
            ObjectCalls.getMethodBind("FBXState", "get_allow_geometry_helper_nodes", GET_ALLOW_GEOMETRY_HELPER_NODES_HASH)
        }

        private const val SET_ALLOW_GEOMETRY_HELPER_NODES_HASH = 2586408642L
        private val setAllowGeometryHelperNodesBind by lazy {
            ObjectCalls.getMethodBind("FBXState", "set_allow_geometry_helper_nodes", SET_ALLOW_GEOMETRY_HELPER_NODES_HASH)
        }
    }
}
