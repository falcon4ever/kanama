package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector3

// GENERATED desktop/Android companion for CPUParticles3D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP CPUParticles3D waits on: ptrcallWithPackedColorListArg,
//   ptrcallWithPackedVector3ListArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Sets the initial positions to spawn particles when using `EMISSION_SHAPE_POINTS` or
 * `EMISSION_SHAPE_DIRECTED_POINTS`.
 *
 * Generated from Godot docs: CPUParticles3D.set_emission_points
 */
fun CPUParticles3D.setEmissionPoints(array: List<Vector3>) {
    ObjectCalls.ptrcallWithPackedVector3ListArg(setEmissionPointsBind, handle, array)
}

/**
 * Sets the direction the particles will be emitted in when using `EMISSION_SHAPE_DIRECTED_POINTS`.
 *
 * Generated from Godot docs: CPUParticles3D.set_emission_normals
 */
fun CPUParticles3D.setEmissionNormals(array: List<Vector3>) {
    ObjectCalls.ptrcallWithPackedVector3ListArg(setEmissionNormalsBind, handle, array)
}

/**
 * Sets the `Color`s to modulate particles by when using `EMISSION_SHAPE_POINTS` or
 * `EMISSION_SHAPE_DIRECTED_POINTS`. Note: `emission_colors` multiplies the particle mesh's vertex
 * colors. To have a visible effect on a `BaseMaterial3D`,
 * `BaseMaterial3D.vertex_color_use_as_albedo` must be `true`. For a `ShaderMaterial`, `ALBEDO *=
 * COLOR.rgb;` must be inserted in the shader's `fragment()` function. Otherwise, `emission_colors`
 * will have no visible effect.
 *
 * Generated from Godot docs: CPUParticles3D.set_emission_colors
 */
fun CPUParticles3D.setEmissionColors(array: List<Color>) {
    ObjectCalls.ptrcallWithPackedColorListArg(setEmissionColorsBind, handle, array)
}

private const val SET_EMISSION_POINTS_HASH = 334873810L
private val setEmissionPointsBind by lazy {
    ObjectCalls.getMethodBind("CPUParticles3D", "set_emission_points", SET_EMISSION_POINTS_HASH)
}

private const val SET_EMISSION_NORMALS_HASH = 334873810L
private val setEmissionNormalsBind by lazy {
    ObjectCalls.getMethodBind("CPUParticles3D", "set_emission_normals", SET_EMISSION_NORMALS_HASH)
}

private const val SET_EMISSION_COLORS_HASH = 3546319833L
private val setEmissionColorsBind by lazy {
    ObjectCalls.getMethodBind("CPUParticles3D", "set_emission_colors", SET_EMISSION_COLORS_HASH)
}
