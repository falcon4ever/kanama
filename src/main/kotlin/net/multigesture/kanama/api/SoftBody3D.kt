package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector3

/**
 * A deformable 3D physics mesh.
 *
 * Generated from Godot docs: SoftBody3D
 */
class SoftBody3D(handle: MemorySegment) : MeshInstance3D(handle) {
    var collisionLayer: Long
        @JvmName("collisionLayerProperty")
        get() = getCollisionLayer()
        @JvmName("setCollisionLayerProperty")
        set(value) = setCollisionLayer(value)

    var collisionMask: Long
        @JvmName("collisionMaskProperty")
        get() = getCollisionMask()
        @JvmName("setCollisionMaskProperty")
        set(value) = setCollisionMask(value)

    var parentCollisionIgnore: NodePath
        @JvmName("parentCollisionIgnoreProperty")
        get() = getParentCollisionIgnore()
        @JvmName("setParentCollisionIgnoreProperty")
        set(value) = setParentCollisionIgnore(value)

    var simulationPrecision: Int
        @JvmName("simulationPrecisionProperty")
        get() = getSimulationPrecision()
        @JvmName("setSimulationPrecisionProperty")
        set(value) = setSimulationPrecision(value)

    var totalMass: Double
        @JvmName("totalMassProperty")
        get() = getTotalMass()
        @JvmName("setTotalMassProperty")
        set(value) = setTotalMass(value)

    var linearStiffness: Double
        @JvmName("linearStiffnessProperty")
        get() = getLinearStiffness()
        @JvmName("setLinearStiffnessProperty")
        set(value) = setLinearStiffness(value)

    var shrinkingFactor: Double
        @JvmName("shrinkingFactorProperty")
        get() = getShrinkingFactor()
        @JvmName("setShrinkingFactorProperty")
        set(value) = setShrinkingFactor(value)

    var pressureCoefficient: Double
        @JvmName("pressureCoefficientProperty")
        get() = getPressureCoefficient()
        @JvmName("setPressureCoefficientProperty")
        set(value) = setPressureCoefficient(value)

    var dampingCoefficient: Double
        @JvmName("dampingCoefficientProperty")
        get() = getDampingCoefficient()
        @JvmName("setDampingCoefficientProperty")
        set(value) = setDampingCoefficient(value)

    var dragCoefficient: Double
        @JvmName("dragCoefficientProperty")
        get() = getDragCoefficient()
        @JvmName("setDragCoefficientProperty")
        set(value) = setDragCoefficient(value)

    var rayPickable: Boolean
        @JvmName("rayPickableProperty")
        get() = isRayPickable()
        @JvmName("setRayPickableProperty")
        set(value) = setRayPickable(value)

    var disableMode: Long
        @JvmName("disableModeProperty")
        get() = getDisableMode()
        @JvmName("setDisableModeProperty")
        set(value) = setDisableMode(value)

