package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * A visual shader node that instances a `VisualShaderGroup`.
 *
 * Generated from Godot docs: VisualShaderNodeGroup
 */
class VisualShaderNodeGroup(handle: GodotHandle) : VisualShaderNode(handle) {
    var group: VisualShaderGroup?
        @JvmName("groupProperty")
        get() = getGroup()
        @JvmName("setGroupProperty")
        set(value) = setGroup(value)

    /**
     * The `VisualShaderGroup` resource instanced by this node.
     *
     * Generated from Godot docs: VisualShaderNodeGroup.set_group
     */
    fun setGroup(group: VisualShaderGroup?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setGroupBind, segment, listOf(group?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(group)
    }

    /**
     * The `VisualShaderGroup` resource instanced by this node.
     *
     * Generated from Godot docs: VisualShaderNodeGroup.get_group
     */
    fun getGroup(): VisualShaderGroup? {
        checkOpen()
        val ret = VisualShaderGroup.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getGroupBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeGroup? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeGroup? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeGroup(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeGroup? =
            if (handle.address() == 0L) null else VisualShaderNodeGroup(GodotHandle(handle))

        /** A new VisualShaderNodeGroup, GDScript's `VisualShaderNodeGroup.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeGroup =
            RefCounted.owned(VisualShaderNodeGroup(GodotHandle(requireConstructed(ObjectCalls.constructObject("VisualShaderNodeGroup"), "VisualShaderNodeGroup"))))
    }

    private object Binds {
        private const val SET_GROUP_HASH = 3093265740L
        @JvmField
        val setGroupBind =
            ObjectCalls.getMethodBind("VisualShaderNodeGroup", "set_group", SET_GROUP_HASH)

        private const val GET_GROUP_HASH = 1230504110L
        @JvmField
        val getGroupBind =
            ObjectCalls.getMethodBind("VisualShaderNodeGroup", "get_group", GET_GROUP_HASH)
    }
}
