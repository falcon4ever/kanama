package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * Represents a screen touch event.
 *
 * Generated from Godot docs: InputEventScreenTouch
 */
class InputEventScreenTouch(handle: MemorySegment) : InputEventFromWindow(handle) {
    var index: Int
        @JvmName("indexProperty")
        get() = getIndex()
        @JvmName("setIndexProperty")
        set(value) = setIndex(value)

    var position: Vector2
        @JvmName("positionProperty")
        get() = getPosition()
        @JvmName("setPositionProperty")
        set(value) = setPosition(value)

    var doubleTap: Boolean
        @JvmName("doubleTapProperty")
        get() = isDoubleTap()
        @JvmName("setDoubleTapProperty")
        set(value) = setDoubleTap(value)

    /**
     * The touch index in the case of a multi-touch event. One index = one finger.
     *
     * Generated from Godot docs: InputEventScreenTouch.set_index
     */
    fun setIndex(index: Int) {
        ObjectCalls.ptrcallWithIntArg(setIndexBind, handle, index)
    }

    /**
     * The touch index in the case of a multi-touch event. One index = one finger.
     *
     * Generated from Godot docs: InputEventScreenTouch.get_index
     */
    fun getIndex(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getIndexBind, handle)
    }

    /**
     * The touch position in the viewport the node is in, using the coordinate system of this viewport.
     *
     * Generated from Godot docs: InputEventScreenTouch.set_position
     */
    fun setPosition(position: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setPositionBind, handle, position)
    }

    /**
     * The touch position in the viewport the node is in, using the coordinate system of this viewport.
     *
     * Generated from Godot docs: InputEventScreenTouch.get_position
     */
    fun getPosition(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getPositionBind, handle)
    }

    /**
     * If `true`, the touch's state is pressed. If `false`, the touch's state is released.
     *
     * Generated from Godot docs: InputEventScreenTouch.set_pressed
     */
    fun setPressed(pressed: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPressedBind, handle, pressed)
    }

    /**
     * If `true`, the touch event has been canceled.
     *
     * Generated from Godot docs: InputEventScreenTouch.set_canceled
     */
    fun setCanceled(canceled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCanceledBind, handle, canceled)
    }

    /**
     * If `true`, the touch's state is a double tap.
     *
     * Generated from Godot docs: InputEventScreenTouch.set_double_tap
     */
    fun setDoubleTap(doubleTap: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDoubleTapBind, handle, doubleTap)
    }

    /**
     * If `true`, the touch's state is a double tap.
     *
     * Generated from Godot docs: InputEventScreenTouch.is_double_tap
     */
    fun isDoubleTap(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDoubleTapBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): InputEventScreenTouch? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): InputEventScreenTouch? =
            if (handle.address() == 0L) null else InputEventScreenTouch(handle)

        private const val SET_INDEX_HASH = 1286410249L
        private val setIndexBind by lazy {
            ObjectCalls.getMethodBind("InputEventScreenTouch", "set_index", SET_INDEX_HASH)
        }

        private const val GET_INDEX_HASH = 3905245786L
        private val getIndexBind by lazy {
            ObjectCalls.getMethodBind("InputEventScreenTouch", "get_index", GET_INDEX_HASH)
        }

        private const val SET_POSITION_HASH = 743155724L
        private val setPositionBind by lazy {
            ObjectCalls.getMethodBind("InputEventScreenTouch", "set_position", SET_POSITION_HASH)
        }

        private const val GET_POSITION_HASH = 3341600327L
        private val getPositionBind by lazy {
            ObjectCalls.getMethodBind("InputEventScreenTouch", "get_position", GET_POSITION_HASH)
        }

        private const val SET_PRESSED_HASH = 2586408642L
        private val setPressedBind by lazy {
            ObjectCalls.getMethodBind("InputEventScreenTouch", "set_pressed", SET_PRESSED_HASH)
        }

        private const val SET_CANCELED_HASH = 2586408642L
        private val setCanceledBind by lazy {
            ObjectCalls.getMethodBind("InputEventScreenTouch", "set_canceled", SET_CANCELED_HASH)
        }

        private const val SET_DOUBLE_TAP_HASH = 2586408642L
        private val setDoubleTapBind by lazy {
            ObjectCalls.getMethodBind("InputEventScreenTouch", "set_double_tap", SET_DOUBLE_TAP_HASH)
        }

        private const val IS_DOUBLE_TAP_HASH = 36873697L
        private val isDoubleTapBind by lazy {
            ObjectCalls.getMethodBind("InputEventScreenTouch", "is_double_tap", IS_DOUBLE_TAP_HASH)
        }
    }
}
