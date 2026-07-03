package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Vector2

/**
 * A modification that jiggles `Bone2D` nodes as they move towards a target.
 *
 * Generated from Godot docs: SkeletonModification2DJiggle
 */
class SkeletonModification2DJiggle(handle: MemorySegment) : SkeletonModification2D(handle) {
    var targetNodepath: NodePath
        @JvmName("targetNodepathProperty")
        get() = getTargetNode()
        @JvmName("setTargetNodepathProperty")
        set(value) = setTargetNode(value)

    var jiggleDataChainLength: Int
        @JvmName("jiggleDataChainLengthProperty")
        get() = getJiggleDataChainLength()
        @JvmName("setJiggleDataChainLengthProperty")
        set(value) = setJiggleDataChainLength(value)

    var stiffness: Double
        @JvmName("stiffnessProperty")
        get() = getStiffness()
        @JvmName("setStiffnessProperty")
        set(value) = setStiffness(value)

    var mass: Double
        @JvmName("massProperty")
        get() = getMass()
        @JvmName("setMassProperty")
        set(value) = setMass(value)

    var damping: Double
        @JvmName("dampingProperty")
        get() = getDamping()
        @JvmName("setDampingProperty")
        set(value) = setDamping(value)

    var useGravity: Boolean
        @JvmName("useGravityProperty")
        get() = getUseGravity()
        @JvmName("setUseGravityProperty")
        set(value) = setUseGravity(value)

    var gravity: Vector2
        @JvmName("gravityProperty")
        get() = getGravity()
        @JvmName("setGravityProperty")
        set(value) = setGravity(value)

