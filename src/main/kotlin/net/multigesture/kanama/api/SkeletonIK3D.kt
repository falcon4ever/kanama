package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector3

/**
 * A node used to rotate all bones of a `Skeleton3D` bone chain a way that places the end bone at a
 * desired 3D position.
 *
 * Generated from Godot docs: SkeletonIK3D
 */
class SkeletonIK3D(handle: MemorySegment) : SkeletonModifier3D(handle) {
    var rootBone: String
        @JvmName("rootBoneProperty")
        get() = getRootBone()
        @JvmName("setRootBoneProperty")
        set(value) = setRootBone(value)

    var tipBone: String
        @JvmName("tipBoneProperty")
        get() = getTipBone()
        @JvmName("setTipBoneProperty")
        set(value) = setTipBone(value)

    var target: Transform3D
        @JvmName("targetProperty")
        get() = getTargetTransform()
        @JvmName("setTargetProperty")
        set(value) = setTargetTransform(value)

    var overrideTipBasis: Boolean
        @JvmName("overrideTipBasisProperty")
        get() = isOverrideTipBasis()
        @JvmName("setOverrideTipBasisProperty")
        set(value) = setOverrideTipBasis(value)

    var useMagnet: Boolean
        @JvmName("useMagnetProperty")
        get() = isUsingMagnet()
        @JvmName("setUseMagnetProperty")
        set(value) = setUseMagnet(value)

    var magnet: Vector3
        @JvmName("magnetProperty")
        get() = getMagnetPosition()
        @JvmName("setMagnetProperty")
        set(value) = setMagnetPosition(value)

    var targetNode: NodePath
        @JvmName("targetNodeProperty")
        get() = getTargetNode()
        @JvmName("setTargetNodeProperty")
        set(value) = setTargetNode(value)

    var minDistance: Double
        @JvmName("minDistanceProperty")
        get() = getMinDistance()
        @JvmName("setMinDistanceProperty")
        set(value) = setMinDistance(value)

    var maxIterations: Int
        @JvmName("maxIterationsProperty")
        get() = getMaxIterations()
        @JvmName("setMaxIterationsProperty")
        set(value) = setMaxIterations(value)

    var interpolation: Double
        @JvmName("interpolationProperty")
        get() = getInterpolation()
        @JvmName("setInterpolationProperty")
        set(value) = setInterpolation(value)

    fun setRootBone(rootBone: String) {
        ObjectCalls.ptrcallWithStringNameArg(setRootBoneBind, handle, rootBone)
    }

