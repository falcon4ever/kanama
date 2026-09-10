package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for Time (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP Time waits on: ptrcallWithDictionaryAndBoolArgRetString,
//   ptrcallWithDictionaryArgRetLong
// Index: docs/reference/generated/ios-shape-gap.md

fun Time.getDateTimeStringFromDateTimeDict(values: Map<String, Any?>, useSpace: Boolean = false): String {
    return ObjectCalls.ptrcallWithDictionaryAndBoolArgRetString(getDateTimeStringFromDateTimeDictBind, timeSingleton, values, useSpace)
}

fun Time.getUnixTimeFromDateTimeDict(values: Map<String, Any?>): Long {
    return ObjectCalls.ptrcallWithDictionaryArgRetLong(getUnixTimeFromDateTimeDictBind, timeSingleton, values)
}

private val timeSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("Time")
}

private const val GET_DATETIME_STRING_FROM_DATETIME_DICT_HASH = 1898123706L
private val getDateTimeStringFromDateTimeDictBind by lazy {
    ObjectCalls.getMethodBind("Time", "get_datetime_string_from_datetime_dict", GET_DATETIME_STRING_FROM_DATETIME_DICT_HASH)
}

private const val GET_UNIX_TIME_FROM_DATETIME_DICT_HASH = 3021115443L
private val getUnixTimeFromDateTimeDictBind by lazy {
    ObjectCalls.getMethodBind("Time", "get_unix_time_from_datetime_dict", GET_UNIX_TIME_FROM_DATETIME_DICT_HASH)
}
