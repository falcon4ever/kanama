package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector2

/**
 * Abstract base class for touch gestures.
 *
 * Generated from Godot docs: InputEventGesture
 */
open class InputEventGesture(handle: GodotHandle) : InputEventWithModifiers(handle) {
    var position: Vector2
        @JvmName("positionProperty")
        get() = getPosition()
        @JvmName("setPositionProperty")
        set(value) = setPosition(value)

    /**
     * The local gesture position relative to the `Viewport`. If used in `Control._gui_input`, the
     * position is relative to the current `Control` that received this gesture.
     *
     * Generated from Godot docs: InputEventGesture.set_position
     */
    fun setPosition(position: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(Binds.setPositionBind, segment, position)
    }

    /**
     * The local gesture position relative to the `Viewport`. If used in `Control._gui_input`, the
     * position is relative to the current `Control` that received this gesture.
     *
     * Generated from Godot docs: InputEventGesture.get_position
     */
    fun getPosition(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getPositionBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): InputEventGesture? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): InputEventGesture? =
            if (handle.address() == 0L) null else RefCounted.owned(InputEventGesture(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): InputEventGesture? =
            if (handle.address() == 0L) null else InputEventGesture(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_POSITION_HASH = 743155724L
        @JvmField
        val setPositionBind =
            ObjectCalls.getMethodBind("InputEventGesture", "set_position", SET_POSITION_HASH)

        private const val GET_POSITION_HASH = 3341600327L
        @JvmField
        val getPositionBind =
            ObjectCalls.getMethodBind("InputEventGesture", "get_position", GET_POSITION_HASH)
    }
}
