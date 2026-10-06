package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.NodePath

/**
 * A `RigidBody2D`-derived node used to make `Bone2D`s in a `Skeleton2D` react to physics.
 *
 * Generated from Godot docs: PhysicalBone2D
 */
class PhysicalBone2D(handle: GodotHandle) : RigidBody2D(handle) {
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

    /**
     * Returns the first `Joint2D` child node, if one exists. This is mainly a helper function to make
     * it easier to get the `Joint2D` that the `PhysicalBone2D` is autoconfiguring.
     *
     * Generated from Godot docs: PhysicalBone2D.get_joint
     */
    fun getJoint(): Joint2D? {
        return Joint2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getJointBind, segment))
    }

    /**
     * If `true`, the `PhysicalBone2D` will automatically configure the first `Joint2D` child node. The
     * automatic configuration is limited to setting up the node properties and positioning the
     * `Joint2D`.
     *
     * Generated from Godot docs: PhysicalBone2D.get_auto_configure_joint
     */
    fun getAutoConfigureJoint(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getAutoConfigureJointBind, segment)
    }

    /**
     * If `true`, the `PhysicalBone2D` will automatically configure the first `Joint2D` child node. The
     * automatic configuration is limited to setting up the node properties and positioning the
     * `Joint2D`.
     *
     * Generated from Godot docs: PhysicalBone2D.set_auto_configure_joint
     */
    fun setAutoConfigureJoint(autoConfigureJoint: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setAutoConfigureJointBind, segment, autoConfigureJoint)
    }

    /**
     * If `true`, the `PhysicalBone2D` will start simulating using physics. If `false`, the
     * `PhysicalBone2D` will follow the transform of the `Bone2D` node. Note: To have the `Bone2D`s
     * visually follow the `PhysicalBone2D`, use a `SkeletonModification2DPhysicalBones` modification
     * on the `Skeleton2D` node with the `Bone2D` nodes.
     *
     * Generated from Godot docs: PhysicalBone2D.set_simulate_physics
     */
    fun setSimulatePhysics(simulatePhysics: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setSimulatePhysicsBind, segment, simulatePhysics)
    }

    /**
     * If `true`, the `PhysicalBone2D` will start simulating using physics. If `false`, the
     * `PhysicalBone2D` will follow the transform of the `Bone2D` node. Note: To have the `Bone2D`s
     * visually follow the `PhysicalBone2D`, use a `SkeletonModification2DPhysicalBones` modification
     * on the `Skeleton2D` node with the `Bone2D` nodes.
     *
     * Generated from Godot docs: PhysicalBone2D.get_simulate_physics
     */
    fun getSimulatePhysics(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getSimulatePhysicsBind, segment)
    }

    /**
     * Returns a boolean that indicates whether the `PhysicalBone2D` is running and simulating using
     * the Godot 2D physics engine. When `true`, the PhysicalBone2D node is using physics.
     *
     * Generated from Godot docs: PhysicalBone2D.is_simulating_physics
     */
    fun isSimulatingPhysics(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isSimulatingPhysicsBind, segment)
    }

    /**
     * The `NodePath` to the `Bone2D` that this `PhysicalBone2D` should simulate.
     *
     * Generated from Godot docs: PhysicalBone2D.set_bone2d_nodepath
     */
    fun setBone2dNodepath(nodepath: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(Binds.setBone2dNodepathBind, segment, nodepath)
    }

    /**
     * The `NodePath` to the `Bone2D` that this `PhysicalBone2D` should simulate.
     *
     * Generated from Godot docs: PhysicalBone2D.get_bone2d_nodepath
     */
    fun getBone2dNodepath(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(Binds.getBone2dNodepathBind, segment)
    }

    /**
     * The index of the `Bone2D` that this `PhysicalBone2D` should simulate.
     *
     * Generated from Godot docs: PhysicalBone2D.set_bone2d_index
     */
    fun setBone2dIndex(boneIndex: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setBone2dIndexBind, segment, boneIndex)
    }

    /**
     * The index of the `Bone2D` that this `PhysicalBone2D` should simulate.
     *
     * Generated from Godot docs: PhysicalBone2D.get_bone2d_index
     */
    fun getBone2dIndex(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getBone2dIndexBind, segment)
    }

    /**
     * If `true`, the `PhysicalBone2D` will keep the transform of the bone it is bound to when
     * simulating physics.
     *
     * Generated from Godot docs: PhysicalBone2D.set_follow_bone_when_simulating
     */
    fun setFollowBoneWhenSimulating(followBone: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setFollowBoneWhenSimulatingBind, segment, followBone)
    }

    /**
     * If `true`, the `PhysicalBone2D` will keep the transform of the bone it is bound to when
     * simulating physics.
     *
     * Generated from Godot docs: PhysicalBone2D.get_follow_bone_when_simulating
     */
    fun getFollowBoneWhenSimulating(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getFollowBoneWhenSimulatingBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PhysicalBone2D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): PhysicalBone2D? =
            if (handle.address() == 0L) null else PhysicalBone2D(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_JOINT_HASH = 3582132112L
        @JvmField
        val getJointBind =
            ObjectCalls.getMethodBind("PhysicalBone2D", "get_joint", GET_JOINT_HASH)

        private const val GET_AUTO_CONFIGURE_JOINT_HASH = 36873697L
        @JvmField
        val getAutoConfigureJointBind =
            ObjectCalls.getMethodBind("PhysicalBone2D", "get_auto_configure_joint", GET_AUTO_CONFIGURE_JOINT_HASH)

        private const val SET_AUTO_CONFIGURE_JOINT_HASH = 2586408642L
        @JvmField
        val setAutoConfigureJointBind =
            ObjectCalls.getMethodBind("PhysicalBone2D", "set_auto_configure_joint", SET_AUTO_CONFIGURE_JOINT_HASH)

        private const val SET_SIMULATE_PHYSICS_HASH = 2586408642L
        @JvmField
        val setSimulatePhysicsBind =
            ObjectCalls.getMethodBind("PhysicalBone2D", "set_simulate_physics", SET_SIMULATE_PHYSICS_HASH)

        private const val GET_SIMULATE_PHYSICS_HASH = 36873697L
        @JvmField
        val getSimulatePhysicsBind =
            ObjectCalls.getMethodBind("PhysicalBone2D", "get_simulate_physics", GET_SIMULATE_PHYSICS_HASH)

        private const val IS_SIMULATING_PHYSICS_HASH = 36873697L
        @JvmField
        val isSimulatingPhysicsBind =
            ObjectCalls.getMethodBind("PhysicalBone2D", "is_simulating_physics", IS_SIMULATING_PHYSICS_HASH)

        private const val SET_BONE2D_NODEPATH_HASH = 1348162250L
        @JvmField
        val setBone2dNodepathBind =
            ObjectCalls.getMethodBind("PhysicalBone2D", "set_bone2d_nodepath", SET_BONE2D_NODEPATH_HASH)

        private const val GET_BONE2D_NODEPATH_HASH = 4075236667L
        @JvmField
        val getBone2dNodepathBind =
            ObjectCalls.getMethodBind("PhysicalBone2D", "get_bone2d_nodepath", GET_BONE2D_NODEPATH_HASH)

        private const val SET_BONE2D_INDEX_HASH = 1286410249L
        @JvmField
        val setBone2dIndexBind =
            ObjectCalls.getMethodBind("PhysicalBone2D", "set_bone2d_index", SET_BONE2D_INDEX_HASH)

        private const val GET_BONE2D_INDEX_HASH = 3905245786L
        @JvmField
        val getBone2dIndexBind =
            ObjectCalls.getMethodBind("PhysicalBone2D", "get_bone2d_index", GET_BONE2D_INDEX_HASH)

        private const val SET_FOLLOW_BONE_WHEN_SIMULATING_HASH = 2586408642L
        @JvmField
        val setFollowBoneWhenSimulatingBind =
            ObjectCalls.getMethodBind("PhysicalBone2D", "set_follow_bone_when_simulating", SET_FOLLOW_BONE_WHEN_SIMULATING_HASH)

        private const val GET_FOLLOW_BONE_WHEN_SIMULATING_HASH = 36873697L
        @JvmField
        val getFollowBoneWhenSimulatingBind =
            ObjectCalls.getMethodBind("PhysicalBone2D", "get_follow_bone_when_simulating", GET_FOLLOW_BONE_WHEN_SIMULATING_HASH)
    }
}
