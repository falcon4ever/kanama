package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2
import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName

/**
 * Represents a mouse or a pen movement.
 *
 * Generated from Godot docs: InputEventMouseMotion
 */
class InputEventMouseMotion(handle: MemorySegment) : InputEventMouse(handle) {
    var tilt: Vector2
        @JvmName("tiltProperty")
        get() = getTilt()
        @JvmName("setTiltProperty")
        set(value) = setTilt(value)

    var pressure: Double
        @JvmName("pressureProperty")
        get() = getPressure()
        @JvmName("setPressureProperty")
        set(value) = setPressure(value)

    var penInverted: Boolean
        @JvmName("penInvertedProperty")
        get() = getPenInverted()
        @JvmName("setPenInvertedProperty")
        set(value) = setPenInverted(value)

    var relative: Vector2
        @JvmName("relativeProperty")
        get() = getRelative()
        @JvmName("setRelativeProperty")
        set(value) = setRelative(value)

    var screenRelative: Vector2
        @JvmName("screenRelativeProperty")
        get() = getScreenRelative()
        @JvmName("setScreenRelativeProperty")
        set(value) = setScreenRelative(value)

    var velocity: Vector2
        @JvmName("velocityProperty")
        get() = getVelocity()
        @JvmName("setVelocityProperty")
        set(value) = setVelocity(value)

    var screenVelocity: Vector2
        @JvmName("screenVelocityProperty")
        get() = getScreenVelocity()
        @JvmName("setScreenVelocityProperty")
        set(value) = setScreenVelocity(value)

    /**
     * Represents the angles of tilt of the pen. Positive X-coordinate value indicates a tilt to the
     * right. Positive Y-coordinate value indicates a tilt toward the user. Ranges from `-1.0` to `1.0`
     * for both axes.
     *
     * Generated from Godot docs: InputEventMouseMotion.set_tilt
     */
    fun setTilt(tilt: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(setTiltBind, handle, tilt)
    }

    /**
     * Represents the angles of tilt of the pen. Positive X-coordinate value indicates a tilt to the
     * right. Positive Y-coordinate value indicates a tilt toward the user. Ranges from `-1.0` to `1.0`
     * for both axes.
     *
     * Generated from Godot docs: InputEventMouseMotion.get_tilt
     */
    fun getTilt(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(getTiltBind, handle)
    }

