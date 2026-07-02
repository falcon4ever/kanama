package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: CSGShape3D
 */
open class CSGShape3D(handle: MemorySegment) : GeometryInstance3D(handle) {
    var autosmooth: Boolean
        @JvmName("autosmoothProperty")
        get() = isAutosmooth()
        @JvmName("setAutosmoothProperty")
        set(value) = setAutosmooth(value)

    var smoothingAngle: Double
        @JvmName("smoothingAngleProperty")
        get() = getSmoothingAngle()
        @JvmName("setSmoothingAngleProperty")
        set(value) = setSmoothingAngle(value)

    var operation: Long
        @JvmName("operationProperty")
        get() = getOperation()
        @JvmName("setOperationProperty")
        set(value) = setOperation(value)

    var snap: Double
        @JvmName("snapProperty")
        get() = getSnap()
        @JvmName("setSnapProperty")
        set(value) = setSnap(value)

    var calculateTangents: Boolean
        @JvmName("calculateTangentsProperty")
        get() = isCalculatingTangents()
        @JvmName("setCalculateTangentsProperty")
        set(value) = setCalculateTangents(value)

    var useCollision: Boolean
        @JvmName("useCollisionProperty")
        get() = isUsingCollision()
        @JvmName("setUseCollisionProperty")
        set(value) = setUseCollision(value)

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

    var collisionPriority: Double
        @JvmName("collisionPriorityProperty")
        get() = getCollisionPriority()
        @JvmName("setCollisionPriorityProperty")
        set(value) = setCollisionPriority(value)