    /**
     * The NodePath to the node that is the target for the Jiggle modification. This node is what the
     * Jiggle chain will attempt to rotate the bone chain to.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.set_target_node
     */
    fun setTargetNode(targetNodepath: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setTargetNodeBind, handle, targetNodepath)
    }

    /**
     * The NodePath to the node that is the target for the Jiggle modification. This node is what the
     * Jiggle chain will attempt to rotate the bone chain to.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.get_target_node
     */
    fun getTargetNode(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getTargetNodeBind, handle)
    }

    /**
     * The amount of Jiggle joints in the Jiggle modification.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.set_jiggle_data_chain_length
     */
    fun setJiggleDataChainLength(length: Int) {
        ObjectCalls.ptrcallWithIntArg(setJiggleDataChainLengthBind, handle, length)
    }

    /**
     * The amount of Jiggle joints in the Jiggle modification.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.get_jiggle_data_chain_length
     */
    fun getJiggleDataChainLength(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getJiggleDataChainLengthBind, handle)
    }

    /**
     * The default amount of stiffness assigned to the Jiggle joints, if they are not overridden.
     * Higher values act more like springs, quickly moving into the correct position.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.set_stiffness
     */
    fun setStiffness(stiffness: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setStiffnessBind, handle, stiffness)
    }

    /**
     * The default amount of stiffness assigned to the Jiggle joints, if they are not overridden.
     * Higher values act more like springs, quickly moving into the correct position.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.get_stiffness
     */
    fun getStiffness(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getStiffnessBind, handle)
    }

    /**
     * The default amount of mass assigned to the Jiggle joints, if they are not overridden. Higher
     * values lead to faster movements and more overshooting.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.set_mass
     */
    fun setMass(mass: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMassBind, handle, mass)
    }

    /**
     * The default amount of mass assigned to the Jiggle joints, if they are not overridden. Higher
     * values lead to faster movements and more overshooting.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.get_mass
     */
    fun getMass(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMassBind, handle)
    }

    /**
     * The default amount of damping applied to the Jiggle joints, if they are not overridden. Higher
     * values lead to more of the calculated velocity being applied.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.set_damping
     */
    fun setDamping(damping: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDampingBind, handle, damping)
    }

    /**
     * The default amount of damping applied to the Jiggle joints, if they are not overridden. Higher
     * values lead to more of the calculated velocity being applied.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.get_damping
     */
    fun getDamping(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDampingBind, handle)
    }

    /**
     * Whether the gravity vector, `gravity`, should be applied to the Jiggle joints, assuming they are
     * not overriding the default settings.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.set_use_gravity
     */
    fun setUseGravity(useGravity: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseGravityBind, handle, useGravity)
    }

    /**
     * Whether the gravity vector, `gravity`, should be applied to the Jiggle joints, assuming they are
     * not overriding the default settings.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.get_use_gravity
     */
    fun getUseGravity(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUseGravityBind, handle)
    }

    /**
     * The default amount of gravity applied to the Jiggle joints, if they are not overridden.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.set_gravity
     */
    fun setGravity(gravity: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setGravityBind, handle, gravity)
    }

    /**
     * The default amount of gravity applied to the Jiggle joints, if they are not overridden.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.get_gravity
     */
    fun getGravity(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getGravityBind, handle)
    }

    /**
     * If `true`, the Jiggle modifier will take colliders into account, keeping them from entering into
     * these collision objects.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.set_use_colliders
     */
    fun setUseColliders(useColliders: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseCollidersBind, handle, useColliders)
    }

    /**
     * Returns whether the jiggle modifier is taking physics colliders into account when solving.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.get_use_colliders
     */
    fun getUseColliders(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUseCollidersBind, handle)
    }

    /**
     * Sets the collision mask that the Jiggle modifier will use when reacting to colliders, if the
     * Jiggle modifier is set to take colliders into account.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.set_collision_mask
     */
    fun setCollisionMask(collisionMask: Int) {
        ObjectCalls.ptrcallWithIntArg(setCollisionMaskBind, handle, collisionMask)
    }

    /**
     * Returns the collision mask used by the Jiggle modifier when collisions are enabled.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.get_collision_mask
     */
    fun getCollisionMask(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getCollisionMaskBind, handle)
    }

    /**
     * Resets the internal jiggle simulation state to the current bone positions, clearing velocity,
     * acceleration, and accumulated forces.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.reset
     */
    fun reset() {
        ObjectCalls.ptrcallNoArgs(resetBind, handle)
    }

    /**
     * Sets the `Bone2D` node assigned to the Jiggle joint at `joint_idx`.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.set_jiggle_joint_bone2d_node
     */
    fun setJiggleJointBone2dNode(jointIdx: Int, bone2dNode: NodePath) {
        ObjectCalls.ptrcallWithIntAndNodePathArg(setJiggleJointBone2dNodeBind, handle, jointIdx, bone2dNode)
    }

    /**
     * Returns the `Bone2D` node assigned to the Jiggle joint at `joint_idx`.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.get_jiggle_joint_bone2d_node
     */
    fun getJiggleJointBone2dNode(jointIdx: Int): NodePath {
        return ObjectCalls.ptrcallWithIntArgRetNodePath(getJiggleJointBone2dNodeBind, handle, jointIdx)
    }

    /**
     * Sets the bone index, `bone_idx`, of the Jiggle joint at `joint_idx`. When possible, this will
     * also update the `bone2d_node` of the Jiggle joint based on data provided by the linked skeleton.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.set_jiggle_joint_bone_index
     */
    fun setJiggleJointBoneIndex(jointIdx: Int, boneIdx: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setJiggleJointBoneIndexBind, handle, jointIdx, boneIdx)
    }

    /**
     * Returns the index of the `Bone2D` node assigned to the Jiggle joint at `joint_idx`.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.get_jiggle_joint_bone_index
     */
    fun getJiggleJointBoneIndex(jointIdx: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getJiggleJointBoneIndexBind, handle, jointIdx)
    }

    /**
     * Sets whether the Jiggle joint at `joint_idx` should override the default Jiggle joint settings.
     * Setting this to `true` will make the joint use its own settings rather than the default ones
     * attached to the modification.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.set_jiggle_joint_override
     */
    fun setJiggleJointOverride(jointIdx: Int, override: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setJiggleJointOverrideBind, handle, jointIdx, override)
    }

    /**
     * Returns a boolean that indicates whether the joint at `joint_idx` is overriding the default
     * Jiggle joint data defined in the modification.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.get_jiggle_joint_override
     */
    fun getJiggleJointOverride(jointIdx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getJiggleJointOverrideBind, handle, jointIdx)
    }

    /**
     * Sets the of stiffness of the Jiggle joint at `joint_idx`.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.set_jiggle_joint_stiffness
     */
    fun setJiggleJointStiffness(jointIdx: Int, stiffness: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setJiggleJointStiffnessBind, handle, jointIdx, stiffness)
    }

    /**
     * Returns the stiffness of the Jiggle joint at `joint_idx`.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.get_jiggle_joint_stiffness
     */
    fun getJiggleJointStiffness(jointIdx: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getJiggleJointStiffnessBind, handle, jointIdx)
    }

    /**
     * Sets the of mass of the Jiggle joint at `joint_idx`.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.set_jiggle_joint_mass
     */
    fun setJiggleJointMass(jointIdx: Int, mass: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setJiggleJointMassBind, handle, jointIdx, mass)
    }

    /**
     * Returns the amount of mass of the jiggle joint at `joint_idx`.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.get_jiggle_joint_mass
     */
    fun getJiggleJointMass(jointIdx: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getJiggleJointMassBind, handle, jointIdx)
    }

    /**
     * Sets the amount of damping of the Jiggle joint at `joint_idx`.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.set_jiggle_joint_damping
     */
    fun setJiggleJointDamping(jointIdx: Int, damping: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setJiggleJointDampingBind, handle, jointIdx, damping)
    }

    /**
     * Returns the amount of damping of the Jiggle joint at `joint_idx`.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.get_jiggle_joint_damping
     */
    fun getJiggleJointDamping(jointIdx: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getJiggleJointDampingBind, handle, jointIdx)
    }

    /**
     * Sets whether the Jiggle joint at `joint_idx` should use gravity.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.set_jiggle_joint_use_gravity
     */
    fun setJiggleJointUseGravity(jointIdx: Int, useGravity: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setJiggleJointUseGravityBind, handle, jointIdx, useGravity)
    }

    /**
     * Returns a boolean that indicates whether the joint at `joint_idx` is using gravity or not.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.get_jiggle_joint_use_gravity
     */
    fun getJiggleJointUseGravity(jointIdx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getJiggleJointUseGravityBind, handle, jointIdx)
    }

    /**
     * Sets the gravity vector of the Jiggle joint at `joint_idx`.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.set_jiggle_joint_gravity
     */
    fun setJiggleJointGravity(jointIdx: Int, gravity: Vector2) {
        ObjectCalls.ptrcallWithIntAndVector2Arg(setJiggleJointGravityBind, handle, jointIdx, gravity)
    }

    /**
     * Returns a `Vector2` representing the amount of gravity the Jiggle joint at `joint_idx` is
     * influenced by.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.get_jiggle_joint_gravity
     */
    fun getJiggleJointGravity(jointIdx: Int): Vector2 {
        return ObjectCalls.ptrcallWithIntArgRetVector2(getJiggleJointGravityBind, handle, jointIdx)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): SkeletonModification2DJiggle? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SkeletonModification2DJiggle? =
            if (handle.address() == 0L) null else SkeletonModification2DJiggle(handle)

        private const val SET_TARGET_NODE_HASH = 1348162250L
        private val setTargetNodeBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "set_target_node", SET_TARGET_NODE_HASH)
        }

        private const val GET_TARGET_NODE_HASH = 4075236667L
        private val getTargetNodeBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "get_target_node", GET_TARGET_NODE_HASH)
        }

        private const val SET_JIGGLE_DATA_CHAIN_LENGTH_HASH = 1286410249L
        private val setJiggleDataChainLengthBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "set_jiggle_data_chain_length", SET_JIGGLE_DATA_CHAIN_LENGTH_HASH)
        }

        private const val GET_JIGGLE_DATA_CHAIN_LENGTH_HASH = 2455072627L
        private val getJiggleDataChainLengthBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "get_jiggle_data_chain_length", GET_JIGGLE_DATA_CHAIN_LENGTH_HASH)
        }

        private const val SET_STIFFNESS_HASH = 373806689L
        private val setStiffnessBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "set_stiffness", SET_STIFFNESS_HASH)
        }

        private const val GET_STIFFNESS_HASH = 1740695150L
        private val getStiffnessBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "get_stiffness", GET_STIFFNESS_HASH)
        }

        private const val SET_MASS_HASH = 373806689L
        private val setMassBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "set_mass", SET_MASS_HASH)
        }

        private const val GET_MASS_HASH = 1740695150L
        private val getMassBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "get_mass", GET_MASS_HASH)
        }

        private const val SET_DAMPING_HASH = 373806689L
        private val setDampingBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "set_damping", SET_DAMPING_HASH)
        }

        private const val GET_DAMPING_HASH = 1740695150L
        private val getDampingBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "get_damping", GET_DAMPING_HASH)
        }

        private const val SET_USE_GRAVITY_HASH = 2586408642L
        private val setUseGravityBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "set_use_gravity", SET_USE_GRAVITY_HASH)
        }

        private const val GET_USE_GRAVITY_HASH = 36873697L
        private val getUseGravityBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "get_use_gravity", GET_USE_GRAVITY_HASH)
        }

        private const val SET_GRAVITY_HASH = 743155724L
        private val setGravityBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "set_gravity", SET_GRAVITY_HASH)
        }

        private const val GET_GRAVITY_HASH = 3341600327L
        private val getGravityBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "get_gravity", GET_GRAVITY_HASH)
        }

        private const val SET_USE_COLLIDERS_HASH = 2586408642L
        private val setUseCollidersBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "set_use_colliders", SET_USE_COLLIDERS_HASH)
        }

        private const val GET_USE_COLLIDERS_HASH = 36873697L
        private val getUseCollidersBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "get_use_colliders", GET_USE_COLLIDERS_HASH)
        }

        private const val SET_COLLISION_MASK_HASH = 1286410249L
        private val setCollisionMaskBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "set_collision_mask", SET_COLLISION_MASK_HASH)
        }

        private const val GET_COLLISION_MASK_HASH = 3905245786L
        private val getCollisionMaskBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "get_collision_mask", GET_COLLISION_MASK_HASH)
        }

        private const val RESET_HASH = 3218959716L
        private val resetBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "reset", RESET_HASH)
        }

        private const val SET_JIGGLE_JOINT_BONE2D_NODE_HASH = 2761262315L
        private val setJiggleJointBone2dNodeBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "set_jiggle_joint_bone2d_node", SET_JIGGLE_JOINT_BONE2D_NODE_HASH)
        }

        private const val GET_JIGGLE_JOINT_BONE2D_NODE_HASH = 408788394L
        private val getJiggleJointBone2dNodeBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "get_jiggle_joint_bone2d_node", GET_JIGGLE_JOINT_BONE2D_NODE_HASH)
        }

        private const val SET_JIGGLE_JOINT_BONE_INDEX_HASH = 3937882851L
        private val setJiggleJointBoneIndexBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "set_jiggle_joint_bone_index", SET_JIGGLE_JOINT_BONE_INDEX_HASH)
        }

        private const val GET_JIGGLE_JOINT_BONE_INDEX_HASH = 923996154L
        private val getJiggleJointBoneIndexBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "get_jiggle_joint_bone_index", GET_JIGGLE_JOINT_BONE_INDEX_HASH)
        }

        private const val SET_JIGGLE_JOINT_OVERRIDE_HASH = 300928843L
        private val setJiggleJointOverrideBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "set_jiggle_joint_override", SET_JIGGLE_JOINT_OVERRIDE_HASH)
        }

        private const val GET_JIGGLE_JOINT_OVERRIDE_HASH = 1116898809L
        private val getJiggleJointOverrideBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "get_jiggle_joint_override", GET_JIGGLE_JOINT_OVERRIDE_HASH)
        }

        private const val SET_JIGGLE_JOINT_STIFFNESS_HASH = 1602489585L
        private val setJiggleJointStiffnessBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "set_jiggle_joint_stiffness", SET_JIGGLE_JOINT_STIFFNESS_HASH)
        }

        private const val GET_JIGGLE_JOINT_STIFFNESS_HASH = 2339986948L
        private val getJiggleJointStiffnessBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "get_jiggle_joint_stiffness", GET_JIGGLE_JOINT_STIFFNESS_HASH)
        }

        private const val SET_JIGGLE_JOINT_MASS_HASH = 1602489585L
        private val setJiggleJointMassBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "set_jiggle_joint_mass", SET_JIGGLE_JOINT_MASS_HASH)
        }

        private const val GET_JIGGLE_JOINT_MASS_HASH = 2339986948L
        private val getJiggleJointMassBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "get_jiggle_joint_mass", GET_JIGGLE_JOINT_MASS_HASH)
        }

        private const val SET_JIGGLE_JOINT_DAMPING_HASH = 1602489585L
        private val setJiggleJointDampingBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "set_jiggle_joint_damping", SET_JIGGLE_JOINT_DAMPING_HASH)
        }

        private const val GET_JIGGLE_JOINT_DAMPING_HASH = 2339986948L
        private val getJiggleJointDampingBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "get_jiggle_joint_damping", GET_JIGGLE_JOINT_DAMPING_HASH)
        }

        private const val SET_JIGGLE_JOINT_USE_GRAVITY_HASH = 300928843L
        private val setJiggleJointUseGravityBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "set_jiggle_joint_use_gravity", SET_JIGGLE_JOINT_USE_GRAVITY_HASH)
        }

        private const val GET_JIGGLE_JOINT_USE_GRAVITY_HASH = 1116898809L
        private val getJiggleJointUseGravityBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "get_jiggle_joint_use_gravity", GET_JIGGLE_JOINT_USE_GRAVITY_HASH)
        }

        private const val SET_JIGGLE_JOINT_GRAVITY_HASH = 163021252L
        private val setJiggleJointGravityBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "set_jiggle_joint_gravity", SET_JIGGLE_JOINT_GRAVITY_HASH)
        }

        private const val GET_JIGGLE_JOINT_GRAVITY_HASH = 2299179447L
        private val getJiggleJointGravityBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "get_jiggle_joint_gravity", GET_JIGGLE_JOINT_GRAVITY_HASH)
        }
    }
}
