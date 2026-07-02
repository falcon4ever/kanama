package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A physics joint that restricts the movement of two 2D physics bodies to a fixed axis.
 *
 * Generated from Godot docs: GrooveJoint2D
 */
class GrooveJoint2D(handle: MemorySegment) : Joint2D(handle) {
    var length: Double
        @JvmName("lengthProperty")
        get() = getLength()
        @JvmName("setLengthProperty")
        set(value) = setLength(value)

    var initialOffset: Double
        @JvmName("initialOffsetProperty")
        get() = getInitialOffset()
        @JvmName("setInitialOffsetProperty")
        set(value) = setInitialOffset(value)

    fun setLength(length: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setLengthBind, handle, length)
    }

    fun getLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLengthBind, handle)
    }

    fun setInitialOffset(offset: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setInitialOffsetBind, handle, offset)
    }

    fun getInitialOffset(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getInitialOffsetBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): GrooveJoint2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GrooveJoint2D? =
            if (handle.address() == 0L) null else GrooveJoint2D(handle)

        private const val SET_LENGTH_HASH = 373806689L
        private val setLengthBind by lazy {
            ObjectCalls.getMethodBind("GrooveJoint2D", "set_length", SET_LENGTH_HASH)
        }

        private const val GET_LENGTH_HASH = 1740695150L
        private val getLengthBind by lazy {
            ObjectCalls.getMethodBind("GrooveJoint2D", "get_length", GET_LENGTH_HASH)
        }

        private const val SET_INITIAL_OFFSET_HASH = 373806689L
        private val setInitialOffsetBind by lazy {
            ObjectCalls.getMethodBind("GrooveJoint2D", "set_initial_offset", SET_INITIAL_OFFSET_HASH)
        }

        private const val GET_INITIAL_OFFSET_HASH = 1740695150L
        private val getInitialOffsetBind by lazy {
            ObjectCalls.getMethodBind("GrooveJoint2D", "get_initial_offset", GET_INITIAL_OFFSET_HASH)
        }
    }
}
