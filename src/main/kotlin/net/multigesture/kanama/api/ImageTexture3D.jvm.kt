package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for ImageTexture3D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP ImageTexture3D waits on: ptrcallWithLongThreeIntBoolObjectListArgsRetLong,
//   ptrcallWithObjectListArg
// Index: docs/contributing/ios-shape-gap.md

/**
 * Creates the `ImageTexture3D` with specified `format`, `width`, `height`, and `depth`. If
 * `use_mipmaps` is `true`, generates mipmaps for the `ImageTexture3D`.
 *
 * Generated from Godot docs: ImageTexture3D.create
 */
fun ImageTexture3D.create(format: Long, width: Int, height: Int, depth: Int, useMipmaps: Boolean, data: List<Image>): Long {
    checkOpen()
    return ObjectCalls.ptrcallWithLongThreeIntBoolObjectListArgsRetLong(createBind, handle, format, width, height, depth, useMipmaps, data)
}

/**
 * Replaces the texture's existing data with the layers specified in `data`. The size of `data`
 * must match the parameters that were used for `create`. In other words, the texture cannot be
 * resized or have its format changed by calling `update`.
 *
 * Generated from Godot docs: ImageTexture3D.update
 */
fun ImageTexture3D.update(data: List<Image>) {
    checkOpen()
    ObjectCalls.ptrcallWithObjectListArg(updateBind, handle, data)
}

private const val CREATE_HASH = 1130379827L
private val createBind by lazy {
    ObjectCalls.getMethodBind("ImageTexture3D", "create", CREATE_HASH)
}

private const val UPDATE_HASH = 381264803L
private val updateBind by lazy {
    ObjectCalls.getMethodBind("ImageTexture3D", "update", UPDATE_HASH)
}