    fun getPhysicsRid(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getPhysicsRidBind, handle)
    }

    fun setCollisionMask(collisionMask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setCollisionMaskBind, handle, collisionMask)
    }

    fun getCollisionMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getCollisionMaskBind, handle)
    }

    fun setCollisionLayer(collisionLayer: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setCollisionLayerBind, handle, collisionLayer)
    }

    fun getCollisionLayer(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getCollisionLayerBind, handle)
    }

    fun setCollisionMaskValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setCollisionMaskValueBind, handle, layerNumber, value)
    }

    fun getCollisionMaskValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getCollisionMaskValueBind, handle, layerNumber)
    }

    fun setCollisionLayerValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setCollisionLayerValueBind, handle, layerNumber, value)
    }

    fun getCollisionLayerValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getCollisionLayerValueBind, handle, layerNumber)
    }

    fun setParentCollisionIgnore(parentCollisionIgnore: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setParentCollisionIgnoreBind, handle, parentCollisionIgnore)
    }

    fun getParentCollisionIgnore(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getParentCollisionIgnoreBind, handle)
    }

    fun setDisableMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setDisableModeBind, handle, mode)
    }

    fun getDisableMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDisableModeBind, handle)
    }

    fun getCollisionExceptions(): List<PhysicsBody3D> {
        return ObjectCalls.ptrcallNoArgsRetTypedPhysicsBody3DList(getCollisionExceptionsBind, handle)
    }

    fun addCollisionExceptionWith(body: Node) {
        ObjectCalls.ptrcallWithObjectArgs(addCollisionExceptionWithBind, handle, listOf(body.handle))
    }

    fun removeCollisionExceptionWith(body: Node) {
        ObjectCalls.ptrcallWithObjectArgs(removeCollisionExceptionWithBind, handle, listOf(body.handle))
    }

    fun setSimulationPrecision(simulationPrecision: Int) {
        ObjectCalls.ptrcallWithIntArg(setSimulationPrecisionBind, handle, simulationPrecision)
    }

    fun getSimulationPrecision(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSimulationPrecisionBind, handle)
    }

    fun setTotalMass(mass: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTotalMassBind, handle, mass)
    }

    fun getTotalMass(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTotalMassBind, handle)
    }

    fun setLinearStiffness(linearStiffness: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setLinearStiffnessBind, handle, linearStiffness)
    }

    fun getLinearStiffness(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLinearStiffnessBind, handle)
    }

    fun setShrinkingFactor(shrinkingFactor: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setShrinkingFactorBind, handle, shrinkingFactor)
    }

    fun getShrinkingFactor(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getShrinkingFactorBind, handle)
    }

    fun setPressureCoefficient(pressureCoefficient: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPressureCoefficientBind, handle, pressureCoefficient)
    }

    fun getPressureCoefficient(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPressureCoefficientBind, handle)
    }

    fun setDampingCoefficient(dampingCoefficient: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDampingCoefficientBind, handle, dampingCoefficient)
    }

    fun getDampingCoefficient(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDampingCoefficientBind, handle)
    }

    fun setDragCoefficient(dragCoefficient: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDragCoefficientBind, handle, dragCoefficient)
    }

    fun getDragCoefficient(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDragCoefficientBind, handle)
    }

    fun getPointTransform(pointIndex: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getPointTransformBind, handle, pointIndex)
    }

    fun applyImpulse(pointIndex: Int, impulse: Vector3) {
        ObjectCalls.ptrcallWithIntAndVector3Arg(applyImpulseBind, handle, pointIndex, impulse)
    }

    fun applyForce(pointIndex: Int, force: Vector3) {
        ObjectCalls.ptrcallWithIntAndVector3Arg(applyForceBind, handle, pointIndex, force)
    }

    fun applyCentralImpulse(impulse: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(applyCentralImpulseBind, handle, impulse)
    }

    fun applyCentralForce(force: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(applyCentralForceBind, handle, force)
    }

    fun setPointPinned(pointIndex: Int, pinned: Boolean, attachmentPath: NodePath, insertAt: Int = -1) {
        ObjectCalls.ptrcallWithIntBoolNodePathIntArgs(setPointPinnedBind, handle, pointIndex, pinned, attachmentPath, insertAt)
    }

    fun isPointPinned(pointIndex: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isPointPinnedBind, handle, pointIndex)
    }

    fun setRayPickable(rayPickable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setRayPickableBind, handle, rayPickable)
    }

    fun isRayPickable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isRayPickableBind, handle)
    }

    companion object {
        const val DISABLE_MODE_REMOVE: Long = 0L
        const val DISABLE_MODE_KEEP_ACTIVE: Long = 1L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): SoftBody3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SoftBody3D? =
            if (handle.address() == 0L) null else SoftBody3D(handle)

        private const val GET_PHYSICS_RID_HASH = 2944877500L
        private val getPhysicsRidBind by lazy {
            ObjectCalls.getMethodBind("SoftBody3D", "get_physics_rid", GET_PHYSICS_RID_HASH)
        }

        private const val SET_COLLISION_MASK_HASH = 1286410249L
        private val setCollisionMaskBind by lazy {
            ObjectCalls.getMethodBind("SoftBody3D", "set_collision_mask", SET_COLLISION_MASK_HASH)
        }

        private const val GET_COLLISION_MASK_HASH = 3905245786L
        private val getCollisionMaskBind by lazy {
            ObjectCalls.getMethodBind("SoftBody3D", "get_collision_mask", GET_COLLISION_MASK_HASH)
        }

        private const val SET_COLLISION_LAYER_HASH = 1286410249L
        private val setCollisionLayerBind by lazy {
            ObjectCalls.getMethodBind("SoftBody3D", "set_collision_layer", SET_COLLISION_LAYER_HASH)
        }

        private const val GET_COLLISION_LAYER_HASH = 3905245786L
        private val getCollisionLayerBind by lazy {
            ObjectCalls.getMethodBind("SoftBody3D", "get_collision_layer", GET_COLLISION_LAYER_HASH)
        }

        private const val SET_COLLISION_MASK_VALUE_HASH = 300928843L
        private val setCollisionMaskValueBind by lazy {
            ObjectCalls.getMethodBind("SoftBody3D", "set_collision_mask_value", SET_COLLISION_MASK_VALUE_HASH)
        }

        private const val GET_COLLISION_MASK_VALUE_HASH = 1116898809L
        private val getCollisionMaskValueBind by lazy {
            ObjectCalls.getMethodBind("SoftBody3D", "get_collision_mask_value", GET_COLLISION_MASK_VALUE_HASH)
        }

        private const val SET_COLLISION_LAYER_VALUE_HASH = 300928843L
        private val setCollisionLayerValueBind by lazy {
            ObjectCalls.getMethodBind("SoftBody3D", "set_collision_layer_value", SET_COLLISION_LAYER_VALUE_HASH)
        }

        private const val GET_COLLISION_LAYER_VALUE_HASH = 1116898809L
        private val getCollisionLayerValueBind by lazy {
            ObjectCalls.getMethodBind("SoftBody3D", "get_collision_layer_value", GET_COLLISION_LAYER_VALUE_HASH)
        }

        private const val SET_PARENT_COLLISION_IGNORE_HASH = 1348162250L
        private val setParentCollisionIgnoreBind by lazy {
            ObjectCalls.getMethodBind("SoftBody3D", "set_parent_collision_ignore", SET_PARENT_COLLISION_IGNORE_HASH)
        }

        private const val GET_PARENT_COLLISION_IGNORE_HASH = 4075236667L
        private val getParentCollisionIgnoreBind by lazy {
            ObjectCalls.getMethodBind("SoftBody3D", "get_parent_collision_ignore", GET_PARENT_COLLISION_IGNORE_HASH)
        }

        private const val SET_DISABLE_MODE_HASH = 1104158384L
        private val setDisableModeBind by lazy {
            ObjectCalls.getMethodBind("SoftBody3D", "set_disable_mode", SET_DISABLE_MODE_HASH)
        }

        private const val GET_DISABLE_MODE_HASH = 4135042476L
        private val getDisableModeBind by lazy {
            ObjectCalls.getMethodBind("SoftBody3D", "get_disable_mode", GET_DISABLE_MODE_HASH)
        }

        private const val GET_COLLISION_EXCEPTIONS_HASH = 2915620761L
        private val getCollisionExceptionsBind by lazy {
            ObjectCalls.getMethodBind("SoftBody3D", "get_collision_exceptions", GET_COLLISION_EXCEPTIONS_HASH)
        }

        private const val ADD_COLLISION_EXCEPTION_WITH_HASH = 1078189570L
        private val addCollisionExceptionWithBind by lazy {
            ObjectCalls.getMethodBind("SoftBody3D", "add_collision_exception_with", ADD_COLLISION_EXCEPTION_WITH_HASH)
        }

        private const val REMOVE_COLLISION_EXCEPTION_WITH_HASH = 1078189570L
        private val removeCollisionExceptionWithBind by lazy {
            ObjectCalls.getMethodBind("SoftBody3D", "remove_collision_exception_with", REMOVE_COLLISION_EXCEPTION_WITH_HASH)
        }

        private const val SET_SIMULATION_PRECISION_HASH = 1286410249L
        private val setSimulationPrecisionBind by lazy {
            ObjectCalls.getMethodBind("SoftBody3D", "set_simulation_precision", SET_SIMULATION_PRECISION_HASH)
        }

        private const val GET_SIMULATION_PRECISION_HASH = 2455072627L
        private val getSimulationPrecisionBind by lazy {
            ObjectCalls.getMethodBind("SoftBody3D", "get_simulation_precision", GET_SIMULATION_PRECISION_HASH)
        }

        private const val SET_TOTAL_MASS_HASH = 373806689L
        private val setTotalMassBind by lazy {
            ObjectCalls.getMethodBind("SoftBody3D", "set_total_mass", SET_TOTAL_MASS_HASH)
        }

        private const val GET_TOTAL_MASS_HASH = 191475506L
        private val getTotalMassBind by lazy {
            ObjectCalls.getMethodBind("SoftBody3D", "get_total_mass", GET_TOTAL_MASS_HASH)
        }

        private const val SET_LINEAR_STIFFNESS_HASH = 373806689L
        private val setLinearStiffnessBind by lazy {
            ObjectCalls.getMethodBind("SoftBody3D", "set_linear_stiffness", SET_LINEAR_STIFFNESS_HASH)
        }

        private const val GET_LINEAR_STIFFNESS_HASH = 191475506L
        private val getLinearStiffnessBind by lazy {
            ObjectCalls.getMethodBind("SoftBody3D", "get_linear_stiffness", GET_LINEAR_STIFFNESS_HASH)
        }

        private const val SET_SHRINKING_FACTOR_HASH = 373806689L
        private val setShrinkingFactorBind by lazy {
            ObjectCalls.getMethodBind("SoftBody3D", "set_shrinking_factor", SET_SHRINKING_FACTOR_HASH)
        }

        private const val GET_SHRINKING_FACTOR_HASH = 191475506L
        private val getShrinkingFactorBind by lazy {
            ObjectCalls.getMethodBind("SoftBody3D", "get_shrinking_factor", GET_SHRINKING_FACTOR_HASH)
        }

        private const val SET_PRESSURE_COEFFICIENT_HASH = 373806689L
        private val setPressureCoefficientBind by lazy {
            ObjectCalls.getMethodBind("SoftBody3D", "set_pressure_coefficient", SET_PRESSURE_COEFFICIENT_HASH)
        }

        private const val GET_PRESSURE_COEFFICIENT_HASH = 191475506L
        private val getPressureCoefficientBind by lazy {
            ObjectCalls.getMethodBind("SoftBody3D", "get_pressure_coefficient", GET_PRESSURE_COEFFICIENT_HASH)
        }

        private const val SET_DAMPING_COEFFICIENT_HASH = 373806689L
        private val setDampingCoefficientBind by lazy {
            ObjectCalls.getMethodBind("SoftBody3D", "set_damping_coefficient", SET_DAMPING_COEFFICIENT_HASH)
        }

        private const val GET_DAMPING_COEFFICIENT_HASH = 191475506L
        private val getDampingCoefficientBind by lazy {
            ObjectCalls.getMethodBind("SoftBody3D", "get_damping_coefficient", GET_DAMPING_COEFFICIENT_HASH)
        }

        private const val SET_DRAG_COEFFICIENT_HASH = 373806689L
        private val setDragCoefficientBind by lazy {
            ObjectCalls.getMethodBind("SoftBody3D", "set_drag_coefficient", SET_DRAG_COEFFICIENT_HASH)
        }

        private const val GET_DRAG_COEFFICIENT_HASH = 191475506L
        private val getDragCoefficientBind by lazy {
            ObjectCalls.getMethodBind("SoftBody3D", "get_drag_coefficient", GET_DRAG_COEFFICIENT_HASH)
        }

        private const val GET_POINT_TRANSFORM_HASH = 871989493L
        private val getPointTransformBind by lazy {
            ObjectCalls.getMethodBind("SoftBody3D", "get_point_transform", GET_POINT_TRANSFORM_HASH)
        }

        private const val APPLY_IMPULSE_HASH = 1530502735L
        private val applyImpulseBind by lazy {
            ObjectCalls.getMethodBind("SoftBody3D", "apply_impulse", APPLY_IMPULSE_HASH)
        }

        private const val APPLY_FORCE_HASH = 1530502735L
        private val applyForceBind by lazy {
            ObjectCalls.getMethodBind("SoftBody3D", "apply_force", APPLY_FORCE_HASH)
        }

        private const val APPLY_CENTRAL_IMPULSE_HASH = 3460891852L
        private val applyCentralImpulseBind by lazy {
            ObjectCalls.getMethodBind("SoftBody3D", "apply_central_impulse", APPLY_CENTRAL_IMPULSE_HASH)
        }

        private const val APPLY_CENTRAL_FORCE_HASH = 3460891852L
        private val applyCentralForceBind by lazy {
            ObjectCalls.getMethodBind("SoftBody3D", "apply_central_force", APPLY_CENTRAL_FORCE_HASH)
        }

        private const val SET_POINT_PINNED_HASH = 528784402L
        private val setPointPinnedBind by lazy {
            ObjectCalls.getMethodBind("SoftBody3D", "set_point_pinned", SET_POINT_PINNED_HASH)
        }

        private const val IS_POINT_PINNED_HASH = 1116898809L
        private val isPointPinnedBind by lazy {
            ObjectCalls.getMethodBind("SoftBody3D", "is_point_pinned", IS_POINT_PINNED_HASH)
        }

        private const val SET_RAY_PICKABLE_HASH = 2586408642L
        private val setRayPickableBind by lazy {
            ObjectCalls.getMethodBind("SoftBody3D", "set_ray_pickable", SET_RAY_PICKABLE_HASH)
        }

        private const val IS_RAY_PICKABLE_HASH = 36873697L
        private val isRayPickableBind by lazy {
            ObjectCalls.getMethodBind("SoftBody3D", "is_ray_pickable", IS_RAY_PICKABLE_HASH)
        }
    }
}
