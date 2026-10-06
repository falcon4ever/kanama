package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Vector2

/**
 * A modification that jiggles `Bone2D` nodes as they move towards a target.
 *
 * Generated from Godot docs: SkeletonModification2DJiggle
 */
class SkeletonModification2DJiggle(handle: GodotHandle) : SkeletonModification2D(handle) {
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
        checkOpen()
        ObjectCalls.ptrcallWithNodePathArg(Binds.setTargetNodeBind, segment, targetNodepath)
    }

    /**
     * The NodePath to the node that is the target for the Jiggle modification. This node is what the
     * Jiggle chain will attempt to rotate the bone chain to.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.get_target_node
     */
    fun getTargetNode(): NodePath {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetNodePath(Binds.getTargetNodeBind, segment)
    }

    /**
     * The amount of Jiggle joints in the Jiggle modification.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.set_jiggle_data_chain_length
     */
    fun setJiggleDataChainLength(length: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setJiggleDataChainLengthBind, segment, length)
    }

    /**
     * The amount of Jiggle joints in the Jiggle modification.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.get_jiggle_data_chain_length
     */
    fun getJiggleDataChainLength(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getJiggleDataChainLengthBind, segment)
    }

    /**
     * The default amount of stiffness assigned to the Jiggle joints, if they are not overridden.
     * Higher values act more like springs, quickly moving into the correct position.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.set_stiffness
     */
    fun setStiffness(stiffness: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setStiffnessBind, segment, stiffness)
    }

    /**
     * The default amount of stiffness assigned to the Jiggle joints, if they are not overridden.
     * Higher values act more like springs, quickly moving into the correct position.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.get_stiffness
     */
    fun getStiffness(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getStiffnessBind, segment)
    }

    /**
     * The default amount of mass assigned to the Jiggle joints, if they are not overridden. Higher
     * values lead to faster movements and more overshooting.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.set_mass
     */
    fun setMass(mass: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setMassBind, segment, mass)
    }

    /**
     * The default amount of mass assigned to the Jiggle joints, if they are not overridden. Higher
     * values lead to faster movements and more overshooting.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.get_mass
     */
    fun getMass(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getMassBind, segment)
    }

    /**
     * The default amount of damping applied to the Jiggle joints, if they are not overridden. Higher
     * values lead to more of the calculated velocity being applied.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.set_damping
     */
    fun setDamping(damping: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDampingBind, segment, damping)
    }

    /**
     * The default amount of damping applied to the Jiggle joints, if they are not overridden. Higher
     * values lead to more of the calculated velocity being applied.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.get_damping
     */
    fun getDamping(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDampingBind, segment)
    }

    /**
     * Whether the gravity vector, `gravity`, should be applied to the Jiggle joints, assuming they are
     * not overriding the default settings.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.set_use_gravity
     */
    fun setUseGravity(useGravity: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setUseGravityBind, segment, useGravity)
    }

    /**
     * Whether the gravity vector, `gravity`, should be applied to the Jiggle joints, assuming they are
     * not overriding the default settings.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.get_use_gravity
     */
    fun getUseGravity(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getUseGravityBind, segment)
    }

    /**
     * The default amount of gravity applied to the Jiggle joints, if they are not overridden.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.set_gravity
     */
    fun setGravity(gravity: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(Binds.setGravityBind, segment, gravity)
    }

    /**
     * The default amount of gravity applied to the Jiggle joints, if they are not overridden.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.get_gravity
     */
    fun getGravity(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getGravityBind, segment)
    }

    /**
     * If `true`, the Jiggle modifier will take colliders into account, keeping them from entering into
     * these collision objects.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.set_use_colliders
     */
    fun setUseColliders(useColliders: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setUseCollidersBind, segment, useColliders)
    }

    /**
     * Returns whether the jiggle modifier is taking physics colliders into account when solving.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.get_use_colliders
     */
    fun getUseColliders(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getUseCollidersBind, segment)
    }

    /**
     * Sets the collision mask that the Jiggle modifier will use when reacting to colliders, if the
     * Jiggle modifier is set to take colliders into account.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.set_collision_mask
     */
    fun setCollisionMask(collisionMask: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setCollisionMaskBind, segment, collisionMask)
    }

    /**
     * Returns the collision mask used by the Jiggle modifier when collisions are enabled.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.get_collision_mask
     */
    fun getCollisionMask(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getCollisionMaskBind, segment)
    }

    /**
     * Resets the internal jiggle simulation state to the current bone positions, clearing velocity,
     * acceleration, and accumulated forces.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.reset
     */
    fun reset() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.resetBind, segment)
    }

    /**
     * Sets the `Bone2D` node assigned to the Jiggle joint at `joint_idx`.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.set_jiggle_joint_bone2d_node
     */
    fun setJiggleJointBone2dNode(jointIdx: Int, bone2dNode: NodePath) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndNodePathArg(Binds.setJiggleJointBone2dNodeBind, segment, jointIdx, bone2dNode)
    }

    /**
     * Returns the `Bone2D` node assigned to the Jiggle joint at `joint_idx`.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.get_jiggle_joint_bone2d_node
     */
    fun getJiggleJointBone2dNode(jointIdx: Int): NodePath {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetNodePath(Binds.getJiggleJointBone2dNodeBind, segment, jointIdx)
    }

    /**
     * Sets the bone index, `bone_idx`, of the Jiggle joint at `joint_idx`. When possible, this will
     * also update the `bone2d_node` of the Jiggle joint based on data provided by the linked skeleton.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.set_jiggle_joint_bone_index
     */
    fun setJiggleJointBoneIndex(jointIdx: Int, boneIdx: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.setJiggleJointBoneIndexBind, segment, jointIdx, boneIdx)
    }

    /**
     * Returns the index of the `Bone2D` node assigned to the Jiggle joint at `joint_idx`.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.get_jiggle_joint_bone_index
     */
    fun getJiggleJointBoneIndex(jointIdx: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getJiggleJointBoneIndexBind, segment, jointIdx)
    }

    /**
     * Sets whether the Jiggle joint at `joint_idx` should override the default Jiggle joint settings.
     * Setting this to `true` will make the joint use its own settings rather than the default ones
     * attached to the modification.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.set_jiggle_joint_override
     */
    fun setJiggleJointOverride(jointIdx: Int, override: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setJiggleJointOverrideBind, segment, jointIdx, override)
    }

    /**
     * Returns a boolean that indicates whether the joint at `joint_idx` is overriding the default
     * Jiggle joint data defined in the modification.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.get_jiggle_joint_override
     */
    fun getJiggleJointOverride(jointIdx: Int): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.getJiggleJointOverrideBind, segment, jointIdx)
    }

    /**
     * Sets the of stiffness of the Jiggle joint at `joint_idx`.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.set_jiggle_joint_stiffness
     */
    fun setJiggleJointStiffness(jointIdx: Int, stiffness: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndDoubleArg(Binds.setJiggleJointStiffnessBind, segment, jointIdx, stiffness)
    }

    /**
     * Returns the stiffness of the Jiggle joint at `joint_idx`.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.get_jiggle_joint_stiffness
     */
    fun getJiggleJointStiffness(jointIdx: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDouble(Binds.getJiggleJointStiffnessBind, segment, jointIdx)
    }

    /**
     * Sets the of mass of the Jiggle joint at `joint_idx`.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.set_jiggle_joint_mass
     */
    fun setJiggleJointMass(jointIdx: Int, mass: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndDoubleArg(Binds.setJiggleJointMassBind, segment, jointIdx, mass)
    }

    /**
     * Returns the amount of mass of the jiggle joint at `joint_idx`.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.get_jiggle_joint_mass
     */
    fun getJiggleJointMass(jointIdx: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDouble(Binds.getJiggleJointMassBind, segment, jointIdx)
    }

    /**
     * Sets the amount of damping of the Jiggle joint at `joint_idx`.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.set_jiggle_joint_damping
     */
    fun setJiggleJointDamping(jointIdx: Int, damping: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndDoubleArg(Binds.setJiggleJointDampingBind, segment, jointIdx, damping)
    }

    /**
     * Returns the amount of damping of the Jiggle joint at `joint_idx`.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.get_jiggle_joint_damping
     */
    fun getJiggleJointDamping(jointIdx: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDouble(Binds.getJiggleJointDampingBind, segment, jointIdx)
    }

    /**
     * Sets whether the Jiggle joint at `joint_idx` should use gravity.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.set_jiggle_joint_use_gravity
     */
    fun setJiggleJointUseGravity(jointIdx: Int, useGravity: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setJiggleJointUseGravityBind, segment, jointIdx, useGravity)
    }

    /**
     * Returns a boolean that indicates whether the joint at `joint_idx` is using gravity or not.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.get_jiggle_joint_use_gravity
     */
    fun getJiggleJointUseGravity(jointIdx: Int): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.getJiggleJointUseGravityBind, segment, jointIdx)
    }

    /**
     * Sets the gravity vector of the Jiggle joint at `joint_idx`.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.set_jiggle_joint_gravity
     */
    fun setJiggleJointGravity(jointIdx: Int, gravity: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndVector2Arg(Binds.setJiggleJointGravityBind, segment, jointIdx, gravity)
    }

    /**
     * Returns a `Vector2` representing the amount of gravity the Jiggle joint at `joint_idx` is
     * influenced by.
     *
     * Generated from Godot docs: SkeletonModification2DJiggle.get_jiggle_joint_gravity
     */
    fun getJiggleJointGravity(jointIdx: Int): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetVector2(Binds.getJiggleJointGravityBind, segment, jointIdx)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): SkeletonModification2DJiggle? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): SkeletonModification2DJiggle? =
            if (handle.address() == 0L) null else RefCounted.owned(SkeletonModification2DJiggle(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): SkeletonModification2DJiggle? =
            if (handle.address() == 0L) null else SkeletonModification2DJiggle(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_TARGET_NODE_HASH = 1348162250L
        @JvmField
        val setTargetNodeBind =
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "set_target_node", SET_TARGET_NODE_HASH)

        private const val GET_TARGET_NODE_HASH = 4075236667L
        @JvmField
        val getTargetNodeBind =
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "get_target_node", GET_TARGET_NODE_HASH)

        private const val SET_JIGGLE_DATA_CHAIN_LENGTH_HASH = 1286410249L
        @JvmField
        val setJiggleDataChainLengthBind =
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "set_jiggle_data_chain_length", SET_JIGGLE_DATA_CHAIN_LENGTH_HASH)

        private const val GET_JIGGLE_DATA_CHAIN_LENGTH_HASH = 2455072627L
        @JvmField
        val getJiggleDataChainLengthBind =
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "get_jiggle_data_chain_length", GET_JIGGLE_DATA_CHAIN_LENGTH_HASH)

        private const val SET_STIFFNESS_HASH = 373806689L
        @JvmField
        val setStiffnessBind =
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "set_stiffness", SET_STIFFNESS_HASH)

        private const val GET_STIFFNESS_HASH = 1740695150L
        @JvmField
        val getStiffnessBind =
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "get_stiffness", GET_STIFFNESS_HASH)

        private const val SET_MASS_HASH = 373806689L
        @JvmField
        val setMassBind =
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "set_mass", SET_MASS_HASH)

        private const val GET_MASS_HASH = 1740695150L
        @JvmField
        val getMassBind =
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "get_mass", GET_MASS_HASH)

        private const val SET_DAMPING_HASH = 373806689L
        @JvmField
        val setDampingBind =
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "set_damping", SET_DAMPING_HASH)

        private const val GET_DAMPING_HASH = 1740695150L
        @JvmField
        val getDampingBind =
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "get_damping", GET_DAMPING_HASH)

        private const val SET_USE_GRAVITY_HASH = 2586408642L
        @JvmField
        val setUseGravityBind =
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "set_use_gravity", SET_USE_GRAVITY_HASH)

        private const val GET_USE_GRAVITY_HASH = 36873697L
        @JvmField
        val getUseGravityBind =
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "get_use_gravity", GET_USE_GRAVITY_HASH)

        private const val SET_GRAVITY_HASH = 743155724L
        @JvmField
        val setGravityBind =
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "set_gravity", SET_GRAVITY_HASH)

        private const val GET_GRAVITY_HASH = 3341600327L
        @JvmField
        val getGravityBind =
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "get_gravity", GET_GRAVITY_HASH)

        private const val SET_USE_COLLIDERS_HASH = 2586408642L
        @JvmField
        val setUseCollidersBind =
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "set_use_colliders", SET_USE_COLLIDERS_HASH)

        private const val GET_USE_COLLIDERS_HASH = 36873697L
        @JvmField
        val getUseCollidersBind =
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "get_use_colliders", GET_USE_COLLIDERS_HASH)

        private const val SET_COLLISION_MASK_HASH = 1286410249L
        @JvmField
        val setCollisionMaskBind =
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "set_collision_mask", SET_COLLISION_MASK_HASH)

        private const val GET_COLLISION_MASK_HASH = 3905245786L
        @JvmField
        val getCollisionMaskBind =
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "get_collision_mask", GET_COLLISION_MASK_HASH)

        private const val RESET_HASH = 3218959716L
        @JvmField
        val resetBind =
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "reset", RESET_HASH)

        private const val SET_JIGGLE_JOINT_BONE2D_NODE_HASH = 2761262315L
        @JvmField
        val setJiggleJointBone2dNodeBind =
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "set_jiggle_joint_bone2d_node", SET_JIGGLE_JOINT_BONE2D_NODE_HASH)

        private const val GET_JIGGLE_JOINT_BONE2D_NODE_HASH = 408788394L
        @JvmField
        val getJiggleJointBone2dNodeBind =
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "get_jiggle_joint_bone2d_node", GET_JIGGLE_JOINT_BONE2D_NODE_HASH)

        private const val SET_JIGGLE_JOINT_BONE_INDEX_HASH = 3937882851L
        @JvmField
        val setJiggleJointBoneIndexBind =
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "set_jiggle_joint_bone_index", SET_JIGGLE_JOINT_BONE_INDEX_HASH)

        private const val GET_JIGGLE_JOINT_BONE_INDEX_HASH = 923996154L
        @JvmField
        val getJiggleJointBoneIndexBind =
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "get_jiggle_joint_bone_index", GET_JIGGLE_JOINT_BONE_INDEX_HASH)

        private const val SET_JIGGLE_JOINT_OVERRIDE_HASH = 300928843L
        @JvmField
        val setJiggleJointOverrideBind =
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "set_jiggle_joint_override", SET_JIGGLE_JOINT_OVERRIDE_HASH)

        private const val GET_JIGGLE_JOINT_OVERRIDE_HASH = 1116898809L
        @JvmField
        val getJiggleJointOverrideBind =
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "get_jiggle_joint_override", GET_JIGGLE_JOINT_OVERRIDE_HASH)

        private const val SET_JIGGLE_JOINT_STIFFNESS_HASH = 1602489585L
        @JvmField
        val setJiggleJointStiffnessBind =
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "set_jiggle_joint_stiffness", SET_JIGGLE_JOINT_STIFFNESS_HASH)

        private const val GET_JIGGLE_JOINT_STIFFNESS_HASH = 2339986948L
        @JvmField
        val getJiggleJointStiffnessBind =
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "get_jiggle_joint_stiffness", GET_JIGGLE_JOINT_STIFFNESS_HASH)

        private const val SET_JIGGLE_JOINT_MASS_HASH = 1602489585L
        @JvmField
        val setJiggleJointMassBind =
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "set_jiggle_joint_mass", SET_JIGGLE_JOINT_MASS_HASH)

        private const val GET_JIGGLE_JOINT_MASS_HASH = 2339986948L
        @JvmField
        val getJiggleJointMassBind =
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "get_jiggle_joint_mass", GET_JIGGLE_JOINT_MASS_HASH)

        private const val SET_JIGGLE_JOINT_DAMPING_HASH = 1602489585L
        @JvmField
        val setJiggleJointDampingBind =
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "set_jiggle_joint_damping", SET_JIGGLE_JOINT_DAMPING_HASH)

        private const val GET_JIGGLE_JOINT_DAMPING_HASH = 2339986948L
        @JvmField
        val getJiggleJointDampingBind =
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "get_jiggle_joint_damping", GET_JIGGLE_JOINT_DAMPING_HASH)

        private const val SET_JIGGLE_JOINT_USE_GRAVITY_HASH = 300928843L
        @JvmField
        val setJiggleJointUseGravityBind =
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "set_jiggle_joint_use_gravity", SET_JIGGLE_JOINT_USE_GRAVITY_HASH)

        private const val GET_JIGGLE_JOINT_USE_GRAVITY_HASH = 1116898809L
        @JvmField
        val getJiggleJointUseGravityBind =
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "get_jiggle_joint_use_gravity", GET_JIGGLE_JOINT_USE_GRAVITY_HASH)

        private const val SET_JIGGLE_JOINT_GRAVITY_HASH = 163021252L
        @JvmField
        val setJiggleJointGravityBind =
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "set_jiggle_joint_gravity", SET_JIGGLE_JOINT_GRAVITY_HASH)

        private const val GET_JIGGLE_JOINT_GRAVITY_HASH = 2299179447L
        @JvmField
        val getJiggleJointGravityBind =
            ObjectCalls.getMethodBind("SkeletonModification2DJiggle", "get_jiggle_joint_gravity", GET_JIGGLE_JOINT_GRAVITY_HASH)
    }
}
