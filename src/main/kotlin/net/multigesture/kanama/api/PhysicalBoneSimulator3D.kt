package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

/**
 * Node that can be the parent of `PhysicalBone3D` and can apply the simulation results to
 * `Skeleton3D`.
 *
 * Generated from Godot docs: PhysicalBoneSimulator3D
 */
class PhysicalBoneSimulator3D(handle: MemorySegment) : SkeletonModifier3D(handle) {
    fun isSimulatingPhysics(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSimulatingPhysicsBind, handle)
    }

    fun physicalBonesStopSimulation() {
        ObjectCalls.ptrcallNoArgs(physicalBonesStopSimulationBind, handle)
    }

    fun physicalBonesStartSimulation(bones: List<String>) {
        ObjectCalls.ptrcallWithStringNameListArg(physicalBonesStartSimulationBind, handle, bones)
    }

    fun physicalBonesAddCollisionException(exception: RID) {
        ObjectCalls.ptrcallWithRIDArg(physicalBonesAddCollisionExceptionBind, handle, exception)
    }

    fun physicalBonesRemoveCollisionException(exception: RID) {
        ObjectCalls.ptrcallWithRIDArg(physicalBonesRemoveCollisionExceptionBind, handle, exception)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PhysicalBoneSimulator3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PhysicalBoneSimulator3D? =
            if (handle.address() == 0L) null else PhysicalBoneSimulator3D(handle)

        private const val IS_SIMULATING_PHYSICS_HASH = 36873697L
        private val isSimulatingPhysicsBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBoneSimulator3D", "is_simulating_physics", IS_SIMULATING_PHYSICS_HASH)
        }

        private const val PHYSICAL_BONES_STOP_SIMULATION_HASH = 3218959716L
        private val physicalBonesStopSimulationBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBoneSimulator3D", "physical_bones_stop_simulation", PHYSICAL_BONES_STOP_SIMULATION_HASH)
        }

        private const val PHYSICAL_BONES_START_SIMULATION_HASH = 2787316981L
        private val physicalBonesStartSimulationBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBoneSimulator3D", "physical_bones_start_simulation", PHYSICAL_BONES_START_SIMULATION_HASH)
        }

        private const val PHYSICAL_BONES_ADD_COLLISION_EXCEPTION_HASH = 2722037293L
        private val physicalBonesAddCollisionExceptionBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBoneSimulator3D", "physical_bones_add_collision_exception", PHYSICAL_BONES_ADD_COLLISION_EXCEPTION_HASH)
        }

        private const val PHYSICAL_BONES_REMOVE_COLLISION_EXCEPTION_HASH = 2722037293L
        private val physicalBonesRemoveCollisionExceptionBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBoneSimulator3D", "physical_bones_remove_collision_exception", PHYSICAL_BONES_REMOVE_COLLISION_EXCEPTION_HASH)
        }
    }
}
