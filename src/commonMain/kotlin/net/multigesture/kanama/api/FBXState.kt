package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

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
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getAllowGeometryHelperNodesBind, segment)
    }

    fun setAllowGeometryHelperNodes(allow: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setAllowGeometryHelperNodesBind, segment, allow)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): FBXState? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): FBXState? =
            if (handle.address() == 0L) null else RefCounted.owned(FBXState(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): FBXState? =
            if (handle.address() == 0L) null else FBXState(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_ALLOW_GEOMETRY_HELPER_NODES_HASH = 2240911060L
        @JvmField
        val getAllowGeometryHelperNodesBind =
            ObjectCalls.getMethodBind("FBXState", "get_allow_geometry_helper_nodes", GET_ALLOW_GEOMETRY_HELPER_NODES_HASH)

        private const val SET_ALLOW_GEOMETRY_HELPER_NODES_HASH = 2586408642L
        @JvmField
        val setAllowGeometryHelperNodesBind =
            ObjectCalls.getMethodBind("FBXState", "set_allow_geometry_helper_nodes", SET_ALLOW_GEOMETRY_HELPER_NODES_HASH)
    }
}
