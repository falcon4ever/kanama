package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath

/**
 * A `RigidBody2D`-derived node used to make `Bone2D`s in a `Skeleton2D` react to physics.
 *
 * Generated from Godot docs: PhysicalBone2D
 */
class PhysicalBone2D(handle: MemorySegment) : RigidBody2D(handle) {
    var bone2dNodepath: NodePath
        @JvmName("bone2dNodepathProperty")
        get() = getBone2dNodepath()
        @JvmName("setBone2dNodepathProperty")
        set(value) = setBone2dNodepath(value)

    var bone2dIndex: Int
        @JvmName("bone2dIndexProperty")
        get() = getBone2dIndex()
        @JvmName("setBone2dIndexProperty")
        set(value) = setBone2dIndex(value)

    var autoConfigureJoint: Boolean
        @JvmName("autoConfigureJointProperty")
        get() = getAutoConfigureJoint()
        @JvmName("setAutoConfigureJointProperty")
        set(value) = setAutoConfigureJoint(value)

    var simulatePhysics: Boolean
        @JvmName("simulatePhysicsProperty")
        get() = getSimulatePhysics()
        @JvmName("setSimulatePhysicsProperty")
        set(value) = setSimulatePhysics(value)

    var followBoneWhenSimulating: Boolean
        @JvmName("followBoneWhenSimulatingProperty")
        get() = getFollowBoneWhenSimulating()
        @JvmName("setFollowBoneWhenSimulatingProperty")
        set(value) = setFollowBoneWhenSimulating(value)

    fun getJoint(): Joint2D? {
        return Joint2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getJointBind, handle))
    }

    fun getAutoConfigureJoint(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getAutoConfigureJointBind, handle)
    }

    fun setAutoConfigureJoint(autoConfigureJoint: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAutoConfigureJointBind, handle, autoConfigureJoint)
    }

    fun setSimulatePhysics(simulatePhysics: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSimulatePhysicsBind, handle, simulatePhysics)
    }

    fun getSimulatePhysics(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getSimulatePhysicsBind, handle)
    }

    fun isSimulatingPhysics(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSimulatingPhysicsBind, handle)
    }

    fun setBone2dNodepath(nodepath: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setBone2dNodepathBind, handle, nodepath)
    }

    fun getBone2dNodepath(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getBone2dNodepathBind, handle)
    }

    fun setBone2dIndex(boneIndex: Int) {
        ObjectCalls.ptrcallWithIntArg(setBone2dIndexBind, handle, boneIndex)
    }

    fun getBone2dIndex(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getBone2dIndexBind, handle)
    }

    fun setFollowBoneWhenSimulating(followBone: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFollowBoneWhenSimulatingBind, handle, followBone)
    }

    fun getFollowBoneWhenSimulating(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getFollowBoneWhenSimulatingBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PhysicalBone2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PhysicalBone2D? =
            if (handle.address() == 0L) null else PhysicalBone2D(handle)

        private const val GET_JOINT_HASH = 3582132112L
        private val getJointBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone2D", "get_joint", GET_JOINT_HASH)
        }

        private const val GET_AUTO_CONFIGURE_JOINT_HASH = 36873697L
        private val getAutoConfigureJointBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone2D", "get_auto_configure_joint", GET_AUTO_CONFIGURE_JOINT_HASH)
        }

        private const val SET_AUTO_CONFIGURE_JOINT_HASH = 2586408642L
        private val setAutoConfigureJointBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone2D", "set_auto_configure_joint", SET_AUTO_CONFIGURE_JOINT_HASH)
        }

        private const val SET_SIMULATE_PHYSICS_HASH = 2586408642L
        private val setSimulatePhysicsBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone2D", "set_simulate_physics", SET_SIMULATE_PHYSICS_HASH)
        }

        private const val GET_SIMULATE_PHYSICS_HASH = 36873697L
        private val getSimulatePhysicsBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone2D", "get_simulate_physics", GET_SIMULATE_PHYSICS_HASH)
        }

        private const val IS_SIMULATING_PHYSICS_HASH = 36873697L
        private val isSimulatingPhysicsBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone2D", "is_simulating_physics", IS_SIMULATING_PHYSICS_HASH)
        }

        private const val SET_BONE2D_NODEPATH_HASH = 1348162250L
        private val setBone2dNodepathBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone2D", "set_bone2d_nodepath", SET_BONE2D_NODEPATH_HASH)
        }

        private const val GET_BONE2D_NODEPATH_HASH = 4075236667L
        private val getBone2dNodepathBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone2D", "get_bone2d_nodepath", GET_BONE2D_NODEPATH_HASH)
        }

        private const val SET_BONE2D_INDEX_HASH = 1286410249L
        private val setBone2dIndexBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone2D", "set_bone2d_index", SET_BONE2D_INDEX_HASH)
        }

        private const val GET_BONE2D_INDEX_HASH = 3905245786L
        private val getBone2dIndexBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone2D", "get_bone2d_index", GET_BONE2D_INDEX_HASH)
        }

        private const val SET_FOLLOW_BONE_WHEN_SIMULATING_HASH = 2586408642L
        private val setFollowBoneWhenSimulatingBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone2D", "set_follow_bone_when_simulating", SET_FOLLOW_BONE_WHEN_SIMULATING_HASH)
        }

        private const val GET_FOLLOW_BONE_WHEN_SIMULATING_HASH = 36873697L
        private val getFollowBoneWhenSimulatingBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone2D", "get_follow_bone_when_simulating", GET_FOLLOW_BONE_WHEN_SIMULATING_HASH)
        }
    }
}
