package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for OpenXRFutureResult (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP OpenXRFutureResult waits on: ptrcallWithVariantArg
// Index: docs/reference/generated/ios-shape-gap.md

fun OpenXRFutureResult.setResultValue(resultValue: Any?) {
    checkOpen()
    ObjectCalls.ptrcallWithVariantArg(setResultValueBind, handle, resultValue)
}

private const val SET_RESULT_VALUE_HASH = 1114965689L
private val setResultValueBind by lazy {
    ObjectCalls.getMethodBind("OpenXRFutureResult", "set_result_value", SET_RESULT_VALUE_HASH)
}
