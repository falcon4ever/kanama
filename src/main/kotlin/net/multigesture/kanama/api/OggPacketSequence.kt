package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: OggPacketSequence
 */
class OggPacketSequence(handle: MemorySegment) : Resource(handle) {
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
        ObjectCalls.ptrcallWithArrayListArg(setPacketDataBind, handle, packetData)
    }

    fun getPacketData(): List<List<Any?>> {
        return ObjectCalls.ptrcallNoArgsRetArrayList(getPacketDataBind, handle)
    }

    fun setPacketGranulePositions(granulePositions: List<Long>) {
        ObjectCalls.ptrcallWithPackedInt64ListArg(setPacketGranulePositionsBind, handle, granulePositions)
    }

    fun getPacketGranulePositions(): List<Long> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt64List(getPacketGranulePositionsBind, handle)
    }

    fun setSamplingRate(samplingRate: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSamplingRateBind, handle, samplingRate)
    }

    fun getSamplingRate(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSamplingRateBind, handle)
    }

    fun getLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLengthBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OggPacketSequence? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OggPacketSequence? =
            if (handle.address() == 0L) null else OggPacketSequence(handle)

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
