package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.RID

/**
 * Abstract base class for all 2D physics joints.
 *
 * Generated from Godot docs: Joint2D
 */
open class Joint2D(handle: MemorySegment) : Node2D(handle) {
    var nodeA: NodePath
        @JvmName("nodeAProperty")
        get() = getNodeA()
        @JvmName("setNodeAProperty")
        set(value) = setNodeA(value)

    var nodeB: NodePath
        @JvmName("nodeBProperty")
        get() = getNodeB()
        @JvmName("setNodeBProperty")
        set(value) = setNodeB(value)

    var bias: Double
        @JvmName("biasProperty")
        get() = getBias()
        @JvmName("setBiasProperty")
        set(value) = setBias(value)

    var disableCollision: Boolean
        @JvmName("disableCollisionProperty")
        get() = getExcludeNodesFromCollision()
        @JvmName("setDisableCollisionProperty")
        set(value) = setExcludeNodesFromCollision(value)

    /**
     * Path to the first body (A) attached to the joint. The node must inherit `PhysicsBody2D`.
     *
     * Generated from Godot docs: Joint2D.set_node_a
     */
    fun setNodeA(node: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setNodeABind, handle, node)
    }

    /**
     * Path to the first body (A) attached to the joint. The node must inherit `PhysicsBody2D`.
     *
     * Generated from Godot docs: Joint2D.get_node_a
     */
    fun getNodeA(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getNodeABind, handle)
    }

    /**
     * Path to the second body (B) attached to the joint. The node must inherit `PhysicsBody2D`.
     *
     * Generated from Godot docs: Joint2D.set_node_b
     */
    fun setNodeB(node: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setNodeBBind, handle, node)
    }

    /**
     * Path to the second body (B) attached to the joint. The node must inherit `PhysicsBody2D`.
     *
     * Generated from Godot docs: Joint2D.get_node_b
     */
    fun getNodeB(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getNodeBBind, handle)
    }

    /**
     * When `node_a` and `node_b` move in different directions the `bias` controls how fast the joint
     * pulls them back to their original position. The lower the `bias` the more the two bodies can
     * pull on the joint. When set to `0`, the default value from
     * `ProjectSettings.physics/2d/solver/default_constraint_bias` is used.
     *
     * Generated from Godot docs: Joint2D.set_bias
     */
    fun setBias(bias: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setBiasBind, handle, bias)
    }

    /**
     * When `node_a` and `node_b` move in different directions the `bias` controls how fast the joint
     * pulls them back to their original position. The lower the `bias` the more the two bodies can
     * pull on the joint. When set to `0`, the default value from
     * `ProjectSettings.physics/2d/solver/default_constraint_bias` is used.
     *
     * Generated from Godot docs: Joint2D.get_bias
     */
    fun getBias(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getBiasBind, handle)
    }

    /**
     * If `true`, the two bodies bound together do not collide with each other.
     *
     * Generated from Godot docs: Joint2D.set_exclude_nodes_from_collision
     */
    fun setExcludeNodesFromCollision(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setExcludeNodesFromCollisionBind, handle, enable)
    }

    /**
     * If `true`, the two bodies bound together do not collide with each other.
     *
     * Generated from Godot docs: Joint2D.get_exclude_nodes_from_collision
     */
    fun getExcludeNodesFromCollision(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getExcludeNodesFromCollisionBind, handle)
    }

    /**
     * Returns the joint's internal `RID` from the `PhysicsServer2D`.
     *
     * Generated from Godot docs: Joint2D.get_rid
     */
    fun getRid(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getRidBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): Joint2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Joint2D? =
            if (handle.address() == 0L) null else Joint2D(handle)

        private const val SET_NODE_A_HASH = 1348162250L
        private val setNodeABind by lazy {
            ObjectCalls.getMethodBind("Joint2D", "set_node_a", SET_NODE_A_HASH)
        }

        private const val GET_NODE_A_HASH = 4075236667L
        private val getNodeABind by lazy {
            ObjectCalls.getMethodBind("Joint2D", "get_node_a", GET_NODE_A_HASH)
        }

        private const val SET_NODE_B_HASH = 1348162250L
        private val setNodeBBind by lazy {
            ObjectCalls.getMethodBind("Joint2D", "set_node_b", SET_NODE_B_HASH)
        }

        private const val GET_NODE_B_HASH = 4075236667L
        private val getNodeBBind by lazy {
            ObjectCalls.getMethodBind("Joint2D", "get_node_b", GET_NODE_B_HASH)
        }

        private const val SET_BIAS_HASH = 373806689L
        private val setBiasBind by lazy {
            ObjectCalls.getMethodBind("Joint2D", "set_bias", SET_BIAS_HASH)
        }

        private const val GET_BIAS_HASH = 1740695150L
        private val getBiasBind by lazy {
            ObjectCalls.getMethodBind("Joint2D", "get_bias", GET_BIAS_HASH)
        }

        private const val SET_EXCLUDE_NODES_FROM_COLLISION_HASH = 2586408642L
        private val setExcludeNodesFromCollisionBind by lazy {
            ObjectCalls.getMethodBind("Joint2D", "set_exclude_nodes_from_collision", SET_EXCLUDE_NODES_FROM_COLLISION_HASH)
        }

        private const val GET_EXCLUDE_NODES_FROM_COLLISION_HASH = 36873697L
        private val getExcludeNodesFromCollisionBind by lazy {
            ObjectCalls.getMethodBind("Joint2D", "get_exclude_nodes_from_collision", GET_EXCLUDE_NODES_FROM_COLLISION_HASH)
        }

        private const val GET_RID_HASH = 2944877500L
        private val getRidBind by lazy {
            ObjectCalls.getMethodBind("Joint2D", "get_rid", GET_RID_HASH)
        }
    }
}
