package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Describes a mapping of bone names for retargeting `Skeleton3D` into common names defined by a
 * `SkeletonProfile`.
 *
 * Generated from Godot docs: BoneMap
 */
class BoneMap(handle: GodotHandle) : Resource(handle) {
    var profile: SkeletonProfile?
        @JvmName("profileProperty")
        get() = getProfile()
        @JvmName("setProfileProperty")
        set(value) = setProfile(value)

    /**
     * A `SkeletonProfile` of the mapping target. Key names in the `BoneMap` are synchronized with it.
     *
     * Generated from Godot docs: BoneMap.get_profile
     */
    fun getProfile(): SkeletonProfile? {
        checkOpen()
        val ret = SkeletonProfile.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getProfileBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * A `SkeletonProfile` of the mapping target. Key names in the `BoneMap` are synchronized with it.
     *
     * Generated from Godot docs: BoneMap.set_profile
     */
    fun setProfile(profile: SkeletonProfile?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setProfileBind, segment, listOf(profile?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(profile)
    }

    /**
     * Returns a skeleton bone name is mapped to `profile_bone_name`. In the retargeting process, the
     * returned bone name is the bone name of the source skeleton.
     *
     * Generated from Godot docs: BoneMap.get_skeleton_bone_name
     */
    fun getSkeletonBoneName(profileBoneName: String): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithStringNameArgRetStringName(Binds.getSkeletonBoneNameBind, segment, profileBoneName)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Maps a skeleton bone name to `profile_bone_name`. In the retargeting process, the setting bone
     * name is the bone name of the source skeleton.
     *
     * Generated from Godot docs: BoneMap.set_skeleton_bone_name
     */
    fun setSkeletonBoneName(profileBoneName: String, skeletonBoneName: String) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoStringNameArgs(Binds.setSkeletonBoneNameBind, segment, profileBoneName, skeletonBoneName)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns a profile bone name having `skeleton_bone_name`. If not found, an empty `StringName`
     * will be returned. In the retargeting process, the returned bone name is the bone name of the
     * target skeleton.
     *
     * Generated from Godot docs: BoneMap.find_profile_bone_name
     */
    fun findProfileBoneName(skeletonBoneName: String): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithStringNameArgRetStringName(Binds.findProfileBoneNameBind, segment, skeletonBoneName)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /** Signal `bone_map_updated()`; see [TypedSignal]. */
    val boneMapUpdated: Signal0
        @JvmName("boneMapUpdatedTypedSignal")
        get() = Signal0(this, "bone_map_updated")

    /** Signal `profile_updated()`; see [TypedSignal]. */
    val profileUpdated: Signal0
        @JvmName("profileUpdatedTypedSignal")
        get() = Signal0(this, "profile_updated")

    object Signals {
        const val boneMapUpdated: String = "bone_map_updated"
        const val profileUpdated: String = "profile_updated"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): BoneMap? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): BoneMap? =
            if (handle.address() == 0L) null else RefCounted.owned(BoneMap(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): BoneMap? =
            if (handle.address() == 0L) null else BoneMap(GodotHandle(handle))

        /** A new BoneMap, GDScript's `BoneMap.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): BoneMap =
            RefCounted.owned(BoneMap(GodotHandle(ObjectCalls.constructObject("BoneMap"))))
    }

    private object Binds {
        private const val GET_PROFILE_HASH = 4291782652L
        @JvmField
        val getProfileBind =
            ObjectCalls.getMethodBind("BoneMap", "get_profile", GET_PROFILE_HASH)

        private const val SET_PROFILE_HASH = 3870374136L
        @JvmField
        val setProfileBind =
            ObjectCalls.getMethodBind("BoneMap", "set_profile", SET_PROFILE_HASH)

        private const val GET_SKELETON_BONE_NAME_HASH = 1965194235L
        @JvmField
        val getSkeletonBoneNameBind =
            ObjectCalls.getMethodBind("BoneMap", "get_skeleton_bone_name", GET_SKELETON_BONE_NAME_HASH)

        private const val SET_SKELETON_BONE_NAME_HASH = 3740211285L
        @JvmField
        val setSkeletonBoneNameBind =
            ObjectCalls.getMethodBind("BoneMap", "set_skeleton_bone_name", SET_SKELETON_BONE_NAME_HASH)

        private const val FIND_PROFILE_BONE_NAME_HASH = 1965194235L
        @JvmField
        val findProfileBoneNameBind =
            ObjectCalls.getMethodBind("BoneMap", "find_profile_bone_name", FIND_PROFILE_BONE_NAME_HASH)
    }
}
