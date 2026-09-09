package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Rect2i

// GENERATED desktop/Android companion for DrawableTexture2D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP DrawableTexture2D waits on: ptrcallWithRect2iObjectColorIntObjectArgs,
//   ptrcallWithRect2iTwoObjectListColorIntObjectArgs
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Draws to given `rect` on this texture by copying from the given `source`. A `modulate` color can
 * be passed in for the shader to use, but defaults to White. The `mipmap` value can specify a draw
 * to a lower mipmap level. The `material` parameter can take a ShaderMaterial with a TextureBlit
 * Shader for custom drawing behavior.
 *
 * Generated from Godot docs: DrawableTexture2D.blit_rect
 */
fun DrawableTexture2D.blitRect(rect: Rect2i, source: Texture2D?, modulate: Color, mipmap: Int = 0, material: Material?) {
    checkOpen()
    ObjectCalls.ptrcallWithRect2iObjectColorIntObjectArgs(blitRectBind, handle, rect, source?.requireOpenHandle() ?: MemorySegment.NULL, modulate, mipmap, material?.requireOpenHandle() ?: MemorySegment.NULL)
}

/**
 * Draws to the given `rect` on this texture, as well as on up to 3 DrawableTexture
 * `extra_targets`. All `extra_targets` must be the same size and DrawableFormat as the original
 * target, otherwise the Shader may fail. Expects up to 4 Texture `sources`, but will replace
 * missing `sources` with default Black Textures.
 *
 * Generated from Godot docs: DrawableTexture2D.blit_rect_multi
 */
fun DrawableTexture2D.blitRectMulti(rect: Rect2i, sources: List<Texture2D>, extraTargets: List<DrawableTexture2D>, modulate: Color, mipmap: Int = 0, material: Material?) {
    checkOpen()
    ObjectCalls.ptrcallWithRect2iTwoObjectListColorIntObjectArgs(blitRectMultiBind, handle, rect, sources, extraTargets, modulate, mipmap, material?.requireOpenHandle() ?: MemorySegment.NULL)
}

private const val BLIT_RECT_HASH = 319217173L
private val blitRectBind by lazy {
    ObjectCalls.getMethodBind("DrawableTexture2D", "blit_rect", BLIT_RECT_HASH)
}

private const val BLIT_RECT_MULTI_HASH = 3074783066L
private val blitRectMultiBind by lazy {
    ObjectCalls.getMethodBind("DrawableTexture2D", "blit_rect_multi", BLIT_RECT_MULTI_HASH)
}
