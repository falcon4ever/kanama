package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for RegExMatch (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP RegExMatch waits on: ptrcallWithVariantArgRetInt, ptrcallWithVariantArgRetString
// Index: docs/reference/generated/ios-shape-gap.md

fun RegExMatch.getString(name: Any?): String {
    checkOpen()
    return ObjectCalls.ptrcallWithVariantArgRetString(getStringBind, handle, name)
}

fun RegExMatch.getStart(name: Any?): Int {
    checkOpen()
    return ObjectCalls.ptrcallWithVariantArgRetInt(getStartBind, handle, name)
}

fun RegExMatch.getEnd(name: Any?): Int {
    checkOpen()
    return ObjectCalls.ptrcallWithVariantArgRetInt(getEndBind, handle, name)
}

private const val GET_STRING_HASH = 687115856L
private val getStringBind by lazy {
    ObjectCalls.getMethodBind("RegExMatch", "get_string", GET_STRING_HASH)
}

private const val GET_START_HASH = 490464691L
private val getStartBind by lazy {
    ObjectCalls.getMethodBind("RegExMatch", "get_start", GET_START_HASH)
}

private const val GET_END_HASH = 490464691L
private val getEndBind by lazy {
    ObjectCalls.getMethodBind("RegExMatch", "get_end", GET_END_HASH)
}
