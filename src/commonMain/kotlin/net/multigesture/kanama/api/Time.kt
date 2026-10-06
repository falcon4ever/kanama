package net.multigesture.kanama.api

import kotlin.jvm.JvmField
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
    private inline val singleton: RawSegment
        get() = Binds.singleton

    @JvmStatic
    fun getDateTimeDictFromUnixTime(unixTime: Long): Map<String, Any?> {
        return ObjectCalls.ptrcallWithLongArgRetDictionary(Binds.getDateTimeDictFromUnixTimeBind, singleton, unixTime)
    }

    /**
     * Converts the given Unix timestamp to a dictionary of keys: `year`, `month`, `day`, and
     * `weekday`.
     *
     * Generated from Godot docs: Time.get_date_dict_from_unix_time
     */
    @JvmStatic
    fun getDateDictFromUnixTime(unixTime: Long): Map<String, Any?> {
        return ObjectCalls.ptrcallWithLongArgRetDictionary(Binds.getDateDictFromUnixTimeBind, singleton, unixTime)
    }

    /**
     * Converts the given time to a dictionary of keys: `hour`, `minute`, and `second`.
     *
     * Generated from Godot docs: Time.get_time_dict_from_unix_time
     */
    @JvmStatic
    fun getTimeDictFromUnixTime(unixTime: Long): Map<String, Any?> {
        return ObjectCalls.ptrcallWithLongArgRetDictionary(Binds.getTimeDictFromUnixTimeBind, singleton, unixTime)
    }

    @JvmStatic
    fun getDateTimeStringFromUnixTime(unixTime: Long, useSpace: Boolean = false): String {
        return ObjectCalls.ptrcallWithLongAndBoolArgRetString(Binds.getDateTimeStringFromUnixTimeBind, singleton, unixTime, useSpace)
    }

    /**
     * Converts the given Unix timestamp to an ISO 8601 date string (YYYY-MM-DD).
     *
     * Generated from Godot docs: Time.get_date_string_from_unix_time
     */
    @JvmStatic
    fun getDateStringFromUnixTime(unixTime: Long): String {
        return ObjectCalls.ptrcallWithLongArgRetString(Binds.getDateStringFromUnixTimeBind, singleton, unixTime)
    }

    /**
     * Converts the given Unix timestamp to an ISO 8601 time string (HH:MM:SS).
     *
     * Generated from Godot docs: Time.get_time_string_from_unix_time
     */
    @JvmStatic
    fun getTimeStringFromUnixTime(unixTime: Long): String {
        return ObjectCalls.ptrcallWithLongArgRetString(Binds.getTimeStringFromUnixTimeBind, singleton, unixTime)
    }

    @JvmStatic
    fun getDateTimeDictFromDateTimeString(value: String, weekday: Boolean): Map<String, Any?> {
        return ObjectCalls.ptrcallWithStringAndBoolArgRetDictionary(Binds.getDateTimeDictFromDateTimeStringBind, singleton, value, weekday)
    }

    @JvmStatic
    fun getDateTimeStringFromDateTimeDict(values: Map<String, Any?>, useSpace: Boolean = false): String {
        return ObjectCalls.ptrcallWithDictionaryAndBoolArgRetString(Binds.getDateTimeStringFromDateTimeDictBind, singleton, values, useSpace)
    }

    @JvmStatic
    fun getUnixTimeFromDateTimeDict(values: Map<String, Any?>): Long {
        return ObjectCalls.ptrcallWithDictionaryArgRetLong(Binds.getUnixTimeFromDateTimeDictBind, singleton, values)
    }

    @JvmStatic
    fun getUnixTimeFromDateTimeString(value: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(Binds.getUnixTimeFromDateTimeStringBind, singleton, value)
    }

    /**
     * Converts the given timezone offset in minutes to a timezone offset string. For example, -480
     * returns "-08:00", 345 returns "+05:45", and 0 returns "+00:00".
     *
     * Generated from Godot docs: Time.get_offset_string_from_offset_minutes
     */
    @JvmStatic
    fun getOffsetStringFromOffsetMinutes(minutes: Long): String {
        return ObjectCalls.ptrcallWithLongArgRetString(Binds.getOffsetStringFromOffsetMinutesBind, singleton, minutes)
    }

    @JvmStatic
    fun getDateTimeDictFromSystem(utc: Boolean = false): Map<String, Any?> {
        return ObjectCalls.ptrcallWithBoolArgRetDictionary(Binds.getDateTimeDictFromSystemBind, singleton, utc)
    }

    /**
     * Returns the current date as a dictionary of keys: `year`, `month`, `day`, and `weekday`. The
     * returned values are in the system's local time when `utc` is `false`, otherwise they are in UTC.
     *
     * Generated from Godot docs: Time.get_date_dict_from_system
     */
    @JvmStatic
    fun getDateDictFromSystem(utc: Boolean = false): Map<String, Any?> {
        return ObjectCalls.ptrcallWithBoolArgRetDictionary(Binds.getDateDictFromSystemBind, singleton, utc)
    }

    /**
     * Returns the current time as a dictionary of keys: `hour`, `minute`, and `second`. The returned
     * values are in the system's local time when `utc` is `false`, otherwise they are in UTC.
     *
     * Generated from Godot docs: Time.get_time_dict_from_system
     */
    @JvmStatic
    fun getTimeDictFromSystem(utc: Boolean = false): Map<String, Any?> {
        return ObjectCalls.ptrcallWithBoolArgRetDictionary(Binds.getTimeDictFromSystemBind, singleton, utc)
    }

    @JvmStatic
    fun getDateTimeStringFromSystem(utc: Boolean = false, useSpace: Boolean = false): String {
        return ObjectCalls.ptrcallWithTwoBoolArgsRetString(Binds.getDateTimeStringFromSystemBind, singleton, utc, useSpace)
    }

    /**
     * Returns the current date as an ISO 8601 date string (YYYY-MM-DD). The returned values are in the
     * system's local time when `utc` is `false`, otherwise they are in UTC.
     *
     * Generated from Godot docs: Time.get_date_string_from_system
     */
    @JvmStatic
    fun getDateStringFromSystem(utc: Boolean = false): String {
        return ObjectCalls.ptrcallWithBoolArgRetString(Binds.getDateStringFromSystemBind, singleton, utc)
    }

    /**
     * Returns the current time as an ISO 8601 time string (HH:MM:SS). The returned values are in the
     * system's local time when `utc` is `false`, otherwise they are in UTC.
     *
     * Generated from Godot docs: Time.get_time_string_from_system
     */
    @JvmStatic
    fun getTimeStringFromSystem(utc: Boolean = false): String {
        return ObjectCalls.ptrcallWithBoolArgRetString(Binds.getTimeStringFromSystemBind, singleton, utc)
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
        return ObjectCalls.ptrcallNoArgsRetDictionary(Binds.getTimeZoneFromSystemBind, singleton)
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
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getUnixTimeFromSystemBind, singleton)
    }

    /**
     * Returns the amount of time passed in milliseconds since the engine started. Will always be
     * positive or 0 and uses a 64-bit value (it will wrap after roughly 500 million years).
     *
     * Generated from Godot docs: Time.get_ticks_msec
     */
    @JvmStatic
    fun getTicksMsec(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(Binds.getTicksMsecBind, singleton)
    }

    /**
     * Returns the amount of time passed in microseconds since the engine started. Will always be
     * positive or 0 and uses a 64-bit value (it will wrap after roughly half a million years).
     *
     * Generated from Godot docs: Time.get_ticks_usec
     */
    @JvmStatic
    fun getTicksUsec(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(Binds.getTicksUsecBind, singleton)
    }

    /**
     * Godot's `Time.Month` enum as a typed value: `.value` is the raw number Godot uses, and the
     * companion holds the named values (`Time.Month.<NAME>`).
     *
     * Generated from Godot docs: Time.Month
     */
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

    /**
     * Godot's `Time.Weekday` enum as a typed value: `.value` is the raw number Godot uses, and the
     * companion holds the named values (`Time.Weekday.<NAME>`).
     *
     * Generated from Godot docs: Time.Weekday
     */
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

    private object Binds {
        @JvmField
        val singleton = ObjectCalls.getSingleton("Time")

        private const val GET_DATETIME_DICT_FROM_UNIX_TIME_HASH = 3485342025L
        @JvmField
        val getDateTimeDictFromUnixTimeBind =
            ObjectCalls.getMethodBind("Time", "get_datetime_dict_from_unix_time", GET_DATETIME_DICT_FROM_UNIX_TIME_HASH)

        private const val GET_DATE_DICT_FROM_UNIX_TIME_HASH = 3485342025L
        @JvmField
        val getDateDictFromUnixTimeBind =
            ObjectCalls.getMethodBind("Time", "get_date_dict_from_unix_time", GET_DATE_DICT_FROM_UNIX_TIME_HASH)

        private const val GET_TIME_DICT_FROM_UNIX_TIME_HASH = 3485342025L
        @JvmField
        val getTimeDictFromUnixTimeBind =
            ObjectCalls.getMethodBind("Time", "get_time_dict_from_unix_time", GET_TIME_DICT_FROM_UNIX_TIME_HASH)

        private const val GET_DATETIME_STRING_FROM_UNIX_TIME_HASH = 2311239925L
        @JvmField
        val getDateTimeStringFromUnixTimeBind =
            ObjectCalls.getMethodBind("Time", "get_datetime_string_from_unix_time", GET_DATETIME_STRING_FROM_UNIX_TIME_HASH)

        private const val GET_DATE_STRING_FROM_UNIX_TIME_HASH = 844755477L
        @JvmField
        val getDateStringFromUnixTimeBind =
            ObjectCalls.getMethodBind("Time", "get_date_string_from_unix_time", GET_DATE_STRING_FROM_UNIX_TIME_HASH)

        private const val GET_TIME_STRING_FROM_UNIX_TIME_HASH = 844755477L
        @JvmField
        val getTimeStringFromUnixTimeBind =
            ObjectCalls.getMethodBind("Time", "get_time_string_from_unix_time", GET_TIME_STRING_FROM_UNIX_TIME_HASH)

        private const val GET_DATETIME_DICT_FROM_DATETIME_STRING_HASH = 3253569256L
        @JvmField
        val getDateTimeDictFromDateTimeStringBind =
            ObjectCalls.getMethodBind("Time", "get_datetime_dict_from_datetime_string", GET_DATETIME_DICT_FROM_DATETIME_STRING_HASH)

        private const val GET_DATETIME_STRING_FROM_DATETIME_DICT_HASH = 1898123706L
        @JvmField
        val getDateTimeStringFromDateTimeDictBind =
            ObjectCalls.getMethodBind("Time", "get_datetime_string_from_datetime_dict", GET_DATETIME_STRING_FROM_DATETIME_DICT_HASH)

        private const val GET_UNIX_TIME_FROM_DATETIME_DICT_HASH = 3021115443L
        @JvmField
        val getUnixTimeFromDateTimeDictBind =
            ObjectCalls.getMethodBind("Time", "get_unix_time_from_datetime_dict", GET_UNIX_TIME_FROM_DATETIME_DICT_HASH)

        private const val GET_UNIX_TIME_FROM_DATETIME_STRING_HASH = 1321353865L
        @JvmField
        val getUnixTimeFromDateTimeStringBind =
            ObjectCalls.getMethodBind("Time", "get_unix_time_from_datetime_string", GET_UNIX_TIME_FROM_DATETIME_STRING_HASH)

        private const val GET_OFFSET_STRING_FROM_OFFSET_MINUTES_HASH = 844755477L
        @JvmField
        val getOffsetStringFromOffsetMinutesBind =
            ObjectCalls.getMethodBind("Time", "get_offset_string_from_offset_minutes", GET_OFFSET_STRING_FROM_OFFSET_MINUTES_HASH)

        private const val GET_DATETIME_DICT_FROM_SYSTEM_HASH = 205769976L
        @JvmField
        val getDateTimeDictFromSystemBind =
            ObjectCalls.getMethodBind("Time", "get_datetime_dict_from_system", GET_DATETIME_DICT_FROM_SYSTEM_HASH)

        private const val GET_DATE_DICT_FROM_SYSTEM_HASH = 205769976L
        @JvmField
        val getDateDictFromSystemBind =
            ObjectCalls.getMethodBind("Time", "get_date_dict_from_system", GET_DATE_DICT_FROM_SYSTEM_HASH)

        private const val GET_TIME_DICT_FROM_SYSTEM_HASH = 205769976L
        @JvmField
        val getTimeDictFromSystemBind =
            ObjectCalls.getMethodBind("Time", "get_time_dict_from_system", GET_TIME_DICT_FROM_SYSTEM_HASH)

        private const val GET_DATETIME_STRING_FROM_SYSTEM_HASH = 1136425492L
        @JvmField
        val getDateTimeStringFromSystemBind =
            ObjectCalls.getMethodBind("Time", "get_datetime_string_from_system", GET_DATETIME_STRING_FROM_SYSTEM_HASH)

        private const val GET_DATE_STRING_FROM_SYSTEM_HASH = 1162154673L
        @JvmField
        val getDateStringFromSystemBind =
            ObjectCalls.getMethodBind("Time", "get_date_string_from_system", GET_DATE_STRING_FROM_SYSTEM_HASH)

        private const val GET_TIME_STRING_FROM_SYSTEM_HASH = 1162154673L
        @JvmField
        val getTimeStringFromSystemBind =
            ObjectCalls.getMethodBind("Time", "get_time_string_from_system", GET_TIME_STRING_FROM_SYSTEM_HASH)

        private const val GET_TIME_ZONE_FROM_SYSTEM_HASH = 3102165223L
        @JvmField
        val getTimeZoneFromSystemBind =
            ObjectCalls.getMethodBind("Time", "get_time_zone_from_system", GET_TIME_ZONE_FROM_SYSTEM_HASH)

        private const val GET_UNIX_TIME_FROM_SYSTEM_HASH = 1740695150L
        @JvmField
        val getUnixTimeFromSystemBind =
            ObjectCalls.getMethodBind("Time", "get_unix_time_from_system", GET_UNIX_TIME_FROM_SYSTEM_HASH)

        private const val GET_TICKS_MSEC_HASH = 3905245786L
        @JvmField
        val getTicksMsecBind =
            ObjectCalls.getMethodBind("Time", "get_ticks_msec", GET_TICKS_MSEC_HASH)

        private const val GET_TICKS_USEC_HASH = 3905245786L
        @JvmField
        val getTicksUsecBind =
            ObjectCalls.getMethodBind("Time", "get_ticks_usec", GET_TICKS_USEC_HASH)
    }
}
