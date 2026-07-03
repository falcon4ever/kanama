package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * A virtual joystick control for touchscreen devices.
 *
 * Generated from Godot docs: VirtualJoystick
 */
class VirtualJoystick(handle: MemorySegment) : Control(handle) {
    var joystickMode: Long
        @JvmName("joystickModeProperty")
        get() = getJoystickMode()
        @JvmName("setJoystickModeProperty")
        set(value) = setJoystickMode(value)

    var joystickSize: Double
        @JvmName("joystickSizeProperty")
        get() = getJoystickSize()
        @JvmName("setJoystickSizeProperty")
        set(value) = setJoystickSize(value)

    var tipSize: Double
        @JvmName("tipSizeProperty")
        get() = getTipSize()
        @JvmName("setTipSizeProperty")
        set(value) = setTipSize(value)

    var deadzoneRatio: Double
        @JvmName("deadzoneRatioProperty")
        get() = getDeadzoneRatio()
        @JvmName("setDeadzoneRatioProperty")
        set(value) = setDeadzoneRatio(value)

    var clampzoneRatio: Double
        @JvmName("clampzoneRatioProperty")
        get() = getClampzoneRatio()
        @JvmName("setClampzoneRatioProperty")
        set(value) = setClampzoneRatio(value)

    var initialOffsetRatio: Vector2
        @JvmName("initialOffsetRatioProperty")
        get() = getInitialOffsetRatio()
        @JvmName("setInitialOffsetRatioProperty")
        set(value) = setInitialOffsetRatio(value)

    var actionLeft: String
        @JvmName("actionLeftProperty")
        get() = getActionLeft()
        @JvmName("setActionLeftProperty")
        set(value) = setActionLeft(value)

    var actionRight: String
        @JvmName("actionRightProperty")
        get() = getActionRight()
        @JvmName("setActionRightProperty")
        set(value) = setActionRight(value)

    var actionUp: String
        @JvmName("actionUpProperty")
        get() = getActionUp()
        @JvmName("setActionUpProperty")
        set(value) = setActionUp(value)

    var actionDown: String
        @JvmName("actionDownProperty")
        get() = getActionDown()
        @JvmName("setActionDownProperty")
        set(value) = setActionDown(value)

    var visibilityMode: Long
        @JvmName("visibilityModeProperty")
        get() = getVisibilityMode()
        @JvmName("setVisibilityModeProperty")
        set(value) = setVisibilityMode(value)

