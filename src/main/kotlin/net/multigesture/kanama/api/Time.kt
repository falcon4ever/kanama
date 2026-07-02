package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

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
    fun getDateTimeDictFromUnixTime(unixTime: Long): Map<String, Any?> {
        return ObjectCalls.ptrcallWithLongArgRetDictionary(getDateTimeDictFromUnixTimeBind, singleton, unixTime)
    }

    @JvmStatic
    fun getDateDictFromUnixTime(unixTime: Long): Map<String, Any?> {
        return ObjectCalls.ptrcallWithLongArgRetDictionary(getDateDictFromUnixTimeBind, singleton, unixTime)
    }

    @JvmStatic
    fun getTimeDictFromUnixTime(unixTime: Long): Map<String, Any?> {
        return ObjectCalls.ptrcallWithLongArgRetDictionary(getTimeDictFromUnixTimeBind, singleton, unixTime)
    }

    @JvmStatic
    fun getDateTimeStringFromUnixTime(unixTime: Long, useSpace: Boolean = false): String {
        return ObjectCalls.ptrcallWithLongAndBoolArgRetString(getDateTimeStringFromUnixTimeBind, singleton, unixTime, useSpace)
    }

    @JvmStatic
    fun getDateStringFromUnixTime(unixTime: Long): String {
        return ObjectCalls.ptrcallWithLongArgRetString(getDateStringFromUnixTimeBind, singleton, unixTime)
    }

    @JvmStatic
    fun getTimeStringFromUnixTime(unixTime: Long): String {
        return ObjectCalls.ptrcallWithLongArgRetString(getTimeStringFromUnixTimeBind, singleton, unixTime)
    }

    @JvmStatic
    fun getDateTimeDictFromDateTimeString(value: String, weekday: Boolean): Map<String, Any?> {
        return ObjectCalls.ptrcallWithStringAndBoolArgRetDictionary(getDateTimeDictFromDateTimeStringBind, singleton, value, weekday)
    }

    @JvmStatic
    fun getDateTimeStringFromDateTimeDict(values: Map<String, Any?>, useSpace: Boolean = false): String {
        return ObjectCalls.ptrcallWithDictionaryAndBoolArgRetString(getDateTimeStringFromDateTimeDictBind, singleton, values, useSpace)
    }

    @JvmStatic
    fun getUnixTimeFromDateTimeDict(values: Map<String, Any?>): Long {
        return ObjectCalls.ptrcallWithDictionaryArgRetLong(getUnixTimeFromDateTimeDictBind, singleton, values)
    }

    @JvmStatic
    fun getUnixTimeFromDateTimeString(value: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(getUnixTimeFromDateTimeStringBind, singleton, value)
    }

    @JvmStatic
    fun getOffsetStringFromOffsetMinutes(minutes: Long): String {
        return ObjectCalls.ptrcallWithLongArgRetString(getOffsetStringFromOffsetMinutesBind, singleton, minutes)
    }

    @JvmStatic
    fun getDateTimeDictFromSystem(utc: Boolean = false): Map<String, Any?> {
        return ObjectCalls.ptrcallWithBoolArgRetDictionary(getDateTimeDictFromSystemBind, singleton, utc)
    }

    @JvmStatic
    fun getDateDictFromSystem(utc: Boolean = false): Map<String, Any?> {
        return ObjectCalls.ptrcallWithBoolArgRetDictionary(getDateDictFromSystemBind, singleton, utc)
    }

    @JvmStatic
    fun getTimeDictFromSystem(utc: Boolean = false): Map<String, Any?> {
        return ObjectCalls.ptrcallWithBoolArgRetDictionary(getTimeDictFromSystemBind, singleton, utc)
    }

    @JvmStatic
    fun getDateTimeStringFromSystem(utc: Boolean = false, useSpace: Boolean = false): String {
        return ObjectCalls.ptrcallWithTwoBoolArgsRetString(getDateTimeStringFromSystemBind, singleton, utc, useSpace)
    }

    @JvmStatic
    fun getDateStringFromSystem(utc: Boolean = false): String {
        return ObjectCalls.ptrcallWithBoolArgRetString(getDateStringFromSystemBind, singleton, utc)
    }

    @JvmStatic
    fun getTimeStringFromSystem(utc: Boolean = false): String {
        return ObjectCalls.ptrcallWithBoolArgRetString(getTimeStringFromSystemBind, singleton, utc)
    }

    @JvmStatic
    fun getTimeZoneFromSystem(): Map<String, Any?> {
        return ObjectCalls.ptrcallNoArgsRetDictionary(getTimeZoneFromSystemBind, singleton)
    }

    @JvmStatic
    fun getUnixTimeFromSystem(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getUnixTimeFromSystemBind, singleton)
    }

    @JvmStatic
    fun getTicksMsec(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTicksMsecBind, singleton)
    }

    @JvmStatic
    fun getTicksUsec(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTicksUsecBind, singleton)
    }

    @JvmStatic
    fun fromHandle(handle: MemorySegment): Time? =
        wrap(handle)

    internal fun wrap(handle: MemorySegment): Time? =
        if (handle.address() == 0L) null else this

    private const val GET_DATETIME_DICT_FROM_UNIX_TIME_HASH = 3485342025L
    private val getDateTimeDictFromUnixTimeBind by lazy {
        ObjectCalls.getMethodBind("Time", "get_datetime_dict_from_unix_time", GET_DATETIME_DICT_FROM_UNIX_TIME_HASH)
    }

    private const val GET_DATE_DICT_FROM_UNIX_TIME_HASH = 3485342025L
    private val getDateDictFromUnixTimeBind by lazy {
        ObjectCalls.getMethodBind("Time", "get_date_dict_from_unix_time", GET_DATE_DICT_FROM_UNIX_TIME_HASH)
    }

    private const val GET_TIME_DICT_FROM_UNIX_TIME_HASH = 3485342025L
    private val getTimeDictFromUnixTimeBind by lazy {
        ObjectCalls.getMethodBind("Time", "get_time_dict_from_unix_time", GET_TIME_DICT_FROM_UNIX_TIME_HASH)
    }

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

    private const val GET_DATETIME_DICT_FROM_DATETIME_STRING_HASH = 3253569256L
    private val getDateTimeDictFromDateTimeStringBind by lazy {
        ObjectCalls.getMethodBind("Time", "get_datetime_dict_from_datetime_string", GET_DATETIME_DICT_FROM_DATETIME_STRING_HASH)
    }

    private const val GET_DATETIME_STRING_FROM_DATETIME_DICT_HASH = 1898123706L
    private val getDateTimeStringFromDateTimeDictBind by lazy {
        ObjectCalls.getMethodBind("Time", "get_datetime_string_from_datetime_dict", GET_DATETIME_STRING_FROM_DATETIME_DICT_HASH)
    }

    private const val GET_UNIX_TIME_FROM_DATETIME_DICT_HASH = 3021115443L
    private val getUnixTimeFromDateTimeDictBind by lazy {
        ObjectCalls.getMethodBind("Time", "get_unix_time_from_datetime_dict", GET_UNIX_TIME_FROM_DATETIME_DICT_HASH)
    }

    private const val GET_UNIX_TIME_FROM_DATETIME_STRING_HASH = 1321353865L
    private val getUnixTimeFromDateTimeStringBind by lazy {
        ObjectCalls.getMethodBind("Time", "get_unix_time_from_datetime_string", GET_UNIX_TIME_FROM_DATETIME_STRING_HASH)
    }

    private const val GET_OFFSET_STRING_FROM_OFFSET_MINUTES_HASH = 844755477L
    private val getOffsetStringFromOffsetMinutesBind by lazy {
        ObjectCalls.getMethodBind("Time", "get_offset_string_from_offset_minutes", GET_OFFSET_STRING_FROM_OFFSET_MINUTES_HASH)
    }

    private const val GET_DATETIME_DICT_FROM_SYSTEM_HASH = 205769976L
    private val getDateTimeDictFromSystemBind by lazy {
        ObjectCalls.getMethodBind("Time", "get_datetime_dict_from_system", GET_DATETIME_DICT_FROM_SYSTEM_HASH)
    }

    private const val GET_DATE_DICT_FROM_SYSTEM_HASH = 205769976L
    private val getDateDictFromSystemBind by lazy {
        ObjectCalls.getMethodBind("Time", "get_date_dict_from_system", GET_DATE_DICT_FROM_SYSTEM_HASH)
    }

    private const val GET_TIME_DICT_FROM_SYSTEM_HASH = 205769976L
    private val getTimeDictFromSystemBind by lazy {
        ObjectCalls.getMethodBind("Time", "get_time_dict_from_system", GET_TIME_DICT_FROM_SYSTEM_HASH)
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

    private const val GET_TIME_ZONE_FROM_SYSTEM_HASH = 3102165223L
    private val getTimeZoneFromSystemBind by lazy {
        ObjectCalls.getMethodBind("Time", "get_time_zone_from_system", GET_TIME_ZONE_FROM_SYSTEM_HASH)
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