    fun isRootShape(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isRootShapeBind, handle)
    }

    fun setOperation(operation: Long) {
        ObjectCalls.ptrcallWithLongArg(setOperationBind, handle, operation)
    }

    fun getOperation(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getOperationBind, handle)
    }

    fun setSnap(snap: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSnapBind, handle, snap)
    }

    fun getSnap(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSnapBind, handle)
    }

    fun setUseCollision(operation: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseCollisionBind, handle, operation)
    }

    fun isUsingCollision(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingCollisionBind, handle)
    }

    fun setCollisionLayer(layer: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setCollisionLayerBind, handle, layer)
    }

    fun getCollisionLayer(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getCollisionLayerBind, handle)
    }

    fun setCollisionMask(mask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setCollisionMaskBind, handle, mask)
    }

    fun getCollisionMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getCollisionMaskBind, handle)
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

    fun setCollisionPriority(priority: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setCollisionPriorityBind, handle, priority)
    }

    fun getCollisionPriority(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getCollisionPriorityBind, handle)
    }

    fun bakeCollisionShape(): ConcavePolygonShape3D? {
        return ConcavePolygonShape3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(bakeCollisionShapeBind, handle))
    }

    fun setCalculateTangents(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCalculateTangentsBind, handle, enabled)
    }

    fun isCalculatingTangents(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCalculatingTangentsBind, handle)
    }

    fun getMeshes(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(getMeshesBind, handle)
    }

    fun bakeStaticMesh(): ArrayMesh? {
        return ArrayMesh.wrap(ObjectCalls.ptrcallNoArgsRetObject(bakeStaticMeshBind, handle))
    }

    fun setAutosmooth(autosmooth: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAutosmoothBind, handle, autosmooth)
    }

    fun isAutosmooth(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAutosmoothBind, handle)
    }

    fun setSmoothingAngle(smoothingAngle: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSmoothingAngleBind, handle, smoothingAngle)
    }

    fun getSmoothingAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSmoothingAngleBind, handle)
    }

    companion object {
        const val OPERATION_UNION: Long = 0L
        const val OPERATION_INTERSECTION: Long = 1L
        const val OPERATION_SUBTRACTION: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): CSGShape3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CSGShape3D? =
            if (handle.address() == 0L) null else CSGShape3D(handle)

        private const val IS_ROOT_SHAPE_HASH = 36873697L
        private val isRootShapeBind by lazy {
            ObjectCalls.getMethodBind("CSGShape3D", "is_root_shape", IS_ROOT_SHAPE_HASH)
        }

        private const val SET_OPERATION_HASH = 811425055L
        private val setOperationBind by lazy {
            ObjectCalls.getMethodBind("CSGShape3D", "set_operation", SET_OPERATION_HASH)
        }

        private const val GET_OPERATION_HASH = 2662425879L
        private val getOperationBind by lazy {
            ObjectCalls.getMethodBind("CSGShape3D", "get_operation", GET_OPERATION_HASH)
        }

        private const val SET_SNAP_HASH = 373806689L
        private val setSnapBind by lazy {
            ObjectCalls.getMethodBind("CSGShape3D", "set_snap", SET_SNAP_HASH)
        }

        private const val GET_SNAP_HASH = 1740695150L
        private val getSnapBind by lazy {
            ObjectCalls.getMethodBind("CSGShape3D", "get_snap", GET_SNAP_HASH)
        }

        private const val SET_USE_COLLISION_HASH = 2586408642L
        private val setUseCollisionBind by lazy {
            ObjectCalls.getMethodBind("CSGShape3D", "set_use_collision", SET_USE_COLLISION_HASH)
        }

        private const val IS_USING_COLLISION_HASH = 36873697L
        private val isUsingCollisionBind by lazy {
            ObjectCalls.getMethodBind("CSGShape3D", "is_using_collision", IS_USING_COLLISION_HASH)
        }

        private const val SET_COLLISION_LAYER_HASH = 1286410249L
        private val setCollisionLayerBind by lazy {
            ObjectCalls.getMethodBind("CSGShape3D", "set_collision_layer", SET_COLLISION_LAYER_HASH)
        }

        private const val GET_COLLISION_LAYER_HASH = 3905245786L
        private val getCollisionLayerBind by lazy {
            ObjectCalls.getMethodBind("CSGShape3D", "get_collision_layer", GET_COLLISION_LAYER_HASH)
        }

        private const val SET_COLLISION_MASK_HASH = 1286410249L
        private val setCollisionMaskBind by lazy {
            ObjectCalls.getMethodBind("CSGShape3D", "set_collision_mask", SET_COLLISION_MASK_HASH)
        }

        private const val GET_COLLISION_MASK_HASH = 3905245786L
        private val getCollisionMaskBind by lazy {
            ObjectCalls.getMethodBind("CSGShape3D", "get_collision_mask", GET_COLLISION_MASK_HASH)
        }

        private const val SET_COLLISION_MASK_VALUE_HASH = 300928843L
        private val setCollisionMaskValueBind by lazy {
            ObjectCalls.getMethodBind("CSGShape3D", "set_collision_mask_value", SET_COLLISION_MASK_VALUE_HASH)
        }

        private const val GET_COLLISION_MASK_VALUE_HASH = 1116898809L
        private val getCollisionMaskValueBind by lazy {
            ObjectCalls.getMethodBind("CSGShape3D", "get_collision_mask_value", GET_COLLISION_MASK_VALUE_HASH)
        }

        private const val SET_COLLISION_LAYER_VALUE_HASH = 300928843L
        private val setCollisionLayerValueBind by lazy {
            ObjectCalls.getMethodBind("CSGShape3D", "set_collision_layer_value", SET_COLLISION_LAYER_VALUE_HASH)
        }

        private const val GET_COLLISION_LAYER_VALUE_HASH = 1116898809L
        private val getCollisionLayerValueBind by lazy {
            ObjectCalls.getMethodBind("CSGShape3D", "get_collision_layer_value", GET_COLLISION_LAYER_VALUE_HASH)
        }

        private const val SET_COLLISION_PRIORITY_HASH = 373806689L
        private val setCollisionPriorityBind by lazy {
            ObjectCalls.getMethodBind("CSGShape3D", "set_collision_priority", SET_COLLISION_PRIORITY_HASH)
        }

        private const val GET_COLLISION_PRIORITY_HASH = 1740695150L
        private val getCollisionPriorityBind by lazy {
            ObjectCalls.getMethodBind("CSGShape3D", "get_collision_priority", GET_COLLISION_PRIORITY_HASH)
        }

        private const val BAKE_COLLISION_SHAPE_HASH = 36102322L
        private val bakeCollisionShapeBind by lazy {
            ObjectCalls.getMethodBind("CSGShape3D", "bake_collision_shape", BAKE_COLLISION_SHAPE_HASH)
        }

        private const val SET_CALCULATE_TANGENTS_HASH = 2586408642L
        private val setCalculateTangentsBind by lazy {
            ObjectCalls.getMethodBind("CSGShape3D", "set_calculate_tangents", SET_CALCULATE_TANGENTS_HASH)
        }

        private const val IS_CALCULATING_TANGENTS_HASH = 36873697L
        private val isCalculatingTangentsBind by lazy {
            ObjectCalls.getMethodBind("CSGShape3D", "is_calculating_tangents", IS_CALCULATING_TANGENTS_HASH)
        }

        private const val GET_MESHES_HASH = 3995934104L
        private val getMeshesBind by lazy {
            ObjectCalls.getMethodBind("CSGShape3D", "get_meshes", GET_MESHES_HASH)
        }

        private const val BAKE_STATIC_MESH_HASH = 1605880883L
        private val bakeStaticMeshBind by lazy {
            ObjectCalls.getMethodBind("CSGShape3D", "bake_static_mesh", BAKE_STATIC_MESH_HASH)
        }

        private const val SET_AUTOSMOOTH_HASH = 2586408642L
        private val setAutosmoothBind by lazy {
            ObjectCalls.getMethodBind("CSGShape3D", "set_autosmooth", SET_AUTOSMOOTH_HASH)
        }

        private const val IS_AUTOSMOOTH_HASH = 36873697L
        private val isAutosmoothBind by lazy {
            ObjectCalls.getMethodBind("CSGShape3D", "is_autosmooth", IS_AUTOSMOOTH_HASH)
        }

        private const val SET_SMOOTHING_ANGLE_HASH = 373806689L
        private val setSmoothingAngleBind by lazy {
            ObjectCalls.getMethodBind("CSGShape3D", "set_smoothing_angle", SET_SMOOTHING_ANGLE_HASH)
        }

        private const val GET_SMOOTHING_ANGLE_HASH = 1740695150L
        private val getSmoothingAngleBind by lazy {
            ObjectCalls.getMethodBind("CSGShape3D", "get_smoothing_angle", GET_SMOOTHING_ANGLE_HASH)
        }
    }
}
