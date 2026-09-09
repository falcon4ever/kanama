package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector2

// GENERATED desktop/Android companion for CPUParticles2D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP CPUParticles2D waits on: ptrcallWithPackedColorListArg,
//   ptrcallWithPackedVector2ListArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Sets the initial positions to spawn particles when using `EMISSION_SHAPE_POINTS` or
 * `EMISSION_SHAPE_DIRECTED_POINTS`.
 *
 * Generated from Godot docs: CPUParticles2D.set_emission_points
 */
fun CPUParticles2D.setEmissionPoints(array: List<Vector2>) {
    ObjectCalls.ptrcallWithPackedVector2ListArg(setEmissionPointsBind, handle, array)
}

/**
 * Sets the direction the particles will be emitted in when using `EMISSION_SHAPE_DIRECTED_POINTS`.
 *
 * Generated from Godot docs: CPUParticles2D.set_emission_normals
 */
fun CPUParticles2D.setEmissionNormals(array: List<Vector2>) {
    ObjectCalls.ptrcallWithPackedVector2ListArg(setEmissionNormalsBind, handle, array)
}

/**
 * Sets the `Color`s to modulate particles by when using `EMISSION_SHAPE_POINTS` or
 * `EMISSION_SHAPE_DIRECTED_POINTS`.
 *
 * Generated from Godot docs: CPUParticles2D.set_emission_colors
 */
fun CPUParticles2D.setEmissionColors(array: List<Color>) {
    ObjectCalls.ptrcallWithPackedColorListArg(setEmissionColorsBind, handle, array)
}

private const val SET_EMISSION_POINTS_HASH = 1509147220L
private val setEmissionPointsBind by lazy {
    ObjectCalls.getMethodBind("CPUParticles2D", "set_emission_points", SET_EMISSION_POINTS_HASH)
}

private const val SET_EMISSION_NORMALS_HASH = 1509147220L
private val setEmissionNormalsBind by lazy {
    ObjectCalls.getMethodBind("CPUParticles2D", "set_emission_normals", SET_EMISSION_NORMALS_HASH)
}

private const val SET_EMISSION_COLORS_HASH = 3546319833L
private val setEmissionColorsBind by lazy {
    ObjectCalls.getMethodBind("CPUParticles2D", "set_emission_colors", SET_EMISSION_COLORS_HASH)
}
