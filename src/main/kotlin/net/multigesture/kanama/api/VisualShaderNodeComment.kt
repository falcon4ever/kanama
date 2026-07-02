package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeComment
 */
class VisualShaderNodeComment(handle: MemorySegment) : VisualShaderNodeFrame(handle) {
    var description: String
        @JvmName("descriptionProperty")
        get() = getDescription()
        @JvmName("setDescriptionProperty")
        set(value) = setDescription(value)

    fun setDescription(description: String) {
        ObjectCalls.ptrcallWithStringArg(setDescriptionBind, handle, description)
    }

    fun getDescription(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getDescriptionBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeComment? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeComment? =
            if (handle.address() == 0L) null else VisualShaderNodeComment(handle)

        private const val SET_DESCRIPTION_HASH = 83702148L
        private val setDescriptionBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeComment", "set_description", SET_DESCRIPTION_HASH)
        }

        private const val GET_DESCRIPTION_HASH = 201670096L
        private val getDescriptionBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeComment", "get_description", GET_DESCRIPTION_HASH)
        }
    }
}
