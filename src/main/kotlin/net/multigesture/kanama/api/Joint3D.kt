package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.RID

/**
 * Abstract base class for all 3D physics joints.
 *
 * Generated from Godot docs: Joint3D
 */
open class Joint3D(handle: MemorySegment) : Node3D(handle) {
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

    fun setNodeA(node: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setNodeABind, handle, node)
    }

    fun getNodeA(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getNodeABind, handle)
    }

    fun setNodeB(node: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setNodeBBind, handle, node)
    }

    fun getNodeB(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getNodeBBind, handle)
    }

    fun setSolverPriority(priority: Int) {
        ObjectCalls.ptrcallWithIntArg(setSolverPriorityBind, handle, priority)
    }

    fun getSolverPriority(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSolverPriorityBind, handle)
    }

    fun setExcludeNodesFromCollision(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setExcludeNodesFromCollisionBind, handle, enable)
    }

    fun getExcludeNodesFromCollision(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getExcludeNodesFromCollisionBind, handle)
    }

    fun getRid(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getRidBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): Joint3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Joint3D? =
            if (handle.address() == 0L) null else Joint3D(handle)

        private const val SET_NODE_A_HASH = 1348162250L
        private val setNodeABind by lazy {
            ObjectCalls.getMethodBind("Joint3D", "set_node_a", SET_NODE_A_HASH)
        }

        private const val GET_NODE_A_HASH = 4075236667L
        private val getNodeABind by lazy {
            ObjectCalls.getMethodBind("Joint3D", "get_node_a", GET_NODE_A_HASH)
        }

        private const val SET_NODE_B_HASH = 1348162250L
        private val setNodeBBind by lazy {
            ObjectCalls.getMethodBind("Joint3D", "set_node_b", SET_NODE_B_HASH)
        }

        private const val GET_NODE_B_HASH = 4075236667L
        private val getNodeBBind by lazy {
            ObjectCalls.getMethodBind("Joint3D", "get_node_b", GET_NODE_B_HASH)
        }

        private const val SET_SOLVER_PRIORITY_HASH = 1286410249L
        private val setSolverPriorityBind by lazy {
            ObjectCalls.getMethodBind("Joint3D", "set_solver_priority", SET_SOLVER_PRIORITY_HASH)
        }

        private const val GET_SOLVER_PRIORITY_HASH = 3905245786L
        private val getSolverPriorityBind by lazy {
            ObjectCalls.getMethodBind("Joint3D", "get_solver_priority", GET_SOLVER_PRIORITY_HASH)
        }

        private const val SET_EXCLUDE_NODES_FROM_COLLISION_HASH = 2586408642L
        private val setExcludeNodesFromCollisionBind by lazy {
            ObjectCalls.getMethodBind("Joint3D", "set_exclude_nodes_from_collision", SET_EXCLUDE_NODES_FROM_COLLISION_HASH)
        }

        private const val GET_EXCLUDE_NODES_FROM_COLLISION_HASH = 36873697L
        private val getExcludeNodesFromCollisionBind by lazy {
            ObjectCalls.getMethodBind("Joint3D", "get_exclude_nodes_from_collision", GET_EXCLUDE_NODES_FROM_COLLISION_HASH)
        }

        private const val GET_RID_HASH = 2944877500L
        private val getRidBind by lazy {
            ObjectCalls.getMethodBind("Joint3D", "get_rid", GET_RID_HASH)
        }
    }
}
