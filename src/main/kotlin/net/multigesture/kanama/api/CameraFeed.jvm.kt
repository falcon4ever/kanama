package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for CameraFeed (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP CameraFeed waits on: ptrcallWithIntAndDictionaryArgRetBool
// Index: docs/contributing/ios-shape-gap.md

/**
 * Sets the feed format parameters for the given `index` in the `formats` array. Returns `true` on
 * success. By default, the YUYV encoded stream is transformed to `FEED_RGB`. The YUYV encoded
 * stream output format can be changed by setting `parameters`'s `output` entry to one of the
 * following: - `"separate"` will result in `FEED_YCBCR_SEP`; - `"grayscale"` will result in
 * desaturated `FEED_RGB`; - `"copy"` will result in `FEED_YCBCR`.
 *
 * Generated from Godot docs: CameraFeed.set_format
 */
fun CameraFeed.setFormat(index: Int, parameters: Map<String, Any?>): Boolean {
    checkOpen()
    return ObjectCalls.ptrcallWithIntAndDictionaryArgRetBool(setFormatBind, handle, index, parameters)
}

private const val SET_FORMAT_HASH = 31872775L
private val setFormatBind by lazy {
    ObjectCalls.getMethodBind("CameraFeed", "set_format", SET_FORMAT_HASH)
}
