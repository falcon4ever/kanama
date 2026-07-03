package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Represents a MIDI message from a MIDI device, such as a musical keyboard.
 *
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

    /**
     * The MIDI channel of this message, ranging from `0` to `15`. MIDI channel `9` is reserved for
     * percussion instruments.
     *
     * Generated from Godot docs: InputEventMIDI.set_channel
     */
    fun setChannel(channel: Int) {
        ObjectCalls.ptrcallWithIntArg(setChannelBind, handle, channel)
    }

    /**
     * The MIDI channel of this message, ranging from `0` to `15`. MIDI channel `9` is reserved for
     * percussion instruments.
     *
     * Generated from Godot docs: InputEventMIDI.get_channel
     */
    fun getChannel(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getChannelBind, handle)
    }

    /**
     * Represents the type of MIDI message (see the `MIDIMessage` enum). For more information, see the
     * MIDI message status byte list chart
     * (https://www.midi.org/specifications-old/item/table-2-expanded-messages-list-status-bytes).
     *
     * Generated from Godot docs: InputEventMIDI.set_message
     */
    fun setMessage(message: Long) {
        ObjectCalls.ptrcallWithLongArg(setMessageBind, handle, message)
    }

    /**
     * Represents the type of MIDI message (see the `MIDIMessage` enum). For more information, see the
     * MIDI message status byte list chart
     * (https://www.midi.org/specifications-old/item/table-2-expanded-messages-list-status-bytes).
     *
     * Generated from Godot docs: InputEventMIDI.get_message
     */
    fun getMessage(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getMessageBind, handle)
    }

    /**
     * The pitch index number of this MIDI message. This value ranges from `0` to `127`. On a piano,
     * the middle C is `60`, followed by a C-sharp (`61`), then a D (`62`), and so on. Each octave is
     * split in offsets of 12. See the "MIDI note number" column of the piano key frequency chart
     * (https://en.wikipedia.org/wiki/Piano_key_frequencies) a full list.
     *
     * Generated from Godot docs: InputEventMIDI.set_pitch
     */
    fun setPitch(pitch: Int) {
        ObjectCalls.ptrcallWithIntArg(setPitchBind, handle, pitch)
    }

    /**
     * The pitch index number of this MIDI message. This value ranges from `0` to `127`. On a piano,
     * the middle C is `60`, followed by a C-sharp (`61`), then a D (`62`), and so on. Each octave is
     * split in offsets of 12. See the "MIDI note number" column of the piano key frequency chart
     * (https://en.wikipedia.org/wiki/Piano_key_frequencies) a full list.
     *
     * Generated from Godot docs: InputEventMIDI.get_pitch
     */
    fun getPitch(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getPitchBind, handle)
    }

    /**
     * The velocity of the MIDI message. This value ranges from `0` to `127`. For a musical keyboard,
     * this corresponds to how quickly the key was pressed, and is rarely above `110` in practice.
     * Note: Some MIDI devices may send a `MIDI_MESSAGE_NOTE_ON` message with `0` velocity and expect
     * it to be treated the same as a `MIDI_MESSAGE_NOTE_OFF` message. If necessary, this can be
     * handled with a few lines of code:
     *
     * Generated from Godot docs: InputEventMIDI.set_velocity
     */
    fun setVelocity(velocity: Int) {
        ObjectCalls.ptrcallWithIntArg(setVelocityBind, handle, velocity)
    }

    /**
     * The velocity of the MIDI message. This value ranges from `0` to `127`. For a musical keyboard,
     * this corresponds to how quickly the key was pressed, and is rarely above `110` in practice.
     * Note: Some MIDI devices may send a `MIDI_MESSAGE_NOTE_ON` message with `0` velocity and expect
     * it to be treated the same as a `MIDI_MESSAGE_NOTE_OFF` message. If necessary, this can be
     * handled with a few lines of code:
     *
     * Generated from Godot docs: InputEventMIDI.get_velocity
     */
    fun getVelocity(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getVelocityBind, handle)
    }

    /**
     * The instrument (also called program or preset) used on this MIDI message. This value ranges from
     * `0` to `127`. To see what each value means, refer to the General MIDI's instrument list
     * (https://en.wikipedia.org/wiki/General_MIDI#Program_change_events). Keep in mind that the list
     * is off by 1 because it does not begin from 0. A value of `0` corresponds to the acoustic grand
     * piano.
     *
     * Generated from Godot docs: InputEventMIDI.set_instrument
     */
    fun setInstrument(instrument: Int) {
        ObjectCalls.ptrcallWithIntArg(setInstrumentBind, handle, instrument)
    }

    /**
     * The instrument (also called program or preset) used on this MIDI message. This value ranges from
     * `0` to `127`. To see what each value means, refer to the General MIDI's instrument list
     * (https://en.wikipedia.org/wiki/General_MIDI#Program_change_events). Keep in mind that the list
     * is off by 1 because it does not begin from 0. A value of `0` corresponds to the acoustic grand
     * piano.
     *
     * Generated from Godot docs: InputEventMIDI.get_instrument
     */
    fun getInstrument(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getInstrumentBind, handle)
    }

    /**
     * The strength of the key being pressed. This value ranges from `0` to `127`. Note: For many
     * devices, this value is always `0`. Other devices such as musical keyboards may simulate pressure
     * by changing the `velocity`, instead.
     *
     * Generated from Godot docs: InputEventMIDI.set_pressure
     */
    fun setPressure(pressure: Int) {
        ObjectCalls.ptrcallWithIntArg(setPressureBind, handle, pressure)
    }

    /**
     * The strength of the key being pressed. This value ranges from `0` to `127`. Note: For many
     * devices, this value is always `0`. Other devices such as musical keyboards may simulate pressure
     * by changing the `velocity`, instead.
     *
     * Generated from Godot docs: InputEventMIDI.get_pressure
     */
    fun getPressure(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getPressureBind, handle)
    }

    /**
     * The unique number of the controller, if `message` is `MIDI_MESSAGE_CONTROL_CHANGE`, otherwise
     * this is `0`. This value can be used to identify sliders for volume, balance, and panning, as
     * well as switches and pedals on the MIDI device. See the General MIDI specification
     * (https://en.wikipedia.org/wiki/General_MIDI#Controller_events) for a small list.
     *
     * Generated from Godot docs: InputEventMIDI.set_controller_number
     */
    fun setControllerNumber(controllerNumber: Int) {
        ObjectCalls.ptrcallWithIntArg(setControllerNumberBind, handle, controllerNumber)
    }

    /**
     * The unique number of the controller, if `message` is `MIDI_MESSAGE_CONTROL_CHANGE`, otherwise
     * this is `0`. This value can be used to identify sliders for volume, balance, and panning, as
     * well as switches and pedals on the MIDI device. See the General MIDI specification
     * (https://en.wikipedia.org/wiki/General_MIDI#Controller_events) for a small list.
     *
     * Generated from Godot docs: InputEventMIDI.get_controller_number
     */
    fun getControllerNumber(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getControllerNumberBind, handle)
    }

    /**
     * The value applied to the controller. If `message` is `MIDI_MESSAGE_CONTROL_CHANGE`, this value
     * ranges from `0` to `127`, otherwise it is `0`. See also `controller_value`.
     *
     * Generated from Godot docs: InputEventMIDI.set_controller_value
     */
    fun setControllerValue(controllerValue: Int) {
        ObjectCalls.ptrcallWithIntArg(setControllerValueBind, handle, controllerValue)
    }

    /**
     * The value applied to the controller. If `message` is `MIDI_MESSAGE_CONTROL_CHANGE`, this value
     * ranges from `0` to `127`, otherwise it is `0`. See also `controller_value`.
     *
     * Generated from Godot docs: InputEventMIDI.get_controller_value
     */
    fun getControllerValue(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getControllerValueBind, handle)
    }

    companion object {
        @JvmStatic
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
