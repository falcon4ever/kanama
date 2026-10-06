package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector2

/**
 * Represents a screen touch event.
 *
 * Generated from Godot docs: InputEventScreenTouch
 */
class InputEventScreenTouch(handle: GodotHandle) : InputEventFromWindow(handle) {
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
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setIndexBind, segment, index)
    }

    /**
     * The touch index in the case of a multi-touch event. One index = one finger.
     *
     * Generated from Godot docs: InputEventScreenTouch.get_index
     */
    fun getIndex(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getIndexBind, segment)
    }

    /**
     * The touch position in the viewport the node is in, using the coordinate system of this viewport.
     *
     * Generated from Godot docs: InputEventScreenTouch.set_position
     */
    fun setPosition(position: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(Binds.setPositionBind, segment, position)
    }

    /**
     * The touch position in the viewport the node is in, using the coordinate system of this viewport.
     *
     * Generated from Godot docs: InputEventScreenTouch.get_position
     */
    fun getPosition(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getPositionBind, segment)
    }

    /**
     * If `true`, the touch's state is pressed. If `false`, the touch's state is released.
     *
     * Generated from Godot docs: InputEventScreenTouch.set_pressed
     */
    fun setPressed(pressed: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setPressedBind, segment, pressed)
    }

    /**
     * If `true`, the touch event has been canceled.
     *
     * Generated from Godot docs: InputEventScreenTouch.set_canceled
     */
    fun setCanceled(canceled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setCanceledBind, segment, canceled)
    }

    /**
     * If `true`, the touch's state is a double tap.
     *
     * Generated from Godot docs: InputEventScreenTouch.set_double_tap
     */
    fun setDoubleTap(doubleTap: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setDoubleTapBind, segment, doubleTap)
    }

    /**
     * If `true`, the touch's state is a double tap.
     *
     * Generated from Godot docs: InputEventScreenTouch.is_double_tap
     */
    fun isDoubleTap(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isDoubleTapBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): InputEventScreenTouch? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): InputEventScreenTouch? =
            if (handle.address() == 0L) null else RefCounted.owned(InputEventScreenTouch(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): InputEventScreenTouch? =
            if (handle.address() == 0L) null else InputEventScreenTouch(GodotHandle(handle))

        // Instantiate an InputEventScreenTouch.
        @JvmStatic
        fun create(): InputEventScreenTouch =
            RefCounted.owned(InputEventScreenTouch(GodotHandle(ObjectCalls.constructObject("InputEventScreenTouch"))))

        // Downcast a GodotObject to InputEventScreenTouch (null if not).
        @JvmStatic
        fun from(value: GodotObject): InputEventScreenTouch? =
            if (value.isClass("InputEventScreenTouch")) RefCounted.retained(InputEventScreenTouch(value.handle)) else null
    }

    private object Binds {
        private const val SET_INDEX_HASH = 1286410249L
        @JvmField
        val setIndexBind =
            ObjectCalls.getMethodBind("InputEventScreenTouch", "set_index", SET_INDEX_HASH)

        private const val GET_INDEX_HASH = 3905245786L
        @JvmField
        val getIndexBind =
            ObjectCalls.getMethodBind("InputEventScreenTouch", "get_index", GET_INDEX_HASH)

        private const val SET_POSITION_HASH = 743155724L
        @JvmField
        val setPositionBind =
            ObjectCalls.getMethodBind("InputEventScreenTouch", "set_position", SET_POSITION_HASH)

        private const val GET_POSITION_HASH = 3341600327L
        @JvmField
        val getPositionBind =
            ObjectCalls.getMethodBind("InputEventScreenTouch", "get_position", GET_POSITION_HASH)

        private const val SET_PRESSED_HASH = 2586408642L
        @JvmField
        val setPressedBind =
            ObjectCalls.getMethodBind("InputEventScreenTouch", "set_pressed", SET_PRESSED_HASH)

        private const val SET_CANCELED_HASH = 2586408642L
        @JvmField
        val setCanceledBind =
            ObjectCalls.getMethodBind("InputEventScreenTouch", "set_canceled", SET_CANCELED_HASH)

        private const val SET_DOUBLE_TAP_HASH = 2586408642L
        @JvmField
        val setDoubleTapBind =
            ObjectCalls.getMethodBind("InputEventScreenTouch", "set_double_tap", SET_DOUBLE_TAP_HASH)

        private const val IS_DOUBLE_TAP_HASH = 36873697L
        @JvmField
        val isDoubleTapBind =
            ObjectCalls.getMethodBind("InputEventScreenTouch", "is_double_tap", IS_DOUBLE_TAP_HASH)
    }
}
