package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Virtual class to define custom `VisualShaderNode`s for use in the Visual Shader Editor.
 *
 * Generated from Godot docs: VisualShaderNodeCustom
 */
class VisualShaderNodeCustom(handle: GodotHandle) : VisualShaderNode(handle) {
    /**
     * Returns the selected index of the drop-down list option within a graph. You may use this
     * function to define the specific behavior in the `_get_code` or `_get_global_code`.
     *
     * Generated from Godot docs: VisualShaderNodeCustom.get_option_index
     */
    fun getOptionIndex(option: Int): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetInt(Binds.getOptionIndexBind, segment, option)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeCustom? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeCustom? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeCustom(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeCustom? =
            if (handle.address() == 0L) null else VisualShaderNodeCustom(GodotHandle(handle))

        /** A new VisualShaderNodeCustom, GDScript's `VisualShaderNodeCustom.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeCustom =
            RefCounted.owned(VisualShaderNodeCustom(GodotHandle(ObjectCalls.constructObject("VisualShaderNodeCustom"))))
    }

    private object Binds {
        private const val GET_OPTION_INDEX_HASH = 923996154L
        @JvmField
        val getOptionIndexBind =
            ObjectCalls.getMethodBind("VisualShaderNodeCustom", "get_option_index", GET_OPTION_INDEX_HASH)
    }
}
