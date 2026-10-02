package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A singleton for working with time data.
 *
 * Generated from Godot docs: Time
 */
object Time {
    private val singleton: RawSegment by lazy {
        ObjectCalls.getSingleton("Time")
    }

    @JvmStatic
    fun getDateTimeDictFromUnixTime(unixTime: Long): Map<String, Any?> {
        return ObjectCalls.ptrcallWithLongArgRetDictionary(getDateTimeDictFromUnixTimeBind, singleton, unixTime)
    }

    /**
     * Converts the given Unix timestamp to a dictionary of keys: `year`, `month`, `day`, and
     * `weekday`.
     *
     * Generated from Godot docs: Time.get_date_dict_from_unix_time
     */
    @JvmStatic
    fun getDateDictFromUnixTime(unixTime: Long): Map<String, Any?> {
        return ObjectCalls.ptrcallWithLongArgRetDictionary(getDateDictFromUnixTimeBind, singleton, unixTime)
    }

    /**
     * Converts the given time to a dictionary of keys: `hour`, `minute`, and `second`.
     *
     * Generated from Godot docs: Time.get_time_dict_from_unix_time
     */
    @JvmStatic
    fun getTimeDictFromUnixTime(unixTime: Long): Map<String, Any?> {
        return ObjectCalls.ptrcallWithLongArgRetDictionary(getTimeDictFromUnixTimeBind, singleton, unixTime)
    }

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
    fun getDateTimeDictFromSystem(utc: Boolean = false): Map<String, Any?> {
        return ObjectCalls.ptrcallWithBoolArgRetDictionary(getDateTimeDictFromSystemBind, singleton, utc)
    }

    /**
     * Returns the current date as a dictionary of keys: `year`, `month`, `day`, and `weekday`. The
     * returned values are in the system's local time when `utc` is `false`, otherwise they are in UTC.
     *
     * Generated from Godot docs: Time.get_date_dict_from_system
     */
    @JvmStatic
    fun getDateDictFromSystem(utc: Boolean = false): Map<String, Any?> {
        return ObjectCalls.ptrcallWithBoolArgRetDictionary(getDateDictFromSystemBind, singleton, utc)
    }

    /**
     * Returns the current time as a dictionary of keys: `hour`, `minute`, and `second`. The returned
     * values are in the system's local time when `utc` is `false`, otherwise they are in UTC.
     *
     * Generated from Godot docs: Time.get_time_dict_from_system
     */
    @JvmStatic
    fun getTimeDictFromSystem(utc: Boolean = false): Map<String, Any?> {
        return ObjectCalls.ptrcallWithBoolArgRetDictionary(getTimeDictFromSystemBind, singleton, utc)
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
     * Returns the current time zone as a dictionary of keys: `bias` and `name`. - `bias` is the offset
     * from UTC in minutes, since not all time zones are multiples of an hour from UTC. - `name` is the
     * localized name of the time zone, according to the OS locale settings of the current user.
     *
     * Generated from Godot docs: Time.get_time_zone_from_system
     */
    @JvmStatic
    fun getTimeZoneFromSystem(): Map<String, Any?> {
        return ObjectCalls.ptrcallNoArgsRetDictionary(getTimeZoneFromSystemBind, singleton)
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

    @JvmInline
    value class Month(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The month of January, represented numerically as `01`.
             *
             * Generated from Godot docs: Time.MONTH_JANUARY
             */
            val JANUARY: Month get() = Month(1L)
            /**
             * The month of February, represented numerically as `02`.
             *
             * Generated from Godot docs: Time.MONTH_FEBRUARY
             */
            val FEBRUARY: Month get() = Month(2L)
            /**
             * The month of March, represented numerically as `03`.
             *
             * Generated from Godot docs: Time.MONTH_MARCH
             */
            val MARCH: Month get() = Month(3L)
            /**
             * The month of April, represented numerically as `04`.
             *
             * Generated from Godot docs: Time.MONTH_APRIL
             */
            val APRIL: Month get() = Month(4L)
            /**
             * The month of May, represented numerically as `05`.
             *
             * Generated from Godot docs: Time.MONTH_MAY
             */
            val MAY: Month get() = Month(5L)
            /**
             * The month of June, represented numerically as `06`.
             *
             * Generated from Godot docs: Time.MONTH_JUNE
             */
            val JUNE: Month get() = Month(6L)
            /**
             * The month of July, represented numerically as `07`.
             *
             * Generated from Godot docs: Time.MONTH_JULY
             */
            val JULY: Month get() = Month(7L)
            /**
             * The month of August, represented numerically as `08`.
             *
             * Generated from Godot docs: Time.MONTH_AUGUST
             */
            val AUGUST: Month get() = Month(8L)
            /**
             * The month of September, represented numerically as `09`.
             *
             * Generated from Godot docs: Time.MONTH_SEPTEMBER
             */
            val SEPTEMBER: Month get() = Month(9L)
            /**
             * The month of October, represented numerically as `10`.
             *
             * Generated from Godot docs: Time.MONTH_OCTOBER
             */
            val OCTOBER: Month get() = Month(10L)
            /**
             * The month of November, represented numerically as `11`.
             *
             * Generated from Godot docs: Time.MONTH_NOVEMBER
             */
            val NOVEMBER: Month get() = Month(11L)
            /**
             * The month of December, represented numerically as `12`.
             *
             * Generated from Godot docs: Time.MONTH_DECEMBER
             */
            val DECEMBER: Month get() = Month(12L)
        }
    }

    @JvmInline
    value class Weekday(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The day of the week Sunday, represented numerically as `0`.
             *
             * Generated from Godot docs: Time.WEEKDAY_SUNDAY
             */
            val SUNDAY: Weekday get() = Weekday(0L)
            /**
             * The day of the week Monday, represented numerically as `1`.
             *
             * Generated from Godot docs: Time.WEEKDAY_MONDAY
             */
            val MONDAY: Weekday get() = Weekday(1L)
            /**
             * The day of the week Tuesday, represented numerically as `2`.
             *
             * Generated from Godot docs: Time.WEEKDAY_TUESDAY
             */
            val TUESDAY: Weekday get() = Weekday(2L)
            /**
             * The day of the week Wednesday, represented numerically as `3`.
             *
             * Generated from Godot docs: Time.WEEKDAY_WEDNESDAY
             */
            val WEDNESDAY: Weekday get() = Weekday(3L)
            /**
             * The day of the week Thursday, represented numerically as `4`.
             *
             * Generated from Godot docs: Time.WEEKDAY_THURSDAY
             */
            val THURSDAY: Weekday get() = Weekday(4L)
            /**
             * The day of the week Friday, represented numerically as `5`.
             *
             * Generated from Godot docs: Time.WEEKDAY_FRIDAY
             */
            val FRIDAY: Weekday get() = Weekday(5L)
            /**
             * The day of the week Saturday, represented numerically as `6`.
             *
             * Generated from Godot docs: Time.WEEKDAY_SATURDAY
             */
            val SATURDAY: Weekday get() = Weekday(6L)
        }
    }

    @JvmStatic
    fun fromHandle(handle: GodotHandle): Time? =
        wrap(handle.segment)

    internal fun wrap(handle: RawSegment): Time? =
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
