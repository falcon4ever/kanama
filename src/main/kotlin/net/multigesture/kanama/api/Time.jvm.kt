package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for Time (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP Time waits on: ptrcallNoArgsRetDictionary, ptrcallWithBoolArgRetDictionary,
//   ptrcallWithDictionaryAndBoolArgRetString, ptrcallWithDictionaryArgRetLong,
//   ptrcallWithLongArgRetDictionary, ptrcallWithStringAndBoolArgRetDictionary
// Index: docs/reference/generated/ios-shape-gap.md

fun Time.getDateTimeDictFromUnixTime(unixTime: Long): Map<String, Any?> {
    return ObjectCalls.ptrcallWithLongArgRetDictionary(getDateTimeDictFromUnixTimeBind, timeSingleton, unixTime)
}

/**
 * Converts the given Unix timestamp to a dictionary of keys: `year`, `month`, `day`, and
 * `weekday`.
 *
 * Generated from Godot docs: Time.get_date_dict_from_unix_time
 */
fun Time.getDateDictFromUnixTime(unixTime: Long): Map<String, Any?> {
    return ObjectCalls.ptrcallWithLongArgRetDictionary(getDateDictFromUnixTimeBind, timeSingleton, unixTime)
}

/**
 * Converts the given time to a dictionary of keys: `hour`, `minute`, and `second`.
 *
 * Generated from Godot docs: Time.get_time_dict_from_unix_time
 */
fun Time.getTimeDictFromUnixTime(unixTime: Long): Map<String, Any?> {
    return ObjectCalls.ptrcallWithLongArgRetDictionary(getTimeDictFromUnixTimeBind, timeSingleton, unixTime)
}

fun Time.getDateTimeDictFromDateTimeString(value: String, weekday: Boolean): Map<String, Any?> {
    return ObjectCalls.ptrcallWithStringAndBoolArgRetDictionary(getDateTimeDictFromDateTimeStringBind, timeSingleton, value, weekday)
}

fun Time.getDateTimeStringFromDateTimeDict(values: Map<String, Any?>, useSpace: Boolean = false): String {
    return ObjectCalls.ptrcallWithDictionaryAndBoolArgRetString(getDateTimeStringFromDateTimeDictBind, timeSingleton, values, useSpace)
}

fun Time.getUnixTimeFromDateTimeDict(values: Map<String, Any?>): Long {
    return ObjectCalls.ptrcallWithDictionaryArgRetLong(getUnixTimeFromDateTimeDictBind, timeSingleton, values)
}

fun Time.getDateTimeDictFromSystem(utc: Boolean = false): Map<String, Any?> {
    return ObjectCalls.ptrcallWithBoolArgRetDictionary(getDateTimeDictFromSystemBind, timeSingleton, utc)
}

/**
 * Returns the current date as a dictionary of keys: `year`, `month`, `day`, and `weekday`. The
 * returned values are in the system's local time when `utc` is `false`, otherwise they are in UTC.
 *
 * Generated from Godot docs: Time.get_date_dict_from_system
 */
fun Time.getDateDictFromSystem(utc: Boolean = false): Map<String, Any?> {
    return ObjectCalls.ptrcallWithBoolArgRetDictionary(getDateDictFromSystemBind, timeSingleton, utc)
}

/**
 * Returns the current time as a dictionary of keys: `hour`, `minute`, and `second`. The returned
 * values are in the system's local time when `utc` is `false`, otherwise they are in UTC.
 *
 * Generated from Godot docs: Time.get_time_dict_from_system
 */
fun Time.getTimeDictFromSystem(utc: Boolean = false): Map<String, Any?> {
    return ObjectCalls.ptrcallWithBoolArgRetDictionary(getTimeDictFromSystemBind, timeSingleton, utc)
}

/**
 * Returns the current time zone as a dictionary of keys: `bias` and `name`. - `bias` is the offset
 * from UTC in minutes, since not all time zones are multiples of an hour from UTC. - `name` is the
 * localized name of the time zone, according to the OS locale settings of the current user.
 *
 * Generated from Godot docs: Time.get_time_zone_from_system
 */
fun Time.getTimeZoneFromSystem(): Map<String, Any?> {
    return ObjectCalls.ptrcallNoArgsRetDictionary(getTimeZoneFromSystemBind, timeSingleton)
}

private val timeSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("Time")
}

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

private const val GET_TIME_ZONE_FROM_SYSTEM_HASH = 3102165223L
private val getTimeZoneFromSystemBind by lazy {
    ObjectCalls.getMethodBind("Time", "get_time_zone_from_system", GET_TIME_ZONE_FROM_SYSTEM_HASH)
}
