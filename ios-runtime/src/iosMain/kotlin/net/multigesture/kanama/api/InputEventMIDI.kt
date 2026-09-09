package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: InputEventMIDI
 */
class InputEventMIDI(handle: MemorySegment) : InputEvent(handle) {
    var channel: Int
        @JvmName("channelProperty")
        get() = getChannel()
        @JvmName("setChannelProperty")
        set(value) = setChannel(value)

    var message: Long
        @JvmName("messageProperty")
        get() = getMessage()
        @JvmName("setMessageProperty")
        set(value) = setMessage(value)

    var pitch: Int
        @JvmName("pitchProperty")
        get() = getPitch()
        @JvmName("setPitchProperty")
        set(value) = setPitch(value)

    var velocity: Int
        @JvmName("velocityProperty")
        get() = getVelocity()
        @JvmName("setVelocityProperty")
        set(value) = setVelocity(value)

    var instrument: Int
        @JvmName("instrumentProperty")
        get() = getInstrument()
        @JvmName("setInstrumentProperty")
        set(value) = setInstrument(value)

    var pressure: Int
        @JvmName("pressureProperty")
        get() = getPressure()
        @JvmName("setPressureProperty")
        set(value) = setPressure(value)

    var controllerNumber: Int
        @JvmName("controllerNumberProperty")
        get() = getControllerNumber()
        @JvmName("setControllerNumberProperty")
        set(value) = setControllerNumber(value)

    var controllerValue: Int
        @JvmName("controllerValueProperty")
        get() = getControllerValue()
        @JvmName("setControllerValueProperty")
        set(value) = setControllerValue(value)

    fun setChannel(channel: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setChannelBind, handle, channel)
    }

    fun getChannel(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getChannelBind, handle)
    }

    fun setMessage(message: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setMessageBind, handle, message)
    }

    fun getMessage(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getMessageBind, handle)
    }

    fun setPitch(pitch: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setPitchBind, handle, pitch)
    }

    fun getPitch(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getPitchBind, handle)
    }

    fun setVelocity(velocity: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setVelocityBind, handle, velocity)
    }

    fun getVelocity(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getVelocityBind, handle)
    }

    fun setInstrument(instrument: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setInstrumentBind, handle, instrument)
    }

    fun getInstrument(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getInstrumentBind, handle)
    }

    fun setPressure(pressure: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setPressureBind, handle, pressure)
    }

    fun getPressure(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getPressureBind, handle)
    }

    fun setControllerNumber(controllerNumber: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setControllerNumberBind, handle, controllerNumber)
    }

    fun getControllerNumber(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getControllerNumberBind, handle)
    }

    fun setControllerValue(controllerValue: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setControllerValueBind, handle, controllerValue)
    }

    fun getControllerValue(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getControllerValueBind, handle)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): InputEventMIDI? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): InputEventMIDI? =
            if (handle.address() == 0L) null else InputEventMIDI(handle)

        private const val SET_CHANNEL_HASH = 1286410249L
        private val setChannelBind by lazy {
            ObjectCalls.getMethodBind("InputEventMIDI", "set_channel", SET_CHANNEL_HASH)
        }

        private const val GET_CHANNEL_HASH = 3905245786L
        private val getChannelBind by lazy {
            ObjectCalls.getMethodBind("InputEventMIDI", "get_channel", GET_CHANNEL_HASH)
        }

        private const val SET_MESSAGE_HASH = 1064271510L
        private val setMessageBind by lazy {
            ObjectCalls.getMethodBind("InputEventMIDI", "set_message", SET_MESSAGE_HASH)
        }

        private const val GET_MESSAGE_HASH = 1936512097L
        private val getMessageBind by lazy {
            ObjectCalls.getMethodBind("InputEventMIDI", "get_message", GET_MESSAGE_HASH)
        }

        private const val SET_PITCH_HASH = 1286410249L
        private val setPitchBind by lazy {
            ObjectCalls.getMethodBind("InputEventMIDI", "set_pitch", SET_PITCH_HASH)
        }

        private const val GET_PITCH_HASH = 3905245786L
        private val getPitchBind by lazy {
            ObjectCalls.getMethodBind("InputEventMIDI", "get_pitch", GET_PITCH_HASH)
        }

        private const val SET_VELOCITY_HASH = 1286410249L
        private val setVelocityBind by lazy {
            ObjectCalls.getMethodBind("InputEventMIDI", "set_velocity", SET_VELOCITY_HASH)
        }

        private const val GET_VELOCITY_HASH = 3905245786L
        private val getVelocityBind by lazy {
            ObjectCalls.getMethodBind("InputEventMIDI", "get_velocity", GET_VELOCITY_HASH)
        }

        private const val SET_INSTRUMENT_HASH = 1286410249L
        private val setInstrumentBind by lazy {
            ObjectCalls.getMethodBind("InputEventMIDI", "set_instrument", SET_INSTRUMENT_HASH)
        }

        private const val GET_INSTRUMENT_HASH = 3905245786L
        private val getInstrumentBind by lazy {
            ObjectCalls.getMethodBind("InputEventMIDI", "get_instrument", GET_INSTRUMENT_HASH)
        }

        private const val SET_PRESSURE_HASH = 1286410249L
        private val setPressureBind by lazy {
            ObjectCalls.getMethodBind("InputEventMIDI", "set_pressure", SET_PRESSURE_HASH)
        }

        private const val GET_PRESSURE_HASH = 3905245786L
        private val getPressureBind by lazy {
            ObjectCalls.getMethodBind("InputEventMIDI", "get_pressure", GET_PRESSURE_HASH)
        }

        private const val SET_CONTROLLER_NUMBER_HASH = 1286410249L
        private val setControllerNumberBind by lazy {
            ObjectCalls.getMethodBind("InputEventMIDI", "set_controller_number", SET_CONTROLLER_NUMBER_HASH)
        }

        private const val GET_CONTROLLER_NUMBER_HASH = 3905245786L
        private val getControllerNumberBind by lazy {
            ObjectCalls.getMethodBind("InputEventMIDI", "get_controller_number", GET_CONTROLLER_NUMBER_HASH)
        }

        private const val SET_CONTROLLER_VALUE_HASH = 1286410249L
        private val setControllerValueBind by lazy {
            ObjectCalls.getMethodBind("InputEventMIDI", "set_controller_value", SET_CONTROLLER_VALUE_HASH)
        }

        private const val GET_CONTROLLER_VALUE_HASH = 3905245786L
        private val getControllerValueBind by lazy {
            ObjectCalls.getMethodBind("InputEventMIDI", "get_controller_value", GET_CONTROLLER_VALUE_HASH)
        }
    }
}
