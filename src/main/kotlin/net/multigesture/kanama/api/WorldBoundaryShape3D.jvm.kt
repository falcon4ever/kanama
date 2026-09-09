package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Plane

// GENERATED desktop/Android companion for WorldBoundaryShape3D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP WorldBoundaryShape3D waits on: ptrcallNoArgsRetPlane, ptrcallWithPlaneArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * The `Plane` used by the `WorldBoundaryShape3D` for collision.
 *
 * Generated from Godot docs: WorldBoundaryShape3D.set_plane
 */
fun WorldBoundaryShape3D.setPlane(plane: Plane) {
    checkOpen()
    ObjectCalls.ptrcallWithPlaneArg(setPlaneBind, handle, plane)
}

/**
 * The `Plane` used by the `WorldBoundaryShape3D` for collision.
 *
 * Generated from Godot docs: WorldBoundaryShape3D.get_plane
 */
fun WorldBoundaryShape3D.getPlane(): Plane {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetPlane(getPlaneBind, handle)
}

var WorldBoundaryShape3D.plane: Plane
    @JvmName("planeProperty")
    get() = getPlane()
    @JvmName("setPlaneProperty")
    set(value) = setPlane(value)

private const val SET_PLANE_HASH = 3505987427L
private val setPlaneBind by lazy {
    ObjectCalls.getMethodBind("WorldBoundaryShape3D", "set_plane", SET_PLANE_HASH)
}

private const val GET_PLANE_HASH = 2753500971L
private val getPlaneBind by lazy {
    ObjectCalls.getMethodBind("WorldBoundaryShape3D", "get_plane", GET_PLANE_HASH)
}