    /**
     * Represents the pressure the user puts on the pen. Ranges from `0.0` to `1.0`.
     *
     * Generated from Godot docs: InputEventMouseMotion.set_pressure
     */
    fun setPressure(pressure: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setPressureBind, handle, pressure)
    }

    /**
     * Represents the pressure the user puts on the pen. Ranges from `0.0` to `1.0`.
     *
     * Generated from Godot docs: InputEventMouseMotion.get_pressure
     */
    fun getPressure(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getPressureBind, handle)
    }

    /**
     * Returns `true` when using the eraser end of a stylus pen. Note: This property is implemented on
     * Linux, macOS and Windows.
     *
     * Generated from Godot docs: InputEventMouseMotion.set_pen_inverted
     */
    fun setPenInverted(penInverted: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setPenInvertedBind, handle, penInverted)
    }

    /**
     * Returns `true` when using the eraser end of a stylus pen. Note: This property is implemented on
     * Linux, macOS and Windows.
     *
     * Generated from Godot docs: InputEventMouseMotion.get_pen_inverted
     */
    fun getPenInverted(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getPenInvertedBind, handle)
    }

    /**
     * The mouse position relative to the previous position (position at the last frame). Note: Since
     * `InputEventMouseMotion` may only be emitted when the mouse moves, it is not possible to reliably
     * detect when the mouse has stopped moving by checking this property. A separate, short timer may
     * be necessary. Note: `relative` is automatically scaled according to the content scale factor,
     * which is defined by the project's stretch mode settings. This means mouse sensitivity will
     * appear different depending on resolution when using `relative` in a script that handles mouse
     * aiming with the `Input.MOUSE_MODE_CAPTURED` mouse mode. To avoid this, use `screen_relative`
     * instead.
     *
     * Generated from Godot docs: InputEventMouseMotion.set_relative
     */
    fun setRelative(relative: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(setRelativeBind, handle, relative)
    }

    /**
     * The mouse position relative to the previous position (position at the last frame). Note: Since
     * `InputEventMouseMotion` may only be emitted when the mouse moves, it is not possible to reliably
     * detect when the mouse has stopped moving by checking this property. A separate, short timer may
     * be necessary. Note: `relative` is automatically scaled according to the content scale factor,
     * which is defined by the project's stretch mode settings. This means mouse sensitivity will
     * appear different depending on resolution when using `relative` in a script that handles mouse
     * aiming with the `Input.MOUSE_MODE_CAPTURED` mouse mode. To avoid this, use `screen_relative`
     * instead.
     *
     * Generated from Godot docs: InputEventMouseMotion.get_relative
     */
    fun getRelative(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(getRelativeBind, handle)
    }

    /**
     * The unscaled mouse position relative to the previous position in the coordinate system of the
     * screen (position at the last frame). Note: Since `InputEventMouseMotion` may only be emitted
     * when the mouse moves, it is not possible to reliably detect when the mouse has stopped moving by
     * checking this property. A separate, short timer may be necessary. Note: This coordinate is not
     * scaled according to the content scale factor or calls to `InputEvent.xformed_by`. This should be
     * preferred over `relative` for mouse aiming when using the `Input.MOUSE_MODE_CAPTURED` mouse
     * mode, regardless of the project's stretch mode.
     *
     * Generated from Godot docs: InputEventMouseMotion.set_screen_relative
     */
    fun setScreenRelative(relative: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(setScreenRelativeBind, handle, relative)
    }

    /**
     * The unscaled mouse position relative to the previous position in the coordinate system of the
     * screen (position at the last frame). Note: Since `InputEventMouseMotion` may only be emitted
     * when the mouse moves, it is not possible to reliably detect when the mouse has stopped moving by
     * checking this property. A separate, short timer may be necessary. Note: This coordinate is not
     * scaled according to the content scale factor or calls to `InputEvent.xformed_by`. This should be
     * preferred over `relative` for mouse aiming when using the `Input.MOUSE_MODE_CAPTURED` mouse
     * mode, regardless of the project's stretch mode.
     *
     * Generated from Godot docs: InputEventMouseMotion.get_screen_relative
     */
    fun getScreenRelative(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(getScreenRelativeBind, handle)
    }

    /**
     * The mouse velocity in pixels per second. Note: `velocity` is automatically scaled according to
     * the content scale factor, which is defined by the project's stretch mode settings. That means
     * mouse sensitivity may appear different depending on resolution. Note: In
     * `Input.MOUSE_MODE_CAPTURED` mode, `velocity` returns `(0, 0)` because the mouse cursor is hidden
     * and locked. Use `screen_relative` for mouse aiming using the `Input.MOUSE_MODE_CAPTURED` mouse
     * mode.
     *
     * Generated from Godot docs: InputEventMouseMotion.set_velocity
     */
    fun setVelocity(velocity: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(setVelocityBind, handle, velocity)
    }

    /**
     * The mouse velocity in pixels per second. Note: `velocity` is automatically scaled according to
     * the content scale factor, which is defined by the project's stretch mode settings. That means
     * mouse sensitivity may appear different depending on resolution. Note: In
     * `Input.MOUSE_MODE_CAPTURED` mode, `velocity` returns `(0, 0)` because the mouse cursor is hidden
     * and locked. Use `screen_relative` for mouse aiming using the `Input.MOUSE_MODE_CAPTURED` mouse
     * mode.
     *
     * Generated from Godot docs: InputEventMouseMotion.get_velocity
     */
    fun getVelocity(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(getVelocityBind, handle)
    }

    /**
     * The unscaled mouse velocity in pixels per second in screen coordinates. This velocity is not
     * scaled according to the content scale factor or calls to `InputEvent.xformed_by`. Note: In
     * `Input.MOUSE_MODE_CAPTURED` mode, `screen_velocity` returns `(0, 0)` because the mouse cursor is
     * hidden and locked. Use `screen_relative` for mouse aiming using the `Input.MOUSE_MODE_CAPTURED`
     * mouse mode.
     *
     * Generated from Godot docs: InputEventMouseMotion.set_screen_velocity
     */
    fun setScreenVelocity(velocity: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(setScreenVelocityBind, handle, velocity)
    }

    /**
     * The unscaled mouse velocity in pixels per second in screen coordinates. This velocity is not
     * scaled according to the content scale factor or calls to `InputEvent.xformed_by`. Note: In
     * `Input.MOUSE_MODE_CAPTURED` mode, `screen_velocity` returns `(0, 0)` because the mouse cursor is
     * hidden and locked. Use `screen_relative` for mouse aiming using the `Input.MOUSE_MODE_CAPTURED`
     * mouse mode.
     *
     * Generated from Godot docs: InputEventMouseMotion.get_screen_velocity
     */
    fun getScreenVelocity(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(getScreenVelocityBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): InputEventMouseMotion? =
            wrap(handle)

        @JvmStatic
        fun from(value: GodotObject): InputEventMouseMotion? =
            if (value.isClass("InputEventMouseMotion")) InputEventMouseMotion(value.handle) else null

        internal fun wrap(handle: MemorySegment): InputEventMouseMotion? =
            if (handle.address() == 0L) null else InputEventMouseMotion(handle)

        private const val SET_TILT_HASH = 743155724L
        private val setTiltBind by lazy {
            ObjectCalls.getMethodBind("InputEventMouseMotion", "set_tilt", SET_TILT_HASH)
        }

        private const val GET_TILT_HASH = 3341600327L
        private val getTiltBind by lazy {
            ObjectCalls.getMethodBind("InputEventMouseMotion", "get_tilt", GET_TILT_HASH)
        }

        private const val SET_PRESSURE_HASH = 373806689L
        private val setPressureBind by lazy {
            ObjectCalls.getMethodBind("InputEventMouseMotion", "set_pressure", SET_PRESSURE_HASH)
        }

        private const val GET_PRESSURE_HASH = 1740695150L
        private val getPressureBind by lazy {
            ObjectCalls.getMethodBind("InputEventMouseMotion", "get_pressure", GET_PRESSURE_HASH)
        }

        private const val SET_PEN_INVERTED_HASH = 2586408642L
        private val setPenInvertedBind by lazy {
            ObjectCalls.getMethodBind("InputEventMouseMotion", "set_pen_inverted", SET_PEN_INVERTED_HASH)
        }

        private const val GET_PEN_INVERTED_HASH = 36873697L
        private val getPenInvertedBind by lazy {
            ObjectCalls.getMethodBind("InputEventMouseMotion", "get_pen_inverted", GET_PEN_INVERTED_HASH)
        }

        private const val SET_RELATIVE_HASH = 743155724L
        private val setRelativeBind by lazy {
            ObjectCalls.getMethodBind("InputEventMouseMotion", "set_relative", SET_RELATIVE_HASH)
        }

        private const val GET_RELATIVE_HASH = 3341600327L
        private val getRelativeBind by lazy {
            ObjectCalls.getMethodBind("InputEventMouseMotion", "get_relative", GET_RELATIVE_HASH)
        }

        private const val SET_SCREEN_RELATIVE_HASH = 743155724L
        private val setScreenRelativeBind by lazy {
            ObjectCalls.getMethodBind("InputEventMouseMotion", "set_screen_relative", SET_SCREEN_RELATIVE_HASH)
        }

        private const val GET_SCREEN_RELATIVE_HASH = 3341600327L
        private val getScreenRelativeBind by lazy {
            ObjectCalls.getMethodBind("InputEventMouseMotion", "get_screen_relative", GET_SCREEN_RELATIVE_HASH)
        }

        private const val SET_VELOCITY_HASH = 743155724L
        private val setVelocityBind by lazy {
            ObjectCalls.getMethodBind("InputEventMouseMotion", "set_velocity", SET_VELOCITY_HASH)
        }

        private const val GET_VELOCITY_HASH = 3341600327L
        private val getVelocityBind by lazy {
            ObjectCalls.getMethodBind("InputEventMouseMotion", "get_velocity", GET_VELOCITY_HASH)
        }

        private const val SET_SCREEN_VELOCITY_HASH = 743155724L
        private val setScreenVelocityBind by lazy {
            ObjectCalls.getMethodBind("InputEventMouseMotion", "set_screen_velocity", SET_SCREEN_VELOCITY_HASH)
        }

        private const val GET_SCREEN_VELOCITY_HASH = 3341600327L
        private val getScreenVelocityBind by lazy {
            ObjectCalls.getMethodBind("InputEventMouseMotion", "get_screen_velocity", GET_SCREEN_VELOCITY_HASH)
        }
    }
}
