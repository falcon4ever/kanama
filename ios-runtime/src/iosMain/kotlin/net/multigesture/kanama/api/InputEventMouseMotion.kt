package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector2

/**
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

    fun setTilt(tilt: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(setTiltBind, handle, tilt)
    }

    fun getTilt(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(getTiltBind, handle)
    }

    fun setPressure(pressure: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setPressureBind, handle, pressure)
    }

    fun getPressure(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getPressureBind, handle)
    }

    fun setPenInverted(penInverted: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setPenInvertedBind, handle, penInverted)
    }

    fun getPenInverted(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getPenInvertedBind, handle)
    }

    fun setRelative(relative: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(setRelativeBind, handle, relative)
    }

    fun getRelative(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(getRelativeBind, handle)
    }

    fun setScreenRelative(relative: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(setScreenRelativeBind, handle, relative)
    }

    fun getScreenRelative(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(getScreenRelativeBind, handle)
    }

    fun setVelocity(velocity: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(setVelocityBind, handle, velocity)
    }

    fun getVelocity(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(getVelocityBind, handle)
    }

    fun setScreenVelocity(velocity: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(setScreenVelocityBind, handle, velocity)
    }

    fun getScreenVelocity(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(getScreenVelocityBind, handle)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): InputEventMouseMotion? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): InputEventMouseMotion? =
            if (handle.address() == 0L) null else InputEventMouseMotion(handle)

        // Cast a generic event to InputEventMouseMotion (null if not), mirroring the desktop helper.
        fun from(value: GodotObject): InputEventMouseMotion? =
            if (value.isClass("InputEventMouseMotion")) InputEventMouseMotion(value.handle) else null

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
