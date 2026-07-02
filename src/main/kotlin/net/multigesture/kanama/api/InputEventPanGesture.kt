package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * Represents a panning touch gesture.
 *
 * Generated from Godot docs: InputEventPanGesture
 */
class InputEventPanGesture(handle: MemorySegment) : InputEventGesture(handle) {
    var delta: Vector2
        @JvmName("deltaProperty")
        get() = getDelta()
        @JvmName("setDeltaProperty")
        set(value) = setDelta(value)

    fun setDelta(delta: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setDeltaBind, handle, delta)
    }

    fun getDelta(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getDeltaBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): InputEventPanGesture? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): InputEventPanGesture? =
            if (handle.address() == 0L) null else InputEventPanGesture(handle)

        private const val SET_DELTA_HASH = 743155724L
        private val setDeltaBind by lazy {
            ObjectCalls.getMethodBind("InputEventPanGesture", "set_delta", SET_DELTA_HASH)
        }

        private const val GET_DELTA_HASH = 3341600327L
        private val getDeltaBind by lazy {
            ObjectCalls.getMethodBind("InputEventPanGesture", "get_delta", GET_DELTA_HASH)
        }
    }
}
