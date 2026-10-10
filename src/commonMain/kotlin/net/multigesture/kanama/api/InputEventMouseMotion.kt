package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector2

/**
 * Represents a mouse or a pen movement.
 *
 * Generated from Godot docs: InputEventMouseMotion
 */
class InputEventMouseMotion(handle: GodotHandle) : InputEventMouse(handle) {
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
        ObjectCalls.ptrcallWithVector2Arg(Binds.setTiltBind, segment, tilt)
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
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getTiltBind, segment)
    }

    /**
     * Represents the pressure the user puts on the pen. Ranges from `0.0` to `1.0`.
     *
     * Generated from Godot docs: InputEventMouseMotion.set_pressure
     */
    fun setPressure(pressure: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setPressureBind, segment, pressure)
    }

    /**
     * Represents the pressure the user puts on the pen. Ranges from `0.0` to `1.0`.
     *
     * Generated from Godot docs: InputEventMouseMotion.get_pressure
     */
    fun getPressure(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getPressureBind, segment)
    }

    /**
     * Returns `true` when using the eraser end of a stylus pen. Note: This property is implemented on
     * Linux, macOS and Windows.
     *
     * Generated from Godot docs: InputEventMouseMotion.set_pen_inverted
     */
    fun setPenInverted(penInverted: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setPenInvertedBind, segment, penInverted)
    }

    /**
     * Returns `true` when using the eraser end of a stylus pen. Note: This property is implemented on
     * Linux, macOS and Windows.
     *
     * Generated from Godot docs: InputEventMouseMotion.get_pen_inverted
     */
    fun getPenInverted(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getPenInvertedBind, segment)
    }

    /**
     * The mouse position relative to the previous position (position at the last frame). Note: Since
     * `InputEventMouseMotion` may only be emitted when the mouse moves, it is not possible to reliably
     * detect when the mouse has stopped moving by checking this property. A separate, short timer may
     * be necessary. Note: `relative` is automatically scaled according to the content scale factor,
     * which is defined by the project's stretch mode settings. This means mouse sensitivity will
     * appear different depending on resolution when using `relative` in a script that handles mouse
     * aiming with the `Input.MouseMode.CAPTURED` mouse mode. To avoid this, use `screen_relative`
     * instead.
     *
     * Generated from Godot docs: InputEventMouseMotion.set_relative
     */
    fun setRelative(relative: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(Binds.setRelativeBind, segment, relative)
    }

    /**
     * The mouse position relative to the previous position (position at the last frame). Note: Since
     * `InputEventMouseMotion` may only be emitted when the mouse moves, it is not possible to reliably
     * detect when the mouse has stopped moving by checking this property. A separate, short timer may
     * be necessary. Note: `relative` is automatically scaled according to the content scale factor,
     * which is defined by the project's stretch mode settings. This means mouse sensitivity will
     * appear different depending on resolution when using `relative` in a script that handles mouse
     * aiming with the `Input.MouseMode.CAPTURED` mouse mode. To avoid this, use `screen_relative`
     * instead.
     *
     * Generated from Godot docs: InputEventMouseMotion.get_relative
     */
    fun getRelative(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getRelativeBind, segment)
    }

    /**
     * The unscaled mouse position relative to the previous position in the coordinate system of the
     * screen (position at the last frame). Note: Since `InputEventMouseMotion` may only be emitted
     * when the mouse moves, it is not possible to reliably detect when the mouse has stopped moving by
     * checking this property. A separate, short timer may be necessary. Note: This coordinate is not
     * scaled according to the content scale factor or calls to `InputEvent.xformed_by`. This should be
     * preferred over `relative` for mouse aiming when using the `Input.MouseMode.CAPTURED` mouse mode,
     * regardless of the project's stretch mode.
     *
     * Generated from Godot docs: InputEventMouseMotion.set_screen_relative
     */
    fun setScreenRelative(relative: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(Binds.setScreenRelativeBind, segment, relative)
    }

    /**
     * The unscaled mouse position relative to the previous position in the coordinate system of the
     * screen (position at the last frame). Note: Since `InputEventMouseMotion` may only be emitted
     * when the mouse moves, it is not possible to reliably detect when the mouse has stopped moving by
     * checking this property. A separate, short timer may be necessary. Note: This coordinate is not
     * scaled according to the content scale factor or calls to `InputEvent.xformed_by`. This should be
     * preferred over `relative` for mouse aiming when using the `Input.MouseMode.CAPTURED` mouse mode,
     * regardless of the project's stretch mode.
     *
     * Generated from Godot docs: InputEventMouseMotion.get_screen_relative
     */
    fun getScreenRelative(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getScreenRelativeBind, segment)
    }

    /**
     * The mouse velocity in pixels per second. Note: `velocity` is automatically scaled according to
     * the content scale factor, which is defined by the project's stretch mode settings. That means
     * mouse sensitivity may appear different depending on resolution. Note: In
     * `Input.MouseMode.CAPTURED` mode, `velocity` returns `(0, 0)` because the mouse cursor is hidden
     * and locked. Use `screen_relative` for mouse aiming using the `Input.MouseMode.CAPTURED` mouse
     * mode.
     *
     * Generated from Godot docs: InputEventMouseMotion.set_velocity
     */
    fun setVelocity(velocity: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(Binds.setVelocityBind, segment, velocity)
    }

    /**
     * The mouse velocity in pixels per second. Note: `velocity` is automatically scaled according to
     * the content scale factor, which is defined by the project's stretch mode settings. That means
     * mouse sensitivity may appear different depending on resolution. Note: In
     * `Input.MouseMode.CAPTURED` mode, `velocity` returns `(0, 0)` because the mouse cursor is hidden
     * and locked. Use `screen_relative` for mouse aiming using the `Input.MouseMode.CAPTURED` mouse
     * mode.
     *
     * Generated from Godot docs: InputEventMouseMotion.get_velocity
     */
    fun getVelocity(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getVelocityBind, segment)
    }

    /**
     * The unscaled mouse velocity in pixels per second in screen coordinates. This velocity is not
     * scaled according to the content scale factor or calls to `InputEvent.xformed_by`. Note: In
     * `Input.MouseMode.CAPTURED` mode, `screen_velocity` returns `(0, 0)` because the mouse cursor is
     * hidden and locked. Use `screen_relative` for mouse aiming using the `Input.MouseMode.CAPTURED`
     * mouse mode.
     *
     * Generated from Godot docs: InputEventMouseMotion.set_screen_velocity
     */
    fun setScreenVelocity(velocity: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(Binds.setScreenVelocityBind, segment, velocity)
    }

    /**
     * The unscaled mouse velocity in pixels per second in screen coordinates. This velocity is not
     * scaled according to the content scale factor or calls to `InputEvent.xformed_by`. Note: In
     * `Input.MouseMode.CAPTURED` mode, `screen_velocity` returns `(0, 0)` because the mouse cursor is
     * hidden and locked. Use `screen_relative` for mouse aiming using the `Input.MouseMode.CAPTURED`
     * mouse mode.
     *
     * Generated from Godot docs: InputEventMouseMotion.get_screen_velocity
     */
    fun getScreenVelocity(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getScreenVelocityBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): InputEventMouseMotion? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): InputEventMouseMotion? =
            if (handle.address() == 0L) null else RefCounted.owned(InputEventMouseMotion(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): InputEventMouseMotion? =
            if (handle.address() == 0L) null else InputEventMouseMotion(GodotHandle(handle))

        // Downcast a GodotObject to InputEventMouseMotion (null if not).
        @JvmStatic
        fun from(value: GodotObject): InputEventMouseMotion? =
            if (value.isClass("InputEventMouseMotion")) RefCounted.retained(InputEventMouseMotion(value.handle)) else null
    }

    private object Binds {
        private const val SET_TILT_HASH = 743155724L
        @JvmField
        val setTiltBind =
            ObjectCalls.getMethodBind("InputEventMouseMotion", "set_tilt", SET_TILT_HASH)

        private const val GET_TILT_HASH = 3341600327L
        @JvmField
        val getTiltBind =
            ObjectCalls.getMethodBind("InputEventMouseMotion", "get_tilt", GET_TILT_HASH)

        private const val SET_PRESSURE_HASH = 373806689L
        @JvmField
        val setPressureBind =
            ObjectCalls.getMethodBind("InputEventMouseMotion", "set_pressure", SET_PRESSURE_HASH)

        private const val GET_PRESSURE_HASH = 1740695150L
        @JvmField
        val getPressureBind =
            ObjectCalls.getMethodBind("InputEventMouseMotion", "get_pressure", GET_PRESSURE_HASH)

        private const val SET_PEN_INVERTED_HASH = 2586408642L
        @JvmField
        val setPenInvertedBind =
            ObjectCalls.getMethodBind("InputEventMouseMotion", "set_pen_inverted", SET_PEN_INVERTED_HASH)

        private const val GET_PEN_INVERTED_HASH = 36873697L
        @JvmField
        val getPenInvertedBind =
            ObjectCalls.getMethodBind("InputEventMouseMotion", "get_pen_inverted", GET_PEN_INVERTED_HASH)

        private const val SET_RELATIVE_HASH = 743155724L
        @JvmField
        val setRelativeBind =
            ObjectCalls.getMethodBind("InputEventMouseMotion", "set_relative", SET_RELATIVE_HASH)

        private const val GET_RELATIVE_HASH = 3341600327L
        @JvmField
        val getRelativeBind =
            ObjectCalls.getMethodBind("InputEventMouseMotion", "get_relative", GET_RELATIVE_HASH)

        private const val SET_SCREEN_RELATIVE_HASH = 743155724L
        @JvmField
        val setScreenRelativeBind =
            ObjectCalls.getMethodBind("InputEventMouseMotion", "set_screen_relative", SET_SCREEN_RELATIVE_HASH)

        private const val GET_SCREEN_RELATIVE_HASH = 3341600327L
        @JvmField
        val getScreenRelativeBind =
            ObjectCalls.getMethodBind("InputEventMouseMotion", "get_screen_relative", GET_SCREEN_RELATIVE_HASH)

        private const val SET_VELOCITY_HASH = 743155724L
        @JvmField
        val setVelocityBind =
            ObjectCalls.getMethodBind("InputEventMouseMotion", "set_velocity", SET_VELOCITY_HASH)

        private const val GET_VELOCITY_HASH = 3341600327L
        @JvmField
        val getVelocityBind =
            ObjectCalls.getMethodBind("InputEventMouseMotion", "get_velocity", GET_VELOCITY_HASH)

        private const val SET_SCREEN_VELOCITY_HASH = 743155724L
        @JvmField
        val setScreenVelocityBind =
            ObjectCalls.getMethodBind("InputEventMouseMotion", "set_screen_velocity", SET_SCREEN_VELOCITY_HASH)

        private const val GET_SCREEN_VELOCITY_HASH = 3341600327L
        @JvmField
        val getScreenVelocityBind =
            ObjectCalls.getMethodBind("InputEventMouseMotion", "get_screen_velocity", GET_SCREEN_VELOCITY_HASH)
    }
}
