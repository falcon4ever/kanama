package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeSample3D
 */
open class VisualShaderNodeSample3D(handle: GodotHandle) : VisualShaderNode(handle) {
    var source: VisualShaderNodeSample3D.Source
        @JvmName("sourceProperty")
        get() = getSource()
        @JvmName("setSourceProperty")
        set(value) = setSource(value)

    fun setSource(value: VisualShaderNodeSample3D.Source) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setSourceBind, segment, value.value)
    }

    fun getSource(): VisualShaderNodeSample3D.Source {
        checkOpen()
        return VisualShaderNodeSample3D.Source(ObjectCalls.ptrcallNoArgsRetLong(getSourceBind, segment))
    }

    @JvmInline
    value class Source(override val value: Long) : GodotEnumValue {
        companion object {
            val TEXTURE: Source get() = Source(0L)
            val PORT: Source get() = Source(1L)
            val MAX: Source get() = Source(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeSample3D? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeSample3D? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeSample3D(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeSample3D? =
            if (handle.address() == 0L) null else VisualShaderNodeSample3D(GodotHandle(handle))

        private const val SET_SOURCE_HASH = 3315130991L
        private val setSourceBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeSample3D", "set_source", SET_SOURCE_HASH)
        }

        private const val GET_SOURCE_HASH = 1079494121L
        private val getSourceBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeSample3D", "get_source", GET_SOURCE_HASH)
        }
    }
}
