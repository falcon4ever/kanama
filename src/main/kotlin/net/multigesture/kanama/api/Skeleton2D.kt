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
    fun getBoneCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getBoneCountBind, handle)
    }

    fun getBone(idx: Int): Bone2D? {
        return Bone2D.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getBoneBind, handle, idx))
    }

    fun getSkeleton(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getSkeletonBind, handle)
    }

    fun setModificationStack(modificationStack: SkeletonModificationStack2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setModificationStackBind, handle, listOf(modificationStack?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getModificationStack(): SkeletonModificationStack2D? {
        return SkeletonModificationStack2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getModificationStackBind, handle))
    }

    fun executeModifications(delta: Double, executionMode: Int) {
        ObjectCalls.ptrcallWithDoubleAndIntArgs(executeModificationsBind, handle, delta, executionMode)
    }

    fun setBoneLocalPoseOverride(boneIdx: Int, overridePose: Transform2D, strength: Double, persistent: Boolean) {
        ObjectCalls.ptrcallWithIntTransform2DDoubleBoolArgs(setBoneLocalPoseOverrideBind, handle, boneIdx, overridePose, strength, persistent)
    }

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
