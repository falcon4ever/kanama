package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector2

/**
 * Represents a panning touch gesture.
 *
 * Generated from Godot docs: InputEventPanGesture
 */
class InputEventPanGesture(handle: GodotHandle) : InputEventGesture(handle) {
    var delta: Vector2
        @JvmName("deltaProperty")
        get() = getDelta()
        @JvmName("setDeltaProperty")
        set(value) = setDelta(value)

    /**
     * Panning amount since last pan event.
     *
     * Generated from Godot docs: InputEventPanGesture.set_delta
     */
    fun setDelta(delta: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(Binds.setDeltaBind, segment, delta)
    }

    /**
     * Panning amount since last pan event.
     *
     * Generated from Godot docs: InputEventPanGesture.get_delta
     */
    fun getDelta(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getDeltaBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): InputEventPanGesture? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): InputEventPanGesture? =
            if (handle.address() == 0L) null else RefCounted.owned(InputEventPanGesture(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): InputEventPanGesture? =
            if (handle.address() == 0L) null else InputEventPanGesture(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_DELTA_HASH = 743155724L
        @JvmField
        val setDeltaBind =
            ObjectCalls.getMethodBind("InputEventPanGesture", "set_delta", SET_DELTA_HASH)

        private const val GET_DELTA_HASH = 3341600327L
        @JvmField
        val getDeltaBind =
            ObjectCalls.getMethodBind("InputEventPanGesture", "get_delta", GET_DELTA_HASH)
    }
}
