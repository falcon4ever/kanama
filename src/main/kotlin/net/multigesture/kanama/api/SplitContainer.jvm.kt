package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for SplitContainer (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP SplitContainer waits on: ptrcallWithPackedInt32ListArg
// Index: docs/contributing/ios-shape-gap.md

/**
 * Offsets for each dragger in pixels. Each one is the offset of the split between the `Control`
 * nodes before and after the dragger, with `0` being the default position. The default position is
 * based on the `Control` nodes expand flags and minimum sizes. See
 * `Control.size_flags_horizontal`, `Control.size_flags_vertical`, and
 * `Control.size_flags_stretch_ratio`. If none of the `Control` nodes before the dragger are
 * expanded, the default position will be at the start of the `SplitContainer`. If none of the
 * `Control` nodes after the dragger are expanded, the default position will be at the end of the
 * `SplitContainer`. If the dragger is in between expanded `Control` nodes, the default position
 * will be in the middle, based on the `Control.size_flags_stretch_ratio`s and minimum sizes. Note:
 * If the split offsets cause `Control` nodes to overlap, the first split will take priority when
 * resolving the positions.
 *
 * Generated from Godot docs: SplitContainer.set_split_offsets
 */
fun SplitContainer.setSplitOffsets(offsets: List<Int>) {
    ObjectCalls.ptrcallWithPackedInt32ListArg(setSplitOffsetsBind, handle, offsets)
}

private const val SET_SPLIT_OFFSETS_HASH = 3614634198L
private val setSplitOffsetsBind by lazy {
    ObjectCalls.getMethodBind("SplitContainer", "set_split_offsets", SET_SPLIT_OFFSETS_HASH)
}
