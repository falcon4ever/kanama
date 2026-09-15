package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: OggPacketSequence
 */
class OggPacketSequence(handle: GodotHandle) : Resource(handle) {
    var packetData: List<List<Any?>>
        @JvmName("packetDataProperty")
        get() = getPacketData()
        @JvmName("setPacketDataProperty")
        set(value) = setPacketData(value)

    var granulePositions: List<Long>
        @JvmName("granulePositionsProperty")
        get() = getPacketGranulePositions()
        @JvmName("setGranulePositionsProperty")
        set(value) = setPacketGranulePositions(value)

    var samplingRate: Double
        @JvmName("samplingRateProperty")
        get() = getSamplingRate()
        @JvmName("setSamplingRateProperty")
        set(value) = setSamplingRate(value)

    fun setPacketData(packetData: List<List<Any?>>) {
        checkOpen()
        ObjectCalls.ptrcallWithArrayListArg(setPacketDataBind, segment, packetData)
    }

    fun getPacketData(): List<List<Any?>> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetArrayList(getPacketDataBind, segment)
    }

    fun setPacketGranulePositions(granulePositions: List<Long>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedInt64ListArg(setPacketGranulePositionsBind, segment, granulePositions)
    }

    fun getPacketGranulePositions(): List<Long> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt64List(getPacketGranulePositionsBind, segment)
    }

    fun setSamplingRate(samplingRate: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setSamplingRateBind, segment, samplingRate)
    }

    fun getSamplingRate(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getSamplingRateBind, segment)
    }

    fun getLength(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getLengthBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OggPacketSequence? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OggPacketSequence? =
            if (handle.address() == 0L) null else OggPacketSequence(GodotHandle(handle))

        private const val SET_PACKET_DATA_HASH = 381264803L
        private val setPacketDataBind by lazy {
            ObjectCalls.getMethodBind("OggPacketSequence", "set_packet_data", SET_PACKET_DATA_HASH)
        }

        private const val GET_PACKET_DATA_HASH = 3995934104L
        private val getPacketDataBind by lazy {
            ObjectCalls.getMethodBind("OggPacketSequence", "get_packet_data", GET_PACKET_DATA_HASH)
        }

        private const val SET_PACKET_GRANULE_POSITIONS_HASH = 3709968205L
        private val setPacketGranulePositionsBind by lazy {
            ObjectCalls.getMethodBind("OggPacketSequence", "set_packet_granule_positions", SET_PACKET_GRANULE_POSITIONS_HASH)
        }

        private const val GET_PACKET_GRANULE_POSITIONS_HASH = 235988956L
        private val getPacketGranulePositionsBind by lazy {
            ObjectCalls.getMethodBind("OggPacketSequence", "get_packet_granule_positions", GET_PACKET_GRANULE_POSITIONS_HASH)
        }

        private const val SET_SAMPLING_RATE_HASH = 373806689L
        private val setSamplingRateBind by lazy {
            ObjectCalls.getMethodBind("OggPacketSequence", "set_sampling_rate", SET_SAMPLING_RATE_HASH)
        }

        private const val GET_SAMPLING_RATE_HASH = 1740695150L
        private val getSamplingRateBind by lazy {
            ObjectCalls.getMethodBind("OggPacketSequence", "get_sampling_rate", GET_SAMPLING_RATE_HASH)
        }

        private const val GET_LENGTH_HASH = 1740695150L
        private val getLengthBind by lazy {
            ObjectCalls.getMethodBind("OggPacketSequence", "get_length", GET_LENGTH_HASH)
        }
    }
}
