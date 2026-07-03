package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Quaternion
import net.multigesture.kanama.types.Vector3

/**
 * A base class of the collision that interacts with `SpringBoneSimulator3D`.
 *
 * Generated from Godot docs: SpringBoneCollision3D
 */
open class SpringBoneCollision3D(handle: MemorySegment) : Node3D(handle) {
    var boneName: String
        @JvmName("boneNameProperty")
        get() = getBoneName()
        @JvmName("setBoneNameProperty")
        set(value) = setBoneName(value)

    var bone: Int
        @JvmName("boneProperty")
        get() = getBone()
        @JvmName("setBoneProperty")
        set(value) = setBone(value)

    var positionOffset: Vector3
        @JvmName("positionOffsetProperty")
        get() = getPositionOffset()
        @JvmName("setPositionOffsetProperty")
        set(value) = setPositionOffset(value)

    var rotationOffset: Quaternion
        @JvmName("rotationOffsetProperty")
        get() = getRotationOffset()
        @JvmName("setRotationOffsetProperty")
        set(value) = setRotationOffset(value)

    /**
     * Get parent `Skeleton3D` node of the parent `SpringBoneSimulator3D` if found.
     *
     * Generated from Godot docs: SpringBoneCollision3D.get_skeleton
     */
    fun getSkeleton(): Skeleton3D? {
        return Skeleton3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getSkeletonBind, handle))
    }

    /**
     * The name of the attached bone.
     *
     * Generated from Godot docs: SpringBoneCollision3D.set_bone_name
     */
    fun setBoneName(boneName: String) {
        ObjectCalls.ptrcallWithStringArg(setBoneNameBind, handle, boneName)
    }

    /**
     * The name of the attached bone.
     *
     * Generated from Godot docs: SpringBoneCollision3D.get_bone_name
     */
    fun getBoneName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getBoneNameBind, handle)
    }

    /**
     * The index of the attached bone.
     *
     * Generated from Godot docs: SpringBoneCollision3D.set_bone
     */
    fun setBone(bone: Int) {
        ObjectCalls.ptrcallWithIntArg(setBoneBind, handle, bone)
    }

    /**
     * The index of the attached bone.
     *
     * Generated from Godot docs: SpringBoneCollision3D.get_bone
     */
    fun getBone(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getBoneBind, handle)
    }

    /**
     * The offset of the position from `Skeleton3D`'s `bone` pose position.
     *
     * Generated from Godot docs: SpringBoneCollision3D.set_position_offset
     */
    fun setPositionOffset(offset: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setPositionOffsetBind, handle, offset)
    }

    /**
     * The offset of the position from `Skeleton3D`'s `bone` pose position.
     *
     * Generated from Godot docs: SpringBoneCollision3D.get_position_offset
     */
    fun getPositionOffset(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getPositionOffsetBind, handle)
    }

    /**
     * The offset of the rotation from `Skeleton3D`'s `bone` pose rotation.
     *
     * Generated from Godot docs: SpringBoneCollision3D.set_rotation_offset
     */
    fun setRotationOffset(offset: Quaternion) {
        ObjectCalls.ptrcallWithQuaternionArg(setRotationOffsetBind, handle, offset)
    }

    /**
     * The offset of the rotation from `Skeleton3D`'s `bone` pose rotation.
     *
     * Generated from Godot docs: SpringBoneCollision3D.get_rotation_offset
     */
    fun getRotationOffset(): Quaternion {
        return ObjectCalls.ptrcallNoArgsRetQuaternion(getRotationOffsetBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): SpringBoneCollision3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SpringBoneCollision3D? =
            if (handle.address() == 0L) null else SpringBoneCollision3D(handle)

        private const val GET_SKELETON_HASH = 1488626673L
        private val getSkeletonBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneCollision3D", "get_skeleton", GET_SKELETON_HASH)
        }

        private const val SET_BONE_NAME_HASH = 83702148L
        private val setBoneNameBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneCollision3D", "set_bone_name", SET_BONE_NAME_HASH)
        }

        private const val GET_BONE_NAME_HASH = 201670096L
        private val getBoneNameBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneCollision3D", "get_bone_name", GET_BONE_NAME_HASH)
        }

        private const val SET_BONE_HASH = 1286410249L
        private val setBoneBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneCollision3D", "set_bone", SET_BONE_HASH)
        }

        private const val GET_BONE_HASH = 3905245786L
        private val getBoneBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneCollision3D", "get_bone", GET_BONE_HASH)
        }

        private const val SET_POSITION_OFFSET_HASH = 3460891852L
        private val setPositionOffsetBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneCollision3D", "set_position_offset", SET_POSITION_OFFSET_HASH)
        }

        private const val GET_POSITION_OFFSET_HASH = 3360562783L
        private val getPositionOffsetBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneCollision3D", "get_position_offset", GET_POSITION_OFFSET_HASH)
        }

        private const val SET_ROTATION_OFFSET_HASH = 1727505552L
        private val setRotationOffsetBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneCollision3D", "set_rotation_offset", SET_ROTATION_OFFSET_HASH)
        }

        private const val GET_ROTATION_OFFSET_HASH = 1222331677L
        private val getRotationOffsetBind by lazy {
            ObjectCalls.getMethodBind("SpringBoneCollision3D", "get_rotation_offset", GET_ROTATION_OFFSET_HASH)
        }
    }
}
