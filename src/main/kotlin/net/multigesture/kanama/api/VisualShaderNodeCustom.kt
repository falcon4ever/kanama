package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeCustom
 */
class VisualShaderNodeCustom(handle: MemorySegment) : VisualShaderNode(handle) {
    fun getOptionIndex(option: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(getOptionIndexBind, handle, option)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeCustom? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeCustom? =
            if (handle.address() == 0L) null else VisualShaderNodeCustom(handle)

        private const val GET_OPTION_INDEX_HASH = 923996154L
        private val getOptionIndexBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeCustom", "get_option_index", GET_OPTION_INDEX_HASH)
        }
    }
}
