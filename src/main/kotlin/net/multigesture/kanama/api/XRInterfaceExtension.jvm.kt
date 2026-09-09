package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Rect2i
import net.multigesture.kanama.types.Vector2

// GENERATED desktop/Android companion for XRInterfaceExtension (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP XRInterfaceExtension waits on: ptrcallWithRIDRect2Rect2iBoolUInt32BoolVector2FourDoubleArgs
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Blits our render results to screen optionally applying lens distortion. This can only be called
 * while processing `_commit_views`.
 *
 * Generated from Godot docs: XRInterfaceExtension.add_blit
 */
fun XRInterfaceExtension.addBlit(renderTarget: RID, srcRect: Rect2, dstRect: Rect2i, useLayer: Boolean, layer: Long, applyLensDistortion: Boolean, eyeCenter: Vector2, k1: Double, k2: Double, upscale: Double, aspectRatio: Double) {
    checkOpen()
    ObjectCalls.ptrcallWithRIDRect2Rect2iBoolUInt32BoolVector2FourDoubleArgs(addBlitBind, handle, renderTarget, srcRect, dstRect, useLayer, layer, applyLensDistortion, eyeCenter, k1, k2, upscale, aspectRatio)
}

private const val ADD_BLIT_HASH = 258596971L
private val addBlitBind by lazy {
    ObjectCalls.getMethodBind("XRInterfaceExtension", "add_blit", ADD_BLIT_HASH)
}
