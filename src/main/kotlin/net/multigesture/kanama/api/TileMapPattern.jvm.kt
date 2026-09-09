package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2i

// GENERATED desktop/Android companion for TileMapPattern (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP TileMapPattern waits on: ptrcallNoArgsRetVector2iList
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns the list of used cell coordinates in the pattern.
 *
 * Generated from Godot docs: TileMapPattern.get_used_cells
 */
fun TileMapPattern.getUsedCells(): List<Vector2i> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetVector2iList(getUsedCellsBind, handle)
}

private const val GET_USED_CELLS_HASH = 3995934104L
private val getUsedCellsBind by lazy {
    ObjectCalls.getMethodBind("TileMapPattern", "get_used_cells", GET_USED_CELLS_HASH)
}