    fun getRootBone(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getRootBoneBind, handle)
    }

    fun setTipBone(tipBone: String) {
        ObjectCalls.ptrcallWithStringNameArg(setTipBoneBind, handle, tipBone)
    }

    fun getTipBone(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getTipBoneBind, handle)
    }

    fun setTargetTransform(target: Transform3D) {
        ObjectCalls.ptrcallWithTransform3DArg(setTargetTransformBind, handle, target)
    }

    fun getTargetTransform(): Transform3D {
        return ObjectCalls.ptrcallNoArgsRetTransform3D(getTargetTransformBind, handle)
    }

    fun setTargetNode(node: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setTargetNodeBind, handle, node)
    }

    fun getTargetNode(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getTargetNodeBind, handle)
    }

    fun setOverrideTipBasis(override: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setOverrideTipBasisBind, handle, override)
    }

    fun isOverrideTipBasis(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isOverrideTipBasisBind, handle)
    }

    fun setUseMagnet(use: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseMagnetBind, handle, use)
    }

    fun isUsingMagnet(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingMagnetBind, handle)
    }

    fun setMagnetPosition(localPosition: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setMagnetPositionBind, handle, localPosition)
    }

    fun getMagnetPosition(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getMagnetPositionBind, handle)
    }

    fun getParentSkeleton(): Skeleton3D? {
        return Skeleton3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getParentSkeletonBind, handle))
    }

    fun isRunning(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isRunningBind, handle)
    }

    fun setMinDistance(minDistance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMinDistanceBind, handle, minDistance)
    }

    fun getMinDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMinDistanceBind, handle)
    }

    fun setMaxIterations(iterations: Int) {
        ObjectCalls.ptrcallWithIntArg(setMaxIterationsBind, handle, iterations)
    }

    fun getMaxIterations(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxIterationsBind, handle)
    }

    /**
     * Starts applying IK effects on each frame to the `Skeleton3D` bones but will only take effect
     * starting on the next frame. If `one_time` is `true`, this will take effect immediately but also
     * reset on the next frame.
     *
     * Generated from Godot docs: SkeletonIK3D.start
     */
    fun start(oneTime: Boolean = false) {
        ObjectCalls.ptrcallWithBoolArg(startBind, handle, oneTime)
    }

    /**
     * Stops applying IK effects on each frame to the `Skeleton3D` bones and also calls
     * `Skeleton3D.clear_bones_global_pose_override` to remove existing overrides on all bones.
     *
     * Generated from Godot docs: SkeletonIK3D.stop
     */
    fun stop() {
        ObjectCalls.ptrcallNoArgs(stopBind, handle)
    }

    fun setInterpolation(interpolation: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setInterpolationBind, handle, interpolation)
    }

    fun getInterpolation(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getInterpolationBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): SkeletonIK3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SkeletonIK3D? =
            if (handle.address() == 0L) null else SkeletonIK3D(handle)

        private const val SET_ROOT_BONE_HASH = 3304788590L
        private val setRootBoneBind by lazy {
            ObjectCalls.getMethodBind("SkeletonIK3D", "set_root_bone", SET_ROOT_BONE_HASH)
        }

        private const val GET_ROOT_BONE_HASH = 2002593661L
        private val getRootBoneBind by lazy {
            ObjectCalls.getMethodBind("SkeletonIK3D", "get_root_bone", GET_ROOT_BONE_HASH)
        }

        private const val SET_TIP_BONE_HASH = 3304788590L
        private val setTipBoneBind by lazy {
            ObjectCalls.getMethodBind("SkeletonIK3D", "set_tip_bone", SET_TIP_BONE_HASH)
        }

        private const val GET_TIP_BONE_HASH = 2002593661L
        private val getTipBoneBind by lazy {
            ObjectCalls.getMethodBind("SkeletonIK3D", "get_tip_bone", GET_TIP_BONE_HASH)
        }

        private const val SET_TARGET_TRANSFORM_HASH = 2952846383L
        private val setTargetTransformBind by lazy {
            ObjectCalls.getMethodBind("SkeletonIK3D", "set_target_transform", SET_TARGET_TRANSFORM_HASH)
        }

        private const val GET_TARGET_TRANSFORM_HASH = 3229777777L
        private val getTargetTransformBind by lazy {
            ObjectCalls.getMethodBind("SkeletonIK3D", "get_target_transform", GET_TARGET_TRANSFORM_HASH)
        }

        private const val SET_TARGET_NODE_HASH = 1348162250L
        private val setTargetNodeBind by lazy {
            ObjectCalls.getMethodBind("SkeletonIK3D", "set_target_node", SET_TARGET_NODE_HASH)
        }

        private const val GET_TARGET_NODE_HASH = 277076166L
        private val getTargetNodeBind by lazy {
            ObjectCalls.getMethodBind("SkeletonIK3D", "get_target_node", GET_TARGET_NODE_HASH)
        }

        private const val SET_OVERRIDE_TIP_BASIS_HASH = 2586408642L
        private val setOverrideTipBasisBind by lazy {
            ObjectCalls.getMethodBind("SkeletonIK3D", "set_override_tip_basis", SET_OVERRIDE_TIP_BASIS_HASH)
        }

        private const val IS_OVERRIDE_TIP_BASIS_HASH = 36873697L
        private val isOverrideTipBasisBind by lazy {
            ObjectCalls.getMethodBind("SkeletonIK3D", "is_override_tip_basis", IS_OVERRIDE_TIP_BASIS_HASH)
        }

        private const val SET_USE_MAGNET_HASH = 2586408642L
        private val setUseMagnetBind by lazy {
            ObjectCalls.getMethodBind("SkeletonIK3D", "set_use_magnet", SET_USE_MAGNET_HASH)
        }

        private const val IS_USING_MAGNET_HASH = 36873697L
        private val isUsingMagnetBind by lazy {
            ObjectCalls.getMethodBind("SkeletonIK3D", "is_using_magnet", IS_USING_MAGNET_HASH)
        }

        private const val SET_MAGNET_POSITION_HASH = 3460891852L
        private val setMagnetPositionBind by lazy {
            ObjectCalls.getMethodBind("SkeletonIK3D", "set_magnet_position", SET_MAGNET_POSITION_HASH)
        }

        private const val GET_MAGNET_POSITION_HASH = 3360562783L
        private val getMagnetPositionBind by lazy {
            ObjectCalls.getMethodBind("SkeletonIK3D", "get_magnet_position", GET_MAGNET_POSITION_HASH)
        }

        private const val GET_PARENT_SKELETON_HASH = 1488626673L
        private val getParentSkeletonBind by lazy {
            ObjectCalls.getMethodBind("SkeletonIK3D", "get_parent_skeleton", GET_PARENT_SKELETON_HASH)
        }

        private const val IS_RUNNING_HASH = 2240911060L
        private val isRunningBind by lazy {
            ObjectCalls.getMethodBind("SkeletonIK3D", "is_running", IS_RUNNING_HASH)
        }

        private const val SET_MIN_DISTANCE_HASH = 373806689L
        private val setMinDistanceBind by lazy {
            ObjectCalls.getMethodBind("SkeletonIK3D", "set_min_distance", SET_MIN_DISTANCE_HASH)
        }

        private const val GET_MIN_DISTANCE_HASH = 1740695150L
        private val getMinDistanceBind by lazy {
            ObjectCalls.getMethodBind("SkeletonIK3D", "get_min_distance", GET_MIN_DISTANCE_HASH)
        }

        private const val SET_MAX_ITERATIONS_HASH = 1286410249L
        private val setMaxIterationsBind by lazy {
            ObjectCalls.getMethodBind("SkeletonIK3D", "set_max_iterations", SET_MAX_ITERATIONS_HASH)
        }

        private const val GET_MAX_ITERATIONS_HASH = 3905245786L
        private val getMaxIterationsBind by lazy {
            ObjectCalls.getMethodBind("SkeletonIK3D", "get_max_iterations", GET_MAX_ITERATIONS_HASH)
        }

        private const val START_HASH = 107499316L
        private val startBind by lazy {
            ObjectCalls.getMethodBind("SkeletonIK3D", "start", START_HASH)
        }

        private const val STOP_HASH = 3218959716L
        private val stopBind by lazy {
            ObjectCalls.getMethodBind("SkeletonIK3D", "stop", STOP_HASH)
        }

        private const val SET_INTERPOLATION_HASH = 373806689L
        private val setInterpolationBind by lazy {
            ObjectCalls.getMethodBind("SkeletonIK3D", "set_interpolation", SET_INTERPOLATION_HASH)
        }

        private const val GET_INTERPOLATION_HASH = 1740695150L
        private val getInterpolationBind by lazy {
            ObjectCalls.getMethodBind("SkeletonIK3D", "get_interpolation", GET_INTERPOLATION_HASH)
        }
    }
}
