package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A base node for nodes which samples 3D textures in the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeSample3D
 */
open class VisualShaderNodeSample3D(handle: GodotHandle) : VisualShaderNode(handle) {
    var source: VisualShaderNodeSample3D.Source
        @JvmName("sourceProperty")
        get() = getSource()
        @JvmName("setSourceProperty")
        set(value) = setSource(value)

    /**
     * An input source type.
     *
     * Generated from Godot docs: VisualShaderNodeSample3D.set_source
     */
    fun setSource(value: VisualShaderNodeSample3D.Source) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setSourceBind, segment, value.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * An input source type.
     *
     * Generated from Godot docs: VisualShaderNodeSample3D.get_source
     */
    fun getSource(): VisualShaderNodeSample3D.Source {
        checkOpen()
        val ret = VisualShaderNodeSample3D.Source(ObjectCalls.ptrcallNoArgsRetLong(Binds.getSourceBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Godot's `VisualShaderNodeSample3D.Source` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values (`VisualShaderNodeSample3D.Source.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeSample3D.Source
     */
    @JvmInline
    value class Source(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Creates internal uniform and provides a way to assign it within node.
             *
             * Generated from Godot docs: VisualShaderNodeSample3D.SOURCE_TEXTURE
             */
            val TEXTURE: Source get() = Source(0L)
            /**
             * Use the uniform texture from sampler port.
             *
             * Generated from Godot docs: VisualShaderNodeSample3D.SOURCE_PORT
             */
            val PORT: Source get() = Source(1L)
            /**
             * Represents the size of the `Source` enum.
             *
             * Generated from Godot docs: VisualShaderNodeSample3D.SOURCE_MAX
             */
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
    }

    private object Binds {
        private const val SET_SOURCE_HASH = 3315130991L
        @JvmField
        val setSourceBind =
            ObjectCalls.getMethodBind("VisualShaderNodeSample3D", "set_source", SET_SOURCE_HASH)

        private const val GET_SOURCE_HASH = 1079494121L
        @JvmField
        val getSourceBind =
            ObjectCalls.getMethodBind("VisualShaderNodeSample3D", "get_source", GET_SOURCE_HASH)
    }
}
