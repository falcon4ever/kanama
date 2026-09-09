package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * A singleton for working with time data.
 *
 * Generated from Godot docs: Time
 */
object Time {
    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("Time")
    }

    const val MONTH_JANUARY: Long = 1L
    const val MONTH_FEBRUARY: Long = 2L
    const val MONTH_MARCH: Long = 3L
    const val MONTH_APRIL: Long = 4L
    const val MONTH_MAY: Long = 5L
    const val MONTH_JUNE: Long = 6L
    const val MONTH_JULY: Long = 7L
    const val MONTH_AUGUST: Long = 8L
    const val MONTH_SEPTEMBER: Long = 9L
    const val MONTH_OCTOBER: Long = 10L
    const val MONTH_NOVEMBER: Long = 11L
    const val MONTH_DECEMBER: Long = 12L
    const val WEEKDAY_SUNDAY: Long = 0L
    const val WEEKDAY_MONDAY: Long = 1L
    const val WEEKDAY_TUESDAY: Long = 2L
    const val WEEKDAY_WEDNESDAY: Long = 3L
    const val WEEKDAY_THURSDAY: Long = 4L
    const val WEEKDAY_FRIDAY: Long = 5L
    const val WEEKDAY_SATURDAY: Long = 6L

    @JvmStatic
    fun getUnixTimeFromDateTimeString(value: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(getUnixTimeFromDateTimeStringBind, singleton, value)
    }

    /**
     * Returns the current Unix timestamp in seconds based on the system time in UTC. This method is
     * implemented by the operating system and always returns the time in UTC. The Unix timestamp is
     * the number of seconds passed since 1970-01-01 at 00:00:00, the Unix epoch
     * (https://en.wikipedia.org/wiki/Unix_time). Note: Unlike other methods that use integer
     * timestamps, this method returns the timestamp as a `float` for sub-second precision.
     *
     * Generated from Godot docs: Time.get_unix_time_from_system
     */
    @JvmStatic
    fun getUnixTimeFromSystem(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getUnixTimeFromSystemBind, singleton)
    }

    /**
     * Returns the amount of time passed in milliseconds since the engine started. Will always be
     * positive or 0 and uses a 64-bit value (it will wrap after roughly 500 million years).
     *
     * Generated from Godot docs: Time.get_ticks_msec
     */
    @JvmStatic
    fun getTicksMsec(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTicksMsecBind, singleton)
    }

    /**
     * Returns the amount of time passed in microseconds since the engine started. Will always be
     * positive or 0 and uses a 64-bit value (it will wrap after roughly half a million years).
     *
     * Generated from Godot docs: Time.get_ticks_usec
     */
    @JvmStatic
    fun getTicksUsec(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTicksUsecBind, singleton)
    }

    @JvmStatic
    fun fromHandle(handle: MemorySegment): Time? =
        wrap(handle)

    internal fun wrap(handle: MemorySegment): Time? =
        if (handle.address() == 0L) null else this

    private const val GET_UNIX_TIME_FROM_DATETIME_STRING_HASH = 1321353865L
    private val getUnixTimeFromDateTimeStringBind by lazy {
        ObjectCalls.getMethodBind("Time", "get_unix_time_from_datetime_string", GET_UNIX_TIME_FROM_DATETIME_STRING_HASH)
    }

    private const val GET_UNIX_TIME_FROM_SYSTEM_HASH = 1740695150L
    private val getUnixTimeFromSystemBind by lazy {
        ObjectCalls.getMethodBind("Time", "get_unix_time_from_system", GET_UNIX_TIME_FROM_SYSTEM_HASH)
    }

    private const val GET_TICKS_MSEC_HASH = 3905245786L
    private val getTicksMsecBind by lazy {
        ObjectCalls.getMethodBind("Time", "get_ticks_msec", GET_TICKS_MSEC_HASH)
    }

    private const val GET_TICKS_USEC_HASH = 3905245786L
    private val getTicksUsecBind by lazy {
        ObjectCalls.getMethodBind("Time", "get_ticks_usec", GET_TICKS_USEC_HASH)
    }
}