    /**
     * The joystick mode to use.
     *
     * Generated from Godot docs: VirtualJoystick.set_joystick_mode
     */
    fun setJoystickMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setJoystickModeBind, handle, mode)
    }

    /**
     * The joystick mode to use.
     *
     * Generated from Godot docs: VirtualJoystick.get_joystick_mode
     */
    fun getJoystickMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getJoystickModeBind, handle)
    }

    /**
     * The size of the joystick in pixels.
     *
     * Generated from Godot docs: VirtualJoystick.set_joystick_size
     */
    fun setJoystickSize(size: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setJoystickSizeBind, handle, size)
    }

    /**
     * The size of the joystick in pixels.
     *
     * Generated from Godot docs: VirtualJoystick.get_joystick_size
     */
    fun getJoystickSize(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getJoystickSizeBind, handle)
    }

    /**
     * The size of the joystick tip in pixels.
     *
     * Generated from Godot docs: VirtualJoystick.set_tip_size
     */
    fun setTipSize(size: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTipSizeBind, handle, size)
    }

    /**
     * The size of the joystick tip in pixels.
     *
     * Generated from Godot docs: VirtualJoystick.get_tip_size
     */
    fun getTipSize(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTipSizeBind, handle)
    }

    /**
     * The ratio of the joystick size that defines the joystick deadzone. The joystick tip must move
     * beyond this ratio before being considered active. This deadzone is applied before triggering
     * input actions and affects the joystick's input vector and all related signals. Note that input
     * actions may also define their own deadzones in the InputMap. If both are set, the joystick
     * deadzone is applied first, followed by the action's deadzone. By default, this value is `0.0`,
     * meaning the joystick does not apply its own deadzone and relies entirely on the InputMap action
     * deadzones.
     *
     * Generated from Godot docs: VirtualJoystick.set_deadzone_ratio
     */
    fun setDeadzoneRatio(ratio: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDeadzoneRatioBind, handle, ratio)
    }

    /**
     * The ratio of the joystick size that defines the joystick deadzone. The joystick tip must move
     * beyond this ratio before being considered active. This deadzone is applied before triggering
     * input actions and affects the joystick's input vector and all related signals. Note that input
     * actions may also define their own deadzones in the InputMap. If both are set, the joystick
     * deadzone is applied first, followed by the action's deadzone. By default, this value is `0.0`,
     * meaning the joystick does not apply its own deadzone and relies entirely on the InputMap action
     * deadzones.
     *
     * Generated from Godot docs: VirtualJoystick.get_deadzone_ratio
     */
    fun getDeadzoneRatio(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDeadzoneRatioBind, handle)
    }

    /**
     * The multiplier applied to the joystick's radius that defines the clamp zone. This zone limits
     * how far the joystick tip can move from its center before being clamped. A value of `1.0` means
     * the tip can move up to the edge of the joystick's visual size. In `JOYSTICK_FOLLOWING` mode,
     * this radius also determines how far the finger can move before the joystick base starts
     * following the touch input.
     *
     * Generated from Godot docs: VirtualJoystick.set_clampzone_ratio
     */
    fun setClampzoneRatio(ratio: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setClampzoneRatioBind, handle, ratio)
    }

    /**
     * The multiplier applied to the joystick's radius that defines the clamp zone. This zone limits
     * how far the joystick tip can move from its center before being clamped. A value of `1.0` means
     * the tip can move up to the edge of the joystick's visual size. In `JOYSTICK_FOLLOWING` mode,
     * this radius also determines how far the finger can move before the joystick base starts
     * following the touch input.
     *
     * Generated from Godot docs: VirtualJoystick.get_clampzone_ratio
     */
    fun getClampzoneRatio(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getClampzoneRatioBind, handle)
    }

    /**
     * The initial position of the joystick as a ratio of the control's size. `(0, 0)` is top-left and
     * `(1, 1)` is bottom-right.
     *
     * Generated from Godot docs: VirtualJoystick.set_initial_offset_ratio
     */
    fun setInitialOffsetRatio(ratio: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setInitialOffsetRatioBind, handle, ratio)
    }

    /**
     * The initial position of the joystick as a ratio of the control's size. `(0, 0)` is top-left and
     * `(1, 1)` is bottom-right.
     *
     * Generated from Godot docs: VirtualJoystick.get_initial_offset_ratio
     */
    fun getInitialOffsetRatio(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getInitialOffsetRatioBind, handle)
    }

    /**
     * The action to trigger when the joystick is moved left.
     *
     * Generated from Godot docs: VirtualJoystick.set_action_left
     */
    fun setActionLeft(action: String) {
        ObjectCalls.ptrcallWithStringNameArg(setActionLeftBind, handle, action)
    }

    /**
     * The action to trigger when the joystick is moved left.
     *
     * Generated from Godot docs: VirtualJoystick.get_action_left
     */
    fun getActionLeft(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getActionLeftBind, handle)
    }

    /**
     * The action to trigger when the joystick is moved right.
     *
     * Generated from Godot docs: VirtualJoystick.set_action_right
     */
    fun setActionRight(action: String) {
        ObjectCalls.ptrcallWithStringNameArg(setActionRightBind, handle, action)
    }

    /**
     * The action to trigger when the joystick is moved right.
     *
     * Generated from Godot docs: VirtualJoystick.get_action_right
     */
    fun getActionRight(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getActionRightBind, handle)
    }

    /**
     * The action to trigger when the joystick is moved up.
     *
     * Generated from Godot docs: VirtualJoystick.set_action_up
     */
    fun setActionUp(action: String) {
        ObjectCalls.ptrcallWithStringNameArg(setActionUpBind, handle, action)
    }

    /**
     * The action to trigger when the joystick is moved up.
     *
     * Generated from Godot docs: VirtualJoystick.get_action_up
     */
    fun getActionUp(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getActionUpBind, handle)
    }

    /**
     * The action to trigger when the joystick is moved down.
     *
     * Generated from Godot docs: VirtualJoystick.set_action_down
     */
    fun setActionDown(action: String) {
        ObjectCalls.ptrcallWithStringNameArg(setActionDownBind, handle, action)
    }

    /**
     * The action to trigger when the joystick is moved down.
     *
     * Generated from Godot docs: VirtualJoystick.get_action_down
     */
    fun getActionDown(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getActionDownBind, handle)
    }

    /**
     * The visibility mode to use.
     *
     * Generated from Godot docs: VirtualJoystick.set_visibility_mode
     */
    fun setVisibilityMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setVisibilityModeBind, handle, mode)
    }

    /**
     * The visibility mode to use.
     *
     * Generated from Godot docs: VirtualJoystick.get_visibility_mode
     */
    fun getVisibilityMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getVisibilityModeBind, handle)
    }

    object Signals {
        const val pressed: String = "pressed"
        const val tapped: String = "tapped"
        const val released: String = "released"
        const val flicked: String = "flicked"
        const val flickCanceled: String = "flick_canceled"
    }

    companion object {
        const val JOYSTICK_FIXED: Long = 0L
        const val JOYSTICK_DYNAMIC: Long = 1L
        const val JOYSTICK_FOLLOWING: Long = 2L
        const val VISIBILITY_ALWAYS: Long = 0L
        const val VISIBILITY_WHEN_TOUCHED: Long = 1L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): VirtualJoystick? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VirtualJoystick? =
            if (handle.address() == 0L) null else VirtualJoystick(handle)

        private const val SET_JOYSTICK_MODE_HASH = 1316760817L
        private val setJoystickModeBind by lazy {
            ObjectCalls.getMethodBind("VirtualJoystick", "set_joystick_mode", SET_JOYSTICK_MODE_HASH)
        }

        private const val GET_JOYSTICK_MODE_HASH = 2694680530L
        private val getJoystickModeBind by lazy {
            ObjectCalls.getMethodBind("VirtualJoystick", "get_joystick_mode", GET_JOYSTICK_MODE_HASH)
        }

        private const val SET_JOYSTICK_SIZE_HASH = 373806689L
        private val setJoystickSizeBind by lazy {
            ObjectCalls.getMethodBind("VirtualJoystick", "set_joystick_size", SET_JOYSTICK_SIZE_HASH)
        }

        private const val GET_JOYSTICK_SIZE_HASH = 1740695150L
        private val getJoystickSizeBind by lazy {
            ObjectCalls.getMethodBind("VirtualJoystick", "get_joystick_size", GET_JOYSTICK_SIZE_HASH)
        }

        private const val SET_TIP_SIZE_HASH = 373806689L
        private val setTipSizeBind by lazy {
            ObjectCalls.getMethodBind("VirtualJoystick", "set_tip_size", SET_TIP_SIZE_HASH)
        }

        private const val GET_TIP_SIZE_HASH = 1740695150L
        private val getTipSizeBind by lazy {
            ObjectCalls.getMethodBind("VirtualJoystick", "get_tip_size", GET_TIP_SIZE_HASH)
        }

        private const val SET_DEADZONE_RATIO_HASH = 373806689L
        private val setDeadzoneRatioBind by lazy {
            ObjectCalls.getMethodBind("VirtualJoystick", "set_deadzone_ratio", SET_DEADZONE_RATIO_HASH)
        }

        private const val GET_DEADZONE_RATIO_HASH = 1740695150L
        private val getDeadzoneRatioBind by lazy {
            ObjectCalls.getMethodBind("VirtualJoystick", "get_deadzone_ratio", GET_DEADZONE_RATIO_HASH)
        }

        private const val SET_CLAMPZONE_RATIO_HASH = 373806689L
        private val setClampzoneRatioBind by lazy {
            ObjectCalls.getMethodBind("VirtualJoystick", "set_clampzone_ratio", SET_CLAMPZONE_RATIO_HASH)
        }

        private const val GET_CLAMPZONE_RATIO_HASH = 1740695150L
        private val getClampzoneRatioBind by lazy {
            ObjectCalls.getMethodBind("VirtualJoystick", "get_clampzone_ratio", GET_CLAMPZONE_RATIO_HASH)
        }

        private const val SET_INITIAL_OFFSET_RATIO_HASH = 743155724L
        private val setInitialOffsetRatioBind by lazy {
            ObjectCalls.getMethodBind("VirtualJoystick", "set_initial_offset_ratio", SET_INITIAL_OFFSET_RATIO_HASH)
        }

        private const val GET_INITIAL_OFFSET_RATIO_HASH = 3341600327L
        private val getInitialOffsetRatioBind by lazy {
            ObjectCalls.getMethodBind("VirtualJoystick", "get_initial_offset_ratio", GET_INITIAL_OFFSET_RATIO_HASH)
        }

        private const val SET_ACTION_LEFT_HASH = 3304788590L
        private val setActionLeftBind by lazy {
            ObjectCalls.getMethodBind("VirtualJoystick", "set_action_left", SET_ACTION_LEFT_HASH)
        }

        private const val GET_ACTION_LEFT_HASH = 2002593661L
        private val getActionLeftBind by lazy {
            ObjectCalls.getMethodBind("VirtualJoystick", "get_action_left", GET_ACTION_LEFT_HASH)
        }

        private const val SET_ACTION_RIGHT_HASH = 3304788590L
        private val setActionRightBind by lazy {
            ObjectCalls.getMethodBind("VirtualJoystick", "set_action_right", SET_ACTION_RIGHT_HASH)
        }

        private const val GET_ACTION_RIGHT_HASH = 2002593661L
        private val getActionRightBind by lazy {
            ObjectCalls.getMethodBind("VirtualJoystick", "get_action_right", GET_ACTION_RIGHT_HASH)
        }

        private const val SET_ACTION_UP_HASH = 3304788590L
        private val setActionUpBind by lazy {
            ObjectCalls.getMethodBind("VirtualJoystick", "set_action_up", SET_ACTION_UP_HASH)
        }

        private const val GET_ACTION_UP_HASH = 2002593661L
        private val getActionUpBind by lazy {
            ObjectCalls.getMethodBind("VirtualJoystick", "get_action_up", GET_ACTION_UP_HASH)
        }

        private const val SET_ACTION_DOWN_HASH = 3304788590L
        private val setActionDownBind by lazy {
            ObjectCalls.getMethodBind("VirtualJoystick", "set_action_down", SET_ACTION_DOWN_HASH)
        }

        private const val GET_ACTION_DOWN_HASH = 2002593661L
        private val getActionDownBind by lazy {
            ObjectCalls.getMethodBind("VirtualJoystick", "get_action_down", GET_ACTION_DOWN_HASH)
        }

        private const val SET_VISIBILITY_MODE_HASH = 2638298545L
        private val setVisibilityModeBind by lazy {
            ObjectCalls.getMethodBind("VirtualJoystick", "set_visibility_mode", SET_VISIBILITY_MODE_HASH)
        }

        private const val GET_VISIBILITY_MODE_HASH = 3530872950L
        private val getVisibilityModeBind by lazy {
            ObjectCalls.getMethodBind("VirtualJoystick", "get_visibility_mode", GET_VISIBILITY_MODE_HASH)
        }
    }
}
