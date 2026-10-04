package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeCustom
 */
class VisualShaderNodeCustom(handle: GodotHandle) : VisualShaderNode(handle) {
    fun getOptionIndex(option: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(getOptionIndexBind, segment, option)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeCustom? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeCustom? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeCustom(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeCustom? =
            if (handle.address() == 0L) null else VisualShaderNodeCustom(GodotHandle(handle))

        private const val GET_OPTION_INDEX_HASH = 923996154L
        private val getOptionIndexBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeCustom", "get_option_index", GET_OPTION_INDEX_HASH)
        }
    }
}
