package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i

// GENERATED desktop/Android companion for TileSetAtlasSource (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP TileSetAtlasSource waits on: ptrcallWithObjectAndThreeVector2iArgsRetPackedVector2List
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns an array of tiles coordinates ID that will be automatically removed when modifying one
 * or several of those properties: `texture`, `margins`, `separation` or `texture_region_size`.
 * This can be used to undo changes that would have caused tiles data loss.
 *
 * Generated from Godot docs: TileSetAtlasSource.get_tiles_to_be_removed_on_change
 */
fun TileSetAtlasSource.getTilesToBeRemovedOnChange(texture: Texture2D?, margins: Vector2i, separation: Vector2i, textureRegionSize: Vector2i): List<Vector2> {
    checkOpen()
    return ObjectCalls.ptrcallWithObjectAndThreeVector2iArgsRetPackedVector2List(getTilesToBeRemovedOnChangeBind, handle, texture?.requireOpenHandle() ?: MemorySegment.NULL, margins, separation, textureRegionSize)
}

private const val GET_TILES_TO_BE_REMOVED_ON_CHANGE_HASH = 1240378054L
private val getTilesToBeRemovedOnChangeBind by lazy {
    ObjectCalls.getMethodBind("TileSetAtlasSource", "get_tiles_to_be_removed_on_change", GET_TILES_TO_BE_REMOVED_ON_CHANGE_HASH)
}
