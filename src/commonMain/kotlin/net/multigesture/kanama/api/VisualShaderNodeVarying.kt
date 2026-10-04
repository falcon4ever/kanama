package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeVarying
 */
open class VisualShaderNodeVarying(handle: GodotHandle) : VisualShaderNode(handle) {
    var varyingName: String
        @JvmName("varyingNameProperty")
        get() = getVaryingName()
        @JvmName("setVaryingNameProperty")
        set(value) = setVaryingName(value)

    var varyingType: VisualShader.VaryingType
        @JvmName("varyingTypeProperty")
        get() = getVaryingType()
        @JvmName("setVaryingTypeProperty")
        set(value) = setVaryingType(value)

    fun setVaryingName(name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setVaryingNameBind, segment, name)
    }

    fun getVaryingName(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getVaryingNameBind, segment)
    }

    fun setVaryingType(type: VisualShader.VaryingType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setVaryingTypeBind, segment, type.value)
    }

    fun getVaryingType(): VisualShader.VaryingType {
        checkOpen()
        return VisualShader.VaryingType(ObjectCalls.ptrcallNoArgsRetLong(getVaryingTypeBind, segment))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeVarying? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeVarying? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeVarying(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeVarying? =
            if (handle.address() == 0L) null else VisualShaderNodeVarying(GodotHandle(handle))

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
