package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath

/**
 * A modification that applies the transforms of `PhysicalBone2D` nodes to `Bone2D` nodes.
 *
 * Generated from Godot docs: SkeletonModification2DPhysicalBones
 */
class SkeletonModification2DPhysicalBones(handle: MemorySegment) : SkeletonModification2D(handle) {
    var physicalBoneChainLength: Int
        @JvmName("physicalBoneChainLengthProperty")
        get() = getPhysicalBoneChainLength()
        @JvmName("setPhysicalBoneChainLengthProperty")
        set(value) = setPhysicalBoneChainLength(value)

    fun setPhysicalBoneChainLength(length: Int) {
        ObjectCalls.ptrcallWithIntArg(setPhysicalBoneChainLengthBind, handle, length)
    }

    fun getPhysicalBoneChainLength(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getPhysicalBoneChainLengthBind, handle)
    }

    fun setPhysicalBoneNode(jointIdx: Int, physicalbone2dNode: NodePath) {
        ObjectCalls.ptrcallWithIntAndNodePathArg(setPhysicalBoneNodeBind, handle, jointIdx, physicalbone2dNode)
    }

    fun getPhysicalBoneNode(jointIdx: Int): NodePath {
        return ObjectCalls.ptrcallWithIntArgRetNodePath(getPhysicalBoneNodeBind, handle, jointIdx)
    }

    fun fetchPhysicalBones() {
        ObjectCalls.ptrcallNoArgs(fetchPhysicalBonesBind, handle)
    }

    fun startSimulation(bones: List<String>) {
        ObjectCalls.ptrcallWithStringNameListArg(startSimulationBind, handle, bones)
    }

    fun stopSimulation(bones: List<String>) {
        ObjectCalls.ptrcallWithStringNameListArg(stopSimulationBind, handle, bones)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): SkeletonModification2DPhysicalBones? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SkeletonModification2DPhysicalBones? =
            if (handle.address() == 0L) null else SkeletonModification2DPhysicalBones(handle)

        private const val SET_PHYSICAL_BONE_CHAIN_LENGTH_HASH = 1286410249L
        private val setPhysicalBoneChainLengthBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DPhysicalBones", "set_physical_bone_chain_length", SET_PHYSICAL_BONE_CHAIN_LENGTH_HASH)
        }

        private const val GET_PHYSICAL_BONE_CHAIN_LENGTH_HASH = 2455072627L
        private val getPhysicalBoneChainLengthBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DPhysicalBones", "get_physical_bone_chain_length", GET_PHYSICAL_BONE_CHAIN_LENGTH_HASH)
        }

        private const val SET_PHYSICAL_BONE_NODE_HASH = 2761262315L
        private val setPhysicalBoneNodeBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DPhysicalBones", "set_physical_bone_node", SET_PHYSICAL_BONE_NODE_HASH)
        }

        private const val GET_PHYSICAL_BONE_NODE_HASH = 408788394L
        private val getPhysicalBoneNodeBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DPhysicalBones", "get_physical_bone_node", GET_PHYSICAL_BONE_NODE_HASH)
        }

        private const val FETCH_PHYSICAL_BONES_HASH = 3218959716L
        private val fetchPhysicalBonesBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DPhysicalBones", "fetch_physical_bones", FETCH_PHYSICAL_BONES_HASH)
        }

        private const val START_SIMULATION_HASH = 2787316981L
        private val startSimulationBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DPhysicalBones", "start_simulation", START_SIMULATION_HASH)
        }

        private const val STOP_SIMULATION_HASH = 2787316981L
        private val stopSimulationBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DPhysicalBones", "stop_simulation", STOP_SIMULATION_HASH)
        }
    }
}
