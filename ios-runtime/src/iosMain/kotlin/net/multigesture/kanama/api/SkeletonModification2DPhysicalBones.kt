package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.NodePath

/**
 * Generated from Godot docs: SkeletonModification2DPhysicalBones
 */
class SkeletonModification2DPhysicalBones(handle: MemorySegment) : SkeletonModification2D(handle) {
    var physicalBoneChainLength: Int
        @JvmName("physicalBoneChainLengthProperty")
        get() = getPhysicalBoneChainLength()
        @JvmName("setPhysicalBoneChainLengthProperty")
        set(value) = setPhysicalBoneChainLength(value)

    fun setPhysicalBoneChainLength(length: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setPhysicalBoneChainLengthBind, handle, length)
    }

    fun getPhysicalBoneChainLength(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getPhysicalBoneChainLengthBind, handle)
    }

    fun setPhysicalBoneNode(jointIdx: Int, physicalbone2dNode: NodePath) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndNodePathArg(setPhysicalBoneNodeBind, handle, jointIdx, physicalbone2dNode)
    }

    fun fetchPhysicalBones() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(fetchPhysicalBonesBind, handle)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): SkeletonModification2DPhysicalBones? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SkeletonModification2DPhysicalBones? =
            if (handle.address() == 0L) null else SkeletonModification2DPhysicalBones(handle)

        private const val SET_PHYSICAL_BONE_CHAIN_LENGTH_HASH = 1286410249L
        private val setPhysicalBoneChainLengthBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DPhysicalBones", "set_physical_bone_chain_length", SET_PHYSICAL_BONE_CHAIN_LENGTH_HASH)
        }

        private const val GET_PHYSICAL_BONE_CHAIN_LENGTH_HASH = 2455072627L
        private val getPhysicalBoneChainLengthBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DPhysicalBones", "get_physical_bone_chain_length", GET_PHYSICAL_BONE_CHAIN_LENGTH_HASH)
        }

        private const val SET_PHYSICAL_BONE_NODE_HASH = 2761262315L
        private val setPhysicalBoneNodeBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DPhysicalBones", "set_physical_bone_node", SET_PHYSICAL_BONE_NODE_HASH)
        }

        private const val FETCH_PHYSICAL_BONES_HASH = 3218959716L
        private val fetchPhysicalBonesBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2DPhysicalBones", "fetch_physical_bones", FETCH_PHYSICAL_BONES_HASH)
        }
    }
}
