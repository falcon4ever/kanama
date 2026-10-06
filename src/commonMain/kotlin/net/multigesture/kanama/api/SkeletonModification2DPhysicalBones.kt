package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.NodePath

/**
 * A modification that applies the transforms of `PhysicalBone2D` nodes to `Bone2D` nodes.
 *
 * Generated from Godot docs: SkeletonModification2DPhysicalBones
 */
class SkeletonModification2DPhysicalBones(handle: GodotHandle) : SkeletonModification2D(handle) {
    var physicalBoneChainLength: Int
        @JvmName("physicalBoneChainLengthProperty")
        get() = getPhysicalBoneChainLength()
        @JvmName("setPhysicalBoneChainLengthProperty")
        set(value) = setPhysicalBoneChainLength(value)

    /**
     * The number of `PhysicalBone2D` nodes linked in this modification.
     *
     * Generated from Godot docs: SkeletonModification2DPhysicalBones.set_physical_bone_chain_length
     */
    fun setPhysicalBoneChainLength(length: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setPhysicalBoneChainLengthBind, segment, length)
    }

    /**
     * The number of `PhysicalBone2D` nodes linked in this modification.
     *
     * Generated from Godot docs: SkeletonModification2DPhysicalBones.get_physical_bone_chain_length
     */
    fun getPhysicalBoneChainLength(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getPhysicalBoneChainLengthBind, segment)
    }

    /**
     * Sets the `PhysicalBone2D` node at `joint_idx`. Note: This is just the index used for this
     * modification, not the bone index used in the `Skeleton2D`.
     *
     * Generated from Godot docs: SkeletonModification2DPhysicalBones.set_physical_bone_node
     */
    fun setPhysicalBoneNode(jointIdx: Int, physicalbone2dNode: NodePath) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndNodePathArg(Binds.setPhysicalBoneNodeBind, segment, jointIdx, physicalbone2dNode)
    }

    /**
     * Returns the `PhysicalBone2D` node at `joint_idx`.
     *
     * Generated from Godot docs: SkeletonModification2DPhysicalBones.get_physical_bone_node
     */
    fun getPhysicalBoneNode(jointIdx: Int): NodePath {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetNodePath(Binds.getPhysicalBoneNodeBind, segment, jointIdx)
    }

    /**
     * Empties the list of `PhysicalBone2D` nodes and populates it with all `PhysicalBone2D` nodes that
     * are children of the `Skeleton2D`.
     *
     * Generated from Godot docs: SkeletonModification2DPhysicalBones.fetch_physical_bones
     */
    fun fetchPhysicalBones() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.fetchPhysicalBonesBind, segment)
    }

    /**
     * Tell the `PhysicalBone2D` nodes to start simulating and interacting with the physics world.
     * Optionally, an array of bone names can be passed to this function, and that will cause only
     * `PhysicalBone2D` nodes with those names to start simulating.
     *
     * Generated from Godot docs: SkeletonModification2DPhysicalBones.start_simulation
     */
    fun startSimulation(bones: List<String>) {
        checkOpen()
        ObjectCalls.ptrcallWithStringNameListArg(Binds.startSimulationBind, segment, bones)
    }

    /**
     * Tell the `PhysicalBone2D` nodes to stop simulating and interacting with the physics world.
     * Optionally, an array of bone names can be passed to this function, and that will cause only
     * `PhysicalBone2D` nodes with those names to stop simulating.
     *
     * Generated from Godot docs: SkeletonModification2DPhysicalBones.stop_simulation
     */
    fun stopSimulation(bones: List<String>) {
        checkOpen()
        ObjectCalls.ptrcallWithStringNameListArg(Binds.stopSimulationBind, segment, bones)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): SkeletonModification2DPhysicalBones? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): SkeletonModification2DPhysicalBones? =
            if (handle.address() == 0L) null else RefCounted.owned(SkeletonModification2DPhysicalBones(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): SkeletonModification2DPhysicalBones? =
            if (handle.address() == 0L) null else SkeletonModification2DPhysicalBones(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_PHYSICAL_BONE_CHAIN_LENGTH_HASH = 1286410249L
        @JvmField
        val setPhysicalBoneChainLengthBind =
            ObjectCalls.getMethodBind("SkeletonModification2DPhysicalBones", "set_physical_bone_chain_length", SET_PHYSICAL_BONE_CHAIN_LENGTH_HASH)

        private const val GET_PHYSICAL_BONE_CHAIN_LENGTH_HASH = 2455072627L
        @JvmField
        val getPhysicalBoneChainLengthBind =
            ObjectCalls.getMethodBind("SkeletonModification2DPhysicalBones", "get_physical_bone_chain_length", GET_PHYSICAL_BONE_CHAIN_LENGTH_HASH)

        private const val SET_PHYSICAL_BONE_NODE_HASH = 2761262315L
        @JvmField
        val setPhysicalBoneNodeBind =
            ObjectCalls.getMethodBind("SkeletonModification2DPhysicalBones", "set_physical_bone_node", SET_PHYSICAL_BONE_NODE_HASH)

        private const val GET_PHYSICAL_BONE_NODE_HASH = 408788394L
        @JvmField
        val getPhysicalBoneNodeBind =
            ObjectCalls.getMethodBind("SkeletonModification2DPhysicalBones", "get_physical_bone_node", GET_PHYSICAL_BONE_NODE_HASH)

        private const val FETCH_PHYSICAL_BONES_HASH = 3218959716L
        @JvmField
        val fetchPhysicalBonesBind =
            ObjectCalls.getMethodBind("SkeletonModification2DPhysicalBones", "fetch_physical_bones", FETCH_PHYSICAL_BONES_HASH)

        private const val START_SIMULATION_HASH = 2787316981L
        @JvmField
        val startSimulationBind =
            ObjectCalls.getMethodBind("SkeletonModification2DPhysicalBones", "start_simulation", START_SIMULATION_HASH)

        private const val STOP_SIMULATION_HASH = 2787316981L
        @JvmField
        val stopSimulationBind =
            ObjectCalls.getMethodBind("SkeletonModification2DPhysicalBones", "stop_simulation", STOP_SIMULATION_HASH)
    }
}
