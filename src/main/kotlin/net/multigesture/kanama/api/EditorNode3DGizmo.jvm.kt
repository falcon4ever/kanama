package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector3

// GENERATED desktop/Android companion for EditorNode3DGizmo (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP EditorNode3DGizmo waits on: ptrcallWithPackedVector3ListArg,
//   ptrcallWithPackedVector3ListObjectBoolColorArgs,
//   ptrcallWithPackedVector3ListObjectPackedInt32ListTwoBoolArgs
// Index: docs/contributing/ios-shape-gap.md

/**
 * Adds lines to the gizmo (as sets of 2 points), with a given material. The lines are used for
 * visualizing the gizmo. Call this method during `_redraw`.
 *
 * Generated from Godot docs: EditorNode3DGizmo.add_lines
 */
fun EditorNode3DGizmo.addLines(lines: List<Vector3>, material: Material?, billboard: Boolean = false, modulate: Color) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedVector3ListObjectBoolColorArgs(addLinesBind, handle, lines, material?.requireOpenHandle() ?: MemorySegment.NULL, billboard, modulate)
}

/**
 * Adds the specified `segments` to the gizmo's collision shape for picking. Call this method
 * during `_redraw`.
 *
 * Generated from Godot docs: EditorNode3DGizmo.add_collision_segments
 */
fun EditorNode3DGizmo.addCollisionSegments(segments: List<Vector3>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedVector3ListArg(addCollisionSegmentsBind, handle, segments)
}

/**
 * Adds a list of handles (points) which can be used to edit the properties of the gizmo's
 * `Node3D`. The `ids` argument can be used to specify a custom identifier for each handle, if an
 * empty array is passed, the ids will be assigned automatically from the `handles` argument order.
 * The `secondary` argument marks the added handles as secondary, meaning they will normally have
 * lower selection priority than regular handles. When the user is holding the shift key secondary
 * handles will switch to have higher priority than regular handles. This change in priority can be
 * used to place multiple handles at the same point while still giving the user control on their
 * selection. There are virtual methods which will be called upon editing of these handles. Call
 * this method during `_redraw`.
 *
 * Generated from Godot docs: EditorNode3DGizmo.add_handles
 */
fun EditorNode3DGizmo.addHandles(handles: List<Vector3>, material: Material?, ids: List<Int>, billboard: Boolean = false, secondary: Boolean = false) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedVector3ListObjectPackedInt32ListTwoBoolArgs(addHandlesBind, handle, handles, material?.requireOpenHandle() ?: MemorySegment.NULL, ids, billboard, secondary)
}

private const val ADD_LINES_HASH = 2910971437L
private val addLinesBind by lazy {
    ObjectCalls.getMethodBind("EditorNode3DGizmo", "add_lines", ADD_LINES_HASH)
}

private const val ADD_COLLISION_SEGMENTS_HASH = 334873810L
private val addCollisionSegmentsBind by lazy {
    ObjectCalls.getMethodBind("EditorNode3DGizmo", "add_collision_segments", ADD_COLLISION_SEGMENTS_HASH)
}

private const val ADD_HANDLES_HASH = 2254560097L
private val addHandlesBind by lazy {
    ObjectCalls.getMethodBind("EditorNode3DGizmo", "add_handles", ADD_HANDLES_HASH)
}
