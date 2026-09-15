package net.multigesture.kanama.api

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
        ObjectCalls.ptrcallWithVector2Arg(setPositionBind, segment, position)
    }

    /**
     * The local gesture position relative to the `Viewport`. If used in `Control._gui_input`, the
     * position is relative to the current `Control` that received this gesture.
     *
     * Generated from Godot docs: InputEventGesture.get_position
     */
    fun getPosition(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(getPositionBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): InputEventGesture? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): InputEventGesture? =
            if (handle.address() == 0L) null else InputEventGesture(GodotHandle(handle))

        private const val SET_POSITION_HASH = 743155724L
        private val setPositionBind by lazy {
            ObjectCalls.getMethodBind("InputEventGesture", "set_position", SET_POSITION_HASH)
        }

        private const val GET_POSITION_HASH = 3341600327L
        private val getPositionBind by lazy {
            ObjectCalls.getMethodBind("InputEventGesture", "get_position", GET_POSITION_HASH)
        }
    }
}
