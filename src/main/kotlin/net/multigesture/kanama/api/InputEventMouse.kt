package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * Base input event type for mouse events.
 *
 * Generated from Godot docs: InputEventMouse
 */
open class InputEventMouse(handle: MemorySegment) : InputEventWithModifiers(handle) {
    var buttonMask: Long
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
    fun setButtonMask(buttonMask: Long) {
        ObjectCalls.ptrcallWithLongArg(setButtonMaskBind, handle, buttonMask)
    }

    /**
     * The mouse button mask identifier, one of or a bitwise combination of the `MouseButton` button
     * masks.
     *
     * Generated from Godot docs: InputEventMouse.get_button_mask
     */
    fun getButtonMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getButtonMaskBind, handle)
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
        ObjectCalls.ptrcallWithVector2Arg(setPositionBind, handle, position)
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
        return ObjectCalls.ptrcallNoArgsRetVector2(getPositionBind, handle)
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
        ObjectCalls.ptrcallWithVector2Arg(setGlobalPositionBind, handle, globalPosition)
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
        return ObjectCalls.ptrcallNoArgsRetVector2(getGlobalPositionBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): InputEventMouse? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): InputEventMouse? =
            if (handle.address() == 0L) null else InputEventMouse(handle)

        private const val SET_BUTTON_MASK_HASH = 3950145251L
        private val setButtonMaskBind by lazy {
            ObjectCalls.getMethodBind("InputEventMouse", "set_button_mask", SET_BUTTON_MASK_HASH)
        }

        private const val GET_BUTTON_MASK_HASH = 2512161324L
        private val getButtonMaskBind by lazy {
            ObjectCalls.getMethodBind("InputEventMouse", "get_button_mask", GET_BUTTON_MASK_HASH)
        }

        private const val SET_POSITION_HASH = 743155724L
        private val setPositionBind by lazy {
            ObjectCalls.getMethodBind("InputEventMouse", "set_position", SET_POSITION_HASH)
        }

        private const val GET_POSITION_HASH = 3341600327L
        private val getPositionBind by lazy {
            ObjectCalls.getMethodBind("InputEventMouse", "get_position", GET_POSITION_HASH)
        }

        private const val SET_GLOBAL_POSITION_HASH = 743155724L
        private val setGlobalPositionBind by lazy {
            ObjectCalls.getMethodBind("InputEventMouse", "set_global_position", SET_GLOBAL_POSITION_HASH)
        }

        private const val GET_GLOBAL_POSITION_HASH = 3341600327L
        private val getGlobalPositionBind by lazy {
            ObjectCalls.getMethodBind("InputEventMouse", "get_global_position", GET_GLOBAL_POSITION_HASH)
        }
    }
}
