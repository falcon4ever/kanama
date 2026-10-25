package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A sequence of Ogg packets.
 *
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

    /**
     * Contains the raw packets that make up this OggPacketSequence.
     *
     * Generated from Godot docs: OggPacketSequence.set_packet_data
     */
    fun setPacketData(packetData: List<List<Any?>>) {
        checkOpen()
        ObjectCalls.ptrcallWithArrayListArg(Binds.setPacketDataBind, segment, packetData)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Contains the raw packets that make up this OggPacketSequence.
     *
     * Generated from Godot docs: OggPacketSequence.get_packet_data
     */
    fun getPacketData(): List<List<Any?>> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetArrayList(Binds.getPacketDataBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Contains the granule positions for each page in this packet sequence.
     *
     * Generated from Godot docs: OggPacketSequence.set_packet_granule_positions
     */
    fun setPacketGranulePositions(granulePositions: List<Long>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedInt64ListArg(Binds.setPacketGranulePositionsBind, segment, granulePositions)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Contains the granule positions for each page in this packet sequence.
     *
     * Generated from Godot docs: OggPacketSequence.get_packet_granule_positions
     */
    fun getPacketGranulePositions(): List<Long> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetPackedInt64List(Binds.getPacketGranulePositionsBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Holds sample rate information about this sequence. Must be set by another class that actually
     * understands the codec.
     *
     * Generated from Godot docs: OggPacketSequence.set_sampling_rate
     */
    fun setSamplingRate(samplingRate: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setSamplingRateBind, segment, samplingRate)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Holds sample rate information about this sequence. Must be set by another class that actually
     * understands the codec.
     *
     * Generated from Godot docs: OggPacketSequence.get_sampling_rate
     */
    fun getSamplingRate(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getSamplingRateBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The length of this stream, in seconds.
     *
     * Generated from Godot docs: OggPacketSequence.get_length
     */
    fun getLength(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getLengthBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OggPacketSequence? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OggPacketSequence? =
            if (handle.address() == 0L) null else RefCounted.owned(OggPacketSequence(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OggPacketSequence? =
            if (handle.address() == 0L) null else OggPacketSequence(GodotHandle(handle))

        /** A new OggPacketSequence, GDScript's `OggPacketSequence.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): OggPacketSequence =
            RefCounted.owned(OggPacketSequence(GodotHandle(ObjectCalls.constructObject("OggPacketSequence"))))
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
