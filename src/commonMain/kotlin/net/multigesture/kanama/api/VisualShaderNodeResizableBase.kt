package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector2

/**
 * Generated from Godot docs: VisualShaderNodeResizableBase
 */
open class VisualShaderNodeResizableBase(handle: GodotHandle) : VisualShaderNode(handle) {
    var size: Vector2
        @JvmName("sizeProperty")
        get() = getSize()
        @JvmName("setSizeProperty")
        set(value) = setSize(value)

    fun setSize(size: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(Binds.setSizeBind, segment, size)
    }

    fun getSize(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getSizeBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeResizableBase? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeResizableBase? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeResizableBase(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeResizableBase? =
            if (handle.address() == 0L) null else VisualShaderNodeResizableBase(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_SIZE_HASH = 743155724L
        @JvmField
        val setSizeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeResizableBase", "set_size", SET_SIZE_HASH)

        private const val GET_SIZE_HASH = 3341600327L
        @JvmField
        val getSizeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeResizableBase", "get_size", GET_SIZE_HASH)
    }
}
