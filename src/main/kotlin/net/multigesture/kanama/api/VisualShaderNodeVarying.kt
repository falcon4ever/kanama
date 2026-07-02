package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeVarying
 */
open class VisualShaderNodeVarying(handle: MemorySegment) : VisualShaderNode(handle) {
    var varyingName: String
        @JvmName("varyingNameProperty")
        get() = getVaryingName()
        @JvmName("setVaryingNameProperty")
        set(value) = setVaryingName(value)

    var varyingType: Long
        @JvmName("varyingTypeProperty")
        get() = getVaryingType()
        @JvmName("setVaryingTypeProperty")
        set(value) = setVaryingType(value)

    fun setVaryingName(name: String) {
        ObjectCalls.ptrcallWithStringArg(setVaryingNameBind, handle, name)
    }

    fun getVaryingName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getVaryingNameBind, handle)
    }

    fun setVaryingType(type: Long) {
        ObjectCalls.ptrcallWithLongArg(setVaryingTypeBind, handle, type)
    }

    fun getVaryingType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getVaryingTypeBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeVarying? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeVarying? =
            if (handle.address() == 0L) null else VisualShaderNodeVarying(handle)

        private const val SET_VARYING_NAME_HASH = 83702148L
        private val setVaryingNameBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeVarying", "set_varying_name", SET_VARYING_NAME_HASH)
        }

        private const val GET_VARYING_NAME_HASH = 201670096L
        private val getVaryingNameBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeVarying", "get_varying_name", GET_VARYING_NAME_HASH)
        }

        private const val SET_VARYING_TYPE_HASH = 3565867981L
        private val setVaryingTypeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeVarying", "set_varying_type", SET_VARYING_TYPE_HASH)
        }

        private const val GET_VARYING_TYPE_HASH = 523183580L
        private val getVaryingTypeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeVarying", "get_varying_type", GET_VARYING_TYPE_HASH)
        }
    }
}
