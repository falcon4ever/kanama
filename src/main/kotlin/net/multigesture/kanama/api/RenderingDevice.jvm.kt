package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

// GENERATED desktop/Android companion for RenderingDevice (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP RenderingDevice waits on: ptrcallWithTwoObjectByteArrayListArgsRetRID
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Creates a new texture. It can be accessed with the RID that is returned. Once finished with your
 * RID, you will want to free the RID using the RenderingDevice's `free_rid` method. Note: `data`
 * takes an `Array` of `PackedByteArray`s. For `TEXTURE_TYPE_1D`, `TEXTURE_TYPE_2D`, and
 * `TEXTURE_TYPE_3D` types, this array should only have one element, a `PackedByteArray` containing
 * all the data for the texture. For `_ARRAY` and `_CUBE` types, the length should be the same as
 * the number of `RDTextureFormat.array_layers` in `format`. Note: Not to be confused with
 * `RenderingServer.texture_2d_create`, which creates the Godot-specific `Texture2D` resource as
 * opposed to the graphics API's own texture type.
 *
 * Generated from Godot docs: RenderingDevice.texture_create
 */
fun RenderingDevice.textureCreate(format: RDTextureFormat?, view: RDTextureView?, data: List<ByteArray>): RID {
    return ObjectCalls.ptrcallWithTwoObjectByteArrayListArgsRetRID(textureCreateBind, handle, format?.requireOpenHandle() ?: MemorySegment.NULL, view?.requireOpenHandle() ?: MemorySegment.NULL, data)
}

private const val TEXTURE_CREATE_HASH = 3709173589L
private val textureCreateBind by lazy {
    ObjectCalls.getMethodBind("RenderingDevice", "texture_create", TEXTURE_CREATE_HASH)
}
