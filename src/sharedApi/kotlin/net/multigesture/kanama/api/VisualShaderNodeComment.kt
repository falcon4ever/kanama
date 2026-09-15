package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeComment
 */
class VisualShaderNodeComment(handle: GodotHandle) : VisualShaderNodeFrame(handle) {
    var description: String
        @JvmName("descriptionProperty")
        get() = getDescription()
        @JvmName("setDescriptionProperty")
        set(value) = setDescription(value)

    fun setDescription(description: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setDescriptionBind, segment, description)
    }

    fun getDescription(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getDescriptionBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeComment? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeComment? =
            if (handle.address() == 0L) null else VisualShaderNodeComment(GodotHandle(handle))

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
