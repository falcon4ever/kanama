package net.multigesture.kanama.api

import kotlin.jvm.JvmField
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
        ObjectCalls.ptrcallWithStringArg(Binds.setDescriptionBind, segment, description)
    }

    fun getDescription(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getDescriptionBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeComment? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeComment? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeComment(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeComment? =
            if (handle.address() == 0L) null else VisualShaderNodeComment(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_DESCRIPTION_HASH = 83702148L
        @JvmField
        val setDescriptionBind =
            ObjectCalls.getMethodBind("VisualShaderNodeComment", "set_description", SET_DESCRIPTION_HASH)

        private const val GET_DESCRIPTION_HASH = 201670096L
        @JvmField
        val getDescriptionBind =
            ObjectCalls.getMethodBind("VisualShaderNodeComment", "get_description", GET_DESCRIPTION_HASH)
    }
}
