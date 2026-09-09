package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for RegEx (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP RegEx waits on: ptrcallWithStringTwoIntArgsRetTypedObjectList,
//   ptrcallWithTwoStringBoolTwoIntArgsRetString
// Index: docs/contributing/ios-shape-gap.md

fun RegEx.searchAll(subject: String, offset: Int = 0, end: Int = -1): List<RegExMatch> {
    checkOpen()
    return ObjectCalls.ptrcallWithStringTwoIntArgsRetTypedObjectList(searchAllBind, handle, subject, offset, end, RegExMatch::fromHandle)
}

fun RegEx.sub(subject: String, replacement: String, all: Boolean = false, offset: Int = 0, end: Int = -1): String {
    checkOpen()
    return ObjectCalls.ptrcallWithTwoStringBoolTwoIntArgsRetString(subBind, handle, subject, replacement, all, offset, end)
}

private const val SEARCH_ALL_HASH = 849021363L
private val searchAllBind by lazy {
    ObjectCalls.getMethodBind("RegEx", "search_all", SEARCH_ALL_HASH)
}

private const val SUB_HASH = 54019702L
private val subBind by lazy {
    ObjectCalls.getMethodBind("RegEx", "sub", SUB_HASH)
}
