package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

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
        ObjectCalls.ptrcallWithArrayListArg(Binds.setPacketDataBind, segment, packetData)
    }

    fun getPacketData(): List<List<Any?>> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetArrayList(Binds.getPacketDataBind, segment)
    }

    fun setPacketGranulePositions(granulePositions: List<Long>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedInt64ListArg(Binds.setPacketGranulePositionsBind, segment, granulePositions)
    }

    fun getPacketGranulePositions(): List<Long> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt64List(Binds.getPacketGranulePositionsBind, segment)
    }

    fun setSamplingRate(samplingRate: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setSamplingRateBind, segment, samplingRate)
    }

    fun getSamplingRate(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getSamplingRateBind, segment)
    }

    fun getLength(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getLengthBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OggPacketSequence? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OggPacketSequence? =
            if (handle.address() == 0L) null else RefCounted.owned(OggPacketSequence(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OggPacketSequence? =
            if (handle.address() == 0L) null else OggPacketSequence(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_PACKET_DATA_HASH = 381264803L
        @JvmField
        val setPacketDataBind =
            ObjectCalls.getMethodBind("OggPacketSequence", "set_packet_data", SET_PACKET_DATA_HASH)

        private const val GET_PACKET_DATA_HASH = 3995934104L
        @JvmField
        val getPacketDataBind =
            ObjectCalls.getMethodBind("OggPacketSequence", "get_packet_data", GET_PACKET_DATA_HASH)

        private const val SET_PACKET_GRANULE_POSITIONS_HASH = 3709968205L
        @JvmField
        val setPacketGranulePositionsBind =
            ObjectCalls.getMethodBind("OggPacketSequence", "set_packet_granule_positions", SET_PACKET_GRANULE_POSITIONS_HASH)

        private const val GET_PACKET_GRANULE_POSITIONS_HASH = 235988956L
        @JvmField
        val getPacketGranulePositionsBind =
            ObjectCalls.getMethodBind("OggPacketSequence", "get_packet_granule_positions", GET_PACKET_GRANULE_POSITIONS_HASH)

        private const val SET_SAMPLING_RATE_HASH = 373806689L
        @JvmField
        val setSamplingRateBind =
            ObjectCalls.getMethodBind("OggPacketSequence", "set_sampling_rate", SET_SAMPLING_RATE_HASH)

        private const val GET_SAMPLING_RATE_HASH = 1740695150L
        @JvmField
        val getSamplingRateBind =
            ObjectCalls.getMethodBind("OggPacketSequence", "get_sampling_rate", GET_SAMPLING_RATE_HASH)

        private const val GET_LENGTH_HASH = 1740695150L
        @JvmField
        val getLengthBind =
            ObjectCalls.getMethodBind("OggPacketSequence", "get_length", GET_LENGTH_HASH)
    }
}
