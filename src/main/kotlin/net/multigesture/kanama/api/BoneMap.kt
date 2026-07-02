package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Describes a mapping of bone names for retargeting `Skeleton3D` into common names defined by a
 * `SkeletonProfile`.
 *
 * Generated from Godot docs: BoneMap
 */
class BoneMap(handle: MemorySegment) : Resource(handle) {
    var profile: SkeletonProfile?
        @JvmName("profileProperty")
        get() = getProfile()
        @JvmName("setProfileProperty")
        set(value) = setProfile(value)

    fun getProfile(): SkeletonProfile? {
        return SkeletonProfile.wrap(ObjectCalls.ptrcallNoArgsRetObject(getProfileBind, handle))
    }

    fun setProfile(profile: SkeletonProfile?) {
        ObjectCalls.ptrcallWithObjectArgs(setProfileBind, handle, listOf(profile?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getSkeletonBoneName(profileBoneName: String): String {
        return ObjectCalls.ptrcallWithStringNameArgRetStringName(getSkeletonBoneNameBind, handle, profileBoneName)
    }

    fun setSkeletonBoneName(profileBoneName: String, skeletonBoneName: String) {
        ObjectCalls.ptrcallWithTwoStringNameArgs(setSkeletonBoneNameBind, handle, profileBoneName, skeletonBoneName)
    }

    fun findProfileBoneName(skeletonBoneName: String): String {
        return ObjectCalls.ptrcallWithStringNameArgRetStringName(findProfileBoneNameBind, handle, skeletonBoneName)
    }

    object Signals {
        const val boneMapUpdated: String = "bone_map_updated"
        const val profileUpdated: String = "profile_updated"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): BoneMap? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): BoneMap? =
            if (handle.address() == 0L) null else BoneMap(handle)

        private const val GET_PROFILE_HASH = 4291782652L
        private val getProfileBind by lazy {
            ObjectCalls.getMethodBind("BoneMap", "get_profile", GET_PROFILE_HASH)
        }

        private const val SET_PROFILE_HASH = 3870374136L
        private val setProfileBind by lazy {
            ObjectCalls.getMethodBind("BoneMap", "set_profile", SET_PROFILE_HASH)
        }

        private const val GET_SKELETON_BONE_NAME_HASH = 1965194235L
        private val getSkeletonBoneNameBind by lazy {
            ObjectCalls.getMethodBind("BoneMap", "get_skeleton_bone_name", GET_SKELETON_BONE_NAME_HASH)
        }

        private const val SET_SKELETON_BONE_NAME_HASH = 3740211285L
        private val setSkeletonBoneNameBind by lazy {
            ObjectCalls.getMethodBind("BoneMap", "set_skeleton_bone_name", SET_SKELETON_BONE_NAME_HASH)
        }

        private const val FIND_PROFILE_BONE_NAME_HASH = 1965194235L
        private val findProfileBoneNameBind by lazy {
            ObjectCalls.getMethodBind("BoneMap", "find_profile_bone_name", FIND_PROFILE_BONE_NAME_HASH)
        }
    }
}
