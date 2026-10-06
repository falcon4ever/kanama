package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.RID

/**
 * Node that can be the parent of `PhysicalBone3D` and can apply the simulation results to
 * `Skeleton3D`.
 *
 * Generated from Godot docs: PhysicalBoneSimulator3D
 */
class PhysicalBoneSimulator3D(handle: GodotHandle) : SkeletonModifier3D(handle) {
    /**
     * Returns a boolean that indicates whether the `PhysicalBoneSimulator3D` is running and
     * simulating.
     *
     * Generated from Godot docs: PhysicalBoneSimulator3D.is_simulating_physics
     */
    fun isSimulatingPhysics(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isSimulatingPhysicsBind, segment)
    }

    /**
     * Tells the `PhysicalBone3D` nodes in the Skeleton to stop simulating.
     *
     * Generated from Godot docs: PhysicalBoneSimulator3D.physical_bones_stop_simulation
     */
    fun physicalBonesStopSimulation() {
        ObjectCalls.ptrcallNoArgs(Binds.physicalBonesStopSimulationBind, segment)
    }

    /**
     * Tells the `PhysicalBone3D` nodes in the Skeleton to start simulating and reacting to the physics
     * world. Optionally, a list of bone names can be passed-in, allowing only the passed-in bones to
     * be simulated.
     *
     * Generated from Godot docs: PhysicalBoneSimulator3D.physical_bones_start_simulation
     */
    fun physicalBonesStartSimulation(bones: List<String>) {
        ObjectCalls.ptrcallWithStringNameListArg(Binds.physicalBonesStartSimulationBind, segment, bones)
    }

    /**
     * Adds a collision exception to the physical bone. Works just like the `RigidBody3D` node.
     *
     * Generated from Godot docs: PhysicalBoneSimulator3D.physical_bones_add_collision_exception
     */
    fun physicalBonesAddCollisionException(exception: RID) {
        ObjectCalls.ptrcallWithRIDArg(Binds.physicalBonesAddCollisionExceptionBind, segment, exception)
    }

    /**
     * Removes a collision exception to the physical bone. Works just like the `RigidBody3D` node.
     *
     * Generated from Godot docs: PhysicalBoneSimulator3D.physical_bones_remove_collision_exception
     */
    fun physicalBonesRemoveCollisionException(exception: RID) {
        ObjectCalls.ptrcallWithRIDArg(Binds.physicalBonesRemoveCollisionExceptionBind, segment, exception)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PhysicalBoneSimulator3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): PhysicalBoneSimulator3D? =
            if (handle.address() == 0L) null else PhysicalBoneSimulator3D(GodotHandle(handle))
    }

    private object Binds {
        private const val IS_SIMULATING_PHYSICS_HASH = 36873697L
        @JvmField
        val isSimulatingPhysicsBind =
            ObjectCalls.getMethodBind("PhysicalBoneSimulator3D", "is_simulating_physics", IS_SIMULATING_PHYSICS_HASH)

        private const val PHYSICAL_BONES_STOP_SIMULATION_HASH = 3218959716L
        @JvmField
        val physicalBonesStopSimulationBind =
            ObjectCalls.getMethodBind("PhysicalBoneSimulator3D", "physical_bones_stop_simulation", PHYSICAL_BONES_STOP_SIMULATION_HASH)

        private const val PHYSICAL_BONES_START_SIMULATION_HASH = 2787316981L
        @JvmField
        val physicalBonesStartSimulationBind =
            ObjectCalls.getMethodBind("PhysicalBoneSimulator3D", "physical_bones_start_simulation", PHYSICAL_BONES_START_SIMULATION_HASH)

        private const val PHYSICAL_BONES_ADD_COLLISION_EXCEPTION_HASH = 2722037293L
        @JvmField
        val physicalBonesAddCollisionExceptionBind =
            ObjectCalls.getMethodBind("PhysicalBoneSimulator3D", "physical_bones_add_collision_exception", PHYSICAL_BONES_ADD_COLLISION_EXCEPTION_HASH)

        private const val PHYSICAL_BONES_REMOVE_COLLISION_EXCEPTION_HASH = 2722037293L
        @JvmField
        val physicalBonesRemoveCollisionExceptionBind =
            ObjectCalls.getMethodBind("PhysicalBoneSimulator3D", "physical_bones_remove_collision_exception", PHYSICAL_BONES_REMOVE_COLLISION_EXCEPTION_HASH)
    }
}
