package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for PhysicalBoneSimulator3D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP PhysicalBoneSimulator3D waits on: ptrcallWithStringNameListArg
// Index: docs/contributing/ios-shape-gap.md

/**
 * Tells the `PhysicalBone3D` nodes in the Skeleton to start simulating and reacting to the physics
 * world. Optionally, a list of bone names can be passed-in, allowing only the passed-in bones to
 * be simulated.
 *
 * Generated from Godot docs: PhysicalBoneSimulator3D.physical_bones_start_simulation
 */
fun PhysicalBoneSimulator3D.physicalBonesStartSimulation(bones: List<String>) {
    ObjectCalls.ptrcallWithStringNameListArg(physicalBonesStartSimulationBind, handle, bones)
}

private const val PHYSICAL_BONES_START_SIMULATION_HASH = 2787316981L
private val physicalBonesStartSimulationBind by lazy {
    ObjectCalls.getMethodBind("PhysicalBoneSimulator3D", "physical_bones_start_simulation", PHYSICAL_BONES_START_SIMULATION_HASH)
}
