package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.RID

/**
 * Abstract base class for all 3D physics joints.
 *
 * Generated from Godot docs: Joint3D
 */
open class Joint3D(handle: GodotHandle) : Node3D(handle) {
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

    var solverPriority: Int
        @JvmName("solverPriorityProperty")
        get() = getSolverPriority()
        @JvmName("setSolverPriorityProperty")
        set(value) = setSolverPriority(value)

    var excludeNodesFromCollision: Boolean
        @JvmName("excludeNodesFromCollisionProperty")
        get() = getExcludeNodesFromCollision()
        @JvmName("setExcludeNodesFromCollisionProperty")
        set(value) = setExcludeNodesFromCollision(value)

    /**
     * Path to the first node (A) attached to the joint. The node must inherit `PhysicsBody3D`. If left
     * empty and `node_b` is set, the body is attached to a fixed `StaticBody3D` without collision
     * shapes.
     *
     * Generated from Godot docs: Joint3D.set_node_a
     */
    fun setNodeA(node: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(Binds.setNodeABind, segment, node)
    }

    /**
     * Path to the first node (A) attached to the joint. The node must inherit `PhysicsBody3D`. If left
     * empty and `node_b` is set, the body is attached to a fixed `StaticBody3D` without collision
     * shapes.
     *
     * Generated from Godot docs: Joint3D.get_node_a
     */
    fun getNodeA(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(Binds.getNodeABind, segment)
    }

    /**
     * Path to the second node (B) attached to the joint. The node must inherit `PhysicsBody3D`. If
     * left empty and `node_a` is set, the body is attached to a fixed `StaticBody3D` without collision
     * shapes.
     *
     * Generated from Godot docs: Joint3D.set_node_b
     */
    fun setNodeB(node: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(Binds.setNodeBBind, segment, node)
    }

    /**
     * Path to the second node (B) attached to the joint. The node must inherit `PhysicsBody3D`. If
     * left empty and `node_a` is set, the body is attached to a fixed `StaticBody3D` without collision
     * shapes.
     *
     * Generated from Godot docs: Joint3D.get_node_b
     */
    fun getNodeB(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(Binds.getNodeBBind, segment)
    }

    /**
     * The priority used to define which solver is executed first for multiple joints. The lower the
     * value, the higher the priority. Note: Only supported when using GodotPhysics3D. This property is
     * ignored when using Jolt Physics.
     *
     * Generated from Godot docs: Joint3D.set_solver_priority
     */
    fun setSolverPriority(priority: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setSolverPriorityBind, segment, priority)
    }

    /**
     * The priority used to define which solver is executed first for multiple joints. The lower the
     * value, the higher the priority. Note: Only supported when using GodotPhysics3D. This property is
     * ignored when using Jolt Physics.
     *
     * Generated from Godot docs: Joint3D.get_solver_priority
     */
    fun getSolverPriority(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSolverPriorityBind, segment)
    }

    /**
     * If `true`, the two bodies bound together do not collide with each other.
     *
     * Generated from Godot docs: Joint3D.set_exclude_nodes_from_collision
     */
    fun setExcludeNodesFromCollision(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setExcludeNodesFromCollisionBind, segment, enable)
    }

    /**
     * If `true`, the two bodies bound together do not collide with each other.
     *
     * Generated from Godot docs: Joint3D.get_exclude_nodes_from_collision
     */
    fun getExcludeNodesFromCollision(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getExcludeNodesFromCollisionBind, segment)
    }

    /**
     * Returns the joint's internal `RID` from the `PhysicsServer3D`.
     *
     * Generated from Godot docs: Joint3D.get_rid
     */
    fun getRid(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(Binds.getRidBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Joint3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): Joint3D? =
            if (handle.address() == 0L) null else Joint3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_NODE_A_HASH = 1348162250L
        @JvmField
        val setNodeABind =
            ObjectCalls.getMethodBind("Joint3D", "set_node_a", SET_NODE_A_HASH)

        private const val GET_NODE_A_HASH = 4075236667L
        @JvmField
        val getNodeABind =
            ObjectCalls.getMethodBind("Joint3D", "get_node_a", GET_NODE_A_HASH)

        private const val SET_NODE_B_HASH = 1348162250L
        @JvmField
        val setNodeBBind =
            ObjectCalls.getMethodBind("Joint3D", "set_node_b", SET_NODE_B_HASH)

        private const val GET_NODE_B_HASH = 4075236667L
        @JvmField
        val getNodeBBind =
            ObjectCalls.getMethodBind("Joint3D", "get_node_b", GET_NODE_B_HASH)

        private const val SET_SOLVER_PRIORITY_HASH = 1286410249L
        @JvmField
        val setSolverPriorityBind =
            ObjectCalls.getMethodBind("Joint3D", "set_solver_priority", SET_SOLVER_PRIORITY_HASH)

        private const val GET_SOLVER_PRIORITY_HASH = 3905245786L
        @JvmField
        val getSolverPriorityBind =
            ObjectCalls.getMethodBind("Joint3D", "get_solver_priority", GET_SOLVER_PRIORITY_HASH)

        private const val SET_EXCLUDE_NODES_FROM_COLLISION_HASH = 2586408642L
        @JvmField
        val setExcludeNodesFromCollisionBind =
            ObjectCalls.getMethodBind("Joint3D", "set_exclude_nodes_from_collision", SET_EXCLUDE_NODES_FROM_COLLISION_HASH)

        private const val GET_EXCLUDE_NODES_FROM_COLLISION_HASH = 36873697L
        @JvmField
        val getExcludeNodesFromCollisionBind =
            ObjectCalls.getMethodBind("Joint3D", "get_exclude_nodes_from_collision", GET_EXCLUDE_NODES_FROM_COLLISION_HASH)

        private const val GET_RID_HASH = 2944877500L
        @JvmField
        val getRidBind =
            ObjectCalls.getMethodBind("Joint3D", "get_rid", GET_RID_HASH)
    }
}
