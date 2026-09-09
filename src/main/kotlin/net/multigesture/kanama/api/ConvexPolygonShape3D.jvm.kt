package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector3

// GENERATED desktop/Android companion for ConvexPolygonShape3D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP ConvexPolygonShape3D waits on: ptrcallNoArgsRetPackedVector3List,
//   ptrcallWithPackedVector3ListArg
// Index: docs/contributing/ios-shape-gap.md

/**
 * The list of 3D points forming the convex polygon shape.
 *
 * Generated from Godot docs: ConvexPolygonShape3D.set_points
 */
fun ConvexPolygonShape3D.setPoints(points: List<Vector3>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedVector3ListArg(setPointsBind, handle, points)
}

/**
 * The list of 3D points forming the convex polygon shape.
 *
 * Generated from Godot docs: ConvexPolygonShape3D.get_points
 */
fun ConvexPolygonShape3D.getPoints(): List<Vector3> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetPackedVector3List(getPointsBind, handle)
}

var ConvexPolygonShape3D.points: List<Vector3>
    @JvmName("pointsProperty")
    get() = getPoints()
    @JvmName("setPointsProperty")
    set(value) = setPoints(value)

private const val SET_POINTS_HASH = 334873810L
private val setPointsBind by lazy {
    ObjectCalls.getMethodBind("ConvexPolygonShape3D", "set_points", SET_POINTS_HASH)
}

private const val GET_POINTS_HASH = 497664490L
private val getPointsBind by lazy {
    ObjectCalls.getMethodBind("ConvexPolygonShape3D", "get_points", GET_POINTS_HASH)
}
