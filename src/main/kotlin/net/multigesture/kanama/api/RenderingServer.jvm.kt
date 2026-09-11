package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector2i

// GENERATED desktop/Android companion for RenderingServer (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP RenderingServer waits on: ptrcallWithRIDArgRetTypedObjectList,
//   ptrcallWithRIDRIDListVector2iArgsRetTypedObjectList
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns 3D texture data as an array of `Image`s for the specified texture `RID`.
 *
 * Generated from Godot docs: RenderingServer.texture_3d_get
 */
fun RenderingServer.texture3dGet(texture: RID): List<Image> {
    return ObjectCalls.ptrcallWithRIDArgRetTypedObjectList(texture3dGetBind, renderingServerSingleton, texture, Image::fromHandle)
}

/**
 * Bakes the material data of the Mesh passed in the `base` parameter with optional
 * `material_overrides` to a set of `Image`s of size `image_size`. Returns an array of `Image`s
 * containing material properties as specified in `BakeChannels`.
 *
 * Generated from Godot docs: RenderingServer.bake_render_uv2
 */
fun RenderingServer.bakeRenderUv2(base: RID, materialOverrides: List<RID>, imageSize: Vector2i): List<Image> {
    return ObjectCalls.ptrcallWithRIDRIDListVector2iArgsRetTypedObjectList(bakeRenderUv2Bind, renderingServerSingleton, base, materialOverrides, imageSize, Image::fromHandle)
}

private val renderingServerSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("RenderingServer")
}

private const val TEXTURE_3D_GET_HASH = 2684255073L
private val texture3dGetBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "texture_3d_get", TEXTURE_3D_GET_HASH)
}

private const val BAKE_RENDER_UV2_HASH = 1904608558L
private val bakeRenderUv2Bind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "bake_render_uv2", BAKE_RENDER_UV2_HASH)
}
