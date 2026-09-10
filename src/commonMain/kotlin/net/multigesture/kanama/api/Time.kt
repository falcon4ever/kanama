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
    fun getDateTimeStringFromUnixTime(unixTime: Long, useSpace: Boolean = false): String {
        return ObjectCalls.ptrcallWithLongAndBoolArgRetString(getDateTimeStringFromUnixTimeBind, singleton, unixTime, useSpace)
    }

    /**
     * Converts the given Unix timestamp to an ISO 8601 date string (YYYY-MM-DD).
     *
     * Generated from Godot docs: Time.get_date_string_from_unix_time
     */
    @JvmStatic
    fun getDateStringFromUnixTime(unixTime: Long): String {
        return ObjectCalls.ptrcallWithLongArgRetString(getDateStringFromUnixTimeBind, singleton, unixTime)
    }

    /**
     * Converts the given Unix timestamp to an ISO 8601 time string (HH:MM:SS).
     *
     * Generated from Godot docs: Time.get_time_string_from_unix_time
     */
    @JvmStatic
    fun getTimeStringFromUnixTime(unixTime: Long): String {
        return ObjectCalls.ptrcallWithLongArgRetString(getTimeStringFromUnixTimeBind, singleton, unixTime)
    }

    @JvmStatic
    fun getUnixTimeFromDateTimeString(value: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(getUnixTimeFromDateTimeStringBind, singleton, value)
    }

    /**
     * Converts the given timezone offset in minutes to a timezone offset string. For example, -480
     * returns "-08:00", 345 returns "+05:45", and 0 returns "+00:00".
     *
     * Generated from Godot docs: Time.get_offset_string_from_offset_minutes
     */
    @JvmStatic
    fun getOffsetStringFromOffsetMinutes(minutes: Long): String {
        return ObjectCalls.ptrcallWithLongArgRetString(getOffsetStringFromOffsetMinutesBind, singleton, minutes)
    }

    @JvmStatic
    fun getDateTimeStringFromSystem(utc: Boolean = false, useSpace: Boolean = false): String {
        return ObjectCalls.ptrcallWithTwoBoolArgsRetString(getDateTimeStringFromSystemBind, singleton, utc, useSpace)
    }

    /**
     * Returns the current date as an ISO 8601 date string (YYYY-MM-DD). The returned values are in the
     * system's local time when `utc` is `false`, otherwise they are in UTC.
     *
     * Generated from Godot docs: Time.get_date_string_from_system
     */
    @JvmStatic
    fun getDateStringFromSystem(utc: Boolean = false): String {
        return ObjectCalls.ptrcallWithBoolArgRetString(getDateStringFromSystemBind, singleton, utc)
    }

    /**
     * Returns the current time as an ISO 8601 time string (HH:MM:SS). The returned values are in the
     * system's local time when `utc` is `false`, otherwise they are in UTC.
     *
     * Generated from Godot docs: Time.get_time_string_from_system
     */
    @JvmStatic
    fun getTimeStringFromSystem(utc: Boolean = false): String {
        return ObjectCalls.ptrcallWithBoolArgRetString(getTimeStringFromSystemBind, singleton, utc)
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

    private const val GET_DATETIME_STRING_FROM_UNIX_TIME_HASH = 2311239925L
    private val getDateTimeStringFromUnixTimeBind by lazy {
        ObjectCalls.getMethodBind("Time", "get_datetime_string_from_unix_time", GET_DATETIME_STRING_FROM_UNIX_TIME_HASH)
    }

    private const val GET_DATE_STRING_FROM_UNIX_TIME_HASH = 844755477L
    private val getDateStringFromUnixTimeBind by lazy {
        ObjectCalls.getMethodBind("Time", "get_date_string_from_unix_time", GET_DATE_STRING_FROM_UNIX_TIME_HASH)
    }

    private const val GET_TIME_STRING_FROM_UNIX_TIME_HASH = 844755477L
    private val getTimeStringFromUnixTimeBind by lazy {
        ObjectCalls.getMethodBind("Time", "get_time_string_from_unix_time", GET_TIME_STRING_FROM_UNIX_TIME_HASH)
    }

    private const val GET_UNIX_TIME_FROM_DATETIME_STRING_HASH = 1321353865L
    private val getUnixTimeFromDateTimeStringBind by lazy {
        ObjectCalls.getMethodBind("Time", "get_unix_time_from_datetime_string", GET_UNIX_TIME_FROM_DATETIME_STRING_HASH)
    }

    private const val GET_OFFSET_STRING_FROM_OFFSET_MINUTES_HASH = 844755477L
    private val getOffsetStringFromOffsetMinutesBind by lazy {
        ObjectCalls.getMethodBind("Time", "get_offset_string_from_offset_minutes", GET_OFFSET_STRING_FROM_OFFSET_MINUTES_HASH)
    }

    private const val GET_DATETIME_STRING_FROM_SYSTEM_HASH = 1136425492L
    private val getDateTimeStringFromSystemBind by lazy {
        ObjectCalls.getMethodBind("Time", "get_datetime_string_from_system", GET_DATETIME_STRING_FROM_SYSTEM_HASH)
    }

    private const val GET_DATE_STRING_FROM_SYSTEM_HASH = 1162154673L
    private val getDateStringFromSystemBind by lazy {
        ObjectCalls.getMethodBind("Time", "get_date_string_from_system", GET_DATE_STRING_FROM_SYSTEM_HASH)
    }

    private const val GET_TIME_STRING_FROM_SYSTEM_HASH = 1162154673L
    private val getTimeStringFromSystemBind by lazy {
        ObjectCalls.getMethodBind("Time", "get_time_string_from_system", GET_TIME_STRING_FROM_SYSTEM_HASH)
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
