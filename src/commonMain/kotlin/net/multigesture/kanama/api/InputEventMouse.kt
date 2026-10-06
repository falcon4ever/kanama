package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector2

/**
 * Base input event type for mouse events.
 *
 * Generated from Godot docs: InputEventMouse
 */
open class InputEventMouse(handle: GodotHandle) : InputEventWithModifiers(handle) {
    var buttonMask: MouseButtonMask
        @JvmName("buttonMaskProperty")
        get() = getButtonMask()
        @JvmName("setButtonMaskProperty")
        set(value) = setButtonMask(value)

    var position: Vector2
        @JvmName("positionProperty")
        get() = getPosition()
        @JvmName("setPositionProperty")
        set(value) = setPosition(value)

    var globalPosition: Vector2
        @JvmName("globalPositionProperty")
        get() = getGlobalPosition()
        @JvmName("setGlobalPositionProperty")
        set(value) = setGlobalPosition(value)

    /**
     * The mouse button mask identifier, one of or a bitwise combination of the `MouseButton` button
     * masks.
     *
     * Generated from Godot docs: InputEventMouse.set_button_mask
     */
    fun setButtonMask(buttonMask: MouseButtonMask) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setButtonMaskBind, segment, buttonMask.value)
    }

    /**
     * The mouse button mask identifier, one of or a bitwise combination of the `MouseButton` button
     * masks.
     *
     * Generated from Godot docs: InputEventMouse.get_button_mask
     */
    fun getButtonMask(): MouseButtonMask {
        checkOpen()
        return MouseButtonMask(ObjectCalls.ptrcallNoArgsRetLong(Binds.getButtonMaskBind, segment))
    }

    /**
     * When received in `Node._input` or `Node._unhandled_input`, returns the mouse's position in the
     * `Viewport` this `Node` is in using the coordinate system of this `Viewport`. When received in
     * `Control._gui_input`, returns the mouse's position in the `Control` using the local coordinate
     * system of the `Control`.
     *
     * Generated from Godot docs: InputEventMouse.set_position
     */
    fun setPosition(position: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(Binds.setPositionBind, segment, position)
    }

    /**
     * When received in `Node._input` or `Node._unhandled_input`, returns the mouse's position in the
     * `Viewport` this `Node` is in using the coordinate system of this `Viewport`. When received in
     * `Control._gui_input`, returns the mouse's position in the `Control` using the local coordinate
     * system of the `Control`.
     *
     * Generated from Godot docs: InputEventMouse.get_position
     */
    fun getPosition(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getPositionBind, segment)
    }

    /**
     * When received in `Node._input` or `Node._unhandled_input`, returns the mouse's position in the
     * root `Viewport` using the coordinate system of the root `Viewport`. When received in
     * `Control._gui_input`, returns the mouse's position in the `CanvasLayer` that the `Control` is in
     * using the coordinate system of the `CanvasLayer`.
     *
     * Generated from Godot docs: InputEventMouse.set_global_position
     */
    fun setGlobalPosition(globalPosition: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(Binds.setGlobalPositionBind, segment, globalPosition)
    }

    /**
     * When received in `Node._input` or `Node._unhandled_input`, returns the mouse's position in the
     * root `Viewport` using the coordinate system of the root `Viewport`. When received in
     * `Control._gui_input`, returns the mouse's position in the `CanvasLayer` that the `Control` is in
     * using the coordinate system of the `CanvasLayer`.
     *
     * Generated from Godot docs: InputEventMouse.get_global_position
     */
    fun getGlobalPosition(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getGlobalPositionBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): InputEventMouse? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): InputEventMouse? =
            if (handle.address() == 0L) null else RefCounted.owned(InputEventMouse(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): InputEventMouse? =
            if (handle.address() == 0L) null else InputEventMouse(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_BUTTON_MASK_HASH = 3950145251L
        @JvmField
        val setButtonMaskBind =
            ObjectCalls.getMethodBind("InputEventMouse", "set_button_mask", SET_BUTTON_MASK_HASH)

        private const val GET_BUTTON_MASK_HASH = 2512161324L
        @JvmField
        val getButtonMaskBind =
            ObjectCalls.getMethodBind("InputEventMouse", "get_button_mask", GET_BUTTON_MASK_HASH)

        private const val SET_POSITION_HASH = 743155724L
        @JvmField
        val setPositionBind =
            ObjectCalls.getMethodBind("InputEventMouse", "set_position", SET_POSITION_HASH)

        private const val GET_POSITION_HASH = 3341600327L
        @JvmField
        val getPositionBind =
            ObjectCalls.getMethodBind("InputEventMouse", "get_position", GET_POSITION_HASH)

        private const val SET_GLOBAL_POSITION_HASH = 743155724L
        @JvmField
        val setGlobalPositionBind =
            ObjectCalls.getMethodBind("InputEventMouse", "set_global_position", SET_GLOBAL_POSITION_HASH)

        private const val GET_GLOBAL_POSITION_HASH = 3341600327L
        @JvmField
        val getGlobalPositionBind =
            ObjectCalls.getMethodBind("InputEventMouse", "get_global_position", GET_GLOBAL_POSITION_HASH)
    }
}
