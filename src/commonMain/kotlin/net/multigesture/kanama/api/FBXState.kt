package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: FBXState
 */
class FBXState(handle: GodotHandle) : GLTFState(handle) {
    var allowGeometryHelperNodes: Boolean
        @JvmName("allowGeometryHelperNodesProperty")
        get() = getAllowGeometryHelperNodes()
        @JvmName("setAllowGeometryHelperNodesProperty")
        set(value) = setAllowGeometryHelperNodes(value)

    fun getAllowGeometryHelperNodes(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getAllowGeometryHelperNodesBind, segment)
    }

    fun setAllowGeometryHelperNodes(allow: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setAllowGeometryHelperNodesBind, segment, allow)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): FBXState? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): FBXState? =
            if (handle.address() == 0L) null else FBXState(GodotHandle(handle))

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
