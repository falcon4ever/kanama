package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Rect2i
import net.multigesture.kanama.types.Vector2

// GENERATED desktop/Android companion for BitMap (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP BitMap waits on: ptrcallWithIntAndRect2iArg, ptrcallWithRect2iAndBoolArg,
//   ptrcallWithRect2iAndDoubleArgsRetPackedVector2ListList
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Sets a rectangular portion of the bitmap to the specified value.
 *
 * Generated from Godot docs: BitMap.set_bit_rect
 */
fun BitMap.setBitRect(rect: Rect2i, bit: Boolean) {
    checkOpen()
    ObjectCalls.ptrcallWithRect2iAndBoolArg(setBitRectBind, handle, rect, bit)
}

/**
 * Applies morphological dilation or erosion to the bitmap. If `pixels` is positive, dilation is
 * applied to the bitmap. If `pixels` is negative, erosion is applied to the bitmap. `rect` defines
 * the area where the morphological operation is applied. Pixels located outside the `rect` are
 * unaffected by `grow_mask`.
 *
 * Generated from Godot docs: BitMap.grow_mask
 */
fun BitMap.growMask(pixels: Int, rect: Rect2i) {
    checkOpen()
    ObjectCalls.ptrcallWithIntAndRect2iArg(growMaskBind, handle, pixels, rect)
}

/**
 * Creates an `Array` of polygons covering a rectangular portion of the bitmap. It uses a marching
 * squares algorithm, followed by Ramer-Douglas-Peucker (RDP) reduction of the number of vertices.
 * Each polygon is described as a `PackedVector2Array` of its vertices. To get polygons covering
 * the whole bitmap, pass:
 *
 * Generated from Godot docs: BitMap.opaque_to_polygons
 */
fun BitMap.opaqueToPolygons(rect: Rect2i, epsilon: Double = 2.0): List<List<Vector2>> {
    checkOpen()
    return ObjectCalls.ptrcallWithRect2iAndDoubleArgsRetPackedVector2ListList(opaqueToPolygonsBind, handle, rect, epsilon)
}

private const val SET_BIT_RECT_HASH = 472162941L
private val setBitRectBind by lazy {
    ObjectCalls.getMethodBind("BitMap", "set_bit_rect", SET_BIT_RECT_HASH)
}

private const val GROW_MASK_HASH = 3317281434L
private val growMaskBind by lazy {
    ObjectCalls.getMethodBind("BitMap", "grow_mask", GROW_MASK_HASH)
}

private const val OPAQUE_TO_POLYGONS_HASH = 48478126L
private val opaqueToPolygonsBind by lazy {
    ObjectCalls.getMethodBind("BitMap", "opaque_to_polygons", OPAQUE_TO_POLYGONS_HASH)
}
