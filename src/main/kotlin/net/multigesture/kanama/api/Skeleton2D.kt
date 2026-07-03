package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Transform2D

/**
 * The parent of a hierarchy of `Bone2D`s, used to create a 2D skeletal animation.
 *
 * Generated from Godot docs: Skeleton2D
 */
class Skeleton2D(handle: MemorySegment) : Node2D(handle) {
    /**
     * Returns the number of `Bone2D` nodes in the node hierarchy parented by Skeleton2D.
     *
     * Generated from Godot docs: Skeleton2D.get_bone_count
     */
    fun getBoneCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getBoneCountBind, handle)
    }

    /**
     * Returns a `Bone2D` from the node hierarchy parented by Skeleton2D. The object to return is
     * identified by the parameter `idx`. Bones are indexed by descending the node hierarchy from top
     * to bottom, adding the children of each branch before moving to the next sibling.
     *
     * Generated from Godot docs: Skeleton2D.get_bone
     */
    fun getBone(idx: Int): Bone2D? {
        return Bone2D.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getBoneBind, handle, idx))
    }

    /**
     * Returns the `RID` of a Skeleton2D instance.
     *
     * Generated from Godot docs: Skeleton2D.get_skeleton
     */
    fun getSkeleton(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getSkeletonBind, handle)
    }

    /**
     * Sets the `SkeletonModificationStack2D` attached to this skeleton.
     *
     * Generated from Godot docs: Skeleton2D.set_modification_stack
     */
    fun setModificationStack(modificationStack: SkeletonModificationStack2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setModificationStackBind, handle, listOf(modificationStack?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Returns the `SkeletonModificationStack2D` attached to this skeleton, if one exists.
     *
     * Generated from Godot docs: Skeleton2D.get_modification_stack
     */
    fun getModificationStack(): SkeletonModificationStack2D? {
        return SkeletonModificationStack2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getModificationStackBind, handle))
    }

    /**
     * Executes all the modifications on the `SkeletonModificationStack2D`, if the Skeleton2D has one
     * assigned.
     *
     * Generated from Godot docs: Skeleton2D.execute_modifications
     */
    fun executeModifications(delta: Double, executionMode: Int) {
        ObjectCalls.ptrcallWithDoubleAndIntArgs(executeModificationsBind, handle, delta, executionMode)
    }

    /**
     * Sets the local pose transform, `override_pose`, for the bone at `bone_idx`. `strength` is the
     * interpolation strength that will be used when applying the pose, and `persistent` determines if
     * the applied pose will remain. Note: The pose transform needs to be a local transform relative to
     * the `Bone2D` node at `bone_idx`!
     *
     * Generated from Godot docs: Skeleton2D.set_bone_local_pose_override
     */
    fun setBoneLocalPoseOverride(boneIdx: Int, overridePose: Transform2D, strength: Double, persistent: Boolean) {
        ObjectCalls.ptrcallWithIntTransform2DDoubleBoolArgs(setBoneLocalPoseOverrideBind, handle, boneIdx, overridePose, strength, persistent)
    }

    /**
     * Returns the local pose override transform for `bone_idx`.
     *
     * Generated from Godot docs: Skeleton2D.get_bone_local_pose_override
     */
    fun getBoneLocalPoseOverride(boneIdx: Int): Transform2D {
        return ObjectCalls.ptrcallWithIntArgRetTransform2D(getBoneLocalPoseOverrideBind, handle, boneIdx)
    }

    object Signals {
        const val boneSetupChanged: String = "bone_setup_changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): Skeleton2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Skeleton2D? =
            if (handle.address() == 0L) null else Skeleton2D(handle)

        private const val GET_BONE_COUNT_HASH = 3905245786L
        private val getBoneCountBind by lazy {
            ObjectCalls.getMethodBind("Skeleton2D", "get_bone_count", GET_BONE_COUNT_HASH)
        }

        private const val GET_BONE_HASH = 2556267111L
        private val getBoneBind by lazy {
            ObjectCalls.getMethodBind("Skeleton2D", "get_bone", GET_BONE_HASH)
        }

        private const val GET_SKELETON_HASH = 2944877500L
        private val getSkeletonBind by lazy {
            ObjectCalls.getMethodBind("Skeleton2D", "get_skeleton", GET_SKELETON_HASH)
        }

        private const val SET_MODIFICATION_STACK_HASH = 3907307132L
        private val setModificationStackBind by lazy {
            ObjectCalls.getMethodBind("Skeleton2D", "set_modification_stack", SET_MODIFICATION_STACK_HASH)
        }

        private const val GET_MODIFICATION_STACK_HASH = 2107508396L
        private val getModificationStackBind by lazy {
            ObjectCalls.getMethodBind("Skeleton2D", "get_modification_stack", GET_MODIFICATION_STACK_HASH)
        }

        private const val EXECUTE_MODIFICATIONS_HASH = 1005356550L
        private val executeModificationsBind by lazy {
            ObjectCalls.getMethodBind("Skeleton2D", "execute_modifications", EXECUTE_MODIFICATIONS_HASH)
        }

        private const val SET_BONE_LOCAL_POSE_OVERRIDE_HASH = 555457532L
        private val setBoneLocalPoseOverrideBind by lazy {
            ObjectCalls.getMethodBind("Skeleton2D", "set_bone_local_pose_override", SET_BONE_LOCAL_POSE_OVERRIDE_HASH)
        }

        private const val GET_BONE_LOCAL_POSE_OVERRIDE_HASH = 2995540667L
        private val getBoneLocalPoseOverrideBind by lazy {
            ObjectCalls.getMethodBind("Skeleton2D", "get_bone_local_pose_override", GET_BONE_LOCAL_POSE_OVERRIDE_HASH)
        }
    }
}
